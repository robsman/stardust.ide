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
import org.eclipse.jface.action.Action;

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ActivityCommandFactory;


public class SetActivitySubprocessAction extends Action
{
   private ProcessDefinitionType process;

   private ActivityType activity;

   private WorkflowModelEditor editor;

   public SetActivitySubprocessAction(ActivityType activity,
         ProcessDefinitionType process, WorkflowModelEditor editor)
   {
      super(process.getName());
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/process.gif")); //$NON-NLS-1$
      this.activity = activity;
      this.process = process;
      this.editor = editor;
   }

   public void run()
   {
      CompoundCommand cmd = new CompoundCommand();

      cmd.add(ActivityCommandFactory.getSetSubprocessCommand(activity, process));

      editor.getEditDomain().getCommandStack().execute(cmd.unwrap());
   }
}
