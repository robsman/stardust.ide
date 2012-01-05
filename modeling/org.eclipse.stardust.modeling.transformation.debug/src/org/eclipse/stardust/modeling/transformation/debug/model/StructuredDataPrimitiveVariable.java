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
package org.eclipse.stardust.modeling.transformation.debug.model;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IWatchExpressionResult;
import org.eclipse.stardust.modeling.transformation.debug.WatchExpressionDelegate;

import ag.carnot.bpm.rt.data.structured.TypedXPath;
import ag.carnot.bpm.rt.data.structured.Utils;

import com.infinity.bpm.rt.integration.javascript.StructuredDataMapAccessor;

public class StructuredDataPrimitiveVariable extends AbstractVariable
{
   final private StructuredDataMapAccessor accessor;
   final private TypedXPath typedXPath;
   final private String name;
   final private JsStackFrame stackFrame;
   private StructuredDataPrimitiveValue value;

   public StructuredDataPrimitiveVariable(JsStackFrame stackFrame, AbstractVariable parent, String name,
         StructuredDataMapAccessor accessor)
   {
      this(stackFrame, parent, name, accessor, accessor.getXPath());
   }

   public StructuredDataPrimitiveVariable(JsStackFrame stackFrame, AbstractVariable parent, String name,
         StructuredDataMapAccessor accessor, TypedXPath typedXPath)
   {
      super(stackFrame, parent);
      this.accessor = accessor;
      this.typedXPath = typedXPath;
      this.name = name;
      this.stackFrame = stackFrame;
      this.value = new StructuredDataPrimitiveValue(this);
   }
   
   public JsStackFrame getStackFrame()
   {
      return stackFrame;
   }
   
   public StructuredDataMapAccessor getAccessor()
   {
      return accessor;
   }
   
   public TypedXPath getTypedXPath()
   {
      return typedXPath;
   }

   public String getName() throws DebugException
   {
      return name;
   }

   public String getReferenceTypeName() throws DebugException
   {
      return getTypedXPath().getXsdTypeName();
   }

   public IValue getValue() throws DebugException
   {
      return value;
   }

   public void setValue(String expression) throws DebugException
   {
      StringBuffer buffer = new StringBuffer();
      AbstractVariable parent = getParent();
      if (parent instanceof StructuredDataMapVariable)
      {
         // name may contain characters which are not allowed in JS identifiers, e.g -.
         // must be access "name indexed".
         buffer.append("[\"").append(name).append("\"]");
      }
      else
      {
         buffer.append(name);
      }
      
      while (null != parent)
      {
         if ( !(parent instanceof StructuredDataMapVariable) && !(parent instanceof StructuredDataListVariable))
         {
            buffer.insert(0, ".");
         }
         buffer.insert(0, parent.getQualifiedName());
         parent = parent.getParent();
      }
      
      buffer.append(" = ").append(expression);
      
      //buffer = new StringBuffer(expression);

      if (verifyValue(buffer.toString()))
      {
         IWatchExpressionResult result = WatchExpressionDelegate.evaluateExpression(
               buffer.toString(), getFrame());
         if ( !result.hasErrors())
         {
            final IValue rawValue = result.getValue();
            if (rawValue instanceof JsValue)
            {
               JsValue jsValue = (JsValue) rawValue;
               if (isCompatible(jsValue))
               {
                  hasChanged = true;
                  fireChangeEvent(DebugEvent.CONTENT);
               }
            }
            else if (rawValue instanceof StructuredDataPrimitiveValue)
            {
               value = (StructuredDataPrimitiveValue) rawValue;
               hasChanged = true;
               fireChangeEvent(DebugEvent.CONTENT);
            }
         }
      }
   }
   
   private boolean isCompatible(JsValue jsValue)
   {
      // TODO: check if the types of old/new value are matching/compatible (e.g. string->string, ...)
      //value = new StructuredDataPrimitiveValue(this, jsValue.getValueString());
      Class currentType = Utils.getJavaTypeForTypedXPath(typedXPath);
      Object rawValue = jsValue.getRawValue();
      
      if(currentType.isAssignableFrom(rawValue.getClass()))
      {
         return true;
      }
      else
      {
         return false;
      }
      
      
      /*if (rawValue instanceof String)
      {
         return true;
      }
      else if (rawValue instanceof Boolean)
      {
         return true;
      }
      else if (rawValue instanceof Character)
      {
         return true;
      }
      else if (rawValue instanceof Byte)
      {
         return true;
      }
      else if (rawValue instanceof Short)
      {
         return true;
      }
      else if (rawValue instanceof Integer)
      {
         return true;
      }
      else if (rawValue instanceof Long)
      {
         return true;
      }
      else if (rawValue instanceof Float)
      {
         return true;
      }
      else if (rawValue instanceof Double)
      {
         return true;
      }
      
      return false;*/
   }

}
