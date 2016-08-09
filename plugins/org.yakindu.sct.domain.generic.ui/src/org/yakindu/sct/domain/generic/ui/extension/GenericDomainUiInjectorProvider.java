/**
 * Copyright (c) 2015 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.sct.domain.generic.ui.extension;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.yakindu.sct.domain.extension.DomainRegistry;
import org.yakindu.sct.domain.generic.extension.GenericDomainInjectorProvider;
import org.yakindu.sct.domain.generic.modules.EntryRuleRuntimeModule;
import org.yakindu.sct.domain.generic.ui.modules.EntryRuleUIModule;
import org.yakindu.sct.domain.generic.ui.modules.GenericEditorModule;
import org.yakindu.sct.domain.generic.ui.modules.GenericSimulationModule;
import org.yakindu.sct.domain.ui.extension.IDomainUiInjectorProvider;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.stext.stext.Guard;
import org.yakindu.sct.model.stext.stext.StateSpecification;
import org.yakindu.sct.model.stext.stext.StatechartSpecification;
import org.yakindu.sct.model.stext.stext.TransitionSpecification;
import org.yakindu.sct.model.stext.ui.STextUiModule;
import org.yakindu.sct.model.stext.ui.internal.STextActivator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

/**
 * 
 * @author andreas muelder - Initial contribution and API
 * @author terfloth - added editorInjector
 * 
 */
public class GenericDomainUiInjectorProvider implements IDomainUiInjectorProvider {

	private static final Map<String, Class<? extends EObject>> semanticTargetToRuleMap = new HashMap<String, Class<? extends EObject>>();

	private Map<String, Injector> embeddedInjectors = new HashMap<String, Injector>();

	static {
		semanticTargetToRuleMap.put(Statechart.class.getName(), StatechartSpecification.class);
		semanticTargetToRuleMap.put(Transition.class.getName(), TransitionSpecification.class);
		semanticTargetToRuleMap.put(State.class.getName(), StateSpecification.class);
		semanticTargetToRuleMap.put(Guard.class.getName(), Guard.class);
	}

	public Module getSharedStateModule() {
		return new SharedStateModule();
	}

	public Module getLanguageUIModule() {
		return new STextUiModule(STextActivator.getInstance());
	}

	public Module getSimulationModule() {
		return new GenericSimulationModule();
	}

	protected Module getResourceModule() {
		GenericDomainInjectorProvider coreProvider = (GenericDomainInjectorProvider)DomainRegistry.getDomainDescriptor("org.yakindu.domain.default").getDomainInjectorProvider();
		Module uiModule = Modules.override(coreProvider.getLanguageRuntimeModule()).with(getLanguageUIModule());
		Module result = Modules.override(uiModule).with(getSharedStateModule());
		return Modules.override(result).with(coreProvider.getTypeSystemModule());
	}

	protected Module getEmbeddedEditorModule(Class<? extends EObject> rule) {
		Module resource = getResourceModule();
		return Modules.override(resource).with(new EntryRuleRuntimeModule(rule), new EntryRuleUIModule(rule));
	}

	@Override
	public Injector getEmbeddedEditorInjector(String semanticTarget) {
		if (embeddedInjectors.get(semanticTarget) == null) {
			Class<? extends EObject> rule = semanticTargetToRuleMap.get(semanticTarget);
			org.eclipse.core.runtime.Assert.isNotNull(rule);
			embeddedInjectors.put(semanticTarget, Guice.createInjector(getEmbeddedEditorModule(rule)));
		}
		return embeddedInjectors.get(semanticTarget);
	}


	@Override
	public Injector getSimulationInjector() {
		return Guice.createInjector(getSimulationModule());
	}

	@Override
	public Injector getEditorInjector() {
		return Guice.createInjector(new GenericEditorModule());
	}
}
