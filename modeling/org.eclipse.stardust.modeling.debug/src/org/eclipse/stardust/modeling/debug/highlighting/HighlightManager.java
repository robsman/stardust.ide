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
package org.eclipse.stardust.modeling.debug.highlighting;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.FilteringIterator;
import org.eclipse.stardust.common.Functor;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.TransformingIterator;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.IDiagramChangeListener;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractModelElementNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.IHighliteableGraphicalObject;
import org.eclipse.stardust.modeling.core.highlighting.HighlightState;
import org.eclipse.stardust.modeling.core.highlighting.HighlightUtils;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.debugger.UiAccessor;
import org.eclipse.stardust.modeling.debug.util.EmptyIterator;
import org.eclipse.stardust.modeling.debug.util.WorkflowModelUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorSite;

public class HighlightManager implements IPartListener, IDiagramChangeListener
{
   private static HighlightManager highlightManager;
   
   private Map<IHighlightable, HighlightState> highlightedElements = CollectionUtils.newMap();
   private Map<WorkflowModelEditor, List<DiagramType>> editorDiagrams = CollectionUtils.newMap();
   
   private IHighlightable currentSelected = null;
   
   private HighlightManager()
   {
      IWorkbench workbench = PlatformUI.getWorkbench();
      IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
      for (int windowIdx = 0; windowIdx < windows.length; ++windowIdx)
      {
         IWorkbenchPage[] pages = windows[windowIdx].getPages();
         for (int pageIdx = 0; pageIdx < pages.length; ++pageIdx)
         {
            pages[pageIdx].addPartListener(this);
            IEditorReference[] editorRefs = pages[pageIdx].getEditorReferences();
            for (int editorIdx = 0; editorIdx < editorRefs.length; ++editorIdx)
            {
               IEditorPart editor = editorRefs[editorIdx].getEditor(false);
               if (null != editor && editor instanceof WorkflowModelEditor)
               {
                  addWorkflowModelEditor((WorkflowModelEditor) editor);
               }
            }
         }
      }
   }
   
   private Iterator getEditPartIterator(WorkflowModelEditor editor,
         DiagramType diagram, final IHighlightable highlightable)
   {
      ModelType model = editor.getWorkflowModel();
      final String processDefinitionId = highlightable.getProcessDefinitionId();
      ProcessDefinitionType processDefinition = null;
      if (processDefinitionId != null)
      {
         QName qproc = QName.valueOf(processDefinitionId);
         String namespace = qproc.getNamespaceURI();
         if ( !namespace.equals(model.getId()))
         {
            throw new RuntimeException(MessageFormat.format(
                  Debug_Messages.EXP_CannotFindEditorForModelNamespace,
                  new Object[] { namespace }));
         }
         processDefinition = WorkflowModelUtils.findProcessDefinition(model, qproc.getLocalPart());
      }
      if (null == processDefinition)
      {
         return new EmptyIterator();
      }
      
      final String processDefinitionChildId = highlightable.getProcessDefinitionChildId();
      IIdentifiableModelElement modelElement = null;
      if (processDefinitionChildId != null)
      {
         QName qchild = QName.valueOf(processDefinitionChildId);
         if ( !qchild.getNamespaceURI().equals(model.getId()))
         {
            throw new RuntimeException(MessageFormat.format(
                  Debug_Messages.EXP_CannotFindEditorForModelNamespace,
                  new Object[] { qchild.getNamespaceURI() }));
         }
         String childId = qchild.getLocalPart();
         List contentList = processDefinition.eContents();
         for (Iterator contentIter = contentList.iterator(); contentIter.hasNext();)
         {
            Object rawContent = contentIter.next();
            if (rawContent instanceof IIdentifiableModelElement)
            {
               modelElement = (IIdentifiableModelElement) rawContent; 
               if (childId.equals(modelElement.getId()))
               {
                  break;
               }
            }
         }
      }

      Iterator diagramElementIterator = null;
      if (modelElement instanceof ActivityType)
      {
         diagramElementIterator = WorkflowModelUtils
               .getActivitySymbols(diagram, (ActivityType)modelElement).iterator();
      }
      else if (modelElement instanceof TransitionType)
      {
         diagramElementIterator = WorkflowModelUtils.getTransitionConnections(diagram,
               (TransitionType)modelElement).iterator();
      }
      else
      {
         return new EmptyIterator();
      }

      Iterator iterator = new FilteringIterator(diagramElementIterator, new Predicate()
      {
         public boolean accept(Object o)
         {
            if (o instanceof IModelElement)
            {
               IModelElement modelElement = (IModelElement) o;
               
               String pdId = Constants.EMPTY;
               String childId = Constants.EMPTY;
   
               if (modelElement instanceof ActivitySymbolType)
               {
                  ActivitySymbolType activitySymbol = (ActivitySymbolType) modelElement;
                  ActivityType activity = activitySymbol.getActivity();
                  ProcessDefinitionType process = (ProcessDefinitionType) activity.eContainer();
                  ModelType model = (ModelType) process.eContainer();
                  childId = '{' + model.getId() + '}' + activity.getId();
                  pdId = '{' + model.getId() + '}' + process.getId();
                  
               }
               else if (modelElement instanceof TransitionConnectionType)
               {
                  TransitionConnectionType transConnection = (TransitionConnectionType) modelElement;
                  TransitionType transition = transConnection.getTransition();
                  if (null != transition)
                  {
                     ProcessDefinitionType process = (ProcessDefinitionType) transition.eContainer();
                     ModelType model = (ModelType) process.eContainer();
                     childId = '{' + model.getId() + '}' + transition.getId();
                     pdId = '{' + model.getId() + '}' + process.getId();
                  }
               }
               if (childId.equals(processDefinitionChildId)&&
                      pdId.equals(processDefinitionId))
               {
                  return true;
               }
            }
            return false;
         }
      });

      final WorkflowModelEditor modelEditor = editor;
      return new TransformingIterator(iterator, new Functor()
      {
         public Object execute(Object source)
         {
            if (source instanceof IModelElement)
            {
               return UiAccessor.findEditPart(modelEditor, source);
            }
            return null;
         }
      });
   }
   
