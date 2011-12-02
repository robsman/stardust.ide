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
package org.eclipse.stardust.modeling.debug;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class DebugPlugin extends AbstractUIPlugin
{
   // The shared instance.
   private static DebugPlugin plugin;

   private static BundleContext context = null;

   public static final String ID_DEBUG_LAUNCH_CONFIGURATION_TYPE = "org.eclipse.stardust.modeling.debug.launchConfigurationType"; //$NON-NLS-1$

   /**
    * The constructor.
    */
   public DebugPlugin()
   {
      plugin = this;
   }

   /**
    * This method is called upon plug-in activation
    */
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      DebugPlugin.context = context;
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
   public static DebugPlugin getDefault()
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
      return AbstractUIPlugin.imageDescriptorFromPlugin(Constants.ID_CWM_DEBUG_MODEL,
            path);
   }

   public static Image getImage(String path)
   {
      final ImageRegistry imgRegistry = getDefault().getImageRegistry();

      Image image = imgRegistry.get(path);
      if (null == image)
      {
         ImageDescriptor descriptor = getImageDescriptor(path);
         if (null != descriptor)
         {
            image = descriptor.createImage();
         }
         if (null != image)
         {
            imgRegistry.put(path, image);
         }
      }

      return image;
   }

   public static BundleContext getContext()
   {
      return context;
   }

   /**
    * Returns the active workbench shell or <code>null</code> if none
    * 
    * @return the active workbench shell or <code>null</code> if none
    */
   public static Shell getActiveWorkbenchShell()
   {
      IWorkbenchWindow window = getActiveWorkbenchWindow();
      if (window != null)
      {
         return window.getShell();
      }
      return null;
   }

   /**
    * Returns the active workbench window
    * 
    * @return the active workbench window
    */
   public static IWorkbenchWindow getActiveWorkbenchWindow()
   {
      return getDefault().getWorkbench().getActiveWorkbenchWindow();
   }
}
