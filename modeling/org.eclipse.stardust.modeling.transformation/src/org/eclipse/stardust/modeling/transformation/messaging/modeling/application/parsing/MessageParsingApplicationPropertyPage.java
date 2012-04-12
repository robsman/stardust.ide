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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.parsing;


import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationUtils;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.AbstractMessageProcessingPropertyPage;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.launch.ProcessingLauncherConstants;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.TypesLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;

/**
 * The implementation deals with <code>Rule</code> and <code>RuleModel</code>
 * objects and persists the resource URIs of those.
 * 
 * @author Marc Gille
 */
public class MessageParsingApplicationPropertyPage extends
		AbstractMessageProcessingPropertyPage implements
		IApplicationPropertyPage
{
	private ModelType modelType;
	private MessageTransformationUtils mtaUtils = new MessageTransformationUtils();

	public MessageParsingApplicationPropertyPage()
	{
		super();
	}

	/**
	 * 
	 */
	public void loadFieldsFromElement(IModelElementNodeSymbol symbol,
			IModelElement element)
	{
		modelType = ModelUtils.findContainingModel(element);
		initializeMessageFormatComboViewer(element);
		initializeTypeComboViewer((ApplicationType) element);
		initializeTestConfigurationComboViewer(element);
	}
	
	/**
	 * 
	 */
	public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ApplicationType application = (ApplicationType) element;

      application.getAccessPoint().clear();

      DataTypeType dataTypeSerializable = ModelUtils.getDataType(application,
            CarnotConstants.SERIALIZABLE_DATA_ID);

      AccessPointType apInputMessage = AccessPointUtil.createAccessPoint("InputMessageString", //$NON-NLS-1$
            "InputMessageString: java.lang.String", DirectionType.IN_LITERAL, //$NON-NLS-1$
            dataTypeSerializable);
      AttributeUtil.setAttribute(apInputMessage, CarnotConstants.CLASS_NAME_ATT,
            String.class.getName());
      application.getAccessPoint().add(apInputMessage);

      if (messageTypeComboViewer.getSelection() != null && !messageTypeComboViewer.getSelection().isEmpty())
      {
         DataTypeType structuredDataType = ModelUtils.getDataType(application,
               StructuredDataConstants.STRUCTURED_DATA);

         TypeDeclarationType selectedTypeDeclaration = (TypeDeclarationType) ((IStructuredSelection) messageTypeComboViewer.getSelection()).getFirstElement();
         IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(selectedTypeDeclaration);
         StructAccessPointType apOutputMessage = mtaUtils.createStructAccessPoint(
               "OutputMessage", "OutputMessage ("+selectedTypeDeclaration.getId()+")", DirectionType.OUT_LITERAL, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
               structuredDataType, xPathMap.getRootXPath(), xPathMap);
         application.getAccessPoint().add(apOutputMessage);
         StructuredTypeUtils.setStructuredAccessPointAttributes(apOutputMessage, selectedTypeDeclaration);
      }      
      loadMessageFormat(element);
      loadTestConfiguration(element);
   }

	/**
	 * 
	 */
	public Control createBody(final Composite parent)
	{
		final Composite composite = FormBuilder.createComposite(parent, 1);

		formatComboViewer = (ComboViewer) FormBuilder.createComboViewer(
				composite, Modeling_Messages.LBL_SR_MSG_FORMAT,
				new ArrayList()).getViewer();

		formatComboViewer.setContentProvider(new ArrayContentProvider());
				
		messageTypeComboViewer = (ComboViewer) FormBuilder.createComboViewer(
				composite, Modeling_Messages.LBL_TARGET_MSG_TYPE,
				new ArrayList()).getViewer();

		messageTypeComboViewer.setContentProvider(new ArrayContentProvider());
		messageTypeComboViewer.setLabelProvider(new TypesLabelProvider());

		// Testing and debugging group
		
		createTestGroup(composite);
		this.messageTypeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

         public void selectionChanged(SelectionChangedEvent event)
         {
            enableRunButton();
            
         }
		   
		});
		
	      this.formatComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

	         public void selectionChanged(SelectionChangedEvent event)
	         {
	            enableRunButton();
	            
	         }
	           
	        });

		createConfigurationLink.addSelectionListener(new SelectionListener()
		{
			public void widgetSelected(SelectionEvent e)
			{
				ILaunchManager lm = DebugPlugin.getDefault().getLaunchManager();
				ILaunchConfigurationType ct = lm.getLaunchConfigurationType("org.eclipse.stardust.modeling.transformation.application.launch.testType"); //$NON-NLS-1$
				
				try
				{
					FileDialog fd = new FileDialog(composite.getShell(), SWT.SAVE);
				
					
					fd.setText(Modeling_Messages.TXT_SEL_FILE_CONTAINING_TEST_IP_MSG);
					fd.setFilterPath("C:/"); //$NON-NLS-1$

					String sourceFilePath = fd.open();
					if (sourceFilePath != null) {
	                    ILaunchConfiguration[] launchConfigurations = lm.getLaunchConfigurations(ct);
	                    ILaunchConfigurationWorkingCopy workingCopy = ct.newInstance(null, "New Messaging Test" + launchConfigurations.length); //$NON-NLS-1$
	                                        
	                    IProject project = ModelUtils.getProjectFromEObject(modelType);
	                    workingCopy.setAttribute(ProcessingLauncherConstants.MODEL_URI, modelType.eResource().getURI().toString());
	                    workingCopy.setAttribute(ProcessingLauncherConstants.IPP_PROJECT, project.getName());
	                    workingCopy.setAttribute(ProcessingLauncherConstants.MODEL_NAME, modelType.eResource().getURI().lastSegment());
	                    
	                    if ( !messageTypeComboViewer.getSelection().isEmpty())
	                    {
	                       TypeDeclarationType selectedTypeDeclaration = (TypeDeclarationType) ((IStructuredSelection) messageTypeComboViewer.getSelection()).getFirstElement();
	                       workingCopy.setAttribute(ProcessingLauncherConstants.LEGO_DATA, selectedTypeDeclaration.getId());                                       
	                    }
	                    
	                    workingCopy.setAttribute(ProcessingLauncherConstants.SOURCE_FILE_PATH, sourceFilePath);
	                    workingCopy.setAttribute(ProcessingLauncherConstants.SOURCE_FORMAT, getMessageFormat());
	                    workingCopy.setAttribute(ProcessingLauncherConstants.TARGET_FORMAT, getMessageFormat());
	                    workingCopy.setAttribute(ProcessingLauncherConstants.TEST_SERIALIZATION, true);
	                    workingCopy.setAttribute(ProcessingLauncherConstants.SCREEN_ONLY, true);
	                    workingCopy.setAttribute(ProcessingLauncherConstants.TEST_PARSING, ct.getName());
	                    
	                    ILaunchConfiguration configuration = workingCopy.doSave();
	                
	                    refreshTestConfigurationComboViewer();

	                    testConfigurationsComboViewer.setSelection(new StructuredSelection(
	                            configuration));
					}					
				}
				catch (CoreException e1)
				{
					throw new RuntimeException(Modeling_Messages.EXC_COULD_NOT_CREATE_LAUNCH_CFG, e1);
				}
			}
			
			public void widgetDefaultSelected(SelectionEvent e)
			{
			}
		});

		return composite;
	}
	

}