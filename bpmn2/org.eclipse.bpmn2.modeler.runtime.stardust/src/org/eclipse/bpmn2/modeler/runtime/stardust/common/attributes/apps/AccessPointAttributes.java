package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;

/**
 * Stardust Attributes for Application- and Trigger-Accesspoints.
 *
 * @author Simon Nikles
 *
 */
public enum AccessPointAttributes {

	ROOT_ELEMENT("RootElement", Labels.accessPoint_root, null, "", null),
	PRIMITIVE_TYPE("carnot:engine:type", Labels.accessPoint_primitive_type, null, "", "org.eclipse.stardust.engine.core.pojo.data.Type"),
	SERIALIZABLE_CLASS_NAME("carnot:engine:className", Labels.accessPoint_serializable_className, null, "", null),
	STRUCTURED_DATA_TYPE("carnot:engine:dataType",Labels.accessPoint_structured_dataType, null, "", null),
	SEPARATOR("carnot:engine:path:separator", Labels.accessPoint_path_separator, null,  "/", null),
	BIDIRECTIONAL("carnot:engine:data:bidirectional", Labels.accessPoint_bidirectional, null, "true", "boolean"),
	;

	private String attributeName;
	private String label;
	private Map<String, String> choices;
	private String defaultVal;
	private String dataType;

	private AccessPointAttributes(String attributeName, String label, Map<String, String> choices, String defaultVal, String dataType) {
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
