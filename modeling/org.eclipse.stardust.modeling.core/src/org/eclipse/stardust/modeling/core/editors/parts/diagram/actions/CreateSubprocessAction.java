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

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ActivityCommandFactory;


public class CreateSubprocessAction extends SelectionAction
{

   private ActivityType activity;

   public CreateSubprocessAction(WorkflowModelEditor part)
   {
      super(part);
      setId(DiagramActionConstants.CREATE_SUBPROCESS);
      setText(Diagram_Messages.CreateSubprocessAction_LB_NewSubprocess);
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/process.gif")); //$NON-NLS-1$
   }

   protected boolean calculateEnabled()
   {
      return true;
   }

   public void run()
   {
      final DiagramType[] newDiagram = new DiagramType[1];
      execute(createCommand(newDiagram));
      if (getWorkbenchPart() instanceof WorkflowModelEditor)
      {
         try
         {
            ((WorkflowModelEditor) getWorkbenchPart()).showDiagramPage(newDiagram[0]);
         }
         catch (PartInitException e)
         {
            // e.printStackTrace(); // ignore
         }
      }
   }

   private CompoundCommand createCommand(final DiagramType[] newDiagram)
   {
      final CompoundCommand compoundCommand = new CompoundCommand();

      IdFactory id = new IdFactory(Diagram_Messages.ID_ProcessDefinition,
            Diagram_Messages.BASENAME_ProcessDefinition);
      final CreateModelElementCommand command = new CreateModelElementCommand(
            IContainedElementCommand.MODEL, id, CarnotWorkflowModelPackage.eINSTANCE
                  .getProcessDefinitionType())
      {
         protected IModelElement createModelElement()
         {
            ProcessDefinitionType process = (ProcessDefinitionType) super
                  .createModelElement();
            DiagramType diagram = CarnotWorkflowModelFactory.eINSTANCE
            	.createDiagramType();
            diagram.setName(Diagram_Messages.DIAGRAM_NAME_Default);
            diagram.setOrientation(OrientationType.VERTICAL_LITERAL.toString().equals(
            PlatformUI.getPreferenceStore().getString(
                BpmProjectNature.PREFERENCE_MODELING_DIRECTION)) ?
                OrientationType.VERTICAL_LITERAL : OrientationType.HORIZONTAL_LITERAL);
            process.getDiagram().add(diagram);
            newDiagram[0] = diagram;
            PoolSymbol pool = DiagramUtil.createDefaultPool(null);
            diagram.getPoolSymbols().add(pool);

            compoundCommand.add(ActivityCommandFactory.getSetSubprocessCommand(activity,
                  process));
            if (PlatformUI.getPreferenceStore().getBoolean(
                  BpmProjectNature.PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION))
            {
               compoundCommand.add(new SetValueCmd(activity,
                     CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name(),
                     process.getName()));
            }
            return process;
         }
      };
      command.setParent(getModel());
      compoundCommand.add(command);
      return compoundCommand;
   }

   public void setActivity(ActivityType activity)
   {
      this.activity = activity;
   }

   protected ModelType getModel()
   {
      return ModelUtils.findContainingModel(activity);
   }
}
