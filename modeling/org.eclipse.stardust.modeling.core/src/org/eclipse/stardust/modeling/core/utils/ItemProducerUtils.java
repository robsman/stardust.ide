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
package org.eclipse.stardust.modeling.core.utils;

import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.modeling.common.platform.utils.ItemConsumer;
import org.eclipse.stardust.modeling.common.platform.utils.ItemProducer;


public class ItemProducerUtils
{
   public static IIdentifiableModelElement findElement(final String id,
         ItemProducer producer)
   {
      final IIdentifiableModelElement[] resultHolder = new IIdentifiableModelElement[1];

      producer.produce(id, new ItemConsumer()
      {
         public void addData(Object object)
         {
            if ((object instanceof IIdentifiableModelElement)
                  && (null == resultHolder[0]))
            {
               IIdentifiableModelElement element = (IIdentifiableModelElement) object;
               if (((null != id) && id.equals(element.getId())) || id == element.getId())
               {
                  resultHolder[0] = element;
               }
            }
         }

         public void produceComplete()
         {
         }

         public void clear()
         {
         }
      });

      return resultHolder[0];
   }
}
