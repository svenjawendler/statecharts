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
package org.yakindu.sct.domain.extension;

import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * @author andreas muelder - Initial contribution and API
 * @author terfloth
 * @author jdicks - seperate ui and core
 * 
 */
public interface IDomainInjectorProvider {

	/**
	 * Returns the injector used for injection the {@link AbstractSCTResource}
	 * 
	 */
	public Injector getResourceInjector();

	/**
	 * Returns the Injector for the model sequencing without overriding existing
	 * bindings.
	 */
	public Injector getSequencerInjector();

	/**
	 * Returns the Injector for the model sequencing, giving precedence to those
	 * bindings in the overrides module.
	 */
	public Injector getSequencerInjector(Module overrides);

	/**
	 * @param generatorId
	 */
	public Injector getGeneratorInjector(String generatorId);
	
	public Injector getGeneratorInjector(String generatorId, Module overrides);
}
