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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.ShareUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionInfo;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionState;
import org.eclipse.stardust.modeling.core.modelserver.jobs.StateCache;
import org.eclipse.stardust.modeling.core.modelserver.ui.ModelContainer.Container;


public class RevertUtil
{
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;
    
   private WorkflowModelEditor editor;
   
   private ModelType localModel;
   private ModelType remoteModelHead;
   
   private Map<String, EObject> localMap;
   private Map<String, EObject> remoteMapHead;
   
   private List<EObject> lockedElements = new ArrayList<EObject>();
   // local deleted elements, not committed
   private List<EObject> deletedElements = new ArrayList<EObject>(); 
   private List<EObject> otherElements = new ArrayList<EObject>(); 
      
   // elements the user has selected to merge/commit
   private List<EObject> selectedElements;   

   public RevertUtil(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }      

   public WorkflowModelEditor getEditor()
   {
      return editor;
   }

   public List<EObject> getDeletedElements()
   {
      return deletedElements;
   }
   
   public void analyze(IProgressMonitor monitor) throws RMSException
   {      
      monitor.beginTask(Diagram_Messages.TASK_PREPARING_FOR_REVERT, 3);
      
      monitor.subTask(Diagram_Messages.TASK_READING_LOCAL_MD);
      ModelServer modelServer = editor.getModelServer();
      ModelType model = modelServer.getModel();
      StateCache stateCache = modelServer.getStateCache();
      
      WorkflowModelManager manager = new WorkflowModelManager();
      Resource resource = model.eResource();
      try
      {
         manager.load(resource.getURI());
      }
      catch (IOException e)
      {
         throw new RMSException("...", RMSException.DEFAULT);          //$NON-NLS-1$
      }
      localModel = manager.getModel();

      monitor.subTask(Diagram_Messages.TASK_READING_REMONTE_MD);
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
      Map<String, EObject> localMapClone = new HashMap<String, EObject>(localMap);
      
      // remove
      localMapClone.keySet().removeAll(remoteMapHead.keySet());
      
      // add
      remoteMapClone.keySet().removeAll(localMap.keySet());
      List<EObject> tempNewElements = new ArrayList<EObject>(remoteMapClone.values());
      for (EObject element : tempNewElements)
      {
         // only if we have no lock file, it is a new element              
         if (LockFileUtils.hasLockFile(element, modelServer.getModel()))
         {
            IFolder folder = LockFileUtils.getLockFolder(element, model);
            IFile lockFile = LockFileUtils.getFile(folder, element);   
            
            if(LockFileUtils.getInfo(null, lockFile) == CollisionInfo.LOCKED_BY_USER)
            {
               deletedElements.add(element);
               lockedElements.add(element);
            }            
         }
      }                
                  
      Iterator<Entry<String, EObject>> it = localMap.entrySet().iterator(); 
      while (it.hasNext()) 
      {
         Map.Entry entry = (Map.Entry) it.next();
         EObject element = (EObject) entry.getValue();
         if (ShareUtils.isLockableElement(element))
         {
            if (LockFileUtils.hasLockFile(element, modelServer.getModel()))
            {               
               EObject sameElement = CopyPasteUtil.getSameModelElement(element, model, null);
               CollisionInfo stateInfo = stateCache.getState(sameElement);
               if (stateInfo.getState() == CollisionState.LOCKED_BY_USER)
               {                  
                  otherElements.add(element);
                  lockedElements.add(element);
               }
            }
         }         
      }
   }
     
   public List<EObject> getSelectedElementsDeleted()
   {
      List<EObject> elements = new ArrayList<EObject>(deletedElements);
      elements.retainAll(selectedElements);
      return elements;
   }   
   
   public List<EObject> getSelectedElementsOther()
   {
      List<EObject> elements = new ArrayList<EObject>(otherElements);
      elements.retainAll(selectedElements);
            
      ModelServer modelServer = editor.getModelServer();
      ModelType model = modelServer.getModel();
      
      List<EObject> tmpElements = new ArrayList<EObject>();
      for (EObject element : elements)
      {
         EObject sameElement = CopyPasteUtil.getSameElement(element, model);
         tmpElements.add(sameElement);
      }
      return tmpElements;
   }
   
