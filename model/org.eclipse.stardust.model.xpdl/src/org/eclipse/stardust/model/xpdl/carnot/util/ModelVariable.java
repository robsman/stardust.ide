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
package org.eclipse.stardust.model.xpdl.carnot.util;

public class ModelVariable implements Cloneable
{
   private String id;
   
   private String name;

   private String defaultValue;

   private String description;

   private boolean removed;     
   
   public ModelVariable(String name, String defaultValue, String description)
   {
      super();
      this.name = name;
      this.defaultValue = defaultValue;
      this.description = description;
      this.id = null;
   }

   public ModelVariable()
   {}

   public String getId()
   {
      return id;
   }
   
   public String getName()
   {
      return name;
   }

   public String getType()
   {
      if (name.startsWith("${")) //$NON-NLS-1$
      {
         name = name.substring(2, name.length() - 1);
      }
      return VariableContextHelper.getType(name);
   }
   
   public void setName(String name)
   {
      this.name = name;
      if (id == null) {
         id = name;
      }
   }

   public String getDefaultValue()
   {
      return defaultValue;
   }

   public void setDefaultValue(String defaultValue)
   {
      this.defaultValue = defaultValue;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public boolean isRemoved()
   {
      return removed;
   }

   public void setRemoved(boolean removed)
   {
      this.removed = removed;
   }

   public ModelVariable clone()
   {
      return new ModelVariable(new String(this.name), new String(this.defaultValue),
            new String(this.description));
   }
}