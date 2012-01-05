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
import java.util.Map;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.common.Pair;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;
import org.eclipse.stardust.modeling.debug.highlighting.IHighlightable;

import ag.carnot.workflow.model.Activity;
import ag.carnot.workflow.model.ApplicationContext;
import ag.carnot.workflow.model.DataMapping;
import ag.carnot.workflow.model.IModel;
import ag.carnot.workflow.model.IParticipant;
import ag.carnot.workflow.model.ImplementationType;
import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.workflow.runtime.ServiceFactory;
import ag.carnot.workflow.runtime.WorkflowService;
import ag.carnot.workflow.runtime.beans.IActivityInstance;
import ag.carnot.workflow.runtime.beans.IUser;
import ag.carnot.workflow.runtime.details.ActivityDetails;

/**
 * @author sborn
 * @version $Revision$
 */
public class ActivityInstanceDigest extends AbstractJavaTypeValue implements IHighlightable
{
   private static final Logger trace = LogManager.getLogger(ActivityInstanceDigest.class);
   
   public static final String IMPL_TYPE_MANUAL = ImplementationType.Manual.getId();  
   public static final String IMPL_TYPE_ROUTE = ImplementationType.Route.getId();  
   public static final String IMPL_TYPE_APPLICATION = ImplementationType.Application.getId();  
   public static final String IMPL_TYPE_SUBPROCESS = ImplementationType.SubProcess.getId();
   
   private long oid;
   private String activityId;
   private String activityName;
   private boolean isInteractive;
   private String implementationTypeId;
   private ProcessInstanceDigest processInstance;
   private DataMappingDigest dataMappings[];
   
   private ApplicationDigest application;
   private String procDefId;
   private String performerName;
   private String userPerformerName;
   
   private IThread thread;
   
   /**
    * This constuctor is called from within the moonglow plugin.
    * 
    * @param name
    * @param value
    */
   public ActivityInstanceDigest(IVariable variable)
   {
      super(variable);
      
      try
      {
         IVariable[] subVariables = variable.getValue().getVariables();
   
         oid = DebugVariableUtils.extractAsLong("oid", subVariables); //$NON-NLS-1$
         activityId = DebugVariableUtils.extractAsString("activityId", subVariables); //$NON-NLS-1$
         activityName = DebugVariableUtils.extractAsString("activityName", subVariables); //$NON-NLS-1$
         isInteractive = DebugVariableUtils.extractAsBoolean("isInteractive", subVariables); //$NON-NLS-1$
         implementationTypeId = DebugVariableUtils.extractAsString(
               "implementationTypeId", subVariables); //$NON-NLS-1$
         
         initDataMappings(subVariables);
         
         application = JavaTypeValueFactory.createInstance("application", subVariables); //$NON-NLS-1$
         procDefId = DebugVariableUtils.extractAsString("procDefId", subVariables); //$NON-NLS-1$
         processInstance = JavaTypeValueFactory.createInstance("processInstance", subVariables); //$NON-NLS-1$
         
         performerName = DebugVariableUtils.extractAsString("performerName", subVariables); //$NON-NLS-1$
         userPerformerName = DebugVariableUtils.extractAsString("userPerformerName", subVariables); //$NON-NLS-1$
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
   public ActivityInstanceDigest(IActivityInstance activityInstance)
   {
      super(null);
      
      Assert.isNotNull(activityInstance);

      // activity instance part
      oid = activityInstance.getOID();
      IModel model = (IModel) activityInstance.getActivity().getModel();
      // activity part
      Activity activity = new ActivityDetails(activityInstance.getActivity());
      activityId = '{' + model.getId() + '}' + activity.getId();
      activityName = activity.getName();
      isInteractive = activity.isInteractive();
      ImplementationType implType = activity.getImplementationType();
      implementationTypeId = implType.getId();
      
      ApplicationContext context = null;
      if (ImplementationType.Manual == implType)
      {
         context = activity.getApplicationContext(PredefinedConstants.DEFAULT_CONTEXT);
      }
      else if (ImplementationType.Application == implType)
      {
         if (isInteractive())
         {
            // At the moment only JFC is supported for interactive applications
            context = activity.getApplicationContext(PredefinedConstants.JFC_CONTEXT);
            if (null != context)
            {
               application = new ApplicationDigest(context);
            }
         }
         else
         {
            context = activity.getApplicationContext(PredefinedConstants.ENGINE_CONTEXT);
            application = new ApplicationDigest(activity.getApplication());
         }
      }
      
      if (null != context)
      {
         loadDataMappings(activityInstance, context);
      }
      else
      {
         dataMappings = new DataMappingDigest[0];
      }
      
      // process definition part
      procDefId = '{' + model.getId() + '}' + activityInstance.getProcessInstance().getProcessDefinition().getId();
      processInstance = new ProcessInstanceDigest(activityInstance.getProcessInstance());
      
      // performer part
      IParticipant participant = activityInstance.getCurrentPerformer();
      if (null != participant)
      {
         performerName = participant.getName();
      }
      else
      {
         IUser user = activityInstance.getCurrentUserPerformer();
         if (null != user)
         {
            userPerformerName = user.getName();
         }
      }
   }
   
   public void updateDataMappings()
   {
      Map values = new HashMap();
      for (int idx = 0; idx < dataMappings.length; ++idx)
      {
         if ( dataMappings[idx].hasChanged())
         {
            trace.info(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_DataMappingHasChanged"), //$NON-NLS-1$
                  dataMappings[idx]));
            try
            {
               Object value = dataMappings[idx].getDataField().getDataHolder().getData();
               values.put(dataMappings[idx].getMappingId(), value);
            }
            catch (InternalException e)
            {
               trace.warn(MessageFormat.format(
                     Internal_Debugger_Messages.getString("MSG_FailedDeserializingOutDataValueOfMapping"),  //$NON-NLS-1$
                     dataMappings[idx].getMappingId()), e);
            }
         }
         else
         {
            trace.info(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_DataMappingHasNotChanged"), //$NON-NLS-1$
                  dataMappings[idx]));
         }
      }
      
      if (0 < values.size())
      {
         ServiceFactory sf = (ServiceFactory) Parameters.instance().get(
               Constants.CURRENT_SERVICE_FACTORY_PARAM);
         
         WorkflowService workflowService = sf.getWorkflowService();
         try
         {
            workflowService.activate(oid);
            workflowService.suspendToDefaultPerformer(oid,
                  PredefinedConstants.DEFAULT_CONTEXT, values);
         }
         finally
         {
            sf.release(workflowService);
         }
      }
   }

