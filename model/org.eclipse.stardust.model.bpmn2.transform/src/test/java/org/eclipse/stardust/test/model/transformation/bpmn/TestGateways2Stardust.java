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
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_CONDITIONAL_SEQUENCE;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_DEFAULT_SEQUENCE;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_SEQUENCE_B2GATE;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_SEQUENCE_C2GATE;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_SEQUENCE_GATE2D;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_SEQUENCE_GATE2E;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_A;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_B;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_C;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_D;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_E;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_XOR_MIXED_GATEWAY;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_MODEL_OUTPUT_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.getResourceFilePath;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.loadBpmnModel;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.transformModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.junit.Test;

/**
 * @author Simon Nikles
 *
 */
public class TestGateways2Stardust {

    @Test
    public void testXORGatewayOneSplitOneMerge() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "XORGatewaysSingle.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testXORGatewaysSingle.xpdl";
        final String testCondition = "test='Condition to B'";
        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        // A -> B, C or D -> E
        // A has condition, D is default path
        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);

        TransitionType transitionAB = CarnotModelQuery.findTransition(process, TEST_ID_CONDITIONAL_SEQUENCE);
        TransitionType defaultTransitionAD = CarnotModelQuery.findTransition(process, TEST_ID_DEFAULT_SEQUENCE);

        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(taskE);

        assertNotNull(transitionAB);
        assertNotNull(defaultTransitionAD);

        assertTrue(taskA.getOutTransitions().size()==3);
        assertTrue(taskE.getInTransitions().size()==3);

        assertEquals(JoinSplitType.XOR_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, taskE.getJoin());
        System.out.println("sequence condition AB " + transitionAB.getCondition());
        System.out.println("sequence condition AD " + defaultTransitionAD.getCondition());

        //assertEquals(testCondition, transitionAB.getCondition());
        assertEquals(XMLConstants.CONDITION_VALUE, transitionAB.getCondition());
        assertEquals(testCondition, transitionAB.getExpression().getMixed().getValue(0));
        assertEquals(XMLConstants.CONDITION_OTHERWISE_VALUE, defaultTransitionAD.getCondition());

    }

    @Test
    public void testParallelGatewayOneSplitOneMerge() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "ParallelGatewaysSingle.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testParallelGatewaysSingle.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        // A -> B, C and D -> E
        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);

        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(taskE);

        assertTrue(taskA.getOutTransitions().size()==3);
        assertTrue(taskE.getInTransitions().size()==3);

        assertEquals(JoinSplitType.AND_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.AND_LITERAL, taskE.getJoin());

        for (TransitionType trans : taskA.getOutTransitions()) {
            assertEquals("CONDITION", trans.getCondition());
            assertEquals("true", trans.getExpression().getMixed().getValue(0));
        }
    }

    @Test
    public void testMixedGateway() {
        // Gateway with two incoming and two outgoing sequence-flows
        // expected in stardust:
        // - two 'gateways'
        // - one Route Activity (representing the Gateway)
        // -- having XOR Join and XOR Split
        // - four transitions
        // -- b->Route, c->Route, Route->d (with condition), Route->e (with condition)

        final String modelFile = TEST_BPMN_MODEL_DIR + "MixedGateway.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testMixedGateway.xpdl";

        final String expr4D = "expr4D";
        final String expr4E = "expr4E";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);
        ActivityType route = CarnotModelQuery.findActivity(process, TEST_ID_XOR_MIXED_GATEWAY);

        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(taskE);
        assertNotNull(route);

        assertEquals(JoinSplitType.NONE_LITERAL, taskB.getSplit());
        assertEquals(JoinSplitType.NONE_LITERAL, taskC.getSplit());
        assertEquals(JoinSplitType.NONE_LITERAL, taskD.getJoin());
        assertEquals(JoinSplitType.NONE_LITERAL, taskE.getJoin());

        assertEquals(JoinSplitType.XOR_LITERAL, route.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, route.getSplit());

        TransitionType transitionB2G = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_B2GATE);
        TransitionType transitionC2G = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_C2GATE);
        TransitionType transitionG2D = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATE2D);
        TransitionType transitionG2E = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATE2E);

        assertNotNull(transitionB2G);
        assertNotNull(transitionC2G);
        assertNotNull(transitionG2D);
        assertNotNull(transitionG2E);

        assertEquals(taskB, transitionB2G.getFrom());
        assertEquals(taskC, transitionC2G.getFrom());
        assertEquals(route, transitionB2G.getTo());
        assertEquals(route, transitionC2G.getTo());
        assertEquals(route, transitionG2D.getFrom());
        assertEquals(route, transitionG2E.getFrom());
        assertEquals(taskD, transitionG2D.getTo());
        assertEquals(taskE, transitionG2E.getTo());

        assertEquals(expr4D, transitionG2D.getExpression().getMixed().getValue(0));
        assertEquals(expr4E, transitionG2E.getExpression().getMixed().getValue(0));

    }

    @Test
    public void testSequentialWithMixedGateway() {
        // Sequence of Gateways (two diverging and one mixed)
        // expected in stardust:
        // - first gate as split on task a
        // - second gate as route with split
        // - third gate as route with join and split

        final String modelFile = TEST_BPMN_MODEL_DIR + "SequentialMixedGateway.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testSequentialMixedGateway.xpdl";
        // ids
        final String TEST_ID_GATE_B = "TestModelGateB";
        final String TEST_ID_SEQUENCE_GA2GB = "TestModelSequenceGA2GB";
        final String TEST_ID_SEQUENCE_GA2MIXED = "TestModelSequenceGA2MixedGate";
        final String TEST_ID_SEQUENCE_GB2B = "TestModelSequenceGB2B";
        final String TEST_ID_SEQUENCE_GB2MIXED = "TestModelSequenceGB2MixedGate";
        final String TEST_ID_SEQUENCE_MIXED2C = "TestModelSequenceMixedGate2C";
        final String TEST_ID_SEQUENCE_MIXED2D = "TestModelSequenceMixedGate2D";

        final String x1 = "test='X=1'";
        final String x2 = "test='X=2'";
        final String y1 = "test='Y=1'";
        final String y2 = "test='Y=2'";
        final String z1 = "test='Z=1'";
        final String z2 = "test='Z=2'";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);

        ActivityType routeA = CarnotModelQuery.findActivity(process, TEST_ID_GATE_B);
        ActivityType routeB = CarnotModelQuery.findActivity(process, TEST_ID_XOR_MIXED_GATEWAY);

        TransitionType transitionA2RouteA = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GA2GB); //x1
        TransitionType transitionA2RouteB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GA2MIXED); //x2
        TransitionType transitionRouteA2B = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GB2B); //y1
        TransitionType transitionRouteA2RouteB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GB2MIXED); //y2
        TransitionType transitionRouteB2C = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_MIXED2C); //z1
        TransitionType transitionRouteB2D = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_MIXED2D); //z2

        // Elements found?
        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(routeA);
        assertNotNull(routeB);
        assertNotNull(transitionA2RouteA);
        assertNotNull(transitionA2RouteB);
        assertNotNull(transitionRouteA2B);
        assertNotNull(transitionRouteA2RouteB);
        assertNotNull(transitionRouteB2C);
        assertNotNull(transitionRouteB2D);

        // Join- and Split config
        assertEquals(JoinSplitType.XOR_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.NONE_LITERAL, taskA.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, routeA.getSplit());
        assertEquals(JoinSplitType.NONE_LITERAL, routeA.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, routeB.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, routeB.getJoin());

        // Transition source/target
        assertEquals(taskA, transitionA2RouteA.getFrom());
        assertEquals(taskA, transitionA2RouteB.getFrom());
        assertEquals(routeA, transitionRouteA2B.getFrom());
        assertEquals(routeA, transitionRouteA2RouteB.getFrom());
        assertEquals(routeB, transitionRouteB2C.getFrom());
        assertEquals(routeB, transitionRouteB2D.getFrom());

        assertEquals(routeA, transitionA2RouteA.getTo());
        assertEquals(routeB, transitionA2RouteB.getTo());
        assertEquals(taskB, transitionRouteA2B.getTo());
        assertEquals(routeB, transitionRouteA2RouteB.getTo());
        assertEquals(taskC, transitionRouteB2C.getTo());
        assertEquals(taskD, transitionRouteB2D.getTo());

        // Transition conditions
        assertEquals(x1, transitionA2RouteA.getExpression().getMixed().getValue(0));
        assertEquals(x2, transitionA2RouteB.getExpression().getMixed().getValue(0));
        assertEquals(y1, transitionRouteA2B.getExpression().getMixed().getValue(0));
        assertEquals(y2, transitionRouteA2RouteB.getExpression().getMixed().getValue(0));
        assertEquals(z1, transitionRouteB2C.getExpression().getMixed().getValue(0));
        assertEquals(z2, transitionRouteB2D.getExpression().getMixed().getValue(0));

    }

}
