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
      cell.setFont(labelProvider.getFont(cell.getElement(), cell.getColumnIndex()));
      cell.setImage(labelProvider
            .getColumnImage(cell.getElement(), cell.getColumnIndex()));
      cell.setText(labelProvider.getColumnText(cell.getElement(), cell.getColumnIndex()));
   }
   
   public void setShowGroupInfo(boolean showGroupInfo)
   {
      labelProvider.setShowGroupInfo(showGroupInfo);
   }
}