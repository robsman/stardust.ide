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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.ui.IWorkbenchPart;


public class CreateLinkTypeAction extends SelectionAction
{
   protected IModelElement linkType;

   public CreateLinkTypeAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.CREATE_LINK_TYPE);
      setText(Diagram_Messages.LB_NewLinkType);
      setImageDescriptor(DiagramPlugin
            .getImageDescriptor("icons/full/obj16/link_type.gif")); //$NON-NLS-1$
   }

   protected boolean calculateEnabled()
   {
      return getSelectedObjects().size() == 1 && getModel() != null;
   }

   public void run()
   {
      execute(createCommand());
      CreationUtils.showInOutlineAndEdit(linkType);
   }

   private CreateModelElementCommand createCommand()
   {
      IdFactory id = new IdFactory("LinkType", "Link Type"); //$NON-NLS-1$ //$NON-NLS-2$
      CreateModelElementCommand command = new CreateModelElementCommand(
            IContainedElementCommand.MODEL, id, CarnotWorkflowModelPackage.eINSTANCE
                  .getLinkTypeType())
      {

         protected IModelElement createModelElement()
         {
            IModelElement element = super.createModelElement();
            linkType = element;
            return element;
         }

      };
      command.setParent(getModel());
      return command;
   }

   private ModelType getModel()
   {
      Object selection = getSelectedObjects().get(0);
      if (selection instanceof EditPart)
      {
         Object model = ((EditPart) selection).getModel();
         if (model instanceof ModelType)
         {
            return (ModelType) model;
         }
      }
      return null;
   }
}