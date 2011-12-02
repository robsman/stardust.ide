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
package org.eclipse.stardust.modeling.validation.impl;

import java.util.Map;

import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

import ag.carnot.workflow.model.PluggableType;

public class ElValidationPluggableTypAdapter implements PluggableType
{
   boolean nullId;

   private final IMetaType metaType;

   public ElValidationPluggableTypAdapter(IMetaType type)
   {
      this(type, false);
   }

   public ElValidationPluggableTypAdapter(IMetaType type, boolean nullId)
   {
      this.nullId = nullId;

      this.metaType = type;
   }

   public String getId()
   {
      return (nullId) //
            ? null //
            : (null != metaType) ? metaType.getId() : null;
   }

   public String getStringAttribute(String name)
   {
      return AttributeUtil.getAttributeValue(metaType, name);
   }

   //
   // TODO dummy implementations
   //

   public Map getAllAttributes()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Object getAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean getBooleanAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return false;
   }

   public float getFloatAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public int getIntegerAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public long getLongAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return 0;
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
