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
package org.eclipse.stardust.modeling.common.ui.classpath;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.common.projectnature.classpath.BpmClasspathUtils;
import org.eclipse.stardust.modeling.common.projectnature.classpath.CarnotHomeLocationClasspathContainer;
import org.eclipse.stardust.modeling.common.ui.UI_Messages;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;

/**
 * @author rsauer
 * @version $Revision$
 */
public class CarnotHomeClasspathContainerPage extends WizardPage
      implements IClasspathContainerPage
{
   private String initialValue;

   private LabeledText txtHomeFolder;

   public CarnotHomeClasspathContainerPage()
   {
      super(UI_Messages.STR_HomeLocation);
   }

   public boolean finish()
   {
      // TODO Auto-generated method stub
      return true;
   }

   public IClasspathEntry getSelection()
   {
      String homeFolder = null;

      if ((null != txtHomeFolder) && !txtHomeFolder.getText().isDisposed())
      {
         homeFolder = txtHomeFolder.getText().getText();
      }

      IPath entryPath = BpmClasspathUtils
            .encodeClasspathEntryHint(
                  CarnotHomeLocationClasspathContainer.PATH_CARNOT_HOME_LOCATION_CP,
                  homeFolder);

      return JavaCore.newContainerEntry(entryPath);
   }

   public void setSelection(IClasspathEntry containerEntry)
   {
      this.initialValue = (null != containerEntry) ? BpmClasspathUtils
            .retrieveEncodedClasspathEntryHint(containerEntry.getPath()) : null;
   }

   public void createControl(Composite parent)
   {
      Composite panel = FormBuilder.createComposite(parent, 3);

      this.txtHomeFolder = FormBuilder.createLabeledText(panel,
            UI_Messages.LB_HomeLocation);
      FormBuilder.createButton(panel, UI_Messages.BTN_Browse, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent event)
         {
            browseForFile(txtHomeFolder.getText());
         }
      });

      txtHomeFolder.getText().setText(
            !StringUtils.isEmpty(initialValue) ? initialValue : ""); //$NON-NLS-1$

      setControl(panel);
   }

   protected void browseForFile(Text txtTarget)
   {
      DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.OPEN);
      dialog.setFilterPath(txtTarget.getText());
      String directory = dialog.open();
      if (directory != null)
      {
         txtTarget.setText(directory);
      }
   }
}
