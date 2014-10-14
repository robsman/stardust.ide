package org.eclipse.bpmn2.modeler.runtime.stardust.composites.data;

import java.util.List;

import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataObjectDetailComposite  extends DefaultDetailComposite {

	private AbstractPropertiesProvider dataObjectReferencePropertiesProvider;

	public StardustDataObjectDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustDataObjectDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		StardustDataObjectType sdData = null;
		List<StardustDataObjectType> list = ModelDecorator.getAllExtensionAttributeValues(be, StardustDataObjectType.class);
		if (list.size() > 0) {
			sdData = list.get(0);
		} else {
			sdData = SdbpmnFactory.eINSTANCE.createStardustDataObjectType();
			EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustDataObject();
			ModelDecorator.addExtensionAttributeValue(be, feature, sdData, true);
		}
		setTitle("Stardust Type");
		StardustDataDetailComposite sdDataSection = new StardustDataDetailComposite(this, SWT.NONE);
		sdDataSection.setTitle("Stardust Type");
		sdDataSection.setBusinessObject(sdData);

		StardustDataObjectBpmnDetailComposite bpmnSection = new StardustDataObjectBpmnDetailComposite(this, SWT.NONE);
		bpmnSection.setTitle("Attributes");
		bpmnSection.setBusinessObject(be);

		super.createBindings(be);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		
		if (object instanceof DataObject) {
			if (propertiesProvider == null) {
				propertiesProvider = new AbstractPropertiesProvider(object) {
					String[] properties = new String[] { "id" };
					
					@Override
					public String[] getProperties() {
						return properties;
					}
				};
			}
			return propertiesProvider;
		}
		else if (object instanceof DataObjectReference) {
			if (dataObjectReferencePropertiesProvider == null) {
				dataObjectReferencePropertiesProvider = new AbstractPropertiesProvider(object) {
					String[] properties = new String[] { "id", "dataObjectRef" }; 

					@Override
					public String[] getProperties() {
						return properties; 
					}
				};
		
			}
			return dataObjectReferencePropertiesProvider;
		}
		return null;
	}

//	@Override
//	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
//		
//		if (object instanceof DataObject) {
//			if (propertiesProvider == null) {
//				propertiesProvider = new AbstractPropertiesProvider(object) {
//					String[] properties = new String[] { "id", "isCollection", "itemSubjectRef" };
//					
//					@Override
//					public String[] getProperties() {
//						return properties;
//					}
//				};
//			}
//			return propertiesProvider;
//		}
//		else if (object instanceof DataObjectReference) {
//			if (dataObjectReferencePropertiesProvider == null) {
//				dataObjectReferencePropertiesProvider = new AbstractPropertiesProvider(object) {
//					String[] properties = new String[] { "id", "dataObjectRef" }; 
//
//					@Override
//					public String[] getProperties() {
//						return properties; 
//					}
//				};
//		
//			}
//			return dataObjectReferencePropertiesProvider;
//		}
//		return null;
//	}

}
