/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.modeling.core.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreateModelElementUtil;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CommandUtils;

public class ConvertGatewayUtil
{
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;   
   
   private EObject element;
   private boolean modified = false;
   private ModelType model;

   public boolean isModified()
   {
      return modified;
   }

   public ConvertGatewayUtil(EObject element)
   {
      this.element = element;
      model = ModelUtils.findContainingModel(element);
   }

   public void convert()
   {
      if(element instanceof ProcessDefinitionType)
      {
         convertGateway((ProcessDefinitionType) element);
      }
      else 
      {
         for (ProcessDefinitionType process : ((ModelType) element).getProcessDefinition())
         {
            convertGateway(process);
         }
      }
   }

   private void convertGateway(ProcessDefinitionType process)
   {
      ActivityType startActivity = findStartActivity(process);
      
      Set<ActivityType> allActivities = CollectionUtils.newSet();
      allActivities.addAll(process.getActivity());

      List<ActivityType> reachedActivities = CollectionUtils.newLinkedList();
      Set<ActivityType> visitedActivities = CollectionUtils.newSet();
      reachedActivities.add(startActivity);

      while (!reachedActivities.isEmpty())
      {
         ActivityType activity = (ActivityType) reachedActivities.remove(0);
         if (!visitedActivities.contains(activity))
         {
            visitedActivities.add(activity);
            for (TransitionType transition : activity.getOutTransitions())
            {
               if ( !visitedActivities.contains(transition.getTo()))
               {
                  reachedActivities.add(transition.getTo());
               }
            }
            
            String activityId = activity.getId();            
            JoinSplitType join = activity.getJoin();
            JoinSplitType split = activity.getSplit();
            if(!StringUtils.isEmpty(activityId)
                  && activityId.matches("^(?i)gateway.*"))
            {
            }
            else
            {
               if(!split.equals(JoinSplitType.NONE_LITERAL))
               {
                  addGatewayActivity(activity, split, true);
               }
               if(!join.equals(JoinSplitType.NONE_LITERAL))
               {
                  // same, so we can use one method with a flag
                  addGatewayActivity(activity, join, false);                  
               }               
            }            
         }
      }      
   }   

   private void addGatewayActivity(ActivityType activity, JoinSplitType type, boolean isSplit)
   {      
      modified = true;
      IdFactory idFactoryTransition = new IdFactory(Diagram_Messages.BASENAME_Transition, 
            Diagram_Messages.BASENAME_Transition);
      IdFactory idFactoryActivity = new IdFactory("gateway", "gateway");      
      
      ProcessDefinitionType process = (ProcessDefinitionType) activity.eContainer();
      TransitionType newTransition = (TransitionType) CreateModelElementUtil.createModelElement(idFactoryTransition, PKG.getTransitionType(), process, model);      
      ActivityType newActivity = (ActivityType) CreateModelElementUtil.createModelElement(idFactoryActivity, PKG.getActivityType(), process, model);
      
      process.getActivity().add(newActivity);
      process.getTransition().add(newTransition);
      
      if(isSplit)
      {
         removeGatewaySymbols(activity, newActivity, isSplit);
                           
         EList<TransitionType> outTransitions = activity.getOutTransitions();
         List<TransitionType> transitions = new ArrayList<TransitionType>();
         transitions.addAll(outTransitions);
         
         for(TransitionType transition : transitions)
         {
            transition.setFrom(newActivity);
         }

         newTransition.setFrom(activity);
         newTransition.setTo(newActivity);
         
         activity.setSplit(JoinSplitType.NONE_LITERAL);
         newActivity.setSplit(type);
         newActivity.setJoin(type);
      }
      else
      {
         removeGatewaySymbols(activity, newActivity, isSplit);
                  
         EList<TransitionType> inTransitions = activity.getInTransitions();
         for(TransitionType transition : inTransitions)
         {
            transition.setTo(newActivity);
         }

         newTransition.setFrom(newActivity);
         newTransition.setTo(activity);         

         activity.setJoin(JoinSplitType.NONE_LITERAL);
         newActivity.setSplit(type);
         newActivity.setJoin(type);         
      }      
      createSymbols(activity, newActivity);      
   }

