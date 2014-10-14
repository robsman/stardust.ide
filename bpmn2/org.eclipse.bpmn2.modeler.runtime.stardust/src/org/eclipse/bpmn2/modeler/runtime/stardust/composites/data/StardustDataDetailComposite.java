package org.eclipse.bpmn2.modeler.runtime.stardust.composites.data;

import java.util.List;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataDetailComposite extends DefaultDetailComposite {

	public StardustDataDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustDataDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(final EObject be) {
		final EObject parentBo = ((DefaultDetailComposite)getParent()).getBusinessObject();
		final DefaultDetailComposite parentComp = (DefaultDetailComposite)getParent();
		setTitle("Stardust Type");
		EStructuralFeature feature = null;
		if (be instanceof StardustDataStoreType) {
			feature = SdbpmnPackage.eINSTANCE.getStardustDataStoreType_Type();
		} else {
			feature = SdbpmnPackage.eINSTANCE.getStardustDataObjectType_Type();
		}
		ObjectEditor editor = new ComboObjectEditor(this, be, feature) {

			@Override
			protected boolean setValue(final Object newValue) {
				final Object oldValue = getValue();
				if (super.setValue(newValue)) {
					if (oldValue != newValue) {
						if (parentBo instanceof ItemAwareElement) {
							RecordingCommand command = new RecordingCommand(editingDomain) {				
								@Override
								protected void doExecute() {							
									((ItemAwareElement)parentBo).setItemSubjectRef(null);
								}
							};			
							editingDomain.getCommandStack().execute(command);							
						}
						//setBusinessObject(be);
						parentComp.setBusinessObject(parentBo);
					}
					return true;
				}
				return false;
			}

		};
		editor.createControl(getAttributesParent(), "Type");
//		
//		if (be instanceof StardustDataObjectType) {
//			final DataObject dataObject = (DataObject)parentBo;
//			if (null == dataObject) return;
//
//			if (null == dataObject.getItemSubjectRef()) {
//				RecordingCommand command = new RecordingCommand(editingDomain) {				
//					@Override
//					protected void doExecute() {
//						StardustDataObjectType sdDataStore = (StardustDataObjectType)be;
//						StardustDataObjectTypeEnum dataType = null;
//						if (null != sdDataStore.getType()) 
//							dataType = StardustDataObjectTypeEnum.forKey(sdDataStore.getType().toString());
//						if (null != dataType) {
//							ItemDefinition itemDefinition = null;
//							switch(dataType) {
//							case PRIMITIVE:
//								itemDefinition = getDefaultItemDefinition(dataObject, "org.eclipse.stardust.engine.api.runtime.Document");
//								dataObject.setItemSubjectRef(itemDefinition);
//								break;
//							case STRUCTURED:
//								itemDefinition = getDefaultItemDefinition(dataObject, "java.util.List");
//								dataObject.setItemSubjectRef(itemDefinition);
//								break;
//							case SERIALIZABLE:
//								itemDefinition = getDefaultItemDefinition(dataObject, "org.eclipse.stardust.engine.api.runtime.Folder");
//								dataObject.setItemSubjectRef(itemDefinition);
//								break;
//							default:
//								break;
//							}
//						}
//					}
//				};			
//				editingDomain.getCommandStack().execute(command);				
//			}
//		}  
		if (be instanceof StardustDataStoreType) {
			final DataStore dataStore = (DataStore)parentBo;
			if (null == dataStore) return;

			if (null == dataStore.getItemSubjectRef()) {
				RecordingCommand command = new RecordingCommand(editingDomain) {				
					@Override
					protected void doExecute() {

						StardustDataStoreType sdDataStore = (StardustDataStoreType)be;
						StardustDataStoreTypeEnum dataType = null;
						if (null != sdDataStore.getType()) 
							dataType = StardustDataStoreTypeEnum.forKey(sdDataStore.getType().toString());
						if (null != dataType) {
							ItemDefinition itemDefinition = null;
							switch(dataType) {
							case DOCUMENT:
								itemDefinition = getDefaultItemDefinition(dataStore, StardustDataStoreTypeEnum.DOCUMENT.getDefaultClass());
								dataStore.setItemSubjectRef(itemDefinition);
								break;
							case DOCUMENT_LIST:
								itemDefinition = getDefaultItemDefinition(dataStore, StardustDataStoreTypeEnum.DOCUMENT_LIST.getDefaultClass());
								dataStore.setItemSubjectRef(itemDefinition);
								break;
							case DOCUMENT_FOLDER:
								itemDefinition = getDefaultItemDefinition(dataStore, StardustDataStoreTypeEnum.DOCUMENT_FOLDER.getDefaultClass());
								dataStore.setItemSubjectRef(itemDefinition);
								break;
							case DOCUMENT_FOLDER_LIST:
								itemDefinition = getDefaultItemDefinition(dataStore, StardustDataStoreTypeEnum.DOCUMENT_FOLDER_LIST.getDefaultClass());
								dataStore.setItemSubjectRef(itemDefinition);
							case ENTITY_BEAN:
								break;
							default:
								break;
							}
						}
					}
				};			
				editingDomain.getCommandStack().execute(command);				
			}
		}
	}
	
	private ItemDefinition addDefaultItemDefinition(final Definitions definitions, final String clsName) {
		final ItemDefinition itemDef = Bpmn2Factory.eINSTANCE.createItemDefinition();
		RecordingCommand command = new RecordingCommand(editingDomain) {				
			@Override
			protected void doExecute() {
				ExtensionHelper.getInstance().setAnyAttribute(itemDef, ExtensionHelper2.STARDUST_SYNTHETIC_ITEMDEF, "true");
				EObject wrapper = ModelUtil.createStringWrapper(clsName);
				itemDef.setStructureRef(wrapper);
				definitions.getRootElements().add(itemDef);
				ModelUtil.setID(itemDef);
				definitions.getRootElements().add(itemDef);
			}
		};			
		editingDomain.getCommandStack().execute(command);
		System.out
				.println("StardustDataDetailComposite.addDefaultItemDefinition() " + clsName);
		
		return itemDef;		
	}

	private ItemDefinition getDefaultItemDefinition(EObject dataStore, String clsName) {
		Definitions definitions = ModelUtil.getDefinitions(dataStore);
		List<ItemDefinition> elements = ModelUtil.getAllRootElements(definitions, ItemDefinition.class);
		for (ItemDefinition def : elements) {
			if (null != def.getStructureRef()) {
				if (def.getStructureRef().toString().equals(clsName)) {
					System.out
							.println("StardustDataDetailComposite.getDefaultItemDefinition() found " + clsName);
					return def;
				}
			}
		}
		return addDefaultItemDefinition(definitions, clsName);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
//		if (object instanceof DataStoreReference 
//		 || object instanceof DataObjectReference 
//		 || object instanceof DataStore 
//		 || object instanceof DataObject) {
		if (object instanceof StardustDataObjectType
		 || object instanceof StardustDataStoreType) {
			if (propertiesProvider == null) {
				propertiesProvider = new AbstractPropertiesProvider(object) {
					String[] properties = new String[] {"type"};

					@Override
					public String[] getProperties() {
						return properties; 
					}
				};
		
			}
			return propertiesProvider;
		}
		return null;
	}	

}
