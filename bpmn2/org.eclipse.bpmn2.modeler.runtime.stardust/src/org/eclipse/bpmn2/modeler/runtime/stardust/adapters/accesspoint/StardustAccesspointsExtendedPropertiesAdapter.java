package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes.BIDIRECTIONAL;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes.PRIMITIVE_TYPE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes.ROOT_ELEMENT;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes.SEPARATOR;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes.SERIALIZABLE_CLASS_NAME;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes.STRUCTURED_DATA_TYPE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.ACCESSPOINT_DEFAULT_VALUE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.ACCESSPOINT_LOCATION;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.camel.CamelAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.jms.JmsAppAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.webapp.WebAppAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.ApplicationTypes;
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
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(PRIMITIVE_TYPE.attributeName(), PRIMITIVE_TYPE.defaultVal(), PRIMITIVE_TYPE.dataType()));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(ROOT_ELEMENT.attributeName(), ""+sdAccessPoint.getId(), ROOT_ELEMENT.dataType()));
	}

	public void createSerializableTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(SERIALIZABLE_CLASS_NAME.attributeName(), SERIALIZABLE_CLASS_NAME.defaultVal(), SERIALIZABLE_CLASS_NAME.dataType()));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(ROOT_ELEMENT.attributeName(), ""+sdAccessPoint.getId(), ROOT_ELEMENT.dataType()));
	}

	public void createStructuredTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(STRUCTURED_DATA_TYPE.attributeName(), STRUCTURED_DATA_TYPE.defaultVal(), STRUCTURED_DATA_TYPE.dataType()));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(SEPARATOR.attributeName(), SEPARATOR.defaultVal(), SEPARATOR.dataType()));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(BIDIRECTIONAL.attributeName(), BIDIRECTIONAL.defaultVal(), BIDIRECTIONAL.dataType()));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(ROOT_ELEMENT.attributeName(), ""+sdAccessPoint.getId(), ROOT_ELEMENT.dataType()));
	}

	public void clearSubModels(StardustAccessPointType sdAccessPoint) {

		AttributeType[] ats = new AttributeType[] {
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, PRIMITIVE_TYPE.attributeName()),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, SERIALIZABLE_CLASS_NAME.attributeName()),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, STRUCTURED_DATA_TYPE.attributeName()),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, SEPARATOR.attributeName()),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, BIDIRECTIONAL.attributeName()),

			PropertyAdapterCommons.findAttributeType(sdAccessPoint, ACCESSPOINT_LOCATION.attributeName()),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, ACCESSPOINT_DEFAULT_VALUE.attributeName()),

			PropertyAdapterCommons.findAttributeType(sdAccessPoint, ROOT_ELEMENT.attributeName())
		};

		for (AttributeType at : ats) {
			if (null != at) sdAccessPoint.getAttribute().remove(at);
		}
	}

}
