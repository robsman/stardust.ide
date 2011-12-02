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

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.ui.PartInitException;


/**
 * @author fherinean
 * @version $Revision$
 */
public class OpenDiagramAction extends UpdateDiagramAction
{
   public static final int DIAGRAM = 0;
   public static final int DEFAULT_DIAGRAM = 1;
   public static final int DEFAULT_SUBPROCESS_DIAGRAM = 2;

   private int kind;

   public OpenDiagramAction(WorkflowModelEditor editor, int kind)
   {
      super(editor);
      this.kind = kind;
      initUI();
   }

   protected boolean calculateEnabled()
   {
      return getSelectedObjects().size() == 1 && getDiagram() != null &&
         !((WorkflowModelEditor) getWorkbenchPart()).isActiveDiagram(getDiagram());
   }

   public void run()
   {
      try
      {
         DiagramType diagram = getDiagram();
         Command command = createUpdateDiagramCommand(diagram);
         if (command.canExecute())
         {
            MessageDialog dialog = new MessageDialog(
               getWorkbenchPart().getSite().getShell(),
               Diagram_Messages.MSG_UpdateDiagram, null,
               Diagram_Messages.MSG_OpenDiagramAction1_Diagram + diagram.getName() +
                  Diagram_Messages.MSG_OpenDiagramAction2_isInconsistent +
                  Diagram_Messages.MSG_OpenDiagramAction3_WantToUpdate,
               MessageDialog.QUESTION, new String[] {
                  IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL }, 0); // OK is the
             // default
            switch (dialog.open())
            {
               case 0:  // yes, update first then open
                  execute(command);
                  break;
               case 1:  // no update, just open diagram
                  break;
               default: // cancel, close or escape pressed, no further action performed
                  return;
            }
         }
         ((WorkflowModelEditor) getWorkbenchPart()).showDiagramPage(diagram);
      }
      catch (PartInitException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private DiagramType getDiagram()
   {
      Object selection = getSelectedObjects().get(0);
      if (!(selection instanceof EditPart) || selection instanceof DiagramEditPart)
      {
         return null;
      }
      Object element = ((EditPart) selection).getModel();
      if (element instanceof IModelElementNodeSymbol)
      {
         element = ((IModelElementNodeSymbol) element).getModelElement();
      }
      if (element instanceof DiagramType && kind == DIAGRAM)
      {
         return (DiagramType) element;
      }
      if (element instanceof ModelType && kind == DEFAULT_DIAGRAM)
      {
         ModelType model = (ModelType) element;
         List diagrams = model.getDiagram();
         if (diagrams.size() > 0)
         {
            return (DiagramType) diagrams.get(0);
         }
      }
      if (element instanceof ProcessDefinitionType && kind == DEFAULT_DIAGRAM)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) element;
         List diagrams = process.getDiagram();
         if (diagrams.size() > 0)
         {
            return (DiagramType) diagrams.get(0);
         }
      }
      if (element instanceof ActivityType && kind == DEFAULT_SUBPROCESS_DIAGRAM)
      {
         ActivityType activity = (ActivityType) element;
         if (activity != null)
         {
            if (ActivityUtil.isSubprocessActivity(activity))
            {
               ProcessDefinitionType process = activity.getImplementationProcess();
               if (process != null)
               {
                  List diagrams = process.getDiagram();
                  if (diagrams.size() > 0)
                  {
                     return (DiagramType) diagrams.get(0);
                  }
               }
            }
         }
      }
      return null;
   }

   protected void initUI()
   {
      super.init();
      switch (kind)
      {
         case DIAGRAM:
            setId(DiagramActionConstants.DIAGRAM_OPEN);
            setText(Diagram_Messages.TXT_ShowDiagram);
            break;
         case DEFAULT_DIAGRAM:
            setId(DiagramActionConstants.DEFAULT_DIAGRAM_OPEN);
            setText(Diagram_Messages.TXT_ShowDefaultDiagram);
            break;
         case DEFAULT_SUBPROCESS_DIAGRAM:
            setId(DiagramActionConstants.SUBPROCESS_DIAGRAM_OPEN);
            setText(Diagram_Messages.TXT_ShowSubprocessDiagram);
            break;
      }
   }
}
