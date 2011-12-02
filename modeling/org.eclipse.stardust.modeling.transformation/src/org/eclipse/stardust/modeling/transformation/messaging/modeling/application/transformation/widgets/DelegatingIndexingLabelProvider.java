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

import java.util.Map;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;


/**
 * LabelProvider for the main element table.
 * 
 * @author Rainer Pielmann
 * @version $Revision: 18031 $
 */
public class DelegatingIndexingLabelProvider extends CellLabelProvider
{

   private IndexingLabelProvider labelProvider;

   public DelegatingIndexingLabelProvider(MessageTransformationController controller, Map<String, String> indexMap, String xPath)
   {
      super();
      this.labelProvider = new IndexingLabelProvider(controller, indexMap, xPath);
   }

   public void update(ViewerCell cell)
   {
      cell.setFont(labelProvider.getFont(cell.getElement(), cell.getColumnIndex()));
      cell.setImage(labelProvider
            .getColumnImage(cell.getElement(), cell.getColumnIndex()));
      cell.setText(labelProvider.getColumnText(cell.getElement(), cell.getColumnIndex()));
   }
}