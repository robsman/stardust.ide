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
package org.eclipse.stardust.modeling.refactoring.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.refactoring.query.matches.EObjectMatch;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.EObjectStringValueSubstituteChange;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DataMappingOperator implements IJdtOperator
{
   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;

   public List getRefactoringChanges(ModelType model, Object element, RefactoringArguments arguments)
   {
      String originalClassName = null;
      String newClassName = null;
      if (element instanceof IType)
      {
         originalClassName = ((IType) element).getFullyQualifiedName();
         newClassName = OperatorsRegistry.getNewClassName((IType) element, arguments);
         if (newClassName.equals(originalClassName))
         {
            return Collections.EMPTY_LIST;
         }
      }

      String originalMethodName = null;
      String newMethodName = null;
      if (element instanceof IMethod)
      {
         originalMethodName = ((IMethod) element).getElementName();
         newMethodName = OperatorsRegistry.getNewMethodName((IMethod) element, arguments);
         if (newMethodName.equals(originalMethodName))
         {
            return Collections.EMPTY_LIST;
         }
      }

      if (newClassName == null && newMethodName == null)
      {
         return Collections.EMPTY_LIST;
      }

      List result = new ArrayList();
      List processTypes = model.getProcessDefinition();
      for (int i = 0; i < processTypes.size(); i++)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) processTypes.get(i);
         List activities = process.getActivity();
         for (int j = 0; j < activities.size(); j++)
         {
            ActivityType activity = (ActivityType) activities.get(j);
            List dataMappings = activity.getDataMapping();
            for (int k = 0; k < dataMappings.size(); k++)
            {
               DataMappingType mapping = (DataMappingType) dataMappings.get(k);
               if (DirectionType.OUT_LITERAL.equals(mapping.getDirection()))
               {
                  if (isJavaType(mapping.getData()))
                  {
                     if (element instanceof IType)
                     {
                        EObjectStringValueSubstituteChange.addParamsSubstitution(result,
                           CWM_PKG.getDataMappingType_DataPath().getName(),
                           mapping, CWM_PKG.getDataMappingType_DataPath(),
                           originalClassName, newClassName);
                     }
                     else if (element instanceof IMethod)
                     {
                        EObjectStringValueSubstituteChange.addMethodSubstitution(result,
                           CWM_PKG.getDataMappingType_DataPath().getName(),
                           mapping, CWM_PKG.getDataMappingType_DataPath(),
                           originalMethodName, newMethodName);
                     }
                  }
               }
               else if (DirectionType.IN_LITERAL.equals(mapping.getDirection()))
               {
                  boolean isJavaApp = isJavaApplication(mapping);
                  if (isJavaApp)
                  {
                     if (element instanceof IType)
                     {
                        EObjectStringValueSubstituteChange.addParamsSubstitution(result,
                           CWM_PKG.getDataMappingType_ApplicationAccessPoint().getName(),
                           mapping, CWM_PKG.getDataMappingType_ApplicationAccessPoint(),
                           originalClassName, newClassName);
                     }
                     else if (element instanceof IMethod)
                     {
                        EObjectStringValueSubstituteChange.addMethodSubstitution(result,
                           CWM_PKG.getDataMappingType_ApplicationAccessPoint().getName(),
                           mapping, CWM_PKG.getDataMappingType_ApplicationAccessPoint(),
                           originalMethodName, newMethodName);
                     }
                  }
                  if (isJavaApp || isJavaAccessPoint(mapping))
                  {
                     if (element instanceof IType)
                     {
                        EObjectStringValueSubstituteChange.addParamsSubstitution(result,
                           CWM_PKG.getDataMappingType_ApplicationPath().getName(),
                           mapping, CWM_PKG.getDataMappingType_ApplicationPath(),
                           originalClassName, newClassName);
                     }
                     else if (element instanceof IMethod)
                     {
                        EObjectStringValueSubstituteChange.addMethodSubstitution(result,
                           CWM_PKG.getDataMappingType_ApplicationPath().getName(),
                           mapping, CWM_PKG.getDataMappingType_ApplicationPath(),
                           originalMethodName, newMethodName);
                     }
                  }
               }
            }
         }
      }
      return result;
   }

   public List getQueryMatches(IFile file, ModelType model, Object element)
   {
      String className = null;
      if (element instanceof IType)
      {
         className = ((IType) element).getFullyQualifiedName();
      }

      String methodName = null;
      if (element instanceof IMethod)
      {
         methodName = ((IMethod) element).getElementName();
      }

      if (className == null && methodName == null)
      {
         return Collections.EMPTY_LIST;
      }

      List result = new ArrayList();
      List processTypes = model.getProcessDefinition();
      for (int i = 0; i < processTypes.size(); i++)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) processTypes.get(i);
         List activities = process.getActivity();
         for (int j = 0; j < activities.size(); j++)
         {
            ActivityType activity = (ActivityType) activities.get(j);
            List dataMappings = activity.getDataMapping();
            for (int k = 0; k < dataMappings.size(); k++)
            {
               DataMappingType mapping = (DataMappingType) dataMappings.get(k);
               if (DirectionType.OUT_LITERAL.equals(mapping.getDirection()))
               {
                  if (isJavaType(mapping.getData()))
                  {
                     if (element instanceof IType)
                     {
                        EObjectMatch.addParamsMatch(result, file, mapping,
                           CWM_PKG.getDataMappingType_DataPath(), className);
                     }
                     else if (element instanceof IMethod)
                     {
                        EObjectMatch.addMethodMatch(result, file, mapping,
                           CWM_PKG.getDataMappingType_DataPath(), methodName);
                     }
                  }
               }
               else if (DirectionType.IN_LITERAL.equals(mapping.getDirection()))
               {
                  boolean isJavaApp = isJavaApplication(mapping);
                  if (isJavaApp)
                  {
                     if (element instanceof IType)
                     {
                        EObjectMatch.addParamsMatch(result, file, mapping,
                           CWM_PKG.getDataMappingType_ApplicationAccessPoint(), className);
                     }
                     else if (element instanceof IMethod)
                     {
                        EObjectMatch.addMethodMatch(result, file, mapping,
                           CWM_PKG.getDataMappingType_ApplicationAccessPoint(), methodName);
                     }
                  }
                  if (isJavaApp || isJavaAccessPoint(mapping))
                  {
                     if (element instanceof IType)
                     {
                        EObjectMatch.addParamsMatch(result, file, mapping,
                           CWM_PKG.getDataMappingType_ApplicationPath(), className);
                     }
                     else if (element instanceof IMethod)
                     {
                        EObjectMatch.addMethodMatch(result, file, mapping,
                           CWM_PKG.getDataMappingType_ApplicationPath(), methodName);
                     }
                  }
               }
            }
         }
      }
      return result;
   }

   private boolean isJavaAccessPoint(DataMappingType mapping)
   {
      String apId = mapping.getApplicationAccessPoint();
      if (apId != null && apId.length() > 0)
      {
         ActivityType activity = (ActivityType) mapping.eContainer();
         List inAccessPoints = ActivityUtil.getExplicitAccessPoints(activity, true,
            mapping.getContext());
         for (int i = 0; i < inAccessPoints.size(); i++)
         {
            AccessPointType ap = (AccessPointType) inAccessPoints.get(i);
            if (ap.getId().equals(apId))
            {
               DataTypeType type = ap.getType();
               String id = type.getId();
               return CarnotConstants.SERIALIZABLE_DATA_ID.equals(id)
                  || CarnotConstants.ENTITY_BEAN_DATA_ID.equals(id)
                  || CarnotConstants.PRIMITIVE_DATA_ID.equals(id);
            }
         }
      }
      return false;
   }

   private boolean isJavaApplication(DataMappingType mapping)
   {
      boolean isJavaApplication = false;
      ActivityType activity = (ActivityType) mapping.eContainer();
      if (ActivityUtil.isApplicationActivity(activity))
      {
         ApplicationType application = activity.getApplication();
         if (application != null)
         {
            if (application.isInteractive())
            {
               isJavaApplication = CarnotConstants.CASABAC_CONTEXT_ID.equals(mapping.getContext())
                  || CarnotConstants.JFC_CONTEXT_ID.equals(mapping.getContext());
            }
            else
            {
               ApplicationTypeType type = application.getType();
               isJavaApplication = CarnotConstants.PLAIN_JAVA_APPLICATION_ID.equals(type.getId())
                  || CarnotConstants.SESSION_BEAN_APPLICATION_ID.equals(type.getId());
            }
         }
      }
      return isJavaApplication;
   }

   private boolean isJavaType(DataType data)
   {
      if (data != null)
      {
         DataTypeType type = data.getType();
         String id = type.getId();
         return CarnotConstants.SERIALIZABLE_DATA_ID.equals(id)
            || CarnotConstants.ENTITY_BEAN_DATA_ID.equals(id)
            || CarnotConstants.PRIMITIVE_DATA_ID.equals(id)
            || CarnotConstants.HIBERNATE_DATA_ID.equals(id);
      }
      return false;
   }
}
