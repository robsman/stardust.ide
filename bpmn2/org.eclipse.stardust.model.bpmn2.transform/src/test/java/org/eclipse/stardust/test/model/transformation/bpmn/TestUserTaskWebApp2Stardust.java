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
import static org.junit.Assert.assertTrue;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestUserTaskWebApp2Stardust extends Bpmn2StardustTestSuite {

	private static final String DATA_OUTPUT_ASSOCIATION_ID = "TestModelOutputAssociationTaskA";
	private static final String DATA_ASSOCIATION_ASSIGNMENT_ID = "TestAssignmentOutputTaskA";
	private static final String JSF_ACCESS_POINT = "getCustomerData()";

    @Test
    public void testWebAppUserTask() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "UserTaskWebApp.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testUserTaskWebApp.xpdl";

        // MODEL / PROCESS
        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);
        assertNotNull(process);
        assertNotNull(result);

        // USER TASK
        ActivityType activity = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(activity);
        assertEquals(ActivityImplementationType.APPLICATION_LITERAL, activity.getImplementation());

        // DATA MAPPING
        assertTrue(activity.getDataMapping().size()==1);
        DataMappingType mapping = activity.getDataMapping().get(0);

        assertEquals(DATA_OUTPUT_ASSOCIATION_ID + "_" + DATA_ASSOCIATION_ASSIGNMENT_ID, mapping.getId());
        assertEquals(JSF_ACCESS_POINT, mapping.getApplicationAccessPoint());

        // INTERACTIVE APPLICATION (JSF)
        ApplicationType application = activity.getApplication();
        assertTrue(application.isInteractive());
        assertNotNull(application);
        assertNotNull(application.getContext());
        ContextType context = application.getContext().get(0);
        assertNotNull(context);
        assertEquals(PredefinedConstants.JSF_CONTEXT, context.getType().getId());
    }


}
