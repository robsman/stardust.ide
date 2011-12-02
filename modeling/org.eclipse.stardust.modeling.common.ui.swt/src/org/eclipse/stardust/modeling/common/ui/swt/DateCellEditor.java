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
package org.eclipse.stardust.modeling.common.ui.swt;

import java.text.DateFormat;
import java.text.ParseException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;

import com.gface.date.DatePickerCombo;

public class DateCellEditor extends AbstractCellEditor
{
	private DateFormat dateFormat;

	public DateCellEditor(ObjectTable objectTable, int columnIndex)
	{
		super(objectTable, columnIndex);

		dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	}

	protected Control createEditorControl()
	{
		return new DatePickerCombo(objectTable.getTable(),
					SWT.BORDER);
	}

	protected void activateEditor(Control editorControl, Listener textListener, TableItem item, int column, String text)
	{
		editorControl.addListener(SWT.FocusOut, textListener);
		editorControl.addListener(SWT.Traverse, textListener);
		editor.setEditor(editorControl, item, column);

		try
		{
			((DatePickerCombo)editorControl).setDate(dateFormat.parse(text));
		}
		catch (ParseException e)
		{
			throw new RuntimeException(e);
		}
	}

	protected String getTextFromEditor(Control editorControl)
	{
		return ((DatePickerCombo)editorControl).getText();
	}

	protected Object getValueFromEditor(Control editorControl)
	{
		return ((DatePickerCombo)editorControl).getDate();
	}
}
