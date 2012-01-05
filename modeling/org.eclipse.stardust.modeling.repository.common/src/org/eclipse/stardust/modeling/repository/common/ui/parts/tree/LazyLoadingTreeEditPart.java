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
package org.eclipse.stardust.modeling.repository.common.ui.parts.tree;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

abstract class LazyLoadingTreeEditPart extends AbstractTreeEditPart
{
   private static List DUMMY = Collections.singletonList(new EObjectDescriptor(null, null,
         "invisible", "invisible", null, null, null)); //$NON-NLS-1$ //$NON-NLS-2$
   
   private Adapter notificationAdapter;
   
   private boolean expanded;
   private TreeListener listener = new TreeListener()
   {
      public void treeCollapsed(TreeEvent e)
      {
         // ignored
      }

      public void treeExpanded(TreeEvent e)
      {
         if (e.item == getWidget())
         {
            expand();
         }
      }
   };

   public LazyLoadingTreeEditPart(Object model)
   {
      super(model);
   }

   protected Image getImage()
   {
      if (getModel() == null)
      {
         return null;
      }
      return doGetImage();
   }
   
   protected abstract Image doGetImage();

   protected String getText()
   {               
      if (getModel() == null)
      {
         return Repository_Messages.TXT_INVISIBLE;
      }
      return doGetText();
   }   
   
   protected abstract String doGetText();

   protected List getModelChildren()
   {
      if (!expanded && isLazyLoading())
      {
         return DUMMY;
      }
      try
      {
         return doGetModelChildren();
      }
      catch (Throwable t)
      {
         if (t instanceof PublicException && t.getCause() != null)
         {
            t = ((PublicException) t).getCause();
         }
         return Collections.singletonList(new EObjectDescriptor(null, null,
               "Error", t.toString(), null, null, null)); //$NON-NLS-1$
      }
   }    
   
   protected abstract List doGetModelChildren();

   protected boolean isLazyLoading()
   {
      return true;
   }

   public void setWidget(final Widget widget)
   {
      if (checkTreeItem())
      {
         TreeItem item = (TreeItem) getWidget();
         item.getParent().removeTreeListener(listener);
      }
      super.setWidget(widget);
      if (checkTreeItem())
      {
         TreeItem item = (TreeItem) widget;
         item.getParent().addTreeListener(listener);
         if (item.getExpanded())
         {
            expand();
         }
      }
   }

   private void expand()
   {
      if (!expanded)
      {
         expanded = true;
         BusyIndicator.showWhile(null, new Runnable()
         {
            public void run()
            {
               refreshChildren();
            }
         });
      }
   }

   public void activate()
   {
      if (!isActive())
      {
         super.activate();

         final EObject model = (EObject) getModel();
         if (null != model)
         {
            model.eAdapters().add(getNotificationAdapter());
         }
      }
   }

   public void deactivate()
   {
      if (isActive())
      {
         final EObject model = (EObject) getModel();
         if (null != model)
         {
            try
            {
               model.eAdapters().remove(getNotificationAdapter());
            }
            catch (Throwable t)
            {

            }
         }

         super.deactivate();
      }
   }

   private Adapter getNotificationAdapter()
   {
      if (null == notificationAdapter)
      {
         notificationAdapter = new Adapter()
         {
            private Notifier target;

            public void notifyChanged(Notification notification)
            {
               handleNotification(notification);
            }

            public Notifier getTarget()
            {
               return target;
            }

            public void setTarget(Notifier newTarget)
            {
               target = newTarget;
            }

            public boolean isAdapterForType(Object type)
            {
               return type.equals(getModel().getClass());
            }
         };
      }
      return notificationAdapter;
   }

   public void handleNotification(Notification n)
   {
      // whenever a notification involves an index, then we refresh the children
      // otherwise refresh the visuals
      if (n.getPosition() != Notification.NO_INDEX
            || n.getEventType() == Notification.ADD_MANY
            || n.getEventType() == Notification.REMOVE_MANY)
      {
         refreshChildren();
      }
      else
      {
         refreshVisuals();
      }
   }
}
