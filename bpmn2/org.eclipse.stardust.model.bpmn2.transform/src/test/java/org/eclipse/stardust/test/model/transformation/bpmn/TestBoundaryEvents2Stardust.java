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

import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ABORT_ACTION_SCOPE_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ABORT_ACTIVITY_ACTION;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.EXCEPTION_CLASS_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.TIMER_PERIOD_ATT;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.BOUNDARY_EVENT_FIRED_CONDITION;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.BOUNDARY_EVENT_NOT_FIRED_CONDITION;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.BOUNDARY_SPLIT_HAPPY_ROUTE_POSTFIX;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.CONTROL_FLOW_VAR_SUFFIX;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.stardust.engine.core.runtime.beans.AbortScope;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.NativeBoundaryEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.junit.Ignore;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestBoundaryEvents2Stardust extends Bpmn2StardustTestSuite {

	private static final String TEST_ID_GATEWAY = "TestModelGateway";
	private static final String TEST_ID_TASK_DEFAULT = "TestModelTaskDefault";
	private static final String TEST_ID_TASK_EXCEPTIONAL = "TestModelTaskException";
	private static final String TEST_ID_BOUNDARY_EVENT = "TestModelBoundaryEventHdl";
	private static final String TEST_ID_SECOND_BOUNDARY_EVENT = "TestModelSecondBoundaryEvent"; // timer (for multi-event-models)
	private static final String TEST_ID_TASK_HAPPY_PATH = TEST_ID_TASK_A + BOUNDARY_SPLIT_HAPPY_ROUTE_POSTFIX; // "_BSHR";
	private static final String TEST_ID_TASK_EVENT_PATH = TEST_ID_BOUNDARY_EVENT + BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX; //"_BSER";
	private static final String TEST_ID_TASK_SECOND_EVENT_PATH = TEST_ID_SECOND_BOUNDARY_EVENT + BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX; //"_BSER";

	private static final String TEST_ID_EVENT_CONTROL_FLOW_VARIABLE = TEST_ID_BOUNDARY_EVENT + CONTROL_FLOW_VAR_SUFFIX;
	private static final String TEST_ID_SECOND_EVENT_CONTROL_FLOW_VARIABLE = TEST_ID_SECOND_BOUNDARY_EVENT + CONTROL_FLOW_VAR_SUFFIX;

	private static final String CONDITION_HAPPY_PATH = TEST_ID_EVENT_CONTROL_FLOW_VARIABLE + BOUNDARY_EVENT_NOT_FIRED_CONDITION + ";";
	private static final String CONDITION_EVENT_PATH = "ON_BOUNDARY_EVENT(" + TEST_ID_BOUNDARY_EVENT + ")"; //TEST_ID_EVENT_CONTROL_FLOW_VARIABLE + BOUNDARY_EVENT_FIRED_CONDITION + ";";
	private static final String CONDITION_SECOND_EVENT_PATH = TEST_ID_SECOND_EVENT_CONTROL_FLOW_VARIABLE + BOUNDARY_EVENT_FIRED_CONDITION + ";";

	private static final String FIVE_SECONDS_PERIOD = "000000:000000:000000:000000:000000:000005";

    @Test
    public void testBoundaryTimerEvent() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "BoundaryTimerEvent.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testBoundaryTimerEvent.xpdl";

        ModelType model = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = model.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType routeHappyPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_DEFAULT);
        assertNotNull(routeHappyPath);
        ActivityType routeEventPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_EXCEPTIONAL);
        assertNotNull(routeEventPath);

        EventHandlerType timerEventHandler = CarnotModelQuery.findEventHandler(taskA, TEST_ID_BOUNDARY_EVENT);
        assertNotNull(timerEventHandler);
        String eventType = AttributeUtil.getAttributeValue(timerEventHandler, NativeBoundaryEvent2Stardust.ATT_BOUNDARY_EVENT_TYPE);
        assertEquals(NativeBoundaryEvent2Stardust.VAL_TYPE_INTERRUPTING, eventType);

        EventActionTypeType cancelActivityActionType = ModelUtils.findElementById(model.getEventActionType(), ABORT_ACTIVITY_ACTION);
        assertNotNull(cancelActivityActionType);
        EventActionType cancelAction = getFirstEventActionOfType(timerEventHandler, cancelActivityActionType);
        assertNotNull(cancelAction);

        String timerPeriod = AttributeUtil.getAttributeValue(timerEventHandler, TIMER_PERIOD_ATT);
        boolean timerUsesData = AttributeUtil.getBooleanValue(timerEventHandler, TIMER_CONDITION_USE_DATA_ATT);
        String abortScope = AttributeUtil.getAttributeValue(cancelAction, ABORT_ACTION_SCOPE_ATT);

        assertNull(CarnotModelQuery.findActivity(process, TEST_ID_BOUNDARY_EVENT));

        assertTrue(transitionExistsBetween(taskA, routeHappyPath));
        assertTrue(transitionExistsBetween(taskA, routeEventPath));

        assertEquals("true", transitionConditionBetween(taskA, routeHappyPath));
        assertEquals(CONDITION_EVENT_PATH, transitionConditionBetween(taskA, routeEventPath));

        assertFalse(timerUsesData);
        assertEquals(FIVE_SECONDS_PERIOD, timerPeriod);

        assertEquals(AbortScope.SUB_HIERARCHY, abortScope);

    }

    @Test
    public void testBoundaryErrorEvent() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "BoundaryErrorEvent.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testBoundaryErrorEvent.xpdl";

        ModelType model = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = model.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType routeHappyPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_DEFAULT);
        assertNotNull(routeHappyPath);
        ActivityType routeEventPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_EXCEPTIONAL);
        assertNotNull(routeEventPath);

        EventHandlerType exceptionEventHandler = CarnotModelQuery.findEventHandler(taskA, TEST_ID_BOUNDARY_EVENT);
        assertNotNull(exceptionEventHandler);
        String eventType = AttributeUtil.getAttributeValue(exceptionEventHandler, NativeBoundaryEvent2Stardust.ATT_BOUNDARY_EVENT_TYPE);
        assertEquals(NativeBoundaryEvent2Stardust.VAL_TYPE_INTERRUPTING, eventType);

        EventActionTypeType cancelActivityActionType = ModelUtils.findElementById(model.getEventActionType(), ABORT_ACTIVITY_ACTION);
        assertNotNull(cancelActivityActionType);

        EventActionType cancelAction = getFirstEventActionOfType(exceptionEventHandler, cancelActivityActionType);
        assertNotNull(cancelAction);

        String exceptionClass = AttributeUtil.getAttributeValue(exceptionEventHandler, EXCEPTION_CLASS_ATT);
        String abortScope = AttributeUtil.getAttributeValue(cancelAction, ABORT_ACTION_SCOPE_ATT);

        assertNull(CarnotModelQuery.findActivity(process, TEST_ID_BOUNDARY_EVENT));

        assertTrue(transitionExistsBetween(taskA, routeHappyPath));
        assertTrue(transitionExistsBetween(taskA, routeEventPath));

        assertEquals("true", transitionConditionBetween(taskA, routeHappyPath));
        assertEquals(CONDITION_EVENT_PATH, transitionConditionBetween(taskA, routeEventPath));

        assertEquals(AbortScope.SUB_HIERARCHY, abortScope);

        assertEquals("java.lang.Exception", exceptionClass);
    }

    @Test
    public void testBoundarySplit() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "BoundaryEventWithMultipleOutgoing.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testBoundaryEventWithMultipleOutgoing.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        assertNotNull(taskB);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        assertNotNull(taskC);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        assertNotNull(taskD);
