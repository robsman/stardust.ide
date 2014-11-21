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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.callable.process;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newModelDiagram;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;

import java.util.List;

import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.Process;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

public class Process2Stardust extends AbstractElement2Stardust {

	public Process2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addProcess(Process process) {
		List<Documentation> docs = process.getDocumentation();
		String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);

		ProcessDefinitionType def =
				newProcessDefinition(carnotModel)
				.withIdAndName(process.getId(), process.getName())
				.withDescription(processDescription)
				.build();
		newModelDiagram(carnotModel).forProcess(def).build();
	}

}
