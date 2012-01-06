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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.figures.anchors.TransitionConnectionAnchor;
import org.eclipse.stardust.modeling.core.ui.StringUtils;

public class ReloadConnectionCommandFactory
{
   private static final CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;

   public static final ReloadConnectionCommandFactory INSTANCE = new ReloadConnectionCommandFactory();

   public Command createReloadConnectionCmd(INodeSymbol symbol)
   {
      CompoundCommand command = new CompoundCommand();
      if (symbol instanceof GatewaySymbol)
      {
         GatewaySymbol gateway = (GatewaySymbol) symbol;
         ActivitySymbolType activitySymbol = gateway.getActivitySymbol();
         if (activitySymbol != null)
         {
            ActivityType activity = (ActivityType) activitySymbol.getModelElement();
            if (activity != null)
            {
               command.setLabel(gateway.getFlowKind().getName()
                     + Diagram_Messages.LB_GatewayOfActivity + activity.getId() + ')');
               reloadTransitions(command, activity, activitySymbol, gateway);
            }
         }
      }

      if (symbol instanceof ActivitySymbolType)
      {
         ActivitySymbolType activitySymbol = (ActivitySymbolType) symbol;
         ActivityType activity = (ActivityType) activitySymbol.getModelElement();
         if (activity != null)
         {
            setCommandLabels(command, activity, Diagram_Messages.LB_CMD_Activity);
            reloadTransitions(command, activity, activitySymbol, null);
            reloadConnections(command, activitySymbol, false, getDataSet(activity), PKG
                  .getISymbolContainer_DataSymbol(), PKG
                  .getISymbolContainer_ActivitySymbol(), PKG
                  .getISymbolContainer_DataMappingConnection(),
                  Diagram_Messages.CONN_NAME_DataMapping, PKG
                        .getDataMappingConnectionType());
                        
            if ((ActivityUtil.isApplicationActivity(activity) || 
                  DiagramPlugin.isBusinessPerspective())
                  && activity.getApplication() != null)
            {
               reloadConnections(command, activitySymbol, false, Collections
                     .singleton(activity.getApplication()), PKG
                     .getISymbolContainer_ApplicationSymbol(), PKG
                     .getISymbolContainer_ActivitySymbol(), PKG
                     .getISymbolContainer_ExecutedByConnection(),
                     Diagram_Messages.CONN_NAME_ExecutedBy, PKG
                           .getExecutedByConnectionType());
            }
            if ((ActivityUtil.isInteractive(activity) || 
                  DiagramPlugin.isBusinessPerspective())
                  && activity.getPerformer() != null)
            {
               reloadConnections(command, activitySymbol, false, Collections
                     .singleton(activity.getPerformer()),
                     getPerformerSymbolFeature(activity), PKG
                           .getISymbolContainer_ActivitySymbol(), PKG
                           .getISymbolContainer_PerformsConnection(),
                     Diagram_Messages.CONN_NAME_Performs, PKG.getPerformsConnectionType());
            }
         }
      }

      if (symbol instanceof StartEventSymbol)
      {
         StartEventSymbol eventSymbol = (StartEventSymbol) symbol;
         TriggerType trigger = eventSymbol.getTrigger();
         if (trigger != null)
         {
            setCommandLabels(command, trigger, Diagram_Messages.LB_CMD_Trigger);
            ProcessDefinitionType process = ModelUtils.findContainingProcess(eventSymbol);
            if (process != null)
            {
               IModelParticipant participant = getTriggerParticipant(trigger);
               if (participant instanceof RoleType || participant instanceof OrganizationType)
               {
                  reloadConnections(command, eventSymbol, false,
                     Collections.singleton(participant),
                     participant instanceof RoleType
                        ? PKG.getISymbolContainer_RoleSymbol()
                        : PKG.getISymbolContainer_OrganizationSymbol(),
                     PKG.getISymbolContainer_StartEventSymbols(),
                     PKG.getISymbolContainer_TriggersConnection(),
                     Diagram_Messages.CONN_NAME_Triggers,
                     PKG.getTriggersConnectionType());
               }
            }
         }
      }

      if (symbol instanceof DataSymbolType)
      {
         DataSymbolType dataSymbol = (DataSymbolType) symbol;
         DataType data = (DataType) dataSymbol.getModelElement();
         if (data != null)
         {
            setCommandLabels(command, data, Diagram_Messages.LB_CMD_Data);
            ProcessDefinitionType process = ModelUtils.findContainingProcess(dataSymbol);
            if (process != null)
            {
               reloadConnections(command, dataSymbol, true, getDataActivitySet(process,
                     data), PKG.getISymbolContainer_DataSymbol(), PKG
                     .getISymbolContainer_ActivitySymbol(), PKG
                     .getISymbolContainer_DataMappingConnection(),
                     Diagram_Messages.CONN_NAME_DataMapping, PKG
                           .getDataMappingConnectionType());
            }
         }
      }

      if (symbol instanceof ApplicationSymbolType)
      {
         ApplicationSymbolType applicationSymbol = (ApplicationSymbolType) symbol;
         ApplicationType application = (ApplicationType) applicationSymbol
               .getModelElement();
         if (application != null)
         {
            setCommandLabels(command, application, Diagram_Messages.LB_CMD_Application);
            ProcessDefinitionType process = ModelUtils
                  .findContainingProcess(applicationSymbol);
            if (process != null)
            {
               reloadConnections(command, applicationSymbol, true,
                     getApplicationActivitySet(process, application), PKG
                           .getISymbolContainer_ApplicationSymbol(), PKG
                           .getISymbolContainer_ActivitySymbol(), PKG
                           .getISymbolContainer_ExecutedByConnection(),
                     Diagram_Messages.CONN_NAME_ExecutedBy, PKG
                           .getExecutedByConnectionType());
            }
         }
      }

      if (symbol instanceof OrganizationSymbolType)
      {
         OrganizationSymbolType organizationSymbol = (OrganizationSymbolType) symbol;
         OrganizationType organization = (OrganizationType) organizationSymbol
               .getModelElement();
         if (organization != null)
         {
            setCommandLabels(command, organization, Diagram_Messages.LB_CMD_Organization);
            ProcessDefinitionType process = ModelUtils
                  .findContainingProcess(organizationSymbol);
            if (process != null)
            {
               reloadConnections(command, organizationSymbol, true,
                     getParticipantActivitySet(process, organization), PKG
                           .getISymbolContainer_OrganizationSymbol(), PKG
                           .getISymbolContainer_ActivitySymbol(), PKG
                           .getISymbolContainer_PerformsConnection(),
                     Diagram_Messages.CONN_NAME_Performs, PKG.getPerformsConnectionType());
               reloadConnections(command, organizationSymbol, true,
                     getParticipantTriggerSet(process, organization),
                     PKG.getISymbolContainer_OrganizationSymbol(),
                     PKG.getISymbolContainer_StartEventSymbols(),
                     PKG.getISymbolContainer_TriggersConnection(),
                     Diagram_Messages.CONN_NAME_Triggers,
                     PKG.getTriggersConnectionType());
            }
            Set<IModelParticipant> members = getOrganizationMemberSet(organization);
            reloadConnections(command, organizationSymbol, false, members, PKG
                  .getISymbolContainer_RoleSymbol(), PKG
                  .getISymbolContainer_OrganizationSymbol(), PKG
                  .getISymbolContainer_WorksForConnection(),
                  Diagram_Messages.CONN_NAME_WorksFor, PKG.getWorksForConnectionType());
            if (null != organization.getTeamLead())
            {
               reloadConnections(command, organizationSymbol, false,
                     Collections.singleton(organization.getTeamLead()),
                     PKG.getISymbolContainer_RoleSymbol(),
                     PKG.getISymbolContainer_OrganizationSymbol(),
                     PKG.getISymbolContainer_TeamLeadConnection(),
                     Diagram_Messages.CONN_NAME_TeamLead,
                     PKG.getTeamLeadConnectionType());
            }
            reloadConnections(command, organizationSymbol, false, members, PKG
                  .getISymbolContainer_OrganizationSymbol(), PKG
                  .getISymbolContainer_OrganizationSymbol(), PKG
                  .getISymbolContainer_PartOfConnection(),
                  Diagram_Messages.CONN_NAME_PartOf, PKG.getPartOfConnectionType());
            ModelType modelType = ModelUtils.findContainingModel(organizationSymbol);
            if (modelType != null)
            {
               reloadConnections(command, organizationSymbol, true,
                     getParentOrganizationSet(modelType, organization), PKG
                           .getISymbolContainer_OrganizationSymbol(), PKG
                           .getISymbolContainer_OrganizationSymbol(), PKG
                           .getISymbolContainer_PartOfConnection(),
                     Diagram_Messages.CONN_NAME_PartOf, PKG.getPartOfConnectionType());
            }
         }
      }

      if (symbol instanceof RoleSymbolType)
      {
         RoleSymbolType roleSymbol = (RoleSymbolType) symbol;
         RoleType role = (RoleType) roleSymbol.getModelElement();
         if (role != null)
         {
            setCommandLabels(command, role, Diagram_Messages.LB_CMD_Role);
            ProcessDefinitionType process = ModelUtils.findContainingProcess(roleSymbol);
            if (process != null)
            {
               reloadConnections(command, roleSymbol, true, getParticipantActivitySet(
                     process, role), PKG.getISymbolContainer_RoleSymbol(), PKG
                     .getISymbolContainer_ActivitySymbol(), PKG
                     .getISymbolContainer_PerformsConnection(),
                     Diagram_Messages.CONN_NAME_Performs, PKG.getPerformsConnectionType());
               reloadConnections(command, roleSymbol, true,
                     getParticipantTriggerSet(process, role),
                     PKG.getISymbolContainer_RoleSymbol(),
                     PKG.getISymbolContainer_StartEventSymbols(),
                     PKG.getISymbolContainer_TriggersConnection(),
                     Diagram_Messages.CONN_NAME_Triggers,
                     PKG.getTriggersConnectionType());
            }
            ModelType modelType = ModelUtils.findContainingModel(roleSymbol);
            if (modelType != null)
            {
               reloadConnections(command, roleSymbol, true, getParentOrganizationSet(
                     modelType, role), PKG.getISymbolContainer_RoleSymbol(), PKG
                     .getISymbolContainer_OrganizationSymbol(), PKG
                     .getISymbolContainer_WorksForConnection(),
                     Diagram_Messages.CONN_NAME_WorksFor, PKG.getWorksForConnectionType());
               reloadConnections(command, roleSymbol, true, new HashSet<OrganizationType>(role.getTeams()),
                     PKG.getISymbolContainer_RoleSymbol(),
                     PKG.getISymbolContainer_OrganizationSymbol(),
                     PKG.getISymbolContainer_TeamLeadConnection(),
                     Diagram_Messages.CONN_NAME_TeamLead, PKG.getTeamLeadConnectionType());
            }
         }
      }

      if (symbol instanceof ConditionalPerformerSymbolType)
      {
         ConditionalPerformerSymbolType conditionalPerformerSymbol = (ConditionalPerformerSymbolType) symbol;
         ConditionalPerformerType conditionalPerformer = (ConditionalPerformerType) conditionalPerformerSymbol
               .getModelElement();
         if (conditionalPerformer != null)
         {
            setCommandLabels(command, conditionalPerformer,
                  Diagram_Messages.LB_CMD_ConditionalPerformer);
            ProcessDefinitionType process = ModelUtils
                  .findContainingProcess(conditionalPerformerSymbol);
            if (process != null)
            {
               reloadConnections(command, conditionalPerformerSymbol, true,
                     getParticipantActivitySet(process, conditionalPerformer), PKG
                           .getISymbolContainer_ConditionalPerformerSymbol(), PKG
                           .getISymbolContainer_ActivitySymbol(), PKG
                           .getISymbolContainer_PerformsConnection(),
                     Diagram_Messages.CONN_NAME_Performs, PKG.getPerformsConnectionType());
            }
         }
      }
      return command;
   }

