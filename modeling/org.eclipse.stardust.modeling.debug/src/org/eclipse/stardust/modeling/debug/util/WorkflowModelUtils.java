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
package org.eclipse.stardust.modeling.debug.util;

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

import ag.carnot.base.CollectionUtils;

public final class WorkflowModelUtils
{
   private WorkflowModelUtils()
   {
      // utility class
   }

   public static List<ActivitySymbolType> getActivitySymbols(DiagramType diagram, ActivityType activity)
   {
      List<ActivitySymbolType> result = CollectionUtils.newList();
      List<ActivitySymbolType> allSymbols = activity.getActivitySymbols();
      for (ActivitySymbolType symbol : allSymbols)
      {
         if (diagram == ModelUtils.findContainingDiagram(symbol))
         {
            result.add(symbol);
         }
      }
      return result;
      //return DiagramUtil.getSymbols(diagram,
      //      CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ActivitySymbol(),
      //      activity);
   }
   
   public static List getGatewaySymbols(DiagramType diagram, TransitionType transition)
   {
      return DiagramUtil.getSymbols(diagram,
            CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_GatewaySymbol(),
            transition);
   }
  
   public static List getTransitionConnections(DiagramType diagram, TransitionType transition)
   {
      return DiagramUtil.getSymbols(diagram,
            CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_TransitionConnection(),
            transition);
   }
   
   public static ProcessDefinitionType findProcessDefinition(ModelType model, String id)
   {
      return (ProcessDefinitionType) ModelUtils.findIdentifiableElement(model,
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_ProcessDefinition(), id);
   }
   
   public static ActivityType findActivity(ProcessDefinitionType processDefinition,
         String id)
   {
      return (ActivityType) ModelUtils.findIdentifiableElement(processDefinition,
            CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_Activity(), id);
   }
}
