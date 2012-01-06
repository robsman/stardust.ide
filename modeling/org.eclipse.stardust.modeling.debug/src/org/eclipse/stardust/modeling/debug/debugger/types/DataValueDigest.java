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
package org.eclipse.stardust.modeling.debug.debugger.types;

import java.io.Serializable;
import java.text.MessageFormat;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaVariable;
import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.core.spi.extensions.model.BridgeObject;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;

public class DataValueDigest extends AbstractJavaTypeValue
{
   private String dataId;
   private String dataName;
   private String dataTypeId;
   private DataField dataField;
   
   /**
    * This constuctor is called from within the moonglow plugin.
    * 
    * @param name
    * @param value
    */
   public DataValueDigest(IVariable variable)
   {
      super(variable);
      
      try
      {
         IVariable[] subVariables = getAssociatedVariable().getValue().getVariables();
         
         dataId = DebugVariableUtils.extractAsString("dataId", subVariables); //$NON-NLS-1$
         dataName = DebugVariableUtils.extractAsString("dataName", subVariables); //$NON-NLS-1$
         dataTypeId = DebugVariableUtils.extractAsString("dataTypeId", subVariables); //$NON-NLS-1$
         
         dataField = JavaTypeValueFactory.createInstance("dataField", subVariables); //$NON-NLS-1$
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
   
   /**
    * This constructor is called from within the debugging engine. Breakpoints on it have
    * do be set programmatically while launching the debugging engine.
    * 
    * @param activityInstance
    */
   public DataValueDigest(IData data, Serializable serializedValue)
   {
      super(null);
      IModel model = (IModel) data.getModel();
      this.dataId = data.getId();
      this.dataName = data.getName();
      BridgeObject dvBridge = BridgeObject.getBridge(data, null, Direction.OUT, null);
      this.dataTypeId = Reflect.getAbbreviatedName(dvBridge.getEndClass());
      dataField = new DataField(dataName, dvBridge.getEndClass().getName(),
            serializedValue, '{' + model.getId() + '}' + dataId, null);
   }

   /**
    * @return Returns the dataId.
    */
   public String getDataId()
   {
      return dataId;
   }

   /**
    * @return Returns the dataName.
    */
   public String getDataName()
   {
      return dataName;
   }

   /**
    * @return Returns the dataTypeId.
    */
   public String getDataTypeId()
   {
      return dataTypeId;
   }

   public String getName()
   {
      return MessageFormat.format("{0} ({1})", //$NON-NLS-1$
            getDataName(), getDataId());
   }
   
   public String getTypeName()
   {
      return getDataTypeId();
   }

   public String getValue()
   {
      try
      {
         if (null != dataField)
         {
            return dataField.getAssociatedVariable().getValue().getValueString();
         }
         
         return "DataMappingDigest.getValue(): dataField == null"; //$NON-NLS-1$
      }
      catch (DebugException e)
      {
         throw new InternalException(e);
      }
   }

   public void setValue(String value)
   {
      try
      {
         IJavaVariable variable = (IJavaVariable) DebugVariableUtils.findVariable(
               "evaluatedValue", //$NON-NLS-1$
               getAssociatedVariable().getValue().getVariables());

         setWritebackVariable(variable);

         if (true == variable.verifyValue(value))
         {
            variable.setValue(value);
         }
      }
      catch (DebugException e)
      {
         throw new InternalException(e);
      }
   }
   
   public boolean supportsValueModification()
   {
      return true;
   }

   public boolean hasChanged()
   {
      return dataField.hasChanged();
   }

   public DataField getDataField()
   {
      return dataField;
   }

   public DataField[] getSubFields()
   {
      return new DataField[0];
   }

   public boolean hasSubFields()
   {
      return false;
   }
   
   public String toString()
   {
      return MessageFormat.format(Internal_Debugger_Messages.getString("DataValueDigest_ToString"), new Object[] { //$NON-NLS-1$
            dataId, dataField.getDataHolder().getData()});
   }
}
