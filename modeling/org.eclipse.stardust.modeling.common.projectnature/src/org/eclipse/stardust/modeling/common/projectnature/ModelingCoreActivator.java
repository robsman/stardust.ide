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
package org.eclipse.stardust.modeling.common.projectnature;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ModelingCoreActivator extends Plugin
{

   // The plug-in ID
   public static final String PLUGIN_ID = "org.eclipse.stardust.modeling.common.projectnature"; //$NON-NLS-1$

   public static final String ID_BPM_CORE_LIBS_CP = PLUGIN_ID + ".carnotBpmCoreLibraries"; //$NON-NLS-1$
   
   public static final String ID_BPM_SPRING_LIBS_CP = PLUGIN_ID + ".carnotBpmSpringLibraries"; //$NON-NLS-1$
   
   public static final String ID_BPM_TOOL_LIBS_CP = PLUGIN_ID + ".carnotBpmToolLibraries"; //$NON-NLS-1$
   
   public static final String ID_CARNOT_HOME_LOCATION_CP = PLUGIN_ID + ".carnotHomeLocation"; //$NON-NLS-1$
   
   public static final String ID_CARNOT_WORK_LOCATION_CP = PLUGIN_ID + ".carnotWorkLocation"; //$NON-NLS-1$
   
   public static final String ID_CARNOT_TOOL_CP_PROVIDER = PLUGIN_ID + ".carnotToolClasspathProvider"; //$NON-NLS-1$
   
   // The shared instance
   private static ModelingCoreActivator plugin;

   /**
    * The constructor
    */
   public ModelingCoreActivator()
   {
      plugin = this;
   }

   public void start(BundleContext context) throws Exception
   {
      super.start(context);
   }

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
   public static ModelingCoreActivator getDefault()
   {
      return plugin;
   }
}