   /**
    * @return The associated IThread (can be null)
    */
   public IThread getThread()
   {
      return thread;
   }

   public void setThread(IThread thread)
   {
      this.thread = thread;
   }

   /**
    * @return Returns the oid.
    */
   public long getOid()
   {
      return oid;
   }

   /**
    * @return Returns the activityId.
    */
   public String getActivityId()
   {
      return activityId;
   }

   /**
    * @return Returns the activityName.
    */
   public String getActivityName()
   {
      return activityName;
   }

   /**
    * @return Returns the implementationTypeId.
    */
   public String getImplementationTypeId()
   {
      return implementationTypeId;
   }
   
   /**
    * @return Returns the application.
    */
   public ApplicationDigest getApplication()
   {
      return application;
   }
   
   /**
    * @return Returns the dataMappings.
    */
   public DataMappingDigest[] getDataMappings()
   {
      return dataMappings;
   }
   
   public ProcessInstanceDigest getProcessInstance()
   {
      return processInstance;
   }

   public String getPerformerName()
   {
      return performerName;
   }
   
   public String getUserPerformerName()
   {
      return userPerformerName;
   }
   
   public String getProcessDefinitionChildId()
   {
      return activityId;
   }

   public String getProcessDefinitionId()
   {
      return procDefId;
   }
   
   public String toString()
   {
      return MessageFormat.format(Internal_Debugger_Messages.getString("MSG_ActivityInstanceDigest_ToString"), //$NON-NLS-1$
            oid, activityId, procDefId);
   }
   
   public boolean equals(Object other)
   {
      if (this == other)
      {
         return true;
      }
      if (!(other instanceof ActivityInstanceDigest))
      {
         return false;
      }
      ActivityInstanceDigest tmp = (ActivityInstanceDigest) other;
      return CompareHelper.areEqual(procDefId, tmp.procDefId) &&
             CompareHelper.areEqual(activityId, tmp.activityId);
   }
   
   public int hashCode()
   {
      return new Highlightable(getProcessDefinitionId(), getProcessDefinitionChildId()).hashCode();
   }

   private void loadDataMappings(IActivityInstance activityInstance, ApplicationContext context)
   {
      Map mappings = new HashMap();
      for (Iterator i = context.getAllInDataMappings().iterator(); i.hasNext();)
      {
         DataMapping mapping = (DataMapping) i.next();
         mappings.put(mapping.getId(), new Pair(mapping, Direction.IN));
      }
      
      for (Iterator i = context.getAllOutDataMappings().iterator(); i.hasNext();)
      {
         DataMapping mapping = (DataMapping) i.next();
         mappings.put(mapping.getId(), mappings.containsKey(mapping.getId())
               ? new Pair(mapping, Direction.IN_OUT)
               : new Pair(mapping, Direction.OUT));
      }
      
      ServiceFactory sf = (ServiceFactory) Parameters.instance().get(
            Constants.CURRENT_SERVICE_FACTORY_PARAM);
      WorkflowService workflowService = sf.getWorkflowService();      

      final Map inValues;
      try
      {
         inValues = workflowService.getInDataValues(activityInstance.getOID(),
               context.getId(), null);
      }
      finally
      {
         sf.release(workflowService);
      }

      int idx = 0;
      dataMappings = new DataMappingDigest[mappings.size()];
      for (Iterator i = mappings.values().iterator(); i.hasNext();)
      {
         Pair mappingPair = (Pair) i.next();
         DataMapping dataMapping = (DataMapping) mappingPair.getFirst();
         
         dataMappings[idx] = new DataMappingDigest(
               activityInstance,
               dataMapping,
               (Direction) mappingPair.getSecond(),
               (Serializable) inValues.get(dataMapping.getId()));
         ++idx;
      }
   }
   /**
    * @param subVariables
    */
   private void initDataMappings(IVariable[] subVariables)
   {
      Object[] array = JavaTypeValueFactory.createArrayInstance(
            "dataMappings", subVariables); //$NON-NLS-1$
      if (null != array)
      {
         dataMappings = new DataMappingDigest[array.length];
         System.arraycopy(array, 0, dataMappings, 0, array.length);
      }
   }

   public boolean isInteractive()
   {
      return isInteractive;
   }
}
