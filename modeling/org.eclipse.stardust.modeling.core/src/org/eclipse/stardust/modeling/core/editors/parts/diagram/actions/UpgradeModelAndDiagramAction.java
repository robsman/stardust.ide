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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;


public class UpgradeModelAndDiagramAction extends SelectionAction
{
   private WorkflowModelEditor editor;

   public UpgradeModelAndDiagramAction(WorkflowModelEditor editor)
   {
      super(editor);
      this.editor = editor;
      setId(DiagramActionConstants.MODEL_DIAGRAM_UPGRADE);
      setText(Diagram_Messages.LB_UpgradeModel);
   }

   protected boolean calculateEnabled()
   {
      Command cmd = UnexecutableCommand.INSTANCE;
      Object obj = getSelection();
      if (obj instanceof IStructuredSelection)
      {
         obj = ((IStructuredSelection) obj).getFirstElement();
         if (obj instanceof ModelTreeEditPart)
         {
            cmd = createUpgradeModelAndDiagramCmd();
         }
      }
      return cmd.canExecute();
   }

   public void run()
   {
      execute(createUpgradeModelAndDiagramCmd());
      editor.refreshDiagramPages();
   }

   public Command createUpgradeModelAndDiagramCmd()
   {
      CompoundCommand cmd = new CompoundCommand();
      cmd.add(createUpdateModelCmd());
      cmd.add(createUpdateDiagramCmd());
      return cmd.unwrap();
   }

   private Command createUpdateModelCmd()
   {
      Command cmd = new UpgradeModelAction(editor).createUpdateModelCmd();
      return cmd instanceof UnexecutableCommand ? null : cmd;
   }

   private Command createUpdateDiagramCmd()
   {
      Command cmd = new UpdateProcessDiagramAction(editor).createUpdateDiagramCmd();
      return cmd instanceof UnexecutableCommand ? null : cmd;
   }
}
