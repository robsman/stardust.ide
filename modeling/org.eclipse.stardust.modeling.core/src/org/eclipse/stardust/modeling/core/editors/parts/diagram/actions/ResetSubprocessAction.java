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

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ActivityCommandFactory;


public class ResetSubprocessAction extends SelectionAction
{
   private ActivityType activity;

   public ResetSubprocessAction(WorkflowModelEditor part)
   {
      super(part);
      setId(DiagramActionConstants.RESET_SUBPROCESS);
      setText(Diagram_Messages.ResetSubprocessAction_Lb_NoSubprocess);
      //setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/process.gif")); //$NON-NLS-1$
   }

   protected boolean calculateEnabled()
   {
      return (null != activity) && (null != activity.getImplementationProcess());
   }

   public void run()
   {
      CompoundCommand cmd = new CompoundCommand();

      cmd.add(ActivityCommandFactory.getSetSubprocessCommand(activity, null));

      execute(cmd.unwrap());
   }

   public void setActivity(ActivityType activity)
   {
      this.activity = activity;
   }
}
