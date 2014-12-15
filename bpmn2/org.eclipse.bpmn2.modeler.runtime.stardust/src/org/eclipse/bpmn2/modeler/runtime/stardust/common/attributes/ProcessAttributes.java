package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;

public enum ProcessAttributes {

	ATTACHMENTS_UNIQUE_PER_ROOT("carnot:engine:dms:byReference", Labels.process_attachment_unique_per_root, null, "false", "boolean")
	;

	private String attributeName;
	private String label;
	private Map<String, String> choices;
	private String defaultVal;
	private String dataType;

	private ProcessAttributes(String attributeName, String label, Map<String, String> choices, String defaultVal, String dataType) {
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
