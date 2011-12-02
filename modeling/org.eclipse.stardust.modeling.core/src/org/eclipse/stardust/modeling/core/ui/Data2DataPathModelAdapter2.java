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
package org.eclipse.stardust.modeling.core.ui;

import org.eclipse.stardust.modeling.common.ui.jface.databinding.AbstractModelAdapter;

/**
 * @author fherinean
 * @version $Revision$
 */
public class Data2DataPathModelAdapter2 extends AbstractModelAdapter
{
   private Object model;
   private Object value;

   public Data2DataPathModelAdapter2(Object model, Object value)
   {
      this.model = model;
      this.value = value;
   }

   public Object getModel()
   {
      return model;
   }

   public Object getValue()
   {
      return value;
   }

   public void updateModel(Object value)
   {
      // nothing, it's an unidirectional adapter
   }
}
