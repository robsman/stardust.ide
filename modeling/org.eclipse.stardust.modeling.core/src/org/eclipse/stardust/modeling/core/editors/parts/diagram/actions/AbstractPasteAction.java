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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.AbstractMultiPageGraphicalEditor;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.editors.cap.StoreObject;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.utils.HierarchyUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.ui.IWorkbenchPart;


public abstract class AbstractPasteAction extends SelectionAction
{
   protected Clipboard clipboard;
   protected List copySet;
   protected ModelType targetModel;
   protected StoreObject storage;
   protected DiagramModeType targetDiagramMode;    
   protected boolean copyLanes = false;
   protected boolean sameModel = false;
   protected Integer isValid = null;
   
   protected AbstractPasteAction(IWorkbenchPart part)
   {
      super(part);
   }
   
   protected boolean isValidSelection()
   {
      IWorkbenchPart part = getWorkbenchPart();
      if(!(part instanceof WorkflowModelEditor))
      {
         return false;
      }
      copyLanes = false;
      Object currentContent = clipboard.getContents();
      // do we have something to paste?
      if (currentContent instanceof List)
      {
         // we need a copy, so we can remove one element
         List content = new ArrayList((List) currentContent);  
         // remove it from list
         copySet = extractStorage(content);
      }
      else
      {
         return false;
      }      
      // what is the current selection?
      List selection = getSelectedObjects();
      if(selection == null || selection.size() == 0 || selection.size() > 1)
      {
         return false;
      }
      targetModel = CopyPasteUtil.getTargetModel(getSelectedObjects().get(0));
      if(targetModel == null)
      {
         return false;
      }         
      if(storage.getSourceModel().equals(targetModel))
      {
         sameModel = true;
      }
      else
      {
         sameModel = false;
      }
      
      // returns if content belongs to diagram OR outline
      isValid = CopyPasteUtil.validateSelection(copySet, false);
      if(isValid == null)
      {
         return false;
      }
      return true;
   }
   
   // extract the original Model (a copy) and the storage Object
   protected List extractStorage(List currentContent)
   {
      List copySet = new ArrayList();
      for(int i = 0; i < currentContent.size(); i++)
      {
         Object entry = currentContent.get(i);
         if(entry instanceof StoreObject)
         {
            storage = (StoreObject) entry;
         }
         else
         {
            copySet.add(entry);
         }
      }
      return copySet;
   }
   
