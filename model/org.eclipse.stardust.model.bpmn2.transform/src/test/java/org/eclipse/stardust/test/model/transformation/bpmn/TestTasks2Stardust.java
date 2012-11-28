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
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_B;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_C;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_MODEL_OUTPUT_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.getResourceFilePath;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.loadBpmnModel;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.transformModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestTasks2Stardust {

	private static final String PERFORMER_ROLE_ADMIN = "Administrator";
	private static final String PERFORMER_ORGANIZATION = "OrganizationX";
	private static final String PERFORMER_CONDITIONAL = "ConditionalX";

    @Test
    public void testUserTask() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "UserTask.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testUserTask.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);

        assertNotNull(process);
        assertNotNull(result);

        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        assertNotNull(taskA);
        assertNotNull(taskB);
        assertNotNull(taskC);

        IModelParticipant adminRole = taskA.getPerformer();
        IModelParticipant organization =  taskB.getPerformer();
        IModelParticipant conditionalPerformer =  taskC.getPerformer();
        assertNotNull(adminRole);
        assertNotNull(organization);
        assertNotNull(conditionalPerformer);

        assertEquals(ActivityImplementationType.MANUAL_LITERAL, taskA.getImplementation());
        assertEquals(ActivityImplementationType.MANUAL_LITERAL, taskB.getImplementation());
        assertEquals(ActivityImplementationType.MANUAL_LITERAL, taskC.getImplementation());

        assertEquals(PERFORMER_ROLE_ADMIN, adminRole.getId());
        assertEquals(PERFORMER_ORGANIZATION, organization.getId());
        assertEquals(PERFORMER_CONDITIONAL, conditionalPerformer.getId());

        assertTrue(adminRole instanceof RoleType);
        assertTrue(organization instanceof OrganizationType);
        assertTrue(conditionalPerformer instanceof ConditionalPerformerType);

        assertEquals("PROCESS_ID", ((ConditionalPerformerType)conditionalPerformer).getData().getId());
        assertEquals("getId()", ((ConditionalPerformerType)conditionalPerformer).getDataPath());
        assertEquals("userGroup", AttributeUtil.getAttributeValue(conditionalPerformer, PredefinedConstants.CONDITIONAL_PERFORMER_KIND));
        assertEquals("Public", AttributeUtil.getAttributeValue(adminRole, PredefinedConstants.MODELELEMENT_VISIBILITY));
        assertEquals("false", AttributeUtil.getAttributeValue(organization, PredefinedConstants.BINDING_ATT));


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
