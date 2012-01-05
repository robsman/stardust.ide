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

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PrinterGraphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.utils.TransitionConnectionUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;


public class TransitionConnectionFigure extends AbstractConnectionSymbolFigure implements IPropertyChangeListener
{
   private Shape rect;

   private Label label;

   private static final boolean SHOW_BORDER = false;

   private static final boolean ROUNDED = false;

   private static final boolean USE_TOOLTIP = true;

   private static final boolean USE_TOOLTIP_COLORS = true;

   /**
    * The following constants specify how connection segment endpoints have to be modified
    * to support rounded corners. Each row corresponds to one of the eight possible corner
    * kinds.
    * <p />
    * Format of row is
    * <ol>
    * <li>radius prefactor for x coordinate of segment end</li>
    * <li>radius prefactor for y coordinate of segment end</li>
    * <li>radius prefactor for x coordinate of next segment start</li>
    * <li>radius prefactor for y coordinate of next segment start</li>
    * <li>radius prefactor for x coordinate of corner bounding box</li>
    * <li>radius prefactor for y coordinate of corner bounding box</li>
    * <li>start angle of corner arc</li>
    * </ol>
    */
   private static final int[][] MODIFIERS =
   {
      { 0,  0,  0,  0,  0,  0,   0}, // unmodified corner
      { 0,  1,  1,  0,  0,  0,  90}, // SN, WE
      { 0,  1, -1,  0, -2,  0,   0}, // SN, EW
      { 0, -1,  1,  0,  0, -2, 180}, // NS, WE
      { 0, -1, -1,  0, -2, -2, 270}, // NS, EW
      { 1,  0,  0,  1,  0,  0,  90}, // EW, NS
      {-1,  0,  0,  1, -2,  0,   0}, // WE, NS
      { 1,  0,  0, -1,  0, -2, 180}, // EW, SN
      {-1,  0,  0, -1, -2, -2, 270}, // WE, SN
   };

   public static final Color FG_COLOR = new Color(null, 0, 102, 121);

   private int antialiasMode = SWT.ON;

   private Point startPoint;
   private Point endPoint;

   private String transitionLabel;

   private TransitionConnectionType transition;
   
   public TransitionConnectionFigure(TransitionConnectionType transition)
   {
      this.transition = transition;
      setDefaultBorderColor(FG_COLOR);
      setDefaultFillColor(FG_COLOR);
      setDefaultRouting(ROUTING_MANHATTAN);

      if(TransitionConnectionUtils.showForkOnTraversal(transition))
      {
         setSourceDecoration(new ForkOnTraversalDecoration());         
      }
      else
      {
         setSourceDecoration(null);         
      }
      // arrow at target endpoint
      setTargetDecoration(new SequenceFlowArrowhead());
      getTargetDecoration().setForegroundColor(FG_COLOR);
      getTargetDecoration().setBackgroundColor(FG_COLOR);
      
      // register to preference store
      IPreferenceStore store = PlatformUI.getPreferenceStore();
      store.addPropertyChangeListener(this);
   }

   protected void finalize() throws Throwable
   {
      // remove from preference store
      IPreferenceStore store = PlatformUI.getPreferenceStore();
      store.removePropertyChangeListener(this);
      super.finalize();
   }

   // overwriten because in super it is protected
   public RotatableDecoration getTargetDecoration() {
      return super.getTargetDecoration();
   }   
   
