package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

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
			handleBackloopsToStart(container, startEvents);
			handleParallelStarts(container, startEvents, potentialStartElements);
		}
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
