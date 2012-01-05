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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.ViewableType;
import org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.views.bookmark.WorkflowModelBookmarkView;
import org.eclipse.ui.PlatformUI;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;
import ag.carnot.workflow.model.PredefinedConstants;

public class DeleteAllCommandFactory
{
   private static final CarnotWorkflowModelPackage cwm_pkg = CarnotWorkflowModelPackage.eINSTANCE;

   public static Command createDeleteAllCommand(TypeDeclarationType declaration)
   {
      CompoundCommand cmd = new CompoundCommand();
      ModelType model = ModelUtils.findContainingModel(declaration);
      
      List applications = model.getApplication();
      List allApplicationAccessPoints = new ArrayList();
      
      for (Iterator it = applications.iterator(); it.hasNext();)
      {
         ApplicationType application = (ApplicationType) it.next();   
         List accessPoints = application.getAccessPoint();
         for (Iterator i = accessPoints.iterator(); i.hasNext();)
         {
            AccessPointType accessPoint = (AccessPointType) i.next(); 
            TypeDeclarationType accessPointDeclaration = (TypeDeclarationType) AttributeUtil.getIdentifiable(
                  accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT);
            if(accessPointDeclaration != null && accessPointDeclaration.equals(declaration))
            {
               allApplicationAccessPoints.add(accessPoint);
               AttributeType att = AttributeUtil.getAttribute(accessPoint,
                     StructuredDataConstants.TYPE_DECLARATION_ATT);
               if(att != null)
               {
                  cmd.add(new DeleteValueCmd(att, cwm_pkg.getAttributeType_Reference()));               
               }
               addDeleteActivityReferences(cmd, accessPoint);
               cmd.add(new DeleteValueCmd(application, accessPoint, 
                     accessPoint.eContainingFeature()));
            }            
         }
      }   
      
      cmd.add(new DeleteValueCmd(declaration.eContainer(), declaration, 
            XpdlPackage.eINSTANCE.getTypeDeclarationsType_TypeDeclaration()));
      
      return cmd;
   }
   
