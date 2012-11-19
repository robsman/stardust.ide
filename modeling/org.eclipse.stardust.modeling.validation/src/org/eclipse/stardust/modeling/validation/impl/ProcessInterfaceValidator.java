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

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;
import org.eclipse.stardust.modeling.validation.*;

public class ProcessInterfaceValidator implements IModelElementValidator
{
   private static final Issue[] ISSUE_ARRAY = new Issue[0];

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();

      if (element instanceof ProcessDefinitionType)
      {
         ProcessDefinitionType proc = (ProcessDefinitionType) element;
         IdRef externalRef = proc.getExternalRef();
         if (proc.getExternalRef() != null)
         {
            ProcessDefinitionType referencedProcess = externalRef.get(ProcessDefinitionType.class);
            // Process interface does not exist anymore
            if (referencedProcess == null || referencedProcess.getFormalParameters() == null)
            {
               result.add(Issue.warning(proc,
                     Validation_Messages.MODEL_ProcessInterface_NotValid,
                     ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
            }
            else
            {
               FormalParametersType parameters = proc.getFormalParameters();
               FormalParametersType referencedParameters = referencedProcess.getFormalParameters();
               FormalParameterMappingsType mappings = proc.getFormalParameterMappings();
               // Not every parameter provided
               for (FormalParameterType referencedParameter : referencedParameters.getFormalParameter())
               {
                  FormalParameterType parameter = parameters.getFormalParameter(referencedParameter.getId());
                  if (parameter == null)
                  {
                     result.add(Issue.warning(proc, MessageFormat.format(
                           Validation_Messages.MODEL_ProcessInterface_ParameterMissing, referencedParameter.getId()),
                           ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
                  }
                  else
                  {
                     // No mapping provided
                     if (mappings.getMappedData(parameter) == null)
                     {
                        result.add(Issue.warning(proc, MessageFormat.format(
                              Validation_Messages.MODEL_ProcessInterface_NoMapping, parameter.getId()),
                              ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
                     }
                     else
                     {
                        // Types incompatible
                        if (ModelUtils.haveDifferentTypes(referencedParameter, parameter))
                        {
                           result.add(Issue.warning(proc, MessageFormat.format(
                                 Validation_Messages.MODEL_ProcessInterface_IncompatibleTypes, parameter.getId()),
                                 ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
                        }
                     }
                  }
               }
               // Pending mappings
               for (FormalParameterType parameter : parameters.getFormalParameter())
               {
                  FormalParameterType referencedParameter = referencedParameters.getFormalParameter(parameter.getId());
                  if (referencedParameter == null)
                  {
                     // Should be an error as soon as it is possible to remove such
                     // pending mappings via UI
                     result.add(Issue.warning(proc, MessageFormat.format(
                           Validation_Messages.MODEL_ProcessInterface_ParameterPending, parameter.getId()),
                           ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
                  }
               }

            }
         }
         if (proc.getFormalParameters() != null)
         {
            boolean invalid = false;
            if (AttributeUtil.getAttribute(proc, "carnot:engine:externalInvocationType") != null) //$NON-NLS-1$
            {
               for (FormalParameterType parameter : proc.getFormalParameters().getFormalParameter())
               {
                  String category = parameter.getDataType().getCarnotType();
                  if (category == null)
                  {
                     if (parameter.getDataType().getBasicType() != null)
                     {
                        category = PredefinedConstants.PRIMITIVE_DATA;
                     }
                     if (parameter.getDataType().getDeclaredType() != null)
                     {
                        category = PredefinedConstants.STRUCTURED_DATA;
                     }
                  }
                  if (!PredefinedConstants.PRIMITIVE_DATA.equals(category)
                        && !PredefinedConstants.STRUCTURED_DATA.equals(category))
                  {
                     invalid = true;
                  }
                  else
                  {
                     DataType dataType = proc.getFormalParameterMappings().getMappedData(parameter);
                     String typeValue = AttributeUtil.getAttributeValue(dataType, PredefinedConstants.TYPE_ATT);
                     if (typeValue.equals(Type.Timestamp.getId())
                           || typeValue.equals(Type.Timestamp.getId()))
                     {
                        invalid = true;
                     }
                  }
               }
            }
            if (invalid)
            {
               result.add(Issue.error(proc,
                     Validation_Messages.MODEL_ProcessInterface_InvalidForExternalInvocation,
                     ValidationService.PKG_CWM.getProcessDefinitionType_FormalParameters()));
            }
         }

      }
      return (Issue[]) result.toArray(ISSUE_ARRAY);
   }
}
