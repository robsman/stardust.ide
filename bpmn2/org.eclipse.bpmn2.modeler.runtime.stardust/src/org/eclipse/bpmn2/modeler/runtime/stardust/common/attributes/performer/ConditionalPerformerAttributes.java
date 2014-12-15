package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.performer;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ResourceDataMappingAttributeNames.REALM_DATA;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ResourceDataMappingAttributeNames.REALM_DATA_PATH;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.performer.ConditionalPerformerResultEnum;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CommonAttributes.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;

public enum ConditionalPerformerAttributes {

	VISIBILITY(Visibility.NAME, Visibility.LABEL, Visibility.getChoices(), Visibility.PUBLIC.getKey(), null),
	PERFORMER_KIND("carnot:engine:conditionalPerformer:kind", Labels.conditionalPerformerKind, ConditionalPerformerResultEnum.getChoices(), "", null),
	USER_REALM_DATA(REALM_DATA.attributeName(), REALM_DATA.label(), null, "", null),
	USER_REALM_DATA_PATH(REALM_DATA_PATH.attributeName(), REALM_DATA_PATH.label(), null, "", null)

	;

	private String attributeName;
	private String label;
	private Map<String, String> choices;
	private String defaultVal;
	private String dataType;

	private ConditionalPerformerAttributes(String attributeName, String label, Map<String, String> choices, String defaultVal, String dataType) {
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
