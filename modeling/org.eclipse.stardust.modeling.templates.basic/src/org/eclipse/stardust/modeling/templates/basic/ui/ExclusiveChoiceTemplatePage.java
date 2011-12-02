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

public class ExclusiveChoiceTemplatePage extends WizardPage {
	
	private Properties properties;
	private String location;
	private String number;
	private Text numberText;
	private Combo combo;
	private String kindText;


	protected ExclusiveChoiceTemplatePage(String pageName, String title, ImageDescriptor titleImage) {
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
		Group group = createGroup(composite, "Exclusive Choice Pattern");
		Label nameLabel = new Label(group, SWT.NONE);
		nameLabel.setText("Number of Activities:");
		numberText = new Text(group, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
		numberText.setLayoutData(data);
				
        
	    Label kind = new Label(group, SWT.NONE);
	    kind.setText("Activity Kind:");
		combo = new Combo(group, SWT.READ_ONLY);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        combo.setLayoutData(data);
        combo.setItems(new String[]{"Manual Activity", "Application Activity", "Route Activity"});
        combo.select(1);
        kindText = combo.getText();
        
    		

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
        
        Listener listener = new Listener() {
			public void handleEvent(Event event) {
				validateFields();
			}
		};
		
		numberText.addListener(SWT.Modify, listener);
		setControl(composite);
		validateFields();
	}

	private void validateFields() {
        number = numberText.getText();
        kindText = combo.getText();        
        if (number.length() == 0) {
           setErrorMessage("Please provide a valid number of activities (>=2 and <=99)");
           setPageComplete(false);
           return;
       }
       try {
          int i = Integer.parseInt(number);
          if (i < 2 || i >99) {
              setErrorMessage("Please provide a valid number of activities (>=2 and <=99)");
              setPageComplete(false);
              return;  
          }
        } catch (Throwable t) {
           setErrorMessage("Please provide a valid number of activities (>=2 and <=99)");
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
		// Set the result to be the current values
		/*Properties result = new Properties();
		result.setProperty("url", urlText.getText()); //$NON-NLS-1$
		this.properties = result;*/
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

}
