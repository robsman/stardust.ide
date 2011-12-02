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

import org.eclipse.draw2d.AbstractLabeledBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.ScaledGraphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;


public class SwimlaneBorder extends CompoundBorder
{
   public static int ButtonSize = 20;
   public static final int ORIENTATION_VERTICAL = -1;
   public static final int ORIENTATION_HORIZONTAL = -2;
   public static final int borderWidth = 2;
   public static Color defaultSwimlaneBorderColor = new Color(null, 225, 225, 225);
   public static Color participantSwimlaneBorderColor = ColorConstants.lightGray;
      
   public SwimlaneBorder(IIdentifiableElement identifiable)
   {
      this(identifiable, null);
   }

   public SwimlaneBorder(IIdentifiableElement identifiable, IFigure button)
   {
      super(new LineBorder(defaultSwimlaneBorderColor, borderWidth), new BorderWithLabel(identifiable.getName(), button));
      setOrientation(DiagramPlugin.isVerticalModelling((IGraphicalObject) identifiable)
            ? ORIENTATION_VERTICAL : ORIENTATION_HORIZONTAL);
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) identifiable);      
      setDiagramMode(diagram.getMode());
   }   
   
   public void setLabel(String label)
   {
      ((BorderWithLabel) getInnerBorder()).setLabel(label);
   }

   public Insets getSwimlaneBorderInsets()
   {      
      BorderWithLabel innerBorder = (BorderWithLabel) getInnerBorder();
      LineBorder outerBorder = (LineBorder) getOuterBorder();
      Insets insets = null;
      if(innerBorder.insets == null)
      {
         insets = new Insets(outerBorder.getInsets(null));
      }
      else
      {
         insets = new Insets(innerBorder.insets);
         insets.add(outerBorder.getInsets(null));         
      }      
      return insets;
   }
   
   public void setHasParticipant(boolean hasParticipant)
   {
      ((BorderWithLabel) getInnerBorder()).hasParticipant = hasParticipant;  
      if(hasParticipant)
      {
         ((LineBorder) getOuterBorder()).setColor(participantSwimlaneBorderColor);         
      }
      else
      {
         ((LineBorder) getOuterBorder()).setColor(defaultSwimlaneBorderColor);         
      }
   }      

   public void setDiagramMode(DiagramModeType mode)
   {
      ((BorderWithLabel) getInnerBorder()).setDiagramMode(mode);      
   }
   
   public void setOrientation(int orientation)
   {
      ((BorderWithLabel) getInnerBorder()).setOrientation(orientation);
   }

   private static class BorderWithLabel extends AbstractLabeledBorder
   {
      public boolean hasParticipant = false;
      private Insets insets;

      private static Color defaultColor = ColorConstants.menuBackgroundSelected;

      private Insets padding = new Insets(1, 3, 2, 2);
      private Color fillColor = defaultColor;

      private int orientation = ORIENTATION_VERTICAL;
      private DiagramModeType diagramMode; 

      private Insets underlineInsets;
      private IFigure button;

      /**
       * @param s
       */
      public BorderWithLabel(String s, IFigure button)
      {
         super(s);
         this.button = button;
         setPadding(3);
         setOrientation(ORIENTATION_VERTICAL);
      }

      public void setDiagramMode(DiagramModeType mode)
      {
         diagramMode = mode;
      }
      
      public void setOrientation(int orientation)
      {
         if ((ORIENTATION_VERTICAL == orientation)
               || (ORIENTATION_HORIZONTAL == orientation))
         {
            this.orientation = orientation;

            if (ORIENTATION_HORIZONTAL == orientation)
            {
               underlineInsets = new Insets(2, 0, 2, 2);
            }
            else
            {
               underlineInsets = new Insets(0, 2, 2, 2);
            }
         }

         this.insets = null;
         invalidate();
      }

      public Insets getInsets(IFigure fig)
      {
         if (null == insets)
         {
            this.insets = calculateInsets(fig);
         }
         return insets;
      }

      protected Insets calculateInsets(IFigure figure)
      {
         if (null == getFont(figure))
         {
            return new Insets(0, 0, 0, 0);
         }
         // TODO
         if (ORIENTATION_HORIZONTAL == orientation)
         {
            return new Insets(0, getTextExtents(figure).height + padding.getHeight(), 0,
                  0);
         }
         else
         {
            return new Insets(getTextExtents(figure).height + padding.getHeight(), 0, 0,
                  0);
         }
      }

      protected Color getBackgroundColor()
      {
         return fillColor;
      }

      protected Insets getPadding()
      {
         return padding;
      }

      public boolean isOpaque()
      {
         return true;
      }

      public void setBackgroundColor(Color color)
      {
         fillColor = color;
      }

      public void setPadding(int all)
      {
         padding = new Insets(all);
         invalidate();
      }

      public void setPadding(Insets pad)
      {
         padding = pad;
         invalidate();
      }

      public void paint(IFigure figure, Graphics g, Insets insets)
      {
         if(figure instanceof LaneFigure)
         {
            if(hasParticipant)
            {
               setBackgroundColor(participantSwimlaneBorderColor);              
            }
            else
            {
               setBackgroundColor(defaultSwimlaneBorderColor);  
            }            
         }
         else
         {
            setBackgroundColor(defaultSwimlaneBorderColor);            
         }

         tempRect.setBounds(getPaintRectangle(figure, insets));
         Rectangle rec = tempRect;
         if (ORIENTATION_HORIZONTAL == orientation)
         {
            rec.width = Math.min(rec.width, getTextExtents(figure).height
                  + padding.getWidth());
         }
         else
         {
            rec.height = Math.min(rec.height, getTextExtents(figure).height
                  + padding.getHeight());
            g.clipRect(rec);
         }
         
         // clip only on vertical orientation, otherwise the label is not visible 
         // g.clipRect(rec);
         g.setBackgroundColor(fillColor);
         g.fillRectangle(rec);

         int x;
         int y;
         if (ORIENTATION_HORIZONTAL == orientation)
         {
            x = rec.x + padding.right;
            y = rec.bottom() - padding.bottom;
         }
         else
         {
            x = rec.x + padding.left;
            y = rec.y + padding.top;
         }

         int textWidth = getTextExtents(figure).width;
         boolean textToLong = false;
         
         int spaceForText;
         // check if text too long and the available space
         if (ORIENTATION_HORIZONTAL == orientation)
         {
            int freeSpace = rec.height - padding.getHeight() - textWidth - ButtonSize;
            if(freeSpace < 0)
            {
               textToLong = true;
            }            
            spaceForText = rec.height - padding.getHeight() - ButtonSize;
         }
         else
         {
            int freeSpace = rec.width - padding.getWidth() - textWidth - ButtonSize;
            if(freeSpace < 0)
            {
               textToLong = true;
            }            
            spaceForText = rec.width - padding.getWidth() - ButtonSize;
         }
         int newTextLength = spaceForText/5 - 3; // ...
         
         String labelToShow = new String(getLabel());
         // to prevent a StringIndexOutOfBoundsException
         if(labelToShow.length() < newTextLength)
         {
            newTextLength = labelToShow.length();
         }
         
         if(textToLong)
         {
            if(newTextLength > 0)
            {
               labelToShow = labelToShow.substring(0, newTextLength) + "...";                //$NON-NLS-1$
            }
            else
            {
               labelToShow = ""; //$NON-NLS-1$
            }
         }    
         Dimension textExtents = FigureUtilities.getTextExtents(labelToShow, getFont(figure));         
         textWidth = textExtents.width;
         
         if (ORIENTATION_HORIZONTAL == orientation)
         {
            int freeSpace = rec.height - padding.getHeight() - textWidth - ButtonSize/2;
            freeSpace /= 2;
            y -= freeSpace;               
         }
         else
         {
            int freeSpace = rec.width - padding.getWidth() - textWidth - ButtonSize/2;
            freeSpace /= 2;
            x += freeSpace;
         }
         
         g.setFont(getFont(figure));
         g.pushState();
         g.translate(x, y);
         
         if (ORIENTATION_HORIZONTAL == orientation)
         {
            // at the moment only works for SWTGraphics (not implemented for ScaledGraphics and PrinterGraphics)
            if(g instanceof SWTGraphics)
            {
               g.rotate(270);
               g.setForegroundColor(getTextColor());
               g.drawString(labelToShow, 0, 0);
            }
            else if (g instanceof ScaledGraphics)
            {
               // create an rotated image of the label as ScaledGraphics does not implement rotate()
               Image labelAsImage = ImageUtilities
                     .createRotatedImageOfString(labelToShow, getFont(figure),
                           getTextColor(), g.getBackgroundColor());
               g.drawImage(labelAsImage, 0, 0);
            }
         }       
         else if (ORIENTATION_VERTICAL == orientation)
         {
            g.setForegroundColor(getTextColor());
            g.drawString(labelToShow, 0, 0);
         }
         g.popState();
            
         Rectangle bounds = getPaintRectangle(figure, insets);
         if (ORIENTATION_HORIZONTAL == orientation)
         {
            underlineInsets.left = getTextExtents(figure).height
                  + getPadding().getHeight() - 2;
            bounds.crop(underlineInsets);
            // g.drawLine(bounds.x, bounds.y - 2, bounds.x, bounds.bottom() + 2);
         }
         else
         {
            underlineInsets.top = getTextExtents(figure).height
                  + getPadding().getHeight() - 2;
            bounds.crop(underlineInsets);
            // g.drawLine(bounds.x - 2, bounds.y, bounds.right() + 2, bounds.y);
         }
         
         if(diagramMode.equals(DiagramModeType.MODE_450_LITERAL) && button != null)
         {
            if (ORIENTATION_HORIZONTAL == orientation)
            {
               button.setBounds(new Rectangle(figure.getBounds().x, figure.getBounds().y, ButtonSize, ButtonSize));
            }
            else
            {
               button.setBounds(new Rectangle(figure.getBounds().x + figure.getBounds().width - ButtonSize, figure.getBounds().y, ButtonSize, ButtonSize));               
            }
            button.paint(g);
         } 
      }

      public Dimension getPreferredSize(IFigure fig)
      {
         Dimension dimension;
         try
         {
            dimension = super.getPreferredSize(fig);
         }
         catch(NullPointerException e)
         {
            dimension = new Dimension();
         }
         return dimension;
      }
   }
}