/**
 * Copyright (c) 2016 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.sct.domain.generic.modules;

import org.eclipse.xtext.service.AbstractGenericModule;
import org.yakindu.sct.domain.extension.DomainRegistry;
import org.yakindu.sct.generator.core.filesystem.ISCTFileSystemAccess;
import org.yakindu.sct.generator.core.impl.IGeneratorLog;

/**
 * @author Johannes Dicks - Initial contribution and API
 *
 */
public class GenericGeneratorModule extends AbstractGenericModule {

	public Class<? extends ISCTFileSystemAccess> bindISCTFileSystemAccess() {
		return DomainRegistry.getDefaultBinding(ISCTFileSystemAccess.class);
	}

	public Class<? extends IGeneratorLog> bindIGeneratorLog() {
		return DomainRegistry.getDefaultBinding(IGeneratorLog.class);
	}

}
