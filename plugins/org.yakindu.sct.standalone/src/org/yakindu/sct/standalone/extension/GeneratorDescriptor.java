package org.yakindu.sct.standalone.extension;

import java.net.URL;
import java.util.List;

import org.osgi.framework.Bundle;
import org.yakindu.sct.generator.core.ISCTGenerator;
import org.yakindu.sct.generator.core.extensions.IGeneratorDescriptor;
/**
* This class is meant to be used to describe generators which can be plugged into the SCT runtime.
 */
public class GeneratorDescriptor implements IGeneratorDescriptor {

	private String contentType;
	private String description;
	private String elementRefType;
	private String name;
	private String id;
	private List<String> libraryIDs;
	private ISCTGenerator generator;

	public GeneratorDescriptor(String id,String name,String contentType, String description, String elementRefType, 
			 List<String> libraryIDs, ISCTGenerator generator) {
		this.contentType = contentType;
		this.description = description;
		this.elementRefType = elementRefType;
		this.name = name;
		this.id = id;
		this.libraryIDs = libraryIDs;
		this.generator = generator;
	}

	@Override
	public ISCTGenerator createGenerator() {
		return generator;
	}
	@Override
	public List<String> getLibraryIDs() {
		return libraryIDs;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public URL getImagePath() {
		return null;
	} 
	@Override
	public String getContentType() {
		return contentType;
	}
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public String getElementRefType() {
		return elementRefType;
	}
	@Override
	public Bundle getBundle() {
		return null;
	}

}