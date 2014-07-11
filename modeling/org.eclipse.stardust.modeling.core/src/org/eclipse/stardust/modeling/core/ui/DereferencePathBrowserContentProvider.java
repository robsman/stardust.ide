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
package org.eclipse.stardust.modeling.core.ui;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;

public class DereferencePathBrowserContentProvider implements ITreeContentProvider
{
   private final TypeFinder finder;

   private boolean isConstructor = false;

   private boolean isDeep = true;

   public DereferencePathBrowserContentProvider(TypeFinder finder, boolean isConstructor,
         boolean deep)
   {
      this.finder = finder;
      this.isConstructor = isConstructor;
      this.isDeep = deep;
   }

   public Object[] getChildren(Object parentElement)
   {
      MethodInfo[] methods = null;
      try
      {
         if (parentElement instanceof TypeInfo)
         {
            methods = sort((isConstructor
                  ? finder.getConstructors((TypeInfo) parentElement)
                  : finder.getMethods((TypeInfo) parentElement)).toArray());
         }
         else if (parentElement instanceof MethodInfo)
         {
            String returnType = ((MethodInfo) parentElement).getReturnType();
            TypeInfo type = finder.findType(returnType);
            if (type != null)
            {
               methods = sort(type.getMethods().toArray());
            }
         }
      }
      catch (JavaModelException ex)
      {
         // (fh) ignore
      }
      return methods;
   }

   private MethodInfo[] sort(Object[] objects)
   {
      Map<String, Object> methodMap = CollectionUtils.newMap();
      String[] methodNames = new String[objects.length];
      for (int i = 0; i < objects.length; i++)
      {
         String methodName = ((MethodInfo) objects[i]).getLabel();
         methodMap.put(methodName, objects[i]);
         methodNames[i] = methodName;
      }

      Arrays.sort(methodNames);

      MethodInfo[] sortedMethods = new MethodInfo[objects.length];
      for (int i = 0; i < methodNames.length; i++)
      {
         sortedMethods[i] = (MethodInfo) methodMap.get(methodNames[i]);
      }
      return sortedMethods;
   }

   public Object getParent(Object element)
   {
      return null;
   }

   public boolean hasChildren(Object element)
   {
      if (element instanceof TypeInfo)
      {
         if (isConstructor)
         {
            return true;
         }
         try
         {
            return !((TypeInfo) element).getMethods().isEmpty();
         }
         catch (JavaModelException e)
         {
            // (fh) ignore - defaults to false
         }
      }
      else if ((element instanceof MethodInfo) && isDeep)
      {
         String returnType = ((MethodInfo) element).getReturnType();
         return (!StringUtils.isEmpty(returnType)) && isNoPrimitiveType(returnType);
      }
      return false;
   }

   public Object[] getElements(Object inputElement)
   {
      if (hasChildren(inputElement))
      {
         return getChildren(inputElement);
      }
      return new Object[0];
   }

   public void dispose()
   {

   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {}

   private boolean isNoPrimitiveType(String returnType)
   {
      return !((returnType.equalsIgnoreCase("void") || returnType.equals("int") //$NON-NLS-1$ //$NON-NLS-2$
            || returnType.equals("long") || returnType.equals("double") //$NON-NLS-1$ //$NON-NLS-2$
            || returnType.equals("boolean") || returnType.equals("char") //$NON-NLS-1$ //$NON-NLS-2$
            || returnType.equals("byte") || StringUtils.isEmpty(returnType))); //$NON-NLS-1$
   }
}
