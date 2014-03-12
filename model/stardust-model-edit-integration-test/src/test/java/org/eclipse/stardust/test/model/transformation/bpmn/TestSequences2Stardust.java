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
public class TestSequences2Stardust extends Bpmn2StardustTestSuite {

    public TestSequences2Stardust() {}

    @Test
    public void testSequenceActivityToActivity() {
        final String modelFile = "Sequence.bpmn";
        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType processDef = result.getProcessDefinition().get(0);
        assertNotNull(processDef);

        TransitionType transition = CarnotModelQuery.findTransition(processDef, TEST_ID_SEQUENCE_A_TO_B);
        ActivityType taskA = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_B);

        assertNotNull(transition);
        assertNotNull(transition.getFrom());
        assertNotNull(transition.getTo());
        assertEquals(taskA, transition.getFrom());
        assertEquals(taskB, transition.getTo());
    }

}
