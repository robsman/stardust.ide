package org.eclipse.stardust.modeling.validation.impl;

import org.eclipse.ui.PlatformUI;

public class IdeConfigurationProvider implements ConfigurationProvider
{
   @Override
   public String getString(String optionName)
   {
      return PlatformUI.getPreferenceStore().getString(optionName);
   }
}
