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
package org.eclipse.stardust.modeling.common;

import java.util.Map;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.stardust.common.config.ParametersFacade;
import org.osgi.framework.BundleContext;

import org.eclipse.stardust.engine.api.model.Modules;
import org.eclipse.stardust.engine.core.extensions.ExtensionService;

/**
 * The activator class controls the plug-in life cycle
 */
public class BpmCommonActivator extends Plugin
{

   // The plug-in ID
   public static final String PLUGIN_ID = "org.eclipse.stardust.modeling.common"; //$NON-NLS-1$

   // The shared instance
   private static BpmCommonActivator plugin;

   /**
    * The constructor
    */
   public BpmCommonActivator()
   {
      plugin = this;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
    */
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
    */
   public void stop(BundleContext context) throws Exception
   {
      plugin = null;
      super.stop(context);
   }

   /**
    * Returns the shared instance
    * 
    * @return the shared instance
    */
   public static BpmCommonActivator getDefault()
   {
      return plugin;
   }

   /**
    * Resets the extensions for this session.
    * 
    * @param path options for resetting.
    */
   public void resetExtensions(String options)
   {
      ExtensionService.resetModuleExtensions(options);
   }

   /**
    * Safely initializes extensions for the given module.
    * 
    * @param module the name of the module to be initialized.
    * @return <code>null</code> if the module is successfully initialized,
    *         or the exception that occurred during initialization.
    */
   public Exception initializeExtensions(Modules module)
   {
      Exception exception = null;

      try
      {
         ExtensionService.initializeModuleExtensions(module);
      }
      catch (Exception e)
      {
         exception = e;
      }

      return exception;
   }
   
   public static void pushToNewPropertyLayer(Map<String, ?> properties)
   {
      ParametersFacade.pushLayer(properties);
   }
   
   public static void popPropertyLayer()
   {
      ParametersFacade.popLayer();
   }
}
