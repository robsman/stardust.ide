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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;

import ag.carnot.workflow.model.IData;
import ag.carnot.workflow.model.IModel;
import ag.carnot.workflow.model.IProcessDefinition;
import ag.carnot.workflow.model.ProcessDefinition;
import ag.carnot.workflow.runtime.beans.DetailsFactory;
import ag.carnot.workflow.runtime.beans.IDataValue;
import ag.carnot.workflow.runtime.beans.IProcessInstance;
import ag.carnot.workflow.runtime.beans.ProcessInstanceBean;
import ag.carnot.workflow.runtime.details.ProcessDefinitionDetails;
import ag.carnot.workflow.spi.runtime.AccessPathEvaluationContext;
import ag.carnot.workflow.spi.runtime.ExtendedAccessPathEvaluator;
import ag.carnot.workflow.spi.runtime.SpiUtils;

/**
 * @author sborn
 * @version $Revision$
 */
public class ProcessInstanceDigest extends AbstractJavaTypeValue
{
   private static final Logger trace = LogManager.getLogger(ProcessInstanceDigest.class);
   
   private long oid;
   private String procDefId;
   private ActivityInstanceDigest startingActivityInstance;
   private DataValueDigest dataValues[];
   
   /**
    * This constructor is called from within the moonglow plugin.
    * 
    * @param name
    * @param value
    */
   public ProcessInstanceDigest(IVariable variable)
   {
      super(variable);
      
      try
      {
         IVariable[] subVariables = getAssociatedVariable().getValue().getVariables();

         oid = DebugVariableUtils.extractAsLong("oid", subVariables); //$NON-NLS-1$
         procDefId = DebugVariableUtils.extractAsString("procDefId", subVariables); //$NON-NLS-1$
         startingActivityInstance = JavaTypeValueFactory.createInstance("startingActivityInstance", subVariables); //$NON-NLS-1$
         
         initDataValues(subVariables);
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
    * @param processInstance
    */
   public ProcessInstanceDigest(IProcessInstance processInstance)
   {
      super(null);
      
      Assert.isNotNull(processInstance);

      // process instance part
      oid = processInstance.getOID();
      IModel model = (IModel) processInstance.getProcessDefinition().getModel();
      // process definition part
      ProcessDefinition procDef = (ProcessDefinition) DetailsFactory.create(
            processInstance.getProcessDefinition(), IProcessDefinition.class,
            ProcessDefinitionDetails.class);
      procDefId = '{' + model.getId() + '}' + procDef.getId();
      startingActivityInstance = null != processInstance.getStartingActivityInstance()
            ? new ActivityInstanceDigest(processInstance.getStartingActivityInstance())
            : null;
      
      Map dataValueMap = processInstance.getExistingDataValues(false);
      List<DataValueDigest> digests = CollectionUtils.newList(dataValueMap.size());
      for (Iterator iter = dataValueMap.entrySet().iterator(); iter.hasNext();)
      {
         Map.Entry entry = (Map.Entry) iter.next();
         QName qname = QName.valueOf((String) entry.getKey());
         if (qname.getNamespaceURI().equals(model.getId()))
         {
            IData data = model.findData(qname.getLocalPart());
            ExtendedAccessPathEvaluator evaluator = SpiUtils.createExtendedAccessPathEvaluator(data.getType());
            Object evaluatedValue = evaluator.evaluate(data, entry.getValue(), "",
                  new AccessPathEvaluationContext(processInstance, null));
            if (trace.isDebugEnabled())
            {
               trace.debug("data is " + data.getId() + " value is " + entry.getValue() + " evaluated value is " + evaluatedValue);
            }
            digests.add(new DataValueDigest(data, (Serializable) evaluatedValue));
         }
      }
      dataValues = digests.toArray(new DataValueDigest[digests.size()]);
   }

   public void updateDataValues()
   {
      HashMap existingValues = new HashMap();

      ProcessInstanceBean processInstance = ProcessInstanceBean.findByOID(oid);
      for (Iterator i = processInstance.getAllDataValues(); i.hasNext();)
      {
         IDataValue value = (IDataValue) i.next();

         IData data = value.getData();
         if ( !data.isPredefined())
         {
            existingValues.put(data.getId(), value);
         }
      }

      for (int idx = 0; idx < dataValues.length; ++idx)
      {
         if ( dataValues[idx].hasChanged()
               && existingValues.containsKey(dataValues[idx].getDataId()))
         {
            trace.info(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_DataValueHasChanged"), //$NON-NLS-1$
                  new Object[] {dataValues[idx]}));
            IDataValue value = (IDataValue) existingValues.get(dataValues[idx]
                  .getDataId());
            try
            {
               value.setValue(dataValues[idx].getDataField()
                     /*Reflect.convertStringToObject(
                     dataValues[idx].getDataTypeId(),
                     dataValues[idx].getDataField().getValue())*/, false);
            }
            catch (InternalException e)
            {
               trace.warn(MessageFormat.format(
                     Internal_Debugger_Messages.getString("MSG_FailedDeserializingNewValueOfData"), //$NON-NLS-1$
                     new Object[] {value.getData().getId()}), e);
            }
         }
         else
         {
            trace.info(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_DataValueHasNotChanged"), //$NON-NLS-1$
                  new Object[] {dataValues[idx]}));
         }
      }
   }

   public DataValueDigest[] getDataValues()
   {
      return dataValues;
   }

   public long getOid()
   {
      return oid;
   }

   public String getProcDefId()
   {
      return procDefId;
   }

   public ActivityInstanceDigest getStartingActivityInstance()
   {
      return startingActivityInstance;
   }
   
   public String toString()
   {
      return MessageFormat.format(Internal_Debugger_Messages.getString("MSG_ProcessInstanceDigest_ToString"), //$NON-NLS-1$
            new Object[] { new Long(getOid()), getProcDefId() });
   }

   /**
    * @param subVariables
    */
   private void initDataValues(IVariable[] subVariables)
   {
      Object[] array = JavaTypeValueFactory.createArrayInstance(
            "dataValues", subVariables); //$NON-NLS-1$
      if (null != array)
      {
         dataValues = new DataValueDigest[array.length];
         System.arraycopy(array, 0, dataValues, 0, array.length);
      }
   }
}
