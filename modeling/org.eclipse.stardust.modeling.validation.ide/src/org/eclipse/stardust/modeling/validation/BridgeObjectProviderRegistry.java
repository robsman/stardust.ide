package org.eclipse.stardust.modeling.validation;

import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;

public class BridgeObjectProviderRegistry
{
   private final ValidationExtensionRegistry extensionRegistry = new ValidationExtensionRegistry();

   private static final AtomicReference<BridgeObjectProviderRegistry> INSTANCE = new AtomicReference<BridgeObjectProviderRegistry>();

   public static IBridgeObjectProvider getBridgeObjectProvider(ITypedElement modelElement)
   {
      if (null == INSTANCE.get())
      {
         INSTANCE.compareAndSet(null, new BridgeObjectProviderRegistry());
      }

      return INSTANCE.get().doGetBridgeObjectProvider(modelElement);
   }

   public IBridgeObjectProvider doGetBridgeObjectProvider(ITypedElement modelElement)
   {
      // (fh) DO NOT CACHE

      IMetaType type = modelElement.getMetaType();
      if (type != null)
      {
         String id = type.getId();
         IConfigurationElement[] extensions = extensionRegistry
               .getConfigurationElementsFor(ValidationConstants.BRIDGE_PROVIDER_EXTENSION_POINT);
         for (int i = 0; i < extensions.length; i++ )
         {
            IConfigurationElement extension = extensions[i];
            try
            {
               String dataTypeId = extension.getAttribute(ValidationConstants.EP_ATTR_DATA_TYPE_ID);
               if (CompareHelper.areEqual(dataTypeId, id))
               {
                  Object provider = extension.createExecutableExtension(ValidationConstants.EP_ATTR_CLASS);
                  if (IBridgeObjectProvider.class.isInstance(provider))
                  {
                     return (IBridgeObjectProvider) provider;
                  }
               }
            }
            catch (CoreException e)
            {
               // todo (fh) some messages?
               // e.printStackTrace();
            }
         }
      }
      return null;
   }

   private BridgeObjectProviderRegistry()
   {
      // utility class
   }
}
