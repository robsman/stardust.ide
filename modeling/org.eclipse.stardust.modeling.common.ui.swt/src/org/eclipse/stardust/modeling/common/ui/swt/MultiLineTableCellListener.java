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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class MultiLineTableCellListener implements Listener
{
	Table table;
	
	public MultiLineTableCellListener(Table table)
	{
		super();
	
		this.table = table;
		
		table.addListener(SWT.MeasureItem, this);
		table.addListener(SWT.PaintItem, this);
		table.addListener(SWT.EraseItem, this);
	}

	/**
	 * 
	 */
	public void handleEvent(Event event)
	{
		switch (event.type)
		{
			case SWT.MeasureItem:
			{
				TableItem item = (TableItem) event.item;
				String text = getText(item, event.index);
				Point size = event.gc.textExtent(text);
	
				event.width = size.x;
				event.height = Math.max(event.height, size.y);
				
				break;
			}
			case SWT.PaintItem:
			{
				TableItem item = (TableItem) event.item;
				String text = getText(item, event.index);
				Point size = event.gc.textExtent(text);
				int offset2 = event.index == 0 ? Math.max(0,
							(event.height - size.y) / 2) : 0;
				
				event.gc.drawText(text, event.x, event.y + offset2, true);
				
				break;
			}
			case SWT.EraseItem:
			{
				event.detail &= ~SWT.FOREGROUND;
				break;
			}
		}
	}

	/**
	 * 
	 * @param item
	 * @param column
	 * @return
	 */
	private String getText(TableItem item, int column)
	{
		String text = item.getText(column);
		
		return text;
	}
}
