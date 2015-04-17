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
package org.eclipse.stardust.modeling.data.structured.annotations;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.xsd.XSDFeature;
import org.w3c.dom.Attr;

public class AttributeAnnotation extends ConfigurationItem
   implements IAnnotation
{
   private ElementAnnotation parentElement;

   public AttributeAnnotation(ElementAnnotation parentElement,
         IConfigurationElement config)
   {
      super(config);
      this.parentElement = parentElement;
   }

   public boolean canModify()
   {
      return true;
   }

   public boolean exists()
   {
      XSDFeature element = getElement();
      return getAttribute(element, false, false) != null || getAttribute(element, false, true) != null;
   }

   public List<IAnnotation> getChildren()
   {
      return Collections.emptyList();
   }

   public IAnnotation getParent()
   {
      return parentElement;
   }

   private Attr getAttribute(XSDFeature element, boolean create, boolean forceInternal)
   {
      if (create && forceInternal)
      {
         // that is instead of assert -- will provoke a NPE if used incorrectly
         return null;
      }
      Attr attribute = null;
      GenericElementAdapter parentAdapter = parentElement.getElementAdapter(element, create, forceInternal);
      if (parentAdapter != null)
      {
         String name = getConfigurationAttribute("name"); //$NON-NLS-1$
         attribute = parentAdapter.getAttribute(name);
         if (attribute == null && create)
         {
            attribute = parentAdapter.createAttribute(name);
         }
      }
      return attribute;
   }

   public String getRawValue()
   {
      return getRawValue(getElement());
   }

   public String getRawValue(XSDFeature element)
   {
      Attr attribute = getAttribute(element, false, false);
      if (attribute == null)
      {
         attribute = getAttribute(element, false, true);
      }
      return attribute == null ? null : attribute.getValue();
   }

   public void setRawValue(String value)
   {
      XSDFeature element = getElement();
      setRawValue(element, value);
   }

   public void setRawValue(XSDFeature element, String value)
   {
      if (value == null)
      {
         GenericElementAdapter parentAdapter = parentElement.getElementAdapter(element, false, false);
         if (parentAdapter != null)
         {
            parentAdapter.removeAttribute(getConfigurationAttribute("name"), parentElement.getNamespace()); //$NON-NLS-1$
         }
      }
      else
      {
         Attr attribute = getAttribute(element, true, false);
         attribute.setValue(value);
      }
      if (element == getElement())
      {
         validate();
      }
   }

   public boolean delete()
   {
      XSDFeature element = getElement();
      GenericElementAdapter parentAdapter = parentElement.getElementAdapter(element, false, false);
      if (parentAdapter != null)
      {
         String name = getConfigurationAttribute("name"); //$NON-NLS-1$
         Attr attribute = parentAdapter.getAttribute(name);
         if (attribute != null)
         {
            parentAdapter.removeAttribute(name, parentElement.getNamespace());
            return true;
         }
      }
      return false;
   }

   public XSDFeature getElement()
   {
      IAnnotation parent = getParent();
      return parent == null ? null : parent.getElement();
   }
}