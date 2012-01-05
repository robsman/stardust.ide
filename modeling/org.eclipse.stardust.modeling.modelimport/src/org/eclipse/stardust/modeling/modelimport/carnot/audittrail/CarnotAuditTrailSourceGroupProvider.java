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
package org.eclipse.stardust.modeling.modelimport.carnot.audittrail;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.common.Base64;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotWorkflowModelResourceFactoryImpl;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.projectnature.ModelingCoreActivator;
import org.eclipse.stardust.modeling.common.projectnature.classpath.BpmCoreLibrariesClasspathContainer;
import org.eclipse.stardust.modeling.common.projectnature.classpath.CarnotToolClasspathProvider;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.modelimport.IImportModelWizardPage;
import org.eclipse.stardust.modeling.modelimport.ISourceGroupProvider;
import org.eclipse.stardust.modeling.modelimport.ImportMessages;
import org.eclipse.stardust.modeling.modelimport.ImportModelWizardPage;
import org.eclipse.stardust.modeling.modelimport.ImportPlugin;
import org.eclipse.stardust.modeling.modelimport.ThirdPartySourceGroupProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import ag.carnot.workflow.model.xpdl.XpdlUtils;

public class CarnotAuditTrailSourceGroupProvider extends ThirdPartySourceGroupProvider
      implements ISourceGroupProvider, IExecutableExtension
{
   public static final String ATTR_CARNOT_HOME = ImportPlugin.PLUGIN_ID
         + ".deployment.CARNOT_HOME"; //$NON-NLS-1$

   public static final String ATTR_CARNOT_WORK = ImportPlugin.PLUGIN_ID
         + ".deployment.CARNOT_WORK"; //$NON-NLS-1$

   public static final String ATTR_CARNOT_USER = ImportPlugin.PLUGIN_ID
         + ".deployment.User"; //$NON-NLS-1$

   private static final String CONTAINER_BROWSE_BUTTON_LABEL = ImportMessages.BTN_Browse;

   private IImportModelWizardPage wizardPage;

   private Button chkWithCarnotHome;

   private Text txtCarnotHome;

   private Button btnBrowseCarnotWork;

   private Button chkWithCarnotWork;

   private Text txtCarnotWork;

   private Button btnBrowseCarnotHome;

   private LabeledText txtTargetFileName;

   private Composite parent;

   private boolean complete;

   private ModifyListener txtModifyListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent event)
      {
         complete = wizardPage instanceof ImportModelWizardPage ? !StringUtils
               .isEmpty(txtTargetFileName.getText().getText()) : true;
         ((WizardPage) wizardPage).setPageComplete(complete);
      }
   };

   private ExpandableComposite expComp;

   public String getTargetFileName()
   {
      return txtTargetFileName.getText().getText();
   }

   public Control createAdditionalOptionsGroup(Composite optionsGroup, boolean enabled)
   {
      Control control = super.createAdditionalOptionsGroup(optionsGroup, false);
      control.setEnabled(false);

      return control;
   }

   public Control createSourceGroup(Composite composite, IImportModelWizardPage wizardPage)
   {
      this.parent = composite;
      this.wizardPage = wizardPage;

      Composite containerGroup = FormBuilder.createComposite(composite, 3);

      if (wizardPage instanceof ImportModelWizardPage)
      {
         this.txtTargetFileName = FormBuilder.createLabeledText(containerGroup,
               ImportMessages.LB_TargetFile);
         ((GridData) txtTargetFileName.getText().getLayoutData()).horizontalSpan = 2;
         txtTargetFileName.getText().addModifyListener(txtModifyListener);
      }

      return containerGroup;
   }

   protected void browseForFile(Text txtTarget)
   {
      DirectoryDialog dialog = new DirectoryDialog(parent.getShell(), SWT.OPEN);
      dialog.setFilterPath(txtTarget.getText());
      String directory = dialog.open();
      if (directory != null)
      {
         txtTarget.setText(directory);
      }
   }

   protected void updateLocationStatus(Button chkWithLocation, Text txtLocation,
         Button btnBrowseLocation)
   {
      if (!chkWithLocation.isDisposed())
      {
         txtLocation.setEnabled(chkWithLocation.getSelection());
         btnBrowseLocation.setEnabled(chkWithLocation.getSelection());

         if (!chkWithLocation.getSelection())
         {
            txtLocation.setText("");
         }
      }
      setComplete(true);
   }

   public Resource getExternalResource()
   {
      Resource result = null;

      // TODO
      File sourceFile = importModel();
      if (null != sourceFile)
      {
         IPath sPath = new Path(sourceFile.getAbsolutePath());
         result = new CarnotWorkflowModelResourceFactoryImpl().createResource(URI
               .createFileURI(sPath.toString()));
      }

      return result;
   }

   public IPath getResourcePath(IPath containerFullPath)
   {
      StringBuffer modelName = new StringBuffer();
      modelName.append(getTargetFileName());
      if (!modelName.toString().endsWith(XpdlUtils.EXT_XPDL))
      {
         if (!modelName.toString().endsWith(".")) //$NON-NLS-1$
         {
            modelName.append("."); //$NON-NLS-1$
         }
         modelName.append(XpdlUtils.EXT_XPDL);
      }

      return containerFullPath.append(modelName.toString());
   }

   public boolean isComplete()
   {
      return complete;
   }

   public void setComplete(boolean complete)
   {
      this.complete = complete && (txtTargetFileName == null  || !StringUtils.isEmpty(getTargetFileName()));
      if (parent.isVisible())
      {
         this.wizardPage.updateButtons();
      }
   }

   public void setInitializationData(IConfigurationElement config, String propertyName,
         Object data) throws CoreException
   {
   // TODO Auto-generated method stub

   }

   public String getCarnotHomeLocation()
   {
      return txtCarnotHome.getText();
   }

   public String getCarnotWorkLocation()
   {
      return txtCarnotWork.getText();
   }

   public File importModel()
   {
      File targetFile;
      try
      {
         targetFile = File.createTempFile("carnot-model", ".xml"); //$NON-NLS-1$ //$NON-NLS-2$
         targetFile.deleteOnExit();

         ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
         ILaunchConfigurationType type = manager
               .getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);
         ILaunchConfigurationWorkingCopy wc = type.newInstance(null,
               "Infinity AuditTrail Process Model Import"); //$NON-NLS-1$
         wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, wizardPage
               .getProjectContext().getName());
         wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH_PROVIDER,
               ModelingCoreActivator.ID_CARNOT_TOOL_CP_PROVIDER);
         wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
               ModelImportTool.class.getName());
         wc
               .setAttribute(
                     IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS,
                     "--filename64 " + new String(Base64.encode(targetFile.getAbsolutePath().getBytes()))); //$NON-NLS-1$

         wc.setAttribute(CarnotToolClasspathProvider.ATTR_HOME_LOCATION,
               getCarnotHomeLocation());

         wc.setAttribute(CarnotToolClasspathProvider.ATTR_WORK_LOCATION,
               getCarnotWorkLocation());

         wc.setAttribute(CarnotToolClasspathProvider.ATTR_EXTRA_LOCATION,
               BpmCoreLibrariesClasspathContainer.getLibraryLocation(
                     ImportPlugin.PLUGIN_ID, new String[] {"bin", ""}).toString()); //$NON-NLS-1$ //$NON-NLS-2$

         ILaunchConfiguration config = wc.doSave();
         ILaunch toolLaunch = config.launch(ILaunchManager.RUN_MODE, null);

         boolean started = (0 < toolLaunch.getProcesses().length);

         config.delete();
         wc.delete();

         while (started && !toolLaunch.isTerminated())
         {
            try
            {
               Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
               // ignore
            }
         }

         if (started)
         {
            saveWidgetValues();

            IProcess[] tools = toolLaunch.getProcesses();
            for (int i = 0; i < tools.length; i++)
            {
               if (1 != tools[i].getExitValue())
               {
                  targetFile = null;
               }
            }
         }
      }
      catch (CoreException e)
      {
         // TODO
         e.printStackTrace();
         targetFile = null;
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
         targetFile = null;
      }

      return targetFile;
   }

   protected void restoreWidgetValues()
   {
      String carnotHome = null;
      String workspace = null;
      IProject project = wizardPage.getProjectContext();
      if (null != project && project.exists() && project.isOpen())
      {
         try
         {
            carnotHome = project
                  .getPersistentProperty(BpmProjectNature.PREFERENCE_CARNOT_HOME);
            workspace = project
                  .getPersistentProperty(BpmProjectNature.PREFERENCE_CARNOT_WORK);
         }
         catch (CoreException e)
         {
            // ignore
         }
      }
      chkWithCarnotHome.setSelection(!StringUtils.isEmpty(carnotHome));
      updateLocationStatus(chkWithCarnotHome, txtCarnotHome, btnBrowseCarnotHome);
      if (chkWithCarnotHome.getSelection())
      {
         txtCarnotHome.setText(carnotHome);
      }
      chkWithCarnotWork.setSelection(!StringUtils.isEmpty(workspace));
      updateLocationStatus(chkWithCarnotWork, txtCarnotWork, btnBrowseCarnotWork);
      if (chkWithCarnotWork.getSelection())
      {
         txtCarnotWork.setText(workspace);
      }

      expComp.setExpanded(chkWithCarnotHome.getSelection()
            || chkWithCarnotWork.getSelection());

   }

   protected void saveWidgetValues()
   {
      IProject project = (null != wizardPage.getProjectContext()) ? wizardPage
            .getProjectContext() : null;
      if ((null != project) && project.exists() && project.isOpen()
            && project.isLocal(IResource.DEPTH_ZERO))
      {
         try
         {
            project.setPersistentProperty(BpmProjectNature.PREFERENCE_CARNOT_HOME,
                  getCarnotHomeLocation());
         }
         catch (CoreException e)
         {
            // ignore
         }
         try
         {
            project.setPersistentProperty(BpmProjectNature.PREFERENCE_CARNOT_WORK,
                  getCarnotWorkLocation());
         }
         catch (CoreException e)
         {
            // ignore
         }
      }
   }

   /**
    * @see org.eclipse.stardust.modeling.modelimport.ISourceGroupProvider#createAdvancedExpandableControl(org.eclipse.swt.widgets.Composite,
    *      org.eclipse.stardust.modeling.modelimport.ImportModelWizardPage)
    */
   public Control createAdvancedExpandableControl(Composite advancedComp,
         IImportModelWizardPage page)
   {

      expComp = new ExpandableComposite(advancedComp, ExpandableComposite.EXPANDED
            | ExpandableComposite.TWISTIE);
      GridLayout layout = new GridLayout();
      expComp.setLayout(layout);
      expComp.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());

      expComp.setText("Advanced");

      Composite containerGroup = FormBuilder.createComposite(expComp, 3);
      expComp.setClient(containerGroup);

      this.chkWithCarnotHome = FormBuilder.createCheckBox(containerGroup,
            "Custom CARNOT HOME");
      this.txtCarnotHome = FormBuilder.createText(containerGroup);
      FormBuilder.applyDefaultTextControlWidth(txtCarnotHome);
      this.btnBrowseCarnotHome = FormBuilder.createButton(containerGroup,
            CONTAINER_BROWSE_BUTTON_LABEL, new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent event)
               {
                  browseForFile(txtCarnotHome);
               }
            });
      this.chkWithCarnotWork = FormBuilder.createCheckBox(containerGroup,
            "Custom CARNOT Workspace");
      this.txtCarnotWork = FormBuilder.createText(containerGroup);

      FormBuilder.applyDefaultTextControlWidth(txtCarnotWork);
      this.btnBrowseCarnotWork = FormBuilder.createButton(containerGroup,
            CONTAINER_BROWSE_BUTTON_LABEL, new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent event)
               {
                  browseForFile(txtCarnotWork);
               }
            });
      chkWithCarnotHome.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateLocationStatus(chkWithCarnotHome, txtCarnotHome, btnBrowseCarnotHome);
         }
      });
      chkWithCarnotWork.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateLocationStatus(chkWithCarnotWork, txtCarnotWork, btnBrowseCarnotWork);
         }
      });

      txtCarnotHome.addModifyListener(txtModifyListener);
      txtCarnotWork.addModifyListener(txtModifyListener);

      restoreWidgetValues();

      // return expComp;
      return expComp;
   }
}
