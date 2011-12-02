/*******************************************************************************
 * Copyright (c) 2001, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation 
 *     Sungard - Improved routing and connection handling
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.editors.figures.routers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Ray;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.utils.TransitionConnectionUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


/**
 * Provides a {@link Connection} with an orthogonal route between the Connection's source
 * and target anchors.
 */
public final class TransitionConnectionRouter extends AbstractRouter
{
   private Map constraints = new HashMap(11);

   private Map rowsUsed = new HashMap();

   private Map colsUsed = new HashMap();

   // private Hashtable offsets = new Hashtable(7);

   private Map reservedInfo = new HashMap();

   private class ReservedInfo
   {
      public List reservedRows = new ArrayList(2);

      public List reservedCols = new ArrayList(2);
   }

   private static Ray UP = new Ray(0, -1), DOWN = new Ray(0, 1), LEFT = new Ray(-1, 0),
         RIGHT = new Ray(1, 0);

   /**
    * @see ConnectionRouter#invalidate(Connection)
    */
   public void invalidate(Connection connection)
   {
      removeReservedLines(connection);
   }

   private int getColumnNear(Connection connection, int r, int n, int x)
   {
      int min = Math.min(n, x), max = Math.max(n, x);
      if (min > r)
      {
         max = min;
         min = r - (min - r);
      }
      if (max < r)
      {
         min = max;
         max = r + (r - max);
      }
      int proximity = 0;
      int direction = -1;
      if (r % 2 == 1)
         r--;
      Integer i;
      while (proximity < r)
      {
         i = new Integer(r + proximity * direction);
         if (!colsUsed.containsKey(i))
         {
            colsUsed.put(i, i);
            reserveColumn(connection, i);
            return i.intValue();
         }
         int j = i.intValue();
         if (j <= min)
            return j + 2;
         if (j >= max)
            return j - 2;
         if (direction == 1)
            direction = -1;
         else
         {
            direction = 1;
            proximity += 2;
         }
      }
      return r;
   }

   /**
    * Returns the direction the point <i>p</i> is in relation to the given rectangle.
    * Possible values are LEFT (-1,0), RIGHT (1,0), UP (0,-1) and DOWN (0,1).
    * 
    * @param r
    *           the rectangle
    * @param p
    *           the point
    * @return the direction from <i>r</i> to <i>p</i>
    */
   protected Ray getDirection(Rectangle r, Point p)
   {
      int i, distance = Math.abs(r.x - p.x);
      Ray direction;

      direction = LEFT;

      i = Math.abs(r.y - p.y);
      if (i <= distance)
      {
         distance = i;
         direction = UP;
      }

      i = Math.abs(r.bottom() - p.y);
      if (i <= distance)
      {
         distance = i;
         direction = DOWN;
      }

      i = Math.abs(r.right() - p.x);
      if (i < distance)
      {
         distance = i;
         direction = RIGHT;
      }

      return direction;
   }

   protected Ray getEndDirection(Connection conn)
   {
      ConnectionAnchor anchor = conn.getTargetAnchor();
      Point p = getEndPoint(conn);
      Rectangle rect;
      if (anchor.getOwner() == null)
         rect = new Rectangle(p.x - 1, p.y - 1, 2, 2);
      else
      {
         rect = conn.getTargetAnchor().getOwner().getBounds().getCopy();
         conn.getTargetAnchor().getOwner().translateToAbsolute(rect);
      }
      return getDirection(rect, p);
   }

   protected int getRowNear(Connection connection, int r, int n, int x)
   {
      int min = Math.min(n, x), max = Math.max(n, x);
      if (min > r)
      {
         max = min;
         min = r - (min - r);
      }
      if (max < r)
      {
         min = max;
         max = r + (r - max);
      }

      int proximity = 0;
      int direction = -1;
      if (r % 2 == 1)
         r--;
      Integer i;
      while (proximity < r)
      {
         i = new Integer(r + proximity * direction);
         if (!rowsUsed.containsKey(i))
         {
            rowsUsed.put(i, i);
            reserveRow(connection, i);
            return i.intValue();
         }
         int j = i.intValue();
         if (j <= min)
            return j + 2;
         if (j >= max)
            return j - 2;
         if (direction == 1)
            direction = -1;
         else
         {
            direction = 1;
            proximity += 2;
         }
      }
      return r;
   }

