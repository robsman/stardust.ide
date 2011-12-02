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
package org.eclipse.stardust.modeling.common.ui.jface;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class CarnotUiPlugin extends AbstractUIPlugin
{
   public static final String PLUGIN_ID = "org.eclipse.stardust.modeling.common.ui.jface"; //$NON-NLS-1$

   public static final String PATH_OVR_WARNINGS = "icons/full/ovr16/warning_co.gif"; //$NON-NLS-1$
   
   public static final String PATH_OVR_ERRORS = "icons/full/ovr16/error_co.gif"; //$NON-NLS-1$
   
   public static final String PATH_OVR_LINK = "icons/full/ovr16/link_co.gif"; //$NON-NLS-1$
   
   public static final String PATH_OVR_REF = "icons/full/ovr16/ref_co.gif"; //$NON-NLS-1$
   
   public static final String PATH_OVR_INTERFACE = "icons/full/ovr16/interface_tsk.gif"; //$NON-NLS-1$
   
   public static final String PATH_OVR_PRIVATE = "icons/full/ovr16/owned_ovr.gif"; //$NON-NLS-1$

   private static CarnotUiPlugin plugin;

   private IImageManager iconManager;

   /**
    * This method is called upon plug-in activation
    */
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      plugin = this;
   }

   /**
    * This method is called when the plug-in is stopped
    */
   public void stop(BundleContext context) throws Exception
   {
      plugin = null;
      
      super.stop(context);
   }

   /**
    * Returns the shared instance.
    */
   public static CarnotUiPlugin getDefault()
   {
      return plugin;
   }
   
   public IImageManager getImageManager()
   {
      if (null == iconManager)
      {
         iconManager = new ImageManager(this);
      }
      
      return iconManager;
   }
}