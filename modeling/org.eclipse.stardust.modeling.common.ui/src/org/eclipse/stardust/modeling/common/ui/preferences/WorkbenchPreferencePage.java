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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.BpmUiActivator;
import org.eclipse.stardust.modeling.common.ui.UI_Messages;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

public class WorkbenchPreferencePage extends PreferencePage
      implements IWorkbenchPreferencePage
{
   private Button chkEnableAutoValidation;

   private Text licenseFilePath;

   private Button chkEnableAutoIdGeneration;

   private Button radioFocusModeElement;
   
   private Button radioFocusModePropertyDialog;

   private Button radioFocusModeEditBox;
   
   private Button radioAlwaysSwitchActivityType;

   private Button radioNeverSwitchActivityType;

   private Button radioWarnSwitchActivityType;

   private Button chkEnableAutoSubprocessNameGeneration;

   public WorkbenchPreferencePage()
   {}

   public WorkbenchPreferencePage(String title)
   {
      super(title);
   }

   public WorkbenchPreferencePage(String title, ImageDescriptor image)
   {
      super(title, image);
   }
   
   protected Control createContents(Composite parent)
   {
      Composite panel = FormBuilder.createComposite(parent, 3);

      this.chkEnableAutoValidation = FormBuilder.createCheckBox(panel,
            UI_Messages.WorkbenchPreferencePage_autoValidation, 3);

      this.chkEnableAutoIdGeneration = FormBuilder.createCheckBox(panel,
            UI_Messages.WorkbenchPreferencePage_autoIdGeneration, 3);

      this.chkEnableAutoSubprocessNameGeneration = FormBuilder.createCheckBox(panel,
            UI_Messages.WorkbenchPreferencePage_autoSubprocessNameGeneration, 3);
      
      Group groupSwitchFocusMode = FormBuilder.createGroup(panel,
            UI_Messages.LB_SwitchFocusMode, 3, 3);
      groupSwitchFocusMode.setLayoutData(FormBuilder
            .createDefaultSingleLineWidgetGridData(3));
      radioFocusModeElement = FormBuilder.createRadioButton(
            groupSwitchFocusMode, UI_Messages.LB_Element);
      radioFocusModePropertyDialog = FormBuilder.createRadioButton(
            groupSwitchFocusMode, UI_Messages.LB_PropertyDialog);
      radioFocusModeEditBox = FormBuilder.createRadioButton(
            groupSwitchFocusMode, UI_Messages.LB_EditBox);
      
      Group groupSwitchActivityType = FormBuilder.createGroup(panel,
            UI_Messages.LB_SwitchAutomaticallyActivityType, 3, 3);
      groupSwitchActivityType.setLayoutData(FormBuilder
            .createDefaultSingleLineWidgetGridData(3));
      radioAlwaysSwitchActivityType = FormBuilder.createRadioButton(
            groupSwitchActivityType, UI_Messages.LB_Always);
      radioNeverSwitchActivityType = FormBuilder.createRadioButton(
            groupSwitchActivityType, UI_Messages.LB_Never);
      radioWarnSwitchActivityType = FormBuilder.createRadioButton(
            groupSwitchActivityType, UI_Messages.LB_ShowWarning);

      this.licenseFilePath = FormBuilder.createLabeledText(panel,
            UI_Messages.WorkbenchPreferencePage_licenseFilePath).getText();
      FormBuilder.createButton(panel, UI_Messages.BTN_Browse, new SelectionListener()
      {
         public void widgetSelected(SelectionEvent e)
         {
            FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
            dialog.setFilterPath(licenseFilePath.getText());
            String file = dialog.open();
            if (file != null)
            {
               licenseFilePath.setText(file);
            }
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {}
      });

      updateCheckbox();
      updateSwitchActivityTypeGroup();
      updateFocusMode();
      licenseFilePath.setText(BpmUiActivator.getDefault().getTraceFilePath());

      return panel;
   }

   private void updateFocusMode()
   {
      String focusMode = PlatformUI.getPreferenceStore().getString(
            BpmProjectNature.PREFERENCE_FOCUS_MODE);
      if(StringUtils.isEmpty(focusMode))
      {
         focusMode = BpmProjectNature.DEFAULT_PREFERENCE_FOCUS_MODE;
      }
      radioFocusModeElement.setSelection(
            focusMode.equals(BpmProjectNature.PREFERENCE_FOCUS_MODE_ELEMENT));
      radioFocusModePropertyDialog.setSelection(
            focusMode.equals(BpmProjectNature.PREFERENCE_FOCUS_MODE_DIALOG));
      radioFocusModeEditBox.setSelection(
            focusMode.equals(BpmProjectNature.PREFERENCE_FOCUS_MODE_EDITOR));
   }

   private void updateSwitchActivityTypeGroup()
   {
      radioAlwaysSwitchActivityType.setSelection(PlatformUI.getPreferenceStore()
            .getBoolean(BpmProjectNature.PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE));
      radioNeverSwitchActivityType.setSelection(PlatformUI.getPreferenceStore()
            .getBoolean(BpmProjectNature.PREFERENCE_NEVER_SWITCH_ACTIVITY_TYPE));
      radioWarnSwitchActivityType.setSelection(PlatformUI.getPreferenceStore()
            .getBoolean(BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE));
   }

   private void updateCheckbox()
   {
      chkEnableAutoValidation.setSelection(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_VALIDATION));
      chkEnableAutoIdGeneration.setSelection(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION));
      chkEnableAutoSubprocessNameGeneration.setSelection(PlatformUI.getPreferenceStore()
            .getBoolean(BpmProjectNature.PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION));      
   }

   public void init(IWorkbench workbench)
   {}

   public boolean performOk()
   {
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_AUTO_VALIDATION,
            chkEnableAutoValidation.getSelection());

      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION,
            chkEnableAutoIdGeneration.getSelection());

      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION,
            chkEnableAutoSubprocessNameGeneration.getSelection());
      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE,
            radioAlwaysSwitchActivityType.getSelection());

      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_NEVER_SWITCH_ACTIVITY_TYPE,
            radioNeverSwitchActivityType.getSelection());

      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE,
            radioWarnSwitchActivityType.getSelection());

      String focusMode = BpmProjectNature.DEFAULT_PREFERENCE_FOCUS_MODE;
      if (radioFocusModeElement.getSelection())
      {
         focusMode = BpmProjectNature.PREFERENCE_FOCUS_MODE_ELEMENT;
      }
      else if (radioFocusModePropertyDialog.getSelection())
      {
         focusMode = BpmProjectNature.PREFERENCE_FOCUS_MODE_DIALOG;
      }
      else if (radioFocusModeEditBox.getSelection())
      {
         focusMode = BpmProjectNature.PREFERENCE_FOCUS_MODE_EDITOR;
      }
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_FOCUS_MODE, focusMode);
      
      BpmUiActivator.getDefault().setTraceFilePath(licenseFilePath.getText());
      return true;
   }

   protected void performDefaults()
   {
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_AUTO_VALIDATION);
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION);
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION);
      
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE);
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_NEVER_SWITCH_ACTIVITY_TYPE);
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE);

      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_FOCUS_MODE);
      
      updateCheckbox();
      licenseFilePath.setText(BpmUiActivator.getDefault().getTraceFilePath());
   }
}