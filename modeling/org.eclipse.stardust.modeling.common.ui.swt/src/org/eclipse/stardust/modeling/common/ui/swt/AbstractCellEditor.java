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
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;

/**
 * Editor for a table cell of an ObjectTable. 
 * 
 * @author mgille
 *
 */
public abstract class AbstractCellEditor implements Listener
{
	protected TableEditor editor;
	protected ObjectTable objectTable;
	protected int tableColumn;

	public AbstractCellEditor(ObjectTable objectTable, int columnIndex)
	{
		super();

		this.objectTable = objectTable;
		this.tableColumn = columnIndex;

		editor = new TableEditor(objectTable.getTable());

		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;

		objectTable.getTable().addListener(SWT.MouseDown, this);
	}
	
	/**
	 * 
	 */
	public void handleEvent(Event event)
	{
		final Control editorControl = createEditorControl();
		Rectangle clientArea = objectTable.getTable().getClientArea();
		Point pt = new Point(event.x, event.y);
		int index = objectTable.getTable().getTopIndex();

		while (index < objectTable.getTable().getItemCount())
		{
			boolean visible = false;
			final TableItem item = objectTable.getTable().getItem(index);

			for (int i = 0; i < objectTable.getTable().getColumnCount(); i++)
			{
				if (i != tableColumn)
				{
					continue;
				}

				Rectangle rect = item.getBounds(i);

				if (rect.contains(pt))
				{
					final int column = i;
					final int row = index;

					Listener textListener = new Listener()
					{
						public void handleEvent(final Event e)
						{
							switch (e.type)
							{
								case SWT.FocusOut:
								{
									disposeEditorControl(item, editorControl, column);

									break;
								}
								case SWT.Traverse:
								{
									switch (e.detail)
									{
										case SWT.TRAVERSE_ARROW_PREVIOUS:
										case SWT.TRAVERSE_TAB_PREVIOUS:
										{
											e.doit = false;
										
											// Set new focus
											
											int newRow = row;
											int newColumn = column;
											
											if (column - 1 == 0)
											{
												--newRow;
												
												if (newRow == -1)
												{
													break;
												}
												
												newColumn = objectTable.getTable().getColumnCount() - 1;
												
											}
											else
											{
												--newColumn;
											}
											
											TableItem newItem = objectTable.getTable().getItem(newRow);
											
											Event event = new Event();
											
											event.x = newItem.getBounds(newColumn).x + 1;
											event.y = newItem.getBounds(newColumn).y + 1;
											
											objectTable.getTable().notifyListeners(SWT.MouseDown, event);

											disposeEditorControl(item, editorControl, column);

											e.doit = false;

											break;
										}
										case SWT.TRAVERSE_ARROW_NEXT:
										case SWT.TRAVERSE_TAB_NEXT:
										{
											// Set new focus
											
											int newRow = row;
											int newColumn = column;
											
											if (column + 1 == objectTable.getTable().getColumnCount())
											{
												++newRow;
												
												if (newRow == objectTable.getTable().getItemCount())
												{
													break;
												}
												
												// TODO case without number columns
												
												newColumn = 1;												
											}
											else
											{
												++newColumn;
											}
											
											TableItem newItem = objectTable.getTable().getItem(newRow);
											
											Event event = new Event();
											
											event.x = newItem.getBounds(newColumn).x + 1;
											event.y = newItem.getBounds(newColumn).y + 1;
											
											objectTable.getTable().notifyListeners(SWT.MouseDown, event);
											
											disposeEditorControl(item, editorControl, column);

											e.doit = false;

											break;
										}
										case SWT.TRAVERSE_RETURN:
										{
											disposeEditorControl(item, editorControl, column);

											e.doit = false;

											break;

										}
										case SWT.TRAVERSE_ESCAPE:
										{
											editorControl.dispose();
									
											e.doit = false;

											break;
										}
									}

									break;
								}
							}
						}
					};

					activateEditor(editorControl, textListener, item, i, item.getText(i));
					editorControl.setFocus();
				}

				if (!visible && rect.intersects(clientArea))
				{
					visible = true;
				}
			}

			if (!visible)
			{
				return;
			}

			index++;
		}
	}

	private void disposeEditorControl(TableItem item, Control editorControl, int column)
	{
		if (item.getData() == null)
		{
			objectTable.addObjectViaEditing(objectTable.createObject());
		}		

		// Set value of the object
		
		objectTable.setValueFromString(item.getData(), column, getTextFromEditor(editorControl));
		
		// Set value of the table cell underneath the editor
		
		item.setText(column, getTextFromEditor(editorControl));
		
		// Dispose editor control
		
		editorControl.dispose();
	}
	
	protected abstract Control createEditorControl();
	protected abstract void activateEditor(Control editorControl, Listener textListener, TableItem item, int column, String text);
	protected abstract Object getValueFromEditor(Control editorControl);
	protected abstract String getTextFromEditor(Control editorControl);	
}
