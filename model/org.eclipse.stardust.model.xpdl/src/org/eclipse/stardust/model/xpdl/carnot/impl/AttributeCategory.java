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
package org.eclipse.stardust.model.xpdl.carnot.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.IAttributeCategory;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelMessages;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class AttributeCategory implements IAttributeCategory
{
   private String id;

   private IAttributeCategory parent;

   private IExtensibleElement target;

   public AttributeCategory(IExtensibleElement target, String id,
         IAttributeCategory parent)
   {
      this.target = target;
      this.id = id;
      this.parent = parent;
   }

   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
      // todo: change the id's of contained attributes
   }

   public String getFullId()
   {
      return parent == null || parent.getFullId() == null
         ? id : parent.getFullId() + ":" + id; //$NON-NLS-1$
   }

   public List<AttributeType> getAttributes()
   {
      List<AttributeType> attributes = CollectionUtils.newList();
      String prefix = getFullId();
      if (prefix != null)
      {
         prefix += ":";
      }
      for (AttributeType attribute : target.getAttribute())
      {
         String name = attribute.getName();
         if (prefix == null && name.indexOf(":") < 0
               || name != null && name.startsWith(prefix)
                  && name.indexOf(':', prefix.length()) < 0)
         {
            attributes.add(attribute);
         }
      }
      return attributes;
   }

   public List<IAttributeCategory> getAttributeCategories()
   {
      List<IAttributeCategory> attributeCategories = CollectionUtils.newList();
      Set<String> ids = CollectionUtils.newSet();
      String prefix = getFullId();
      if (prefix != null)
      {
         prefix += ":";
         for (AttributeType attribute : target.getAttribute())
         {
            String name = attribute.getName();
            if (name != null && name.startsWith(prefix)
                     && name.indexOf(':', prefix.length()) > 0)
            {
               String[] categoryIds = name.substring(prefix.length()).split(":"); //$NON-NLS-1$
               if ((categoryIds.length > 1) && (!ids.contains(categoryIds[0])))
               {
                  ids.add(categoryIds[0]);
                  attributeCategories.add(new AttributeCategory(target, categoryIds[0], this));
               }
            }
         }
      }
      return attributeCategories;
   }

   public void removeAttribute(String attributeId)
   {
      List<AttributeType> toRemove = CollectionUtils.newList();
      if (getFullId() != null)
      {
         String name = getFullId() + ":" + attributeId; //$NON-NLS-1$
         for (AttributeType attribute : target.getAttribute())
         {
            if (attribute.getName().equals(name))
            {
               toRemove.add(attribute);
            }
         }
      }
      target.getAttribute().removeAll(toRemove);
   }

   public void removeAttributeCategory(String categoryId)
   {
      List<AttributeType> toRemove = CollectionUtils.newList();
      if (getFullId() != null)
      {
         String prefix = getFullId() + ":" + categoryId + ":"; //$NON-NLS-1$ //$NON-NLS-2$
         for (AttributeType attribute : target.getAttribute())
         {
            if (attribute.getName().startsWith(prefix))
            {
               toRemove.add(attribute);
            }
         }
      }
      target.getAttribute().removeAll(toRemove);
   }

   public AttributeType getAttribute(String attributeId)
   {
      if (getFullId() != null)
      {
         return AttributeUtil.getAttribute(target, getFullId() + ":" + attributeId); //$NON-NLS-1$
      }
      return null;
   }

   public IAttributeCategory getAttributeCategory(String categoryId)
   {
      if (getFullId() != null)
      {
         String prefix = getFullId() + ":" + categoryId + ":"; //$NON-NLS-1$ //$NON-NLS-2$
         for (AttributeType attribute : target.getAttribute())
         {
            if (attribute.getName().startsWith(prefix))
            {
               return new AttributeCategory(target, categoryId, this);
            }
         }
      }
      return null;
   }

   public AttributeType createAttribute(String attributeId)
   {
      if (getFullId() != null)
      {
         String attName = getFullId() + ":" + attributeId; //$NON-NLS-1$
         if (AttributeUtil.getAttribute(target, attName) != null)
         {
            throw new IllegalArgumentException(MessageFormat.format(
                  ModelMessages.MSG_ATTRIBUTE_EXISTS, new Object[] {attName})); //$NON-NLS-1$
         }
         AttributeType attribute = CarnotWorkflowModelFactory.eINSTANCE
               .createAttributeType();
         attribute.setName(attName);
         target.getAttribute().add(attribute);
         return attribute;
      }
      return null;
   }

   public IAttributeCategory createAttributeCategory(String categoryId)
   {
      if (getFullId() != null)
      {
         String prefix = getFullId() + ": " + categoryId; //$NON-NLS-1$
         for (AttributeType attribute : target.getAttribute())
         {
            if (attribute.getName().startsWith(prefix))
            {
               throw new IllegalArgumentException(MessageFormat.format(
                     ModelMessages.MSG_ATTRIBUTE_CATEGORY_EXISTS, //$NON-NLS-1$
                     new Object[] {prefix}));
            }
         }
         return new AttributeCategory(target, categoryId, this);
      }
      return null;
   }

   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof AttributeCategory)) return false;

      final AttributeCategory attributeCategory = (AttributeCategory) o;

      if (id != null ? !id.equals(attributeCategory.id) : attributeCategory.id != null) return false;
      if (parent != null ? !parent.equals(attributeCategory.parent) : attributeCategory.parent != null) return false;
      if (target != null ? !target.equals(attributeCategory.target) : attributeCategory.target != null) return false;

      return true;
   }

   public int hashCode()
   {
      int result;
      result = (id != null ? id.hashCode() : 0);
      result = 29 * result + (parent != null ? parent.hashCode() : 0);
      result = 29 * result + (target != null ? target.hashCode() : 0);
      return result;
   }
}
