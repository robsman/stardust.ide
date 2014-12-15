package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;

public enum SynchronousApplicationsCommonAttributes {

	SYNCH_RETRY_ENABLE("synchronous:retry:enable", Labels.synchronous_retry_enable, null, "true", "boolean"),
	SYNCH_RETRY_NUMBER("synchronous:retry:number", Labels.synchronous_retry_number, null, "", null),
	SYNCH_RETRY_TIME("synchronous:retry:time", Labels.synchronous_retry_time, null, "", null)
	;

	private String attributeName;
	private String label;
	private Map<String, String> choices;
	private String defaultVal;
	private String dataType;

	private SynchronousApplicationsCommonAttributes(String attributeName, String label, Map<String, String> choices, String defaultVal, String dataType) {
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
