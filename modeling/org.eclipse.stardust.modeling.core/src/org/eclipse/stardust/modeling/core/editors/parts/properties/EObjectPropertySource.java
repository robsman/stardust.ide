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
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class EObjectPropertySource implements IPropertySource
{
   private final IPropertyDescriptor[] propDescriptors;

   private final EObject object;

   public EObjectPropertySource(EObject obj, IPropertyDescriptor[] descriptors)
   {
      this.propDescriptors = descriptors;
      this.object = obj;
   }

   public Object getEditableValue()
   {
      return object;
   }

   public IPropertyDescriptor[] getPropertyDescriptors()
   {
      return propDescriptors;
   }

   public Object getPropertyValue(Object id)
   {
      EStructuralFeature feature = extractFeature(id);
      EObject target = extractTarget(id, object);

      Object result = target.eGet(feature);
      return (null != result) ? result : ""; //$NON-NLS-1$
   }

   public boolean isPropertySet(Object id)
   {
      EStructuralFeature feature = extractFeature(id);
      EObject target = extractTarget(id, object);

      return target.eIsSet(feature);
   }

   public void resetPropertyValue(Object id)
   {
      EStructuralFeature feature = extractFeature(id);
      EObject target = extractTarget(id, object);

      target.eUnset(feature);
   }

   public void setPropertyValue(Object id, Object value)
   {
      EStructuralFeature feature = extractFeature(id);
      EObject target = extractTarget(id, object);

      target.eSet(feature, value);
   }
   
   private static EObject extractTarget(Object propertyId, EObject defaultTarget)
   {
      EObject target;
      if (propertyId instanceof BoundEObjectPropertyId)
      {
         target = ((BoundEObjectPropertyId) propertyId).getObject();
      }
      else
      {
         target = defaultTarget;
      }
      return target;
   }

   private static EStructuralFeature extractFeature(Object propertyId)
   {
      EStructuralFeature eFtr = null;
      if (propertyId instanceof BoundEObjectPropertyId)
      {
         eFtr = ((BoundEObjectPropertyId) propertyId).getId();
      }
      else if (propertyId instanceof EStructuralFeature)
      {
         eFtr = (EStructuralFeature) propertyId;
      }
      return eFtr;
   }
}
