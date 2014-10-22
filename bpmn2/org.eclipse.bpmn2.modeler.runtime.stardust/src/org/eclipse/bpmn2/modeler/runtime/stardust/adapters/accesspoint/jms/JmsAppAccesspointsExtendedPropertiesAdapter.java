package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.jms;

import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.StardustAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.StardustAccesspointsExtendedPropertiesAdapter.StardustAccesspointsFeatureDescriptorFactory;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AcessPointDataTypes;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;

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
				AcessPointDataTypes apType = AcessPointDataTypes.forKey(value.toString());
				accessPointPropertyAdapter.clearSubModels(sdAccessPoint);
				switch(apType) {
				case SERIALIZABLE_TYPE:
					accessPointPropertyAdapter.createSerializableTypeModel(sdAccessPoint);
					sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:jms.location", "HEADER", "org.eclipse.stardust.engine.extensions.jms.app.JMSLocation"));
					break;
				default: return;
				}
			}
		};
	}


}
