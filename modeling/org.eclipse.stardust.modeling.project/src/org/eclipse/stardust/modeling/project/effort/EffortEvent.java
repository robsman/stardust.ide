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

public class EffortEvent
{
   private EffortNotifier source;
   private String property;
   private Object newValue;
   private Object oldValue;

   public EffortEvent(EffortNotifier source, String property, Object newValue, Object oldValue)
   {
      this.source = source;
      this.property = property;
      this.newValue = newValue;
      this.oldValue = oldValue;
   }

   public EffortNotifier getSource()
   {
      return source;
   }

   public String getProperty()
   {
      return property;
   }

   public Object getNewValue()
   {
      return newValue;
   }

   public Object getOldValue()
   {
      return oldValue;
   }
}
