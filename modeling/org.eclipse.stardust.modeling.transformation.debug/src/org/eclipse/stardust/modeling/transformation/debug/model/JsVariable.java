/*******************************************************************************
 * Copyright (c) 2007, 2011 Actuate Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Actuate Corporation  - initial API and implementation
 *    SunGard CSA LLC - Adapted to Eclipse Debug Framework
 *******************************************************************************/
package org.eclipse.stardust.modeling.transformation.debug.model;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.core.model.IWatchExpressionResult;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.transformation.debug.WatchExpressionDelegate;

public class JsVariable extends AbstractVariable
{
   public static final IVariable[] NO_VARS = new IVariable[0];

   private JsValue value;
   private String name;
   private String typeName;

   public JsVariable(JsStackFrame frame, AbstractVariable parent, String name, Object dataField)
   {
      this(frame, parent, name, dataField, false);
   }

   public JsVariable(JsStackFrame frame, AbstractVariable parent, String name, Object dataField, boolean isPrimitive)
   {
      super(frame, parent);
      this.name = name;
      this.value = new JsValue(this, dataField, isPrimitive);
   }

   public IValue getValue() throws DebugException
   {
      return value;
   }

   public String getName() throws DebugException
   {
      return name;
   }

   public String getReferenceTypeName() throws DebugException
   {
      return StringUtils.isEmpty(typeName) ? "<no type available>" : typeName;
   }

   public void setReferenceTypeName(String typeName) throws DebugException
   {
      this.typeName = typeName;
   }

   public void setValue(String expression) throws DebugException
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append(name);
      AbstractVariable parent = getParent();
      while (null != parent)
      {
         // prepend parent-name followed by "."
         buffer.insert(0, ".").insert(0, parent.getQualifiedName());
         parent = parent.getParent();
      }

      buffer.append(" = ").append(expression);

      if (verifyValue(buffer.toString()))
      {
         IWatchExpressionResult result = WatchExpressionDelegate.evaluateExpression(
               buffer.toString(), getFrame());
         if ( !result.hasErrors())
         {
            if (result.getValue() instanceof JsValue)
            {
               value = (JsValue) result.getValue();
               hasChanged = true;
               fireChangeEvent(DebugEvent.CONTENT);
            }
         }
      }
   }
}
