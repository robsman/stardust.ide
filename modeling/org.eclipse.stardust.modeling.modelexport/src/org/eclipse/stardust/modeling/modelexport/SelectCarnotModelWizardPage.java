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
package org.eclipse.stardust.modeling.modelexport;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.deploy.DeployUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public class SelectCarnotModelWizardPage extends WizardExportResourcesPage
{
//   private static final String LBL_CARNOT_HOME = ExportMessages.LB_CarnotHome;

//   private static final String DIRECTORY_LABEL = ExportMessages.LB_CarnotWorkspace;

   private static final String CONTAINER_BROWSE_BUTTON_LABEL = ExportMessages.BTN_Browse;

   private ExpandableComposite sctAdvanced;
   
   private Button chkWithCarnotHome;
   
   private Text txtCarnotHome;

   private Button btnBrowseCarnotWork;

   private Button chkWithCarnotWork;
   
   private Text txtCarnotWork;
   
   private Button btnBrowseCarnotHome;

//   private LabeledText txtUserId;

   private IProject projectContext;

//   protected boolean useExtraMemory;

   private ModifyListener txtModifyListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent event)
      {
         updatePageCompletion();
      }
   };

   public SelectCarnotModelWizardPage(String pageId, IStructuredSelection selection)
   {
      super(pageId, selection);
      
      Set<IProject> projects = CollectionUtils.newSet();
      if (!selection.isEmpty())
      {
         for (Iterator<?> i = selection.iterator(); i.hasNext();)
         {
            IResource resource = (IResource) i.next();
            projects.add(resource.getProject());
         }
         
         if (1 == projects.size())
         {
            projectContext = projects.iterator().next();
         }
      }
   }
   
   public boolean deployModel()
   {
      @SuppressWarnings("unchecked")
      List<IResource> resources = getSelectedResources();
      return DeployUtil.deployModel(resources, getCarnotHomeLocation(), getCarnotWorkLocation());
   }

   protected void createDestinationGroup(Composite parent)
   {
      this.sctAdvanced = new ExpandableComposite(parent,
            ExpandableComposite.EXPANDED | ExpandableComposite.TWISTIE);
      GridLayout layout = new GridLayout();
      sctAdvanced.setLayout(layout);
      sctAdvanced.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      
      sctAdvanced.setText("Advanced");
      
      Composite containerGroup = FormBuilder.createComposite(sctAdvanced, 3);
      sctAdvanced.setClient(containerGroup);

      this.chkWithCarnotHome = FormBuilder.createCheckBox(containerGroup, "Custom CARNOT HOME");
      this.txtCarnotHome = FormBuilder.createText(containerGroup);
      FormBuilder.applyDefaultTextControlWidth(txtCarnotHome);
      this.btnBrowseCarnotHome = FormBuilder.createButton(containerGroup, CONTAINER_BROWSE_BUTTON_LABEL,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent event)
               {
                  browseForFile(txtCarnotHome);
               }
            });
      this.chkWithCarnotWork = FormBuilder.createCheckBox(containerGroup, "Custom CARNOT Workspace");
      this.txtCarnotWork = FormBuilder.createText(containerGroup);
      // data.widthHint = SIZING_TEXT_FIELD_WIDTH;
      FormBuilder.applyDefaultTextControlWidth(txtCarnotWork);
      this.btnBrowseCarnotWork = FormBuilder.createButton(containerGroup, CONTAINER_BROWSE_BUTTON_LABEL,
            new SelectionAdapter()
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
      
//      this.txtUserId = FormBuilder.createLabeledText(containerGroup, "Deployer:");
//      ((GridData) txtUserId.getText().getLayoutData()).horizontalSpan = 2;

      txtCarnotHome.addModifyListener(txtModifyListener);
      txtCarnotWork.addModifyListener(txtModifyListener);
//      txtUserId.getText().addModifyListener(txtModifyListener);
      
/*      FormBuilder.createCheckBox(containerGroup, "Use extra memory for large models")
         .addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
            widgetSelected(e);
         }

         public void widgetSelected(SelectionEvent e)
         {
            useExtraMemory = ((Button) e.widget).getSelection();
         }
      });*/

      setTitle(ExportMessages.LB_Select);
      setDescription(ExportMessages.DESC_DeployModelEnvironment);

      setImageDescriptor(ExportPlugin.getImageDescriptor("icons/full/wizbean/export_wiz.zip")); //$NON-NLS-1$

      restoreWidgetValues();
   }

   protected boolean validateSourceGroup()
   {
      if (super.validateSourceGroup())
      {
         return !getSelectedResources().isEmpty();
      }
      return false;
   }

   public void setVisible(boolean visible)
   {
      super.setVisible(visible);
      if ( !visible)
      {
         saveWidgetValues();
      }
   }

   protected void restoreWidgetValues()
   {
      String carnotHome = null;
      String workspace = null;
      if (null != projectContext && projectContext.isAccessible())
      {
         try
         {
            carnotHome = projectContext.getPersistentProperty(BpmProjectNature.PREFERENCE_CARNOT_HOME);
         }
         catch (CoreException e)
         {
            // ignore
         }
         try
         {
            workspace = projectContext.getPersistentProperty(BpmProjectNature.PREFERENCE_CARNOT_WORK);
         }
         catch (CoreException e)
         {
            // ignore
         }
      }

      chkWithCarnotHome.setSelection( !StringUtils.isEmpty(carnotHome));
      updateLocationStatus(chkWithCarnotHome, txtCarnotHome, btnBrowseCarnotHome);
      if (chkWithCarnotHome.getSelection())
      {
         txtCarnotHome.setText(carnotHome);
      }
      chkWithCarnotWork.setSelection( !StringUtils.isEmpty(workspace));
      updateLocationStatus(chkWithCarnotWork, txtCarnotWork, btnBrowseCarnotWork);
      if (chkWithCarnotWork.getSelection())
      {
         txtCarnotWork.setText(workspace);
      }
      
      sctAdvanced.setExpanded(chkWithCarnotHome.getSelection()
            || chkWithCarnotWork.getSelection());
   }

   protected void saveWidgetValues()
   {
      @SuppressWarnings("unchecked")
      List<IResource> resources = getSelectedResources(); 
      IProject project = resources.isEmpty() ? null
            : resources.get(0).getProject();
      if (null != project && project.isAccessible())
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

   protected boolean validateDestinationGroup()
   {
      String carnotHome = getCarnotHomeLocation();
      String carnotWork = getCarnotWorkLocation();
//      String userId = getUserId();

      boolean valid = (!chkWithCarnotHome.getSelection() || !StringUtils.isEmpty(carnotHome))
            && (!chkWithCarnotWork.getSelection() || !StringUtils.isEmpty(carnotWork));// &&
      // !StringUtils.isEmpty(userId);

      return valid;
   }

   protected void updateLocationStatus(Button chkWithLocation, Text txtLocation,
         Button btnBrowseLocation)
   {
      if ( !chkWithLocation.isDisposed())
      {
         txtLocation.setEnabled(chkWithLocation.getSelection());
         btnBrowseLocation.setEnabled(chkWithLocation.getSelection());
         
         if ( !chkWithLocation.getSelection())
         {
            txtLocation.setText("");
         }
      }

      updatePageCompletion();
   }

   protected void browseForFile(Text txtTarget)
   {
      DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.OPEN);
      dialog.setFilterPath(txtTarget.getText());
      String directory = dialog.open();
      if (directory != null)
      {
         txtTarget.setText(directory);
      }
   }

   public void handleEvent(Event event)
   {
   }

   protected List<String> getTypesToExport()
   {
      List<String> list = CollectionUtils.newList();
      list.add("cwm"); //$NON-NLS-1$
      return list;
   }

   protected void createOptionsGroup(Composite parent)
   {
      // overrided for no options
   }

   public String getCarnotHomeLocation()
   {
      return txtCarnotHome.getText();
   }

   public String getCarnotWorkLocation()
   {
      return txtCarnotWork.getText();
   }
}
