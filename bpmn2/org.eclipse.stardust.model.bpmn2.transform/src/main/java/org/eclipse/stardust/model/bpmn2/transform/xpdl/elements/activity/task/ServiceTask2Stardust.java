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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity.task;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newApplicationActivity;

import java.util.List;

import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common.ServiceInterfaceUtil;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

public class ServiceTask2Stardust extends AbstractElement2Stardust {

	protected final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ServiceTask2Stardust.class);

	private BpmnModelQuery bpmnquery;

	public ServiceTask2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
		bpmnquery = new BpmnModelQuery(logger);
	}

	public void addServiceTask(ServiceTask task, FlowElementsContainer container) {
		logger.info("Add service task: " + task);
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

	private void setInvokedApplication(ServiceTask task, BpmApplicationActivityBuilder builder, FlowElementsContainer container) {
		ServiceInterfaceUtil serviceUtil = new ServiceInterfaceUtil(carnotModel, bpmnquery, failures);
		ApplicationType application = serviceUtil.getApplicationAndReportFailure(task, container);
		if (application != null) {
			builder.setApplicationModel(carnotModel);
			builder.invokingApplication(application);
			logger.info("setInvokedApplication: " + application);
		}
	}

}
