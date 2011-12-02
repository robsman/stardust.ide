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
package org.eclipse.stardust.modeling.debug.engine;

import java.awt.Toolkit;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;
import org.eclipse.stardust.modeling.debug.debugger.Debugger;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.ProcessInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.ui.ApplicationFrame;
import org.eclipse.stardust.modeling.debug.debugger.ui.WorkflowGUIAdapter;

import ag.carnot.base.Action;
import ag.carnot.base.Direction;
import ag.carnot.base.log.LogManager;
import ag.carnot.base.log.Logger;
import ag.carnot.config.Parameters;
import ag.carnot.config.PropertyLayer;
import ag.carnot.error.InternalException;
import ag.carnot.error.InvalidValueException;
import ag.carnot.error.ObjectNotFoundException;
import ag.carnot.workflow.model.IActivity;
import ag.carnot.workflow.model.IDataMapping;
import ag.carnot.workflow.model.ImplementationType;
import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.workflow.runtime.ServiceFactory;
import ag.carnot.workflow.runtime.WorkflowService;
import ag.carnot.workflow.runtime.beans.ActivityInstanceBean;
import ag.carnot.workflow.runtime.beans.ForkingService;
import ag.carnot.workflow.runtime.beans.IActivityInstance;
import ag.carnot.workflow.runtime.beans.IProcessInstance;
import ag.carnot.workflow.runtime.beans.IUser;
import ag.carnot.workflow.runtime.beans.ProcessInstanceBean;
import ag.carnot.workflow.runtime.beans.interceptors.PropertyLayerProviderInterceptor;
import ag.carnot.workflow.runtime.beans.removethis.SecurityProperties;
import ag.carnot.workflow.runtime.details.ActivityInstanceDetails;

/**
 * @author sborn
 * @version $Revision$
 */
public class ManagedRunner implements Runnable
{
   private static final Logger trace = LogManager.getLogger(ManagedRunner.class);
   
   private static final int PREFERRED_FRAME_Y_OFFSET = 25;
   private static final int PREFERRED_APP_FRAME_WIDTH = 500;
   private static final int PREFERRED_APP_FRAME_HEIGHT = 500;
   private static final int PREFERRED_DATA_FRAME_HEIGHT = 150;
   private static final int PREFERRED_WORKLIST_FRAME_HEIGHT = 150;
   
   private Action managedRunnable;
   private final ForkingService service;
   private final boolean isActivityThread;
   private IActivityInstance nextActivityInstance;
   private IUser inheritedUser;  
   
   public ManagedRunner(Action managedRunnable, ForkingService service, boolean isActivityThread)
   {
      this(null, managedRunnable, service, isActivityThread);
   }

   public ManagedRunner(IUser inheritedUser, Action managedRunnable, ForkingService service, boolean isActivityThread)
   {
      this.inheritedUser = inheritedUser;
      this.managedRunnable = managedRunnable;
      this.service = service;
      this.isActivityThread = isActivityThread;
      this.nextActivityInstance = null;
   }

