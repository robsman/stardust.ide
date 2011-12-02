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
package org.eclipse.stardust.modeling.core.editors.parts.dialog;

import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.gef.commands.Command;

public class ApplyUpdatesCommand extends Command
{
   private final ChangeDescription changes;
   
   public ApplyUpdatesCommand(ChangeDescription changes)
   {
      this.changes = changes;
   }

   public void execute()
   {
      // nothing to be done, as the command receives a descriptions of changes already
      // applied
   }

   public void undo()
   {
      changes.applyAndReverse();
   }

   public void redo()
   {
      changes.applyAndReverse();
   }
   
   public boolean hasChanges()
   {
      return !changes.getObjectChanges().isEmpty()
          || !changes.getObjectsToAttach().isEmpty()
          || !changes.getObjectsToDetach().isEmpty()
          || !changes.getResourceChanges().isEmpty();
   }
}
