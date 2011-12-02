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
package org.eclipse.stardust.modeling.core.editors.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;

public abstract class CarnotBooleanEditor extends CarnotCellEditor
{
   public CarnotBooleanEditor(int column)
   {
      super(column);
   }

   public Control createControl(final Composite parent, final Object item)
   {
      final Button button = new Button(parent, SWT.CHECK);
      Boolean value = (Boolean) getValue(item);
      button.setSelection(value.booleanValue());
      button.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            Boolean value = button.getSelection() ? Boolean.TRUE : Boolean.FALSE;
            setValue(item, value);
         }
      });
      return button;
   }

   public void refresh(TreeEditor editor)
   {
      Button button = (Button) editor.getEditor();
      Item item = editor.getItem();
      Boolean value = (Boolean) getValue(item.getData());
      button.setSelection(value.booleanValue());
   }
}
