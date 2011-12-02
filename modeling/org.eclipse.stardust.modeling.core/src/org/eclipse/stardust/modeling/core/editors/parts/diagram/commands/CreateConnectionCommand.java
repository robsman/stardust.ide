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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.common.ui.IdFactory;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CreateConnectionCommand extends CreateModelElementCommand implements IConnectionCommand
{
   protected IIdentifiableModelElement source;
   protected IIdentifiableModelElement target;

   public CreateConnectionCommand(int parentLevel, IdFactory id, EClass eClass)
   {
      super(parentLevel, id, eClass);
   }

   public void setTargetSymbol(INodeSymbol targetSymbol)
   {
      if (targetSymbol instanceof IModelElementNodeSymbol)
      {
         target = ((IModelElementNodeSymbol) targetSymbol).getModelElement();
      }
      // todo: workaround for Gateways
      else if (targetSymbol instanceof GatewaySymbol)
      {
         target = ((GatewaySymbol) targetSymbol).getActivitySymbol().getModelElement();
      }
   }

   public void setSourceSymbol(INodeSymbol sourceSymbol)
   {
      if (sourceSymbol instanceof IModelElementNodeSymbol)
      {
         source = ((IModelElementNodeSymbol) sourceSymbol).getModelElement();
      }
      // todo: workaround for Gateways
      else if (sourceSymbol instanceof GatewaySymbol)
      {
         source = ((GatewaySymbol) sourceSymbol).getActivitySymbol().getModelElement();
      }
   }

   public void setTargetAnchorType(String anchorType)
   {
   }

   public void setSourceAnchorType(String anchorType)
   {
   }

   public void setLocation(Rectangle location)
   {
   }
   
   public Rectangle getLocation()
   {
      return null;
   }
}
