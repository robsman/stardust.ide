package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.camel;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.camel.accesspoint.CamelAcessPointDataTypes;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;

/**
 * @author Simon Nikles
 *
 */
public class CamelAccesspointsExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustAccessPointType> {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public CamelAccesspointsExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustAccessPointType object) {
		super(adapterFactory, object);
		
		System.out
				.println("CamelAccesspointsExtendedPropertiesAdapter.CamelAccesspointsExtendedPropertiesAdapter() " + object);
		
		
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
				CamelAcessPointDataTypes apType = CamelAcessPointDataTypes.forKey(value.toString());
				clearSubModels(sdAccessPoint);
				switch(apType) {
				case PRIMITIVE_TYPE:
					createPrimitiveTypeModel(sdAccessPoint);
					break;
				case SERIALIZABLE_TYPE:
					createSerializableTypeModel(sdAccessPoint);
					break;
				case STRUCT_TYPE:
					createStructuredTypeModel(sdAccessPoint);
					break;
				}
			}
		});
	}
	
	private void createPrimitiveTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:type", "", "org.eclipse.stardust.engine.core.pojo.data.Type"));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
	}

	private void createSerializableTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:className", "", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
	}

	private void createStructuredTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:dataType", "", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:path:separator", "/", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:data:bidirectional", "true", "boolean"));		
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
	}

	private void clearSubModels(StardustAccessPointType sdAccessPoint) {

		AttributeType[] ats = new AttributeType[] { 		
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:type"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:className"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:dataType"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:path:separator"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "carnot:engine:data:bidirectional"),
			PropertyAdapterCommons.findAttributeType(sdAccessPoint, "RootElement")
		};
		
		for (AttributeType at : ats) {
			if (null != at) sdAccessPoint.getAttribute().remove(at);
		}
	}	
	
}
