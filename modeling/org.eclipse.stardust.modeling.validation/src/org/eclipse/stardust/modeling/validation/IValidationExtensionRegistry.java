/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.modeling.validation;

import org.eclipse.core.runtime.IConfigurationElement;

public interface IValidationExtensionRegistry
{
   public IConfigurationElement[] getConfigurationElementsFor(String extensionPointId);
   
   
}