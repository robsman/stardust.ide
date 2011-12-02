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
import java.util.Collections;
import java.util.ArrayList;

import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.refactoring.RefactoringUtils;
import org.eclipse.stardust.modeling.refactoring.operators.IJdtOperator;
import org.eclipse.stardust.modeling.refactoring.operators.OperatorsRegistry;
import org.eclipse.stardust.modeling.refactoring.query.matches.EObjectMatch;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.AttributeValueChange;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.EObjectStringValueSubstituteChange;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.core.resources.IFile;


/**
 * @author fherinean
 * @version $Revision$
 */
public class TriggerOperator implements IJdtOperator
{
   private String typeId;

   public TriggerOperator(String id)
   {
      typeId = id;
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
      List triggerTypes = model.getTriggerType();
      for (int i = 0; i < triggerTypes.size(); i++)
      {
         TriggerTypeType type = (TriggerTypeType) triggerTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List triggers = type.getTriggers();
            for (int j = 0; j < triggers.size(); j++)
            {
               TriggerType trigger = (TriggerType) triggers.get(j);
               if (element instanceof IPackageFragment)
               {                  
                  processAccessPointsPackage(result, trigger, originalPackageName, newPackageName);
               }               
               else if (element instanceof IType)
               {
                  processAccessPoints(result, trigger, originalClassName, newClassName);
               }
               else if (element instanceof IMethod)
               {
                  processParameterMappings(result, trigger, originalMethodName, newMethodName);
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
      List triggerTypes = model.getTriggerType();
      for (int i = 0; i < triggerTypes.size(); i++)
      {
         TriggerTypeType type = (TriggerTypeType) triggerTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List triggers = type.getTriggers();
            for (int j = 0; j < triggers.size(); j++)
            {
               TriggerType trigger = (TriggerType) triggers.get(j);
               if (element instanceof IPackageFragment)
               {
               }               
               else if (element instanceof IType)
               {
                  queryAccessPoints(result, file, trigger, className);
               }
               else if (element instanceof IMethod)
               {
                  queryParameterMappings(result, file, trigger, methodName);
               }
            }
            break;
         }
      }
      return result;
   }

   private void queryParameterMappings(List result, IFile file, TriggerType trigger,
                                       String methodName)
   {
      List parameterMappings = trigger.getParameterMapping();
      for (int i = 0; i < parameterMappings.size(); i++)
      {
         ParameterMappingType param = (ParameterMappingType) parameterMappings.get(i);
         EObjectMatch.addMethodMatch(result, file, param,
            CarnotWorkflowModelPackage.eINSTANCE.getParameterMappingType_ParameterPath(),
            methodName);
      }
   }

   private void processParameterMappings(List result, TriggerType trigger,
                                         String originalMethodName, String newMethodName)
   {
      List parameterMappings = trigger.getParameterMapping();
      for (int i = 0; i < parameterMappings.size(); i++)
      {
         ParameterMappingType param = (ParameterMappingType) parameterMappings.get(i);
         EObjectStringValueSubstituteChange.addMethodSubstitution(result, "access path", param, //$NON-NLS-1$
            CarnotWorkflowModelPackage.eINSTANCE.getParameterMappingType_ParameterPath(),
            originalMethodName, newMethodName);
      }
   }

   private void queryAccessPoints(List result, IFile file, TriggerType trigger,
                                  String className)
   {
      List accessPoints = trigger.getAccessPoint();
      for (int i = 0; i < accessPoints.size(); i++)
      {
         AccessPointType ap = (AccessPointType) accessPoints.get(i);
         AttributeType attribute = AttributeUtil.getAttribute(ap,
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

   private void processAccessPoints(List result, TriggerType trigger,
                               String originalClassName, String newClassName)
   {
      List accessPoints = trigger.getAccessPoint();
      for (int i = 0; i < accessPoints.size(); i++)
      {
         AccessPointType ap = (AccessPointType) accessPoints.get(i);
         AttributeType attribute = AttributeUtil.getAttribute(ap,
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
   
   private void processAccessPointsPackage(List result, TriggerType trigger,
         String originalClassName, String newClassName)
   {
      List accessPoints = trigger.getAccessPoint();
      for (int i = 0; i < accessPoints.size(); i++)
      {
         AccessPointType ap = (AccessPointType) accessPoints.get(i);
         AttributeType attribute = AttributeUtil.getAttribute(ap,
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
}