package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.camel.CamelAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.jms.JmsAppAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.webapp.WebAppAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.ApplicationTypes;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsLocationEnum;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.trigger.TriggerAppTypeEnum;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;

/**
 * @author Simon Nikles
 *
 */
public class StardustAccesspointsExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustAccessPointType> {

	public static interface StardustAccesspointsFeatureDescriptorFactory {
		public FeatureDescriptor<StardustAccessPointType> createFeatureDescriptor(
				final StardustAccesspointsExtendedPropertiesAdapter accessPointPropertyAdapter,
				StardustAccessPointType object, EStructuralFeature feature);
	}

	public StardustAccesspointsExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustAccessPointType object) {
		super(adapterFactory, object);

		final EStructuralFeature nameFeature = CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name();
		final EStructuralFeature idFeature = CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id();

		setFeatureDescriptor(nameFeature, new FeatureDescriptor<StardustAccessPointType>(this, object, nameFeature) {

			@Override
			public void internalSet(StardustAccessPointType object, EStructuralFeature feature, Object value, int index) {
				super.internalSet(object, nameFeature, value, index);
			}
			protected void internalPostSet(Object value) {
				object.eSet(idFeature, value.toString());
			}
		});

		final EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getStardustAccessPointType_TypeRef();
		setProperty(feature, UI_CAN_SET_NULL, Boolean.TRUE);
		setProperty(feature, UI_IS_MULTI_CHOICE, Boolean.TRUE);

		if (null == object) {
			return;
		}
		final EObject eContainer = object.eContainer();
		if (null != eContainer) {
			StardustInterfaceType sdInterface = null;
			if (eContainer instanceof StardustApplicationType) {
				final StardustApplicationType appType = (StardustApplicationType)eContainer;
				if (null == appType.eContainer()) {
					clearSubModels(object);
					return;
				}
				sdInterface = (StardustInterfaceType)appType.eContainer();
			} else if (eContainer instanceof StardustTriggerType) {
				final StardustTriggerType trigger = (StardustTriggerType)eContainer;
				if (null == trigger.eContainer()) {
					clearSubModels(object);
					return;
				}
				sdInterface = (StardustInterfaceType)trigger.eContainer();
			}
			if (null == sdInterface) return;

			final String appTypeId = sdInterface.getApplicationType();
			final ApplicationTypes applicationType = ApplicationTypes.forKey(appTypeId);
			final TriggerAppTypeEnum triggerType = TriggerAppTypeEnum.forKey(appTypeId);

			FeatureDescriptor<StardustAccessPointType> featureDesc = null;

			if (null != applicationType) {
				switch (applicationType) {
				case CAMELCONSUMER:
				case CAMELPRODUCER_SEND:
				case CAMELPRODUCER_SENDRECEIVE:
					featureDesc = CamelAccesspointsExtendedPropertiesAdapter.INSTANCE.createFeatureDescriptor(this, object, feature);
					break;
				case JMS:
					featureDesc = JmsAppAccesspointsExtendedPropertiesAdapter.INSTANCE.createFeatureDescriptor(this, object, feature);
					break;
				case EXTERNAL_WEBAPP:
					featureDesc = WebAppAccesspointsExtendedPropertiesAdapter.INSTANCE.createFeatureDescriptor(this, object, feature);
					break;
				default:
					break;
				}
			} else if (null != triggerType) {
				switch (triggerType) {
				case JMS:
					featureDesc = JmsAppAccesspointsExtendedPropertiesAdapter.INSTANCE.createFeatureDescriptor(this, object, feature);
					break;
				default:
					break;
				}

			}
			if (null != featureDesc) setFeatureDescriptor(feature, featureDesc);
		}

	}

	public void createPrimitiveTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:type", "", "org.eclipse.stardust.engine.core.pojo.data.Type"));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
	}

	public void createSerializableTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:className", "", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
	}

	public void createStructuredTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:dataType", "", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:path:separator", "/", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:data:bidirectional", "true", "boolean"));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
	}

	public void clearSubModels(StardustAccessPointType sdAccessPoint) {

		AttributeType[] ats = new AttributeType[] {
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:type"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:className"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:dataType"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:path:separator"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:data:bidirectional"),

			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:jms.location"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:defaultValue"),

			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "RootElement")
		};

		for (AttributeType at : ats) {
			if (null != at) sdAccessPoint.getAttribute().remove(at);
		}
	}

}
