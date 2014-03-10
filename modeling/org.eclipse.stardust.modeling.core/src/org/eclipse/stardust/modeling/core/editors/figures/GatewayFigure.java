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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.GatewaySymbolEditPart;
import org.eclipse.swt.graphics.Color;


/**
 * @author rsauer
 * @version $Revision$
 */
public class GatewayFigure extends Polygon implements IGraphicalObjectFigure
{
   public static final int GATEWAY_TYPE_XOR = 0;
   public static final int GATEWAY_TYPE_OR = 1;
   public static final int GATEWAY_TYPE_AND = 2;
   public static final int GATEWAY_TYPE_COMPLEX = 3;

   public static final int XOR_TYPE_DATA = 0;
   public static final int XOR_TYPE_EVENT = 1;

   private static final int BORDER_LINE_WIDTH = 1;

   private int gatewayType;
   private int kind;

   private Color borderColor;

   @SuppressWarnings("unused")
   private Color fillColor;

   private GatewaySymbolEditPart editPart;

   public GatewayFigure(GatewaySymbolEditPart gatewaySymbolEditPart)
   {
      editPart = gatewaySymbolEditPart;
      setOutline(true);
      setFill(true);
   }

   public GatewaySymbolEditPart getEditPart()
   {
      return editPart;
   }

   public int getKind()
   {
      return kind;
   }

   public void setKind(int kind)
   {
      this.kind = kind;
   }

   public void setTypeIndicator(int gatewayType)
   {
      this.gatewayType = gatewayType;
      repaint();
   }

   protected boolean useLocalCoordinates()
   {
      return true;
   }

   public Dimension getMinimumSize(int wHint, int hHint)
   {
      return new Dimension(40, 40);
   }

   public Dimension getPreferredSize(int wHint, int hHint)
   {
      return getMinimumSize();
   }

   public void setBounds(Rectangle rect)
   {
      removeAllPoints();

      // forcing outline to be of uneven width/height to get smooth edges
      final int width = 2 * ((rect.width + 1) / 2) - 1;
      final int height = 2 * ((rect.height + 1) / 2) - 1;

      // compute points
      int left = rect.x;
      int top = rect.y;
      int center = rect.x + width/2;
      int middle = rect.y + height/2;
      int right = 2 * center - left;
      int bottom = 2 * middle - top;

      addPoint(new Point(center, top));
      addPoint(new Point(left, middle));
      addPoint(new Point(center, bottom));
      addPoint(new Point(right, middle));

      super.setBounds(rect);
   }

   protected void outlineShape(Graphics g)
   {
      g.setLineWidth(BORDER_LINE_WIDTH);
      g.setForegroundColor(borderColor == null ? ColorConstants.gray : borderColor);
      super.outlineShape(g);
      switch (gatewayType)
      {
      case GATEWAY_TYPE_AND:
         g.setLineWidth( 3 * BORDER_LINE_WIDTH);
         Rectangle rect = getBounds();
         /*Point center = rect.getCenter();
         center.translate( -1, -1);
         int width = rect.width / 4;
         int height = rect.height / 4;
         g.drawLine(center.x - width, center.y, center.x + width, center.y);
         g.drawLine(center.x, center.y - height, center.x, center.y + height);*/
         g.drawLine(Math.round(rect.x + rect.width / 4),
                    Math.round(rect.y + rect.height / 2),
                    Math.round(rect.x + rect.width * 3 / 4),
                    Math.round(rect.y + rect.height / 2));
         g.drawLine(Math.round(rect.x + rect.width / 2),
                    Math.round(rect.y + rect.height / 4),
                    Math.round(rect.x + rect.width / 2),
                    Math.round(rect.y + rect.height * 3 / 4));
         break;
      case GATEWAY_TYPE_OR:
         g.setLineWidth( 3 * BORDER_LINE_WIDTH);
         rect = getBounds();
         g.drawOval(Math.round(rect.x + rect.width / 4),
                    Math.round(rect.y + rect.height / 4),
                    Math.round(rect.width / 2),
                    Math.round(rect.height / 2));
      }
   }

   public void setBorderColor(Color color)
   {
      borderColor = color;
   }

   public void setFillColor(Color color)
   {
      fillColor = color;
   }
}