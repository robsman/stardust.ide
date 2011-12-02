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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Checkbox cell editor, heavily inspired by
 * {@link org.eclipse.jface.viewers.TextCellEditor}.
 * 
 * @author rsauer
 * @version $Revision$
 */
public class BooleanCellEditor extends CellEditor
{
   private Button checkBox;

   private SelectionListener selectionListener;

   public BooleanCellEditor(Composite parent)
   {
      super(parent);
   }

   protected Control createControl(Composite parent)
   {
      this.checkBox = new Button(parent, SWT.CHECK);

      checkBox.addKeyListener(new KeyAdapter()
      {
         // hook key pressed - see PR 14201
         public void keyPressed(KeyEvent e)
         {
            keyReleaseOccured(e);
         }
      });
      checkBox.addTraverseListener(new TraverseListener()
      {
         public void keyTraversed(TraverseEvent e)
         {
            if (e.detail == SWT.TRAVERSE_ESCAPE || e.detail == SWT.TRAVERSE_RETURN)
            {
               e.doit = false;
            }
         }
      });
      checkBox.addFocusListener(new FocusAdapter()
      {
         public void focusLost(FocusEvent e)
         {
            BooleanCellEditor.this.focusLost();
         }
      });
      checkBox.setFont(parent.getFont());
      checkBox.setBackground(parent.getBackground());
      checkBox.addSelectionListener(getSelectionListener());
      return checkBox;
   }

   protected Object doGetValue()
   {
      return Boolean.valueOf(checkBox.getSelection());
   }

   protected void doSetFocus()
   {
      checkBox.setFocus();
   }

   protected void doSetValue(Object value)
   {
      checkBox.setSelection(Boolean.TRUE.equals(value));
   }

   private SelectionListener getSelectionListener()
   {
      if (null == selectionListener)
      {
         selectionListener = new SelectionListener()
         {
            public void widgetSelected(SelectionEvent e)
            {
               valueChanged(true, true);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
               valueChanged(true, true);
            }
         };
      }
      return selectionListener;
   }
}