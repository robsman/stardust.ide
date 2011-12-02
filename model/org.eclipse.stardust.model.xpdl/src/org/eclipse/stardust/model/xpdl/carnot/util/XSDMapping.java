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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class XSDMapping
{
   private static Map mapping = new HashMap();
   
   static
   {
      mapping.put("anySimpleType", String.class.getName()); //$NON-NLS-1$
      mapping.put("anyURI", String.class.getName()); //$NON-NLS-1$
      mapping.put("base64Binary", String.class.getName()); //$NON-NLS-1$
      mapping.put("boolean", Boolean.class.getName()); //$NON-NLS-1$
      mapping.put("byte", Byte.class.getName()); //$NON-NLS-1$
      mapping.put("date", Date.class.getName()); //$NON-NLS-1$
      mapping.put("dateTime", Date.class.getName()); //$NON-NLS-1$
      mapping.put("decimal", BigDecimal.class.getName()); //$NON-NLS-1$
      mapping.put("double", Double.class.getName()); //$NON-NLS-1$
      mapping.put("duration", Long.class.getName()); //$NON-NLS-1$
      mapping.put("ENTITIES", String.class.getName()); //$NON-NLS-1$
      mapping.put("ENTITY", String.class.getName()); //$NON-NLS-1$
      mapping.put("float", Float.class.getName()); //$NON-NLS-1$      
      mapping.put("gDay", String.class.getName()); //$NON-NLS-1$
      mapping.put("gMonth", String.class.getName()); //$NON-NLS-1$
      mapping.put("gMonthDay", String.class.getName()); //$NON-NLS-1$
      mapping.put("gYear", String.class.getName()); //$NON-NLS-1$
      mapping.put("gYearMonth", String.class.getName()); //$NON-NLS-1$
      mapping.put("hexBinary", String.class.getName()); //$NON-NLS-1$
      mapping.put("ID", String.class.getName()); //$NON-NLS-1$
      mapping.put("IDREF", String.class.getName()); //$NON-NLS-1$
      mapping.put("IDREFS", String.class.getName()); //$NON-NLS-1$
      mapping.put("int", Integer.class.getName()); //$NON-NLS-1$
      mapping.put("integer", Integer.class.getName()); //$NON-NLS-1$      
      mapping.put("language", String.class.getName()); //$NON-NLS-1$
      mapping.put("long", Long.class.getName()); //$NON-NLS-1$  
      mapping.put("Name", String.class.getName()); //$NON-NLS-1$
      mapping.put("NCName", String.class.getName()); //$NON-NLS-1$
      mapping.put("negativeInteger", Integer.class.getName()); //$NON-NLS-1$      
      mapping.put("NMTOKEN", String.class.getName()); //$NON-NLS-1$
      mapping.put("NMTOKENS", String.class.getName()); //$NON-NLS-1$
      mapping.put("nonNegativeInteger", Integer.class.getName()); //$NON-NLS-1$
      mapping.put("nonPositiveInteger", Integer.class.getName()); //$NON-NLS-1$
      mapping.put("normalizedString", String.class.getName()); //$NON-NLS-1$
      mapping.put("NOTATION", String.class.getName()); //$NON-NLS-1$
      mapping.put("positiveInteger", Integer.class.getName()); //$NON-NLS-1$
      mapping.put("QName", String.class.getName()); //$NON-NLS-1$      
      mapping.put("short", Short.class.getName()); //$NON-NLS-1$
      mapping.put("string", String.class.getName()); //$NON-NLS-1$
      mapping.put("time", Date.class.getName()); //$NON-NLS-1$
      mapping.put("token", String.class.getName()); //$NON-NLS-1$
      mapping.put("unsignedByte", Byte.class.getName()); //$NON-NLS-1$
      mapping.put("unsignedInt", Integer.class.getName()); //$NON-NLS-1$
      mapping.put("unsignedLong", Long.class.getName()); //$NON-NLS-1$        
      mapping.put("unsignedShort", Short.class.getName()); //$NON-NLS-1$
   }

   public static String getJavaTypeForXSDType(String typeName)
   {
      return (String) mapping.get(typeName);
   }   
}