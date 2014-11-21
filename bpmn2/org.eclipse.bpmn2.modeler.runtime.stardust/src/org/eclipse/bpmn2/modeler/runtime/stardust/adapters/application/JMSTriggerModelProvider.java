package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.triggers.JMSTriggerAttributes.MESSAGE_ACCEPTOR;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.triggers.JMSTriggerAttributes.MESSAGE_TYPE;

import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType;

/**
 * Initialize and update Stardust attributes for JMS Applications.
 *
 * @author Simon Nikles
 *
 */
public class JMSTriggerModelProvider extends TriggerModelMaintainer {

	private static final String ID_PREFIX_JMS_TRIGGER = "JmsTrigger_";
	private static final String NAME_PREFIX_JMS_TRIGGER = "JmsTrigger ";

	public static void createJmsTriggerModel(StardustInterfaceType sdInterface) {
		removeTriggerModel(sdInterface);
		StardustTriggerType trigger = SdbpmnFactory.eINSTANCE.createStardustTriggerType();
		long triggerId = generateTriggerId();
		trigger.setId(ID_PREFIX_JMS_TRIGGER + triggerId);
		trigger.setName(NAME_PREFIX_JMS_TRIGGER + triggerId);
		trigger.getAttribute().add(PropertyAdapterCommons.createAttributeType(MESSAGE_ACCEPTOR.attributeName(), MESSAGE_ACCEPTOR.defaultVal(), MESSAGE_ACCEPTOR.dataType()));
		trigger.getAttribute().add(PropertyAdapterCommons.createAttributeType(MESSAGE_TYPE.attributeName(), MESSAGE_TYPE.defaultVal(), MESSAGE_TYPE.dataType()));

		sdInterface.setStardustTrigger(trigger);
	}

}
