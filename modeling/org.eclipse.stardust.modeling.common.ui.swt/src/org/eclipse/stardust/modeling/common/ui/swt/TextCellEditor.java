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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author mgille
 *
 */
public class TextCellEditor extends AbstractCellEditor
{
	public TextCellEditor(ObjectTable objectTable, int columnIndex)
	{
		super(objectTable, columnIndex);
	}
	
	protected Control createEditorControl()
	{
		return new Text(objectTable.getTable(), SWT.MULTI);
	}

	protected void activateEditor(Control editorControl, Listener textListener, TableItem item, int column, String text)
	{
		editorControl.addListener(SWT.FocusOut, textListener);
		editorControl.addListener(SWT.Traverse, textListener);
		editor.setEditor(editorControl, item, column);
		((Text)editorControl).setText(text);
		((Text)editorControl).selectAll();
	}

	protected String getTextFromEditor(Control editorControl)
	{
	   if(editorControl.isDisposed())
	   {
	      return ""; //$NON-NLS-1$
	   }
	   return ((Text)editorControl).getText();
	}

	protected Object getValueFromEditor(Control editorControl)
	{
       if(editorControl.isDisposed())
       {
          return null;
       }	   
       return ((Text)editorControl).getText();
	}
}