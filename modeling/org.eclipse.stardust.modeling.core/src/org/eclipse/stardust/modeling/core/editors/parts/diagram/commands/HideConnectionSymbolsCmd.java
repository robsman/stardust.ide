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
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;


public class HideConnectionSymbolsCmd extends Command
{
   private final AbstractNodeSymbolEditPart target;
   
   public HideConnectionSymbolsCmd(AbstractNodeSymbolEditPart target)
   {
      this.target = target;
   }

   public void execute()
   {
      redo();
   }

   public void redo()
   {
      target.setShowingConnections(false);
   }

   public void undo()
   {
      target.setShowingConnections(true);
   }
}
