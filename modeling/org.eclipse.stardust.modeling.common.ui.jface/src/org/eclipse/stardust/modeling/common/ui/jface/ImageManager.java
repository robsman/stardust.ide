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

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ImageManager implements IImageManager
{
   private final String bundleId;
   
   private final Plugin plugin;
   
   private ImageRegistry imageRegistry;
   
   private ImageRegistry plainImages = new ImageRegistry();
   private ImageRegistry warningImages = new ImageRegistry();
   private ImageRegistry errorImages = new ImageRegistry();   
   private ImageRegistry linkImages = new ImageRegistry();
   private ImageRegistry refImages = new ImageRegistry();
   
   private ImageRegistry ovrIfPlainImages = new ImageRegistry();
   private ImageRegistry ovrIfWarningImages = new ImageRegistry();
   private ImageRegistry ovrIfErrorImages = new ImageRegistry();
   private ImageRegistry ovrIfLinkImages = new ImageRegistry();
   private ImageRegistry ovrIfRefImages = new ImageRegistry();

   private ImageRegistry ovrPrivatePlainImages = new ImageRegistry();
   private ImageRegistry ovrPrivateWarningImages = new ImageRegistry();
   private ImageRegistry ovrPrivateErrorImages = new ImageRegistry();
   private ImageRegistry ovrPrivateLinkImages = new ImageRegistry();
   private ImageRegistry ovrPrivateRefImages = new ImageRegistry();

   public ImageManager(String bundleId)
   {
      this.bundleId = bundleId;
      this.plugin = null;
   }

   public ImageManager(AbstractUIPlugin plugin)
   {
      this.bundleId = null;
      this.plugin = plugin;
   }

   public ImageDescriptor getImageDescriptor(String path)
   {
      ImageDescriptor descriptor = getImageRegistry().getDescriptor(path);
      if (null == descriptor && path != null)
      {
         try
         {
            descriptor = loadImage(path);
         }
         catch (Exception ex)
         {
            // TODO
         }
      }

      return descriptor;
   }

   public Image getImage(String path)
   {
      Image image = getImageRegistry().get(path);
      if (null == image)
      {
         ImageDescriptor descriptor = getImageDescriptor(path);
         if (null != descriptor)
         {
            try
            {
               image = descriptor.createImage();
            }
            catch (Exception ex)
            {
               // eat exception
            }
         }
         if (null != image)
         {
            getImageRegistry().put(path, image);
         }
      }

      return image;
   }

   public Image getPlainIcon(String iconLocator)
   {
      return getIcon(iconLocator, ICON_STYLE_PLAIN);
   }
   
   public Image getIconWithWarnings(String iconLocator)
   {
      return getIcon(iconLocator, ICON_STYLE_WARNINGS);
   }
   
   public Image getIconWithErrors(String iconLocator)
   {
      return getIcon(iconLocator, ICON_STYLE_ERRORS);
   }
   
   public Image getIconWithLink(String iconLocator)
   {
      return getIcon(iconLocator, ICON_STYLE_LINK);
   }
   
   public Image getIconWithRef(String iconLocator)
   {
      return getIcon(iconLocator, ICON_STYLE_REF);
   }
   
   public Image getIcon(String iconLocator, int style)
   {
      ImageRegistry registry = null;
      switch (style)
      {
         case ICON_STYLE_PLAIN:
            registry = plainImages;
            break;
         case ICON_STYLE_WARNINGS:
            registry = warningImages;
            break;
         case ICON_STYLE_ERRORS:
            registry = errorImages;
            break;
         case ICON_STYLE_LINK:
            registry = linkImages;
            break;
         case ICON_STYLE_REF:
            registry = refImages;
            break;
      }
      return registry == null ? null : getIcon(iconLocator, registry, style);
   }

   public ImageDescriptor getIconDescriptor(String iconLocator, int style)
   {
      ImageDescriptor result;

      if (ICON_STYLE_PLAIN == style)
      {
         result = plainImages.getDescriptor(iconLocator);
         if (null == result)
         {
            result = getImageDescriptor(iconLocator);
            if (null != result)
            {
               plainImages.put(iconLocator, result);
            }
         }
      }
      else if (ICON_STYLE_WARNINGS == style)
      {
         result = warningImages.getDescriptor(iconLocator);
         if (null == result)
         {
            ImageDescriptor plainIcon = getIconDescriptor(iconLocator, ICON_STYLE_PLAIN);
            if (null != plainIcon)
            {
               result = new IconWithOverlays(plainIcon, IconWithOverlays.OVR_WARNINGS);
               warningImages.put(iconLocator, result);
            }
         }
      }
      else if (ICON_STYLE_ERRORS == style)
      {
         result = errorImages.getDescriptor(iconLocator);
         if (null == result)
         {
            ImageDescriptor plainIcon = getIconDescriptor(iconLocator, ICON_STYLE_PLAIN);
            if (null != plainIcon)
            {
               result = new IconWithOverlays(plainIcon, IconWithOverlays.OVR_ERRORS);
               errorImages.put(iconLocator, result);
            }
         }
      }
      else if (ICON_STYLE_LINK == style)
      {
         result = linkImages.getDescriptor(iconLocator);
         if (null == result)
         {
            ImageDescriptor plainIcon = getIconDescriptor(iconLocator, ICON_STYLE_PLAIN);
            if (null != plainIcon)
            {
               result = new IconWithOverlays(plainIcon, IconWithOverlays.OVR_LINK);
               linkImages.put(iconLocator, result);
            }
         }
      }
      else if (ICON_STYLE_REF == style)
      {
         result = refImages.getDescriptor(iconLocator);
         if (null == result)
         {
            ImageDescriptor plainIcon = getIconDescriptor(iconLocator, ICON_STYLE_PLAIN);
            if (null != plainIcon)
            {
               result = new IconWithOverlays(plainIcon, IconWithOverlays.OVR_REF);
               refImages.put(iconLocator, result);
            }
         }
      }
      else
      {
         result = null;
      }      
      return result;
   }

   private ImageRegistry getImageRegistry()
   {
      if (plugin instanceof AbstractUIPlugin)
      {
         return ((AbstractUIPlugin) plugin).getImageRegistry();
      }
      else
      {
         if (null == imageRegistry)
         {
            this.imageRegistry = new ImageRegistry();
         }
         return imageRegistry;
      }
   }

   private ImageDescriptor loadImage(String path)
   {
      try
      {
         URL url = new URL(path);
         return ImageDescriptor.createFromURL(url);
      }
      catch (MalformedURLException ex)
      {
         return AbstractUIPlugin.imageDescriptorFromPlugin((null != plugin)
               ? plugin.getBundle().getSymbolicName()
               : bundleId, path);
      }
   }

   private Image getIcon(String iconLocator, ImageRegistry registry, int style)
   {
      Image result = registry.get(iconLocator);
      if (null == result)
      {
         ImageDescriptor iconDescriptor = getIconDescriptor(iconLocator, style);
         if (null != iconDescriptor)
         {
            // try again, retrieving via possibly cached descriptor
            result = registry.get(iconLocator);
            if (null == result)
            {
               // no descriptor cached, explicitly create image and put into cache
               result = iconDescriptor.createImage();
               registry.put(iconLocator, result);
            }            
         }
      }
      return result;
   }

   public void registerImage(String path, Image image)
   {
      getImageRegistry().put(path, image);
   }

   public void registerImage(String path, Image image, int style, boolean isOvrIF)
   {
      ImageRegistry registry = getRegistry(style, isOvrIF);
      if (registry != null)
      {
         registry.put(path, image);
      }
   }

   public Image getImage(String path, int style, boolean isOvrIF)
   {
      Image image = null;
      ImageRegistry registry = getRegistry(style, isOvrIF);
      if (registry != null)
      {
         image = registry.get(path);
      }
      return image;
   }

   private ImageRegistry getRegistry(int style, boolean isOvrIF)
   {
      ImageRegistry registry = null;
      switch (style)
      {
         case ICON_STYLE_PLAIN:
            registry = isOvrIF ? ovrIfPlainImages : ovrPrivatePlainImages;
            break;
         case ICON_STYLE_WARNINGS:
            registry = isOvrIF ? ovrIfWarningImages : ovrPrivateWarningImages;
            break;
         case ICON_STYLE_ERRORS:
            registry = isOvrIF ? ovrIfErrorImages : ovrPrivateErrorImages;
            break;
         case ICON_STYLE_LINK:
            registry = isOvrIF ? ovrIfLinkImages : ovrPrivateLinkImages;
            break;
         case ICON_STYLE_REF:
            registry = isOvrIF ? ovrIfRefImages : ovrPrivateRefImages;
            break;
      }
      return registry;
   }

}