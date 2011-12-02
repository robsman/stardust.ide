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
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaValue;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Debug_Messages;

import ag.carnot.base.Assert;
import ag.carnot.error.InternalException;

/**
 * @author sborn
 * @version $Revision$
 */
public class JavaTypeValueFactory
{
   public static <T> T createInstance(IVariable variable)
   {
      try
      {
         IJavaValue value = (IJavaValue) variable.getValue();
         if (null != value.getJavaType())
         {
            String varTypeName = variable.getReferenceTypeName();

            if (ActivityInstanceDigest.class.getName().equals(varTypeName))
            {
               return (T) new ActivityInstanceDigest(variable);
            }
            else if (ProcessInstanceDigest.class.getName().equals(varTypeName))
            {
               return (T) new ProcessInstanceDigest(variable);
            }
            else if (TransitionTokenDigest.class.getName().equals(varTypeName))
            {
               return (T) new TransitionTokenDigest(variable);
            }
            else if (ApplicationDigest.class.getName().equals(varTypeName))
            {
               return (T) new ApplicationDigest(variable);
            }
            else if (DataValueDigest.class.getName().equals(varTypeName))
            {
               return (T) new DataValueDigest(variable);
            }
            else if (DataMappingDigest.class.getName().equals(varTypeName))
            {
               return (T) new DataMappingDigest(variable);
            }
            else if (NamedValue.class.getName().equals(varTypeName))
            {
               return (T) new NamedValue(variable);
            }
            else if (DataField.class.getName().equals(varTypeName))
            {
               return (T) new DataField(variable);
            }
            else if (String.class.getName().equals(varTypeName))
            {
               return (T) value.getValueString();
            }
            else if (Boolean.class.getName().equals(varTypeName))
            {
               IVariable[] subVars = value.getVariables();
               return createInstance("value", subVars); //$NON-NLS-1$
            }
            else if (boolean.class.equals(varTypeName))
            {
               return (T) Boolean.valueOf(value.getValueString());
            }
         }
         return null;
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }      
   }
   
   public static <T> T createInstance(String name, IVariable[] variables)
   {
      try
      {
         for (int idx = 0; idx < variables.length; ++idx)
         {
            if (name.equals(variables[idx].getName()))
            {
               return createInstance(variables[idx]);
            }
         }
         return null;
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
   
   public static Object[] createArrayInstance(IVariable variable)
   {
      try
      {
         Object[] result = null;
         IJavaValue value = (IJavaValue) variable.getValue();

         if (null != value.getJavaType())
         {
            String arrayTypeName = variable.getReferenceTypeName();

            Assert.condition(arrayTypeName.endsWith("[]"), MessageFormat.format( //$NON-NLS-1$
                  Debug_Messages.MSG_COND_isNoArrayType, arrayTypeName));

            IVariable[] arrayVariables = value.getVariables();
            int arraySize = arrayVariables.length;
            result = new Object[arraySize];

            for (int idx = 0; idx < arraySize; ++idx)
            {
               result[idx] = createInstance(arrayVariables[idx]);
            }
         }

         return result;
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }

   public static Object[] createArrayInstance(String name, IVariable[] variables)
   {
      try
      {
         Object[] result = null;

         for (int idx = 0; idx < variables.length; ++idx)
         {
            if (name.equals(variables[idx].getName()))
            {
               result = createArrayInstance(variables[idx]);
               break;
            }
         }

         return result;
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
}
