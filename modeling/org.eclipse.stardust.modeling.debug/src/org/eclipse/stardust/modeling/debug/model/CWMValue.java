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
package org.eclipse.stardust.modeling.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.modeling.debug.debugger.types.DataField;


public class CWMValue extends CWMDebugElement implements IValue
{
   private CWMVariable variable;
   private DataField dataField;
   
   public CWMValue(CWMVariable variable, DataField dataField)
   {
      super((CWMDebugTarget) variable.getDebugTarget());
      this.variable = variable;
      this.dataField = dataField;
   }

   public String getReferenceTypeName() throws DebugException
   {
      return variable.getReferenceTypeName();
   }

   public String getValueString() throws DebugException
   {
      return dataField.getValue();
   }

   public boolean isAllocated() throws DebugException
   {
      return true;
   }

   public IVariable[] getVariables() throws DebugException
   {
      return dataField.getWritebackVariable().getValue().getVariables();
   }

   public boolean hasVariables() throws DebugException
   {
      return dataField.getWritebackVariable().getValue().hasVariables();
   }

}
