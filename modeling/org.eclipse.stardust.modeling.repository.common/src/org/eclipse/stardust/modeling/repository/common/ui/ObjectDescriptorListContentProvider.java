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
package org.eclipse.stardust.modeling.repository.common.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;

import ag.carnot.base.CollectionUtils;

public class ObjectDescriptorListContentProvider implements ITreeContentProvider
{
   private static final Object[] EMPTY = new Object[0];
   
   private Map<Object, List<IObjectDescriptor>> data;
   private Map<Object, Object> parents;
   
   public Object[] getChildren(Object parentElement)
   {
      if (parentElement instanceof IObjectDescriptor)
      {
         IObjectDescriptor[] children = ((IObjectDescriptor) parentElement).getChildren();
         for (int i = 0; i < children.length; i++)
         {
            parents.put(children[i], parentElement);
         }
         return children;
      }
      List<IObjectDescriptor> children = data.get(parentElement);
      if (children != null)
      {
         for (int i = 0; i < children.size(); i++)
         {
            parents.put(children.get(i), parentElement);
         }
         return children.toArray();
      }
      return EMPTY;
   }

   public Object getParent(Object element)
   {
      return parents.get(element);
   }

   public boolean hasChildren(Object element)
   {
      if (element instanceof IObjectDescriptor)
      {
         return ((IObjectDescriptor) element).hasChildren();
      }
      List<IObjectDescriptor> children = data.get(element);
      return children != null && !children.isEmpty();
   }

   public Object[] getElements(Object inputElement)
   {
      return data.keySet().toArray();
   }

   public void dispose()
   {
      if (data != null)
      {
         data.clear();
         data = null;
      }
      if (parents != null)
      {
         parents.clear();
         parents = null;
      }
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
   }

   public void reset(Object newInput)
   {
      if (data == null)
      {
         data = CollectionUtils.newMap();
      }
      else
      {
         data.clear();
      }
      if (parents == null)
      {
         parents = CollectionUtils.newMap();
      }
      else
      {
         parents.clear();
      }
      if (newInput instanceof List)
      {
         createMapData((List<?>) newInput);
      }
   }

   private void createMapData(List<?> list)
   {
      for (int i = 0; i < list.size(); i++)
      {
         Object object = list.get(i);
         if (object instanceof IObjectDescriptor)
         {
            IObjectDescriptor descriptor = (IObjectDescriptor) object;
            Object key = descriptor.getType();
            if (key == null)
            {
               key = this;
            }
            List<IObjectDescriptor> children = data.get(key);
            if (children == null)
            {
               children = CollectionUtils.newList();
               data.put(key, children);
            }
            children.add(descriptor);
         }
      }
   }
   
   public String toString()
   {
      return "<"+Repository_Messages.TXT_TYPELESS+">"; //$NON-NLS-1$ //$NON-NLS-3$
   }
}
