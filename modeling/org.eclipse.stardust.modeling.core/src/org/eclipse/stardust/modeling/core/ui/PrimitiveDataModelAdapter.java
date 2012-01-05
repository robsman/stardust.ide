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

import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.AbstractModelAdapter;

public class PrimitiveDataModelAdapter extends AbstractModelAdapter
{

   private final Object model;

   private final Object value;

   private final IExtensibleElement element;

   public PrimitiveDataModelAdapter(Object model, Object value, IExtensibleElement element)
   {
      this.model = model;
      this.value = value;
      this.element = element;
   }

   public Object getModel()
   {
      return model;
   }

   public Object getValue()
   {
      return value;
   }

   public void updateModel(Object type)
   {
      AttributeUtil.setAttribute(element, CarnotConstants.TYPE_ATT, Type.class.getName(),
            ((Type) type).getId());
      AttributeUtil.setAttribute(element, CarnotConstants.DEFAULT_VALUE_ATT, null);
   }

}
