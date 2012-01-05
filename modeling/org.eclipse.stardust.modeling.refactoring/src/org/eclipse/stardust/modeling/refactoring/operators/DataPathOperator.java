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
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.refactoring.query.matches.EObjectMatch;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.EObjectStringValueSubstituteChange;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DataPathOperator implements IJdtOperator
{
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
         List dataPaths = process.getDataPath();
         for (int j = 0; j < dataPaths.size(); j++)
         {
            DataPathType path = (DataPathType) dataPaths.get(j);
            if (isJavaType(path.getData()))
            {
               if (element instanceof IType
                  && DirectionType.OUT_LITERAL.equals(path.getDirection()))
               {
                  EObjectStringValueSubstituteChange.addParamsSubstitution(result,
                     CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_DataPath().getName(),
                     path, CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_DataPath(),
                     originalClassName, newClassName);
               }
               if (element instanceof IMethod)
               {
                  EObjectStringValueSubstituteChange.addMethodSubstitution(result,
                     CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_DataPath().getName(),
                     path, CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_DataPath(),
                     originalMethodName, newMethodName);
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
         List dataPaths = process.getDataPath();
         for (int j = 0; j < dataPaths.size(); j++)
         {
            DataPathType path = (DataPathType) dataPaths.get(j);
            if (isJavaType(path.getData()))
            {
               if (element instanceof IType
                  && DirectionType.OUT_LITERAL.equals(path.getDirection()))
               {
                  EObjectMatch.addParamsMatch(result, file, path,
                     CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_DataPath(),
                     className);
               }
               if (element instanceof IMethod)
               {
                  EObjectMatch.addMethodMatch(result, file, path,
                     CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_DataPath(),
                     methodName);
               }
            }
         }
      }
      return result;
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