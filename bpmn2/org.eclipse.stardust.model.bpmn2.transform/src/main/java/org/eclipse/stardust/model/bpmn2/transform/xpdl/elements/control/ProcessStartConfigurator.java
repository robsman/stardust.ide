/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ITpearls AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.extensions.jms.app.JMSLocation;
import org.eclipse.stardust.model.bpmn2.transform.util.PredefinedDataInfo;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.builder.variable.AbstractApplicationAccessPointBuilder;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

/**
 * @author Simon Nikles
 *
 * Introduces additional elements (route activities) to provide startable Stardust processes.
 * i.e.
 * - If the first activity (after a BPMN StartEvent) is the target of a backloop, an artificial start activity has to be introduced.
 * - If there is no BPMN StartEvent but multiple activities without incoming sequence flows, a route activity with an AND-Split is added (parallel start of activities).
 */
public class ProcessStartConfigurator  extends AbstractElement2Stardust {

	public static final String START_ROUTE_NAME = "START_ROUTE";
	public static final String START_ROUTE_TRANSITION_NAME = "START_ROUTE_TRANSITION";
	public static final String START_ROUTE_PRE_FIX = START_ROUTE_NAME + "_";
	public static final String START_ROUTE_POST_FIX = "_" + START_ROUTE_NAME;


	private Set<FlowElementsContainer> containers;


