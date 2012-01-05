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
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.refactoring.RefactoringUtils;
import org.eclipse.stardust.modeling.refactoring.query.matches.EObjectMatch;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.AttributeValueChange;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.EObjectStringValueSubstituteChange;


/**
 * @author fherinean
 * @version $Revision$
 */
public class InteractiveApplicationOperator implements IJdtOperator
{
   private String[] typeIds;

   public InteractiveApplicationOperator(String[] ids)
   {
      typeIds = ids;
   }

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
      List contextTypes = model.getApplicationContextType();
      for (int i = 0; i < contextTypes.size(); i++)
      {
         ApplicationContextTypeType type = (ApplicationContextTypeType) contextTypes.get(i);
         for (int j = 0; j < typeIds.length; j++)
         {
            String typeId = typeIds[j];
            if (typeId.equals(type.getId()))
            {
               List contexts = type.getContexts();
               for (int k = 0; k < contexts.size(); k++)
               {
                  ContextType ctx = (ContextType) contexts.get(k);
                  if (element instanceof IPackageFragment)
                  {
                     processPackage(result, ctx, originalPackageName, newPackageName);
                  }                  
                  else if (element instanceof IType)
                  {
                     processClass(result, ctx, originalClassName, newClassName);
                     processParams(result, ctx, originalClassName, newClassName);
                  }
                  else if (element instanceof IMethod)
                  {
                     processMethods(result, ctx, originalMethodName, newMethodName);
                  }
               }
               break;
            }
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
      List contextTypes = model.getApplicationContextType();
      for (int i = 0; i < contextTypes.size(); i++)
      {
         ApplicationContextTypeType type = (ApplicationContextTypeType) contextTypes.get(i);
         for (int j = 0; j < typeIds.length; j++)
         {
            String typeId = typeIds[j];
            if (typeId.equals(type.getId()))
            {
               List contexts = type.getContexts();
               for (int k = 0; k < contexts.size(); k++)
               {
                  ContextType ctx = (ContextType) contexts.get(k);      
                  if (element instanceof IPackageFragment)
                  {
                  }                  
                  else if (element instanceof IType)
                  {
                     queryClass(result, file, ctx, className);
                     queryParams(result, file, ctx, className);
                  }
                  else if (element instanceof IMethod)
                  {
                     queryMethods(result, file, ctx, methodName);
                  }
               }
               break;
            }
         }
      }
      return result;
   }

   private void queryParams(List result, IFile file, ContextType context,
                            String className)
   {
      AttributeType attribute = AttributeUtil.getAttribute(context,
         CarnotConstants.METHOD_NAME_ATT);
      if (attribute != null)
      {
         EObjectMatch.addParamsMatch(result, file, attribute, className);
      }
   }

   private void processParams(List result, ContextType context,
                              String originalClassName, String newClassName)
   {
      AttributeType attribute = AttributeUtil.getAttribute(context,
         CarnotConstants.METHOD_NAME_ATT);
      if (attribute != null)
      {
         EObjectStringValueSubstituteChange.addParamsSubstitution(result,
            attribute.getName(), attribute,
            CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(),
            originalClassName, newClassName);
      }
   }

   private void queryMethods(List result, IFile file, ContextType context,
                             String methodName)
   {
      AttributeType attribute = AttributeUtil.getAttribute(context,
         CarnotConstants.METHOD_NAME_ATT);
      if (attribute != null)
      {
         EObjectMatch.addMethodMatch(result, file, attribute, methodName);
      }
   }

   private void processMethods(List result, ContextType context,
                               String originalMethodName, String newMethodName)
   {
      AttributeType attribute = AttributeUtil.getAttribute(context,
         CarnotConstants.METHOD_NAME_ATT);
      if (attribute != null)
      {
         EObjectStringValueSubstituteChange.addMethodSubstitution(result,
            attribute.getName(), attribute,
            CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(),
            originalMethodName, newMethodName);
      }
   }

   private void queryClass(List result, IFile file, ContextType context,
                           String className)
   {
      AttributeType attribute = AttributeUtil.getAttribute(context,
         CarnotConstants.CLASS_NAME_ATT);
      if (attribute != null)
      {
         if (className.equals(attribute.getValue()))
         {
            result.add(new EObjectMatch(file, attribute, 0, className.length()));
         }
      }
   }

   private void processClass(List result, ContextType context,
                               String originalClassName, String newClassName)
   {
      AttributeType attribute = AttributeUtil.getAttribute(context,
         CarnotConstants.CLASS_NAME_ATT);
      if (attribute != null)
      {
         String className = attribute.getValue();
         if (originalClassName.equals(className))
         {
            result.add(new AttributeValueChange(attribute, newClassName));
         }
      }
   }

   private void processPackage(List result, ContextType context,
         String originalClassName, String newClassName)
   {
      AttributeType attribute = AttributeUtil.getAttribute(context,
            CarnotConstants.CLASS_NAME_ATT);
      if (attribute != null)
      {
         String className = attribute.getValue();
         if(RefactoringUtils.containsPackage(className, originalClassName))
         {
            result.add(new AttributeValueChange(attribute, 
                  RefactoringUtils.getNewClassName(className, originalClassName, newClassName)));                  
         }
      }
   }         
}