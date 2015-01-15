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

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.Var;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class BpmSerializableVariableBuilder<P>
      extends AbstractModelElementBuilder<DataType, BpmSerializableVariableBuilder<P>>
      implements Var<P>
{
   public BpmSerializableVariableBuilder()
   {
      super(F_CWM.createDataType());

      AttributeUtil.setBooleanAttribute(element, PredefinedConstants.BROWSABLE_ATT, true);
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
         DataTypeType serializableMetaType = ModelUtils.findIdentifiableElement(
               this.model.getDataType(), PredefinedConstants.SERIALIZABLE_DATA);
         if (null != serializableMetaType)
         {
            element.setType(serializableMetaType);
         }
      }
   }

   @Override
   protected DataType finalizeElement()
   {
      super.finalizeElement();
      model.getData().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Data";
   }

   public static BpmSerializableVariableBuilder<Object> newSerializableVariable()
   {
      return new BpmSerializableVariableBuilder<Object>();
   }

   public static BpmSerializableVariableBuilder<Object> newSerializableVariable(ModelType model)
   {
      return newSerializableVariable().inModel(model);
   }

   public BpmSerializableVariableBuilder<P> ofClass(String className, String value)
   {
      AttributeUtil.setAttribute(element, PredefinedConstants.CLASS_NAME_ATT, className, value);

      return this;
   }
}