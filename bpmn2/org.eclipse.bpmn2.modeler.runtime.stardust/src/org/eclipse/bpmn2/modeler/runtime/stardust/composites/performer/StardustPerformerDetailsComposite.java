package org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustPerformerDetailsComposite extends DefaultDetailComposite {

	Definitions definitions;

	public StardustPerformerDetailsComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustPerformerDetailsComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		definitions = ModelUtil.getDefinitions(be);
		super.createBindings(definitions);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"rootElements#Resource", //$NON-NLS-1$
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}
}
