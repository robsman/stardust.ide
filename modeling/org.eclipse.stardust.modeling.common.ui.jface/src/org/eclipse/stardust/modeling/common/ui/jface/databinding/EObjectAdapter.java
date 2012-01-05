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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.common.CompareHelper;

public class EObjectAdapter extends AdapterImpl implements IModelAdapter
{
   private final EObject model;

   private final EStructuralFeature feature;

   private final EFeatureAdapter modelValueAdapter;

   private IBindingMediator manager;

   public EObjectAdapter(EObject model, EStructuralFeature feature)
   {
      this(model, feature, EFeatureAdapter.INSTANCE);
   }

   public EObjectAdapter(EObject model, EStructuralFeature feature,
         EFeatureAdapter modelValueAdapter)
   {
      this.model = model;
      this.feature = feature;

      this.modelValueAdapter = modelValueAdapter;
   }

   public EObject getEModel()
   {
      return model;
   }

   public EStructuralFeature getEFeature()
   {
      return feature;
   }

   public void bind(IBindingMediator manager)
   {
      if (null != this.manager)
      {
         // TODO force unbind
      }

      this.manager = manager;
      if ( !this.model.eAdapters().contains(this))
      {
         this.model.eAdapters().add(this);
      }
   }

   public void unbind()
   {
      this.model.eAdapters().remove(this);
      this.manager = null;
   }

   public Object getModel()
   {
      return model;
   }

   public Object getValue()
   {
      Object result;

      if (null == feature)
      {
         result = model;
      }
      else
      {
         result = (null != model) ? model.eGet(feature) : null;
      }

      // convert to neutral format
      if (null != modelValueAdapter)
      {
         result = modelValueAdapter.fromModel(this, result);
      }

      return result;
   }

   public void updateModel(Object value)
   {
      if ((null != model) && (null != feature))
      {
         Object oldValue = getValue();

         if ( /* !model.eIsSet(feature) || */!CompareHelper.areEqual(oldValue, value))
         {
            Object newValue = (null != modelValueAdapter) ? modelValueAdapter.toModel(
                  this, value) : value;

            model.eSet(feature, newValue);
         }
      }
   }

   public void updateVisuals(Object value)
   {
      this.manager.updateWidget(this, value);
   }

   public void notifyChanged(Notification msg)
   {
      if (Notification.REMOVING_ADAPTER != msg.getEventType())
      {
         if ((null == feature) || feature.equals(msg.getFeature()))
         {
            updateVisuals(getValue());
         }
         else
         {
            super.notifyChanged(msg);
         }
      }
   }

   public Object getFeature()
   {
      return getEFeature();
   }
}