   private Set<TriggerType> getParticipantTriggerSet(
         ProcessDefinitionType process, IModelParticipant participant)
   {
      Set<TriggerType> participants = CollectionUtils.newSet();
      for (TriggerType trigger : process.getTrigger())
      {
         String triggerTypeId = trigger.getType().getId();
         if (PredefinedConstants.MANUAL_TRIGGER.equals(triggerTypeId)
               || PredefinedConstants.SCAN_TRIGGER.equals(triggerTypeId))
         {
            if (participant == AttributeUtil.getIdentifiable(trigger, PredefinedConstants.PARTICIPANT_ATT))
            {
               participants.add(trigger);
            }
         }
      }
      return participants;
   }

   private IModelParticipant getTriggerParticipant(TriggerType trigger)
   {
      String triggerTypeId = trigger.getType().getId();
      if (PredefinedConstants.MANUAL_TRIGGER.equals(triggerTypeId) || PredefinedConstants.SCAN_TRIGGER.equals(triggerTypeId))
      {
         return (IModelParticipant) AttributeUtil.getIdentifiable(trigger, PredefinedConstants.PARTICIPANT_ATT);
      }
      return null;
   }

   public Command createReloadConnectionCmd(List<INodeSymbol> symbols)
   {
      CompoundCommand command = new CompoundCommand();
      for (INodeSymbol symbol : symbols)
      {
         command.add(createReloadConnectionCmd(symbol));
      }
      return command;
   }

