package org.eclipse.stardust.modeling.validation;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;

public interface ExtensionDescriptor
{
   boolean isMatchingClass(EObject element, String classAttributeName)
         throws ClassNotFoundException;

   IConfigurationElement[] getFilters();

   Object createExecutableExtension() throws InstantiationException;
}
