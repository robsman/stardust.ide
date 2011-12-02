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
package org.eclipse.stardust.model.xpdl.xpdl2.util;

import java.util.List;

import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;

import ag.carnot.base.CompareHelper;
import ag.carnot.base.StringUtils;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ExtendedAttributeUtil
{
   public static ExtendedAttributeType getAttribute(Extensible decl, String name)
   {
      return decl == null ? null : getAttribute(decl.getExtendedAttributes(), name);
   }
   
   public static ExtendedAttributeType getAttribute(ExtendedAttributesType attributes, String name)
   {
      if (attributes != null)
      {
         for (ExtendedAttributeType attribute : attributes.getExtendedAttribute())
         {
            if (attribute.getName().equals(name))
            {
               return attribute;
            }
         }
      }
      return null;
   }

   public static boolean getBooleanValue(Extensible decl, String name)
   {
      return decl == null ? false : getBooleanValue(decl.getExtendedAttributes(), name);
   }

   public static boolean getBooleanValue(ExtendedAttributesType attributes, String name)
   {
      return attributes == null ? false : getBooleanValue(getAttribute(attributes, name));
   }

   public static boolean getBooleanValue(ExtendedAttributeType attribute)
   {
      return null != attribute && Boolean.TRUE.toString().equals(attribute.getValue());
   }

   public static String getAttributeValue(Extensible decl, String name)
   {
      ExtendedAttributeType attribute = getAttribute(decl, name);
      return attribute == null ? null : attribute.getValue();
   }

   public static String getAttributeValue(ExtendedAttributesType attributes, String name)
   {
      ExtendedAttributeType attribute = getAttribute(attributes, name);
      return attribute == null ? null : attribute.getValue();
   }

   public static ExtendedAttributeType setAttribute(Extensible decl, String name, String value)
   {
      if (decl == null)
      {
         return null;
      }
      ExtendedAttributesType attributes = decl.getExtendedAttributes();
      if (attributes == null)
      {
         attributes = XpdlFactory.eINSTANCE.createExtendedAttributesType();
         decl.setExtendedAttributes(attributes);
      }
      return setAttribute(attributes, name, value);
   }
   
   public static ExtendedAttributeType setAttribute(ExtendedAttributesType attributes, String name, String value)
   {
      ExtendedAttributeType attribute = null;
      if (attributes != null)
      {
         List<ExtendedAttributeType> list = attributes.getExtendedAttribute();
         for (int i = 0; i < list.size(); i++)
         {
            ExtendedAttributeType attr = list.get(i);
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
         if (!StringUtils.isEmpty(value))
         {
            if (null == attribute)
            {
               attribute = XpdlFactory.eINSTANCE.createExtendedAttributeType();
               attribute.setName(name);
               attribute.setValue(value);
               list.add(attribute);
            }
            else
            {
               if (!CompareHelper.areEqual(value, attribute.getValue()))
               {
                  attribute.setValue(value);
               }
            }
         }
      }
      return attribute;
   }

   public static void setBooleanAttribute(Extensible decl, String name, boolean value)
   {
      setAttribute(decl, name, Boolean.toString(value));
   }

   public static void setBooleanAttribute(ExtendedAttributesType attributes, String name, boolean value)
   {
      setAttribute(attributes, name, Boolean.toString(value));
   }

   public static ExtendedAttributeType createAttribute(Extensible decl, String name)
   {
      ExtendedAttributesType attributes = decl.getExtendedAttributes();
      if (attributes == null)
      {
         attributes = XpdlFactory.eINSTANCE.createExtendedAttributesType();
         decl.setExtendedAttributes(attributes);
      }
      ExtendedAttributeType attribute = XpdlFactory.eINSTANCE.createExtendedAttributeType();
      attribute.setName(name);
      attributes.getExtendedAttribute().add(attribute);
      return attribute;
   }
}
