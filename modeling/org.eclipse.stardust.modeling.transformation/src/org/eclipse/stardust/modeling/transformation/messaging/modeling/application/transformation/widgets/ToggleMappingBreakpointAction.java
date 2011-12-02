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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.IMessageTransformationApplicationView;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.breakpoints.MessageTransformationMappingBreakpoint;

import  com.infinity.bpm.messaging.model.mapping.FieldMapping;

public class ToggleMappingBreakpointAction extends Action
{
   private MessageTransformationController controller;
   private IMessageTransformationApplicationView view;

   public ToggleMappingBreakpointAction(IMessageTransformationApplicationView view,
         MessageTransformationController controller)
   {
      this.controller = controller;
      this.view = view;
   }

   public void run()
   {
      super.run();

      FieldMapping selectedTargetField = controller.getSelectedTargetFieldMapping();

      final IFile workingResource = view.getWorkingResource();
      final String fieldPath = selectedTargetField.getFieldPath();
      if (false == MessageTransformationMappingBreakpoint.toggleBreakpointOff(
            workingResource, fieldPath))
      {
         if (null != workingResource)
         {
            new MessageTransformationMappingBreakpoint(workingResource, fieldPath);
         }
      }
      view.refreshDocument();
   }

   public String getText()
   {
      return Modeling_Messages.MSG_TOGGLE_BREAKPOINT;
   }

   @Override
   public boolean isEnabled()
   {
      return true;
   }

}
