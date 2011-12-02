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
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;


public class ModifyConnectionSymbolCommand extends Command
{
   private final SetSymbolContainerCommand symContainerCmd = new SetSymbolContainerCommand();
   
   private boolean connectSource;

   private INodeSymbol symbol;
   private INodeSymbol symbolBackup;

   private String anchorType;
   private String anchorTypeBackup;

   private IConnectionSymbol connectionSymbol;

   public void setSource(INodeSymbol source)
   {
      symbol = source;
      connectSource = true;
   }

   public void setTarget(INodeSymbol target)
   {
      symbol = target;
      connectSource = false;
   }

   public void setConnection(IConnectionSymbol connection)
   {
      Object container = connection.eContainer();

      if (container instanceof ISymbolContainer)
      {
         setConnection((ISymbolContainer) container, connection);
      }
   }

   public void setConnection(ISymbolContainer container, IConnectionSymbol connection)
   {
      this.connectionSymbol = connection;

      symContainerCmd.setContainer(container, null);
      symContainerCmd.setSymbol(connection);
   }

   public void execute()
   {
      symContainerCmd.execute();
      
      if (connectSource)
      {
         symbolBackup = connectionSymbol.getSourceNode();
         anchorTypeBackup = connectionSymbol.getSourceAnchor();
      }
      else
      {
         symbolBackup = connectionSymbol.getTargetNode();
         anchorTypeBackup = connectionSymbol.getTargetAnchor();
      }

      redo();
   }

   public void redo()
   {
      symContainerCmd.redo();

      if (connectSource)
      {
         // (fh) the order of execution is important, since setting the source node will
         // recompute the anchor and we want to use the new one.
         connectionSymbol.setSourceAnchor(anchorType);
         connectionSymbol.setSourceNode(symbol);
      }
      else
      {
         connectionSymbol.setTargetAnchor(anchorType);
         connectionSymbol.setTargetNode(symbol);
      }
   }

   public void undo()
   {
      if (connectSource)
      {
         connectionSymbol.setSourceAnchor(anchorTypeBackup);
         connectionSymbol.setSourceNode(symbolBackup);
      }
      else
      {
         connectionSymbol.setTargetAnchor(anchorTypeBackup);
         connectionSymbol.setTargetNode(symbolBackup);
      }

      symContainerCmd.undo();
   }

   public void setAnchorType(String anchorType)
   {
      this.anchorType = anchorType;
   }
}