package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CommonAttributes.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsDirectionEnum;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsLocationEnum;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsMessageType;

/**
 * Stardust Attributes for JMS Applications.
 *
 * @author Simon Nikles
 *
 */
public enum JMSApplicationAttributes {

	VISIBILITY(Visibility.NAME, Visibility.LABEL, Visibility.getChoices(), "Public", null),
	DIRECTION("carnot:engine:type", Labels.jms_Direction, JmsDirectionEnum.getChoices(), "", "org.eclipse.stardust.engine.extensions.jms.app.JMSDirection"),
	QUEUE_CONNECTION_FACTORY_JNDI("carnot:engine:queueConnectionFactory.jndiName", Labels.jms_queueConnectionFactory_jndiName, null, "jms/CarnotXAConnectionFactory", null),
	QUEUE_JNDI("carnot:engine:queue.jndiName", Labels.jms_queue_jndiName, null, "jms/CarnotApplicationQueue", null),
	REQUEST_MESSAGE_TYPE("carnot:engine:requestMessageType", Labels.jms_requestMessageType, JmsMessageType.getChoices(), JmsMessageType.getDefault().getKey(), "org.eclipse.stardust.engine.extensions.jms.app.MessageType"),
	RESPONSE_MESSAGE_TYPE("carnot:engine:responseMessageType", Labels.jms_responseMessageType, JmsMessageType.getChoices(), JmsMessageType.getDefault().getKey(), "org.eclipse.stardust.engine.extensions.jms.app.MessageType"),
	INCLUDE_OID_HEADERS("carnot:engine:includeOidHeaders", Labels.jms_includeOidHeaders, null, "false", "boolean"),
	MESSAGE_PROVIDER("carnot:engine:messageProvider", Labels.jms_messageProvider, null, "org.eclipse.stardust.engine.extensions.jms.app.DefaultMessageProvider", null),
	MESSAGE_ACCEPTOR("carnot:engine:messageAcceptor", Labels.jms_messageAcceptor, null, "org.eclipse.stardust.engine.extensions.jms.app.DefaultMessageAcceptor", null),

	ACCESSPOINT_LOCATION("carnot:engine:jms.location", Labels.jms_accesspointLocation, JmsLocationEnum.getChoices(), JmsLocationEnum.HEADER.getKey(), "org.eclipse.stardust.engine.extensions.jms.app.JMSLocation"),
	ACCESSPOINT_DEFAULT_VALUE("carnot:engine:defaultValue", Labels.jms_accesspointDefaultValue, null, "", null)
	;

	private String attributeName;
	private String label;
	private Map<String, String> choices;
	private String defaultVal;
	private String dataType;

	private JMSApplicationAttributes(String attributeName, String label, Map<String, String> choices, String defaultVal, String dataType) {
		this.attributeName = attributeName;
		this.label = label;
		this.choices = choices;
		this.defaultVal = defaultVal;
		this.dataType = dataType;
	}

	public String attributeName() {
		return attributeName;
	}

	public String label() {
		return label;
	}

	public Map<String, String> choices() {
		return choices;
	}

	public String defaultVal() {
		return defaultVal;
	}

	public String dataType() {
		return dataType;
	}

}
