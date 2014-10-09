package org.eclipse.bpmn2.modeler.runtime.stardust.composites.data;

import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataStoreReferenceDetailComposite extends DefaultDetailComposite {

	private AbstractPropertiesProvider propertiesProvider;

	public StardustDataStoreReferenceDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustDataStoreReferenceDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (object instanceof DataStoreReference) {
			if (propertiesProvider == null) {
				propertiesProvider = new AbstractPropertiesProvider(object) {
					String[] properties = new String[] {"id", "name"};

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
