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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.modeling.common.ui.IdFactory;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CreateConnectionSymbolCommand extends CreateSymbolCommand
      implements IConnectionCommand
{
   private INodeSymbol sourceSymbol;

   private INodeSymbol targetSymbol;

   protected String targetAnchorType;

   protected String sourceAnchorType;

   public CreateConnectionSymbolCommand(IdFactory id, EClass eClass)
   {
      super(PARENT, id, eClass);
   }

   public void setTargetSymbol(INodeSymbol activitySymbol)
   {
      this.targetSymbol = activitySymbol;
   }

   public void setSourceSymbol(INodeSymbol eventSymbol)
   {
      this.sourceSymbol = eventSymbol;
   }

   public void setTargetAnchorType(String anchorType)
   {
      this.targetAnchorType = anchorType;
   }

   public void setSourceAnchorType(String anchorType)
   {
      this.sourceAnchorType = anchorType;
   }

   public void undo()
   {
      super.undo();
      IConnectionSymbol connection = (IConnectionSymbol) getModelElement();
      if (connection != null)
      {
         connection.setSourceNode(null);
         connection.setTargetNode(null);
      }
   }

   public void redo()
   {
      IConnectionSymbol connection = (IConnectionSymbol) getModelElement();
      if (connection != null)
      {
         connection.setSourceNode(getSourceSymbol());
         connection.setTargetNode(getTargetSymbol());
      }
      super.redo();
   }

   protected List getContainingFeatureList()
   {
      return ((ISymbolContainer) getContainer()).getConnectionContainingFeatures();
   }

   public INodeSymbol getSourceSymbol()
   {
      return sourceSymbol;
   }

   public INodeSymbol getTargetSymbol()
   {
      return targetSymbol;
   }
}
