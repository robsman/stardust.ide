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

import org.eclipse.gef.EditDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetActivityControlFlowCmd;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SetActivityControlFlowAction extends Action
{
   private final EditDomain domain;

   private final ActivityType activity;

   private final FlowControlType aspect;

   private final JoinSplitType behavior;

   private WorkflowModelEditor editor;

   public SetActivityControlFlowAction(WorkflowModelEditor editor, EditDomain domain, ActivityType activity,
         FlowControlType aspect, JoinSplitType controlFlowType)
   {
      
      super(ModelUtils.getFlowTypeText(controlFlowType.getLiteral()));
      this.editor = editor;
      this.domain = domain;
      this.activity = activity;
      this.aspect = aspect;
      this.behavior = controlFlowType;
   }

   public void run()
   {
      // TODO
      domain.getCommandStack().execute(
            new SetActivityControlFlowCmd(editor, activity, aspect, behavior));
   }

   public JoinSplitType getControlFlowType()
   {
      return behavior;
   }

}
