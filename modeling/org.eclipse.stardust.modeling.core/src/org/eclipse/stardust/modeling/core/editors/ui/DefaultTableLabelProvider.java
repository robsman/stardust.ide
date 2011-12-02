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
package org.eclipse.stardust.modeling.core.editors.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author fherinean
 * @version $Revision$
 */
public class DefaultTableLabelProvider implements TableLabelProvider
{
   private static TableLabelProvider instance = new DefaultTableLabelProvider();

   private LabelProvider delegate;

   private DefaultTableLabelProvider() {};

   public DefaultTableLabelProvider(LabelProvider delegate)
   {
      this.delegate = delegate;
   }

   public String getText(String name, Object element)
   {
      return ""; //$NON-NLS-1$
   }

   public String getText(int index, Object element)
   {
      return element == null ? "" : index == 0 ? delegate == null ? element.toString() : delegate.getText(element) : ""; //$NON-NLS-1$ //$NON-NLS-2$
   }

   public boolean isNamed()
   {
      return false;
   }

   public boolean accept(Object element)
   {
      return true;
   }

   public Image getImage(Object element)
   {
      return delegate == null ? null : delegate.getImage(element);
   }

   public static TableLabelProvider instance()
   {
      return instance;
   }
}
