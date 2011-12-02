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

import java.util.Arrays;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.Coordinates;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


/**
 * @author rsauer
 * @version $Revision$
 */
public class ConnectionBendpointsCommand extends Command
{
   private static final int CMD_INSERT_BENDPOINT = -1;
   private static final int CMD_MOVE_BENDPOINT = -2;
   private static final int CMD_REMOVE_BENDPOINT = -3;
   private static final int CMD_REMOVE_ALL_BENDPOINTS = -4;

   private final int cmdKind;

   private final IConnectionSymbol target;

   private int index;
   private Point location;

   private Coordinates[] coordinatesBackup;

   public static ConnectionBendpointsCommand insertBendpoint(
         IConnectionSymbol connection, Point location, int insertAt)
   {
      ConnectionBendpointsCommand result = new ConnectionBendpointsCommand(
            CMD_INSERT_BENDPOINT, connection);
      result.setNewBendpoint(insertAt, location);
      return result;
   }

   public static ConnectionBendpointsCommand moveBendpoint(
         IConnectionSymbol connection, Point newLocation, int bendpointIdx)
   {
      ConnectionBendpointsCommand result = new ConnectionBendpointsCommand(
            CMD_MOVE_BENDPOINT, connection);
      result.setMovedBendpoint(bendpointIdx, newLocation);
      return result;
   }

   public static ConnectionBendpointsCommand removeBendpoint(
         IConnectionSymbol connection, int bendpointIdx)
   {
      ConnectionBendpointsCommand result = new ConnectionBendpointsCommand(
            CMD_REMOVE_BENDPOINT, connection);
      result.setRemovedBendpoint(bendpointIdx);
      return result;
   }

   public static ConnectionBendpointsCommand removeAllBendpoints(
         IConnectionSymbol connection)
   {
      return new ConnectionBendpointsCommand(CMD_REMOVE_ALL_BENDPOINTS, connection);
   }

   ConnectionBendpointsCommand(int cmdKind, IConnectionSymbol target)
   {
      this.cmdKind = cmdKind;
      this.target = target;
   }

   public void setNewBendpoint(int index, Point location)
   {
      if (CMD_INSERT_BENDPOINT != cmdKind)
      {
         throw new UnsupportedOperationException(
               Diagram_Messages.EX_MustNotInsertBendpoint);
      }

      this.index = index;
      this.location = (null != location) ? location.getCopy() : null;
   }

   public void setMovedBendpoint(int index, Point location)
   {
      if (CMD_MOVE_BENDPOINT != cmdKind)
      {
         throw new UnsupportedOperationException(
               Diagram_Messages.EX_MustNotMoveBendpoint);
      }

      this.index = index;
      this.location = (null != location) ? location.getCopy() : null;
   }

   public void setRemovedBendpoint(int index)
   {
      if (CMD_REMOVE_BENDPOINT != cmdKind)
      {
         throw new UnsupportedOperationException(
               Diagram_Messages.EX_MustNotRemoveBendpoint);
      }

      this.index = index;
      this.location = null;
   }

   public void execute()
   {
      if ((CMD_MOVE_BENDPOINT == cmdKind) || (CMD_REMOVE_BENDPOINT == cmdKind))
      {
         this.coordinatesBackup = new Coordinates[1];
         coordinatesBackup[0] = CarnotWorkflowModelFactory.eINSTANCE.createCoordinates();
         Coordinates c = (Coordinates) target.getCoordinates().get(index);
         this.coordinatesBackup[0].setXPos(c.getXPos());
         this.coordinatesBackup[0].setYPos(c.getYPos());
      }
      else if (CMD_REMOVE_ALL_BENDPOINTS == cmdKind)
      {
         this.coordinatesBackup = new Coordinates[target.getCoordinates().size()];
         target.getCoordinates().toArray(coordinatesBackup);
      }

      redo();
   }

   public void redo()
   {
      if (CMD_INSERT_BENDPOINT == cmdKind)
      {
         Coordinates newCoordinates = CarnotWorkflowModelPackage.eINSTANCE.getCarnotWorkflowModelFactory()
               .createCoordinates();
         newCoordinates.setXPos(location.x);
         newCoordinates.setYPos(location.y);

         target.getCoordinates().add(index, newCoordinates);
      }
      else if (CMD_MOVE_BENDPOINT == cmdKind)
      {
         Coordinates coordinates = (Coordinates) target.getCoordinates().get(index);
         coordinates.setXPos(location.x);
         coordinates.setYPos(location.y);
      }
      else if (CMD_REMOVE_BENDPOINT == cmdKind)
      {
         target.getCoordinates().remove(index);
      }
      else if (CMD_REMOVE_ALL_BENDPOINTS == cmdKind)
      {
         target.getCoordinates().clear();
      }
      else
      {
         // TODO warn
      }
   }

   public void undo()
   {
      if (CMD_INSERT_BENDPOINT == cmdKind)
      {
         target.getCoordinates().remove(index);
      }
      else if (CMD_MOVE_BENDPOINT == cmdKind)
      {
         Coordinates coordinates = (Coordinates) target.getCoordinates().get(index);
         coordinates.setXPos(coordinatesBackup[0].getXPos());
         coordinates.setYPos(coordinatesBackup[0].getYPos());
      }
      else if (CMD_REMOVE_BENDPOINT == cmdKind)
      {
         target.getCoordinates().add(index, coordinatesBackup[0]);
      }
      else if (CMD_REMOVE_ALL_BENDPOINTS == cmdKind)
      {
         target.getCoordinates().addAll(Arrays.asList(coordinatesBackup));
      }
      else
      {
         // TODO warn
      }
   }
}