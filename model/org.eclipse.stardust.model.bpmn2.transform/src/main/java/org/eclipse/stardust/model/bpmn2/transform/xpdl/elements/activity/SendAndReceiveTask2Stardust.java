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
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.Task;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common.ServiceInterfaceUtil;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

/**
 * @author Simon Nikles
 *
 */
public class SendAndReceiveTask2Stardust extends AbstractElement2Stardust {

	private BpmnModelQuery bpmnquery;

	public SendAndReceiveTask2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
		bpmnquery = new BpmnModelQuery(logger);
	}

	public void addSendTask(SendTask activity, FlowElementsContainer container) {
		logger.debug("Add SendTask: " + activity);
		addTask(activity, container);
	}

	public void addReceiveTask(ReceiveTask activity, FlowElementsContainer container) {
		logger.debug("Add ReceiveTask: " + activity);
		addTask(activity, container);
	}

	private void addTask(Task task, FlowElementsContainer container) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(task, container);
		if (processDef == null) return;
		String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());
		BpmApplicationActivityBuilder builder =
				newApplicationActivity(processDef)
				.withIdAndName(task.getId(), task.getName())
				.withDescription(descr);
		setInvokedApplication(task, builder, container);
		builder.build();
	}

	private void setInvokedApplication(Task task, BpmApplicationActivityBuilder builder, FlowElementsContainer container) {
		ServiceInterfaceUtil serviceUtil = new ServiceInterfaceUtil(carnotModel, bpmnquery, failures);
		ApplicationType application = serviceUtil.getApplicationAndReportFailure(task, container);
		if (application != null) {
			builder.setApplicationModel(carnotModel);
			builder.invokingApplication(application);
			logger.info("setInvokedApplication: " + application);
		}
	}
}
