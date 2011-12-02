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
package org.eclipse.stardust.modeling.data.structured.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


public class SchemaLocationPage extends WizardPage 
{
  protected Button fileButton;
  protected Button urlButton;
  private String location;
    
  public SchemaLocationPage(String location)
  {
    super("XSDLocationPage"); //$NON-NLS-1$

    this.setTitle(Structured_Messages.SchemaLocationPageTitle);
    this.setDescription(Structured_Messages.SchemaLocationPageDescription);
    this.location = location;
  }
    
  public boolean isPageComplete()
  {
    return true;
  }
    
  public void createControl(Composite parent)
  {
    Composite base = new Composite(parent, SWT.NONE);
    base.setLayout(new GridLayout());
      
    FormBuilder.createLabel(base, Structured_Messages.SelectSchemaFromLabel);
    Composite radioButtonsGroup = FormBuilder.createComposite(base, 1);
    fileButton = FormBuilder.createRadioButton(radioButtonsGroup, Structured_Messages.WorkbenchLocation);
    urlButton = FormBuilder.createRadioButton(radioButtonsGroup, Structured_Messages.HTTPLocation);
    if (location != null)
    {
       urlButton.setSelection(true);
       fileButton.setEnabled(false);
    }

    setControl(base);
  }

  // actions on finish
  public boolean performFinish()
  {
    return true;
  }

  public boolean isURL()
  {
    return location != null || urlButton.getSelection();
  }
}
