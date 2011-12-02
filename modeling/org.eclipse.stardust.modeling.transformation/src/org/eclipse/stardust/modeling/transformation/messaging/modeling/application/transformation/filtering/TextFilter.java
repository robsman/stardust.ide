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





public class TextFilter extends AbstractMessageFilter {
	private String text;

	public TextFilter(MessageTransformationController controller) {
		super(controller);
	}
	
	protected boolean matches(AccessPointType messageType) {
		if (text == null || text.equalsIgnoreCase("")) { //$NON-NLS-1$
			return true;
		}
		String[] criterias = text.split(" "); //$NON-NLS-1$
		String name = messageType.getId();
		for (int i = 0; i < criterias.length; i++) {			
			if (name.indexOf(criterias[i]) != -1) {
				return true;
			}
		}
		return false;
	}

	public void setText(String text) {
		this.text = text;
	}

}
