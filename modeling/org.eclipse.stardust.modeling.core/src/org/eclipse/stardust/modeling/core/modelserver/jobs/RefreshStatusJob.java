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

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.modelserver.LockFileUtils;
import org.eclipse.stardust.modeling.core.modelserver.ModelHistoryEntry;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.modelserver.RMSException;
import org.eclipse.stardust.modeling.core.modelserver.Vcs;
import org.eclipse.stardust.modeling.core.modelserver.VcsStatus;
import org.eclipse.stardust.modeling.core.modelserver.ui.VcsFeedView;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import ag.carnot.base.CompareHelper;

public class RefreshStatusJob extends Job
{
   private NullProgressMonitor NULL_MONITOR = new NullProgressMonitor();
   
   private ModelServer modelServer;
   private boolean jobInterrupted = false;
   
   // TODO: (fh) please check that it is created only in the context of a WorkflowModelEditor
   public RefreshStatusJob(ModelServer server)
   {
      super(Diagram_Messages.LB_REFRESH_STATUS_JOB);
      setPriority(Job.LONG);
      modelServer = server;
   }   
   
   public void callSchedule()
   {
      if(jobInterrupted)
      {
         jobInterrupted = false;
         schedule(5000);         
         return;
      }
         
      if (PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_COLLISION_REFRESH))
      {    
         int refreshRate = PlatformUI.getPreferenceStore().getInt(BpmProjectNature.PREFERENCE_COLLISION_REFRESH_RATE);
         schedule(refreshRate * 1000);
      }
      else
      {
         modelServer.getJobUtils().setRefreshStatusJob(null);         
      }
   }

   protected IStatus run(final IProgressMonitor monitor)
   {
      try
      {
         final Vcs vcs = ModelServerUtils.getVcs2(modelServer.getProject());
         if (vcs == null)
         {
            // (fh) project not shared
            modelServer.getJobUtils().setRefreshStatusJob(null);
            return Status.CANCEL_STATUS;
         }

         // wait until other operation is finished
         if(!modelServer.isEnableRefreshStatusJob())
         {
            jobInterrupted = true;
            monitor.done();
            callSchedule();               
            return Status.OK_STATUS;            
         }

         if (!PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_COLLISION_REFRESH))
         {    
            monitor.done();
            return Status.OK_STATUS;                        
         }         
         
         final StateCache stateCache = modelServer.getStateCache();
         final ModelType model = modelServer.getModel();
         
         // editor closed, finish job here
         WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
         if (editor == null)
         {
            monitor.done();
            modelServer.getJobUtils().setRefreshStatusJob(null);
            return Status.OK_STATUS;            
         }
         
         Map<String, EObject> localMap = ModelServerUtils.createUuidToElementMap(model);
         monitor.beginTask(Diagram_Messages.LB_REFRESH_STATUS_JOB + modelServer.getModelName(), localMap.size() + 1);
         IResource[] placeholder = new IResource[1];
         for (EObject element : localMap.values())
         {
            if (monitor.isCanceled())
            {
               break;
            }
            monitor.subTask(modelServer.getLabel(element));
            IFile lockFile = LockFileUtils.getLockFile(element);
            // is this possible? (the map contains only elements with uuid)
            if (lockFile == null)
            {
               stateCache.setState(element, CollisionInfo.ADDED);
            }
            else if (!lockFile.exists())
            {
               stateCache.setState(element, CollisionInfo.REMOVED);
            }
            else
            {
               VcsStatus status = vcs.getStatus(lockFile);
               if(status.getStatus() == VcsStatus.STATUS_NOT_SHARED)
               {
                  placeholder[0] = lockFile;
                  // (fh) intentionally no monitor passed to the vcs
                  // otherwise the work logged here will be overwritten
                  vcs.update(placeholder, NULL_MONITOR);
                  stateCache.setState(element, CollisionInfo.REMOVED);                  
               }               
               else if (!CompareHelper.areEqual(status.getHeadRevision(), status.getBaseRevision()))
               {
                  placeholder[0] = lockFile;
                  // (fh) intentionally no monitor passed to the vcs
                  // otherwise the work logged here will be overwritten
                  vcs.update(placeholder, NULL_MONITOR);
                  stateCache.updateState(element, status);
               }
               else
               {
                  stateCache.updateState(element, status);                  
               }
            }
            monitor.worked(1);
         }
         
         if (PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_COLLISION_UPDATE))
         {    
            // update history, if we have a view open
            IWorkbenchWindow window = editor.getSite().getWorkbenchWindow();
            IWorkbenchPage page = window.getActivePage();
            final IViewPart view = page.findView("org.eclipse.stardust.modeling.core.modelserver.ui.VcsFeedView"); //$NON-NLS-1$
                     
            Display.getDefault().syncExec(new Runnable()
            {
               public void run()
               {
                  if(view != null)
                  {
                     List<ModelHistoryEntry>[] history = new List[1];
                     try
                     {
                        history[0] = modelServer.getModelHistory(model, monitor);
                        ((VcsFeedView) view).replaceData(history[0]);
                     }
                     catch (RMSException e)
                     {
                     }
                  }
               }
            });                     
         }
         monitor.worked(1);         
      }
      catch (Exception e)
      {
         jobInterrupted = true;
      }    
      finally
      {
         modelServer.refreshLocal(monitor);
      }
      modelServer.setLastRefresh(new Long(System.currentTimeMillis()));
      
      monitor.done();
      callSchedule();               
      return Status.OK_STATUS;
   }
}