	public ProcessStartConfigurator(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void configureProcessStarts(Map<FlowElementsContainer, List<StartEvent>> startEventsPerContainer, Map<FlowElementsContainer, List<FlowNode>> potentialStartNodesPerContainer) {
		containers = new HashSet<FlowElementsContainer>();
		containers.addAll(startEventsPerContainer.keySet());
		containers.addAll(potentialStartNodesPerContainer.keySet());

		for (FlowElementsContainer container : containers) {
			List<StartEvent> startEvents = startEventsPerContainer.get(container);
			List<FlowNode> potentialStartElements = potentialStartNodesPerContainer.get(container);
			boolean multiStart = handleMultiStartEvent(container, startEvents);
			if (!multiStart) {
				handleBackloopsToStart(container, startEvents);
			}
			handleParallelStarts(container, startEvents, potentialStartElements);
		}
	}

	private boolean handleMultiStartEvent(FlowElementsContainer container, List<StartEvent> startEvents) {
		if (null == startEvents || !(startEvents.size() > 1)) return false;
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		ActivityType startRoute = insertOrSplitRoute(container, processDef);
		for (StartEvent startEvent : startEvents) {
			List<FlowNode> successors = BpmnModelQuery.getSequenceSuccessorNodesOf(startEvent);
			for (FlowNode target : successors) {
	        	ActivityType targetActivity = query.findActivity(target, container);
	        	TransitionType transition = TransitionUtil.createTransition(START_ROUTE_PRE_FIX + targetActivity.getId(), START_ROUTE_TRANSITION_NAME, "", processDef, startRoute, targetActivity);
	        	TransitionUtil.setTransitionExpression(transition, PredefinedDataInfo.VAR_START_EVENT_ID + "==" + startEvent.getId());
	        	addJmsStartEventHeaders(startEvent, processDef);
			}
		}
		return true;
	}

	private void addJmsStartEventHeaders(StartEvent startEvent, ProcessDefinitionType processDef) {
		TriggerType trigger = CarnotModelQuery.findTrigger(processDef, startEvent.getId());
		if (null == trigger) {
			failures.add("Trigger for BPMN Start Event " + startEvent.getId() + " not found in target model.");
			return;
		}
		if (!PredefinedConstants.JMS_TRIGGER.equals(trigger.getType().getId())) return;

        AccessPointType ap = CarnotWorkflowModelFactory.eINSTANCE.createAccessPointType();
        ap.setDirection(DirectionType.OUT_LITERAL);
        ap.setId(PredefinedDataInfo.VAR_START_EVENT_ID);
        ap.setName(PredefinedDataInfo.LBL_START_EVENT_ID);
        ap.setType(query.findDataType(PredefinedConstants.SERIALIZABLE_DATA));

        AttributeUtil.setBooleanAttribute(ap, PredefinedConstants.BROWSABLE_ATT, true);
        AttributeUtil.setAttribute(ap, PredefinedConstants.JMS_LOCATION_PROPERTY, JMSLocation.HEADER.getId(), JMSLocation.class.getName());
        AttributeUtil.setAttribute(ap, PredefinedConstants.CLASS_NAME_ATT, String.class.getName());

        trigger.getAccessPoint().add(ap);

        addJmsStartEventIdDataFlow(trigger, ap);
	}

	private void addJmsStartEventIdDataFlow(TriggerType trigger, AccessPointType ap) {
		// TODO Auto-generated method stub

	}

	private ActivityType insertOrSplitRoute(FlowElementsContainer container, ProcessDefinitionType processDef) {
		ActivityType route = createRouteActivity(processDef, container.getId() + START_ROUTE_POST_FIX, START_ROUTE_NAME);
        route.setSplit(JoinSplitType.OR_LITERAL);
        return route;
	}

	/**
	 * Insert route predecissor, if there is a backloop to the direct successor of a start event.
	 */
	private void handleBackloopsToStart(FlowElementsContainer container, List<StartEvent> startEvents) {
		if (null == startEvents || 0 == startEvents.size()) return;
		for (StartEvent startEvent : startEvents) { // although multiple start events are not supported yet
			if (BpmnModelQuery.isRoutingFlowNode(startEvent)) continue; // split (routing) is handled globally (for all flow nodes)
			int i = 0;
			for (FlowNode successorNode : BpmnModelQuery.getSequenceSuccessorNodesOf(startEvent)) {
				if (1 < BpmnModelQuery.getNumberOfSequencePredecessorNodesOf(successorNode)) {
					insertRouteWithTransitionTo(container, successorNode, i);
					i++;
				}
			}
		}
	}

	/**
	 * Insert route predecissor, if there are no start events and more than one 'start activity'.
	 */
	private void handleParallelStarts(FlowElementsContainer container, List<StartEvent> startEvents, List<FlowNode> potentialStartElements) {
		if (null != startEvents && 0 < startEvents.size()) return;
		if (null == potentialStartElements || 1 >= potentialStartElements.size()) return;
		insertAndSplitRouteWithTransitionTo(container, potentialStartElements);
	}

	private void insertAndSplitRouteWithTransitionTo(FlowElementsContainer container, List<FlowNode> potentialStartElements) {
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		ActivityType route = createRouteActivity(processDef, container.getId() + START_ROUTE_POST_FIX, START_ROUTE_NAME);
        route.setSplit(JoinSplitType.AND_LITERAL);

        for (FlowNode node : potentialStartElements) {
        	ActivityType targetActivity = query.findActivity(node, container);
        	TransitionUtil.createTransition(START_ROUTE_PRE_FIX + node.getId(), START_ROUTE_TRANSITION_NAME, "", processDef, route, targetActivity);
        }
	}

	private void insertRouteWithTransitionTo(FlowElementsContainer container, FlowNode successorNode, int startNr) {
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		ActivityType route = createRouteActivity(processDef, startNr + container.getId() + START_ROUTE_POST_FIX, START_ROUTE_NAME);
		ActivityType targetActivity = query.findActivity(successorNode, container);
		TransitionUtil.createTransition(START_ROUTE_PRE_FIX + successorNode.getId(), START_ROUTE_TRANSITION_NAME, "", processDef, route, targetActivity);
	}

	public ActivityType createRouteActivity(FlowElementsContainer container, FlowNode node, int startNr) {
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		return createRouteActivity(processDef, startNr + container.getId() + START_ROUTE_POST_FIX, START_ROUTE_NAME);
	}

	private ActivityType createRouteActivity(ProcessDefinitionType processDef, String id, String name) {
		return newRouteActivity(processDef)
               .withIdAndName(id, name)
               .build();
	}

}