   protected Ray getStartDirection(Connection conn)
   {
      ConnectionAnchor anchor = conn.getSourceAnchor();
      Point p = getStartPoint(conn);
      Rectangle rect;
      if (anchor.getOwner() == null)
         rect = new Rectangle(p.x - 1, p.y - 1, 2, 2);
      else
      {
         rect = conn.getSourceAnchor().getOwner().getBounds().getCopy();
         conn.getSourceAnchor().getOwner().translateToAbsolute(rect);
      }
      return getDirection(rect, p);
   }

   protected void processPositions(Ray start, Ray end, List positions,
         boolean horizontal, Connection conn)
   {
      removeReservedLines(conn);

      int pos[] = new int[positions.size() + 2];
      if (horizontal)
         pos[0] = start.x;
      else
         pos[0] = start.y;
      int i;
      for (i = 0; i < positions.size(); i++)
      {
         pos[i + 1] = ((Integer) positions.get(i)).intValue();
      }
      if (horizontal == (positions.size() % 2 == 1))
         pos[++i] = end.x;
      else
         pos[++i] = end.y;

      PointList points = new PointList();
      points.addPoint(new Point(start.x, start.y));
      Point p;
      int current, prev, min, max;
      boolean adjust;
      for (i = 2; i < pos.length - 1; i++)
      {
         horizontal = !horizontal;
         prev = pos[i - 1];
         current = pos[i];

         adjust = (i != pos.length - 2);
         if (horizontal)
         {
            if (adjust)
            {
               min = pos[i - 2];
               max = pos[i + 2];
               pos[i] = current = getRowNear(conn, current, min, max);
            }
            p = new Point(prev, current);
         }
         else
         {
            if (adjust)
            {
               min = pos[i - 2];
               max = pos[i + 2];
               pos[i] = current = getColumnNear(conn, current, min, max);
            }
            p = new Point(current, prev);
         }
         points.addPoint(p);
      }
      points.addPoint(new Point(end.x, end.y));
      if (isStraight(points))
      {
         PointList fix = new PointList(2);
         fix.addPoint(points.getFirstPoint());
         fix.addPoint(points.getLastPoint());
         points = fix;
      }
      conn.setPoints(points);
   }

   private boolean isStraight(PointList points)
   {
      Point dummy = new Point();
      if (points.size() > 2)
      {
         points.getPoint(dummy, 0);
         int x = dummy.x;
         int y = dummy.y;
         boolean horizontal = true;
         boolean vertical = true;
         for (int i = 1; i < points.size() && (horizontal || vertical); i++)
         {
            points.getPoint(dummy, i);
            if (horizontal && x != dummy.x)
            {
               horizontal = false;
            }
            if (vertical && y != dummy.y)
            {
               vertical = false;
            }
         }
         return horizontal || vertical;
      }
      return false;
   }

   /**
    * @see ConnectionRouter#remove(Connection)
    */
   public void remove(Connection connection)
   {
      removeReservedLines(connection);
      constraints.remove(connection);
   }

   protected void removeReservedLines(Connection connection)
   {
      ReservedInfo rInfo = (ReservedInfo) reservedInfo.get(connection);
      if (rInfo == null)
         return;

      for (int i = 0; i < rInfo.reservedRows.size(); i++)
      {
         rowsUsed.remove(rInfo.reservedRows.get(i));
      }
      for (int i = 0; i < rInfo.reservedCols.size(); i++)
      {
         colsUsed.remove(rInfo.reservedCols.get(i));
      }
      reservedInfo.remove(connection);
   }

   protected void reserveColumn(Connection connection, Integer column)
   {
      ReservedInfo info = (ReservedInfo) reservedInfo.get(connection);
      if (info == null)
      {
         info = new ReservedInfo();
         reservedInfo.put(connection, info);
      }
      info.reservedCols.add(column);
   }

