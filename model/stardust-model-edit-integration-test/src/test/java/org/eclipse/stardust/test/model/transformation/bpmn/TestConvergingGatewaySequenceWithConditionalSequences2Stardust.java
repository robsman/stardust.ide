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
import static org.junit.Assert.assertNull;

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
public class TestConvergingGatewaySequenceWithConditionalSequences2Stardust extends Bpmn2StardustTestSuite {

	private static final String TEST_ID_TASK_X = "TestModelTaskX";
	private static final String SEQ_A_GA = "TestModelSequenceAtoGateA";
	private static final String SEQ_B_GA = "TestModelSequenceBtoGateA";
	private static final String SEQ_C_GB = "TestModelSequenceCtoGateB";
	private static final String SEQ_GA_GB = "TestModelSequenceGateAtoGateB";
	private static final String SEQ_GB_E = "TestModelSequenceGateBtoE";
	private static final String SEQ_C_D = "TestModelSequenceCtoD";
	private static final String SEQ_D_GB = "TestModelSequenceDtoGateB";
	private static final String SEQ_X_GC = "TestModelSequenceXtoGateC";
	private static final String SEQ_GC_A = "TestModelSequenceGateCtoA";
	private static final String SEQ_GC_B = "TestModelSequenceGateCtoB";
	private static final String SEQ_GC_C = "TestModelSequenceGateCtoC";

	private static final String COND_SEQ_A_GA = "\"X\"==\"X\";";
	private static final String COND_SEQ_B_GA = "\"X\"==\"Y\";";
	private static final String COND_SEQ_C_GB = "\"X\"==\"Z\";";
	private static final String COND_SEQ_C_D = "\"X\"==\"V\";";

	private static final String GATE_A_EXCL = "TestModelExclusiveGateA";
	private static final String GATE_B_EXCL = "TestModelExclusiveGateB";

    public TestConvergingGatewaySequenceWithConditionalSequences2Stardust() {}

    @Test
    public void testSequenceOfConvergingGatewaysAndConditionalSequences() {
        final String modelFile = "ConvergingGatewaySequenceWithConditionalSequences.bpmn";
        ModelType result = transformModel(loadBpmnModel(modelFile));
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
        TransitionType transitionA2GA= CarnotModelQuery.findTransition(processDef, SEQ_A_GA);
        TransitionType transitionB2GA= CarnotModelQuery.findTransition(processDef, SEQ_B_GA);
        TransitionType transitionC2GB= CarnotModelQuery.findTransition(processDef, SEQ_C_GB);
        TransitionType transitionGA2GB= CarnotModelQuery.findTransition(processDef, SEQ_GA_GB);
        TransitionType transitionGB2E= CarnotModelQuery.findTransition(processDef, SEQ_GB_E);
        TransitionType transitionC2D= CarnotModelQuery.findTransition(processDef, SEQ_C_D);
        TransitionType transitionD2GB= CarnotModelQuery.findTransition(processDef, SEQ_D_GB);
        TransitionType transitionX2GC= CarnotModelQuery.findTransition(processDef, SEQ_X_GC);
        TransitionType transitionGC2A= CarnotModelQuery.findTransition(processDef, SEQ_GC_A);
        TransitionType transitionGC2B= CarnotModelQuery.findTransition(processDef, SEQ_GC_B);
        TransitionType transitionGC2C= CarnotModelQuery.findTransition(processDef, SEQ_GC_C);
        assertNotNull(transitionA2GA);
        assertNotNull(transitionB2GA);
        assertNotNull(transitionC2GB);
        assertNotNull(transitionC2D);
        assertNotNull(transitionD2GB);
        assertNotNull(transitionGC2A);
        assertNotNull(transitionGC2B);
        assertNotNull(transitionGC2C);
        assertNotNull(transitionGA2GB);
        assertNotNull(transitionGB2E);
        assertNull(transitionX2GC);

        // gateway route
        ActivityType gateA = CarnotModelQuery.findActivity(processDef, GATE_A_EXCL);
        assertNotNull(gateA);
        assertEquals(JoinSplitType.XOR_LITERAL, gateA.getJoin());
        ActivityType gateB = CarnotModelQuery.findActivity(processDef, GATE_B_EXCL);
        assertNotNull(gateB);
        assertEquals(JoinSplitType.XOR_LITERAL, gateB.getJoin());

        // activity splits
        assertEquals(JoinSplitType.AND_LITERAL, taskX.getSplit());
        assertEquals(JoinSplitType.AND_LITERAL, taskC.getSplit());

        // transition conditions
		assertEquals(COND_SEQ_A_GA, getCondition(transitionA2GA));
		assertEquals(COND_SEQ_B_GA, getCondition(transitionB2GA));
		assertEquals(COND_SEQ_C_GB, getCondition(transitionC2GB));
		assertEquals(COND_SEQ_C_D, getCondition(transitionC2D));

        // transition sources / targets


    	assertEquals(taskX, transitionGC2A.getFrom()); // sourceRef="TestModelParallelGateC"
    	assertEquals(taskA, transitionGC2A.getTo()); // targetRef="TestModelTaskA" />

    	assertEquals(taskX, transitionGC2B.getFrom()); // sourceRef="TestModelParallelGateC"
    	assertEquals(taskB, transitionGC2B.getTo()); // targetRef="TestModelTaskB"

    	assertEquals(taskX, transitionGC2C.getFrom()); // sourceRef="TestModelParallelGateC"
    	assertEquals(taskC, transitionGC2C.getTo()); // targetRef="TestModelTaskC" />

    	assertEquals(taskA, transitionA2GA.getFrom()); //sourceRef="TestModelTaskA"
    	assertEquals(gateA, transitionA2GA.getTo()); // targetRef="TestModelExclusiveGateA">

    	assertEquals(taskB, transitionB2GA.getFrom()); // sourceRef="TestModelTaskB"
    	assertEquals(gateA, transitionB2GA.getTo()); // targetRef="TestModelExclusiveGateA">

    	assertEquals(gateA, transitionGA2GB.getFrom()); // sourceRef="TestModelExclusiveGateA"
    	assertEquals(gateB, transitionGA2GB.getTo()); // targetRef="TestModelExclusiveGateB"

    	assertEquals(taskC, transitionC2GB.getFrom()); // sourceRef="TestModelTaskC"
    	assertEquals(gateB, transitionC2GB.getTo()); // targetRef="TestModelExclusiveGateB">

    	assertEquals(gateB, transitionGB2E.getFrom()); // sourceRef="TestModelExclusiveGateB"
    	assertEquals(taskE, transitionGB2E.getTo()); // targetRef="TestModelTaskE"

    	assertEquals(taskC, transitionC2D.getFrom()); // sourceRef="TestModelTaskC"
    	assertEquals(taskD, transitionC2D.getTo()); // targetRef="TestModelTaskD">

    	assertEquals(taskD, transitionD2GB.getFrom()); // sourceRef="TestModelTaskD"
    	assertEquals(gateB, transitionD2GB.getTo()); //targetRef="TestModelExclusiveGateB" />

//    	assertEquals(taskX, transitionX2GC.getFrom()); // sourceRef="TestModelTaskX"
//    	assertEquals(gateC, transitionX2GC.getTo()); // targetRef="TestModelParallelGateC"
    }

    private String getCondition(TransitionType transition) {
    	return ModelUtils.getCDataString(transition.getExpression().getMixed());
    }

}
