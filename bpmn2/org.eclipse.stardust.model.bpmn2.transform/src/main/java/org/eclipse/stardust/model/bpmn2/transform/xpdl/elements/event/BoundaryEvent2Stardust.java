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

import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ACTIVITY_STATECHANGE_CONDITION;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.EXCEPTION_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.EXCEPTION_CONDITION;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.TARGET_TIMESTAMP_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.TIMER_CONDITION;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;

import java.util.List;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.TransitionUtil;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.XSDType2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventaction.BpmAbortActivityEventActionBuilder;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventaction.BpmSetDataEventActionBuilder;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventhandler.BpmActivityExceptionEventHandlerBuilder;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventhandler.BpmActivityTimerEventHandlerBuilder;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class BoundaryEvent2Stardust extends AbstractElement2Stardust {

	public static final String CONTROL_FLOW_VAR_SUFFIX = "_CFV";
	public static final String BOUNDARY_EVENT_NOT_FIRED_CONDITION = "==0";
	public static final String BOUNDARY_EVENT_FIRED_CONDITION = ">0";

	public static final String BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX = "_BSER";
	public static final String BOUNDARY_SPLIT_HAPPY_ROUTE_POSTFIX = "_BSHR";
	public static final String BOUNDARY_SPLIT_EVENT_TRANSITION_POSTFIX = "_BSET";
	public static final String BOUNDARY_SPLIT_HAPPY_TRANSITION_POSTFIX = "_BSHT";

	private static final String INTERRUPTING_ACTION_POSTFIX = "_IRA";
	private static final String SET_CONTROL_FLOW_DATA_ACTION_POSTFIX = "_CFD";

	private static final String TRUE = "true";
	private static final String PATH_TIMER = "longValue()";
	private static final String PATH_ERROR = "hashCode().toString().length().longValue()";

	private static final String OR_CONJUNCTION = " || ";
	private static final String AND_CONJUNCTION = " && ";
	private static final String EXPRESSION_END = ";";
	private static final String EMPTY_STRING = "";
	private static final String VISIBILITY_PRIVATE = "Private";

	private enum HandlerType {
		TIMER (TIMER_CONDITION),
		ERROR (EXCEPTION_CONDITION),
		STATE (ACTIVITY_STATECHANGE_CONDITION);

		private String typeId;

		private HandlerType(String typeId) {
			this.typeId = typeId;
		}

		public static HandlerType byTypeId(String typeId) {
			for (HandlerType hdl : values()) {
				if (hdl.typeId.equals(typeId)) return hdl;
			}
			return null;
		}
	}

	private BpmnModelQuery bpmnquery;
	private DataType controlFlowData;
	private ProcessDefinitionType processDef = null;

	public BoundaryEvent2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
		bpmnquery = new BpmnModelQuery(logger);
	}

	public void addBoundaryEvent(BoundaryEvent event, FlowElementsContainer container) {
		processDef = query.findProcessDefinition(container.getId());
		logger.debug("addBoundaryEvent " + event);
		if (event.getAttachedToRef() == null) {
			failures.add("Boundary Event not attached to activity " + event);
			return;
		}
		ActivityType eventHolder = addEvent(event, container);
		if (null != eventHolder) {
			createBoundaryEventControl(event, eventHolder, controlFlowData);
		}
	}

	private static String getControlFlowVariableName(String eventId) {
		return eventId + CONTROL_FLOW_VAR_SUFFIX;
	}

	private static String getControlFlowVariableId(BoundaryEvent event) {
		return getControlFlowVariableName(event.getId());
	}

	private ActivityType addEvent(BoundaryEvent event, FlowElementsContainer container) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(event, container);
		EventDefinition def = bpmnquery.getFirstEventDefinition(event);
		if (def == null) {
			failures.add("No Event Definition found. (Event " + event + " in " + container + ")");
			return null;
		}
		if (def instanceof TimerEventDefinition) {
			return createTimerHandler(container, processDef, event, (TimerEventDefinition)def);

		} else if (def instanceof ErrorEventDefinition) {
			return createErrorHandler(container, processDef, event, (ErrorEventDefinition)def);

		} else {
			failures.add("Boundary Event Type " + event + ": " + def.eClass().getName() + " not supported.");
			return null;
		}
	}

	private ActivityType createTimerHandler(FlowElementsContainer container, ProcessDefinitionType processDef, BoundaryEvent event, TimerEventDefinition def) {
		String id = event.getId();
		String name = getNonEmptyName(event.getName(), id, event);
		Period timerPeriod = EventDefinitions2Stardust.getPeriod(def);
		if (timerPeriod == null) {
			failures.add("Invalid time period for boundary event definition " + event + " " + def);
			return null;
		}
		ActivityType activity = query.findActivity(event.getAttachedToRef(), container);
		if (activity == null) {
			failures.add("No activity found for attached boundary event " + event);
			return null;
		}
		EventHandlerType handler = createBoundaryTimerHandler(event, activity, id, name, timerPeriod);
		createControlFlowDataAction(handler, event, id, name);
		createCancelActivityAction(handler, id, name);
		return activity;
	}

    private ActivityType createErrorHandler(FlowElementsContainer container, ProcessDefinitionType processDef, BoundaryEvent event, ErrorEventDefinition def) {
		String id = event.getId();
		String name = getNonEmptyName(event.getName(), id, event);
		String errorCode = EventDefinitions2Stardust.getErrorCode(BpmnModelQuery.getModelDefinitions(def), def);
		if (errorCode == null) {
			failures.add("No Error defined for definition " + event + " " + def);
			return null;
		}
		ActivityType activity = query.findActivity(event.getAttachedToRef(), container);
		if (activity == null) {
			failures.add("No activity found for attached boundary event " + event);
			return null;
		}
		EventHandlerType handler = createBoundaryErrorHandler(event, activity, id, name, errorCode);
		createControlFlowDataAction(handler, event, id, name);
		createCancelActivityAction(handler, id, name);
		return activity;
	}

	private EventHandlerType createBoundaryTimerHandler(BoundaryEvent event, ActivityType activity, String id, String name, Period p) {
		return BpmActivityTimerEventHandlerBuilder
				.newActivityTimerEventHandler(activity)
				.withId(id)
				.withName(name)
				.withAutoBinding()
				.withConstantPeriod(p)
				.build();
	}

	private EventHandlerType createBoundaryErrorHandler(BoundaryEvent event, ActivityType activity, String id, String name, String errorCode) {
		return BpmActivityExceptionEventHandlerBuilder
				.newActivityExceptionEventHandler(activity)
				.withId(id)
				.withName(name)
				.withAutoBinding()
				.forExceptionClass(errorCode)
				.build();
	}

	private EventActionType createCancelActivityAction(EventHandlerType handler, String id, String name) {
		return BpmAbortActivityEventActionBuilder
				.newAbortActivityAction(handler)
				.withId(id + INTERRUPTING_ACTION_POSTFIX)
				.withName(name + INTERRUPTING_ACTION_POSTFIX)
				.withScopeSubHierarchy()
				.build();
	}

	private EventActionType createControlFlowDataAction(EventHandlerType handler, BoundaryEvent event, String id, String name) {

		String accessPoint = getEventDataAccessPoint(HandlerType.byTypeId(handler.getType().getId())); // TARGET_TIMESTAMP_ATT;
		String accessPath =  getEventDataAccessPath(HandlerType.byTypeId(handler.getType().getId())); //"longValue()";
		controlFlowData = createControlFlowData(event);
		String dataPath = EMPTY_STRING;
		return  BpmSetDataEventActionBuilder
				.newSetDataAction(handler)
				.withId(id + SET_CONTROL_FLOW_DATA_ACTION_POSTFIX)
				.withName(name + SET_CONTROL_FLOW_DATA_ACTION_POSTFIX)
				.fromAccessPoint(accessPoint)
				.fromAccessPath(accessPath)
				.settingData(controlFlowData)
				.settingDataPath(dataPath)
				.build();
	}

	private DataType createControlFlowData(BoundaryEvent event) {
		XSDType2Stardust dataType = XSDType2Stardust.LONG;
		DataType variable =
				BpmModelBuilder.newPrimitiveVariable(carnotModel)
				.withIdAndName(getControlFlowVariableId(event), getNonEmptyName(event.getName(), event.getId(), event) + CONTROL_FLOW_VAR_SUFFIX)
				.ofType(dataType.getType())
				.havingDefaultValue(0l)
				.build();
		variable.setPredefined(false);
		AttributeUtil.setAttribute(variable, PredefinedConstants.MODELELEMENT_VISIBILITY, VISIBILITY_PRIVATE);
		return variable;
	}

	private void createBoundaryEventControl(BoundaryEvent event, ActivityType eventHolder, DataType controlFlowData) {

		String happyRouteId = getBoundaryEventHappyPathRouteId(eventHolder);
		String eventRouteId = getBoundaryEventEventPathRouteId(event);
		String happyTransitionId = getBoundaryEventHappyPathTransitionId(eventHolder);
		String eventTransitionId = getBoundaryEventEventPathTransitionId(event);

		ActivityType happyRoute = CarnotModelQuery.findActivity(processDef, happyRouteId);
		ActivityType eventRoute = CarnotModelQuery.findActivity(processDef, eventRouteId);
		TransitionType happyTransition = CarnotModelQuery.findTransition(processDef, happyTransitionId);
		TransitionType eventTransition = CarnotModelQuery.findTransition(processDef, eventTransitionId);

		if (null == happyRoute) {
			String happyRouteName = getBoundaryEventHappyPathRouteName(eventHolder);
			happyRoute = createBoundaryEventControlRoute(happyRouteId, happyRouteName);
		}
		if (null == eventRoute) {
			String eventRouteName = getBoundaryEventEventPathRouteName(eventHolder, event);
			eventRoute = createBoundaryEventControlRoute(eventRouteId, eventRouteName);
		}
		if (null == happyTransition) {
			String happyTransitionName = getBoundaryEventHappyPathTransitionName(eventHolder);
			eventHolder.setSplit(JoinSplitType.XOR_LITERAL);
			happyTransition = createBoundaryControlTransition(happyTransitionId, happyTransitionName, eventHolder, happyRoute);
		}
		if (null == eventTransition) {
			String eventTransitionName = getBoundaryEventEventPathTransitionName(eventHolder, event);
			eventTransition = createBoundaryControlTransition(eventTransitionId, eventTransitionName, eventHolder, eventRoute);
		}

		addBoundaryEventControllCondition(happyTransition, eventTransition, controlFlowData);
	}

	private void addBoundaryEventControllCondition(TransitionType happyTransition, TransitionType eventTransition, DataType controlFlowData) {
		String happyExpression = TransitionUtil.getTransitionExpression(happyTransition);
		String eventExpression = TransitionUtil.getTransitionExpression(eventTransition);
		String happyConjunction = AND_CONJUNCTION;
		String eventConjunction = OR_CONJUNCTION;
		if (TRUE.equals(happyExpression) || happyExpression.isEmpty()) {
			happyExpression = EMPTY_STRING;
			happyConjunction = EMPTY_STRING;
		}
		if (TRUE.equals(eventExpression) || eventExpression.isEmpty()) {
			eventExpression = EMPTY_STRING;
			eventConjunction = EMPTY_STRING;
		}

		happyExpression = happyExpression.replaceAll(EXPRESSION_END, "");
		happyExpression += happyConjunction + controlFlowData.getId() + BOUNDARY_EVENT_NOT_FIRED_CONDITION + EXPRESSION_END;

		eventExpression = eventExpression.replaceAll(EXPRESSION_END, "");
		eventExpression += eventConjunction + controlFlowData.getId() + BOUNDARY_EVENT_FIRED_CONDITION + EXPRESSION_END;

		TransitionUtil.setTransitionExpression(happyTransition, happyExpression);
		TransitionUtil.setTransitionExpression(eventTransition, eventExpression);
	}

	private TransitionType createBoundaryControlTransition(String id, String name, ActivityType source, ActivityType target) {
		return TransitionUtil.createTransition(id, name, EMPTY_STRING, processDef, source, target);
	}

	private ActivityType createBoundaryEventControlRoute(String routeId, String routeName) {
        return newRouteActivity(processDef)
                .withIdAndName(routeId, routeName)
                .build();
	}

	private String getEventDataAccessPoint(HandlerType handlerType) {
		String accessPoint = EMPTY_STRING;
		switch (handlerType) {
		case TIMER:
			accessPoint = TARGET_TIMESTAMP_ATT;
			break;
		case ERROR:
			accessPoint = EXCEPTION_ATT;
			break;
		}
		return accessPoint;
	}

	private String getEventDataAccessPath(HandlerType handlerType) {
		switch (handlerType) {
		case TIMER:
			return PATH_TIMER;
		case ERROR:
			return PATH_ERROR;
		}
		return null;
	}

	public static String getBoundaryEventHappyPathRouteId(ActivityType eventHolder) {
		if (null == eventHolder) return BOUNDARY_SPLIT_HAPPY_ROUTE_POSTFIX;
		return eventHolder.getId() + BOUNDARY_SPLIT_HAPPY_ROUTE_POSTFIX;
	}

	public static String getBoundaryEventHappyPathRouteName(ActivityType eventHolder) {
		return eventHolder.getName() + BOUNDARY_SPLIT_HAPPY_ROUTE_POSTFIX;
	}

	public static String getBoundaryEventHappyPathTransitionId(ActivityType eventHolder) {
		return eventHolder.getId() + BOUNDARY_SPLIT_HAPPY_TRANSITION_POSTFIX;
	}

	public static String getBoundaryEventHappyPathTransitionName(ActivityType eventHolder) {
		return eventHolder.getName() + BOUNDARY_SPLIT_HAPPY_TRANSITION_POSTFIX;
	}

	public static String getBoundaryEventEventPathRouteId(BoundaryEvent event) {
		return event.getId() + BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX;
	}

	public static String getBoundaryEventEventPathRouteName(ActivityType eventHolder, BoundaryEvent event) {
		return eventHolder.getName() + "_" + event.getName() + BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX;
	}

	public static String getBoundaryEventEventPathTransitionId(BoundaryEvent event) {
		return event.getId() + BOUNDARY_SPLIT_EVENT_TRANSITION_POSTFIX;
	}

	public static String getBoundaryEventEventPathTransitionName(ActivityType eventHolder, BoundaryEvent event) {
		return eventHolder.getName() + "_" + event.getName() + BOUNDARY_SPLIT_EVENT_TRANSITION_POSTFIX;
	}

	/*
	public static String getBoundaryEventEventPathRouteId(ActivityType eventHolder) {
		return eventHolder.getId() + BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX;
	}

	public static String getBoundaryEventEventPathRouteName(ActivityType eventHolder) {
		return eventHolder.getName() + BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX;
	}

	public static String getBoundaryEventEventPathTransitionId(ActivityType eventHolder) {
		return eventHolder.getId() + BOUNDARY_SPLIT_EVENT_TRANSITION_POSTFIX;
	}

	public static String getBoundaryEventEventPathTransitionName(ActivityType eventHolder) {
		return eventHolder.getName() + BOUNDARY_SPLIT_EVENT_TRANSITION_POSTFIX;
	}
	*/


}
