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
package org.eclipse.stardust.modeling.core.editors.parts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

public class NotificationAdapter implements Adapter
{
   private final NotificationAdaptee adaptee;

   private Notifier target;

   public NotificationAdapter(NotificationAdaptee part)
   {
      this.adaptee = part;
   }

   public void notifyChanged(Notification notification)
   {
      adaptee.handleNotification(notification);
   }

   public Notifier getTarget()
   {
      return target;
   }

   public void setTarget(Notifier newTarget)
   {
      this.target = newTarget;
   }

   public boolean isAdapterForType(Object type)
   {
      boolean isAdapter = false;

      if ((null != adaptee) && (null != adaptee.getModel()))
      {
         isAdapter = type.equals(adaptee.getModel().getClass());
      }

      return isAdapter;
   }
}
