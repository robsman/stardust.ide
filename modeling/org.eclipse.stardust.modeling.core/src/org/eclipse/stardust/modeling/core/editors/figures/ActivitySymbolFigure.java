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
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType;
import org.eclipse.swt.graphics.Color;


public class ActivitySymbolFigure extends AbstractLabeledIconFigure
{
   private static final String STANDARD_LOOP = "icons/full/ovr13/loopSign.gif";
   private static final String PARALLEL_LOOP = "icons/full/ovr13/parallelSign.gif";
   private static final String SEQUENTIAL_LOOP = "icons/full/ovr13/sequentialSign.gif";

   private static final Color BG_GRADIENT_FROM = ColorConstants.white;

   private static final Color BG_GRADIENT_TO = new Color(null, 226, 221, 219);

   private static final Color FG_BORDER = new Color(null, 180, 177, 177);

   private static final String SIZING_TEXT = "MMMMMMMMMMMMMMM"; //$NON-NLS-1$

   private Figure shapeContainer;

   private Figure overlayContainer;

   private IconFigure blankOverlay;

   private IconFigure subProcOverlay;

   private IconFigure loopOverlay;

   private Figure eventOverlay;

   private boolean hasEventIcon = false;

   private Dimension cachedMinSize;

   public ActivitySymbolFigure()
   {
      super("icons/full/obj16/activity.gif"); //$NON-NLS-1$

      setFill(true);
      getLabel().setOpaque(false);

      // containers will be initialized during constructor call
      add(shapeContainer, MyBorderLayout.TOP);

      this.overlayContainer = new Figure();
      FlowLayout overlayLayout = new FlowLayout(true);
      overlayLayout.setMajorAlignment(FlowLayout.ALIGN_CENTER);
      overlayLayout.setMajorSpacing(0);
      overlayLayout.setMinorSpacing(0);
      overlayContainer.setLayoutManager(overlayLayout);
      overlayContainer.setBorder(new MarginBorder(2, 6, -1, 6));
      add(overlayContainer, MyBorderLayout.BOTTOM);

      blankOverlay = new IconFigure("icons/full/ovr13/blank.gif"); //$NON-NLS-1$

      subProcOverlay = new IconFigure("icons/full/ovr13/plusSignSmall.gif"); //$NON-NLS-1$

      loopOverlay = new IconFigure(STANDARD_LOOP); //$NON-NLS-1$

      setLoopActivity(null, true);
      setSubProcActivity(false);
      setEventHandlerType(false, null);

      getLabel().setForegroundColor(ColorConstants.black);
      getLabel().setTextPlacement(PositionConstants.SOUTH);
   }

   public Dimension getMinimumSize(int width, int height)
   {
      if (cachedMinSize == null)
      {
         Dimension labelDim = getLabel().getMinimumSize();
         // check minimum size
         Dimension txtSize = FigureUtilities.getTextExtents(SIZING_TEXT, getLabel()
               .getFont());
         Insets insets = getLabel().getInsets();
         labelDim.width = Math.max(labelDim.width, txtSize.width + insets.getWidth());
         cachedMinSize = super.getMinimumSize(width, height).getCopy();
         insets = getInsets();
         cachedMinSize.width = Math.max(cachedMinSize.width, labelDim.width
               + insets.getWidth());
      }
      return cachedMinSize;
   }

   public Dimension getPreferredSize(int width, int height)
   {
      Dimension prefSize = super.getPreferredSize(width, height).getCopy();
      Dimension minSize = getMinimumSize(width, height);
      // prefSize.width = Math.min(prefSize.width, width);
      prefSize.width = Math.max(minSize.width, prefSize.width);
      prefSize.height = Math.max(minSize.height, prefSize.height);

      // // forcing outline to be of uneven width/height to get smooth edges
      prefSize.width = (prefSize.width & 1) == 0 ? prefSize.width + 1 : prefSize.width;
      prefSize.height = (prefSize.height & 1) == 0
            ? prefSize.height + 1
            : prefSize.height;

      return prefSize;
   }

