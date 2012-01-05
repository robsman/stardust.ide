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
package org.eclipse.stardust.modeling.project.effort;

import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.modeling.project.i18n.Messages;

public final class EffortParameterScope extends NamedItemList implements NamedItem
{
   private Class<?> scopeClass;

   private String filter;

   private EffortParameters parameters;

   EffortParameterScope(EffortParameters parameters, Class<?> scope, String filter)
   {
      this.parameters = parameters;
      this.scopeClass = scope;
      this.filter = filter;
   }
   
   public boolean isApplicable(Class<?> scope, String filter)
   {
      return CompareHelper.areEqual(scopeClass, scope) && CompareHelper.areEqual(this.filter, filter);
   }

   public boolean isApplicable(Object target)
   {
      boolean result = scopeClass.isInstance(target);
      return filter == null ? result : filter(target);
   }

   private boolean filter(Object target)
   {
      // TODO keep in sync with the declarations
      ApplicationType app = (ApplicationType) target;
      if ("Interactive".equals(filter))
      {
         return app.isInteractive();
      }
      if ("Service".equals(filter))
      {
         return !app.isInteractive();
      }
      return false;
   }

   public String getFilter()
   {
      return filter;
   }

   public Class<?> getScopeClass()
   {
      return scopeClass;
   }

   public String getDisplayName()
   {
      return Messages.getString(getName());
   }

   public String toString()
   {
      return getDisplayName();
   }

   public String getSimpleName()
   {
      String name = getScopeClass().getName();
      int ix = name.lastIndexOf('.');
      if (ix > 0)
      {
         name = name.substring(ix + 1);
      }
      return name;
   }
   
   public String getName()
   {
      return filter == null ? getSimpleName() : filter;
   }

   public void addParameter(EffortParameter parameter)
   {
      add(parameter);
      parameters.markModified();
   }
   
   public void removeParameter(EffortParameter parameter)
   {
      remove(parameter);
      parameters.markModified();
   }
   
   public Iterator<String> getParameterNames()
   {
      return getNames();
   }

   public boolean hasParameter(String name)
   {
      return get(name) != null;
   }

   public EffortParameter getParameter(String name)
   {
      EffortParameter parameter = (EffortParameter) get(name);
      if (parameter != null)
      {
         return parameter;
      }
      throw new IllegalArgumentException(MessageFormat.format(
            "Effort driver ID {0} is not supported.", new Object[] {name})); //$NON-NLS-1$
   }

   public int parameterCount()
   {
      return size();
   }

   public EffortParameters getEffortParameters()
   {
      return parameters;
   }
}
