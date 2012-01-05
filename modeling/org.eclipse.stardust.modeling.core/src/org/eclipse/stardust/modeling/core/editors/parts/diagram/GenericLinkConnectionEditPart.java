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
package org.eclipse.stardust.modeling.core.editors.parts.diagram;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.LinkColor;
import org.eclipse.stardust.model.xpdl.carnot.LinkEndStyle;
import org.eclipse.stardust.model.xpdl.carnot.LinkLineStyle;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.swt.graphics.Color;


public class GenericLinkConnectionEditPart extends AbstractConnectionSymbolEditPart
{
   private static final Color[] COLOR_MAPPING = {
      ColorConstants.black,
      ColorConstants.darkBlue,
      ColorConstants.darkGray,
      ColorConstants.blue,
      ColorConstants.lightGray,
      ColorConstants.red,
      ColorConstants.yellow
   };

   private static final int[] STYLE_MAPPING = {
      Graphics.LINE_SOLID,
      Graphics.LINE_DOT,
      Graphics.LINE_DASH
   };

   /** Template for a rhombus that points to the right when the rotation angle is 0 */
   public static final PointList RHOMBUS = new PointList();
   static {
      RHOMBUS.addPoint(0, 0);
      RHOMBUS.addPoint(-1, 1);
      RHOMBUS.addPoint(-2, 0);
      RHOMBUS.addPoint(-1, -1);
   }

   private Label nameLabel;
   private Label sourceRoleLabel;
   private Label targetRoleLabel;

   protected GenericLinkConnectionEditPart(GenericLinkConnectionType model)
   {
      super(model);
   }

   public void activate()
   {
      if (!isActive())
      {
         super.activate();

         GenericLinkConnectionType link = (GenericLinkConnectionType) getModel();
         LinkTypeType type = link.getLinkType();
         if (null != type)
         {
            type.eAdapters().add(getNotificationAdapter());
         }
      }
   }

   public void deactivate()
   {
      if (isActive())
      {
         GenericLinkConnectionType link = (GenericLinkConnectionType) getModel();
         LinkTypeType type = link.getLinkType();
         if (null != type)
         {
            type.eAdapters().remove(getNotificationAdapter());
         }

         super.deactivate();
      }
   }

   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PolylineConnection pLine = (PolylineConnection) getFigure();
      GenericLinkConnectionType link = (GenericLinkConnectionType) getModel();
      LinkTypeType type = link.getLinkType();

      pLine.setForegroundColor(getLineColor(type.getLineColor()));
      pLine.setLineStyle(getLineStyle(type.getLineStyle()));
      pLine.setSourceDecoration(getDecoration(type.getSourceSymbol()));
      pLine.setTargetDecoration(getDecoration(type.getTargetSymbol()));

      nameLabel = setLabel(nameLabel, pLine, normalizeLabel(type.getName()),
         type.isShowLinkTypeName(), ConnectionLocator.MIDDLE, true);
      sourceRoleLabel = setLabel(sourceRoleLabel, pLine, normalizeLabel(type.getSourceRole()),
         type.isShowRoleNames(), ConnectionLocator.SOURCE, false);
      targetRoleLabel = setLabel(targetRoleLabel, pLine, normalizeLabel(type.getTargetRole()),
         type.isShowRoleNames(), ConnectionLocator.TARGET, false);
   }

   private Label setLabel(Label label, PolylineConnection pLine, String name,
                          boolean show, int align, boolean border)
   {
      if (name == null || !show)
      {
         // remove nameLabel
         if (label != null)
         {
            pLine.remove(label);
            label = null;
         }
      }
      else
      {
         // add / refresh nameLabel
         if (label == null)
         {
            label = new Label(name);
            label.setBackgroundColor(ColorConstants.listBackground);
            if (border)
            {
               label.setOpaque(true);
               label.setBorder(new LineBorder());
            }
            pLine.add(label, new ConnectionLocator(pLine, align));
         }
         else
         {
            label.setText(name);
         }
      }
      return label;
   }

   private String normalizeLabel(String name)
   {
      return name == null || name.trim().length() == 0 ? null : " " + name.trim() + " "; //$NON-NLS-1$ //$NON-NLS-2$
   }

   private RotatableDecoration getDecoration(LinkEndStyle sourceSymbol)
   {
      RotatableDecoration decoration = null;
      if (sourceSymbol != null)
      {
         switch (sourceSymbol.getValue())
         {
            case LinkEndStyle.EMPTY_RHOMBUS:
               decoration = new PolygonDecoration();
               ((PolygonDecoration) decoration).setTemplate(RHOMBUS);
               decoration.setBackgroundColor(ColorConstants.listBackground);
               break;
            case LinkEndStyle.EMPTY_TRIANGLE:
               decoration = new PolygonDecoration();
               decoration.setBackgroundColor(ColorConstants.listBackground);
               break;
            case LinkEndStyle.FILLED_RHOMBUS:
               decoration = new PolygonDecoration();
               ((PolygonDecoration) decoration).setTemplate(RHOMBUS);
               break;
            case LinkEndStyle.FILLED_TRIANGLE:
               decoration = new PolygonDecoration();
               break;
            case LinkEndStyle.OPEN_TRIANGLE:
               decoration = new PolylineDecoration();
               break;
         }
      }
      return decoration;
   }

   private Color getLineColor(LinkColor color)
   {
      Color lineColor = ColorConstants.gray;
      if (color != null && color.getValue() >= 0 && color.getValue() < COLOR_MAPPING.length)
      {
         lineColor = COLOR_MAPPING[color.getValue()];
      }
      return lineColor;
   }

   private int getLineStyle(LinkLineStyle style)
   {
      int lineStyle = Graphics.LINE_SOLID;
      if (style != null && style.getValue() >= 0 && style.getValue() < STYLE_MAPPING.length)
      {
         lineStyle = STYLE_MAPPING[style.getValue()];
      }
      return lineStyle;
   }
}
