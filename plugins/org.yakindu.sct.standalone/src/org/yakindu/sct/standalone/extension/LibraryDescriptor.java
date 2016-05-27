package org.yakindu.sct.standalone.extension;

import org.eclipse.emf.common.util.URI;
import org.yakindu.sct.generator.core.extensions.ILibraryDescriptor;
import org.yakindu.sct.generator.core.features.IDefaultFeatureValueProvider;

public class LibraryDescriptor implements ILibraryDescriptor {
	
	private String id;
	private IDefaultFeatureValueProvider defaultFeatureValueProvider;
	private URI uri;
	
	public LibraryDescriptor(String id,URI uri,IDefaultFeatureValueProvider defaultFeatureValueProvider) {
		this.id = id;
		this.uri = uri;
		this.defaultFeatureValueProvider = defaultFeatureValueProvider;
	}
	
	@Override
	public URI getURI() {
		return uri;
	}
	@Override
	public String getLibraryId() {
		return id;
	}
	@Override
	public IDefaultFeatureValueProvider createFeatureValueProvider() {
		return defaultFeatureValueProvider;
	}
}