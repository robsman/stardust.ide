/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.modeling.validation;

import static org.eclipse.stardust.common.CollectionUtils.newArrayList;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.stardust.modeling.validation.impl.EquinoxExtensionDescriptor;

public class ValidationExtensionRegistry implements IValidationExtensionRegistry
{
   public List<ExtensionDescriptor> getExtensionDescriptorsFor(String extensionPointId)
   {
      IConfigurationElement[] configElements = getConfigurationElementsFor(extensionPointId);

      List<ExtensionDescriptor> extensions = newArrayList();
      for (IConfigurationElement configurationElement : configElements)
      {
         extensions.add(new EquinoxExtensionDescriptor(configurationElement));
      }
      return extensions;
   }

   public IConfigurationElement[] getConfigurationElementsFor(String extensionPointId)
   {
      IConfigurationElement[] configElements = Platform.getExtensionRegistry()
            .getConfigurationElementsFor(extensionPointId);
      return configElements;
   }
}