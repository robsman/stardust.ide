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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;


/**
 * @author rsauer
 * @version $Revision$
 */
public class MoveNodeSymbolCommand extends Command
{
   private INodeSymbol target;

   private Point oldPosition;

   private Integer oldWidth;

   private Integer oldHeight;

   private Point newPosition;

   private Integer newWidth;

   private Integer newHeight;

   private boolean executed = false;

   public MoveNodeSymbolCommand()
   {}

   public void setPart(INodeSymbol target)
   {
      this.target = target;
   }

   public void setBounds(Rectangle r)
   {
      if (null != r)
      {
         setLocation(r.getLocation());
         setSize(r.getSize());
      }
      else
      {
         setLocation((Point) null);
         setSize(null);
      }
   }

   public void setLocation(Point p)
   {
      this.newPosition = p;
   }

   public void setSize(Dimension d)
   {
      if (null != d)
      {
         this.newWidth = new Integer(d.width);
         this.newHeight = new Integer(d.height);
      }
      else
      {
         this.newWidth = null;
         this.newHeight = null;
      }
   }

   public void setWidth(int width)
   {
      this.newWidth = new Integer(width);
   }

   public void setHeight(int height)
   {
      this.newHeight = new Integer(height);
   }

   public void execute()
   {
      executed = true;

      this.oldPosition = new Point(target.getXPos(), target.getYPos());

      this.oldWidth = target.isSetWidth() ? new Integer(target.getWidth()) : null;
      this.oldHeight = target.isSetHeight() ? new Integer(target.getHeight()) : null;

      newWidth = newWidth == null ? oldWidth : newWidth;
      newHeight = newHeight == null ? oldHeight : newHeight;

      redo();
   }

   public void redo()
   {
      if (executed)
      {
         setTargetPosition(newPosition);
         setTargetSize(newWidth, newHeight);
      }
   }

   public void undo()
   {
      if (executed)
      {
         setTargetSize(oldWidth, oldHeight);
         setTargetPosition(oldPosition);
      }
   }

   private void setTargetPosition(Point point)
   {
      long xPos = 0;
      long yPos = 0;
      if (null != point)
      {
         xPos = point.x;
         yPos = point.y;
      }
      target.setXPos(xPos);
      target.setYPos(yPos);
   }

   private void setTargetSize(Integer width, Integer height)
   {
      if (null != width)
      {
         target.setWidth(width.intValue());
      }
      else
      {
         target.unsetWidth();
      }

      if (null != height)
      {
         target.setHeight(height.intValue());
      }
      else
      {
         target.unsetHeight();
      }
   }
}