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
package org.eclipse.stardust.modeling.modelimport.carnot;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotWorkflowModelResourceFactoryImpl;
import org.eclipse.stardust.modeling.modelimport.IImportModelWizardPage;
import org.eclipse.stardust.modeling.modelimport.ISourceGroupProvider;
import org.eclipse.stardust.modeling.modelimport.Import_Messages;
import org.eclipse.stardust.modeling.modelimport.ThirdPartySourceGroupProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

public class CarnotXmlSourceGroupProvider extends ThirdPartySourceGroupProvider
      implements ISourceGroupProvider, IExecutableExtension
{
   private static final int SIZING_BUTTON_WIDTH = 75;

   private static final int SIZING_TEXT_LABEL_WIDTH = 55;

   private static final String XML_SOURCE_FILE_LABEL = Import_Messages.LB_FromXML;

   private static final String CONTAINER_BROWSE_BUTTON_LABEL = Import_Messages.BTN_Browse;

   private IImportModelWizardPage wizardPage;

   private Text fileNameField;

   private Composite parent;

   private boolean complete;

   public Control createAdditionalOptionsGroup(Composite optionsGroup, boolean enabled)
   {
      Control control = super.createAdditionalOptionsGroup(optionsGroup, false);
      control.setEnabled(false);
      return control;
   }

   public Control createSourceGroup(Composite parent, IImportModelWizardPage wizardPage)
   {
      this.parent = parent;
      this.wizardPage = wizardPage;

      Composite containerGroup = new Composite(parent, SWT.NONE);
      {
         GridLayout layout = new GridLayout();
         layout.numColumns = 3;
         containerGroup.setLayout(layout);

         GridData gridData = new GridData();
         gridData.horizontalAlignment = SWT.FILL;
         gridData.grabExcessHorizontalSpace = true;
         containerGroup.setLayoutData(gridData);

         containerGroup.setFont(parent.getFont());
      }

      Label label = new Label(containerGroup, SWT.None);
      {
         label.setText(XML_SOURCE_FILE_LABEL);
         label.setFont(parent.getFont());
         GridData data = new GridData();
         data.widthHint = SIZING_TEXT_LABEL_WIDTH;
         label.setLayoutData(data);

      }

      fileNameField = new Text(containerGroup, SWT.SINGLE | SWT.BORDER);
      {
         fileNameField.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
               | GridData.GRAB_HORIZONTAL));

         fileNameField.setFont(parent.getFont());
         fileNameField.addModifyListener(new ModifyListener()
         {
            public void modifyText(ModifyEvent event)
            {
               setComplete(fileNameField.getText().trim().length() > 0);
            }

         });
      }

      Button containerBrowseButton = new Button(containerGroup, SWT.PUSH);
      {
         containerBrowseButton.setText(CONTAINER_BROWSE_BUTTON_LABEL);
         containerBrowseButton.setFont(parent.getFont());

         GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
         data.widthHint = SIZING_BUTTON_WIDTH;
         containerBrowseButton.setLayoutData(data);

         containerBrowseButton.addSelectionListener(new SelectionListener()
         {
            public void widgetSelected(SelectionEvent event)
            {
               browseForFile();
            }

            public void widgetDefaultSelected(SelectionEvent event)
            {
               browseForFile();
            }
         });
      }

      return containerGroup;
   }

   protected void browseForFile()
   {
      FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
      dialog.setFilterExtensions(new String[] {"*.xpdl", "*.xml", "*.mod", "*.*"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      dialog.setFilterNames(new String[] {
            Import_Messages.STR_XpdlFiles, Import_Messages.STR_XmlFiles,
            Import_Messages.STR_ModFiles, Import_Messages.STR_AnyFile});
      String filename = dialog.open();
      if (filename != null)
      {
         fileNameField.setText(filename);
      }
   }

   public Resource getExternalResource()
   {
      IPath sPath = new Path(fileNameField.getText());
      String lastSegment = sPath.lastSegment();
      if (lastSegment == null)
      {
         return null;
      }

      Resource resource = new CarnotWorkflowModelResourceFactoryImpl().createResource(URI
            .createFileURI(sPath.toString()));

      return resource;
   }

   public IPath getResourcePath(IPath containerFullPath)
   {
      IPath sPath = new Path(fileNameField.getText());

      String lastSegment = sPath.lastSegment();
      if (lastSegment == null)
      {
         return null;
      }
      StringBuffer modelName = new StringBuffer();
      int ix = lastSegment.lastIndexOf('.'); //$NON-NLS-1$
      modelName.append(ix < 0 ? lastSegment : lastSegment.substring(0, ix));
      modelName.append(".").append(XpdlUtils.EXT_XPDL); //$NON-NLS-1$

      return containerFullPath.append(modelName.toString());
   }

   public boolean isComplete()
   {
      return complete;
   }

   /*
    * is called every time the file name in fileNameField is changed
    */
   public void setComplete(boolean complete)
   {
      this.complete = complete;
      // we need to send an event 
      // to call the check if model with same id is already there
      if (wizardPage instanceof WizardResourceImportPage)
      {
         Event event = new Event();
         event.type = SWT.Modify;
         ((WizardResourceImportPage) wizardPage).handleEvent(event);
      }
      this.wizardPage.updateButtons();
   }

   public void setInitializationData(IConfigurationElement config, String propertyName,
         Object data) throws CoreException
   {
   // TODO Auto-generated method stub

   }

   /**
    * @see org.eclipse.stardust.modeling.modelimport.ISourceGroupProvider#createAdvancedExpandableControl(org.eclipse.swt.widgets.Composite, org.eclipse.stardust.modeling.modelimport.ImportModelWizardPage)
    */
   public Control createAdvancedExpandableControl(Composite parent, IImportModelWizardPage page)
   {
      // not implemented
      return null;
   }
}