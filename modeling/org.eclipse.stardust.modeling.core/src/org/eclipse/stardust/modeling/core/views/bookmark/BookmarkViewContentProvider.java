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
package org.eclipse.stardust.modeling.core.views.bookmark;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.DocumentRoot;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class BookmarkViewContentProvider implements ITreeContentProvider
{
   private static final Object[] EMPTY_ARRAY = new Object[0];

   public Object[] getChildren(Object parentElement)
   {
      if (parentElement instanceof ModelType)
      {
         return ((ModelType) parentElement).getView().toArray();
      }
      else if (parentElement instanceof ViewType)
      {
         List l = new ArrayList();
         l.addAll(((ViewType) parentElement).getViewable());
         l.addAll(((ViewType) parentElement).getView());
         return l.toArray();
      }
      else if (parentElement instanceof DocumentRoot)
      {
         return new Object[] {((DocumentRoot) parentElement).getModel()};
      }
      return EMPTY_ARRAY;
   }

   public Object getParent(Object element)
   {
      if (element instanceof ViewType)
      {
         return ModelUtils.findContainingModel((ViewType) element);
      }
      return null;
   }

   public boolean hasChildren(Object element)
   {
      if (element instanceof ModelType)
      {
         return ((ModelType) element).getView().size() > 0;
      }
      else if (element instanceof ViewType)
      {
         return ((ViewType) element).getViewable().size() > 0
               || ((ViewType) element).getView().size() > 0;
      }
      else if (element instanceof DocumentRoot)
      {
         return ((DocumentRoot) element).getModel() != null;
      }
      return false;
   }

   public Object[] getElements(Object inputElement)
   {
      if (hasChildren(inputElement))
      {
         return getChildren(inputElement);
      }
      return EMPTY_ARRAY;
   }

   public void dispose()
   {}

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {}
}
