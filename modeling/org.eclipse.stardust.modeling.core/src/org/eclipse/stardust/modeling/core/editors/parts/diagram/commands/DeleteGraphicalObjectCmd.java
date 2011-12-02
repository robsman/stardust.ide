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

import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


public class DeleteGraphicalObjectCmd extends Command
{
   private IGraphicalObject target;

   private CommandUtils.ContainmentState containmentBackup;

   public DeleteGraphicalObjectCmd(IGraphicalObject target)
   {
      this.target = target;
   }

   public IGraphicalObject getTarget()
   {
      return target;
   }

   public void execute()
   {
      if (null != target.eContainer())
      {
         if (target.eContainer() instanceof ISymbolContainer)
         {
            this.containmentBackup = CommandUtils.backupContainment(target);
            
            // TODO add missing types
            if (false)
            {
               throw new UnsupportedOperationException(
                     Diagram_Messages.EX_TargetNeitherNodeNorConnection + target + "."); //$NON-NLS-1$ 
            }
         }
         else
         {
            throw new UnsupportedOperationException(
                  Diagram_Messages.EX_TargetNotContained + target.eContainer()
                        + "."); //$NON-NLS-1$
         }
      }
      
      CommandUtils.undoContainment(containmentBackup);
   }

   public void redo()
   {
      CommandUtils.undoContainment(containmentBackup);
   }

   public void undo()
   {
      CommandUtils.redoContainment(containmentBackup);
   }
}