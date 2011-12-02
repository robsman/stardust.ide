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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.launch;

import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class TestOutputDialog extends Dialog
{
	Double value;

	/**
	 * @param parent
	 */
	public TestOutputDialog(Shell parent)
	{
		super(parent);
	}

	/**
	 * @param parent
	 * @param style
	 */
	public TestOutputDialog(Shell parent, int style)
	{
		super(parent, style);
	}

	/**
	 * Makes the dialog visible.
	 * 
	 * @return
	 */
	public Double open()
	{
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER
				| SWT.APPLICATION_MODAL);
		
		shell.setText(Modeling_Messages.TXT_TEST_OP_DIALOG);
		shell.setLayout(new GridLayout(2, true));

		Label label = new Label(shell, SWT.NULL);
		label.setText(Modeling_Messages.TXT_PLEASE_ENTER_VALID_NUMBER);

		final Text text = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);

		final Button buttonOK = new Button(shell, SWT.PUSH);
		
		buttonOK.setText(Modeling_Messages.TXT_OK);
		buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		Button buttonCancel = new Button(shell, SWT.PUSH);
		buttonCancel.setText(Modeling_Messages.TXT_CANCEL);

		text.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				try
				{
					value = new Double(text.getText());
					buttonOK.setEnabled(true);
				}
				catch (Exception e)
				{
					buttonOK.setEnabled(false);
				}
			}
		});

		buttonOK.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event event)
			{
				shell.dispose();
			}
		});

		buttonCancel.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event event)
			{
				value = null;
				shell.dispose();
			}
		});

		shell.addListener(SWT.Traverse, new Listener()
		{
			public void handleEvent(Event event)
			{
				if (event.detail == SWT.TRAVERSE_ESCAPE)
					event.doit = false;
			}
		});

		text.setText(""); //$NON-NLS-1$
		
		shell.pack();
		shell.open();

		Display display = parent.getDisplay();
		
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}

		return value;
	}
	
	public void setText(String output)
	{
		
	}

	public static void main(String[] args)
	{
		Shell shell = new Shell();
		TestOutputDialog dialog = new TestOutputDialog(shell);
		System.out.println(dialog.open());
	}
}