   private void setCommandLabels(CompoundCommand command,
         IIdentifiableModelElement identifiable, String label)
   {
      command.setLabel(StringUtils.isEmpty(identifiable.getName()) ? label
            + ": " + identifiable.getElementOid() : identifiable.getName() //$NON-NLS-1$
            + " (" + identifiable.getId() + ')'); //$NON-NLS-1$
   }

   private Set<IModelParticipant> getOrganizationMemberSet(OrganizationType organization)
   {
      Set<IModelParticipant> set = CollectionUtils.newSet();
      for (ParticipantType participant : organization.getParticipant())
      {
         set.add(participant.getParticipant());
      }
      return set;
   }

   private Set<OrganizationType> getParentOrganizationSet(ModelType modelType, IModelParticipant role)
   {
      Set<OrganizationType> set = CollectionUtils.newSet();
      for (OrganizationType organization : modelType.getOrganization())
      {
         for (ParticipantType participant : organization.getParticipant())
         {
            if (role.equals(participant.getParticipant()))
            {
               set.add(organization);
            }
         }
      }
      return set;
   }

   private EStructuralFeature getPerformerSymbolFeature(ActivityType activity)
   {
      IModelParticipant performer = activity.getPerformer();
      if (performer instanceof ConditionalPerformerType)
      {
         return PKG.getISymbolContainer_ConditionalPerformerSymbol();
      }
      else if (performer instanceof OrganizationType)
      {
         return PKG.getISymbolContainer_OrganizationSymbol();
      }
      else if (performer instanceof RoleType)
      {
         return PKG.getISymbolContainer_RoleSymbol();
      }
      return null;
   }

