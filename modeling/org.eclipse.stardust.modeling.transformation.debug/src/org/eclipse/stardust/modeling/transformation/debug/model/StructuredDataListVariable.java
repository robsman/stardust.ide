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

import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.stardust.engine.core.javascript.StructuredDataListAccessor;
import org.eclipse.stardust.engine.core.struct.TypedXPath;

public class StructuredDataListVariable extends AbstractVariable
{
   final private StructuredDataListAccessor accessor;
   final private TypedXPath typedXPath;
   final private String name;
   final private int size;
   final private JsStackFrame stackFrame;

   public StructuredDataListVariable(JsStackFrame stackFrame, AbstractVariable parent, String name,
         StructuredDataListAccessor accessor)
   {
      this(stackFrame, parent, name, accessor, accessor == null ? null : accessor.getXPath());
   }

   public StructuredDataListVariable(JsStackFrame stackFrame, AbstractVariable parent, String name,
         StructuredDataListAccessor accessor, TypedXPath typedXPath)
   {
      super(stackFrame, parent);
      this.accessor = accessor;
      this.typedXPath = typedXPath;
      this.name = name;

      if (null != accessor)
      {
         List list = (List) accessor.getValue();
         this.size = list.size();
      }
      else
      {
         this.size = 0;
      }

      this.stackFrame = stackFrame;
   }
   
   public JsStackFrame getStackFrame()
   {
      return stackFrame;
   }
   
   public StructuredDataListAccessor getAccessor()
   {
      return accessor;
   }
   
   public TypedXPath getTypedXPath()
   {
      return typedXPath;
   }
   
   public int getSize()
   {
      return size;
   }

   public String getName() throws DebugException
   {
      return name;
   }

   public String getReferenceTypeName() throws DebugException
   {
      return accessor == null ? "null" : getTypedXPath().getXsdTypeName() + "[" + size
            + "]";
   }

   public IValue getValue() throws DebugException
   {
      return new StructuredDataListValue(this);
   }

   public boolean hasValueChanged() throws DebugException
   {
      return false;
   }

   public void setValue(String expression) throws DebugException
   {
   }

   public boolean supportsValueModification()
   {
      return false;
   }
}
