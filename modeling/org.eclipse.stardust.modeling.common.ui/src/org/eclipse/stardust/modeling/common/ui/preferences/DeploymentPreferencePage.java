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
package org.eclipse.stardust.modeling.common.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.UI_Messages;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;


public class DeploymentPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
   private Button alwaysDeploy;   
   
   private LabeledText realm;
   private LabeledText partition;
   private LabeledText id;
   private LabeledText password;
   private LabeledText domain;
   
   protected Control createContents(Composite parent)
   {
      Composite panel = FormBuilder.createComposite(parent, 3);

      Group deployGroup = FormBuilder.createGroup(panel,
            UI_Messages.PANEL_DEPLOY_MD, 2, 3);
      deployGroup.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(2));
      alwaysDeploy = FormBuilder.createCheckBox(deployGroup,
            UI_Messages.BOX_ALWAYS_DEPLOY_NEW_VERSION, 3);            

      realm = FormBuilder.createLabeledText(deployGroup, UI_Messages.LBL_TXT_REALM);
      partition = FormBuilder.createLabeledText(deployGroup, UI_Messages.LBL_TXT_PARTITION);
      id = FormBuilder.createLabeledText(deployGroup, UI_Messages.LBL_TXT_ID);
      password = FormBuilder.createLabeledText(deployGroup, UI_Messages.LBL_TXT_PASSWORD);
      domain = FormBuilder.createLabeledText(deployGroup, UI_Messages.LBL_TXT_DOMAIN);
      
      
      setValues();
      return panel;
   }

   private void setValues()
   {      
      alwaysDeploy.setSelection(PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_DEPLOY_version));
      
      realm.getText().setText(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_realm));            
      partition.getText().setText(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_partition));            
      id.getText().setText(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_id));            
      password.getText().setText(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_password));            
      domain.getText().setText(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_domain));            
   }

   public boolean performOk()
   {
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_DEPLOY_version,
            alwaysDeploy.getSelection());                  
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_DEPLOY_realm,
            realm.getText().getText());      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_DEPLOY_partition,
            partition.getText().getText());      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_DEPLOY_id,
            id.getText().getText());      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_DEPLOY_password,
            password.getText().getText());      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_DEPLOY_domain,
            domain.getText().getText());      
      
      return true;
   }

   public void init(IWorkbench workbench)
   {      
   }
}