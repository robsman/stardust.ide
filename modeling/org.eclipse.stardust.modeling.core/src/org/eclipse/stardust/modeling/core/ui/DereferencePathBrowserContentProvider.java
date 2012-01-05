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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
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
      if (parentElement instanceof TypeInfo)
      {
         parentElement = ((TypeInfo) parentElement).getType();
      }
      MethodInfo[] methods = null;
      if (parentElement instanceof IType)
      {
         if (isConstructor)
         {
            methods = sort(finder.getConstructors((IType) parentElement).toArray());
         }
         else
         {
            methods = sort(finder.getMethods((IType) parentElement).toArray());
         }
      }
      else if (parentElement instanceof MethodInfo)
      {
         String returnType = ((MethodInfo) parentElement).getReturnType();
         IType type = finder.findExactType(returnType);
         if (type != null)
         {
            methods = sort(finder.getMethods(type).toArray());
         }
      }
      return methods;
   }

   private MethodInfo[] sort(Object[] objects)
   {
      Map methodMap = new HashMap();
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
         element = ((TypeInfo) element).getType();
      }
      if (element instanceof IType)
      {
         if (isConstructor)
         {
            return true;
         }
         return finder.getMethods((IType) element).size() > 0;
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
