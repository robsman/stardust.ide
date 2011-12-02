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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;


public class DeployModelActionDelegate implements IWorkbenchWindowActionDelegate
{
   ISelection selection;
   
   public void init(IWorkbenchWindow window)
   {
   }

   public void run(IAction action)
   {
      IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      if (part instanceof WorkflowModelEditor)         
      {
         WorkflowModelEditor editor = (WorkflowModelEditor) part;
         ModelType model = (ModelType) editor.getModel();
         DeployModelAction.deploy(model);
      }
   }

   public void selectionChanged(IAction action, ISelection selection)
   {
      this.selection = selection;
   }

   public void dispose()
   {
   }
}