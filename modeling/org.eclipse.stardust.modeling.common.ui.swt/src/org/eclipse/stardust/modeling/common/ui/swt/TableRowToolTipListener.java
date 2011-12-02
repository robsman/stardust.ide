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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableRowToolTipListener implements Listener
{	
	Table table;
	Shell shell;
	Display display;
	Shell tip = null;
	Label label = null;
	Listener labelListener;
	Method getterMethod;

	public TableRowToolTipListener(final Table table, Class type, String propertyName)
	{
		super();

		try
		{
			getterMethod = type.getMethod(MetadataManager.getGetterMethodName(propertyName), null);
		}
		catch (SecurityException e1)
		{
			throw new RuntimeException(e1);
		}
		catch (NoSuchMethodException e1)
		{
			throw new RuntimeException(e1);
		}
		
		this.table = table;
		
		display = table.getDisplay();
		shell = table.getShell();

		labelListener = new Listener()
		{
			public void handleEvent(Event event)
			{
				Label label = (Label) event.widget;
				Shell shell = label.getShell();
				
				switch (event.type)
				{
					case SWT.MouseDown:
						Event e = new Event();
						e.item = (TableItem) label.getData("_TABLEITEM");
						// Assuming table is single select, set the selection as if
						// the mouse down event went through to the table
						table.setSelection(new TableItem[]
						{ (TableItem) e.item });
						table.notifyListeners(SWT.Selection, e);
						shell.dispose();
						table.setFocus();
						break;
					case SWT.MouseExit:
						shell.dispose();
						break;
				}
			}
		};
		
		table.addListener (SWT.Dispose, this);
		table.addListener (SWT.KeyDown, this);
		table.addListener (SWT.MouseMove, this);
		table.addListener (SWT.MouseHover, this);
	}

	public void handleEvent(Event event)
	{
		switch (event.type)
		{
			case SWT.Dispose:
			case SWT.KeyDown:
			case SWT.MouseMove:
			{
				if (tip == null)
					break;

				tip.dispose();
				
				tip = null;
				label = null;
				
				break;
			}
			case SWT.MouseHover:
			{
				TableItem item = table.getItem(new Point(event.x, event.y));
				
				if (item != null)
				{
					if (tip != null && !tip.isDisposed())
						tip.dispose();
					
					Object object = item.getData();
					String text = null;
					
					if (object != null)
					{
						try
						{
							Object value = getterMethod.invoke(object, null);
							
							if (value == null)
							{
								return;
							}
							
							text = value.toString();
						}
						catch (IllegalArgumentException e)
						{
							throw new RuntimeException(e);
						}
						catch (IllegalAccessException e)
						{
							throw new RuntimeException(e);
						}
						catch (InvocationTargetException e)
						{
							throw new RuntimeException(e);
						}
					}
					
					tip = new Shell(shell, SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
					tip.setBackground(display
								.getSystemColor(SWT.COLOR_INFO_BACKGROUND));

					FillLayout layout = new FillLayout();
					
					layout.marginWidth = 2;
					tip.setLayout(layout);
					label = new Label(tip, SWT.NONE);
					label.setForeground(display
								.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
					label.setBackground(display
								.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
					label.setData("_TABLEITEM", item);					
					label.setText(text);					
					label.addListener(SWT.MouseExit, labelListener);
					label.addListener(SWT.MouseDown, labelListener);
					
					Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
					Rectangle rect = item.getBounds(0);
					Point pt = table.toDisplay(rect.x, rect.y);
					
					tip.setBounds(pt.x, pt.y, size.x, size.y);
					tip.setVisible(true);
				}
			}
		}
	}
}
