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
package org.eclipse.stardust.modeling.debug.model.ui;

import java.util.Map;

import org.eclipse.debug.core.model.IVariable;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

/**
 * Modification listener for structured data 
 */
public class StructuredDataModificationListener implements ModifyListener
{

   private IVariable variable;
   private Map outValues;
   private StructuredValue rootValue;

   public StructuredDataModificationListener(IVariable variable, Map outValues,
         StructuredValue rootValue)
   {
      this.variable = variable;
      this.outValues = outValues;
      this.rootValue = rootValue;
   }

   public void modifyText(ModifyEvent e)
   {
      this.outValues.put(this.variable, this.rootValue);
   }

}
