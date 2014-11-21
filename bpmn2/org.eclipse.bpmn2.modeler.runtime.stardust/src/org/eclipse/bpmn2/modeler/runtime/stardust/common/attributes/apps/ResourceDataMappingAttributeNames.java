package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;

public enum ResourceDataMappingAttributeNames {

	DATA("data", "", Labels.resource_data),
	DATA_PATH("dataPath", "", Labels.resource_dataPath),
	REALM_DATA("realmData", "carnot:engine:conditionalPerformer:realmData", Labels.resource_realmData),
	REALM_DATA_PATH("realmDataPath", "carnot:engine:conditionalPerformer:realmDataPath", Labels.resource_realmDataPath);

	private String internalName;
	private String attributeName;
	private String label;

	private ResourceDataMappingAttributeNames(String internalName, String attributeName, String label) {
		this.internalName = internalName;
		this.attributeName = attributeName;
		this.label = label;
	}

	public String internalName() {
		return internalName;
	}

	public String attributeName() {
		return attributeName;
	}

	public String label() {
		return label;
	}

	public boolean equalsInternalName(String name) {
		return internalName().equals(name);
	}
}
