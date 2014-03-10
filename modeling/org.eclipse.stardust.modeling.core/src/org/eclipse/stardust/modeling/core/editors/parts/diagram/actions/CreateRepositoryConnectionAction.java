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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode.Spec;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public class CreateRepositoryConnectionAction extends SelectionAction
{
   private IConfigurationElement config;
   protected DataType data;

   private ConnectionManager manager;

   public CreateRepositoryConnectionAction(IConfigurationElement config, WorkflowModelEditor part)
   {
      super(part);
      this.config = config;

      setId(ObjectRepositoryActivator.CREATE_REPOSITORY_CONNECTION_ACTION + config.getAttribute(SpiConstants.ID));
      setText(config.getAttribute(SpiConstants.NAME));
      setImageDescriptor(DiagramPlugin.getImageDescriptor(config));
   }

   // when to view
   protected boolean calculateEnabled()
   {
      return !getSelectedObjects().isEmpty() && isConnectionCategory();
   }

   private boolean isConnectionCategory()
   {
      Object selection = getSelectedObjects().get(0);
      if (selection instanceof ChildCategoryNode)
      {
         Object model = ((ChildCategoryNode) selection).getModel();
         if (model instanceof Spec)
         {
            String label = ((Spec) model).label;
            if(label. equals(Diagram_Messages.LBL_EXTERNAL_MD_INFORMATION))
            {
               return true;
            }
         }
      }
      return false;
   }

   // when command is executed
   public void run()
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
      manager = editor.getConnectionManager();

      // a flag that will be set when the activation of the bundle failed
      boolean create = true;
      try
      {

         ChangeRecorder recorder = new ChangeRecorder();
         recorder.beginRecording(Collections.singleton(manager.getRepository()));

         Connection connection = manager.create(config.getAttribute(SpiConstants.ID));

         EditPart part = editor.findEditPart(connection);
         // open blocking properties dialog !

         // if user press cancel in the dialog then we must delete the new connection
         // otherwise save it

         final StructuredSelection selection = new StructuredSelection(part);
         ShowPropertiesAction propDlgAction = new ShowPropertiesAction(editor,
               new ISelectionProvider() {
                  public void addSelectionChangedListener(ISelectionChangedListener listener)
                  {
                     // ignore
                  }

                  public ISelection getSelection()
                  {
                     return selection;
                  }

                  public void removeSelectionChangedListener(ISelectionChangedListener listener)
                  {
                     // ignore
                  }

                  public void setSelection(ISelection selection)
                  {
                     // ignore
                  }
         });

         // activate bundle
         String contributor = config.getNamespace();
         if(contributor != null)
         {
            Bundle bundle = Platform.getBundle(contributor);
            try
            {
               bundle.start();
            }
            catch (BundleException e)
            {
               ErrorDialog.openError(editor.getSite().getShell() , Diagram_Messages.ERROR_DIA_ERROR,
                     Diagram_Messages.ERROR_DIA_CANNOT_START_BUNDLE, new Status(IStatus.ERROR,
                           contributor, 0, Diagram_Messages.ERROR_DIA_CANNOT_START_BUNDLE, null));
               create = false;
            }
         }
         if (!create) {
            // undo changes
            // (fh) thinking out loud : do we really want to undo the connection creation ?
            if (recorder.isRecording())
            {
               ChangeDescription changes = recorder.endRecording();
               changes.apply();
            }
            return;
         }

         PreferenceDialog dlg = propDlgAction.createDialog(recorder);
         if (dlg != null)
         {
            dlg.setBlockOnOpen(true);
            if (dlg.open() == Window.OK)
            {
               connection.setProperty(IConnectionManager.CONNECTION_MANAGER_CREATED, "false"); //$NON-NLS-1$
               // connection.eNotify(new NotificationImpl(Notification.ADD, null, null));
               // we need this so that the image of the connection gets displayed
               connection.setProperty(IConnectionManager.CONNECTION_MANAGER, manager);
               connection.eNotify(new NotificationImpl(Notification.SET, null, null));
            }
         }
      }
      catch (InvalidRegistryObjectException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}