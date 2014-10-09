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

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSchema;

public class CategoryAnnotation implements IAnnotation
{
   private IConfigurationElement config;
   private XSDElementDeclaration element;
   private List<IAnnotation> children;
   private AnnotationContentProvider provider;

   static final String ANY = "any"; //$NON-NLS-1$

   public CategoryAnnotation(AnnotationContentProvider provider,
         XSDElementDeclaration element, IConfigurationElement config)
   {
      this.provider = provider;
      this.element = element;
      this.config = config;
   }

   private ExtendedAttributeType getExternalAnnotationAttribute()
   {
      return ExtendedAttributeUtil.getAttribute(this.provider.getDeclaration(),
            ExtendedAttributeType.EXTERNAL_ANNOTATIONS_NAME);
   }

   public IConfigurationElement getConfiguration()
   {
      return config;
   }

   public String getId()
   {
      return config.getAttribute("id"); //$NON-NLS-1$
   }

   public String getName()
   {
      return config.getAttribute("name"); //$NON-NLS-1$
   }

   public List<IAnnotation> getChildren()
   {
      if (children == null)
      {
         IConfigurationElement[] elements = config.getChildren("element"); //$NON-NLS-1$
         children = CollectionUtils.newList();
         for (int i = 0; i < elements.length; i++)
         {
            children.add(new ElementAnnotation(this, elements[i]));
         }
      }
      return children;
   }

   public IAnnotation getParent()
   {
      return null;
   }

   public boolean canModify()
   {
      return false;
   }

   public boolean exists()
   {
      List<IAnnotation> children = getChildren();
      for (int i = 0; i < children.size(); i++)
      {
         ElementAnnotation element = (ElementAnnotation) children.get(i);
         if (element.exists())
         {
            return true;
         }
      }
      return false;
   }

   public String getNamespace()
   {
      return config.getAttribute("namespace"); //$NON-NLS-1$
   }

   public String getDefaultPrefix()
   {
      return config.getAttribute("defaultPrefix"); //$NON-NLS-1$
   }

   public String getRawValue()
   {
      return getRawValue(getElement());
   }

   public String getRawValue(XSDElementDeclaration element)
   {
      return null;
   }

   public void setRawValue(String value)
   {
      setRawValue(getElement(), value);
   }

   public void setRawValue(XSDElementDeclaration element, String value)
   {
   }

   public XSDAnnotation getAnnotation(XSDElementDeclaration element, boolean create, boolean forceInternal)
   {
      if (create && forceInternal)
      {
         // that is instead of assert -- will provoke a NPE if used incorrectly
         return null;
      }
      XSDAnnotation annotation = null;
      if (provider.isInternalSchema() && forceInternal)
      {
         return null;
      }
      else if (provider.isInternalSchema() || forceInternal)
      {
         annotation = getInternalAnnotation(element, create);
      }
      else
      {
         annotation = getExternalAnnotation(element, create);
         if (annotation == null && !create)
         {
            annotation = getInternalAnnotation(element, false);
         }
      }
      return annotation;
   }

   private XSDAnnotation getInternalAnnotation(XSDElementDeclaration element, boolean create)
   {
      if (element == null)
      {
         return null;
      }
      XSDAnnotation annotation = element.getAnnotation();
      if (annotation == null)
      {
         if (create)
         {
            annotation = XSDFactory.eINSTANCE.createXSDAnnotation();
            element.setAnnotation(annotation);
            if (annotation.getElement() == null)
            {
               annotation.updateElement();
            }
         }
      }
      return annotation;
   }

   private XSDAnnotation getExternalAnnotation(XSDElementDeclaration element, boolean create)
   {
      if (element == null)
      {
         return null;
      }
      XSDAnnotation annotation = null;
      ExtendedAttributeType externalAnnotationAttribute = getExternalAnnotationAttribute();
      if (externalAnnotationAttribute == null)
      {
         if (create)
         {
            externalAnnotationAttribute = ExtendedAttributeUtil.createAttribute(provider.getDeclaration(),
                  ExtendedAttributeType.EXTERNAL_ANNOTATIONS_NAME);
         }
      }
      if (externalAnnotationAttribute != null)
      {
         annotation = externalAnnotationAttribute.getExtendedAnnotation();
         if (annotation == null && create)
         {
            annotation = ExtensionFactory.eINSTANCE.createExtendedAnnotationType();
            externalAnnotationAttribute.setExtendedAnnotation((ExtendedAnnotationType) annotation);
            annotation.updateElement();
         }
      }
      return annotation;
   }

   public String getSourceURI(XSDConcreteComponent component)
   {
      if (provider.isInternalSchema())
      {
         return CategoryAnnotation.ANY;
      }
      List<String> path = CollectionUtils.newList();
      while (!(component == null || component instanceof XSDSchema))
      {
         if (component instanceof XSDElementDeclaration)
         {
            path.add(((XSDElementDeclaration) component).getName());
         }
         else if (component instanceof XSDNamedComponent && component.eContainer() instanceof XSDSchema)
         {
            path.add(((XSDNamedComponent) component).getName());
         }
         component = component.getContainer();
      }
      StringBuffer sb = new StringBuffer();
      for (int i = path.size() - 1; i >= 0; i--)
      {
         sb.append((String) path.get(i));
         if (i > 0)
         {
            sb.append('/');
         }
      }
      return sb.toString();
   }

   public void setElementDeclaration(XSDElementDeclaration element)
   {
      this.element = element;
   }

   public XSDElementDeclaration getElement()
   {
      return element;
   }
}
