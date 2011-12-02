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

import java.util.ArrayList;

import org.eclipse.swt.graphics.Image;

public class CompositeTableLabelProvider implements TableLabelProvider
{
   private ArrayList providers = new ArrayList();
   private boolean named;

   public CompositeTableLabelProvider(boolean named)
   {
      this.named = named;
   }

   public void addLabelProvider(TableLabelProvider labelProvider)
   {
      providers.add(labelProvider);
   }

   private TableLabelProvider getProvider(Object element)
   {
      for (int i = 0; i < providers.size(); i++)
      {
         TableLabelProvider provider = (TableLabelProvider) providers.get(i);
         if (provider.accept(element))
         {
            return provider;
         }
      }
      return DefaultTableLabelProvider.instance();
   }

   public boolean accept(Object element)
   {
      return true;
   }

   public Image getImage(Object element)
   {
      TableLabelProvider provider = getProvider(element);
      return provider.getImage(element);
   }

   public String getText(String name, Object element)
   {
      TableLabelProvider provider = getProvider(element);
      return provider.getText(name, element);
   }

   public String getText(int index, Object element)
   {
      TableLabelProvider provider = getProvider(element);
      return provider.getText(index, element);
   }

   public boolean isNamed()
   {
      return named;
   }
}
