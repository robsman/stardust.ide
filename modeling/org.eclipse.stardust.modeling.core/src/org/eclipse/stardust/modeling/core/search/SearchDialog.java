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
package org.eclipse.stardust.modeling.core.search;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class SearchDialog extends Dialog
{
   private Text searchText;

   private String searchString;

   public SearchDialog(Shell parentShell)
   {
      super(parentShell);
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite control = (Composite) super.createDialogArea(parent);
      Composite composite = FormBuilder.createComposite(control, 2);
      FormBuilder.createLabel(composite, Diagram_Messages.LB_Find);
      searchText = FormBuilder.createText(composite);
      return control;
   }

   protected Control createContents(Composite parent)
   {
      Control control = super.createContents(parent);
      getButton(IDialogConstants.OK_ID).setText(Diagram_Messages.BTN_Search);
      return control;
   }

   protected void configureShell(Shell newShell)
   {
      super.configureShell(newShell);
      newShell.setText(Diagram_Messages.TITEL_Search);
   }

   protected void okPressed()
   {
      searchString = searchText.getText().trim();
      super.okPressed();
   }

   public String getSearchString()
   {
      return searchString;
   }

}
