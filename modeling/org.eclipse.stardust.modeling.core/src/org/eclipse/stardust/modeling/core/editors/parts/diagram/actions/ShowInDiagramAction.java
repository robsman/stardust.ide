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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.SelectionPopup;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;


public class ShowInDiagramAction extends SelectionAction
{
   private WorkflowModelEditor editor;

   public ShowInDiagramAction(IWorkbenchPart part)
   {
      super(part);
      this.editor = (WorkflowModelEditor) part;
      setText(Diagram_Messages.LB_ShowInDiagram);
      setToolTipText(Diagram_Messages.LB_ShowInDiagram);
      setId(DiagramActionConstants.SHOW_IN_DIAGRAM);
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/diagram.gif")); //$NON-NLS-1$
   }

   protected boolean calculateEnabled()
   {
      List selection = getSelectedObjects();
      if(selection.size() != 1)
      {
         return false;
      }
      Object element = getSelectedObjects().get(0);
      // must be outline
      if(!(element instanceof AbstractEObjectTreeEditPart
            || element instanceof IModelElement))
      {
         return false;
      }
      List symbols = getSymbols(element);
      if(symbols.size() > 0) 
      {
         return true;
      }      
      return false;
   }

   private Set getDiagrams(Object element)
   {
      Set diagrams = new HashSet();
      List symbols = getSymbols(element);
      for(Iterator iter = symbols.iterator(); iter.hasNext();)
      {
         Object symbol = iter.next();
         DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) symbol);
         diagrams.add(diagram);
      }
      return diagrams;
   }
   
   private List getSymbols(Object element)
   {
      Object modelElement = null;
      if(element instanceof AbstractEObjectTreeEditPart)
      {
         modelElement = ((AbstractEditPart) element).getModel();
      }
      else if(element instanceof IModelElement)
      {
         modelElement = element;
      }
      if(modelElement instanceof ApplicationType
            || modelElement instanceof DataType
            || modelElement instanceof IModelParticipant
            || modelElement instanceof ActivityType
            || modelElement instanceof TriggerType
            || modelElement instanceof LinkTypeType
            || modelElement instanceof ProcessDefinitionType)
      {
         return ((IIdentifiableModelElement) modelElement).getSymbols();
      }
      else if(modelElement instanceof TransitionType)
      {
         return ((TransitionType) modelElement).getTransitionConnections();
      }
      return Collections.EMPTY_LIST;
   }

   private List getSymbolsForDiagram(List symbols, DiagramType diagram)
   {
      List diagramSymbols = new ArrayList();
      for(Iterator iter = symbols.iterator(); iter.hasNext();)
      {
         Object symbol = iter.next();
         DiagramType symbolDiagram = ModelUtils.findContainingDiagram((IGraphicalObject) symbol);
         if(symbolDiagram.equals(diagram))
         {
            diagramSymbols.add(symbol);
         }
      }
      return diagramSymbols;      
   }   
   
   public void run()
   {
      Object element = getSelectedObjects().get(0);
      List symbols = getSymbols(element);
      Set diagrams = getDiagrams(element);
      DiagramType diagram;
      if(diagrams.size() > 1)
      {
         SelectionPopup popup = new SelectionPopup(editor.getSite().getShell());
         popup.setContentProvider(new ArrayContentProvider());
         popup.setLabelProvider(new LabelProvider()
         {
            public Image getImage(Object element)
            {
               if (element instanceof EObject)
               {
                  return DiagramPlugin.getImage(editor.getIconFactory().getIconFor((EObject) element));
               }
               return super.getImage(element);
            }

            public String getText(Object element)
            {
               if (element instanceof DiagramType)
               {
                  String diagramName = ((DiagramType) element).getName();
                  ProcessDefinitionType process  = ModelUtils.findContainingProcess((EObject) element);
                  if(process != null)
                  {
                     return diagramName + " (" + process.getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
                  }
                  return diagramName;
               }
               return super.getText(element);
            }
         });
         popup.setInput(diagrams);
         diagram = (DiagramType) popup.open();
         if(diagram == null)
         {
            return;
         }
         else
         {
            List diagramSymbols = getSymbolsForDiagram(symbols, diagram);
            symbols = diagramSymbols;
         }
      }  
      else
      {
         Object[] content = diagrams.toArray();
         diagram = (DiagramType) content[0];
      }
      editor.selectSymbols(symbols, diagram);
   }   
}