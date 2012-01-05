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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CompoundDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateTypedModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;


public class CreateTriggerAction extends SelectionAction
{
   private IConfigurationElement config;
   protected IModelElement trigger;

   public CreateTriggerAction(IConfigurationElement config, WorkflowModelEditor part)
   {
      super(part);
      this.config = config;
      setId(DiagramActionConstants.CREATE_TRIGGER + config.getAttribute(SpiConstants.ID));
      setText(config.getAttribute(SpiConstants.NAME));
      setImageDescriptor(DiagramPlugin.getImageDescriptor(config));
   }

   protected boolean calculateEnabled()
   {
      return !DiagramPlugin.isBusinessView((WorkflowModelEditor) getWorkbenchPart())
         && getSelectedObjects().size() == 1 && getProcess() != null;
   }

   public void run()
   {
      ProcessDefinitionType process = getProcess();
      Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(process);
      if (lockedByCurrentUser == null || lockedByCurrentUser.equals(Boolean.TRUE))
      {      
         execute(createCommand());
         CreationUtils.showInOutlineAndEdit(trigger);
      }
      else
      {
         ModelServerUtils.showMessageBox(Diagram_Messages.MSG_LOCK_NEEDED);         
      }
   }

   private Command createCommand()
   {
      IdFactory id = new IdFactory(config.getAttribute(SpiConstants.ID), config.getAttribute(SpiConstants.NAME));
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(new CreateMetaTypeCommand(config, CarnotWorkflowModelPackage.eINSTANCE
            .getTriggerTypeType(), new EStructuralFeature[] {}));
      command.add(new CreateTypedModelElementCommand(IContainedElementCommand.PROCESS,
            id, config.getAttribute(SpiConstants.ID),
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_TriggerType(),
            CarnotWorkflowModelPackage.eINSTANCE.getTriggerType())
      {

         protected IModelElement createModelElement()
         {
            IModelElement element = super.createModelElement();
            trigger = element;
            return element;
         }
      });
      command.setParent(getProcess());
      return command;
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
}