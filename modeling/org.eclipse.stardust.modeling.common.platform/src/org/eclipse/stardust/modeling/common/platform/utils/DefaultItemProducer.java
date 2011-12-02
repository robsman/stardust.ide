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
package org.eclipse.stardust.modeling.common.platform.utils;

import java.util.Collection;
import java.util.Iterator;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DefaultItemProducer implements ItemProducer
{
   private Collection<?> data;

   public DefaultItemProducer(Collection<?> data)
   {
      this.data = data;
   }

   public void produce(String hint, ItemConsumer consumer)
   {
      if (data != null)
      {
         for (Iterator<?> i = data.iterator(); i.hasNext();)
         {
            Object value = i.next();
            if (accept(hint, value))
            {
               consumer.addData(value);
            }
         }
      }
      consumer.produceComplete();
   }

   public boolean accept(String hint, Object value)
   {
      return true;
   }
}
