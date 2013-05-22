/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.variable;

import java.util.Date;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.Var;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;



public class BpmPrimitiveVariableBuilder<P>
      extends AbstractModelElementBuilder<DataType, BpmPrimitiveVariableBuilder<P>>
      implements Var<P>
{
   public BpmPrimitiveVariableBuilder()
   {
      super(F_CWM.createDataType());
   }

   public String variableId()
   {
      return element.getId();
   }

   @Override
   protected void setModel(ModelType model)
   {
      super.setModel(model);

      if ((null == element.getType()) && (null != this.model))
      {
         DataTypeType primitiveMetaType = ModelUtils.findIdentifiableElement(
               this.model.getDataType(), PredefinedConstants.PRIMITIVE_DATA);
         if (null != primitiveMetaType)
         {
            element.setType(primitiveMetaType);
         }
      }
   }

   @Override
   protected DataType finalizeElement()
   {
      super.finalizeElement();

      if (null == AttributeUtil.getAttributeValue(element, PredefinedConstants.TYPE_ATT))
      {
         // if no explicit type was given, make it a string variable
         ofType(Type.String);
      }

      // TODO set type specific default value?

      model.getData().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Data";
   }

   public static BpmPrimitiveVariableBuilder<Object> newPrimitiveVariable()
   {
      return new BpmPrimitiveVariableBuilder<Object>();
   }

   public static BpmPrimitiveVariableBuilder<Object> newPrimitiveVariable(ModelType model)
   {
      return newPrimitiveVariable().inModel(model);
   }

   @SuppressWarnings("unchecked")
   public <T> BpmPrimitiveVariableBuilder<T> ofType(Class<T> type)
   {
      // this converts the T to the argument type
      Type primitiveType;
      if ((Boolean.class == type) || (Boolean.TYPE == type))
      {
         primitiveType = Type.Boolean;
      }
      else if ((Byte.class == type) || (Byte.TYPE == type))
      {
         primitiveType = Type.Byte;
      }
      else if ((Character.class == type) || (Character.TYPE == type))
      {
         primitiveType = Type.Char;
      }
      else if ((Double.class == type) || (Double.TYPE == type))
      {
         primitiveType = Type.Double;
      }
      else if ((Float.class == type) || (Float.TYPE == type))
      {
         primitiveType = Type.Float;
      }
      else if ((Integer.class == type) || (Integer.TYPE == type))
      {
         primitiveType = Type.Integer;
      }
      else if ((Long.class == type) || (Long.TYPE == type))
      {
         primitiveType = Type.Long;
      }
      else if ((Short.class == type) || (Short.TYPE == type))
      {
         primitiveType = Type.Short;
      }
      else if (String.class == type)
      {
         primitiveType = Type.String;
      }
      else if (Date.class == type)
      {
         primitiveType = Type.Timestamp;
      }
      else
      {
         throw new IllegalArgumentException("Illegal primitive variable type: " + type);
      }

      return (BpmPrimitiveVariableBuilder<T>) ofType(primitiveType);
   }

   public BpmPrimitiveVariableBuilder<P> ofType(Type typeCode)
   {
      AttributeUtil.setAttribute(element, PredefinedConstants.TYPE_ATT,
            Type.class.getName(), typeCode.getId());

      return this;
   }

   public BpmPrimitiveVariableBuilder<P> havingDefaultValue(P defaultValue)
   {
      if (null != defaultValue)
      {
         AttributeUtil.setAttribute(element, PredefinedConstants.DEFAULT_VALUE_ATT,
               defaultValue.toString());
      }

      return this;
   }
}
