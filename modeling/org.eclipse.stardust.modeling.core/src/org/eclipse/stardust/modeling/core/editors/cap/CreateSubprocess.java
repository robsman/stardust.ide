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
package org.eclipse.stardust.modeling.core.editors.cap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreateModelElementUtil;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.ActivitySymbolNodeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.utils.SnapGridUtils;
import org.eclipse.ui.PlatformUI;


public class CreateSubprocess
{   
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;   

   private static Object inConnection_ = null;
   private static Object outConnection_ = null; 
   private List transitionList;
   private List connectionList;
   
   private WorkflowModelEditor editor;
   private ModelType model;
   private ProcessDefinitionType process;
   private DiagramType diagram;
   private PoolSymbol pool;
   private LaneSymbol laneSymbol = null;
   
   private ActivityType activity;
   private ActivitySymbolType symbol;
   
   private StoreObject storage;
   
   public CreateSubprocess(List selection, StoreObject storage, WorkflowModelEditor editor)
   {      
      model = getModelFromSelection(selection);      
      this.storage = storage;
      this.editor = editor;
      getInOutConnections(selection);      
   }

   private void getInOutConnections(List selection)
   {
      List activitySymbols = new ArrayList();
      for (int i = 0; i < selection.size(); i++)
      {
         Object element = selection.get(i);  
         Object modelElement = null;
         if(element instanceof INodeSymbol)
         {
            modelElement = element;                  
         }
         else if (element instanceof AbstractNodeSymbolEditPart)
         {
            modelElement = ((AbstractEditPart) element).getModel();                  
         }
         if(modelElement instanceof ActivitySymbolType)
         {
            activitySymbols.add((ActivitySymbolType) modelElement);
         }         
      }
      
      // collect possible in and out connections and the transitions
      inConnection_ = null;
      outConnection_ = null;
      isValidActivityNetwork(activitySymbols);  
      transitionList = new ArrayList();
      connectionList = new ArrayList();
      if(inConnection_ != null && inConnection_ instanceof TransitionConnectionType)
      {
         connectionList.add(inConnection_);
         if(((TransitionConnectionType) inConnection_).getTransition() != null)
         {
            transitionList.add(((TransitionConnectionType) inConnection_).getTransition());
         }
      }
      if(outConnection_ != null && outConnection_ instanceof TransitionConnectionType)
      {
         connectionList.add(outConnection_);
         if(((TransitionConnectionType) outConnection_).getTransition() != null)
         {
            transitionList.add(((TransitionConnectionType) outConnection_).getTransition());
         }
      }      
   }   
   
   public void updateStorage()
   {
      storage.setTargetDiagram(diagram);
      storage.setTargetProcess(process);
      storage.setTargetModel(model);
      storage.setSameModel(true);  
      storage.setTargetObject(pool); 
   }

   public void createElements()
   {
      createSubProcess();
      createActivity();
   }
   
   private void createSubProcess()
   {
      IdFactory idFactory = new IdFactory(Diagram_Messages.ID_ProcessDefinition,
            Diagram_Messages.BASENAME_ProcessDefinition);      
      EClass eClass = PKG.getProcessDefinitionType();
      EObject container = model;
      process = (ProcessDefinitionType) CreateModelElementUtil.createModelElement(idFactory, eClass, container, model);
      CreateModelElementUtil.addModelElement(CreateModelElementUtil.getContainingFeature(eClass, container), process, container);
      createDiagram(process);
   }

   private void createDiagram(ProcessDefinitionType process)
   {
      EClass eClass = PKG.getDiagramType();
      EObject container = process;
      diagram = (DiagramType) CreateModelElementUtil.createModelElement(null, eClass, container, model);
      diagram.setName(Diagram_Messages.DIAGRAM_NAME_Diagram);
      
      diagram.setOrientation(OrientationType.VERTICAL_LITERAL.toString().equals(
            PlatformUI.getPreferenceStore().getString(
                  BpmProjectNature.PREFERENCE_MODELING_DIRECTION)) ?
            OrientationType.VERTICAL_LITERAL : OrientationType.HORIZONTAL_LITERAL);

      DiagramModeType defaultMode;            
      if (PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_CLASSIC_MODE))
      {
         defaultMode = DiagramModeType.MODE_400_LITERAL;
      }
      else
      {
         defaultMode = DiagramModeType.MODE_450_LITERAL;
      }
      diagram.setMode(defaultMode);            
      
