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
package org.eclipse.stardust.modeling.core.editors.dnd;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.DiagramPlugin;


/**
 * @author rsauer
 * @version $Revision$
 */
public abstract class IGatewayLocator
{
   public abstract Dimension getGatewayLocation(FlowControlType gatewayKind, Dimension activitySize, int gatewaySize);

   public static IGatewayLocator getDefaultLocator(final INodeSymbol symbol)
   {
      return new IGatewayLocator()
      {
         public Dimension getGatewayLocation(FlowControlType gatewayKind,
               Dimension activitySize, int gatewaySize)
         {
            int width = (activitySize.width - gatewaySize) / 2;
            int height = 0;
   
            if (DiagramPlugin.isVerticalModelling(symbol))
            {
               if (FlowControlType.JOIN_LITERAL.equals(gatewayKind))
               {
                  height = - (activitySize.height + 3 * gatewaySize) / 2;
               }
               else if (FlowControlType.SPLIT_LITERAL.equals(gatewayKind))
               {
                  height = (activitySize.height + 3 * gatewaySize) / 2;
               }
            }
            else
            {
               if (FlowControlType.JOIN_LITERAL.equals(gatewayKind))
               {
                  width -= (activitySize.width + 2 * gatewaySize) / 2;
               }
               else if (FlowControlType.SPLIT_LITERAL.equals(gatewayKind))
               {
                  width += (activitySize.width + 3 * gatewaySize) / 2;
               }
            }
   
            return new Dimension(width, height);
         }
      };
   }
}
