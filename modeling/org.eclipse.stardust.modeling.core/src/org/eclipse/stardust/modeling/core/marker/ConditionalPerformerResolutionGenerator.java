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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.ui.IMarkerResolution;


public class ConditionalPerformerResolutionGenerator implements IResolutionGenerator
{
   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      // TODO: enable when implemented
      return false; //issue.getModelElement() instanceof ConditionalPerformerType;
   }

   public void addResolutions(List<IMarkerResolution> list, WorkflowModelEditor editor, Issue issue)
   {
      if (issue.getFeature().equals(
            ValidationService.PKG_CWM.getConditionalPerformerType_Data()))
      {
         list.add(new MarkerResolution(getAction(Diagram_Messages.LB_ACTION_SetData)));
      }
      else if (issue.getFeature().equals(
            ValidationService.PKG_CWM.getConditionalPerformerType_DataPath()))
      {
         list.add(new MarkerResolution(getAction(Diagram_Messages.LB_ACTION_SetDataPath)));
      }
   }

   private IAction getAction(String label)
   {
      return new MyAction(label);
   }

   private class MyAction extends Action
   {
      public MyAction(String label)
      {
         super(label);
      }
   }
}
