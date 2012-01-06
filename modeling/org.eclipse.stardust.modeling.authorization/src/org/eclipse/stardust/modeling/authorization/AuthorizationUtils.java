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
package org.eclipse.stardust.modeling.authorization;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class AuthorizationUtils
{
   public static final String ACTIVITY_SCOPE = "activity"; //$NON-NLS-1$
   public static final String DATA_SCOPE = "data"; //$NON-NLS-1$
   public static final String PROCESS_DEFINITION_SCOPE = "processDefinition"; //$NON-NLS-1$
   public static final String MODEL_SCOPE = "model"; //$NON-NLS-1$
   
   public static List<Permission> getPermissions(IExtensibleElement element)
   {
      ArrayList<Permission> permissions = new ArrayList<Permission>();
      String scope = getScope(element);
      if (element != null)
      {
         List<IConfigurationElement> extensions = SpiExtensionRegistry.instance().getExtensionList(
               Constants.PLUGIN_ID, Constants.EXTENSION_POINT_ID);
         for (IConfigurationElement config : extensions)
         {
            if (config.getAttribute(Constants.SCOPE_ATTRIBUTE).equals(scope))
            {
               Permission permission = new Permission(config, (IExtensibleElement) element);
               if (!isInteractiveActivity(element) && permission.isDefaultOwner())
               {
                  ModelType model = ModelUtils.findContainingModel(element);
                  RoleType admin = (RoleType) ModelUtils.findIdentifiableElement(
                        model.getRole(), PredefinedConstants.ADMINISTRATOR_ROLE);
                  permission.setDefault(admin);
               }
               permissions.add(permission);
            }
         }
      }
      return permissions;
   }
   
   public static String getScope(IExtensibleElement element)
   {
      if (element instanceof ModelType)
      {
         return MODEL_SCOPE;
      }
      if (element instanceof ProcessDefinitionType)
      {
         return PROCESS_DEFINITION_SCOPE;
      }
      if (element instanceof DataType)
      {
         return DATA_SCOPE;
      }
      if (element instanceof ActivityType)
      {
         return ACTIVITY_SCOPE;
      }
      return null;
   }
      
   public static boolean isInteractiveActivity(IExtensibleElement element)
   {
      if (element instanceof ActivityType)
      {
         if ((ActivityUtil.isInteractive((ActivityType) element)))
         {
            return true;
         }
      }
      return false;
   }
}