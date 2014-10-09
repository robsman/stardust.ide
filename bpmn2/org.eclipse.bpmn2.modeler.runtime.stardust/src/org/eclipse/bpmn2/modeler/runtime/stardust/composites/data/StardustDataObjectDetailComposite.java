package org.eclipse.bpmn2.modeler.runtime.stardust.composites.data;

import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.emf.ecore.EObject;
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
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		
		if (object instanceof DataObject) {
			if (propertiesProvider == null) {
				propertiesProvider = new AbstractPropertiesProvider(object) {
					String[] properties = new String[] { "id", "isCollection", "itemSubjectRef" };
					
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

}
