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
package org.eclipse.stardust.modeling.core.wizards;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;


public abstract class ExportDiagramResourceWizardPage extends WizardExportResourcesPage
{
   private final IStructuredSelection initialResourceSelection;

   protected ExportDiagramResourceWizardPage(String pageName,
         IStructuredSelection selection)
   {
      super(pageName, selection);
      this.initialResourceSelection = selection;
   }

   public void createControl(Composite parent)
   {
      initializeDialogUnits(parent);

      Composite composite = new Composite(parent, SWT.NULL);
      composite.setLayout(new GridLayout());
      composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
            | GridData.HORIZONTAL_ALIGN_FILL));
      composite.setFont(parent.getFont());

      createResourcesGroup(composite);
      createButtons(composite);

      createDestinationGroup(composite);

      createOptionsGroup(composite);

      restoreResourceSpecificationWidgetValues(); // ie.- local
      restoreWidgetValues(); // ie.- subclass hook
      if (initialResourceSelection != null)
      {
         setupBasedOnInitialSelections();
      }

      updateWidgetEnablements();
      setPageComplete(determinePageCompletion());
      setErrorMessage(null); // should not initially have error message

      setControl(composite);
   }

   private void createButtons(Composite parent)
   {
      Font font = parent.getFont();

      Composite buttonComposite = new Composite(parent, SWT.NONE);
      buttonComposite.setFont(parent.getFont());

      GridLayout layout = new GridLayout();
      layout.numColumns = 3;
      layout.makeColumnsEqualWidth = true;
      buttonComposite.setLayout(layout);
      buttonComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
            | GridData.HORIZONTAL_ALIGN_FILL));

      Button selectTypesButton = createButton(buttonComposite,
            IDialogConstants.SELECT_TYPES_ID, Diagram_Messages.LB_SelectTypes, false);

      SelectionListener listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            handleTypesEditButtonPressed();
         }
      };
      selectTypesButton.addSelectionListener(listener);
      selectTypesButton.setFont(font);
      setButtonLayoutData(selectTypesButton);
   }
}