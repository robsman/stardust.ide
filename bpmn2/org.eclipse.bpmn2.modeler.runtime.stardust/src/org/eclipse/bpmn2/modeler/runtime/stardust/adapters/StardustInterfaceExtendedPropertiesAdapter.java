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

import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.ApplicationModelMaintainer.generateAppTypeId;
import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.ApplicationModelMaintainer.removeApplicationModel;
import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.CamelApplicationModelProvider.createCamelApplicationModel;
import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.ExternalWebAppModelProvider.createExternalWebappApplicationModel;
import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.JMSApplicationModelProvider.createJmsApplicationModel;
import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.PlainJavaModelProvider.createPlainJavaApplicationModel;
import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.SpringBeanApplicationModelProvider.createSpringBeanApplicationModel;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CommonAttributes.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.ApplicationTypes;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;

public class StardustInterfaceExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustInterfaceType> {
	private static Long elementOid = null;
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

	private static StardustAccessPointType createStardustAccessPointType(String id, String name, DirectionType direction, String typeRef) {
		StardustAccessPointType ac = SdbpmnFactory.eINSTANCE.createStardustAccessPointType();
		ac.setId(id);
		ac.setName(name);
		ac.setDirection(direction);
		ac.setTypeRef(typeRef);
		return ac;
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
		//sdApplication.setElementOid(generateElementOid(sdApplication.eResource()));
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

		sdAccessPoint = createStardustAccessPointType("carnot:engine:endpointAddress", "Endpoint Address", DirectionType.IN_LITERAL, "serializable");
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:className", "", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:browsable", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType("parameters", "parameters", DirectionType.IN_LITERAL, "plainXML");
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:browsable", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType("parameters", "parameters", DirectionType.OUT_LITERAL, "plainXML");
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType("parameters_struct", "parameters_struct", DirectionType.IN_LITERAL, "struct");
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:dataType", "getCRO", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:transformation", "DOM", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:separator", "/", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:bidirectional", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdAccessPoint = createStardustAccessPointType("parameters_struct", "parameters_struct", DirectionType.OUT_LITERAL, "struct");
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:dataType", "getCROResponse", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:separator", "/", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:bidirectional", "true", "boolean"));
		sdApplication.getAccessPoint().add(sdAccessPoint);

		sdInterface.setStardustApplication(sdApplication);
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

	/**
	 * Currently oids are not set in the modeler but after the transformation.
	 * According to a discussion with sungard, identification relies on the id,
	 * the oid is required for legacy reasons but no problems are expected with
	 * respect to versions of models (if the oid differs between versions).
	 */
	@SuppressWarnings("unused")
	private static long generateElementOid(Resource resource) {
		if (null == elementOid) elementOid = maxAppOid(resource)+1;
		return elementOid++;
	}

}
