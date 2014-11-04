/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.ui.property.events.EventDefinitionsListComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustEventDefinitionListComposite extends EventDefinitionsListComposite {

	public StardustEventDefinitionListComposite(Composite parent, Event event) {
		super(parent, event);
	}

	@Override
	protected EObject addListItem(EObject object, EStructuralFeature feature) {
		if (event instanceof ThrowEvent && ((ThrowEvent)event).getEventDefinitions().size() > 0
		|| event instanceof CatchEvent && ((CatchEvent)event).getEventDefinitions().size() > 0) {
			MessageDialog.openError(getShell(), "Not supported", "Only one event definition supported!");
			return null;
		}
		EObject addedItem = super.addListItem(object, feature);
		return addedItem;
	}
}
