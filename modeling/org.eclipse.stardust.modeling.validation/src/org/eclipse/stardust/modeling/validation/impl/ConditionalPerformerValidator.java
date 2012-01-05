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
import java.util.List;

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.IBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.ValidatorRegistry;
import org.eclipse.stardust.modeling.validation.util.IModelParticipantUtils;


public class ConditionalPerformerValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      ConditionalPerformerType conditionalPerformer = (ConditionalPerformerType) element;

      if (IModelParticipantUtils.isDuplicateId(conditionalPerformer))
      {
         result.add(Issue.error(conditionalPerformer,
               Validation_Messages.MSG_COND_PERFORMER_DuplicateId, ValidationService.PKG_CWM
                     .getIIdentifiableElement_Id()));
      }

      // TODO: Check: associated workflow data must exist
      DataType data = conditionalPerformer.getData();
      if (null == data)
      {
         result.add(Issue.error(conditionalPerformer,
               Validation_Messages.MSG_COND_PERFORMER_NoDataSet, ValidationService.PKG_CWM
                     .getConditionalPerformerType_Data()));
      }
      else
      {
         IBridgeObjectProvider dataBridgeProvider = ValidatorRegistry.getBridgeObjectProvider(conditionalPerformer.getData());
         if (null != dataBridgeProvider)
         {
            try
            {
               String dataPath = conditionalPerformer.getDataPath();
               dataPath = VariableContextHelper.getInstance().getContext(
                     (ModelType) element.eContainer()).replaceAllVariablesByDefaultValue(
                     dataPath);
               BridgeObject rhs = dataBridgeProvider.getBridgeObject(data, dataPath,
                     DirectionType.OUT_LITERAL);
               IType type = rhs.getEndClass();
               String className = type == null ? null : type.getFullyQualifiedName();
               if (!(String.class.getName().equals(className))
                     && !(Long.class.getName().equals(className)))
               {
                  result.add(Issue.error(conditionalPerformer,
                        Validation_Messages.MSG_COND_PERFORMER_UnsupportedType));
               }
            }
            catch (ValidationException e)
            {
               result.add(Issue.warning(conditionalPerformer, e.getMessage(),
                     ValidationService.PKG_CWM.getConditionalPerformerType_DataPath()));
            }
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}