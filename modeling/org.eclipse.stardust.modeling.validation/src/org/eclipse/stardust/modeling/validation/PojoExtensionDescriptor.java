package org.eclipse.stardust.modeling.validation;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.StringUtils;

public class PojoExtensionDescriptor implements ExtensionDescriptor
{
   private final ServerConfigurationElement template;

   public PojoExtensionDescriptor(ServerConfigurationElement template)
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
      Class< ? > targetClass = Class.forName(objectClass);

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
      return template.createExecutableExtension(null);
   }
}
