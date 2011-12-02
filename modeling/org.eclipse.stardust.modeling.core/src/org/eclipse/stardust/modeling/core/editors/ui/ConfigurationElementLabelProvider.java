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

import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.swt.graphics.Image;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ConfigurationElementLabelProvider implements TableLabelProvider
{
   private static TableLabelProvider instance = new ConfigurationElementLabelProvider();

   public String getText(int index, Object element)
   {
      ConfigurationElement config = (ConfigurationElement) element;
      String name = null;
      String[] names = config.getAttributeNames();
      if (index < names.length)
      {
         name = names[index];
      }
      return getText(name, element);
   }

   public String getText(String name, Object element)
   {
      ConfigurationElement config = (ConfigurationElement) element;
      Object value = name == null ? null : config.getAttribute(name);
      return value == null ? "" : value.toString(); //$NON-NLS-1$
   }

   public boolean isNamed()
   {
      return true;
   }

   public boolean accept(Object element)
   {
      return element instanceof ConfigurationElement;
   }

   public Image getImage(Object element)
   {
      ConfigurationElement config = (ConfigurationElement) element;
      String icon = config.getAttribute(SpiConstants.ICON);
      return icon == null ? null : DiagramPlugin.getImage(icon);
   }

   public static TableLabelProvider instance()
   {
      return instance;
   }
}
