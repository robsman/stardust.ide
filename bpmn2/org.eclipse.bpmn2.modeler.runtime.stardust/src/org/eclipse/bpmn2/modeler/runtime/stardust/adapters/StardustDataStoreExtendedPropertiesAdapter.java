package org.eclipse.bpmn2.modeler.runtime.stardust.adapters;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CommonAttributes.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.data.StardustDataStoreTypeEnum;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataStoreExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustDataStoreType> {

	private static Hashtable<String, Object> choices = null;

	public StardustDataStoreExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustDataStoreType object) {
		super(adapterFactory, object);
		if (null == object) return;
		EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getStardustDataStoreType_Type();
		setProperty(feature, UI_CAN_SET_NULL, Boolean.TRUE);
		setProperty(feature, UI_IS_MULTI_CHOICE, Boolean.TRUE);

		setFeatureDescriptor(feature, new FeatureDescriptor<StardustDataStoreType>(this,object,feature) {
			@Override
			protected void internalSet(StardustDataStoreType sdData, EStructuralFeature feature, Object value, int index) {
				super.internalSet(object, feature, value, index);

				if (null == value) {
					removeDataModel(sdData);
					return;
				}
				StardustDataStoreTypeEnum dataType = StardustDataStoreTypeEnum.forKey(value.toString());
				switch(dataType) {
				case DOCUMENT:
				case DOCUMENT_LIST:
				case DOCUMENT_FOLDER:
				case DOCUMENT_FOLDER_LIST:
				case ENTITY_BEAN:
					removeDataModel(sdData);
					StardustAttributesType stardustAttributes = sdData.getStardustAttributes();
					if (null == stardustAttributes) {
						stardustAttributes = SdbpmnFactory.eINSTANCE.createStardustAttributesType();
						sdData.setStardustAttributes(stardustAttributes);
					}
					sdData.getStardustAttributes().getAttributeType().add(PropertyAdapterCommons.createAttributeType(Visibility.NAME, "Public", null));
					break;
				default:
					removeDataModel(sdData);
					break;
				}
			}

			@Override
			public Hashtable<String, Object> getChoiceOfValues() {
				if (choices==null) {
					choices = new Hashtable<String, Object>();
					for (StardustDataStoreTypeEnum type : StardustDataStoreTypeEnum.values()) {
						if (type.isActive()) choices.put(type.getDisplayName(), type.getKey());
					}
				}
				return choices;
			}
		});
	}

	private void removeDataModel(StardustDataStoreType sdData) {
		if (null != sdData.getStardustAttributes()) sdData.getStardustAttributes().getAttributeType().clear();
//		sdData.setType("");
	}


}
