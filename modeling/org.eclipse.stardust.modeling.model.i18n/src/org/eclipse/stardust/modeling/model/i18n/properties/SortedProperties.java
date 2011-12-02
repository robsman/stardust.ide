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
package org.eclipse.stardust.modeling.model.i18n.properties;

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

public class SortedProperties extends Properties
{
   private static final long serialVersionUID = 1L;
   
   private List<String> prefixes;

   public SortedProperties(List<String> prefixes)
   {
      this.prefixes = prefixes;
   }

   public synchronized Enumeration<Object> keys()
   {
      TreeSet<Object> set = new TreeSet<Object>(new Comparator<Object>()
      {
         public int compare(Object o1, Object o2)
         {
            int result = 0;
            
            String fullKey1 = o1.toString();
            String fullKey2 = o2.toString();
            
            // test if at least one of the keys is not qualified
            int i1 = fullKey1.lastIndexOf('.');
            int i2 = fullKey2.lastIndexOf('.');
            
            if (i1 < 0 && i2 >= 0)
            {
               result = -1;
            }
            else if (i2 < 0 && i1 >= 0)
            {
               result = 1;
            }
            else if (i1 < 0 && i2 < 0)
            {
               result = fullKey1.compareTo(fullKey2);
            }
            else
            {
               String key1 = fullKey1.substring(i1 + 1);
               String key2 = fullKey2.substring(i2 + 1);
               
               String qualifier1 = fullKey1.substring(0, i1);
               String qualifier2 = fullKey2.substring(0, i2);

               i1 = prefixes.indexOf(qualifier1);
               i2 = prefixes.indexOf(qualifier2);

               if (i1 < 0 && i2 >= 0)
               {
                  result = -1;
               }
               else if (i2 < 0 && i1 >= 0)
               {
                  result = 1;
               }
               else if (i1 < 0 && i2 < 0)
               {
                  result = qualifier1.compareTo(qualifier2);
               }
               else
               {
                  result = i1 - i2;
               }
               if (result == 0)
               {
                  result = key1.compareTo(key2);
               }
            }
            return result;
         }
      });
      for (Enumeration<Object> en = super.keys(); en.hasMoreElements(); set.add(en.nextElement()));
      return Collections.enumeration(set);
   }
}