   protected void reserveRow(Connection connection, Integer row)
   {
      ReservedInfo info = (ReservedInfo) reservedInfo.get(connection);
      if (info == null)
      {
         info = new ReservedInfo();
         reservedInfo.put(connection, info);
      }
      info.reservedRows.add(row);
   }

   /**
    * @see ConnectionRouter#route(Connection)
    */
   public void route(Connection conn)
   {
      if ((conn.getSourceAnchor() == null) || (conn.getTargetAnchor() == null))
         return;
      int i;
      Point startPoint = getStartPoint(conn);
      conn.translateToRelative(startPoint);
      Point endPoint = getEndPoint(conn);
      conn.translateToRelative(endPoint);

      Ray start = new Ray(startPoint);
      Ray end = new Ray(endPoint);
      Ray average = start.getAveraged(end);

      Ray direction = new Ray(start, end);
      Ray startNormal = getStartDirection(conn);
      Ray endNormal = getEndDirection(conn);

      List positions = new ArrayList(5);
      // rsauer fixing routing if start and end ray have the same normal
      final boolean fixRouting = startNormal.equals(endNormal);
      final Ray realStart = start;
      final Ray realEnd = end;
      final boolean startHorizontally = startNormal.isHorizontal();

      if (fixRouting)
      {
         if (startNormal.isHorizontal())
         {
            positions.add(new Integer(start.y));

            startPoint = new Point(start.x + startNormal.getScaled(25).x, start.y);
            endPoint = new Point(end.x + endNormal.getScaled(25).x, end.y);

            startNormal = (start.y <= end.y) ? DOWN : UP;
            endNormal = (start.y <= end.y) ? UP : DOWN;
         }
         else
         {
            positions.add(new Integer(start.x));

            startPoint = new Point(start.x, start.y + startNormal.getScaled(25).y);
            endPoint = new Point(end.x, end.y + endNormal.getScaled(25).y);

            startNormal = (start.x <= end.x) ? RIGHT : LEFT;
            endNormal = (start.x <= end.x) ? RIGHT : LEFT;
         }

         start = new Ray(startPoint);
         end = new Ray(endPoint);
         average = start.getAveraged(end);

         direction = new Ray(start, end);
      }

      boolean horizontal = startNormal.isHorizontal();
      if (horizontal)
         positions.add(new Integer(start.y));
      else
         positions.add(new Integer(start.x));
      horizontal = !horizontal;

      if (startNormal.dotProduct(endNormal) == 0)
      {
         if ((startNormal.dotProduct(direction) >= 0)
               && (endNormal.dotProduct(direction) <= 0))
         {
            // 0
         }
         else
         {
            // 2
            if (startNormal.dotProduct(direction) < 0)
               i = startNormal.similarity(start.getAdded(startNormal.getScaled(25)));
            else
            {
               if (horizontal)
                  i = average.y;
               else
                  i = average.x;
            }
            positions.add(new Integer(i));
            horizontal = !horizontal;

            if (endNormal.dotProduct(direction) > 0)
               i = endNormal.similarity(end.getAdded(endNormal.getScaled(25)));
            else
            {
               if (horizontal)
                  i = average.y;
               else
                  i = average.x;
            }
            positions.add(new Integer(i));
            horizontal = !horizontal;
         }
      }
      else
      {
         if (startNormal.dotProduct(endNormal) > 0)
         {
            // 1
            if (startNormal.dotProduct(direction) >= 0)
               i = startNormal.similarity(start.getAdded(startNormal.getScaled(25)));
            else
               i = endNormal.similarity(end.getAdded(endNormal.getScaled(25)));
            positions.add(new Integer(i));
            horizontal = !horizontal;
         }
         else
         {
            // 3 or 1
            if (startNormal.dotProduct(direction) < 0)
            {
               i = startNormal.similarity(start.getAdded(startNormal.getScaled(25)));
               positions.add(new Integer(i));
               horizontal = !horizontal;
            }

            if (horizontal)
               i = average.y;
            else
               i = average.x;
            positions.add(new Integer(i));
            horizontal = !horizontal;

            if (startNormal.dotProduct(direction) < 0)
            {
               i = endNormal.similarity(end.getAdded(endNormal.getScaled(25)));
               positions.add(new Integer(i));
               horizontal = !horizontal;
            }
         }
      }
      if (horizontal)
         positions.add(new Integer(end.y));
      else
         positions.add(new Integer(end.x));

      if (fixRouting)
      {
         if (startHorizontally)
         {
            positions.add(new Integer(end.y));
         }
         else
         {
            positions.add(new Integer(end.x));
         }
      }
            
      // check if it is a backward transition (loop)
      List savePositions = (List) ((ArrayList) positions).clone();         
      boolean isLoop = false;
      boolean gatewaysFlag[] = new boolean[] {false};
      Integer newPosition = null;
      boolean[] isVertical = new boolean[] {false};
      Point startPoint__ = new Point(start.x, start.y);
      Point endPoint__ = new Point(end.x, end.y);
            
      isLoop = positions.size() == 5 
                  && positions.get(0).equals(positions.get(2))
                  && positions.get(2).equals(positions.get(4));
      
      if(isLoop) 
      {    
         TransitionConnectionUtils.orderPoints(isVertical, startPoint__, endPoint__);
         newPosition = TransitionConnectionUtils.getNewPosition(conn, 
               isVertical, true, startPoint__, endPoint__);
         // always increase
         if(newPosition != null)
         {
            Integer value = new Integer(newPosition.intValue() + TransitionConnectionUtils.ADD_FIX_VALUE);
   
            positions.clear();
            positions.add(savePositions.get(0));
            positions.add(savePositions.get(1));
            positions.add(value);
            positions.add(savePositions.get(3));
            positions.add(savePositions.get(4));         
         }
      }    

      WorkflowModelEditor editor = null;
      if(!isLoop)
      {
         IWorkbenchWindow workBenchWindow;
         try
         {
            workBenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            IWorkbenchPage page = workBenchWindow.getActivePage();
            if(page != null)
            {
               IEditorPart editor_ = page.getActiveEditor();
               if(editor_ instanceof WorkflowModelEditor)
               {
                  editor = (WorkflowModelEditor) editor_;
               }
            }
         }
         catch (IllegalStateException e)
         {
            // called from servlet
         }      
      }      
      
      // check if we have gateways
      if(!isLoop && editor != null && positions.size() == 5 || positions.size() == 4)
      {
         newPosition = TransitionConnectionUtils.isTransitionWithGateways(conn, savePositions, 
               startPoint__, endPoint__, isVertical, gatewaysFlag);
         if(newPosition != null)
         {
            positions.clear();            
            if(savePositions.size() == 4)
            {        
               if(gatewaysFlag[0])
               {
                  positions.add(savePositions.get(0));
                  positions.add(newPosition);                        
                  positions.add(savePositions.get(2));
                  positions.add(savePositions.get(3));                                                      
               }
               else 
               {
                  positions.add(savePositions.get(0));
                  positions.add(savePositions.get(1));
                  positions.add(newPosition);
                  positions.add(savePositions.get(3));                  
               }
            }
            else if(savePositions.size() == 5)
            {                 
               positions.add(savePositions.get(0));
               positions.add(newPosition);
               positions.add(savePositions.get(2));
               positions.add(newPosition);
               positions.add(savePositions.get(4));
            }            
         }
      }      
      processPositions(realStart, realEnd, positions, startHorizontally, conn);
   }

   /**
    * Gets the constraint for the given {@link Connection}.
    * 
    * @param connection
    *           The connection whose constraint we are retrieving
    * @return The constraint
    */
   public Object getConstraint(Connection connection)
   {
      return constraints.get(connection);
   }

   /**
    * Sets the constraint for the given {@link Connection}.
    * 
    * @param connection
    *           The connection whose constraint we are setting
    * @param constraint
    *           The constraint
    */
   public void setConstraint(Connection connection, Object constraint)
   {
      constraints.put(connection, constraint);
   }

   public Point getStartConnectionPoint(Connection connection)
   {
      return getStartPoint(connection);
   }

   public Point getEndConnectionPoint(Connection connection)
   {
      return getEndPoint(connection);
   }
}