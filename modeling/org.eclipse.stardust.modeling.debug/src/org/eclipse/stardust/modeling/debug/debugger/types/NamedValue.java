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
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.common.error.InternalException;

public class NamedValue extends AbstractJavaTypeValue
{
   // prefixing variable names with nv as base class has a member with name value, too,
   // thus giving errors when extracting via DebugVariableUtils
   private String nvName;
   private String nvValue;
   
   public NamedValue(IVariable variable)
   {
      super(variable);
      
      try
      {
         IVariable[] subVariables = getAssociatedVariable().getValue().getVariables();
         
         this.nvName = DebugVariableUtils.extractAsString("nvName", subVariables); //$NON-NLS-1$
         this.nvValue = DebugVariableUtils.extractAsString("nvValue", subVariables); //$NON-NLS-1$
      }
      catch (DebugException e)
      {
         throw new InternalException(e);
      }
   }

   public NamedValue(String name, String value)
   {
      super(null);
      
      this.nvName = name;
      this.nvValue = value;
   }

   /**
    * @return Returns the value.
    */
   public String getValue()
   {
      return nvValue;
   }

   /**
    * @return Returns the name.
    */
   public String getName()
   {
      return nvName;
   }
}