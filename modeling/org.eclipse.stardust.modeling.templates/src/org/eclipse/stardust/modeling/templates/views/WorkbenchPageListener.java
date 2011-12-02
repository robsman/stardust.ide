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

import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

public class WorkbenchPageListener implements IPageListener
{
   private PartListener partListener;
   private Set registeredPages = new HashSet();

   public WorkbenchPageListener(PartListener partListener)
   {
      this.partListener = partListener;
   }

   public void pageActivated(IWorkbenchPage page)
   {
      // (fh) ignore
   }

   public void pageClosed(IWorkbenchPage page)
   {
      removeListeners(page);
   }

   public void pageOpened(IWorkbenchPage page)
   {
      registerListeners(page);
   }

   public void register(IWorkbenchWindow window)
   {
      window.addPageListener(this);
      IWorkbenchPage[] pages = window.getPages();
      for (int i = 0; i < pages.length; i++)
      {
         registerListeners(pages[i]);
      }
   }

   public void unregister(IWorkbenchWindow window)
   {
      window.removePageListener(this);
      IWorkbenchPage[] pages = window.getPages();
      for (int i = 0; i < pages.length; i++)
      {
         removeListeners(pages[i]);
      }
   }

   private void registerListeners(IWorkbenchPage page)
   {
      if (!registeredPages.contains(page))
      {
         registeredPages.add(page);
         partListener.register(page);
      }
   }

   private void removeListeners(IWorkbenchPage page)
   {
      if (registeredPages.contains(page))
      {
         registeredPages.remove(page);
         partListener.unregister(page);
      }
   }
}