   public void setLabel(final String text)
   {
      transitionLabel = text;
      int preferredWidth = ((startPoint != null && endPoint != null)
            && (Math.abs(startPoint.x - endPoint.x) < 200)
                  && (Math.abs(startPoint.y - endPoint.y) < 60))
            ? Math.abs(startPoint.x - endPoint.x) - 20
            : 200;

      if (text == null || text.length() == 0)
      {
         if (label != null)
         {
            remove(rect);
            rect.remove(label);
            label = null;
            rect = null;
         }
      }
      else
      {
         if (label == null)
         {
            if (ROUNDED)
            {
               rect = new RoundedRectangle()
               {
                  public Dimension getPreferredSize(int wHint, int hHint)
                  {
                     return super.getPreferredSize(wHint == -1 ? 200 : wHint, hHint);
                  }
               };
            }
            else
            {
               rect = new RectangleFigure()
               {
                  public Dimension getPreferredSize(int wHint, int hHint)
                  {
                     return super.getPreferredSize(wHint == -1 ? 200 : wHint, hHint);
                  }
               };
            }
            rect.setOutline(SHOW_BORDER);
            label = new Label(text);

            // Workaround (rsauer): explicity setting font to prevent NPE in headless mode
            if (null == label.getFont())
            {
               label.setFont(JFaceResources.getFontRegistry().get(
                     JFaceResources.DIALOG_FONT));
            }

            if (USE_TOOLTIP)
            {
               label.setToolTip(new Label(text));
            }
            else
            {
               label.addMouseMotionListener(new MouseMotionListener()
               {
                  private boolean hoover = false;

                  public void mouseDragged(MouseEvent me)
                  {
                     restoreLayout();
                  }

                  public void mouseEntered(MouseEvent me)
                  {
                  }

                  public void mouseExited(MouseEvent me)
                  {
                     restoreLayout();
                  }

                  public void mouseHover(MouseEvent me)
                  {
                     hoover = true;
                     rect.setPreferredSize(label.getPreferredSize(-1, -1));
                     if (USE_TOOLTIP_COLORS)
                     {
                        rect.setForegroundColor(ColorConstants.tooltipForeground);
                        rect.setBackgroundColor(ColorConstants.tooltipBackground);
                     }
                  }

                  public void mouseMoved(MouseEvent me)
                  {
                  }

                  private void restoreLayout()
                  {
                     if (hoover)
                     {
                        rect.setPreferredSize(null);
                        if (USE_TOOLTIP_COLORS)
                        {
                           rect.setForegroundColor(TransitionConnectionFigure.this.getForegroundColor());
                           rect.setBackgroundColor(ColorConstants.listBackground);
                        }
                        hoover = false;
                     }
                  }
               });
            }
            label.setBorder(new MarginBorder(0, 5, 3, 5));
            rect.setBackgroundColor(ColorConstants.listBackground);
            rect.setLayoutManager(new BorderLayout());
            rect.add(label, BorderLayout.CENTER);
            add(rect, new ConnectionLocator(this)
            {
               private final PointList plOverride = new PointList(2);

               private final Point p0 = new Point();
               private final Point p1 = new Point();
               private final Point p2 = new Point();

               protected Point getLocation(PointList points)
               {
                  // locate label in middle of first vertical segment, if possible
                  PointList pl;
                  if (2 < points.size())
                  {
                     plOverride.removeAllPoints();
                     
                     points.getPoint(p0, 0);
                     points.getPoint(p1, 1);
                     points.getPoint(p2, 2);
                     
                     if (0 == (p1.x - p0.x))
                     {
                        // first segment is vertical
                        plOverride.addPoint(p0);
                        plOverride.addPoint(p1);
         }
                     else if (0 == (p2.x - p1.x))
                     {
                        // second segment is vertical
                        plOverride.addPoint(p1);
                        plOverride.addPoint(p2);
                     }
                     else
                     {
                        // none of first two segments is vertical, so locate on first
                        plOverride.addPoint(p0);
                        plOverride.addPoint(p1);
                     }
                     
                     pl = plOverride;
                  }
                  else
                  {
                     // two points or foewer, fall back two default behavior
                     pl = points;
                  }
                  return super.getLocation(pl);
               }
            });
         }
         else if (!text.equals(label.getText()))
         {
            label.setText(text);
            if (USE_TOOLTIP)
            {
               label.setToolTip(new Label(text));
            }
         }
         if (preferredWidth < 200)
         {
            rect.setSize(preferredWidth, rect.getPreferredSize().height);
            rect.setLocation(new Point(startPoint.x + 5, rect.getLocation().y));
         }
      }
   }

