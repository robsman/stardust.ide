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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.process;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newSubProcessActivity;

import java.util.List;

import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

public class Subprocess2Stardust extends AbstractElement2Stardust {

	public Subprocess2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addSubprocess(SubProcess subprocess, FlowElementsContainer container) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(subprocess, container);
		if (processDef == null) return;
		List<Documentation> docs = subprocess.getDocumentation();
		String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);
		ProcessDefinitionType implProcessDef = newProcessDefinition(carnotModel)
				.withIdAndName(subprocess.getId(), subprocess.getName())
				.withDescription(processDescription).build();
		ActivityType activity = newSubProcessActivity(processDef)
				.withIdAndName(subprocess.getId(), subprocess.getName())
				.withDescription(processDescription)
				.build();
		activity.setImplementationProcess(implProcessDef);
	}


}
