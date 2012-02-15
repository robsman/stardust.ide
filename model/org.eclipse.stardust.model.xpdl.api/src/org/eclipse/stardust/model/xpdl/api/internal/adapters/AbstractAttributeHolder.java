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
package org.eclipse.stardust.model.xpdl.api.internal.adapters;

import java.util.Map;

import org.eclipse.stardust.common.AttributeHolder;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;

public class AbstractAttributeHolder implements AttributeHolder
{
   protected final IExtensibleElement ieeDelegate;

   public AbstractAttributeHolder(IExtensibleElement delegate)
   {
      this.ieeDelegate = delegate;
   }

   public Object getAttribute(String name)
   {
      return AttributeUtils.getAttribute(ieeDelegate, name);
   }

   public Map getAllAttributes()
   {
      return AttributeUtils.getAllAtttributes(ieeDelegate);
   }

   public String getStringAttribute(String name)
   {
      return (String) getAttribute(name);
   }

   public boolean getBooleanAttribute(String name)
   {
      return Boolean.TRUE.equals(getAttribute(name));
   }

   public int getIntegerAttribute(String name)
   {
      Object value = getAttribute(name);
      return (value instanceof Integer) ? ((Integer) value).intValue() : 0;
   }

   public long getLongAttribute(String name)
   {
      Object value = getAttribute(name);
      return (value instanceof Long) ? ((Long) value).longValue() : 0l;
   }

   public float getFloatAttribute(String name)
   {
      Object value = getAttribute(name);
      return (value instanceof Float) ? ((Float) value).floatValue() : 0.0f;
   }

   public void markModified()
   {
      // TODO Auto-generated method stub

   }

   public void removeAllAttributes()
   {
      // TODO Auto-generated method stub

   }

   public void removeAttribute(String arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setAllAttributes(Map arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setAttribute(String arg0, Object arg1)
   {
      // TODO Auto-generated method stub

   }
}