   protected Label createLabel()
   {
      return new Label();
   }

   public void setEventHandlerType(boolean eventHandler, ActivityType activity)
   {
      if (activity != null)
      {
         if (hasEventIcon)
         {
            overlayContainer.getChildren().remove(eventOverlay);
            hasEventIcon = false;
         }

         if (eventHandler)
         {

            eventOverlay = getEventOverlay(activity.getEventHandler());
            if (!overlayContainer.getChildren().contains(eventOverlay))
            {
               if (overlayContainer.getChildren().contains(blankOverlay))
               {
                  overlayContainer.remove(blankOverlay);
               }
               if (!hasEventIcon)
               {
                  overlayContainer.add(eventOverlay);
                  hasEventIcon = true;
               }

               if (overlayContainer.getChildren().contains(subProcOverlay))
               {
                  overlayContainer.add(blankOverlay);
               }
               if (overlayContainer.getChildren().contains(loopOverlay))
               {
                  overlayContainer.add(blankOverlay);
               }

            }
         }
         else
         {
            if (overlayContainer.getChildren().contains(eventOverlay))
            {
               overlayContainer.remove(eventOverlay);
               hasEventIcon = false;
               if (overlayContainer.getChildren().contains(blankOverlay))
               {
                  overlayContainer.remove(blankOverlay);
               }
            }
            if (overlayContainer.getChildren().isEmpty())
            {
               overlayContainer.add(blankOverlay);
            }
         }
      }
   }

   private Figure getEventOverlay(EList eventHandlerList)
   {
      if ((1 == eventHandlerList.size())
            && (null != ((EventHandlerType) eventHandlerList.get(0)).getType()))
      {
         EventHandlerType eventHandler = (EventHandlerType) eventHandlerList.get(0);
         return new IconFigure(SpiExtensionRegistry.instance().getTypeIcon(
               eventHandler.getType().getExtensionPointId(),
               eventHandler.getType().getId()));
      }
      return new IconFigure("icons/full/obj16/condition.gif"); //$NON-NLS-1$
   }

   public void setLoopActivity(LoopTypeType loopType, boolean sequential)
   {
      if (loopType != null)
      {
         loopOverlay.setIconPath(loopType == LoopTypeType.MULTI_INSTANCE
               ? sequential ? SEQUENTIAL_LOOP : PARALLEL_LOOP : STANDARD_LOOP);
         if (!overlayContainer.getChildren().contains(loopOverlay))
         {
            if (overlayContainer.getChildren().contains(blankOverlay))
            {
               overlayContainer.remove(blankOverlay);
            }
            overlayContainer.add(loopOverlay, 0);
            if (overlayContainer.getChildren().contains(subProcOverlay))
            {
               // use blank to center subproc overlay
               overlayContainer.add(blankOverlay);
            }
            if (overlayContainer.getChildren().contains(eventOverlay))
            {
               overlayContainer.add(blankOverlay);
            }
         }
      }
      else
      {
         if (overlayContainer.getChildren().contains(loopOverlay))
         {
            overlayContainer.remove(loopOverlay);
            if (overlayContainer.getChildren().contains(blankOverlay))
            {
               overlayContainer.remove(blankOverlay);
            }
         }
         if (overlayContainer.getChildren().isEmpty())
         {
            overlayContainer.add(blankOverlay);
         }
      }
   }

