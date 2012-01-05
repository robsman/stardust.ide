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
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public abstract class AbstractLabeledFigure extends RoundedRectangle
      implements IGraphicalObjectFigure, ILabeledFigure, EditableFigure
{
   private final Label label;

   private Figure shape;

   private final Color defaultBorderColor;
   private final Color defaultFillColor;

   public AbstractLabeledFigure(Figure shape)
   {
      this(shape, ColorConstants.darkGray, ColorConstants.white);
   }

   public AbstractLabeledFigure(Figure shape, Color borderColor, Color fillColor)
   {
      this.defaultBorderColor = borderColor;
      this.defaultFillColor = fillColor;

      setCornerDimensions(new Dimension(12, 12));

      setFill(false);
      setBorder(new MarginBorder(1));

      label = createLabel();
      label.setBorder(new MarginBorder(1, 6, 1, 6));
      label.setForegroundColor(ColorConstants.black);
      label.setOpaque(true);
      this.shape = shape;

      setLayoutManager(createFigureLayout());
      doAddShape(shape);
      doAddLabel(label);

      setBorderColor(null);
      setFillColor(null);

      setMinimumSize(new Dimension(50, -1));
      setMaximumSize(new Dimension(300, 100));
   }

   protected Label createLabel()
   {
      return new Label();
   }

   protected LayoutManager createFigureLayout()
   {
      return new MyBorderLayout();
   }

   protected void doAddShape(Figure shape)
   {
      add(shape, MyBorderLayout.CENTER);
   }
   
   protected void doRemoveShape(Figure shape)
   {
      remove(shape);
   }
   
   protected void doAddLabel(Label label)
   {
      add(label, MyBorderLayout.BOTTOM);
   }
   
   protected void doRemoveLabel(Label label)
   {
      remove(label);
   }
   
   public void setBorderColor(Color color)
   {
      setForegroundColor((null != color) ? color : defaultBorderColor);
   }

   public void setFillColor(Color color)
   {
      setBackgroundColor((null != color) ? color : defaultFillColor);
   }

   protected Label getLabel()
   {
      return label;
   }

   public void setName(String name)
   {
      label.setText((null != name) ? name : ""); //$NON-NLS-1$
   }

   public void setShape(Figure figure)
   {
      if (this.shape != figure)
      {
         doRemoveShape(shape);
         shape = figure;
         doAddShape(shape);
      }      
   }

   public Figure getShape()
   {
      return shape;
   }

   public void setText(String text)
   {
      getLabel().setText(text == null ? "" : text); //$NON-NLS-1$
   }

   public String getText()
   {
      return getLabel().getText();
   }

   public Rectangle getEditingBounds()
   {
      return getLabel().getBounds();
   }

   public int getEditingStyle()
   {
      return SWT.SINGLE;
   }

   /**
    * @return Returns the defaultBorderColor.
    */
   protected Color getDefaultBorderColor()
   {
      return defaultBorderColor;
   }

   /**
    * @return Returns the defaultFillColor.
    */
   protected Color getDefaultFillColor()
   {
      return defaultFillColor;
   }
}