   protected void outlineShape(Graphics g)
   {
      int DEFAULT_RADIUS = 5;

      boolean printing = g instanceof PrinterGraphics;

      int[] sourceModifiers = MODIFIERS[0];

      PointList points = getPoints();

      if (points.size() > 2)
      {
         Point start = new Point();
         Point middle = new Point();
         Point end = new Point();

         int lastRadius = 0;

         g.pushState();
         try
         {
            for (int i = 0; i < points.size() - 1; i++)
            {
               points.getPoint(start, i);
               points.getPoint(middle, i + 1);
               points.getPoint(end, Math.min(i + 2, points.size() - 1));

               if (start.equals(middle))
               {
                  continue;
               }

               int radius;

               int[] targetModifiers = MODIFIERS[0];
               if (start.x == middle.x)
               {
                  radius = Math.min(DEFAULT_RADIUS, Math.abs(end.x - middle.x) / 2);
                  radius = Math.min(radius, Math.abs(middle.y - start.y) / 2);

                  if (start.y > middle.y)
                  {
                     if (middle.x < end.x)
                     {
                        targetModifiers = MODIFIERS[1];
                     }
                     else if (middle.x > end.x)
                     {
                        targetModifiers = MODIFIERS[2];
                     }
                  }
                  else if (start.y < middle.y)
                  {
                     if (middle.x < end.x)
                     {
                        targetModifiers = MODIFIERS[3];
                     }
                     else if (middle.x > end.x)
                     {
                        targetModifiers = MODIFIERS[4];
                     }
                  }
               }
               else if (start.y == middle.y)
               {
                  radius = Math.min(DEFAULT_RADIUS, Math.abs(end.y - middle.y) / 2);
                  radius = Math.min(radius, Math.abs(middle.x - start.x) / 2);

                  if (middle.y < end.y)
                  {
                     if (start.x > middle.x)
                     {
                        targetModifiers = MODIFIERS[5];
                     }
                     else if (start.x < middle.x)
                     {
                        targetModifiers = MODIFIERS[6];
                     }
                  }
                  else if (middle.y > end.y)
                  {
                     if (start.x > middle.x)
                     {
                        targetModifiers = MODIFIERS[7];
                     }
                     else if (start.x < middle.x)
                     {
                        targetModifiers = MODIFIERS[8];
                     }
                  }
               }
               else
               {
                  radius = 0;
               }

               boolean retry = false;
               do
               {
                  try
                  {
                     if (!printing)
                     {
                        g.setAntialias(antialiasMode);
                     }

                     g.drawLine(start.x + lastRadius * sourceModifiers[2], start.y
                           + lastRadius * sourceModifiers[3], middle.x + radius
                           * targetModifiers[0], middle.y + radius * targetModifiers[1]);

                     if ((0 != targetModifiers[0]) || (0 != targetModifiers[1]))
                     {
                        g.drawArc(middle.x + radius * targetModifiers[4], middle.y
                              + radius * targetModifiers[5], 2 * radius, 2 * radius,
                              targetModifiers[6], 90);
                     }
                     retry = false;
                  }
                  catch (SWTException e)
                  {
                     if (!printing && SWT.ON == antialiasMode)
                     {
                        // try again without anti-aliasing (has problems on Linux with SWT
                        // version <= 3.1.1)
                        this.antialiasMode = (SWT.ON == antialiasMode)
                              ? SWT.DEFAULT
                              : SWT.OFF;
                        retry = true;
                     }

                     if (!retry)
                     {
                        throw e;
                     }
                  }
               }
               while (retry);

               sourceModifiers = targetModifiers;
               lastRadius = radius;
            }
         }
         finally
         {
            g.popState();
         }
      }
      else
      {
         super.outlineShape(g);
      }
   }

   public void layout()
   {      
      super.layout();

      if (null != getConnectionRouter())
      {
         Point startConnectionPoint = getStartPoint(this);
         Point endConnectionPoint = getEndPoint(this);
         if (startConnectionPoint != null && endConnectionPoint != null)
         {
            startPoint = startConnectionPoint;
            endPoint = endConnectionPoint;
            setLabel(transitionLabel);
         }
      }
      updateForkOnTraversal(null);
   }

   /**
    * Copied as original method is access restricted.
    * 
    * @see AbstractRouter#getEndPoint(Connection)
    */
   private static Point getEndPoint(Connection connection)
   {
      Point ref = connection.getSourceAnchor().getReferencePoint();
      return connection.getTargetAnchor().getLocation(ref).getCopy();
   }

   /**
    * Copied as original method is access restricted.
    * 
    * @see AbstractRouter#getStartPoint(Connection)
    */
   private static Point getStartPoint(Connection conn)
   {
      Point ref = conn.getTargetAnchor().getReferencePoint();
      return conn.getSourceAnchor().getLocation(ref).getCopy();
   }

   public void propertyChange(PropertyChangeEvent event)
   {
      if(event.getProperty().equals(BpmProjectNature.PREFERENCE_VIEW_FORK_ON_TRAVERSAL_MODE))
      {      
         updateForkOnTraversal(null);      
      }
   }

   public void updateForkOnTraversal(Boolean show)
   {
      boolean showForkOnTraversal;
      if(show == null)
      {
         showForkOnTraversal = TransitionConnectionUtils.showForkOnTraversal(transition);
      }
      else
      {
         showForkOnTraversal = show.booleanValue();
      }
      if(showForkOnTraversal && getSourceDecoration() == null)
      {
         setSourceDecoration(new ForkOnTraversalDecoration());         
      }
      else if(!showForkOnTraversal && getSourceDecoration() != null)
      {
         setSourceDecoration(null);         
      }
   }
}