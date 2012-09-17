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
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_PARTNER_ENTITY_ORG_A;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_RESOURCE_ROLE_A;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_A;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_MODEL_OUTPUT_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.getResourceFilePath;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.loadBpmnModel;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.transformModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestTasks2Stardust {


    @Test
    public void testUserTask() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "UserTask.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testUserTask.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        assertNotNull(process);
        assertNotNull(result);
        ActivityType activity = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(activity);
        assertEquals(ActivityImplementationType.MANUAL_LITERAL, activity.getImplementation());
    }

    @Test
    public void testTaskPerformerAndOrganisation() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "PerformerAndPartnerEntityOrg.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "PerformerAndPartnerEntityOrg.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);
        assertNotNull(process);
        assertNotNull(result);

        IModelParticipant orgParticipant = CarnotModelQuery.findParticipant(result, TEST_ID_PARTNER_ENTITY_ORG_A);
        IModelParticipant resourceRole = CarnotModelQuery.findParticipant(result, TEST_ID_RESOURCE_ROLE_A);
        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(orgParticipant);
        assertNotNull(resourceRole);
        assertNotNull(taskA);
        assertTrue(orgParticipant instanceof OrganizationType);
        assertTrue(resourceRole instanceof RoleType);

        assertNotNull(taskA.getPerformer());
        assertTrue(taskA.getPerformer().equals(resourceRole));
    }

    @Test
    public void testAbstractTask() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "AbstractTask.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testAbstractTask.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType processDef = result.getProcessDefinition().get(0);
        ActivityType taskA = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_A);

        assertNotNull(processDef);
        assertNotNull(taskA);
        assertEquals(ActivityImplementationType.ROUTE_LITERAL, taskA.getImplementation());

    }
}
