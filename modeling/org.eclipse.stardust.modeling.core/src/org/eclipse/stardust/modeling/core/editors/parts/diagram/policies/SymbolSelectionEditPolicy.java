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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.policies;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;


public class SymbolSelectionEditPolicy extends NonResizableEditPolicy
{
   protected List createSelectionHandles()
   {
      return super.createSelectionHandles();
   }

   public EditPart getTargetEditPart(Request request)
   {
      // TODO Auto-generated method stub
      EditPart result = null;

      if (getHost().getModel() instanceof INodeSymbol)
      {
         IGraphicalObject symbol = (INodeSymbol) getHost().getModel();
         while (symbol.eContainer() instanceof GroupSymbolType)
         {
            symbol = (IGraphicalObject) symbol.eContainer();
         }

         if (getHost().getModel() != symbol)
         {
            result = (EditPart) getHost().getRoot()
                  .getViewer()
                  .getEditPartRegistry()
                  .get(symbol);
         }
      }

      return (null != result) ? result : super.getTargetEditPart(request);
   }
}
