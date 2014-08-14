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

import java.util.Date;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.DateTimeObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.jface.window.Window;

public class AttributeTypeDateTimeEditor extends DateTimeObjectEditor {
	public AttributeTypeDateTimeEditor(AbstractDetailComposite parent, AttributeType object) {
		super(parent, object, CarnotPackage.eINSTANCE.getAttributeType_Value());
	}

	@Override
	protected boolean setValue(Object result) {
		if (result instanceof Date) {
			result = ((Date) result).toString();
		}
		return super.setValue(result);
	}
	
	
}