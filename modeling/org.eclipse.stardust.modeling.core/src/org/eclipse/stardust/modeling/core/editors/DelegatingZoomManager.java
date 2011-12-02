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
package org.eclipse.stardust.modeling.core.editors;

import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.jface.util.ListenerList;

public class DelegatingZoomManager extends ZoomManager implements ZoomListener
{
   private ZoomManager currentZoomManager;

   private ListenerList zoomListeners = new ListenerList(3);

   public DelegatingZoomManager()
   {
      super((ScalableFigure) null, (Viewport) null);
   }

   public void zoomChanged(double zoom)
   {
      Object[] listeners = zoomListeners.getListeners();
      for (int i = 0; i < listeners.length; ++i)
      {
         ((ZoomListener) listeners[i]).zoomChanged(zoom);
      }
   }

   public void addZoomListener(ZoomListener listener)
   {
      zoomListeners.add(listener);
   }

   public void removeZoomListener(ZoomListener listener)
   {
      zoomListeners.remove(listener);
   }

   public void setCurrentZoomManager(ZoomManager zoomManager)
   {
      if (currentZoomManager != zoomManager)
      {
         if (null != currentZoomManager)
         {
            currentZoomManager.removeZoomListener(this);
         }

         this.currentZoomManager = zoomManager;
         if (null != currentZoomManager)
         {
            currentZoomManager.addZoomListener(this);
            zoomChanged(currentZoomManager.getZoom());
         }
      }
   }

   public boolean canZoomIn()
   {
      return (null == currentZoomManager) ? false : currentZoomManager.canZoomIn();
   }

   public boolean canZoomOut()
   {
      return (null == currentZoomManager) ? false : currentZoomManager.canZoomOut();
   }

   public double getMaxZoom()
   {
      return (null == currentZoomManager) ? 1 : currentZoomManager.getMaxZoom();
   }

   public double getMinZoom()
   {
      return (null == currentZoomManager) ? 1 : currentZoomManager.getMinZoom();
   }

   public double getNextZoomLevel()
   {
      return (null == currentZoomManager) ? 1 : currentZoomManager.getNextZoomLevel();
   }

   public double getPreviousZoomLevel()
   {
      return (null == currentZoomManager) ? 1 : currentZoomManager.getPreviousZoomLevel();
   }

   public ScalableFigure getScalableFigure()
   {
      return (null == currentZoomManager) ? null : currentZoomManager.getScalableFigure();
   }

   public double getUIMultiplier()
   {
      return (null == currentZoomManager) ? 1 : currentZoomManager.getUIMultiplier();
   }

   public Viewport getViewport()
   {
      return (null == currentZoomManager) ? null : currentZoomManager.getViewport();
   }

   public double getZoom()
   {
      return (null == currentZoomManager) ? 1 : currentZoomManager.getZoom();
   }

   public String getZoomAsText()
   {
      return (null == currentZoomManager) ? " 100%" : currentZoomManager.getZoomAsText(); //$NON-NLS-1$
   }

   public double[] getZoomLevels()
   {
      return (null == currentZoomManager)
            ? new double[] {1}
            : currentZoomManager.getZoomLevels();
   }

   public String[] getZoomLevelsAsText()
   {
      return (null == currentZoomManager)
            ? new String[] {" 100%"} //$NON-NLS-1$
            : currentZoomManager.getZoomLevelsAsText();
   }

   public void setUIMultiplier(double multiplier)
   {
      if (null != currentZoomManager)
      {
         currentZoomManager.setUIMultiplier(multiplier);
      }
   }

   public void setViewLocation(Point p)
   {
      if (null != currentZoomManager)
      {
         currentZoomManager.setViewLocation(p);
      }
   }

   public void setZoom(double zoom)
   {
      if (null != currentZoomManager)
      {
         currentZoomManager.setZoom(zoom);
      }
   }

   public void setZoomAnimationStyle(int style)
   {
      if (null != currentZoomManager)
      {
         currentZoomManager.setZoomAnimationStyle(style);
      }
   }

   public void setZoomAsText(String zoomString)
   {
      if (null != currentZoomManager)
      {
         currentZoomManager.setZoomAsText(zoomString);
      }
   }

   public void setZoomLevels(double[] zoomLevels)
   {
      if (null != currentZoomManager)
      {
         currentZoomManager.setZoomLevels(zoomLevels);
      }
   }

   public void zoomIn()
   {
      if (null != currentZoomManager)
      {
         currentZoomManager.zoomIn();
      }
   }

   public void zoomOut()
   {
      if (null != currentZoomManager)
      {
         currentZoomManager.zoomOut();
      }
   }

   public void zoomTo(Rectangle rect)
   {
      if (null != currentZoomManager)
      {
         currentZoomManager.zoomTo(rect);
      }
   }
}