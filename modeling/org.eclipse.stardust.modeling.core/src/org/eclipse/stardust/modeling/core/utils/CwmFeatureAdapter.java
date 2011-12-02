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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.TextType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EFeatureAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EObjectAdapter;

import ag.carnot.base.StringUtils;

public class CwmFeatureAdapter extends EFeatureAdapter
{
   public static final CwmFeatureAdapter INSTANCE = new CwmFeatureAdapter();

   private static final CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;

   public Object fromModel(EObjectAdapter binding, Object value)
   {
      Object result;

      EClassifier eType = (null != binding.getEFeature()) ? binding.getEFeature()
            .getEType() : null;

      if (PKG.getDescriptionType().equals(eType))
      {
         if (value instanceof DescriptionType)
         {
            result = ModelUtils.getCDataString(((DescriptionType) value).getMixed());
         }
         else
         {
            result = ""; //$NON-NLS-1$
         }
      }
      else if (PKG.getTextType().equals(eType))
      {
         if (value instanceof TextType)
         {
            result = ModelUtils.getCDataString(((TextType) value).getMixed());
         }
         else
         {
            result = ""; //$NON-NLS-1$
         }
      }
      else
      {
         result = super.fromModel(binding, value);
      }

      return result;
   }

   public Object toModel(EObjectAdapter binding, Object value)
   {
      Object result;

      EClassifier eType = (null != binding.getEFeature()) ? binding.getEFeature()
            .getEType() : null;

      if (PKG.getDescriptionType().equals(eType))
      {
         String sVal = (value instanceof String) ? (String) value : String.valueOf(value);

         if ( !StringUtils.isEmpty(sVal))
         {
            DescriptionType descr = PKG.getCarnotWorkflowModelFactory()
                  .createDescriptionType();
            ModelUtils.setCDataString(descr.getMixed(), sVal);
            result = descr;
         }
         else
         {
            result = null;
         }
      }
      else if (PKG.getTextType().equals(eType))
      {
         String sVal = (value instanceof String) ? (String) value : String.valueOf(value);

         if ( !StringUtils.isEmpty(sVal))
         {
            TextType txt = PKG.getCarnotWorkflowModelFactory().createTextType();
            ModelUtils.setCDataString(txt.getMixed(), sVal);
            result = txt;
         }
         else
         {
            result = null;
         }
      }
      else
      {
         result = super.toModel(binding, value);
      }

      return result;
   }
}
