package org.eclipse.stardust.modeling.validation.util;

import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.modeling.validation.ExtensionDescriptor;

/**
 * TODO Consolidate with ExtensionsResolver in org.eclipse.stardust.modeling.common.platform
 */
public class ExtensionsResolver
{
   public static boolean isMatchingElement(EObject element, String classAttributeName,
         Map<String, String> filterValues, ExtensionDescriptor template)
   {
      try
      {
         if (template.isMatchingClass(element, classAttributeName))
         {
            IConfigurationElement[] filters = template.getFilters();
            for (Map.Entry<String, String> entry : filterValues.entrySet())
            {
               if (!SpiExtensionRegistry.matchFilter(filters, entry.getKey(), entry.getValue()))
               {
                  return false;
               }
            }
            for (int j = 0; j < filters.length; j++)
            {
               boolean notOperator = false;
               String name = filters[j].getAttribute(SpiConstants.NAME);
               String value = filters[j].getAttribute(SpiConstants.ATTR_VALUE);
               if (name.startsWith("!")) //$NON-NLS-1$
               {
                  notOperator = true;
                  name = name.substring(1);
               }
               if ("metaType".equals(name)) //$NON-NLS-1$
               {
                  if (!(element instanceof ITypedElement))
                  {
                     return false; // (fh) metaType filter requires an ITypedElement instance object
                  }
                  IMetaType metaType = ((ITypedElement) element).getMetaType();
                  if (!SpiExtensionRegistry.matchFilterValue(notOperator, metaType == null ? null : metaType.getId(), value))
                  {
                     return false;
                  }
               }
               if (name.startsWith(SpiExtensionRegistry.FEATURE_PREFIX))
               {
                  String featureName = name.substring(SpiExtensionRegistry.FEATURE_PREFIX.length());
                     Object featureValue = SpiExtensionRegistry.getFeatureValue(element, featureName);
                     if(featureValue == null)
                  {
                     return false;
                     }

                  if (!SpiExtensionRegistry.matchFilterValue(notOperator, featureValue == null ? null : featureValue.toString(), value))
                  {
                     return false;
                  }
               }
            }
            return true;
         }
      }
      catch (ClassNotFoundException e)
      {
         // do nothing
         // e.printStackTrace();
      }
      return false;
   }

   private ExtensionsResolver()
   {
      // utility class
   }
}
