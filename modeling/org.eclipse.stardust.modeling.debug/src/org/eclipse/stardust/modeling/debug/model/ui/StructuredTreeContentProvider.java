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
package org.eclipse.stardust.modeling.debug.model.ui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataXPathUtils;
import org.eclipse.stardust.engine.core.struct.TypedXPath;

import ag.carnot.workflow.runtime.beans.BigData;

/**
 * Tree representation of structured data
 */
public class StructuredTreeContentProvider implements ITreeContentProvider
{

   private IXPathMap xPathMap;

   public StructuredTreeContentProvider (IXPathMap xPathMap)
   {
      this.xPathMap = xPathMap;
   }
   
   public void dispose()
   {
      // ignore
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      // ignore
   }

   private TypedXPath makeChildXPath(TypedXPath parentXPath, String childName)
   {
      if (StructuredDataXPathUtils.isRootXPath(parentXPath.getXPath()))
      {
         return this.xPathMap.getXPath(childName);
      }
      else 
      {
         return this.xPathMap.getXPath(parentXPath.getXPath()+"/"+childName);
      }
   }
   
   public Object[] getChildren(Object parentElement)
   {
      StructuredValue parentValue = (StructuredValue)parentElement;
      List childNodes = new LinkedList();

      // show all complex types and list items as subnodes
      Map parent = (Map)parentValue.getData();
      for (Iterator i = parent.entrySet().iterator(); i.hasNext(); )
      {
         Entry e = (Entry)i.next();
         String childName = (String)e.getKey();
         TypedXPath childXPath = this.makeChildXPath(parentValue.getXPath(), childName);
         if (childXPath.isList())
         {
            // list
            for (Iterator c = ((List)e.getValue()).iterator(); c.hasNext(); )
            {
               childNodes.add(new StructuredValue(c.next(), childXPath, parentValue, xPathMap));
            }
         }
         else if (childXPath.getType() == BigData.NULL)
         {
            // single complex type
            childNodes.add(new StructuredValue(e.getValue(), childXPath, parentValue, xPathMap));
         }
      }
      return childNodes.toArray();
   }

   public Object getParent(Object element)
   {
      StructuredValue value = (StructuredValue)element;
      return value.getParent();
   }

   public boolean hasChildren(Object element)
   {
      StructuredValue parentValue = (StructuredValue)element;
      
      if (parentValue.getXPath().getType() != BigData.NULL)
      {
         return false;
      }
      
      Map parent = (Map)parentValue.getData();
      
      if (parent == null)
      {
         return false;
      }
      
      for (Iterator i = parent.entrySet().iterator(); i.hasNext(); )
      {
         Entry e = (Entry)i.next();
         String childName = (String)e.getKey();
         TypedXPath childXPath = this.makeChildXPath(parentValue.getXPath(), childName);

         if (childXPath.isList()) 
         {
            if (((List)e.getValue()).size() > 0)
            {
               return true;
            }
         }
         else if (childXPath.getType() == BigData.NULL)
         {
            return true;
         }
      }
      
      return false;
   }

   public Object[] getElements(Object inputElement)
   {
      StructuredTreeModel model = (StructuredTreeModel)inputElement;
      Object rootData = model.getStructuredValue().getData();
      if (rootData instanceof Map)
      {
         return new Object[] {model.getStructuredValue()};
      }
      else if (rootData instanceof List)
      {
         List childNodes = new LinkedList();
         for (Iterator c = ((List)rootData).iterator(); c.hasNext(); )
         {
            childNodes.add(new StructuredValue(c.next(), model.getStructuredValue().getXPath(), null, xPathMap));
         }
         
         return childNodes.toArray();
      }
      else 
      {
         throw new PublicException("Unsupported class '"+rootData.getClass().getName()+"'");
      }
   }

}
