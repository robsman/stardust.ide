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
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
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
public class ApplicationOperator implements IJdtOperator
{
   private String typeId;
   private boolean prefixed;
   private String[] classNames;
   private String[] constructorNames;
   private String[] methodNames;

   public ApplicationOperator(String id, String[] classAttributes,
         String[] constructorAttributes, String[] methodAttributes)
   {
      this(id, false, classAttributes, constructorAttributes, methodAttributes);
   }

   public ApplicationOperator(String id, boolean prefixMatch, String[] classAttributes,
         String[] constructorAttributes, String[] methodAttributes)
   {
      typeId = id;
      prefixed = prefixMatch;
      classNames = classAttributes;
      constructorNames = constructorAttributes;
      methodNames = methodAttributes;
   }

   public List getRefactoringChanges(ModelType model, Object element,
         RefactoringArguments arguments)
   {
      String originalPackageName = null;
      String newPackageName = null;
      if (element instanceof IPackageFragment)
      {
         originalPackageName = ((IPackageFragment) element).getElementName();
         newPackageName = OperatorsRegistry.getNewPackageName(
               (IPackageFragment) element, arguments);
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
      List applicationTypes = model.getApplicationType();
      for (int i = 0; i < applicationTypes.size(); i++)
      {
         ApplicationTypeType type = (ApplicationTypeType) applicationTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List pojos = type.getApplications();
            for (int j = 0; j < pojos.size(); j++)
            {
               ApplicationType application = (ApplicationType) pojos.get(j);
               if (element instanceof IPackageFragment)
               {
                  processPackages(result, application, originalPackageName, newPackageName);
                  //processParams(result, application, originalPackageName, newPackageName);
                  //processAccessPoints(result, application, originalPackageName, newPackageName);
               }
               else if (element instanceof IType)
               {
                  processClasses(result, application, originalClassName, newClassName);
                  processParams(result, application, originalClassName, newClassName);
                  processAccessPoints(result, application, originalClassName,
                        newClassName);
               }
               else if (element instanceof IMethod)
               {
                  processMethods(result, application, originalMethodName, newMethodName);
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
      List applicationTypes = model.getApplicationType();
      for (int i = 0; i < applicationTypes.size(); i++)
      {
         ApplicationTypeType type = (ApplicationTypeType) applicationTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List pojos = type.getApplications();
            for (int j = 0; j < pojos.size(); j++)
            {
               ApplicationType application = (ApplicationType) pojos.get(j);
               if (element instanceof IPackageFragment)
               {
                  // seems this is never called
               }
               else if (element instanceof IType)
               {
                  queryClasses(result, file, application, className);
                  queryParams(result, file, application, className);
                  queryAccessPoints(result, file, application, className);
               }
               else if (element instanceof IMethod)
               {
                  queryMethods(result, file, application, methodName);
               }
            }
            break;
         }
      }
      return result;
   }

   private void queryAccessPoints(List result, IFile file, ApplicationType application,
         String className)
   {
      List accessPoints = application.getAccessPoint();
      for (int i = 0; i < accessPoints.size(); i++)
      {
         AccessPointType ap = (AccessPointType) accessPoints.get(i);
         DataTypeType type = ap.getType();
         if (type != null && CarnotConstants.SERIALIZABLE_DATA_ID.equals(type.getId()))
         {
            AttributeType attribute = AttributeUtil.getAttribute(application,
                  CarnotConstants.CLASS_NAME_ATT);
            if (attribute != null)
            {
               if (className.equals(attribute.getValue()))
               {
                  result.add(new EObjectMatch(file, attribute, 0, className.length()));
               }
            }
         }
      }
   }

   private void processAccessPoints(List result, ApplicationType application,
         String originalClassName, String newClassName)
   {
      List accessPoints = application.getAccessPoint();
      for (int i = 0; i < accessPoints.size(); i++)
      {
         AccessPointType ap = (AccessPointType) accessPoints.get(i);
         DataTypeType type = ap.getType();
         if (type != null && CarnotConstants.SERIALIZABLE_DATA_ID.equals(type.getId()))
         {
            AttributeType attribute = AttributeUtil.getAttribute(application,
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
      }
   }

   private void queryParams(List result, IFile file, ApplicationType application,
         String className)
   {
      for (int k = 0; k < methodNames.length; k++)
      {
         AttributeType attribute = AttributeUtil
               .getAttribute(application, methodNames[k]);
         if (attribute != null)
         {
            EObjectMatch.addParamsMatch(result, file, attribute, className);
         }
      }
   }

   private void processParams(List result, ApplicationType application,
         String originalClassName, String newClassName)
   {
      for (int k = 0; k < methodNames.length; k++)
      {
         AttributeType attribute = AttributeUtil
               .getAttribute(application, methodNames[k]);
         if (attribute != null)
         {
            EObjectStringValueSubstituteChange.addParamsSubstitution(result, attribute
                  .getName(), attribute, CarnotWorkflowModelPackage.eINSTANCE
                  .getAttributeType_Value(), originalClassName, newClassName);
         }
      }
   }

   private void queryMethods(List result, IFile file, ApplicationType application,
         String methodName)
   {
      for (int k = 0; k < methodNames.length; k++)
      {
         AttributeType attribute = AttributeUtil
               .getAttribute(application, methodNames[k]);
         if (attribute != null)
         {
            EObjectMatch.addMethodMatch(result, file, attribute, methodName);
         }
      }
   }

   private void processMethods(List result, ApplicationType application,
         String originalMethodName, String newMethodName)
   {
      for (int k = 0; k < methodNames.length; k++)
      {
         AttributeType attribute = AttributeUtil
               .getAttribute(application, methodNames[k]);
         if (attribute != null)
         {
            EObjectStringValueSubstituteChange.addMethodSubstitution(result, attribute
                  .getName(), attribute, CarnotWorkflowModelPackage.eINSTANCE
                  .getAttributeType_Value(), originalMethodName, newMethodName);
         }
      }
   }

   private void queryClasses(List result, IFile file, ApplicationType application,
         String className)
   {
      for (int k = 0; k < classNames.length; k++)
      {
         List attributes = application.getAttribute();
         for (int i = 0; i < attributes.size(); i++)
         {
            AttributeType attribute = (AttributeType) attributes.get(i);
            if (prefixed && attribute.getName().startsWith(classNames[k])
                  || attribute.getName().equals(classNames[k]))
            {
               if (className.equals(attribute.getValue()))
               {
                  result.add(new EObjectMatch(file, attribute, 0, className.length()));
                  if (!prefixed)
                  {
                     // constructors are never prefixed
                     queryConstructors(result, file, application, className);
                  }
               }
            }
         }
      }
   }
   
   private void processClasses(List result, ApplicationType application,
         String originalClassName, String newClassName)
   {
      for (int k = 0; k < classNames.length; k++)
      {
         List attributes = application.getAttribute();
         for (int i = 0; i < attributes.size(); i++)
         {
            AttributeType attribute = (AttributeType) attributes.get(i);
            if (prefixed && attribute.getName().startsWith(classNames[k])
                  || attribute.getName().equals(classNames[k]))
            {
               String className = attribute.getValue();
               if (originalClassName.equals(className))
               {
                  result.add(new AttributeValueChange(attribute, newClassName));
                  if (!prefixed)
                  {
                     // constructors are never prefixed
                     processConstructors(result, application, originalClassName,
                           newClassName);
                  }
               }
            }
         }
      }
   }
   
   private void processPackages(List result, ApplicationType application,
         String originalClassName, String newClassName)
   {
      for (int k = 0; k < classNames.length; k++)
      {
         List attributes = application.getAttribute();
         for (int i = 0; i < attributes.size(); i++)
         {
            AttributeType attribute = (AttributeType) attributes.get(i);
            if (attribute.getName().startsWith(classNames[k]))
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
   }   

   private void queryConstructors(List result, IFile file, ApplicationType application,
         String className)
   {
      String originalConstructorName = getConstructorName(className);
      for (int l = 0; l < constructorNames.length; l++)
      {
         AttributeType attribute = AttributeUtil.getAttribute(application,
               constructorNames[l]);
         if (attribute != null)
         {
            EObjectMatch.addMethodMatch(result, file, attribute, originalConstructorName);
            EObjectMatch.addParamsMatch(result, file, attribute, className);
         }
      }
   }

   private void processConstructors(List result, ApplicationType application,
         String originalClassName, String newClassName)
   {
      String originalConstructorName = getConstructorName(originalClassName);
      String newConstructorName = getConstructorName(newClassName);
      if (!newConstructorName.equals(originalConstructorName))
      {
         for (int l = 0; l < constructorNames.length; l++)
         {
            AttributeType attribute = AttributeUtil.getAttribute(application,
                  constructorNames[l]);
            if (attribute != null)
            {
               EObjectStringValueSubstituteChange.addMethodSubstitution(result, attribute
                     .getName(), attribute, CarnotWorkflowModelPackage.eINSTANCE
                     .getAttributeType_Value(), originalConstructorName,
                     newConstructorName);
               EObjectStringValueSubstituteChange.addParamsSubstitution(result, attribute
                     .getName(), attribute, CarnotWorkflowModelPackage.eINSTANCE
                     .getAttributeType_Value(), originalClassName, newClassName);
            }
         }
      }
   }

   private String getConstructorName(String className)
   {
      int ix = className.lastIndexOf('.');
      String constructorName = ix < 0 ? className : className.substring(ix + 1);
      return constructorName;
   }
}