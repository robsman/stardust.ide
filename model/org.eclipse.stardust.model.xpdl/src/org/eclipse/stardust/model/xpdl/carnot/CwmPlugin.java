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
package org.eclipse.stardust.model.xpdl.carnot;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class CwmPlugin extends Plugin
{
   public static final String PLUGIN_ID = "org.eclipse.stardust.model.xpdl";

   public static final String EXTENSION_POINT_MODEL_ADAPTER_FACTORY = "modelAdapterFactory";

   private static CwmPlugin instance;

   public static CwmPlugin getDefault()
   {
      return instance;
   }

   public CwmPlugin()
   {
      // TODO Auto-generated constructor stub
   }

   public void start(BundleContext context) throws Exception
   {
      super.start(context);

      // TODO improve safeness against race conditions
      instance = this;
   }

   public void stop(BundleContext context) throws Exception
   {
      instance = null;

      super.stop(context);
   }

}
