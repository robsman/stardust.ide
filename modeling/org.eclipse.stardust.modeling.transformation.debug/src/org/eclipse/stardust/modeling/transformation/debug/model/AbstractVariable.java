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

public abstract class AbstractVariable extends JsDebugElement implements IVariable
{
   private final AbstractVariable parent;
   private final JsStackFrame frame;
   
   protected boolean hasChanged = false;

   public AbstractVariable(JsStackFrame frame, AbstractVariable parent)
   {
      super((JsDebugTarget) frame.getDebugTarget());
      this.parent = parent;
      this.frame = frame;
   }
   
   public AbstractVariable getParent()
   {
      return parent;
   }
   
   public String getQualifiedName() throws DebugException
   {
      return getName();
   }
   
   public JsStackFrame getFrame()
   {
      return frame;
   }
   
   public boolean supportsValueModification()
   {
      /* TODO: Not mandatory by FSpec. If it will be mandatory then it has to be polished.
      try
      {
         return frame.getThread().getTopStackFrame() == frame;
      }
      catch (DebugException e)
      {
         e.printStackTrace();
      }
      */

      return false;
   }
   
   public void setValue(IValue value) throws DebugException
   {
      if (verifyValue(value))
      {
         setValue(value.getValueString());
      }
   }

   public boolean verifyValue(String expression) throws DebugException
   {
      // TODO: The expression has to be verified.
      return true;
   }

   public boolean verifyValue(IValue value) throws DebugException
   {
      return verifyValue(value.getValueString());
   }
   
   public boolean hasValueChanged() throws DebugException
   {
      return hasChanged;
   }
}