   protected boolean isValidForDiagram()
   {
      Object selectedObject = getSelectedObjects().get(0);
      if(selectedObject instanceof EditPart) 
      {
         DiagramEditPart diagramEP = null;
         DiagramType diagram = null;                  
         if(selectedObject instanceof DiagramEditPart
            || selectedObject instanceof DiagramRootEditPart)
         {
            if(selectedObject instanceof DiagramEditPart)
            {
               diagramEP = (DiagramEditPart) selectedObject;
            }               
            else if(selectedObject instanceof DiagramRootEditPart)
            {
               diagramEP = (DiagramEditPart) ((DiagramRootEditPart) selectedObject).getChildren().get(0);
            }
            if(diagramEP != null)
            {
               diagram = (DiagramType) diagramEP.getModel();                  
            }
            storage.setTargetDiagram(diagram);
            // target is a process diagram
            if(ModelUtils.findContainingProcess(diagram) != null)
            {
               targetDiagramMode = diagram.getMode();
            }
         }
         
         if(diagramEP != null)
         {
            if(copySet.get(0) instanceof LaneSymbol)
            {
               copyLanes = true;
            }
            // target is a process diagram
            if(ModelUtils.findContainingProcess(diagram) != null)
            {
               // if the selection is only valid for model diagram
               if(isValid.intValue() == CopyPasteUtil.SELECTION_MODEL_DIAGRAM)
               {
                  return false;
               }
               // find real target EP
               EditPart targetEP = PoolLaneUtils.findTargetEditPart((WorkflowModelEditor) getWorkbenchPart());
               if(targetEP != null 
                     && targetEP instanceof LaneEditPart
                     || targetEP instanceof PoolEditPart
                     || targetEP instanceof DiagramEditPart
                     || targetEP instanceof DiagramRootEditPart)
               {
                  if(targetEP instanceof DiagramRootEditPart
                        || targetEP instanceof DiagramEditPart)
                  {
                     targetEP = diagramEP;                           
                  }                        
                  if(targetEP instanceof LaneEditPart)
                  {
                     if(((LaneEditPart) targetEP).getLaneFigure().isCollapsed())
                     {
                        return false;
                     }
                  }                           
                  if(targetDiagramMode.equals(DiagramModeType.MODE_450_LITERAL))
                  {
                     if(PoolLaneUtils.containsLanes(targetEP) && !copyLanes)
                     {
                        return false;
                     }
                     // in BPMN Mode do not paste outside pool
                     if(targetEP instanceof DiagramEditPart)
                     {
                        return false;
                     }                           
                     if(copyLanes)
                     {
                        if(PoolLaneUtils.containsOthers((AbstractSwimlaneEditPart) targetEP))
                        {
                           return false;
                        }
                        DiagramEditorPage diagramEditorPage = (DiagramEditorPage) ((AbstractMultiPageGraphicalEditor) getWorkbenchPart()).getCurrentPage();                                 
                        // mouse location is absolute
                        Point mouseLocation = diagramEditorPage.getMouseLocation();
                        // check because of null pointer exception
                        if(targetEP.getModel() != null)
                        {
                           EObject model = (EObject) targetEP.getModel();
                           if(model.eContainer() == null)
                           {
                              return false;
                           }
                        }
                        
                        // new location in the target
                        Point newContainerLocation = PoolLaneUtils.getLocation((GraphicalEditPart) targetEP, 
                              ((AbstractGraphicalEditPart) targetEP).getFigure(), 
                                 mouseLocation, true);                                                            
                        if(!PoolLaneUtils.isSensitiveArea((AbstractSwimlaneEditPart) targetEP, newContainerLocation))
                        {
                           return false;
                        }
                        ISwimlaneSymbol targetSymbol = (ISwimlaneSymbol) targetEP.getModel();
                        // next check for participants
                        if(!sameModel)
                        {
                           // 1st check if target is pool (has no participant)  
                           // else (target is lane) check if any parent has an participant assigned
                           if(targetSymbol instanceof LaneSymbol)
                           {
                              if(HierarchyUtils.hasParentLanesParticipant((LaneSymbol) targetSymbol) != null)
                              {
                                 // then we can copy lanes only if no participant is assigned
                                 for(int i = 0; i < copySet.size(); i++)
                                 {
                                    LaneSymbol symbol = (LaneSymbol) copySet.get(i);
                                    if(symbol.getParticipantReference() != null
                                          || HierarchyUtils.hasChildLanesParticipant(symbol))
                                    {
                                       return false;
                                    }
                                 }
                              }
                           }
                        }
                        else
                        {
                           // paste lane into same model into other lane
                           if(targetSymbol instanceof LaneSymbol)
                           {
                              IModelParticipant parentParticipant = HierarchyUtils.hasParentLanesParticipant((LaneSymbol) targetSymbol);
                              // target has a participant assigned
                              if(parentParticipant != null)
                              {
                                 ModelType model = (ModelType) ((AbstractNodeEditPart) targetEP).getEditor().getModel();
                                 Map organizationTree = HierarchyUtils.createHierarchy(model); 
                                 List organizationChildren = null;
                                 // target participant is organization
                                 if(parentParticipant instanceof OrganizationType)
                                 {
                                    organizationChildren = HierarchyUtils.getChildHierarchy(organizationTree, (OrganizationType) parentParticipant);                                          
                                 }
                                 for(int l = 0; l < copySet.size(); l++)
                                 {
                                    LaneSymbol laneCopy = (LaneSymbol) copySet.get(l);
                                    IModelParticipant laneCopyParticipant = laneCopy.getParticipantReference();
                                    List childParticipants = null;
                                    if(laneCopyParticipant == null)
                                    {
                                       childParticipants = HierarchyUtils.getTopLevelChildParticipants(laneCopy);
                                    }
                                    else
                                    {
                                       childParticipants = new ArrayList();
                                       childParticipants.add(laneCopyParticipant);
                                    }                                          
                                    // lane to be copied has also a participant assigned
                                    if(childParticipants != null)
                                    {
                                       if(organizationChildren == null)
                                       {
                                          return false;
                                       }
                                       else
                                       {
                                          for(int o = 0; 0 < childParticipants.size(); o++)
                                          {
                                             IModelParticipant childParticipant = (IModelParticipant) childParticipants.get(o);
                                             if(!organizationChildren.contains(childParticipant))
                                             {
                                                return false;
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
                  else
                  {
                     // lanes can be copied only to BPMN mode
                     if(copyLanes)
                     {
                        return false;
                     }
                  }
                  storage.setTargetEditPart(targetEP);
                  // if target is diagram EP in Classic Mode the the target Object is the default Pool
                  if(!targetDiagramMode.equals(DiagramModeType.MODE_450_LITERAL)
                        && targetEP instanceof DiagramEditPart)
                  {
                     targetEP = ((DiagramEditPart) targetEP).getPoolDelegate();
                     storage.setTargetObject((EObject) targetEP.getModel());                              
                  }
                  else
                  {
                     storage.setTargetObject((EObject) targetEP.getModel());                           
                  }
                  return true;                     
               }
            }
            else
            {
               if(copyLanes)
               {
                  return false;
               }                     
               EditPart targetEP = PoolLaneUtils.findTargetEditPart((WorkflowModelEditor) getWorkbenchPart());
               if(targetEP != null
                     && targetEP instanceof PoolEditPart
                     || targetEP instanceof DiagramEditPart
                     || targetEP instanceof DiagramRootEditPart)
               {
                  if(targetEP instanceof DiagramRootEditPart)
                  {
                     targetEP = diagramEP;
                  }                        
                  // if the selection is only valid for process diagram
                  if(isValid.intValue() == CopyPasteUtil.SELECTION_PROCESS_DIAGRAM)
                  {
                     return false;
                  }
                  storage.setTargetEditPart(targetEP);
                  storage.setTargetObject((EObject) targetEP.getModel());
                  return true;                     
               }                     
            }
         }               
         return false;
      }
      return false;
   }
   
   protected boolean isValidForOutline()
   {
      if(CopyPasteUtil.isTypeDeclarationsNode(getSelectedObjects().get(0)))
      {
         if(!CopyPasteUtil.isTypeDeclarationOnly(copySet))
         {
            return false;
         }
         else
         {
            return true;
         }
      }
      else if(CopyPasteUtil.isDataCategoryNode(getSelectedObjects().get(0)))
      {
         if(!CopyPasteUtil.isDataOnly(copySet))
         {
            return false;
         }
         else
         {
            return true;
         }
      } 
      else if(CopyPasteUtil.isParticipantCategoryNode(getSelectedObjects().get(0))) 
      {
         if(!CopyPasteUtil.isParticipantOnly(copySet))
         {
            return false;
         }            
         else
         {
            return true;               
         }
      } 
      else if(CopyPasteUtil.isApplicationCategoryNode(getSelectedObjects().get(0)))
      {
         if(!CopyPasteUtil.isApplicationOnly(copySet))
         {
            return false;
         }            
         else
         {
            return true;               
         }
      }
      // process node selected
      else if(CopyPasteUtil.isProcessCategoryNode(getSelectedObjects().get(0)) != null)
      {       
         ProcessDefinitionType selectedProcess = CopyPasteUtil.isProcessCategoryNode(getSelectedObjects().get(0));
         // can only be diagram of the same process - in the same model
         if(sameModel && CopyPasteUtil.isProcessDiagramOnly(copySet, selectedProcess, storage.getSourceModel()))
         {
            return true;
         }
         if(CopyPasteUtil.isProcessChildOnly(copySet))
         {
            return true;
         }
         
         return false;
      }      
      // here we can paste everything (if parent is there - check by id)
      else if(CopyPasteUtil.isModelCategoryNode(getSelectedObjects().get(0)))
      {
         // if not the same model, copy set may not contain diagram
         if(!sameModel)
         {
            if(CopyPasteUtil.containsDiagram(copySet))
            {
               return false;
            }
         } 
         // a target process must be selected
         if(CopyPasteUtil.containsProcessChild(copySet))
         {
            return false;
         }
         
         // no diagrams that belong to a process
         if(CopyPasteUtil.containsProcessDiagram(copySet))
         {
            return false;
         }
         else
         {
            return true;               
         }                     
         // we can do the check here already            
         // set a targetObject for the generic paste?
      }
      return false;
   }
   
   protected EObject getTargetObject()
   {
      Object targetEP = getSelectedObjects().get(0);
      EObject targetObject = null;      
      if(targetEP instanceof EditPart) 
      {
         DiagramEditPart diagramEP = null;
         if(targetEP instanceof DiagramRootEditPart)
         {
            diagramEP = (DiagramEditPart) ((DiagramRootEditPart) targetEP).getChildren().get(0);               
         }
         else if(targetEP instanceof DiagramEditPart)
         {
            diagramEP = (DiagramEditPart) targetEP;               
         }
         if(diagramEP != null)
         {
            targetObject = (EObject) diagramEP.getModel();               
         }
         /*
         if(targetEP instanceof LaneEditPart)
         {               
            targetObject = (EObject) ((LaneEditPart) targetEP).getModel();
         }
         if(targetEP instanceof PoolEditPart)
         {
            targetObject = (EObject) ((PoolEditPart) targetEP).getModel();
         }
         */
      }
      return targetObject;
   }   
}