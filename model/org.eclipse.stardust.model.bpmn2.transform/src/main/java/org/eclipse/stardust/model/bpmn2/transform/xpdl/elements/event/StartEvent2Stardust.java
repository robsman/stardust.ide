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

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualTrigger;

import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDLExtension;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnTimerCycle;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class StartEvent2Stardust extends AbstractElement2Stardust {

	private BpmnModelQuery bpmnquery;

	public StartEvent2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
		bpmnquery = new BpmnModelQuery();
	}

	public void addStartEvent(StartEvent event, FlowElementsContainer container) {
		logger.debug("addStartEvent " + event);
		EventDefinition def = bpmnquery.getFirstEventDefinition(event);

		if (!checkAndReportElementSupport(event, def, container)) return;

		ProcessDefinitionType processDef = getProcessOrReportFailure(event, container);
		if (processDef == null) return;

		if (def == null) {
			failures.add("StartEvent - no event definition available (" + event.getId() + "). Manual Start is assumed.");
			addManualTrigger(event, container, processDef);
		} else if (def instanceof MessageEventDefinition) {
			addMessageTrigger(event, (MessageEventDefinition) def, container, processDef);
		} else if (def instanceof TimerEventDefinition) {
			addTimerTrigger(event, (TimerEventDefinition) def, container, processDef);
		}
	}

    private boolean checkAndReportElementSupport(StartEvent event, EventDefinition def, FlowElementsContainer container) {
		int eventDefCount = bpmnquery.countEventDefinitions(event);
		if (eventDefCount > 1) {
			failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "StartEvent - Multiple Event definitions " + event.getId());
			return false;
		}
		if (def== null
			|| def instanceof MessageEventDefinition
			|| def instanceof TimerEventDefinition)
			return true;
		else {
			failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "StartEvent " + event.getId()
					+ " EventDefinition " + def.getClass().getName());
			return false;
		}
	}

	private void addManualTrigger(StartEvent event, FlowElementsContainer container, ProcessDefinitionType processDef) {
        logger.debug("addManualTrigger " + event);
        TriggerType trigger = newManualTrigger(processDef)
            .withIdAndName(event.getId(), event.getName())
            .build();
        Bpmn2StardustXPDLExtension.addStartEventExtensions(event, trigger);
    }

    private void addMessageTrigger(StartEvent event, MessageEventDefinition def, FlowElementsContainer container, ProcessDefinitionType processDef) {
        logger.debug("addMessageTrigger (JMS) " + event);
        TriggerTypeType triggerType = Bpmn2StardustXPDLExtension.getMessageStartEventTriggerType(event, carnotModel);
        if (triggerType != null) {
            TriggerType trigger = AbstractElementBuilder.F_CWM.createTriggerType();
            trigger.setType(triggerType);
            trigger.setId(event.getId());
            trigger.setName(event.getName());
            Bpmn2StardustXPDLExtension.addMessageStartEventExtensions(event, trigger);
            processDef.getTrigger().add(trigger);
        } else {
            failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_CREATION + "(Start event: " + event.getId() + " - trigger type + " + PredefinedConstants.JMS_TRIGGER + " not found)");
        }
    }

    private void addTimerTrigger(StartEvent event, TimerEventDefinition def, FlowElementsContainer container, ProcessDefinitionType processDef) {
        logger.debug("addTimerTrigger " + event);

        TriggerTypeType triggerType = XpdlModelUtils.findElementById(carnotModel.getTriggerType(), PredefinedConstants.TIMER_TRIGGER);
        if (triggerType != null) {
            TriggerType trigger = AbstractElementBuilder.F_CWM.createTriggerType();
            trigger.setType(triggerType);
            trigger.setId(event.getId());
            trigger.setName(event.getName());
            setTimerTriggerDefinition(event, def, trigger);
            Bpmn2StardustXPDLExtension.addTimerStartEventExtensions(event, trigger);
            processDef.getTrigger().add(trigger);
        } else {
            failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_CREATION + "(Start event: " + event.getId() + " - trigger type + " + PredefinedConstants.JMS_TRIGGER + " not found)");
        }
    }

    private void setTimerTriggerDefinition(StartEvent event, TimerEventDefinition eventDef, TriggerType trigger) {
        // According to OMG-BPMN, only one (date, cycle or duration) for executable processes;
        // duration is not considered here (rather useful for waiting-timers)
        if (eventDef.getTimeCycle()!= null) {
            setTimerCycleDefinition(eventDef, trigger);
        } else if (eventDef.getTimeDate() != null) {
            setTimerTimeDefinition(event, eventDef, trigger);
        }
    }

    private void setTimerCycleDefinition(TimerEventDefinition eventDef, TriggerType trigger) {
        Expression cycleExpression =  eventDef.getTimeCycle();
        if (cycleExpression instanceof FormalExpression) {
            FormalExpression formalCycle = (FormalExpression)cycleExpression;
            String body = formalCycle.getBody();
            BpmnTimerCycle cycle = BpmnTimerCycle.getCycle(body);
            Duration dur = cycle.getCycleDuration();
            Period p = new Period((short)dur.getYears(), (short)dur.getMonths(), (short)dur.getDays(), (short)dur.getHours(), (short)dur.getMinutes(), (short)dur.getSeconds());
            AttributeUtil.setAttribute(trigger, PredefinedConstants.TIMER_TRIGGER_START_TIMESTAMP_ATT, "long", String.valueOf(cycle.getStartDate().getTime()));
            AttributeUtil.setAttribute(trigger, PredefinedConstants.TIMER_TRIGGER_PERIODICITY_ATT, "Period", String.valueOf(p.toString()));
        } else {
            String expression = DocumentationTool.getInformalExpressionValue(cycleExpression);
            DescriptionType descriptor = trigger.getDescription() != null ? trigger.getDescription() : AbstractElementBuilder.F_CWM.createDescriptionType();
            String descr = XpdlModelUtils.getCDataString(descriptor.getMixed());
            XpdlModelUtils.setCDataString(descriptor.getMixed(), expression.concat(descr), true);
        }
    }

    private void setTimerTimeDefinition(StartEvent event, TimerEventDefinition eventDef, TriggerType trigger) {
        Expression timeExpression =  eventDef.getTimeDate();
        if (timeExpression instanceof FormalExpression) {
            FormalExpression formalCycle = (FormalExpression)timeExpression;
            String body = formalCycle.getBody();
            try {
                Date d = DatatypeFactory.newInstance().newXMLGregorianCalendar(body).toGregorianCalendar().getTime();
                AttributeUtil.setAttribute(trigger, PredefinedConstants.TIMER_TRIGGER_START_TIMESTAMP_ATT, "long", String.valueOf(d.getTime()));
            } catch (Exception e) {
                logger.info("Date cannot be parsed: " + body + " (Start Event " + event.getId() + " )");
            }
        } else {
            String expression = DocumentationTool.getInformalExpressionValue(timeExpression);
            DescriptionType descriptor = trigger.getDescription() != null ? trigger.getDescription() : AbstractElementBuilder.F_CWM.createDescriptionType();
            String descr = XpdlModelUtils.getCDataString(descriptor.getMixed());
            if (null!=descr) expression = expression.concat(descr);
            XpdlModelUtils.setCDataString(descriptor.getMixed(), expression, true);
            trigger.setDescription(descriptor);
        }
    }

}
