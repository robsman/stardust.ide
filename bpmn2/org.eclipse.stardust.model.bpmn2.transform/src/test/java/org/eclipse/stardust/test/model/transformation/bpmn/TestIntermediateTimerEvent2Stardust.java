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


import static org.eclipse.stardust.engine.api.runtime.ActivityInstanceState.COMPLETED;
import static org.eclipse.stardust.engine.api.runtime.ActivityInstanceState.HIBERNATED;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_BPMN_MODEL_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_MAIN_PROCESS;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_A;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_B;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_MODEL_OUTPUT_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.getResourceFilePath;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.loadBpmnModel;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.transformModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.NativeIntermediateEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.junit.Test;

/**
 * @author Simon Nikles
 *
 */
public class TestIntermediateTimerEvent2Stardust extends Bpmn2StardustTestSuite {
	
	private static final String ID_TIMER_EVENT = "TestModelIntermediateTimer";
	private static final String INTERMEDIATE_EVENT_HOST = null;

    @Test
    public void testIntermediateTimer() {

    	final String modelFile = TEST_BPMN_MODEL_DIR + "CatchingIntermediateTimerEvent.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testCatchingIntermediateTimerEvent.xpdl";

        final String DURATION = "000000:000000:000000:000001:000030:000000";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        assertNotNull(result);
        ProcessDefinitionType process = CarnotModelQuery.findProcessDefinition(result, TEST_ID_MAIN_PROCESS);
        assertNotNull(process);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        assertNotNull(taskB);
        ActivityType eventRoute = CarnotModelQuery.findActivity(process, ID_TIMER_EVENT);
        assertNotNull(eventRoute);

        boolean isEventHost = AttributeUtil.getBooleanValue(eventRoute, NativeIntermediateEvent2Stardust.ATT_INTERMEDIATE_EVENT_HOST);
        assertTrue(isEventHost);
        assertTrue(eventRoute.isHibernateOnCreation());
        
        EList<EventHandlerType> eventHandler = eventRoute.getEventHandler();
        EventHandlerType handler = eventHandler.get(0);
        assertNotNull(handler);
        //assertNotNull(handler.getBindAction());
        assertNotNull(handler.getEventAction());
        //assertEquals(1, handler.getBindAction().size());
        assertEquals(1, handler.getEventAction().size());

        //BindActionType bindAction = handler.getBindAction().get(0);
        EventActionType eventAction = handler.getEventAction().get(0);
        //int bindTargetState = Integer.parseInt(AttributeUtil.getAttributeValue(bindAction, PredefinedConstants.TARGET_STATE_ATT));
        int eventTargetState = Integer.parseInt(AttributeUtil.getAttributeValue(eventAction, PredefinedConstants.TARGET_STATE_ATT));
        boolean useData = Boolean.parseBoolean(AttributeUtil.getAttributeValue(handler, PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT));
        String period = AttributeUtil.getAttributeValue(handler, PredefinedConstants.TIMER_PERIOD_ATT);
        assertTrue(handler.isAutoBind());
        EventConditionTypeType type = handler.getType();
        assertEquals(PredefinedConstants.TIMER_CONDITION, type.getId());
//        assertEquals(PredefinedConstants.SCHEDULE_ACTIVITY_ACTION, bindAction.getType().getId());
        assertEquals(PredefinedConstants.COMPLETE_ACTIVITY_ACTION, eventAction.getType().getId());
        //assertEquals(HIBERNATED, bindTargetState);
        assertEquals(COMPLETED, eventTargetState);
        assertEquals(DURATION, period);
        assertFalse(useData);
        
    }

}
