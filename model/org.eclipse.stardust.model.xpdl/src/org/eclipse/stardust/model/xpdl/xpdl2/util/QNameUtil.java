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
package org.eclipse.stardust.model.xpdl.xpdl2.util;

public final class QNameUtil
{
   private QNameUtil() {};
   
   public static String toString(String namespaceURI, String localPart)
   {
      if (namespaceURI == null)
      {
         return localPart;
      }
      else
      {
         return "{" + namespaceURI + "}" + localPart;
      }
   }
   
   public static String parseNamespaceURI(String qNameAsString)
   {
      if (qNameAsString == null)
      {
         return null;
      }

      if (qNameAsString.length() == 0)
      {
         return null;
      }

      // local part only?
      if (qNameAsString.charAt(0) != '{')
      {
          return null;
      }

      // Namespace URI improperly specified?
      if (qNameAsString.startsWith("{}"))
      {
         return null;
      }

      // Namespace URI and local part specified
      int endOfNamespaceURI = qNameAsString.indexOf('}');
      if (endOfNamespaceURI == -1)
      {
          throw new IllegalArgumentException(
              "Cannot parse QName from \"" + qNameAsString + "\", missing closing \"}\"");
      }
      return qNameAsString.substring(1, endOfNamespaceURI);
   }

   public static String parseLocalName(String qNameAsString)
   {
      if (qNameAsString == null)
      {
         return null;
      }

      if (qNameAsString.length() == 0)
      {
         return qNameAsString;
      }

      // local part only?
      if (qNameAsString.charAt(0) != '{')
      {
          return qNameAsString;
      }

      // Namespace URI improperly specified?
      if (qNameAsString.startsWith("{}"))
      {
         return qNameAsString.substring(2);
      }

      // Namespace URI and local part specified
      int endOfNamespaceURI = qNameAsString.indexOf('}');
      if (endOfNamespaceURI == -1)
      {
          throw new IllegalArgumentException(
              "Cannot parse QName from \"" + qNameAsString + "\", missing closing \"}\"");
      }
      return qNameAsString.substring(endOfNamespaceURI + 1);
   }
}
