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
package org.eclipse.stardust.modeling.validation.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class DiagramValidator implements IModelElementValidator
{
   private static final String[] messages = {
         Validation_Messages.MSG_DIAGRAM_DiagramNameIsEmpty,
         Validation_Messages.MSG_DIAGRAM_NoRequiredGatewaySymbols,
         Validation_Messages.MSG_DIAGRAM_MultipleOutgoingTransitions,
         Validation_Messages.MSG_DIAGRAM_InvalidConnectionTarget,
         Validation_Messages.MSG_DIAGRAM_NoJoinTypeNONE,
         Validation_Messages.MSG_DIAGRAM_MultipleIncomingTransitions,
         Validation_Messages.MSG_DIAGRAM_InvalidConnectionSource,
         Validation_Messages.MSG_DIAGRAM_NoSplitTypeNONE,};

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();

      DiagramType diagram = (DiagramType) element;

      if (StringUtils.isEmpty(diagram.getName()))
      {
         result.add(Issue.warning(diagram, getMessage(diagram, 0),
               ValidationService.PKG_CWM.getDiagramType_Name()));
      }

      if (needsUpdate(diagram))
      {
         result.add(Issue.warning(diagram, getMessage(diagram, 1)));
      }

      checkStartEvents(result, diagram);
      checkEndEvents(result, diagram);

      checkConnectionSymbols(result, diagram);

      ValidationService vs = ValidationService.getInstance();

      result.addAll(Arrays.asList(vs.validateModelElements(diagram.getPoolSymbols())));
      // validate start event symbols
      result.addAll(Arrays.asList(vs.validateModelElements(
            DiagramUtil.getSymbols(diagram, 
                  ValidationService.PKG_CWM.getISymbolContainer_StartEventSymbols(), null))));
      
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private void checkConnectionSymbols(List<Issue> result, DiagramType diagram)
   {
      checkConnectionSymbols(result, diagram, ValidationService.PKG_CWM
            .getISymbolContainer_DataMappingConnection(),
            Validation_Messages.MSG_NoDataMappingSymbols);
      checkConnectionSymbols(result, diagram, ValidationService.PKG_CWM
            .getISymbolContainer_TransitionConnection(),
            Validation_Messages.MSG_NoTransitionSymbols);
      checkConnectionSymbols(result, diagram, ValidationService.PKG_CWM
            .getISymbolContainer_ExecutedByConnection(),
            Validation_Messages.MSG_NoExecutedBySymbols);
      checkConnectionSymbols(result, diagram, ValidationService.PKG_CWM
            .getISymbolContainer_PerformsConnection(),
            Validation_Messages.MSG_NoPerformsSymbols);
      checkConnectionSymbols(result, diagram, ValidationService.PKG_CWM
            .getISymbolContainer_WorksForConnection(),
            Validation_Messages.MSG_NoWorksForSymbols);
      checkConnectionSymbols(result, diagram, ValidationService.PKG_CWM
            .getISymbolContainer_RefersToConnection(),
            Validation_Messages.MSG_NoRefersToSymbols);
      checkConnectionSymbols(result, diagram, ValidationService.PKG_CWM
            .getISymbolContainer_TeamLeadConnection(), "fdfdsfsd"); //$NON-NLS-1$
   }

   private void checkConnectionSymbols(List<Issue> result, DiagramType diagram,
         EStructuralFeature feature, String msg)
   {
      List<IConnectionSymbol> connections = new ArrayList<IConnectionSymbol>();
      connections.addAll((Collection) diagram.eGet(feature));
      for (Iterator iter = diagram.getPoolSymbols().iterator(); iter.hasNext();)
      {
         PoolSymbol poolSymbol = (PoolSymbol) iter.next();
         connections.addAll((Collection) poolSymbol.eGet(feature));
         for (Iterator iterator = poolSymbol.getLanes().iterator(); iterator.hasNext();)
         {
            LaneSymbol laneSymbol = (LaneSymbol) iterator.next();
            connections.addAll((Collection) laneSymbol.eGet(feature));
         }
      }
      for (Iterator iter = connections.iterator(); iter.hasNext();)
      {
         IConnectionSymbol connection = (IConnectionSymbol) iter.next();
         if (connection.getSourceNode() == null || connection.getTargetNode() == null)
         {
            result.add(Issue.warning(connection, msg, ValidationService.PKG_CWM
                  .getIConnectionSymbol()));
         }
         
         if(connection instanceof TeamLeadConnectionType)            
         {
            OrganizationSymbolType target = (OrganizationSymbolType) ((TeamLeadConnectionType) connection).getTargetNode();
            OrganizationType organization = target.getOrganization();            
            
            if(organization.getTeamLead() == null)
            {
               result.add(Issue.error(organization, Validation_Messages.ERR_Invalid_TeamLeadConnection, ValidationService.PKG_CWM.getTeamLeadConnectionType_TeamLeadSymbol()));               
            }
         }
      }
   }

   private void checkStartEvents(List result, DiagramType diagram)
   {
      List startEvents = DiagramUtil.getSymbols(diagram, ValidationService.PKG_CWM
            .getISymbolContainer_StartEventSymbols(), null);
      for (int i = 0; i < startEvents.size(); i++)
      {
         StartEventSymbol eventSymbol = (StartEventSymbol) startEvents.get(i);
         List transitions = eventSymbol.getOutTransitions();
         if (transitions.size() > 1)
         {
            result.add(Issue.warning(eventSymbol, getMessage(eventSymbol, 2)));
         }
         for (int j = 0; j < transitions.size(); j++)
         {
            TransitionConnectionType connection = (TransitionConnectionType) transitions
                  .get(j);
            ActivityType activity = getActivity(connection.getTargetActivitySymbol());
            if (activity == null)
            {
               result.add(Issue.warning(connection, getMessage(eventSymbol, 3)));
            }
            else if (activity.getJoin() != null
                  && activity.getJoin().getValue() != JoinSplitType.NONE)
            {
               result.add(Issue.warning(activity, getMessage(eventSymbol, 4)));
            }
         }
      }
   }

   private void checkEndEvents(List result, DiagramType diagram)
   {
      List endEvents = DiagramUtil.getSymbols(diagram, ValidationService.PKG_CWM
            .getISymbolContainer_EndEventSymbols(), null);
      for (int i = 0; i < endEvents.size(); i++)
      {
         EndEventSymbol eventSymbol = (EndEventSymbol) endEvents.get(i);
         List transitions = eventSymbol.getInTransitions();
         if (transitions.size() > 1)
         {
            result.add(Issue.warning(eventSymbol, getMessage(eventSymbol, 5)));
         }
         for (int j = 0; j < transitions.size(); j++)
         {
            TransitionConnectionType connection = (TransitionConnectionType) transitions
                  .get(j);
            ActivityType activity = getActivity(connection.getSourceActivitySymbol());
            if (activity == null)
            {
               result.add(Issue.warning(connection, getMessage(eventSymbol, 6)));
            }
            else if (activity.getSplit() != null
                  && activity.getSplit().getValue() != JoinSplitType.NONE)
            {
               result.add(Issue.warning(activity, getMessage(eventSymbol, 7)));
            }
         }
      }
   }

   private ActivityType getActivity(IFlowObjectSymbol symbol)
   {
      if (symbol instanceof GatewaySymbol)
      {
         symbol = ((GatewaySymbol) symbol).getActivitySymbol();
      }
      if (symbol instanceof ActivitySymbolType)
      {
         return ((ActivitySymbolType) symbol).getActivity();
      }
      return null;
   }

   private boolean needsUpdate(DiagramType diagram)
   {
      List<ActivitySymbolType> activitySymbols = DiagramUtil.getSymbols(diagram,
            CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ActivitySymbol(),
            null);
      for (int i = 0; i < activitySymbols.size(); i++)
      {
         ActivitySymbolType symbol = (ActivitySymbolType) activitySymbols.get(i);
         ActivityType activity = symbol.getActivity();
         if (activity != null)
         {
            if (activity.getJoin() != JoinSplitType.NONE_LITERAL
                  && !hasGateway(symbol, FlowControlType.JOIN_LITERAL))
            {
               return true;
            }
            if (activity.getSplit() != JoinSplitType.NONE_LITERAL
                  && !hasGateway(symbol, FlowControlType.SPLIT_LITERAL))
            {
               return true;
            }
         }
      }
      return false;
   }

   private boolean hasGateway(ActivitySymbolType target, FlowControlType flow)
   {
      List<GatewaySymbol> gateways = target.getGatewaySymbols();
      for (int i = 0; i < gateways.size(); i++)
      {
         GatewaySymbol gateway = (GatewaySymbol) gateways.get(i);
         if (flow.equals(gateway.getFlowKind()))
         {
            return true;
         }
      }
      return false;
   }

   private String getMessage(Object source, int i)
   {
      String msg = messages[i];
      Object[] args = getArguments(source);
      return MessageFormat.format(msg, args);
   }

   private Object[] getArguments(Object source)
   {
      if (source instanceof DiagramType)
      {
         DiagramType diagram = (DiagramType) source;
         return new Object[] {Long.toString(diagram.getElementOid()), diagram.getName()};
      }
      else if (source instanceof AbstractEventSymbol)
      {
         AbstractEventSymbol event = (AbstractEventSymbol) source;
         return new Object[] {
               Long.toString(event.getElementOid()),
               event instanceof StartEventSymbol ? ((StartEventSymbol) event)
                     .getTrigger() == null ? "StartEvent" //$NON-NLS-1$
                     : ((StartEventSymbol) event).getTrigger().getName() : "EndEvent" //$NON-NLS-1$
         };

      }
      return new Object[0];
   }
}