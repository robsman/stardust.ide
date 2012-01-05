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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.LinkAttribute;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

public class AddExternalReferenceAction extends SelectionAction
{
   private ModelType modelType;

   private ConnectionManager connectionManager;

   private WorkflowModelEditor editor;

   private Connection selectedConnection;

   public AddExternalReferenceAction(WorkflowModelEditor part)
   {
      super(part);
      setId(ObjectRepositoryActivator.ADD_EXTERNAL_REFERENCES_ACTION);
      setText(Diagram_Messages.POPUP_FILECONNECTION_ADD_EXTERNAL_MODEL_REFERENCE);
      modelType = (ModelType) part.getModel();
      if (modelType != null)
      {
         connectionManager = (ConnectionManager) modelType.getConnectionManager();
      }
      editor = part;
   }

   protected boolean calculateEnabled()
   {
      if (modelType == null)
      {
         return false;
      }
      selectedConnection = null;
      if (getSelectedObjects().size() == 0 || getSelectedObjects().size() > 1)
      {
         return false;
      }
      if (getSelectedObjects().get(0) instanceof EditPart)
      {
         Object model = ((EditPart) getSelectedObjects().get(0)).getModel();
         if (model instanceof ExternalPackages || model instanceof Connection)
         {
            if (model instanceof Connection)
            {
               selectedConnection = (Connection) model;
               String byReference = selectedConnection.getAttribute(IConnectionManager.BY_REFERENCE);
               if (!"true".equals(byReference)) //$NON-NLS-1$
               {
                  return false;
               }
               List<String> uris = ModelUtils.getURIsForExternalPackages(modelType);
               String uri = "cnx://" + selectedConnection.getId() + "/"; //$NON-NLS-1$ //$NON-NLS-2$
               for (Iterator<String> i = uris.iterator(); i.hasNext();)
               {
                  if (uri.equals(i.next()))
                  {
                     return false;
                  }
               }
            }
            return true;
         }
      }
      return false;
   }

   public void run()
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();      
      ModelServer modelServer = editor.getModelServer();
      if(modelServer.isModelShared())
      {
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(modelType);
         if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
         {
            MessageDialog.openInformation(null, Diagram_Messages.DIA_REPOSITORY_CONNECTION,
            Diagram_Messages.DIA_THIS_OPERATION_REQUIRES_THE_MD_TO_BE_LOCKED_YOU_MUST_LOCK_THE_MD_TO_PROCEED);            
            return;
         }
      }
      
