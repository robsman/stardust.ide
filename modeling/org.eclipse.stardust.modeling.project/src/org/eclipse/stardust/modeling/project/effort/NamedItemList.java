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
package org.eclipse.stardust.modeling.project.effort;

import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;

public abstract class NamedItemList extends EffortNotifier
{
   public static final String ADD_PROPERTY = "items.add"; //$NON-NLS-1$
   public static final String REMOVE_PROPERTY = "items.remove"; //$NON-NLS-1$
   
   private List<NamedItem> items = CollectionUtils.newList();
   
   void add(NamedItem item)
   {
      if (!items.contains(item))
      {
         items.add(item);
         notifyListeners(new EffortEvent(this, ADD_PROPERTY, item, null));
      }
   }

   void remove(int index)
   {
      Object item = items.remove(index);
      notifyListeners(new EffortEvent(this, REMOVE_PROPERTY, null, item));
   }
   
   void remove(NamedItem item)
   {
      if (items.remove(item))
      {
         notifyListeners(new EffortEvent(this, REMOVE_PROPERTY, null, item));
      }
   }
   
   void clear()
   {
      items.clear();
   }
   
   int size()
   {
      return items.size();
   }
   
   NamedItem get(int index)
   {
      return items.get(index);
   }   
   
   NamedItem get(String name)
   {
      for (int i = 0; i < items.size(); i++)
      {
         NamedItem item = items.get(i);
         if (CompareHelper.areEqual(item.getName(), name))
         {
            return item;
         }
      }
      return null;
   }

   String getName(int index)
   {
      return items.get(index).getName();
   }
   
   Iterator<String> getNames()
   {
      final Iterator<NamedItem> source = items.iterator();
      return new Iterator<String>()
      {
         public boolean hasNext()
         {
            return source.hasNext();
         }

         public String next()
         {
            return source.next().getName();
         }

         public void remove()
         {
            source.remove();
         }
      };
   }
}