   private void removeGatewaySymbols(ActivityType activity, ActivityType newActivity, boolean isSplit)
   {
      for(INodeSymbol symbol : activity.getSymbols())
      {
         List<INodeSymbol> remove = new ArrayList<INodeSymbol>();         
         List<GatewaySymbol> gateways = ((ActivitySymbolType) symbol).getGatewaySymbols();      
         for (GatewaySymbol gateway : gateways)
         {
            if(isSplit && FlowControlType.SPLIT_LITERAL.equals(gateway.getFlowKind()))
            {
               remove.add(gateway);
            }
            else if(!isSplit && FlowControlType.JOIN_LITERAL.equals(gateway.getFlowKind()))
            {
               remove.add(gateway);               
            }            
         }
         for(INodeSymbol gatewaySymbol : remove)
         {         
            ISymbolContainer container = (ISymbolContainer) gatewaySymbol.eContainer();
            ISymbolContainer  connectionContainer = ModelUtils.findContainingPool(container);            
            
            List<IConnectionSymbol> connections = new ArrayList<IConnectionSymbol>();
            for(Iterator iter = connectionContainer.getConnections().valueListIterator(); iter.hasNext();)
            {
               IConnectionSymbol connection = (IConnectionSymbol) iter.next();
               if(connection.getSourceNode() != null && connection.getSourceNode().equals(gatewaySymbol)
                     || connection.getTargetNode() != null && connection.getTargetNode().equals(gatewaySymbol))
               {
                  connections.add(connection);
               }
            }

            for(IConnectionSymbol connection : connections)
            {
               connection.setSourceNode(null);
               connection.setTargetNode(null);               
               
               Object ref = ((EObject) connectionContainer).eGet(connection.eContainingFeature());
               if (ref instanceof List)
               {
                  ((List) ref).remove(connection);
               }
               else
               {
                  // container.eUnset(connection.eContainingFeature());
               }
            }
            
            ((GatewaySymbol) gatewaySymbol).setActivitySymbol(null);
            ((ActivitySymbolType) symbol).getGatewaySymbols().remove(gatewaySymbol);            
            container.getGatewaySymbol().remove(gatewaySymbol);
         }
      }
   }
      
