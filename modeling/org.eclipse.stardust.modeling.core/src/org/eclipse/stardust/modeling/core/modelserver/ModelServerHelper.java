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
package org.eclipse.stardust.modeling.core.modelserver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.ShareUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.modelserver.jobs.JobUtils;
import org.eclipse.stardust.modeling.core.modelserver.jobs.StateCache;
import org.eclipse.stardust.modeling.repository.common.ExtendedModelManager;


public class ModelServerHelper
{
   protected EObject cachedModelElement;
   
   protected StateCache stateCache;   
   
   protected int modelHistoryEntries = 0;
   
   protected int statusFileValue = 0;
   // if we just called share, we do not need to make a remote request
   // so expensive calls are not necessary, as we now the state
   protected boolean shareJob = false;   
   protected Boolean createLockFilesFinished = Boolean.FALSE;
   
   protected boolean enableRefreshStatusJob = true;   
   
   protected Long lastRefresh = null;      
   
   protected JobUtils jobUtils = null;

   protected ModelType model;
   protected ModelType remoteModel;   
   
   protected IProject project;
   protected IFolder modelLockFolder;
   protected IFolder modelCacheFolder;   

   // in case of connection error, we save that the connection failed
   // so we show the next message that we have connection problems not before this time frame
   protected long retryConnection;
   protected boolean retry = true;
   protected long lastRequest = 0;      
   private long remoteModelTimeStamp = 0;      
   
   public void setCachedModelElement(EObject cachedModelElement)
   {
      this.cachedModelElement = cachedModelElement;
   }   
   
   public ModelType getModel()
   {
      return model;
   }   

   public JobUtils getJobUtils()
   {
      return jobUtils;
   }

   public boolean isEnableRefreshStatusJob()
   {
      return enableRefreshStatusJob;
   }      

   public String getModelName()
   {
      return " (" + model.getName() + ")"; //$NON-NLS-1$; //$NON-NLS-2$;
   }   
      
   private ModelType getRemoteModel() throws RMSException, IOException
   {
      IFile modelFile = ModelServerUtils.getModelFile(model);
      Vcs vcs = ModelServerUtils.getVcs(getProject());         
      InputStream in = vcs.getHeadRevision(modelFile);
      if (in != null)
      {
         ExtendedModelManager emm = new ExtendedModelManager();
         emm.load(URI.createURI("vcs:/resource/" + modelFile.getName()), in); //$NON-NLS-1$;
         return emm.getModel();
      }
      // TODO: (fh) should never reach this line
      return null;
   }
   
   public ModelType getRemoteModel(boolean useCache) throws RMSException, IOException
   {
      if(!useCache)
      {
         return getRemoteModel();
      }
      
      IFile modelFile = ModelServerUtils.getModelFile(model);
      Vcs vcs = ModelServerUtils.getVcs(getProject());
      
      IFile file = getCacheFile(modelFile, vcs);
      if (!file.exists())
      {
         cleanCache();         
         InputStream in = vcs.getHeadRevision(modelFile);
         if (in != null)
         {
            return createRemoteModel(modelFile, file, in);
         }         
      }
      else
      {
         return getCachedModel(file);
      }      
      // TODO: (fh) should never reach this line
      return null;
   }

   private void cleanCache()
   {
      IFolder modelCacheFolder = getModelCacheFolder();
      try
      {
         IResource[] members = modelCacheFolder.members();
         if (null != members)
         {
            for (int i = 0; i < members.length; i++ )
            {
               if ((members[i] instanceof IFile) && members[i].exists())
               {
                  ((IFile) members[i]).delete(true, null);
               }
            }
         }            
      }
      catch (CoreException e)
      {
      }
      remoteModel = null;
   }

   protected IFile getCacheFile(IFile modelFile, Vcs vcs)
   {
      VcsStatus status = vcs.getStatus(modelFile);      
      Long headRevision = status.getHeadRevision();
      IFolder folder = getModelCacheFolder();
      String fileName = headRevision.toString() + "_" + modelFile.getName(); //$NON-NLS-1$
      IFile file = folder.getFile(fileName);
      return file;
   }

   private synchronized ModelType getCachedModel(IFile cacheFile) throws IOException
   {      
      String fileName = "platform:" + cacheFile.getFullPath().toString(); //$NON-NLS-1$      
      java.net.URI uri_ = java.net.URI.create(fileName);
      File file = new File(uri_.getPath());
      if (!file.isAbsolute())
      {
         file = new File(Platform.getLocation().toFile(), file.toString());
      }
    
      if(file.exists() && remoteModelTimeStamp != 0)
      {
         if(file.lastModified() == remoteModelTimeStamp)
         {
            if(remoteModel != null)
            {
               return remoteModel;
            }            
         }
      }      
      
      if(file.exists())
      {
         remoteModelTimeStamp = file.lastModified();         
      }
      
      ExtendedModelManager emm = new ExtendedModelManager();
      emm.load(URI.createPlatformResourceURI(cacheFile.getFullPath().toString(), false));      
      remoteModel = emm.getModel();
      
      return remoteModel;
   }     
   
   private synchronized ModelType createRemoteModel(IFile modelFile, IFile cacheFile, InputStream in) throws IOException
   {
      ExtendedModelManager emm = new ExtendedModelManager();
      emm.load(URI.createPlatformResourceURI(cacheFile.getFullPath().toString(), false), in); //$NON-NLS-1$;
      emm.save(URI.createPlatformResourceURI(cacheFile.getFullPath().toString(), false));      
      remoteModel = emm.getModel();
      
      return remoteModel;
   }  

