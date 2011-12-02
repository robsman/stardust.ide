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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;


public class DeleteViewAction extends Action
{
   private static final String ACTION_NAME = Diagram_Messages.NAME_DeleteViewAction;

   private EditDomain editDomain;

   private TreeViewer viewer;

   public DeleteViewAction(EditDomain editDomain, TreeViewer treeViewer)
   {
      super(ACTION_NAME);
      this.editDomain = editDomain;
      this.viewer = treeViewer;
   }

   public void run()
   {
      editDomain.getCommandStack().execute(createCommand());
   }



   private Command createCommand()
   {
      EObject element = (EObject) ((IStructuredSelection) viewer.getSelection())
            .getFirstElement();
      DeleteValueCmd command = new DeleteValueCmd(element.eContainer(), element, element
            .eContainingFeature())
      {
         public void execute()
         {
            super.execute();
            viewer.refresh();
         }

         public void redo()
         {
            super.redo();
            viewer.refresh();
         }

         public void undo()
         {
            super.undo();
            viewer.refresh();
         }

      };
      return command;
   }
}
