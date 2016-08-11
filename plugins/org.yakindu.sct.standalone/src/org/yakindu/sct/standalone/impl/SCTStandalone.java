package org.yakindu.sct.standalone.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.impl.ResourceSetGlobalScopeProvider;
import org.yakindu.sct.domain.extension.DomainRegistry;
import org.yakindu.sct.domain.extension.IDomainInjectorProvider;
import org.yakindu.sct.domain.generic.extension.GenericDomainInjectorProvider;
import org.yakindu.sct.generator.core.GeneratorExecutor;
import org.yakindu.sct.generator.core.extensions.GeneratorExtensions;
import org.yakindu.sct.generator.core.extensions.LibraryExtensions;
import org.yakindu.sct.generator.core.features.impl.CoreLibraryDefaultFeatureValueProvider;
import org.yakindu.sct.generator.core.features.impl.SCTBaseLibaryDefaultFeatureValueProvider;
import org.yakindu.sct.generator.core.filesystem.ISCTFileSystemAccess;
import org.yakindu.sct.generator.core.impl.IGeneratorLog;
import org.yakindu.sct.generator.genmodel.SGenRuntimeModule;
import org.yakindu.sct.generator.genmodel.SGenStandaloneSetup;
import org.yakindu.sct.generator.java.JavaCodeGenerator;
import org.yakindu.sct.model.sgen.GeneratorModel;
import org.yakindu.sct.model.sgen.SGenPackage;
import org.yakindu.sct.model.stext.STextRuntimeModule;
import org.yakindu.sct.model.stext.STextStandaloneSetup;
import org.yakindu.sct.standalone.api.ISCTStandalone;
import org.yakindu.sct.standalone.cmdln.api.SCTStandaloneOptions;
import org.yakindu.sct.standalone.extension.DomainDescriptor;
import org.yakindu.sct.standalone.extension.GeneratorDescriptor;
import org.yakindu.sct.standalone.extension.LibraryDescriptor;
import org.yakindu.sct.standalone.generator.Log4jGeneratorLog;
import org.yakindu.sct.standalone.generator.StandaloneFileSystemAccess;
import org.yakindu.sct.standalone.io.ResourceUtil;

import com.google.common.collect.Lists;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

public class SCTStandalone implements ISCTStandalone {

	private ResourceSet resourceSet;
	private SCTStandaloneOptions parameter;
	private ResourceUtil resourceUtil;

	@Override
	public void generate() {
		generateAll();
	}

	@Override
	public void init(SCTStandaloneOptions options) {
		this.parameter = options;
		// TODO init default bindings properly (needed to avoid implementation
		// dependencies withion e.g. domain.generic)
		DomainRegistry.addDefaultBinding(ISCTFileSystemAccess.class, StandaloneFileSystemAccess.class);
		DomainRegistry.addDefaultBinding(IGeneratorLog.class, Log4jGeneratorLog.class);
		
		
		initLanguages();
		initResourceSet();
		
		List<String> paths = Lists.newArrayList();
		paths.add(parameter.getAbsoluteWorkspaceDir());
		resourceUtil = new ResourceUtil(paths);
		doLoadEMFResource(resourceUtil.getAbsolutePath(parameter.getSGenPath()));
		
		
		
		loadSCTs(resourceUtil.getAbsolutePath(parameter.getSCTDir()));
		
		//TODO old stuff needed to init sct extensions like domains, libraries and generators
		// since we can use the the extensions this will work without modifications now.
		//
		// //TODO this will be obsolete when extensions can be used
		// initSCTDomain(parameter);
		// initSgenLibraries(parameter);
		// initSCTGenerator(parameter);
	}
	
	protected void loadSCTs(String path) {
		File file = Path.fromOSString(path).toFile();
		loadSCTs(file);
	}

