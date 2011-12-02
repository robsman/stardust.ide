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
import org.eclipse.swt.widgets.Text;

/**
 * Modification listener for primitive data types 
 */
public class PrimitiveDataModificationListener implements ModifyListener
{
   private final Map outValueRepository;
   private final IVariable variable;
   
   public PrimitiveDataModificationListener(IVariable variable, Map outValueRepository)
   {
      this.outValueRepository = outValueRepository;
      this.variable = variable;
   }
   
   public void modifyText(ModifyEvent e)
   {
      outValueRepository.put(variable, ((Text) e.widget).getText());
   }
}