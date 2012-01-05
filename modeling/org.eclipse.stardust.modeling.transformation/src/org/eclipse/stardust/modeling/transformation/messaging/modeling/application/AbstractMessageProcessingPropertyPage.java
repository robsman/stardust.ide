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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.actions.OpenLaunchDialogAction;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.transformation.messaging.format.FormatManager;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.launch.ProcessingLauncherConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;

public abstract class AbstractMessageProcessingPropertyPage extends
		AbstractModelElementPropertyPage
{
	protected ComboViewer formatComboViewer;
	protected ComboViewer formatModelComboViewer;
	protected ComboViewer messageTypeComboViewer;
	protected ComboViewer testConfigurationsComboViewer;
	protected String xmlString;
	protected Link manageConfigurationsLink;
	protected Link createConfigurationLink;
   private Button runTestButton;

	protected void initializeTypeComboViewer(ApplicationType application)
   {
      ModelType modelType = ModelUtils.findContainingModel(application);

      List dataTypes = modelType.getTypeDeclarations().getTypeDeclaration();
      if (dataTypes.isEmpty()) {
         setErrorMessage(Modeling_Messages.MSG_MD_NOT_CONTAIN_ANY_STR_DATA_TYPES);         
      }
      messageTypeComboViewer.setInput(dataTypes.toArray());

      // try to find access point of type structured data and use its
      // type declaration reference to initialize the type combobox selection
      // if not found, the selection should remain empty
      DataTypeType structuredDataType = ModelUtils.getDataType(application,
            StructuredDataConstants.STRUCTURED_DATA);
      for (int i = 0; i < application.getAccessPoint().size(); i++ )
      {
         AccessPointType accessPoint = (AccessPointType) application.getAccessPoint()
               .get(i);
         if (accessPoint.getType() != null && accessPoint.getType().equals(structuredDataType))
         {
            String declaredTypeId = AttributeUtil.getAttributeValue(accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT);
            TypeDeclarationType declaration = modelType.getTypeDeclarations()
                  .getTypeDeclaration(declaredTypeId);
            messageTypeComboViewer.setSelection(declaration == null
                  ? StructuredSelection.EMPTY
                  : new StructuredSelection(declaration));
            return;
         }
      }
   }

	protected void initializeMessageFormatComboViewer(IModelElement element)
	{
		formatComboViewer.setInput(FormatManager.getMessageFormats());

		String messageFormat = AttributeUtil.getAttributeValue(
				(IExtensibleElement) element, Constants.MESSAGE_FORMAT);

		if (messageFormat != null)
		{
			formatComboViewer.setSelection(new StructuredSelection(
					messageFormat));
		}
	}

	protected void initializeTestConfigurationComboViewer(IModelElement element)
	{
	    refreshTestConfigurationComboViewer();
		String testConfigurationName = AttributeUtil.getAttributeValue((IExtensibleElement) element, Constants.TEST_CONFIGURATION);		
		ILaunchConfiguration lc = getConfigurationByName(testConfigurationName);
		if (lc != null) 
		{
		   testConfigurationsComboViewer.setSelection(new StructuredSelection(lc));
		}
	}

	protected ILaunchConfiguration getConfigurationByName(String testConfigurationName)
   {
       if (testConfigurationName == null) 
       {
          return null;
       }
	   ILaunchManager lm = DebugPlugin.getDefault().getLaunchManager();
       ILaunchConfigurationType ct = lm
               .getLaunchConfigurationType("org.eclipse.stardust.modeling.transformation.messaging.modeling.application.launch.testType"); //$NON-NLS-1$

       try
       {
           ILaunchConfiguration[] cfgs = lm.getLaunchConfigurations(ct);
           if (cfgs.length > 0) 
           {
              for (int i = 0; i < cfgs.length; i++) 
              {
                 if (cfgs[i].getName().equalsIgnoreCase(testConfigurationName))
                 {
                    return cfgs[i];
                 }
              }
           }
       }
       catch (CoreException e)
       {
           throw new RuntimeException(
                   Modeling_Messages.EXC_CANNOT_RETRIEVE_LAUNCH_CFG, e);
       }
       return null;
   }

   protected void refreshTestConfigurationComboViewer()
	{
		ILaunchManager lm = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType ct = lm
				.getLaunchConfigurationType("org.eclipse.stardust.modeling.transformation.messaging.modeling.application.launch.testType"); //$NON-NLS-1$

		try
		{
			ILaunchConfiguration[] cfgs = lm.getLaunchConfigurations(ct);
			testConfigurationsComboViewer.setInput(cfgs);			
		}
		catch (CoreException e)
		{
			throw new RuntimeException(
					Modeling_Messages.EXC_CANNOT_RETRIEVE_LAUNCH_CFG, e);
		}
	}

	protected void loadMessageFormat(IModelElement element)
	{
		AttributeUtil.setAttribute((IExtensibleElement) element,
				Constants.MESSAGE_FORMAT, getMessageFormat());
	}
	
	protected void loadTestConfiguration(IModelElement element) 
	{
       AttributeUtil.setAttribute((IExtensibleElement) element,
             Constants.TEST_CONFIGURATION, getTestConfiguration());	   
	}


	protected String getMessageFormat()
	{
		if (formatComboViewer.getSelection() != null)
		{
			return (String) ((IStructuredSelection) formatComboViewer
					.getSelection()).getFirstElement();
		}
		return null;
	}
	
    protected String getTestConfiguration()
    {
        if (testConfigurationsComboViewer.getSelection() != null)
        {
           ILaunchConfiguration lc = ((ILaunchConfiguration)((IStructuredSelection) testConfigurationsComboViewer
                 .getSelection()).getFirstElement()); 
           if (lc != null) 
           {
              return lc.getName();              
           }           
        }
        return null;
    }	

	protected File getFormatModelFile()
	{
		if (formatModelComboViewer.getSelection() != null)
		{
			return (File) ((IStructuredSelection) formatModelComboViewer
					.getSelection()).getFirstElement();
		}

		return null;
	}

	protected void createTestGroup(Composite composite)
	{
		final Group testGroup = new Group(composite, SWT.NONE);

		FormLayout layout = new FormLayout();

		layout.marginHeight = 10;
		layout.marginWidth = 10;

		testGroup.setLayout(layout);
		testGroup.setText(Modeling_Messages.TXT_TESTING_DEB);

		Label testConfigurationsLabel = new Label(testGroup, 0);

		testConfigurationsLabel.setText(Modeling_Messages.TXT_TEST_CFG);

		testConfigurationsComboViewer = new ComboViewer(testGroup);
		testConfigurationsComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

         public void selectionChanged(SelectionChangedEvent event)
         {
            enableRunButton();
            
         }
		   
		});

		testConfigurationsComboViewer
				.setContentProvider(new ArrayContentProvider());
		
		// TODO Move to subclass to distinguish between source and target
		
		testConfigurationsComboViewer.setLabelProvider(new LabelProvider()
		{
			public Image getImage(Object element)
			{
				return null;
			}

			public String getText(Object element)
			{
				if (element instanceof ILaunchConfiguration)
				{
					ILaunchConfiguration configuration = (ILaunchConfiguration)element;
					
					String filePath = null;
					
					try
					{
						filePath = configuration.getAttribute(ProcessingLauncherConstants.SOURCE_FILE_PATH, ""); //$NON-NLS-1$
					}
					catch (CoreException e)
					{
						throw new RuntimeException(Modeling_Messages.EXC_COULD_NOT_RETRIEVE_SR_FILE_PATH, e);
					}
							
					if (filePath != null && filePath.length() != 0)
					{
						return ((ILaunchConfiguration) element).getName() + " (" + filePath + ")";  //$NON-NLS-1$ //$NON-NLS-2$
					}
					else
					{
						return ((ILaunchConfiguration) element).getName();
					}
				}

				return null;
			}
		});

	  runTestButton = new Button(testGroup, SWT.PUSH);

		runTestButton.setText(Modeling_Messages.TXT_RUN);
		enableRunButton();
		
		runTestButton.addSelectionListener(new SelectionListener()
		{
			public void widgetSelected(SelectionEvent e)
			{
				if (testConfigurationsComboViewer.getSelection() != null)
				{
					ILaunchConfiguration cfg = (ILaunchConfiguration) ((IStructuredSelection) testConfigurationsComboViewer
							.getSelection()).getFirstElement();
					
		        boolean pr,mn,mu,ld,sfp,sf,tfp,tf;
		        pr=mn=mu=ld=sfp=sf=tfp=tf=false;
                tfp = tf = true;                
		        try
                  {
					 pr = !StringUtils.isEmpty(cfg.getAttribute(ProcessingLauncherConstants.IPP_PROJECT, "")); //$NON-NLS-1$
	                 mn = !StringUtils.isEmpty(cfg.getAttribute(ProcessingLauncherConstants.MODEL_NAME, "")); //$NON-NLS-1$
	                 mu = !StringUtils.isEmpty(cfg.getAttribute(ProcessingLauncherConstants.MODEL_URI, "")); //$NON-NLS-1$
	                 ld = !StringUtils.isEmpty(cfg.getAttribute(ProcessingLauncherConstants.LEGO_DATA, "")); //$NON-NLS-1$
	                 sfp = !StringUtils.isEmpty(cfg.getAttribute(ProcessingLauncherConstants.SOURCE_FILE_PATH, "")); //$NON-NLS-1$
	                 sf = !StringUtils.isEmpty(cfg.getAttribute(ProcessingLauncherConstants.SOURCE_FORMAT, ""));	                  //$NON-NLS-1$
	                 if (cfg.getAttribute(ProcessingLauncherConstants.TEST_SERIALIZATION, true)) {
	                    if (!cfg.getAttribute("screenOnly", false)) {	 //$NON-NLS-1$
	                       tfp = !StringUtils.isEmpty(cfg.getAttribute(ProcessingLauncherConstants.TARGET_FILE_PATH, "")); //$NON-NLS-1$
	                       tf = !StringUtils.isEmpty(cfg.getAttribute(ProcessingLauncherConstants.TARGET_FORMAT, ""));   //$NON-NLS-1$
	                    }	                       	                    	                  
	                 }
                  } catch (Throwable t) {
                     
                  }
                  if (pr && mn && mu && ld && sfp && sf && tfp && tf) {
                     DebugUITools.launch(cfg, ILaunchManager.RUN_MODE);
                  } else {
                     MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
                           SWT.ICON_WARNING | SWT.CANCEL);
                     messageBox.setText(Modeling_Messages.TXT_WR_LEER);
                     messageBox.setMessage(Modeling_Messages.MSG_SEL_RUN_CFG_NOT_CONSISTE_PLEASE_CHECK_THIS);
                     messageBox.open();
                  }                  
				}			   
			}

			public void widgetDefaultSelected(SelectionEvent e)
			{
			}
		});

		manageConfigurationsLink = new Link(testGroup, SWT.NONE);

		manageConfigurationsLink.setText(Modeling_Messages.HTML_TEST_CONFIGURATION);
		manageConfigurationsLink.addSelectionListener(new SelectionListener()
		{
			public void widgetDefaultSelected(SelectionEvent e)
			{
			}

			public void widgetSelected(SelectionEvent e)
			{
			}
		});

		createConfigurationLink = new Link(testGroup, SWT.NONE);

		createConfigurationLink
				.setText(Modeling_Messages.HTML_NEW_TEST_CFG_MATCHING_SETTINGS_PR_CFG);

		// Layout test and debugging group

		FormData formData = new FormData();

		formData.left = new FormAttachment(0, 0);
		formData.top = new FormAttachment(testConfigurationsComboViewer
				.getControl(), 0, SWT.CENTER);

		testConfigurationsLabel.setLayoutData(formData);

		formData = new FormData();

		formData.left = new FormAttachment(testConfigurationsLabel, 5,
				SWT.RIGHT);
		formData.top = new FormAttachment(runTestButton, 0, SWT.CENTER);
		formData.width = 300;

		testConfigurationsComboViewer.getControl().setLayoutData(formData);

		formData = new FormData();

		formData.left = new FormAttachment(testConfigurationsComboViewer
				.getControl(), 5, SWT.RIGHT);
		formData.top = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);

		runTestButton.setLayoutData(formData);

		formData = new FormData();

		formData.left = new FormAttachment(0, 0);
		formData.top = new FormAttachment(runTestButton, 5, SWT.BOTTOM);
		formData.right = new FormAttachment(100, 0);

		manageConfigurationsLink.setLayoutData(formData);
		manageConfigurationsLink.addSelectionListener(new SelectionListener() {

         public void widgetDefaultSelected(SelectionEvent e)
         {

         }

         public void widgetSelected(SelectionEvent e)
         {
            OpenLaunchDialogAction launchDialogAction = new OpenLaunchDialogAction("org.eclipse.debug.ui.launchGroup.run"); //$NON-NLS-1$
            launchDialogAction.run(); 
         }
		   
		});
        

		formData = new FormData();

		formData.left = new FormAttachment(0, 0);
		formData.top = new FormAttachment(manageConfigurationsLink, 5,
				SWT.BOTTOM);
		formData.right = new FormAttachment(100, 0);

		createConfigurationLink.setLayoutData(formData);
	}

   protected void enableRunButton()
   {
     runTestButton.setEnabled(true);
     setErrorMessage(null);
	 if (StringUtils.isEmpty(this.messageTypeComboViewer.getCombo().getText())) {
        runTestButton.setEnabled(false);
        setErrorMessage(Modeling_Messages.MSG_NO_MSG_TYPE_SET);
     }
     if (StringUtils.isEmpty(this.formatComboViewer.getCombo().getText())) {
        runTestButton.setEnabled(false);
        setErrorMessage(Modeling_Messages.MSG_NO_MSG_FORMAT_SET);
     } 
   }
	
}
