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

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.tasks.ActivityDetailComposite;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.widgets.Composite;

/**
 * Just to hide the data-flow parts (these are configurable sufficiently in the IO section etc.).
 *
 * @author Simon Nikles
 *
 */
public class StardustActivityDetailComposite extends ActivityDetailComposite {

	public StardustActivityDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustActivityDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	protected void createMessageAssociations(Composite container, Activity serviceTask,
			EReference operationRef, Operation operation,
			EReference messageRef, Message message) {

		super.createMessageAssociations(container, serviceTask,
				operationRef, operation,
				messageRef, message);

		if (null != inputComposite) {
			inputComposite.setVisible(false);
		}
		if (null != outputComposite) {
			outputComposite.setVisible(false);
		}
	}
}
