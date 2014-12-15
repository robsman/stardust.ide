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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.extensions.jms.app.JMSLocation;
import org.eclipse.stardust.model.bpmn2.transform.util.PredefinedDataInfo;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
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
		ActivityType startRoute = insertXOrSplitRoute(container, processDef);
		Map<String, TransitionType> transitionPerTarget = new HashMap<String, TransitionType>();
		for (StartEvent startEvent : startEvents) {
			List<FlowNode> successors = BpmnModelQuery.getSequenceSuccessorNodesOf(startEvent);
			ActivityType targetActivity = null;
			if (successors.size() > 1) {
				targetActivity = insertParallelRouteForStartEvent(container, startEvent, successors);
			} else if (successors.size() == 1) {
				targetActivity = query.findActivity(successors.get(0), container);
			}
			if (null == targetActivity) {
				failures.add("Target Activity for Start Event not available! " + startEvent);
				continue;
			}
			if (transitionPerTarget.containsKey(targetActivity.getId())) {
				TransitionType transition = transitionPerTarget.get(targetActivity.getId());
				concatStartEventTransitionCondition(transition, startEvent.getId());
			} else {
	        	TransitionType transition = TransitionUtil.createTransition(START_ROUTE_PRE_FIX + targetActivity.getId(), START_ROUTE_TRANSITION_NAME, "", processDef, startRoute, targetActivity);
	        	TransitionUtil.setTransitionExpression(transition, PredefinedDataInfo.VAR_START_EVENT_ID + "==\"" + startEvent.getId() + "\";");
	        	transitionPerTarget.put(targetActivity.getId(), transition);
			}
        	addJmsStartEventHeaders(startEvent, processDef);
		}
		return true;
	}

	private void concatStartEventTransitionCondition(TransitionType transition, String startEventId) {
		String expression = TransitionUtil.getTransitionExpression(transition);
		if (null == expression) {
			failures.add("Failed concating condtition for Multi-Start Event - no existing condition.");
			return;
		}
		if (expression.trim().endsWith(";")) {
			// remove expected semicolon
			expression = expression.trim().substring(0, expression.length()-1);
			// remove expected semicolon concatenate OR operator and alternative eventId for transition
			expression = expression.concat(" || ").concat(PredefinedDataInfo.VAR_START_EVENT_ID + "==\"" + startEventId + "\";");
			// set modified condition as new transition-expression
			TransitionUtil.setTransitionExpression(transition, expression);
		} else {
			failures.add("Failed concating condtition for Multi-Start Event. Existing expression is not valid.");
			return;
		}
	}

	private void addJmsStartEventHeaders(StartEvent startEvent, ProcessDefinitionType processDef) {
		TriggerType trigger = CarnotModelQuery.findTrigger(processDef, startEvent.getId());
		if (null == trigger) {
			if (null == startEvent.getEventDefinitions() || 0 >= startEvent.getEventDefinitions().size()) {
				return; // bpmn 'none' start event as api-trigger is accepted (no failure)
			}
			failures.add("Trigger for BPMN Start Event " + startEvent.getId() + " not found in target model.");
			return;
		}
		if (!PredefinedConstants.JMS_TRIGGER.equals(trigger.getType().getId())) {
			failures.add("Trigger Type not supported for multiple BPMN Start Events " + startEvent.getId() + " (Allowed are 'None StartEvents' for API calls and (JMS) Message StartEvents).");
			return;
		}

        AccessPointType ap = CarnotWorkflowModelFactory.eINSTANCE.createAccessPointType();
        ap.setDirection(DirectionType.OUT_LITERAL);
        ap.setId(PredefinedDataInfo.VAR_START_EVENT_ID);
        ap.setName(PredefinedDataInfo.LBL_START_EVENT_ID);
        ap.setType(query.findDataType(PredefinedConstants.SERIALIZABLE_DATA));

        AttributeUtil.setBooleanAttribute(ap, PredefinedConstants.BROWSABLE_ATT, true);
        AttributeUtil.setAttribute(ap, PredefinedConstants.JMS_LOCATION_PROPERTY, JMSLocation.class.getName(), JMSLocation.HEADER.getId());
        AttributeUtil.setAttribute(ap, PredefinedConstants.CLASS_NAME_ATT, String.class.getName());

        trigger.getAccessPoint().add(ap);

        addJmsStartEventIdDataFlow(trigger, ap);
	}

	private void addJmsStartEventIdDataFlow(TriggerType trigger, AccessPointType ap) {
		DataType startEventVar = query.findVariable(PredefinedDataInfo.VAR_START_EVENT_ID);
		ParameterMappingType mapping = CarnotWorkflowModelFactory.eINSTANCE.createParameterMappingType();
		mapping.setParameter(ap.getId());
		mapping.setData(startEventVar);

		trigger.getParameterMapping().add(mapping);
	}


	private ActivityType insertXOrSplitRoute(FlowElementsContainer container, ProcessDefinitionType processDef) {
		ActivityType route = createRouteActivity(processDef, container.getId() + START_ROUTE_POST_FIX, START_ROUTE_NAME);
        route.setSplit(JoinSplitType.XOR_LITERAL);
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
		if (null != startEvents && 1 < startEvents.size()) return;
		if (null == potentialStartElements || 1 >= potentialStartElements.size()) return;
		insertAndSplitRouteWithTransitionTo(container, potentialStartElements);
	}

	private ActivityType insertParallelRouteForStartEvent(FlowElementsContainer container, StartEvent startEvent, List<FlowNode> successors) {
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		ActivityType route = createRouteActivity(processDef, startEvent.getId() + START_ROUTE_POST_FIX, START_ROUTE_NAME);
        route.setSplit(JoinSplitType.AND_LITERAL);
        for (FlowNode successor: successors) {
        	ActivityType targetActivity = query.findActivity(successor, container);
        	TransitionUtil.createTransition(START_ROUTE_PRE_FIX + successor.getId(), START_ROUTE_TRANSITION_NAME, "", processDef, route, targetActivity);
        }
        return route;
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
