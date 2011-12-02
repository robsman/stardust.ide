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
package org.eclipse.stardust.modeling.templates.views;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class WorkbenchWindowListener implements IWindowListener
{
   private WorkbenchPageListener pageListener;
   private Set registeredWindows = new HashSet();

   public WorkbenchWindowListener(WorkbenchPageListener pageListener)
   {
      this.pageListener = pageListener;
      register();
   }

   public void register()
   {
      IWorkbench workbench = PlatformUI.getWorkbench();
      workbench.addWindowListener(this);
      IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
      for (int i = 0; i < windows.length; i++)
      {
         registerListeners(windows[i]);
      }
   }

   public void unregister()
   {
      IWorkbench workbench = PlatformUI.getWorkbench();
      workbench.removeWindowListener(this);
      IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
      for (int i = 0; i < windows.length; i++)
      {
         removeListeners(windows[i]);
      }
   }

   public void windowActivated(IWorkbenchWindow window)
   {
      // (fh) ignore
   }

   public void windowDeactivated(IWorkbenchWindow window)
   {
      // (fh) ignore
   }

   public void windowClosed(IWorkbenchWindow window)
   {
      removeListeners(window);
   }

   public void windowOpened(IWorkbenchWindow window)
   {
      registerListeners(window);
   }

   private void registerListeners(IWorkbenchWindow window)
   {
      if (!registeredWindows.contains(window))
      {
         registeredWindows.add(window);
         pageListener.register(window);
      }
   }

   private void removeListeners(IWorkbenchWindow window)
   {
      if (registeredWindows.contains(window))
      {
         registeredWindows.remove(window);
         pageListener.unregister(window);
      }
   }
}
