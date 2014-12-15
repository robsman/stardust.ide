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

import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestSwimlanes2Stardust extends Bpmn2StardustTestSuite {

    @Test
    public void testPoolToOrganization() {
    }

    @Test
    public void testLaneToRole() {
        /*final String modelFile = BPMN_MODEL_DIR + "Lane.bpmn";
        final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testLane.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);

        assertNotNull(result);
        IModelParticipant participant = findParticipant(result, TEST_ID_LANE_L1);
        assertNotNull(participant);
        assertTrue(participant instanceof RoleType); */
    }

}
