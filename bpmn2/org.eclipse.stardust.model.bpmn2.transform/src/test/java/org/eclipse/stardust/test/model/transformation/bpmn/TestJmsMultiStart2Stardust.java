package org.eclipse.stardust.test.model.transformation.bpmn;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.eclipse.stardust.model.bpmn2.transform.util.PredefinedDataInfo;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.ProcessStartConfigurator;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.TransitionUtil;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.junit.Test;

public class TestJmsMultiStart2Stardust extends Bpmn2StardustTestSuite {

	private static final String JMS_TRIGGER_1 = "JMSEvent1";
	private static final String JMS_TRIGGER_2 = "JMSEvent2";
	private static final String JMS_TRIGGER_3 = "JMSEvent3";

	private static final String START_ROUTE_ID = "process_8" + ProcessStartConfigurator.START_ROUTE_POST_FIX;

	private static final String JMS_TRIGGER_1_CONDITION = PredefinedDataInfo.VAR_START_EVENT_ID+"==\"JMSEvent1\";";
	private static final String JMS_TRIGGER_2_CONDITION = PredefinedDataInfo.VAR_START_EVENT_ID+"==\"JMSEvent2\";";
	private static final String JMS_TRIGGER_3_CONDITION = PredefinedDataInfo.VAR_START_EVENT_ID+"==\"JMSEvent3\";";

	@Test
	public void testMultipleJmsWithDistinctControlFlow() {

        final String modelFile = TEST_BPMN_MODEL_DIR + "JmsMultiStartEvent.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testJmsMultiStartEvent.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType processDef = result.getProcessDefinition().get(0);

        // TODO check source and target / mappings of parameter mappings etc.

        TriggerType trigger1 = CarnotModelQuery.findTrigger(processDef, JMS_TRIGGER_1);
        assertThat("Start Trigger 1 available", trigger1, notNullValue());
        assertThat("Exactly one generated access point", trigger1.getAccessPoint().size(), is(1));
        assertThat("Exactly one generated parameter mapping to StartEventVariable", trigger1.getParameterMapping().size(), is(1));

        TriggerType trigger2 = CarnotModelQuery.findTrigger(processDef, JMS_TRIGGER_2);
        assertThat("Start Trigger 2 available", trigger2, notNullValue());
        assertThat("Exactly one generated access point", trigger2.getAccessPoint().size(), is(1));
        assertThat("Exactly one generated parameter mapping to StartEventVariable", trigger2.getParameterMapping().size(), is(1));

        TriggerType trigger3 = CarnotModelQuery.findTrigger(processDef, JMS_TRIGGER_3);
        assertThat("Start Trigger 3 available", trigger3, notNullValue());
        assertThat("Exactly one generated access point", trigger3.getAccessPoint().size(), is(1));
        assertThat("Exactly one generated parameter mapping to StartEventVariable", trigger3.getParameterMapping().size(), is(1));

        ActivityType startRoute = CarnotModelQuery.findActivity(processDef, START_ROUTE_ID);
        assertThat("Generated start route available", startRoute, notNullValue());

        ActivityType taskA = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(processDef, TEST_ID_TASK_C);

        assertThat("Task A not null", taskA, notNullValue());
        assertThat("Task B not null", taskB, notNullValue());
        assertThat("Task C not null", taskC, notNullValue());

        assertThat("Exactly one incoming sequence", taskA.getInTransitions().size(), is(1));
        assertThat("Exactly one incoming sequence", taskB.getInTransitions().size(), is(1));
        assertThat("Exactly one incoming sequence", taskC.getInTransitions().size(), is(1));

        assertThat("Flow Condition corresponds to start event", taskA.getInTransitions().get(0).getCondition(), is(TransitionUtil.CONDITION_KEY));
        assertThat("Flow Condition corresponds to start event", taskB.getInTransitions().get(0).getCondition(), is(TransitionUtil.CONDITION_KEY));
        assertThat("Flow Condition corresponds to start event", taskC.getInTransitions().get(0).getCondition(), is(TransitionUtil.CONDITION_KEY));

        assertThat("Flow Condition corresponds to start event", getCondition(taskA.getInTransitions().get(0)), is(JMS_TRIGGER_1_CONDITION));
        assertThat("Flow Condition corresponds to start event", getCondition(taskB.getInTransitions().get(0)), is(JMS_TRIGGER_2_CONDITION));
        assertThat("Flow Condition corresponds to start event", getCondition(taskC.getInTransitions().get(0)), is(JMS_TRIGGER_3_CONDITION));

	}

    private String getCondition(TransitionType transition) {
    	return ModelUtils.getCDataString(transition.getExpression().getMixed());
    }

}
