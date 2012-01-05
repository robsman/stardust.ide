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
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetActivityControlFlowCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;


public class ActivityCommandFactory extends DefaultPropSheetCmdFactory
{
   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;

   public static final ActivityCommandFactory INSTANCE = new ActivityCommandFactory();

   public Command createSetValueCommand(UndoablePropSheetEntry entry,
         IPropertyDescriptor descriptor, IPropertySource target, Object value)
   {
      if (null == value)
      {
         return createResetValueCommand(descriptor, target);
      }
      else
      {
         CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());
         result.add(super.createSetValueCommand(entry, descriptor, target, value));
         addUpdateCommand(result, (BoundEObjectPropertyId) descriptor.getId(), target,
               value);
         return result.unwrap();
      }
   }

   public Command createResetValueCommand(IPropertyDescriptor descriptor,
         IPropertySource target)
   {
      CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());
      result.add(super.createResetValueCommand(descriptor, target));
      addUpdateCommand(result, (BoundEObjectPropertyId) descriptor.getId(), target, null);
      return result.unwrap();
   }

   private void addUpdateCommand(CompoundCommand result, BoundEObjectPropertyId id,
         IPropertySource target, Object value)
   {
      EStructuralFeature feature = id.getId();
      if (feature.equals(CWM_PKG.getActivityType_Implementation()))
      {
         addResetCommands(result, (ActivityType) target.getEditableValue(),
               (ActivityImplementationType) value);
      }
      else if (feature.equals(CWM_PKG.getActivityType_Join()))
      {
         addSetJoinSplitType(result, id.getPart(), (ActivityType) target
               .getEditableValue(), FlowControlType.JOIN_LITERAL, (JoinSplitType) value);
      }
      else if (feature.equals(CWM_PKG.getActivityType_Split()))
      {
         addSetJoinSplitType(result, id.getPart(), (ActivityType) target
               .getEditableValue(), FlowControlType.SPLIT_LITERAL, (JoinSplitType) value);
      }
   }

   private void addSetJoinSplitType(CompoundCommand result, EditPart part,
         ActivityType activity, FlowControlType flowType, JoinSplitType gatewayType)
   {
      if (gatewayType == null)
      {
         gatewayType = JoinSplitType.NONE_LITERAL;
      }
      if (getGatewayType(activity, flowType) != gatewayType)
      {
         result.add(new SetActivityControlFlowCmd(null, activity, flowType, gatewayType));
      }
   }

   private JoinSplitType getGatewayType(ActivityType activity, FlowControlType flowType)
   {
      return FlowControlType.JOIN_LITERAL.equals(flowType)
            ? activity.getJoin()
            : activity.getSplit();
   }

   private void addResetCommands(CompoundCommand command, ActivityType activity,
         ActivityImplementationType implementation)
   {
      if (!ActivityImplementationType.APPLICATION_LITERAL.equals(implementation))
      {
         if (activity.getApplication() != null)
         {
            command.add(new DeleteValueCmd(activity, activity.getApplication(), CWM_PKG
                  .getActivityType_Application()));
            DiagramCommandFactory.INSTANCE.addDeleteConnectionCommands(command, activity,
                  CWM_PKG.getISymbolContainer_ExecutedByConnection());
         }
      }

      if (!ActivityImplementationType.SUBPROCESS_LITERAL.equals(implementation))
      {
         if (activity.getImplementationProcess() != null)
         {
            command.add(new DeleteValueCmd(activity, activity.getImplementationProcess(),
                  CWM_PKG.getActivityType_ImplementationProcess()));
         }
         if (activity.getSubProcessMode() != null)
         {
            command.add(new DeleteValueCmd(activity, activity.getSubProcessMode(),
                  CWM_PKG.getActivityType_SubProcessMode()));
         }
      }

      if (ActivityImplementationType.ROUTE_LITERAL.equals(implementation)
            || ActivityImplementationType.SUBPROCESS_LITERAL.equals(implementation)
            || ActivityImplementationType.APPLICATION_LITERAL.equals(implementation)
            && activity.getApplication() != null
            && !activity.getApplication().isInteractive())
      {
         if (activity.eIsSet(CWM_PKG.getActivityType_AllowsAbortByPerformer()))
         {
            command.add(new DeleteValueCmd(activity, activity.isAllowsAbortByPerformer(),
                  CWM_PKG.getActivityType_AllowsAbortByPerformer()));
         }
         if (activity.eIsSet(CWM_PKG.getActivityType_Performer()))
         {
            command.add(new DeleteValueCmd(activity, activity.getPerformer(), CWM_PKG
                  .getActivityType_Performer()));
            DiagramCommandFactory.INSTANCE.addDeleteConnectionCommands(command, activity,
                  CWM_PKG.getISymbolContainer_PerformsConnection());
         }
      }

      List contexts = ActivityUtil.getContextTypes(activity, implementation, null);
      Set contextIds = new HashSet();
      for (int i = 0; i < contexts.size(); i++)
      {
         ApplicationContextTypeType ctx = (ApplicationContextTypeType) contexts.get(i);
         contextIds.add(ctx.getId());
      }

      Set usedData = new HashSet();
      Set unusedData = new HashSet();
      List mappings2Delete = new ArrayList();
      List mappings = activity.getDataMapping();
      for (int i = 0; i < mappings.size(); i++)
      {
         DataMappingType mapping = (DataMappingType) mappings.get(i);
         if (contextIds.contains(mapping.getContext()))
         {
            usedData.add(mapping.getData());
         }
         else
         {
            unusedData.add(mapping.getData());
            mappings2Delete.add(mapping);
         }
      }
      unusedData.removeAll(usedData);
      if (!unusedData.isEmpty())
      {
         for (Iterator iterator = unusedData.iterator(); iterator.hasNext();)
         {
            DataType data = (DataType) iterator.next();
            DiagramCommandFactory.INSTANCE.addDeleteConnectionCommands(command, activity,
                  data, CWM_PKG.getISymbolContainer_DataMappingConnection());
         }
         if (!ActivityImplementationType.MANUAL_LITERAL.equals(activity
               .getImplementation())
               && !ActivityImplementationType.ROUTE_LITERAL.equals(activity
                     .getImplementation())
                        && !ActivityImplementationType.SUBPROCESS_LITERAL.equals(activity
                     .getImplementation())
               && !ActivityImplementationType.APPLICATION_LITERAL.equals(implementation))
         {
            // we need to delete mappings after deleting connections, otherwise, on undo
            // the
            // connection direction is lost
            for (int i = 0; i < mappings2Delete.size(); i++)
            {
               DataMappingType mapping = (DataMappingType) mappings.get(i);
               command.add(new DeleteValueCmd(activity, mapping, CWM_PKG
                     .getActivityType_DataMapping()));
            }
         }
      }
   }

   public static Command getSetImplementationCommand(ActivityImplementationType implType,
         ActivityType activity)
   {
      CompoundCommand command = new CompoundCommand();
      addUpdateDataMappingCommand(command, activity, implType);
      // todo: (fh) although now it's not used, better to pass a valid EditPart
      command.add(INSTANCE.getSetCommand(null, activity, CWM_PKG
            .getActivityType_Implementation(), implType));
      // depending on type, delete participant
      if(!activity.getImplementation().equals(implType) 
            && activity.getImplementation().equals(ActivityImplementationType.MANUAL_LITERAL))
      {
         command.add(new SetValueCmd(activity, CWM_PKG.getActivityType_Performer(), null));
      }
      return command;
   }

   private static void addUpdateDataMappingCommand(CompoundCommand command,
         ActivityType activity, ActivityImplementationType implType)
   {
      if (ActivityImplementationType.ROUTE_LITERAL.equals(activity.getImplementation())
            && (ActivityImplementationType.MANUAL_LITERAL).equals(implType))
      {
         for (Iterator iter = activity.getDataMapping().iterator(); iter.hasNext();)
         {
            DataMappingType dataMapping = (DataMappingType) iter.next();
            command.add(new DeleteValueCmd(dataMapping, CWM_PKG
                  .getDataMappingType_ApplicationAccessPoint()));
         }
      }
      else if (ActivityImplementationType.MANUAL_LITERAL.equals(activity
            .getImplementation())
            && (ActivityImplementationType.ROUTE_LITERAL).equals(implType))
      {
         for (Iterator iter = activity.getDataMapping().iterator(); iter.hasNext();)
         {
            DataMappingType dataMapping = (DataMappingType) iter.next();
            command.add(new SetValueCmd(dataMapping, CWM_PKG
                  .getDataMappingType_ApplicationAccessPoint(), dataMapping.getId()));
         }
      }
   }

   public static void addSetApplicationCommands(CompoundCommand command,
         ActivityType activity, ApplicationType newApplication,
         ApplicationType originalApplication, boolean updateDependenciesOnly)
   {
      if (!updateDependenciesOnly && newApplication != originalApplication)
      {
         command.add(new SetValueCmd(activity, CarnotWorkflowModelPackage.eINSTANCE
               .getActivityType_Application(), newApplication));
      }

      addUpdateConnectionsCommand(command, activity, newApplication, originalApplication,
            CWM_PKG.getActivitySymbolType_ExecutedByConnections(), CWM_PKG
                  .getISymbolContainer_ApplicationSymbol(), CWM_PKG
                  .getExecutedByConnectionType_ApplicationSymbol());
   }

   public static Command getSetSubprocessCommand(ActivityType activity,
         ProcessDefinitionType process)
   {
      CompoundCommand command = new CompoundCommand();
      command.add(new SetValueCmd(activity, CWM_PKG
            .getActivityType_ImplementationProcess(), process));
      return command;
   }
}
