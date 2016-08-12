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
package org.yakindu.sct.domain.generic.extension;

import org.yakindu.sct.domain.extension.DomainRegistry;
import org.yakindu.sct.domain.extension.IDomainDescriptor;
import org.yakindu.sct.domain.extension.IDomainInjectorProvider;
import org.yakindu.sct.domain.generic.modules.GenericSequencerModule;
import org.yakindu.sct.domain.generic.modules.GenericTypeSystemModule;
import org.yakindu.sct.model.stext.STextRuntimeModule;

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
public class GenericDomainInjectorProvider implements IDomainInjectorProvider {


	
	private Injector resourceInjector;
	
	public Module getLanguageRuntimeModule() {
		return new STextRuntimeModule();
	}

	public Module getTypeSystemModule() {
		return new GenericTypeSystemModule();
	}


	public Module getSequencerModule() {
		return new GenericSequencerModule();
	}

	public Module getGeneratorModule(String generatorId) {
		// the indirection over DomainRegistry is done to decouple concrete FileSystemAccess implementations from generic domain
		return DomainRegistry.getDefaultmodule(IDomainDescriptor.GENERATOR_MODULE);
	}

	protected Module getResourceModule() {
		return Modules.override(getLanguageRuntimeModule()).with(getTypeSystemModule());
	}

	@Override
	public Injector getResourceInjector() {
		if (resourceInjector == null)
			resourceInjector = Guice.createInjector(getResourceModule());
		return resourceInjector;
	}

	@Override
	public Injector getSequencerInjector() {
		return Guice.createInjector(getSequencerModule());
	}

	@Override
	public Injector getSequencerInjector(Module overrides) {
		if (overrides != null) {
			return Guice.createInjector(Modules.override(getSequencerModule()).with(overrides));
		}
		return getSequencerInjector();
	}

	@Override
	public Injector getGeneratorInjector(String generatorId) {
		return getGeneratorInjector(generatorId, null);
	}

	@Override
	public Injector getGeneratorInjector(String generatorId, Module baseModule) {
		Module genModule = getGeneratorModule(generatorId);

		if (baseModule != null) {
			genModule = Modules.override(baseModule).with(genModule);
		} 
		
		genModule = Modules.override(getSequencerModule()).with(genModule);
		return Guice.createInjector(genModule);
	}

}
