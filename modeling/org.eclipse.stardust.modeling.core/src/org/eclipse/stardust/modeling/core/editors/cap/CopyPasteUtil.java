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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.PublicInterfaceSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils.EObjectInvocationHandler;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.SymbolGroupEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode.Spec;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDSchema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CopyPasteUtil
{
   public static final String idPrefix = "CopyOf"; //$NON-NLS-1$

   public static final int SELECTION_OUTLINE = 1;

   // for global model elements or no model element (Annotations)
   public static final int SELECTION_GLOBAL_DIAGRAM = 2;

   // process definition only
   public static final int SELECTION_MODEL_DIAGRAM = 3;

   // activities and life cycles
   public static final int SELECTION_PROCESS_DIAGRAM = 4;

   // create subprocess from selection, process diagram only
   public static boolean validateSelectionForSubprocess(List selection)
   {
      List activitySymbols = new ArrayList();
      for (int i = 0; i < selection.size(); i++)
      {
         Object element = selection.get(i);
         if (element instanceof SymbolGroupEditPart
               || element instanceof AbstractEObjectTreeEditPart
               || element instanceof IModelElement
               || element instanceof TypeDeclarationType)
         {
            return false;
         }
         if (!(element instanceof INodeSymbol || element instanceof AbstractNodeSymbolEditPart))
         {
            return false;
         }
         Object modelElement = null;
         if (element instanceof INodeSymbol)
         {
            modelElement = element;
         }
         else if (element instanceof AbstractNodeSymbolEditPart)
         {
            modelElement = ((AbstractEditPart) element).getModel();
         }
         // process symbol?
         if (modelElement instanceof ProcessSymbolType
               || modelElement instanceof PoolSymbol
               || modelElement instanceof LaneSymbol
               || modelElement instanceof AbstractEventSymbol)
         {
            return false;
         }
         if (ModelUtils.findContainingProcess((EObject) modelElement) == null)
         {
            return false;
         }
         if (modelElement instanceof ActivitySymbolType)
         {
            activitySymbols.add((ActivitySymbolType) modelElement);
         }
      }
      return CreateSubprocess.isValidActivityNetwork(activitySymbols);
   }

   // here we check if the selection is valid (copy/cut and paste uses this)
   // returns if its for diagram or for outline
   public static Integer validateSelection(List selection, boolean cutAction)
   {
      boolean diagram = false;
      boolean modelDiagram = false;
      boolean processDiagram = false;

      boolean outline = false;
      boolean containsProcess = false; // outline
      boolean containsDiagram = false; // outline

      boolean containsLane = false; // diagram
      boolean containsOther = false; // diagram

      boolean isOutlineProcessChild = false;
      boolean isNotOutlineProcessChild = false;

      if (selection != null && selection.size() > 0)
      {
         for (int i = 0; i < selection.size(); i++)
         {
            Object element = selection.get(i);
            // can be outline or diagram

            if (element instanceof PublicInterfaceSymbol)
            {
               return null;
            }

            if (element instanceof SymbolGroupEditPart)
            {
               return null;
            }
            if (element instanceof INodeSymbol)
            {
               diagram = true;
            }
            else if (element instanceof AbstractNodeSymbolEditPart)
            {
               diagram = true;
            }
            else if (element instanceof AbstractEObjectTreeEditPart)
            {
               outline = true;
            }
            else if (element instanceof IModelElement)
            {
               outline = true;
            }
            else if (element instanceof TypeDeclarationType)
            {
               outline = true;
            }
            else
            {
               return null;
            }
            // check if is valid selection
            if (outline)
            {
               Object modelElement = null;
               if (element instanceof AbstractEObjectTreeEditPart)
               {
                  modelElement = ((AbstractEditPart) element).getModel();
               }
               else if (element instanceof IModelElement
                     || element instanceof TypeDeclarationType)
               {
                  modelElement = element;
               }
               if (modelElement instanceof ChildCategoryNode.Spec)
               {
                  return null;
               }

               // Collision
               if (cutAction)
               {
                  WorkflowModelEditor editor = GenericUtils
                        .getWorkflowModelEditor(ModelUtils
                              .findContainingModel((EObject) modelElement));
                  if (editor == null)
                  {
                     return null;
                  }
               }

               if (modelElement instanceof ProcessDefinitionType)
               {
                  containsProcess = true;
               }
               if (modelElement instanceof DiagramType)
               {
                  containsDiagram = true;
               }

               // only data, applications, participants, process definitions, process and
               // model diagrams
               boolean match = modelElement instanceof DataType
                     || modelElement instanceof TypeDeclarationType
                     || modelElement instanceof IModelParticipant
                     || modelElement instanceof ApplicationType
                     || modelElement instanceof ProcessDefinitionType
                     // we should enhance this
                     || modelElement instanceof ActivityType
                     || modelElement instanceof TriggerType
                     || modelElement instanceof DiagramType;
               // do not cut predefined data
               if (cutAction && modelElement instanceof DataType
                     && ((DataType) modelElement).isPredefined())
               {
                  return null;
               }

               if (modelElement instanceof ActivityType
                     || modelElement instanceof TriggerType)
               {
                  isOutlineProcessChild = true;
               }
               else
               {
                  isNotOutlineProcessChild = true;
               }

               if (!match)
                  return null;

               if (containsDiagram && containsProcess)
               {
                  return null;
               }

               if (isOutlineProcessChild && isNotOutlineProcessChild)
               {
                  return null;
               }
            }
            if (diagram)
            {
               Object modelElement = null;
               if (element instanceof INodeSymbol)
               {
                  modelElement = element;
               }
               else if (element instanceof AbstractNodeSymbolEditPart)
               {
                  modelElement = ((AbstractEditPart) element).getModel();
               }

               // Collision
               if (cutAction)
               {
                  WorkflowModelEditor editor = GenericUtils
                        .getWorkflowModelEditor(ModelUtils
                              .findContainingModel((EObject) modelElement));
                  if (editor == null)
                  {
                     return null;
                  }
               }

               // pool is disabled, copy lanes will become a new feature
               if (modelElement instanceof PoolSymbol
                     || modelElement instanceof LaneSymbol)
               {
                  return null;
               }
               if (ModelUtils.findContainingProcess((EObject) modelElement) == null)
               {
                  modelDiagram = true;
               }
               else
               {
                  processDiagram = true;
               }
               if (modelElement instanceof LaneSymbol)
               {
                  containsLane = true;
               }
               else
               {
                  containsOther = true;
               }
               if (containsLane && containsOther)
               {
                  return null;
               }
               // do not cut predefined data
               if (cutAction && modelElement instanceof DataSymbolType)
               {
                  DataType checkData = ((DataSymbolType) modelElement).getData();
                  if (checkData != null && checkData.isPredefined())
                  {
                     return null;
                  }
               }
            }
         }
      }
      if (diagram || outline)
      {
         if (diagram)
         {
            if (modelDiagram)
            {
               return new Integer(SELECTION_MODEL_DIAGRAM);
            }
            if (processDiagram)
            {
               return new Integer(SELECTION_PROCESS_DIAGRAM);
            }
         }
         else
         {
            return new Integer(SELECTION_OUTLINE);
         }
      }
      return null;
   }

   public static boolean containsGateway(List selection)
   {
      if (selection != null && selection.size() > 0)
      {
         for (int i = 0; i < selection.size(); i++)
         {
            Object element = selection.get(i);

            Object modelElement = null;
            if (element instanceof INodeSymbol)
            {
               modelElement = element;
            }
            else if (element instanceof AbstractNodeSymbolEditPart)
            {
               modelElement = ((AbstractEditPart) element).getModel();
            }

            if (modelElement != null && modelElement instanceof GatewaySymbol)
            {
               return true;
            }
         }
      }
      return false;
   }

   // creates the copy set and fills a storeObject (with necessary infos)
   // used by copy and cut
   public static List createCopySet(Integer isValid, List selectedObjects,
         WorkflowModelEditor editor, boolean copySymbols)
   {
      List contentList = new ArrayList();
      // save mouse location, model, diagram, process
      StoreObject storage = new StoreObject();
      ModelType sourceModel = null;
      ModelType originalModelCopy = null;
      DiagramType sourceDiagram = null;
      ProcessDefinitionType sourceProcess = null;
      // only diagram
      if (isValid.intValue() != CopyPasteUtil.SELECTION_OUTLINE)
      {
         DiagramEditorPage diagramEditorPage = (DiagramEditorPage) editor
               .getCurrentPage();
         Point location = diagramEditorPage.getMouseLocation().getCopy();
         storage.setLocation(location);
      }
      storage.setCopySymbols(copySymbols);
      for (int i = 0; i < selectedObjects.size(); i++)
      {
         Object entry = selectedObjects.get(i);
         EObject modelElement;
         EObject saveModelElement;

         modelElement = (EObject) ((AbstractEditPart) entry).getModel();
         if (sourceDiagram == null)
         {
            if (modelElement instanceof IGraphicalObject)
            {
               sourceDiagram = ModelUtils
                     .findContainingDiagram((IGraphicalObject) modelElement);
            }
            else
            {
               // if it is an diagram, we can get the process, but not if we have two
               // different diagrams
               if (modelElement instanceof DiagramType)
               {

               }
            }
         }
         if (sourceDiagram != null)
         {
            sourceProcess = ModelUtils.findContainingProcess(sourceDiagram);
         }
         // if there is a diagram, we can get the process
         if (sourceModel == null)
         {
            sourceModel = ModelUtils.findContainingModel(modelElement);
            // we must do a deep copy
            // because of a Bug in XSD, we must overwrite copy
            Copier copier = new EcoreUtil.Copier()
            {
               private static final long serialVersionUID = 1L;

               public EObject copy(EObject object)
               {
                  if (object instanceof XSDConcreteComponent)
                  {
                     XSDConcreteComponent original = (XSDConcreteComponent) object;
                     XSDConcreteComponent clone = original.cloneConcreteComponent(true, false);
                     XSDSchema schema = clone.getSchema();
                     Document doc = schema.updateDocument();
                     if (original.getElement() != null)
                     {
                        Element clonedElement = (Element) doc.importNode(original
                              .getElement(), true);
                        //doc.appendChild(clonedElement);
                        clone.setElement(clonedElement);
                     }
                     return clone;
                  }
                  return super.copy(object);
               }
            };
            originalModelCopy = (ModelType) copier.copy(sourceModel);
            copier.copyReferences();
            // repair the attribute references that gets confused by copying
            ModelUtils.resolve(sourceModel, sourceModel);
            ModelUtils.resolve(originalModelCopy, originalModelCopy);
         }
         saveModelElement = CopyPasteUtil.getSameModelElement(modelElement,
               originalModelCopy, null);
         // now we must set the absolute location
         if (saveModelElement instanceof INodeSymbol)
         {
            Point location = new Point(((INodeSymbol) modelElement).getXPos(),
                  ((INodeSymbol) modelElement).getYPos());
            EditPart hostEP = ((AbstractEditPart) entry).getParent();
            location = PoolLaneUtils.getAbsoluteLocation(hostEP, location, sourceDiagram);
            ((INodeSymbol) saveModelElement).setXPos(location.x);
            ((INodeSymbol) saveModelElement).setYPos(location.y);
         }
         if (saveModelElement instanceof ActivitySymbolType)
         {
            List gatewaySymbols = ((ActivitySymbolType) saveModelElement)
                  .getGatewaySymbols();
            if (!gatewaySymbols.isEmpty())
            {
               for (Iterator iter = gatewaySymbols.iterator(); iter.hasNext();)
               {
                  GatewaySymbol gatewaySymbol = (GatewaySymbol) iter.next();
                  if (!contentList.contains(gatewaySymbol))
                  {
                     EObject originalGatewaySymbol = CopyPasteUtil.getSameModelElement(
                           gatewaySymbol, sourceModel, null);
                     EditPart editPart = editor.findEditPart(originalGatewaySymbol);

                     Point location = new Point(((INodeSymbol) gatewaySymbol).getXPos(),
                           ((INodeSymbol) gatewaySymbol).getYPos());
                     EditPart hostEP = ((AbstractEditPart) editPart).getParent();
                     location = PoolLaneUtils.getAbsoluteLocation(hostEP, location,
                           sourceDiagram);
                     ((INodeSymbol) gatewaySymbol).setXPos(location.x);
                     ((INodeSymbol) gatewaySymbol).setYPos(location.y);
                     contentList.add(gatewaySymbol);
                  }
               }
            }
         }
         if (!contentList.contains(saveModelElement))
         {
            contentList.add(saveModelElement);
         }
      }
      if (sourceDiagram != null)
      {
         storage.setSourceDiagram(sourceDiagram);
      }
      if (sourceProcess != null)
      {
         storage.setSourceProcess(sourceProcess);
      }

      storage.setOriginalModelCopy(originalModelCopy);
      storage.setSourceModel(sourceModel);

      contentList.add(storage);
      return contentList;
   }

   // not working for diagram children
   // target must be of type Model!!!
   public static EObject getSameModelElement(EObject source, ModelType target,
         Map changedCache)
   {
      return MergeUtils.getSameModelElement(source, target, changedCache);
   }

   // a symbol must be checked by oid
   public static EObject getSameElement(EObject source, EObject model)
   {
      return MergeUtils.getSameElement(source, model);
   }

   // /////////////////////////

   public static boolean isProcessChildOnly(List copySet)
   {
      for (int i = 0; i < copySet.size(); i++)
      {
         EObject child = (EObject) copySet.get(i);
         if (!(child instanceof ActivityType) && !(child instanceof TriggerType))
         {
            return false;
         }
      }
      return true;
   }

   public static boolean isTypeDeclarationOnly(List copySet)
   {
      for (int i = 0; i < copySet.size(); i++)
      {
         EObject child = (EObject) copySet.get(i);
         if (!(child instanceof TypeDeclarationType))
         {
            return false;
         }
      }
      return true;
   }

   public static boolean isDataOnly(List copySet)
   {
      for (int i = 0; i < copySet.size(); i++)
      {
         EObject child = (EObject) copySet.get(i);
         if (!(child instanceof DataType))
         {
            return false;
         }
      }
      return true;
   }

   public static boolean isParticipantOnly(List copySet)
   {
      for (int i = 0; i < copySet.size(); i++)
      {
         EObject child = (EObject) copySet.get(i);
         if (!(child instanceof IModelParticipant))
         {
            return false;
         }
      }
      return true;
   }

   public static boolean isApplicationOnly(List copySet)
   {
      for (int i = 0; i < copySet.size(); i++)
      {
         EObject child = (EObject) copySet.get(i);
         if (!(child instanceof ApplicationType))
         {
            return false;
         }
      }
      return true;
   }

   public static boolean isProcessDiagramOnly(List copySet,
         ProcessDefinitionType selectedProcess, ModelType originalModel)
   {
      for (int i = 0; i < copySet.size(); i++)
      {
         EObject child = (EObject) copySet.get(i);
         if (!(child instanceof DiagramType))
         {
            return false;
         }
         else
         {
            ProcessDefinitionType process = ModelUtils.findContainingProcess(child);
            if (process == null)
            {
               return false;
            }
            else
            {
               // we are in the same Model
               ProcessDefinitionType originalProcess = (ProcessDefinitionType) CopyPasteUtil
                     .getSameModelElement(process, originalModel, null);
               // compare by object
               if (!originalProcess.equals(selectedProcess))
               {
                  return false;
               }

            }
         }
      }
      return true;
   }

   public static boolean containsDiagram(List copySet)
   {
      for (int i = 0; i < copySet.size(); i++)
      {
         EObject child = (EObject) copySet.get(i);
         if (child instanceof DiagramType)
         {
            return true;
         }
      }
      return false;
   }

   public static boolean containsProcessDiagram(List copySet)
   {
      for (int i = 0; i < copySet.size(); i++)
      {
         EObject child = (EObject) copySet.get(i);
         if (child instanceof DiagramType)
         {
            DiagramType diagram = (DiagramType) child;
            // when comparing by object, we need the object from the same model (not a
            // copy), what is the process in this model?
            ProcessDefinitionType process = ModelUtils.findContainingProcess(diagram);
            if (process != null)
            {
               return true;
            }
         }
      }
      return false;
   }

   public static boolean containsProcessChild(List copySet)
   {
      for (int i = 0; i < copySet.size(); i++)
      {
         EObject child = (EObject) copySet.get(i);
         if (child instanceof ActivityType || child instanceof TriggerType)
         {
            return true;
         }
      }
      return false;
   }

   // //////////////////////////////////

   public static boolean isTypeDeclarationsNode(Object selection)
   {
      if (selection instanceof EditPart)
      {
         Object model = ((EditPart) selection).getModel();
         if (model != null && model instanceof TypeDeclarationsType)
         {
            return true;
         }
      }
      return false;
   }

   public static boolean isDataCategoryNode(Object selection)
   {
      if (selection instanceof ChildCategoryNode)
      {
         if (Arrays.asList(((ChildCategoryNode) selection).getChildrenFeatures())
               .contains(CarnotWorkflowModelPackage.eINSTANCE.getModelType_Data()))
         {
            return true;
         }
      }
      return false;
   }

   public static boolean isParticipantCategoryNode(Object selection)
   {
      if (selection instanceof ChildCategoryNode)
      {
         if (Arrays.asList(((ChildCategoryNode) selection).getChildrenFeatures())
               .contains(
                     CarnotWorkflowModelPackage.eINSTANCE
                           .getModelType_ConditionalPerformer()))
         {
            return true;
         }
         if (Arrays
               .asList(((ChildCategoryNode) selection).getChildrenFeatures())
               .contains(CarnotWorkflowModelPackage.eINSTANCE.getModelType_Organization()))
         {
            return true;

         }
         if (Arrays.asList(((ChildCategoryNode) selection).getChildrenFeatures())
               .contains(CarnotWorkflowModelPackage.eINSTANCE.getModelType_Role()))
         {
            return true;
         }
      }
      return false;
   }

   public static boolean isApplicationCategoryNode(Object selection)
   {
      if (selection instanceof ChildCategoryNode)
      {
         if (Arrays.asList(((ChildCategoryNode) selection).getChildrenFeatures())
               .contains(CarnotWorkflowModelPackage.eINSTANCE.getModelType_Application()))
         {
            return true;
         }
      }
      return false;
   }

   public static ProcessDefinitionType isProcessCategoryNode(Object selection)
   {
      if (selection instanceof EditPart)
      {
         Object model = ((EditPart) selection).getModel();
         if (model instanceof ProcessDefinitionType)
         {
            return (ProcessDefinitionType) model;
         }
      }
      return null;
   }

   public static boolean isModelCategoryNode(Object selection)
   {
      if (selection instanceof ModelTreeEditPart)
      {
         return true;
      }
      return false;
   }

   // ///

   public static void replaceChangedNames(Map<EObject, EObject> changedCache,
         ModelType model)
   {
      for (Entry<EObject, EObject> entry : changedCache.entrySet())
      {
         EObject raw = entry.getKey();
         EObject copy = entry.getValue();
         EObject element = CopyPasteUtil.getSameModelElement(raw, model, changedCache);
         if (element != null)
         {
            if (copy instanceof DiagramType)
            {
               ((DiagramType) element).setName(((DiagramType) copy).getName());
            }
            else if (copy instanceof IIdentifiableElement)
            {
               ((IIdentifiableElement) element).setId(((IIdentifiableElement) copy)
                     .getId());
               ((IIdentifiableElement) element).setName(((IIdentifiableElement) copy)
                     .getName());
            }
            else if (copy instanceof TypeDeclarationType)
            {
               ((TypeDeclarationType) element)
                     .setId(((TypeDeclarationType) copy).getId());
               ((TypeDeclarationType) element).setName(((TypeDeclarationType) copy)
                     .getName());
            }
         }
      }
   }

   public static IModelElement getRawSymbolFromList(List rawSymbols, INodeSymbol copy)
   {
      for (int i = 0; i < rawSymbols.size(); i++)
      {
         IModelElement raw = (IModelElement) rawSymbols.get(i);
         if (raw.getElementOid() == ((IModelElement) copy).getElementOid())
         {
            return raw;
         }
      }
      return null;
   }

   public static String getNewTypeDeclarationId(List allDeclarations, String currentId,
         String prefix)
   {
      StringBuffer generatedString = new StringBuffer(prefix);
      generatedString.append(currentId);
      while (allDeclarations.contains(generatedString.toString()))
      {
         generatedString.insert(0, prefix);
      }
      allDeclarations.add(generatedString.toString());
      return generatedString.toString();
   }

   // find target model from selection
   public static ModelType getTargetModel(Object selectedObject)
   {
      if (selectedObject instanceof EditPart)
      {
         DiagramEditPart diagramEP = null;
         DiagramType diagram = null;
         if (selectedObject instanceof DiagramEditPart
               || selectedObject instanceof DiagramRootEditPart)
         {
            if (selectedObject instanceof DiagramEditPart)
            {
               diagramEP = (DiagramEditPart) selectedObject;
            }
            else if (selectedObject instanceof DiagramRootEditPart)
            {
               diagramEP = (DiagramEditPart) ((DiagramRootEditPart) selectedObject)
                     .getChildren().get(0);
            }
            if (diagramEP != null)
            {
               diagram = (DiagramType) diagramEP.getModel();
            }
            if (diagram != null)
            {
               return ModelUtils.findContainingModel(diagram);
            }
         }
         if (selectedObject instanceof ChildCategoryNode)
         {
            ChildCategoryNode.Spec spec = (Spec) ((ChildCategoryNode) selectedObject)
                  .getModel();
            EObject target = spec.getTarget();
            if (target instanceof Proxy)
            {
               InvocationHandler handler = Proxy.getInvocationHandler(target);
               if (handler instanceof EObjectInvocationHandler)
               {
                  EObject model = ((EObjectInvocationHandler) handler).getModel();
                  return model instanceof ModelType ? (ModelType) model : null;
               }
            }
            return target instanceof ModelType ? (ModelType) target : null;
         }
         else if (selectedObject instanceof ModelTreeEditPart)
         {
            return (ModelType) ((ModelTreeEditPart) selectedObject).getModel();
         }
         Object model = ((EditPart) selectedObject).getModel();
         if (model instanceof ProcessDefinitionType)
         {
            return ModelUtils.findContainingModel((EObject) model);
         }
         if (model instanceof TypeDeclarationsType)
         {
            return (ModelType) ((TypeDeclarationsType) model).eContainer();
         }
      }
      return null;
   }

   public static EObject getEObjectFromSelection(Object element)
   {
      Object modelElement = null;
      if (element instanceof AbstractEObjectTreeEditPart)
      {
         modelElement = ((AbstractEditPart) element).getModel();
      }
      else if (element instanceof AbstractNodeSymbolEditPart)
      {
         modelElement = ((AbstractEditPart) element).getModel();
      }
      else
      {
         modelElement = element;
      }
      if (modelElement instanceof DiagramRootEditPart)
      {
         modelElement = (DiagramEditPart) ((DiagramRootEditPart) modelElement)
               .getChildren().get(0);
      }
      if (modelElement instanceof DiagramEditPart)
      {
         modelElement = ((DiagramEditPart) modelElement).getModel();
      }
      if (modelElement instanceof ChildCategoryNode.Spec)
      {
         return null;
      }
      return (EObject) modelElement;
   }
}