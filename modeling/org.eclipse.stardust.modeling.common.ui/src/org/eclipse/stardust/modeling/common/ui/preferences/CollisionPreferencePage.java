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
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.UI_Messages;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

public class CollisionPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
   private Button chkUpdate;   
   private Button chkRefresh;   
   private LabeledText refreshRate;
   private Button chkRetry;   
   private LabeledText retryRate;
   
   private ModifyListener rateListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         String refreshValue = refreshRate.getText().getText();
         boolean isValid = validate(refreshValue);

         if(isValid)
         {
            refreshValue = retryRate.getText().getText();
            isValid = validate(refreshValue);
         }
         
         if(!isValid)
         {         
            setErrorMessage(UI_Messages.CollisionPreferencePage_refreshRateValidation);            
            setValid(false);
         }
         else
         {
            setValid(true);
            setErrorMessage(null);
         }
      }      
   };         
   
   protected Control createContents(Composite parent)
   {
      Composite panel = FormBuilder.createComposite(parent, 3);

      Group updateGroup = FormBuilder.createGroup(panel,
            UI_Messages.CollisionPreferencePage_Update, 1, 3);
      updateGroup.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(3));
      chkUpdate = FormBuilder.createCheckBox(updateGroup,
            UI_Messages.CollisionPreferencePage_enableUpdate, 3);            
      
      Group refreshGroup = FormBuilder.createGroup(panel,
            UI_Messages.CollisionPreferencePage_Refresh, 2, 3);
      refreshGroup.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(3));
      chkRefresh = FormBuilder.createCheckBox(refreshGroup,
            UI_Messages.CollisionPreferencePage_enableRefresh, 3);      
      refreshRate = FormBuilder.createLabeledText(refreshGroup, UI_Messages.CollisionPreferencePage_refreshRate);
      
      Group retryGroup = FormBuilder.createGroup(panel,
            UI_Messages.CollisionPreferencePage_Retry, 2, 3);
      chkRetry = FormBuilder.createCheckBox(retryGroup,
            UI_Messages.CollisionPreferencePage_enableRetry, 3);      
      retryGroup.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(3));
      retryRate = FormBuilder.createLabeledText(retryGroup, UI_Messages.CollisionPreferencePage_retryRate);      
      
      setValues();
      return panel;
   }

   private void setValues()
   {
      chkUpdate.setSelection(PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_COLLISION_UPDATE));
      
      chkRefresh.setSelection(PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_COLLISION_REFRESH));
      refreshRate.getText().setText(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_COLLISION_REFRESH_RATE));      
      chkRetry.setSelection(PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY));
      retryRate.getText().setText(PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY_RATE));      
      
      refreshRate.getText().addModifyListener(rateListener);
      retryRate.getText().addModifyListener(rateListener);
   }

   public boolean performOk()
   {
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_COLLISION_UPDATE,
            chkUpdate.getSelection());            
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_COLLISION_REFRESH,
            chkRefresh.getSelection());      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_COLLISION_REFRESH_RATE,
            refreshRate.getText().getText());      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY,
            chkRetry.getSelection());            
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY_RATE,
            retryRate.getText().getText());            
      
      return true;
   }

   public void init(IWorkbench workbench)
   {      
   }

   private boolean validate(String refreshValue)
   {
      boolean isValid = true;
      if(StringUtils.isEmpty(refreshValue))
      {
         isValid = false;
      }
      else
      {
         int value = 0;
         try
         {
            value = Integer.parseInt(refreshValue);
         }
         catch (NumberFormatException e1)
         {
            isValid = false;
         }
         if (value < 30 || value > 7200)
         {
            isValid = false;               
         }
      }
      return isValid;
   }
}