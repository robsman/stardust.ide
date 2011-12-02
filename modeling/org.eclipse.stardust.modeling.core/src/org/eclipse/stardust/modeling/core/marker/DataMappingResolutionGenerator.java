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
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.ui.IMarkerResolution;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DataMappingResolutionGenerator implements IResolutionGenerator
{
   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      if (issue.getModelElement() instanceof DataMappingType)
      {
         return issue.getFeature().equals
            (ValidationService.PKG_CWM.getDataMappingType_Context());
      }
      return false;
   }

   public void addResolutions(List<IMarkerResolution> list, final WorkflowModelEditor editor, final Issue issue)
   {
      if (issue.getFeature().equals
            (ValidationService.PKG_CWM.getDataMappingType_Context()))
      {
         list.add(new MarkerResolution(new Action(Diagram_Messages.LB_ACTION_DelDataMapping)
         {
            public void run()
            {
               DataMappingType mapping = (DataMappingType) issue.getModelElement();
               ActivityType activity = (ActivityType) mapping.eContainer();
               DeleteValueCmd cmd = new DeleteValueCmd(activity, mapping,
                  ValidationService.PKG_CWM.getActivityType_DataMapping());
               editor.getEditDomain().getCommandStack().execute(cmd);
            }
         }));
      }
   }
}
