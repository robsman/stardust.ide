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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


public class MoveLaneCommand extends Command
{
   private boolean changingPools;
   private PoolSymbol pool;
   private ISwimlaneSymbol successor;
   
   private ISwimlaneSymbol parentLane;
   private ISwimlaneSymbol successorSibling;
   
   private LaneSymbol target;

   private CommandUtils.ContainmentState poolBackup;
   private CommandUtils.ContainmentState parentBackup;

   public void setTarget(LaneSymbol target)
   {
      this.target = target;
   }

   public void setPool(PoolSymbol pool)
   {
      setPool(pool, null);
   }

   public void setPool(PoolSymbol pool, LaneSymbol successor)
   {
      this.changingPools = true;
      this.pool = pool;
      this.successor = successor;
   }

   public void setParentLane(ISwimlaneSymbol parentLane)
   {
      setParentLane(parentLane, null);
   }

   public void setParentLane(ISwimlaneSymbol parentLane, ISwimlaneSymbol successor)
   {
      this.parentLane = parentLane;
      this.successorSibling = successor;
   }

   public void setContainer(ISwimlaneSymbol container)
   {
      if (container instanceof PoolSymbol)
      {
         setPool((PoolSymbol) container);
      }
      else if (container instanceof LaneSymbol)
      {
         setPool(((LaneSymbol) container).getParentPool());
      }
      else
      {
         throw new IllegalArgumentException(Diagram_Messages.EX_FailedObtainingParentToll
               + container);
      }
      setParentLane(container);
   }

   public void execute()
   {
      if (null != target.getParentLane())
      {
         this.parentBackup = CommandUtils.backupContainment(target,
               target.getParentLane().getChildLanes());
      }
      if (changingPools && (null != target.getParentPool()))
      {
         this.poolBackup = CommandUtils.backupContainment(target,
               target.getParentPool().getLanes());
      }

      redo();
   }

   public void redo()
   {
      CommandUtils.undoContainment(parentBackup);

      if (changingPools)
      {
         CommandUtils.undoContainment(poolBackup);
         if (null != pool)
         {
            List lanes = pool.getLanes();
            int position = (null != successor) ? lanes.indexOf(successor) : -1;
            if ( -1 != position)
            {
               lanes.add(position, target);
            }
            else
            {
               lanes.add(target);
            }
         }
      }

      ISwimlaneSymbol parent = parentLane;
      if (null == parent)
      {
         parent = pool;
      }
      if (null != parent)
      {
         List lanes = parent.getChildLanes();
         int position = (null != successorSibling) ? lanes.indexOf(successorSibling) : -1;
         if ( -1 != position)
         {
            lanes.add(position, target);
         }
         else
         {
            lanes.add(target);
         }
      }
   }

   public void undo()
   {
      ISwimlaneSymbol parent = parentLane;
      if (null == parent)
      {
         parent = pool;
      }
      if (null != parent)
      {
         parent.getChildLanes().remove(target);
      }

      if (changingPools)
      {
         if (null != pool)
         {
            pool.getLanes().remove(target);
         }
         CommandUtils.redoContainment(poolBackup);
      }

      CommandUtils.redoContainment(parentBackup);
   }
}