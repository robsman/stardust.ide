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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.launch;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.stardust.modeling.debug.CwmFileSelectionDialog;
import org.eclipse.stardust.modeling.transformation.messaging.format.FormatManager;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.TypesLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.eclipse.wst.jsdt.ui.JavaScriptElementLabelProvider;



public class ProcessingConfigurationTab extends AbstractLaunchConfigurationTab
{


   private Button testSerializationCheckbox;
    private Text sourceFilePathText;
    private Text targetFilePathText;
    private Text projectText;
    private ComboViewer sourceFormatComboViewer;  
    private ComboViewer targetFormatComboViewer;
    private Button screenOutputCheckbox;
    private Text modelText;
    private String modelUri = ""; //$NON-NLS-1$
    private ComboViewer legoComboViewer;

    public void createControl(Composite parent)
    {
        Composite composite = FormBuilder.createComposite(parent, 1);

        setControl(composite);

        FormBuilder
                .createLabel(
                        composite,
                        Modeling_Messages.MSG_READING_DATA_FROM_SOURCEMESSAGE);
        
        final Group group2 = new Group(composite, SWT.NONE);

        FormLayout layout2 = new FormLayout();

        layout2.marginHeight = 10;
        layout2.marginWidth = 10;

        group2.setLayout(layout2);
        group2.setText(Modeling_Messages.TXT_STR_DATA_SEL);

        Label label2 = new Label(group2, 0);
        Control layoutDirective2 = label2;

        FormData formData2 = new FormData();

        formData2.left = new FormAttachment(0, 0);
        formData2.top = new FormAttachment(0, 0);
        formData2.width = 200;

        label2.setLayoutData(formData2);
        label2.setText(Modeling_Messages.TXT_PROJECT);

        projectText = new Text(group2, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);

        formData2 = new FormData();

        formData2.left = new FormAttachment(layoutDirective2, 5, SWT.RIGHT);
        formData2.top = new FormAttachment(label2, 0, SWT.TOP);
        formData2.width = 300;

        projectText.setLayoutData(formData2);
    
        Button browseProjectsButton = new Button(group2, 0);

        formData2 = new FormData();

        formData2.left = new FormAttachment(projectText, 5, SWT.RIGHT);
        formData2.top = new FormAttachment(label2, 0, SWT.TOP);

        browseProjectsButton.setLayoutData(formData2);
        browseProjectsButton.setText(Modeling_Messages.TXT_BW_DREI_PUNKT);
        browseProjectsButton.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                browseProjects();
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });
        
        ////////////////Model////////////////////////
        
        label2 = new Label(group2, 0);

        formData2 = new FormData();

        formData2.left = new FormAttachment(0, 0);
        formData2.top = new FormAttachment(projectText, 5, SWT.BOTTOM);
        label2.setLayoutData(formData2);
        label2.setText(Modeling_Messages.TXT_PRC);
        modelText = new Text(group2, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);

        formData2 = new FormData();

        formData2.left = new FormAttachment(layoutDirective2, 5, SWT.RIGHT);
        formData2.top = new FormAttachment(label2, 0, SWT.TOP);
        formData2.width = 300;

        modelText.setLayoutData(formData2);

        Button browseModelsButton = new Button(group2, 0);

        formData2 = new FormData();

        formData2.left = new FormAttachment(modelText, 5, SWT.RIGHT);
        formData2.top = new FormAttachment(label2, 0, SWT.TOP);

        browseModelsButton.setLayoutData(formData2);
        browseModelsButton.setText(Modeling_Messages.TXT_BW_DREI_PUNKT);
        browseModelsButton.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                browseModelFiles();
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });

        /////////////////Structured Data//////////////////////////////////      
        label2 = new Label(group2, 0);

        formData2 = new FormData();

        formData2.left = new FormAttachment(0, 0);
        formData2.top = new FormAttachment(modelText, 5, SWT.BOTTOM);
        label2.setLayoutData(formData2);
        label2.setText(Modeling_Messages.TXT_STR_DATA);
        
        TypesLabelProvider labelProvider = new TypesLabelProvider();
        Combo legoCombo = new Combo(group2, SWT.NO);
        legoComboViewer = new ComboViewer(legoCombo);
        legoComboViewer.setContentProvider(new ArrayContentProvider());
        legoComboViewer.setLabelProvider(labelProvider);
        legoComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                TypeDeclarationType legoType = (TypeDeclarationType)selection.getFirstElement();
                String text = legoType.getName();
                updateLaunchConfigurationDialog();
            }
            
        });
        
    
        formData2 = new FormData();

        formData2.left = new FormAttachment(layoutDirective2, 5, SWT.RIGHT);
        formData2.top = new FormAttachment(label2, 0, SWT.TOP);
        formData2.width = 300;

        legoCombo.setLayoutData(formData2);



        
        ///////////////////////Parsing Group////////////////////////////////////
        
        
        
        
        final Group group = new Group(composite, SWT.NONE);

        FormLayout layout = new FormLayout();

        layout.marginHeight = 10;
        layout.marginWidth = 10;

        group.setLayout(layout);
        group.setText(Modeling_Messages.TXT_PR);

        Label label = new Label(group, 0);
        Control layoutDirective = label;

        FormData formData = new FormData();

        formData.left = new FormAttachment(0, 0);
        formData.top = new FormAttachment(0, 0);
        formData.width = 200;

        label.setLayoutData(formData);
        label.setText(Modeling_Messages.TXT_SR_MSG_FILE);

        sourceFilePathText = new Text(group, SWT.SINGLE | SWT.BORDER);

        formData = new FormData();

        formData.left = new FormAttachment(layoutDirective, 5, SWT.RIGHT);
        formData.top = new FormAttachment(label, 0, SWT.TOP);
        formData.width = 300;

        sourceFilePathText.setLayoutData(formData);
        sourceFilePathText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                updateLaunchConfigurationDialog();
            }
        });
        


        Button button = new Button(group, 0);

        formData = new FormData();

        formData.left = new FormAttachment(sourceFilePathText, 5, SWT.RIGHT);
        formData.top = new FormAttachment(label, 0, SWT.TOP);

        button.setLayoutData(formData);
        button.setText(Modeling_Messages.TXT_BW_DREI_PUNKT);
        button.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                FileDialog fd = new FileDialog(group.getShell(), SWT.SAVE);
                fd.setText(Modeling_Messages.TXT_BW);
                fd.setFilterPath("C:/"); //$NON-NLS-1$
                String fileName = fd.open();
                if (fileName != null) {
                   sourceFilePathText.setText(fileName);                   
                } else {
                   sourceFilePathText.setText(""); //$NON-NLS-1$
                } 
                updateLaunchConfigurationDialog();
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });

        label = new Label(group, 0);

        formData = new FormData();

        formData.left = new FormAttachment(0, 0);
        formData.top = new FormAttachment(sourceFilePathText, 5, SWT.BOTTOM);

        label.setLayoutData(formData);
        label.setText(Modeling_Messages.TXT_SR_MSG_FORMAT);

        sourceFormatComboViewer = new ComboViewer(group);

        formData = new FormData();

        formData.left = new FormAttachment(layoutDirective, 5, SWT.RIGHT);
        formData.top = new FormAttachment(label, 0, SWT.TOP);

        sourceFormatComboViewer.getControl().setLayoutData(formData);

        sourceFormatComboViewer.setContentProvider(new ArrayContentProvider());
        sourceFormatComboViewer.setLabelProvider(new LabelProvider()
        {
            public Image getImage(Object element)
            {
                return null;
            }

            public String getText(Object element)
            {
                if (element instanceof String)
                {
                    return element.toString();
                }

                return null;
            }
        });

        sourceFormatComboViewer.setInput(FormatManager.getMessageFormats());
        sourceFormatComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

         public void selectionChanged(SelectionChangedEvent event)
         {
            updateLaunchConfigurationDialog();
            
         }
           
        });

        testSerializationCheckbox = FormBuilder.createCheckBox(composite,
                Modeling_Messages.BOX_TEST_SER);

        testSerializationCheckbox.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateLaunchConfigurationDialog();

                if (testSerializationCheckbox.getSelection())
                {
                    enableTargetSettings();
                }
                else
                {
                    disableTargetSettings();
                }
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });

        final Group targetGroup = new Group(composite, SWT.NONE);

        layout = new FormLayout();

        layout.marginHeight = 10;
        layout.marginWidth = 10;

        targetGroup.setLayout(layout);
        targetGroup.setText(Modeling_Messages.TXT_SER);

        screenOutputCheckbox = new Button(targetGroup, SWT.CHECK);

        formData = new FormData();

        formData.left = new FormAttachment(0, 0);
        formData.top = new FormAttachment(0, 0);

        screenOutputCheckbox.setText(Modeling_Messages.TXT_SCREEN_OP);

        label = new Label(targetGroup, 0);
        layoutDirective = label;

        formData = new FormData();

        formData.left = new FormAttachment(0, 0);
        formData.top = new FormAttachment(screenOutputCheckbox, 5, SWT.BOTTOM);
        formData.width = 200;

        label.setLayoutData(formData);
        label.setText(Modeling_Messages.TXT_TARGET_MSG_FILE);

        targetFilePathText = new Text(targetGroup, SWT.SINGLE | SWT.BORDER);
        targetFilePathText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                updateLaunchConfigurationDialog();
            }
        });

        formData = new FormData();

        formData.left = new FormAttachment(layoutDirective, 5, SWT.RIGHT);
        formData.top = new FormAttachment(label, 0, SWT.TOP);
        formData.width = 300;

        targetFilePathText.setLayoutData(formData);

        button = new Button(targetGroup, 0);

        formData = new FormData();

        formData.left = new FormAttachment(targetFilePathText, 5, SWT.RIGHT);
        formData.top = new FormAttachment(label, 0, SWT.TOP);

        button.setLayoutData(formData);
        button.setText(Modeling_Messages.TXT_BW_DREI_PUNKT);
        button.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                FileDialog fd = new FileDialog(targetGroup.getShell(), SWT.SAVE);
                fd.setText(Modeling_Messages.TXT_BW);
                fd.setFilterPath("C:/"); //$NON-NLS-1$
                String sourceFilePath = fd.open();
                if (sourceFilePath != null) {
                   targetFilePathText.setText(sourceFilePath);   
                }                                                
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });

        label = new Label(targetGroup, 0);

        formData = new FormData();

        formData.left = new FormAttachment(0, 0);
        formData.top = new FormAttachment(targetFilePathText, 5, SWT.BOTTOM);

        label.setLayoutData(formData);
        label.setText(Modeling_Messages.TXT_TARGET_MSG_FORMAT);

        targetFormatComboViewer = new ComboViewer(targetGroup);

        formData = new FormData();

        formData.left = new FormAttachment(layoutDirective, 5, SWT.RIGHT);
        formData.top = new FormAttachment(label, 0, SWT.TOP);
        formData.width = 150;

        targetFormatComboViewer.getControl().setLayoutData(formData);

        targetFormatComboViewer.setContentProvider(new ArrayContentProvider());
        targetFormatComboViewer.setLabelProvider(new LabelProvider()
        {
            public Image getImage(Object element)
            {
                return null;
            }

            public String getText(Object element)
            {
                if (element instanceof String)
                {
                    return element.toString();
                }

                return null;
            }
        });
        
        targetFormatComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
               updateLaunchConfigurationDialog();             
            }           
        });
        
        
        targetFormatComboViewer.setInput(FormatManager.getMessageFormats());

 
        // Validation Group
        
        /*final Group validationGroup = new Group(composite, SWT.NONE);

        layout = new FormLayout();

        layout.marginHeight = 10;
        layout.marginWidth = 10;

        validationGroup.setLayout(layout);
        validationGroup.setText("Validation");

        Button checkbox = new Button(validationGroup, SWT.CHECK);

        formData = new FormData();

        formData.left = new FormAttachment(0, 0);
        formData.top = new FormAttachment(0, 0);

        checkbox.setText("Check field validation rules");
        checkbox.setLayoutData(formData);

        Button checkbox2 = new Button(validationGroup, SWT.CHECK);

        formData = new FormData();

        formData.left = new FormAttachment(0, 0);
        formData.top = new FormAttachment(checkbox, 5, SWT.BOTTOM);

        checkbox2.setText("Check message validation rules");
        checkbox2.setLayoutData(formData);*/

        screenOutputCheckbox.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateLaunchConfigurationDialog();

                if (screenOutputCheckbox.getSelection())
                {
                    targetFilePathText.setEnabled(false);
                }
                else
                {
                    targetFilePathText.setEnabled(true);                    
                }
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });
        
        testSerializationCheckbox.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateLaunchConfigurationDialog();
                
                if (testSerializationCheckbox.getSelection())
                {
                    targetFilePathText.setEnabled(true);
                    screenOutputCheckbox.setEnabled(true);
                    targetFormatComboViewer.getCombo().setEnabled(true);
                    if (screenOutputCheckbox.getSelection())
                    {
                        targetFilePathText.setEnabled(false);
                    }                    
                }
                else
                {
                   targetFormatComboViewer.getCombo().setEnabled(false);
                   targetFilePathText.setEnabled(false); 
                   screenOutputCheckbox.setEnabled(false);
                }
                
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });

        // Initialize defaults

        testSerializationCheckbox.setSelection(false);
        disableTargetSettings();
    }

    private void enableTargetSettings()
    {
        screenOutputCheckbox.setEnabled(true);
        targetFilePathText.setEnabled(true);
        targetFormatComboViewer.getControl().setEnabled(true);
        //targetFormatModelComboViewer.getControl().setEnabled(true);
    }

    private void disableTargetSettings()
    {
        screenOutputCheckbox.setEnabled(false);
        screenOutputCheckbox.setSelection(false);
        targetFilePathText.setEnabled(false);
        targetFormatComboViewer.getControl().setEnabled(false);
        //targetFormatModelComboViewer.getControl().setEnabled(false);
    }

    public String getName()
    {
        return Modeling_Messages.TXT_MSG_PRC_TEST_CFG;
    }

    public void initializeFrom(ILaunchConfiguration configuration)
    {
        try
        {
            projectText.setText(configuration.getAttribute(ProcessingLauncherConstants.IPP_PROJECT, "")); //$NON-NLS-1$
            modelText.setText(configuration.getAttribute(ProcessingLauncherConstants.MODEL_NAME, "")); //$NON-NLS-1$
            modelUri = configuration.getAttribute(ProcessingLauncherConstants.MODEL_URI, ""); //$NON-NLS-1$
            if (modelUri != "") { //$NON-NLS-1$
                try {
                    ModelType model = this.loadModel(modelUri);
                    List dataTypes = model.getTypeDeclarations().getTypeDeclaration();
                    legoComboViewer.setInput(dataTypes);
                    String declaredTypeId = configuration.getAttribute(ProcessingLauncherConstants.LEGO_DATA, ""); //$NON-NLS-1$
                    legoComboViewer.setSelection(new StructuredSelection(ModelUtils.getTypeDeclaration(model, declaredTypeId)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            sourceFilePathText.setText(configuration.getAttribute(
                    ProcessingLauncherConstants.SOURCE_FILE_PATH, "")); //$NON-NLS-1$
            String sourceMessageFormat = configuration.getAttribute(
                    ProcessingLauncherConstants.SOURCE_FORMAT, ""); //$NON-NLS-1$

            if (sourceMessageFormat != null)
            {
                sourceFormatComboViewer.setSelection(new StructuredSelection(
                        sourceMessageFormat));
            }

            String sourceFormatModelPath = configuration.getAttribute(
                    ProcessingLauncherConstants.SOURCE_FORMAT_MODEL, ""); //$NON-NLS-1$

            
            this.targetFilePathText.setText(configuration.getAttribute(
                    ProcessingLauncherConstants.TARGET_FILE_PATH, "")); //$NON-NLS-1$
            this.testSerializationCheckbox.setSelection(configuration.getAttribute(
                  ProcessingLauncherConstants.TEST_SERIALIZATION, false));
            if (configuration.getAttribute(
                    ProcessingLauncherConstants.TEST_SERIALIZATION, true))
            {
                
               screenOutputCheckbox.setEnabled(true);
               targetFormatComboViewer.getCombo().setEnabled(true);



                if (configuration.getAttribute(ProcessingLauncherConstants.SCREEN_ONLY,
                        false))
                {
                    screenOutputCheckbox.setSelection(true);
                    targetFilePathText.setEnabled(false);
                    
                }
                else
                {
                    screenOutputCheckbox.setSelection(false);

                    targetFilePathText.setEnabled(true);
                    targetFormatComboViewer.getCombo().setEnabled(true);
                }
                targetFilePathText.setText(configuration.getAttribute(
                      ProcessingLauncherConstants.TARGET_FILE_PATH, "")); //$NON-NLS-1$


            }
            String targetMessageFormat = configuration.getAttribute(
                  ProcessingLauncherConstants.TARGET_FORMAT, ""); //$NON-NLS-1$

          if (targetMessageFormat != null)
          {
              targetFormatComboViewer
                      .setSelection(new StructuredSelection(
                              targetMessageFormat));
          }

        }
        catch (CoreException e)
        {
            e.printStackTrace();
        }
    }

    public void performApply(ILaunchConfigurationWorkingCopy configuration)
    {
        configuration.setAttribute(ProcessingLauncherConstants.IPP_PROJECT, projectText.getText());
        configuration.setAttribute(ProcessingLauncherConstants.MODEL_NAME,  modelText.getText());
        configuration.setAttribute(ProcessingLauncherConstants.MODEL_URI, modelUri);
        if ( !legoComboViewer.getSelection().isEmpty())
        {
           TypeDeclarationType selectedTypeDeclaration = (TypeDeclarationType) ((IStructuredSelection) legoComboViewer.getSelection()).getFirstElement();
           configuration.setAttribute(ProcessingLauncherConstants.LEGO_DATA, selectedTypeDeclaration.getId());
        }
        configuration.setAttribute(ProcessingLauncherConstants.SOURCE_FILE_PATH,
                sourceFilePathText.getText());
        configuration.setAttribute(ProcessingLauncherConstants.TARGET_FILE_PATH,
              targetFilePathText.getText());

        if (sourceFormatComboViewer.getSelection() != null)
        {
            configuration.setAttribute(ProcessingLauncherConstants.SOURCE_FORMAT,
                    (String) ((IStructuredSelection) sourceFormatComboViewer
                            .getSelection()).getFirstElement());
        }

        if (testSerializationCheckbox.getSelection())           
        {
            if (targetFormatComboViewer.getSelection() != null)
            {
                configuration
                        .setAttribute(
                                ProcessingLauncherConstants.TARGET_FORMAT,
                                (String) ((IStructuredSelection) targetFormatComboViewer
                                        .getSelection()).getFirstElement());
            }

            if (screenOutputCheckbox.getSelection())
            {
                configuration.setAttribute(ProcessingLauncherConstants.SCREEN_ONLY, true);
            }
            else
            {
                configuration
                        .setAttribute(ProcessingLauncherConstants.SCREEN_ONLY, false);
                configuration.setAttribute(ProcessingLauncherConstants.TARGET_FILE_PATH,
                        targetFilePathText.getText());
            }
            
        } 
        configuration.setAttribute(ProcessingLauncherConstants.TEST_SERIALIZATION, testSerializationCheckbox.getSelection());
        
    }

    public void setDefaults(ILaunchConfigurationWorkingCopy configuration)
    {
        configuration.setAttribute(ProcessingLauncherConstants.TEST_SERIALIZATION, false);
    }

    public boolean isValid(ILaunchConfiguration launchConfig)
    {
       if (StringUtils.isEmpty(projectText.getText())) {
          setErrorMessage(Modeling_Messages.MSG_NO_PROJECT_SEL);
          return false;
       }
       if (StringUtils.isEmpty(modelText.getText())) {
          setErrorMessage(Modeling_Messages.MSG_NO_PROCESS_MD_SEL);
          return false;
       }     
       if (legoComboViewer.getSelection().isEmpty()) {
          setErrorMessage(Modeling_Messages.MSG_NO_STRUCTURED_TYPE_SEL);
          return false;
       }    
       if (StringUtils.isEmpty(sourceFilePathText.getText())) {
          setErrorMessage(Modeling_Messages.MSG_NO_SR_FILE_SEL);
          return false;
       }
       if (sourceFormatComboViewer.getSelection().isEmpty()) {
          setErrorMessage(Modeling_Messages.MSG_NO_MSG_FORMAT_SEL);
          return false;
       }
       if (testSerializationCheckbox.getSelection()) {
          if (targetFormatComboViewer.getSelection().isEmpty()) {
             return false;
          } 
          if (!screenOutputCheckbox.getSelection()) {
             if (StringUtils.isEmpty(targetFilePathText.getText())) {
                return false;
             }            
          }
       }
       setErrorMessage(null);
       return true;      
    }
    
    
    private void browseProjects()
    {
        IJavaProject[] projects;
        
        try
        {
            projects = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot()).getJavaProjects();
        }
        catch (JavaModelException e)
        {
            projects = new IJavaProject[0];
        }

        ILabelProvider labelProvider = new JavaScriptElementLabelProvider(JavaScriptElementLabelProvider.SHOW_DEFAULT);
        ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
        dialog.setTitle(Modeling_Messages.DIA_MSG_TRANS_RUN_CFG);
        dialog.setElements(projects);
        String projectName = projectText.getText();

        if (!StringUtils.isEmpty(projectName))
        {
            IJavaProject javaProject = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName));
            if (javaProject != null)
            {
                dialog.setInitialSelections(new Object[] {javaProject});
            }
        }

        if (dialog.open() == Window.OK)
        {
            IJavaProject javaProject = (IJavaProject) dialog.getFirstResult();

            if (null != javaProject)
            {
                updateLaunchConfigurationDialog();
                projectText.setText(javaProject.getElementName());                
                modelText.setText("");                 //$NON-NLS-1$
                legoComboViewer.setInput(null);                
            }
            else
            {
                projectText.setText(""); //$NON-NLS-1$
            }
        }
        updateLaunchConfigurationDialog();
    }
    
    private void browseModelFiles()
    {
        String projectName = projectText.getText();
        if (StringUtils.isEmpty(projectName)) {
           MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
                 SWT.ICON_WARNING | SWT.CANCEL);
           messageBox.setText(Modeling_Messages.TXT_WR_LEER);
           messageBox.setMessage(Modeling_Messages.MSG_FIRST_NEED_SELECTED_PROJECT);
           messageBox.open();
        } else {
           ResourceListSelectionDialog dialog = new CwmFileSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot()
                 .getProject(projectName), IResource.FILE);

           if (dialog.open() == Window.OK)
           {
              Object[] files = dialog.getResult();
              IFile modelFile = (IFile) files[0];
              modelUri = URI.createPlatformResourceURI(
                    modelFile.getFullPath().toString(), true).toString();
     
              String fullPath = modelFile.getFullPath().toString();
              modelText.setText(fullPath.substring(projectName.length() + 2));
              updateLaunchConfigurationDialog();
              //updateProcessModel();
              updateStructruredData();
              //updateLaunchConfigurationDialog();
           }
           
        }
    }
    
    private void updateStructruredData() {
        ModelType sdModel = null;
        try {
            sdModel = loadModel(modelUri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List dataTypes = sdModel.getTypeDeclarations().getTypeDeclaration();
        legoComboViewer.setInput(dataTypes);
        updateLaunchConfigurationDialog();
    }

    public ModelType loadModel(String modelUri) throws Exception
    {
          WorkflowModelManager modelManager = new WorkflowModelManager();
          modelManager.load(URI.createURI(modelUri));
          ModelType model = modelManager.getModel();
          return model;
    }

}
