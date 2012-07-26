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
package org.eclipse.stardust.modeling.validation;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;

public class ValidatorRegistry
{
   private static final IModelValidator[] EMPTY_MODEL_VALIDATORS = new IModelValidator[0];
   private static final IModelElementValidator[] EMPTY_MODEL_ELEMENT_VALIDATORS = new IModelElementValidator[0];
   private static IValidationExtensionRegistry validationExtensionRegistry = ValidationExtensionRegistry.getInstance();
   
   private static ThreadLocal<Map<String, String>> filterSet = new ThreadLocal<Map<String, String>>();
   
   public static void setValidationExtensionRegistry(
         IValidationExtensionRegistry validationExtensionRegistry)
   {
      ValidatorRegistry.validationExtensionRegistry = validationExtensionRegistry;
   }   
   
   public static void setFilters(Map<String, String> filters)
   {
      filterSet.set(filters);
   }

   public static Map<String, String> getFilters()
   {
      return filterSet.get();
   }

   public static IModelValidator[] getModelValidators()
   {      
      List<IModelValidator> result = null;
      IConfigurationElement[] extensions = validationExtensionRegistry
            .getConfigurationElementsFor(ValidationConstants.MODEL_VALIDATOR_EXTENSION_POINT);
      for (int i = 0; i < extensions.length; i++)
      {
         IConfigurationElement extension = extensions[i];
         try
         {
            IModelValidator validator = (IModelValidator) extension.createExecutableExtension("class"); //$NON-NLS-1$
            if (result == null)
            {
               result = CollectionUtils.newList();
            }
            result.add(validator);
         }
         catch (Exception ex)
         {
            // todo (fh) some messages?
            // ex.printStackTrace();
         }
      }
      return result == null ? EMPTY_MODEL_VALIDATORS : result.toArray(new IModelValidator[result.size()]);
   }
   
   public static IBridgeObjectProvider getBridgeObjectProvider(ITypedElement modelElement)
   {
      IMetaType type = modelElement.getMetaType();
      if (type != null)
      {
         String id = type.getId();
         IConfigurationElement[] extensions = validationExtensionRegistry.getConfigurationElementsFor(
               ValidationConstants.BRIDGE_PROVIDER_EXTENSION_POINT);
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

   public static IModelElementValidator[] getModelElementValidators(IModelElement element)
   {
      List<IModelElementValidator> result = null;
      IConfigurationElement[] extensions = validationExtensionRegistry.getConfigurationElementsFor(
            ValidationConstants.ELEMENT_VALIDATOR_EXTENSION_POINT);
      for (int i = 0; i < extensions.length; i++)
      {
         IConfigurationElement extension = extensions[i];
         if (SpiExtensionRegistry.isMatchingElement(element, ValidationConstants.EP_ATTR_TARGET_TYPE, getFilters(), extension))
         {
            try
            {
               IModelElementValidator validator = (IModelElementValidator) extension.createExecutableExtension("class"); //$NON-NLS-1$
               if (result == null)
               {
                  result = CollectionUtils.newList();
               }
               result.add(validator);
            }
            catch (Exception ex)
            {
               // todo (fh) some messages?
               // ex.printStackTrace();
            }
         }
      }
      return result == null ? EMPTY_MODEL_ELEMENT_VALIDATORS : result.toArray(new IModelElementValidator[result.size()]);
   }
}