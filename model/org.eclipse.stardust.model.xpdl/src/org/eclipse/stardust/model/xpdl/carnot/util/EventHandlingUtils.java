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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;


/**
 * @author rsauer
 * @version $Revision$
 */
public class EventHandlingUtils
{
   public static Map findInstalledEventConditionTypes(boolean activityConditions,
         boolean processConditions)
   {
      Map items = new HashMap();

      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();

      Map extensions = registry.getExtensions(CarnotConstants.CONDITION_TYPES_EXTENSION_POINT_ID);
      for (Iterator i = extensions.keySet().iterator(); i.hasNext();)
      {
         String id = (String) i.next();
         IConfigurationElement extension = (IConfigurationElement) extensions.get(id);
         if (activityConditions
               && Boolean.toString(true).equalsIgnoreCase(
                     extension.getAttribute(SpiConstants.EH_IS_ACTIVITY_CONDITION)))
         {
            items.put(id, extensions.get(id));
         }
         else if (processConditions
               && Boolean.toString(true).equalsIgnoreCase(
                     extension.getAttribute(SpiConstants.EH_IS_PROCESS_CONDITION)))
         {
            items.put(id, extensions.get(id));
         }
      }
      return items;
   }

   public static IConfigurationElement findInstalledEventConditionType(
         String conditionTypeId, boolean activityConditions, boolean processConditions)
   {
      return (IConfigurationElement) findInstalledEventConditionTypes(activityConditions,
            processConditions).get(conditionTypeId);
   }

   public static Map findConfiguredEventConditionTypes(ModelType model,
         boolean activityConditions, boolean processConditions)
   {
      Map types = new HashMap();
      for (Iterator iterator = model.getEventConditionType().iterator(); iterator.hasNext();)
      {
         EventConditionTypeType conditionType = (EventConditionTypeType) iterator.next();
         if (activityConditions && conditionType.isActivityCondition())
         {
            types.put(conditionType.getId(), conditionType);
         }
         else if (processConditions && conditionType.isProcessCondition())
         {
            types.put(conditionType.getId(), conditionType);
         }
      }
      return types;
   }
   
   public static EventConditionTypeType findConfiguredEventConditionType(ModelType model,
         String conditionTypeId, boolean activityConditions, boolean processConditions)
   {
      return (EventConditionTypeType) findConfiguredEventConditionTypes(model,
            activityConditions, processConditions).get(conditionTypeId);
   }
}
