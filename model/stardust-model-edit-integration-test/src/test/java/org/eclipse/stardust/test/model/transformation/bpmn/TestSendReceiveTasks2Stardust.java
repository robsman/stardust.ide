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
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestSendReceiveTasks2Stardust extends Bpmn2StardustTestSuite {

	private static final String INTERFACE_JMS_SEND = "SendJmsImpl";
	private static final String INTERFACE_JMS_RECEIVE= "ReceiveJmsImpl";

	private static final String SEND_TASK_ID = "TestModelSendTask";
	private static final String DATA_OBJECT_SENT_MESSAGE = "TestModelDataObjectOutMessage";

	private static final String RECEIVE_TASK_ID = "TestModelReceiveTask";
	private static final String DATA_OBJECT_RECEIVED_MESSAGE = "TestModelDataObjectInMessage";

	private static final String ACCESS_POINT_NAMES = "content";

    @Test
    public void testSendTask() {
        final String modelFile = "SendAndReceiveTask.bpmn";

        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType processDef = result.getProcessDefinition().get(0);

        ActivityType sendTask = CarnotModelQuery.findActivity(processDef, SEND_TASK_ID);
        ActivityType receiveTask = CarnotModelQuery.findActivity(processDef, RECEIVE_TASK_ID);

        assertNotNull(sendTask);
        assertNotNull(receiveTask);

		ApplicationType sendApp = sendTask.getApplication();
		ApplicationType receiveApp = receiveTask.getApplication();
		assertNotNull(sendApp);
		assertEquals(INTERFACE_JMS_SEND, sendApp.getId());
		assertNotNull(receiveApp);
		assertEquals(INTERFACE_JMS_RECEIVE, receiveApp.getId());

        assertNotNull(sendApp.getAccessPoint().get(0));

        assertNotNull(receiveApp.getAccessPoint().get(0));

        assertNotNull(sendTask.getDataMapping().get(0));
        DataMappingType inDataMapping = sendTask.getDataMapping().get(0);
        String sendAccessPoint = inDataMapping.getApplicationAccessPoint();
        DataType sendDataVar = inDataMapping.getData();

        assertEquals(ACCESS_POINT_NAMES, sendAccessPoint);
        assertEquals(DATA_OBJECT_SENT_MESSAGE, sendDataVar.getId());

        assertNotNull(receiveTask.getDataMapping().get(0));
        DataMappingType outDataMapping = receiveTask.getDataMapping().get(0);
        String receiveAccessPoint = outDataMapping.getApplicationAccessPoint();
        DataType receiveDataVar = outDataMapping.getData();

        assertEquals(ACCESS_POINT_NAMES, receiveAccessPoint);
        assertEquals(DATA_OBJECT_RECEIVED_MESSAGE, receiveDataVar.getId());


    }



}