   public static HighlightManager getDefault()
   {
      if (null == highlightManager)
      {
         highlightManager = new HighlightManager();
      }
      
      return highlightManager;
   }
   
   public void setHighlightState(IHighlightable highlightable, HighlightState state)
   {
      synchronized (highlightedElements)
      {
         if (highlightable.equals(currentSelected))
         {
            currentSelected = null;
         }
         highlightedElements.put(highlightable, state);
         doHighlighting(highlightable, state);
      }
   }
   
   public void setCurrentSelected(IHighlightable highlightable)
   {
      boolean found = false;
      synchronized (highlightedElements)
      {
         if (highlightedElements.containsKey(highlightable))
         {
            IHighlightable previous = currentSelected;
            currentSelected = highlightable;
            
            if (null != previous)
            {
               doHighlighting(previous, (HighlightState) highlightedElements.get(previous));
            }
            
            if (null != currentSelected)
            {
               found = doHighlighting(currentSelected, (HighlightState) highlightedElements.get(currentSelected));
            }
         }
      }
      if (!found && null != currentSelected)
      {
         String processId = highlightable.getProcessDefinitionId();
         if (processId != null)
         {
            QName qproc = QName.valueOf(processId);
            String namespace = qproc.getNamespaceURI();
            if (!StringUtils.isEmpty(namespace))
            {
               IEditorPart activeEditor = UiAccessor.getActiveEditPart();
               if (activeEditor instanceof WorkflowModelEditor)
               {
                  ModelType model = ((WorkflowModelEditor) activeEditor).getWorkflowModel();
                  WorkflowModelEditor editor = UiAccessor.getEditorForModel(CollectionUtils.<String>newSet(), model, namespace);
                  if (editor == null)
                  {
                     throw new RuntimeException(MessageFormat.format(
                           Debug_Messages.EXP_CannotFindEditorForModelNamespace,
                           new Object[] { namespace }));
                  }
               }
            }
         }
      }
   }
   
   public IHighlightable getCurrentSelected()
   {
      synchronized (highlightedElements)
      {
         return currentSelected;
      }
   }
   
   private void doHighlighting(WorkflowModelEditor modelEditor, DiagramType diagram)
   {
      // TODO
      synchronized (highlightedElements)
      {
         for (Map.Entry<IHighlightable, HighlightState> entry : highlightedElements.entrySet())
         {
            IHighlightable highlightable = entry.getKey();
            String processId = highlightable.getProcessDefinitionId();
            if (processId != null)
            {
               QName qproc = QName.valueOf(processId);
               String namespace = qproc.getNamespaceURI();
               ModelType model = modelEditor.getWorkflowModel();
               if (StringUtils.isEmpty(namespace) || namespace.equals(model.getId()))
               {
                  doHighlighting(modelEditor, diagram, highlightable, entry.getValue());
               }
            }
         }
      }
   }
   
