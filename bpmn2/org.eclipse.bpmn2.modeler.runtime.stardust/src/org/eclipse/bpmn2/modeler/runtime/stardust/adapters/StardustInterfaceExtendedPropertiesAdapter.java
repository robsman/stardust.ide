/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013, 2014 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.runtime.stardust.adapters;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.ApplicationTypes;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.common.PropertyCommons.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsDirectionEnum;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationCleaner;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;

/**
 *
 */
public class StardustInterfaceExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustInterfaceType> {
	private static Long elementOid = null;
	private static long appTypeId = 1;
	private static Hashtable<String, Object> choices = null;
	private StardustTriggerInterfaceExtendedPropertiesAdapter triggerTypeAdapter;

	private StardustInterfaceType sdInterface;

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public StardustInterfaceExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustInterfaceType object) {
		super(adapterFactory, object);
		sdInterface = object;
System.out
		.println("############################# StardustInterfaceExtendedPropertiesAdapter.StardustInterfaceExtendedPropertiesAdapter() " + object);

		triggerTypeAdapter = new StardustTriggerInterfaceExtendedPropertiesAdapter(adapterFactory, object);

		EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getStardustInterfaceType_ApplicationType();

		// this allows the user to select "null" for the Application Type
		setProperty(feature, UI_CAN_SET_NULL, Boolean.TRUE);
		// this tells the UI framework that this feature is a MultiChoice item
		// which should be rendered as a ComboBox
		setProperty(feature, UI_IS_MULTI_CHOICE, Boolean.TRUE);

		setFeatureDescriptor(feature,
				new FeatureDescriptor<StardustInterfaceType>(this,object,feature) {
			@Override
			protected void internalSet(StardustInterfaceType sdInterface, EStructuralFeature feature, Object value, int index) {
				// Whenever the StardustInterfaceType.applicationType feature changes,
				// the framework will call this FeatureDescriptor's internalSet() inside
				// an EMF transaction. This gives us the opportunity to construct all of
				// the required objects for a specific ApplicationType within the same
				// transaction as the one that changed the ApplicationType.
				super.internalSet(object, feature, value, index);

				if (null == sdInterface.getStardustApplication() && null != sdInterface.getStardustTrigger()) {
					triggerTypeAdapter.internalSetApplicationType(sdInterface, feature, value, index);
					return;
				}

				if (null == value) {
					removeApplicationModel(sdInterface);
					return;
				}
				ApplicationTypes appType = ApplicationTypes.forKey(value.toString());
				switch(appType) {
				case WEBSERVICE:
					createWebServiceApplicationModel(sdInterface);
					break;
				case CAMELCONSUMER:
					createCamelApplicationModel(sdInterface, appType);
					break;
				case CAMELPRODUCER_SEND:
					createCamelApplicationModel(sdInterface, appType);
					break;
				case CAMELPRODUCER_SENDRECEIVE:
					createCamelApplicationModel(sdInterface, appType);
					break;
				case PLAINJAVA:
					createPlainJavaApplicationModel(sdInterface);
					break;
				case SPRINGBEAN:
					createSpringBeanApplicationModel(sdInterface);
					break;
				case EXTERNAL_WEBAPP:
					createExternalWebappApplicationModel(sdInterface);
					break;
				case JMS:
					createJmsApplicationModel(sdInterface);
					break;
				case SESSIONBEAN:
				default:
					removeApplicationModel(sdInterface);
					break;
				}
			}

			@Override
			public Hashtable<String, Object> getChoiceOfValues() {
				if (null == sdInterface.getStardustApplication() && null != sdInterface.getStardustTrigger()) {
					return triggerTypeAdapter.getChoiceOfValues();
				}
				if (choices==null) {
					choices = new Hashtable<String, Object>();
					for (ApplicationTypes type : ApplicationTypes.values()) {
						choices.put(type.getDisplayName(), type.getKey());
					}
				}
				return choices;
			}
		});
	}

	private static Long maxAppOid(Resource resource) {
		Definitions definitions = ModelUtil.getDefinitions(resource);
		Long maxOid = Long.valueOf(0);
		List<Interface> ifaces = ModelUtil.getAllRootElements(definitions, Interface.class);
		for (Interface iface : ifaces) {
			List<StardustApplicationType> applications = ModelDecorator.getAllExtensionAttributeValues(iface, StardustApplicationType.class);
			if (null != applications && applications.size() > 0) {
				Long appOid = applications.get(0).getElementOid();
				if (null != appOid) maxOid = Math.max(appOid, maxOid);
			}
		}
		return maxOid;
	}

	private static StardustAccessPointType createStardustAccessPointType(long elementOid, String id, String name, DirectionType direction, String typeRef) {
		StardustAccessPointType ac = SdbpmnFactory.eINSTANCE.createStardustAccessPointType();
		ac.setElementOid(elementOid);
		ac.setId(id);
		ac.setName(name);
		ac.setDirection(direction);
		ac.setTypeRef(typeRef);
		return ac;
	}

	private static long generateElementOid(Resource resource) {
		if (null == elementOid) elementOid = maxAppOid(resource)+1;
		return elementOid++;
	}

	private static long generateAppTypeId() {
		return appTypeId++;
	}


	/**
	 * Creates the sdbpmn and carnot model object hierarchy for a WebService ApplicationType
	 *
	 * @param sdInterface the StardustInterfaceType object which is the
	 *            container for the model objects.
	 */
	private static void createWebServiceApplicationModel(StardustInterfaceType sdInterface) {
		// first delete the previous StardustApplicationType
		removeApplicationModel(sdInterface);

		// and configure for a WebService StardustApplicationType
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		sdApplication.setElementOid(generateElementOid(sdApplication.eResource()));
		sdApplication.setId("WebServiceApp_" + generateAppTypeId());
		sdApplication.setName("WebServiceApp");

		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(Visibility.NAME, "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:wsRuntime", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:wsdlUrl", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:wsServiceName", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:wsPortName", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:wsOperationName", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:wsSoapProtocol", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:className", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:browsable", "true", "boolean"));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:dataType", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:transformation", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:path:separator", "/", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:data:bidirectional", "true", "boolean"));

		StardustAccessPointType sdAccessPoint;

		sdAccessPoint = createStardustAccessPointType(generateElementOid(sdApplication.eResource()), "carnot:engine:endpointAddress", "Endpoint Address", DirectionType.IN_LITERAL, "serializable");
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:className", "", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:browsable", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType(generateElementOid(sdApplication.eResource()), "parameters", "parameters", DirectionType.IN_LITERAL, "plainXML");
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:browsable", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType(generateElementOid(sdApplication.eResource()), "parameters", "parameters", DirectionType.OUT_LITERAL, "plainXML");
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType(generateElementOid(sdApplication.eResource()), "parameters_struct", "parameters_struct", DirectionType.IN_LITERAL, "struct");
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:dataType", "getCRO", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:transformation", "DOM", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:separator", "/", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:bidirectional", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType(generateElementOid(sdApplication.eResource()), "parameters_struct", "parameters_struct", DirectionType.OUT_LITERAL, "struct");
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:dataType", "getCROResponse", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:separator", "/", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:bidirectional", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdInterface.setStardustApplication(sdApplication);
	}

	private static void createExternalWebappApplicationModel(StardustInterfaceType sdInterface) {
		removeApplicationModel(sdInterface);

		// and configure for a WebService StardustApplicationType
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		StardustContextType contextType = SdbpmnFactory.eINSTANCE.createStardustContextType();
		contextType.setTypeRef(ApplicationTypes.EXTERNAL_WEBAPP.getKey());
		sdApplication.getContext1().add(contextType);
		long appNr = generateAppTypeId();
		sdApplication.setElementOid(generateElementOid(sdApplication.eResource()));
		sdApplication.setId("ExternalWebApplicationApp_" + appNr);
		sdApplication.setName("ExternalWebApplicationApp " + appNr);
		sdApplication.setInteractive(true);

		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(Visibility.NAME, "", null));
		contextType.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:ui:externalWebApp:uri", "", null));

		sdInterface.setStardustApplication(sdApplication);
	}

	/**
	 * Creates the sdbpmn and carnot model object hierarchy for a PlainJava
	 * ApplicationType
	 *
	 * @param sdInterface the StardustInterfaceType object which is the
	 *            container for the model objects.
	 */
	private static void createPlainJavaApplicationModel(StardustInterfaceType sdInterface) {
		// first delete the previous StardustApplicationType
		removeApplicationModel(sdInterface);

		// and configure for a PlainJava StardustApplicationType
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		sdApplication.setElementOid(generateElementOid(sdApplication.eResource()));
		sdApplication.setId("JavaApp_" + generateAppTypeId());
		sdApplication.setName("JavaApp");
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(Visibility.NAME, "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:className", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:methodName", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:constructorName", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:enable", "true", "boolean"));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:number", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:time", "", null));
		sdInterface.setStardustApplication(sdApplication);
	}

	/**
	 * Creates the sdbpmn and carnot model object hierarchy for a SpringBean
	 * ApplicationType
	 *
	 * @param sdInterface the StardustInterfaceType object which is the
	 *            container for the model objects.
	 */
	private static void createSpringBeanApplicationModel(StardustInterfaceType sdInterface) {
		// first delete the previous StardustApplicationType
		removeApplicationModel(sdInterface);

		// and configure for a PlainJava StardustApplicationType
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		sdApplication.setElementOid(generateElementOid(sdApplication.eResource()));
		sdApplication.setId("SpringBean_" + generateAppTypeId());
		sdApplication.setName("SpringBean");

		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(Visibility.NAME, "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:spring::beanId", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:className", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:methodName", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:enable", "true", "boolean"));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:number", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:time", "", null));

		sdInterface.setStardustApplication(sdApplication);
	}

	/**
	 * Creates the sdbpmn and carnot model object hierarchy for a CamelProducer/Consumer
	 * ApplicationType
	 *
	 * @param sdInterface the StardustInterfaceType object which is the
	 *            container for the model objects.
	 * @param camelAppType
	 */
	private static void createCamelApplicationModel(StardustInterfaceType sdInterface, ApplicationTypes camelAppType) {
		// first delete the previous StardustApplicationType
		removeApplicationModel(sdInterface);

		// and configure for a Camel StardustApplicationType
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		sdApplication.getAccessPoint1();
		sdApplication.setElementOid(generateElementOid(sdInterface.eResource()));
		if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SEND)) {
			sdApplication.setId("CamelProducerSend_" + generateAppTypeId());
			sdApplication.setName("CamelProducerSend");
		} else if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE)) {
			sdApplication.setId("CamelProducerSendReceive_" + generateAppTypeId());
			sdApplication.setName("CamelProducerSendReceive");
		}
		else {
			sdApplication.setId("CamelConsumer_" + generateAppTypeId());
			sdApplication.setName("CamelConsumer");
		}

		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(Visibility.NAME, "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::invocationType", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:enable", "false", "boolean"));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::camelContextId", "defaultCamelContext", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::supportMultipleAccessPoints", "true", "boolean"));
		// GG supposed to be generated by stardust engine: sdApplication.getAttribute().add(createAttributeType("messageTransformation:TransformationProperty::", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::transactedRoute", "true", "boolean"));
		// Consumer Route is needed for Camel consumer and Camel producer!
		if (camelAppType.equals(ApplicationTypes.CAMELCONSUMER) || (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE))) {
			sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::consumerRoute", "", null));
		}
		if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SEND) || (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE))) {
			sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::processContextHeaders", "false", "boolean"));
			sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::routeEntries", "", null));

		}
		String invocationPattern = "";
		if (camelAppType.equals(ApplicationTypes.CAMELCONSUMER)) invocationPattern = "receive";
		else if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SEND)) invocationPattern = "send";
		else if (camelAppType.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE)) invocationPattern = "sendReceive";
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::invocationPattern", invocationPattern, null));

		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::inBodyAccessPoint", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::outBodyAccessPoint", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:camel::additionalSpringBeanDefinitions", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:enable", "true", "boolean"));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:number", "", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("synchronous:retry:time", "", null));

		sdInterface.setStardustApplication(sdApplication);
	}

	private void createJmsApplicationModel(StardustInterfaceType sdInterface) {
		removeApplicationModel(sdInterface);
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		sdApplication.setElementOid(generateElementOid(sdInterface.eResource()));
		long appTypeId = generateAppTypeId();
		sdApplication.setId("JavaMessagingService_" + appTypeId);
		sdApplication.setName("JavaMessagingService " + appTypeId);
		sdApplication.getAccessPoint1();
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:visibility", "Public", null));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:type", "", "org.eclipse.stardust.engine.extensions.jms.app.JMSDirection"));

		sdInterface.setStardustApplication(sdApplication);
	}

	public static void updateJmsApplicationModel(StardustInterfaceType sdInterface, JmsDirectionEnum newDirectionValue) {

		StardustApplicationType sdApplication = sdInterface.getStardustApplication();

		AttributeType at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:requestMessageType");
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:responseMessageType");
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:messageProvider");
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:messageAcceptor");
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:queueConnectionFactory.jndiName");
		if (null != at) sdApplication.getAttribute().remove(at);
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:queue.jndiName");
		if (null != at) sdApplication.getAttribute().remove(at);

		sdApplication.getAccessPoint1().clear();

		if (null != newDirectionValue) {
			if (JmsDirectionEnum.OUT.equals(newDirectionValue) || JmsDirectionEnum.INOUT.equals(newDirectionValue)) {
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:queueConnectionFactory.jndiName", "jms/CarnotXAConnectionFactory", null));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:queue.jndiName", "jms/CarnotApplicationQueue", null));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:messageProvider", "org.eclipse.stardust.engine.extensions.jms.app.DefaultMessageProvider", null));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:requestMessageType", "Map", "org.eclipse.stardust.engine.extensions.jms.app.MessageType"));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:includeOidHeaders", "false", "boolean"));
			}
			if (JmsDirectionEnum.IN.equals(newDirectionValue) || JmsDirectionEnum.INOUT.equals(newDirectionValue)) {
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:messageAcceptor", "org.eclipse.stardust.engine.extensions.jms.app.DefaultMessageAcceptor", null));
				sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:responseMessageType", "Map", "org.eclipse.stardust.engine.extensions.jms.app.MessageType"));
			}
		}
	}

	private static void removeApplicationModel(StardustInterfaceType sdInterface) {
		boolean recreateEmptyApp = null != sdInterface.getStardustApplication();
		StardustApplicationConfigurationCleaner.INSTANCE.performResetExistingApp(sdInterface);
		if (recreateEmptyApp) {
			sdInterface.setStardustApplication(SdbpmnFactory.eINSTANCE.createStardustApplicationType());
		} else {
			sdInterface.setStardustApplication(null);
		}
	}
}
