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
import org.eclipse.bpmn2.modeler.ui.property.events.TimerEventDefinitionPropertySection;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

public class StardustEventDefinitionPropertySection extends TimerEventDefinitionPropertySection implements ITabbedPropertyConstants {

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustTimerEventDefinitionDetailComposite(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.events.TimerEventDefinitionPropertySection#createSectionRoot(org.eclipse.swt.widgets.Composite, int)
	 */
	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustTimerEventDefinitionDetailComposite(parent,style);
	}
	
	public static AttributeType findAttributeType(
			StardustTimerStartEventType element, String name) {
		// check for illegal values
		if (element.getStardustAttributes() != null
				&& !element.getStardustAttributes().getAttributeType()
						.isEmpty()) {
			return element.getStardustAttributes().getAttributeType().get(0);
		} else {
			return null;
		}
	}
}
