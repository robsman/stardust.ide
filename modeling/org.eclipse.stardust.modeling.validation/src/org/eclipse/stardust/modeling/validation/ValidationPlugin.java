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

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ValidationPlugin extends Plugin
{
   public static final String SYMBOLIC_ID = "org.eclipse.stardust.modeling.validation"; //$NON-NLS-1$
   
   public static final String VALIDATION_MARKER_ID = SYMBOLIC_ID + ".wfModelValidationMarker"; //$NON-NLS-1$
   
   public static final String VALIDATION_MARKER_ATTR_ELEMENT_OID = "wfModelValidationElementId"; //$NON-NLS-1$

   public static final String MARKER_ELEMENT = "markerModelElement"; //$NON-NLS-1$
   
   // The shared instance.
   private static ValidationPlugin plugin;

   /**
    * The constructor.
    */
   public ValidationPlugin()
   {
   }

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
      super.stop(context);
      plugin = null;
   }

   /**
    * Returns the shared instance.
    * 
    * @return the shared instance.
    */
   public static ValidationPlugin getDefault()
   {
      return plugin;
   }
}