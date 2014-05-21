package org.eclipse.stardust.modeling.validation.impl;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.validation.ExtensionDescriptor;
import org.osgi.framework.Bundle;

public class EquinoxExtensionDescriptor implements ExtensionDescriptor
{
   private final IConfigurationElement template;

   public EquinoxExtensionDescriptor(IConfigurationElement template)
   {
      this.template = template;
   }

   @Override
   public boolean isMatchingClass(EObject element, String classAttributeName)
         throws ClassNotFoundException
   {
      String objectClass = template.getAttribute(classAttributeName);
      if (StringUtils.isEmpty(objectClass))
      {
         return true;
      }

      // legacy support of the objectClass
      Class< ? > targetClass = null;
      String bundleId = null;
      if (template.getContributor() != null)
      {
         bundleId = template.getContributor().getName();
      }
      if (bundleId != null)
      {
         Bundle bundle = Platform.getBundle(bundleId);
         if (bundle != null && bundle.getState() == Bundle.ACTIVE)
         {
            targetClass = bundle.loadClass(objectClass);
         }
      }
      if (targetClass == null)
      {
         targetClass = Class.forName(objectClass);
      }
      return targetClass.isInstance(element);
   }

   @Override
   public IConfigurationElement[] getFilters()
   {
      return template.getChildren("filter"); //$NON-NLS-1$
   }

   @Override
   public Object createExecutableExtension() throws InstantiationException
   {
      try
      {
         return template.createExecutableExtension("class"); //$NON-NLS-1$
      }
      catch (CoreException ce)
      {
         throw new InstantiationException(ce.getMessage());
      }
   }
}
