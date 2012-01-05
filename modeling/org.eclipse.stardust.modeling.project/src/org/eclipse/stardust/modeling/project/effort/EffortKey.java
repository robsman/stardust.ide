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

import org.eclipse.stardust.common.CompareHelper;

public class EffortKey extends EffortNotifier implements NamedItem, ScopedItem
{
   private EffortByKeyParameter parameter;
   private String name;

   private EffortPerUnit effortPerUnit;

   public EffortKey(EffortByKeyParameter parameter, String name, double value)
   {
      if (parameter == null)
      {
         throw new IllegalArgumentException("Parameter may not be null."); //$NON-NLS-1$
      }

      if (name == null)
      {
         throw new IllegalArgumentException("Name may not be null."); //$NON-NLS-1$
      }

      this.parameter = parameter;
      this.name = name;
      effortPerUnit = new EffortPerUnit(this, value);
   }

   public EffortKey(EffortByKeyParameter parameter, String name, String initializer)
   {
      if (parameter == null)
      {
         throw new IllegalArgumentException("Parameter may not be null."); //$NON-NLS-1$
      }

      if (name == null)
      {
         throw new IllegalArgumentException("Name may not be null."); //$NON-NLS-1$
      }

      this.parameter = parameter;
      this.name = name;
      effortPerUnit = new EffortPerUnit(this, initializer);
   }

   public EffortByKeyParameter getParameter()
   {
      return parameter;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      String oldName = this.name;
      if (!CompareHelper.areEqual(name, oldName))
      {
         this.name = name;
         parameter.getScope().getEffortParameters().markModified();
         parameter.getScope().getEffortParameters().keyNameChanged(this, name, oldName);
         notifyListeners(new EffortEvent(this, NAME_PROPERTY, name, oldName));
      }
   }

   public EffortPerUnit getEffortPerUnit()
   {
      return effortPerUnit;
   }

   public String getInitializer()
   {
      return effortPerUnit.getInitializer();
   }

   public EffortParameterScope getScope()
   {
      return parameter.getScope();
   }
}
