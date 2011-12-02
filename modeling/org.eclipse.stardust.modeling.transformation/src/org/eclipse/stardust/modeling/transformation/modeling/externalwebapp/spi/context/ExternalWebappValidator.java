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
package org.eclipse.stardust.modeling.transformation.modeling.externalwebapp.spi.context;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;



public class ExternalWebappValidator implements IModelElementValidator {
	public Issue[] validate(IModelElement element) throws ValidationException {
		ModelType model = ModelUtils.findContainingModel(element);
		MessageTransformationController controller = new MessageTransformationController();

		try {
			controller.intializeModel(model, element);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}

		List result = new ArrayList();

		String url = AttributeUtil.getAttributeValue(
				(IExtensibleElement) element,
				ExternalWebappContextPropertyPage.COMPONENT_URL_ATT);
		if (url == null) {
			result.add(Issue.warning(element, Modeling_Messages.MSG_EXT_WEBAPP_NO_URL, 
					ExternalWebappContextPropertyPage.COMPONENT_URL_ATT));
		}

		return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
	}
}
