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
package org.eclipse.stardust.modeling.common.ui.jface;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

public class IconWithOverlays extends CompositeImageDescriptor
{
   public int defaultPosition = IDecoration.BOTTOM_LEFT; 
   
   public static int OVR_NONE = 0;

   public static int OVR_WARNINGS = 1 << 0;

   public static int OVR_ERRORS = 1 << 1;

   public static int OVR_LINK = 1 << 2;

   public static int OVR_REF = 1 << 3;
   
   public static int OVR_IF = 1 << 4;
   
   public static int OVR_PRIVATE = 1 << 5;

   private final ImageData baseIconData;

   private final ImageData overlayData;

   public IconWithOverlays(String baseIconLocator, int overlayMask)
   {
      this(CarnotUiPlugin.getDefault().getImageManager().getImageDescriptor(
            baseIconLocator), overlayMask);
   }

   public IconWithOverlays(ImageDescriptor baseIconDescriptor, int overlayMask)
   {
      this.baseIconData = baseIconDescriptor.getImageData();

      this.overlayData = CarnotUiPlugin.getDefault().getImageManager().getImage(
            getOverlayPath(overlayMask)).getImageData();
   }

   public IconWithOverlays(Image baseIcon, int overlayMask)
   {
      this.baseIconData = baseIcon.getImageData();

      this.overlayData = CarnotUiPlugin.getDefault().getImageManager().getImage(
            getOverlayPath(overlayMask)).getImageData();
   }
   
   public IconWithOverlays(Image baseIcon, Image overlay, int position)
   {
      this.baseIconData = baseIcon.getImageData();
      this.overlayData = overlay.getImageData();
      defaultPosition = position;
   }

   private String getOverlayPath(int overlayMask)
   {
      if ((overlayMask & OVR_REF) != 0)
      {
         return CarnotUiPlugin.PATH_OVR_REF;
      }
      if ((overlayMask & OVR_IF) != 0)
      {
         return CarnotUiPlugin.PATH_OVR_INTERFACE;
      }
      if ((overlayMask & OVR_PRIVATE) != 0)
      {
         return CarnotUiPlugin.PATH_OVR_PRIVATE;
      }
      if ((overlayMask & OVR_LINK) != 0)
      {
         return CarnotUiPlugin.PATH_OVR_LINK;
      }
      if ((overlayMask & OVR_ERRORS) != 0)
      {
         return CarnotUiPlugin.PATH_OVR_ERRORS;
      }
      return CarnotUiPlugin.PATH_OVR_WARNINGS;
   }

   protected void drawCompositeImage(int width, int height)
   {
      this.drawImage(baseIconData, 0, 0);
      if (null != overlayData)
      {
         switch (defaultPosition) {
         case IDecoration.TOP_LEFT:
            drawImage(overlayData, 0, 0);
            break;
         case IDecoration.TOP_RIGHT:
            drawImage(overlayData, baseIconData.width - overlayData.width, 0);
            break;
         case IDecoration.BOTTOM_LEFT:
            drawImage(overlayData, 0, baseIconData.height - overlayData.height);            
            break;
         case IDecoration.BOTTOM_RIGHT:
            drawImage(overlayData, baseIconData.width - overlayData.width, baseIconData.height - overlayData.height);            
            break;
         }
      }      
   }

   protected Point getSize()
   {
      return new Point(baseIconData.width, baseIconData.height);
   }
}