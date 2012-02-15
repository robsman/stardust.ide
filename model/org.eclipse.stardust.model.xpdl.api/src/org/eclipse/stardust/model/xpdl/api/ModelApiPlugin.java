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
package org.eclipse.stardust.model.xpdl.api;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.stardust.model.xpdl.api.internal.adapters.AdapterRegistry;
import org.osgi.framework.BundleContext;


public class ModelApiPlugin extends Plugin
{
   private static ModelApiPlugin instance;

   public AdapterRegistry adapterRegistry;

   public static ModelApiPlugin instance()
   {
      return instance;
   }

   public static AdapterRegistry getAdapterRegistry()
   {
      return instance().adapterRegistry;
   }

   public ModelApiPlugin()
   {
      super();
   }

   public void start(BundleContext context) throws Exception
   {
      super.start(context);

      this.adapterRegistry = new AdapterRegistry();

      instance = this;
   }

   public void stop(BundleContext context) throws Exception
   {
      instance = null;

      super.stop(context);
   }

}