   private void createSymbols(ActivityType activity, ActivityType newActivity)
   {
      int distance = 100;
      
      for(INodeSymbol symbol : activity.getSymbols())
      {
         int height = symbol.getHeight();
         int width = symbol.getWidth();
         
         ISymbolContainer activitySymbolContainer = (ISymbolContainer) symbol.eContainer();   
      
         IdFactory idFactoryGateway = new IdFactory("Gateway", Diagram_Messages.BASENAME_Gateway); //$NON-NLS-1$      
         IdFactory idFactorySymbol = new IdFactory("Symbol", Diagram_Messages.BASENAME_Symbol); //$NON-NLS-1$                        
         ActivitySymbolType activitySymbol = (ActivitySymbolType) CreateModelElementUtil.createModelElement(idFactorySymbol, PKG.getActivitySymbolType(), activitySymbolContainer, model);      
         activitySymbolContainer.getActivitySymbol().add(activitySymbol);
         
         activitySymbol.setXPos(symbol.getXPos());
         activitySymbol.setYPos(symbol.getYPos() + distance);   
         
         newActivity.getSymbols().add(activitySymbol);
         activitySymbol.setActivity(newActivity);
         
         GatewaySymbol gatewaySymbolIn = (GatewaySymbol) CreateModelElementUtil.createModelElement(idFactoryGateway, PKG.getGatewaySymbol(), activitySymbolContainer, model);      
         gatewaySymbolIn.setActivitySymbol(activitySymbol);
         gatewaySymbolIn.setXPos(symbol.getXPos() + width/2);
         gatewaySymbolIn.setYPos(symbol.getYPos() + distance - height);   
         gatewaySymbolIn.setFlowKind(FlowControlType.JOIN_LITERAL);
         activitySymbolContainer.getGatewaySymbol().add(gatewaySymbolIn);
         
         GatewaySymbol gatewaySymbolOut = (GatewaySymbol) CreateModelElementUtil.createModelElement(idFactoryGateway, PKG.getGatewaySymbol(), activitySymbolContainer, model);            
         gatewaySymbolOut.setActivitySymbol(activitySymbol);               
         gatewaySymbolOut.setXPos(symbol.getXPos() + width/2);
         gatewaySymbolOut.setYPos(symbol.getYPos() + distance +  height);            
         gatewaySymbolOut.setFlowKind(FlowControlType.SPLIT_LITERAL);                  
         activitySymbolContainer.getGatewaySymbol().add(gatewaySymbolOut);
         
         TransitionConnectionType connectionIn = (TransitionConnectionType) CreateModelElementUtil.createModelElement(null, PKG.getTransitionConnectionType(), null, model);      
         connectionIn.setSourceNode(gatewaySymbolIn);
         connectionIn.setTargetNode(activitySymbol);          
         addConnection(activitySymbolContainer, connectionIn);
         
         TransitionConnectionType connectionOut = (TransitionConnectionType) CreateModelElementUtil.createModelElement(null, PKG.getTransitionConnectionType(), null, model);            
         connectionOut.setSourceNode(activitySymbol);
         connectionOut.setTargetNode(gatewaySymbolOut);  
         addConnection(activitySymbolContainer, connectionOut);
         
         for(TransitionType transition : newActivity.getOutTransitions())
         {
            ActivityType to = transition.getTo();
            for(ActivitySymbolType toSymbol : to.getActivitySymbols())
            {
               if(toSymbol.eContainer().equals(activitySymbol.eContainer()))
               {
                  TransitionConnectionType connection = (TransitionConnectionType) CreateModelElementUtil.createModelElement(null, PKG.getTransitionConnectionType(), null, model);            
                  connection.setSourceNode(gatewaySymbolOut);
                  connection.setTargetNode(toSymbol);       
                  connection.setTransition(transition);
                  addConnection(activitySymbolContainer, connection);                  
               }
            }
         }         
         for(TransitionType transition : newActivity.getInTransitions())
         {
            ActivityType from = transition.getFrom();
            for(ActivitySymbolType fromSymbol : from.getActivitySymbols())
            {
               if(fromSymbol.eContainer().equals(activitySymbol.eContainer()))
               {
                  TransitionConnectionType connection = (TransitionConnectionType) CreateModelElementUtil.createModelElement(null, PKG.getTransitionConnectionType(), null, model);            
                  connection.setSourceNode(fromSymbol);
                  connection.setTargetNode(gatewaySymbolIn);                  
                  connection.setTransition(transition);                  
                  addConnection(activitySymbolContainer, connection);                                    
               }
            }
         }         
      }
   }
   
   private void addConnection(ISymbolContainer container, TransitionConnectionType connection)
   {
      container = ModelUtils.findContainingPool(container);
      
      EStructuralFeature containmentFeature = CommandUtils.findContainmentFeature(
            container.getConnectionContainingFeatures(), connection);
      
      Object ref = ((EObject) container).eGet(containmentFeature);
      if (ref instanceof List)
      {
         ((List) ref).add(connection);
      }      
   }   
      
   public static ActivityType findStartActivity(ProcessDefinitionType process)
   {
      ActivityType startActivity = null;
      
      for(ActivityType activity : process.getActivity())
      {
         if (activity.getInTransitions().isEmpty())
         {
            if(startActivity == null)
            {
               startActivity = activity;
            }
            else
            {
               return null;
            }            
         }
      }
            
      if (null != startActivity)
      {
         Set<ActivityType> allActivities = CollectionUtils.newSet();
         allActivities.addAll(process.getActivity());

         List<ActivityType> reachedActivities = CollectionUtils.newLinkedList();
         Set<ActivityType> visitedActivities = CollectionUtils.newSet();
         reachedActivities.add(startActivity);

         while (!reachedActivities.isEmpty())
         {
            ActivityType activity = (ActivityType) reachedActivities.remove(0);
            if (!visitedActivities.contains(activity))
            {
               visitedActivities.add(activity);
               for (TransitionType transition : activity.getOutTransitions())
               {
                  if ( !visitedActivities.contains(transition.getTo()))
                  {
                     reachedActivities.add(transition.getTo());
                  }
               }
            }
         }

         allActivities.removeAll(visitedActivities);
         if ( !allActivities.isEmpty())
         {
            return null;
         }
      }
      
      return startActivity;
   }   
}