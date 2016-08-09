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
package org.yakindu.sct.domain.ui.extension;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.xtext.EcoreUtil2;
import org.yakindu.base.base.BasePackage;
import org.yakindu.base.base.DomainElement;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @author andreas muelder - Initial contribution and API
 * 
 */
public class DomainUiRegistry {

	private static final String EXTENSION_POINT_ID = "org.yakindu.sct.domain.ui";
	private static final String DOMAIN_ID = "domainID";
	private static final String MODULE_PROVIDER = "domainModuleProvider";

	private DomainUiRegistry() {
	}

	
	private static List<IDomainUiDescriptor> descriptors;

	private static final class ConfigElementDomainDescriptor implements IDomainUiDescriptor {

		private final IConfigurationElement configElement;

		private URL path;

		private IDomainUiInjectorProvider injectorProvider;

		ConfigElementDomainDescriptor(IConfigurationElement configElement) {
			this.configElement = configElement;
		}

		@Override
		public String getDomainID() {
			return configElement.getAttribute(DOMAIN_ID);
		}

		@Override
		public IDomainUiInjectorProvider getDomainInjectorProvider() {
			if (injectorProvider == null) {
				try {
					injectorProvider = (IDomainUiInjectorProvider) configElement
							.createExecutableExtension(MODULE_PROVIDER);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
			return injectorProvider;
		}
	}

	public static List<IDomainUiDescriptor> getDomainDescriptors() {
		if (descriptors == null) {
			descriptors = Lists.newArrayList();
			if (Platform.isRunning()) {
				initFromExtensions();
			}
		}
		return descriptors;
	}

	protected static void initFromExtensions() {
		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (IConfigurationElement iConfigurationElement : configurationElements) {
			descriptors.add(new ConfigElementDomainDescriptor(iConfigurationElement));
		}
	}

	public static IDomainUiDescriptor getDomainDescriptor(final String id) {
		final String defaultDomainID = BasePackage.Literals.DOMAIN_ELEMENT__DOMAIN_ID.getDefaultValueLiteral();
		try {
			return Iterables.find(getDomainDescriptors(), new Predicate<IDomainUiDescriptor>() {
				@Override
				public boolean apply(IDomainUiDescriptor input) {
					return input.getDomainID().equals(id != null ? id : defaultDomainID);
				}
			});
		} catch (NoSuchElementException e) {
			if (defaultDomainID.equals(id)) {
				throw new IllegalArgumentException("No default domain found!");
			}
			System.err.println("Could not find domain descriptor for id " + id + " - > using default domain");
			return getDomainDescriptor(defaultDomainID);
		}
	}

	public static IDomainUiDescriptor getDomainDescriptor(EObject object) {
		DomainElement domainElement = EcoreUtil2.getContainerOfType(object, DomainElement.class);
		String domainID = domainElement != null
				? domainElement.getDomainID()
				: BasePackage.Literals.DOMAIN_ELEMENT__DOMAIN_ID.getDefaultValueLiteral();
		return getDomainDescriptor(domainID);
	}
	
	public static String determineDomainID(URI uri) {
		String result = BasePackage.Literals.DOMAIN_ELEMENT__DOMAIN_ID.getDefaultValueLiteral();
		if (URIConverter.INSTANCE.exists(uri, null)) {
			XMIResource resource = new XMIResourceImpl(uri);
			try {
				resource.load(null);
				DomainElement element = (DomainElement) EcoreUtil.getObjectByType(resource.getContents(),
						BasePackage.Literals.DOMAIN_ELEMENT);
				String domainID = element.getDomainID();
				Assert.isNotNull(domainID);
				result = domainID;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				resource.unload();
			}
		}
		return result;
	}
}
