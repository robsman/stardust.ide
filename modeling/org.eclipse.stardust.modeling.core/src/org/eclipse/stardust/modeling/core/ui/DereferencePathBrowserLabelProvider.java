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
package org.eclipse.stardust.modeling.core.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.swt.graphics.Image;


public class DereferencePathBrowserLabelProvider implements ILabelProvider
{

   public Image getImage(Object element)
   {
      return null;
   }

   public String getText(Object element)
   {
      if (element instanceof MethodInfo)
      {

         return ((MethodInfo) element).getLabel();

      }
      return ""; //$NON-NLS-1$
   }

   public void addListener(ILabelProviderListener listener)
   {}

   public void dispose()
   {}

   public boolean isLabelProperty(Object element, String property)
   {
      return false;
   }

   public void removeListener(ILabelProviderListener listener)
   {}

}
