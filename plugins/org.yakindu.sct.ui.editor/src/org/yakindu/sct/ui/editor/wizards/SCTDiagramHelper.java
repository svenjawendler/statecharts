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
package org.yakindu.sct.ui.editor.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.EcoreUtil2;
import org.yakindu.base.base.BasePackage;
import org.yakindu.base.gmf.runtime.util.EditPartUtils;
import org.yakindu.sct.model.sgraph.SGraphPackage;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.ui.editor.DiagramActivator;
import org.yakindu.sct.ui.editor.editor.StatechartDiagramEditor;
import org.yakindu.sct.ui.editor.factories.FactoryUtils;
import org.yakindu.sct.ui.editor.providers.SemanticHints;

/**
 * Initially extracted from CreationWizard
 * 
 * @author Johannes Dicks - Initial contribution and API
 *
 */
public class SCTDiagramHelper {
	
	
	public Statechart createDefaultDiagram(final URI modelURI, final String domainID,IRunnableContext context, final boolean empty) {
		
		final Resource[] resource = new Resource[1];
		WorkspaceModifyOperation op = new WorkspaceModifyOperation(){

			@Override
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException, InterruptedException {
				resource[0] = doCreate(modelURI, domainID, monitor, empty);
			}
			
		};
		exec(context, op);
		return (Statechart) resource[0].getContents().get(0);
	}

	public boolean openDiagram(final Resource diagram,final String editorID,IRunnableContext context) {
		
		final Boolean[] resource = new Boolean[1];
		WorkspaceModifyOperation op = new WorkspaceModifyOperation(){

			@Override
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException, InterruptedException {
				resource[0] = doOpen(diagram, editorID);
			}
			
		};
		exec(context, op);
		return resource[0];
	}
	
	private void exec(IRunnableContext context, WorkspaceModifyOperation op) {
		try {
			context.run(false, true, op);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected Resource doCreate(final URI modelURI, final String domainID, IProgressMonitor progressMonitor, final boolean empty) {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		progressMonitor.beginTask("Creating diagram file ...", 3);
		final Resource resource = editingDomain.getResourceSet().createResource(modelURI);
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain,
				"Creating diagram file ...", Collections.EMPTY_LIST) {
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {

				if (empty) {
					FactoryUtils.createStatechartModelWithEmptyRegion(resource, DiagramActivator.DIAGRAM_PREFERENCES_HINT);					
				} else {
					FactoryUtils.createStatechartModel(resource, DiagramActivator.DIAGRAM_PREFERENCES_HINT);
				}
				Statechart statechart = (Statechart) EcoreUtil.getObjectByType(resource.getContents(),
						SGraphPackage.Literals.STATECHART);
				statechart.setDomainID(domainID != null ? domainID
						: BasePackage.Literals.DOMAIN_ELEMENT__DOMAIN_ID.getDefaultValueLiteral());
				try {
					resource.save(getSaveOptions());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return CommandResult.newOKCommandResult();
			}
		};
		try {
			command.execute(progressMonitor, null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		setCharset(WorkspaceSynchronizer.getFile(resource));
		editingDomain.dispose();
		return resource;
	}
	
	protected Map<String, String> getSaveOptions() {
		Map<String, String> saveOptions = new HashMap<String, String>();
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		return saveOptions;
	}

	protected void setCharset(IFile file) {
		if (file == null) {
			return;
		}
		try {
			file.setCharset("UTF-8", new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private boolean doOpen(Resource diagram, String editorID) throws PartInitException {
		String path = diagram.getURI().toPlatformString(true);
		IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));
		if (workspaceResource instanceof IFile) {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			StatechartDiagramEditor editor = (StatechartDiagramEditor)page.openEditor(new FileEditorInput((IFile) workspaceResource), editorID);
			if (editor != null) {
				List<EObject> allNotationElements = EcoreUtil2.eAllContentsAsList(diagram);
				for (EObject eObject : allNotationElements) {
					if (eObject instanceof View && ((View) eObject).getType().equals(SemanticHints.STATE_NAME)) {
						IGraphicalEditPart editPart = EditPartUtils.findEditPartForSemanticElement(
								editor.getDiagramGraphicalViewer().getRootEditPart(), ((View) eObject).getElement());
						editPart = editPart.getChildBySemanticHint(SemanticHints.STATE_NAME);
						if (editPart != null) {
							final DirectEditRequest request = new DirectEditRequest();
							request.setDirectEditFeature(BasePackage.Literals.NAMED_ELEMENT__NAME);
							editPart.performRequest(request);
							break;
						}
					}
				}
				return false;
			}
		}
		return false;
	}

}
