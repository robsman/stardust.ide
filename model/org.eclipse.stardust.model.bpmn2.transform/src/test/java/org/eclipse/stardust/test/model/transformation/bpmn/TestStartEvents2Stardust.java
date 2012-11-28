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

import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_BPMN_MODEL_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_START_EVENT;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_START_EVENT_TIMER_CYCLE_STOP;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_START_EVENT_TIMER_DATE;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_MODEL_OUTPUT_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.getResourceFilePath;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.loadBpmnModel;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.transformModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class TestStartEvents2Stardust {

    @Test
    public void testStartEventNone() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "StartEventNone.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testStartEventNone.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        assertNotNull(process);
        assertNotNull(result);
        TriggerType trigger = CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT);
        assertNotNull(trigger);
        assertEquals(PredefinedConstants.MANUAL_TRIGGER, trigger.getType().getId());
    }

    @Test
    public void testStartEventMessage() {
        // TODO JMS_TRIGGER type is not available

//		final String modelFile = BPMN_MODEL_DIR + "StartEventMessage.bpmn";
//		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testStartEventMessage.xpdl";
//
//		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
//		ProcessDefinitionType process = result.getProcessDefinition().get(0);
//
//		assertNotNull(process);
//		assertNotNull(result);
//		TriggerType trigger =  CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT);
//		assertNotNull(trigger);
//		assertEquals(PredefinedConstants.JMS_TRIGGER, trigger.getType().getId());

    }

    @Test
    public void testStartEventTimer() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "StartEventTimer.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testStartEventTimer.xpdl";

        Calendar cal = Calendar.getInstance();
        cal.set(2012, 8, 18, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long stopTime = cal.getTime().getTime();
        cal.set(2012, 8, 1, 11, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long startTime = cal.getTime().getTime(); //2012-09-01T11:00:00
        String period = "000001:000002:000001:000020:000030:000000";

        Definitions defs = loadBpmnModel(modelFile);
        ModelType result = transformModel(defs, fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);
        assertNotNull(process);
        assertNotNull(result);
        TriggerType triggerCycle = CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT_TIMER_CYCLE_STOP);
        TriggerType triggerDate = CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT_TIMER_DATE);

        assertNotNull(triggerDate);
        assertNotNull(triggerCycle);

        assertEquals(PredefinedConstants.TIMER_TRIGGER, triggerDate.getType().getId());
        assertEquals(PredefinedConstants.TIMER_TRIGGER, triggerCycle.getType().getId());

        String dateStart = AttributeUtil.getAttributeValue(triggerDate, PredefinedConstants.TIMER_TRIGGER_START_TIMESTAMP_ATT);
        String cycleStart = AttributeUtil.getAttributeValue(triggerCycle, PredefinedConstants.TIMER_TRIGGER_START_TIMESTAMP_ATT);
        String cyclePeriod = AttributeUtil.getAttributeValue(triggerCycle, PredefinedConstants.TIMER_TRIGGER_PERIODICITY_ATT);
        String cycleStop = AttributeUtil.getAttributeValue(triggerCycle, PredefinedConstants.TIMER_TRIGGER_STOP_TIMESTAMP_ATT);

        assertEquals(startTime, Long.parseLong(dateStart));
        assertEquals(startTime, Long.parseLong(cycleStart));
        assertEquals(period, cyclePeriod);
        assertEquals(stopTime, Long.parseLong(cycleStop));


    }

}
