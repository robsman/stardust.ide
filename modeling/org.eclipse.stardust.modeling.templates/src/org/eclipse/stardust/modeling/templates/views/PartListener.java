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

import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;


public class PartListener implements IPartListener
{
   private WorkflowModelEditorAction action;
   private Set registeredEditors = new HashSet();

   public PartListener(WorkflowModelEditorAction action)
   {
      this.action = action;
   }

   public void partActivated(IWorkbenchPart part)
   {
      // (fh) ignore
   }

   public void partBroughtToTop(IWorkbenchPart part)
   {
      // (fh) ignore
   }

   public void partClosed(IWorkbenchPart part)
   {
      removeAction(part);
   }

   public void partDeactivated(IWorkbenchPart part)
   {
      // (fh) ignore
   }

   public void partOpened(IWorkbenchPart part)
   {
      registerAction(part);
   }

   public void register(IWorkbenchPage page)
   {
      page.addPartListener(this);
      IEditorReference[] refs = page.getEditorReferences();
      for (int i = 0; i < refs.length; i++)
      {
         IEditorPart editor = refs[i].getEditor(false);
         registerAction((IWorkbenchPart) editor);
      }
   }

   public void unregister(IWorkbenchPage page)
   {
      page.removePartListener(this);
      IEditorReference[] refs = page.getEditorReferences();
      for (int i = 0; i < refs.length; i++)
      {
         IEditorPart editor = refs[i].getEditor(false);
         removeAction((IWorkbenchPart) editor);
      }
   }

   private void registerAction(IWorkbenchPart part)
   {
      if (part instanceof WorkflowModelEditor && !registeredEditors.contains(part))
      {
         registeredEditors.add(part);
         action.register((WorkflowModelEditor) part);
      }
   }

   private void removeAction(IWorkbenchPart part)
   {
      if (part instanceof WorkflowModelEditor && registeredEditors.contains(part))
      {
         registeredEditors.remove(part);
         action.unregister((WorkflowModelEditor) part);
      }
   }
}
