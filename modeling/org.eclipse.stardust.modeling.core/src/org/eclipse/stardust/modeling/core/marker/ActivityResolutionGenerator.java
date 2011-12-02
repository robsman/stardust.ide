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
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.LoopType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.ui.IMarkerResolution;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ActivityResolutionGenerator implements IResolutionGenerator
{
   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      // TODO enable after implementing actions
      if (issue.getModelElement() instanceof ActivityType)
      {
         return /*issue.getFeature().equals
            (ValidationService.PKG_CWM.getTransitionType_To())
         || issue.getFeature().equals
            (ValidationService.PKG_CWM.getTransitionType_From())
         ||*/ issue.getFeature().equals
            (ValidationService.PKG_CWM.getActivityType_LoopCondition());
      }
      return false;
   }

   public void addResolutions(List<IMarkerResolution> list, final WorkflowModelEditor editor, final Issue issue)
   {
      /*if (issue.getFeature().equals
            (ValidationService.PKG_CWM.getTransitionType_To()))
      {
         list.add(new MarkerResolution(getAction(Diagram_Messages.LB_ACTION_RemoveTransition))); 
         list.add(new MarkerResolution(getAction(Diagram_Messages.LB_ACTION_ChangeJoinType))); 
      }

      else if (issue.getFeature().equals
            (ValidationService.PKG_CWM.getTransitionType_From()))
      {
         list.add(new MarkerResolution(getAction(Diagram_Messages.LB_ACTION_RemoveTransition))); 
         list.add(new MarkerResolution(getAction(Diagram_Messages.LB_ACTION_ChangeSplitType))); 
      }
      else*/ if (issue.getFeature().equals
            (ValidationService.PKG_CWM.getActivityType_LoopCondition()))
      {
         list.add(new MarkerResolution(new Action(Diagram_Messages.LB_ACTION_NoLoop)
         {
            public void run()
            {
               SetValueCmd cmd = new SetValueCmd(issue.getModelElement(),
                  ValidationService.PKG_CWM.getActivityType_LoopType(),
                  LoopType.NONE_LITERAL);
               editor.getEditDomain().getCommandStack().execute(cmd);
            }
         }));
      }
   }

/*   private IAction getAction(String label)
   {
      return new MyAction(label);
   }*/

/*   private class MyAction extends Action
   {
      public MyAction(String label)
      {
         super(label);
      }
   }*/
}
