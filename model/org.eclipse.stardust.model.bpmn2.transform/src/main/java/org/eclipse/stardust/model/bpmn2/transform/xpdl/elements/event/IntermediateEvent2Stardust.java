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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newApplicationActivity;

import java.util.List;

import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common.ServiceInterfaceUtil;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.IntermediateAndEndEventDataFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

public class IntermediateEvent2Stardust extends AbstractElement2Stardust {

	private BpmnModelQuery bpmnquery;

	public IntermediateEvent2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
		bpmnquery = new BpmnModelQuery(logger);
	}

	public void addIntermediateCatchEvent(IntermediateCatchEvent event, FlowElementsContainer container) {
		logger.debug("addIntermediateCatchEvent " + event);
		addEvent(event, container);
//		new IntermediateAndEndEventDataFlow2Stardust(carnotModel, failures).addDataFlows(event, container);
	}

	public void addIntermediateThrowEvent(IntermediateThrowEvent event, FlowElementsContainer container) {
		logger.debug("addIntermediateThrowEvent " + event);
		addEvent(event, container);
//		new IntermediateAndEndEventDataFlow2Stardust(carnotModel, failures).addDataFlows(event, container);
	}

	protected void addEvent(Event event, FlowElementsContainer container) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(event, container);
		EventDefinition def = bpmnquery.getFirstEventDefinition(event);
		if (def == null) {
			failures.add("No Event Definition found. (Event " + event + " in " + container + ")");
			return;
		}
		if (def instanceof MessageEventDefinition) {
			createMessageApplicationActivity(processDef, event, (MessageEventDefinition)def, container);
		}
	}

	private ActivityType createMessageApplicationActivity(ProcessDefinitionType processDef, Event event, MessageEventDefinition def, FlowElementsContainer container) {
		ServiceInterfaceUtil serviceUtil = new ServiceInterfaceUtil(carnotModel, bpmnquery, failures);
		ApplicationType application = serviceUtil.getApplicationAndReportFailures(event, def,  container);
		String id = event.getId();
		String name = event.getName();
		name = getNonEmptyName(name, id, event);
		String descr = DocumentationTool.getDescriptionFromDocumentation(event.getDocumentation());
		return createApplicationActivity(processDef, id, name, descr, application);
	}

	private ActivityType createApplicationActivity(ProcessDefinitionType processDef, String id, String name, String descr, ApplicationType application) {
		BpmApplicationActivityBuilder builder =
				newApplicationActivity(processDef)
				.withIdAndName(id, name)
				.withDescription(descr);

		if (application != null) {
			builder.setApplicationModel(carnotModel);
			builder.invokingApplication(application);
		}

		return builder.build();
	}

}
