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

package org.eclipse.stardust.modeling.validation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.*;

public class ServerConfigurationElement implements IConfigurationElement
{
   Class<?> theClass;
   Map<String, String> attributes = new HashMap<String, String>();
   Map<String, IConfigurationElement[]> children = new HashMap<String, IConfigurationElement[]>();
   
   public Class< ? > getTheClass()
   {
      return theClass;
   }

   public void setTheClass(Class< ? > theClass)
   {
      this.theClass = theClass;
   }

   @Override
   public Object createExecutableExtension(String arg0)
   {
      Object newInstance = null;
      
      try
      {
         newInstance = theClass.newInstance();
      }
      catch (InstantiationException e)
      {
      }
      catch (IllegalAccessException e)
      {
      }      
      catch (Exception e)
      {
      }
      
      return newInstance;
   }

   @Override
   public String getAttribute(String key) throws InvalidRegistryObjectException
   {
      return attributes.get(key);
   }

   public void addAttribute(String key, String value) 
   {
      attributes.put(key, value);      
   }   
   
   @Override
   public String getAttributeAsIs(String arg0) throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String[] getAttributeNames() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IConfigurationElement[] getChildren() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IConfigurationElement[] getChildren(String key)
         throws InvalidRegistryObjectException
   {
      return children.get(key);
   }

   public void addChildren(String key, IConfigurationElement[] value)
   {
      children.put(key, value);      
   }   
   
   @Override
   public IContributor getContributor() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IExtension getDeclaringExtension() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getName() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getNamespace() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getNamespaceIdentifier() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Object getParent() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getValue() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getValueAsIs() throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean isValid()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public String getAttribute(String attrName, String locale)
         throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getValue(String locale) throws InvalidRegistryObjectException
   {
      // TODO Auto-generated method stub
      return null;
   }
}