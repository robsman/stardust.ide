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
package org.eclipse.stardust.modeling.model.i18n.properties;

import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class ScopedPropertyModel extends AdapterImpl
{
   private PropertyModel model = null;
   private IModelElement element = null;
   private Set<String> propertiesSet = CollectionUtils.newSet();

   /**
    * Qualifying prefix for all the properties belonging to this model.
    */
   private String prefix = null;

   public ScopedPropertyModel(IModelElement element)
   {
      this.element = element;
      
      ModelType root = ModelUtils.findContainingModel(element);
      model = PropertyModel.get(root);
      
      prefix = model.computePrefix(null, element, null, true);
      
      propertiesSet.clear();
      propertiesSet.add(PropertyModel.NAME); //$NON-NLS-1$
      if (element instanceof ModelType || element instanceof IIdentifiableModelElement)
      {
         propertiesSet.add(PropertyModel.DESCRIPTION); //$NON-NLS-1$
      }
   }

   public void dispose()
   {
      unregister();
      model.dispose();
   }

   /**
    * Gets the names of all properties defined for this model element.
    * 
    * @return a set of strings containing the property names.
    */
   public Set<String> getProperties()
   {
      return propertiesSet;
   }

   public String getProperty(Object locale, String propertyName)
   {
      return model.getProperty(locale, prefix + propertyName);
   }

   public void setProperty(Object locale, String propertyName, String value)
   {
      if (StringUtils.isEmpty(value.toString()))
      {
         model.deleteProperty(locale, prefix + propertyName);
      }
      else
      {
         model.setProperty(locale, prefix + propertyName, value.toString());
      }
   }

   public void register()
   {
      if (element != null && !element.eAdapters().contains(this))
      {
         element.eAdapters().add(this);
      }
   }

   void unregister()
   {
      if (element != null)
      {
         element.eAdapters().remove(this);
      }
   }

   public void notifyChanged(Notification msg)
   {
      if (msg.getNotifier().equals(element)
            && CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id().equals(
                  msg.getFeature()))
      {
         prefix = model.computePrefix(null, element, null, true);
         
         String oldId = msg.getOldStringValue();
         String newId = msg.getNewStringValue();
         
         model.updatePropertiesFor((IIdentifiableElement) element, oldId, newId);
      }
   }

   public PropertyModel getPropertyModel()
   {
      return model;
   }
}
