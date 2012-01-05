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

import ag.carnot.bpm.rt.data.structured.StructuredDataXPathUtils;

import com.infinity.bpm.rt.integration.javascript.StructuredDataMapAccessor;

public class StructuredDataPrimitiveValue extends JsDebugElement implements IValue
{
   final private StructuredDataPrimitiveVariable variable;
   final private String valueString;
   
   public StructuredDataPrimitiveValue(StructuredDataPrimitiveVariable variable)
   {
      this(variable, null);
   }

   public StructuredDataPrimitiveValue(StructuredDataPrimitiveVariable variable, String valueString)
   {
      super((JsDebugTarget) variable.getDebugTarget());
      this.variable = variable;
      this.valueString = valueString;
   }

   public String getReferenceTypeName() throws DebugException
   {
      return variable.getReferenceTypeName();
   }

   public String getValueString() throws DebugException
   {
      if (null == valueString)
      {
         final StructuredDataMapAccessor accessor = variable.getAccessor();
         String localXPath = StructuredDataXPathUtils.getLastXPathPart(variable
               .getTypedXPath().getXPath());
         Object object = accessor.get(localXPath, null);
         return null == object ? getReferenceTypeName() : object.toString();
      }
      else
      {
         return valueString;
      }
   }

   public IVariable[] getVariables() throws DebugException
   {
      return new IVariable[0];
   }

   public boolean hasVariables() throws DebugException
   {
      return false;
   }

   public boolean isAllocated() throws DebugException
   {
      return true;
   }

}
