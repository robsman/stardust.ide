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

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IFilter;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.stardust.modeling.repository.common.ui.ConnectionEditUtils;
import org.eclipse.stardust.modeling.repository.common.ui.ConnectionQueryUtils;
import org.eclipse.stardust.modeling.repository.common.ui.ObjectDescriptorLabelProvider;
import org.eclipse.stardust.modeling.repository.common.ui.ObjectDescriptorListContentProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

public class ElementSelectionAction extends SelectionAction
{
   private ObjectDescriptorLabelProvider labelProvider;
   private ObjectDescriptorListContentProvider contentProvider;

   public ElementSelectionAction(IWorkbenchPart part)
   {
      super(part);
      setId(ObjectRepositoryActivator.PLUGIN_ID + '.' + "select"); //$NON-NLS-1$
      setText(Diagram_Messages.TXT_SEL_MD_ELEMENTS);
      labelProvider = new ObjectDescriptorLabelProvider();
      contentProvider = new ObjectDescriptorListContentProvider();
   }

   public void run()
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
      ModelType model = editor.getWorkflowModel();
      
      ModelServer modelServer = editor.getModelServer();
      if(modelServer.isModelShared())
      {
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(model);
         if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
         {
            MessageDialog.openInformation(null, Diagram_Messages.MSG_DIA_REPOSITORY_CONNECTION,
            Diagram_Messages.MSG_DIA_THIS_OPERATION_REQUIRES_THE_MD_TO_BE_LOCKED_YOU_MUST_LOCK_THE_MD_TO_PROCEED);            
            return;
         }
      }      
      
      Connection connection = getConnection();
      ConnectionManager manager = (ConnectionManager) getWorkbenchPart().getAdapter(
            ConnectionManager.class);
      try
      {
         manager.open(connection);
         List<IObjectDescriptor> content = ConnectionQueryUtils.select(connection, manager, new IFilter[0]);
         List<IObjectDescriptor> result = select(getWorkbenchPart().getSite().getShell(), connection, content);
         
         // call the manager with the selection (and connection? )
         Command cmd = ConnectionEditUtils.linkObject(model, result.toArray(new IObjectDescriptor[0]), manager);
         execute(cmd);
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public List<IObjectDescriptor> select(Shell parent, Connection connection, List<IObjectDescriptor> input) throws CoreException
   {
      contentProvider.reset(input);
      ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(parent,
            labelProvider, contentProvider);
      dialog.setTitle(Diagram_Messages.DIA_TITLE_ELEMENT_SELE);
      dialog.setMessage(connection.getName());
      dialog.setInput(input);
      dialog.setValidator(new ISelectionStatusValidator()
      {
         public IStatus validate(Object[] selection)
         {
            String description = null;
            for (int i = 0; i < selection.length; i++)
            {
               if (!(selection[i] instanceof IObjectDescriptor))
               {
                  return Status.CANCEL_STATUS;
               }
            }
            if(selection.length == 1)
            {
               // must be a description so we changed the descriptor
               description = ((IObjectDescriptor) selection[0]).getDescription();     
            }
            return new Status(Status.OK, ObjectRepositoryActivator.PLUGIN_ID, 0, description == null ? "" : description, null); //$NON-NLS-1$
         }
      });
      if (dialog.open() == Window.OK)
      {  
         Object[] entries = dialog.getResult();
         List<IObjectDescriptor> dialogResult = CollectionUtils.newList();
         for (int i = 0; i < entries.length; i++) 
         {
            if (entries[i] instanceof IObjectDescriptor)
            {
               dialogResult.add((IObjectDescriptor) entries[i]);
            }
         }
         if (dialogResult.size() > 0)
         {
            return dialogResult;
         }
      }
      return Collections.emptyList();
   }

   protected boolean calculateEnabled()
   {
      return getConnection() != null;
   }
   
   private Connection getConnection()
   {
      List<?> objects = getSelectedObjects();
      if (objects.size() == 1)
      {
         Object selection = objects.get(0);
         if (selection instanceof IAdaptable)
         {
            Object item = ((IAdaptable) selection).getAdapter(EObject.class);
            if (item instanceof Connection)
            {
               return (Connection) item;
            }
         }
      }
      return null;
   }
}