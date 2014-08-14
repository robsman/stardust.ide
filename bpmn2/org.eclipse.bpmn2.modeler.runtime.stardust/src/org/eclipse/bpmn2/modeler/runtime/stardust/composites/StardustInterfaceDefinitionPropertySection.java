/******************************************************************************* 
 * Copyright (c) 2011, 2012 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement;
import org.eclipse.bpmn2.modeler.ui.property.data.InterfacePropertySection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

public class StardustInterfaceDefinitionPropertySection extends InterfacePropertySection implements
		ITabbedPropertyConstants {


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustInterfaceDefinitionDetailComposite(this);
	}

	/*
	 * (non-Javadoca)
	 * @see org.eclipse.bpmn2.modeler.ui.property.events.TimerEventDefinitionPropertySection#createSectionRoot(org.eclipse.swt.widgets.Composite, int)
	 */
	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustInterfaceDefinitionDetailComposite(parent, style);
	}

	public static AttributeType findAttributeType(IExtensibleElement element, String name) {
		for (AttributeType at : element.getAttribute()) {
			if (at.getName().equals(name))
				return at;
		}
		return null;
	}
}