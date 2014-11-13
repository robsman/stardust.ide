package org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer;

import java.util.List;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class StardustResourceDetailsComposite extends DefaultDetailComposite {

	public StardustResourceDetailsComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustResourceDetailsComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		super.createBindings(be);
		StardustResourceType sdResource = null;
		List<StardustResourceType> list = ModelDecorator.getAllExtensionAttributeValues(be, StardustResourceType.class);
		if (list.size() > 0) {
			sdResource = list.get(0);
		} else {
			sdResource = SdbpmnFactory.eINSTANCE.createStardustResourceType();
			EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustResource();
			ModelDecorator.addExtensionAttributeValue(be, feature, sdResource, true);
		}
		StardustResourceDefinitionDetailsComposite performerDetailsSection = new StardustResourceDefinitionDetailsComposite(this, SWT.NONE);
		performerDetailsSection.setBusinessObject(sdResource);
		performerDetailsSection.setTitle("Stardust Performer");
	}


	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"id", //$NON-NLS-1$
						"name" //$NON-NLS-1$
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
