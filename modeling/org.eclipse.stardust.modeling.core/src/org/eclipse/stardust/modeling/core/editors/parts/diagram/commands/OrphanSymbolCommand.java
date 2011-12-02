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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


public class OrphanSymbolCommand extends Command
{
   private ISymbolContainer symbolContainer;
   private IGraphicalObject symbol;

   private EStructuralFeature symbolContainmentFeature;

   private Long symbolElementOidBackup;

   public void setContainer(ISymbolContainer container)
   {
      this.symbolContainer = container;
   }

   public void setSymbolSymbol(IGraphicalObject symbol, EStructuralFeature containmentFeature)
   {
      this.symbol = symbol;
      this.symbolContainmentFeature = containmentFeature;
   }

   public void execute()
   {
      this.symbolElementOidBackup = symbol.isSetElementOid() ? new Long(
            symbol.getElementOid()) : null;

      redo();
   }

   public void redo()
   {
      if (null != symbol)
      {
         if ((null == symbolContainer) || (null == symbolContainmentFeature))
         {
            throw new RuntimeException(Diagram_Messages.EX_MissingNodeSymbolContainer + symbol);
         }

         EList symbolContainment = (EList) symbolContainer.eGet(symbolContainmentFeature);
         symbolContainment.remove(symbol);
         if (null != symbolElementOidBackup)
         {
            symbol.setElementOid(symbolElementOidBackup.longValue());
         }
         else
         {
            symbol.unsetElementOid();
         }
      }
   }

   public void undo()
   {
      if (null != symbol)
      {
         if ((null == symbolContainer) || (null == symbolContainmentFeature))
         {
            throw new RuntimeException(Diagram_Messages.EX_MissingNodeSymbolContainer + symbol);
         }

         EList symbolContainment = (EList) symbolContainer.eGet(symbolContainmentFeature);
         if ( !symbol.isSetElementOid())
         {
            symbol.unsetElementOid();
         }
         symbolContainment.add(symbol);
      }
   }
}