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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.modelserver.CommitUtil;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.modelserver.RMSException;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionInfo;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionState;
import org.eclipse.stardust.modeling.core.modelserver.jobs.StateCache;
import org.eclipse.stardust.modeling.core.modelserver.ui.CommitDialog;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;


/**
 * @version $Revision: 13371 $
 */
public class CommitChangesAction extends SelectionAction
{
   public CommitChangesAction(WorkflowModelEditor editor)
   {
      super(editor);
      setText(Diagram_Messages.LB_CommitModelElement);
      setToolTipText(Diagram_Messages.LB_CommitModelElement);
      setId(DiagramActionConstants.COMMIT_MODEL_ELEMENT);      
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/commit.gif")); //$NON-NLS-1$
   }   

   // can action be selected
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
                  
         if (modelElement instanceof ModelType)
         {
            // if selection contains the model type only, then it is always active.
            if (selectedObjects.size() == 1)
            {
               return true;
            }
         }         
         
         StateCache stateCache = modelServer.getStateCache();
         CollisionInfo info = stateCache.getState(modelElement);
         CollisionState state = info.getState();
         if (state != CollisionState.ADDED && state != CollisionState.LOCKED_BY_USER)
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
      StateCache stateCache = editor.getModelServer().getStateCache();

      // analyze commit changes
      final CommitUtil commitUtil = new CommitUtil(editor);
      IRunnableWithProgress analyzeOperation = new IRunnableWithProgress()
      {
         public void run(IProgressMonitor monitor) throws InvocationTargetException,
               InterruptedException
         {
            try
            {
               commitUtil.analyze(monitor);
            }
            catch (RMSException e)
            {
               throw new InvocationTargetException(e);
            }
         }
      };
            
      try
      {
         new ProgressMonitorDialog(editor.getSite().getShell()).run(true, true, analyzeOperation);
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

      if (!commitUtil.containsChanges())
      {
         ModelServerUtils.showMessageBox(Diagram_Messages.MSG_NO_CHANGES, Diagram_Messages.MSG_NO_CHANGES);
         return;
      }            

      List<?> selectedObjects = getSelectedObjects();
      List<EObject> preCheckedElements = new ArrayList<EObject>(); 
      for (Object object : selectedObjects)
      {
         EObject element = ModelServerUtils.getLockableElementFromSelection(object);
         CollisionInfo info = stateCache.getState(element);
         CollisionState state = info.getState();
         if (state == CollisionState.LOCKED_BY_USER || state == CollisionState.ADDED)
         {
            if (element instanceof ModelType)
            {
               preCheckedElements.add(commitUtil.getLocalModel());
            }
            else
            {
               EObject sameElement = CopyPasteUtil.getSameElement(element, commitUtil.getLocalModel());
               if(sameElement != null)
               {
                  preCheckedElements.add(sameElement);
               }
            }
         }
      }
      
      final EObject[] lockables = preCheckedElements.toArray(new EObject[preCheckedElements.size()]);
      
      final CommitDialog dialog = new CommitDialog(editor, commitUtil);
      // view only if contains changes to revert
      dialog.setSelection(lockables);
      if (Dialog.CANCEL == dialog.open())
      {
         return;
      }
      
      IRunnableWithProgress commitOperation = new IRunnableWithProgress()
      {
         public void run(IProgressMonitor monitor) throws InvocationTargetException,
               InterruptedException
         {
            try
            {
               editor.getModelServer().commit(commitUtil, dialog.getCheckedElements(),
                     dialog.getCommitString(), dialog.getKeepLocks(), monitor);
            }
            catch (RMSException e)
            {
               throw new InvocationTargetException(e);
            }
         }
      };
      try
      {
         new ProgressMonitorDialog(editor.getSite().getShell()).run(true, true, commitOperation);
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