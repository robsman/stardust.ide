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
package org.eclipse.stardust.modeling.data.structured.actions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.actions.ISpiAction;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class OpenXSDEditorAction extends Action implements ISpiAction
{
   private IStructuredSelection selection;

   private WorkflowModelEditor editor;

   protected List<?> getSelectedObjects()
   {
      return selection instanceof IStructuredSelection
         ? ((IStructuredSelection) selection).toList()
         : Collections.EMPTY_LIST;
   }

   public void setConfiguration(IConfigurationElement config, WorkflowModelEditor editor,
         IStructuredSelection selection)
   {
      setId(config.getAttribute(SpiConstants.ID));
      setText(Structured_Messages.OpenXSDEditorAction_ActionLabel); 
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/schemaView.gif")); //$NON-NLS-1$
      this.editor = editor;
      this.selection = selection;
   }

   public boolean isEnabled()
   {
      if (getSelectedObjects().size() == 1)
      {
         Object selection = getSelectedObjects().get(0);
         if (selection instanceof TreeEditPart)
         {
            Object model = ((TreeEditPart) selection).getModel();
            if (model instanceof TypeDeclarationType)
            {
               TypeDeclarationType decl = (TypeDeclarationType) model;
               return isWorkspaceSchema(decl)/* || isInternalSchema(decl)*/;
            }
         }
      }
      return false;
   }

/*   private boolean isInternalSchema(TypeDeclarationType decl)
   {
      XpdlTypeType xpdlType = decl.getDataType();
      if (xpdlType instanceof ExternalReferenceType)
      {
         ExternalReferenceType ref = (ExternalReferenceType) xpdlType;
         String location = ref.getLocation();
         return location != null
            && location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX);
      }
      return xpdlType instanceof SchemaTypeType;
   }*/

   private boolean isWorkspaceSchema(TypeDeclarationType decl)
   {
      XpdlTypeType xpdlType = decl.getDataType();
      if (xpdlType instanceof ExternalReferenceType)
      {
         ExternalReferenceType ref = (ExternalReferenceType) xpdlType;
         String location = ref.getLocation();
         return location != null
            && !location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX)
            && !location.startsWith("http://"); //$NON-NLS-1$
      }
      return false;
   }

   public void run()
   {
      IRunnableWithProgress op = new IRunnableWithProgress()
      {
         public void run(IProgressMonitor monitor) throws InvocationTargetException
         {
            try
            {
               IEditorInput input = null;
               TreeEditPart part = (TreeEditPart) getSelectedObjects().get(0);
               TypeDeclarationType decl = (TypeDeclarationType) part.getModel();
               IProject project = ModelUtils.getProjectFromEObject(decl);
               String name = decl.getId() + ".xsd"; //$NON-NLS-1$
               if (isWorkspaceSchema(decl))
               {
                  ExternalReferenceType ref = decl.getExternalReference();
                  name = project.getFile(ref.getLocation()).getName();
                  IFile file = GenericUtils.getFile(project, ref.getLocation());
                  if (file != null)
                  {
                     input = new FileEditorInput(file);
                  }
                  else
                  {
                     MessageDialog.openError(editor.getSite().getShell(),
                           name, Structured_Messages.OpenXSDEditorAction_FileNotFoundMessage); 
                  }
               }
/*               else if (isInternalSchema(decl))
               {
                  IFile file = project.getFile(name);
                  XSDSchema schema = TypeDeclarationUtils.getSchema(decl);
                  if (schema != null)
                  {
                     input = new XSDFileEditorInput(file, schema);
                  }
               }*/
               if (input != null)
               {
                  openEditor(monitor, input, name);
               }
            }
            finally
            {
               monitor.done();
            }
         }
      };
      try
      {
         editor.getSite().getWorkbenchWindow().run(false, false, op);
      }
      catch (InterruptedException e)
      {
      }
      catch (InvocationTargetException e)
      {
         Throwable realException = e.getTargetException();
         MessageDialog.openError(editor.getSite().getShell(),
               Structured_Messages.OpenXSDEditorAction_ErrorTitle, realException.getMessage());
      }
   }

   private void openEditor(IProgressMonitor monitor, final IEditorInput input, final String name)
   {
      monitor.setTaskName(Structured_Messages.OpenXSDEditorAction_TaskName);
      editor.getSite().getShell().getDisplay().asyncExec(new Runnable()
      {
         public void run()
         {
            IWorkbenchPage page = editor.getSite().getPage();
            try
            {
               IContentTypeManager ctm = Platform.getContentTypeManager();
               String tmp = "<schema></schema>"; //$NON-NLS-1$
               IContentType ct = ctm.findContentTypeFor(new ByteArrayInputStream(tmp.getBytes("UTF-8")), name); //$NON-NLS-1$
               IEditorRegistry editorReg = PlatformUI.getWorkbench().getEditorRegistry();
               IEditorDescriptor editorDesc = editorReg.getDefaultEditor(name, ct);
               page.openEditor(input, editorDesc.getId(), true);
            }
            catch (PartInitException e)
            {
            }
            catch (UnsupportedEncodingException e)
            {
               e.printStackTrace();
            }
            catch (IOException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      });
      monitor.worked(2);
   }
}