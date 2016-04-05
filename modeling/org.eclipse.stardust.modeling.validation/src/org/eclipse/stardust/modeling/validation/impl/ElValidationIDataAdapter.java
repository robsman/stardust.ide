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
package org.eclipse.stardust.modeling.validation.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IDataType;
import org.eclipse.stardust.engine.api.model.IReference;
import org.eclipse.stardust.engine.api.model.PluggableType;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.model.utils.ModelElement;
import org.eclipse.stardust.engine.core.model.utils.RootElement;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class ElValidationIDataAdapter implements IData
{

   private static final long serialVersionUID = 1L;

   private final DataType data;

   public ElValidationIDataAdapter(DataType data)
   {
      this.data = data;
   }

   public PluggableType getType()
   {
      PluggableType result = null;

      if (null != data.getMetaType())
      {
         result = new ElValidationPluggableTypAdapter(data.getMetaType());
      }
      else if (null != AttributeUtil.getAttributeValue(data, "carnot:engine:typeHint")) //$NON-NLS-1$
      {
         result = new ElValidationPluggableTypAdapter(
               (IMetaType) ModelUtils.findIdentifiableElement(
                     ModelUtils.findContainingModel(data).getDataType(),
                     PredefinedConstants.PRIMITIVE_DATA), //
               true);
      }

      return result;
   }

   public Map getAllAttributes()
   {
      Map result = null;

      for (Iterator i = data.getAttribute().iterator(); i.hasNext();)
      {
         AttributeType attr = (AttributeType) i.next();
         Object value = getAttributeValue(attr);
         if ( !StringUtils.isEmpty(attr.getName()) && (null != value))
         {
            if (null == result)
            {
               result = new HashMap(data.getAttribute().size());
            }
            result.put(attr.getName(), value);
         }
      }

      return (null != result)
            ? Collections.unmodifiableMap(result)
            : Collections.EMPTY_MAP;
   }

   private static Object getAttributeValue(AttributeType attr)
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
   
   //
   // TODO dummy implementations
   //

   public void checkConsistency(List arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setDataType(IDataType arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setId(String arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setName(String arg0)
   {
      // TODO Auto-generated method stub

   }

   public void delete()
   {
      // TODO Auto-generated method stub

   }

   public String getDescription()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public int getElementOID()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public RootElement getModel()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public long getOID()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public ModelElement getParent()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getUniqueId()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean isPredefined()
   {
      // TODO Auto-generated method stub
      return false;
   }

   public boolean isTransient()
   {
      // TODO Auto-generated method stub
      return false;
   }

   public void register(int arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setDescription(String arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setParent(ModelElement arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setPredefined(boolean arg0)
   {
      // TODO Auto-generated method stub

   }

   public Direction getDirection()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getName()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Object getAttribute(String name)
   {
      return AttributeUtil.getAttributeValue(data, name);
   }

   public boolean getBooleanAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return false;
   }

   public float getFloatAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public int getIntegerAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public long getLongAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public String getStringAttribute(String arg0)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void markModified()
   {
      // TODO Auto-generated method stub

   }

   public void removeAllAttributes()
   {
      // TODO Auto-generated method stub

   }

   public void removeAttribute(String arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setAllAttributes(Map arg0)
   {
      // TODO Auto-generated method stub

   }

   public void setAttribute(String arg0, Object arg1)
   {
      // TODO Auto-generated method stub

   }

   public String getId()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Object getRuntimeAttribute(String name)
   {
      throw new UnsupportedOperationException(Validation_Messages.EXC_NOT_IMPLEMENTED);
   }

   public Object setRuntimeAttribute(String name, Object value)
   {
      throw new UnsupportedOperationException(Validation_Messages.EXC_NOT_IMPLEMENTED);
   }

	public IReference getExternalReference() {
		// TODO Auto-generated method stub
		return null;
	}

   @Override
   public String getQualifiedId()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
