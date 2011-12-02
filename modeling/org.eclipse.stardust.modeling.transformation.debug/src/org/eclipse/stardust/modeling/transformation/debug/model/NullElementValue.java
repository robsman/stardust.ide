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

public class NullElementValue extends JsDebugElement implements IValue
{
   final private NullElementVariable variable;
   
   public NullElementValue(NullElementVariable variable)
   {
      super((JsDebugTarget) variable.getDebugTarget());
      this.variable = variable;
   }

   public String getReferenceTypeName() throws DebugException
   {
      return "<null>";
   }

   public String getValueString() throws DebugException
   {
      return "<null>";
   }

   public IVariable[] getVariables() throws DebugException
   {
      return new IVariable[0];
   }

   public boolean hasVariables() throws DebugException
   {
      return getVariables().length > 0;
   }

   public boolean isAllocated() throws DebugException
   {
      return true;
   }

}
