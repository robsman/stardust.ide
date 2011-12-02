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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.SymbolGroupEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.ui.IWorkbenchPart;


/**
 * @author fherinean
 * @version $Revision$
 */
public class UngroupSymbolsAction extends SelectionAction
{
   public UngroupSymbolsAction(IWorkbenchPart part)
   {
      super(part);
      
      setId(DiagramActionConstants.UNGROUP_SYMBOLS);
      setText(Diagram_Messages.UNGROUP_SYMBOLS_LABEL);
   }
   
   protected boolean calculateEnabled()
   {
      Command cmd = createUngroupSymbolsCommand();
      return (null != cmd) ? cmd.canExecute() : false;
   }

   public void run()
   {
      execute(createUngroupSymbolsCommand());
   }

   private Command createUngroupSymbolsCommand()
   {
      CompoundCommand result = new CompoundCommand();
      
      if ((1 == getSelectedObjects().size())
            && (getSelectedObjects().get(0) instanceof SymbolGroupEditPart))
      {
         GroupSymbolType group = (GroupSymbolType) ((SymbolGroupEditPart) getSelectedObjects().get(
               0)).getModel();
         
         EObject container = ModelUtils.findContainingProcess(group);
         if (container == null)
         {
            container = ModelUtils.findContainingDiagram(group);
         }
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(container);
         if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
         {
            return UnexecutableCommand.INSTANCE;                  
         }
         
         
         if (group.eContainer() instanceof ISymbolContainer)
         {
            CompoundCommand cmdAddToDiagram = new CompoundCommand();
            
            for (Iterator i = group.getNodes().valueListIterator(); i.hasNext();)
            {
               INodeSymbol node = (INodeSymbol) i.next();
               
               if (null != node.eContainmentFeature())
               {
                  // remove from group
                  result.add(new DeleteValueCmd(group, node, node.eContainmentFeature()));
                  
                  // adjust coordinates
                  cmdAddToDiagram.add(new SetValueCmd(node,
                        CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_XPos(),
                        new Long(node.getXPos() + group.getXPos())));
                  cmdAddToDiagram.add(new SetValueCmd(node,
                        CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_YPos(),
                        new Long(node.getYPos() + group.getYPos())));
                  
                  // add to group's parent
                  cmdAddToDiagram.add(new SetValueCmd(group.eContainer(),
                        node.eContainmentFeature(), node));
               }
               else
               {
                  result.add(UnexecutableCommand.INSTANCE);
               }
            }
            
            for (Iterator i = group.getConnections().valueListIterator(); i.hasNext();)
            {
               IConnectionSymbol connection = (IConnectionSymbol) i.next();
               
               if (null != connection.eContainmentFeature())
               {
                  // remove from group
                  result.add(new DeleteValueCmd(group, connection,
                        connection.eContainmentFeature()));
                  
                  // add to group's parent
                  result.add(new SetValueCmd(group.eContainer(),
                        connection.eContainmentFeature(), connection));
               }
               else
               {
                  result.add(UnexecutableCommand.INSTANCE);
               }
            }

            result.add(new DeleteValueCmd(group.eContainer(), group,
                  CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_GroupSymbol()));
            
            result.add(cmdAddToDiagram);
         }
         else
         {
            result.add(UnexecutableCommand.INSTANCE);
         }
      }
      else
      {
         result.add(UnexecutableCommand.INSTANCE);
      }

      return result.unwrap();
   }
}
