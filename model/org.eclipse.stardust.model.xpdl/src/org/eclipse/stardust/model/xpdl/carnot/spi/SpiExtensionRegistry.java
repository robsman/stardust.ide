/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.carnot.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;

/**
 * @author fherinean
 * @version $Revision$
 */
public class SpiExtensionRegistry
{
   public static final String FEATURE_PREFIX = "feature:"; //$NON-NLS-1$

   private static SpiExtensionRegistry instance;
   private IStardustExtensionRegistry extensionRegistry = StardustExtensionRegistry.instance();

   private Map<String, Map<String, IConfigurationElement>> registry = CollectionUtils.newMap();      
   private Map<String, List<IConfigurationElement>> registry_ = CollectionUtils.newMap();         

   public static SpiExtensionRegistry instance()
   {
      if (instance == null)
      {
         instance = new SpiExtensionRegistry();
      }
      return instance;
   }

   public void setExtensionRegistry(IStardustExtensionRegistry extensionRegistry)
   {
      this.extensionRegistry = extensionRegistry;
   }
      
   public Map<String, IConfigurationElement> getExtensions(String extensionPointId)
   {
	  return getExtensions(CarnotConstants.DIAGRAM_PLUGIN_ID, extensionPointId);
   }

   public Map<String, IConfigurationElement> getExtensions(String packageName, String extensionPointId)
   {
      String expandedId = packageName + "." + extensionPointId; //$NON-NLS-1$
      Map<String, IConfigurationElement> extensions = registry.get(expandedId);
      if (extensions == null)
      {
         extensions = new TreeMap<String, IConfigurationElement>();
         registry.put(expandedId, extensions);
         addExternalExtensions(extensions, expandedId);
      }
      return extensions;
   }

   public List<IConfigurationElement> getExtensionList(String extensionPointId)
   {
	  return getExtensionList(CarnotConstants.DIAGRAM_PLUGIN_ID, extensionPointId);
   }
   
   public List<IConfigurationElement> getExtensionList(String packageName, String extensionPointId)
   {
      String expandedId = packageName + "." + extensionPointId; //$NON-NLS-1$
      List<IConfigurationElement> extensions = null;
      try
      {
         extensions = registry_.get(expandedId);
         if (extensions == null)
         {
            extensions = CollectionUtils.newList();
            registry_.put(expandedId, extensions);
            
            if (extensionRegistry != null)
            {               
               IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(expandedId);
               IExtension[] extension = extensionPoint.getExtensions();
               for (int i = 0; i < extension.length; i++)
               {
                  IConfigurationElement[] configuration = extension[i].getConfigurationElements();
                  for (int j = 0; j < configuration.length; j++)
                  {
                     extensions.add(configuration[j]);
                  }
               }
            }
         }
      }
      catch (Exception e)
      {
         // ignoring this exception makes it possible to run it in headless mode
      }
      return extensions;
   }
   
   private void addExternalExtensions(Map<String, IConfigurationElement> extensions, String expandedId)
   {
      try
      {
         if (extensionRegistry != null)
         {
            IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(expandedId);
            if(extensionPoint != null)
            {
               IExtension[] extension = extensionPoint.getExtensions();
               for (int i = 0; i < extension.length; i++)
               {
                  IConfigurationElement[] configuration = extension[i].getConfigurationElements();
                  for (int j = 0; j < configuration.length; j++)
                  {
                     String id = configuration[j].getAttribute(SpiConstants.ID);
                     extensions.put(id, configuration[j]);
                  }
               }
            }
         }
      }
      catch (Exception e)
      {
         // ignoring this exception makes it possible to run it in headless mode
      }
   }

   public static void stop()
   {
      if (null != instance)
      {
         instance.registry.clear();
      }
   }

   public String getTypeIcon(String extensionPointId, String type)
   {
      Map<String, IConfigurationElement> extensions = getExtensions(extensionPointId);
      IConfigurationElement element = extensions.get(type);
      
      return element == null ? null : encodeExtensionIcon(element);
   }
   
   /**
    * There is no actual check if the returned object is a property page or not,
    * but it is assumed to be so since it is created from the property pages extension. 
    * 
    * @param pageId
    * @param metaType
    * @return
    */
   public static Object createPropertyPage(String pageId, String metaType)
   {
      return createExecutableExtension("org.eclipse.ui.propertyPages", "class", pageId, //$NON-NLS-1$ //$NON-NLS-2$
            "filter", "metaType", metaType); //$NON-NLS-1$ //$NON-NLS-2$
   }

