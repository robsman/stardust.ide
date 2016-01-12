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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDFeature;
import org.w3c.dom.Element;

public class ElementAnnotation extends ConfigurationItem
   implements IAnnotation
{
   private CategoryAnnotation parentCategory;
   private ElementAnnotation parentElement;
   private List<IAnnotation> children;

   public ElementAnnotation(CategoryAnnotation parentCategory,
         IConfigurationElement config)
   {
      super(config);
      this.parentCategory = parentCategory;
   }

   public ElementAnnotation(ElementAnnotation parentElement,
         IConfigurationElement config)
   {
      super(config);
      this.parentElement = parentElement;
   }

   public boolean exists()
   {
      XSDFeature element = getElement();
      return getElementAdapter(element, false, false) != null || getElementAdapter(element, false, true) != null;
   }

   public List<IAnnotation> getChildren()
   {
      if (children == null)
      {
         children = new ArrayList<IAnnotation>();
         IConfigurationElement[] attributes = getConfigurationChildren("attribute"); //$NON-NLS-1$
         for (int i = 0; i < attributes.length; i++)
         {
            children.add(new AttributeAnnotation(this, attributes[i]));
         }
         IConfigurationElement[] elements = getConfigurationChildren("element"); //$NON-NLS-1$
         for (int i = 0; i < elements.length; i++)
         {
            children.add(new ElementAnnotation(this, elements[i]));
         }
      }
      return children;
   }

   public IAnnotation getParent()
   {
      return parentCategory == null ? (IAnnotation) parentElement : parentCategory;
   }

   public GenericElementAdapter getElementAdapter(XSDFeature element, boolean create, boolean forceInternal)
   {
      if (create && forceInternal)
      {
         // that is instead of assert -- will provoke a NPE if used incorrectly
         return null;
      }
      String name = getConfigurationAttribute("name"); //$NON-NLS-1$
      String namespace = getNamespace();
      if (parentCategory == null)
      {
         GenericElementAdapter parent = parentElement.getElementAdapter(element, create, forceInternal);
         if (parent == null)
         {
            return null;
         }
         GenericElementAdapter result = parent.getChild(name, namespace);
         if (result == null && create)
         {
            return parent.createChild(name, namespace, getDefaultPrefix());
         }
         return result;
      }
      else
      {
         XSDAnnotation annotation = parentCategory.getAnnotation(element, create, forceInternal);
         if (annotation == null)
         {
            return null;
         }
         String sourceURI = forceInternal ? CategoryAnnotation.ANY : parentCategory.getSourceURI(element);
         List<Element> appInfos = getAppInfos(annotation, sourceURI);
         if (appInfos.isEmpty())
         {
            if (create)
            {
               Element appInfoElement = annotation.createApplicationInformation(
                     CategoryAnnotation.ANY == sourceURI ? null : sourceURI);
               annotation.getApplicationInformation().add(appInfoElement);
               annotation.getElement().appendChild(appInfoElement);
               if (sourceURI != null && CategoryAnnotation.ANY != sourceURI)
               {
                  appInfos.add(appInfoElement);
               }
            }
            else
            {
               return null;
            }
         }

         GenericElementAdapter parent = null;
         GenericElementAdapter result = null;
         for (int i = 0; i < appInfos.size(); i++)
         {
            Element appInfoElement = (Element) appInfos.get(i);
            parent = new GenericElementAdapter(appInfoElement);
            result = parent.getChild(name, namespace);
            if (result != null)
            {
               return result;
            }
         }
         if (result == null && create)
         {
            result = parent.createChild(name, namespace, getDefaultPrefix());
         }
         return result;
      }
   }

   private List<Element> getAppInfos(XSDAnnotation annotation, String sourceURI)
   {
      return CategoryAnnotation.ANY == sourceURI
            ? annotation.getApplicationInformation()
            : annotation.getApplicationInformation(sourceURI);
   }

   private String getDefaultPrefix()
   {
      return parentCategory == null ? parentElement.getDefaultPrefix() : parentCategory.getDefaultPrefix();
   }

   protected String getNamespace()
   {
      return parentCategory == null ? parentElement.getNamespace() : parentCategory.getNamespace();
   }

   public String getRawValue()
   {
      return getRawValue(getElement());
   }

   public String getRawValue(XSDFeature element)
   {
      GenericElementAdapter adapter = getElementAdapter(element, false, false);
      if (adapter == null)
      {
         adapter = getElementAdapter(element, false, true);
      }
      return adapter == null ? null : adapter.getValue();
   }

   public void setRawValue(String value)
   {
      XSDFeature element = getElement();
      setRawValue(element, value);
   }

   public void setRawValue(XSDFeature element, String value)
   {
      GenericElementAdapter adapter = getElementAdapter(element, true, false);
      adapter.setValue(value);
      // TODO: validate against the provided element
      if (element == getElement())
      {
         validate();
      }
   }

   public boolean delete()
   {
      XSDFeature element = getElement();
      String name = getConfigurationAttribute("name"); //$NON-NLS-1$
      String namespace = getNamespace();
      if (parentCategory == null)
      {
         GenericElementAdapter parent = parentElement.getElementAdapter(element, false, false);
         if (parent != null && parent.removeChild(name, namespace))
         {
            return true;
         }
      }
      else
      {
         XSDAnnotation annotation = parentCategory.getAnnotation(element, false, false);
         if (annotation != null)
         {
            String sourceURI = parentCategory.getSourceURI(element);
            List<Element> appInfos = getAppInfos(annotation, sourceURI);
            if (!appInfos.isEmpty())
            {
               for (int i = 0; i < appInfos.size(); i++)
               {
                  Element appInfoElement = appInfos.get(i);
                  GenericElementAdapter parent = new GenericElementAdapter(appInfoElement);
                  if (parent.removeChild(name, namespace))
                  {
                     return true;
                  }
               }
            }
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
