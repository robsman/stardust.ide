package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SynchronousApplicationsCommonAttributes.SYNCH_RETRY_ENABLE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SynchronousApplicationsCommonAttributes.SYNCH_RETRY_NUMBER;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SynchronousApplicationsCommonAttributes.SYNCH_RETRY_TIME;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CommonAttributes.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;

/**
 * Stardust Attributes for Plain Java Application Types.
 *
 * @author Simon Nikles
 *
 */
public enum PlainJavaAppAttributes {

	VISIBILITY(Visibility.NAME, Visibility.LABEL, Visibility.getChoices(), "Public", null),

	CLASS_NAME("carnot:engine:className", Labels.java_className, null, "", null),
	METHOD_NAME("carnot:engine:methodName", Labels.java_methodName, null, "", null),
	CONSTRUCTOR_NAME("carnot:engine:constructorName", Labels.java_constructorName, null, "", null),
	RETRY_ENABLE(SYNCH_RETRY_ENABLE.attributeName(), SYNCH_RETRY_ENABLE.label(), SYNCH_RETRY_ENABLE.choices(), SYNCH_RETRY_ENABLE.defaultVal(), SYNCH_RETRY_ENABLE.dataType()),
	RETRY_NUMBER(SYNCH_RETRY_NUMBER.attributeName(), SYNCH_RETRY_NUMBER.label(), SYNCH_RETRY_NUMBER.choices(), SYNCH_RETRY_NUMBER.defaultVal(), SYNCH_RETRY_NUMBER.dataType()),
	RETRY_INTERVAL(SYNCH_RETRY_TIME.attributeName(), SYNCH_RETRY_TIME.label(), SYNCH_RETRY_TIME.choices(), SYNCH_RETRY_TIME.defaultVal(), SYNCH_RETRY_TIME.dataType())
	;

	private String attributeName;
	private String label;
	private Map<String, String> choices;
	private String defaultVal;
	private String dataType;

	private PlainJavaAppAttributes(String attributeName, String label, Map<String, String> choices, String defaultVal, String dataType) {
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
