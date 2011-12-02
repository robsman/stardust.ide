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
package org.eclipse.stardust.modeling.core.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IFilter;

/**
 * @author fherinean
 * @version $Revision$
 */
public abstract class ModelElementNotificationAdapter extends AdapterImpl
{
   private List targets = new ArrayList();
   private EStructuralFeature elementListFeature;
   private int[] elementFeatureIds;
   private boolean registerChildren;
   private IFilter filter;
   
   public abstract void elementChanged(EObject element);

   public abstract void elementAdded(EObject element);

   public abstract void elementMoved(EObject element);

   public abstract void elementRemoved(EObject element);

   public ModelElementNotificationAdapter(EStructuralFeature elementListFeature,
         int[] elementFeatureIds, boolean registerChildren)
   {
      this(elementListFeature, elementFeatureIds, null, registerChildren);
   }

   public ModelElementNotificationAdapter(EStructuralFeature elementListFeature,
         int[] elementFeatureIds, IFilter filter, boolean registerChildren)
   {
      this.elementListFeature = elementListFeature;
      this.elementFeatureIds = elementFeatureIds;
      this.filter = filter;
      this.registerChildren = registerChildren;
   }

   public void init(EObject target)
   {
      if (null != target)
      {
         targets.add(target);
         registerAdapter(target);
         if (registerChildren)
         {
            registerAdapter(getChildren(target));
         }
      }
   }

   protected List getChildren(EObject parent)
   {
      List unfiltered = (List) parent.eGet(elementListFeature);
      ArrayList result = new ArrayList();
      if (filter == null)
      {
         result.addAll(unfiltered);
      }
      else
      {
         for (Iterator i = unfiltered.iterator(); i.hasNext();)
         {
            Object element = i.next();
            if (filter.select(element))
            {
               result.add(element);
            }
         }
      }
      return result;
   }

   private void registerAdapter(List elements)
   {
      for (Iterator i = elements.iterator(); i.hasNext();)
      {
         registerAdapter((Notifier) i.next());
      }
   }

   public void dispose()
   {
      for (int i = 0; i < targets.size(); i++)
      {
         EObject target = (EObject) targets.get(i);
         if (registerChildren)
         {
            unregisterAdapter(getChildren(target));
         }
         unregisterAdapter(target);
      }
      targets.clear();
   }

   public void dispose(EObject target)
   {
      if (registerChildren)
      {
         unregisterAdapter(getChildren(target));
      }
      unregisterAdapter(target);
      targets.remove(target);
   }
   
   protected Iterator targets()
   {
      return targets.iterator();
   }

   private void unregisterAdapter(List elements)
   {
      for (Iterator i = elements.iterator(); i.hasNext();)
      {
         unregisterAdapter((Notifier) i.next());
      }
   }

   public void notifyChanged(Notification msg)
   {
      if (targets.contains(msg.getNotifier()))
      {
         if (elementListFeature == msg.getFeature())
         {
            if (Notification.ADD == msg.getEventType() && matches(msg.getNewValue()))
            {
               if (registerChildren)
               {
                  registerAdapter((Notifier) msg.getNewValue());
               }
               elementAdded((EObject) msg.getNewValue());
            }
            else if (Notification.REMOVE == msg.getEventType() && matches(msg.getOldValue()))
            {
               if (registerChildren)
               {
                  unregisterAdapter((Notifier) msg.getOldValue());
               }
               elementRemoved((EObject) msg.getOldValue());
            }
            else if (Notification.REMOVE_MANY == msg.getEventType() && matches(msg.getOldValue()))
            {
               List removeList = (List) msg.getOldValue(); 
               for (Iterator i = removeList.iterator(); i.hasNext();)
               {
                  EObject element = (EObject) i.next();
                  if (registerChildren)
                  {
                     unregisterAdapter((Notifier) element);
                  }
                  elementRemoved(element);                  
               }
            }
            else if (Notification.MOVE == msg.getEventType() && matches(msg.getNewValue()))
            {
               elementMoved((EObject) msg.getNewValue());
            }
         }
      }
      else if (registerChildren && matches(msg.getNotifier()))
      {
         EObject notifier = (EObject) msg.getNotifier();
         if (targets.contains(notifier.eContainer()) && notifier.eContainingFeature() == elementListFeature)
         {
            int featureId = msg.getFeatureID(notifier.getClass());
            for (int i = 0; i < elementFeatureIds.length; i++)
            {
               if (featureId == elementFeatureIds[i])
               {
                  elementChanged(notifier);
               }
            }
         }
      }
   }

   private boolean matches(Object value)
   {
      return filter == null || filter.select(value);
   }

   protected void registerAdapter(Notifier target)
   {
      if (target != null && !target.eAdapters().contains(this))
      {
         target.eAdapters().add(this);
      }
   }

   protected void unregisterAdapter(Notifier target)
   {
      if (null != target)
      {
         target.eAdapters().remove(this);
      }
   }
}
