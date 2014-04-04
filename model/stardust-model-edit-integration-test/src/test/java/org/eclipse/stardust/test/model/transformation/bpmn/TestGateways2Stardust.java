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
public class TestGateways2Stardust extends Bpmn2StardustTestSuite {

	private static final String TEST_ID_PARALLEL_SPLIT = "_4ace9b8b-afd3-428c-9926-2a0318285663";
	private static final String TEST_ID_PARALLEL_JOIN = "_19969e5c-c41c-49de-af79-529e94cb4446";

	private static final String TEST_ID_EXCLUSIVE_SPLIT = "TestModelXORSplitGateway";
	private static final String TEST_ID_EXCLUSIVE_JOIN = "TestModelXORJoinGateway";

    @Test
    public void testXORGatewayOneSplitOneMerge() {
        final String modelFile = "XORGatewaysSingle.bpmn";
        final String testCondition = "test='Condition to B'";
        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        // A -> B, C or D -> E
        // A has condition, D is default path
        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);

        ActivityType exclusiveSplit = CarnotModelQuery.findGateway(process, TEST_ID_EXCLUSIVE_SPLIT);
        ActivityType exclusiveJoin = CarnotModelQuery.findGateway(process, TEST_ID_EXCLUSIVE_JOIN);

        TransitionType transitionAB = CarnotModelQuery.findTransition(process, TEST_ID_CONDITIONAL_SEQUENCE);
        TransitionType defaultTransitionAD = CarnotModelQuery.findTransition(process, TEST_ID_DEFAULT_SEQUENCE);

        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(taskE);

        assertNotNull(exclusiveSplit);
        assertNotNull(exclusiveJoin);

        assertNotNull(transitionAB);
        assertNotNull(defaultTransitionAD);

        assertEquals(1, taskA.getOutTransitions().size());
        assertEquals(3, exclusiveSplit.getOutTransitions().size());
        assertEquals(1, taskB.getOutTransitions().size());
        assertEquals(1, taskC.getOutTransitions().size());
        assertEquals(1, taskD.getOutTransitions().size());
        assertEquals(3, exclusiveJoin.getInTransitions().size());
        assertEquals(1, taskE.getInTransitions().size());

        assertEquals(JoinSplitType.XOR_LITERAL, exclusiveSplit.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, exclusiveJoin.getJoin());
//        System.out.println("sequence condition AB " + transitionAB.getCondition());
//        System.out.println("sequence condition AD " + defaultTransitionAD.getCondition());

