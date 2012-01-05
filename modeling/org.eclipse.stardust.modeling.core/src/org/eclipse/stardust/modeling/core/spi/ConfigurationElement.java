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
package org.eclipse.stardust.modeling.core.spi;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Status;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ConfigurationElement
{
   public static final String CFG_PAGE = "page"; //$NON-NLS-1$
   
   private HashMap attributes = new HashMap();

   private String name;
   
   private IConfigurationElement delegate;

   private Class pageClass;
   
   public ConfigurationElement(IConfigurationElement delegate)
   {
      this.delegate = delegate;
   }

   public ConfigurationElement(String name)
   {
      this.name = name;
   }

   private void setAttribute(String name, String value)
   {
      attributes.put(name, value);
   }

   public Object createExecutableExtension(String propertyName) throws CoreException
   {
      if (delegate != null)
      {
         return delegate.createExecutableExtension(propertyName);
      }

      try
      {
         if (pageClass == null)
         {
            String propertyClass = (String) attributes.get(propertyName);
            pageClass = Class.forName(propertyClass);
         }
         return pageClass.newInstance();
      }
      catch (Exception e)
      {
         // todo: come with a reasonable error message.
         throw new CoreException(new Status(IStatus.ERROR, CarnotConstants.DIAGRAM_PLUGIN_ID,
                 IStatus.ERROR, e.getMessage(), e));
      }
   }

   public String getAttribute(String name) throws InvalidRegistryObjectException
   {
      if (delegate != null)
      {
         return SpiConstants.ICON.equals(name)
               ? SpiExtensionRegistry.encodeExtensionIcon(delegate)
               : delegate.getAttribute(name);
      }
      
      return (String) attributes.get(name);
   }

   public String getName() throws InvalidRegistryObjectException
   {
      if (delegate != null)
      {
         return delegate.getName();
      }

      return name;
   }
   
   /**
    * creates a ConfigurationElement and adds a category to it 
    * (for ordering the elements in the tree)
    */
   public static ConfigurationElement createPageConfiguration(String id, String name,
         String icon, String propertyPageClass, String category)
   {
      ConfigurationElement page = ConfigurationElement.createPageConfiguration(id, 
            name, icon, propertyPageClass);
      page.setAttribute(SpiConstants.CATEGORY, category);
      return page;
   }   
   
   public static ConfigurationElement createPageConfiguration(String id, String name,
         String icon, String propertyPageClass)
   {
      ConfigurationElement page = new ConfigurationElement(CFG_PAGE);
      page.setAttribute(SpiConstants.ID, id);
      page.setAttribute(SpiConstants.NAME, name);
      page.setAttribute(SpiConstants.ICON, icon);
      page.setAttribute(SpiConstants.PROPERTY_PAGE_CLASS, propertyPageClass);
      return page;
   }

   public static ConfigurationElement createPageConfiguration(String id, String name,
         String icon, Class propertyPageClass)
   {
      ConfigurationElement page = new ConfigurationElement(CFG_PAGE);
      page.setAttribute(SpiConstants.ID, id);
      page.setAttribute(SpiConstants.NAME, name);
      page.setAttribute(SpiConstants.ICON, icon);
      page.setAttribute(SpiConstants.PROPERTY_PAGE_CLASS, propertyPageClass.getName());
      page.pageClass = propertyPageClass;
      return page;
   }

   public ConfigurationElement[] getChildren(String elementName)
   {
      if (delegate != null)
      {
         IConfigurationElement[] children = delegate.getChildren(elementName);
         ConfigurationElement[] result = new ConfigurationElement[children.length];
         for (int i = 0; i < children.length; i++)
         {
            result[i] = new ConfigurationElement(children[i]);
         }
         return result;
      }
      return new ConfigurationElement[0];
   }

   public String[] getAttributeNames()
   {
      if (delegate != null)
      {
         return delegate.getAttributeNames();
      }
      return (String[]) attributes.keySet().toArray(new String[attributes.size()]);
   }
}
