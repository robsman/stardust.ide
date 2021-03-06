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
package org.eclipse.stardust.modeling.templates.basic.ui;


import java.util.Properties;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.modeling.templates.Templates_Messages;
import org.eclipse.stardust.modeling.templates.basic.Templates_Basic_Messages;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class LoopTemplatePage extends WizardPage {
	
	private Properties properties;
	private String location;
	private String number;
	private Text numberText;
	private Text conditionText;
	private Combo combo;
	private Combo orientationCombo;
	private String kindText;
	private String orientationText;
	private String loopCondition;


	protected LoopTemplatePage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite (parent, SWT.NO);
		composite.setLayout(new GridLayout());
		GridData gd = new GridData();
		
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;
		composite.setLayoutData(gd);
		Group group = createGroup(composite, Templates_Basic_Messages.TXT_LOOP_PATTERN);
		Label nameLabel = new Label(group, SWT.NONE);
		nameLabel.setText(Templates_Basic_Messages.LBL_TXT_NUMBER_OF_ACTIVITIES);
		numberText = new Text(group, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
		numberText.setLayoutData(data);
				
        
	    Label kind = new Label(group, SWT.NONE);
	    kind.setText(Templates_Basic_Messages.LBL_TXT_ACTIVITY_KIND);
		combo = new Combo(group, SWT.READ_ONLY);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        combo.setLayoutData(data);
        combo.setItems(new String[]{Templates_Messages.MANUAL_ACTIVITY, Templates_Messages.APPLICATION_ACTIVITY, Templates_Messages.ROUTE_ACTIVITY});
        combo.select(1);
        kindText = Templates_Basic_Messages.TXT_APPLICATION_ACTIVITY;
        
        Label conditionLabel = new Label(group, SWT.NONE);
        conditionLabel.setText(Templates_Basic_Messages.LBL_TXT_LOOP_CONDITIONS);
        conditionText = new Text(group, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        conditionText.setLayoutData(data);
        
    		
		combo.addSelectionListener(new SelectionListener(){

         public void widgetDefaultSelected(SelectionEvent e)
         {
            // TODO Auto-generated method stub
            
         }

         public void widgetSelected(SelectionEvent e)
         {
            kindText = combo.getText() ;           
         }
		   
		});
		
	    Label orientation = new Label(group, SWT.NONE);
	    orientation.setText(Templates_Basic_Messages.LBL_TXT_ORIENTATION);
	    orientationCombo = new Combo(group, SWT.READ_ONLY);
	    data = new GridData(GridData.FILL_HORIZONTAL);
	    data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
	    orientationCombo.setLayoutData(data);
	    orientationCombo.setItems(new String[]{Templates_Basic_Messages.COMBO_BOX_VERTICAL, Templates_Basic_Messages.COMBO_BOX_HORIZONTAL});
	    orientationCombo.select(0);
	    orientationText = Templates_Basic_Messages.TXT_VERTICAL;
	    
	    orientationCombo.addSelectionListener(new SelectionListener(){

	          public void widgetDefaultSelected(SelectionEvent e)
	          {
	             // TODO Auto-generated method stub
	             
	          }

	          public void widgetSelected(SelectionEvent e)
	          {
	             orientationText = orientationCombo.getText() ;           
	          }
	            
	         });
        
        Listener listener = new Listener() {
			public void handleEvent(Event event) {
				validateFields();
			}
		};
		
		numberText.addListener(SWT.Modify, listener);
		conditionText.addListener(SWT.Modify, listener);
		setControl(composite);
		validateFields();
	}

	private void validateFields() {
        number = numberText.getText();
        kindText = combo.getText();
        loopCondition = conditionText.getText();
        if (number.length() == 0) {
           setErrorMessage(Templates_Basic_Messages.ERR_MSG_PLEASE_PROVIDE_A_VALID_NUMBER_OF_ACTIVITIES);
           setPageComplete(false);
           return;
       }
       try {
          int i = Integer.parseInt(number);
          if (i < 2 || i >99) {
              setErrorMessage(Templates_Basic_Messages.ERR_MSG_PLEASE_PROVIDE_A_VALID_NUMBER_OF_ACTIVITIES);
              setPageComplete(false);
              return;  
          }
        } catch (Throwable t) {
           setErrorMessage(Templates_Basic_Messages.ERR_MSG_PLEASE_PROVIDE_A_VALID_NUMBER_OF_ACTIVITIES);
           setPageComplete(false);
           return;         
        }
        if (loopCondition.length() == 0) {
           setErrorMessage(Templates_Basic_Messages.ERR_MSG_PLEASE_PROVIDE_A_LOOP_CONDITION);
           setPageComplete(false);
           return;         
        }
		setErrorMessage(null);
		setPageComplete(true);
	}

	
	protected Group createGroup(Composite parent, String text) {
		Group group = new Group(parent, SWT.NULL);
		group.setText(text);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		
		group.setLayoutData(data);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		group.setLayout(layout);
		return group;
	}
	
	public boolean finish(IProgressMonitor monitor) {
		return true;
	}

	public Control getControl() {
		return super.getControl();
	}

	public String getDescription() {
		return super.getDescription();
	}

	protected void initializeDialogUnits(Control testControl) {
		super.initializeDialogUnits(testControl);
	}

	public void setMessage(String newMessage) {
		super.setMessage(newMessage);
	}
	
	public Properties getProperties() {
		return properties;
	}

	public String getLocation() {
		return location;
	}
	
	public String getNumber() {
		return number;
	}

   public String getKind()
   {
      return kindText;
   }

   public String getOrientationText()
   {
      return orientationText;
   }

   public String getLoopCondition()
   {
      return loopCondition;
   }

}
