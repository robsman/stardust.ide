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

import ag.carnot.base.CompareHelper;

public class EffortByKeyParameter extends NamedItemList
   implements EffortParameter
{
   private String name;

   private EffortParameterScope scope;

   public EffortByKeyParameter(EffortParameterScope scope, String name,
         String initializers)
   {
      this(scope, name);
      String[] keyDefinitions = initializers.split(","); //$NON-NLS-1$
      for (int n = 0; n < keyDefinitions.length; ++n)
      {
         String[] keyDefinition = keyDefinitions[n].trim().split("="); //$NON-NLS-1$
         add(new EffortKey(this, keyDefinition[0].trim(), keyDefinition[1].trim()));
      }
   }

   public EffortByKeyParameter(EffortParameterScope scope, String name, String[] keyNames,
         double[] efforts)
   {
      this(scope, name);
      for (int i = 0; i < efforts.length; i++)
      {
         add(new EffortKey(this, keyNames[i], efforts[i]));
      }
   }
   
   private EffortByKeyParameter(EffortParameterScope scope, String name)
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
         scope.getEffortParameters().markModified();
         scope.getEffortParameters().parameterNameChanged(this, name, oldName);
         notifyListeners(new EffortEvent(this, NAME_PROPERTY, name, oldName));
      }
   }

   public void addKey(EffortKey key)
   {
      add(key);
      scope.getEffortParameters().markModified();
   }

   public void removeKey(EffortKey key)
   {
      remove(key);
      scope.getEffortParameters().markModified();
   }

   public EffortKey getKey(String keyName)
   {
      return (EffortKey) get(keyName);
   }

   public Iterator<String> getKeyNames()
   {
      return getNames();
   }

   public double[] calculateEffort(String keyName)
   {
      EffortKey key = getKey(keyName);
      if (key != null)
      {
         return key.getEffortPerUnit().getEffort();
      }
      throw new IllegalArgumentException(MessageFormat.format("Unknown key {0}.", new Object[] {keyName})); //$NON-NLS-1$
   }

   public String getType()
   {
      return BY_KEY_PARAMETER;
   }

   public String getInitializers()
   {
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < size(); ++i)
      {
         EffortKey key = (EffortKey) get(i);
         if (i > 0)
         {
            buffer.append(',');
         }
         buffer.append(key.getName());
         buffer.append('=');
         buffer.append(key.getInitializer());
      }
      return buffer.toString();
   }

   public int keyCount()
   {
      return size();
   }

   public String toString()
   {
      return getName();
   }
}
