/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.utils;

import java.util.List;


public class ElementBuilderUtils
{

   public static <T, E extends T> String deriveDefaultId(E element, List<T> domain,
         String baseId)
   {
      String id = baseId + (1 + domain.size());
      if (null != XpdlModelUtils.findElementById(domain, id))
      {
         int counter = 1;
         while (true)
         {
            String paddedId = id + "_" + counter;
            if (null == XpdlModelUtils.findElementById(domain, paddedId))
            {
               id = paddedId;
               break;
            }
         }
      }

      return id;
   }

   private ElementBuilderUtils()
   {
      // utility class
   }
}
