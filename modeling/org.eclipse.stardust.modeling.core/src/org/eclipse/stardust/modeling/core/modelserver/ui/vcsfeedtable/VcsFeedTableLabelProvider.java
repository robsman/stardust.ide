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

import java.text.DateFormat;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.stardust.modeling.core.modelserver.ModelHistoryEntry;
import org.eclipse.swt.graphics.Image;


public class VcsFeedTableLabelProvider implements ITableLabelProvider
{
   public Image getColumnImage(Object element, int columnIndex)
   {
      return null;
   }

   public String getColumnText(Object element, int columnIndex)
   {
      ModelHistoryEntry modelHistoryEntry = (ModelHistoryEntry) element;

      switch (columnIndex)
      {
      case 0:
         return modelHistoryEntry.getRevision();
      case 1:
         DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
         return df.format(modelHistoryEntry.getTimestamp());
      case 2:
         return modelHistoryEntry.getAuthor();
      case 3:
    	 return extractEvent(modelHistoryEntry.getComment());      	 
      case 4:    	  
    	 return extractComment(modelHistoryEntry.getComment());       
      default:
         return null;
      }
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
   

public boolean isLabelProperty(Object element, String property)
   {
      return true;
   }

   public void addListener(ILabelProviderListener listener)
   {
   }

   public void dispose()
   {
   }

   public void removeListener(ILabelProviderListener listener)
   {
   }

}
