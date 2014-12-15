package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CommonAttributes.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;

/**
 * Stardust Attributes for JMS Triggers.
 *
 * @author Simon Nikles
 *
 */
public enum ExternalWebAppAttributes {

	VISIBILITY(Visibility.NAME, Visibility.LABEL, Visibility.getChoices(), "Public", null),
	WEBAPP_URI("carnot:engine:ui:externalWebApp:uri", Labels.webapp_uri, null, "", null)
	;

	private String attributeName;
	private String label;
	private Map<String, String> choices;
	private String defaultVal;
	private String dataType;

	private ExternalWebAppAttributes(String attributeName, String label, Map<String, String> choices, String defaultVal, String dataType) {
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
