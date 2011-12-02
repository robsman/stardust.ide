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
package org.eclipse.stardust.modeling.core.properties;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelVariable;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;


public class LiteralSelectionDialog extends Dialog implements ModifyListener
{
   private String title = Diagram_Messages.TXT_DELETE_VARIABLE;   
   
   private WorkflowModelEditor wfme;

   private Composite mainComposite;

   protected ViewerFilter selectedFilter;

   private Map<String, String> indexMap = new HashMap<String, String>();

   private Button emptyLiteralButton;

   private Button defaultValueButton;

   private Button specifyLiteralButton;

   private boolean canceled;

   private Text literalText;

   private boolean defaultValueSelected;

   private boolean emptyLiteralSelected = true;

   private boolean literalSelected;

   private ModelVariable modelVariable;

   private String literal;


   public LiteralSelectionDialog(Shell parentShell, ModelVariable modelVariable)
   {
      super(parentShell);
      setShellStyle(SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL
            | SWT.RESIZE);
      this.modelVariable = modelVariable;
   }
   
   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      shell.setText(title);
   }   

   @Override
   protected void cancelPressed()
   {
      canceled = true;
      super.cancelPressed();
   }

   public boolean isCanceled()
   {
      return canceled;
   }

   protected Control createDialogArea(Composite parent)
   {
      mainComposite = parent;
      if ((null != PlatformUI.getWorkbench())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage()))
      {
         IEditorPart currentEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
               .getActivePage().getActiveEditor();
         if (currentEditor instanceof WorkflowModelEditor)
         {
            wfme = (WorkflowModelEditor) currentEditor;
         }
      }

      Composite comp = (Composite) super.createDialogArea(parent);

      Group radioComposite = new Group(comp, SWT.NONE);
      radioComposite.setText(title);
      GridLayout radioCompLayout = new GridLayout();
      radioComposite.setLayout(radioCompLayout);
      GridData radioCompGridData = new GridData();
      radioCompGridData.grabExcessHorizontalSpace = true;
      radioCompGridData.grabExcessVerticalSpace = true;
      radioCompGridData.horizontalAlignment = SWT.FILL;
      radioCompGridData.verticalAlignment = SWT.FILL;
      radioComposite.setLayoutData(radioCompGridData);

      Label emptyLabel = new Label(radioComposite, SWT.NONE);
      Label label = new Label(radioComposite, SWT.NONE);
      label.setText(Diagram_Messages.TXT_SUBSTITUTE_ALL_OCCURRENCES_OF_THE_VARIABLE_TO_BE_REMOVED);
      emptyLabel = new Label(radioComposite, SWT.NONE);

      emptyLiteralButton = new Button(radioComposite, SWT.RADIO);
      emptyLiteralButton.setText(Diagram_Messages.TXT_WITH_AN_EMPTY_LITERAL);
      emptyLiteralButton.addSelectionListener(new SelectionListener()
      {

         public void widgetDefaultSelected(SelectionEvent e)
         {}

         public void widgetSelected(SelectionEvent e)
         {
            specifyLiteralButton.setSelection(false);
            literalText.setEnabled(false);
            defaultValueSelected = false;
            literalSelected = false;
            emptyLiteralSelected = true;
         }

      });

      defaultValueButton = new Button(radioComposite, SWT.RADIO);
      defaultValueButton
            .setText(Diagram_Messages.TXT_WITH_THE_DEFAULT_VALUE_OF_THE_VARIABLE_TO_BE_REMOVED);
      defaultValueButton.addSelectionListener(new SelectionListener()
      {

         public void widgetDefaultSelected(SelectionEvent e)
         {}

         public void widgetSelected(SelectionEvent e)
         {
            specifyLiteralButton.setSelection(false);
            literalText.setEnabled(false);
            defaultValueSelected = true;
            literalSelected = false;
            emptyLiteralSelected = false;
         }
      });

      Composite literalPanel = new Composite(radioComposite, SWT.NONE);
      GridLayout literalLayout = new GridLayout();
      literalLayout.numColumns = 2;
      literalLayout.horizontalSpacing = 0;
      literalLayout.verticalSpacing = 0;
      literalLayout.marginWidth = 0;
      literalLayout.marginHeight = 0;
      literalPanel.setLayout(literalLayout);

      specifyLiteralButton = new Button(literalPanel, SWT.RADIO);
      specifyLiteralButton.setText(Diagram_Messages.TXT_WITH_AN_ARBITRARY_LITERAL);
      specifyLiteralButton.addSelectionListener(new SelectionListener()
      {

         public void widgetDefaultSelected(SelectionEvent e)
         {}

         public void widgetSelected(SelectionEvent e)
         {
            emptyLiteralButton.setSelection(false);
            defaultValueButton.setSelection(false);
            literalText.setEnabled(true);
            defaultValueSelected = false;
            literalSelected = true;
            emptyLiteralSelected = false;

         }

      });

      literalText = new Text(literalPanel, SWT.BORDER);
      literalText.addModifyListener(this);

      emptyLiteralButton.setSelection(true);
      defaultValueButton.setSelection(false);
      specifyLiteralButton.setSelection(false);
      literalText.setEnabled(false);

      parent.getShell().setMinimumSize(600, 300);
      return comp;
   }

   public boolean isDefaultValueSelected()
   {
      return defaultValueSelected;
   }

   public boolean isEmptyLiteralSelected()
   {
      return emptyLiteralSelected;
   }

   public boolean isLiteralSelected()
   {
      return literalSelected;
   }
   
   public String getLiteral()
   {
      return literal;
   }

   public void modifyText(ModifyEvent arg0) {  
      literal = literalText.getText();
  }
}