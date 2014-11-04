package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.jms;

import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.StardustAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.StardustAccesspointsExtendedPropertiesAdapter.StardustAccesspointsFeatureDescriptorFactory;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AcessPointDataTypes;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsLocationEnum;
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
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:jms.location", JmsLocationEnum.HEADER.getKey(), "org.eclipse.stardust.engine.extensions.jms.app.JMSLocation"));
		if (DirectionType.IN_LITERAL.equals(sdAccessPoint.getDirection())) {
			sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:defaultValue", "", null));
		}
	}

}
