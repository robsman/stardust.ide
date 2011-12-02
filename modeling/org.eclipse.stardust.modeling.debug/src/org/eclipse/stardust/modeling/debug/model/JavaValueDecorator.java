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
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaType;
import org.eclipse.jdt.debug.core.IJavaValue;
import org.eclipse.jdt.debug.core.IJavaVariable;
import org.eclipse.stardust.modeling.debug.Constants;


public class JavaValueDecorator implements IJavaValue
{
   private IJavaValue value;
   private IJavaVariable hasChanged;
   
   public JavaValueDecorator(IJavaValue value, IJavaVariable hasChanged)
   {
      this.value = value;
      this.hasChanged = hasChanged;
   }
   
   public Object getAdapter(Class adapter)
   {
      return value.getAdapter(adapter);
   }
   
   public IDebugTarget getDebugTarget()
   {
      return value.getDebugTarget();
   }
   
   public String getGenericSignature() throws DebugException
   {
      return value.getGenericSignature();
   }
   
   public IJavaType getJavaType() throws DebugException
   {
      return value.getJavaType();
   }
   
   public ILaunch getLaunch()
   {
      return value.getLaunch();
   }
   
   public String getModelIdentifier()
   {
      return Constants.ID_CWM_DEBUG_MODEL;
   }
   
   public String getReferenceTypeName() throws DebugException
   {
      return value.getReferenceTypeName();
   }
   
   public String getSignature() throws DebugException
   {
      return value.getSignature();
   }
   
   public String getValueString() throws DebugException
   {
      return value.getValueString();
   }
   
   public IVariable[] getVariables() throws DebugException
   {
      IVariable[] variables = value.getVariables();
      JavaVariableDecorator[] decorators = new JavaVariableDecorator[variables.length];
      for (int idx = 0; idx < decorators.length; ++idx)
      {
         decorators[idx] = new JavaVariableDecorator((IJavaVariable) variables[idx],
               hasChanged);
      }
      
      return value.getVariables();
   }
   
   public boolean hasVariables() throws DebugException
   {
      return value.hasVariables();
   }
   
   public boolean isAllocated() throws DebugException
   {
      return value.isAllocated();
   }

   public boolean isNull()
   {
      return value == null;
   }
}
