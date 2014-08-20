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
public class TestGateways2Stardust extends Bpmn2StardustTestSuite {
	
	private static final String TEST_ID_INCLUSIVE_SPLIT = "InclusiveSplitGateway";
	private static final String TEST_ID_INCLUSIVE_JOIN = "InclusiveMergeGateway";
	private static final String TEST_ID_XOR_SPLIT = "TestModelXORSplitGateway";
	private static final String TEST_ID_XOR_JOIN = "TestModelXORJoinGateway";

	private static final String TEST_ID_AND_SPLIT = "testModelParallelSplit";
	private static final String TEST_ID_AND_JOIN = "testModelParallelMerge";

	
	
    @Test
    public void testInclusiveORGatewayOneSplitOneMerge() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "InclusiveORGatewaySingle.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "InclusiveORGatewaySingle.xpdl";
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

        ActivityType inclusiveSplit = CarnotModelQuery.findActivity(process, TEST_ID_INCLUSIVE_SPLIT);
        ActivityType inclusiveMerge = CarnotModelQuery.findActivity(process, TEST_ID_INCLUSIVE_JOIN);

        TransitionType transitionAB = CarnotModelQuery.findTransition(process, TEST_ID_CONDITIONAL_SEQUENCE);
        TransitionType defaultTransitionAD = CarnotModelQuery.findTransition(process, TEST_ID_DEFAULT_SEQUENCE);

        assertNotNull(inclusiveSplit);
        assertNotNull(inclusiveMerge);

        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(taskE);

        assertNotNull(transitionAB);
        assertNotNull(defaultTransitionAD);

        assertTrue(taskA.getOutTransitions().size()==1); 
        assertTrue(taskE.getInTransitions().size()==1);

        assertTrue(inclusiveSplit.getOutTransitions().size()==3); 
        assertTrue(inclusiveMerge.getInTransitions().size()==3);

        assertEquals(JoinSplitType.OR_LITERAL, inclusiveSplit.getSplit());
        assertEquals(JoinSplitType.OR_LITERAL, inclusiveMerge.getJoin());
        System.out.println("sequence condition AB " + transitionAB.getCondition());
        System.out.println("sequence condition AD " + defaultTransitionAD.getCondition());

