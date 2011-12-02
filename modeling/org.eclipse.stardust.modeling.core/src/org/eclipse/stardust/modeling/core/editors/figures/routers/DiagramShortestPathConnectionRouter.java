/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Sungard - Improved some issues concerning connection routing and deletion of lanes 
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.editors.figures.routers;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Path;
import org.eclipse.draw2d.graph.ShortestPathRouter;
import org.eclipse.stardust.modeling.core.editors.figures.LaneFigure;


public class DiagramShortestPathConnectionRouter extends AbstractRouter
{
   private class LayoutTracker extends LayoutListener.Stub
   {
      public void postLayout(IFigure container)
      {
         processLayout();
      }

      public void remove(IFigure child)
      {
         if (!(child instanceof LaneFigure))
         {
            removeChild(child);
         }
         else
         {
            for (int i = 0; i < child.getChildren().size(); i++)
            {
               removeChild((IFigure) child.getChildren().get(i));
            }
         }
      }

      public void setConstraint(IFigure child, Object constraint)
      {
         if (!(child instanceof LaneFigure))
         {
            addChild(child);
         }
         else
         {
            for (int i = 0; i < child.getChildren().size(); i++)
            {
               addChild((IFigure) child.getChildren().get(i));
            }
         }
      }
   }

   private Map constraintMap = new HashMap();

   private Map figuresToBounds;

   private Map connectionToPaths;

   private boolean isDirty;

   private ShortestPathRouter algorithm = new ShortestPathRouter();

   private IFigure container;

   private Set staleConnections = new HashSet();

   private LayoutListener listener = new LayoutTracker();

   private FigureListener figureListener = new FigureListener()
   {
      public void figureMoved(IFigure source)
      {
         Rectangle newBounds = source.getBounds().getCopy();
         IFigure parent = source.getParent();
         if (parent instanceof LaneFigure)
         {
            getTranslatedBounds(newBounds, parent);
         }
         if (algorithm.updateObstacle((Rectangle) figuresToBounds.get(source), newBounds))
         {
            queueSomeRouting();
            isDirty = true;
         }
         figuresToBounds.put(source, newBounds);
      }
   };

   private boolean ignoreInvalidate;

   public DiagramShortestPathConnectionRouter(IFigure container)
   {
      isDirty = false;
      algorithm = new ShortestPathRouter();
      this.container = container;
   }

   void addChild(IFigure child)
   {
      if (connectionToPaths == null)
         return;
      if (figuresToBounds.containsKey(child))
         return;
      Rectangle bounds = child.getBounds().getCopy();
      if (child.getParent() instanceof LaneFigure)
      {
         getTranslatedBounds(bounds, child.getParent());
      }
      algorithm.addObstacle(bounds);
      figuresToBounds.put(child, bounds);
      child.addFigureListener(figureListener);
      isDirty = true;
   }

   private void hookAll()
   {
      figuresToBounds = new HashMap();
      for (int i = 0; i < container.getChildren().size(); i++)
      {
         if (container.getChildren().get(i) instanceof LaneFigure)
         {
            for (int j = 0; j < ((Figure) container.getChildren().get(i)).getChildren()
                  .size(); j++)
            {
               addChild((IFigure) ((Figure) container.getChildren().get(i)).getChildren()
                     .get(j));
            }
            ((LaneFigure) container.getChildren().get(i)).addLayoutListener(listener);
         }
         else
         {
            addChild((IFigure) container.getChildren().get(i));
         }
      }
      container.addLayoutListener(listener);
   }

   private void unhookAll()
   {
      container.removeLayoutListener(listener);
      for (Iterator iter = container.getChildren().iterator(); iter.hasNext();)
      {
         IFigure child = (IFigure) iter.next();
         if (child instanceof LaneFigure)
         {
            ((LaneFigure) child).removeLayoutListener(listener);
         }
      }
      if (figuresToBounds != null)
      {
         Iterator figureItr = figuresToBounds.keySet().iterator();
         while (figureItr.hasNext())
         {
            IFigure child = (IFigure) figureItr.next();
            figureItr.remove();
            removeChild(child);
         }
         figuresToBounds = null;
      }
   }

   public Object getConstraint(Connection connection)
   {
      return constraintMap.get(connection);
   }

   public void invalidate(Connection connection)
   {
      if (ignoreInvalidate)
         return;
      staleConnections.add(connection);
      isDirty = true;
   }

