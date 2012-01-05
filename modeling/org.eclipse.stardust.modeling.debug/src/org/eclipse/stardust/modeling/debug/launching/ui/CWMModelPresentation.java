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
package org.eclipse.stardust.modeling.debug.launching.ui;

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugEditorPresentation;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.debug.debugger.UiAccessor;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.highlighting.HighlightManager;
import org.eclipse.stardust.modeling.debug.model.CWMStackFrame;
import org.eclipse.stardust.modeling.debug.util.WorkflowModelUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

public class CWMModelPresentation implements IDebugModelPresentation, IDebugEditorPresentation
{
   private boolean containsActivitySymbol(DiagramType diagram, ActivityType activity)
   {
      if (null != diagram && null != activity)
      {
         List activitySymbols = WorkflowModelUtils.getActivitySymbols(diagram, activity);
         for (Iterator iter = activitySymbols.iterator(); iter.hasNext();)
         {
            ActivitySymbolType symbol = (ActivitySymbolType) iter.next();

            if (symbol.getActivity().equals(activity))
            {
               return true;
            }
         }
      }

      return false;
   }
   
   private boolean showDiagramContainingActivity(WorkflowModelEditor editor,
         ActivityType activity) throws PartInitException
   {
      // search for activity symbol in active diagram
      boolean found = containsActivitySymbol(editor.getActiveDiagram(), activity);

      // search for activity symbol in other opened diagrams
      if (!found)
      {
         List diagrams = editor.getOpenedDiagrams();
         for (Iterator diagIter = diagrams.iterator(); diagIter.hasNext();)
         {
            DiagramType diagram = (DiagramType) diagIter.next();
            if (!editor.isActiveDiagram(diagram))
            {
               found = containsActivitySymbol(diagram, activity);
               if (found)
               {
                  editor.showDiagramPage(diagram);
                  break;
               }
            }
         }
      }

      // search for activity symbol in not opened diagrams
      if (!found)
      {
         ProcessDefinitionType processdefinition = (ProcessDefinitionType) activity.eContainer();
         EList allDiagrams = processdefinition.getDiagram();
         for (Iterator diagIter = allDiagrams.iterator(); diagIter.hasNext();)
         {
            DiagramType diagram = (DiagramType) diagIter.next();
            if ( !editor.getOpenedDiagrams().contains(diagram))
            {
               found = containsActivitySymbol(diagram, activity);
               if (found)
               {
                  editor.showDiagramPage(diagram);
                  break;
               }
            }
         }
      }

      return found;
   }

   public boolean addAnnotations(IEditorPart editorPart, IStackFrame frame)
   {
      CWMStackFrame stackFrame = (CWMStackFrame) frame;

      final ActivityInstanceDigest ai = stackFrame.getActivityInstance();
      try
      {
         if (null != ai)
         {
            WorkflowModelEditor editor = (WorkflowModelEditor) editorPart;
            ModelType model = editor.getWorkflowModel();

            QName qproc = QName.valueOf(ai.getProcessDefinitionId());
            String namespace = qproc.getNamespaceURI();
            if (!namespace.equals(model.getId()))
            {
               editor = UiAccessor.getEditorForModel(CollectionUtils.<String>newSet(), model, namespace);
               if (editor == null)
               {
                  throw new RuntimeException("TO IMPLEMENT SURGE DEBUG");
               }
               model = editor.getWorkflowModel();
            }
            ProcessDefinitionType processDefinition = WorkflowModelUtils.findProcessDefinition(model,
                  qproc.getLocalPart());

            QName qact = QName.valueOf(ai.getActivityId());
            if (!qact.getNamespaceURI().equals(model.getId()))
            {
               throw new RuntimeException("TO IMPLEMENT SURGE DEBUG");
            }
            ActivityType activity = WorkflowModelUtils.findActivity(processDefinition,
                  qact.getLocalPart());

            boolean found = showDiagramContainingActivity(editor, activity);
            if (found)
            {
               // Do highlighting in its own thread. This will prevent deadlocking in
               // eclipse33.
               new Thread(new Runnable()
               {
                  public void run()
                  {
                     HighlightManager.getDefault().setCurrentSelected(ai);
                  }
               }).start();

               EditPart editPart = null;
               List<ActivitySymbolType> symbols = activity.getActivitySymbols();
               for (ActivitySymbolType symbol : symbols)
               {
                  editPart = editor.findEditPart(symbol);
                  if (editPart != null)
                  {
                     editPart.getParent().getViewer().reveal(editPart);
                     break;
                  }
               }
            }
            else
            {
               throw new RuntimeException("No activity symbol found for: " + activity);
            }

            return found;
         }
      }
      catch (PartInitException e)
      {
         e.printStackTrace();
      }

      return false;
   }

   public void removeAnnotations(IEditorPart editorPart, IThread thread)
   {
      System.out.println("To Remove Annotations");
   }

   public void setAttribute(String attribute, Object value)
   {
   }

   public Image getImage(Object element)
   {
      return null;
   }

   public String getText(Object element)
   {
      return null;
   }

   public void computeDetail(IValue value, IValueDetailListener listener)
   {
   }

   public void addListener(ILabelProviderListener listener)
   {
   }

   public void dispose()
   {
   }

   public boolean isLabelProperty(Object element, String property)
   {
      return false;
   }

   public void removeListener(ILabelProviderListener listener)
   {
   }

   public IEditorInput getEditorInput(Object element)
   {
      if (element instanceof IFile)
      {
         return new FileEditorInput((IFile) element);
      }

      return null;
   }

   public String getEditorId(IEditorInput input, Object element)
   {
      if (element instanceof IFile)
      {
         return DiagramPlugin.CARNOT_WORKFLOW_MODEL_EDITOR_ID;
      }

      return null;
   }
}
