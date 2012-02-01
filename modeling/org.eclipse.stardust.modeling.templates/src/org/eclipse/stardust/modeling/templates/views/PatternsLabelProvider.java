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

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;
import org.eclipse.swt.graphics.Image;


public class PatternsLabelProvider extends LabelProvider
{
   public Image getImage(Object element)
   {      
      if (element instanceof Category) {
         URL url = Platform.getBundle("org.eclipse.stardust.modeling.templates").getResource("icons/folder.gif"); //$NON-NLS-1$ //$NON-NLS-2$
         ImageDescriptor image = ImageDescriptor.createFromURL(url);
         return image.createImage();         
      } else {
         URL url = Platform.getBundle("org.eclipse.stardust.modeling.templates").getResource("icons/diagram.gif"); //$NON-NLS-1$ //$NON-NLS-2$
         ImageDescriptor image = ImageDescriptor.createFromURL(url);
         return image.createImage();               
      }
   }

   public String getText(Object element)
   {
      if (element instanceof Category)
      {
         return ((Category) element).getName();
      }
      if (element instanceof ITemplate)
      {
         return ((ITemplate) element).getName();
      }
      return super.getText(element);
   }
}
