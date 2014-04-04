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
package org.eclipse.stardust.test.model.transformation.bpmn;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.junit.Test;

/**
 * @author Simon Nikles
 *
 */
public class TestStartEvents2Stardust extends Bpmn2StardustTestSuite {

    @Test
    public void testStartEventNone() {
        final String modelFile = "StartEventNone.bpmn";

        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType process = result.getProcessDefinition().get(0);
        assertThat(process, is(not(nullValue())));
        assertThat(result, is(not(nullValue())));

        TriggerType trigger = CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT);
        assertThat(trigger, is(not(nullValue())));
        assertThat(trigger.getType().getId(), is(PredefinedConstants.MANUAL_TRIGGER));

        String performer = AttributeUtil.getAttributeValue(trigger, PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT);
        assertThat(performer, is(PredefinedConstants.ADMINISTRATOR_ROLE));
    }

    @Test
    public void testStartEventTimer() {
        final String modelFile = "StartEventTimer.bpmn";

        Calendar cal = Calendar.getInstance();
        cal.set(2012, 8, 18, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long stopTime = cal.getTime().getTime();
        cal.set(2012, 8, 1, 11, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long startTime = cal.getTime().getTime(); //2012-09-01T11:00:00
        String period = "000001:000002:000001:000020:000030:000000";

        Definitions defs = loadBpmnModel(modelFile);
        ModelType result = transformModel(defs);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);
        assertThat(process, is(not(nullValue())));
        assertThat(result, is(not(nullValue())));

        TriggerType triggerCycle = CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT_TIMER_CYCLE_STOP);
        TriggerType triggerDate = CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT_TIMER_DATE);

        assertThat(triggerDate, is(not(nullValue())));
        assertThat(triggerCycle, is(not(nullValue())));
        assertThat(typeOf(triggerDate), is(PredefinedConstants.TIMER_TRIGGER));
        assertThat(typeOf(triggerCycle), is(PredefinedConstants.TIMER_TRIGGER));

        String dateStart = AttributeUtil.getAttributeValue(triggerDate, PredefinedConstants.TIMER_TRIGGER_START_TIMESTAMP_ATT);
        String cycleStart = AttributeUtil.getAttributeValue(triggerCycle, PredefinedConstants.TIMER_TRIGGER_START_TIMESTAMP_ATT);
        String cyclePeriod = AttributeUtil.getAttributeValue(triggerCycle, PredefinedConstants.TIMER_TRIGGER_PERIODICITY_ATT);
        String cycleStop = AttributeUtil.getAttributeValue(triggerCycle, PredefinedConstants.TIMER_TRIGGER_STOP_TIMESTAMP_ATT);

        assertThat(dateStart, is (String.valueOf(startTime)));
        assertThat(cycleStart, is (String.valueOf(startTime)));
        assertThat(cyclePeriod, is (period) );
        assertThat(cycleStop, is (String.valueOf(stopTime)) );
    }

    @Test
    public void testStartEventCamelTimer() {
        final String modelFile = "StartEventTimerCamel.bpmn";

        Definitions defs = loadBpmnModel(modelFile);
        ModelType result = transformModel(defs);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);
        assertThat(process, is(not(nullValue())));
        assertThat(result, is(not(nullValue())));

        TriggerType triggerType = CarnotModelQuery.findTrigger(process, "TestModelStartEventCamel");

        assertThat(triggerType, is(not(nullValue())));

        String camelContext = AttributeUtil.getAttributeValue(triggerType, "carnot:engine:camel::camelContextId");

        assertThat(camelContext, is ("defaultCamelContext"));
    }

	private String typeOf(TriggerType triggerType) {
		return triggerType.getType().getId();
	}

}
