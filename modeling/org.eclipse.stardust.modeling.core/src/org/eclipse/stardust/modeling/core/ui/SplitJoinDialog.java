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
package org.eclipse.stardust.modeling.core.ui;

import java.text.MessageFormat;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


/**
 * @author rsauer
 * @version $Revision: 31000 $
 */
public class SplitJoinDialog extends Dialog
{
	private static boolean isAnd = false;
	
	public static boolean isAnd() 
	{
		return isAnd;
	}

	private Button AND;
	private Button XOR;   
   
   private Button saveButton;
   private FlowControlType flow;   
   
   protected void okPressed() 
   {
	   SplitJoinDialog.isAnd = AND.getSelection();
	   
	   if(saveButton.getSelection())
	   {
		   if(flow.equals(FlowControlType.SPLIT_LITERAL))
		   {			   
			   PlatformUI.getPreferenceStore().setValue(
			              BpmProjectNature.PREFERENCE_SPLIT_PROMPT, false);		   
			   PlatformUI.getPreferenceStore().setValue(
			              BpmProjectNature.PREFERENCE_SPLIT_AND, AND.getSelection());		   
			   PlatformUI.getPreferenceStore().setValue(
			              BpmProjectNature.PREFERENCE_SPLIT_XOR, XOR.getSelection());
		   }
		   else
		   {
			   PlatformUI.getPreferenceStore().setValue(
			              BpmProjectNature.PREFERENCE_JOIN_PROMPT, false);		   
			   PlatformUI.getPreferenceStore().setValue(
			              BpmProjectNature.PREFERENCE_JOIN_AND, AND.getSelection());
			   PlatformUI.getPreferenceStore().setValue(
			              BpmProjectNature.PREFERENCE_JOIN_XOR, XOR.getSelection());
		   }
	   }
	   super.okPressed();
   }

   public SplitJoinDialog(Shell parentShell, FlowControlType flow) 
   {	   
	   super(parentShell);
	   this.flow = flow;
   }   

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      String kind = null;
      if(flow.equals(FlowControlType.SPLIT_LITERAL))
      {			   
    	  kind = "Split"; //$NON-NLS-1$
      }
      else
      {
    	  kind = "Join"; //$NON-NLS-1$
      }      
      
      String title = MessageFormat.format(Diagram_Messages.SplitJoinDialog_Title,
              new Object[] {kind});
      shell.setText(title);
   }   
   
   protected Control createDialogArea(Composite parent)
   {
      Composite panel = (Composite) super.createDialogArea(parent);

      ((GridLayout) panel.getLayout()).numColumns = 3;

      XOR = FormBuilder.createRadioButton(panel,
              Diagram_Messages.LB_XOR);
      XOR.setSelection(true);      
      AND = FormBuilder.createRadioButton(panel,
              Diagram_Messages.LB_AND);
      AND.setSelection(false);
      
      saveButton = FormBuilder.createCheckBox(panel, Diagram_Messages.SplitJoinDialog_Save);
      saveButton.setSelection(false);
      
      return panel;
   }      
}