   public void setSubProcActivity(boolean subProcActivity)
   {
      if (subProcActivity)
      {
         if (!overlayContainer.getChildren().contains(subProcOverlay))
         {
            if (overlayContainer.getChildren().contains(blankOverlay))
            {
               overlayContainer.remove(blankOverlay);
            }
            overlayContainer.add(subProcOverlay);
            if (overlayContainer.getChildren().contains(loopOverlay))
            {
               // use blank to center subproc overlay
               overlayContainer.add(blankOverlay);
            }
            if (overlayContainer.getChildren().contains(eventOverlay))
            {
               overlayContainer.add(blankOverlay);
            }
         }
      }
      else
      {
         if (overlayContainer.getChildren().contains(subProcOverlay))
         {
            overlayContainer.remove(subProcOverlay);
            if (overlayContainer.getChildren().contains(blankOverlay))
            {
               overlayContainer.remove(blankOverlay);
            }
         }
         if (overlayContainer.getChildren().isEmpty())
         {
            overlayContainer.add(blankOverlay);
         }
      }
   }

   public void setShape(Figure figure)
   {
      this.overlayContainer.remove(loopOverlay);

      super.setShape(figure);

      this.overlayContainer.add(loopOverlay, MyBorderLayout.CENTER);
   }

   protected void doAddShape(Figure shape)
   {
      if (null == this.shapeContainer)
      {
         this.shapeContainer = new Figure();
         shapeContainer.setLayoutManager(new MyBorderLayout());
         shapeContainer.setBorder(new MarginBorder(1, 6, 0, 6));
      }
      shapeContainer.add(shape, MyBorderLayout.LEFT);
   }

   protected void doRemoveShape(Figure shape)
   {
      if (null != this.shapeContainer)
      {
         shapeContainer.remove(shape);
      }
   }

   protected void doAddLabel(Label label)
   {
      add(label, MyBorderLayout.CENTER);
   }

   public void setName(String name)
   {
      super.setName(name);
      setToolTip(new Label(name));
   }

   public void setBounds(Rectangle rect)
   {
      // forcing outline to be of uneven width/height to get smooth edges
      final int width = (rect.width & 1) == 0 ? rect.width + 1 : rect.width;
      final int height = (rect.height & 1) == 0 ? rect.height + 1 : rect.height;

      super.setBounds(new Rectangle(rect.x, rect.y, width, height));
   }

   protected void outlineShape(Graphics graphics)
   {
      // TODO Auto-generated method stub
      graphics.pushState();
      try
      {
         int lineWidth = 2;

         if (getForegroundColor().equals(getDefaultBorderColor()))
         {
            graphics.setForegroundColor(FG_BORDER);
         }

         graphics.setLineWidth(lineWidth);

         Rectangle f = Rectangle.SINGLETON;
         Rectangle r = getBounds();
         f.x = r.x + lineWidth / 2;
         f.y = r.y + lineWidth / 2;
         f.width = r.width - lineWidth;
         f.height = r.height - lineWidth;

         graphics.drawRoundRectangle(f, corner.width, corner.height);
      }
      finally
      {
         graphics.popState();
      }
   }

   protected void fillShape(Graphics graphics)
   {
      graphics.pushState();
      try
      {
         Rectangle rect = getBounds().getCopy();

         // drawing left rounded corner area
         rect.width = corner.width;
         // graphics.setClip(rect);
         rect.width += corner.width;
         graphics.setBackgroundColor(BG_GRADIENT_FROM);
         graphics.fillRoundRectangle(rect, corner.width, corner.height);

         // drawing right rounded corner area
         rect.x = getBounds().right() - corner.width;
         rect.width = corner.width;
         // graphics.setClip(rect);
         rect.x -= corner.width;
         rect.width += corner.width;
         graphics.setBackgroundColor(BG_GRADIENT_TO);
         graphics.fillRoundRectangle(rect, corner.width, corner.height);

         // filling space between left and right area with gradient
         graphics.setForegroundColor(BG_GRADIENT_FROM);
         rect.x = getBounds().x + corner.width;
         rect.width = getBounds().width - (2 * corner.width);
         // graphics.setClip(rect);
         graphics.fillGradient(rect, false);
      }
      finally
      {
         graphics.popState();
      }
   }
}
