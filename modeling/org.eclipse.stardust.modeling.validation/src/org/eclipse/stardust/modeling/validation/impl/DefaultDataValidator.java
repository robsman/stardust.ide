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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


public class DefaultDataValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      DataType data = (DataType) element;

      if (findDuplicateId(data))
      {
         result.add(Issue.error(data, Validation_Messages.MSG_DuplicateDataId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      if (fullCheck())
      {
         if (null == data.getType() && !data.eIsProxy())
         {
            result.add(Issue.error(data, Validation_Messages.MSG_DataHasNoType,
                  ValidationService.PKG_CWM.getDataType_Type()));
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private boolean findDuplicateId(DataType data)
   {
      for (Iterator iter = ModelUtils.findContainingModel(data).getData().iterator(); iter
            .hasNext();)
      {
         DataType otherData = (DataType) iter.next();
         if ((null != otherData.getId())
               && (otherData.getId().equals(data.getId()))
               && (!data.equals(otherData)))
         {
            return true;
         }
      }
      return false;
   }

   protected boolean fullCheck()
   {
      return true;
   }
}
