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
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.refactoring.RefactoringUtils;
import org.eclipse.stardust.modeling.refactoring.operators.IJdtOperator;
import org.eclipse.stardust.modeling.refactoring.operators.OperatorsRegistry;
import org.eclipse.stardust.modeling.refactoring.query.matches.EObjectMatch;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.AttributeValueChange;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.core.resources.IFile;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DataOperator implements IJdtOperator
{
   private String typeId;
   private String[] names;

   public DataOperator(String type, String[] attributes)
   {
      typeId = type;
      names = attributes;
   }

   public List getRefactoringChanges(ModelType model, Object element,
                                     RefactoringArguments arguments)
   {      
      if (!(element instanceof IType
            || element instanceof IPackageFragment))
      {
         return Collections.EMPTY_LIST;
      }

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

      if (newPackageName == null && newClassName == null)
      {
         return Collections.EMPTY_LIST;
      }      

      List result = new ArrayList();
      List dataTypes = model.getDataType();
      for (int i = 0; i < dataTypes.size(); i++)
      {
         DataTypeType type = (DataTypeType) dataTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List serializables = type.getData();
            for (int j = 0; j < serializables.size(); j++)
            {
               DataType data = (DataType) serializables.get(j);
               for (int k = 0; k < names.length; k++)
               {
                  AttributeType attribute = AttributeUtil.getAttribute(data, names[k]);
                  if (attribute != null)
                  {
                     if (element instanceof IPackageFragment)
                     {
                        String className = attribute.getValue();
                        if(RefactoringUtils.containsPackage(className, originalPackageName))
                        {
                           result.add(new AttributeValueChange(attribute, 
                                 RefactoringUtils.getNewClassName(className, originalPackageName, newPackageName)));                  
                        }                        
                     }               
                     else if (element instanceof IType)
                     {
                        if (originalClassName.equals(attribute.getValue()))
                        {
                           result.add(new AttributeValueChange(attribute, newClassName));
                        }                        
                     }
                  }
               }
            }
            break;
         }
      }
      return result;
   }

   public List getQueryMatches(IFile file, ModelType model, Object element)
   {
      if (!(element instanceof IType
            || element instanceof IPackageFragment))
      {
         return Collections.EMPTY_LIST;
      }

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

      if (packageName == null && className == null)
      {
         return Collections.EMPTY_LIST;
      }

      List result = new ArrayList();
      List dataTypes = model.getDataType();
      for (int i = 0; i < dataTypes.size(); i++)
      {
         DataTypeType type = (DataTypeType) dataTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List serializables = type.getData();
            for (int j = 0; j < serializables.size(); j++)
            {
               DataType data = (DataType) serializables.get(j);
               for (int k = 0; k < names.length; k++)
               {
                  AttributeType attribute = AttributeUtil.getAttribute(data, names[k]);
                  if (attribute != null)
                  {
                     if (element instanceof IPackageFragment)
                     {
                        // seems this is never called                        
                     }               
                     else if (element instanceof IType)
                     {
                        if (className.equals(attribute.getValue()))
                        {
                           result.add(new EObjectMatch(file, attribute, 0, className.length()));
                        }
                     }
                  }
               }
            }
            break;
         }
      }
      return result;
   }
}