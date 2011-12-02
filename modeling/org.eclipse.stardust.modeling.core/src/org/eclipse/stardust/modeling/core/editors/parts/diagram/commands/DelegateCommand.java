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

/**
 * @author fherinean
 * @version $Revision$
 */
public class DelegateCommand extends Command
{
   private Command delegate;

   public void setDelegate(Command delegate)
   {
      this.delegate = delegate;
   }

   public boolean canExecute()
   {
      return true;
   }

   public void undo()
   {
      if (delegate != null)
      {
         delegate.undo();
      }
   }

   public void redo()
   {
      if (delegate != null)
      {
         delegate.redo();
      }
   }

   public void execute()
   {
      if (delegate != null)
      {
         delegate.execute();
      }
   }
}
