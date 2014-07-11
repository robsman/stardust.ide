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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;


/**
 * LabelProvider for the main element table.
 * 
 * @author Rainer Pielmann
 * @version $Revision$
 */
public class DelegatingMessageTypeLabelProvider extends CellLabelProvider
{

   private MessageTypeLabelProvider labelProvider;

   public DelegatingMessageTypeLabelProvider(MessageTransformationController controller)
   {
      super();
      this.labelProvider = new MessageTypeLabelProvider(controller);
   }

   public void update(ViewerCell cell)
   {
//      String path = getPath(cell);
      Object element = cell.getElement();
      int columnIndex = cell.getColumnIndex();
      cell.setFont(labelProvider.getFont(element, columnIndex));
      cell.setImage(labelProvider.getColumnImage(element, columnIndex));
      String label = labelProvider.getColumnText(element, columnIndex);
      cell.setText(label);
//      System.err.println(path + "[" + columnIndex + "] : " + label);
   }
   
/*   private String getPath(ViewerCell cell)
   {
      StringBuilder b = new StringBuilder();
      TreePath path = cell.getViewerRow().getTreePath();
      for (int i = 0, l = path.getSegmentCount(); i < l; i++)
      {
         b.append("/");
         Object segment = path.getSegment(i);
         if (segment instanceof IIdentifiableElement)
         {
            b.append(((IIdentifiableElement) segment).getId());
         }
         else
         {
            b.append(segment);
         }
      }
      return b.toString();
   }*/

   public void setShowGroupInfo(boolean showGroupInfo)
   {
      labelProvider.setShowGroupInfo(showGroupInfo);
   }
}