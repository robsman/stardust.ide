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
package org.eclipse.stardust.modeling.templates.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.modeling.templates.spi.ITemplateFactory;


public class PatternsContentProvider implements ITreeContentProvider
{
   private Object[] input;

   public Object[] getChildren(Object element)
   {
      if (element instanceof Category)
      {
         return ((Category)element).getChildren();
      }
      return null;
   }

   public Object getParent(Object element)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean hasChildren(Object element)
   {
      if (element instanceof Category)
      {
         return ((Category)element).getChildren().length > 0;
      }
      return false;
   }

   public Object[] getElements(Object inputElement)
   {
      return input;
   }

   public void dispose()
   {
      // TODO: clear old factories
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      Map categoryMap = new HashMap();
      // TODO: clear old factories
      String extensionId = (String) newInput;
      List categories = new ArrayList(); 
      if (extensionId != null)
      {
         IConfigurationElement[] extensions = Platform.getExtensionRegistry()
            .getConfigurationElementsFor(extensionId);
         for (int i = 0; i < extensions.length; i++)
         {
            IConfigurationElement cfg = extensions[i];
            try
            {
               String name = cfg.getName();
               if ("factory".equals(name)) //$NON-NLS-1$
               {
                  ITemplateFactory factory = (ITemplateFactory) cfg.createExecutableExtension("class"); //$NON-NLS-1$
                  Map parameters = new HashMap();
                  IConfigurationElement[] children = extensions[i].getChildren("parameter"); //$NON-NLS-1$
                  for (int j = 0; j < children.length; j++)
                  {
                     String cname = children[j].getAttribute("name"); //$NON-NLS-1$
                     String value = children[j].getAttribute("value"); //$NON-NLS-1$
                     parameters.put(cname, value);
                  }
                  factory.initialize(parameters);
                  //Getting all templates provided by the factory and adding to a category
                  Object object = categoryMap.get(factory.getId()); 
                  Category category = null;
                  if (object != null) {
                       category = (Category)object;                           
                  } else {
                       category = new Category(factory.getId(), factory.getName(), null);
                       categories.add(category);
                       categoryMap.put(category.getId(), category);                                                                 
                  }                                   
                  category.getFactories().add(factory);
               }
            }
            catch (CoreException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      input = categories.toArray();
   }
}
