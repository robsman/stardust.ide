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
package org.eclipse.stardust.modeling.debug.debugger.types;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.modeling.debug.Constants;

public abstract class AbstractJavaTypeValue implements IJavaTypeValue
{
   private String refTypeName;
   private IVariable associatedVariable;
   private IVariable writeBackVariable;
   private IValue value;
   
   protected AbstractJavaTypeValue(IVariable variable)
   {
      try
      {
         this.associatedVariable = variable;
         this.writeBackVariable = variable;
         
         value = null == variable ? null : variable.getValue();
         this.refTypeName = null != value ? value.getReferenceTypeName()
               : Constants.EMPTY;
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }

   public String getVariableName()
   {
      try
      {
         return associatedVariable.getName();
      }
      catch (DebugException e)
      {
         throw new InternalException(e);
      }
   }
   
   public IVariable getAssociatedVariable()
   {
      return associatedVariable;
   }

   public String getRefTypeName()
   {
      return refTypeName;
   }

   public IVariable getWritebackVariable()
   {
      return writeBackVariable;
   }

   public void setWritebackVariable(IVariable variable)
   {
      this.writeBackVariable = variable; 
   }
}
