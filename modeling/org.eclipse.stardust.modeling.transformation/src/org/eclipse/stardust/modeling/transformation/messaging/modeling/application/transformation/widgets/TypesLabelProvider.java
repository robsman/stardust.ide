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

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.swt.graphics.Image;

/**
 * LabelProvider for the main element table.
 * 
 * @author herinean
 * @version $Revision$
 */
public class TypesLabelProvider extends LabelProvider
{
   private ModelType model;

   public String getText(Object element)
   {
      if (element instanceof DataType)
      {
         return((DataType) element).getId();
      }
      if (element instanceof TypeDeclarationType)
      {
         ModelType parent = ModelUtils.findContainingModel((TypeDeclarationType) element);
         String name = ((TypeDeclarationType) element).getName();
         return parent == model ? name : parent.getName() + " / " + name;
      }
      return element.getClass().getName();
   }

   public Image getColumnImage(Object element, int columnIndex)
   {
      // icons ?
      return null;
   }

   public void setModel(ModelType model)
   {
      this.model = model;
   }
}