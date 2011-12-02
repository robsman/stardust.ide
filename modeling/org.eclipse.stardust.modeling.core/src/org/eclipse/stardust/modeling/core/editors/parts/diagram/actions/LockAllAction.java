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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.modelserver.RMSException;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;


/**
 * @version $Revision: 13371 $
 */
public class LockAllAction extends SelectionAction
{
   public LockAllAction(WorkflowModelEditor editor)
   {
      super(editor);
      setText(Diagram_Messages.LB_LOCK_ALL);
      setToolTipText(Diagram_Messages.LB_LOCK_ALL);
      setId(DiagramActionConstants.LOCK_ALL);      
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/lock.gif")); //$NON-NLS-1$
   }

   protected boolean calculateEnabled()
   {
      if (getSelectedObjects().size() != 1)
      {
         return false;
      }
      
      Object selection = getSelectedObjects().get(0);
      if (selection instanceof ModelTreeEditPart)
      {         
         ModelType model = (ModelType) ((ModelTreeEditPart) selection).getModel();
         WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
         
         return editor.getModelServer().isModelShared();
      }      
      return false;
   }

   public void run()
   {
      Object selection = getSelectedObjects().get(0);      
      if (selection instanceof ModelTreeEditPart)
      {
         final WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
         if (editor.isDirty())
         {
            ModelServerUtils.showMessageBox(Diagram_Messages.MSG_SAVE_MODEL_NEEDED);
            return;
         }          
         
         final ModelServer modelServer = editor.getModelServer();
         IRunnableWithProgress op = new IRunnableWithProgress()
         {
            public void run(IProgressMonitor monitor) throws InvocationTargetException,
                  InterruptedException
            {
               try
               {
                  modelServer.lockAll(monitor);
               }
               catch (RMSException e)
               {
                  throw new InvocationTargetException(e);
               }
            }
         };
         try
         {
            new ProgressMonitorDialog(editor.getSite().getShell()).run(true, true, op);
         }
         catch (InvocationTargetException e)
         {
            Throwable t = e.getCause();
            ModelServerUtils.showMessageBox(t.getMessage());
            // TODO: update status
         }
         catch (InterruptedException e)
         {
            // TODO handle cancellation
            e.printStackTrace();
         }
      }
   }
}