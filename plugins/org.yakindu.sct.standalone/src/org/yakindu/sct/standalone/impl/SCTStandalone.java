package org.yakindu.sct.standalone.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.log4j.Logger;
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
import org.yakindu.sct.domain.extension.IDomainDescriptor;
import org.yakindu.sct.generator.core.GeneratorExecutor;
import org.yakindu.sct.generator.core.extensions.GeneratorExtensions;
import org.yakindu.sct.generator.core.extensions.IGeneratorDescriptor;
import org.yakindu.sct.generator.core.extensions.ILibraryDescriptor;
import org.yakindu.sct.generator.core.extensions.LibraryExtensions;
import org.yakindu.sct.generator.genmodel.SGenRuntimeModule;
import org.yakindu.sct.generator.genmodel.SGenStandaloneSetup;
import org.yakindu.sct.model.sgen.GeneratorModel;
import org.yakindu.sct.model.sgen.SGenPackage;
import org.yakindu.sct.model.stext.STextRuntimeModule;
import org.yakindu.sct.model.stext.STextStandaloneSetup;
import org.yakindu.sct.standalone.api.ISCTStandalone;
import org.yakindu.sct.standalone.cmdln.api.SCTStandaloneOptions;
import org.yakindu.sct.standalone.generator.Log4jGeneratorLog;
import org.yakindu.sct.standalone.generator.StandaloneFileSystemAccess;
import org.yakindu.sct.standalone.generator.StandaloneGeneratorModule;
import org.yakindu.sct.standalone.io.ResourceUtil;

import com.google.common.collect.Lists;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

public class SCTStandalone implements ISCTStandalone {

	private static final String EXTENSION_SCT = ".sct";

	private static final String EXTENSION_SGEN = ".sgen";

	private static final Logger LOGGER = Logger.getLogger(SCTStandalone.class);

	private ResourceSet resourceSet;
	private SCTStandaloneOptions parameter;
	private ResourceUtil resourceUtil;

	@Override
	public void init(SCTStandaloneOptions options) {
		this.parameter = options;
		// TODO init default bindings properly (needed to avoid implementation
		// dependencies withion e.g. domain.generic)
		// DomainRegistry.addDefaultBinding(ISCTFileSystemAccess.class,
		// StandaloneFileSystemAccess.class);
		// DomainRegistry.addDefaultBinding(IGeneratorLog.class,
		// Log4jGeneratorLog.class);

		DomainRegistry.addDefaultmodule(IDomainDescriptor.GENERATOR_MODULE,
				new StandaloneGeneratorModule(parameter.getAbsoluteWorkspaceDir(), StandardCharsets.UTF_8,
						StandaloneFileSystemAccess.class, Log4jGeneratorLog.class));

		initLanguages();
		initResourceSet();

		List<String> paths = Lists.newArrayList();
		paths.add(parameter.getAbsoluteWorkspaceDir());
		resourceUtil = new ResourceUtil(paths);

		loadModels(parameter);

		if (LOGGER.isDebugEnabled())
			logAvailableSctExtensions();

	}
	private void logAvailableSctExtensions() {
		LOGGER.debug("Available domains :");
		List<IDomainDescriptor> domainDescriptors = DomainRegistry.getDomainDescriptors();
		for (IDomainDescriptor iDomainDescriptor : domainDescriptors) {

			LOGGER.debug("Name:" + iDomainDescriptor.getName());
			LOGGER.debug("	ID:" + iDomainDescriptor.getDomainID());
			LOGGER.debug("	Desc :" + iDomainDescriptor.getDescription());
		}
		LOGGER.debug("");
		LOGGER.debug("Available libraries :");
		List<ILibraryDescriptor> libraryDescriptors = LibraryExtensions.getLibraryDescriptors();
		for (ILibraryDescriptor libraryDescriptor : libraryDescriptors) {

			LOGGER.debug("ID:" + libraryDescriptor.getLibraryId());
			LOGGER.debug("	URI:" + libraryDescriptor.getURI());
		}
		LOGGER.debug("");
		LOGGER.debug("Available generators :");
		List<IGeneratorDescriptor> generatorDescriptors = GeneratorExtensions.getGeneratorDescriptors();
		for (IGeneratorDescriptor generatorDescriptor : generatorDescriptors) {

			LOGGER.debug("Name:" + generatorDescriptor.getName());
			LOGGER.debug("	ID:" + generatorDescriptor.getId());
			LOGGER.debug("	Desc:" + generatorDescriptor.getDescription());
		}
	}
	@Override
	public void generate() {
		generateAll();
	}

	private void loadModels(SCTStandaloneOptions options) {
		loadSGENs(options);
		loadSCTs(options);
	}
	private void loadSGENs(SCTStandaloneOptions options) {
		String absolutesGen = resourceUtil.getAbsolutePath(options.getSGenPath());

		if (isPlainDir(Path.fromOSString(absolutesGen).toFile())) {
			List<String> loadSCTs = searchFiles(absolutesGen, EXTENSION_SGEN);
			for (String string : loadSCTs) {
				doLoadEMFResource(string);
			}
		}else{
			doLoadEMFResource(absolutesGen);
		}
	}
	private void loadSCTs(SCTStandaloneOptions options) {
		List<String> loadSCTs = searchFiles(resourceUtil.getAbsolutePath(options.getSCTDir()), EXTENSION_SCT);
		for (String string : loadSCTs) {
			doLoadEMFResource(string);
		}
	}

	protected List<String> searchFiles(String absoluteFolderPath, String extension) {
		File file = Path.fromOSString(absoluteFolderPath).toFile();
		return loadSCTs(file, extension);
	}

	private List<String> loadSCTs(File file, String extension) {
		List<String> absolutePaths = Lists.newArrayList();
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			if (isPlainDir(file2) && !(file2.getName().equals("bin")) && !(file2.getName().startsWith(".")))
				absolutePaths.addAll(loadSCTs(file2, extension));
			else {
				if (file2.getName().endsWith(extension))
					absolutePaths.add(file2.getAbsolutePath());
			}
		}
		return absolutePaths;
	}

	protected boolean isPlainDir(File file) {
		try{
		return file.isDirectory() && !isSymbolicLink(file);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
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
	 * 
	 * @param filePath
	 *            an absolute filepath
	 * @return a resource loaded or NULL
	 */
	protected Resource doLoadEMFResource(String filePath) {
		Resource resource = this.resourceSet.getResource(URI.createFileURI(filePath), true);
		LOGGER.info("Loaded resource (" + isLoadedMsg(resource) + "): " + filePath);
		return resource;
	}

	private String isLoadedMsg(Resource resource) {
		if (resource != null)
			return "SUCCESS";
		return "FAILED";
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

	protected void initResourceSet() {
		resourceSet = new ResourceSetImpl();
		// TODO not necessary anymore?!
		// resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(SCTStandaloneOptions.FILE_EXTENSION_SCT,
		// new SCTResourceFactory());
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
}
