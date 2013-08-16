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
package org.eclipse.stardust.modeling.validation.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class IdentifiableModelElementValidator implements IModelElementValidator
{
   public static final int MAX_ID_LENGTH = 50;

   public static void validateIdentifiableElement(IIdentifiableElement ie, List result)
   {
      String elementId = ie.getId();
      if (StringUtils.isEmpty(elementId))
      {
         result.add(Issue.error(
               (IModelElement) ((ie instanceof IModelElement) ? ie : null), //
               Validation_Messages.ERR_ELEMENT_EmptyId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }
      else
      {
         if (elementId.length() > MAX_ID_LENGTH)
         {
            result.add(Issue.error((IModelElement) ((ie instanceof IModelElement)
                  ? ie
                  : null), //
                  MessageFormat.format(Validation_Messages.ERR_ELEMENT_IdLength,
                        new Object[] {String.valueOf(MAX_ID_LENGTH)}),
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
         }
         if((ie instanceof DataType || ie instanceof AccessPointType)
               && !ModelUtils.isValidId(elementId))
         {
            result.add(Issue.warning(
                  (IModelElement) ((ie instanceof IModelElement) ? ie : null), //
                  MessageFormat.format(Validation_Messages.ERR_ELEMENT_InvalidId,
                  new Object[] {elementId}),
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
         }
      }
      if (StringUtils.isEmpty(ie.getName()))
      {
         if (ie instanceof DataType)
         {
            if ( !ie.eIsProxy())
            {
               result.add(Issue.warning(
                     (IModelElement) ((ie instanceof IModelElement) ? ie : null), //
                     Validation_Messages.MSG_ELEMENT_EmptyName,
                     ValidationService.PKG_CWM.getIIdentifiableElement_Name()));
            }
         }

      }
   }

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      // TODO Auto-generated method stub
      List result = new ArrayList();

      if (element instanceof IIdentifiableModelElement)
      {
         validateIdentifiableElement((IIdentifiableModelElement) element, result);
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}