/*******************************************************************************
 * Copyright (c) 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Florin.Herinean (SunGard CSA LLC) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.utils;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XpdlUtil;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EFeatureAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EObjectAdapter;

public final class XpdlFeatureAdapter extends EFeatureAdapter
{
   public static final EFeatureAdapter INSTANCE = new XpdlFeatureAdapter();

   private static final XpdlPackage PKG = XpdlPackage.eINSTANCE;

   public Object fromModel(EObjectAdapter binding, Object value)
   {
      EClassifier eType = (null != binding.getEFeature()) ? binding.getEFeature()
            .getEType() : null;

      if (PKG.getExpressionType().equals(eType))
      {
         return value instanceof ExpressionType
               ? XpdlUtil.getText(((ExpressionType) value).getMixed(), false)
               : ""; //$NON-NLS-1$
      }

      return super.fromModel(binding, value);
   }

   public Object toModel(EObjectAdapter binding, Object value)
   {
      EClassifier eType = (null != binding.getEFeature()) ? binding.getEFeature()
            .getEType() : null;

      if (PKG.getExpressionType().equals(eType))
      {
         String sVal = (value instanceof String) ? (String) value : String.valueOf(value);

         if (!StringUtils.isEmpty(sVal))
         {
            ExpressionType expression = (ExpressionType) binding.getEModel().eGet(binding.getEFeature());
            if (expression == null)
            {
               expression = XpdlFactory.eINSTANCE.createExpressionType();
            }
            XpdlUtil.setText(expression.getMixed(), sVal, false);
            return expression;
         }
         else
         {
            return null;
         }
      }

      return super.toModel(binding, value);
   }

   private XpdlFeatureAdapter() {}
}