   public List<EObject> getLockedElements()
   {
      return lockedElements;
   }
      
   public ModelType getLocalModel()
   {
      return localModel;
   }   
      
   public List<EObject> getSelectedElements()
   {
      return selectedElements;
   }

   public void setUpdateContent(Container container)
   {
      if(lockedElements.contains(localModel))
      {
         container.setModel(localModel);
      }         
      
      List typeDeclarations = new ArrayList();
      typeDeclarations.addAll(localModel.getTypeDeclarations().getTypeDeclaration());
      typeDeclarations.retainAll(lockedElements);
      typeDeclarations.addAll(getDeletedElements(XpdlPackage.eINSTANCE.getTypeDeclarationType()));
      if(!typeDeclarations.isEmpty())
      {
         container.getTypeDeclarations().getContent().addAll(typeDeclarations);
      }
      List applications = new ArrayList();
      applications.addAll(localModel.getApplication());
      applications.retainAll(lockedElements);
      applications.addAll(getDeletedElements(PKG_CWM.getApplicationType()));
      if(!applications.isEmpty())
      {
         container.getApplications().getContent().addAll(applications);
      }
      List datas = new ArrayList();
      datas.addAll(localModel.getData());
      datas.retainAll(lockedElements);
      datas.addAll(getDeletedElements(PKG_CWM.getDataType()));
      if(!datas.isEmpty())
      {
         container.getDatas().getContent().addAll(datas);
      }
      List participants = new ArrayList();
      participants.addAll(localModel.getRole());
      participants.addAll(localModel.getConditionalPerformer());
      participants.addAll(localModel.getOrganization());
      participants.retainAll(lockedElements);
      participants.addAll(getDeletedElements(PKG_CWM.getRoleType()));
      participants.addAll(getDeletedElements(PKG_CWM.getConditionalPerformerType()));
      participants.addAll(getDeletedElements(PKG_CWM.getOrganizationType()));
      if(!participants.isEmpty())
      {
         container.getParticipants().getContent().addAll(participants);
      }
      List processDefinitions = new ArrayList();
      processDefinitions.addAll(localModel.getProcessDefinition());
      processDefinitions.retainAll(lockedElements);
      processDefinitions.addAll(getDeletedElements(PKG_CWM.getProcessDefinitionType()));
      if(!processDefinitions.isEmpty())
      {
         container.setProcessDefinitions(processDefinitions);         
      }
      List diagrams = new ArrayList();
      diagrams.addAll(localModel.getDiagram());
      diagrams.retainAll(lockedElements);
      diagrams.addAll(getDeletedElements(PKG_CWM.getDiagramType()));
      if(!diagrams.isEmpty())
      {
         container.setDiagrams(diagrams);
      }
      List linkTypes = new ArrayList();
      linkTypes.addAll(localModel.getLinkType());
      linkTypes.retainAll(lockedElements);
      linkTypes.addAll(getDeletedElements(PKG_CWM.getLinkTypeType()));
      if(!linkTypes.isEmpty())
      {
         container.setLinkTypes(linkTypes);
      }
   }

   private List<EObject> getDeletedElements(EClass model)
   {
      List<EObject> elements = new ArrayList<EObject>();
      for (int i = 0; i < deletedElements.size(); i++)
      {
         EObject element = (EObject) deletedElements.get(i);  
         if(element.eClass().equals(model))
         {
            elements.add(element);
         }
      }
      return elements;
   }   
            
   public List<EObject> getEObjectsFromSelection(Object[] elements)
   {
      selectedElements = new ArrayList<EObject>();
      for (int i = 0; i < elements.length; i++)
      {
         Object object = elements[i];         
         if(object instanceof EObject)
         {
            selectedElements.add((EObject) object);               
         }
      }
      return selectedElements;
   }

   public ModelType getRemoteModel()
   {
      return remoteModelHead;
   }
}