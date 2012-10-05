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
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_A;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_B;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_C;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_D;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_MODEL_OUTPUT_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.getResourceFilePath;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.loadBpmnModel;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.transformModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class TestSequencesUncontrolledFlow2Stardust { //extends TestCase {

	private static final String SEQ_AB ="TestModelSequenceAtoB";
	private static final String SEQ_AC = "TestModelSequenceAtoC";
	private static final String SEQ_BD ="TestModelSequenceBtoD";
	private static final String SEQ_CD ="TestModelSequenceCtoD";

    public TestSequencesUncontrolledFlow2Stardust() {}

    @Test
    public void testSequenceUncontrolledFlow() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "UncontrolledFlow.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testUncontrolledFlow.xpdl";
        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType processDef = result.getProcessDefinition().get(0);

        ActivityType taskA = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_C);
        ActivityType taskD = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_D);
        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);
        assertNotNull(taskD);

        TransitionType transitionAB = CarnotModelQuery.findTransition(processDef, SEQ_AB);
        TransitionType transitionAC = CarnotModelQuery.findTransition(processDef, SEQ_AC);
        TransitionType transitionBD = CarnotModelQuery.findTransition(processDef, SEQ_BD);
        TransitionType transitionCD = CarnotModelQuery.findTransition(processDef, SEQ_CD);
        assertNotNull(transitionAB);
        assertNotNull(transitionAC);
        assertNotNull(transitionBD);
        assertNotNull(transitionCD);

        // uncontrolled split: AND
        assertEquals(JoinSplitType.AND_LITERAL, taskA.getSplit());

        // uncontrolled join: XOR
        assertEquals(JoinSplitType.XOR_LITERAL, taskD.getJoin());

        // transition source / target...
        assertEquals(taskA, transitionAB.getFrom());
        assertEquals(taskB, transitionAB.getTo());

        assertEquals(taskA, transitionAC.getFrom());
        assertEquals(taskC, transitionAC.getTo());

        assertEquals(taskB, transitionBD.getFrom());
        assertEquals(taskD, transitionBD.getTo());

        assertEquals(taskC, transitionCD.getFrom());
        assertEquals(taskD, transitionCD.getTo());
    }

}
