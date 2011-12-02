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
import org.osgi.framework.BundleContext;

import ag.carnot.config.Parameters;
import ag.carnot.config.ParametersFacade;
import ag.carnot.utils.xml.CompressedDumpReader;

/**
 * The activator class controls the plug-in life cycle
 */
public class BpmCommonActivator extends Plugin
{

   // The plug-in ID
   public static final String PLUGIN_ID = "org.eclipse.stardust.modeling.common";

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
    * Transiently sets the location of the license file for this session.
    * <em>The method name was obfuscated by purpose.</em>.
    * 
    * @param path
    *           The full path of the license file to be used.
    */
   public void setTraceFilePath(String path)
   {
      Parameters.instance().flush();

      CompressedDumpReader.flush();

      Parameters.instance().setString(
            String.valueOf(new char[] {
                  'L', 'i', 'c', 'e', 'n', 's', 'e', '.', 'L', 'i', 'c', 'e', 'n', 's',
                  'e', 'F', 'i', 'l', 'e', 'P', 'a', 't', 'h'}), path);
   }

   /**
    * Checks if a valid license for the given module is present.
    * <em>The method name was obfuscated by purpose.</em>.
    * 
    * @param module
    *           The name of the module to be checked.
    * @return <code>null</code> if the module is licensed properly, or an exception
    *         describing the reason while the license check failed.
    */
   public Exception traceModule(String module)
   {
      Exception moduleTrace = null;

      try
      {
         CompressedDumpReader.instance().openStream(module);
      }
      catch (Exception e)
      {
         moduleTrace = e;
      }

      return moduleTrace;
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
