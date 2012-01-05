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

import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.StringKey;

/**
 * @author rsauer
 * @version $Revision$
 */
public class StringKeyAdapter extends ExtensibleElementValueAdapter
{
   private final Class domain;

   public StringKeyAdapter(Class domain)
   {
      Assert.condition(StringKey.class.isAssignableFrom(domain), ""); //$NON-NLS-1$

      this.domain = domain;
   }

   public Object fromModel(ExtensibleElementAdapter binding, Object value)
   {
      if (value instanceof String)
      {
         return StringKey.getKey(domain, (String) value);
      }
      else
      {
         return super.fromModel(binding, value);
      }
   }

   public Object toModel(ExtensibleElementAdapter binding, Object value)
   {
      if (value instanceof StringKey)
      {
         return ((StringKey) value).getId();
      }
      else
      {
         return super.toModel(binding, value);
      }
   }
}
