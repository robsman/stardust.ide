/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;


import com.infinity.bpm.messaging.model.mapping.FieldMapping;

public class NoExpressionFilter extends AbstractMessageFilter {

	public NoExpressionFilter(MessageTransformationController controller) {
		super(controller);
	}

	protected boolean matches(AccessPointType messageType) {
		FieldMapping fm = (FieldMapping) controller.getFieldMappings().get(controller.getXPathFor(messageType));			
		if ((fm == null) || (fm.getMappingExpression() == null) || (fm.getMappingExpression().equalsIgnoreCase("")) || (fm.getMappingExpression().equalsIgnoreCase("\n"))) { //$NON-NLS-1$ //$NON-NLS-2$
			return true;
		} else {
			return false;
		}
	}
	
}
