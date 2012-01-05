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
package org.eclipse.stardust.modeling.core.utils;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;

/**
 * @author rsauer
 * @version $Revision$
 */
public class MetaTypeModelingUtils
{
   public static IConfigurationElement getApplicationContextExtension(String contextId)
   {
      return getExtension(CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID, contextId);
   }

   public static CreateMetaTypeCommand getCreateContextTypeCmd(IConfigurationElement config)
   {
      return new CreateMetaTypeCommand(
            config,
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationContextTypeType(),
            new EStructuralFeature[] {
                  CarnotWorkflowModelPackage.eINSTANCE.getApplicationContextTypeType_HasMappingId(),
                  CarnotWorkflowModelPackage.eINSTANCE.getApplicationContextTypeType_HasApplicationPath()});
   }

   public static CreateMetaTypeCommand getCreateEventConditionTypeCmd(
         IConfigurationElement config)
   {
      return new CreateMetaTypeCommand(
            config,
            CarnotWorkflowModelPackage.eINSTANCE.getEventConditionTypeType(),
            new EStructuralFeature[] {
                  CarnotWorkflowModelPackage.eINSTANCE.getEventConditionTypeType_Implementation(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventConditionTypeType_ActivityCondition(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventConditionTypeType_ProcessCondition()});
   }

   public static CreateMetaTypeCommand getCreateEventActionTypeCmd(
         IConfigurationElement config)
   {
      return new CreateMetaTypeCommand(
            config,
            CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType(),
            new EStructuralFeature[] {
                  CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType_ActivityAction(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType_ProcessAction(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType_SupportedConditionTypes(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType_UnsupportedContexts()});
   }

   public static IConfigurationElement getExtension(String extensionPointId,
         String extensionId)
   {
      IConfigurationElement result = null;

      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();

      Map extensions = registry.getExtensions(extensionPointId);
      for (Iterator i = extensions.values().iterator(); i.hasNext();)
      {
         IConfigurationElement config = (IConfigurationElement) i.next();
         String id = config.getAttribute(SpiConstants.ID);
         if (CompareHelper.areEqual(id, extensionId))
         {
            result = config;
            break;
         }
      }

      return result;
   }
}
