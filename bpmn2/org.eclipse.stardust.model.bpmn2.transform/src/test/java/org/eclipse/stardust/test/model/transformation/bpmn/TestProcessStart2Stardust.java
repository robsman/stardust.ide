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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.ProcessStartConfigurator;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.junit.Test;

/**
 * @author Simon Nikles
 *
 */
public class TestProcessStart2Stardust extends Bpmn2StardustTestSuite {

	private static final String TEST_ID_TRANSITION_START_TO_A = "TestIdTransitionStartToA";
	private static final String TEST_ID_TRANSITION_START_TO_B = "TestIdTransitionStartToB";
	private static final String TEST_ID_TRANSITION_START_TO_C = "TestIdTransitionStartToC";

    @Test
    public void testStartEventParallelSplit() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "ParallelSplitStart.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testParallelSplitStart.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        assertNotNull(result);

        ProcessDefinitionType process = CarnotModelQuery.findProcessDefinition(result, TEST_ID_MAIN_PROCESS);
        assertNotNull(process);

        ActivityType splittingStartRouteActivity = CarnotModelQuery.findActivity(process, "0"+ process.getId() + ProcessStartConfigurator.START_ROUTE_POST_FIX );
        assertNotNull(splittingStartRouteActivity);
        assertEquals(3, splittingStartRouteActivity.getOutTransitions().size());

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        assertNotNull(taskB);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        assertNotNull(taskC);

        TransitionType startToA = CarnotModelQuery.findTransition(process, TEST_ID_TRANSITION_START_TO_A);
        assertNotNull(startToA);
        TransitionType startToB = CarnotModelQuery.findTransition(process, TEST_ID_TRANSITION_START_TO_B);
        assertNotNull(startToB);
        TransitionType startToC = CarnotModelQuery.findTransition(process, TEST_ID_TRANSITION_START_TO_C);
        assertNotNull(startToC);

        assertEquals(splittingStartRouteActivity, startToA.getFrom());
        assertEquals(splittingStartRouteActivity, startToB.getFrom());
        assertEquals(splittingStartRouteActivity, startToC.getFrom());

        assertEquals(taskA, startToA.getTo());
        assertEquals(taskB, startToB.getTo());
        assertEquals(taskC, startToC.getTo());

    }

    @Test
    public void testParallelBox() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "ParallelBox.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testParallelBox.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        assertNotNull(result);

        ProcessDefinitionType process = CarnotModelQuery.findProcessDefinition(result, TEST_ID_MAIN_PROCESS);
        assertNotNull(process);

        ActivityType splittingStartRouteActivity = CarnotModelQuery.findActivity(process, process.getId() + ProcessStartConfigurator.START_ROUTE_POST_FIX );
        assertNotNull(splittingStartRouteActivity);
        assertEquals(3, splittingStartRouteActivity.getOutTransitions().size());

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        assertNotNull(taskB);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        assertNotNull(taskC);

        String routeToA = ProcessStartConfigurator.START_ROUTE_PRE_FIX + TEST_ID_TASK_A;
        String routeToB = ProcessStartConfigurator.START_ROUTE_PRE_FIX + TEST_ID_TASK_B;
        String routeToC = ProcessStartConfigurator.START_ROUTE_PRE_FIX + TEST_ID_TASK_C;

        TransitionType startToA = CarnotModelQuery.findTransition(process, routeToA);
        assertNotNull(startToA);
        TransitionType startToB = CarnotModelQuery.findTransition(process, routeToB);
        assertNotNull(startToB);
        TransitionType startToC = CarnotModelQuery.findTransition(process, routeToC);
        assertNotNull(startToC);

        assertEquals(splittingStartRouteActivity, startToA.getFrom());
        assertEquals(splittingStartRouteActivity, startToB.getFrom());
        assertEquals(splittingStartRouteActivity, startToC.getFrom());

        assertEquals(taskA, startToA.getTo());
        assertEquals(taskB, startToB.getTo());
        assertEquals(taskC, startToC.getTo());
    }

    @Test
    public void testBackloop() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "BackloopToStart.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testBackloopToStart.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        assertNotNull(result);

        ProcessDefinitionType process = CarnotModelQuery.findProcessDefinition(result, TEST_ID_MAIN_PROCESS);
        assertNotNull(process);

        ActivityType splittingStartRouteActivity = CarnotModelQuery.findActivity(process, 0 + process.getId() + ProcessStartConfigurator.START_ROUTE_POST_FIX );
        assertNotNull(splittingStartRouteActivity);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(taskA);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        assertNotNull(taskB);

        String routeToA = ProcessStartConfigurator.START_ROUTE_PRE_FIX + TEST_ID_TASK_A;

        TransitionType startToA = CarnotModelQuery.findTransition(process, routeToA);
        assertNotNull(startToA);
        assertEquals(splittingStartRouteActivity, startToA.getFrom());
        assertEquals(taskA, startToA.getTo());
    }

}
