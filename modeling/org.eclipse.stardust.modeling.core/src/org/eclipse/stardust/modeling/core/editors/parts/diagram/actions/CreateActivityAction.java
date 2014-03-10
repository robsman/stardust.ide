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

import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;

public class CreateActivityAction extends SelectionAction
{
   private ActivityImplementationType implementation;
   private IModelElement activity;

   public CreateActivityAction(ActivityImplementationType implementation,
         String actionID, WorkflowModelEditor editor)
   {
      super(editor);

      setId(actionID);
      if (implementation == null)
      {
         setText(Diagram_Messages.LB_SUBMENU_NewActivity);
         setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/activity.gif")); //$NON-NLS-1$
      }
      else
      {
         setText(ModelUtils.getActivityImplementationTypeText(implementation)
               + Diagram_Messages.TXT_Activity);
         setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/activity_" //$NON-NLS-1$
               + implementation.getName().toLowerCase() + ".gif")); //$NON-NLS-1$
      }
      this.implementation = implementation;
   }

   protected boolean calculateEnabled()
   {
      return (implementation != null
         && getSelectedObjects().size() == 1 && getProcess() != null);
   }

   private ProcessDefinitionType getProcess()
   {
      Object selection = getSelectedObjects().get(0);
      if (selection instanceof EditPart)
      {
         Object model = ((EditPart) selection).getModel();
         if (model instanceof ProcessDefinitionType)
         {
            return (ProcessDefinitionType) model;
         }
      }
      return null;
   }

   public void run()
   {
      ProcessDefinitionType process = getProcess();
      execute(createCommand(process));
      CreationUtils.showInOutlineAndEdit(activity);
   }

   private CreateModelElementCommand createCommand(ProcessDefinitionType process)
   {
      IdFactory id = new IdFactory(Diagram_Messages.ID_Activity, implementation == null
            ? Diagram_Messages.BASENAME_Activity
            : ModelUtils.getActivityImplementationTypeText(implementation)
                  + Diagram_Messages.BASENAME_Activity);
      CreateModelElementCommand command = new CreateModelElementCommand(
            IContainedElementCommand.PROCESS, id,
            CarnotWorkflowModelPackage.eINSTANCE.getActivityType())
      {
         protected IModelElement createModelElement()
         {
            IModelElement modelElement = super.createModelElement();
            if (modelElement instanceof ActivityType && implementation != null)
            {
               ((ActivityType) modelElement).setImplementation(implementation);
            }
            if (modelElement instanceof ActivityType
               && ActivityImplementationType.SUBPROCESS_LITERAL.equals(implementation))
            {
               ((ActivityType) modelElement).setSubProcessMode(
                     SubProcessModeType.SYNC_SHARED_LITERAL);
            }
            activity = modelElement;
            return modelElement;
         }
      };
      command.setParent(process);

      return command;
   }
}