        //assertEquals(testCondition, transitionAB.getCondition());
        assertEquals(XMLConstants.CONDITION_VALUE, transitionAB.getCondition());
        assertEquals("ConditionToB == true;", transitionAB.getExpression().getMixed().getValue(0));
        assertEquals(XMLConstants.CONDITION_OTHERWISE_VALUE, defaultTransitionAD.getCondition());

    }
	
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

        ActivityType xorSplit = CarnotModelQuery.findActivity(process, TEST_ID_XOR_SPLIT);
        assertNotNull(xorSplit);
        ActivityType xorJoin = CarnotModelQuery.findActivity(process, TEST_ID_XOR_JOIN);
        assertNotNull(xorJoin);
        
        TransitionType transitionAB = CarnotModelQuery.findTransition(process, TEST_ID_CONDITIONAL_SEQUENCE);
        TransitionType defaultTransitionAD = CarnotModelQuery.findTransition(process, TEST_ID_DEFAULT_SEQUENCE);

        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(taskE);

        assertNotNull(transitionAB);
        assertNotNull(defaultTransitionAD);

        assertTrue(xorSplit.getOutTransitions().size()==3);
        assertTrue(xorJoin.getInTransitions().size()==3);

        assertEquals(JoinSplitType.NONE_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.NONE_LITERAL, taskE.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, xorSplit.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, xorSplit.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, xorJoin.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, xorJoin.getJoin());


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

        ActivityType andSplit = CarnotModelQuery.findActivity(process, TEST_ID_AND_SPLIT);
        ActivityType andJoin = CarnotModelQuery.findActivity(process, TEST_ID_AND_JOIN);
        
        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(taskE);

        assertNotNull(andSplit);
        assertNotNull(andJoin);

        assertTrue(andSplit.getOutTransitions().size()==3);
        assertTrue(andJoin.getInTransitions().size()==3);

        assertEquals(JoinSplitType.NONE_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.NONE_LITERAL, taskE.getJoin());

        assertEquals(JoinSplitType.AND_LITERAL, andSplit.getSplit());
        assertEquals(JoinSplitType.AND_LITERAL, andSplit.getJoin());

        assertEquals(JoinSplitType.AND_LITERAL, andJoin.getSplit());
        assertEquals(JoinSplitType.AND_LITERAL, andJoin.getJoin());

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
        final String TEST_ID_GATE_A = "TestModelGateA";
        final String TEST_ID_GATE_MIX = "TestModelXORMixedGateway";
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

        ActivityType secondXorGate = CarnotModelQuery.findActivity(process, TEST_ID_GATE_B);
        ActivityType firstXorGate = CarnotModelQuery.findActivity(process, TEST_ID_GATE_A);
        ActivityType mixedGate = CarnotModelQuery.findActivity(process, TEST_ID_XOR_MIXED_GATEWAY);

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
        
        assertNotNull(firstXorGate);
        assertNotNull(secondXorGate);
        assertNotNull(mixedGate);
        assertNotNull(transitionA2RouteA);
        assertNotNull(transitionA2RouteB);
        assertNotNull(transitionRouteA2B);
        assertNotNull(transitionRouteA2RouteB);
        assertNotNull(transitionRouteB2C);
        assertNotNull(transitionRouteB2D);

        // Join- and Split config
        assertEquals(JoinSplitType.NONE_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.NONE_LITERAL, taskA.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, firstXorGate.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, firstXorGate.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, secondXorGate.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, secondXorGate.getJoin());
        assertEquals(JoinSplitType.XOR_LITERAL, mixedGate.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, mixedGate.getJoin());

        // Transition source/target
        assertEquals(firstXorGate, transitionA2RouteA.getFrom());
        assertEquals(firstXorGate, transitionA2RouteB.getFrom());
        assertEquals(secondXorGate, transitionRouteA2B.getFrom());
        assertEquals(secondXorGate, transitionRouteA2RouteB.getFrom());
        assertEquals(mixedGate, transitionRouteB2C.getFrom());
        assertEquals(mixedGate, transitionRouteB2D.getFrom());

        assertEquals(secondXorGate, transitionA2RouteA.getTo());
        assertEquals(mixedGate, transitionA2RouteB.getTo());
        assertEquals(taskB, transitionRouteA2B.getTo());
        assertEquals(mixedGate, transitionRouteA2RouteB.getTo());
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

        final String modelFile = TEST_BPMN_MODEL_DIR + "ConditionalEnd.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testConditionalEnd.xpdl";
        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);

        ActivityType endA = CarnotModelQuery.findActivity(process, TEST_ID_END_A);
        ActivityType endB = CarnotModelQuery.findActivity(process, TEST_ID_END_B);
        ActivityType endC = CarnotModelQuery.findActivity(process, TEST_ID_END_C);

        ActivityType gateA = CarnotModelQuery.findActivity(process, TEST_ID_GATE_A);
        ActivityType gateB = CarnotModelQuery.findActivity(process, TEST_ID_GATE_B);

        TransitionType transitionGateBB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATEB_B);
        TransitionType transitionGateBEndB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATEB_ENDB);
        TransitionType transitionBEndA = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_B_ENDA);
        TransitionType transitionGateAGateB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATEA_GATEB);
        TransitionType transitionGateAEndC = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATEA_ENDC);

        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(endA);
        assertNotNull(endB);
        assertNotNull(endC);

        assertNotNull(gateA); // taskA has a split config which makes a route activity for gateA unnecessary
        assertNotNull(gateB);

        assertEquals(JoinSplitType.NONE_LITERAL, taskA.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gateB.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gateA.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gateA.getJoin());

        assertEquals(2, gateA.getOutTransitions().size());
        assertEquals(2, gateB.getOutTransitions().size());

        assertEquals(gateA, transitionGateAGateB.getFrom());
        assertEquals(gateB, transitionGateAGateB.getTo());

        assertEquals(gateA, transitionGateAEndC.getFrom());
        assertEquals(endC, transitionGateAEndC.getTo());

        assertEquals(gateB, transitionGateBB.getFrom());
        assertEquals(taskB, transitionGateBB.getTo());

        assertEquals(gateB, transitionGateBEndB.getFrom());
        assertEquals(endB, transitionGateBEndB.getTo());

        assertEquals(taskB, transitionBEndA.getFrom());
        assertEquals(endA, transitionBEndA.getTo());

    }
    
    @Test
    public void testAnotherUncontrolled() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "Uncontrolled_NoneDefaultConditional.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "Uncontrolled_NoneDefaultConditional.xpdl";
        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);
    	
    }
}
