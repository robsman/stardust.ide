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
package org.eclipse.stardust.modeling.core.marker;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.UpgradeDataAction;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.ui.IMarkerResolution;


public class DataResolutionGenerator implements IResolutionGenerator
{
   private IAction action;

   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      if (issue.getModelElement() instanceof DataType)
      {
         action = getAction(editor, issue.getModelElement(),
               DiagramActionConstants.DATA_UPGRADE);
         if (action != null && action.isEnabled())
         {
            return true;
         }
      }
      return false;
   }

   public void addResolutions(List<IMarkerResolution> list, WorkflowModelEditor editor, Issue issue)
   {
      list.add(new MarkerResolution(action));
   }

   private IAction getAction(final WorkflowModelEditor editor,
         final EObject element, String actionId)
   {
      if (actionId == DiagramActionConstants.DATA_UPGRADE)
      {
         return new UpgradeDataAction(editor)
         {
            protected ISelection getSelection()
            {
               EditPart editPart = editor.findEditPart(element);
               return editPart == null ? StructuredSelection.EMPTY : new StructuredSelection(editPart);
            }
         };
      }
      return null;
   }
}