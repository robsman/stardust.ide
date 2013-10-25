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
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_MODEL_OUTPUT_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.getResourceFilePath;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.loadBpmnModel;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.transformModel;
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
public class TestSequencesConditionalWithDefaultAndUnconditional2Stardust {

	private static final String TEST_ID_TASK_X ="TestModelTaskX";

	private static final String SEQ_COND_XA ="TestModelSequenceConditionalXtoA";
	private static final String SEQ_UNCOND_XB ="TestModelSequenceXtoB";
	private static final String SEQ_DEFAULT_XC = "TestModelSequenceDefaultXtoC";

	private static final String CONDITION_XA = "\"A\"==\"A\";";

    public TestSequencesConditionalWithDefaultAndUnconditional2Stardust() {}

    @Test
    public void testSequenceConditionalWithDefaultAndUnconditional() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "ConditionalSequenceWithDefaultAndUnconditional.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testConditionalSequenceWithDefaultAndUnconditional.xpdl";
        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType processDef = result.getProcessDefinition().get(0);

        // activity elements
        ActivityType taskX = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_X);
        ActivityType taskA = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_C);
        assertNotNull(taskX);
        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);

        // transition elements
        TransitionType transitionCondXA = CarnotModelQuery.findTransition(processDef, SEQ_COND_XA);
        TransitionType transitionCondXB = CarnotModelQuery.findTransition(processDef, SEQ_UNCOND_XB);
        TransitionType transitionDefaultXC = CarnotModelQuery.findTransition(processDef, SEQ_DEFAULT_XC);
        assertNotNull(transitionCondXA);
        assertNotNull(transitionCondXB);
        assertNotNull(transitionDefaultXC);

        // generated route (source of sequences X-A and X-C)
        ActivityType route = transitionDefaultXC.getFrom();
        assertEquals(route, transitionCondXA.getFrom());
        assertNotNull(route);

        // activity split
        assertEquals(JoinSplitType.AND_LITERAL, taskX.getSplit());
        assertEquals(JoinSplitType.AND_LITERAL, route.getSplit());

        // transition conditions
        assertEquals(CONDITION_XA, ModelUtils.getCDataString(transitionCondXA.getExpression().getMixed()));
        assertEquals("true", ModelUtils.getCDataString(transitionCondXB.getExpression().getMixed()));
        assertEquals(TransitionUtil.OTHERWISE_KEY, transitionDefaultXC.getCondition());


    }

}
