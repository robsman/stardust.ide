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
package org.eclipse.stardust.modeling.javascript;

import org.eclipse.wst.jsdt.core.infer.InferredAttribute;
import org.eclipse.wst.jsdt.core.infer.InferredType;

public class JSInferredAttribute extends InferredAttribute
{
   private boolean array;

   public JSInferredAttribute(char[] name, InferredType inType, int start, int end)
   {
      super(name, inType, start, end);     
   }

   public boolean isArray()
   {
      return array;
   }

   public void setArray(boolean array)
   {
      this.array = array;
   }

}
