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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaVariable;
import org.eclipse.stardust.common.Money;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.core.runtime.beans.ModelManagerFactory;
import org.eclipse.stardust.engine.core.struct.ClientXPathMap;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConverter;
import org.eclipse.stardust.engine.core.struct.StructuredTypeRtUtils;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;
import org.eclipse.stardust.modeling.debug.model.JavaVariableDecorator;

public class DataField extends AbstractJavaTypeValue
{
   private static final Logger trace = LogManager.getLogger(DataField.class);

   private DataHolder dataHolder;
   
   private String name;
   private String typeName;
   private String evaluatedValue;
   private boolean isTypeSupported;
   private String declaredTypeAdapterId;
   private String dataPath;
   
   private boolean hasChanged;
   
   /**
    * This constuctor is called from within the moonglow plugin.
    * 
    * @param variable
    */
   public DataField(IVariable variable)
   {
      super(variable);
      
      try
      {
         IVariable[] subVariables = getAssociatedVariable().getValue().getVariables();
         
         name = DebugVariableUtils.extractAsString("name", subVariables); //$NON-NLS-1$
         typeName = DebugVariableUtils.extractAsString("typeName", subVariables); //$NON-NLS-1$
         evaluatedValue = DebugVariableUtils.extractAsString("evaluatedValue", subVariables); //$NON-NLS-1$
         isTypeSupported = DebugVariableUtils.extractAsBoolean("isTypeSupported", subVariables); //$NON-NLS-1$
         hasChanged = DebugVariableUtils.extractAsBoolean("hasChanged", subVariables); //$NON-NLS-1$
         declaredTypeAdapterId = DebugVariableUtils.extractAsString("declaredTypeAdapterId", subVariables); //$NON-NLS-1$
         dataPath = DebugVariableUtils.extractAsString("dataPath", subVariables); //$NON-NLS-1$
      
         IVariable hasChangedVariable = DebugVariableUtils.findVariable("hasChanged", subVariables); //$NON-NLS-1$ 
         setWritebackVariable(new JavaVariableDecorator(
               (IJavaVariable) findWritebackVariable(subVariables), 
               (IJavaVariable) hasChangedVariable,
               name));
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
    * @param dataValue
    * @param dataId 
    * @param dataPath 
    */
   public DataField(String name, String typeName, Serializable dataValue, String dataId, String dataPath)
   {
      super(null);
      
      this.dataHolder = new DataHolder(typeName, dataValue);
      this.name = name;
      this.typeName = typeName;
      this.dataPath = dataPath;

      QName qname = QName.valueOf(dataId);
      IModel activeModel = ModelManagerFactory.getCurrent().findActiveModel(qname.getNamespaceURI());
      IData data = activeModel.findData(qname.getLocalPart());
      
      if (data.getType().getId().equals(StructuredDataConstants.STRUCTURED_DATA))
      {
         this.declaredTypeAdapterId = (String) data.getAttribute(StructuredDataConstants.TYPE_DECLARATION_ATT);
      }
      
      if (PrimitivesFactory.isSupportedType(Reflect.getClassFromClassName(typeName)))
      {
         isTypeSupported = true;
      }
      else
      {
         isTypeSupported = false;
         evaluatedValue = Internal_Debugger_Messages.getString("MSG_VariableNotSupported"); //$NON-NLS-1$
         trace.info(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_TypeIsNotSupportedAsDataField"), //$NON-NLS-1$
               typeName));
      }
      
      if (null != dataValue)
      {
         if (isTypeSupported)
         {
            if (data.getType().getId().equals(StructuredDataConstants.STRUCTURED_DATA))
            {
               final IXPathMap xPathMap = new ClientXPathMap(StructuredTypeRtUtils.getAllXPaths(activeModel, this.declaredTypeAdapterId));
               StructuredDataConverter converter = new StructuredDataConverter(xPathMap);
               evaluatedValue = converter.toString(dataValue, this.dataPath);
            }
            else
            {
               evaluatedValue = Reflect.convertObjectToString(dataValue);
            }
         }
      }
      else
      {
         evaluatedValue = Constants.EMPTY;
      }
      
      hasChanged = false;
   }

   public String getDataPath()
   {
      return dataPath;
   }

   public String getDeclaredTypeAdapterId()
   {
      return declaredTypeAdapterId;
   }

   public boolean hasChanged()
   {
      return hasChanged;
   }

   public String getName()
   {
      return name;
   }

   public String getTypeName()
   {
      return typeName;
   }

   public String getValue()
   {
      return evaluatedValue;
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
            evaluatedValue = value;
         }
      }
      catch (DebugException e)
      {
         throw new InternalException(e);
      }
   }

   public boolean supportsValueModification()
   {
      
      return isTypeSupported && getAssociatedVariable().supportsValueModification();
   }

   public DataHolder getDataHolder()
   {
      return dataHolder;
   }

   /**
    * @param subVariables
    * @throws DebugException
    */
   private IVariable findWritebackVariable(IVariable[] subVariables) throws DebugException
   {
      // find the IVariable which holds the real data and set it as writeback variable
      IVariable dataHolderVariable = DebugVariableUtils.findVariable("dataHolder", subVariables); //$NON-NLS-1$
      IVariable[] dataHolderSubVariables = dataHolderVariable.getValue().getVariables();
      String variableName = DebugVariableUtils.extractAsString("variableName", dataHolderSubVariables); //$NON-NLS-1$
      //String typeName = DebugVariableUtils.extractAsString("typeName", dataHolderSubVariables); //$NON-NLS-1$
      
      return DebugVariableUtils.findVariable(variableName, dataHolderSubVariables);
   }

   public static class DataHolder
   {
      private Boolean theBoolean;
      private Byte theByte;
      private Short theShort;
      private Integer theInteger;
      private Long theLong;
      private Float theFloat;
      private Double theDouble;
      private Character theCharacter;
      private String theString;
      private Calendar theCalendar;
      private Date theDate;
      private Money theMoney;
      private List theList;
      private Map theMap;
      private Serializable theSerializable;
      
      private String typeName;
      private String variableName;
      
      public DataHolder(String typeName, Serializable data)
      {
         this.typeName = typeName;
         initFittingField(data);
      }

      public String getVariableName()
      {
         return variableName;
      }
      
      public Serializable getData()
      {
         return (Serializable) Reflect.getFieldValue(this, variableName);
      }

      public String getTypeName()
      {
         return typeName;
      }

      private void initFittingField(Serializable data)
      {
         Class type;
         if (null == theSerializable)
         {
            type = Reflect.getClassFromClassName(typeName);
            trace.info("initFittingField: "+typeName); //$NON-NLS-1$
            if (null == type)
            {
               trace.warn(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_CannotFindClassForTypeName"), //$NON-NLS-1$
                     new Object[] { typeName }));
            }
         }
         else
         {
            type = theSerializable.getClass();
         }
         
         if (Boolean.class.equals(type))
         {
            theBoolean = (Boolean) data;
            this.variableName = "theBoolean"; //$NON-NLS-1$
         }
         else if (Byte.class.equals(type))
         {
            theByte = (Byte) data;
            this.variableName = "theByte"; //$NON-NLS-1$
         }
         else if (Short.class.equals(type))
         {
            theShort = (Short) data;
            this.variableName = "theShort"; //$NON-NLS-1$
         }
         else if (Integer.class.equals(type))
         {
            theInteger = (Integer) data;
            this.variableName = "theInteger"; //$NON-NLS-1$
         }
         else if (Long.class.equals(type))
         {
            theLong = (Long) data;
            this.variableName = "theLong"; //$NON-NLS-1$
         }
         else if (Float.class.equals(type))
         {
            theFloat = (Float) data;
            this.variableName = "theFloat"; //$NON-NLS-1$
         }
         else if (Double.class.equals(type))
         {
            theDouble = (Double) data;
            this.variableName = "theDouble"; //$NON-NLS-1$
         }
         else if (Character.class.equals(type))
         {
            theCharacter = (Character) data;
            this.variableName = "theCharacter"; //$NON-NLS-1$
         }
         else if (String.class.equals(type))
         {
            theString = (String) data;
            this.variableName = "theString"; //$NON-NLS-1$
         }
         else if (Calendar.class.equals(type))
         {
            theCalendar = (Calendar) data;
            this.variableName = "theCalendar"; //$NON-NLS-1$
         }
         else if (Date.class.equals(type))
         {
            theDate = (Date) data;
            this.variableName = "theDate"; //$NON-NLS-1$
         }
         else if (Money.class.equals(type))
         {
            theMoney = (Money) data;
            this.variableName = "theMoney"; //$NON-NLS-1$
         }
         else if (List.class.isAssignableFrom(type))
         {
            theList = (List)data;
            this.variableName = "theList"; //$NON-NLS-1$
         }
         else if (Map.class.isAssignableFrom(type))
         {
            theMap = (Map)data;
            this.variableName = "theMap"; //$NON-NLS-1$
         }
         else
         {
            theSerializable = data;
            this.variableName = "theSerializable"; //$NON-NLS-1$
         }
      }
   }
}
