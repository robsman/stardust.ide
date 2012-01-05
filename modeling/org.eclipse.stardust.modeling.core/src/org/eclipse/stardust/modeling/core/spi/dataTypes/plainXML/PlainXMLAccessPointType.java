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
package org.eclipse.stardust.modeling.core.spi.dataTypes.plainXML;

import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.model.xpdl.carnot.impl.AccessPointTypeImpl;

public class PlainXMLAccessPointType extends AccessPointTypeImpl
{
   private TypedXPath xPath;
   private IXPathMap xPathMap;

   public PlainXMLAccessPointType(TypedXPath xPath, IXPathMap xPathMap)
   {
      this.xPath = xPath;
      this.xPathMap = xPathMap;
   }

   public TypedXPath getXPath()
   {
      return xPath;
   }

   public IXPathMap getXPathMap()
   {
      return xPathMap;
   }

   public int hashCode()
   {
      final int PRIME = 31;
      int result = 1;
      result = PRIME * result + ((name == null) ? 0 : name.hashCode());
      return result;
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      final PlainXMLAccessPointType other = (PlainXMLAccessPointType) obj;
      if (name == null)
      {
         if (other.name != null)
            return false;
      }
      else if (!name.equals(other.name))
         return false;
      return true;
   }
}