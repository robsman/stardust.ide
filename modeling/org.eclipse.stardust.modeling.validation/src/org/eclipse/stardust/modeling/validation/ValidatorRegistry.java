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

import static java.util.Collections.emptyMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.modeling.validation.util.ExtensionsResolver;

public class ValidatorRegistry
{
   private static final IModelValidator[] EMPTY_MODEL_VALIDATORS = new IModelValidator[0];
   private static final IModelElementValidator[] EMPTY_MODEL_ELEMENT_VALIDATORS = new IModelElementValidator[0];

   private static IValidationExtensionRegistry validationExtensionRegistry = discoveryValidationExtensionRegistry();

   private static IValidationExtensionRegistry discoveryValidationExtensionRegistry()
   {
      Iterator<IValidationExtensionRegistry> registries = ServiceLoader.load(
            IValidationExtensionRegistry.class).iterator();

      return registries.hasNext() ? registries.next() : null;
   }

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
      Map<String, String> filters = filterSet.get();
      if (null == filters)
      {
         filters = emptyMap();
      }
      return filters;
   }

   public static IModelValidator[] getModelValidators()
   {      
      List<IModelValidator> result = null;
      List<ExtensionDescriptor> extensions = validationExtensionRegistry
            .getExtensionDescriptorsFor(ValidationConstants.MODEL_VALIDATOR_EXTENSION_POINT);
      for (int i = 0; i < extensions.size(); i++)
      {
         ExtensionDescriptor extension = extensions.get(i);
         try
         {
            IModelValidator validator = (IModelValidator) extension.createExecutableExtension();
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

   public static IModelElementValidator[] getModelElementValidators(IModelElement element)
   {
      List<IModelElementValidator> result = null;
      List<ExtensionDescriptor> extensions = validationExtensionRegistry.getExtensionDescriptorsFor(
            ValidationConstants.ELEMENT_VALIDATOR_EXTENSION_POINT);
      for (ExtensionDescriptor extension : extensions)
      {
         if (ExtensionsResolver.isMatchingElement(element, ValidationConstants.EP_ATTR_TARGET_TYPE, getFilters(), extension))
         {
            try
            {
               IModelElementValidator validator = (IModelElementValidator) extension.createExecutableExtension();
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