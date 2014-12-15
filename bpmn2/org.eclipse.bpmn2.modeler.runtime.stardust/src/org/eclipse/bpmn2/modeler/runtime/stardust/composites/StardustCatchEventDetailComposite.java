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

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

public class StardustCatchEventDetailComposite extends AbstractDetailComposite {

	public StardustCatchEventDetailComposite(
			AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustCatchEventDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		bindList(be,"dataOutputs"); //$NON-NLS-1$
	}

}
