package org.yakindu.sct.standalone.extension;

import java.nio.charset.StandardCharsets;

import org.eclipse.swt.graphics.Image;
import org.yakindu.sct.domain.extension.IDomainDescriptor;
import org.yakindu.sct.domain.extension.IDomainInjectorProvider;
import org.yakindu.sct.domain.generic.extension.GenericDomainInjectorProvider;
import org.yakindu.sct.standalone.generator.Log4jGeneratorLog;
import org.yakindu.sct.standalone.generator.StandaloneFileSystemAccess;
import org.yakindu.sct.standalone.generator.StandaloneGeneratorModule;

import com.google.inject.Module;
import com.google.inject.util.Modules;

public abstract class DomainDescriptor implements IDomainDescriptor {

	private String id;
	private String name;
	private String description;

	public DomainDescriptor(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	@Override
	public String getDomainID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public abstract IDomainInjectorProvider getDomainInjectorProvider();

	@Override
	public Image getImage() {
		return null;
	} 
	
	protected StandaloneGeneratorModule createStandaloneGeneratorModule(final String baseDir) {
		return new StandaloneGeneratorModule(baseDir, StandardCharsets.UTF_8, StandaloneFileSystemAccess.class, Log4jGeneratorLog.class);
	}

	protected Module toStandaloneGeneratorModule(Module module, final String workspaceDir) {
		return Modules.override(module).with(createStandaloneGeneratorModule(workspaceDir));
	}
	protected Module toStandaloneResourceModule(GenericDomainInjectorProvider injectorProvider) {
		Module languageRuntimeModule = injectorProvider.getLanguageRuntimeModule();
		
		return Modules.override(languageRuntimeModule)
				.with(injectorProvider.getTypeSystemModule());
	}

}
