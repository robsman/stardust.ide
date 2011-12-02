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

import ag.carnot.base.CompareHelper;

public class EffortByQuantityParameter extends EffortNotifier implements EffortParameter
{
   private String name;

   private EffortPerUnit effortPerUnit;

   private EffortParameterScope scope;

   public EffortByQuantityParameter(EffortParameterScope scope, String name, String initializers)
   {
      this(scope, name);
      effortPerUnit = initializers == null ? new EffortPerUnit(this, 0.0) : new EffortPerUnit(this, initializers);
   }

   public EffortByQuantityParameter(EffortParameterScope scope, String name, double defaultValue)
   {
      this(scope, name);
      effortPerUnit = new EffortPerUnit(this, defaultValue);
   }

   private EffortByQuantityParameter(EffortParameterScope scope, String name)
   {
      this.scope = scope;
      this.name = name;
   }

   public EffortParameterScope getScope()
   {
      return scope;
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
         getScope().getEffortParameters().markModified();
         getScope().getEffortParameters().parameterNameChanged(this, name, oldName);
         notifyListeners(new EffortEvent(this, NAME_PROPERTY, name, oldName));
      }
   }

   public double[] calculateEffort(String quantityString)
   {
      double quantity = Double.parseDouble(quantityString);
      double[] result = effortPerUnit.getEffort();
      for (int i = 0; i < result.length; i++)
      {
         result[i] *= quantity;
      }
      return result;
   }

   public EffortPerUnit getEffortPerUnit()
   {
      return effortPerUnit;
   }

   public String getType()
   {
      return BY_QUANTITY_PARAMETER;
   }

   public String getInitializers()
   {
      return effortPerUnit.getInitializer();
   }

   public String toString()
   {
      return getName();
   }
}
