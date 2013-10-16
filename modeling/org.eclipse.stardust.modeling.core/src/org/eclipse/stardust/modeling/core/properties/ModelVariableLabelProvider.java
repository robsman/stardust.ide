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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;

import org.eclipse.stardust.model.xpdl.carnot.util.ModelVariable;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;

import org.eclipse.swt.graphics.Image;

public class ModelVariableLabelProvider extends LabelProvider implements  ITableLabelProvider {

   public Image getColumnImage(Object element, int columnIndex)
   {
      return null;
   }

   public String getColumnText(Object element, int columnIndex) {
      ModelVariable variable = (ModelVariable) element;
      String name = variable.getName();
      name = name.replace("${", ""); //$NON-NLS-1$ //$NON-NLS-2$
      name = name.replace("}", ""); //$NON-NLS-1$ //$NON-NLS-2$
            
      switch (columnIndex) {
      case 0:
          return VariableContextHelper.getName(name);
      case 1:
          return variable.getDefaultValue();
      case 2:
          return variable.getDescription();
      case 3:
         return VariableContextHelper.getType(name);
      }
      return "n.a."; //$NON-NLS-1$
  }
}