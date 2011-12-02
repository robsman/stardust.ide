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
package org.eclipse.stardust.modeling.core.modelserver.ui.vcsfeedtable;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.modelserver.ModelHistoryEntry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


public class VcsFeedTable
{
   private TableViewer tableViewer;
   private VcsFeedTableSorter tableSorter;

   public VcsFeedTable(Composite parent)
   {
      super();
      tableViewer = new TableViewer(parent, SWT.FULL_SELECTION);
      Table table = tableViewer.getTable();
      table.setLinesVisible(true);
      table.setHeaderVisible(true);
      createColumns(tableViewer);      
      TableUtil.setInitialColumnSizes(table, new int[] { 10, 20, 20, 20, 20 });
      tableSorter = new VcsFeedTableSorter();
	  tableViewer.setSorter(tableSorter);      
      tableViewer.setLabelProvider(new VcsFeedTableLabelProvider());
      tableViewer.setContentProvider(new VcsFeedTableContentProvider());
      List<ModelHistoryEntry> emptyList = Collections.emptyList();
      tableViewer.setInput(new VcsFeedTableModel(emptyList));      
   }

   public Table getTable()
   {
      return tableViewer.getTable();
   }

   public void updateData(List<ModelHistoryEntry> modelHistoryEntries)
   {
      VcsFeedTableModel vcsFeedTableModel = ((VcsFeedTableModel) this.tableViewer.getInput());
      vcsFeedTableModel.setModelHistoryEntries(modelHistoryEntries);
      for (Iterator<ModelHistoryEntry> i = modelHistoryEntries.iterator(); i.hasNext();)
      {
         this.tableViewer.update(i.next(), null);
      }
   }

   public void replaceData(List<ModelHistoryEntry> modelHistoryEntries)
   {
      this.tableViewer.setInput(new VcsFeedTableModel(modelHistoryEntries));
      this.tableViewer.refresh();
   }
   
	private void createColumns(final TableViewer viewer) {
		Table table = viewer.getTable();

		String[] titles = { Diagram_Messages.LB_VCS_FEED_VIEW_REVISION, 
        					Diagram_Messages.LB_VCS_FEED_VIEW_DATE, 
        			        Diagram_Messages.LB_VCS_FEED_VIEW_AUTHOR,
        			        Diagram_Messages.LB_VCS_FEED_VIEW_EVENT,
        			        Diagram_Messages.LB_VCS_FEED_VIEW_COMMENT};
		for (int i = 0; i < titles.length; i++) {
			final int index = i;
			final TableViewerColumn viewerColumn = new TableViewerColumn(
					viewer, SWT.LEFT);
			final TableColumn column = viewerColumn.getColumn();
			column.setText(titles[i]);			
			column.setMoveable(true);
			column.setResizable(true);
			column.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					tableSorter.setColumn(index);
					int dir = viewer.getTable().getSortDirection();
					if (viewer.getTable().getSortColumn() == column) {
						dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
					} else {

						dir = SWT.DOWN;
					}
					viewer.getTable().setSortDirection(dir);
					viewer.getTable().setSortColumn(column);
					viewer.refresh();
				}
			});			
		}
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

}