        //assertEquals(testCondition, transitionAB.getCondition());
        assertEquals(XMLConstants.CONDITION_VALUE, transitionAB.getCondition());
        assertEquals(testCondition, transitionAB.getExpression().getMixed().getValue(0));
        assertEquals(XMLConstants.CONDITION_OTHERWISE_VALUE, defaultTransitionAD.getCondition());

    }

    @Test
    public void testParallelGatewayOneSplitOneMerge() {
        final String modelFile = "ParallelGatewaysSingle.bpmn";

        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        // A -> B, C and D -> E
        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);

        ActivityType parallelSplit = CarnotModelQuery.findGateway(process, TEST_ID_PARALLEL_SPLIT);
        ActivityType parallelJoin = CarnotModelQuery.findGateway(process, TEST_ID_PARALLEL_JOIN);

        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(taskE);

        assertEquals(1, taskA.getOutTransitions().size());
        assertEquals(1, parallelSplit.getInTransitions().size());
        assertEquals(3, parallelSplit.getOutTransitions().size());
        assertEquals(1, taskB.getInTransitions().size());
        assertEquals(1, taskC.getInTransitions().size());
        assertEquals(1, taskD.getInTransitions().size());
        assertEquals(1, taskB.getOutTransitions().size());
        assertEquals(1, taskC.getOutTransitions().size());
        assertEquals(1, taskD.getOutTransitions().size());
        assertEquals(3, parallelJoin.getInTransitions().size());
        assertEquals(1, parallelJoin.getOutTransitions().size());
        assertEquals(1, taskE.getInTransitions().size());

        assertEquals(JoinSplitType.AND_LITERAL, parallelSplit.getSplit());
        assertEquals(JoinSplitType.AND_LITERAL, parallelJoin.getJoin());

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

        final String modelFile = "MixedGateway.bpmn";

        final String expr4D = "expr4D";
        final String expr4E = "expr4E";

        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);
        ActivityType route = CarnotModelQuery.findGateway(process, TEST_ID_XOR_MIXED_GATEWAY);

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

        final String modelFile = "SequentialMixedGateway.bpmn";
        // ids
        final String TEST_ID_GATE_ONE = "TestModelGateA";
        final String TEST_ID_GATE_TWO = "TestModelGateB";

        final String TEST_ID_SEQUENCE_A2GA = "TestModelSequenceA2GA";
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

        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);

        ActivityType gatewayOne = CarnotModelQuery.findGateway(process, TEST_ID_GATE_ONE);
        ActivityType gatewayTwo = CarnotModelQuery.findGateway(process, TEST_ID_GATE_TWO);
        ActivityType gatewayMixed = CarnotModelQuery.findGateway(process, TEST_ID_XOR_MIXED_GATEWAY);

        TransitionType transitionA2firstGate = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_A2GA);
        TransitionType transitionGateOne2GateTwo = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GA2GB); //x1
        TransitionType transitionGateOne2GateMixed = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GA2MIXED); //x2

        TransitionType transitionRouteA2B = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GB2B); //y1
        TransitionType transitionGateTwo2GateMixed = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GB2MIXED); //y2

        TransitionType transitionRouteB2C = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_MIXED2C); //z1
        TransitionType transitionRouteB2D = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_MIXED2D); //z2

        // Elements found?
        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(gatewayOne);
        assertNotNull(gatewayTwo);
        assertNotNull(gatewayMixed);
        assertNotNull(transitionA2firstGate);
        assertNotNull(transitionGateOne2GateTwo);
        assertNotNull(transitionGateOne2GateMixed);
        assertNotNull(transitionRouteA2B);
        assertNotNull(transitionGateTwo2GateMixed);
        assertNotNull(transitionRouteB2C);
        assertNotNull(transitionRouteB2D);

        // Join- and Split config
        assertEquals(JoinSplitType.NONE_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gatewayOne.getSplit());

        // assertEquals(JoinSplitType.NONE_LITERAL, gatewayOne.getJoin());
        assertEquals(JoinSplitType.NONE_LITERAL, taskA.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, gatewayTwo.getSplit());
        //assertEquals(JoinSplitType.NONE_LITERAL, gatewayTwo.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, gatewayMixed.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gatewayMixed.getJoin());

        // Transition source/target

        assertEquals(taskA, transitionA2firstGate.getFrom());
        assertEquals(gatewayOne, transitionA2firstGate.getTo());

        assertEquals(gatewayOne, transitionGateOne2GateTwo.getFrom());
        assertEquals(gatewayOne, transitionGateOne2GateMixed.getFrom());

        assertEquals(gatewayTwo, transitionRouteA2B.getFrom());
        assertEquals(gatewayTwo, transitionGateTwo2GateMixed.getFrom());
        assertEquals(gatewayMixed, transitionRouteB2C.getFrom());
        assertEquals(gatewayMixed, transitionRouteB2D.getFrom());

        assertEquals(gatewayTwo, transitionGateOne2GateTwo.getTo());
        assertEquals(gatewayMixed, transitionGateOne2GateMixed.getTo());
        assertEquals(taskB, transitionRouteA2B.getTo());
        assertEquals(gatewayMixed, transitionGateTwo2GateMixed.getTo());
        assertEquals(taskC, transitionRouteB2C.getTo());
        assertEquals(taskD, transitionRouteB2D.getTo());

        // Transition conditions
        assertEquals(x1, transitionGateOne2GateTwo.getExpression().getMixed().getValue(0));
        assertEquals(x2, transitionGateOne2GateMixed.getExpression().getMixed().getValue(0));
        assertEquals(y1, transitionRouteA2B.getExpression().getMixed().getValue(0));
        assertEquals(y2, transitionGateTwo2GateMixed.getExpression().getMixed().getValue(0));
        assertEquals(z1, transitionRouteB2C.getExpression().getMixed().getValue(0));
        assertEquals(z2, transitionRouteB2D.getExpression().getMixed().getValue(0));

    }

    @Test
    public void testConditionalEndGateway() {
        final String TEST_ID_END_A = "TestModelEndEventA";
        final String TEST_ID_END_B = "TestModelEndEventB";
        final String TEST_ID_END_C = "TestModelEndEventC";
        final String TEST_ID_GATE_A = "TestModelGateA";
        final String TEST_ID_GATE_B = "TestModelGateB";

        final String TEST_ID_SEQUENCE_GATEB_B = "TestModelGateBToTaskB";
        final String TEST_ID_SEQUENCE_GATEB_ENDB = "TestModelGateBToEndB";
        final String TEST_ID_SEQUENCE_B_ENDA = "TestModelTaskBToEndA";
        final String TEST_ID_SEQUENCE_GATEA_GATEB = "TestModelGateAToGateB";
        final String TEST_ID_SEQUENCE_GATEA_ENDC = "TestModelGateAToEndC";

        final String modelFile = "ConditionalEnd.bpmn";
        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);

        ActivityType endA = CarnotModelQuery.findActivity(process, TEST_ID_END_A);
        ActivityType endB = CarnotModelQuery.findActivity(process, TEST_ID_END_B);
        ActivityType endC = CarnotModelQuery.findActivity(process, TEST_ID_END_C);

        ActivityType gateA = CarnotModelQuery.findGateway(process, TEST_ID_GATE_A);
        ActivityType gateB = CarnotModelQuery.findGateway(process, TEST_ID_GATE_B);

        TransitionType transitionGateBB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATEB_B);
        TransitionType transitionGateBEndB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATEB_ENDB); // TODO - NOT FOUND - NEW ID IN TARGET MODEL
        TransitionType transitionBEndA = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_B_ENDA);
        TransitionType transitionGateAGateB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATEA_GATEB);
        TransitionType transitionGateAEndC = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATEA_ENDC); // TODO - NOT FOUND - NEW ID IN TARGET MODEL

        assertNotNull(taskA);
        assertNotNull(taskB);

        // TODO HOW CAN WE RECOGNIZE 'EVENT-HOSTS'? THE ID'S HAVE CHANGED!
//        assertNotNull(endA);
//        assertNotNull(endB);
//        assertNotNull(endC);

        assertNotNull(gateA);
        assertNotNull(gateB);

        assertEquals(JoinSplitType.XOR_LITERAL, gateA.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gateB.getSplit());

        assertEquals(1, taskA.getOutTransitions().size());
        assertEquals(2, gateA.getOutTransitions().size());
        assertEquals(2, gateB.getOutTransitions().size());

        assertEquals(gateA, transitionGateAGateB.getFrom());
        assertEquals(gateB, transitionGateAGateB.getTo());

// TODO
//        assertEquals(gateA, transitionGateAEndC.getFrom());
//        assertEquals(endC, transitionGateAEndC.getTo());

        assertEquals(gateB, transitionGateBB.getFrom());
        assertEquals(taskB, transitionGateBB.getTo());

// TODO
//        assertEquals(gateB, transitionGateBEndB.getFrom());
//        assertEquals(endB, transitionGateBEndB.getTo());

// TODO
//        assertEquals(taskB, transitionBEndA.getFrom());
//        assertEquals(endA, transitionBEndA.getTo());
    }

}