   public void run()
   {
      Thread thread = Thread.currentThread();
      if (inheritedUser != null)
      {
         PropertyLayer layer = (PropertyLayer) Parameters.instance().get(
               PropertyLayerProviderInterceptor.PROPERTY_LAYER);
         if (layer != null)
         {
            layer.setProperty(SecurityProperties.CURRENT_USER, inheritedUser);
         }
         else
         {
            Parameters.instance().set(SecurityProperties.CURRENT_USER, inheritedUser);
         }
      }
      
      try
      {
         service.isolate(managedRunnable);
         
         do
         {
            if (isActivityThread)
            {
               Debugger debugger = (Debugger) Parameters.instance().get(
                     Constants.CURRENT_DEBUGGER_PARAM);

               if (null == debugger)
               {
                  throw new InternalException(Internal_Debugger_Messages.getString("EXP_NoCurrentDebuggerInstanceFound")); //$NON-NLS-1$
               }

               nextActivityInstance = debugger.getNextActivityInstance();
               if (null != nextActivityInstance
                     // TODO: this shall prevent thread suspension on subprocess activity
                     // instances which are completely done but not terminated.
                     // Find better way.
                     && !isSubProcess(nextActivityInstance)
                     && !nextActivityInstance.isTerminated())
               {
                  boolean completionDone = false;

                  while ( !completionDone)
                  {
                     trace.info(MessageFormat.format(
                           Internal_Debugger_Messages.getString("MSG_AboutToEnterCommunicationMethod"),  //$NON-NLS-1$
                           thread, nextActivityInstance));
                     completionDone = communicateWithSuspendedActivityInstance(
                           new ActivityInstanceDigest(nextActivityInstance),
                           false);
                  }
               }
            }
         } while (null != nextActivityInstance);
      }
      catch (Throwable e)
      {
         // No way to handle it besides logging
         trace.warn(Internal_Debugger_Messages.getString("WARN_ExceptionallyTerminatingManagedRunnable"), e); //$NON-NLS-1$
      }
      finally
      {
         trace.info(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_TerminationOfJavaThread"), //$NON-NLS-1$
               thread));
      }
   }
   
   private boolean communicateWithSuspendedActivityInstance(
         ActivityInstanceDigest activityInstance, boolean reloadValues)
   {
      // Do not remove this method call! It suspends the current thread.
      ManagedRunnerHelper.suspendThread();
      
      activityInstance.updateDataMappings();
      ProcessInstanceDigest processInstance = activityInstance.getProcessInstance();
      if(null != processInstance)
      {
         processInstance.updateDataValues();
      }
      
      if (reloadValues)
      {
         return false;
      }
      
      Map data = new HashMap();
      ActivityThreadCarrier carrier = new ActivityThreadCarrier();

      if (ImplementationType.Application.getId().equals(
            activityInstance.getImplementationTypeId()))
      {
         ServiceFactory sf = (ServiceFactory) Parameters.instance().get(
               Constants.CURRENT_SERVICE_FACTORY_PARAM);

         WorkflowService workflowService = sf.getWorkflowService();
         try
         {
            ActivityInstanceDetails aiDetails = new ActivityInstanceDetails(
                  nextActivityInstance);
            WorkflowGUIAdapter guiAdapter = new WorkflowGUIAdapter();
            JPanel activityPanel = guiAdapter.getPanel(workflowService, aiDetails);
            if (false == showApplicationFrame(aiDetails.getActivity().getName(),
                  activityPanel))
            {
               return false;
            }
   
            data = guiAdapter.processApplicationOutDataMappings(workflowService, aiDetails);
            setOutDataValues(PredefinedConstants.JFC_CONTEXT, data, aiDetails.getOID());
         }
         finally
         {
            sf.release(workflowService);
         }
      }
      
      carrier.setProcessInstance(nextActivityInstance.getProcessInstance());
      carrier.setActivity(nextActivityInstance.getActivity());
      carrier.setActivityInstance(nextActivityInstance);
   
      service.isolate(carrier.createAction());
      
      return true;
   }
   
   private void setOutDataValues(String context, Map values, long activityInstanceOID)
         throws ObjectNotFoundException, InvalidValueException
   {
      IActivityInstance activityInstance = ActivityInstanceBean
            .findByOID(activityInstanceOID);

      if (null == context)
      {
         context = PredefinedConstants.DEFAULT_CONTEXT;
      }

      IActivity activity = activityInstance.getActivity();

      if ((null != values) && !values.isEmpty())
      {
         for (Iterator i = values.entrySet().iterator(); i.hasNext();)
         {
            Map.Entry entry = (Map.Entry) i.next();
            IDataMapping dm = activity.findDataMappingById((String) entry.getKey(),
                  Direction.OUT, context);
            if (dm == null)
            {
               throw new ObjectNotFoundException(MessageFormat.format(
                     Internal_Debugger_Messages.getString("EXP_NoDataMappingWithIdForContext"), //$NON-NLS-1$
                     entry.getKey(), context));
            }
            Object value = entry.getValue();
            setOutDataValue(dm, value, activityInstance.getProcessInstanceOID());
         }
      }
   }

   private void setOutDataValue(IDataMapping mapping, Object value,
         long processInstanceOID) throws ObjectNotFoundException, InvalidValueException
   {
      IProcessInstance processInstance = ProcessInstanceBean
            .findByOID(processInstanceOID);

      processInstance.setOutDataValue(mapping.getData(), mapping.getDataPath(), value);
   }
   
   private boolean showApplicationFrame(String name, JPanel panel)
   {
      JFrame frame = new JFrame(name);
      frame.setVisible(true);
      
      ApplicationFrame applicationFrame = new ApplicationFrame(frame, name, panel);
      applicationFrame.setBounds(
            Toolkit.getDefaultToolkit().getScreenSize().width - PREFERRED_APP_FRAME_WIDTH,
            PREFERRED_FRAME_Y_OFFSET + PREFERRED_DATA_FRAME_HEIGHT + PREFERRED_WORKLIST_FRAME_HEIGHT,
            PREFERRED_APP_FRAME_WIDTH, PREFERRED_APP_FRAME_HEIGHT);

      applicationFrame.setVisible(true);
      
      frame.setVisible(true);
      boolean doComplete = applicationFrame.getDoComplete();
      frame.dispose();
      
      return doComplete;
   }

   private static boolean isSubProcess(IActivityInstance activityInstance)
   {
      return ImplementationType.SubProcess.equals(activityInstance
            .getActivity().getImplementationType());
   }
}
