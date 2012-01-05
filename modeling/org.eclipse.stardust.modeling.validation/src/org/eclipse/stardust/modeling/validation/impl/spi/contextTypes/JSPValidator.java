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
package org.eclipse.stardust.modeling.validation.impl.spi.contextTypes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

import ag.carnot.workflow.model.PredefinedConstants;

public class JSPValidator implements IModelElementValidator
{
   private static final String MESSAGE = Validation_Messages.MSG_JSP_UndefinedHtmlPath;

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      String htmlPath = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.HTML_PATH_ATT);
      if (StringUtils.isEmpty(htmlPath))
      {
         result.add(Issue.warning(element, MESSAGE));
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

}
