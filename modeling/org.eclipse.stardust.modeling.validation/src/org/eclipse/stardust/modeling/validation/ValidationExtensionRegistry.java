/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.modeling.validation;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class ValidationExtensionRegistry implements IValidationExtensionRegistry
{
   private static IValidationExtensionRegistry valdationExtensionRegistry;

   ValidationExtensionRegistry()
   {
   }   
   
   public IConfigurationElement[] getConfigurationElementsFor(String extensionPointId)
   {
      return Platform.getExtensionRegistry().getConfigurationElementsFor(extensionPointId);
   }
   
   public static IValidationExtensionRegistry getInstance()
   {
      if (null == valdationExtensionRegistry)
      {
         valdationExtensionRegistry = new ValidationExtensionRegistry();
      }
      
      return valdationExtensionRegistry;
   }
}