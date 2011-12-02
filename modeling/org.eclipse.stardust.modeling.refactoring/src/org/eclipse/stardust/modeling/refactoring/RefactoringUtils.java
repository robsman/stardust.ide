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
package org.eclipse.stardust.modeling.refactoring;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.JavaProject;

import ag.carnot.base.StringUtils;

public class RefactoringUtils
{
   public static String getNewClassName(String className, 
         String oldPackageName, String newPackageName)
   {
      return StringUtils.replace(className, oldPackageName, newPackageName);
   }
   
   /*
    * here we must check if package name contains at least a dot 
    * (otherwise we replace to much wrong findings) 
    * 
    * this should be changed, we can also find packages with no dot
    * if class path contains at least a dot and string is not ending with package name
    */   
   public static boolean containsPackage(String className, String packageName)
   {
      if(-1 == packageName.indexOf(".")) //$NON-NLS-1$
      {
         return false;
      }
      if(-1 != className.indexOf(packageName))
      {
         return true;
      }      
      return false;
   }
   
   public static IProject getProject(IJavaElement element)
   {      
      IProject project = null;
      Object entry = element;

      if (element != null)
      {
         while (null != ((IJavaElement) entry).getParent())
         {
            entry = ((IJavaElement) entry).getParent();
            if(entry == null)
            {
               break;
            }
            if (entry instanceof JavaProject)
            {
               project = ((JavaProject) entry).getProject();
               break;
            }
         }
      }
      return project;
   }
}