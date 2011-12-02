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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.stardust.modeling.templates.spi.ITemplateFactory;


public class Category
{
   private String id;
   private String name;
   private Category parent;
   
   private List factories = new ArrayList();

   public Category(String id, String name, Category parent)
   {
      this.id = id;
      this.name = name;
      this.parent = parent;
   }

   public String getId()
   {
      return id;
   }

   public String getName()
   {
      return name;
   }

   public Category getParent()
   {
      return parent;
   }

   public List getTemplates() {
      List allTemplates = new ArrayList();
      for (Iterator i = factories.iterator(); i.hasNext();) {
         ITemplateFactory templateFactory = (ITemplateFactory)i.next();
         if (templateFactory.getTemplates() != null) {
            allTemplates.addAll(Arrays.asList(templateFactory.getTemplates()));            
         }
      }
      return allTemplates;
   }
   
   public List getFactories()
   {
      return factories;
   }
   
   public String toString()
   {
      if (name == null)
      {
         return id == null ? "" : id;
      }
      return name;
   }

   public Object[] getChildren()
   {
      List allChildren = getTemplates();     
      Map categoryMap = new HashMap();
      for (Iterator i = factories.iterator(); i.hasNext();) {
         ITemplateFactory factory = (ITemplateFactory)i.next();
         ITemplateFactory[] childFactories = factory.getChildFactories();
         if (childFactories != null) {
            for (int f = 0; f < childFactories.length; f++) {
               ITemplateFactory childFactory = childFactories[f];
               Object object = categoryMap.get(childFactory.getId());
               Category category = null;
               if (object != null) {
                    category = (Category)object;                           
               } else {
                    category = new Category(childFactory.getId(), childFactory.getName(), null);
                    allChildren.add(category);
                    categoryMap.put(category.getId(), category);                                                                 
               }                                   
               category.getFactories().add(childFactory);
            }
         }         
      }
      return allChildren.toArray();       
   }
}
