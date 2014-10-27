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
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;

import java.util.List;

import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EscalationEventDefinition;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.runtime.ActivityInstanceState;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common.ServiceInterfaceUtil;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventaction.BpmCompleteActivityEventActionBuilder;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventhandler.BpmActivityTimerEventHandlerBuilder;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class NativeIntermediateEvent2Stardust extends AbstractElement2Stardust {

	public static final String ATT_INTERMEDIATE_EVENT_HOST = "stardust:bpmnIntermediateEventHost";
	public static final String ATT_ESCALATION_CODE = "carnot:engine:escalationCode";
	public static final String ATT_ERROR_CODE = "carnot:engine:errorCode";

	public static final String ACTION_THROW_ERROR = "throwError";
	public static final String ACTION_THROW_ESCALATION = "throwEscalation";

	private BpmnModelQuery bpmnquery;

	public NativeIntermediateEvent2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
		bpmnquery = new BpmnModelQuery(logger);
	}

	public void addIntermediateCatchEvent(IntermediateCatchEvent event, FlowElementsContainer container) {
		logger.debug("addIntermediateCatchEvent " + event);
		addEvent(event, container);
	}

	public void addIntermediateThrowEvent(IntermediateThrowEvent event, FlowElementsContainer container) {
		logger.debug("addIntermediateThrowEvent " + event);
		ProcessDefinitionType processDef = getProcessAndReportFailure(event, container);
		EventDefinition def = bpmnquery.getFirstEventDefinition(event);
		if (def instanceof EscalationEventDefinition) {
			createIntermediateEscalationThrowRouteActivity(container, processDef, event, (EscalationEventDefinition)def);
		} else {
			addEvent(event, container);
		}
	}

	public void addEndEvent(EndEvent event, FlowElementsContainer container) {
		logger.debug("addEndEvent " + event);
		ProcessDefinitionType processDef = getProcessAndReportFailure(event, container);
		EventDefinition def = bpmnquery.getFirstEventDefinition(event);
		if (def instanceof TerminateEventDefinition) {
			createTerminateRouteActivity(container, processDef, event, (TimerEventDefinition)def);
		} else if (def instanceof ErrorEventDefinition) {
			createEndErrorThrowRouteActivity(container, processDef, event, (ErrorEventDefinition)def);
		} else if (def instanceof EscalationEventDefinition) {
			createEndEscalationThrowRouteActivity(container, processDef, event, (EscalationEventDefinition)def);
		} else {
			addEvent(event, container);
		}
	}

	protected void addEvent(Event event, FlowElementsContainer container) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(event, container);
		EventDefinition def = bpmnquery.getFirstEventDefinition(event);
		if (def == null) {
			failures.add("No Event Definition found. (Event " + event + " in " + container + ")");
			addNoneEventRoute(container, event);
			return;
		}
		if (def instanceof MessageEventDefinition) {
			createMessageApplicationActivity(processDef, event, (MessageEventDefinition)def, container);
		} else if (def instanceof TimerEventDefinition) {
			createTimerRouteActivity(container, processDef, event, (TimerEventDefinition)def);
		} else {
			logger.warn("Event definition not supported: " + def.getClass().getSimpleName() + " element id : " + def.getId());
		}
	}

	private void createIntermediateEscalationThrowRouteActivity(FlowElementsContainer container, ProcessDefinitionType processDef, Event event, EscalationEventDefinition def) {
		String id = event.getId();
		String name = getNonEmptyName(event.getName(), id, event);
		ActivityType route = createRouteActivity(processDef, id, name);
		AttributeUtil.setBooleanAttribute(route, ATT_INTERMEDIATE_EVENT_HOST, true);

		EventHandlerType handler = createStateChangeHandlerForTargetState(ActivityInstanceState.APPLICATION);
		route.getEventHandler().add(handler);

		EventActionType eventAction = createEscalationThrowAction(def.getEscalationRef().getEscalationCode());
		handler.getEventAction().add(eventAction);

		processDef.getActivity().add(route);
	}

	private void createEndEscalationThrowRouteActivity(FlowElementsContainer container, ProcessDefinitionType processDef, Event event, EscalationEventDefinition def) {
		String id = event.getId();
		String name = getNonEmptyName(event.getName(), id, event);
		ActivityType route = createRouteActivity(processDef, id, name);

		EventHandlerType handler = createStateChangeHandlerForTargetState(ActivityInstanceState.APPLICATION);
		route.getEventHandler().add(handler);

		EventActionType eventAction = createEscalationThrowAction(def.getEscalationRef().getEscalationCode());
		handler.getEventAction().add(eventAction);

		processDef.getActivity().add(route);
	}

	private void createEndErrorThrowRouteActivity(FlowElementsContainer container, ProcessDefinitionType processDef, Event event, ErrorEventDefinition def) {
		String id = event.getId();
		String name = getNonEmptyName(event.getName(), id, event);
		ActivityType route = createRouteActivity(processDef, id, name);
		route.setHibernateOnCreation(true);

		EventHandlerType handler = createStateChangeHandlerForTargetState(ActivityInstanceState.HIBERNATED);
		route.getEventHandler().add(handler);

		EventActionType eventAction = createErrorThrowAction(def.getErrorRef().getErrorCode());
		handler.getEventAction().add(eventAction);

		processDef.getActivity().add(route);
	}

	private EventActionType createEscalationThrowAction(String escalationCode) {
		EventActionType eventAction = CarnotWorkflowModelFactory.eINSTANCE.createEventActionType();
		eventAction.setType(query.findPredefinedEventAction(ACTION_THROW_ESCALATION));
		AttributeUtil.setAttribute(eventAction, ATT_ESCALATION_CODE, String.class.getName(), escalationCode);
		return eventAction;
	}

	private EventActionType createErrorThrowAction(String errorCode) {
		EventActionType eventAction = CarnotWorkflowModelFactory.eINSTANCE.createEventActionType();
		eventAction.setType(query.findPredefinedEventAction(ACTION_THROW_ERROR));
		AttributeUtil.setAttribute(eventAction, ATT_ERROR_CODE, String.class.getName(), errorCode);
		return eventAction;
	}

	private EventHandlerType createStateChangeHandlerForTargetState(int state) {
		EventConditionTypeType stateChangeCondition = getActivityStateChangeConditionType();
		EventHandlerType handler = CarnotWorkflowModelFactory.eINSTANCE.createEventHandlerType();
		handler.setAutoBind(true);
		handler.setType(stateChangeCondition);
		AttributeUtil.setAttribute(handler, PredefinedConstants.TARGET_STATE_ATT, ActivityInstanceState.class.getName(), String.valueOf(state));
		return handler;
	}

	private EventConditionTypeType getActivityStateChangeConditionType() {
		return query.findPredefinedEventCondition(PredefinedConstants.ACTIVITY_STATECHANGE_CONDITION);
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

	private void createTimerRouteActivity(FlowElementsContainer container, ProcessDefinitionType processDef, Event event, TimerEventDefinition def) {
		//intermediateCatchEvent
		String id = event.getId();
		int ordinal = bpmnquery.getEventDefinitionOrdinal(event, def);
		String evtDefinitionId = getChildId(event, def, ordinal);
		String name = getNonEmptyName(event.getName(), id, event);

		Period p = EventDefinitions2Stardust.getPeriod(def);
		ActivityType route = createRouteActivity(processDef, id, name, true);

		EventHandlerType handler = BpmActivityTimerEventHandlerBuilder
								.newActivityTimerEventHandler(route)
								.withId(evtDefinitionId)
								.withAutoBinding()
								.withConstantPeriod(p)
								.build();

		BpmCompleteActivityEventActionBuilder
				.newCompleteActivityAction(handler)
				.withId(evtDefinitionId.concat("Action"))
				.build();
	}

	private void createTerminateRouteActivity(FlowElementsContainer container, ProcessDefinitionType processDef, Event event, TimerEventDefinition def) {
		String id = event.getId();
		int ordinal = bpmnquery.getEventDefinitionOrdinal(event, def);
		String evtDefinitionId = getChildId(event, def, ordinal);
		String name = getNonEmptyName(event.getName(), id, event);

		Period p = EventDefinitions2Stardust.getPeriod(def);
		ActivityType route = createRouteActivity(processDef, id, name, true);

		EventHandlerType handler = BpmActivityTimerEventHandlerBuilder
								.newActivityTimerEventHandler(route)
								.withId(evtDefinitionId)
								.withAutoBinding()
								.withConstantPeriod(p)
								.build();

		BpmCompleteActivityEventActionBuilder
				.newCompleteActivityAction(handler)
				.withId(evtDefinitionId.concat("Action"))
				.build();
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

	public ActivityType addNoneEventRoute(FlowElementsContainer container, Event event) {
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		return createRouteActivity(processDef, event.getId(), event.getName(), false);
	}

	private ActivityType createRouteActivity(ProcessDefinitionType processDef, String id, String name) {
		return newRouteActivity(processDef)
               .withIdAndName(id, name)
               .build();
	}

	private ActivityType createRouteActivity(ProcessDefinitionType processDef, String id, String name, boolean asInitiallyHibernatedEventHost) {
		ActivityType activityType = newRouteActivity(processDef)
               .withIdAndName(id, name)
               .build();
		if (asInitiallyHibernatedEventHost) {
			AttributeUtil.setBooleanAttribute(activityType, ATT_INTERMEDIATE_EVENT_HOST, true);
			activityType.setHibernateOnCreation(true);
		}
		return activityType;
	}

}
