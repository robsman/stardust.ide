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
package org.eclipse.stardust.modeling.core.editors.figures;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.stardust.modeling.core.editors.figures.routers.DiagramShortestPathConnectionRouter;
import org.eclipse.stardust.modeling.core.editors.figures.routers.TransitionConnectionRouter;
import org.eclipse.swt.graphics.Color;


public class AbstractConnectionSymbolFigure extends PolylineConnection
      implements IGraphicalObjectFigure
{
   public static final int ROUTING_DEFAULT = 0;

   public static final int ROUTING_SHORTEST_PATH = -1;

   public static final int ROUTING_MANHATTAN = -2;

   public static final int ROUTING_EXPLICIT = -3;

   private Color defaultBorderColor = ColorConstants.black;

   private Color defaultFillColor = ColorConstants.black;

   private int defaultRouting = ROUTING_SHORTEST_PATH;

   private int routing = defaultRouting;

   public AbstractConnectionSymbolFigure()
   {
      // arrow at target endpoint
      setSourceDecoration(null);
      setTargetDecoration(new PolygonDecoration());

      // this check is kind of stupid, I really should be using an enumerated
      // type for the line style that matches Graphics :/
      /*
       * if (getCastedModel().isDashed()) { connection.setLineStyle(Graphics.LINE_DASH); }
       * else { connection.setLineStyle(Graphics.LINE_SOLID); }
       */
   }

   public void setDefaultBorderColor(Color color)
   {
      this.defaultBorderColor = (null != color) ? color : ColorConstants.black;
      setForegroundColor(null);
   }

   public void setDefaultFillColor(Color color)
   {
      this.defaultFillColor = (null != color) ? color : ColorConstants.black;
      setFillColor(null);
   }

   public void setDefaultRouting(int routing)
   {
      switch (routing)
      {
      case ROUTING_MANHATTAN:
         this.defaultRouting = ROUTING_MANHATTAN;
         break;

      case ROUTING_EXPLICIT:
         this.defaultRouting = ROUTING_EXPLICIT;
         break;

      default:
         this.defaultRouting = ROUTING_SHORTEST_PATH;
         break;
      }

      setRouting(defaultRouting);
   }

   public void setBorderColor(Color color)
   {
      setForegroundColor((null != color) ? color : defaultBorderColor);
   }

   public void setFillColor(Color color)
   {
      setBackgroundColor((null != color) ? color : defaultFillColor);
   }

   public int getRouting()
   {
      return routing;
   }

   public void setRouting(int routing)
   {
      switch (routing)
      {
      case ROUTING_SHORTEST_PATH:
         this.routing = ROUTING_SHORTEST_PATH;
         break;

      case ROUTING_MANHATTAN:
         this.routing = ROUTING_MANHATTAN;
         break;

      case ROUTING_EXPLICIT:
         this.routing = ROUTING_EXPLICIT;
         break;

      default:
         this.routing = defaultRouting;
         break;
      }

      setConnectionRouter();
   }

   public void setConnectionRouter(ConnectionRouter cr)
   {
   // intentionally do nothing
   }

   public void setConnectionRouter()
   {
      ConnectionRouter cr = ConnectionRouter.NULL;
      final ConnectionRouter router = getConnectionRouter();
      ConnectionRouter newRouter = cr;

      if (null != cr) // what is doing this check? cr is never null
      {
         // set router only when the caller didn't originally want to reset the connection
         // router
         switch (routing)
         {
         case ROUTING_SHORTEST_PATH:
            if (cr instanceof DiagramShortestPathConnectionRouter)
            {
               newRouter = cr;
            }
            else if ((getParent() instanceof ConnectionLayer)
                  && (((ConnectionLayer) getParent()).getConnectionRouter() instanceof DiagramShortestPathConnectionRouter))
            {
               newRouter = ((ConnectionLayer) getParent()).getConnectionRouter();
            }
            break;

         case ROUTING_MANHATTAN:
            newRouter = (router instanceof TransitionConnectionRouter)
                  ? router
                  : new TransitionConnectionRouter();
            break;

         case ROUTING_EXPLICIT:
            newRouter = (router instanceof BendpointConnectionRouter)
                  ? router
                  : new BendpointConnectionRouter();
            break;

         default:
            break;
         }
      }

      super.setConnectionRouter(newRouter);
   }

   public void revalidate()
   {
      // TODO copied from parent classes to prevent problem when revalidating removed
      // connections
      invalidate();
      if (getParent() == null || isValidationRoot())
         getUpdateManager().addInvalidFigure(this);
      else
         getParent().revalidate();

      if ((null != getSourceAnchor()) && (null != getTargetAnchor()))
      {
         getConnectionRouter().invalidate(this);
      }
   }
}