      Shell shell = Display.getDefault().getActiveShell();
      FileConnectionSelectionDialog dialog = new FileConnectionSelectionDialog(shell);
      if (selectedConnection == null && dialog.open() == Window.OK)
      {
         selectedConnection = dialog.getSelectedConnection();
      }
      if (selectedConnection != null)
      {
         String uri = "cnx://" + selectedConnection.getId() + "/"; //$NON-NLS-1$ //$NON-NLS-2$
         EObject o = connectionManager.find(uri);
         ModelType referencedModel = (ModelType) Reflect.getFieldValue(o, "eObject"); //$NON-NLS-1$
         if (!ModelUtils.externalPackageExists(modelType, referencedModel))
         {
            EditDomain domain = editor.getEditDomain();
            CompoundCommand command = new CompoundCommand();
            ChangeRecorder targetRecorder = new ChangeRecorder();
            targetRecorder.beginRecording(Collections.singleton(modelType.eContainer()));
            createExternalPackage((IObjectDescriptor) o, modelType, referencedModel);
            final ChangeDescription change = targetRecorder.endRecording();
            targetRecorder.dispose();

            Command cmd = new Command()
            {
               public void execute()
               {}

               public void undo()
               {
                  change.applyAndReverse();
               }

               public void redo()
               {
                  change.applyAndReverse();
               }
            };
            command.add(cmd);
            domain.getCommandStack().execute(command);
         }
         else
         {
            MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
                  SWT.ICON_WARNING | SWT.CANCEL);
            messageBox.setText(Diagram_Messages.TXT_REFERENCE_EXISTS);
            messageBox
                  .setMessage(Diagram_Messages.MSG_AN_EXTERNAL_REF_TO_THE_MD_REF_BY_THE_SEL_FILE_CONNECTION_ALREADY_EXISTS);
            messageBox.open();
         }
         try
         {
            refreshTree();
         }
         catch (Throwable t)
         {

         }
      }

   }

   private void refreshTree()
   {
      Tree tree = (Tree) Reflect.getFieldValue(editor.getOutlinePage()
            .getOutlineTreeEditor(), "tree"); //$NON-NLS-1$
      if (tree != null)
      {
         if (tree.getItems().length > 0 && tree.getItem(0) != null)
         {
            if (tree.getItem(0).getData() instanceof ModelTreeEditPart)
            {
               ModelTreeEditPart editPart = (ModelTreeEditPart) tree.getItem(0).getData();
               editPart.refresh();
            }
         }
      }
   }

   public class FileConnectionSelectionDialog extends Dialog
   {

      Connection selectedConnection;

      private ComboViewer fileConnectionCombo;

      public FileConnectionSelectionDialog(Shell parentShell)
      {
         super(parentShell);
      }

      public Connection getSelectedConnection()
      {
         return selectedConnection;
      }

      protected Control createDialogArea(Composite parent)
      {
         Composite control = (Composite) super.createDialogArea(parent);
         Composite composite = FormBuilder.createComposite(control, 2);
         FormBuilder.createLabel(composite, Diagram_Messages.LBL_FILE_CONNECTION);
         fileConnectionCombo = new ComboViewer(FormBuilder.createCombo(composite));
         fileConnectionCombo.setLabelProvider(new ConnectionLabelProvider());
         for (Iterator<Connection> i = connectionManager.getConnections(); i.hasNext();)
         {
            fileConnectionCombo.add(i.next());
         }
         fileConnectionCombo.addSelectionChangedListener(new ISelectionChangedListener()
         {

            public void selectionChanged(SelectionChangedEvent event)
            {
               IStructuredSelection structuredSelection = (IStructuredSelection) event
                     .getSelection();
               selectedConnection = (Connection) structuredSelection.getFirstElement();
            }

         });
         return control;
      }

      protected Control createContents(Composite parent)
      {
         Control control = super.createContents(parent);
         getButton(IDialogConstants.OK_ID).setText(Diagram_Messages.BUT_TXT_OK);
         return control;
      }

      protected void configureShell(Shell newShell)
      {
         super.configureShell(newShell);
         newShell.setText(Diagram_Messages.TXT_FILE_CONNECTION_SELECTION);
      }

   }

   class ConnectionLabelProvider extends LabelProvider
   {

      public String getText(Object element)
      {
         Connection c = (Connection) element;
         return c.getName();
      }

   }

   private ExternalPackage createExternalPackage(IObjectDescriptor descriptor,
         ModelType targetModel, ModelType sourceModel)
   {
      LinkAttribute linkAttribute;
      XpdlFactory xFactory = XpdlFactory.eINSTANCE;
      String packageRef = sourceModel.getId();
      ExternalPackages packages = targetModel.getExternalPackages();
      if (packages == null)
      {
         packages = xFactory.createExternalPackages();
         targetModel.setExternalPackages(packages);
      }
      ExternalPackage pkg = packages.getExternalPackage(packageRef);
      if (pkg == null)
      {
         pkg = xFactory.createExternalPackage();
         pkg.setId(packageRef);
         pkg.setName(sourceModel.getName());
         pkg.setHref(packageRef);

         linkAttribute = new LinkAttribute(descriptor.getURI().trimSegments(2), false,
               false, IConnectionManager.URI_ATTRIBUTE_NAME);
         linkAttribute.setLinkInfo(pkg, false);

         packages.getExternalPackage().add(pkg);
      }
      return pkg;
   }

}