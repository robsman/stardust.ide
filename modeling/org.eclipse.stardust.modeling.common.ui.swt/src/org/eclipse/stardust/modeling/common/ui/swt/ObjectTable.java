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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * 
 * @author mgille
 * 
 */
public class ObjectTable extends Composite
{
	public final static int NULL = 0;
	public final static int ADD_ROW = 1;
	public final static int DELETE_BUTTON = 1 << 2;
	public final static int NUMBER_COLUMN = 1 << 3;
	public final static int SHOW_HEADERS = 1 << 4;
	public final static int EDITABLE = 1 << 5;
	public final static int MULTI_LINE_EDITOR = 1 << 6;

	private int style;
	private int tableStyle;
	private Table table;
	private List objects;
	private Class type;
	private Image[] defaultIcons;
	private Button deleteButton;
	private MetadataManager metadataManager;
	private List changeListeners;

	public ObjectTable(Composite parent, int style, int tableStyle, Class type,
				String[] columnProperties, String[] columnNames, int[] alignments,
				int[] widths, Image[] defaultIcons)
	{
		super(parent, SWT.NULL);

		this.style = style;
		this.tableStyle = tableStyle;
		objects = new ArrayList();
		changeListeners = new ArrayList();

		if ((style & NUMBER_COLUMN) == NUMBER_COLUMN && defaultIcons != null)
		{
			this.defaultIcons = new Image[defaultIcons.length + 1];
			this.defaultIcons[0] = null;

			for (int n = 0; n < defaultIcons.length; ++n)
			{
				this.defaultIcons[n + 1] = defaultIcons[n];
			}
		}
		else
		{
			this.defaultIcons = defaultIcons;
		}

		metadataManager = new MetadataManager(type, columnProperties);

		this.type = type;

		createContent(columnNames, alignments, widths);

		if ((style & ADD_ROW) == ADD_ROW)
		{
			createAddEntryRow();
		}
	}

	public void addChangeListeners(IObjectTableChangeListener changeListener)
	{
		changeListeners.add(changeListener);
	}
	
	public void removeChangeListeners(IObjectTableChangeListener changeListener)
	{
		changeListeners.remove(changeListener);
	}

	/**
	 * 
	 * @param columnNames
	 * @param alignments
	 * @param widths
	 */
	private void createContent(String[] columnNames, int[] alignments,
				int[] widths)
	{
		FormLayout layout = new FormLayout();

		setLayout(layout);

		table = new Table(this, tableStyle);

		FormData data = new FormData();

		data.left = new FormAttachment(0, 0);

		if ((style & DELETE_BUTTON) == DELETE_BUTTON)
		{
			data.right = new FormAttachment(95, 0);
		}
		else
		{
			data.right = new FormAttachment(100, 0);
		}

		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);

		table.setLayoutData(data);
		table.setHeaderVisible((style & SHOW_HEADERS) == SHOW_HEADERS);

		if ((style & MULTI_LINE_EDITOR) == MULTI_LINE_EDITOR)
		{
			new MultiLineTableCellListener(table);
		}

		// Add number column

		if ((style & NUMBER_COLUMN) == NUMBER_COLUMN)
		{
			TableColumn number = new TableColumn(table, SWT.RIGHT);

			number.setText("");
			number.setWidth(25);
		}

		for (int n = 0; n < columnNames.length; ++n)
		{
			TableColumn column = new TableColumn(table, alignments[n]);

			column.setText(columnNames[n]);
			column.setWidth(widths[n]);

			if ((style & EDITABLE) == EDITABLE)
			{
				addEditor(n);
			}
		}

