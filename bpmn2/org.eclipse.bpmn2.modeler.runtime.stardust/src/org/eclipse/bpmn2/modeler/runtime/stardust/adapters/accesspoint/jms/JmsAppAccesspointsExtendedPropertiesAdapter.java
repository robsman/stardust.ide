package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.jms;

import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.AccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AcessPointDataTypes;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;

/**
 * @author Simon Nikles
 *
 */
public class JmsAppAccesspointsExtendedPropertiesAdapter extends AccesspointsExtendedPropertiesAdapter {

	public JmsAppAccesspointsExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustAccessPointType object) {
		super(adapterFactory, object);

		EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getStardustAccessPointType_TypeRef();

		setProperty(feature, UI_CAN_SET_NULL, Boolean.TRUE);
		setProperty(feature, UI_IS_MULTI_CHOICE, Boolean.TRUE);

		setFeatureDescriptor(feature,
				new FeatureDescriptor<StardustAccessPointType>(this,object,feature) {
			@Override
			protected void internalSet(StardustAccessPointType sdAccessPoint, EStructuralFeature feature, Object value, int index) {

				super.internalSet(object, feature, value, index);

				if (null == value) {
					clearSubModels(sdAccessPoint);
					return;
				}
				AcessPointDataTypes apType = AcessPointDataTypes.forKey(value.toString());
				clearSubModels(sdAccessPoint);
				switch(apType) {
				case SERIALIZABLE_TYPE:
					createSerializableTypeModel(sdAccessPoint);
					break;
				default: return;
				} 
			}
		});
	}

	@Override
	protected void createSerializableTypeModel(StardustAccessPointType sdAccessPoint) {
		super.createSerializableTypeModel(sdAccessPoint);
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:jms.location", "HEADER", "org.eclipse.stardust.engine.extensions.jms.app.JMSLocation"));
	}
	
}
