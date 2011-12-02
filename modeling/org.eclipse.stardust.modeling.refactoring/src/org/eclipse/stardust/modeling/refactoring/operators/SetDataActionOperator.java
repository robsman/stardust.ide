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

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.refactoring.operators.IJdtOperator;
import org.eclipse.stardust.modeling.refactoring.operators.OperatorsRegistry;
import org.eclipse.stardust.modeling.refactoring.query.matches.EObjectMatch;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.EObjectStringValueSubstituteChange;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.core.resources.IFile;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SetDataActionOperator implements IJdtOperator
{
   private static final String DATA_ID_ATT = CarnotConstants.ENGINE_SCOPE + "dataId"; //$NON-NLS-1$
   private static final String DATA_PATH_ATT = CarnotConstants.ENGINE_SCOPE + "dataPath"; //$NON-NLS-1$
   private static final String ATTRIBUTE_PATH_ATT = CarnotConstants.ENGINE_SCOPE + "attributePath"; //$NON-NLS-1$

   public List getRefactoringChanges(ModelType model, Object element, RefactoringArguments arguments)
   {
      String originalPackageName = null;
      String newPackageName = null;
      if (element instanceof IPackageFragment)
      {
         originalPackageName = ((IPackageFragment) element).getElementName();
         newPackageName = OperatorsRegistry.getNewPackageName((IPackageFragment) element, arguments);
         if (newPackageName.equals(originalPackageName))
         {
            return Collections.EMPTY_LIST;
         }
      }
      
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
      
      if (newPackageName == null && newClassName == null && newMethodName == null)
      {
         return Collections.EMPTY_LIST;
      }

      List result = new ArrayList();
      List actionTypes = model.getEventActionType();
      for (int i = 0; i < actionTypes.size(); i++)
      {
         EventActionTypeType type = (EventActionTypeType) actionTypes.get(i);
         if (CarnotConstants.SET_DATA_ACTION_ID.equals(type.getId()))
         {
            List actions = type.getActionInstances();
            for (int j = 0; j < actions.size(); j++)
            {
               AbstractEventAction action = (AbstractEventAction) actions.get(j);
               String dataId = AttributeUtil.getAttributeValue(action, DATA_ID_ATT);
               DataType data = (DataType) ModelUtils.findIdentifiableElement(model.getData(), dataId);
               if (isJavaType(data))
               {
                  AttributeType attribute = AttributeUtil.getAttribute(action, DATA_PATH_ATT);
                  if (attribute != null)
                  {
                     if (element instanceof IPackageFragment)
                     {
                     }                     
                     else if (element instanceof IType)
                     {
                        EObjectStringValueSubstituteChange.addParamsSubstitution(result,
                           DATA_PATH_ATT, attribute,
                           CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(),
                           originalClassName, newClassName);
                     }
                     else if (element instanceof IMethod)
                     {
                        EObjectStringValueSubstituteChange.addMethodSubstitution(result,
                           DATA_PATH_ATT, attribute,
                           CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(),
                           originalMethodName, newMethodName);
                     }
                  }
               }
               // todo: (fh) bug*bug*bug : assumes that access point types are always java types
               AttributeType attribute = AttributeUtil.getAttribute(action, ATTRIBUTE_PATH_ATT);
               if (attribute != null && element instanceof IMethod)
               {
                  EObjectStringValueSubstituteChange.addMethodSubstitution(result,
                     ATTRIBUTE_PATH_ATT, attribute,
                     CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(),
                     originalMethodName, newMethodName);
               }
            }
            break;
         }
      }
      return result;
   }

   public List getQueryMatches(IFile file, ModelType model, Object element)
   {
      String packageName = null;
      if (element instanceof IPackageFragment)
      {
         packageName = ((IPackageFragment) element).getElementName();
      }      
      
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

      if (packageName == null && className == null && methodName == null)
      {
         return Collections.EMPTY_LIST;
      }

      List result = new ArrayList();
      List actionTypes = model.getEventActionType();
      for (int i = 0; i < actionTypes.size(); i++)
      {
         EventActionTypeType type = (EventActionTypeType) actionTypes.get(i);
         if (CarnotConstants.SET_DATA_ACTION_ID.equals(type.getId()))
         {
            List actions = type.getActionInstances();
            for (int j = 0; j < actions.size(); j++)
            {
               AbstractEventAction action = (AbstractEventAction) actions.get(j);
               String dataId = AttributeUtil.getAttributeValue(action, DATA_ID_ATT);
               DataType data = (DataType) ModelUtils.findIdentifiableElement(model.getData(), dataId);
               if (isJavaType(data))
               {
                  AttributeType attribute = AttributeUtil.getAttribute(action, DATA_PATH_ATT);
                  if (attribute != null)
                  {
                     if (element instanceof IPackageFragment)
                     {
                     }                     
                     else if (element instanceof IType)
                     {
                        EObjectMatch.addParamsMatch(result, file, attribute, className);
                     }
                     else if (element instanceof IMethod)
                     {
                        EObjectMatch.addMethodMatch(result, file, attribute, methodName);
                     }
                  }
               }
               // todo: (fh) bug*bug*bug : assumes that access point types are always java types
               AttributeType attribute = AttributeUtil.getAttribute(action, ATTRIBUTE_PATH_ATT);
               if (attribute != null && element instanceof IMethod)
               {
                  EObjectMatch.addMethodMatch(result, file, attribute, methodName);
               }
            }
            break;
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