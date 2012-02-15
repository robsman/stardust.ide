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
package org.eclipse.stardust.model.xpdl.api.internal.adapters;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.w3c.dom.DocumentFragment;


public class AttributeUtils
{
   public static void setAttribute(IExtensibleElement target, String name, Object value)
   {
      AttributeType attr = AttributeUtil.getAttribute(target, name);

      if (null == value)
      {
         if (null != attr)
         {
            target.getAttribute().remove(attr);
         }
      }
      else
      {
         if (null == attr)
         {
            attr = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
            attr.setName(name);

            target.getAttribute().add(attr);
         }

         setAttributeValue(attr, value);
      }
   }

   public static void setAttributeValue(AttributeType attr, Object value)
   {
      if (PredefinedConstants.XPDL_EXTENDED_ATTRIBUTES.equals(attr.getValue())
            && (value instanceof DocumentFragment))
      {
         // TODO
         // node.appendChild(node.getOwnerDocument().importNode((DocumentFragment)
         // value, true));
      }
      else
      {
         attr.setAttributeValue(Reflect.getAbbreviatedName(value.getClass()),
               Reflect.convertObjectToString(value));
      }
   }

   public static Object getAttribute(IExtensibleElement target, String name)
   {
      Object result = null;

      AttributeType attr = AttributeUtil.getAttribute(target, name);
      if (null != attr)
      {
         result = getAttributeValue(attr);
      }

      return result;
   }

   public static Object getAttributeValue(AttributeType attr)
   {
      Object result = null;

      if (PredefinedConstants.XPDL_EXTENDED_ATTRIBUTES.equals(attr.getName()))
      {
         // TODO
         /*
          * DocumentFragment extFragment =
          * child.getOwnerDocument().createDocumentFragment();
          * 
          * NodeList extElements = child.getChildNodes(); for (int i = 0; i <
          * extElements.getLength(); ++i) { Node extElement = extElements.item(i);
          * extFragment.appendChild(extElement.cloneNode(true)); }
          * extFragment.normalize(); result = extFragment;
          */
      }
      else
      {
         String valueString = attr.getAttributeValue();
         if ( !StringUtils.isEmpty(valueString))
         {
            String classname = attr.getType();
            if (StringUtils.isEmpty(classname))
            {
               result = valueString;
            }
            else
            {
               try
               {
                  result = Reflect.convertStringToObject(classname, valueString);
               }
               catch (Exception e)
               {
                  // TODO
               }
            }
         }
      }
      return result;
   }

   public static Map getAllAtttributes(IExtensibleElement target)
   {
      Map result = null;

      for (Iterator i = target.getAttribute().iterator(); i.hasNext();)
      {
         AttributeType attr = (AttributeType) i.next();
         Object value = getAttributeValue(attr);
         if ( !StringUtils.isEmpty(attr.getName()) && (null != value))
         {
            if (null == result)
            {
               result = new HashMap(target.getAttribute().size());
            }
            result.put(attr.getName(), value);
         }
      }

      return (null != result)
            ? Collections.unmodifiableMap(result)
            : Collections.EMPTY_MAP;
   }
}
