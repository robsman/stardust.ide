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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner;


/**
 * @author fherinean
 * @version $Revision$
 */
public abstract class EventHandlingNotificationAdapter extends AdapterImpl
{
   private IEventHandlerOwner target;
   
   public abstract void actionChanged(AbstractEventAction action, String property);

   public abstract void actionAdded(AbstractEventAction action);

   public abstract void actionMoved(AbstractEventAction action);

   public abstract void actionRemoved(AbstractEventAction action);

   public abstract void handlerChanged(EventHandlerType type, String property);

   public abstract void handlerAdded(EventHandlerType type);

   public abstract void handlerMoved(EventHandlerType type);

   public abstract void handlerRemoved(EventHandlerType type);

   public void init(IEventHandlerOwner target)
   {
      this.target = target;
      if (null != target)
      {
         registerAdapter(target);

         for (Iterator i = target.getEventHandler().iterator(); i.hasNext();)
         {
            EventHandlerType handler = (EventHandlerType) i.next();
            registerAdapter(handler);
            registerAdapter(handler.getBindAction());
            registerAdapter(handler.getEventAction());
            registerAdapter(handler.getUnbindAction());
         }
      }
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
      if (null != target)
      {
         for (Iterator i = target.getEventHandler().iterator(); i.hasNext();)
         {
            EventHandlerType handler = (EventHandlerType) i.next();
            unregisterAdapter(handler.getUnbindAction());
            unregisterAdapter(handler.getEventAction());
            unregisterAdapter(handler.getBindAction());
            unregisterAdapter(handler);
         }

         unregisterAdapter(target);
      }
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
      if (msg.getNotifier() instanceof IEventHandlerOwner)
      {
         if (CarnotWorkflowModelPackage.IEVENT_HANDLER_OWNER__EVENT_HANDLER ==
            msg.getFeatureID(IEventHandlerOwner.class))
         {
            if (Notification.ADD == msg.getEventType())
            {
               registerAdapter((Notifier) msg.getNewValue());
               handlerAdded((EventHandlerType) msg.getNewValue());
            }
            else if (Notification.REMOVE == msg.getEventType())
            {
               unregisterAdapter((Notifier) msg.getOldValue());
               handlerRemoved((EventHandlerType) msg.getOldValue());
            }
            else if (Notification.MOVE == msg.getEventType())
            {
               handlerMoved((EventHandlerType) msg.getNewValue());
            }
         }
      }
      else if (msg.getNotifier() instanceof EventHandlerType)
      {
         if (CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__EVENT_ACTION == 
               msg.getFeatureID(EventHandlerType.class)
            || CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__BIND_ACTION == 
               msg.getFeatureID(EventHandlerType.class)
            || CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION == 
               msg.getFeatureID(EventHandlerType.class))
         {
            if (Notification.ADD == msg.getEventType())
            {
               AbstractEventAction action = (AbstractEventAction) msg.getNewValue();
               actionAdded(action);
               registerAdapter(action);
            }
            else if (Notification.REMOVE == msg.getEventType())
            {
               AbstractEventAction action = (AbstractEventAction) msg.getOldValue();
               actionRemoved(action);
               unregisterAdapter(action);
            }
            else if (Notification.MOVE == msg.getEventType())
            {
               actionMoved((AbstractEventAction) msg.getNewValue());
            }
         }
         else if (CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ID == 
               msg.getFeatureID(EventHandlerType.class)
            || CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__NAME == 
               msg.getFeatureID(EventHandlerType.class)
            || CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__AUTO_BIND == 
               msg.getFeatureID(EventHandlerType.class)
            || CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__LOG_HANDLER == 
               msg.getFeatureID(EventHandlerType.class)
            || CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH == 
               msg.getFeatureID(EventHandlerType.class))
         {
            handlerChanged((EventHandlerType) msg.getNotifier(), "id"); //$NON-NLS-1$
         }
      }
      else if (msg.getNotifier() instanceof AbstractEventAction)
      {
         if (CarnotWorkflowModelPackage.ABSTRACT_EVENT_ACTION__TYPE == 
            msg.getFeatureID(AbstractEventAction.class)
            || CarnotWorkflowModelPackage.ABSTRACT_EVENT_ACTION__ID == 
               msg.getFeatureID(AbstractEventAction.class)
            || CarnotWorkflowModelPackage.ABSTRACT_EVENT_ACTION__NAME == 
               msg.getFeatureID(AbstractEventAction.class))
         {
            actionChanged((AbstractEventAction) msg.getNotifier(), "id"); //$NON-NLS-1$
         }
      }
   }

   protected void registerAdapter(Notifier target)
   {
      if ((null != target) && !target.eAdapters().contains(this))
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