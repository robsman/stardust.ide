package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.CONSUMER_ROUTE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.CONTEXT_HEADERS;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.CONTEXT_ID;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.INVOCATION_PATTERN;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.INVOCATION_TYPE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.IN_BODY_ACCESSPOINTS;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.MULTIPLE_ACCESSPOINTS;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.OUT_BODY_ACCESSPOINTS;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.PRODUCER_ROUTE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.RETRY_ENABLE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.RETRY_INTERVAL;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.RETRY_NUMBER;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.SPRING_BEANS;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.TRANSACTED_ROUTE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes.VISIBILITY;

import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.StardustInterfaceExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.ApplicationTypes;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

/**
 * Initialization of Stardust Attributes for Camel applications.
 *
 * Moved and refactored from {@linkplain StardustInterfaceExtendedPropertiesAdapter}.
 *
 * @author Simon Nikles
 *
 */
public class CamelApplicationModelProvider extends ApplicationModelMaintainer {

	private enum InvocationPattern {

		RECEIVE("receive"),
		SEND("send"),
		SEND_RECEIVE("sendReceive");

		private String key;
		private InvocationPattern(String key) {
			this.key = key;
		}

		public String key() {
			return key;
		}
	}

	private static final String ID_PREFIX_SEND = "CamelProducerSend_";
	private static final String NAME_SEND = "CamelProducer";

	private static final String ID_PREFIX_SEND_RECEIVE = "CamelProducerSendReceive_";
	private static final String NAME_SEND_RECEIVE = "CamelProducerSendReceive";

	private static final String ID_PREFIX_RECEIVE = "CamelConsumer_";
	private static final String NAME_RECEIVE = "CamelConsumer";

	/**
	 * Creates the sdbpmn and carnot model object hierarchy for a CamelProducer/Consumer
	 * ApplicationType
	 *
	 * @param sdInterface the StardustInterfaceType object which is the
	 *            container for the model objects.
	 * @param camelAppType
	 */
	public static void createCamelApplicationModel(StardustInterfaceType sdInterface, ApplicationTypes camelAppType) {
		// first delete the previous StardustApplicationType
		removeApplicationModel(sdInterface);

		// and configure for a Camel StardustApplicationType
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		sdApplication.getAccessPoint1();
		//sdApplication.setElementOid(generateElementOid(sdInterface.eResource()));
		if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SEND)) {
			sdApplication.setId(ID_PREFIX_SEND + generateAppTypeId());
			sdApplication.setName(NAME_SEND);
		} else if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE)) {
			sdApplication.setId(ID_PREFIX_SEND_RECEIVE + generateAppTypeId());
			sdApplication.setName(NAME_SEND_RECEIVE);
		}
		else {
			sdApplication.setId(ID_PREFIX_RECEIVE + generateAppTypeId());
			sdApplication.setName(NAME_RECEIVE);
		}

		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(VISIBILITY.attributeName(), VISIBILITY.defaultVal(), VISIBILITY.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(INVOCATION_TYPE.attributeName(), INVOCATION_TYPE.defaultVal(), INVOCATION_TYPE.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(RETRY_ENABLE.attributeName(), RETRY_ENABLE.defaultVal(), RETRY_ENABLE.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(CONTEXT_ID.attributeName(), CONTEXT_ID.defaultVal(), CONTEXT_ID.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(MULTIPLE_ACCESSPOINTS.attributeName(), MULTIPLE_ACCESSPOINTS.defaultVal(), MULTIPLE_ACCESSPOINTS.dataType()));

		// GG supposed to be generated by stardust engine: sdApplication.getAttribute().add(createAttributeType("messageTransformation:TransformationProperty::", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(TRANSACTED_ROUTE.attributeName(), TRANSACTED_ROUTE.defaultVal(), TRANSACTED_ROUTE.dataType()));

		// Consumer Route is needed for Camel consumer and Camel producer!
		if (camelAppType.equals(ApplicationTypes.CAMELCONSUMER) || (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE))) {
			sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(CONSUMER_ROUTE.attributeName(), CONSUMER_ROUTE.defaultVal(), CONSUMER_ROUTE.dataType()));
		}
		if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SEND) || (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE))) {
			sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(CONTEXT_HEADERS.attributeName(), CONTEXT_HEADERS.defaultVal(), CONTEXT_HEADERS.dataType()));
			sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(PRODUCER_ROUTE.attributeName(), PRODUCER_ROUTE.defaultVal(), PRODUCER_ROUTE.dataType()));

		}
		String invocationPattern = "";
		if (camelAppType.equals(ApplicationTypes.CAMELCONSUMER)) invocationPattern = InvocationPattern.RECEIVE.key();
		else if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SEND)) invocationPattern = InvocationPattern.SEND.key();
		else if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE)) invocationPattern = InvocationPattern.SEND_RECEIVE.key();
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(INVOCATION_PATTERN.attributeName(), invocationPattern, INVOCATION_PATTERN.dataType()));

		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(IN_BODY_ACCESSPOINTS.attributeName(), IN_BODY_ACCESSPOINTS.defaultVal(), IN_BODY_ACCESSPOINTS.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(OUT_BODY_ACCESSPOINTS.attributeName(), OUT_BODY_ACCESSPOINTS.defaultVal(), OUT_BODY_ACCESSPOINTS.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(SPRING_BEANS.attributeName(), SPRING_BEANS.defaultVal(), SPRING_BEANS.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(RETRY_ENABLE.attributeName(), RETRY_ENABLE.defaultVal(), RETRY_ENABLE.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(RETRY_NUMBER.attributeName(), RETRY_NUMBER.defaultVal(), RETRY_NUMBER.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(RETRY_INTERVAL.attributeName(), RETRY_INTERVAL.defaultVal(), RETRY_INTERVAL.dataType()));

		sdInterface.setStardustApplication(sdApplication);
	}

}