	private void loadSCTs(File file) {
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			try {
				if (isPlainDir(file2)&&!(file2.getName().equals("bin"))&& !(file2.getName().startsWith(".")))
					loadSCTs(file2);
				else if (file2.getName().endsWith(".sct"))
					doLoadEMFResource(file2.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected boolean isPlainDir(File file) throws IOException {
		return file.isDirectory() && !isSymbolicLink(file);
	}

	protected boolean isSymbolicLink(File file) throws IOException {
		if (file == null) {
			throw new NullPointerException("File must not be null");
		}
		File canon;
		if (file.getParent() == null) {
			canon = file;
		} else {
			File canonDir = file.getParentFile().getCanonicalFile();
			canon = new File(canonDir, file.getName());
		}
		return !canon.getCanonicalFile().equals(canon.getAbsoluteFile());
	}
	
	/**
	 * Have to be an absolute path
	 * @param filePath an absolute filepath
	 * @return a resource loaded or NULL
	 */
	protected Resource doLoadEMFResource(String filePath) {
		return this.resourceSet.getResource(URI.createFileURI(filePath), true);
	}
	

	protected void generateAll() {
		EList<Resource> resources = resourceSet.getResources();
		List<GeneratorModel> genModels = Lists.newArrayList();
		for (Resource resource : resources) {
			EObject content = resource.getContents().get(0);
			if (content instanceof GeneratorModel)
				genModels.add((GeneratorModel) content);
		}
		for (GeneratorModel generatorModel : genModels) {
			new GeneratorExecutor().executeGenerator(generatorModel);
		}
	}

	protected void initSCTGenerator(final SCTStandaloneOptions parameter) {
		for (GeneratorDescriptor generator : getGenerators()) {
			GeneratorExtensions.getGeneratorDescriptors().add(generator);
		}
	}

	protected void initSgenLibraries(SCTStandaloneOptions parameter) {
		for (LibraryDescriptor libraryDescriptor : getLibraries(parameter)) {
			LibraryExtensions.getLibraryDescriptors().add(libraryDescriptor);
		}
	}

	protected void initSCTDomain(final SCTStandaloneOptions parameter) {
		for (DomainDescriptor domain : getDomains(parameter)) {
			DomainRegistry.getDomainDescriptors().add(domain);
		}
	}

	protected void initResourceSet() {
		resourceSet = new ResourceSetImpl();
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(SCTStandaloneOptions.FILE_EXTENSION_SCT,
//				new SCTResourceFactory());
	}

	protected void initLanguages() {
		doSgenSetup();
		doSTextSetup();
	}

	protected void doSgenSetup() {
		SGenPackage.eINSTANCE.eClass();
		new SGenStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(Modules.override(new SGenRuntimeModule()).with(new Module() {
					@Override
					public void configure(Binder binder) {
						binder.bind(IGlobalScopeProvider.class).to(ResourceSetGlobalScopeProvider.class);
					}
				}));
			}
		}.createInjectorAndDoEMFRegistration();
	}

	protected void doSTextSetup() {
		NotationPackage.eINSTANCE.eClass();

		Injector stextInjector = new STextStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(Modules.override(new STextRuntimeModule()).with(new Module() {
					@Override
					public void configure(Binder binder) {
						binder.bind(IGlobalScopeProvider.class).to(ResourceSetGlobalScopeProvider.class);
					}
				}));
			}
		}.createInjectorAndDoEMFRegistration();
		IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put(
				SCTStandaloneOptions.FILE_EXTENSION_SCT, stextInjector.getInstance(IResourceServiceProvider.class));
	}

	protected List<LibraryDescriptor> getLibraries(SCTStandaloneOptions parameter) {
		List<LibraryDescriptor> libraries = Lists.newArrayList();

		libraries.add(new LibraryDescriptor("org.yakindu.generator.core.features",
				URI.createFileURI(parameter.getAbsoluteLibrariesDir() + "/CoreFeatureTypeLibrary.xmi"),
				new CoreLibraryDefaultFeatureValueProvider()));
		libraries.add(new LibraryDescriptor("org.yakindu.generator.core.features.sctbase",
				URI.createFileURI(parameter.getAbsoluteLibrariesDir() + "/SCTBaseFeatureLibrary.xmi"),
				new SCTBaseLibaryDefaultFeatureValueProvider()));
		return libraries;
	}

	protected List<GeneratorDescriptor> getGenerators() {

		List<GeneratorDescriptor> generators = Lists.newArrayList();
		generators.add(new GeneratorDescriptor("yakindu::java", "YAKINDU SCT Java Code Generator", "statechart",
				"YAKINDU SCT Java Code Generator",
				"org.yakindu.sct.model.sgraph.Statechart", Lists.newArrayList("org.yakindu.generator.core.features",
						"org.yakindu.generator.core.features.sctbase", "org.yakindu.sct.generator.feature.java"),
				new JavaCodeGenerator()));
		return generators;
	}

	protected List<DomainDescriptor> getDomains(final SCTStandaloneOptions parameter) {
		List<DomainDescriptor> domains = Lists.newArrayList();
		domains.add(new DomainDescriptor("org.yakindu.domain.default", "Default",
				"The default domain for YAKINDU Statechart Tools.") {
			@Override
			public IDomainInjectorProvider getDomainInjectorProvider() {
				return new GenericDomainInjectorProvider() {
					@Override
					public Module getGeneratorModule(String generatorId) {
						Module generatorModule = super.getGeneratorModule(generatorId);
						return toStandaloneGeneratorModule(generatorModule, parameter.getAbsoluteGenTargetDir());
					}
					protected Module getResourceModule() {
						return toStandaloneResourceModule(this);
					};
				};
			}
		});
		return domains;
	}
}
