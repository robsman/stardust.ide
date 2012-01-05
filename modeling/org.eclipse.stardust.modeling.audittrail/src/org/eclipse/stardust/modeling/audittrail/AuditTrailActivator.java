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
package org.eclipse.stardust.modeling.audittrail;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.stardust.common.CollectionUtils;
import org.osgi.framework.BundleContext;

/**
 * @author rsauer
 * @version $Revision$
 */
public class AuditTrailActivator extends Plugin
{
   private final List<IAuditTrailDbListener> dbListeners = CollectionUtils.newList();

   private static AuditTrailActivator instance = null;

   public static AuditTrailActivator instance()
   {
      return instance;
   }

   public AuditTrailActivator()
   {
   }

   public void start(BundleContext context) throws Exception
   {
      super.start(context);

      instance = this;
   }

   public void stop(BundleContext context) throws Exception
   {
      super.stop(context);

      instance = null;
   }

   public void addDbListener(IAuditTrailDbListener listener)
   {
      if (!dbListeners.contains(listener))
      {
         dbListeners.add(listener);
      }
   }

   public void removeDbListener(IAuditTrailDbListener listener)
   {
      dbListeners.remove(listener);
   }

   protected void notifyDbListeners(IFolder dbFolder, int change)
   {
      if ( !dbListeners.isEmpty())
      {
         IAuditTrailDbListener[] listeners = (IAuditTrailDbListener[]) dbListeners.toArray(new IAuditTrailDbListener[0]);
         for (int i = 0; i < listeners.length; i++ )
         {
            listeners[i].onAuditTrailDbChanged(dbFolder, change);
         }
      }
   }
}
