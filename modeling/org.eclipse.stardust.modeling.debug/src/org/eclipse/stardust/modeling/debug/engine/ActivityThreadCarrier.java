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

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;

import ag.carnot.base.Action;
import ag.carnot.base.log.LogManager;
import ag.carnot.base.log.Logger;
import ag.carnot.workflow.model.IActivity;
import ag.carnot.workflow.runtime.TimeoutException;
import ag.carnot.workflow.runtime.beans.*;

/**
 * This class is adapted from
 * {@link ag.carnot.workflow.runtime.beans.ActivityThreadCarrier}. 
 * 
 * @author sborn
 * @version $Revision$
 */
public final class ActivityThreadCarrier extends ActionCarrier
{
   /**
    * to make eclipse happy
    */
   private static final long serialVersionUID = 1L;

   public static final Logger trace = LogManager.getLogger(ActivityThreadCarrier.class);

   public static final String PROCESS_INSTANCE_OID_TAG = "processInstanceOID";  //$NON-NLS-1$
   public static final String ACTIVITY_OID_TAG = "activityOID";  //$NON-NLS-1$
   public static final String ACTIVITY_INSTANCE_OID_TAG = "activityInstanceOID";  //$NON-NLS-1$
   public static final String USER_OID_TAG = "userOID";  //$NON-NLS-1$
   public static final String TIMEOUT_NOTIFICATION = "timeoutNotification";  //$NON-NLS-1$

   private long processInstanceOID;
   private long activityOID;
   private IActivityInstance activityInstance;
   private boolean timeout;
   private Map data = Collections.EMPTY_MAP;

   public ActivityThreadCarrier()
   {
      super(SYSTEM_MESSAGE_TYPE_ID);
   }

   public void setProcessInstance(IProcessInstance processInstance)
   {
      if (processInstance != null)
      {
         processInstanceOID = processInstance.getOID();
      }
      else
      {
         processInstanceOID = 0;
      }
   }

   public void setActivity(IActivity activity)
   {
      if (activity != null)
      {
         activityOID = activity.getOID();
      }
      else
      {
         activityOID = 0;
      }
   }

   public void setActivityInstance(IActivityInstance activityInstance)
   {
      this.activityInstance = activityInstance;
   }

   public void setTimeout(Throwable timeout)
   {
      this.timeout = (timeout != null);
   }

   public void doFillMessage(Message message) throws JMSException
   {
   }

   public void doExtract(Message message) throws JMSException
   {
   }

   public Action doCreateAction()
   {
      trace.debug("activityinstance: " + getActivityInstanceOid());  //$NON-NLS-1$
      trace.debug("processinstance : " + processInstanceOID);  //$NON-NLS-1$

      TimeoutException timeoutException = null;
      if (timeout)
      {
         timeoutException = new TimeoutException(Constants.EMPTY);
      }
      // @todo (france, ub): set the new user identity here!
      return new ActivityThreadRunner(processInstanceOID, activityOID, getActivityInstanceOid(),
            timeoutException, data);
   }

   public void setData(Map data)
   {
      this.data = data;
   }

   public String toString()
   {
      return MessageFormat.format(Internal_Debugger_Messages.getString("MSG_ActivityThreadCarrier"), //$NON-NLS-1$
            processInstanceOID, getActivityInstanceOid(), activityOID);
   }
   
   private long getActivityInstanceOid()
   {
      return null == activityInstance ? 0 : activityInstance.getOID();
   }

   // @todo (france, ub): beschissen
   class ActivityThreadRunner implements Action
   {
      private Map data;
      private long processInstanceOID;
      private long activityOID;
      private long activityInstanceOID;
      private TimeoutException timeoutException;

      public ActivityThreadRunner(long processInstanceOID, long activityOID,
            long activityInstanceOID, TimeoutException timeoutException, Map data)
      {
         this.processInstanceOID = processInstanceOID;
         this.activityOID = activityOID;
         this.activityInstanceOID = activityInstanceOID;
         this.timeoutException = timeoutException;
         this.data = data;
      }

      public Object execute()
      {
         IProcessInstance processInstance = null;
         IActivity activity = null;
         IActivityInstance activityInstance = null;

         if (0 != activityInstanceOID)
         {
            activityInstance = ActivityInstanceBean.findByOID(activityInstanceOID);
            activityInstance.activate();
         }
         else
         {
            processInstance = ProcessInstanceBean.findByOID(processInstanceOID);
            activity = (IActivity) ModelManagerFactory.getCurrent()
                  .lookupObjectByOID(activityOID);
         }

         ActivityThread at = new ActivityThread(processInstance, activity,
               activityInstance, timeoutException, data, false);
         at.run();
         return null;
      }

      public String toString()
      {
         return MessageFormat.format(Internal_Debugger_Messages.getString("MSG_ActivityThread"), //$NON-NLS-1$
               processInstanceOID, activityInstanceOID, activityOID);
      }
   }
}
