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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.ShareUtils;
import org.eclipse.stardust.model.xpdl.carnot.merge.UUIDUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionInfo;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionState;
import org.eclipse.stardust.modeling.core.modelserver.jobs.StateCache;
import org.eclipse.stardust.modeling.core.modelserver.ui.ModelContainer.Container;


public class UpdateUtil implements IMergeUtils
{
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;
    
   private WorkflowModelEditor editor;
   
   private ModelType localModel;
   private ModelType remoteModelHead;
   
   private Map<String, EObject> localMap;
   private Map<String, EObject> remoteMapHead;
   
   private List<EObject> lockedElements = new ArrayList<EObject>();
   private List<EObject> newElements = new ArrayList<EObject>();
   private List<EObject> removedElements = new ArrayList<EObject>(); 
   private List<EObject> changedElements = new ArrayList<EObject>();
   private List<EObject> unChangedElements = new ArrayList<EObject>();
   private List<EObject> allElements = new ArrayList<EObject>();
      
   // elements the user has selected to merge/commit
   private List<EObject> selectedElements;   

   public UpdateUtil(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }      
   
   public void analyze(IProgressMonitor monitor) throws RMSException
   {      
      monitor.beginTask(Diagram_Messages.TASK_PREPARING_FOR_UPDATE, 3);
      
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

      monitor.subTask(Diagram_Messages.TASK_REMOTE_MD);
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
      removedElements.addAll(localMapClone.values());
      
      // add
      remoteMapClone.keySet().removeAll(localMap.keySet());
      List<EObject> tempNewElements = new ArrayList<EObject>(remoteMapClone.values());
      for (EObject element : tempNewElements)
      {
         // only if we have no lock file, it is a new element              
         if (!LockFileUtils.hasLockFile(element, modelServer.getModel()))
         {
            newElements.add(element);
         }
      }                
      
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
         Map.Entry entry = (Map.Entry) it.next();
         EObject element = (EObject) entry.getValue();
         if (ShareUtils.isLockableElement(element))
         {
            if (LockFileUtils.hasLockFile(element, modelServer.getModel()))
            {               
               boolean changed = ModelServerUtils.isChanged(element, 
                                    ModelServerUtils.findByUUID(remoteModelHead, 
                                          UUIDUtils.getUUID(element)));  
               
               // changed
               if(changed)
               {
                  changedElements.add(element);      
                  // if locked by me, it is my own change for commit
                  // we will not show this element on update
                  
                  EObject sameElement = CopyPasteUtil.getSameModelElement(element, model, null);
                  CollisionInfo stateInfo = stateCache.getState(sameElement);
                  if (stateInfo.getState() != CollisionState.LOCKED_BY_USER)
                  {                  
                     allElements.add(element);
                  }
               }
               else
               {
                  unChangedElements.add(element);                        
               }                                                                           
            }
         }         
      }
   }
         
   public List<EObject> getLockedElements()
   {
      return lockedElements;
   }
      
   public ModelType getLocalModel()
   {
      return localModel;
   }   
   
   public boolean containsChanges()
   {
      return !allElements.isEmpty();
   }
   
   public List<EObject> getSelectedElements()
   {
      return selectedElements;
   }

   public List<EObject> getNotSelectedElementsRemoved()
   {
      List<EObject> elements = new ArrayList<EObject>(removedElements);
      elements.removeAll(getSelectedElementsRemoved());
      return elements;
   }      
   
   public List<EObject> getNotSelectedElementsNew()
   {
      List<EObject> elements = new ArrayList<EObject>(newElements);
      elements.removeAll(getSelectedElementsNew());
      return elements;
   }   
   
   public List<EObject> getSelectedElementsNew()
   {
      List<EObject> elements = new ArrayList<EObject>(newElements);
      elements.retainAll(selectedElements);
      return elements;
   }

   public List<EObject> getSelectedElementsRemoved()
   {
      List<EObject> elements = new ArrayList<EObject>(removedElements);
      elements.retainAll(selectedElements);      
      return elements;
   }
   
   public List<EObject> getSelectedElementsChanged()
   {
      ModelServer modelServer = editor.getModelServer();
      ModelType model = modelServer.getModel();
      
      List<EObject> tmpChangedElements = new ArrayList<EObject>();
      List<EObject> elements = new ArrayList<EObject>(changedElements);
      elements.retainAll(selectedElements);      
      for (EObject element : elements)
      {
         EObject sameElement = CopyPasteUtil.getSameElement(element, model);
         tmpChangedElements.add(sameElement);
      }
      return tmpChangedElements;
   }
   
   public void setUpdateContent(Container container)
   {
      if(changedElements.contains(localModel))
      {
         container.setModel(localModel);
      }         
      
      List typeDeclarations = new ArrayList();
      typeDeclarations.addAll(localModel.getTypeDeclarations().getTypeDeclaration());
      typeDeclarations.retainAll(allElements);
      typeDeclarations.addAll(getAddedElements(XpdlPackage.eINSTANCE.getTypeDeclarationType()));
      if(!typeDeclarations.isEmpty())
      {
         container.getTypeDeclarations().getContent().addAll(typeDeclarations);
      }
      List applications = new ArrayList();
      applications.addAll(localModel.getApplication());
      applications.retainAll(allElements);
      applications.addAll(getAddedElements(PKG_CWM.getApplicationType()));
      if(!applications.isEmpty())
      {
         container.getApplications().getContent().addAll(applications);
      }
      List datas = new ArrayList();
      datas.addAll(localModel.getData());
      datas.retainAll(allElements);
      datas.addAll(getAddedElements(PKG_CWM.getDataType()));
      if(!datas.isEmpty())
      {
         container.getDatas().getContent().addAll(datas);
      }
      List participants = new ArrayList();
      participants.addAll(localModel.getRole());
      participants.addAll(localModel.getConditionalPerformer());
      participants.addAll(localModel.getOrganization());
      participants.retainAll(allElements);
      participants.addAll(getAddedElements(PKG_CWM.getRoleType()));
      participants.addAll(getAddedElements(PKG_CWM.getConditionalPerformerType()));
      participants.addAll(getAddedElements(PKG_CWM.getOrganizationType()));
      if(!participants.isEmpty())
      {
         container.getParticipants().getContent().addAll(participants);
      }
      List processDefinitions = new ArrayList();
      processDefinitions.addAll(localModel.getProcessDefinition());
      processDefinitions.retainAll(allElements);
      processDefinitions.addAll(getAddedElements(PKG_CWM.getProcessDefinitionType()));
      if(!processDefinitions.isEmpty())
      {
         container.setProcessDefinitions(processDefinitions);         
      }
      List diagrams = new ArrayList();
      diagrams.addAll(localModel.getDiagram());
      diagrams.retainAll(allElements);
      diagrams.addAll(getAddedElements(PKG_CWM.getDiagramType()));
      if(!diagrams.isEmpty())
      {
         container.setDiagrams(diagrams);
      }
      List linkTypes = new ArrayList();
      linkTypes.addAll(localModel.getLinkType());
      linkTypes.retainAll(allElements);
      linkTypes.addAll(getAddedElements(PKG_CWM.getLinkTypeType()));
      if(!linkTypes.isEmpty())
      {
         container.setLinkTypes(linkTypes);
      }
   }

   private List<EObject> getAddedElements(EClass model)
   {
      List<EObject> elements = new ArrayList<EObject>();
      for (int i = 0; i < newElements.size(); i++)
      {
         EObject element = (EObject) newElements.get(i);  
         if(element.eClass().equals(model))
         {
            elements.add(element);
         }
      }
      return elements;
   }   
   
   public List<EObject> getUnChangedElements()
   {
      return unChangedElements;
   }   
   
   public boolean isRemovedElement(EObject element)
   {
      return removedElements.contains(element);
   }   
   
   public boolean isNewElement(EObject element)
   {
      return newElements.contains(element);
   }
   
   public boolean hasChanged(EObject element)
   {      
      return changedElements.contains(element);
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