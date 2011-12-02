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

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;

import com.infinity.bpm.rt.integration.javascript.StructuredDataMapAccessor;

import ag.carnot.bpm.rt.data.structured.TypedXPath;

public class StructuredDataMapVariable extends AbstractVariable
{
   final private StructuredDataMapAccessor accessor;
   final private TypedXPath typedXPath;
   final private String name;
   final private JsStackFrame stackFrame;

   public StructuredDataMapVariable(JsStackFrame stackFrame, AbstractVariable parent, String name,
         StructuredDataMapAccessor accessor)
   {
      this(stackFrame, parent, name, accessor, accessor.getXPath());
   }

   public StructuredDataMapVariable(JsStackFrame stackFrame, AbstractVariable parent, String name,
         StructuredDataMapAccessor accessor, TypedXPath typedXPath)
   {
      super(stackFrame, parent);
      this.accessor = accessor;
      this.typedXPath = typedXPath;
      this.name = name;
      this.stackFrame = stackFrame;
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
      return new StructuredDataMapValue(this);
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
