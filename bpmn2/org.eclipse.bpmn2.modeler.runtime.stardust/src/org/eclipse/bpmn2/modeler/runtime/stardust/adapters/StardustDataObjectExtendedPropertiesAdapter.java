package org.eclipse.bpmn2.modeler.runtime.stardust.adapters;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataObjectExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustDataObjectType> {

	private static Hashtable<String, Object> choices = null;

	public StardustDataObjectExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustDataObjectType object) {
		super(adapterFactory, object);
//		if (null == object) return;
//		EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getStardustDataObjectType_Type();
//		setProperty(feature, UI_CAN_SET_NULL, Boolean.TRUE);
//		setProperty(feature, UI_IS_MULTI_CHOICE, Boolean.TRUE);
//
//		setFeatureDescriptor(feature, new FeatureDescriptor<StardustDataObjectType>(this,object,feature) {
//			@Override
//			protected void internalSet(StardustDataObjectType sdData, EStructuralFeature feature, Object value, int index) {
//				super.internalSet(object, feature, value, index);
//
//				if (null == value) {
//					removeDataModel(sdData);
//					return;
//				}
//				StardustDataObjectTypeEnum dataType = StardustDataObjectTypeEnum.forKey(value.toString());
//				switch(dataType) {
//				case DOCUMENT:
//					initDocumentProperties();
//				case DOCUMENT_LIST:
//					initDocumentListProperties();
//				case DOCUMENT_FOLDER:
//					initDocumentFolderProperties();
//				case DOCUMENT_FOLDER_LIST:
//					initDocumentFolderListProperties();
//				case ENTITY_BEAN:
//					initEntityBeanProperties();
//				default:
//					removeDataModel(sdData);
//					break;
//				}
//			}
//
//			@Override
//			public Hashtable<String, Object> getChoiceOfValues() {
//				if (choices==null) {
//					choices = new Hashtable<String, Object>();
//					for (StardustDataObjectTypeEnum type : StardustDataObjectTypeEnum.values()) {
//						if (type.isActive()) choices.put(type.getDisplayName(), type.getKey());
//					}
//				}
//				return choices;
//			}
//		});			
	}

	private void initDocumentProperties() {
		// TODO Auto-generated method stub

	}

	private void initDocumentListProperties() {
		// TODO Auto-generated method stub

	}

	private void initDocumentFolderProperties() {
		// TODO Auto-generated method stub

	}

	private void initDocumentFolderListProperties() {
		// TODO Auto-generated method stub

	}

	private void initEntityBeanProperties() {
		// TODO Auto-generated method stub

	}

	private void removeDataModel(StardustDataObjectType sdData) {
		// TODO Auto-generated method stub

	}


}
