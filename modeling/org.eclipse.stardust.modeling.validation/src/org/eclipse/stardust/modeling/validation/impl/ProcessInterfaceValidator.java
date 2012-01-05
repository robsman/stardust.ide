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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

import ag.carnot.workflow.model.PredefinedConstants;

public class ProcessInterfaceValidator implements IModelElementValidator
{
   private static final Issue[] ISSUE_ARRAY = new Issue[0];

   private IModelElement modelElement;

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      modelElement = element;
      List<Issue> result = new ArrayList<Issue>();

      if (element instanceof ProcessDefinitionType)
      {
         ProcessDefinitionType proc = (ProcessDefinitionType) element;
         IdRef externalRef = proc.getExternalRef();
         if (proc.getExternalRef() != null)
         {
            ProcessDefinitionType referencedProcess = findProcess(externalRef);
            if (isMultipleImplementation(proc, referencedProcess))
            {
               String referencingModelID = ((ModelType) modelElement.eContainer())
                     .getId();
               String referencedProcessID = referencedProcess.getId();
               String referencedModelID = ((ModelType) referencedProcess.eContainer())
                     .getId();
               result.add(Issue.error(proc, MessageFormat.format(
                     Validation_Messages.MODEL_ProcessInterface_Multiple_Implementations,
                     new Object[] {
                           referencingModelID, referencedProcessID, referencedModelID}),
                     ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
            }
            // Process interface does not exist anymore
            if (referencedProcess.getFormalParameters() == null)
            {
               result.add(Issue.warning(proc,
                     Validation_Messages.MODEL_ProcessInterface_NotValid,
                     ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
            }
            else
            {
               FormalParametersType parametersType = proc.getFormalParameters();
               FormalParametersType referencedParametersType = referencedProcess
                     .getFormalParameters();
               FormalParameterMappingsType mappings = proc.getFormalParameterMappings();
               // Not every parameter provided
               for (Iterator<FormalParameterType> i = referencedParametersType
                     .getFormalParameter().iterator(); i.hasNext();)
               {
                  FormalParameterType referencedParameterType = i.next();
                  FormalParameterType parameterType = parametersType
                        .getFormalParameter(referencedParameterType.getId());
                  if (parameterType == null)
                  {
                     result.add(Issue.warning(proc, MessageFormat.format(
                           Validation_Messages.MODEL_ProcessInterface_ParameterMissing,
                           new Object[] {referencedParameterType.getId()}),
                           ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
                  }
                  else
                  {
                     // No mapping provided
                     if (mappings.getMappedData(parameterType) == null)
                     {
                        result.add(Issue.warning(proc, MessageFormat.format(
                              Validation_Messages.MODEL_ProcessInterface_NoMapping,
                              new Object[] {parameterType.getId()}),
                              ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
                     }
                     else
                     {
                        // Types incompatible
                        if (ModelUtils.haveDifferentTypes(referencedParameterType,
                              parameterType))
                        {
                           result
                                 .add(Issue
                                       .warning(
                                             proc,
                                             MessageFormat
                                                   .format(
                                                         Validation_Messages.MODEL_ProcessInterface_IncompatibleTypes,
                                                         new Object[] {parameterType
                                                               .getId()}),
                                             ValidationService.PKG_CWM
                                                   .getIIdentifiableElement_Id()));
                           ;
                        }
                     }
                  }
               }
               // Pending mappings
               for (Iterator<FormalParameterType> i = parametersType.getFormalParameter()
                     .iterator(); i.hasNext();)
               {
                  FormalParameterType parameterType = i.next();
                  FormalParameterType referencedParameterType = referencedParametersType
                        .getFormalParameter(parameterType.getId());
                  if (referencedParameterType == null)
                  {
                     // Should be an error as soon as it is possible to remove such
                     // pending mappings via UI
                     result.add(Issue.warning(proc, MessageFormat.format(
                           Validation_Messages.MODEL_ProcessInterface_ParameterPending,
                           new Object[] {parameterType.getId()}),
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
               for (Iterator<FormalParameterType> i = proc.getFormalParameters()
                     .getFormalParameter().iterator(); i.hasNext();)
               {
                  FormalParameterType parameterType = i.next();
                  String category = parameterType.getDataType().getCarnotType();
                  if (category == null)
                  {
                     if (parameterType.getDataType().getBasicType() != null)
                     {
                        category = "primitive"; //$NON-NLS-1$
                     }
                     if (parameterType.getDataType().getDeclaredType() != null)
                     {
                        category = "struct"; //$NON-NLS-1$
                     }
                  }
                  else
                  {
                     category = parameterType.getDataType().getCarnotType();
                  }
                  if (!"struct".equals(category) && !"primitive".equals(category)) //$NON-NLS-1$ //$NON-NLS-2$
                  {
                     invalid = true;
                  }
                  else
                  {
                     DataType dataType = proc.getFormalParameterMappings().getMappedData(
                           parameterType);
                     String typeValue = AttributeUtil.getAttributeValue(dataType,
                           PredefinedConstants.TYPE_ATT);
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
               result
                     .add(Issue
                           .error(
                                 proc,
                                 Validation_Messages.MODEL_ProcessInterface_InvalidForExternalInvocation,
                                 ValidationService.PKG_CWM
                                       .getProcessDefinitionType_FormalParameters()));
            }
         }

      }
      return (Issue[]) result.toArray(ISSUE_ARRAY);
   }

   private boolean isMultipleImplementation(ProcessDefinitionType referencingProcess,
         ProcessDefinitionType referencedProcess)
   {
      ModelType modelType = (ModelType) modelElement.eContainer();
      for (Iterator<ProcessDefinitionType> i = modelType.getProcessDefinition()
            .iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = i.next();
         IdRef externalRef = process.getExternalRef();
         if (process.getExternalRef() != null)
         {
            ProcessDefinitionType extProcess = findProcess(externalRef);
            if (!process.equals(referencingProcess)
                  && referencedProcess.equals(extProcess))
            {
               return true;
            }
         }
      }
      return false;
   }

   private java.util.List<ProcessDefinitionType> collectReferencedProcessDefinitions(
         ModelType model)
   {
      java.util.List<ProcessDefinitionType> processesList = CollectionUtils.newList();
      ExternalPackages packages = model.getExternalPackages();
      if (packages != null)
      {
         for (ExternalPackage pkg : packages.getExternalPackage())
         {
            String uri = ExtendedAttributeUtil.getAttributeValue(pkg,
                  IConnectionManager.URI_ATTRIBUTE_NAME);
            if (!StringUtils.isEmpty(uri))
            {
               IConnectionManager manager = model.getConnectionManager();
               if (manager != null)
               {
                  EObject externalModel = manager.find(uri);
                  if (externalModel instanceof IObjectReference)
                  {
                     externalModel = ((IObjectReference) externalModel).getEObject();
                  }
                  if (externalModel instanceof ModelType)
                  {
                     java.util.List<ProcessDefinitionType> externalDeclarations = ((ModelType) externalModel)
                           .getProcessDefinition();
                     if (externalDeclarations != null)
                     {
                        processesList.addAll(externalDeclarations);
                     }
                  }
               }
            }
         }
      }
      return processesList;
   }

   private ProcessDefinitionType findProcess(IdRef externalReference)
   {
      List<ProcessDefinitionType> referencedProcesses = this
            .collectReferencedProcessDefinitions((ModelType) modelElement.eContainer());
      for (Iterator<ProcessDefinitionType> i = referencedProcesses.iterator(); i
            .hasNext();)
      {
         ProcessDefinitionType proc = i.next();
         ModelType refModel = (ModelType) proc.eContainer();
         String pid = externalReference.getRef();
         String mid = externalReference.getPackageRef().getId();
         if (refModel.getId().equalsIgnoreCase(mid) && proc.getId().equalsIgnoreCase(pid))
         {
            return proc;
         }
      }
      return null;
   }
}
