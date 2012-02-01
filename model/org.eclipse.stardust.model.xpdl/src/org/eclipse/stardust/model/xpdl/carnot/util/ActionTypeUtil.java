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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.UnbindActionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;


public class ActionTypeUtil
{
   public static EventActionTypeType getActionType(IModelElement element)
   {
      if (element instanceof EventActionType)
      {
         return ((EventActionType) element).getType();
      }
      else if (element instanceof BindActionType)
      {
         return ((BindActionType) element).getType();
      }
      else if (element instanceof UnbindActionType)
      {
         return ((UnbindActionType) element).getType();
      }
      return null;
   }

   public static String getContext(IModelElement element)
   {
      if (element instanceof EventActionType)
      {
         return "event"; //$NON-NLS-1$
      }
      else if (element instanceof BindActionType)
      {
         return "bind"; //$NON-NLS-1$
      }
      else if (element instanceof UnbindActionType)
      {
         return "unbind"; //$NON-NLS-1$
      }
      return ""; //$NON-NLS-1$
   }

   private static boolean isSupported(ImplementationType implementation,
         IConfigurationElement type, String context)
   {
      if (!implementation.equals(ImplementationType.PULL_LITERAL)
            && (context.equals("bind") || context.equals("unbind"))) //$NON-NLS-1$ //$NON-NLS-2$
      {
         return false;
      }
      String unsupportedContexts = type.getAttribute(SpiConstants.EA_UNSUPPORTED_CONTEXTS);
      StringTokenizer st = new StringTokenizer(unsupportedContexts == null
            ? "" //$NON-NLS-1$
            : unsupportedContexts, ","); //$NON-NLS-1$
      while (st.hasMoreTokens())
      {
         if (context.equalsIgnoreCase(st.nextToken().trim()))
         {
            return false;
         }
      }
      return true;
   }

   public static List getSupportedActionTypes(EventConditionTypeType condition,
         boolean process, boolean activity, String context)
   {
      List list = new ArrayList();
      List actionTypes = SpiExtensionRegistry.instance().getExtensionList(
    		  CarnotConstants.ACTION_TYPES_EXTENSION_POINT_ID);
      for (int i = 0; i < actionTypes.size(); i++)
      {
         IConfigurationElement action = (IConfigurationElement) actionTypes.get(i);
         if (matchParent(action, process, activity))
         {
            String supportedConditions = action.getAttribute(SpiConstants.EA_SUPPORTED_CONDITION_TYPES);
            StringTokenizer st = new StringTokenizer(supportedConditions == null
                  ? "" //$NON-NLS-1$
                  : supportedConditions, ","); //$NON-NLS-1$
            while (st.hasMoreTokens())
            {
               if (condition.getId().equalsIgnoreCase(st.nextToken().trim()))
               {
                  if (context == null
                        || isSupported(condition.getImplementation(), action, context))
                  {
                     list.add(action);
                  }
               }
            }
         }
      }
      return list;
   }

   public static IConfigurationElement findFirstActionType(EventHandlerType handler, String context)
   {
      boolean isProcess = handler.eContainer() instanceof ProcessDefinitionType;
      boolean isActivity = handler.eContainer() instanceof ActivityType;

      EventConditionTypeType condition = handler.getType();

      List actionTypes = SpiExtensionRegistry.instance().getExtensionList(
    		  CarnotConstants.ACTION_TYPES_EXTENSION_POINT_ID);
      for (int i = 0; i < actionTypes.size(); i++)
      {
         IConfigurationElement action = (IConfigurationElement) actionTypes.get(i);
         if (matchParent(action, isProcess, isActivity))
         {
            String supportedConditions = action.getAttribute(SpiConstants.EA_SUPPORTED_CONDITION_TYPES);
            StringTokenizer st = new StringTokenizer(supportedConditions == null
                  ? "" //$NON-NLS-1$
                  : supportedConditions, ","); //$NON-NLS-1$
            while (st.hasMoreTokens())
            {
               if (condition.getId().equalsIgnoreCase(st.nextToken().trim()))
               {
                  if (isSupported(condition.getImplementation(), action, context))
                  {
                     return action;
                  }
               }
            }
         }
      }
      return null;
   }

   private static boolean matchParent(IConfigurationElement action, boolean process,
         boolean activity)
   {
      return activity && getBoolean(action, SpiConstants.EA_IS_ACTIVITY_ACTION)
         || process && getBoolean(action, SpiConstants.EA_IS_PROCESS_ACTION);
   }

   private static boolean getBoolean(IConfigurationElement action, String attName)
   {
	  return "true".equalsIgnoreCase(action.getAttribute(attName)); //$NON-NLS-1$
   }

   public static boolean isAction(Object selection)
   {
      return selection instanceof AbstractEventAction;
   }

   public static List getSupportedActionTypes(EventHandlerType handler, String context)
   {
      boolean process = handler.eContainer() instanceof ProcessDefinitionType;
      boolean activity = handler.eContainer() instanceof ActivityType;

      EventConditionTypeType type = handler.getType();

      return getSupportedActionTypes(type, process, activity, context);
   }

   public static Object getActionTypeConfigurationElement(IIdentifiableModelElement eventActionType)
   {
      EventActionTypeType type = getActionType(eventActionType);
      return SpiExtensionRegistry.instance().getExtensions(
            CarnotConstants.ACTION_TYPES_EXTENSION_POINT_ID).get(type.getId());
   }
}
