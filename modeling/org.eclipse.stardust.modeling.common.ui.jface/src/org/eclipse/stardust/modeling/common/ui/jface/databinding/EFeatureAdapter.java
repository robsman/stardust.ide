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
package org.eclipse.stardust.modeling.common.ui.jface.databinding;

import ag.carnot.base.StringUtils;

public class EFeatureAdapter
{
   public static final EFeatureAdapter INSTANCE = new EFeatureAdapter();

   public Object fromModel(EObjectAdapter binding, Object value)
   {
      Object result = value;

      if ((null != binding.getEFeature())
            && binding.getEFeature().getEType().isInstance("")) //$NON-NLS-1$
      {
         if (null == value)
         {
            result = ""; //$NON-NLS-1$
         }
      }

      return result;
   }

   public Object toModel(EObjectAdapter binding, Object value)
   {
      Object result = value;

      if ((null != binding.getEFeature())
            && binding.getEFeature().getEType().isInstance("")) //$NON-NLS-1$
      {
         if (StringUtils.isEmpty((String) value))
         {
            result = null;
         }
      }

      return result;
   }
}
