package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.triggers;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsMessageType;

/**
 * Stardust Attributes for JMS Triggers.
 *
 * @author Simon Nikles
 *
 */
public enum JMSTriggerAttributes {

	MESSAGE_ACCEPTOR("carnot:engine:messageAcceptor", Labels.jms_messageAcceptor, null, "org.eclipse.stardust.engine.extensions.jms.app.DefaultMessageAcceptor", null),
	MESSAGE_TYPE("carnot:engine:messageType", Labels.jms_messageType, JmsMessageType.getChoices(), JmsMessageType.getDefault().getKey(), "org.eclipse.stardust.engine.extensions.jms.app.MessageType");
	;

	private String attributeName;
	private String label;
	private Map<String, String> choices;
	private String defaultVal;
	private String dataType;

	private JMSTriggerAttributes(String attributeName, String label, Map<String, String> choices, String defaultVal, String dataType) {
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
