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

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.utils.SnapGridUtils;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SetDefaultSizeAction extends SelectionAction
{
   public static final String SET_DEFAULT_SIZE_LABEL = Diagram_Messages.SetDefaultSizeAction_Label;

   public SetDefaultSizeAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.SET_DEFAULT_SIZE);
      setText(SET_DEFAULT_SIZE_LABEL);
//    setToolTipText(GEFMessages.AlignLeftAction_Tooltip);
//    setImageDescriptor(InternalImages.DESC_HORZ_ALIGN_LEFT);
//    setDisabledImageDescriptor(InternalImages.DESC_HORZ_ALIGN_LEFT_DIS);
   }

   protected boolean calculateEnabled()
   {
      Command cmd = createSetDefaultSizeCommand();
      if (cmd == null)
      {
         return false;
      }
      Object part = getSelectedObjects().get(0);
      if(part instanceof AbstractGraphicalEditPart)
      {
         if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) part) != null)
         {
            return false;
         }         
      }
      return cmd.canExecute();
   }

   public void run()
   {
      execute(createSetDefaultSizeCommand());
   }

   private Command createSetDefaultSizeCommand()
   {
      List editparts = getSelectedObjects();
      if (editparts.isEmpty())
      {
         return null;
      }
      return createSetDefaultSizeCommand(editparts);
   }

   private Command createSetDefaultSizeCommand(List editparts)
   {
      CompoundCommand command = new CompoundCommand();
      command.setDebugLabel(getText());
      for (int i = 0; i < editparts.size(); i++)
      {
         Object part = editparts.get(i);
         if (part instanceof GraphicalEditPart)
         {
            addCommand(command, ((GraphicalEditPart) part).getModel());
         }
      }
      return command.unwrap();
   }

   private void addCommand(CompoundCommand command, Object model)
   {
      if (model instanceof ISymbolContainer)
      {
         ISymbolContainer container = (ISymbolContainer) model;

         CompoundCommand cmd = new CompoundCommand();
         cmd.setDebugLabel(container.eClass().getName());

         List nodes = container.getNodes();
         for (int i = 0; i < nodes.size(); i++)
         {
            FeatureMap.Entry entry = (FeatureMap.Entry) nodes.get(i);
            addCommand(cmd, entry.getValue());
         }

         if (!cmd.isEmpty())
         {
            command.add(cmd);
         }
      }
      else if (model instanceof INodeSymbol)
      {
         EObject container = ModelUtils.findContainingProcess((EObject) model);
         if (container == null)
         {
            container = ModelUtils.findContainingDiagram((IGraphicalObject) model);
         }
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(container);
         if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
         {
            command.add(UnexecutableCommand.INSTANCE);
         }                  
         
         INodeSymbol symbol = (INodeSymbol) model;

         CompoundCommand cmd = new CompoundCommand();
         cmd.setDebugLabel(symbol.eClass().getName());

         int defWidth = symbol instanceof GatewaySymbol ? 40 : -1;
         if (symbol.getWidth() != defWidth)
         {
            cmd.add(new SetValueCmd(symbol,
               CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_Width(),
               new Integer(defWidth)));
         }

         int defHeigth = symbol instanceof GatewaySymbol ? 40 : -1;
         if (symbol.getHeight() != defHeigth)
         {
            cmd.add(new SetValueCmd(symbol,
               CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_Height(),
               new Integer(defHeigth)));
         }

         if (!cmd.isEmpty())
         {
            command.add(cmd);
         }
      }      
   }
}