//        ActivityType routeHappyPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_HAPPY_PATH);
//        assertNotNull(routeHappyPath);
//        ActivityType routeEventPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_EVENT_PATH);
//        assertNotNull(routeEventPath);

        assertNull(CarnotModelQuery.findActivity(process, TEST_ID_BOUNDARY_EVENT));

        assertTrue(transitionExistsBetween(taskA, taskB));
        assertTrue(transitionExistsBetween(taskA, taskC));
        assertTrue(transitionExistsBetween(taskA, taskD));

        //assertEquals(JoinSplitType.XOR_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.AND_LITERAL, taskA.getSplit());

        assertEquals("true", transitionConditionBetween(taskA, taskB));
        assertEquals(CONDITION_EVENT_PATH, transitionConditionBetween(taskA, taskC));
        assertEquals(CONDITION_EVENT_PATH, transitionConditionBetween(taskA, taskD));
    }

    @Test
    public void testBoundaryOnRoutingActivityToGate() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "BoundaryEventFollowedByGateway.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testBoundaryEventFollowedByGateway.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        assertNotNull(taskB);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        assertNotNull(taskC);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        assertNotNull(taskD);
        ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);
        assertNotNull(taskE);
//        ActivityType routeHappyPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_HAPPY_PATH);
//        assertNotNull(routeHappyPath);
//        ActivityType routeEventPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_EVENT_PATH);
//        assertNotNull(routeEventPath);
        ActivityType gateway = CarnotModelQuery.findActivity(process, TEST_ID_GATEWAY);
        assertNotNull(gateway);

        assertNull(CarnotModelQuery.findActivity(process, TEST_ID_BOUNDARY_EVENT));

