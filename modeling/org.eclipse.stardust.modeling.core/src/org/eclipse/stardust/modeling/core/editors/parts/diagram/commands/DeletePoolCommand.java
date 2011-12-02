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

import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;

public class DeletePoolCommand extends DeleteGraphicalObjectCmd
{
   private CommandUtils.ContainmentState diagramContainmentBackup;

   public DeletePoolCommand(PoolSymbol pool)
   {
      super(pool);
   }

//   private DeleteProcessCommand processCommand;

   public PoolSymbol getPool()
   {
      return (PoolSymbol) getTarget();
   }

   public void execute()
   {
/*
      if (null != getPool().getProcess())
      {
         processCommand = new DeleteProcessCommand();
         processCommand.setProcess(getPool().getProcess());
      }
*/

      diagramContainmentBackup = CommandUtils.backupContainment(getPool(),
            getPool().getDiagram().getPoolSymbols());

/*
      if (null != processCommand)
      {
         processCommand.execute();
      }
*/
      CommandUtils.undoContainment(diagramContainmentBackup);

      super.execute();
   }

   public void redo()
   {
/*
      if (null != processCommand)
      {
         processCommand.redo();
      }
*/
      CommandUtils.undoContainment(diagramContainmentBackup);

      super.redo();
   }

   public void undo()
   {
      super.undo();

      CommandUtils.redoContainment(diagramContainmentBackup);
/*
      if (null != processCommand)
      {
         processCommand.undo();
      }
*/
   }
}