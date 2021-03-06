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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.UnbindActionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDSchema;

public class UnusedModelElementsSearcher
{
   private ModelType model;
   private List processes;
   private List diagrams;
   private Set transitionDatas;

   private Map matchedElements;

   // search the model for all elements that are not used by other elements (also references)
   // the search should be enhanced if necessary
   public Map search(ModelType modelType)
   {
      matchedElements = new HashMap();
      model = modelType;
      processes = model.getProcessDefinition();
      getTransitionDatas();
      getDiagrams();

      checkTypeDeclarations();
      checkApplications();
      checkData();
      checkIModelParticipants();
      checkLinkTypes();
      //checkProcesses();
      return matchedElements;
   }

   private void getDiagrams()
   {
      diagrams = new ArrayList();
      for (Iterator i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) i.next();
         List currentDiagrams = process.getDiagram();
         if(!currentDiagrams.isEmpty())
         {
            diagrams.addAll(currentDiagrams);
         }
      }
      List currentDiagrams = model.getDiagram();
      if(!currentDiagrams.isEmpty())
      {
         diagrams.addAll(currentDiagrams);
      }
   }

   private void getTransitionDatas()
   {
      transitionDatas = new HashSet();
      List datas = model.getData();
      List transitions = new ArrayList();
      for (Iterator i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) i.next();
         List processTransitions = process.getTransition();
         if(!processTransitions.isEmpty())
         {
            transitions.addAll(processTransitions);
         }
      }
      for (Iterator it = transitions.iterator(); it.hasNext();)
      {
         TransitionType transition = (TransitionType) it.next();
         String condition = transition.getCondition();
         if(condition != null && condition.equals("CONDITION")) //$NON-NLS-1$
         {
            XmlTextNode type = transition.getExpression();
            String expression = type == null ? null : ModelUtils.getCDataString(transition.getExpression().getMixed());
            if(!StringUtils.isEmpty(expression)
                  && !expression.equals("true") //$NON-NLS-1$
                  && !expression.equals("false")) //$NON-NLS-1$
            {
               for (Iterator i = datas.iterator(); i.hasNext();)
               {
                  DataType data = (DataType) i.next();
                  String dataId = data.getId();
                  if(expression.indexOf(dataId) != -1)
                  {
                     transitionDatas.add(data);
                  }
               }
            }
         }
      }
   }

   private void addModelChildren(List elements)
   {
      List modelChildren = (List) matchedElements.get(model);
      if(modelChildren == null)
      {
         modelChildren = new ArrayList();
      }
      modelChildren.addAll(elements);
      matchedElements.put(model, modelChildren);
   }

   // can be referenced only by other TypeDeclarations and data (and message applications?)
   private void checkTypeDeclarations()
   {
      List elements = new ArrayList();

      List typeDeclarations = model.getTypeDeclarations().getTypeDeclaration();
      if(typeDeclarations.isEmpty())
      {
         return;
      }
      for (Iterator i = typeDeclarations.iterator(); i.hasNext();)
      {
         TypeDeclarationType typeDeclaration = (TypeDeclarationType) i.next();
         if(!isTypeDeclarationUsedInDatas(typeDeclaration)
               && !isTypeDeclarationUsedInTypeDeclarations(typeDeclaration)
               && !isTypeDeclarationUsedInProcesses(typeDeclaration)
               && !isElementUsedinApplications(typeDeclaration))
         {
            elements.add(typeDeclaration);
         }
      }
      if(!elements.isEmpty())
      {
         addModelChildren(elements);
      }
   }

   // can be referenced by activities
   private void checkApplications()
   {
      List elements = new ArrayList();

      List applications = model.getApplication();
      if(applications.isEmpty())
      {
         return;
      }
      for (Iterator i = applications.iterator(); i.hasNext();)
      {
         ApplicationType application = (ApplicationType) i.next();
         if(!isElementUsedInProcesses(application))
         {
            elements.add(application);
         }
      }
      if(!elements.isEmpty())
      {
         addModelChildren(elements);
      }
   }

   private void checkData()
   {
      List elements = new ArrayList();
      List datas = model.getData();
      if(datas.isEmpty())
      {
         return;
      }
      for (Iterator i = datas.iterator(); i.hasNext();)
      {
         DataType data = (DataType) i.next();
         // we will not check predefined data
         if(!data.isPredefined())
         {
            if(!isElementUsedInProcesses(data)
                  && !isDataUsedInConditionalPerformer(data)
                  && !isDataUsedInOrganization(data)
                  && !transitionDatas.contains(data))
            {
               elements.add(data);
            }
         }
      }
      if(!elements.isEmpty())
      {
         addModelChildren(elements);
      }
   }

   // lanes can also reference participants
   private void checkIModelParticipants()
   {
      List elements = new ArrayList();
      List participants = new ArrayList();

      List roles = model.getRole();
      List conditionalPerformers = model.getConditionalPerformer();
      List organizations = model.getOrganization();
      if(!roles.isEmpty())
      {
         participants.addAll(roles);
      }
      if(!conditionalPerformers.isEmpty())
      {
         participants.addAll(conditionalPerformers);
      }
      if(!organizations.isEmpty())
      {
         participants.addAll(organizations);
      }
      for (Iterator i = participants.iterator(); i.hasNext();)
      {
         IModelParticipant element = (IModelParticipant) i.next();
         // we will not check Administrator role
         if(!element.getId().equals(PredefinedConstants.ADMINISTRATOR_ROLE))
         {
            if(!isParticipantUsedInOrganizations(element)
                  && !isElementUsedInProcesses(element))
            {
               elements.add(element);
            }
         }
      }
      if(!elements.isEmpty())
      {
         addModelChildren(elements);
      }
   }

   // if has only the default diagram as child it is unused
   // and default diagram is empty
   private void checkProcesses()
   {
      List elements = new ArrayList();

      List processes = model.getProcessDefinition();
      if(processes.isEmpty())
      {
         return;
      }
      // check first all children (if contains children, it is not unused)
      // if contains only diagram and not referenced then we can delete
      // if a child is unused, then the process is a container (do not delete container)
      for (Iterator i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) i.next();
         if(process.getEventHandler().isEmpty()
               && process.getDataPath().isEmpty()
               && process.getExecutingActivities().isEmpty())
         {
            // elements.add(process);
         }
      }
      if(!elements.isEmpty())
      {
         addModelChildren(elements);
      }
   }

   private void checkLinkTypes()
   {
      List elements = new ArrayList();
      List linkTypes = model.getLinkType();
      if(linkTypes.isEmpty())
      {
         return;
      }
      for (Iterator i = linkTypes.iterator(); i.hasNext();)
      {
         LinkTypeType linkType = (LinkTypeType) i.next();
         if(!isLinkTypeUsedinDiagrams(linkType))
         {
            elements.add(linkType);
         }
      }
      if(!elements.isEmpty())
      {
         addModelChildren(elements);
      }
   }

   //////////////////////////////////////////////

   private boolean isElementUsedinApplications(EObject element)
   {
      List applications = model.getApplication();
      for (Iterator it = applications.iterator(); it.hasNext();)
      {
         ApplicationType application = (ApplicationType) it.next();
         List accessPoints = application.getAccessPoint();
         for (Iterator i = accessPoints.iterator(); i.hasNext();)
         {
            AccessPointType accessPoint = (AccessPointType) i.next();
            TypeDeclarationType declaration = (TypeDeclarationType) AttributeUtil.getIdentifiable(
                  accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT);
            if(declaration != null && declaration.equals(element))
            {
               return true;
            }
         }
         for (ContextType context : application.getContext())
         {
            accessPoints = context.getAccessPoint();
            for (Iterator i = accessPoints.iterator(); i.hasNext();)
            {
               AccessPointType accessPoint = (AccessPointType) i.next();
               TypeDeclarationType declaration = (TypeDeclarationType) AttributeUtil.getIdentifiable(
                     accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT);
               if(declaration != null && declaration.equals(element))
               {
                  return true;
               }
            }
         }
      }
      return false;
   }

   private boolean isLinkTypeUsedinDiagrams(LinkTypeType link)
   {
      for (Iterator it = diagrams.iterator(); it.hasNext();)
      {
         DiagramType diagram = (DiagramType) it.next();
         List pools = ((DiagramType) diagram).getPoolSymbols();
         for(int p = 0; p < pools.size(); p++)
         {
            PoolSymbol pool = (PoolSymbol) pools.get(p);
            EList genericLinks = ((PoolSymbol) pool).getGenericLinkConnection();
            for (int i = 0; i < genericLinks.size(); i++)
            {
               GenericLinkConnectionType connection = (GenericLinkConnectionType) genericLinks.get(i);
               EObject modelElement = connection.getLinkType();
               if(modelElement.equals(link))
               {
                  return true;
               }
            }
            if(isLinkTypeUsedinLanes(pool, link))
            {
               return true;
            }
         }
         EList genericLinks = diagram.getGenericLinkConnection();
         for (int i = 0; i < genericLinks.size(); i++)
         {
            GenericLinkConnectionType connection = (GenericLinkConnectionType) genericLinks.get(i);
            EObject modelElement = connection.getLinkType();
            if(modelElement.equals(link))
            {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isTypeDeclarationUsedInTypeDeclarations(TypeDeclarationType element)
   {
      List typeDeclarations = model.getTypeDeclarations().getTypeDeclaration();
      for (Iterator i = typeDeclarations.iterator(); i.hasNext();)
      {
         TypeDeclarationType typeDeclaration = (TypeDeclarationType) i.next();
         //This is a workaround for working with missing XSDs (CRNT-
         if (!Platform.isRunning())
         {
            ExternalReferenceType externalReference = typeDeclaration
                  .getExternalReference();
            if (externalReference != null)
            {
               Object internalSchema = Reflect.getFieldValue(externalReference, "schema");
               if (internalSchema == null)
               {
                  return true;
               }
            }
         }
         XSDSchema schema = typeDeclaration.getSchema();
         if(schema != null)
         {
             List xsdImports = TypeDeclarationUtils.getImports(schema);
             if(xsdImports != null)
             {
                 Iterator it = xsdImports.iterator();
                 while (it.hasNext())
                 {
                     XSDImport xsdImport = (XSDImport) it.next();
                     String schemaLocation = xsdImport.getSchemaLocation();
                     if (schemaLocation != null)
                     {
                         if(schemaLocation.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
                         {
                             String nextTypeId = schemaLocation.substring(StructuredDataConstants.URN_INTERNAL_PREFIX.length());
                             if(nextTypeId.equals(element.getId()))
                             {
                                return true;
                             }
                         }
                     }
                 }
             }
         }
      }
      return false;
   }

   private boolean isTypeDeclarationUsedInDatas(TypeDeclarationType element)
   {
      List datas = model.getData();
      for (Iterator i = datas.iterator(); i.hasNext();)
      {
         DataType data = (DataType) i.next();
         DataTypeType type = data.getType();
         if(type != null &&
               (type.getId().equals(StructuredDataConstants.STRUCTURED_DATA)
               || type.getId().equals(PredefinedConstants.PRIMITIVE_DATA)))
         {
            String structuredDataId = AttributeUtil.getAttributeValue((IExtensibleElement) data, StructuredDataConstants.TYPE_DECLARATION_ATT);
            if(!StringUtils.isEmpty(structuredDataId) && structuredDataId.equals(element.getId()))
            {
               return true;
            }
         }
         else if(isDMSDataType(data))
         {
            String structuredDataId = AttributeUtil.getAttributeValue((IExtensibleElement) data, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT);
            if(!StringUtils.isEmpty(structuredDataId) && structuredDataId.equals(element.getId()))
            {
               return true;
            }
         }
      }
      return false;
   }

   // maybe not necessary, because can be bound only if a data is selected also
   private boolean isTypeDeclarationUsedInProcesses(TypeDeclarationType element)
   {
      for (ProcessDefinitionType process : model.getProcessDefinition())
      {
         FormalParametersType parametersType = process.getFormalParameters();
         if (parametersType != null)
         {
            for (FormalParameterType type : parametersType.getFormalParameter())
            {
               org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType dataType = type.getDataType();
               DeclaredTypeType declaredType = dataType.getDeclaredType();
               if(declaredType != null)
               {
                  String declaredTypeId = declaredType.getId();
                  if ( !StringUtils.isEmpty(declaredTypeId)
                        && declaredTypeId.equals(element.getId()))
                  {
                     return true;
                  }
               }
            }
         }
      }
      return false;
   }

   private boolean isParticipantUsedInOrganizations(IModelParticipant element)
   {
      for (OrganizationType organization : model.getOrganization())
      {
         for (ParticipantType ref : organization.getParticipant())
         {
            IModelParticipant participant = ref.getParticipant();
            if (participant != null && participant.equals(element))
            {
               return true;
            }
         }
         RoleType role = organization.getTeamLead();
         if (role != null && role.equals(element))
         {
            return true;
         }
      }
      return false;
   }

   private boolean isDataUsedInOrganization(DataType element)
   {
      List<OrganizationType> organizations = model.getOrganization();
      for (OrganizationType organization : organizations)
      {
         if(AttributeUtil.getBooleanValue((IExtensibleElement) organization, PredefinedConstants.BINDING_ATT))
         {
            String dataId = AttributeUtil.getAttributeValue((IExtensibleElement) organization, PredefinedConstants.BINDING_DATA_ID_ATT);
            if(!StringUtils.isEmpty(dataId))
            {
               DataType data = (DataType) ModelUtils.findElementById(model.getData(), dataId);
               if(data != null && data.equals(element))
               {
                  return true;
               }
            }
         }
      }
      return false;
   }

   private boolean isDataUsedInConditionalPerformer(DataType element)
   {
      List conditionalPerformers = model.getConditionalPerformer();
      for (Iterator i = conditionalPerformers.iterator(); i.hasNext();)
      {
         ConditionalPerformerType conditionalPerformer = (ConditionalPerformerType) i.next();
         DataType dataType = conditionalPerformer.getData();
         if(dataType != null && dataType.equals(element))
         {
            return true;
         }
         String dataId = AttributeUtil.getAttributeValue((IExtensibleElement) conditionalPerformer, PredefinedConstants.CONDITIONAL_PERFORMER_REALM_DATA);
         if(!StringUtils.isEmpty(dataId))
         {
            DataType realmData = (DataType) ModelUtils.findElementById(model.getData(), dataId);
            if(realmData != null && realmData.equals(element))
            {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isElementUsedInProcesses(EObject element)
   {
      for (Iterator i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) i.next();
         if(isElementUsedInEventHandlers(process.getEventHandler(), element))
         {
            return true;
         }
         if(isElementUsedInActivities(process, element))
         {
            return true;
         }
         if(isElementUsedInTriggers(process, element))
         {
            return true;
         }
         if(isParticipantUsedInProcessDiagrams(process, element))
         {
            return true;
         }
         if(isDataUsedInProcess(process, element))
         {
            return true;
         }

         List dataPathList = process.getDataPath();
         for (Iterator it = dataPathList.iterator(); it.hasNext();)
         {
            DataPathType dataPath = (DataPathType) it.next();
            DataType data = dataPath.getData();
            if(data != null && data.equals(element))
            {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isDataUsedInProcess(ProcessDefinitionType process, EObject element)
   {
      if(element instanceof DataType)
      {
         FormalParameterMappingsType mappings = process.getFormalParameterMappings();
         FormalParametersType formalParameters = process.getFormalParameters();
         if(formalParameters != null && formalParameters.getFormalParameter() != null)
         {
            for(FormalParameterType type : formalParameters.getFormalParameter())
            {
               DataType mappedData = null;

               try
               {
                  mappedData = mappings.getMappedData(type);
               }
               catch (NullPointerException e)
               {
               }

               if(mappedData != null && mappedData.equals(element))
               {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean isElementUsedInActivities(ProcessDefinitionType process, EObject element)
   {
      List activities = process.getActivity();
      for (Iterator it = activities.iterator(); it.hasNext();)
      {
         ActivityType activity = (ActivityType) it.next();
         if(isElementUsedInDataMappings(activity, element))
         {
            return true;
         }
         if(isElementUsedInEventHandlers(activity.getEventHandler(), element))
         {
            return true;
         }
         ApplicationType activityApplication = activity.getApplication();
         if(activityApplication != null)
         {
            if(activityApplication.equals(element))
            {
               return true;
            }
         }
         ProcessDefinitionType activityProcess = activity.getImplementationProcess();
         if(activityProcess != null)
         {
            if(activityProcess.equals(element))
            {
               return true;
            }
         }
         IModelParticipant activityParticipant = activity.getPerformer();
         if(activityParticipant != null)
         {
            if(activityParticipant.equals(element))
            {
               return true;
            }
         }
         activityParticipant = activity.getQualityControlPerformer();
         if(activityParticipant != null)
         {
            if(activityParticipant.equals(element))
            {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isElementUsedInDataMappings(ActivityType activity, EObject element)
   {
      List dataMappings = activity.getDataMapping();
      for (Iterator it = dataMappings.iterator(); it.hasNext();)
      {
         DataMappingType dataMapping = (DataMappingType) it.next();
         DataType data = dataMapping.getData();
         if(data != null && data.equals(element))
         {
            return true;
         }
      }
      return false;
   }

   private boolean isElementUsedInEventHandlers(List eventHandlers, EObject element)
   {
      for (Iterator it = eventHandlers.iterator(); it.hasNext();)
      {
         EventHandlerType eventHandler = (EventHandlerType) it.next();
         List eventActions = eventHandler.getEventAction();
         for (Iterator a = eventActions.iterator(); a.hasNext();)
         {
            EventActionType actionType = (EventActionType) a.next();
            if(actionType instanceof IExtensibleElement)
            {
               List attributes = actionType.getAttribute();
               for (Iterator attr = attributes.iterator(); attr.hasNext();)
               {
                  AttributeType attribute = (AttributeType) attr.next();
                  if (attribute != null)
                  {
                     if (attribute.getReference() != null)
                     {
                        IIdentifiableModelElement referencedElement = (IIdentifiableModelElement) attribute.getReference().getIdentifiable();
                        if(referencedElement != null && referencedElement.equals(element))
                        {
                           return true;
                        }
                     }
                  }
               }
            }
         }
         List bindActions = eventHandler.getBindAction();
         for (Iterator a = bindActions.iterator(); a.hasNext();)
         {
            BindActionType actionType = (BindActionType) a.next();
            if(actionType instanceof IExtensibleElement)
            {
               List attributes = actionType.getAttribute();
               for (Iterator attr = attributes.iterator(); attr.hasNext();)
               {
                  AttributeType attribute = (AttributeType) attr.next();
                  if (attribute != null)
                  {
                     if (attribute.getReference() != null)
                     {
                        IIdentifiableModelElement referencedElement = (IIdentifiableModelElement) attribute.getReference().getIdentifiable();
                        if(referencedElement != null && referencedElement.equals(element))
                        {
                           return true;
                        }
                     }
                  }
               }
            }
         }
         List unbindActions = eventHandler.getUnbindAction();
         for (Iterator a = unbindActions.iterator(); a.hasNext();)
         {
            UnbindActionType actionType = (UnbindActionType) a.next();
            if(actionType instanceof IExtensibleElement)
            {
               List attributes = actionType.getAttribute();
               for (Iterator attr = attributes.iterator(); attr.hasNext();)
               {
                  AttributeType attribute = (AttributeType) attr.next();
                  if (attribute != null)
                  {
                     if (attribute.getReference() != null)
                     {
                        IIdentifiableModelElement referencedElement = (IIdentifiableModelElement) attribute.getReference().getIdentifiable();
                        if(referencedElement != null && referencedElement.equals(element))
                        {
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }
      return false;
   }

   private boolean isElementUsedInTriggers(ProcessDefinitionType process, EObject element)
   {
      List triggers = process.getTrigger();
      for (Iterator it = triggers.iterator(); it.hasNext();)
      {
         TriggerType trigger = (TriggerType) it.next();
         EList attributes = trigger.getAttribute();
         for(int a = 0; a < attributes.size(); a++)
         {
            AttributeType attribute = (AttributeType) attributes.get(a);
            IdentifiableReference reference = attribute.getReference();
            if(reference != null)
            {
               EObject referencedElement = reference.getIdentifiable();
               if(referencedElement != null && referencedElement.equals(element))
               {
                  return true;
               }
            }
         }
         EList mappings = trigger.getParameterMapping();
         for(int m = 0; m < mappings.size(); m++)
         {
            ParameterMappingType mappingType = (ParameterMappingType) mappings.get(m);
            DataType data = mappingType.getData();
            if(data != null && data.equals(element))
            {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isParticipantUsedInProcessDiagrams(ProcessDefinitionType process, EObject element)
   {
      if(element instanceof IModelParticipant)
      {
         List diagrams = process.getDiagram();
         for (Iterator it = diagrams.iterator(); it.hasNext();)
         {
            DiagramType diagram = (DiagramType) it.next();
            List pools = ((DiagramType) diagram).getPoolSymbols();
            for (Iterator p = pools.iterator(); p.hasNext();)
            {
               PoolSymbol pool = (PoolSymbol) p.next();
               if(!pool.getLanes().isEmpty())
               {
                  return isParticipantUsedInLanes(pool, element);
               }
            }
         }
      }
      return false;
   }

   private boolean isParticipantUsedInLanes(ISwimlaneSymbol container, EObject element)
   {
      if(element instanceof IModelParticipant)
      {
         for (LaneSymbol lane : container.getChildLanes())
         {
            IModelParticipant participant = lane.getParticipantReference();
            if(participant != null && participant.equals(element))
            {
               return true;
            }
            if(!lane.getChildLanes().isEmpty())
            {
               return isParticipantUsedInLanes(lane, element);
            }
         }
      }
      return false;
   }

   private boolean isLinkTypeUsedinLanes(ISwimlaneSymbol container, LinkTypeType link)
   {
      for (LaneSymbol lane : container.getChildLanes())
      {
         EList genericLinks = lane.getGenericLinkConnection();
         for (int i = 0; i < genericLinks.size(); i++)
         {
            GenericLinkConnectionType connection = (GenericLinkConnectionType) genericLinks.get(i);
            EObject modelElement = connection.getLinkType();
            if(modelElement.equals(link))
            {
               return true;
            }
         }
         if(!lane.getChildLanes().isEmpty())
         {
            return isLinkTypeUsedinLanes(lane, link);
         }
      }

      return false;
   }

   private boolean isDMSDataType(DataType data)
   {
      // DMS
      if (data.getType() != null)
      {
         if (data.getType().getId().equals(org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants.DATA_TYPE_ID_DOCUMENT)
               || data.getType().getId().equals(org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants.DATA_TYPE_ID_DOCUMENT_SET)
               || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_DOCUMENT)
               || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST)
               || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_FOLDER)
               || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_FOLDER_LIST))
         {
            return true;
         }
      }
      return false;
   }
}