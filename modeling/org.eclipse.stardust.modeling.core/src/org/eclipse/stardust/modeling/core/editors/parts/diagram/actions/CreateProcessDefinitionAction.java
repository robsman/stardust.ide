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
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
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
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;


public class CreateProcessDefinitionAction extends SelectionAction
{
   private ProcessDefinitionType process;
   
   public CreateProcessDefinitionAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.CREATE_PROCESS_DEFINITION);
      setText(Diagram_Messages.TXT_NewProcessDefinition);
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/process.gif")); //$NON-NLS-1$
   }

   protected boolean calculateEnabled()
   {
      return getSelectedObjects().size() == 1 && getModel() != null;
   }

   public void run()
   {
      final DiagramType[] newDiagram = new DiagramType[1];
      execute(createCommand(newDiagram));
      CreationUtils.showInOutlineAndEdit(process);      
   }

   private Command createCommand(final DiagramType[] newDiagram)
   {
      IdFactory id = new IdFactory(Diagram_Messages.ID_ProcessDefinition, Diagram_Messages.BASENAME_ProcessDefinition); 
      CreateModelElementCommand createProcessCommand = new CreateModelElementCommand(
            IContainedElementCommand.MODEL, id, CarnotWorkflowModelPackage.eINSTANCE
                  .getProcessDefinitionType())
      {
         protected IModelElement createModelElement()
         {
            // dirty fix for duplicate oids: diagram+1, defaultPool+2
            process = (ProcessDefinitionType) super
                  .createModelElement();
            DiagramType diagram = CarnotWorkflowModelFactory.eINSTANCE
                  .createDiagramType();
            diagram.setElementOid(ModelUtils.getElementOid(diagram, getModel()) + 1);
            diagram.setName(Diagram_Messages.DIAGRAM_NAME_Default);
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
            
            process.getDiagram().add(diagram);
            newDiagram[0] = diagram;
            PoolSymbol pool = DiagramUtil.createDefaultPool(null);
            pool.setElementOid(ModelUtils.getElementOid(pool, getModel()) + 2);
            diagram.getPoolSymbols().add(pool);
            return process;
         }
      };
      createProcessCommand.setParent(getModel());
      return createProcessCommand;
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