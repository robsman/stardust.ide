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
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.modelserver.RMSException;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionInfo;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionState;
import org.eclipse.stardust.modeling.core.modelserver.jobs.StateCache;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;


/**
 * @version $Revision: 13371 $
 */
public class LockAction extends SelectionAction
{
   public LockAction(WorkflowModelEditor editor)
   {
      super(editor);
      setText(Diagram_Messages.LB_LOCK);
      setToolTipText(Diagram_Messages.LB_LOCK);
      setId(DiagramActionConstants.LOCK);      
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/lock.gif")); //$NON-NLS-1$
   }
         
   protected boolean calculateEnabled()
   {
      WorkflowModelEditor editor = null;
      
      List<?> selectedObjects = getSelectedObjects();
      if(selectedObjects.isEmpty())
      {
         return false;
      }      
      
      for (Object object : selectedObjects)
      {
         EObject modelElement = ModelServerUtils.getLockableElementFromSelection(object);
         if (modelElement == null)
         {
            return false;
         }         
         
         if(editor == null)
         {
            editor = GenericUtils.getWorkflowModelEditor(ModelUtils.findContainingModel(modelElement));
         }
         
         if (editor == null) {
        	 return false;
         }
         
         ModelServer modelServer = editor.getModelServer();
         if (modelServer == null || !modelServer.isModelShared())
         {
            return false;
         }
         
         StateCache stateCache = modelServer.getStateCache();
         CollisionInfo info = stateCache.getState(modelElement);
         CollisionState state = info.getState();
         if (state != CollisionState.DEFAULT)
         {
            return false;
         }
      }
      return true;
   }

   public void run()
   {
      final WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
      if (editor.isDirty())
      {
         ModelServerUtils.showMessageBox(Diagram_Messages.MSG_SAVE_MODEL_NEEDED);
         return;
      }        

      List<?> selectedObjects = getSelectedObjects();
      final EObject[] lockables = new EObject[selectedObjects.size()];
      for (int i = 0; i < lockables.length; i++)
      {
         lockables[i] = ModelServerUtils.getLockableElementFromSelection(selectedObjects.get(i));
      }
      
      final ModelServer modelServer = editor.getModelServer();
      IRunnableWithProgress op = new IRunnableWithProgress()
      {
         public void run(IProgressMonitor monitor) throws InvocationTargetException,
               InterruptedException
         {
            try
            {
               modelServer.lock(lockables, monitor);
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