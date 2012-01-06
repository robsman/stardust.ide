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
import java.util.Iterator;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.engine.core.javascript.ListValuedXPathAdapter;
import org.eclipse.stardust.engine.core.javascript.StructuredDataListAccessor;
import org.eclipse.stardust.engine.core.javascript.StructuredDataMapAccessor;
import org.eclipse.stardust.engine.core.struct.StructuredDataXPathUtils;
import org.eclipse.stardust.engine.core.struct.TypedXPath;

public class StructuredDataMapValue extends JsDebugElement implements IValue
{
   final private StructuredDataMapVariable variable;
   
   public StructuredDataMapValue(StructuredDataMapVariable variable)
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
      final StructuredDataMapAccessor accessor = variable.getAccessor();
      String localXPath = StructuredDataXPathUtils.getLastXPathPart(variable
            .getTypedXPath().getXPath());
      Object object = accessor.get(localXPath, null);
      return null == object ? getReferenceTypeName() : object.toString();
   }

   public IVariable[] getVariables() throws DebugException
   {
      List variables = new ArrayList();

      final TypedXPath typedXPath = variable.getTypedXPath();
      final StructuredDataMapAccessor accessor = variable.getAccessor();
      for (Iterator iter = typedXPath.getChildXPaths().iterator(); iter.hasNext();)
      {
         TypedXPath child = (TypedXPath) iter.next();

         if (child instanceof ListValuedXPathAdapter)
         {
            continue;
         }

         String name = StructuredDataXPathUtils.getLastXPathPart(child.getXPath());
         final Object object = accessor.get(name, null);
         if (object instanceof StructuredDataListAccessor)
         {
            StructuredDataListAccessor listAccessor = (StructuredDataListAccessor) object;
            variables.add(new StructuredDataListVariable(variable.getStackFrame(),
                  variable, name, listAccessor, child));
         }
         else if (object instanceof StructuredDataMapAccessor)
         {
            StructuredDataMapAccessor mapAccessor = (StructuredDataMapAccessor) object;
            variables.add(new StructuredDataMapVariable(variable.getStackFrame(),
                  variable, name, mapAccessor, child));

         }
         else
         {
            variables.add(new StructuredDataPrimitiveVariable(variable.getStackFrame(),
                  variable, name, accessor, child));
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
