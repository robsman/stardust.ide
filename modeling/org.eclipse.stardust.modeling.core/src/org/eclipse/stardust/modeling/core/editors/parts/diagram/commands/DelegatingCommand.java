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

public abstract class DelegatingCommand extends Command
{
   private Command delegate;
   
   public void dispose()
   {
      if (delegate != null)
      {
         delegate.dispose();
         delegate = null;
      }
   }

   public void execute()
   {
      delegate = createDelegate();
      if (delegate != null)
      {
         delegate.execute();
      }
   }

   public Command createDelegate()
   {
      return null;
   }

   public void redo()
   {
      if (delegate != null)
      {
         delegate.redo();
      }
   }

   public void undo()
   {
      if (delegate != null)
      {
         delegate.undo();
      }
   }
}