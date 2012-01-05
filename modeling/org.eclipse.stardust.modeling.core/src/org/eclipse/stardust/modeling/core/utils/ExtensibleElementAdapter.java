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

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IBindingMediator;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IModelAdapter;

public class ExtensibleElementAdapter extends AdapterImpl implements IModelAdapter
{
   private final EObject model;
   private final String feature;
   private boolean isBoolean;
   private List list;
   private EObject scope;
   private EStructuralFeature scopeFeature;

   private final ExtensibleElementValueAdapter modelValueAdapter;

   private IBindingMediator manager;
   private AttributeType attribute;

   public ExtensibleElementAdapter(EObject model, String feature, boolean isBoolean)
   {
      this(model, feature, isBoolean, ExtensibleElementValueAdapter.INSTANCE);
   }
   
   public ExtensibleElementAdapter(EObject model, String feature, boolean isBoolean,
         ExtensibleElementValueAdapter modelValueAdapter)
   {
      this.model = model;
      this.feature = feature;
      this.isBoolean = isBoolean;
      this.modelValueAdapter = modelValueAdapter;
   }

   public ExtensibleElementAdapter(EObject model, String feature, EObject scope,
         EStructuralFeature scopeFeature)
   {
      this(model, feature, scope, scopeFeature, ExtensibleElementValueAdapter.INSTANCE);
   }
   
   public ExtensibleElementAdapter(EObject model, String feature, EObject scope,
         EStructuralFeature scopeFeature, ExtensibleElementValueAdapter modelValueAdapter)
   {
      this.model = model;
      this.feature = feature;
      this.scope = scope;
      this.scopeFeature = scopeFeature;
      this.modelValueAdapter = modelValueAdapter;
   }

   public ExtensibleElementAdapter(EObject model, String feature, List scope)
   {
      this(model, feature, scope, ExtensibleElementValueAdapter.INSTANCE);
   }
   
   public ExtensibleElementAdapter(EObject model, String feature, List scope,
         ExtensibleElementValueAdapter modelValueAdapter)
   {
      this.model = model;
      this.feature = feature;
      this.list = scope;
      this.modelValueAdapter = modelValueAdapter;
   }

   public EObject getEModel()
   {
      return model;
   }

   public void bind(IBindingMediator manager)
   {
      if (null != this.manager)
      {
         // TODO force unbind
      }

      this.manager = manager;
      if (!model.eAdapters().contains(this))
      {
         model.eAdapters().add(this);
         if (model instanceof IExtensibleElement)
         {
            connect();
         }
      }
   }

   private void connect()
   {
      AttributeType attribute = AttributeUtil.getAttribute((IExtensibleElement) model, feature);
      if (attribute != this.attribute)
      {
         disconnect();
         if (attribute != null)
         {
            attribute.eAdapters().add(this);
         }
         this.attribute = attribute;
      }
   }

   private void disconnect()
   {
      if (attribute != null)
      {
         attribute.eAdapters().remove(this);
      }
   }

   public void unbind()
   {
      this.model.eAdapters().remove(this);
      if (model instanceof IExtensibleElement)
      {
         disconnect();
      }
      this.manager = null;
   }

   public Object getModel()
   {
      return model;
   }

   public Object getValue()
   {
      Object result = null;

      if (null == feature)
      {
         result = model;
      }
      else if (model instanceof IExtensibleElement)
      {
         connect();
         if (isBoolean)
         {
            result = getBoolean();
         }
         else
         {
            if (attribute != null)
            {
               if (attribute.getReference() != null)
               {
                  result = attribute.getReference().getIdentifiable();
               }
               else
               {
                  result = attribute.getValue();
                  if (scope != null)
                  {
                     result = ModelUtils.findIdentifiableElement(scope, scopeFeature, (String) result);
                  }
                  else if (list != null)
                  {
                     result = ModelUtils.findIdentifiableElement(list, (String) result);
                  }
               }
            }
         }
      }

      // convert to neutral format
      if (null != modelValueAdapter)
      {
         result = modelValueAdapter.fromModel(this, result);
      }

      return result;
   }

   private Boolean getBoolean()
   {
      return AttributeUtil.getBooleanValue(attribute) ? Boolean.TRUE : Boolean.FALSE;
   }

   public void updateModel(Object value)
   {
      if ((null != model) && (null != feature))
      {
         Object oldValue = getValue();

         if (!CompareHelper.areEqual(oldValue, value))
         {
            Object newValue = (null != modelValueAdapter) ? modelValueAdapter.toModel(
                  this, value) : value;

            if (isBoolean)
            {
               AttributeUtil.setBooleanAttribute((IExtensibleElement) model, feature,
                  newValue == null ? false : ((Boolean) newValue).booleanValue());
            }
            else
            {
               if (attribute == null)
               {
                  if (AttributeUtil.isReference((IExtensibleElement) model, feature))
                  {
                     AttributeUtil.setReference((IExtensibleElement) model, feature,
                           (IIdentifiableElement) newValue);
                  }
                  else
                  {
                     if ((scope != null || list != null)
                           && newValue instanceof IIdentifiableElement)
                     {
                        newValue = ((IIdentifiableElement) newValue).getId();
                     }
                     AttributeUtil.setAttribute((IExtensibleElement) model, feature,
                        newValue == null ? null : newValue.toString());
                  }
                  connect();
               }
               else if (AttributeUtil.isReference(attribute))
               {
                  AttributeUtil.setReference(attribute, (EObject) newValue);
               }
               else
               {
                  if ((scope != null || list != null)
                     && newValue instanceof IIdentifiableElement)
                  {
                     newValue = ((IIdentifiableElement) newValue).getId();
                  }
                  attribute.setValue(newValue == null ? null : newValue.toString());
               }
            }
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
         if ((null == feature)
            || CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute().equals(msg.getFeature())
            || msg.getNotifier() instanceof AttributeType)
         {
            if (feature != null && CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute().equals(
                  msg.getFeature()))
            {
               switch (msg.getEventType())
               {
               case Notification.REMOVE:
               case Notification.REMOVE_MANY:
                  Object oldObject = msg.getOldValue();
                  if (oldObject == attribute)
                  {
                     disconnect();
                  }
                  break;
               }
            }
            if (feature == null || attribute == msg.getNotifier())
            {
               updateVisuals(getValue());
            }
         }
      }
   }

   public Object getFeature()
   {
      return feature;
   }
}