      pool = DiagramUtil.createDefaultPool(null);
      pool.setElementOid(ModelUtils.getElementOid(pool, model));
      diagram.getPoolSymbols().add(pool);
      CreateModelElementUtil.addModelElement(CreateModelElementUtil.getContainingFeature(eClass, container), diagram, container);
   }
   
   private void createActivity()
   {
      IdFactory idFactory = new IdFactory(Diagram_Messages.ID_Activity, 
            ActivityImplementationType.SUBPROCESS_LITERAL + Diagram_Messages.BASENAME_Activity);
      EClass eClass = PKG.getActivityType();
      EObject container = storage.getSourceProcess();
      activity = (ActivityType) CreateModelElementUtil.createModelElement(idFactory, eClass, container, model);
      activity.setImplementation(ActivityImplementationType.SUBPROCESS_LITERAL);
      activity.setSubProcessMode(SubProcessModeType.SYNC_SHARED_LITERAL);
      activity.eSet(PKG.getActivityType_ImplementationProcess(), process);      
      activity.eSet(PKG.getIIdentifiableElement_Name(), process.getName());
      CreateModelElementUtil.addModelElement(CreateModelElementUtil.getContainingFeature(eClass, container), activity, container);
      createActivitySymbol(activity);
   }

   private void createActivitySymbol(ActivityType activity)
   {
      EClass eClass = PKG.getActivitySymbolType();      
      EObject container = DiagramUtil.getDefaultPool((DiagramType) storage.getSourceDiagram());
      if(laneSymbol != null)
      {
         container = laneSymbol;
      }      
      DiagramType targetDiagram = ModelUtils.findContainingDiagram((IGraphicalObject) container);
      AbstractGraphicalEditPart host = (AbstractGraphicalEditPart) editor.findEditPart(targetDiagram);
      
      symbol = (ActivitySymbolType) CreateModelElementUtil.createModelElement(null, eClass, container, model);
      symbol.setActivity(activity);
      CreateModelElementUtil.addSymbol((ISymbolContainer) container, symbol, model);
      Point location = storage.getLocation();
      
      // size depends on snap2grid values
      ActivitySymbolNodeEditPart editPart = (ActivitySymbolNodeEditPart) editor.findEditPart(symbol);
      Dimension prefSize = editPart.getFigure().getPreferredSize();
      Dimension size = SnapGridUtils.getSnapDimension(prefSize, host, 2, true);
      
      symbol.setHeight(size.height);
      symbol.setWidth(size.width);
      symbol.setXPos(location.x);
      symbol.setYPos(location.y); 
      editPart.refresh();
   }
         
   private ModelType getModelFromSelection(List selection)
   {
      Object modelElement = selection.get(0);
      if (modelElement instanceof AbstractNodeSymbolEditPart
            || modelElement instanceof DiagramEditPart)
      {
         modelElement = ((AbstractEditPart) modelElement).getModel();                  
      }
      return ModelUtils.findContainingModel((EObject) modelElement);
   }

   public void setLaneSymbol(LaneSymbol laneSymbol)
   {
      this.laneSymbol = laneSymbol;
   } 

   public void reconnectConnections()
   {
      if(inConnection_ != null && inConnection_ instanceof TransitionConnectionType)
      {
         ((TransitionConnectionType) inConnection_).setTargetNode(symbol);
         TransitionType transition = ((TransitionConnectionType) inConnection_).getTransition();
         if(transition != null)
         {
            transition.setTo(activity);
         }
      }
      if(outConnection_ != null && outConnection_ instanceof TransitionConnectionType)
      {
         ((TransitionConnectionType) outConnection_).setSourceNode(symbol);
         TransitionType transition = ((TransitionConnectionType) outConnection_).getTransition();
         if(transition != null)
         {
            transition.setFrom(activity);
         }         
      }      
   }
   
   //////////
      
   public void deleteSymbols(List symbols)
   {
      for(Iterator iter = symbols.iterator(); iter.hasNext();)
      {
         INodeSymbol symbol = (INodeSymbol) iter.next();
         ConnectionUtils.deleteConnectionsFromSymbol(symbol, connectionList);               
         MergeUtils.deleteElement(symbol, null);
      }      
   }
   
   public void deleteElements(List modelSymbols)
   {
      for(Iterator iter = modelSymbols.iterator(); iter.hasNext();)
      {
         IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) iter.next();
         IIdentifiableModelElement modelElement = ((IModelElementNodeSymbol) symbol).getModelElement();
         List symbols = modelElement.getSymbols();
         deleteSymbols(symbols);         
         if(modelElement != null)
         {
            deleteElement(modelElement);
         }
      }      
   }
   
   public void deleteElement(IModelElement element)
   {
      if(element instanceof ActivityType)
      {
         ConnectionUtils.deleteTransitions((ActivityType) element, transitionList);
      }
      MergeUtils.deleteElement(element, null);
   }  
   
   public static boolean isValidActivityNetwork(List activitySymbols)
   {
      Object inConnection = null;
      Object outConnection = null;       
      
      if(activitySymbols.isEmpty())
      {
         return false;
      }
      
      // collect all activities
      // map to later get the selected symbol
      Map activityToSymbol = new HashMap();
      List activities = new ArrayList();
      for(Iterator iter = activitySymbols.iterator(); iter.hasNext();)
      {
         ActivitySymbolType activitySymbol = (ActivitySymbolType) iter.next();
         ActivityType activity = activitySymbol.getActivity();
         activityToSymbol.put(activity, activitySymbol);
         activities.add(activity);         
      }
      
      // all activities must be connected
      TransitionType networkInTransition = null;
      TransitionType networkOutTransition = null;
      ActivityType startActivity = null;
      ActivityType endActivity = null;
      
      for(Iterator iter = activities.iterator(); iter.hasNext();)
      {
         boolean hasOutTransitionIntoNetwork = false;
         boolean hasInTransitionIntoNetwork = false;         
         
         boolean mustHaveOutTransitionIntoNetwork = false;
         boolean mustHaveInTransitionIntoNetwork = false;         
         
         ActivityType activity = (ActivityType) iter.next();
         // here we can have activity to be null (undo delete symbol)
         if(activity == null)
         {
            return false;
         }
         List inTransitions = activity.getInTransitions();         
         if(!inTransitions.isEmpty())
         {
            if(inTransitions.size() == 1)
            {
               TransitionType transition = (TransitionType) inTransitions.get(0);
               ActivityType fromActivity = transition.getFrom();
               if(!activities.contains(fromActivity))
               {
                  if(networkInTransition == null)
                  {
                     startActivity = activity;
                     networkInTransition = transition;
                     mustHaveOutTransitionIntoNetwork = true;
                  }
                  else
                  {
                     return false;
                  }                  
               }
               else
               {
                  hasInTransitionIntoNetwork = true;
               }
            }
            else
            {
               hasInTransitionIntoNetwork = true;
               for(Iterator it = inTransitions.iterator(); it.hasNext();)
               {
                  TransitionType transition = (TransitionType) it.next();
                  ActivityType fromActivity = transition.getFrom();
                  if(!activities.contains(fromActivity))
                  {
                     return false;
                  }                  
               }
            }
         }
         else
         {
            startActivity = activity;
            mustHaveOutTransitionIntoNetwork = true;            
         }
         List outTransitions = activity.getOutTransitions();         
         if(!outTransitions.isEmpty())
         {
            if(outTransitions.size() == 1)
            {
               TransitionType transition = (TransitionType) outTransitions.get(0);
               ActivityType toActivity = transition.getTo();
               if(!activities.contains(toActivity))
               {
                  if(networkOutTransition == null)
                  {
                     endActivity = activity;
                     networkOutTransition = transition;
                     mustHaveInTransitionIntoNetwork = true;
                  }
                  else
                  {
                     return false;
                  }                  
               }
               else
               {
                  hasOutTransitionIntoNetwork = true;
               }               
            }
            else
            {
               hasOutTransitionIntoNetwork = true;
               for(Iterator it = outTransitions.iterator(); it.hasNext();)
               {
                  TransitionType transition = (TransitionType) it.next();
                  ActivityType toActivity = transition.getTo();
                  if(!activities.contains(toActivity))
                  {
                     return false;
                  }                  
               }
            }
         }
         else
         {
            endActivity = activity;
            mustHaveInTransitionIntoNetwork = true;
         } 
         if(activities.size() > 1)
         {
            if(mustHaveInTransitionIntoNetwork && !hasInTransitionIntoNetwork)
            {
               return false;            
            }
            if(mustHaveOutTransitionIntoNetwork && !hasOutTransitionIntoNetwork)
            {
               return false;            
            }
         }
      }
      
      if(startActivity != null)
      {
         inConnection = ConnectionUtils.getInConnectionFromSymbol(
               (INodeSymbol) activityToSymbol.get(startActivity), networkInTransition);
         if(inConnection != null)
         {
            // we found more than one connection symbol
            if(inConnection instanceof Boolean)
            {
               return false;
            }
            inConnection_ = inConnection;
         }
      }
      if(endActivity != null)
      {
         outConnection = ConnectionUtils.getOutConnectionFromSymbol(
               (INodeSymbol) activityToSymbol.get(endActivity), networkOutTransition);
         if(outConnection != null)
         {
            // we found more than one connection symbol
            if(outConnection instanceof Boolean)
            {
               return false;
            }
            outConnection_ = outConnection;
         }
      }
      return true;
   }
}