   private void reloadConnections(CompoundCommand command,
         IModelElementNodeSymbol symbol, boolean outgoing, Set<? extends IIdentifiableModelElement> set,
         EStructuralFeature sourceFeature, EStructuralFeature targetFeature,
         EStructuralFeature connectionFeature, String name, EClass connectionEClass)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram(symbol);
      EObject parentContainer = symbol.eContainer();

      // connection symbols are not inside lanes, so we don't check lanes for connection symbols
      ISymbolContainer symbolContainer = (ISymbolContainer) parentContainer;
      if (parentContainer != null)
      {
         if (parentContainer instanceof LaneSymbol)
         {
            PoolSymbol pool = DiagramUtil.getDefaultPool(diagram);
            if (pool != null)
            {
               symbolContainer = pool;
            }
            else
            {
               symbolContainer = diagram;
            }            
         }
      }

      /*
      ISymbolContainer symbolContainer = parentContainer == null
            ? null
            : (ISymbolContainer) (parentContainer.equals(DiagramUtil
                  .getDefaultPool(diagram))
                  || parentContainer instanceof LaneSymbol ? parentContainer : diagram);
      */

      if (null != symbolContainer)
      {
         for (IIdentifiableModelElement element : set)
         {
            IModelElementNodeSymbol sourceSymbol = null;
            IModelElementNodeSymbol targetSymbol = null;

            if (outgoing)
            {
               sourceSymbol = symbol;
               targetSymbol = (IModelElementNodeSymbol) DiagramUtil.getClosestSymbol(
                     symbol, targetFeature, element);
            }
            else
            {
               targetSymbol = symbol;
               sourceSymbol = (IModelElementNodeSymbol) DiagramUtil.getClosestSymbol(
                     symbol, sourceFeature, element);
            }

            if (targetSymbol == null || sourceSymbol == null)
            {
               continue;
            }

            boolean found = false;
            @SuppressWarnings("unchecked")
            List<IConnectionSymbol> connection = (List<IConnectionSymbol>) symbolContainer.eGet(connectionFeature);
            for (IConnectionSymbol type : connection)
            {
               if (targetSymbol.equals(type.getTargetNode())
                     && sourceSymbol.equals(type.getSourceNode()))
               {
                  found = true;
                  break;
               }
            }

            if (!found && !sourceSymbol.equals(targetSymbol))
            {
               IdFactory idFactory = new IdFactory(name, name, Long.toString(sourceSymbol
                     .getElementOid())
                     + ":" + //$NON-NLS-1$
                     Long.toString(targetSymbol.getElementOid()));
               CreateConnectionSymbolCommand cmd = new CreateConnectionSymbolCommand(
                     idFactory, connectionEClass);
               cmd.setParent(symbolContainer);
               cmd.setSourceSymbol(sourceSymbol);
               cmd.setTargetSymbol(targetSymbol);
               cmd.setLabel(name + Diagram_Messages.LB_CMD_P1_OfSource
                     + sourceSymbol.getModelElement().getId()
                     + Diagram_Messages.LB_CMD_P2_Target
                     + targetSymbol.getModelElement().getId() + ')');
               command.add(cmd);
            }
         }
      }
   }

   private Set<ActivityType> getParticipantActivitySet(ProcessDefinitionType process,
         IModelParticipant participant)
   {
      Set<ActivityType> activitySet = CollectionUtils.newSet();
      for (ActivityType activity : process.getActivity())
      {
         if ((ActivityUtil.isInteractive(activity)
               || DiagramPlugin.isBusinessPerspective())
               && participant.equals(activity.getPerformer()))
         {
            activitySet.add(activity);
         }
      }
      return activitySet;
   }

   private Set<ActivityType> getApplicationActivitySet(ProcessDefinitionType process,
         ApplicationType application)
   {
      Set<ActivityType> activitySet = CollectionUtils.newSet();
      for (ActivityType activity : process.getActivity())
      {
         if ((ActivityUtil.isApplicationActivity(activity) ||
               DiagramPlugin.isBusinessPerspective())
               && application.equals(activity.getApplication()))
         {
            activitySet.add(activity);
         }
      }
      return activitySet;
   }

   private Set<DataType> getDataSet(ActivityType activity)
   {
      Set<DataType> dataSet = CollectionUtils.newSet();
      for (DataMappingType dataMapping : activity.getDataMapping())
      {
         if (null != dataMapping.getData())
         {
            dataSet.add(dataMapping.getData());
         }
      }
      return dataSet;
   }

   private Set<ActivityType> getDataActivitySet(ProcessDefinitionType process, DataType data)
   {
      Set<ActivityType> activitySet = CollectionUtils.newSet();
      for (ActivityType activity : process.getActivity())
      {
         for (DataMappingType dataMapping : activity.getDataMapping())
         {
            if (data.equals(dataMapping.getData()))
            {
               activitySet.add(activity);
               break;
            }
         }
      }
      return activitySet;
   }

   private void reloadTransitions(CompoundCommand command, ActivityType activity,
         ActivitySymbolType activitySymbol, GatewaySymbol gatewaySymbol)
   {
      if (gatewaySymbol != null)
      {
         reloadGatewayTransition(command, activitySymbol, gatewaySymbol);
      }
      else
      {
         if (activity.getSplit() != null
               && activity.getSplit().getValue() != JoinSplitType.NONE)
         {
            GatewaySymbol gateway = findGateway(activitySymbol,
                  FlowControlType.SPLIT_LITERAL);
            reloadGatewayTransition(command, activitySymbol, gateway);
         }
         if (activity.getJoin() != null
               && activity.getJoin().getValue() != JoinSplitType.NONE)
         {
            GatewaySymbol gateway = findGateway(activitySymbol,
                  FlowControlType.JOIN_LITERAL);
            reloadGatewayTransition(command, activitySymbol, gateway);
         }
      }

      ProcessDefinitionType process = (ProcessDefinitionType) activity.eContainer();
      if(process == null)
      {
         return;
      }
      for (TransitionType transition : process.getTransition())
      {
         boolean found = false;
         List<TransitionConnectionType> connections = null;
         ActivityType sourceActivity = null;
         ActivityType targetActivity = null;
         IFlowObjectSymbol sourceSymbol = null;
         IFlowObjectSymbol targetSymbol = null;

         if (transition.getFrom() != null && transition.getFrom().equals(activity))
         {
            sourceActivity = activity;
            sourceSymbol = getFlowSymbol(sourceActivity, activitySymbol, true);
            if (sourceSymbol == null || gatewaySymbol != null
                  && gatewaySymbol != sourceSymbol)
            {
               continue;
            }
            connections = sourceSymbol.getOutTransitions();
         }
         else if (transition.getTo() != null && transition.getTo().equals(activity))
         {
            targetActivity = activity;
            targetSymbol = getFlowSymbol(targetActivity, activitySymbol, false);
            if (targetSymbol == null || gatewaySymbol != null
                  && gatewaySymbol != targetSymbol)
            {
               continue;
            }
            connections = targetSymbol.getInTransitions();
         }
         else
         {
            // not a transition involving our activity
            continue;
         }

         if (connections != null)
         {
            for (TransitionConnectionType connection : connections)
            {
               if (transition.equals(connection.getTransition()))
               {
                  found = true;
                  break;
               }
            }
         }

         if (!found)
         {
            if (sourceActivity == null)
            {
               sourceActivity = transition.getFrom();
            }
            if (targetActivity == null)
            {
               targetActivity = transition.getTo();
            }
            if (sourceSymbol == null)
            {
               sourceSymbol = findClosestFlowObject(targetSymbol, sourceActivity, true);
            }
            if (targetSymbol == null)
            {
               targetSymbol = findClosestFlowObject(sourceSymbol, targetActivity, false);
            }

            if (sourceSymbol != null && targetSymbol != null
                  && !sourceSymbol.equals(targetSymbol))
            {
               IdFactory id = new IdFactory("TransitionConnection", //$NON-NLS-1$
                     Diagram_Messages.BASENAME_TransitionConnection, transition);
               DiagramType diagram = ModelUtils.findContainingDiagram(sourceSymbol);
               CreateConnectionSymbolCommand cmd = new CreateConnectionSymbolCommand(id,
                     CarnotWorkflowModelPackage.eINSTANCE.getTransitionConnectionType())
               {
                  protected IModelElement createModelElement()
                  {
                     TransitionConnectionType connection = (TransitionConnectionType) super
                           .createModelElement();
                     connection.setTransition((TransitionType) getIdFactory()
                           .getReferingElement());
                     return connection;
                  }
               };
               cmd.setParent(diagram);
               cmd.setSourceSymbol(sourceSymbol);
               cmd.setTargetSymbol(targetSymbol);
               cmd.setLabel(Diagram_Messages.LB_CMD_TransitionOfSource
                     + sourceActivity.getId() + Diagram_Messages.LB_CMD_P2_Target
                     + targetActivity.getId() + ')');
               command.add(cmd);
            }
         }
      }
   }

   private void reloadGatewayTransition(CompoundCommand command,
         ActivitySymbolType activitySymbol, GatewaySymbol gatewaySymbol)
   {
      if (activitySymbol != null && gatewaySymbol != null)
      {
         boolean outgoing = gatewaySymbol.getFlowKind() == FlowControlType.SPLIT_LITERAL;
         boolean found = false;
         for (TransitionConnectionType connection : outgoing
               ? gatewaySymbol.getInTransitions() : gatewaySymbol.getOutTransitions())
         {
            IFlowObjectSymbol other = outgoing
                  ? connection.getSourceActivitySymbol()
                  : connection.getTargetActivitySymbol();
            if (activitySymbol == other)
            {
               found = true;
               break;
            }
         }
         if (!found)
         {
            IdFactory id = new IdFactory("TransitionConnection", //$NON-NLS-1$
                  Diagram_Messages.BASENAME_TransitionConnection);
            DiagramType diagram = ModelUtils.findContainingDiagram(activitySymbol);
            CreateConnectionSymbolCommand cmd = new CreateConnectionSymbolCommand(id,
                  CarnotWorkflowModelPackage.eINSTANCE.getTransitionConnectionType());
            cmd.setParent(diagram);
            cmd.setSourceAnchorType(TransitionConnectionAnchor.CENTER);
            cmd.setTargetAnchorType(TransitionConnectionAnchor.CENTER);
            cmd.setSourceSymbol(outgoing ? (INodeSymbol) activitySymbol : gatewaySymbol);
            cmd.setTargetSymbol(outgoing ? (INodeSymbol) gatewaySymbol : activitySymbol);
            cmd.setLabel(Diagram_Messages.LB_CMD_GatewayConnectionOf
                  + Diagram_Messages.LB_CMD_P1_source
                  + cmd.getSourceSymbol().getElementOid() + ", " + //$NON-NLS-1$
                  Diagram_Messages.LB_CMD_P2_target
                  + cmd.getTargetSymbol().getElementOid() + ')');
            command.add(cmd);
         }
      }
   }

   private IFlowObjectSymbol findClosestFlowObject(IFlowObjectSymbol reference,
         ActivityType activity, boolean outgoing)
   {
      ActivitySymbolType target = (ActivitySymbolType) DiagramUtil.getClosestSymbol(reference,
            CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ActivitySymbol(), activity);
      return getFlowSymbol(activity, target, outgoing);
   }

   private IFlowObjectSymbol getFlowSymbol(ActivityType activity,
         ActivitySymbolType symbol, boolean outgoing)
   {
      GatewaySymbol gateway = null;
      if (symbol != null)
      {
         if (outgoing)
         {
            if (activity.getSplit() != JoinSplitType.NONE_LITERAL)
            {
               gateway = findGateway(symbol, FlowControlType.SPLIT_LITERAL);
            }
         }
         else if (activity.getJoin() != JoinSplitType.NONE_LITERAL)
         {
            gateway = findGateway(symbol, FlowControlType.JOIN_LITERAL);
         }
      }
      return gateway == null ? (IFlowObjectSymbol) symbol : gateway;
   }

   private GatewaySymbol findGateway(ActivitySymbolType target, FlowControlType flow)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram(target);
      if (null != diagram)
      {
         List<GatewaySymbol> gateways = DiagramUtil.getSymbols(diagram,
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_GatewaySymbol(),
               null);
         for (GatewaySymbol gateway : gateways)
         {
            if (flow.equals(gateway.getFlowKind())
                  && gateway.getActivitySymbol() != null
                  && gateway.getActivitySymbol().equals(target))
            {
               return gateway;
            }
         }
      }
      return null;
   }
}