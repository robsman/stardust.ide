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
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.runtime.stardust.property;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.data.StardustDataObjectReferenceDetailComposite;
import org.eclipse.bpmn2.modeler.ui.property.data.DataObjectReferencePropertySection;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataObjectReferencePropertySection extends DataObjectReferencePropertySection {

	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustDataObjectReferenceDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustDataObjectReferenceDetailComposite(parent,style);
	}

}
