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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.ShareUtils;
import org.eclipse.stardust.model.xpdl.carnot.merge.UUIDUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;


public class LockUtil
{
   private static Map<WorkflowModelEditor, LockUtil> instances;
   
   private WorkflowModelEditor editor;
   
   private ModelType localModel;
   private ModelType remoteModelHead;
   
   private Map<String, EObject> localMap;
   private Map<String, EObject> remoteMapHead;
   
   private List<EObject> newElements = new ArrayList<EObject>();
   private List<EObject> removedElements = new ArrayList<EObject>();   
   private List<EObject> changedElements = new ArrayList<EObject>();
   private List<EObject> allElements = new ArrayList<EObject>();

   private WorkflowModelManager manager;
   private long localModelTimeStamp = 0;
      
   public static LockUtil getLockUtil(WorkflowModelEditor editor)
   {
      LockUtil instance = null;
      if(instances == null)
      {
         instances = new HashMap<WorkflowModelEditor, LockUtil>();
      }
      
      instance = instances.get(editor);
      if(instance == null)
      {
         instance = new LockUtil(editor);
         instances.put(editor, instance);
      }
      
      return instance;
   }
   
   private LockUtil(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }      
   
   public void analyze(IProgressMonitor monitor) throws RMSException
   {      
      // monitor.beginTask("Preparing for lock.", 3);
      
      monitor.subTask(Diagram_Messages.TASK_READING_LOCAL_MD);
      ModelServer modelServer = editor.getModelServer();
      ModelType model = modelServer.getModel();
      Resource resource = model.eResource();
      
      getLocalModel(resource);

      monitor.subTask(Diagram_Messages.TASK_READING_REMOTE_MD);
      try
      {
         remoteModelHead = modelServer.getRemoteModel(true);
      }
      catch (IOException e)
      {
         throw new RMSException("...", RMSException.CONNECTION_ERROR); //$NON-NLS-1$
      }      
      // possible Exception
      if(remoteModelHead == null)
      {
         throw new RMSException(Diagram_Messages.EXC_JOB_FAILED, RMSException.DEFAULT);
      }
      
      monitor.subTask(Diagram_Messages.TASK_ANALYZING_DIFFERENCES);
      localMap = ModelServerUtils.createUuidToElementMap(localModel);
      remoteMapHead = ModelServerUtils.createUuidToElementMap(remoteModelHead);
      
      Map<String, EObject> remoteMapClone = new HashMap<String, EObject>(remoteMapHead);
//      Map<String, EObject> localMapClone = new HashMap<String, EObject>(localMap);
      
      // remove
      remoteMapClone.keySet().removeAll(localMap.keySet());
      List<EObject> tempRemovedElements = new ArrayList<EObject>(remoteMapClone.values());
      for (EObject element : tempRemovedElements)
      {
         // only if we have a lock file, we have removed that element 
         if(LockFileUtils.hasLockFile(element, modelServer.getModel()))
         {
            removedElements.add(element);
         }         
      }      
      newElements = ModelServerUtils.getElementsWithoutUuid(localModel);
      
      if (!removedElements.isEmpty())
      {
         allElements.addAll(removedElements);
         localMap.values().removeAll(removedElements);
      }
      if (!newElements.isEmpty())
      {
         allElements.addAll(newElements);
         localMap.values().removeAll(newElements);     
      }
            
      Iterator<Entry<String, EObject>> it = localMap.entrySet().iterator(); 
      while (it.hasNext()) 
      {
         Map.Entry<String, EObject> entry = it.next();
         EObject element = entry.getValue();
         if (ShareUtils.isLockableElement(element))
         {
            if(LockFileUtils.hasLockFile(element, modelServer.getModel()))
            {               
               boolean changed = ModelServerUtils.isChanged(element, 
                                    ModelServerUtils.findByUUID(remoteModelHead, 
                                          UUIDUtils.getUUID(element)));  
               // changed
               if(changed)
               {
                  changedElements.add(element);      
               }               
            }
         }         
      }
   }

   private void getLocalModel(Resource resource) throws RMSException
   {
      URI uri = resource.getURI();
      int segmentCount = uri.segmentCount();
      String fileName = "platform:"; //$NON-NLS-1$
      for(int i = 1; i < segmentCount; i++)
      {
         fileName += "/" + uri.segment(i); //$NON-NLS-1$
      }
      
      java.net.URI uri_ = java.net.URI.create(fileName);
      File file = new File(uri_.getPath());
      if (!file.isAbsolute())
      {
         file = new File(Platform.getLocation().toFile(), file.toString());
      }
      
      if(file.exists() && localModelTimeStamp != 0)
      {
         if(file.lastModified() > localModelTimeStamp)
         {
            manager = null;            
         }
      }  
      else
      {
         manager = null;
      }
      
      if(manager == null)
      {
         if(file.exists())
         {
            localModelTimeStamp = file.lastModified();            
         }
         
         manager = new WorkflowModelManager();
         try
         {
            manager.load(resource.getURI());
         }
         catch (IOException e)
         {
            throw new RMSException("...", RMSException.DEFAULT);          //$NON-NLS-1$
         }
         localModel = manager.getModel();
      }
   }
    
   public static String getMessageLockedAlready(Map<EObject, String> failed)
   {
      String message = "The following model elements could not be locked:\n"; //$NON-NLS-1$;
      Iterator<Entry<EObject, String>> it = failed.entrySet().iterator(); 
      while (it.hasNext()) 
      {
         Map.Entry<EObject, String> entry = it.next();
         EObject element = entry.getKey();
         String user = entry.getValue();
            
         message += GenericUtils.getElementId(element) + " (locked by " + user + ")\n"; //$NON-NLS-1$ //$NON-NLS-2$;
      }
      return message;
   }   
      
   public String checkUpdateNeeded(List<EObject> elements)
   {
      String updateElements = ""; //$NON-NLS-1$;
      for (EObject element : elements)
      {
         EObject sameElement = CopyPasteUtil.getSameElement(element, localModel);
         if (sameElement != null)
         {            
            if (hasChanged(sameElement))
            {
               updateElements += GenericUtils.getElementId(element) + "\n"; //$NON-NLS-1$;
            }               
         }   
      }
      return updateElements;
   }   
   
   public boolean hasChanged(EObject element)
   {      
      return changedElements.contains(element);
   }
}