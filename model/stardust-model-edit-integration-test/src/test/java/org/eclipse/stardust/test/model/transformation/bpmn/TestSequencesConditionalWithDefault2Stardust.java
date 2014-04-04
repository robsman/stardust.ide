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
public class TestSequencesConditionalWithDefault2Stardust extends Bpmn2StardustTestSuite { //extends TestCase {

	private static final String TEST_ID_TASK_X ="TestModelTaskX";
	private static final String SEQ_COND_XA ="TestModelSequenceConditionalXtoA";
	private static final String SEQ_COND_XB ="TestModelSequenceConditionalXtoB";
	private static final String SEQ_DEFAULT_XC = "TestModelSequenceDefaultlXtoC";

	private static final String CONDITION_XA = "\"A\"==\"A\";";
	private static final String CONDITION_XB = "\"B\"==\"B\";";

    public TestSequencesConditionalWithDefault2Stardust() {}

    @Test
    public void testSequenceConditionalWithDefault() {
        final String modelFile = "ConditionalSequenceWithDefault.bpmn";
        ModelType result = transformModel(loadBpmnModel(modelFile));
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

        // activity split
        assertEquals(JoinSplitType.AND_LITERAL, taskX.getSplit());

        // transition elements
        TransitionType transitionCondXA = CarnotModelQuery.findTransition(processDef, SEQ_COND_XA);
        TransitionType transitionCondXB = CarnotModelQuery.findTransition(processDef, SEQ_COND_XB);
        TransitionType transitionDefaultXC = CarnotModelQuery.findTransition(processDef, SEQ_DEFAULT_XC);
        assertNotNull(transitionCondXA);
        assertNotNull(transitionCondXB);
        assertNotNull(transitionDefaultXC);

        // transition conditions
        assertEquals(CONDITION_XA, ModelUtils.getCDataString(transitionCondXA.getExpression().getMixed()));
        assertEquals(CONDITION_XB, ModelUtils.getCDataString(transitionCondXB.getExpression().getMixed()));
        assertEquals(TransitionUtil.OTHERWISE_KEY, transitionDefaultXC.getCondition());

        // transition source / target
        assertEquals(taskX, transitionCondXA.getFrom());
        assertEquals(taskA, transitionCondXA.getTo());

        assertEquals(taskX, transitionCondXB.getFrom());
        assertEquals(taskB, transitionCondXB.getTo());

        assertEquals(taskX, transitionDefaultXC.getFrom());
        assertEquals(taskC, transitionDefaultXC.getTo());

    }

}
