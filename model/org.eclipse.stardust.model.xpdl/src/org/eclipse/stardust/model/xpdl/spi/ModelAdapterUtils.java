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
package org.eclipse.stardust.model.xpdl.spi;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.stardust.model.xpdl.carnot.CwmPlugin;

public class ModelAdapterUtils
{
   public static IModelAdapterFactory getModelAdapterFactory()
   {
      IModelAdapterFactory adapterFactory = null;

      IExtensionRegistry registry = Platform.getExtensionRegistry();
      IConfigurationElement[] configElements = registry.getConfigurationElementsFor(
            CwmPlugin.PLUGIN_ID, CwmPlugin.EXTENSION_POINT_MODEL_ADAPTER_FACTORY);
      for (IConfigurationElement configElement : configElements)
      {
         try
         {
            adapterFactory = (IModelAdapterFactory) configElement.createExecutableExtension("implementation");
            break;
         }
         catch (CoreException ce)
         {
            System.out.println("Failed instantiating extension: " + ce.getMessage());
         }
      }

      return adapterFactory;
   }
}
