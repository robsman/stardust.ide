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
package org.eclipse.stardust.modeling.core.editors.ui;

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;


/**
 * @author fherinean
 * @version $Revision$
 */
public class MethodViewer
{
   private TypeFinder finder;
   private AbstractListViewer viewer;
   private boolean constructors;
   private int size;

   public MethodViewer(AbstractListViewer viewer, final boolean constructors)
   {
      this.viewer = viewer;
      this.constructors = constructors;
      viewer.setSorter(new ViewerSorter());
      viewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            return ((MethodInfo) element).getLabel();
         }
      });
      setTypeFinder(finder);
   }

   public void setType(IType type)
   {
      if (!viewer.getControl().isDisposed())
      {
         viewer.setInput(type);
      }
   }

   public void selectMethod(String methodName)
   {
      methodName = stripBlanks(methodName);
      for (int i = 0; i < size; i++)
      {
         MethodInfo method = (MethodInfo) viewer.getElementAt(i);
         if (method.getEncoded().equals(methodName))
         {
            viewer.setSelection(new StructuredSelection(method), true);
         }
      }
   }

   private String stripBlanks(String methodName)
   {
      StringBuffer sb = new StringBuffer(methodName.length());
      for (int i = 0; i < methodName.length(); i++)
      {
         char c = methodName.charAt(i);
         if (c != ' ')
         {
            sb.append(c);
         }
      }
      methodName = sb.toString();
      return methodName;
   }

   public String getMethod()
   {
      StructuredSelection selection = (StructuredSelection) viewer.getSelection();
      if (!selection.isEmpty())
      {
         return ((MethodInfo) selection.getFirstElement()).getEncoded();
      }
      return ""; //$NON-NLS-1$
   }

   public void setTypeFinder(TypeFinder finder)
   {
      this.finder = finder;
      viewer.setContentProvider(new ArrayContentProvider()
      {
         public Object[] getElements(Object object)
         {
            if (MethodViewer.this.finder == null)
            {
               return super.getElements(Collections.EMPTY_LIST);
            }

            IType type = (IType) object;
            List methods = constructors ?
               MethodViewer.this.finder.getConstructors(type) :
               MethodViewer.this.finder.getMethods(type);
            size = methods.size();
            return super.getElements(methods);
         }

         public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
         {
            if (newInput == null)
            {
               size = 0;
            }
         }
      });
   }
}
