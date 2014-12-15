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

import java.util.List;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventaction.BpmAbortActivityEventActionBuilder;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventhandler.BpmActivityExceptionEventHandlerBuilder;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventhandler.BpmActivityTimerEventHandlerBuilder;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class NativeBoundaryEvent2Stardust extends AbstractElement2Stardust {

	
	public static final String ATT_BOUNDARY_EVENT_TYPE = "carnot:engine:event:boundaryEventType";
	
	public static final String VAL_TYPE_INTERRUPTING = "Interrupting";
	public static final String VAL_TYPE_NON_INTERRUPTING = "Non-interrupting";
	
	private BpmnModelQuery bpmnquery;

	public NativeBoundaryEvent2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
		bpmnquery = new BpmnModelQuery(logger);
	}

	public void addBoundaryEvent(BoundaryEvent event, FlowElementsContainer container) {
		//processDef = query.findProcessDefinition(container.getId());
		logger.debug("addBoundaryEvent " + event);
		if (event.getAttachedToRef() == null) {
			failures.add("Boundary Event not attached to activity " + event);
			return;
		}
		if (null != event.getOutgoing() && event.getOutgoing().size() > 1) {
			failures.add("Only one outgoing sequence is supported for boundary events - Event is not transformed: " + event);
			return;			
		}

		addEvent(event, container);
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
		if (event.isCancelActivity()) {
			AttributeUtil.setAttribute(handler, ATT_BOUNDARY_EVENT_TYPE, VAL_TYPE_INTERRUPTING);
			createCancelActivityAction(handler, id, name);
		} else {
			AttributeUtil.setAttribute(handler, ATT_BOUNDARY_EVENT_TYPE, VAL_TYPE_NON_INTERRUPTING);
		}
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
		AttributeUtil.setAttribute(handler, ATT_BOUNDARY_EVENT_TYPE, VAL_TYPE_INTERRUPTING); // error is always interrupting
		createCancelActivityAction(handler, id, name);
		return activity;
	}

	private EventHandlerType createBoundaryTimerHandler(BoundaryEvent event, ActivityType activity, String id, String name, Period p) {
		EventHandlerType handler = 
				BpmActivityTimerEventHandlerBuilder
				.newActivityTimerEventHandler(activity)
				.withId(id+"Hdl")
				.withName(name+"Hdl")
				.withAutoBinding()
				.withConstantPeriod(p)
				.build();
		
		AttributeUtil.setAttribute(handler, PredefinedConstants.TIMER_PERIOD_ATT, "Period", p.toString());
		return handler;
	}

	private EventHandlerType createBoundaryErrorHandler(BoundaryEvent event, ActivityType activity, String id, String name, String errorCode) {
		return BpmActivityExceptionEventHandlerBuilder
				.newActivityExceptionEventHandler(activity)
				.withId(id + "Hdl")
				.withName(name + "Hdl")
				.withAutoBinding()
				.forExceptionClass(errorCode)
				.build();
	}

	private EventActionType createCancelActivityAction(EventHandlerType handler, String id, String name) {

		EventActionType action = 
				BpmAbortActivityEventActionBuilder
				.newAbortActivityAction(handler)
				.withId(id + "Action")
				.withName(name + "Action")
				.withScopeSubHierarchy()
				.build();
		return action;
		//action.setType(PredefinedConstants.ABORT_ACTIVITY_ACTION);
	}

}