   public static Object createExecutableExtension(String extensionId,
         String classAttribute, String objectId, String childFilterName,
         String filterName, String filterValue)
   {
      try
      {
         IConfigurationElement[] extensions = Platform.getExtensionRegistry()
               .getConfigurationElementsFor(extensionId);
         for (int i = 0; i < extensions.length; i++)
         {
            IConfigurationElement cfg = extensions[i];
            String id = cfg.getAttribute(SpiConstants.ID);
            if (objectId.equals(id))
            {
               if (childFilterName == null)
               {
                  return cfg.createExecutableExtension(classAttribute);
               }
               IConfigurationElement[] filters = cfg.getChildren(childFilterName);
               for (int j = 0; j < filters.length; j++)
               {
                  String name = filters[j].getAttribute(SpiConstants.NAME);
                  String value = filters[j].getAttribute(SpiConstants.ATTR_VALUE);
                  if (filterName.equals(name) && filterValue.equals(value))
                  {
                     return cfg.createExecutableExtension(classAttribute);
                  }
               }
            }
         }
      }
      catch (CoreException e)
      {
         // ignore
         e.printStackTrace();
      }
      return null;
   }

   public static IConfigurationElement getConfiguration(IExtensibleElement extensible)
   {
      if (extensible instanceof ITypedElement)
      {
         IMetaType type = ((ITypedElement) extensible).getMetaType();
         if (type != null)
         {
            Map<String, IConfigurationElement> extensions = instance().getExtensions(type.getExtensionPointId());
            if (extensions != null)
            {
               return (IConfigurationElement) extensions.get(type.getId());
            }
         }
      }
      return null;
   }
   
   public static List<IConfigurationElement> getConfiguration(IExtensibleElement extensible, String id)
   {
      String typeId = null;
      if (extensible instanceof ITypedElement)
      {         
         IMetaType type = ((ITypedElement) extensible).getMetaType();
         if (type != null)
         {
            typeId = type.getId();
         }
      }               
      
      Class<? extends IExtensibleElement> extensibleClass = extensible.getClass();
      
      List<IConfigurationElement> extensions = instance().getExtensionList("org.eclipse.stardust.model.xpdl", id); //$NON-NLS-1$
      if (extensions != null)
      {
         List<IConfigurationElement> matching = new ArrayList<IConfigurationElement>();
         for(IConfigurationElement element : extensions)
         {               
            String classValue = element.getAttribute("class"); //$NON-NLS-1$
            String meta = element.getAttribute("meta"); //$NON-NLS-1$

            try
            {
               Class<?> typeClass = Class.forName(classValue);                     
               boolean isAssignable = typeClass.isAssignableFrom(extensibleClass);
               
               if(isAssignable)
               {
                  if(!StringUtils.isEmpty(meta))
                  {
                     if(!StringUtils.isEmpty(typeId) && typeId.equals(meta))
                     {
                        matching.add(element);
                     }
                  }
                  else
                  {
                     matching.add(element);
                  }
               }
            }
            catch (ClassNotFoundException e)
            {
            }
         }
         
         return matching; 
      }
      return null;
   }

   public static String encodeExtensionIcon(IConfigurationElement extension)
   {
      return "{" + extension.getContributor().getName() + "}" //$NON-NLS-1$ //$NON-NLS-2$
            + extension.getAttribute(SpiConstants.ICON);
   }

   public static boolean matchFilterValue(boolean notOperator, String filterValue, String value)
   {
      boolean directMatch = filterValue != null && filterValue.equals(value);
      return notOperator ? !directMatch : directMatch;
   }

   public static boolean matchFilter(IConfigurationElement[] filters, String filterName, String filterValue)
   {
      boolean hasFilter = false;
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
         if (filterName.equals(name))
         {
            hasFilter = true;
            if (matchFilterValue(notOperator, filterValue, value))
            {
               return true;
            }
         }
      }
      return !hasFilter;
   }

   public static Object getFeatureValue(EObject element, String featureName)
   {
      String[] featureNames = featureName.split("\\."); //$NON-NLS-1$
      if(featureNames.length > 1)
      {
         String first = featureNames[0];
         int end = first.length();
         String featurePart = featureName.substring(0, end);         
         String featureNext = featureName.substring(end + 1);
         
         EStructuralFeature feature = element.eClass().getEStructuralFeature(featurePart);
         if (feature == null)
         {
            return null;
         }
         Object featureValue = element.eGet(feature);
         if(featureValue instanceof EObject)
         {
            return getFeatureValue((EObject) featureValue, featureNext);
         }
         else
         {
            return null;            
         }
      }            
      
      EStructuralFeature feature = element.eClass().getEStructuralFeature(featureName);
      if (feature == null)
      {
         return null;
      }
      // returns element
      Object featureValue = element.eGet(feature);
      return featureValue;
   }
}