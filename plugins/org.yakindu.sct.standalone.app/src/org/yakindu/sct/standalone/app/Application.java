package org.yakindu.sct.standalone.app;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.yakindu.sct.generator.core.GeneratorExecutor;
import org.yakindu.sct.model.sgen.GeneratorModel;
import org.yakindu.sct.standalone.cmdln.api.SCTGeneratorOptionProvider;
import org.yakindu.sct.standalone.cmdln.api.SCTStandaloneOptions;
import org.yakindu.sct.standalone.cmdln.impl.BaseSCTGeneratorOptionProvider;

import com.google.common.collect.Lists;

public class Application implements IApplication {

	private static final String APPLICATION_ARGS = "application.args";
	private ResourceSet rSet;
	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("SCT Standalone START");

		String[] sctExecutionArguments = getSCTExecutionArguments(context);
		SCTGeneratorOptionProvider parameter = new BaseSCTGeneratorOptionProvider().init().withArgs(sctExecutionArguments).parse();
		
		SCTStandaloneOptions standaloneOptions = parameter.getStandaloneOptions();
		
		System.out.println("workspace DIR : "+ standaloneOptions.getAbsoluteWorkspaceDir());
		System.out.println("genTarget DIR : "+ standaloneOptions.getAbsoluteGenTargetDir());
		System.out.println("libraries DIR : "+ standaloneOptions.getAbsoluteLibrariesDir());
		System.out.println("sct DIR : "+ standaloneOptions.getSCTDir());
		System.out.println("sgen path : "+ standaloneOptions.getSGenPath());
		
		rSet = new ResourceSetImpl();
		
		
		IResource sctResource = getWorkspaceProjectFor(standaloneOptions.getSCTDir());
		/**		TODO: 
		 * Find proper strategy to match SCT files referenced from SGEN
		 * might also be
		 * sctResource = getWorkspaceFolderFor(standaloneOptions.getSCTDir());
		 * or
		 * sctResource = getWorkspaceFileFor(standaloneOptions.getSCTDir());
		**/		
		 
		sctResource.accept(new IResourceVisitor() {
			@Override
			public boolean visit(IResource resource) throws CoreException {
				if(resource.exists()){
					if(resource.getType() == IResource.FOLDER||resource.getType() == IResource.PROJECT){
						return true;
					}
					if(resource.getType() == IResource.FILE && resource.getFileExtension().equals("sct")){
						loadResource(resource);
					}
				}
				return false;
			}
		});
		
				
		//load sgen
		IFile workspaceFileFor = getWorkspaceFileFor(standaloneOptions.getSGenPath());
		Resource loadResource = loadResource(workspaceFileFor);
		GeneratorModel model = (GeneratorModel) loadResource.getContents().get(0);
		
		
		//exec gen
		new GeneratorExecutor().executeGenerator(model);
		
		//close workspace
		ResourcesPlugin.getWorkspace().save(true, new NullProgressMonitor());
		return IApplication.EXIT_OK;
	}

	private IProject getWorkspaceProjectFor(String name) {
		IProject file = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
		ensureProjectExistAndOpen(file);
		return file;
	}

	protected IFolder getWorkspaceFolderFor(String path) {
		IFolder file = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(path));
		ensureProjectExistAndOpen(file);
		return file;
	}

	protected IFile getWorkspaceFileFor(final String path) {
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path( path));
		ensureProjectExistAndOpen(file);
		return file;
	}

	private void ensureProjectExistAndOpen(IResource resource) {
		IProject project = resource.getProject();
		if(!project.exists()){
			try {
				project.create(new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		if(!project.isOpen())
			try {
				project.open(new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}
	}
	
	protected Resource loadResource(IResource file) {
		Resource resource = null;
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		resource = rSet.getResource(uri, true);
		return resource;
	}
	
	private String[] getSCTExecutionArguments(IApplicationContext context) {
		Map applicationArguments = context.getArguments();
		String[] args = (String[]) applicationArguments.get(APPLICATION_ARGS);
		
		List<String> arguments = Lists.newArrayList();
		for (String string : args) {
			arguments.add(string);
		}
		
		arguments.add("-ws");
		arguments.add(ResourcesPlugin.getWorkspace().getRoot().getRawLocationURI().toString());
		return arguments.toArray(new String[arguments.size()]);
	}

	@Override
	public void stop() {
		System.out.println("SCT Standalone END");
	}

}
