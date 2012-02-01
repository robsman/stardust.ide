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
package org.eclipse.stardust.modeling.templates.views;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.net.URL;

import javax.swing.text.Element;
import javax.swing.text.html.HTML;
import javax.swing.text.html.ImageView;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;


public class ExtendedImageView extends ImageView 
{

   private Image theImage;
   private URL url;

   public ExtendedImageView(ITemplate template, Element elem)
   {
      super(elem);      
      String fullPath = (String)getElement().getAttributes().getAttribute(HTML.Attribute.SRC);      
      String imageLocation = fullPath.substring(fullPath.indexOf("/"), fullPath.length()); //$NON-NLS-1$
      //Really?
      url = template.getParentFactory().getClass().getResource(imageLocation);
      ImageDescriptor image = ImageDescriptor.createFromURL(url);
      ImageData imageData = image.getImageData();
      theImage = convertToAWT(imageData);
   }

   public Image getImage()
   {
      return theImage;
   }

   public URL getImageURL()
   {
      return url;
   }
   
   private BufferedImage convertToAWT(ImageData data) {
      ColorModel colorModel = null;
      PaletteData palette = data.palette;
      if (palette.isDirect) {
        colorModel = new DirectColorModel(data.depth, palette.redMask,
            palette.greenMask, palette.blueMask);
        BufferedImage bufferedImage = new BufferedImage(colorModel,
            colorModel.createCompatibleWritableRaster(data.width,
                data.height), false, null);
        WritableRaster raster = bufferedImage.getRaster();
        int[] pixelArray = new int[3];
        for (int y = 0; y < data.height; y++) {
          for (int x = 0; x < data.width; x++) {
            int pixel = data.getPixel(x, y);
            RGB rgb = palette.getRGB(pixel);
            pixelArray[0] = rgb.red;
            pixelArray[1] = rgb.green;
            pixelArray[2] = rgb.blue;
            raster.setPixels(x, y, 1, 1, pixelArray);
          }
        }
        return bufferedImage;
      } else {
        RGB[] rgbs = palette.getRGBs();
        byte[] red = new byte[rgbs.length];
        byte[] green = new byte[rgbs.length];
        byte[] blue = new byte[rgbs.length];
        for (int i = 0; i < rgbs.length; i++) {
          RGB rgb = rgbs[i];
          red[i] = (byte) rgb.red;
          green[i] = (byte) rgb.green;
          blue[i] = (byte) rgb.blue;
        }
        if (data.transparentPixel != -1) {
          colorModel = new IndexColorModel(data.depth, rgbs.length, red,
              green, blue, data.transparentPixel);
        } else {
          colorModel = new IndexColorModel(data.depth, rgbs.length, red,
              green, blue);
        }
        BufferedImage bufferedImage = new BufferedImage(colorModel,
            colorModel.createCompatibleWritableRaster(data.width,
                data.height), false, null);
        WritableRaster raster = bufferedImage.getRaster();
        int[] pixelArray = new int[1];
        for (int y = 0; y < data.height; y++) {
          for (int x = 0; x < data.width; x++) {
            int pixel = data.getPixel(x, y);
            pixelArray[0] = pixel;
            raster.setPixel(x, y, pixelArray);
          }
        }
        return bufferedImage;
      }
    }



}
