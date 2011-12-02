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

import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;

public class DeleteConnectionSymbolCmd extends DeleteGraphicalObjectCmd
{
   private INodeSymbol sourceBackup;
   private INodeSymbol targetBackup;

   public DeleteConnectionSymbolCmd(IConnectionSymbol conn)
   {
      super(conn);
   }

   public void execute()
   {
      this.sourceBackup = getCastedModel().getSourceNode();
      this.targetBackup = getCastedModel().getTargetNode();

      getCastedModel().setTargetNode(null);
      getCastedModel().setSourceNode(null);

      super.execute();
   }

   public void redo()
   {
      getCastedModel().setTargetNode(null);
      getCastedModel().setSourceNode(null);

      super.redo();
   }

   public void undo()
   {
      super.undo();

      getCastedModel().setSourceNode(sourceBackup);
      getCastedModel().setTargetNode(targetBackup);
   }

   private IConnectionSymbol getCastedModel()
   {
      return (IConnectionSymbol) getTarget();
   }
}