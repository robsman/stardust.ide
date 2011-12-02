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
package org.eclipse.stardust.modeling.repository.common.ui;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.swt.graphics.Image;


public class ObjectDescriptorLabelProvider extends LabelProvider
{
   public Image getImage(Object element)
   {
      if (element instanceof IObjectDescriptor)
      {
         return ((IObjectDescriptor) element).getIcon();
      }
      return super.getImage(element);
   }

   public String getText(Object element)
   {
      if (element instanceof IObjectDescriptor)
      {
         return ((IObjectDescriptor) element).getLabel();
      }
      if (element instanceof EClass)
      {
         return ((EClass) element).getName();
      }
      return super.getText(element);
   }
}
