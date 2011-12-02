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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;


/**
 * @author rsauer
 * @version $Revision$
 */
public abstract class AbstractEventHandlingStructureSynchronizer extends AdapterImpl
{
   protected static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   public static final char NODE_PATH_SEPARATOR = ModelElementPropertyDialog.NODE_PATH_SEPARATOR;

   private final IEventHandlerOwner target;
   
   public AbstractEventHandlingStructureSynchronizer(IEventHandlerOwner target)
   {
      this.target = target;
   }

   protected abstract void synchronizeConditionTypes();
   
   protected abstract void synchronizeEventHandlers(String conditionTypeId);

   protected abstract void synchronizeEventActions(EventHandlerType handler);

   public IEventHandlerOwner getEventHandlerOwner()
   {
      return target;
   }

   public void init()
   {
      if (null != target)
      {
         registerAdapter(target);

         for (Iterator i = target.getEventHandler().iterator(); i.hasNext();)
         {
            registerAdapter((EventHandlerType) i.next());
         }
      }
      
      updateOutline(target);
   }

   public void dispose()
   {
      if (null != target)
      {
         for (Iterator i = target.getEventHandler().iterator(); i.hasNext();)
         {
            unregisterAdapter((EventHandlerType) i.next());
         }

         unregisterAdapter(target);
      }
   }

   public void updateOutline(Object hint)
   {
      if (hint instanceof AbstractEventAction)
      {
         hint = ModelUtils.findContainingEventHandlerType((AbstractEventAction) hint);
      }

      if (hint instanceof EventHandlerType)
      {
         synchronizeEventActions((EventHandlerType) hint);
      }
      else if (hint instanceof EventConditionTypeType)
      {
         synchronizeEventHandlers(((EventConditionTypeType) hint).getId());
      }
      else
      {
         synchronizeConditionTypes();
      }

      //rootPage.refreshTree();
   }
   
   public void notifyChanged(Notification msg)
   {
      // TODO Auto-generated method stub

      if (msg.getNotifier() instanceof IEventHandlerOwner &&
            CarnotWorkflowModelPackage.IEVENT_HANDLER_OWNER__EVENT_HANDLER == msg.getFeatureID(IEventHandlerOwner.class))
      {
         if (Notification.ADD == msg.getEventType())
         {
            registerAdapter((Notifier) msg.getNewValue());
            
            updateOutline(((EventHandlerType) msg.getNewValue()).getMetaType());
         }
         else if (Notification.ADD_MANY == msg.getEventType())
         {
            // TODO register adapters
            updateOutline(null);
         }
         else if (Notification.MOVE == msg.getEventType())
         {
            updateOutline(((EventHandlerType) msg.getNewValue()).getMetaType());
         }
         else if (Notification.REMOVE == msg.getEventType())
         {
            unregisterAdapter((Notifier) msg.getOldValue());
            
            updateOutline(((EventHandlerType) msg.getOldValue()).getMetaType());
         }
         else if (Notification.REMOVE_MANY == msg.getEventType())
         {
            // TODO unregister adapters
            updateOutline(null);
         }
      }
      else if (msg.getNotifier() instanceof EventHandlerType &&
            (CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__EVENT_ACTION == msg.getFeatureID(EventHandlerType.class)
            || CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__BIND_ACTION == msg.getFeatureID(EventHandlerType.class)
            || CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION == msg.getFeatureID(EventHandlerType.class)))
      {
         switch (msg.getEventType())
         {
            case Notification.ADD:
            case Notification.ADD_MANY:
            case Notification.MOVE:
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
               updateOutline(((EventHandlerType) msg.getNotifier()).getMetaType());
         }
      }

      super.notifyChanged(msg);
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
