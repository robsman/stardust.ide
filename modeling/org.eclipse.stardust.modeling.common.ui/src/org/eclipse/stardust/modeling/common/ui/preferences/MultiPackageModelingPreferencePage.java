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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;


public class MultiPackageModelingPreferencePage extends PreferencePage
      implements IWorkbenchPreferencePage
{

   private Button visibilityPublic;

   private Button visibilityPrivate;

   protected Control createContents(Composite parent)
   {
      Composite panel = FormBuilder.createComposite(parent, 2);

      Group visibilityGroup = FormBuilder.createGroup(panel,
            UI_Messages.MultiPackageModelingPreferencePage_Visibility, 2, 2);
      visibilityGroup.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(2));
      visibilityPublic = FormBuilder.createRadioButton(visibilityGroup,
            UI_Messages.MultiPackageModelingPreferencePage_Public);
      visibilityPrivate = FormBuilder.createRadioButton(visibilityGroup,
            UI_Messages.MultiPackageModelingPreferencePage_Private);
      setValues();
      return panel;
   }

   private void setValues()
   {
      String visibility = PlatformUI.getPreferenceStore().getString(
            BpmProjectNature.PREFERENCE_MULTIPACKAGEMODELING_VISIBILITY);
      if (visibility == null || visibility == "" || visibility.equalsIgnoreCase("Public")) //$NON-NLS-1$ //$NON-NLS-2$
      {
         visibilityPublic.setSelection(true);
         visibilityPrivate.setSelection(false);
      }
      else
      {
         visibilityPublic.setSelection(false);
         visibilityPrivate.setSelection(true);
      }
   }

   public boolean performOk()
   {
      if (visibilityPublic.getSelection())
      {
         PlatformUI.getPreferenceStore().setValue(
               BpmProjectNature.PREFERENCE_MULTIPACKAGEMODELING_VISIBILITY, "Public"); //$NON-NLS-1$
      }
      else
      {
         PlatformUI.getPreferenceStore().setValue(
               BpmProjectNature.PREFERENCE_MULTIPACKAGEMODELING_VISIBILITY, "Private"); //$NON-NLS-1$
      }
      return true;
   }

   public void init(IWorkbench workbench)
   {}

}