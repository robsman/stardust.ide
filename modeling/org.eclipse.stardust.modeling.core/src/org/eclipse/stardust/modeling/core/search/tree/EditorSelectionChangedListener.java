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
package org.eclipse.stardust.modeling.core.search.tree;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;


public class EditorSelectionChangedListener implements ISelectionChangedListener
{
   private WorkflowModelEditor editor;
   private ISelectionProvider provider;
   
   public EditorSelectionChangedListener(ISelectionProvider page)
   {
      provider = page;
   }

   public void setEditor(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public void selectionChanged(SelectionChangedEvent event)
   {
      EditPart selectedEP = null;
      Object obj = ((IStructuredSelection) event.getSelection()).getFirstElement();
      if (obj instanceof IModelElementNodeSymbol
            || obj instanceof IModelElement)
      {
         selectedEP = editor.findEditPart(obj);               
      }
      else if(obj instanceof AbstractEObjectTreeEditPart)
      {
         Object model = ((AbstractEObjectTreeEditPart) obj).getModel();
         if(model instanceof EObject)
         {
            selectedEP = editor.findEditPart(model);               
         }
      }      
      if(selectedEP != null)
      {
         StructuredSelection useSelection = new StructuredSelection(selectedEP);
         provider.setSelection(useSelection);
      }
      else
      {
         provider.setSelection(null);               
      }
   }
}