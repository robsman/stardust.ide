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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.merge.ShareUtils;
import org.eclipse.stardust.model.xpdl.carnot.merge.UUIDUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.model.xpdl.util.IConnection;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.AbstractMerger;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.editors.cap.OutlineMerger;
import org.eclipse.stardust.modeling.core.editors.cap.StoreObject;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionInfo;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionState;
import org.eclipse.stardust.modeling.core.modelserver.jobs.JobUtils;
import org.eclipse.stardust.modeling.core.modelserver.jobs.StateCache;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class ModelServer extends ModelServerHelper
{
   public static final String SHARE = "SHARE"; //$NON-NLS-1$;
   public static final String UNSHARE = "UNSHARE"; //$NON-NLS-1$;
   
   public static final String COMMIT = "COMMIT"; //$NON-NLS-1$;
   public static final String REVERT = "REVERT"; //$NON-NLS-1$;
   
   public static final String LOCK = "LOCK"; //$NON-NLS-1$;
   public static final String LOCK_ALL = "LOCK_ALL"; //$NON-NLS-1$;
   public static final String UNLOCK = "UNLOCK"; //$NON-NLS-1$;
   public static final String UNLOCK_ALL = "UNLOCK_ALL"; //$NON-NLS-1$;
      
   private boolean isModelShared = false;
   
   public ModelServer(ModelType model)
   {
      stateCache = new StateCache(this);
      
      this.model = model;     
      retryConnection = 1000 * Long.parseLong(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY_RATE));
      retry = PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY);
      // trigger the job
      isModelShared();
   }

   public boolean isLockedByUser()
   {
      if(!isModelShared)
      {
         return true;
      }
      EObject modelElement = cachedModelElement;
      
      ProcessDefinitionType process = ModelUtils.findContainingProcess(cachedModelElement);
      if(process != null)
      {
         modelElement = process;
      }
            
      if(ShareUtils.isLockableElement(modelElement))
      {
         VcsStatus lockInfo = getLockInfo(modelElement);
         if(!lockInfo.isLocked())
         {
            return false;
         }         
         if(lockInfo.isLocked() && lockInfo.isLockedByCurrentUser())
         {
            return true;
         }
         if(lockInfo.getStatus() == VcsStatus.STATUS_NOT_SHARED)
         {
            return true;
         }
      }
      return false;
   }   
   
   public void dispose()
   {
      if (jobUtils != null)
      {
         jobUtils.dispose();
      }
      stateCache = null;
   }      
   
   public StateCache getStateCache()
   {
      return stateCache;
   }
   
   public boolean isAdaptableShared(IAdaptable adaptable)
   {
      EObject eObject = ModelUtils.getEObject(adaptable);
      return isElementShared(eObject);
   }
   
   public boolean isElementShared(EObject eObject)
   {
      if (eObject == null)
      {
         return false;
      }      
      if (!isModelShared())
      {
         return false;
      }
      IFile lockFile = LockFileUtils.getLockFile(eObject);
      if (lockFile == null || !lockFile.exists())
      {
         return false;
      }
      if(eObject instanceof ModelType)
      {
         return isModelShared();
      }          
      return isShared(lockFile);
   }
   
   public boolean isModelShared()
   {
      if (!isProjectShared())
      {
         return false;
      }
      
      IFolder modelLockFolder = getModelLockFolder();
      isModelShared = modelLockFolder != null 
         && modelLockFolder.exists()
         && !StringUtils.isEmpty(UUIDUtils.getUUID(model));
      
      if (isModelShared)
      {
         IFile lockFile = LockFileUtils.getLockFile(model);
         isModelShared = isShared(lockFile); 
         
         if(jobUtils == null)
         {
            jobUtils = new JobUtils(this);
         }
         if (isModelShared)
         {
            jobUtils.update();                           
         }
      }      
      return isModelShared;
   }
   
   //////////
   
   public void unlockAll(IProgressMonitor monitor) throws RMSException
   {      
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
      if (editor == null)
      {
         throw new RMSException(Diagram_Messages.EXC_CANNOT_FIND_ASSOCIATED_WORKFLOW_MD_EDITOR, RMSException.DEFAULT);
      }      
      
      final List<EObject> lockedElements = getStateCache().getAllLockedElements(); 
      List<IFile> lockfiles = new ArrayList<IFile>();

      if (lockedElements.size() == 0)
      {
         return;
      }
      
      // remove objects from cache
      List<EObject> removeFromCache = new ArrayList<EObject>();         
      for (EObject eObject : lockedElements)
      {
         EObject removeElement = CopyPasteUtil.getSameModelElement(eObject, getModel(), null); 
         removeFromCache.add(removeElement);
      }      
      
      for (EObject element : lockedElements)
      {
         IFile lockFile = LockFileUtils.getLockFile(element);
         lockfiles.add(lockFile);
      } 

      if (monitor != null)
      {
         monitor.beginTask(Diagram_Messages.TASK_UNLOCK_ALL, lockedElements.size() * 8 / 3);
      }
      final ModelType remoteModel;
      
      // we need 2 try/catch blocks otherwise the model may be corrupt if vcs.forceUnlock failed
      try
      {
         if (monitor != null)
         {
            monitor.subTask(Diagram_Messages.TASK_READING_REMONTE_MD);
         }
         remoteModel = getRemoteModel(true);
         if (monitor != null)
         {
            monitor.worked(lockedElements.size() * 2 / 3);
         }

         Display.getDefault().syncExec(new Runnable()
         {
            public void run()
            {
               ModelServerUtils.mergeElements3(lockedElements, remoteModel, model);
            }
         });
      }
      catch (IOException e)
      {
         refreshLocal(monitor);
         if (editor != null)
         {
            editor.doSave(monitor);            
            editor.getEditDomain().getCommandStack().flush();
         }
         if (monitor != null)
         {
            monitor.done();
         }
         throw new RMSException(MessageFormat.format(Diagram_Messages.EXC_UNABLE_TO_READ_REMONTE_MD, new Object[]{ e.getMessage()}), RMSException.CONNECTION_ERROR);
      }      

      try
      {
         // unlock elements...
         Vcs vcs = ModelServerUtils.getVcs(getProject());
         for (EObject eObject : lockedElements)
         {
            IFile lockFile = LockFileUtils.getLockFile(eObject);
            if (lockFile != null)
            {
               try
               {
                  vcs.forceUnlock(new IFile[] {lockFile}, monitor);
                  LockFileUtils.setLockFile(lockFile, "0"); //$NON-NLS-1$
                  vcs.commit(new IFile[] {lockFile}, UNLOCK_ALL, false, monitor);
                  stateCache.setState(eObject, CollisionInfo.DEFAULT);
               }
               catch (Exception e)
               {
                  stateCache.setState(eObject, CollisionInfo.DEFAULT);
                  // do nothing, unlocked already meanwhile by other party
               }
            }
         }
      }
      catch (Throwable t)
      {
         // t.printStackTrace();
      }
      finally
      {
         for (EObject eObject : removeFromCache)
         {
            stateCache.remove(eObject);
         }         
         
         refreshLocal(monitor);
         if (editor != null)
         {
            editor.doSave(monitor);            
            editor.getEditDomain().getCommandStack().flush();
         }
         if (monitor != null)
         {
            monitor.done();
         }
      }
   }   
   
   public void lockAll(IProgressMonitor monitor) throws RMSException
   {
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
      if (editor == null)
      {
         throw new RMSException(Diagram_Messages.EXC_CANNOT_FIND_ASSOCIATED_WORKFLOW_MD_EDITOR, RMSException.DEFAULT);
      }
      
      final List<EObject> elements = getStateCache().getElementsToLock(); 
      List<IFile> lockfiles = new ArrayList<IFile>();

      if (elements.size() == 0)
      {
         return;
      }
      
      LockUtil util = new LockUtil(editor);
      util.analyze(monitor);
      String updateElements = util.checkUpdateNeeded(elements);
      if(!StringUtils.isEmpty(updateElements))
      {
    	  throw new RMSException(MessageFormat.format(Diagram_Messages.EXC_UPDATE_NEEDED_FOR +"\n", new Object[]{updateElements}), RMSException.DEFAULT); //$NON-NLS-1$
      }
      
      for (EObject element : elements)
      {
         IFile lockFile = LockFileUtils.getLockFile(element);
         lockfiles.add(lockFile);
      }             
      
      if (monitor != null)
      {
         monitor.beginTask(Diagram_Messages.TASK_LOCK_ALL_ELEMENTS, elements.size() + 2);
      }
      Vcs vcs = ModelServerUtils.getVcs(getProject());
      
      Map<EObject, String> failed = new HashMap<EObject, String>();
      for (EObject eObject : elements)
      {
         IFile file = LockFileUtils.getLockFile(eObject);
         if (file == null)
         {
            // TODO: (fh) should not happen, throw RMSException ?
            continue;
         }
         try
         {
            if (monitor != null)
            {
               monitor.subTask(getLabel(eObject));
            }
            lock(monitor, vcs, file, true);
            LockFileUtils.setLockFile(file, ModelServerUtils.getUser(file));
            if (monitor != null)
            {
               monitor.worked(1);
            }
            commitLockFiles(new IResource[] {file}, true, LOCK_ALL);
            stateCache.updateState(eObject, null);
            if (monitor != null)
            {
               monitor.worked(1);
            }
         }         
         catch (RMSException e)
         {
            VcsStatus vcsStatus = vcs.getStatus(file);
            // makes no sense if we have not updated the lock file
            // stateCache.updateState(eObject, vcsStatus);
            if (vcsStatus.isLocked() && !vcsStatus.isLockedByCurrentUser())
            {
               String lockOwner = vcsStatus.getLockOwner();
               CollisionInfo info = CollisionInfo.create(CollisionState.LOCKED_BY_OTHER, 
                     lockOwner == null ? vcsStatus.getLastCommitAuthor() : lockOwner);               
               
               stateCache.setState(eObject, info);
               failed.put(eObject, vcsStatus.getLockOwner());               
               // throw new RMSException("Already locked by (" + vcsStatus.getLockOwner() + ").", RMSException.ALREADY_LOCKED);
            }   
            else if (vcsStatus.isLocked() && vcsStatus.isLockedByCurrentUser())
            {
               stateCache.setState(eObject, CollisionInfo.LOCKED_BY_USER);
            }   
            else
            {
               throw e;
            }
         }
         finally
         {
            refreshLocal(monitor);            
         }
      }
      // show info to user
      if(!failed.isEmpty())
      {
         final String message = LockUtil.getMessageLockedAlready(failed);
         Display.getDefault().asyncExec(new Runnable()
         {
            public void run()
            {         
               ModelServerUtils.showMessageBox(message);         
            }
         });
      }
      
      if (monitor != null)
      {
         monitor.done();
      }
   }   

   public void updateLockFile(IFile file, IProgressMonitor monitor) throws RMSException
   {    
      Vcs vcs = ModelServerUtils.getVcs(getProject());         
      vcs.update(new IFile[] {file}, monitor);  
   }   
   
   public void revert(final RevertUtil revertUtil, IProgressMonitor monitor) throws RMSException
   {
      if (revertUtil.getSelectedElements().size() == 0)
      {
         return;
      }
      if (monitor != null)
      {
         monitor.beginTask(Diagram_Messages.TASK_REVERT_CHANGES, revertUtil.getSelectedElements().size());
      }
      // remove objects from cache
      List<EObject> removeFromCache = new ArrayList<EObject>();         
      
      try
      {
         // to prevent that the job writes into lock files while commit
         enableRefreshStatusJob = false;
         jobUtils.sleep();
                  
         for (EObject eObject : revertUtil.getSelectedElements())
         {
            EObject removeElement = CopyPasteUtil.getSameModelElement(eObject, getModel(), null); 
            if(removeElement != null)
            {
               removeFromCache.add(removeElement);
            }
         }
         
         // add deleted elements
         if(!revertUtil.getSelectedElementsDeleted().isEmpty())
         {
            Display.getDefault().asyncExec(new Runnable()
            {
               public void run()
               {      
                  StoreObject storage = new StoreObject();
                  storage.setCopySymbols(false);
                  storage.setOriginalModelCopy(revertUtil.getRemoteModel());
                  storage.setSourceModel(revertUtil.getRemoteModel());
                  storage.setSameModel(true);    
                  storage.setCollision(StoreObject.UPDATE);         
                  AbstractMerger util = new OutlineMerger(model, revertUtil.getSelectedElementsDeleted(), storage);      
                  util.merge();         
               }
            });
         }
         
         if(!revertUtil.getSelectedElementsOther().isEmpty())
         {                 
            Display.getDefault().syncExec(new Runnable()
            {
               public void run()
               {
                  ModelServerUtils.mergeElements3(revertUtil.getSelectedElementsOther(), revertUtil.getRemoteModel(), model);
               }
            });
         }

         // unlock elements...
         IFolder lockFolder = ShareUtils.getLockFolder(model);
         Vcs vcs = ModelServerUtils.getVcs(getProject());
         for (EObject eObject : revertUtil.getSelectedElements())
         {
            // this way because element can be new
            IFolder elementLockFolder = LockFileUtils.getLockFolder(eObject.eClass(), lockFolder);               
            IFile lockFile = LockFileUtils.getFile(elementLockFolder, eObject);               
            if (lockFile != null)
            {
               vcs.unlock(new IFile[] {lockFile}, monitor);
               LockFileUtils.setLockFile(lockFile, "0"); //$NON-NLS-1$
               vcs.commit(new IFile[] {lockFile}, REVERT, false, monitor);
               stateCache.setState(eObject, CollisionInfo.DEFAULT);
            }
         }
      }
      catch (Throwable t)
      {
         t.printStackTrace();
      }
      finally
      {
         for (EObject eObject : removeFromCache)
         {
            stateCache.remove(eObject);
         }         
         
         refreshLocal(monitor);
         WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
         if (editor != null)
         {
            editor.doSave(monitor);            
            editor.getEditDomain().getCommandStack().flush();
         }
         if (monitor != null)
         {
            monitor.done();
         }
         enableRefreshStatusJob = true;
         jobUtils.runRefreshJob();         
      }
   }

   public void update(final UpdateUtil updateUtil, IProgressMonitor monitor) throws RMSException
   {
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
      if (editor == null)
      {
         throw new RMSException(Diagram_Messages.EXC_CANNOT_FIND_ASSOCIATED_WORKFLOW_MD_EDITOR, RMSException.DEFAULT);
      }
            
      final ModelType remoteModelHead = updateUtil.getRemoteModel();
      // remove objects from cache
      List<EObject> removeFromCache = new ArrayList<EObject>();         
      
      try
      {         
         // to prevent that the job writes into lock files while commit
         enableRefreshStatusJob = false;
         jobUtils.sleep();
         
         Vcs vcs = ModelServerUtils.getVcs(getProject());                  
         if(!updateUtil.getSelectedElementsNew().isEmpty())
         {        
            IFolder lockFolder = ShareUtils.getLockFolder(model);
            
            List<IFile> lockfiles = new ArrayList<IFile>();
            for (EObject element : updateUtil.getSelectedElementsNew())
            {
               // this way because element is new
               IFolder elementLockFolder = LockFileUtils.getLockFolder(element.eClass(), lockFolder);               
               IFile lockFile = LockFileUtils.getFile(elementLockFolder, element);               
               lockfiles.add(lockFile);               
            }
            IResource[] files = lockfiles.toArray(new IResource[lockfiles.size()]);
            vcs.update(files, monitor);
         }
         
         // added
         if(!updateUtil.getSelectedElementsNew().isEmpty())
         {
            Display.getDefault().asyncExec(new Runnable()
            {
               public void run()
               {            
                  StoreObject storage = new StoreObject();
                  storage.setCopySymbols(false);
                  storage.setOriginalModelCopy(remoteModelHead);
                  storage.setSourceModel(remoteModelHead);
                  storage.setSameModel(true);    
                  storage.setCollision(StoreObject.UPDATE);         
                  AbstractMerger util = new OutlineMerger(model, updateUtil.getSelectedElementsNew(), storage);      
                  util.merge();         
               }
            });
         }
         
         for (EObject eObject : updateUtil.getSelectedElementsRemoved())
         {
            EObject removeElement = CopyPasteUtil.getSameModelElement(eObject, getModel(), null); 
            removeFromCache.add(removeElement);
         }         
         for (EObject eObject : updateUtil.getSelectedElementsChanged())
         {
            EObject removeElement = CopyPasteUtil.getSameModelElement(eObject, getModel(), null); 
            removeFromCache.add(removeElement);
         }         
         
         Display.getDefault().syncExec(new Runnable()
         {
            public void run()
            {
               ModelServerUtils.removeElements(updateUtil.getSelectedElementsRemoved(), model, getProject(), null);
            }
         });         
                  
         Display.getDefault().syncExec(new Runnable()
         {
            public void run()
            {
               ModelServerUtils.mergeElements3(updateUtil.getSelectedElementsChanged(), remoteModelHead, model);
            }
         });                      
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }            
      finally
      {         
         for (EObject eObject : removeFromCache)
         {
            stateCache.remove(eObject);
         }         
         
         refreshLocal(monitor);
         editor.getEditDomain().getCommandStack().flush();
         editor.doSave(monitor);
         enableRefreshStatusJob = true;
         jobUtils.runRefreshJob();
      }
   }   
      
   public void updateModelFile(IProgressMonitor monitor) throws RMSException
   {
      IFile modelFile = ModelServerUtils.getModelFile(model);
      Vcs vcs = ModelServerUtils.getVcs(getProject());                  
      vcs.update(new IResource[] {modelFile}, monitor);         
      vcs.resolve(new IResource[] {modelFile}, monitor);
   }         
   
   public void share() throws RMSException, IOException
   {
      final WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
      if (editor == null)
      {
         throw new RMSException(Diagram_Messages.EXC_CANNOT_FIND_ASSOCIATED_WORKFLOW_MD_EDITOR, RMSException.DEFAULT);
      }

      final IFile modelFile = ModelServerUtils.getModelFile(model);
      final Vcs vcs = ModelServerUtils.getVcs(getProject());                  
      
      boolean isShared = false;
      try
      {
         isShared = vcs.isShared(modelFile, false);
      }
      catch (RMSException e)
      {
         ModelServerUtils.showMessageBox(e.getMessage());
      }
      if (isShared)
      {
         ModelType remoteModel = getRemoteModel(false);
         if(!StringUtils.isEmpty(UUIDUtils.getUUID(remoteModel)))
         {
            throw new RMSException(Diagram_Messages.MSG_MODEL_ALREADY_SHARED, RMSException.ALREADY_SHARED);
         }
      }
      
      IRunnableWithProgress commitOperation = new IRunnableWithProgress()
      {
         public void run(IProgressMonitor monitor) throws InvocationTargetException,
               InterruptedException
         {
            try
            {
               monitor.beginTask(Diagram_Messages.LB_CREATE_CACHE_JOB + getModelName(), 100);                                 
               setCreateLockFilesFinished(null);
               try
               {
                  ModelServerUtils.createLockFiles(getProject(), getModel());
                  IFolder lockFolder = ShareUtils.getLockFolder(model);                     
                  vcs.add(new IResource[] {modelFile, lockFolder}, monitor);
                  vcs.commit(new IResource[] {modelFile, lockFolder}, SHARE + "/" + model.getId(), false, monitor); //$NON-NLS-1$;
                  IFile file = getCacheFile(modelFile, vcs);
                  modelFile.copy(file.getFullPath(), true, null);
               }
               catch (CoreException e)
               {
               }
               finally
               {
                  monitor.worked(100);
                  monitor.done();
               }
               setCreateLockFilesFinished(Boolean.TRUE);
            }
            catch (RMSException e)
            {
               throw new InvocationTargetException(e);
            }
            finally
            {
               refreshLocal(monitor);
            }
         }
      };
      try
      {
         new ProgressMonitorDialog(editor.getSite().getShell()).run(true, true, commitOperation);
      }
      catch (InvocationTargetException e)
      {
         Throwable t = e.getCause();
         ModelServerUtils.showMessageBox(t.getMessage());
         // TODO: update status
      }
      catch (InterruptedException e)
      {
         // TODO handle cancellation
         e.printStackTrace();
      }
   }   

   public void unshare(IProgressMonitor monitor) throws RMSException
   {      
      // to prevent that the job writes into lock files while commit
      enableRefreshStatusJob = false;
      jobUtils.cancel();
      jobUtils = null;

      Vcs vcs = ModelServerUtils.getVcs(project);      

      List<IFile> lockfiles = new ArrayList<IFile>();
      Map<String, EObject> localMap = ModelServerUtils.createUuidToElementMap(model);
      for (Map.Entry<String, EObject> entry : localMap.entrySet())
      {
         EObject element = entry.getValue();
         if(ShareUtils.isLockableElement(element) && LockFileUtils.getLockFile(element).exists())
         {
            VcsStatus lockInfo = getLockInfo(element);
            if (lockInfo.isLockedByCurrentUser())
            {
               IFile file = LockFileUtils.getLockFile(element);               
               lockfiles.add(file);
            }
         }
      }
      if(lockfiles.size() > 0)
      {
         IFile[] files = lockfiles.toArray(new IFile[lockfiles.size()]);
         vcs.unlock(files, monitor);
         commitLockFiles(files, false, UNSHARE + "/" + model.getId()); //$NON-NLS-1$;
      }
      
      // remove all collaboration extended attributes
      UUIDUtils.unsetUUID(model);
      ModelServerUtils.unsetUUIDs(model.getTypeDeclarations().getTypeDeclaration());
      ModelServerUtils.unsetUUIDs(model.getApplication());
      ModelServerUtils.unsetUUIDs(model.getData());         
      ModelServerUtils.unsetUUIDs(model.getRole());
      ModelServerUtils.unsetUUIDs(model.getConditionalPerformer());
      ModelServerUtils.unsetUUIDs(model.getOrganization());
      ModelServerUtils.unsetUUIDs(model.getProcessDefinition());
      ModelServerUtils.unsetUUIDs(model.getDiagram());
      ModelServerUtils.unsetUUIDs(model.getLinkType());

      // remove lock folder 
      try
      {
         getModelLockFolder().delete(true, monitor);
      }
      catch (CoreException e)
      {
         throw new RMSException(Diagram_Messages.EXC_CANNOT_NOT_DELETE_LOCK_FOLDER, RMSException.DEFAULT);
      }
      modelLockFolder = null;
      
      try
      {
         getProject().refreshLocal(IProject.DEPTH_INFINITE, monitor);
      }
      catch (CoreException e)
      {
      }
   }
   
   public void lock(EObject[] lockables, IProgressMonitor monitor) throws RMSException
   {
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
      if (editor == null)
      {
         throw new RMSException(Diagram_Messages.EXC_CANNOT_FIND_ASSOCIATED_WORKFLOW_MD_EDITOR, RMSException.DEFAULT);
      }
      
      if (monitor != null)
      {
         monitor.beginTask(Diagram_Messages.TASK_LOCK_ELEMENTS, lockables.length + 5);
      }
      LockUtil util = new LockUtil(editor);
      util.analyze(monitor);
      String updateElements = util.checkUpdateNeeded(Arrays.asList(lockables));
      if(!StringUtils.isEmpty(updateElements))
      {
    	  //Message_Format
    	  throw new RMSException(MessageFormat.format(Diagram_Messages.EXC_UPDATE_NEEDED_FOR, new Object[]{updateElements}), RMSException.DEFAULT);
      }
      
      Vcs vcs = ModelServerUtils.getVcs(getProject());
      for (int i = 0; i < lockables.length; i++)
      {
         EObject eObject = lockables[i];
         IFile file = LockFileUtils.getLockFile(eObject);
         if (file == null)
         {
            // TODO: (fh) should not happen, throw RMSException ?
            return;
         }
         try
         {
            if (monitor != null)
            {
               monitor.subTask(getLabel(eObject));
            }            
            lock(monitor, vcs, file, true);            
            LockFileUtils.setLockFile(file, ModelServerUtils.getUser(file));
            if (monitor != null)
            {
               monitor.worked(1);
            }
            commitLockFiles(new IResource[] {file}, true, LOCK);
            stateCache.updateState(eObject, null);
            if (monitor != null)
            {
               monitor.worked(1);
            }
         }
         catch (RMSException e)
         {
            VcsStatus vcsStatus = vcs.getStatus(file);
            // stateCache.updateState(eObject, vcsStatus);
            if (vcsStatus.isLocked() && !vcsStatus.isLockedByCurrentUser())
            {
               String lockOwner = vcsStatus.getLockOwner();
               CollisionInfo info = CollisionInfo.create(CollisionState.LOCKED_BY_OTHER, 
                     lockOwner == null ? vcsStatus.getLastCommitAuthor() : lockOwner);               
               
               stateCache.setState(eObject, info);
               throw new RMSException(MessageFormat.format(Diagram_Messages.EXC_ALREADY_LOCKED_BY, new Object[]{vcsStatus.getLockOwner()}), RMSException.ALREADY_LOCKED); //$NON-NLS-2$ //$NON-NLS-1$
            }         
            else
            {
               throw e;
            }
         }
         finally
         {
            refreshLocal(monitor);
         }
      }
      if (monitor != null)
      {
         monitor.done();
      }
   }

   // if lock failed because file is out of date, we update and try again
   private void lock(IProgressMonitor monitor, Vcs vcs, IFile file, boolean update) throws RMSException
   {
      try
      {
         vcs.lock(new IFile[] {file}, monitor);
      }
      catch (RMSException e)
      {
         if(e.getType() == RMSException.FILE_OUT_OF_DATE && update)
         {
            try
            {
               updateLockFile(file, monitor);
               lock(monitor, vcs, file, false);
            }
            catch (RMSException ex)
            {
               throw ex;               
            }
         }
         else
         {
            throw e;
         }
      }
   }
   
   /**
    * @param element
    */
   public void lock(IAdaptable adaptable, IProgressMonitor monitor) throws RMSException
   {
      EObject eObject = ModelUtils.getEObject(adaptable);
      IFile file = LockFileUtils.getLockFile(eObject);
      if (file == null)
      {
         // TODO: handle gracefully
         return;
      }
            
      lock(new EObject[] {eObject}, monitor);
   }
      
   public void commitLockFiles(IResource[] lockFiles, boolean keepLock, String comment) throws RMSException
   {
      Vcs vcs = ModelServerUtils.getVcs(getProject());                  
      try
      {
         vcs.commit(lockFiles, comment, keepLock, null);
      }
      catch (RMSException e)
      {
      }      
   }

   public String getLabel(EObject element)
   {
      String type = element.eClass().getName();
      String name = ""; //$NON-NLS-1$
      if (element instanceof IIdentifiableElement)
      {
         name = ((IIdentifiableElement) element).getName();
      }
      else if (element instanceof DiagramType)
      {
         name = ((DiagramType) element).getName();
      }
      if (element instanceof TypeDeclarationType)
      {
         name = ((TypeDeclarationType) element).getName();
      }
      return type + ": " + name; //$NON-NLS-1$
   }

   public Image getIcon(Image image, EObject eObjectModel)
   {
      StateCache stateCache = getStateCache();
      CollisionInfo info = stateCache.getState(eObjectModel);
      switch (info.getState())
      {
      case LOCKED_BY_USER : return ModelServerUtils.getIconWithOverlay(image, ModelServerUtils.imageLocked, IDecoration.BOTTOM_RIGHT);                              
      case LOCKED_BY_OTHER : return ModelServerUtils.getIconWithOverlay(image, ModelServerUtils.imageProtected, IDecoration.BOTTOM_RIGHT);                              
      case REMOVED : return ModelServerUtils.getIconWithOverlay(image, ModelServerUtils.imageDeleted, IDecoration.TOP_RIGHT);
      case ADDED : return ModelServerUtils.getIconWithOverlay(image, ModelServerUtils.imageAdded, IDecoration.TOP_RIGHT);                              
      }
      return ModelServerUtils.getIconWithOverlay(image, ModelServerUtils.imageVCS, IDecoration.BOTTOM_RIGHT);
   }

   public void commit(CommitUtil commitUtil, List<EObject> checkedElements, String commitString,
         boolean keepLocks, IProgressMonitor monitor) throws RMSException
   {
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
      if (editor == null)
      {
         throw new RMSException(Diagram_Messages.EXC_CANNOT_FIND_ASSOCIATED_WORKFLOW_MD_EDITOR, RMSException.DEFAULT);
      }
      
      try
      {            
         // to prevent that the job writes into lock files while commit
         enableRefreshStatusJob = false;
         jobUtils.sleep();
         
         editor.getEditorChangeTracker().setEnabled(false);
         try
         {
            // we need the latest version of the model file
            // to prevent an out of date error
            updateModelFile(monitor);
            doCommit(commitUtil, checkedElements, commitString, keepLocks, monitor);
            editor.getEditDomain().getCommandStack().flush();
         }
         catch (Exception ex)
         {
            ex.printStackTrace();
            // TODO: messages...
            // TODO: if not canceled, rethrow exception
         }
      }
      finally
      {
         refreshLocal(monitor);
         editor.doSave(monitor);
         editor.getEditorChangeTracker().setEnabled(true);
         enableRefreshStatusJob = true;
         jobUtils.runRefreshJob();         
      }
   }

   public void refreshLocal(IProgressMonitor monitor)
   {
      try
      {
         getModelLockFolder().refreshLocal(IProject.DEPTH_INFINITE, monitor);
      }
      catch (Exception ex)
      {
      }
   }

   private void doCommit(CommitUtil commitUtil, List<EObject> checkedElements, String commitString,
         boolean keepLocks, IProgressMonitor monitor) throws RMSException
   {
      boolean userCanceled = false;
      Map<EObject, EObject> changedCache = new HashMap<EObject, EObject>();            
      
      final IFile modelFile = ModelServerUtils.getModelFile(model);
      final Vcs vcs = ModelServerUtils.getVcs(getProject());                  
      IFolder lockFolder = ShareUtils.getLockFolder(model);                     
         
      ModelType remoteModel = commitUtil.getRemoteModel();

      // add
      List<EObject> selectedElementsNew = commitUtil.getSelectedElementsNew();
      if (!selectedElementsNew.isEmpty())
      {                     
         for (EObject newObject : selectedElementsNew)
         {
            UUIDUtils.setUUID(newObject);
         }
         StoreObject storage = new StoreObject();
         storage.setCopySymbols(false);
         storage.setOriginalModelCopy(commitUtil.getLocalModel());
         storage.setSourceModel(model);
         storage.setSameModel(true);
         storage.setCollision(StoreObject.COMMIT);
         final AbstractMerger util = new OutlineMerger(remoteModel, selectedElementsNew, storage);      
         
         Display.getDefault().syncExec(new Runnable()
         {
            public void run()
            {
               util.merge();
            }
         });            
         
         if (!util.modelChanged())
         {
            userCanceled = true;
         }
         else
         {
            changedCache = util.getChangedCache();
         }
      }
      
      if (userCanceled)
      {
         throw new RMSException(Diagram_Messages.EXC_USER_CANCELED_COMMIT, RMSException.CANCELED);
      }         
      
      if (!selectedElementsNew.isEmpty())
      {
         List<IFile> lockfiles = new ArrayList<IFile>();
         for (EObject element : selectedElementsNew)
         {
            EObject sameElement = CopyPasteUtil.getSameModelElement(element, model, null);            
            UUIDUtils.setUUID(sameElement, UUIDUtils.getUUID(element));
            IFile lockFile;
            try
            {
               lockFile = ModelServerUtils.createLockFileIfNotExists(lockFolder, sameElement, 0);
            }
            catch (Exception e)
            {
               throw new RMSException(e.getMessage(), RMSException.CREATE_LOCK_FILES_FAILED);
            }
            stateCache.setState(element, CollisionInfo.DEFAULT);
            lockfiles.add(lockFile);               
         }
         IResource[] files = lockfiles.toArray(new IResource[lockfiles.size()]);
         vcs.add(files, monitor);            
      }  
         
      // must be merged into remote model and then save the remote model as file and commit
      // store local model again as model file (better store before as tmpFile)      
      final IResource[] removeFiles = ModelServerUtils.removeElements(commitUtil.getSelectedElementsRemoved(),
            remoteModel, getProject(), model);
      if (!commitUtil.getSelectedElementsRemoved().isEmpty())
      {
         List<EObject> elements = commitUtil.getSelectedElementsRemoved();
         for (EObject element : elements)
         {
            // remove from cache
            stateCache.remove(element);
         }
      }         
      
      List<IFile> lockfiles = new ArrayList<IFile>();
      for (int i = 0; i < checkedElements.size(); i++) 
      {
         EObject element = (EObject) checkedElements.get(i);
         // this way because element is already removed (for elements deleted)
         IFolder elementLockFolder = LockFileUtils.getLockFolder(element.eClass(), lockFolder);               
         IFile lockFile = LockFileUtils.getFile(elementLockFolder, element);
         if (!keepLocks && !commitUtil.isRemovedElement(element))
         {
            LockFileUtils.setLockFile(lockFile, "0"); //$NON-NLS-1$
         }
         lockfiles.add(lockFile);
      }   

      ModelServerUtils.mergeElements2(commitUtil.getSelectedElementsChanged(), commitUtil.getLocalModel(), remoteModel);
         
      try
      {
          String commitText = ModelServerUtils.convertCommitString(commitString);
          WorkflowModelManager remoteModelManager = new WorkflowModelManager(remoteModel.eResource());
          remoteModelManager.save(URI.createPlatformResourceURI(modelFile.getFullPath().toString(), false));
          if(removeFiles.length > 0)
          {
             vcs.delete(removeFiles, monitor);                      
          }
          
          lockfiles.add(modelFile);
          IFile[] comitFiles = lockfiles.toArray(new IFile[lockfiles.size()]);          
          vcs.commit(comitFiles, COMMIT + "/" + commitText.trim(), keepLocks, monitor); //$NON-NLS-1$;
       }
       catch (IOException e)
       {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }
         
      if (!changedCache.isEmpty())
      {
         final Map<EObject, EObject> cache = changedCache;
         // change also name/id in current model
         // we could also replace the elements in current model with changed ones
         Display.getDefault().syncExec(new Runnable()
         {
            public void run()
            {
               CopyPasteUtil.replaceChangedNames(cache, model);
            }
         });                     
      }
      
      for (int i = 0; i < checkedElements.size(); i++) 
      {
         EObject element = (EObject) checkedElements.get(i);
         if (!commitUtil.isRemovedElement(element))
         {         
            EObject sameElement = CopyPasteUtil.getSameModelElement(element, model, null);
            getStateCache().updateState(sameElement, null);
         }
      }   
   }

   public boolean requireLock(EObject eObject)
   {
      if (isModelShared())
      {
         if(eObject instanceof IConnection)
         {
            eObject = getModel();
         }
         
         while (eObject != null && !ShareUtils.isLockableElement(eObject))
         {
            eObject = eObject.eContainer();
         }
         if (eObject != null)
         {
            CollisionInfo info = getStateCache().getState(eObject);
            CollisionState state = info.getState();
            return state != CollisionState.LOCKED_BY_USER && state != CollisionState.ADDED;
         }
      }
      return false;
   }

   public EObject getLockableElement(EObject eObject)
   {
      while (eObject != null && !ShareUtils.isLockableElement(eObject))
      {
         eObject = eObject.eContainer();
      }
      return eObject;
   }
}