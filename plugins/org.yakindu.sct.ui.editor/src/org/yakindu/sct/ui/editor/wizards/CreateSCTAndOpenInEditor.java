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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.yakindu.sct.ui.editor.DiagramActivator;

/**
 * @author Johannes Dicks - Initial contribution and API
 *
 */
public class CreateSCTAndOpenInEditor extends WorkspaceModifyOperation {
	private final IProgressMonitor progressMonitor;
	private final SCTDiagramHelper diagramHelper;
	private final String editorID;
	private final String domainID;
	private final URI modelURI;
	private Resource diagram;

	/**
	 * @param rule
	 * @param editorID
	 * @param domainID
	 * @param progressMonitor
	 * @param modelURI
	 * @param diagramHelper TODO
	 */
	public CreateSCTAndOpenInEditor(SCTDiagramHelper diagramHelper, ISchedulingRule rule, String editorID, String domainID,
			IProgressMonitor progressMonitor, URI modelURI) {
		super(rule);
		this.diagramHelper = diagramHelper;
		this.editorID = editorID;
		this.domainID = domainID;
		this.progressMonitor = progressMonitor;
		this.modelURI = modelURI;
	}

	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
		diagram = this.diagramHelper.createDefaultDiagram(modelURI, domainID, progressMonitor);
		if ( diagram != null) {
			try {
				this.diagramHelper.openDiagram(diagram,editorID);
			} catch (PartInitException e) {
				DiagramActivator.getDefault().getLog().log(
						new Status(IStatus.WARNING, DiagramActivator.PLUGIN_ID, "Editor can't be opened", e));
			}
		}
	}

	/**
	 * @return
	 */
	public Resource getResource() {
		return diagram;
	}
}