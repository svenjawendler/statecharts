package org.yakindu.sct.domain.extension;

import java.net.URL;

public interface IDomainDescriptor {
	public static final String GENERATOR_MODULE = "GeneratorModule";
	String getDomainID();

	String getName();

	String getDescription();

	IDomainInjectorProvider getDomainInjectorProvider();

	URL getImagePath();

}