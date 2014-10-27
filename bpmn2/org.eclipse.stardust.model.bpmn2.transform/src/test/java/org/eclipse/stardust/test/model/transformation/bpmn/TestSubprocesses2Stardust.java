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
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestSubprocesses2Stardust extends Bpmn2StardustTestSuite {
    @Test
    public void testCollapsedSubprocess() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "CollapsedSubprocess.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testCollapsedSubprocess.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType mainprocess = CarnotModelQuery.findProcessDefinition(result, TEST_ID_MAIN_PROCESS);
        ProcessDefinitionType subprocess = CarnotModelQuery.findProcessDefinition(result, TEST_ID_SUBPROCESS);

        ActivityType taskA = CarnotModelQuery.findActivity(subprocess, TEST_ID_TASK_A);
        ActivityType subprocessActivity = CarnotModelQuery.findActivity(mainprocess, TEST_ID_SUBPROCESS);

        assertNotNull(mainprocess);
        assertNotNull(subprocessActivity);
        assertNotNull(subprocess);
        assertNotNull(taskA);

        assertEquals(ActivityImplementationType.SUBPROCESS_LITERAL, subprocessActivity.getImplementation());
        assertEquals(subprocess, subprocessActivity.getImplementationProcess());

    }
}
