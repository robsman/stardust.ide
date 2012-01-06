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
import org.eclipse.stardust.engine.api.model.DataMapping;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.core.runtime.beans.IActivityInstance;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;

public class DataMappingDigest extends AbstractJavaTypeValue
{
   private String mappingId;

   private String mappingName;

   private String mappedTypeName;

   private String direction;

   private DataField dataField;

   /**
    * This constuctor is called from within the moonglow plugin.
    * 
    * @param variable
    */
   public DataMappingDigest(IVariable variable)
   {
      super(variable);

      try
      {
         IVariable[] subVariables = getAssociatedVariable().getValue().getVariables();

         mappingId = DebugVariableUtils.extractAsString("mappingId", subVariables); //$NON-NLS-1$
         mappingName = DebugVariableUtils.extractAsString("mappingName", subVariables); //$NON-NLS-1$
         mappedTypeName = DebugVariableUtils.extractAsString(
               "mappedTypeName", subVariables); //$NON-NLS-1$
         direction = DebugVariableUtils.extractAsString("direction", subVariables); //$NON-NLS-1$

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
    */
   public DataMappingDigest(IActivityInstance activityInstance, DataMapping dataMapping, Direction direction,
         Serializable dataValue)
   {
      super(null);
      IModel model = (IModel) activityInstance.getActivity().getModel();
      mappingId = dataMapping.getId();
      mappingName = dataMapping.getName();
      mappedTypeName = dataValue == null
            ? dataMapping.getMappedType().getName()
            : dataValue.getClass().getName();
      this.direction = null != direction
            ? direction.getId()
            : dataMapping.getDirection().getId();
      dataField = new DataField(mappingName, mappedTypeName, dataValue,
            '{' + model.getId() + '}' + dataMapping.getDataId(), dataMapping.getDataPath());
   }

   public boolean supportsValueModification()
   {
      boolean result = Direction.IN_OUT.getId().equals(direction)
            || Direction.OUT.getId().equals(direction);

      return result;
   }

   /**
    * @return Returns the mappingId.
    */
   public String getMappingId()
   {
      return mappingId;
   }

   /**
    * @return Returns the mappingName.
    */
   public String getMappingName()
   {
      return mappingName;
   }

   public String getMappedTypeName()
   {
      return mappedTypeName;
   }

   public String getName()
   {
      return getMappingName();
   }

   public String getTypeName()
   {
      return getMappedTypeName();
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
      return new DataField[] {dataField};
   }

   public boolean hasSubFields()
   {
      return true;
   }

   public String toString()
   {
      return MessageFormat.format(Internal_Debugger_Messages
            .getString("DataMappingDigest_ToString"), //$NON-NLS-1$
            new Object[] {mappingId, dataField.getDataHolder().getData()});
   }
}
