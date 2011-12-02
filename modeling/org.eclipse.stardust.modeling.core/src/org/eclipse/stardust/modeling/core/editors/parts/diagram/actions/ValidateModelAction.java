/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.jobs.ModelValidationJob;


public class ValidateModelAction extends SelectionAction {
	private WorkflowModelEditor editor;

	public ValidateModelAction(WorkflowModelEditor part) {
		super(part);
		this.editor = part;
		setId(DiagramActionConstants.MODEL_VALIDATE);
		setText(Diagram_Messages.TXT_ValidateModel);
		// setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/diagram.gif"));
	}

	protected boolean calculateEnabled() {
		return getSelectedObjects().size() == 1 && getModel() != null;
	}

	public void run() {
		ModelValidationJob validationJob = new ModelValidationJob(editor,
				getModel(), createPerspectiveFilter());
		if (null != validationJob.getModelFile()) {
			validationJob.setRule(ResourcesPlugin.getWorkspace()
					.getRuleFactory().markerRule(validationJob.getModelFile()));
			validationJob.schedule();
		}
	}

	private Map createPerspectiveFilter() {
		Map filters = new HashMap();
		String perspectiveId = DiagramPlugin
				.getViewAsPerspectiveId((WorkflowModelEditor) getWorkbenchPart());
		if (perspectiveId != null) {
			filters.put("perspectiveType", perspectiveId); //$NON-NLS-1$
		}
		return filters;
	}

	private ModelType getModel() {
		Object selection = getSelectedObjects().get(0);
		if (selection instanceof EditPart) {
			Object model = ((EditPart) selection).getModel();
			if (model instanceof ModelType) {
				return (ModelType) model;
			}
		}
		return null;
	}
}
