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
package org.eclipse.stardust.modeling.modelimport;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.IDialogSettings;

public interface IImportModelWizardPage
{
   public boolean performFinish();
   public void updateButtons();
   
   public IDialogSettings getWizardSettings();
   
   public IProject getProjectContext();
}
