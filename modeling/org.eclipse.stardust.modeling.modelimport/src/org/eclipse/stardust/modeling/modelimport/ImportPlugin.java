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

import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ImportPlugin extends AbstractUIPlugin
{
   public static final String PLUGIN_ID = "org.eclipse.stardust.modeling.modelimport"; //$NON-NLS-1$

   private final static String EXTENSION_ID = "thirdPartyModelImport"; //$NON-NLS-1$

   // The shared instance.
   private static ImportPlugin plugin;

   /**
    * The constructor.
    */
   public ImportPlugin()
   {
      plugin = this;
   }

   /**
    * This method is called upon plug-in activation
    */
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
   }

   /**
    * This method is called when the plug-in is stopped
    */
   public void stop(BundleContext context) throws Exception
   {
      super.stop(context);
      plugin = null;
   }

   /**
    * Returns the shared instance.
    */
   public static ImportPlugin getDefault()
   {
      return plugin;
   }

   /**
    * Returns an image descriptor for the image file at the given plug-in relative path.
    * 
    * @param path
    *           the path
    * @return the image descriptor
    */
   public static ImageDescriptor getImageDescriptor(String path)
   {
      return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
   }

   public static Map getExtensions()
   {
      return ImportExtensionRegistry.instance().getExtensions(EXTENSION_ID);
   }
   
   public static Map getSourceGroupProviders()
   {
      return ImportExtensionRegistry.instance().getSourceGroupProviders(EXTENSION_ID);
   }
   
   public static void resetExtensions()
   {
      ImportExtensionRegistry.instance().reset();
   }
}
