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
package org.eclipse.stardust.modeling.core.utils;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EObjectAdapter;


/**
 * @author fherinean
 * @version $Revision$
 */
public class EAttributeAdapter extends EObjectAdapter
{
   private String att;
   private String type;

   public EAttributeAdapter(IExtensibleElement eObject, String att)
   {
      this(eObject, null, att);
   }

   public EAttributeAdapter(IExtensibleElement eObject, String type, String att)
   {
      super(eObject, null);
      this.type = type;
      this.att = att;
   }

   public Object getValue()
   {
      return AttributeUtil.getAttributeValue((IExtensibleElement) getModel(), att);
   }

   public void updateModel(Object value)
   {
      AttributeUtil.setAttribute((IExtensibleElement) getModel(), att, type,
         value == null ? null : value.toString());
   }
}
