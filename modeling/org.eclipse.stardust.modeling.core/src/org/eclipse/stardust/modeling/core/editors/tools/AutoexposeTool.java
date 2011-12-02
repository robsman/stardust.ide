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
package org.eclipse.stardust.modeling.core.editors.tools;

import java.util.Collections;

import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.swt.widgets.Display;

public abstract class AutoexposeTool extends AbstractTool
{
   private AutoexposeHelper exposeHelper;

   public void deactivate()
   {
      setAutoexposeHelper(null);
      super.deactivate();
   }

   protected void doAutoexpose()
   {
      if (exposeHelper == null)
         return;
      if (exposeHelper.step(getLocation()))
      {
         handleAutoexpose();
         Display.getCurrent().asyncExec(new QueuedAutoexpose());
      }
      else
         setAutoexposeHelper(null);
   }

   protected void handleAutoexpose()
   {}

   class QueuedAutoexpose implements Runnable
   {
      public void run()
      {
         if (exposeHelper != null)
            doAutoexpose();
      }
   }

   protected void setAutoexposeHelper(AutoexposeHelper helper)
   {
      exposeHelper = helper;
      if (exposeHelper == null)
         return;
      Display.getCurrent().asyncExec(new QueuedAutoexpose());
   }

   protected void updateAutoexposeHelper()
   {
      if (exposeHelper != null)
         return;
      AutoexposeHelper.Search search;
      search = new AutoexposeHelper.Search(getLocation());
      getCurrentViewer().findObjectAtExcluding(getLocation(), Collections.EMPTY_LIST,
            search);
      setAutoexposeHelper(search.result);
   }

   protected AutoexposeHelper getAutoexposeHelper()
   {
      return exposeHelper;
   }
}
