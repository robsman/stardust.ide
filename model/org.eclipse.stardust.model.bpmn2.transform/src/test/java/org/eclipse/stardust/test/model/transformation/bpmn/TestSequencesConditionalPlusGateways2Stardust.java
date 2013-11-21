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

import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.TransitionUtil;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

import org.junit.Test;

/**
 * @author Simon Nikles
 *
 */
public class TestSequencesConditionalPlusGateways2Stardust extends Bpmn2StardustTestSuite { //extends TestCase {

	private static final String TEST_ID_TASK_X ="TestModelTaskX";
	private static final String SEQ_XA ="TestModelSequenceXtoA";
	private static final String SEQ_XB ="TestModelSequenceXtoB";
	private static final String SEQ_X_GATE_A = "TestModelSequenceXtoGateA";
	private static final String SEQ_GATE_A_GATE_B = "TestModelSequenceGateAtoGateB";
	private static final String SEQ_GATE_A_E = "TestModelSequenceGateAtoE";
	private static final String SEQ_GATE_B_C = "TestModelSequenceGateBtoC";
	private static final String SEQ_GATE_B_D = "TestModelSequenceGateBtoD";

	private static final String GATE_A = "TestModelGateA";
	private static final String GATE_B = "TestModelGateB";

	private static final String CONDITION_XB = "\"B\"==\"B\";";
	private static final String CONDITION_GATE_A_E = "\"B\"==\"E\";";
	private static final String CONDITION_GATE_A_GATE_B = "\"C\"==\"C\";";
	private static final String CONDITION_GATE_B_C = "\"C\"==\"C\";";
	private static final String CONDITION_GATE_B_D = "\"B\"==\"D\";";

    public TestSequencesConditionalPlusGateways2Stardust() {}

    @Test
    public void testSequenceIntegrateConditionalPlusGateways() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "IntegratedConditionalSequenceAndGates.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testIntegratedConditionalSequenceAndGates.xpdl";
        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType processDef = result.getProcessDefinition().get(0);

        // activity elements
        ActivityType taskX = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_X);
        ActivityType taskA = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_D);
        ActivityType taskE = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_E);
        assertNotNull(taskX);
        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);
        assertNotNull(taskE);

        // transition elements
        TransitionType transitionXA = CarnotModelQuery.findTransition(processDef, SEQ_XA);
        TransitionType transitionXB = CarnotModelQuery.findTransition(processDef, SEQ_XB);
        TransitionType transitionXGateA = CarnotModelQuery.findTransition(processDef, SEQ_X_GATE_A);
        TransitionType transitionGateAGateB = CarnotModelQuery.findTransition(processDef, SEQ_GATE_A_GATE_B);
        TransitionType transitionGateAE = CarnotModelQuery.findTransition(processDef, SEQ_GATE_A_E);
        TransitionType transitionGateBC = CarnotModelQuery.findTransition(processDef, SEQ_GATE_B_C);
        TransitionType transitionGateBD = CarnotModelQuery.findTransition(processDef, SEQ_GATE_B_D);
        assertNotNull(transitionXA);
        assertNotNull(transitionXB);
        assertNotNull(transitionXGateA);
        assertNotNull(transitionGateAGateB);
        assertNotNull(transitionGateAE);
        assertNotNull(transitionGateBC);
        assertNotNull(transitionGateBD);

        // generated routes
        ActivityType route = transitionXB.getFrom();
        assertEquals(route, transitionXGateA.getFrom());
        assertNotNull(route);
        ActivityType gateA = CarnotModelQuery.findActivity(processDef, GATE_A);
        ActivityType gateB = CarnotModelQuery.findActivity(processDef, GATE_B);
        assertNotNull(gateA);
        assertNotNull(gateB);

        // activity splits
        assertEquals(JoinSplitType.AND_LITERAL, taskX.getSplit());
        assertEquals(JoinSplitType.AND_LITERAL, route.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gateA.getSplit());
        assertEquals(JoinSplitType.XOR_LITERAL, gateB.getSplit());

        // transition conditions
        assertEquals(CONDITION_GATE_A_E, getCondition(transitionGateAE));
        assertEquals(CONDITION_GATE_A_GATE_B, getCondition(transitionGateAGateB));
        assertEquals(CONDITION_GATE_B_C, getCondition(transitionGateBC));
        assertEquals(CONDITION_GATE_B_D, getCondition(transitionGateBD));
        assertEquals(CONDITION_XB, getCondition(transitionXB));
        assertEquals("true", getCondition(transitionXA));
        assertEquals(TransitionUtil.OTHERWISE_KEY, transitionXGateA.getCondition());

        // transition sources / targets
        assertEquals(taskX, transitionXA.getFrom());
        assertEquals(taskA, transitionXA.getTo());

        assertEquals(route, transitionXB.getFrom());
        assertEquals(taskB, transitionXB.getTo());

        assertEquals(route, transitionXGateA.getFrom());
        assertEquals(gateA, transitionXGateA.getTo());

        assertEquals(gateA, transitionGateAGateB.getFrom());
        assertEquals(gateB, transitionGateAGateB.getTo());

        assertEquals(gateA, transitionGateAE.getFrom());
        assertEquals(taskE, transitionGateAE.getTo());

        assertEquals(gateB, transitionGateBC.getFrom());
        assertEquals(taskC, transitionGateBC.getTo());

        assertEquals(gateB, transitionGateBD.getFrom());
        assertEquals(taskD, transitionGateBD.getTo());

        assertEquals(taskX, route.getInTransitions().get(0).getFrom());
    }

    private String getCondition(TransitionType transition) {
    	return ModelUtils.getCDataString(transition.getExpression().getMixed());
    }

}
