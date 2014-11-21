package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.DIRECTION;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.INCLUDE_OID_HEADERS;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.MESSAGE_ACCEPTOR;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.MESSAGE_PROVIDER;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.QUEUE_CONNECTION_FACTORY_JNDI;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.QUEUE_JNDI;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.REQUEST_MESSAGE_TYPE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.RESPONSE_MESSAGE_TYPE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.VISIBILITY;

import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsDirectionEnum;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;

/**
 * Initialize and update Stardust attributes for JMS Applications.
 *
 * @author Simon Nikles
 *
 */
public class JMSApplicationModelProvider extends ApplicationModelMaintainer {

	private static final String ID_PREFIX_JMS_APP = "JavaMessagingService_";
	private static final String NAME_PREFIX_JMS_APP = "JavaMessagingService ";


	public static void createJmsApplicationModel(StardustInterfaceType sdInterface) {
		removeApplicationModel(sdInterface);
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		//sdApplication.setElementOid(generateElementOid(sdInterface.eResource()));
		long appTypeId = generateAppTypeId();
		sdApplication.setId(ID_PREFIX_JMS_APP + appTypeId);
		sdApplication.setName(NAME_PREFIX_JMS_APP + appTypeId);
		sdApplication.getAccessPoint1();
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(VISIBILITY.attributeName(), VISIBILITY.defaultVal(), VISIBILITY.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(DIRECTION.attributeName(), DIRECTION.defaultVal(), DIRECTION.dataType()));
		sdInterface.setStardustApplication(sdApplication);
	}

	public static void updateJmsApplicationModel(StardustInterfaceType sdInterface, JmsDirectionEnum newDirectionValue) {

		StardustApplicationType sdApplication = sdInterface.getStardustApplication();

		AttributeType at = PropertyAdapterCommons.findAttributeType(sdApplication, REQUEST_MESSAGE_TYPE.attributeName());
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, RESPONSE_MESSAGE_TYPE.attributeName());
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, MESSAGE_PROVIDER.attributeName());
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, MESSAGE_ACCEPTOR.attributeName());
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, QUEUE_CONNECTION_FACTORY_JNDI.attributeName());
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, QUEUE_JNDI.attributeName());
		if (null != at) sdApplication.getAttribute().remove(at);

		sdApplication.getAccessPoint1().clear();

		if (null != newDirectionValue) {
			if (JmsDirectionEnum.OUT.equals(newDirectionValue) || JmsDirectionEnum.INOUT.equals(newDirectionValue)) {
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(QUEUE_CONNECTION_FACTORY_JNDI.attributeName(), QUEUE_CONNECTION_FACTORY_JNDI.defaultVal(), QUEUE_CONNECTION_FACTORY_JNDI.dataType()));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(QUEUE_JNDI.attributeName(), QUEUE_JNDI.defaultVal(), QUEUE_JNDI.dataType()));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(MESSAGE_PROVIDER.attributeName(), MESSAGE_PROVIDER.defaultVal(), MESSAGE_PROVIDER.dataType()));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(REQUEST_MESSAGE_TYPE.attributeName(), REQUEST_MESSAGE_TYPE.defaultVal(), REQUEST_MESSAGE_TYPE.dataType()));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(INCLUDE_OID_HEADERS.attributeName(), INCLUDE_OID_HEADERS.defaultVal(), INCLUDE_OID_HEADERS.dataType()));
			}
			if (JmsDirectionEnum.IN.equals(newDirectionValue) || JmsDirectionEnum.INOUT.equals(newDirectionValue)) {
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(MESSAGE_ACCEPTOR.attributeName(), MESSAGE_ACCEPTOR.defaultVal(), MESSAGE_ACCEPTOR.dataType()));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(RESPONSE_MESSAGE_TYPE.attributeName(), MESSAGE_ACCEPTOR.defaultVal(), MESSAGE_ACCEPTOR.dataType()));
			}
		}
	}
}
