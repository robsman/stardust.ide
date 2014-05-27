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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.impl.AttributeCategory;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;

/**
 * @author fherinean
 * @version $Revision$
 */
public class AttributeUtil
{
   public static AttributeType getAttribute(IExtensibleElement element, String att)
   {
      List<AttributeType> attributes = element.getAttribute();
      return getAttribute(attributes, att);
   }

   private static AttributeType getAttribute(List<AttributeType> attributes, String att)
   {
      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attribute = (AttributeType) attributes.get(i);
         if (attribute.getName() != null && attribute.getName().equals(att))
         {
            return attribute;
         }
      }
      return null;
   }

   public static boolean getBooleanValue(IExtensibleElement element, String att)
   {
      return getBooleanValue(getAttribute(element, att));
   }

   public static boolean getBooleanValue(AttributeType attribute)
   {
      return (null != attribute) && Type.Boolean.getId().equals(attribute.getType())
            && Boolean.TRUE.toString().equals(attribute.getValue());
   }

   public static String getAttributeValue(IExtensibleElement element, String nameAtt)
   {
      AttributeType attribute = getAttribute(element, nameAtt);
      return attribute == null ? null : attribute.getValue();
   }

   public static String getAttributeValue(List<AttributeType> attributes, String nameAtt)
   {
      AttributeType attribute = getAttribute(attributes, nameAtt);
      return attribute == null ? null : attribute.getValue();
   }

   public static AttributeType setAttribute(IExtensibleElement element, String name, String value)
   {
      return setAttribute(element.getAttribute(), name, null, value);
   }

   public static AttributeType setAttribute(IExtensibleElement element, String name, String type,
         String value)
   {
      return setAttribute(element.getAttribute(), name, type, value);
   }

   public static AttributeType setAttribute(List<AttributeType> list, String name, String value)
   {
      return setAttribute(list, name, null, value);
   }

   public static AttributeType setAttribute(List<AttributeType> list, String name, String type, String value)
   {
      AttributeType attribute = null;
      for (int i = 0; i < list.size(); i++)
      {
         AttributeType attr = (AttributeType) list.get(i);
         if (name.equals(attr.getName()))
         {
            if ((null != value) && (0 < value.length()))
            {
               attribute = attr;
            }
            else
            {
               list.remove(i);
            }
            break;
         }
      }
      if ((null != value) && (0 < value.length()))
      {
         if (null == attribute)
         {
            attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
            attribute.setName(name);
         }

         // prevent notification on type change alone
         final boolean deliver = attribute.eDeliver();
         attribute.eSetDeliver(false);
         attribute.setType(type);
         attribute.eSetDeliver(deliver);

         if (!areEqual(value, attribute.getValue()))
         {
            attribute.setValue(value);
         }

         if (!list.contains(attribute))
         {
            list.add(attribute);
         }
      }
      return attribute;
   }

   private static boolean areEqual(Object value1, Object value2)
   {
      return value1 == value2 || value1 != null && value1.equals(value2);
   }

   public static void setBooleanAttribute(IExtensibleElement element, String name,
         Boolean value)
   {
      setAttribute(element, name, "boolean", value == null ? null : value ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   public static void clearExcept(IExtensibleElement element, String[] ids)
   {
      for (Iterator<AttributeType> i = element.getAttribute().iterator(); i.hasNext();)
      {
         AttributeType attribute = (AttributeType) i.next();
         boolean found = false;
         for (int j = 0; j < ids.length; j++)
         {
            if (attribute.getName().equals(ids[j]))
            {
               found = true;
               break;
            }
         }
         if (!found)
         {
            i.remove();
         }
      }
   }

   public static List<IAttributeCategory> getAttributeCategories(IExtensibleElement element)
   {
      List<IAttributeCategory> categories = CollectionUtils.newList();

      for (AttributeType attribute : element.getAttribute())
      {
         String categoryID = getCategoryName(attribute.getName());
         IAttributeCategory category = getExistingCategory(categoryID, categories);
         if (category == null)
         {
            category = new AttributeCategory(element, categoryID, null);
            categories.add(category);
         }
      }

      return categories;
   }

   private static String getCategoryName(String attName)
   {
      if (attName.indexOf(":") < 0) //$NON-NLS-1$
      {
         return null;
      }
      String[] categoryIDs = attName.split(":"); //$NON-NLS-1$
      return categoryIDs[0];
   }

   private static IAttributeCategory getExistingCategory(String id, List<IAttributeCategory> categories)
   {
      for (IAttributeCategory category : categories)
      {
         if (id == null && category.getId() == null
               || id != null && id.equals(category.getId()))
         {
            return category;
         }
      }
      return null;
   }

   public static IAttributeCategory createAttributeCategory(IExtensibleElement element,
         String id)
   {
      return new AttributeCategory(element, id, null);
   }

   public static <T extends EObject> T getIdentifiable(IExtensibleElement element, String name)
   {
      AttributeType attribute = getAttribute(element, name);
      if (attribute != null)
      {
         IdentifiableReference reference = attribute.getReference();
         if (reference != null)
         {
            @SuppressWarnings("unchecked")
            T result = (T) reference.getIdentifiable();
            return result;
         }
      }
      return null;
   }

   public static AttributeType createAttribute(String name)
   {
      AttributeType attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
      attribute.setName(name);
      return attribute;
   }

   public static void setReference(IExtensibleElement element, String name,
         EObject identifiable)
   {
      AttributeType attribute = getAttribute(element, name);
      if (identifiable == null)
      {
         if (attribute != null)
         {
            element.getAttribute().remove(attribute);
         }
      }
      else
      {
         if (attribute == null)
         {
            attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
            attribute.setName(name);
            element.getAttribute().add(attribute);
         }
         setReference(attribute, identifiable);
      }
   }

   public static void setReference(AttributeType attribute, EObject element)
   {
      IdentifiableReference reference = attribute.getReference();
      if (reference == null || reference.getAttribute() != attribute)
      {
         reference = CarnotWorkflowModelFactory.eINSTANCE.createIdentifiableReference();
         attribute.setReference(reference);
      }
      reference.setIdentifiable(element);
   }

   public static EObject getReferenceElement(AttributeType attribute)
   {
      IdentifiableReference ref = attribute.getReference();
      if(ref != null)
      {
         return ref.getIdentifiable();
      }
      return null;
   }

   public static boolean isReference(AttributeType attribute)
   {
      return attribute.getReference() != null;
   }

   public static boolean isReference(IExtensibleElement element, String feature)
   {
      List<IConfigurationElement> configs = SpiExtensionRegistry.getConfiguration(element, "elementReference"); //$NON-NLS-1$
      if (configs != null)
      {
         for(IConfigurationElement config : configs)
         {
            IConfigurationElement[] refs = config.getChildren("attribute"); //$NON-NLS-1$
            for (int k = 0; k < refs.length; k++)
            {
               if (feature.equals(refs[k].getAttribute("attributeName"))) //$NON-NLS-1$
               {
                  return true;
               }
            }
         }
      }
      return false;
   }

   public static String getCDataAttribute(IExtensibleElement data, String name)
   {
      String xmlString = null;
      AttributeType attr = getAttribute(data, name);
      if (attr != null)
      {
         XmlTextNode node = attr.getValueNode();
         if (node != null)
         {
            xmlString = ModelUtils.getCDataString(node.getMixed());
         }
      }
      return xmlString;
   }

   public static void setCDataAttribute(IExtensibleElement data, String name,
         String value)
   {
      if (value == null)
      {
         setAttribute(data, name, value);
      }
      else
      {
         AttributeType attr = getAttribute(data, name);
         if (attr == null)
         {
            attr = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
            attr.setName(name);
            data.getAttribute().add(attr);
         }
         XmlTextNode node = attr.getValueNode();
         if (node == null)
         {
            node = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
            attr.setValueNode(node);
         }
         ModelUtils.setCDataString(node.getMixed(), value);
      }
   }
}