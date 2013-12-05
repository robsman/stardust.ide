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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestTasks2Stardust extends Bpmn2StardustTestSuite {

	private static final String PERFORMER_ROLE_1 = "ROLLE_1"; // uuid="5d167010-bd14-44ce-bc9d-93fbc00e4d19" name="Rolle 1"
	private static final String PERFORMER_ROLE_2 = "ROLLE_2"; // uuid="16ab703f-0b2b-4e17-b62d-246ed19302e7" name="Rolle 2"
	private static final String PERFORMER_ORG_1 = "ORGANISATION_1"; // uuid="0eb7ab16-4626-4fd5-8796-d606c5ebbc2a" name="Organisation 1"
	private static final String PERFORMER_ORG_2 = "ORGANISATION_2"; // uuid="547ee9fe-6b3c-463c-b550-581f8305edbc" name="Organisation 2"
	private static final String CONDITIONAL_PERFORMER = "CONDITIONAL_PERFORMER"; // uuid="bcabf656-4bec-438d-9802-d2a12bca66c6" name="Bedingt"
	private static final String CONDITION_VARIABLE_ID = "DummyVarId";

    @Test
    public void testSimpleUserTaskCreation() {
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

        assertEquals(ActivityImplementationType.MANUAL_LITERAL, taskA.getImplementation());
        assertEquals(ActivityImplementationType.MANUAL_LITERAL, taskB.getImplementation());
        assertEquals(ActivityImplementationType.MANUAL_LITERAL, taskC.getImplementation());
    }

    @Test
    public void testTaskPerformerAndOrganisation() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "UserTaskPerformer.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testUserTaskPerformer.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);
        assertNotNull(process);
        assertNotNull(result);

        IModelParticipant role1 =  CarnotModelQuery.findParticipant(result, PERFORMER_ROLE_1);
        IModelParticipant role2 =  CarnotModelQuery.findParticipant(result, PERFORMER_ROLE_2);
        IModelParticipant org1 =  CarnotModelQuery.findParticipant(result, PERFORMER_ORG_1);
        IModelParticipant org2 =  CarnotModelQuery.findParticipant(result, PERFORMER_ORG_2);
        IModelParticipant conditionalPerformer =  CarnotModelQuery.findParticipant(result, CONDITIONAL_PERFORMER);
        assertTrue(null != role1 && null != role1 && null != org1 && null != org2 && null != conditionalPerformer);
        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        assertTrue(null != taskA && null != taskB && null != taskC);

        // Task Performers
        assertEquals(role1, taskA.getPerformer());
        assertEquals(role2, taskB.getPerformer());
        assertEquals(conditionalPerformer, taskC.getPerformer());

        // Conditional Performer Attributes
        assertEquals(CONDITION_VARIABLE_ID, ((ConditionalPerformerType)conditionalPerformer).getData().getId());
        assertEquals("user", AttributeUtil.getAttributeValue(conditionalPerformer, PredefinedConstants.CONDITIONAL_PERFORMER_KIND));

        // Organisation Structure & Teamlead
        assertThat(org1, is(parentOf(org2, result)));
        assertThat(role1, is(teamLeadOf(org2)));
        assertThat(org2, is(parentOf(role2, result)));

    }

    private IModelParticipant parentOf(IModelParticipant org, ModelType model) {
    	for (OrganizationType orgType : model.getOrganization()) {
    		for (ParticipantType partType : orgType.getParticipant()) {
    			if (partType.getParticipant().equals(org)) {
    				return orgType;
    			}
    		}
    	}
		return null;
	}

	private IModelParticipant teamLeadOf(IModelParticipant org) {
    	return ((OrganizationType)org).getTeamLead();
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
