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

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;

/**
 *
 */
public class StardustInterfaceExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustInterfaceType> {
	private static long elementOid = 10000;
	private static long appTypeId = 0;
	private static Hashtable<String, Object> choices = null;
	
	public enum ApplicationTypes {
		WEBSERVICE("webservice", "Webservice D"),
		PLAINJAVA("plainJava", "Plain Java"),
		SPRINGBEAN("springBean", "Spring Bean"),
		SESSIONBEAN("sessionBean", "Session Bean"),
		CAMELCONSUMER("camelSpringConsumerApplication", "Camel Consumer"),
		CAMELPRODUCER("camelSpringProducerApplication", "Camel Producer"),
		JMS("jms", "JMS Application");
		
		private String key;
		public String displayName;

		private ApplicationTypes(String key, String displayName) {
			this.setKey(key);
			this.displayName = displayName;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public static ApplicationTypes forKey(String key) {
			if (null == key) return null;
			for (ApplicationTypes t : values()) {
				if (key.equals(t.key)) return t;
			}
			return null;
		}
		
	}

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public StardustInterfaceExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustInterfaceType object) {
		super(adapterFactory, object);
		
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
					createCamelApplicationModel(sdInterface, false);
					break;
				case CAMELPRODUCER:
					createCamelApplicationModel(sdInterface, true);
					break;
				case PLAINJAVA:
					createPlainJavaApplicationModel(sdInterface);
					break;
				case SPRINGBEAN:
					createSpringBeanApplicationModel(sdInterface);
					break;					
				case JMS:
				case SESSIONBEAN:
				default:
					removeApplicationModel(sdInterface);
					break;
				}
			}

			@Override
			public Hashtable<String, Object> getChoiceOfValues() {
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

	public static AttributeType createAttributeType(String name, String value, String type) {
		AttributeType at = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
		at.setName(name);
		at.setValue(value);
		if (type != null && !type.isEmpty())
			at.setType(type);
		return at;
	}


	private static StardustAccessPointType createStardustAccessPointType(long elementOid, String id, String name,
			DirectionType direction, String typeRef) {
		StardustAccessPointType ac = SdbpmnFactory.eINSTANCE.createStardustAccessPointType();
		ac.setElementOid(elementOid);
		ac.setId(id);
		ac.setName(name);
		ac.setDirection(direction);
		ac.setTypeRef(typeRef);
		return ac;
	}

	private static long generateElementOid() {
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
		sdApplication.setElementOid(generateElementOid());
		sdApplication.setId("CROServiceApp");
		sdApplication.setName("CROServiceApp");

		sdApplication.getAttribute().add(createAttributeType("carnot:engine:visibility", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:wsRuntime", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:wsdlUrl", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:wsServiceName", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:wsPortName", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:wsOperationName", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:wsSoapProtocol", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:className", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:browsable", "true", "boolean"));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:dataType", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:transformation", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:path:separator", "/", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:data:bidirectional", "true", "boolean"));

		StardustAccessPointType sdAccessPoint;

		sdAccessPoint = createStardustAccessPointType(generateElementOid(), "carnot:engine:endpointAddress", "Endpoint Address", DirectionType.IN_LITERAL, "serializable");
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:className", "", null));
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:browsable", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType(generateElementOid(), "parameters", "parameters", DirectionType.IN_LITERAL, "plainXML");
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:browsable", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType(generateElementOid(), "parameters", "parameters", DirectionType.OUT_LITERAL, "plainXML");
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType(generateElementOid(), "parameters_struct", "parameters_struct", DirectionType.IN_LITERAL, "struct");
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:dataType", "getCRO", null));
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:transformation", "DOM", null));
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:separator", "/", null));
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:bidirectional", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType(generateElementOid(), "parameters_struct", "parameters_struct", DirectionType.OUT_LITERAL, "struct");
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:dataType", "getCROResponse", null));
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:separator", "/", null));
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:bidirectional", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

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
		sdApplication.setElementOid(generateElementOid());
		sdApplication.setId("JavaApp_" + generateAppTypeId());
		sdApplication.setName("JavaApp");
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:visibility", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:className", "", null));		
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:methodName", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:constructorName", "", null));		
		sdApplication.getAttribute().add(createAttributeType("synchronous:retry:enable", "true", "boolean"));
		sdApplication.getAttribute().add(createAttributeType("synchronous:retry:number", "", null));
		sdApplication.getAttribute().add(createAttributeType("synchronous:retry:time", "", null));		
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
		sdApplication.setElementOid(generateElementOid());
		sdApplication.setId("SpringBean");
		sdApplication.setName("SpringBean");

		sdApplication.getAttribute().add(createAttributeType("carnot:engine:visibility", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:spring::beanId", "", null));		
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:className", "", null));		
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:methodName", "", null));		
		sdApplication.getAttribute().add(createAttributeType("synchronous:retry:enable", "true", "boolean"));
		
		sdInterface.setStardustApplication(sdApplication);
	}
	
	/**
	 * Creates the sdbpmn and carnot model object hierarchy for a CamelProducer
	 * ApplicationType
	 * 
	 * @param sdInterface the StardustInterfaceType object which is the
	 *            container for the model objects.
	 * @param camelProducer 
	 */
	private static void createCamelApplicationModel(StardustInterfaceType sdInterface, boolean camelProducer) {
		// first delete the previous StardustApplicationType
		removeApplicationModel(sdInterface);

		// and configure for a PlainJava StardustApplicationType
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		sdApplication.setElementOid(generateElementOid());
		if (camelProducer == true) {
			sdApplication.setId("CamelProducer");
			sdApplication.setName("CamelProducer");
		} else {
			sdApplication.setId("CamelConsumer");
			sdApplication.setName("CamelConsumer");
		}
		
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:visibility", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:camel::invocationType", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:camel::invocationPattern", "", null));
		sdApplication.getAttribute().add(createAttributeType("synchronous:retry:enable", "false", "boolean"));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:camel::camelContextId", "defaultCamelContext", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:camel::supportMultipleAccessPoints", "true", "boolean"));
		// GG supposed to be generated by stardust engine: sdApplication.getAttribute().add(createAttributeType("messageTransformation:TransformationProperty::", "", null));
		if (camelProducer == false) {
			sdApplication.getAttribute().add(createAttributeType("carnot:engine:camel::consumerRoute", "", null));
		}
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:camel::routeEntries", "", null));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:camel::processContextHeaders", "false", "boolean"));
		sdApplication.getAttribute().add(createAttributeType("carnot:engine:camel::outBodyAccessPoint", "", null));

		StardustAccessPointType sdAccessPoint;
		
		sdAccessPoint = createStardustAccessPointType(generateElementOid(), "parameters_struct", "parameters_struct", DirectionType.OUT_LITERAL, "struct");
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:type", "", null));
		sdAccessPoint.getAttribute().add(createAttributeType("RootElement", "", null));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType(generateElementOid(), "parameters_struct", "parameters_struct", DirectionType.IN_LITERAL, "struct");
		sdAccessPoint.getAttribute().add(createAttributeType("carnot:engine:type", "", null));
		sdAccessPoint.getAttribute().add(createAttributeType("RootElement", "", null));
		sdApplication.getAccessPoint().add(sdAccessPoint);
		
		sdInterface.setStardustApplication(sdApplication);
	}	

	private static void removeApplicationModel(StardustInterfaceType sdInterface) {
		sdInterface.setStardustApplication(null);
	}
}
