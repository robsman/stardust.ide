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
package org.eclipse.stardust.modeling.modelimport;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.projectnature.classpath.BpmClasspathUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.WizardResourceImportPage;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

public class ImportModelWizardPage extends WizardResourceImportPage
      implements IImportModelWizardPage
{
   private ISourceGroupProvider sourceGroupProvider;

   private Combo modelTypsCombo;

   private Map sourceGroupControls;

   private Map additionalOptionsControls;

   private Map advancedExpandableControls;

   private StackLayout sourceGroupLayout;

   private StackLayout additionOptionsGroupLayout;

   private StackLayout advancedExpandableGroupLayout;

   protected CarnotWorkflowModelPackage carnotWorkflowModelPackage = CarnotWorkflowModelPackage.eINSTANCE;

   protected CarnotWorkflowModelFactory carnotWorkflowModelFactory = carnotWorkflowModelPackage
         .getCarnotWorkflowModelFactory();

   private IProject projectContext;

   public ImportModelWizardPage(String pageId, IStructuredSelection selection)
   {
      super(pageId, selection);

      if (!selection.isEmpty())
      {
         Set projects = new HashSet();
         for (Iterator i = selection.iterator(); i.hasNext();)
         {
            Object obj = i.next();
            if (obj instanceof IResource)
            {
               IResource resource = (IResource) obj;
               projects.add(resource.getProject());
            }
         }

         if (1 == projects.size())
         {
            projectContext = (IProject) projects.iterator().next();
         }
      }

      if (projectContext == null)
      {
         IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
         IProject[] projects = root.getProjects();
         if (projects.length == 0)
         {
            projectContext = createNewProject(root);
         }
      }

      if (projectContext != null)
      {
         setContainerFieldValue(projectContext.getName());
      }
   }

   private IProject createNewProject(IWorkspaceRoot root)
   {
      IProject project = root.getProject("carnot-bpm"); //$NON-NLS-1$
      try
      {
         project.create(null);
         project.open(null);
         IJavaProject javaProject = JavaCore.create(project);

         IProjectDescription description = project.getDescription();
         description.setNatureIds(new String[] {JavaCore.NATURE_ID});
         project.setDescription(description, null);

         javaProject.setRawClasspath(new IClasspathEntry[] {JavaRuntime
               .getDefaultJREContainerEntry()}, null);
         BpmProjectNature.enableBpmNature(project);
         BpmClasspathUtils.addBpmCoreLibsContainer(project);
      }
      catch (CoreException e)
      {
         // e.printStackTrace();
      }
      return project;
   }

   public void updateButtons()
   {
      this.getWizard().getContainer().updateButtons();
   }

   public IDialogSettings getWizardSettings()
   {
      return getDialogSettings();
   }

   public IProject getProjectContext()
   {
      return projectContext;
   }

   public void createControl(Composite parent)
   {
      initializeDialogUnits(parent);

      Composite composite = new Composite(parent, SWT.NULL);
      composite.setLayout(new GridLayout());
      composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
            | GridData.HORIZONTAL_ALIGN_FILL));
      composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
      composite.setFont(parent.getFont());

      createSourceGroup(composite);

      createDestinationGroup(composite);

      createOptionsGroup(composite);

      createAdvancedExpandedComposite(composite);

      restoreWidgetValues();
      updateWidgetEnablements();
      setPageComplete(determinePageCompletion());
      setErrorMessage(null); // should not initially have error message

      setControl(composite);
   }

   protected void createSourceGroup(Composite parent)
   {
      sourceGroupControls = new HashMap();

      Composite sgContainer = new Composite(parent, SWT.NONE);
      {
         GridData data = new GridData(SWT.NONE);
         data.horizontalAlignment = SWT.FILL;
         data.grabExcessHorizontalSpace = true;
         sgContainer.setLayoutData(data);

         sourceGroupLayout = new StackLayout();
         sgContainer.setLayout(sourceGroupLayout);
      }

      Iterator _iterator = ImportPlugin.getSourceGroupProviders().keySet().iterator();
      for (; _iterator.hasNext();)
      {
         String key = (String) _iterator.next();

         ISourceGroupProvider provider = (ISourceGroupProvider) ImportPlugin
               .getSourceGroupProviders().get(key);
         Control control = provider.createSourceGroup(sgContainer, this);
         sourceGroupControls.put(key, control);
      }

      setPageComplete(false);
      setTitle(ImportMessages.LB_Select); // TODO
      setDescription(ImportMessages.DESC_ImportFile); // TODO
      setImageDescriptor(WorkbenchImages
            .getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_IMPORT_WIZ));

   }

   protected void createOptionsGroupButtons(Group optionsGroup)
   {
      // creates a combo box with all registered 3rd party import plugins
      this.createModelTypeOptions(optionsGroup);

      // conatiner within the additional options should be created
      Composite aogContainer = new Composite(optionsGroup, SWT.NONE);
      {
         GridData data = new GridData(SWT.NONE);
         data.horizontalAlignment = SWT.FILL;
         data.grabExcessHorizontalSpace = true;
         data.horizontalSpan = 2;
         aogContainer.setLayoutData(data);

         additionOptionsGroupLayout = new StackLayout();
         aogContainer.setLayout(additionOptionsGroupLayout);
      }

      additionalOptionsControls = new HashMap();
      // creates all controls of the registered 3rd party import plugins
      Iterator _iterator = ImportPlugin.getSourceGroupProviders().keySet().iterator();
      for (; _iterator.hasNext();)
      {
         String key = (String) _iterator.next();

         ISourceGroupProvider provider = (ISourceGroupProvider) ImportPlugin
               .getSourceGroupProviders().get(key);
         Control control = provider.createAdditionalOptionsGroup(aogContainer, true);
         additionalOptionsControls.put(key, control);
      }
   }

  
   private void createAdvancedExpandedComposite(Composite parent)
   {
      Composite advancedComp = new Composite(parent, SWT.NONE);

      GridData data = new GridData(SWT.NONE);
      data.horizontalAlignment = SWT.FILL;
      data.grabExcessHorizontalSpace = true;
      data.verticalAlignment = SWT.FILL;
      data.grabExcessVerticalSpace = true;
      advancedComp.setLayoutData(data);
      advancedExpandableGroupLayout = new StackLayout();
      advancedExpandableGroupLayout.marginHeight = 5;
      advancedComp.setLayout(advancedExpandableGroupLayout);

      advancedExpandableControls = new HashMap();
      Iterator _iterator = ImportPlugin.getSourceGroupProviders().keySet().iterator();
      for (; _iterator.hasNext();)
      {
         String key = (String) _iterator.next();

         ISourceGroupProvider provider = (ISourceGroupProvider) ImportPlugin
               .getSourceGroupProviders().get(key);
         Control control = provider.createAdvancedExpandableControl(advancedComp, this);
         if (control != null)
         {
            advancedExpandableControls.put(key, control);
         }
      }

      // initial selection after all controls are created
      onModelTypeSelection();
   }

   private void createModelTypeOptions(Composite parent)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      {
         GridLayout layout = new GridLayout();
         layout.numColumns = 2;
         layout.verticalSpacing = 12;
         composite.setLayout(layout);

         GridData data = new GridData();
         data.verticalAlignment = GridData.FILL;
         data.grabExcessVerticalSpace = true;
         data.horizontalAlignment = GridData.FILL;
         composite.setLayoutData(data);
      }

      Label modelTypsComboLabel = new Label(composite, SWT.NONE);
      modelTypsComboLabel.setText(ImportMessages.LB_Types); // TODO

      modelTypsCombo = new Combo(composite, SWT.READ_ONLY);

      SelectionListener listener = new SelectionListener()
      {
         public void widgetSelected(SelectionEvent event)
         {
            widgetDefaultSelected(event);
         }

         public void widgetDefaultSelected(SelectionEvent event)
         {
            ImportModelWizardPage.this.onModelTypeSelection();
         }
      };

      modelTypsCombo.addSelectionListener(listener);

      IConfigurationElement firstConfig = (IConfigurationElement) ImportPlugin
            .getExtensions().get("Infinity XML"); //$NON-NLS-1$
      modelTypsCombo.add(firstConfig.getAttribute(SpiConstants.NAME));
      for (Iterator _iterator = ImportPlugin.getExtensions().keySet().iterator(); _iterator
            .hasNext();)
      {
         String extension = (String) _iterator.next();
         IConfigurationElement config = (IConfigurationElement) ImportPlugin
               .getExtensions().get(extension);
         if (!config.equals(firstConfig))
         {
            modelTypsCombo.add(config.getAttribute(SpiConstants.NAME));
         }
      }

      modelTypsCombo.select(0);
   }

   /**
    * Returns a content provider for <code>FileSystemElement</code>s that returns only
    * files as children.
    */
   protected ITreeContentProvider getFileProvider()
   {
      return null;
   }

   /**
    * Returns a content provider for <code>FileSystemElement</code>s that returns only
    * folders as children.
    */
   protected ITreeContentProvider getFolderProvider()
   {
      return null;
   }

   /**
    * Check if widgets are enabled or disabled by a change in the dialog.
    */
   protected void updateWidgetEnablements()
   {
      // must be set, because the warning otherwise will lay over the error message
      setMessage(null, 2);

      boolean pageComplete = determinePageCompletion();
      setPageComplete(pageComplete);
      if (pageComplete)
      {
         setMessage(null);
      }
      super.updateWidgetEnablements();
      // when no message is already set (maybe pageComplete is enough) check for the id
      if (getMessage() == null && pageComplete)
      {
         validateSelectedModelId();
      }
   }

   /**
    * check if the model to import already exists and show a warning if so
    */
   private void validateSelectedModelId()
   {
      if (sourceGroupProvider != null)
      {
         if (checkFileExists())
         {
            setMessage(ImportMessages.ImportModelWizardPage_WarningIdAlreadyExists, 2);
         }
         else
         {
            setMessage(null, 2);
         }
      }
   }

   /**
    * create the file path and check if file already exists
    * 
    * @return
    */
   public boolean checkFileExists()
   {
      IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
      IPath location = root.getLocation();
      IPath resourcePath = sourceGroupProvider.getResourcePath(getContainerFullPath());
      if (resourcePath == null)
      {
         return false;
      }

      String path = location.toString() + resourcePath.toString();
      File targetFile = new File(path);
      if (targetFile.exists())
      {
         return true;
      }
      return false;
   }

   public boolean performFinish()
   {
      final boolean[] result = new boolean[] {false};

      IRunnableWithProgress op = new IRunnableWithProgress()
      {
         public void run(IProgressMonitor monitor) throws InvocationTargetException
         {
            try
            {
               IPath resourcePath = sourceGroupProvider.getResourcePath(getContainerFullPath());
               if (resourcePath != null)
               {
                  WorkflowModelManager wspModelManager = new WorkflowModelManager();
                  URI uri = URI.createPlatformResourceURI(resourcePath.toString(), false);
                  ModelType model = wspModelManager.createModel(uri);
                  Resource wspResource = model.eResource();

                  Resource extResource = sourceGroupProvider.getExternalResource();

                  if (null != extResource)
                  {
                     Map<String, Object> options = CollectionUtils.newMap();
                     options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
                     extResource.load(options);
                     wspResource.getContents().clear();
                     wspResource.getContents().addAll(extResource.getContents());

                     wspModelManager.save(wspResource.getURI());

                     IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                     IFile file = (IFile) root.findMember(resourcePath);
                     ((ImportModelWizard) getWizard()).openEditor(monitor, file, false);

                     result[0] = true;
                  }
               }
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
         getContainer().run(false, false, op);
      }
      catch (InterruptedException e)
      {
      }
      catch (InvocationTargetException e)
      {
         Throwable realException = e.getTargetException();
         MessageDialog.openError(getShell(), ImportMessages.MSG_Err, realException
               .getMessage());
      }
      return result[0];
   }

   /**
    * several validations if page is complete
    */
   public boolean determinePageCompletion()
   {
      boolean pageComplete = super.determinePageCompletion();

      // no external file to import is selected
      if ((null == sourceGroupProvider) || !sourceGroupProvider.isComplete())
      {
         pageComplete = false;
      }

      // no destination location is selected
      if (getResourcePath() == null || getResourcePath().isEmpty())
      {
         pageComplete = false;
      }

      // no errors due to the destination choice
      if (!validateDestinationGroup())
      {
         pageComplete = false;
      }
      return pageComplete;
   }

   /**
    * Method sets the current selected source group provider
    */
   private void onModelTypeSelection()
   {
      // activate selected provider
      onModelTypeSelection(modelTypsCombo.getText());

      // update buttons necessary due to the source provider change
      updateButtons();
   }

   private void onModelTypeSelection(String selectionIds)
   {
      // iterate over all cached source group controls to set the new visibility
      Iterator _iterator = sourceGroupControls.keySet().iterator();

      for (; _iterator.hasNext();)
      {
         String key = (String) _iterator.next();

         Control sourceGroupControl = (Control) sourceGroupControls.get(key);
         Control additionalOptionsControl = (Control) additionalOptionsControls.get(key);
         Control advancedExpandableControl = (Control) advancedExpandableControls
               .get(key);

         if (key.equals(selectionIds))
         {
            sourceGroupControl.setVisible(true);
           // cache the selected source group provider
            sourceGroupProvider = (ISourceGroupProvider) ImportPlugin
                  .getSourceGroupProviders().get(key);
           
            sourceGroupLayout.topControl = sourceGroupControl;

            additionalOptionsControl.setVisible(true);
            additionOptionsGroupLayout.topControl = additionalOptionsControl;

            if (advancedExpandableControl != null)
            {
               advancedExpandableControl.setVisible(true);
               advancedExpandableGroupLayout.topControl = advancedExpandableControl;
            }
         }
         else
         {
            sourceGroupControl.setVisible(false);
            additionalOptionsControl.setVisible(false);
            if (advancedExpandableControl != null)
            {
               advancedExpandableControl.setVisible(false);
            }
         }
      }
   }

   public ISourceGroupProvider getSourceGroupProvider()
   {
      return sourceGroupProvider;
   }
}