package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SynchronousApplicationsCommonAttributes.SYNCH_RETRY_ENABLE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SynchronousApplicationsCommonAttributes.SYNCH_RETRY_NUMBER;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SynchronousApplicationsCommonAttributes.SYNCH_RETRY_TIME;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CommonAttributes.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;

/**
 * Stardust Attributes for Camel Applications.
 *
 * @author Simon Nikles
 *
 */
public enum CamelAttributes {

	INVOCATION_TYPE("carnot:engine:camel::invocationType", Labels.camel_invocationType, new String[] { "Synchronous", "Asynchronous"}, "", null),
	INVOCATION_PATTERN("carnot:engine:camel::invocationPattern", Labels.camel_invocationPattern, null, "", null),
	CONTEXT_ID("carnot:engine:camel::camelContextId", Labels.camel_camelContextId, null, "defaultCamelContext", null),
	VISIBILITY(Visibility.NAME, Visibility.LABEL, Visibility.getOptionKeys(), "", null),
	MULTIPLE_ACCESSPOINTS("carnot:engine:camel::supportMultipleAccessPoints", Labels.camel_supportMultipleAccessPoints, null, "true", "boolean"),
	TRANSACTED_ROUTE("carnot:engine:camel::transactedRoute", Labels.camel_transactedRoute, null, "true", "boolean"),
	CONSUMER_ROUTE("carnot:engine:camel::consumerRoute", Labels.camel_consumerRoute, null, "", null),
	CONTEXT_HEADERS("carnot:engine:camel::processContextHeaders", Labels.camel_processContextHeaders, null, "false", "boolean"),
	PRODUCER_ROUTE("carnot:engine:camel::routeEntries", Labels.camel_routeEntries, null, "", null),
	IN_BODY_ACCESSPOINTS("carnot:engine:camel::inBodyAccessPoint", Labels.camel_inBodyAccessPoint, null, "", null),
	OUT_BODY_ACCESSPOINTS("carnot:engine:camel::outBodyAccessPoint", Labels.camel_outBodyAccessPoint, null, "", null),
	SPRING_BEANS("carnot:engine:camel::additionalSpringBeanDefinitions", Labels.camel_additionalSpringBeanDefinitions, null, "", null),
	RETRY_ENABLE(SYNCH_RETRY_ENABLE.attributeName(), SYNCH_RETRY_ENABLE.label(), null, SYNCH_RETRY_ENABLE.defaultVal(), SYNCH_RETRY_ENABLE.dataType()),
	RETRY_NUMBER(SYNCH_RETRY_NUMBER.attributeName(), SYNCH_RETRY_NUMBER.label(), null, SYNCH_RETRY_NUMBER.defaultVal(), SYNCH_RETRY_NUMBER.dataType()),
	RETRY_INTERVAL(SYNCH_RETRY_TIME.attributeName(), SYNCH_RETRY_TIME.label(), null, SYNCH_RETRY_TIME.defaultVal(), SYNCH_RETRY_TIME.dataType())
	;

	private String attributeName;
	private String label;
	private String[] optionKeys;
	private String defaultVal;
	private String dataType;

	private CamelAttributes(String attributeName, String label, String[] optionKeys, String defaultVal, String dataType) {
		this.attributeName = attributeName;
		this.label = label;
		this.optionKeys = optionKeys;
		this.defaultVal = defaultVal;
		this.dataType = dataType;
	}

	public String attributeName() {
		return attributeName;
	}

	public String label() {
		return label;
	}

	public String[] optionKeys() {
		return optionKeys;
	}

	public String defaultVal() {
		return defaultVal;
	}

	public String dataType() {
		return dataType;
	}

}
