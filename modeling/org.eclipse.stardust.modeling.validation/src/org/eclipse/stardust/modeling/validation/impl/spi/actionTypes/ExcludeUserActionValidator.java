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
package org.eclipse.stardust.modeling.validation.impl.spi.actionTypes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;

import ag.carnot.workflow.runtime.beans.IUser;

public class ExcludeUserActionValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      Object dataId = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.EXCLUDED_PERFORMER_DATA);
      Object dataPath = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.EXCLUDED_PERFORMER_DATAPATH);
      if ( !(dataId instanceof String) || StringUtils.isEmpty((String) dataId))
      {
         result.add(Issue.error(element, Validation_Messages.MSG_NoDataSpecified));
      }
      else
      {
         DataType data = ModelUtils.findData(element, (String) dataId);
         if (null != data)
         {
            try
            {
               BridgeObject rhs = BridgeObject.getBridge(data, (String) dataPath,
                     DirectionType.OUT_LITERAL);

               BridgeObject lhsUserOid = JavaDataTypeUtils.getBridgeObject(
                     Long.class.getName(), null, DirectionType.IN_LITERAL);
               // BridgeObject lhsUserId =
               // JavaDataTypeUtils.getBridgeObject(String.class.getName(), null,
               // DirectionType.IN_LITERAL);
               BridgeObject lhsIUser = JavaDataTypeUtils.getBridgeObject(
                     IUser.class.getName(), null, DirectionType.IN_LITERAL);

               if ( !(lhsUserOid.acceptAssignmentFrom(rhs) || lhsIUser.acceptAssignmentFrom(rhs)))
               {
                  result.add(Issue.error(element, MessageFormat.format(
                        Validation_Messages.BridgeObject_assignmentNotCompatible,
                        new Object[] {Validation_Messages.MSG_WorkflowUser, rhs}),
                        StringUtils.isEmpty((String) dataPath)
                              ? PredefinedConstants.EXCLUDED_PERFORMER_DATA
                              : PredefinedConstants.EXCLUDED_PERFORMER_DATAPATH));
               }
            }
            catch (ValidationException e)
            {
               result.add(Issue.error(element, e.getMessage(),
                     StringUtils.isEmpty((String) dataPath)
                           ? PredefinedConstants.EXCLUDED_PERFORMER_DATA
                           : PredefinedConstants.EXCLUDED_PERFORMER_DATAPATH));
            }
         }
         else
         {
            result.add(Issue.error(element, Validation_Messages.MSG_InvalidDataSpecified));
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

}
