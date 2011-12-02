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
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.swt.graphics.Image;


public class ProcessDefinitionImplemetationLabelProvider extends LabelProvider implements  ITableLabelProvider {

   public Image getColumnImage(Object element, int columnIndex)
   {
      return null;
   }

   public String getColumnText(Object element, int columnIndex) {
      ProcessDefinitionType process = (ProcessDefinitionType) element;
      switch (columnIndex) {
      case 0:
          ModelType model = (ModelType) process.eContainer();            
          return model.getName();
      case 1:
          return process.getName();
      }
      return "n.a."; //$NON-NLS-1$
  }

}
