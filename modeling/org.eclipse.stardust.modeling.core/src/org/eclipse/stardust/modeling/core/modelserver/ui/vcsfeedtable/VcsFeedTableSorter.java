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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.modeling.core.modelserver.ModelHistoryEntry;


public class VcsFeedTableSorter extends ViewerSorter {
	private int propertyIndex;
	private static final int DESCENDING = 1;
	private int direction = DESCENDING;

	public VcsFeedTableSorter() {
		this.propertyIndex = 0;
		direction = DESCENDING;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			direction = 1 - direction;
		} else {
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}
	
	public int compare(Viewer viewer, Object e1, Object e2) {
        ModelHistoryEntry p1 = (ModelHistoryEntry) e1;
        ModelHistoryEntry p2 = (ModelHistoryEntry) e2;
		int rc = 0;
		switch (propertyIndex) {
		case 0:
			rc = p1.getRevision().compareTo(p2.getRevision());
			break;
		case 1:
			rc = p1.getTimestamp().compareTo(p2.getTimestamp());
			break;
		case 2:
			rc = p1.getAuthor().compareTo(p2.getAuthor());
			break;
		case 3:
			String event1 = extractEvent(p1.getComment());
			String event2 = extractEvent(p2.getComment());
			rc = event1.compareTo(event2);
			break;			
		case 4:
			String comment1 = extractComment(p1.getComment());
			String comment2 = extractComment(p2.getComment());
			rc = comment1.compareTo(comment2);
			break;
			
		default:
			rc = 0;
		}
		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}
	
	private String extractComment(String comment) {
	  if (comment.indexOf("/") > -1) //$NON-NLS-1$
	  {
		 String[] stringArray = comment.split("/"); //$NON-NLS-1$
		 return stringArray[1];
	  }
	  return ""; //$NON-NLS-1$
    }
		   
	private String extractEvent(String comment) {	
	  if (comment.indexOf("/") > -1) //$NON-NLS-1$
	  {
		 String[] stringArray = comment.split("/"); //$NON-NLS-1$
		 return stringArray[0];
	  }
	  return comment;
    }	

}
