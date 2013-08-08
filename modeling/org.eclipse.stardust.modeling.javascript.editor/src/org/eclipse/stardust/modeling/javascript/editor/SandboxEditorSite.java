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
package org.eclipse.stardust.modeling.javascript.editor;

import java.util.Arrays;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.*;
import org.eclipse.ui.internal.KeyBindingService;

public class SandboxEditorSite implements IEditorSite
{
   /**
    * The list of services required for the IEditorSite to be available.
    * 
    * (fh) must be sorted into ascending order according to the natural ordering
    * since it is used with Arrays.binarySearch
    */
   private static final String[] requiredServices = {
      "org.eclipse.e4.ui.services.EContextService",
      "org.eclipse.ui.handlers.IHandlerService"
   };
   
   private IEditorSite delegate;
   private IKeyBindingService keyBindingService;
   private ISelectionProvider selectionProvider;

   public SandboxEditorSite(IEditorSite editorSite)
   {
      delegate = editorSite;
      keyBindingService = new KeyBindingService(this);
   }

   public String getId()
   {
      return delegate.getId();
   }

   public IKeyBindingService getKeyBindingService()
   {
      return keyBindingService;
   }

   public IWorkbenchPart getPart()
   {
      return delegate.getPart();
   }

   public String getPluginId()
   {
      return delegate.getPluginId();
   }

   public String getRegisteredName()
   {
      return delegate.getRegisteredName();
   }

   public void registerContextMenu(MenuManager menuManager,
         ISelectionProvider selectionProvider)
   {
   }

   public void registerContextMenu(String menuId, MenuManager menuManager,
         ISelectionProvider selectionProvider)
   {
   }

   public IWorkbenchPage getPage()
   {
      return delegate.getPage();
   }

   public ISelectionProvider getSelectionProvider()
   {
      return selectionProvider;
   }

   public Shell getShell()
   {
      return delegate.getShell();
   }

   public IWorkbenchWindow getWorkbenchWindow()
   {
      return delegate.getWorkbenchWindow();
   }

   public void setSelectionProvider(ISelectionProvider provider)
   {
      selectionProvider = provider;
   }

   public Object getAdapter(Class adapter)
   {
      return delegate.getAdapter(adapter);
   }

   public Object getService(Class api)
   {
      if (isRequiredService(api))
      {
         return delegate.getService(api);
      }
      return null;
   }

   public boolean hasService(Class api)
   {
      if (isRequiredService(api))
      {
         return delegate.hasService(api);
      }
      return false;
   }
   
   private static boolean isRequiredService(Class<?> api)
   {
      return Arrays.binarySearch(requiredServices, api.getName()) >= 0;
   }

   public IEditorActionBarContributor getActionBarContributor()
   {
      return delegate.getActionBarContributor();
   }

   public IActionBars getActionBars()
   {
      return delegate.getActionBars();
   }

   public void registerContextMenu(MenuManager menuManager,
         ISelectionProvider selectionProvider, boolean includeEditorInput)
   {
   }

   public void registerContextMenu(String menuId, MenuManager menuManager,
         ISelectionProvider selectionProvider, boolean includeEditorInput)
   {
   }
}