package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;

/**
 * @author Simon Nikles
 *
 */
public abstract class AccesspointsExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustAccessPointType> {

	public AccesspointsExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustAccessPointType object) {
		super(adapterFactory, object);
	}

	protected void createPrimitiveTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:type", "", "org.eclipse.stardust.engine.core.pojo.data.Type"));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
	}

	protected void createSerializableTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:className", "", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
	}

	protected void createStructuredTypeModel(StardustAccessPointType sdAccessPoint) {
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:dataType", "", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:path:separator", "/", null));
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:data:bidirectional", "true", "boolean"));		
		sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
	}

	protected void clearSubModels(StardustAccessPointType sdAccessPoint) {

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