   private void processLayout()
   {
      if (staleConnections.isEmpty())
         return;
      ((Connection) staleConnections.iterator().next()).revalidate();
   }

   private void processStaleConnections()
   {
      Iterator iter = staleConnections.iterator();
      if (iter.hasNext() && connectionToPaths == null)
      {
         connectionToPaths = new HashMap();
         hookAll();
      }

      while (iter.hasNext())
      {
         Connection conn = (Connection) iter.next();

         Path path = (Path) connectionToPaths.get(conn);
         if (path == null)
         {
            path = new Path(conn);
            connectionToPaths.put(conn, path);
            algorithm.addPath(path);
         }

         List constraint = (List) getConstraint(conn);
         if (constraint == null)
            constraint = Collections.EMPTY_LIST;

         Point start = conn.getSourceAnchor().getReferencePoint().getCopy();
         Point end = conn.getTargetAnchor().getReferencePoint().getCopy();

         container.translateToRelative(start);
         container.translateToRelative(end);

         path.setStartPoint(start);
         path.setEndPoint(end);

         if (!constraint.isEmpty())
         {
            PointList bends = new PointList(constraint.size());
            for (int i = 0; i < constraint.size(); i++)
            {
               Bendpoint bp = (Bendpoint) constraint.get(i);
               bends.addPoint(bp.getLocation());
            }
            path.setBendPoints(bends);
         }
         else
            path.setBendPoints(null);

         isDirty |= path.isDirty;
      }
      staleConnections.clear();
   }

   void queueSomeRouting()
   {
      if (connectionToPaths == null || connectionToPaths.isEmpty())
         return;
      try
      {
         ignoreInvalidate = true;
         ((Connection) connectionToPaths.keySet().iterator().next()).revalidate();
      }
      finally
      {
         ignoreInvalidate = false;
      }
   }

   public void remove(Connection connection)
   {
      staleConnections.remove(connection);
      constraintMap.remove(connection);
      if (connectionToPaths == null)
         return;
      Path path = (Path) connectionToPaths.remove(connection);
      algorithm.removePath(path);
      isDirty = true;
      if (connectionToPaths.isEmpty())
      {
         unhookAll();
         connectionToPaths = null;
      }
      else
      {
         queueSomeRouting();
      }
   }

   void removeChild(IFigure child)
   {
      if (connectionToPaths == null)
         return;
      Rectangle bounds = child.getBounds().getCopy();
      if (child.getParent() instanceof LaneFigure)
      {
         getTranslatedBounds(bounds, child.getParent());
      }
      boolean change = false;
      
      try
      {
         change = algorithm.removeObstacle(bounds);
      }
      catch (Throwable t)
      {
         //Ignore
      }

      figuresToBounds.remove(child);
      child.removeFigureListener(figureListener);
      if (change)
      {
         isDirty = true;
         queueSomeRouting();
      }
   }

   public void route(Connection conn)
   {
      if (isDirty)
      {
         ignoreInvalidate = true;
         processStaleConnections();
         isDirty = false;
         List updated = algorithm.solve();

         Connection current;
         for (int i = 0; i < updated.size(); i++)
         {
            Path path = (Path) updated.get(i);
            current = (Connection) path.data;
            current.revalidate();

            PointList points = path.getPoints().getCopy();
            Point ref1, ref2, start, end;
            ref1 = new PrecisionPoint(points.getPoint(1));
            ref2 = new PrecisionPoint(points.getPoint(points.size() - 2));
            current.translateToAbsolute(ref1);
            current.translateToAbsolute(ref2);

            start = current.getSourceAnchor().getLocation(ref1).getCopy();
            end = current.getTargetAnchor().getLocation(ref2).getCopy();

            current.translateToRelative(start);
            current.translateToRelative(end);
            points.setPoint(start, 0);
            points.setPoint(end, points.size() - 1);

            current.setPoints(points);
         }
         ignoreInvalidate = false;
      }
   }

   public void setConstraint(Connection connection, Object constraint)
   {
      staleConnections.add(connection);
      constraintMap.put(connection, constraint);
      isDirty = true;
   }

   private void getTranslatedBounds(Rectangle newBounds, IFigure parent)
   {
      Rectangle parentBounds = parent.getBounds().getCopy();
      Insets insets = parent.getBorder().getInsets(parent);
      Rectangle borderBounds = new Rectangle(insets.left, insets.top, insets.getWidth(),
            insets.getHeight());
      newBounds.translate(parentBounds.getLocation());
      newBounds.translate(borderBounds.getLocation());
   }

}

