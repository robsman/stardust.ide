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

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;


public class CreateDiagramAction extends SelectionAction
{
   public CreateDiagramAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.CREATE_DIAGRAM);
      setText(Diagram_Messages.TXT_NewDiagram);
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/diagram.gif")); //$NON-NLS-1$
   }

   protected boolean calculateEnabled()
   {
      return getSelectedObjects().size() == 1
         && (getProcess() != null || getModel() != null);
   }

   public void run()
   {
      ProcessDefinitionType process = getProcess();
      if (process != null)
      {
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(process);
         if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
         {
            ModelServerUtils.showMessageBox(Diagram_Messages.MSG_LOCK_NEEDED);  
            return;
         }
      }

      final DiagramType[] newDiagram = new DiagramType[1];
      Command cmd = null;
      if (process != null)
      {
         cmd = createProcessDiagramCommand(newDiagram);
      }
      else if (getModel() != null)
      {
         cmd = createModelDiagramCommand(newDiagram);
      }
      if (cmd != null)
      {
         execute(cmd);
         CreationUtils.showInOutlineAndEdit(newDiagram[0]);
      }
   }

   private Command createProcessDiagramCommand(
      final DiagramType[] newObject)
   {
      CreateModelElementCommand createDiagramCommand = new CreateModelElementCommand(
            IContainedElementCommand.PROCESS, null, CarnotWorkflowModelPackage.eINSTANCE
                  .getDiagramType())
      {
         protected IModelElement createModelElement()
         {
            DiagramType diagram = (DiagramType) super.createModelElement();            
            diagram.setName(createDiagramName(getProcess().getDiagram()));
            diagram.setOrientation(OrientationType.VERTICAL_LITERAL.toString().equals(
                  PlatformUI.getPreferenceStore().getString(
                        BpmProjectNature.PREFERENCE_MODELING_DIRECTION)) ?
                  OrientationType.VERTICAL_LITERAL : OrientationType.HORIZONTAL_LITERAL);

            DiagramModeType defaultMode;            
            if (PlatformUI.getPreferenceStore().getBoolean(
                  BpmProjectNature.PREFERENCE_CLASSIC_MODE))
            {
               defaultMode = DiagramModeType.MODE_400_LITERAL;
            }
            else
            {
               defaultMode = DiagramModeType.MODE_450_LITERAL;
            }
            diagram.setMode(defaultMode);            
            
            newObject[0] = diagram;
            PoolSymbol pool = DiagramUtil.createDefaultPool(null);
            diagram.getPoolSymbols().add(pool);

            return diagram;
         }
      };
      createDiagramCommand.setParent(getProcess());
      return createDiagramCommand;
   }

   private CreateModelElementCommand createModelDiagramCommand(
      final DiagramType[] newObject)
   {
      CreateModelElementCommand command = new CreateModelElementCommand(
            IContainedElementCommand.MODEL, null, CarnotWorkflowModelPackage.eINSTANCE
                  .getDiagramType())
      {
         protected IModelElement createModelElement()
         {
            DiagramType diagram = (DiagramType) super.createModelElement();
            diagram.setName(createDiagramName(getModel().getDiagram()));
            newObject[0] = diagram;
            return diagram;
         }
      };
      command.setParent(getModel());
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
   
   // get Name for new Diagram
   private String createDiagramName(List diagrams)
   {
      String name;
      String searchName = Diagram_Messages.DIAGRAM_NAME_Diagram;
      int counter = 1;
      
      for (Iterator i = diagrams.iterator(); i.hasNext();)
      {
         DiagramType o = (DiagramType) i.next();
         String existingName = o.getName(); // may be empty
         if (existingName.startsWith(searchName))
         {
            try
            {
               String sn = existingName.substring(searchName.length()).trim();
               int number = Integer.parseInt(sn);
               if (number >= counter)
               {
                  counter = number + 1;
               }
            }
            catch (NumberFormatException nfe)
            {
               // ignore
            }
         }
      }
      name = searchName + counter;      
      return name;
   }
}