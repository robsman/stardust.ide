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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteAllCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeletePoolCommand;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;


public class DiagramComponentEditPolicy extends ComponentEditPolicy
{   
   protected Command createDeleteCommand(final GroupRequest deleteRequest)
   {
      Command cmd = UnexecutableCommand.INSTANCE;
      
      if (getHost() instanceof LaneEditPart)
      {
         CompoundCommand compound = new CompoundCommand();
         addDeleteLaneCommands(compound, ((LaneEditPart) getHost()).getLaneModel());
         DiagramType diagram = null;
         if(getHost().getModel() instanceof DiagramType)
         {
            diagram = (DiagramType) getHost().getModel();
         }
         else
         {                  
            diagram = ModelUtils.findContainingDiagram((IGraphicalObject) getHost().getModel());      
         }
         // classic mode off 
         if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL))
         {
            final AbstractSwimlaneEditPart parent = (AbstractSwimlaneEditPart) getHost().getParent();
            compound.add(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  PoolLaneUtils.setResizeFlags(-1);                  
                  return PoolLaneUtils.reorderLanes(parent, new Integer(PoolLaneUtils.CHILD_LANES_SAME_SIZE));
               }
            });
         }
         cmd = compound;
      }
      else if (getHost() instanceof PoolEditPart)
      {
         PoolSymbol pool = ((PoolEditPart) getHost()).getPoolModel();
         
         CompoundCommand cmdDelPool = new CompoundCommand();
         for (Iterator i = pool.getChildLanes().iterator(); i.hasNext();)
         {
            LaneSymbol lane = (LaneSymbol) i.next();
            cmdDelPool.add(DeleteAllCommandFactory.createDeleteAllCommand(lane));
         }
         cmdDelPool.add(new DeletePoolCommand(pool));
         
         cmd = cmdDelPool;
      }
      else if (getHost() instanceof DiagramEditPart)
      {
         cmd = DeleteAllCommandFactory.createDeleteAllCommand(
            (IModelElement) getHost().getModel());
      }
      return cmd;
   }

   private void addDeleteLaneCommands(CompoundCommand compound, LaneSymbol lane)
   {
      // duplicate the children list because we must delete the children after we delete the parent
      List children = new ArrayList();
      children.addAll(lane.getChildLanes());
      
      compound.add(DeleteAllCommandFactory.createDeleteAllCommand(lane));
      for (int i = 0; i < children.size(); i++)
      {
         addDeleteLaneCommands(compound, (LaneSymbol) children.get(i));
      }
   }
}