   public boolean isLockedAll()
   {
      return stateCache.isLockedAll();
   }
   
   public boolean shareJob()
   {
      return shareJob;
   }

   public void cleanShareJob()
   {
      shareJob = false;
   }   
   
   public Long getLastRefresh()
   {
      return lastRefresh;
   }

   public void setLastRefresh(Long value)
   {
      lastRefresh = value;
   }
   
   public Boolean isCreateLockFilesFinished()
   {
      return createLockFilesFinished;
   }

   public void setCreateLockFilesFinished(Boolean finished)
   {
      createLockFilesFinished = finished;
   }
      
   public void setRetryConnection(long retryConnection)
   {
      this.retryConnection = retryConnection;
   }

   // needed if we have cases of connection errors
   public void setRetry(boolean retry)
   {
      this.retry = retry;
      if (retry)
      {
         lastRequest = retryConnection;
         // TODO: (fh) remove ???
      }
   }   

   public IProject getProject()
   {
      if (project == null)
      {
         project = ShareUtils.getProject(model.eResource());
      }
      return project;
   }   
   
   public IFolder getModelCacheFolder()
   {
      if(modelCacheFolder == null)
      {
         IFolder folder = getModelLockFolder();
         IFolder cache = folder.getFolder("CACHE"); //$NON-NLS-1$
         if(!cache.exists())
         {
            try
            {
               cache.create(true, true, null);
               modelCacheFolder = cache;
            }
            catch (CoreException e)
            {
            }
         }
         else
         {
            modelCacheFolder = cache;
         }
      }
      
      return modelCacheFolder;
   }
   
   public IFolder getModelLockFolder()
   {
      if (modelLockFolder == null)
      {
         modelLockFolder = ShareUtils.getLockFolder(model);
      }      
      return modelLockFolder;
   }   

   public boolean isProjectShared()
   {
      try
      {
         return ModelServerUtils.getVcs2(getProject()) != null;
      }
      catch (Exception e)
      {
         return false;
      }      
   }   

   protected synchronized boolean isShared(IFile lockFile)
   {
      try
      {
         Vcs vcs = ModelServerUtils.getVcs(getProject());
         return vcs.isShared(lockFile, false);
      }
      catch (RMSException e)
      {
         if (e.getType() == RMSException.CONNECTION_ERROR)
         {
            ModelServerUtils.showMessageBox(e.getMessage());
         }
      }
      return false;
   }

   public boolean isLockedByOther(EObject eObject)
   {
      VcsStatus lockInfo = getLockInfo(eObject);
      return lockInfo.isLocked() && !lockInfo.isLockedByCurrentUser();
   }   

   public boolean isLockableElement(IAdaptable adaptable)
   {
      return ShareUtils.isLockableElement(ModelUtils.getEObject(adaptable));
   }
   
   public synchronized VcsStatus getLockInfo(EObject eObject)
   {
      IFile file = LockFileUtils.getLockFile(eObject);
      if (file == null || !file.exists())
      {
         return new VcsStatus(VcsStatus.STATUS_NOT_SHARED, false, null, null, null, null);         
      }
      
      String data = LockFileUtils.readLockFile(file);
      // NPE
      if (data == null)
      {
         return new VcsStatus(VcsStatus.STATUS_NOT_SHARED, false, null, null, null, null);         
      }     
      
      int status = 0;
      try
      {
         status = Integer.parseInt(data);
      }
      catch (NumberFormatException e)
      {
         if(data.equals(ModelServerUtils.getUser(file)))
         {
            status = 1;
         }
         else
         {
            status = 2;
         }
      }
      boolean isLockedByCurrentUser = false;
      String remoteLockOwner = null;
      if(status == 1)
      {
         isLockedByCurrentUser = true;
      }
      else if(status == 2)
      {
         remoteLockOwner = data;         
         try 
         {
            VcsStatus vcsStatus = ModelServerUtils.getVcs(getProject()).getStatus(file);
            remoteLockOwner = vcsStatus.getLastCommitAuthor();
         }
         catch (Exception e)
         {
            return new VcsStatus(VcsStatus.STATUS_NOT_SHARED, false, null, null, null, null);
         }
      }      
      return new VcsStatus(VcsStatus.STATUS_SHARED, isLockedByCurrentUser, remoteLockOwner, null, null, null);
   }

   public List<ModelHistoryEntry> getModelHistory(ModelType model, IProgressMonitor monitor) throws RMSException
   {
      List<ModelHistoryEntry> folderEntries = ModelServerUtils.getVcs(getProject()).getHistory(getModelLockFolder(), monitor);      
      IFile modelFile = ModelServerUtils.getModelFile(model);  
      List<ModelHistoryEntry> modelEntries = ModelServerUtils.getVcs(getProject()).getHistory(modelFile, monitor);      
      List<ModelHistoryEntry> mergedEntries = new ArrayList<ModelHistoryEntry>();
      mergedEntries.addAll(folderEntries);
      
      for(ModelHistoryEntry modelEntry : modelEntries)
      {
         boolean found = false;         
         for(ModelHistoryEntry folderEntry : folderEntries)
         {
            if(folderEntry.getRevision().equals(modelEntry.getRevision())
                  && folderEntry.getTimestamp().equals(modelEntry.getTimestamp())
                  && folderEntry.getComment().equals(modelEntry.getComment()))
            {
               found = true;
               break;
            }
         }
         if(!found)
         {
            mergedEntries.add(modelEntry);
         }         
      }      
      return mergedEntries;
   } 
}