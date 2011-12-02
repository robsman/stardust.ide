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
package org.eclipse.stardust.modeling.modelimport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.*;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;


public class ImportExtensionRegistry
{
   private static ImportExtensionRegistry instance;

   private HashMap extRegistry = new HashMap();

   private HashMap sgpRegistry = new HashMap();
   
   public void reset()
   {
      sgpRegistry.clear();
   }

   public static ImportExtensionRegistry instance()
   {
      if (instance == null)
      {
         instance = new ImportExtensionRegistry();
      }
      return instance;
   }

   public Map getExtensions(String extensionPointId)
   {
      Map extensions = (Map) extRegistry.get(extensionPointId);
      if (extensions == null)
      {
         extensions = new TreeMap();
         extRegistry.put(extensionPointId, extensions);
         addExternalExtensions(extensions, extensionPointId);
      }
      return extensions;
   }

   public Map getSourceGroupProviders(String extensionPointId)
   {
      Map sourceGroupProviders = (Map) sgpRegistry.get(extensionPointId);
      if (sourceGroupProviders == null)
      {
         sourceGroupProviders = new TreeMap();

         for (Iterator _iterator = getExtensions(extensionPointId).keySet().iterator(); _iterator
               .hasNext();)
         {
            String key = (String) _iterator.next();
            IConfigurationElement element = (IConfigurationElement) getExtensions(
                  extensionPointId).get(key);

            try
            {
               ISourceGroupProvider provider = null;
               provider = (ISourceGroupProvider) element
                     .createExecutableExtension("class"); //$NON-NLS-1$
               sourceGroupProviders.put(key, provider);
            }
            catch (CoreException e)
            {
               // TODO
               e.printStackTrace();
            }
         }
         sgpRegistry.put(extensionPointId, sourceGroupProviders);
      }
      return sourceGroupProviders;
   }

   private void addExternalExtensions(Map extensions, String extensionPointId)
   {
      IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
      String expandedId = "org.eclipse.stardust.modeling.modelimport." + extensionPointId; //$NON-NLS-1$
      IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(expandedId);
      IExtension[] extension = extensionPoint.getExtensions();
      for (int i = 0; i < extension.length; i++)
      {
         IConfigurationElement[] configuration = extension[i].getConfigurationElements();
         for (int j = 0; j < configuration.length; j++)
         {
            String id = configuration[j].getAttribute(SpiConstants.NAME);
            extensions.put(id, configuration[j]);
         }
      }
   }

   public static void stop()
   {
      if (null != instance)
      {
         instance.extRegistry.clear();
         instance.sgpRegistry.clear();
      }
   }

}
