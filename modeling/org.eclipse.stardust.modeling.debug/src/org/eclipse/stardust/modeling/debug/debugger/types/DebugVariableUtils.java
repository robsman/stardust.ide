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

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaValue;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Debug_Messages;

import ag.carnot.error.InternalException;

public class DebugVariableUtils
{
   private static boolean isJavaNullValue(IValue value) throws DebugException
   {
      if (value instanceof IJavaValue)
      {
         IJavaValue javaValue = (IJavaValue) value;
         if (null == javaValue.getJavaType())
         {
            return true;
         }
      }
      
      return false;
   }
   
   public static String extractAsString(String name, IVariable[] variables)
   {
      try
      {
         IValue value = findValue(name, variables);
         
         if (null != value)
         {
            if (isJavaNullValue(value))
            {
               return null;
            }
            
            return value.getValueString();
         }
         
         return null;
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
   
   public static long extractAsLong(String name, IVariable[] variables)
   {
      try
      {
         IValue value = findValue(name, variables);
         
         if (null != value)
         {
            return Long.parseLong(value.getValueString());
         }
         
         throw new InternalException(MessageFormat.format(
               Debug_Messages.EXP_VariableDoesNotExist, name));
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
   
   public static double extractAsDouble(String name, IVariable[] variables)
   {
      try
      {
         IValue value = findValue(name, variables);
         
         if (null != value)
         {
            return Double.parseDouble(value.getValueString());
         }
         
         throw new InternalException(MessageFormat.format(
               Debug_Messages.EXP_VariableDoesNotExist, name));
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
   
   public static boolean extractAsBoolean(String name, IVariable[] variables)
   {
      try
      {
         IValue value = findValue(name, variables);
         
         if (null != value)
         {
            return Boolean.valueOf(value.getValueString()).booleanValue();
         }
         
         throw new InternalException(MessageFormat.format(
               Debug_Messages.EXP_VariableDoesNotExist, name));
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
   
   public static IVariable findVariable(String name, IVariable[] variables)
   {
      try
      {
         for (int idx = 0; idx < variables.length; ++idx)
         {
            if (variables[idx].getName().equals(name))
            {
               return variables[idx];
            }
         }
         
         throw new InternalException(MessageFormat.format(
               Debug_Messages.EXP_VariableDoesNotExist, new Object[] {name}));
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
   
   public static IValue findValue(String name, IVariable[] variables)
   {
      try
      {
         return findVariable(name, variables).getValue();
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
}
