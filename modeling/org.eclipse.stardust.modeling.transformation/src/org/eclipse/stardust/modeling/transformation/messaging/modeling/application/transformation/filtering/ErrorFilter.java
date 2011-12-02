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

import ag.carnot.base.StringUtils;

import com.infinity.bpm.messaging.model.mapping.FieldMapping;

public class ErrorFilter extends AbstractMessageFilter {


	public ErrorFilter(MessageTransformationController controller) {
		super(controller);
	}
	
	protected boolean matches(AccessPointType messageType) {
	   String xPath = controller.getXPathFor(messageType);
	   if (xPath != null) {
	     FieldMapping fm = (FieldMapping) controller.getFieldMappings().get(xPath);
	     if ((fm != null) && (fm.getMappingExpression() != null) && (!StringUtils.isEmpty(fm.getMappingExpression())) && (!fm.getMappingExpression().equalsIgnoreCase("\n"))) { //$NON-NLS-1$
	        return !controller.validateMapping(fm, false);	        
	     } else {
	        return false;
	     }       	      
	   }
	   return false;
	}
}
