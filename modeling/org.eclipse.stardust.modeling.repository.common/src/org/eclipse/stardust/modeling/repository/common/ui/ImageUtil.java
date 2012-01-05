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
package org.eclipse.stardust.modeling.repository.common.ui;

import java.util.Map;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.modeling.common.ui.jface.IImageManager;
import org.eclipse.stardust.modeling.common.ui.jface.ImageManager;

public class ImageUtil
{
   private static Map<String, IImageManager> managers;

   public static IImageManager getImageManager(String bundleId)
   {
      if (managers == null)
      {
         managers = CollectionUtils.newMap();
      }
      IImageManager im = (IImageManager) managers.get(bundleId);
      if (im == null)
      {
         im = new ImageManager(bundleId);
         managers.put(bundleId, im);
      }
      return im;
   }
}
