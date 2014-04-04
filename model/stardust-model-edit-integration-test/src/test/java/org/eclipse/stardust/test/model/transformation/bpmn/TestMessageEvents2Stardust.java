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

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestMessageEvents2Stardust extends Bpmn2StardustTestSuite {

	//private static final String INTERFACE_JMS_TRIGGER_START = "ReceiveOffer";
	private static final String INTERFACE_JMS_SEND_EVENT = "SendRequest";
	private static final String INTERFACE_JMS_RECEIVE_EVENT = "ReceiveResponse";
	private static final String INTERFACE_JMS_SEND_END = "SendOrder";

	private static final String START_EVENT_ID = "TestModelStartEvent";
	private static final String SEND_EVENT_ID = "TestModelSendEvent";
	private static final String RECEIVE_EVENT_ID = "TestModelReceiveEvent";
	private static final String END_EVENT_ID = "TestModelEndEvent";

	private static final String ACCESSPOINTS_ID = "content";
	private static final String DATA_OBJECT_ID = "TestModelDataObjectA";

    @Test
    public void testEvents() {
        final String modelFile = "MessageEvents.bpmn";

        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType processDef = result.getProcessDefinition().get(0);

        TriggerType startEvent = CarnotModelQuery.findTrigger(processDef, START_EVENT_ID);
        ActivityType sendEvent = CarnotModelQuery.findActivity(processDef, SEND_EVENT_ID);
        ActivityType receiveEvent = CarnotModelQuery.findActivity(processDef, RECEIVE_EVENT_ID);
        ActivityType endEvent = CarnotModelQuery.findActivity(processDef, END_EVENT_ID);
        assertNotNull(startEvent);
        assertNotNull(sendEvent);
        assertNotNull(receiveEvent);
        assertNotNull(endEvent);

        testApplications(result, processDef, sendEvent, receiveEvent, endEvent);
        testTrigger(result, processDef, startEvent);

    }

	private void testApplications(ModelType result, ProcessDefinitionType processDef, ActivityType sendEvent,
			ActivityType receiveEvent, ActivityType endEvent) {

		ApplicationType intermediateSendApp = sendEvent.getApplication();
		ApplicationType intermediateReceiveApp = receiveEvent.getApplication();
		ApplicationType endSendApp = endEvent.getApplication();
		assertNotNull(intermediateReceiveApp);
		assertNotNull(intermediateSendApp);
		assertNotNull(endSendApp);

		assertEquals(INTERFACE_JMS_SEND_EVENT, intermediateSendApp.getId());
		assertEquals(INTERFACE_JMS_RECEIVE_EVENT, intermediateReceiveApp.getId());
		assertEquals(INTERFACE_JMS_SEND_END, endSendApp.getId());

		assertEquals(PredefinedConstants.JMS_TRIGGER, intermediateSendApp.getType().getId());
		assertEquals(PredefinedConstants.JMS_TRIGGER, intermediateReceiveApp.getType().getId());
		assertEquals(PredefinedConstants.JMS_TRIGGER, endSendApp.getType().getId());

		assertEquals(1, intermediateSendApp.getAccessPoint().size());
		assertEquals(ACCESSPOINTS_ID, intermediateSendApp.getAccessPoint().get(0).getId());

		assertEquals(1, intermediateReceiveApp.getAccessPoint().size());
		assertEquals(ACCESSPOINTS_ID, intermediateReceiveApp.getAccessPoint().get(0).getId());

		assertEquals(1, endSendApp.getAccessPoint().size());
		assertEquals(ACCESSPOINTS_ID, endSendApp.getAccessPoint().get(0).getId());


		// maybe check some sample attributes...
	}

	private void testTrigger(ModelType result, ProcessDefinitionType processDef, TriggerType startEvent) {
		assertEquals(PredefinedConstants.JMS_TRIGGER, startEvent.getType().getId());
		assertEquals(1, startEvent.getAccessPoint().size());
		assertEquals(ACCESSPOINTS_ID, startEvent.getAccessPoint().get(0).getId());
		assertEquals(1, startEvent.getParameterMapping().size());
		assertEquals(DATA_OBJECT_ID, startEvent.getParameterMapping().get(0).getData().getId());
		assertEquals(ACCESSPOINTS_ID, startEvent.getParameterMapping().get(0).getParameter());
	}

}
