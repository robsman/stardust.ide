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
package org.eclipse.stardust.modeling.core.compare;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.internal.ICompareContextIds;
import org.eclipse.compare.internal.ResizableDialog;
import org.eclipse.compare.internal.Utilities;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.common.utils.xml.XmlUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotWorkflowModelResourceImpl;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.w3c.dom.Document;

import ag.carnot.workflow.model.beans.XMLConstants;
import ag.carnot.workflow.model.xpdl.XpdlUtils;

public class ModelCompareDialog extends ResizableDialog
      implements IPropertyChangeListener
{
   private CompareEditorInput fCompareEditorInput;

   private Button fCommitButton;

   public ModelCompareDialog(Shell shell, CompareEditorInput input)
   {
      super(shell, null);

      Assert.isNotNull(input);
      fCompareEditorInput = input;
      fCompareEditorInput.addPropertyChangeListener(this);
      setHelpContextId(ICompareContextIds.COMPARE_DIALOG);
   }

   public boolean close()
   {
      if (super.close())
      {
         if (fCompareEditorInput != null)
            fCompareEditorInput.addPropertyChangeListener(this);
         return true;
      }
      return false;
   }

   /*
    * (non-Javadoc) Method declared on Dialog.
    */
   protected void createButtonsForButtonBar(Composite parent)
   {
      fCommitButton = createButton(parent, IDialogConstants.OK_ID, Utilities
            .getString("CompareDialog.commitAction.label"), true); //$NON-NLS-1$
      fCommitButton.setEnabled(true);
      createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL,
            false);

      fCommitButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent e)
         {
            widgetDefaultSelected(e);

         }

         public void widgetDefaultSelected(SelectionEvent e)
         {
            ModelCompareDialog.this.performFinish();
         }
      });
   }

   public void propertyChange(PropertyChangeEvent event)
   {
      if (fCommitButton != null && fCompareEditorInput != null)
         fCommitButton.setEnabled(fCompareEditorInput.isSaveNeeded());
   }

   /*
    * (non-Javadoc) Method declared on Dialog.
    */
   protected Control createDialogArea(Composite parent2)
   {

      Composite parent = (Composite) super.createDialogArea(parent2);

      Control c = fCompareEditorInput.createContents(parent);
      c.setLayoutData(new GridData(GridData.FILL_BOTH));

      Shell shell = c.getShell();
      shell.setText(fCompareEditorInput.getTitle());
      shell.setImage(fCompareEditorInput.getTitleImage());
      applyDialogFont(parent);
      return parent;
   }

   /*
    * (non-Javadoc) Method declared on Window.
    */
   public int open()
   {

      // ModelElementOidRegistration.createInstance();

      int rc = super.open();

      if (rc == OK && fCompareEditorInput.isSaveNeeded())
      {

         WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
         {
            public void execute(IProgressMonitor pm) throws CoreException
            {
               fCompareEditorInput.saveChanges(pm);
            }
         };

         Shell shell = getParentShell();
         ProgressMonitorDialog pmd = new ProgressMonitorDialog(shell);
         try
         {
            operation.run(pmd.getProgressMonitor());

         }
         catch (InterruptedException x)
         {
            // NeedWork
         }
         catch (OperationCanceledException x)
         {
            // NeedWork
         }
         catch (InvocationTargetException x)
         {
            String title = Utilities.getString("CompareDialog.saveErrorTitle"); //$NON-NLS-1$
            String msg = Utilities.getString("CompareDialog.saveErrorMessage"); //$NON-NLS-1$
            MessageDialog.openError(shell, title, msg
                  + x.getTargetException().getMessage());
         }
      }

      return rc;
   }

   private void performFinish()
   {
      final boolean[] result = new boolean[] {false};

      IRunnableWithProgress op = new IRunnableWithProgress()
      {
         public void run(IProgressMonitor monitor) throws InvocationTargetException
         {
            try
            {
               Map options = new HashMap();
               options.put(XMLResource.OPTION_ENCODING, XMLConstants.ENCODING_ISO_8859_1);
               Resource resource = getMergedResource();

               if (resource != null)
               {
                  if (resource.getURI().fileExtension().endsWith(XpdlUtils.EXT_XPDL)
                        && (resource instanceof CarnotWorkflowModelResourceImpl))
                  {
                     Document domCwm = ((XMLResource) resource).save(null, options, null);
                     if (null != domCwm)
                     {
                        // TODO transform to XPDL
                        Source xsltSource;
                        try
                        {
                           final URL xsltURL = XpdlUtils.getCarnot2XpdlStylesheet();
                           if (xsltURL == null)
                           {
                              throw new InternalException(
                                    "Unable to find XPDL export stylesheet."); //$NON-NLS-1$
                           }
                           xsltSource = new StreamSource(xsltURL.openStream());
                        }
                        catch (IOException e)
                        {
                           throw new PublicException("Invalid JAXP setup", e); //$NON-NLS-1$
                        }

                        // need to override context class loader so XpdlUtils extension
                        // class
                        // is
                        // accessible from Xalan
                        ClassLoader cclBackup = Thread.currentThread()
                              .getContextClassLoader();
                        try
                        {
                           Thread.currentThread().setContextClassLoader(
                                 getClass().getClassLoader());

                           StreamResult target = new StreamResult(
                                 ((CarnotWorkflowModelResourceImpl) resource)
                                       .getNewOutputStream());

                           TransformerFactory transformerFactory = XmlUtils
                                 .newTransformerFactory();
                           Transformer xpdlTrans = transformerFactory
                                 .newTransformer(xsltSource);

                           XmlUtils.transform(new DOMSource(domCwm), xpdlTrans, target,
                                 null, 3, XpdlUtils.UTF8_ENCODING);
                        }
                        catch (TransformerConfigurationException e)
                        {
                           throw new PublicException("Invalid JAXP setup", e); //$NON-NLS-1$
                        }
                        finally
                        {
                           // restoring previous context class loader
                           Thread.currentThread().setContextClassLoader(cclBackup);
                        }
                     }
                  }
                  else
                  {
                     resource.save(options);
                  }
                  IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

                  String uri = resource.getURI().path();
                  if (uri.startsWith("/resource")) //$NON-NLS-1$
                  {
                     uri = uri.substring("/resource".length()); //$NON-NLS-1$
                  }

                  IFile file = (IFile) root.findMember(new Path(uri));

                  openEditor(monitor, file, true);
               }

               result[0] = true;
            }
            catch (IOException e)
            {
               throw new InvocationTargetException(e);
            }
            finally
            {
               monitor.done();
            }
         }
      };

      try
      {
         IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
               .getActivePage();
         page.getWorkbenchWindow().run(false, false, op);
      }
      catch (InterruptedException e)
      {
      }
      catch (InvocationTargetException e)
      {
         Throwable realException = e.getTargetException();
         MessageDialog.openError(getShell(), Diagram_Messages.MSG_Error, realException
               .getMessage());
      }
   }

   private void openEditor(IProgressMonitor monitor, final IFile file,
         final boolean reopen)
   {
      // monitor.setTaskName("Opening file for editing..."); // TODO
      // getShell().getDisplay().asyncExec(new Runnable()
      // {
      // public void run()
      // {
      IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
            .getActivePage();

      try
      {
         IEditorPart part = page.findEditor(new FileEditorInput(file));

         // editor is open
         if (part != null && reopen)
         {
            page.closeEditor(part, true);
         }

         IDE.openEditor(page, file, true);

      }
      catch (PartInitException e)
      {
      }
      // }
      // });
      // monitor.worked(2);
   }

   private Resource getMergedResource()
   {
      Resource resource = null;

      DiffNode node = (DiffNode) fCompareEditorInput.getCompareResult();

      if (node != null)
      {
         ComparableModelElementNode cNode = (ComparableModelElementNode) node.getLeft();
         resource = cNode.getEObject().eResource();
      }

      return resource;
   }
}
