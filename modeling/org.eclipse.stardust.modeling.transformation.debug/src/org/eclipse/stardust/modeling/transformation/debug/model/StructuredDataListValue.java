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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

import com.infinity.bpm.rt.integration.javascript.StructuredDataListAccessor;
import com.infinity.bpm.rt.integration.javascript.StructuredDataMapAccessor;

public class StructuredDataListValue extends JsDebugElement implements IValue
{
   final private StructuredDataListVariable variable;
   
   public StructuredDataListValue(StructuredDataListVariable variable)
   {
      super((JsDebugTarget) variable.getDebugTarget());
      this.variable = variable;
   }

   public String getReferenceTypeName() throws DebugException
   {
      return variable.getReferenceTypeName();
   }

   public String getValueString() throws DebugException
   {
      return getReferenceTypeName();
   }

   public IVariable[] getVariables() throws DebugException
   {
      List variables = new ArrayList();

      final StructuredDataListAccessor accessor = variable.getAccessor();
      if (null != accessor)
      {
         Integer size = (Integer) accessor.get(StructuredDataListAccessor.NAME_LENGTH,
               null);
         for (int idx = 0; idx < size.intValue(); ++idx)
         {
            Object rawValue = accessor.get(idx, null);

            final String name = "[" + idx + "]";
            if (null == rawValue)
            {
               variables.add(new NullElementVariable(variable.getStackFrame(), name));
            }
            else if (rawValue instanceof StructuredDataMapAccessor)
            {
               variables.add(new StructuredDataMapVariable(variable.getStackFrame(), variable,
                     name, (StructuredDataMapAccessor) rawValue));
            }
            else
            {
               variables.add(new StructuredDataListVariable(variable.getStackFrame(), variable,
                     name, (StructuredDataListAccessor) rawValue));
            }
         }
      }

      return variables.isEmpty() ? new IVariable[0] : (IVariable[]) variables
            .toArray(new IVariable[variables.size()]);
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
