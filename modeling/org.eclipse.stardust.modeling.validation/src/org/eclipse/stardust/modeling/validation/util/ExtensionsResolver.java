package org.eclipse.stardust.modeling.validation.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionConverter;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.osgi.framework.Bundle;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;

/**
 * TODO Consolidate with ExtensionsResolver in org.eclipse.stardust.modeling.common.platform
 */
public class ExtensionsResolver
{
   private static Map<IConfigurationElement, Contributor> contributors = new ConcurrentHashMap<IConfigurationElement, ExtensionsResolver.Contributor>();

   public static boolean isMatchingClass(Object element, String classAttributeName, IConfigurationElement template) throws ClassNotFoundException
   {
      Contributor pageContributor = contributors.get(template);
      String pageContributorEnabled = template.getAttribute("pageContributor"); //$NON-NLS-1$
      if (pageContributor == null && pageContributorEnabled == null)
      {
         pageContributor = new Contributor(template);
         contributors.put(template, pageContributor);
      }
      if (pageContributor != null && !pageContributor.isApplicableTo(element, classAttributeName))
      {
         return false;
      }

      String objectClass = template.getAttribute(classAttributeName);
      if (StringUtils.isEmpty(objectClass))
      {
         return true;
      }

      // legacy support of the objectClass
      Class<?> targetClass = null;
      String bundleId = null;
      if(template.getContributor() != null)
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

   public static boolean isMatchingElement(EObject element, String classAttributeName,
         Map<String, String> filterValues, IConfigurationElement template)
   {
      try
      {
         if (isMatchingClass(element, classAttributeName, template))
         {
            IConfigurationElement[] filters = template.getChildren("filter"); //$NON-NLS-1$
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

   private static class Contributor
   {
      private static final String CHILD_ENABLED_WHEN = "enabledWhen"; //$NON-NLS-1$

      private Expression enablementExpression;

      public Contributor(IConfigurationElement template)
      {
         initializeEnablement(template);
      }

      public boolean isApplicableTo(Object element, String classAttributeName)
      {
         if (enablementExpression == null)
         {
            return true;
         }
         try
         {
            EvaluationContext context = new EvaluationContext(null, element);
            context.setAllowPluginActivation(true);
            return enablementExpression.evaluate(context).equals(EvaluationResult.TRUE);
         }
         catch (CoreException e)
         {
//          WorkbenchPlugin.log(e);
            return false;
         }
      }

      private void initializeEnablement(IConfigurationElement template)
      {
         IConfigurationElement[] elements = template.getChildren(CHILD_ENABLED_WHEN);

         if (elements.length == 0)
         {
            return;
         }

         try
         {
            IConfigurationElement[] enablement = elements[0].getChildren();
            if (enablement.length == 0)
            {
               return;
            }
            enablementExpression = ExpressionConverter.getDefault().perform(enablement[0]);
         }
         catch (CoreException e)
         {
//          WorkbenchPlugin.log(e);
         }
      }
   }
}
