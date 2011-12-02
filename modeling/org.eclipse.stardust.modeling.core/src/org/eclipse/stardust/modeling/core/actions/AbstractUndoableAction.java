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
package org.eclipse.stardust.modeling.core.actions;

import java.util.Collections;

import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.modeling.core.editors.parts.dialog.ApplyUpdatesCommand;


/**
 * @author rsauer
 * @version $Revision$
 */
public abstract class AbstractUndoableAction extends EditDomainAwareAction
{
   protected abstract void doRun();
   protected Command doRunCommand()
   {
      return null;
   }

   public final void run()
   {
      CompoundCommand cmd = new CompoundCommand();
      
      ChangeRecorder changeRecorder = new ChangeRecorder();
      changeRecorder.beginRecording(Collections.singleton(getModel()));

      doRun();
      Command command = doRunCommand();
      if(command != null)
      {
         cmd.add(command);
      }
      ChangeDescription change = changeRecorder.endRecording();
      cmd.add(new ApplyUpdatesCommand(change));

      if ( !(change.getObjectChanges().isEmpty() && change.getResourceChanges().isEmpty()))
      {
         getEditDomain().getCommandStack().execute(cmd);
      }
   }
}
