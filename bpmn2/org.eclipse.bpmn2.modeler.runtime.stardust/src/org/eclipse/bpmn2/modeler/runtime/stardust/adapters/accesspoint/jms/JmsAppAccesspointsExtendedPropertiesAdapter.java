package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.jms;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.ACCESSPOINT_DEFAULT_VALUE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.ACCESSPOINT_LOCATION;

import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.StardustAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.StardustAccesspointsExtendedPropertiesAdapter.StardustAccesspointsFeatureDescriptorFactory;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AcessPointDataTypes;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;

/**
 * @author Simon Nikles
 *
 */
public enum JmsAppAccesspointsExtendedPropertiesAdapter implements StardustAccesspointsFeatureDescriptorFactory {

	INSTANCE;

	@Override
	public FeatureDescriptor<StardustAccessPointType> createFeatureDescriptor(
			final StardustAccesspointsExtendedPropertiesAdapter accessPointPropertyAdapter,
			StardustAccessPointType object, EStructuralFeature feature) {

		return new FeatureDescriptor<StardustAccessPointType>(accessPointPropertyAdapter,object,feature) {
			@Override
			protected void internalSet(StardustAccessPointType sdAccessPoint, EStructuralFeature feature, Object value, int index) {

				super.internalSet(object, feature, value, index);

				if (null == value) {
					accessPointPropertyAdapter.clearSubModels(sdAccessPoint);
					return;
				}

				createJmsSerializableTypeModel(sdAccessPoint);

				AcessPointDataTypes apType = AcessPointDataTypes.forKey(value.toString());
				accessPointPropertyAdapter.clearSubModels(sdAccessPoint);
				switch(apType) {
				case SERIALIZABLE_TYPE:
					accessPointPropertyAdapter.createSerializableTypeModel(sdAccessPoint);
					break;
				default: return;
				}
			}
		};
	}

	public void createJmsSerializableTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(ACCESSPOINT_LOCATION.attributeName(), ACCESSPOINT_LOCATION.defaultVal(), ACCESSPOINT_LOCATION.dataType()));
		if (DirectionType.IN_LITERAL.equals(sdAccessPoint.getDirection())) {
			sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType(ACCESSPOINT_DEFAULT_VALUE.attributeName(), ACCESSPOINT_DEFAULT_VALUE.defaultVal(), ACCESSPOINT_DEFAULT_VALUE.dataType()));
		}
	}

}