//        assertTrue(transitionExistsBetween(taskA, routeHappyPath));
//        assertTrue(transitionExistsBetween(taskA, routeEventPath));
//        assertTrue(transitionExistsBetween(routeHappyPath, taskB));
//        assertTrue(transitionExistsBetween(routeHappyPath, gateway));
//        assertTrue(transitionExistsBetween(routeEventPath, taskE));


        assertTrue(transitionExistsBetween(taskA, taskB));
        assertTrue(transitionExistsBetween(taskA, gateway));
        assertTrue(transitionExistsBetween(gateway, taskC));
        assertTrue(transitionExistsBetween(gateway, taskD));
        assertTrue(transitionExistsBetween(taskA, taskE));

        assertEquals(JoinSplitType.AND_LITERAL, taskA.getSplit());
        //assertEquals(JoinSplitType.XOR_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gateway.getSplit());

        assertEquals(CONDITION_EVENT_PATH, transitionConditionBetween(taskA, taskE));

        assertTrue(otherwiseConditionBetween(gateway, taskD));
    }

    @Test
    @Ignore
    // TODO The 'native' approach doesn't support multiple boundary events
    public void testMultipleBoundaryWithIndividualPath() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "BoundaryEventMultipleDifferentExceptionalPath.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testBoundaryEventMultipleDifferentExceptionalPath.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        assertNotNull(taskB);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        assertNotNull(taskC);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        assertNotNull(taskD);
        ActivityType routeHappyPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_HAPPY_PATH);
        assertNotNull(routeHappyPath);
        ActivityType routeErrorEventPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_EVENT_PATH);
        assertNotNull(routeErrorEventPath);
        ActivityType routeTimerEventPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_SECOND_EVENT_PATH);
        assertNotNull(routeTimerEventPath);

        assertNull(CarnotModelQuery.findActivity(process, TEST_ID_BOUNDARY_EVENT));
        assertNull(CarnotModelQuery.findActivity(process, TEST_ID_SECOND_BOUNDARY_EVENT));

        assertTrue(transitionExistsBetween(taskA, routeHappyPath));
        assertTrue(transitionExistsBetween(taskA, routeErrorEventPath));
        assertTrue(transitionExistsBetween(taskA, routeTimerEventPath));
        assertTrue(transitionExistsBetween(routeHappyPath, taskB));
        assertTrue(transitionExistsBetween(routeErrorEventPath, taskD));
        assertTrue(transitionExistsBetween(routeTimerEventPath, taskC));

        assertFalse(transitionExistsBetween(taskA, taskB));
        assertFalse(transitionExistsBetween(taskA, taskC));
        assertFalse(transitionExistsBetween(taskA, taskD));

        assertEquals(JoinSplitType.XOR_LITERAL, taskA.getSplit());
    }

    @Test
    @Ignore
    // TODO The 'native' approach doesn't support multiple boundary events
    public void testMultipleBoundaryWithCommonPathToGateway() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "BoundaryEventMultipleSameExceptionalPathToGateway.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testBoundaryEventMultipleSameExceptionalPathToGateway.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        assertNotNull(taskB);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        assertNotNull(taskC);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        assertNotNull(taskD);
        ActivityType routeHappyPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_HAPPY_PATH);
        assertNotNull(routeHappyPath);
        ActivityType routeErrorEventPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_EVENT_PATH);
        assertNotNull(routeErrorEventPath);
        ActivityType routeTimerEventPath = CarnotModelQuery.findActivity(process, TEST_ID_TASK_SECOND_EVENT_PATH);
        assertNotNull(routeTimerEventPath);
        ActivityType gateway = CarnotModelQuery.findActivity(process, TEST_ID_GATEWAY);
        assertNotNull(gateway);

        assertNull(CarnotModelQuery.findActivity(process, TEST_ID_BOUNDARY_EVENT));
        assertNull(CarnotModelQuery.findActivity(process, TEST_ID_SECOND_BOUNDARY_EVENT));

        assertTrue(transitionExistsBetween(taskA, routeHappyPath));
        assertTrue(transitionExistsBetween(taskA, routeErrorEventPath));
        assertTrue(transitionExistsBetween(taskA, routeTimerEventPath));
        assertTrue(transitionExistsBetween(routeHappyPath, taskB));
        assertTrue(transitionExistsBetween(routeErrorEventPath, gateway));
        assertTrue(transitionExistsBetween(routeTimerEventPath, gateway));
        assertTrue(transitionExistsBetween(gateway, taskC));
        assertTrue(transitionExistsBetween(gateway, taskD));

        assertFalse(transitionExistsBetween(taskA, taskB));
        assertFalse(transitionExistsBetween(taskA, taskC));
        assertFalse(transitionExistsBetween(taskA, taskD));
        assertFalse(transitionExistsBetween(taskA, gateway));

        assertEquals(JoinSplitType.XOR_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gateway.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, gateway.getSplit());

    }
}
