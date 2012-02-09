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
import org.eclipse.debug.core.model.IVariable;

public class NullElementVariable extends JsDebugElement implements IVariable
{
   final private String name;
   final private JsStackFrame stackFrame;

   public NullElementVariable(JsStackFrame stackFrame, String name)
   {
      super((JsDebugTarget) stackFrame.getDebugTarget());
      this.name = name;
      this.stackFrame = stackFrame;
   }
   
   public JsStackFrame getStackFrame()
   {
      return stackFrame;
   }
   
   public String getName() throws DebugException
   {
      return name;
   }

   public String getReferenceTypeName() throws DebugException
   {
      return "<null>"; //$NON-NLS-1$
   }

   public IValue getValue() throws DebugException
   {
      return new NullElementValue(this);
   }

   public boolean hasValueChanged() throws DebugException
   {
      return false;
   }

   public void setValue(String expression) throws DebugException
   {
   }

   public void setValue(IValue value) throws DebugException
   {
   }

   public boolean supportsValueModification()
   {
      return false;
   }

   public boolean verifyValue(String expression) throws DebugException
   {
      return false;
   }

   public boolean verifyValue(IValue value) throws DebugException
   {
      return false;
   }

}