		if ((style & DELETE_BUTTON) == DELETE_BUTTON)
		{
			Composite buttonComposite = new Composite(this, 0);

			data = new FormData();
			data.left = new FormAttachment(table, 10, SWT.RIGHT);
			data.right = new FormAttachment(100, -5);
			data.top = new FormAttachment(table, 0, SWT.TOP);

			buttonComposite.setLayoutData(data);

			FillLayout buttonLayout = new FillLayout(SWT.VERTICAL);

			buttonLayout.spacing = 5;
			buttonComposite.setLayout(buttonLayout);

			deleteButton = new Button(buttonComposite, 0);

			deleteButton.setText("X");
			deleteButton.addListener(SWT.Selection, new Listener()
			{
				public void handleEvent(Event event)
				{
					removeSelected();
				}
			});
		}
	}

	public Table getTable()
	{
		return table;
	}

	public Class getType()
	{
		return type;
	}

	public List getObjects()
	{
		return objects;
	}

	public String[] getPropertyNames()
	{
		return metadataManager.getPropertyNames();
	}

	/**
	 * 
	 * @param list
	 */
	public void setObjects(List objects)
	{
		this.objects = objects;

		table.removeAll();

		for (int n = 0; n < objects.size(); ++n)
		{
			populateTableItem(new TableItem(table, SWT.NULL), objects.get(n));
		}

		if ((style & ADD_ROW) == ADD_ROW)
		{
			createAddEntryRow();
		}
	}

	/**
	 * Creates the stringified representation of all object values in that table
	 * item.
	 * 
	 * @param item
	 * @param object
	 */
	public void populateTableItem(TableItem item, Object object)
	{
		item.setData(object);
		item.setImage(defaultIcons);

		String[] values = null;
		int start = 0;

		if ((style & NUMBER_COLUMN) == NUMBER_COLUMN)
		{
			values = new String[metadataManager.getPropertyNames().length + 1];
			values[0] = "" + table.getItemCount();

			++start;
		}
		else
		{
			values = new String[metadataManager.getPropertyNames().length];
		}

		for (int m = 0; m < metadataManager.getPropertyNames().length; ++m)
		{			
			String value = metadataManager.getStringifiedValue(object, m);

			if (value != null)
			{
					values[start] = value;
			}
			else
			{
				values[start] = "";
			}

			++start;
		}

		item.setText(values);
	}

	/**
	 * 
	 * 
	 */
	public void createAddEntryRow()
	{
		TableItem item = new TableItem(table, SWT.NULL);

		String[] values = new String[table.getColumnCount()];

		for (int n = 0; n < table.getColumnCount(); ++n)
		{
			if (n == 0)
			{
				values[n] = "" + table.getItemCount();
			}
			if (n == 1)
			{
				values[n] = "<New Entry>";
			}
			else
			{
				values[n] = "";
			}
		}

		item.setText(values);
	}

	public void setTooltipProperty(String name)
	{
		new TableRowToolTipListener(table, type, name);
	}

	/**
	 * 
	 * 
	 */
	public Object createObject()
	{
		return metadataManager.createObject();
	}

	public void addObject(Object object)
	{
		getObjects().add(object);
		setObjects(objects);
	}
	
	/**
	 * Called from editors only.
	 * 
	 * @param object
	 */
	protected void addObjectViaEditing(Object object)
	{
		objects.add(object);

		// Convert the auxiliary row into a row bound to an object

		TableItem item = table.getItem(table.getItemCount() - 1);

		populateTableItem(item, object);

		createAddEntryRow();
		
		for (Iterator iter = changeListeners.iterator(); iter.hasNext();)
		{
			IObjectTableChangeListener changeListener = (IObjectTableChangeListener) iter.next();
			
			changeListener.objectAdded(object);
		}
	}

	/**
	 * 
	 * @param object
	 * @param columnIndex
	 * @param value
	 */
	public void setValueFromString(Object object, int columnIndex, String value)
	{
		if ((style & NUMBER_COLUMN) == NUMBER_COLUMN)
		{
			--columnIndex;
		}

		metadataManager.setPropertyFromString(object, columnIndex, value);
		
		for (Iterator iter = changeListeners.iterator(); iter.hasNext();)
		{
			IObjectTableChangeListener changeListener = (IObjectTableChangeListener) iter.next();
			
			changeListener.objectChanged(object);
		}
	}

	/**
	 * 
	 * @param object
	 * @param columnIndex
	 * @return
	 */
	public String getStringifiedValue(Object object, int columnIndex)
	{
		if ((style & NUMBER_COLUMN) == NUMBER_COLUMN)
		{
			--columnIndex;
		}

		return metadataManager.getStringifiedValue(object, columnIndex);
	}

	private void addEditor(int index)
	{
		int offset = 0;

		if ((style & NUMBER_COLUMN) == NUMBER_COLUMN)
		{
			offset = 1;
		}

		if (metadataManager.getType(index) == Date.class)
		{
			new DateCellEditor(this, index + offset);
		}
		else
		{
			new TextCellEditor(this, index + offset);
		}
	}
	
	public void removeSelected()
	{
		// Remove all selected objects
		
		boolean addEntryRowSelected = false;
		
		for (int n = 0; n < table.getSelectionCount(); ++n)
		{
			Object object = table.getSelection()[n].getData(); 

			if (object != null)
			{
				objects.remove(object);
				
				for (Iterator iter = changeListeners.iterator(); iter.hasNext();)
				{
					IObjectTableChangeListener changeListener = (IObjectTableChangeListener) iter.next();
					
					changeListener.objectRemoved(object);
				}
			}
			else
			{
				addEntryRowSelected = true;
			}
		}

		table.remove(table.getSelectionIndices());
		
		if (addEntryRowSelected)
		{
			createAddEntryRow();
		}
	}
}
