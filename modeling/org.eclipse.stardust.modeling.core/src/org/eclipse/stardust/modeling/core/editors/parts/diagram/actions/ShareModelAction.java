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

import java.io.IOException;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.modelserver.RMSException;
import org.eclipse.stardust.modeling.core.modelserver.ui.VcsFeedView;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;


/**
 * @version $Revision: 13371 $
 */
public class ShareModelAction extends SelectionAction
{
   public ShareModelAction(WorkflowModelEditor editor)
   {
      super(editor);
      setText(Diagram_Messages.LB_ShareModel);
      setToolTipText(Diagram_Messages.LB_ShareModel);
      setId(DiagramActionConstants.SHARE_MODEL);      
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/createrepos.gif")); //$NON-NLS-1$
   }
   
   // can action be selected
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
         ModelServer modelServer = editor.getModelServer();
         
         return !modelServer.isModelShared() && modelServer.isProjectShared();
      }      
      return false;
   }
   
   public void run()
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
      if(editor.isDirty())
      {
         ModelServerUtils.showMessageBox(Diagram_Messages.MSG_SAVE_MODEL_NEEDED);
         return;
      }  
            
      try
      {
         editor.getEditorChangeTracker().setEnabled(false);                  
         editor.getModelServer().share();
      }
      catch (RMSException e)
      {
         ModelServerUtils.showMessageBox(e.getMessage());
         return;
      }
      // TODO: (fh) unified behavior
      catch (IOException e)
      {
         ModelServerUtils.showMessageBox(e.getMessage());
         return;
      }
      finally
      {
         editor.getEditorChangeTracker().setEnabled(true);    
      }
      ModelServerUtils.refreshTreeItem(editor.getWorkflowModel());
      editor.getEditDomain().getCommandStack().flush();         
      
      // update refresh button of view
      IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
                        findView("org.eclipse.stardust.modeling.core.modelserver.ui.VcsFeedView"); //$NON-NLS-1$
      
      if(view != null)
      {
         ((VcsFeedView) view).updateAction(true);
      }
   }
}