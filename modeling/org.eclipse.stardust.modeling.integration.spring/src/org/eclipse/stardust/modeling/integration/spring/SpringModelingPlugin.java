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
package org.eclipse.stardust.modeling.integration.spring;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


public class SpringModelingPlugin extends AbstractUIPlugin
{
   public static final String PLUGIN_ID = "org.eclipse.stardust.modeling.integration.spring"; //$NON-NLS-1$

   private static SpringModelingPlugin instance;

   public static SpringModelingPlugin instance()
   {
      return instance;
   }

   public SpringModelingPlugin()
   {
   }

   public void start(BundleContext context) throws Exception
   {
      super.start(context);

      instance = this;
   }

   public void stop(BundleContext context) throws Exception
   {
      instance = null;

      super.stop(context);
   }
}
