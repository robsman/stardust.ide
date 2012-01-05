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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;


public class AccessPathBrowserContentProvider implements ITreeContentProvider
{
   private DirectionType direction;
   private Map childrenCache = new HashMap();
   private Map parentCache = new HashMap();
   private Object root;

   public AccessPathBrowserContentProvider(DirectionType direction)
   {
      this.direction = direction;
   }

   public Object[] getChildren(Object parentElement)
   {
      Object[] children = (Object[]) childrenCache.get(parentElement);
      if (children == null)
      {
         children = new Object[0];
         ITypedElement accessPoint = (ITypedElement) parentElement;
         IMetaType type = accessPoint.getMetaType();
         if (type instanceof DataTypeType)
         {
            DataTypeType dataType = (DataTypeType) accessPoint.getMetaType();
            IAccessPathEditor editor = AccessPointUtil.getSPIAccessPathEditor(dataType);
            List aps = editor.getAccessPoints(null,
               (IExtensibleElement) accessPoint, direction);
            if (AccessPointUtil.isIn(direction))
            {
               List inAPs = editor.getAccessPoints(null,
                  (IExtensibleElement) accessPoint, DirectionType.OUT_LITERAL);
               for (int i = 0; i < inAPs.size(); i++)
               {
                  IExtensibleElement element = (IExtensibleElement) inAPs.get(i);
                  if (AttributeUtil.getBooleanValue(element, CarnotConstants.BROWSABLE_ATT)
                        && !aps.contains(element))
                  {
                     aps.add(element);
                  }
               }
            }
            if (aps != null)
            {
               children = aps.toArray();
            }
         }
         childrenCache.put(parentElement, children);
         for (int i = 0; i < children.length; i++)
         {
            Object child = children[i];
            parentCache.put(child, parentElement);
         }
      }
      return children;
   }

   public Object getParent(Object element)
   {
      return parentCache.get(element);
   }

   public boolean hasChildren(Object element)
   {
      if ( !(element instanceof AccessPointType))
      {
         return false;
      }
      if (DirectionType.IN_LITERAL.equals(((AccessPointType) element).getDirection()))
      {
         return false;
      }
      return getChildren(element).length > 0;
   }

   public Object[] getElements(Object inputElement)
   {
      Object[] children = (Object[]) childrenCache.get(inputElement);
      if (children == null)
      {
         if (inputElement instanceof ITypedElement && inputElement instanceof IExtensibleElement)
         {
            ITypedElement accessPoint = (ITypedElement) inputElement;
            children = getChildren(accessPoint);
         }
         else
         {
            children = new Object[0];
            childrenCache.put(inputElement, children);
         }
      }
      return children;
   }

   public void dispose()
   {
      childrenCache.clear();
      parentCache.clear();
      childrenCache = null;
      parentCache = null;
      root = null;
      direction = null;
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      childrenCache.clear();
      parentCache.clear();
      root = newInput;
   }

   public ISelection parseSelection(String selectedMethod)
   {
      Object selection = null;
      if (root instanceof ITypedElement && root instanceof IExtensibleElement)
      {
         selection = parseSelection((ITypedElement) root, selectedMethod);
      }
      return selection == null ? null : new StructuredSelection(selection);
   }

   private AccessPointType parseSelection(ITypedElement accessPoint, String selectedMethod)
   {
      IMetaType type = accessPoint.getMetaType();
      if (type instanceof DataTypeType)
      {
         DataTypeType dataType = (DataTypeType) accessPoint.getMetaType();
         IAccessPathEditor editor = AccessPointUtil.getSPIAccessPathEditor(dataType);
         String[] splitted = editor.splitAccessPath(selectedMethod);
         if (exists(splitted[0]))
         {
            Object[] children = getChildren(accessPoint);
            for (int i = 0; i < children.length; i++)
            {
               AccessPointType child = (AccessPointType) children[i];
               if (splitted[0].equals(child.getId()))
               {
                  return exists(splitted[1]) ? parseSelection(child, splitted[1]) : child;
               }
            }
         }
      }
      return null;
   }

   private boolean exists(String string)
   {
      return string != null && string.length() > 0;
   }
}
