package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.triggers;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.XSDType2Stardust;

/**
 * Stardust Attributes for JMS Triggers.
 *
 * @author Simon Nikles
 *
 */
public enum ManualTriggerAttributes {

	PARTICIPANT("carnot:engine:participant", Labels.stardust_participant, null, "", XSDType2Stardust.STRING.getPrimitive())
	;

	private String attributeName;
	private String label;
	private Map<String, String> choices;
	private String defaultVal;
	private String dataType;

	private ManualTriggerAttributes(String attributeName, String label, Map<String, String> choices, String defaultVal, String dataType) {
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
