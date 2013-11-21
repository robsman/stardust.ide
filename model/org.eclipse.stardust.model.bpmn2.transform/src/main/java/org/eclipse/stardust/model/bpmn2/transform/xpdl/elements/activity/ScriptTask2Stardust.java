/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newApplicationActivity;

import java.util.List;

import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDLExtension;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

/**
 * @author Simon Nikles
 *
 */
public class ScriptTask2Stardust extends AbstractElement2Stardust {

	public ScriptTask2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addScriptTask(ScriptTask task, FlowElementsContainer container) {
		logger.info("Add script task: " + task);
		ProcessDefinitionType processDef = getProcessAndReportFailure(task, container);
		if (processDef == null) return;
		String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());
		BpmApplicationActivityBuilder builder =
				newApplicationActivity(processDef)
				.withIdAndName(task.getId(), task.getName())
				.withDescription(descr);
		ActivityType activity = builder.build();

		Bpmn2StardustXPDLExtension.addScriptTaskExtensions(query, task, activity);
		if (null != activity.getApplication()) {
			// TODO ASSIGN SCRIPT FROM BPMN-ELEMENT TO APPLICATION (PROPERTY NAME?)
			// TODO ASSIGN SCRIPT-LANGUAGE FROM BPMN (SCRIPT APPLICATION PROPERTIES?)
		}
	}

}
