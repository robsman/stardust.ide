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
package org.eclipse.stardust.modeling.modelimport.elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotWorkflowModelResourceFactoryImpl;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.modelimport.IImportModelWizardPage;
import org.eclipse.stardust.modeling.modelimport.ISourceGroupProvider;
import org.eclipse.stardust.modeling.modelimport.ImportMessages;
import org.eclipse.stardust.modeling.modelimport.ImportPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.WizardDataTransferPage;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;


@SuppressWarnings("restriction")
public class ImportModelElementsWizardPage extends WizardDataTransferPage
      implements IImportModelWizardPage
{
   private ISourceGroupProvider sourceGroupProvider;

   private Combo modelTypsCombo;

   private Map<String, Control> sourceGroupControls;
   private Map<String, Control> advancedExpandableControls;
   
   private StackLayout advancedExpandableGroupLayout;

   private StackLayout layout;

   protected CarnotWorkflowModelPackage carnotWorkflowModelPackage = CarnotWorkflowModelPackage.eINSTANCE;

   protected CarnotWorkflowModelFactory carnotWorkflowModelFactory = carnotWorkflowModelPackage
         .getCarnotWorkflowModelFactory();

   private MergeEditorInput input;

   private WorkflowModelManager target;

   public ImportModelElementsWizardPage(String pageId, Object selection)
   {
      super(pageId);

      if (selection instanceof WorkflowModelManager)
      {
         target = (WorkflowModelManager) selection;
      }
      else if (selection instanceof IResource)
      {
         IResource resource = (IResource) selection;
         IPath destinationPath = resource.getFullPath();
         Resource destination = new CarnotWorkflowModelResourceFactoryImpl()
               .createResource(URI.createPlatformResourceURI(destinationPath.toString(), false));
         target = new WorkflowModelManager(destination);
      }
   }

   public WorkflowModelManager getTarget()
   {
      return target;
   }

   public boolean canFlipToNextPage()
   {
      return isPageComplete();   
   }

   public IWizardPage getNextPage()
   {
      try
      {
         getInput();         
      }
      catch (Exception ex)
      {
         return null;
      }

      ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
      try
      {
         dialog.run(true, true, input);
      }
      catch (Exception e)
      {
         //e.printStackTrace();
      }
      if (dialog.getReturnCode() == Window.CANCEL
            || dialog.getProgressMonitor().isCanceled())
      {
         if (((WizardDialog) getWizard().getContainer()).close())
         {
            return null;
         }
      }
      return super.getNextPage();
   }

   public void setVisible(boolean visible)
   {
      if (!super.getControl().isDisposed())
      {
         super.setVisible(visible);
      }
   }
   
   public void updateButtons()
   {
      if (getWizard().getContainer().getCurrentPage() != null)
      {
         getWizard().getContainer().updateButtons();
      }
   }

   public IDialogSettings getWizardSettings()
   {
      return getDialogSettings();
   }

   public IProject getProjectContext()
   {
      if (target != null)
      {
         ModelType model = target.getModel();
         if (model != null)
         {
            URI modelURI = model.eResource().getURI();
            if (modelURI.isPlatformResource())
            {
               Path path = new Path(modelURI.toPlatformString(true));
               IResource modelResource = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
               return modelResource.getProject();
            }
         }
      }
      return null;
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

      createOptionsGroup(composite);
      
      createAdvancedExpandedComposite(composite);

      setPageComplete(determinePageCompletion());

      setControl(composite);
   }

   protected IPath getResourcePath()
   {
      if (target != null)
      {
         ModelType model = target.getModel();
         if (model != null)
         {
            URI modelURI = model.eResource().getURI();
            if (modelURI.isPlatformResource())
            {
               Path path = new Path(modelURI.toPlatformString(true));
               return path.makeAbsolute();
            }
         }
      }
      return new Path("");
   }

   protected void createSourceGroup(Composite parent)
   {
      sourceGroupControls = new HashMap<String, Control>();

      Composite sgContainer = new Composite(parent, SWT.NONE);
      {
         GridData data = new GridData(SWT.NONE);
         data.horizontalAlignment = SWT.FILL;
         data.grabExcessHorizontalSpace = true;
         sgContainer.setLayoutData(data);

         layout = new StackLayout();
         sgContainer.setLayout(layout);
      }

      @SuppressWarnings("unchecked")
      Map<String, ISourceGroupProvider> sourceGroupProviders = ImportPlugin.getSourceGroupProviders();
      Set<String> keys = sourceGroupProviders.keySet();
      for (String key : keys)
      {
         ISourceGroupProvider provider = sourceGroupProviders.get(key);
         Control control = provider.createSourceGroup(sgContainer, this);
         sourceGroupControls.put(key, control);
      }

      setPageComplete(false);
      setTitle(ImportMessages.LB_Select); // TODO
      setDescription(ImportMessages.DESC_CarnotFileImport);
      // TODO: replace with CARNOT EXP specific image
      setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_IMPORT_WIZ));
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
   
      advancedExpandableControls = new HashMap<String, Control>();
      @SuppressWarnings("unchecked")
      Map<String, ISourceGroupProvider> sourceGroupProviders = ImportPlugin.getSourceGroupProviders();
      Set<String> keys = sourceGroupProviders.keySet();
      for (String key : keys)
      {
         ISourceGroupProvider provider = sourceGroupProviders.get(key);
         Control control = provider.createAdvancedExpandableControl(advancedComp, this);
         if (control != null)
         {
            advancedExpandableControls.put(key, control);
         }
      }

      // initial selection after all controls are created
      onModelTypeSelection();
   }

   protected void createOptionsGroupButtons(Group optionsGroup)
   {
      this.createModelTypeOptions(optionsGroup);
   }

   // layout
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
      modelTypsComboLabel.setText(ImportMessages.LB_ModelTypes); // TODO

      modelTypsCombo = new Combo(composite, SWT.READ_ONLY);

      SelectionListener listener = new SelectionListener()
      {
         public void widgetSelected(SelectionEvent event)
         {
            widgetDefaultSelected(event);
         }

         public void widgetDefaultSelected(SelectionEvent event)
         {
            ImportModelElementsWizardPage.this.onModelTypeSelection();
         }
      };

      modelTypsCombo.addSelectionListener(listener);

      @SuppressWarnings("unchecked")
      Map<String, IConfigurationElement> extensions = ImportPlugin.getExtensions();
      IConfigurationElement firstConfig = (IConfigurationElement) extensions.get("Infinity XML"); //$NON-NLS-1$
      modelTypsCombo.add(firstConfig.getAttribute(SpiConstants.NAME));
      for (IConfigurationElement config : extensions.values())
      {
         if (!config.equals(firstConfig))
         {
            modelTypsCombo.add(config.getAttribute(SpiConstants.NAME));
         }
      }

      modelTypsCombo.select(0);
   }

   public boolean isPageComplete()
   {
      boolean pageIsValid = true;
      
      // no external file to import is selected
      if (sourceGroupProvider != null && !sourceGroupProvider.isComplete())
      {
         pageIsValid = false;
      }
      if (sourceGroupProvider != null && sourceGroupProvider.isComplete())
      {
         pageIsValid = isValidResource();
      }
      
      // no destination location is selected
      if (getResourcePath() == null)
      {
         pageIsValid = false;
      }
      return pageIsValid;
   }

   /**
    * Method sets the current selected source group provider
    */
   private void onModelTypeSelection()
   {
      String selectionIds = modelTypsCombo.getText();

      // iterate over all cached source group controls to set the new visibility
      Iterator<String> _iterator = sourceGroupControls.keySet().iterator();

      while (_iterator.hasNext())
      {
         String key = _iterator.next();

         Control sourceGroupControl = sourceGroupControls.get(key);
         Control advancedExpandableControl = advancedExpandableControls.get(key);

         if (key.equals(selectionIds))
         {
            sourceGroupControl.setVisible(true);
            // cache the selected source group provider
            sourceGroupProvider = (ISourceGroupProvider) ImportPlugin
                  .getSourceGroupProviders().get(key);

            layout.topControl = sourceGroupControl;
            
            if (advancedExpandableControl != null)
            {
               advancedExpandableControl.setVisible(true);
               advancedExpandableGroupLayout.topControl = advancedExpandableControl;
            }
         }
         else
         {
            sourceGroupControl.setVisible(false);
            if (advancedExpandableControl != null)
            {
               advancedExpandableControl.setVisible(false);
            }
         }
      }
      updateButtons();
   }

   public ISourceGroupProvider getSourceGroupProvider()
   {
      return sourceGroupProvider;
   }
    
   public MergeEditorInput getInput()
   {
      if (input == null)
      {
         input = new MergeEditorInput(this);
      }
      // set source and target
      prepareInput();
      return input;
   }

   private boolean isValidResource()
   {
      WorkflowModelManager manager = new WorkflowModelManager();
      Resource resource = sourceGroupProvider.getExternalResource();
      try
      {
         manager.load(resource.getURI());
      }
      catch (IOException e)
      {
         return false;
      }
      return true;
   }   
   
   private void prepareInput()
   {
      Resource source = sourceGroupProvider.getExternalResource();
      input.setSource(new WorkflowModelManager(source));
      input.setTarget(target);
   }

   public boolean performFinish()
   {
      return false;
   }

   protected boolean allowNewContainerName()
   {
      return false;
   }

   public void handleEvent(Event event)
   {
   }
}