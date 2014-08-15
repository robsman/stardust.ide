/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013, 2014 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import java.util.List;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.ui.property.data.InterfaceDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class StardustInterfaceDefinitionDetailComposite extends InterfaceDetailComposite {

	public StardustInterfaceDefinitionDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustInterfaceDefinitionDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		// do all of the standard BPMN2 Interface stuff...
		super.createBindings(be);

		// ...and then the StardustInterface extension
		StardustInterfaceType sdInterface = null;
		// Here "be" is an org.eclipse.bpmn2.Interface object. If the object
		// already has a StardustInterface extension object, then use it.
		List<StardustInterfaceType> list = ModelDecorator.getAllExtensionAttributeValues(be,
				StardustInterfaceType.class);
		if (list.size() > 0) {
			sdInterface = list.get(0);
		} else {
			// Otherwise, use the SdbpmnFactory to create a new one.
			sdInterface = SdbpmnFactory.eINSTANCE.createStardustInterfaceType();
			// This is the feature that will hold the StardustInterface
			// instance object within the org.eclipse.bpmn2.Interface
			// extensionValues container.
			EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustInterface();
			// Add the new object to the Interface's extensionValues.
			// Note the use of the last parameter ("true") to use an
			// {@link InsertionAdapter} to add the object.
			ModelDecorator.addExtensionAttributeValue(be, feature, sdInterface, true);
		}

		// Create a Detail Composite for the StardustInterfaceType object.
		StardustInterfaceDetailComposite sdInterfaceSection = new StardustInterfaceDetailComposite(this, SWT.NONE);
		sdInterfaceSection.setBusinessObject(sdInterface);
		sdInterfaceSection.setTitle("Stardust Interface");
	}
}