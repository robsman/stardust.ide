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
package org.eclipse.stardust.modeling.core.search.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.DeleteAllAction;
import org.eclipse.ui.IWorkbenchPart;


public class DeleteSelectedAction extends DeleteAllAction
{
   private WorkflowModelEditor editor;
   private StructuredSelection structuredSelection;
   private List selectedElements;
   
   public DeleteSelectedAction(IWorkbenchPart part)
   {
      super(part);
      editor = (WorkflowModelEditor) part;
   }

   public void update()
   {
      setSelection(structuredSelection);
   }

   // from elements create a List of edit parts for the model elements as needed by DeleteAllAction
   public void setSelectedElements(Set checkedItems)
   {
      selectedElements = new ArrayList();
      for (Iterator i = checkedItems.iterator(); i.hasNext();)
      {
         EObject model = (EObject) i.next();   
         EditPart modelEditPart = editor.findEditPart(model);
         if(modelEditPart != null)
         {
            selectedElements.add(modelEditPart);
         }
      }
      if(!selectedElements.isEmpty())
      {
         structuredSelection = new StructuredSelection(selectedElements);
      }
      else
      {
         structuredSelection = StructuredSelection.EMPTY;
      }
   }   
   
   protected void setSelection(ISelection selection)
   {
      super.setSelection(selection);
   }

   protected boolean calculateEnabled()
   {
      return super.calculateEnabled();
   }

   public void run()
   {
      super.run();
   }
}