   private boolean doHighlighting(IHighlightable highlightable, HighlightState state)
   {
      boolean found = false;
      String processId = highlightable.getProcessDefinitionId();
      if (processId != null)
      {
         QName qproc = QName.valueOf(processId);
         String namespace = qproc.getNamespaceURI();
         for (Map.Entry<WorkflowModelEditor, List<DiagramType>> entry : editorDiagrams.entrySet())
         {
            WorkflowModelEditor editor = entry.getKey();
            ModelType model = editor.getWorkflowModel();
            if (StringUtils.isEmpty(namespace) || namespace.equals(model.getId()))
            {
               found = true;
               for (DiagramType diagram : entry.getValue())
               {
                  doHighlighting(editor, diagram, highlightable, state);
               }
            }
         }
      }
      return found;
   }
   
   private void doHighlighting(WorkflowModelEditor modelEditor, DiagramType diagram,
         IHighlightable highlightable, HighlightState state)
   {
      Iterator editPartIter = getEditPartIterator(modelEditor, diagram, highlightable);
      while (editPartIter.hasNext())
      {
         Object rawEditPart = editPartIter.next();
         if (rawEditPart instanceof IHighliteableGraphicalObject)
         {
            if (highlightable.equals(currentSelected))
            {
               highlightGraphicalObject((IHighliteableGraphicalObject) rawEditPart,
                     HighlightState.SELECTED_LITERAL);
            }
            else
            {
               highlightGraphicalObject((IHighliteableGraphicalObject) rawEditPart, state);
            }
            // add Gateways and the Connections between Gateways and Activities (workaround)
            if(rawEditPart instanceof AbstractModelElementNodeSymbolEditPart) {
               Object modelElement = ((AbstractModelElementNodeSymbolEditPart) rawEditPart).getModel();                
               if(modelElement instanceof ActivitySymbolType) {                  
                  ActivitySymbolType activitySymbol = (ActivitySymbolType) modelElement;                                         
                  for(Iterator gsi = activitySymbol.getGatewaySymbols().iterator();gsi.hasNext();) {
                     GatewaySymbol gatewaySymbol = (GatewaySymbol) gsi.next();
                     // what Gateway is it (only join)?
                     FlowControlType flowType = gatewaySymbol.getFlowKind();
                     List connections = new ArrayList();
                     if(flowType.equals(FlowControlType.JOIN_LITERAL)) {
                        highlightGraphicalObject((IHighliteableGraphicalObject) UiAccessor.findEditPart(modelEditor, gatewaySymbol), state);                     
                        connections.addAll(gatewaySymbol.getOutTransitions());                  
                     }
                     // the connection between the gateway and the activity it belongs to
                     if(connections.size() > 0) {
                        for(int ci = 0; ci < connections.size(); ci++) {
                           TransitionConnectionType transitionConnection = (TransitionConnectionType) connections.get(ci);
                           highlightGraphicalObject((IHighliteableGraphicalObject) UiAccessor.findEditPart(modelEditor, transitionConnection), state);                     
                        }
                     }
                  }
               }
            } else if(rawEditPart instanceof AbstractConnectionSymbolEditPart) {
               Object modelElement = ((AbstractConnectionSymbolEditPart) rawEditPart).getModel(); 
               if(modelElement instanceof TransitionConnectionType) {
                  TransitionConnectionType transitionConnection = (TransitionConnectionType) modelElement;
                  if(transitionConnection.getSourceActivitySymbol() instanceof GatewaySymbol) {
                     GatewaySymbol gatewaySymbol = (GatewaySymbol) transitionConnection.getSourceActivitySymbol();
                     FlowControlType flowType = gatewaySymbol.getFlowKind();
                     // take only split 
                     if(flowType.equals(FlowControlType.SPLIT_LITERAL)) {
                        // if it is the color for ACTIVE it has to be the color of DONE
                        if(state.equals(HighlightState.ACTIVE_LITERAL)) {
                           highlightGraphicalObject((IHighliteableGraphicalObject) UiAccessor.findEditPart(modelEditor, gatewaySymbol), HighlightState.DONE_LITERAL);
                        } else {
                           highlightGraphicalObject((IHighliteableGraphicalObject) UiAccessor.findEditPart(modelEditor, gatewaySymbol), state);                           
                        }
                        // the connection between the gateway and the activity it belongs to
                        List inTransitions = gatewaySymbol.getInTransitions();
                        Iterator iterator = inTransitions.iterator();
                        while(iterator.hasNext()) {
                           Object entry = iterator.next();
                           if(entry instanceof TransitionConnectionType) {
                              if(state.equals(HighlightState.ACTIVE_LITERAL)) {
                                 highlightGraphicalObject((IHighliteableGraphicalObject) UiAccessor.findEditPart(modelEditor, (TransitionConnectionType) entry), HighlightState.DONE_LITERAL);
                              } else {
                                 highlightGraphicalObject((IHighliteableGraphicalObject) UiAccessor.findEditPart(modelEditor, (TransitionConnectionType) entry), state);
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
   
   public void removeHighlightable(IHighlightable highlightable)
   {
      synchronized (highlightedElements)
      {
         if (highlightedElements.containsKey(highlightable))
         {
            if (highlightable.equals(currentSelected))
            {
               currentSelected = null;
            }
            highlightedElements.remove(highlightable);
            doHighlighting(highlightable, HighlightState.DEFAULT_LITERAL);
         }
      }
   }
   
   public void removeAllHighlightables()
   {
      synchronized (highlightedElements)
      {
         currentSelected = null;
         for (IHighlightable highlightable : highlightedElements.keySet())
         {
            doHighlighting(highlightable, HighlightState.DEFAULT_LITERAL);
         }
         highlightedElements.clear();
      }
   }

   public void partActivated(IWorkbenchPart part)
   {
   }

   public void partBroughtToTop(IWorkbenchPart part)
   {
   }

   public void partClosed(IWorkbenchPart part)
   {
      if (part instanceof WorkflowModelEditor)
      {
         WorkflowModelEditor modelEditor = (WorkflowModelEditor) part;
         modelEditor.removeDiagramChangeListener(this);
         editorDiagrams.remove(modelEditor);
      }
   }

   public void partDeactivated(IWorkbenchPart part)
   {
   }

   public void partOpened(IWorkbenchPart part)
   {
      if (part instanceof WorkflowModelEditor)
      {
         addWorkflowModelEditor((WorkflowModelEditor) part);
      }
   }

   public void diagramPageChanged(DiagramEditorPage page)
   {
      if (null != page)
      {
         WorkflowModelEditor editor = getModelEditor(page);
         if (true == addDiagram(editor, page.getDiagram()))
         {
            // TODO fix
            synchronized (highlightedElements)
            {
               doHighlighting(editor, page.getDiagram());
            }
         }
      }
   }
   
   public void diagramPageClosed(DiagramEditorPage page)
   {
      // (fh) do nothing
   }

   public void diagramPageOpened(DiagramEditorPage page)
   {
      // (fh) do nothing
   }

   private WorkflowModelEditor getModelEditor(DiagramEditorPage page)
   {
      if (null != page)
      {
         IWorkbenchPartSite site = page.getSite();
         if (site instanceof MultiPageEditorSite)
         {
            IEditorPart editorPart = ((MultiPageEditorSite) site).getMultiPageEditor();
            if (editorPart instanceof WorkflowModelEditor)
            {
               return (WorkflowModelEditor) editorPart;
            }
         }
      }
      
      return null;
   }
   
   private boolean addDiagram(WorkflowModelEditor modelEditor, DiagramType diagram)
   {
      if (editorDiagrams.containsKey(modelEditor))
      {
         List diagramList = (List) editorDiagrams.get(modelEditor);
         if (!diagramList.contains(diagram))
         {
            diagramList.add(diagram);
            return true;
         }
      }
      return false;
   }
   
   private void addWorkflowModelEditor(WorkflowModelEditor modelEditor)
   {
      modelEditor.addDiagramChangeListener(this);
      List<DiagramType> diagramList = CollectionUtils.newList();
      diagramList.addAll(modelEditor.getOpenedDiagrams());
      editorDiagrams.put(modelEditor, diagramList);
      
      // TODO: fix
      for (DiagramType diagram : diagramList)
      {
         synchronized (highlightedElements)
         {
            doHighlighting(modelEditor, diagram);
         }
      }
   }
   
   private static void highlightGraphicalObject(IHighliteableGraphicalObject object, HighlightState state)
   {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      HighlightUtils.GraphicalObjectHighlighter highlighter = new HighlightUtils.GraphicalObjectHighlighter(
            object, state);

      // syncExec() will block the current thread as long as Runnable.run()
      // has not been executed.
      workbench.getDisplay().syncExec(highlighter);
      RuntimeException x = highlighter.getException();
      
      if (null != x)
      {
         x.printStackTrace();
      }
   }

}
