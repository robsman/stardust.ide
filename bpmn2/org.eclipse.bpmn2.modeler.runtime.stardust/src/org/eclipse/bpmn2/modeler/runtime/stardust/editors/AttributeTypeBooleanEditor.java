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

package org.eclipse.bpmn2.modeler.runtime.stardust.editors;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.BooleanObjectEditor;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;

public class AttributeTypeBooleanEditor extends BooleanObjectEditor {
	public AttributeTypeBooleanEditor(AbstractDetailComposite parent, AttributeType object) {
		super(parent, object, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value());
	}

	@Override
	protected boolean setValue(Object result) {
		if (result instanceof Boolean) {
			result = ((Boolean) result).toString();
		}
		return super.setValue(result);
	}
}