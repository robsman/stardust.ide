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
package org.eclipse.stardust.modeling.debug.debugger.types;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;

import ag.carnot.error.PublicException;
import ag.carnot.reflect.Reflect;

public class PrimitivesFactory
{
   public static Object createInstance(String typeName, String stringifiedValue)
   {
      Object result = null;
      
      Class type = Reflect.getClassFromClassName(typeName);
      if ( !isSupportedType(type))
      {
         throw new PublicException(MessageFormat.format(Internal_Debugger_Messages.getString("EXP_TypeIsNotSupported"), //$NON-NLS-1$
               typeName));
      }

      if (Boolean.class.equals(type))
      {
         result = Boolean.valueOf(stringifiedValue);
      }
      else if (Byte.class.equals(type))
      {
         result = new Byte(stringifiedValue);
      }
      else if (Short.class.equals(type))
      {
         result = new Short(stringifiedValue);
      }
      else if (Integer.class.equals(type))
      {
         result = new Integer(stringifiedValue);
      }
      else if (Long.class.equals(type))
      {
         result = new Long(stringifiedValue);
      }
      else if (Float.class.equals(type))
      {
         result = new Float(stringifiedValue);
      }
      else if (Double.class.equals(type))
      {
         result = new Double(stringifiedValue);
      }
      else if (Character.class.equals(type))
      {
         result = new Character(stringifiedValue.charAt(0));
      }
      else if (String.class.equals(type))
      {
         result = stringifiedValue;
      }
      /*else if (Calendar.class.equals(type))
      {
      }
      else if (Date.class.equals(type))
      {
      }
      else if (Money.class.equals(type))
      {
         result = new Money(stringifiedValue);
      }*/

      return result;
   }
   
   public static boolean isSupportedJavaType(Class type)
   {
      if (Boolean.class.equals(type) || Byte.class.equals(type)
            || Short.class.equals(type) || Integer.class.equals(type)
            || Long.class.equals(type) || Float.class.equals(type)
            || Double.class.equals(type) || Character.class.equals(type)
            || String.class.equals(type))
      {
         return true;
      }

      return false;
   }
   
   public static boolean isSupportedCarnotType(Class type)
   {
      if (Calendar.class.isAssignableFrom(type)
            || Date.class.equals(type) /*|| Money.class.equals(type)*/)
      {
         return true;
      }

      return false;
   }
   
   public static boolean isSupportedType(Class type)
   {
      if (isSupportedJavaType(type) || isSupportedCarnotType(type) || isSupportedStructuredType(type))
      {
         return true;
      }

      return false;
   }
   
   private static boolean isSupportedStructuredType(Class type)
   {
      if (Map.class.isAssignableFrom(type) || List.class.isAssignableFrom(type))
      {
         return true;
      }
      return false;
   }

   private PrimitivesFactory()
   {
   }
}