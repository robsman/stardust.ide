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
package org.eclipse.stardust.modeling.model.i18n;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.model.i18n.properties.ScopedPropertyModel;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.IModelValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;

public class I18NBundlesValidator implements IModelElementValidator, IModelValidator
{
   private static final String SEPARATOR = ":"; //$NON-NLS-1$

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> issues = null;
      ScopedPropertyModel model = null;
      try
      {
         model = new ScopedPropertyModel(element);
         if (model.getPropertyModel().hasLocales())
         {
            Set<?> locales = model.getPropertyModel().getLocales();
            Set<String> properties = model.getProperties();
            if (!properties.isEmpty())
            {
               for (String propertyName : properties)
               {
                  List<Issue> missing = null;
                  boolean hasAtLeastOne = false;
                  for (Object locale : locales)
                  {
                     if (StringUtils.isEmpty(model.getProperty(locale, propertyName)))
                     {
                        if (missing == null)
                        {
                           missing = CollectionUtils.newList();
                        }
                        missing.add(Issue.warning(element, MessageFormat.format(
                              Messages.I18NBundlesValidator_MISSING_VALUE,
                              propertyName, getLocale(locale)),
                              propertyName + SEPARATOR + locale));
                     }
                     else
                     {
                        hasAtLeastOne = true;
                     }
                  }
                  if (hasAtLeastOne && missing != null && !missing.isEmpty())
                  {
                     if (issues == null)
                     {
                        issues = CollectionUtils.newList();
                     }
                     issues.addAll(missing);
                     missing.clear();
                  }
               }
            }
         }
         return issues == null ? null : issues.toArray(new Issue[issues.size()]);
      }
      finally
      {
         if (model != null)
         {
            model.dispose();
         }
      }
   }

   private String getLocale(Object locale)
   {
      return locale instanceof Locale
         ? ((Locale) locale).getDisplayName()
         : String.valueOf(locale);
   }

   public Issue[] validate(ModelType model) throws ValidationException
   {
      return validate(ModelUtils.getIdentifiableModelProxy(model, ModelType.class));
   }
}