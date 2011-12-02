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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteSymbolCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.utils.CheckDeleteConnections;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.ui.IWorkbenchPart;


public class DeleteSymbolAction extends SelectionAction
{
   private CompoundCommand cmd;

   public DeleteSymbolAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.DELETE_SYMBOL);
      setText((Diagram_Messages.LB_ACTION_RemoveSymbol));
   }

   protected boolean calculateEnabled()
   {
      boolean canExecute = false;

      if (checkSymbols())
      {
         Command cmd = createDeleteCommand(getSelectedObjects());
         if (cmd == null)
         {
            return false;
         }
         canExecute = cmd.canExecute();
      }
      return canExecute;
   }

   private boolean checkSymbols()
   {
      boolean hasDeletableSymbols = false;
      boolean isGatewaySymbol = true;
      boolean isPseudoConnection = false;
      boolean isTreeSymbol = true;
      boolean isSwimlane = true;
      boolean isDiagram = true;

      for (Object selectedObj : getSelectedObjects())
      {
         if (selectedObj instanceof EditPart)
         {
            Object element = ((EditPart) selectedObj).getModel();
            isGatewaySymbol = element instanceof GatewaySymbol;
            if (element instanceof TransitionConnectionType)
            {
               TransitionConnectionType trans = (TransitionConnectionType) element;
               isPseudoConnection = (null == trans.getTransition())
                     && ((trans.getSourceActivitySymbol() instanceof GatewaySymbol) || (trans
                           .getTargetActivitySymbol() instanceof GatewaySymbol));
            }
            isTreeSymbol = selectedObj instanceof AbstractEObjectTreeEditPart;
            isDiagram = selectedObj instanceof DiagramEditPart;
            isSwimlane = selectedObj instanceof AbstractSwimlaneEditPart;
            if (isGatewaySymbol || isPseudoConnection || isTreeSymbol || isSwimlane
                  || isDiagram)
            {
               return false;
            }
            hasDeletableSymbols = true;
         }
      }
      return hasDeletableSymbols;
   }

   public void run()
   {
      // 1st check for connections and show a dialog to select from (or cancel)
      // if there is more than one connection
      if(getSelectedObjects().size() == 1)
      {
         // check and maybe set (delete) another connection
         // if user pressed CANCEL when having more than one connection (dialog opens) then return
         WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
         if(!CheckDeleteConnections.checkConnections(getSelectedObjects(), editor, true))
         {
            cmd = null;
            return;
         }
      }
      Command command = createDeleteCommand(getSelectedObjects());      
      if(command.canExecute())
      {
         execute(command);
      }
   }

   public Command createDeleteCommand(List<EditPart> objects)
   {
      cmd = new CompoundCommand();
      if (objects.isEmpty())
      {
         return null;
      }
      WorkflowModelEditor editor = null;
      
      for (EditPart editPart : objects)
      {
         if (editPart instanceof AbstractConnectionEditPart)
         {
            if(editor == null)
            {
               editor = GenericUtils.getWorkflowModelEditor(ModelUtils.findContainingModel((EObject) editPart.getModel()));
            }
            
            ModelServer server = editor.getModelServer();
            if (server != null && server.requireLock((EObject) editPart.getModel()))
            {
               return UnexecutableCommand.INSTANCE;
            }
            cmd.add(new DeleteConnectionSymbolCmd((IConnectionSymbol) editPart.getModel()));
         }
         else if (editPart instanceof AbstractNodeSymbolEditPart)
         {
            if(editor == null)
            {
               editor = GenericUtils.getWorkflowModelEditor(ModelUtils.findContainingModel((EObject) editPart.getModel()));
            }
                        
            if (editor != null)
            {
               ModelServer server = editor.getModelServer();
               if (server != null && server.requireLock((EObject) editPart.getModel()))
               {
                  return UnexecutableCommand.INSTANCE;
               }
               cmd.add(DeleteSymbolCommandFactory
                     .createDeleteSymbolCommand((INodeSymbol) editPart.getModel()));
            }
         }
      }
      return cmd;
   }
}