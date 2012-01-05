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
package org.eclipse.stardust.modeling.debug.model.ui;

import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConverter;
import org.eclipse.stardust.engine.core.struct.TypedXPath;

/**
 * Tree item of structured data
 */
public class StructuredValue
{
   private StructuredValue parent;
   private Object data;
   private TypedXPath xPath;
   private IXPathMap xPathMap;

   public StructuredValue(Object data, TypedXPath xPath, StructuredValue parent, IXPathMap xPathMap)
   {
      this.data = data;
      this.xPath = xPath;
      this.parent = parent;
      this.xPathMap = xPathMap;
   }

   public StructuredValue getParent()
   {
      return parent;
   }

   public Object getData()
   {
      return this.data;
   }

   public TypedXPath getXPath()
   {
      return this.xPath;
   }
   
   public String toString() 
   {
      StructuredDataConverter converter = new StructuredDataConverter(xPathMap);
      return converter.toString(data, xPath.getXPath());
   }

   public void setData(Object data)
   {
      this.data = data;
   }
}
