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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.UpdateDiagramAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.ui.IMarkerResolution;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DiagramResolutionGenerator implements IResolutionGenerator
{
   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      return issue.getModelElement() instanceof DiagramType
            || CarnotWorkflowModelPackage.eINSTANCE.getIConnectionSymbol().equals(
                  issue.getFeature());
   }

   public void addResolutions(List<IMarkerResolution> list, final WorkflowModelEditor editor,
         final Issue issue)
   {
      if (issue.getFeature() == null)
      {
         IAction action = getAction(editor, issue.getModelElement(),
               DiagramActionConstants.DIAGRAM_UPDATE);
         if (action != null)
         {
            list.add(new MarkerResolution(action));
         }
      }
      if (CarnotWorkflowModelPackage.eINSTANCE.getIConnectionSymbol().equals(
            issue.getFeature()))
      {
         final EObject modelElement = issue.getModelElement();
         if (modelElement instanceof IConnectionSymbol)
         {
            list.add(new MarkerResolution(
                  new Action(Diagram_Messages.MSG_DeleteConnectionResolution)
                  {
                     public void run()
                     {
                        editor.getEditDomain().getCommandStack().execute(
                              new DeleteConnectionSymbolCmd(
                                    (IConnectionSymbol) modelElement));
                     }
                  }));
         }
      }
   }

   private IAction getAction(final WorkflowModelEditor editor,
         final EObject element, String actionId)
   {
      if (actionId == DiagramActionConstants.DIAGRAM_UPDATE)
      {
         return new UpdateDiagramAction(editor)
         {
            protected List<?> getSelectedObjects()
            {
               return Collections.singletonList(editor.findEditPart(element));
            }
         };
      }
      return null;
   }
}