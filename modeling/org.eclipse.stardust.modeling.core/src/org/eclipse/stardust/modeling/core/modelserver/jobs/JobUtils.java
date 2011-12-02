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
package org.eclipse.stardust.modeling.core.modelserver.jobs;

import java.util.Calendar;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.stardust.model.xpdl.carnot.merge.UUIDUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.ui.PlatformUI;

import ag.carnot.base.StringUtils;

public class JobUtils implements IPropertyChangeListener
{
   private ModelServer modelServer;
   private RefreshStatusJob refreshStatusJob;

   public JobUtils(ModelServer server)
   {
      modelServer = server;
      IPreferenceStore store = PlatformUI.getPreferenceStore();
      store.addPropertyChangeListener(this);          
   }

   public synchronized void runRefreshJob()
   {
      if (refreshStatusJob == null)
      {
         setRefreshStatusJob(new RefreshStatusJob(modelServer));
         refreshStatusJob.schedule();
      }
      else
      {
         long refreshRate = 1000 * Long.parseLong(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_COLLISION_REFRESH_RATE));
         Calendar cal = Calendar.getInstance();            
         long current = cal.getTimeInMillis();
         Long previous = modelServer.getLastRefresh();
         if (previous == null || current >= previous.longValue() + refreshRate)
         {
            if(refreshStatusJob.getState() == Job.SLEEPING)
            {
               refreshStatusJob.wakeUp();                           
            }
            else
            {
               refreshStatusJob.schedule();               
            }
         }
         else
         {
            if(refreshStatusJob.getState() == Job.SLEEPING)
            {
               refreshStatusJob.wakeUp(previous.longValue() - current + refreshRate);                           
            }
            else
            {
               refreshStatusJob.schedule(previous.longValue() - current + refreshRate);
            }
         }
      }
   }   
      
   public void update()
   {
      // when preferences changes, we must check this
      IFolder modelLockFolder = modelServer.getModelLockFolder();
      if (modelLockFolder == null 
            || !modelLockFolder.exists()
            || StringUtils.isEmpty(UUIDUtils.getUUID(modelServer.getModel())))
      {
         return;
      }
            
      if (PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_COLLISION_REFRESH))
      {
         runRefreshJob();
      }      
      else
      {
         if (refreshStatusJob != null)
         {
            refreshStatusJob.cancel();
            refreshStatusJob = null;
         }         
      }
   }   
   
   public void propertyChange(PropertyChangeEvent event)
   {
      if (event.getProperty().equals(BpmProjectNature.PREFERENCE_COLLISION_REFRESH))
      {
         update();
      }
      if (event.getProperty().equals(BpmProjectNature.PREFERENCE_COLLISION_REFRESH_RATE))
      {
         update();
      }      
      if (event.getProperty().equals(BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY))
      {
         modelServer.setRetry(PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY));
      }      
      if (event.getProperty().equals(BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY_RATE))
      {
         modelServer.setRetryConnection(1000 * Long.parseLong(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY_RATE)));
      }      
   }

   public void dispose()
   {
      if(refreshStatusJob != null)
      {
         refreshStatusJob.cancel();
         refreshStatusJob = null;
      }
   }

   // wake up with runRefreshJob
   public void sleep()
   {
      if (refreshStatusJob != null)
      {
         refreshStatusJob.sleep();
      }         
   }

   public void cancel()
   {
      if (refreshStatusJob != null)
      {
         refreshStatusJob.cancel();
         refreshStatusJob = null;
      }               
   }  
   
   public void setRefreshStatusJob(RefreshStatusJob job)
   {
      refreshStatusJob = job;
   }   
}