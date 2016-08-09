/** 
 * Copyright (c) 2015 committers of YAKINDU and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 * Contributors:
 * committers of YAKINDU - initial API and implementation
 *
*/
package org.yakindu.sct.domain.ui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.yakindu.sct.domain.extension.DomainRegistry;
import org.yakindu.sct.generator.core.filesystem.ISCTFileSystemAccess;
import org.yakindu.sct.generator.core.impl.IGeneratorLog;
import org.yakindu.sct.generator.core.ui.efs.EFSResourceFileSystemAccess;
import org.yakindu.sct.generator.core.ui.util.EclipseConsoleLog;

public class DomainUiActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		DomainUiActivator.context = bundleContext;
		//TODO SA1: find proper place. proper place means something decoupled from domain.generic and domain.core to keep EFS decoupled from them for e.g. standalone generation
		// at a certain point 
		DomainRegistry.addDefaultBinding(ISCTFileSystemAccess.class, EFSResourceFileSystemAccess.class);
		DomainRegistry.addDefaultBinding(IGeneratorLog.class, EclipseConsoleLog.class);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		DomainUiActivator.context = null;
	}

}