   // application, event handler, trigger have access points
   private static void addDeleteActivityReferences(CompoundCommand cmd, EObject element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      List processes = model.getProcessDefinition();
      for (Iterator i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) i.next();  
         List activities = process.getActivity();
         for (Iterator it = activities.iterator(); it.hasNext();)
         {
            ActivityType activity = (ActivityType) it.next(); 
            ApplicationType application = activity.getApplication();   
            List dataMappings = activity.getDataMapping();
            for (Iterator dm = dataMappings.iterator(); dm.hasNext();)
            {
               DataMappingType dataMapping = (DataMappingType) dm.next();
               DataType data = dataMapping.getData();
               if(data != null && data.equals(element))
               {
                  cmd.add(new DeleteValueCmd(activity, dataMapping, 
                        cwm_pkg.getActivityType_DataMapping()));                  
               }         
               else
               {
                  if(element instanceof AccessPointType)
                  {
                     AccessPointType accessPoint = (AccessPointType) element;  
                     
                     String accessPointAttribute = dataMapping.getApplicationAccessPoint();
                     if(!StringUtils.isEmpty(accessPointAttribute)
                           && application != null
                           && application.equals(accessPoint.eContainer()))
                     {
                        if(accessPoint.getId().equals(accessPointAttribute))
                        {
                           cmd.add(new DeleteValueCmd(dataMapping, cwm_pkg.getDataMappingType_ApplicationPath()));
                           cmd.add(new DeleteValueCmd(dataMapping, cwm_pkg.getDataMappingType_ApplicationAccessPoint()));
                        }
                     }
                  }
               }               
            }    
         }         
      }
   }   
      
   public static Command createDeleteAllCommand(IModelElement model)
   {
      IModelElement root = findRootElement(model);

      CompoundCommand cmd = new CompoundCommand();
      if (root instanceof IConnectionSymbol)
      {
         // special cases when we have a connection with a logical model element
         if (root instanceof DataMappingConnectionType)
         {
            addDeleteDataMappings(cmd, (DataMappingConnectionType) root);
         }
         else if (root instanceof ExecutedByConnectionType)
         {
            addDeleteExecutedBy(cmd, (ExecutedByConnectionType) root);
         }
         else if (root instanceof PerformsConnectionType)
         {
            addDeletePerforms(cmd, (PerformsConnectionType) root);
         }
         else if (root instanceof WorksForConnectionType)
         {
            addDeleteWorksFor(cmd, (WorksForConnectionType) root);
         }
         else if (root instanceof TeamLeadConnectionType)
         {
            addDeleteTeamLead(cmd, (TeamLeadConnectionType) root);
         }
         else if (root instanceof PartOfConnectionType)
         {
            addDeletePartOf(cmd, (PartOfConnectionType) root);
         }
      }
      else if (null != root)
      {
         addDeleteModelElement(cmd, root);
      }
      return cmd.unwrap();
   }

   private static void addDeleteModelElement(CompoundCommand cmd, EObject element)
   {
      addDeleteSymbols(cmd, element);
      addDeleteViewables(cmd, element);
      fixReferences(cmd, element, element, new HashSet());
      if (element instanceof IModelParticipant)
      {
         addDeleteParticipantAssociations(cmd, element);
      }
      if (element instanceof DataType)
      {
         fixDataAttributeReferences(cmd, (DataType) element);
      }
      cmd.add(new DeleteValueCmd(element.eContainer(), element, element
            .eContainingFeature()));
   }

   // TODO: (fh) spi things needs to have an interface to fix references by id.
   // alternative, attributes may have model element references
   private static void fixDataAttributeReferences(CompoundCommand cmd, DataType data)
   {
      List<AbstractEventAction> actions = new ArrayList<AbstractEventAction>();

      ModelType model = ModelUtils.findContainingModel(data);
      // NPE may occur
      if(model == null)
      {
         return;
      }
      Map processes = new HashMap();
      
      for (Iterator iter = model.getProcessDefinition().iterator(); iter.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) iter.next();
         for (Iterator iterator = process.getEventHandler().iterator(); iterator
               .hasNext();)
         {
            getActionTypes(actions, (EventHandlerType) iterator.next());
         }
         for (Iterator iterator = process.getActivity().iterator(); iterator.hasNext();)
         {
            ActivityType activity = (ActivityType) iterator.next();
            
            // only if process is locked
            ModelType modelType = ModelUtils.findContainingModel(process);
            WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(modelType);
            
            ModelServer server = null;
            if(editor != null)
            {
               server = editor.getModelServer();
            }
            
            Boolean requireLock = null;
            if(processes.containsKey(process))
            {
               requireLock = (Boolean) processes.get(process);
            }
            else if(server != null)
            {
               if(editor != null)
               {
                  if(editor.getModelServer().requireLock(process))
                  {
                     requireLock = Boolean.TRUE;                  
                  }
                  else
                  {
                     requireLock = Boolean.FALSE;                                    
                  }
                  processes.put(process, requireLock);
               }
            }            
            
            if (server == null || !requireLock.booleanValue())
            {
               List dataMappings = activity.getDataMapping();
               for (Iterator it = dataMappings.iterator(); it.hasNext();)
               {
                  DataMappingType dataMapping = (DataMappingType) it.next();
                  DataType dataMappingData = dataMapping.getData();
                  if(dataMappingData != null && data.equals(dataMappingData))
                  {
                     cmd.add(new DeleteValueCmd(dataMapping, cwm_pkg
                           .getDataMappingType_Data()));
                     cmd.add(new DeleteValueCmd(activity, dataMapping, cwm_pkg
                           .getActivityType_DataMapping()));                  
                  }         
               }
            }
            
            for (Iterator iter2 = activity.getEventHandler().iterator(); iter2.hasNext();)
            {
               getActionTypes(actions, (EventHandlerType) iter2.next());
            }
         }
      }

      for (AbstractEventAction action : actions)
      {
         if (PredefinedConstants.MAIL_ACTION.equals(action.getType().getId()))
         {
            if (data.getId().equals(
                  AttributeUtil.getAttributeValue(action,
                        PredefinedConstants.MAIL_ACTION_BODY_DATA_ATT)))
            {
               AttributeType att = AttributeUtil.getAttribute(action,
                     PredefinedConstants.MAIL_ACTION_BODY_DATA_ATT);
               cmd
                     .add(new DeleteValueCmd(att.eContainer(), att, att
                           .eContainingFeature()));
               AttributeType attDP = AttributeUtil.getAttribute(action,
                     PredefinedConstants.MAIL_ACTION_BODY_DATA_PATH_ATT);
               if (null != attDP)
               {
                  cmd.add(new DeleteValueCmd(attDP.eContainer(), attDP, attDP
                        .eContainingFeature()));
               }
            }
         }
      }
   }

   private static void getActionTypes(List actions, EventHandlerType eventHandler)
   {
      actions.addAll(eventHandler.getUnbindAction());
      actions.addAll(eventHandler.getBindAction());
      actions.addAll(eventHandler.getEventAction());
   }

   private static void addDeleteViewables(CompoundCommand cmd, EObject eObject)
   {
      ModelType model = ModelUtils.findContainingModel(eObject);
      if (null != model)
      {
         deleteViewables(cmd, model.getView(), eObject);
      }
   }

   private static void addDeleteParticipantAssociations(CompoundCommand cmd,
         EObject element)
   {
      IModelParticipant participant = (IModelParticipant) element;
      List associations = participant.getParticipantAssociations();
      for (int i = 0; i < associations.size(); i++)
      {
         ParticipantType assoc = (ParticipantType) associations.get(i);
         OrganizationType organization = (OrganizationType) assoc.eContainer();
         cmd.add(new DeleteValueCmd(organization, assoc, cwm_pkg
               .getOrganizationType_Participant()));
      }
   }

   private static void deleteViewables(CompoundCommand cmd, EList views, EObject eObject)
   {
      for (Iterator iter = views.iterator(); iter.hasNext();)
      {
         ViewType view = (ViewType) iter.next();
         List deletedViewables = new ArrayList();
         for (Iterator iterator = view.getViewable().iterator(); iterator.hasNext();)
         {
            ViewableType viewable = (ViewableType) iterator.next();

            // delete all views of the children from the parent to delete
            for (Iterator iter2 = ((IModelElement) eObject).eContents().iterator(); iter2
                  .hasNext();)
            {
               EObject childElement = (EObject) iter2.next();
               if (viewable.getViewable().equals(childElement))
               {
                  deletedViewables.add(viewable);
               }
            }

            // delete the view of the parent
            if (viewable.getViewable().equals(eObject))
            {
               deletedViewables.add(viewable);
            }
         }
         for (Iterator iterator = deletedViewables.iterator(); iterator.hasNext();)
         {
            ViewableType viewable = (ViewableType) iterator.next();
            cmd.add(new DeleteValueCmd(viewable.eContainer(), viewable, viewable
                  .eContainingFeature())
            {
               private void updateViewer()
               {
                  WorkflowModelBookmarkView viewPart = (WorkflowModelBookmarkView) PlatformUI
                        .getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(WorkflowModelBookmarkView.VIEW_ID);
                  if (viewPart != null)
                  {
                     viewPart.updateViewer();
                  }
               }

               public void execute()
               {
                  super.execute();
                  updateViewer();
               }

               public void redo()
               {
                  super.redo();
                  updateViewer();
               }

               public void undo()
               {
                  super.undo();
                  updateViewer();
               }

            });
         }
         if (view.getView().size() > 0)
         {
            deleteViewables(cmd, view.getView(), eObject);
         }
      }
   }

   private static void addDeleteSymbols(CompoundCommand cmd, EObject element)
   {
      if (element instanceof IIdentifiableModelElement)
      {
         List tmpSymbols = null;
         List symbols = new ArrayList<IGraphicalObject>();
         Map diagrams = new HashMap();         
         WorkflowModelEditor editor = null;
         
         try
         {
            tmpSymbols = ((IIdentifiableModelElement) element).getSymbols();
         }
         catch (Exception e)
         {
            tmpSymbols = Collections.EMPTY_LIST;
         }
         
         for (int i = 0; i < tmpSymbols.size(); i++)
         {
            Object symbol = tmpSymbols.get(i);
            DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) symbol);      
                        
            // collision: diagram (or process) must be locked, if model is shared
            if(editor == null)
            {
               editor = GenericUtils.getWorkflowModelEditor(ModelUtils.findContainingModel(diagram));
            }

            Boolean requireLock = null;
            if(diagrams.containsKey(diagram))
            {
               requireLock = (Boolean) diagrams.get(diagram);
            }
            else
            {
               if(editor != null)
               {
                  if(editor.getModelServer().requireLock(diagram))
                  {
                     requireLock = Boolean.TRUE;                  
                  }
                  else
                  {
                     requireLock = Boolean.FALSE;                                    
                  }
                  diagrams.put(diagram, requireLock);
               }
            }
            
            if (editor != null && !requireLock.booleanValue())
            {
               symbols.add(symbol);
            }         
         }         
         
         for (int i = 0; i < symbols.size(); i++)
         {
            Object symbol = symbols.get(i);
            if (symbol instanceof INodeSymbol)
            {
               addDeleteConnections(cmd, (INodeSymbol) symbol, ((INodeSymbol) symbol)
                     .getInConnectionFeatures());
               addDeleteConnections(cmd, (INodeSymbol) symbol, ((INodeSymbol) symbol)
                     .getOutConnectionFeatures());
               // System.out.println("to delete = " + getString(symbol));

               if (((INodeSymbol) symbol).eContainer() instanceof GroupSymbolType)
               {
                  moveGroupSymbols(cmd, (GroupSymbolType) ((INodeSymbol) symbol)
                        .eContainer(), (INodeSymbol) symbol);
               }

               cmd.add(new DeleteGraphicalObjectCmd((INodeSymbol) symbol));
            }
            else
            {
               addDeleteConnection(cmd, symbol);
            }
         }
      }
      else
      {
         // when deleting lanes, all gatewaysymbols must be deleted (and the transitions)
         // that belong to activities inside this lane 
         // (may be possible that gateways are moved out of the lane into another lane)
         if(element instanceof LaneSymbol)
         {
            List activitySymbols = ((LaneSymbol) element).getActivitySymbol();
            if(!activitySymbols.isEmpty())
            {
               List gatewayTransitions = new ArrayList();
               for (Iterator iter = activitySymbols.iterator(); iter.hasNext();)
               {
                  ActivitySymbolType activitySymbol = (ActivitySymbolType) iter.next();
                  List gatewaySymbols = ((ActivitySymbolType) activitySymbol).getGatewaySymbols();
                  if(!gatewaySymbols.isEmpty())
                  {
                     for (Iterator it = gatewaySymbols.iterator(); it.hasNext();)
                     {
                        GatewaySymbol gatewaySymbol = (GatewaySymbol) it.next();
                        cmd.add(new DeleteGraphicalObjectCmd((INodeSymbol) gatewaySymbol));
                        if(!gatewaySymbol.getInTransitions().isEmpty())
                        {
                           gatewayTransitions.addAll(gatewaySymbol.getInTransitions());
                        }
                        if(!gatewaySymbol.getOutTransitions().isEmpty())
                        {
                           gatewayTransitions.addAll(gatewaySymbol.getOutTransitions());
                        }
                     }
                  }                  
               }
               if(!gatewayTransitions.isEmpty())
               {
                  for (Iterator iter = gatewayTransitions.iterator(); iter.hasNext();)
                  {
                     TransitionConnectionType transition = (TransitionConnectionType) iter.next();
                     addDeleteConnection(cmd, transition);
                  }
               }
            }
         }
      }
   }

   private static void moveGroupSymbols(CompoundCommand cmd, GroupSymbolType groupSymbol,
         INodeSymbol symbol)
   {
      List gateways = new ArrayList();
      if (symbol instanceof ActivitySymbolType)
      {
         gateways = ((ActivitySymbolType) symbol).getGatewaySymbols();
      }

      long xPos = -1;
      long yPos = -1;
      for (Iterator iter = groupSymbol.getNodes().valueListIterator(); iter.hasNext();)
      {
         INodeSymbol nodeSymbol = (INodeSymbol) iter.next();

         if (!nodeSymbol.equals(symbol) && !gateways.contains(nodeSymbol))
         {
            if (xPos == -1 || nodeSymbol.getXPos() < xPos)
            {
               xPos = nodeSymbol.getXPos();
            }
            if (yPos == -1 || nodeSymbol.getYPos() < yPos)
            {
               yPos = nodeSymbol.getYPos();
            }
         }
      }

      if (xPos != 0 || yPos != 0)
      {
         for (Iterator iter = groupSymbol.getNodes().valueListIterator(); iter.hasNext();)
         {
            INodeSymbol nodeSymbol = (INodeSymbol) iter.next();
            if ((!nodeSymbol.equals(symbol)) && !gateways.contains(nodeSymbol))
            {
               cmd.add(new SetValueCmd(nodeSymbol, CarnotWorkflowModelPackage.eINSTANCE
                     .getINodeSymbol_XPos(), new Long(nodeSymbol.getXPos() - xPos)));
               cmd.add(new SetValueCmd(nodeSymbol, CarnotWorkflowModelPackage.eINSTANCE
                     .getINodeSymbol_YPos(), new Long(nodeSymbol.getYPos() - yPos)));
            }
         }
         cmd.add(new SetValueCmd(groupSymbol, CarnotWorkflowModelPackage.eINSTANCE
               .getINodeSymbol_XPos(), new Long(groupSymbol.getXPos() + xPos)));
         cmd.add(new SetValueCmd(groupSymbol, CarnotWorkflowModelPackage.eINSTANCE
               .getINodeSymbol_YPos(), new Long(groupSymbol.getYPos() + yPos)));
      }

      if (groupSymbol.getNodes().size() == 1)
      {
         cmd.add(new DeleteGraphicalObjectCmd(groupSymbol));
      }

   }

   private static void addDeleteConnections(CompoundCommand cmd, INodeSymbol symbol,
         List connectionFeatures)
   {
      for (int i = 0; i < connectionFeatures.size(); i++)
      {
         EStructuralFeature feature = (EStructuralFeature) connectionFeatures.get(i);
         Object connection = symbol.eGet(feature);
         if (connection instanceof List)
         {
            List connectionList = (List) connection;
            for (int j = 0; j < connectionList.size(); j++)
            {
               connection = connectionList.get(j);
               addDeleteConnection(cmd, connection);
            }
         }
         else
         {
            addDeleteConnection(cmd, connection);
         }
      }
   }

   private static void addDeleteConnection(CompoundCommand cmd, Object symbol)
   {
      if (symbol instanceof IConnectionSymbol)
      {
         if (symbol instanceof TransitionConnectionType)
         {
            TransitionConnectionType transition = (TransitionConnectionType) symbol;
            if (transition.getTransition() != null)
            {
               // the transition connection will be deleted together with the transition.
               return;
            }
            // delete gateways together with the activities
            if (transition.getSourceNode() instanceof GatewaySymbol
                  && ((GatewaySymbol) transition.getSourceNode()).getActivitySymbol()
                        .equals(transition.getTargetNode()))
            {
               // System.out.println("to delete = " +
               // getString(transition.getSourceNode()));
               cmd.add(new DeleteGraphicalObjectCmd(transition.getSourceNode()));
            }
            else if (transition.getTargetNode() instanceof GatewaySymbol
                  && ((GatewaySymbol) transition.getTargetNode()).getActivitySymbol()
                        .equals(transition.getSourceNode()))
            {
               // System.out.println("to delete = " +
               // getString(transition.getTargetNode()));
               cmd.add(new DeleteGraphicalObjectCmd(transition.getTargetNode()));
            }
         }
         // System.out.println("to delete = " + getString(symbol));
         cmd.add(new DeleteConnectionSymbolCmd((IConnectionSymbol) symbol));
      }
   }

   private static void fixReferences(CompoundCommand cmd, EObject element, EObject scope,
         Set visited)
   {
      if (!visited.contains(element))
      {
         visited.add(element);
         List references = element.eClass().getEAllReferences();
         for (int i = 0; i < references.size(); i++)
         {
            EReference reference = (EReference) references.get(i);

            if (reference.isMany())
            {
               List list = (List) element.eGet(reference);
               for (int j = 0; j < list.size(); j++)
               {
                  Object childElement = list.get(j);

                  // Workaround (rsauer): removing participant element first to prevent problems
                  // on outline refresh
                  // but doing so might be a good idea in general
                  if (childElement instanceof ParticipantType)
                  {
                     cmd.add(new DeleteValueCmd(element, childElement, reference));
                  }

                  fixReference(cmd, element, scope, childElement, reference, visited);
               }
            }
            else
            {
               fixReference(cmd, element, scope, element.eGet(reference), reference,
                     visited);
            }
         }
      }
   }

   private static void fixReference(CompoundCommand cmd, EObject element, EObject scope,
         Object value, EReference reference, Set visited)
   {
      if (value != null && value instanceof EObject)
      {
         if (isInScope((EObject) value, scope))
         {
            fixReferences(cmd, (EObject) value, scope, visited);
         }
         else
         {
            if (value instanceof TransitionType
                  && !(scope instanceof ISwimlaneSymbol || scope instanceof DiagramType))
            {
               // we delete transition connections here because they are skipped in the
               // addDeleteConnection to avoid multiple delete attempts.
               List connections = ((TransitionType) value).getSymbols();
               for (int i = 0; i < connections.size(); i++)
               {
                  TransitionConnectionType symbol = (TransitionConnectionType) connections
                        .get(i);
                  // System.out.println("to delete = " + getString(symbol));
                  cmd.add(new DeleteConnectionSymbolCmd(symbol));
               }
               addDeleteModelElement(cmd, (EObject) value);
            }
            if (value instanceof IConnectionSymbol)
            {
               cmd.add(new DeleteConnectionSymbolCmd((IConnectionSymbol) value));
            }
            else
            {
               // System.out.println("to remove " + getString(element) + "."
               // + reference.getName() + " : " + getString(value));
               cmd.add(new DeleteValueCmd(element, value, reference));
            }
         }
      }
   }

   /*
    * private static String getString(Object eObject) { if (eObject instanceof
    * IIdentifiableElement) { return ((IIdentifiableElement) eObject).getId(); } if
    * (eObject instanceof IModelElement) { return ((IModelElement)
    * eObject).eClass().getName() + ((IModelElement) eObject).getElementOid(); } return
    * eObject.toString(); }
    */

   private static boolean isInScope(EObject value, EObject scope)
   {
      while (!scope.equals(value))
      {
         if (value instanceof ModelType)
         {
            break;
         }
         if (value.eContainer() == null)
         {
            break;
         }
         value = value.eContainer();
      }
      return scope.equals(value);
   }

   private static void addDeleteDataMappings(CompoundCommand cmd,
         DataMappingConnectionType dataMappingConnection)
   {
      ActivitySymbolType activitySymbol = dataMappingConnection.getActivitySymbol();
      DataSymbolType dataSymbol = dataMappingConnection.getDataSymbol();
      if (dataSymbol != null && activitySymbol != null)
      {
         DataType data = dataSymbol.getData();
         ActivityType activity = activitySymbol.getActivity();
         addDeleteConnections(cmd, activity,
               cwm_pkg.getActivitySymbolType_DataMappings(), data, cwm_pkg
                     .getDataMappingConnectionType_DataSymbol());
         List dataMappingsList = activity.getDataMapping();
         for (int i = 0; i < dataMappingsList.size(); i++)
         {
            DataMappingType dataMapping = (DataMappingType) dataMappingsList.get(i);
            if (data.equals(dataMapping.getData()))
            {
               cmd
                     .add(new DeleteValueCmd(dataMapping, cwm_pkg
                           .getDataMappingType_Data()));
               cmd.add(new DeleteValueCmd(activity, dataMapping, cwm_pkg
                     .getActivityType_DataMapping()));
            }
         }
      }
   }

   private static void addDeleteWorksFor(CompoundCommand cmd,
         WorksForConnectionType worksForConnection)
   {
      RoleType role = worksForConnection.getParticipantSymbol().getRole();
      OrganizationType organization = worksForConnection.getOrganizationSymbol()
            .getOrganization();
      addDeleteConnections(cmd, role,
            cwm_pkg.getRoleSymbolType_OrganizationMemberships(), organization, cwm_pkg
                  .getWorksForConnectionType_OrganizationSymbol());
      addRemoveParticipant(organization, role, cmd);
   }

   private static void addDeleteTeamLead(CompoundCommand cmd,
         TeamLeadConnectionType teamLeadConnection)
   {
      RoleType role = teamLeadConnection.getTeamLeadSymbol().getRole();
      OrganizationType organization = teamLeadConnection.getTeamSymbol()
            .getOrganization();
      addDeleteConnections(cmd, role,
            cwm_pkg.getRoleSymbolType_Teams(), organization, cwm_pkg
                  .getTeamLeadConnectionType_TeamSymbol());
      cmd.add(new DeleteValueCmd(organization, role,
            cwm_pkg.getOrganizationType_TeamLead()));
   }

   private static void addDeletePartOf(CompoundCommand cmd,
         PartOfConnectionType partOfConnection)
   {
      OrganizationType suborganization = partOfConnection.getSuborganizationSymbol()
            .getOrganization();
      OrganizationType organization = partOfConnection.getOrganizationSymbol()
            .getOrganization();
      addDeleteConnections(cmd, suborganization, cwm_pkg
            .getOrganizationSymbolType_SuperOrganizations(), organization, cwm_pkg
            .getPartOfConnectionType_OrganizationSymbol());
      addRemoveParticipant(organization, suborganization, cmd);
   }

   private static void addRemoveParticipant(OrganizationType organization,
         IModelParticipant modelParticipant, CompoundCommand cmd)
   {
      List participantsList = organization.getParticipant();
      for (int i = 0; i < participantsList.size(); i++)
      {
         ParticipantType participant = (ParticipantType) participantsList.get(i);
         if (modelParticipant.equals(participant.getParticipant()))
         {
            cmd.add(new DeleteValueCmd(organization, participant, cwm_pkg
                  .getOrganizationType_Participant()));
         }
      }
   }

   private static void addDeletePerforms(CompoundCommand cmd,
         PerformsConnectionType performsConnection)
   {
      ActivityType activity = performsConnection.getActivitySymbol().getActivity();
      addDeleteConnections(cmd, activity, cwm_pkg
            .getActivitySymbolType_PerformsConnections(), performsConnection
            .getParticipantSymbol().getModelElement(), cwm_pkg
            .getPerformsConnectionType_ParticipantSymbol());
      cmd.add(new DeleteValueCmd(activity, cwm_pkg.getActivityType_Performer()));
   }

   private static void addDeleteExecutedBy(CompoundCommand cmd,
         ExecutedByConnectionType executedByConnection)
   {
      ActivityType activity = executedByConnection.getActivitySymbol().getActivity();
      addDeleteConnections(cmd, activity, cwm_pkg
            .getActivitySymbolType_ExecutedByConnections(), executedByConnection
            .getApplicationSymbol().getApplication(), cwm_pkg
            .getExecutedByConnectionType_ApplicationSymbol());
      cmd.add(new DeleteValueCmd(activity, cwm_pkg.getActivityType_Application()));
   }

   /*
    * private static void addDeleteConnections(CompoundCommand cmd,
    * IIdentifiableModelElement source, EStructuralFeature connectionFeature) { List
    * symbolsList = source.getSymbols(); for (int i = 0; i < symbolsList.size(); i++) {
    * IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) symbolsList.get(i);
    * IConnectionSymbol connection = (IConnectionSymbol) symbol .eGet(connectionFeature);
    * if (connection != null) { cmd.add(new DeleteConnectionSymbolCmd(connection)); } } }
    */

   private static void addDeleteConnections(CompoundCommand cmd,
         IIdentifiableModelElement source, EStructuralFeature connectionsListFeature,
         IIdentifiableModelElement other, EStructuralFeature otherSymbolFeature)
   {
      List symbolsList = source.getSymbols();
      for (int i = 0; i < symbolsList.size(); i++)
      {
         IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) symbolsList.get(i);
         List connectionList = (List) symbol.eGet(connectionsListFeature);
         for (int j = 0; j < connectionList.size(); j++)
         {
            IConnectionSymbol connection = (IConnectionSymbol) connectionList.get(j);
            IModelElementNodeSymbol otherSymbol = (IModelElementNodeSymbol) connection
                  .eGet(otherSymbolFeature);
            if (other.equals(otherSymbol.getModelElement()))
            {
               cmd.add(new DeleteConnectionSymbolCmd(connection));
            }
         }
      }
   }

   private static IModelElement findRootElement(IModelElement model)
   {
      if (model instanceof IModelElementNodeSymbol)
      {
         model = ((IModelElementNodeSymbol) model).getModelElement();
      }
      else if (model instanceof TransitionConnectionType)
      {
         model = ((TransitionConnectionType) model).getTransition();
      }
      return model;
   }
}