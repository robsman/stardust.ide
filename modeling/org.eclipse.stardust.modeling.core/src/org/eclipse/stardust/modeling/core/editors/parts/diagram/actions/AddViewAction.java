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
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.ViewableType;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;


public class AddViewAction extends Action
{
   private static final String ACTION_NAME = Diagram_Messages.NAME_AddViewAction;

   private ModelType model;

   private EditDomain editDomain;

   private TreeViewer treeViewer;

   private EObject parent;

 
   public AddViewAction(ModelType model, EditDomain editDomain, TreeViewer treeViewer)
   {
      super(ACTION_NAME);
      this.model = model;
      this.editDomain = editDomain;
      this.treeViewer = treeViewer;
   }

   public void run()
   {
      Object element = ((IStructuredSelection) treeViewer.getSelection())
            .getFirstElement();
      if (element instanceof ViewType)
      {
         parent =  (EObject) element;
      }
      else if(element instanceof ViewableType) {
         parent = ((EObject) element).eContainer();
      }
      editDomain.getCommandStack().execute(createCommand());
      parent = null;
   }

   private CreateModelElementCommand createCommand()
   {
      if (parent == null)
      {
         parent = model;
      }
      
      IdFactory idFactory = new IdFactory("View", "View"); //$NON-NLS-1$ //$NON-NLS-2$
      CreateModelElementCommand command = new CreateModelElementCommand(IContainedElementCommand.PARENT, idFactory,
            CarnotWorkflowModelPackage.eINSTANCE.getViewType())
      {

         public void execute()
         {
            super.execute();
            treeViewer.refresh();
            treeViewer.expandToLevel(model, AbstractTreeViewer.ALL_LEVELS);
         }

         public void redo()
         {
            super.redo();
            treeViewer.refresh();
         }

         public void undo()
         {
            super.undo();
            treeViewer.refresh();
         }

      };
      command.setParent(parent);

      return command;
   }

}
