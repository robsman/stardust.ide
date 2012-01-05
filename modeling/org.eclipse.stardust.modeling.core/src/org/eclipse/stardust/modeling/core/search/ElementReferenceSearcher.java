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
package org.eclipse.stardust.modeling.core.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
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
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDSchema;

import ag.carnot.workflow.model.PredefinedConstants;

public class ElementReferenceSearcher
{
   private ModelType model;
   private List processes;   
   private Map matchedElements;   
   private EObject checkElement;
   
   public Map search(EObject element)
   {
      matchedElements = new HashMap();
      model = ModelUtils.findContainingModel(element);
      checkElement = element;
        
      search();  
      return matchedElements;
   }   
   
   public void search()
   {
      processes = model.getProcessDefinition();
      
      if (checkElement instanceof ExternalPackage)
      {
         checkTypeDeclaration((ExternalPackage)checkElement);
         isElementUsedInProcesses((ExternalPackage)checkElement);
      }

      if(checkElement instanceof TypeDeclarationType)
      {
         checkTypeDeclaration((TypeDeclarationType) checkElement);
         isElementUsedinApplications(checkElement);
      }
      else if(checkElement instanceof ApplicationType)
      {
         isElementUsedInProcesses(checkElement);
      }
      else if(checkElement instanceof DataType)
      {
         isElementUsedInProcesses(checkElement);
         isDataUsedInConditionalPerformer(checkElement);      
         isDataUsedInOrganization(checkElement);      
      }
      else if(checkElement instanceof LinkTypeType)
      {
         isElementUsedInProcesses(checkElement);
         isElementUsedinModelDiagrams(checkElement);
      }
      else if(checkElement instanceof IModelParticipant)
      {
         isElementUsedInProcesses(checkElement);
         isParticipantUsedInOrganizations(checkElement);
      }
      else if(checkElement instanceof TransitionType
            || checkElement instanceof ProcessDefinitionType
            || checkElement instanceof ActivityType)
      {
         isElementUsedInProcesses(checkElement);
      }      
   }
         
   private void addChildren(EObject container, List elements)
   {
      List modelChildren = (List) matchedElements.get(container);
      if(modelChildren == null)
      {
         modelChildren = new ArrayList();
      }
      modelChildren.addAll(elements);
      matchedElements.put(container, modelChildren);      
   }   
      
   //////////////////////////////////////////////   
   
   private void checkTypeDeclaration(ExternalPackage externalPackage)
   {
      List elements = new ArrayList();
      List datas = model.getData();
      for (Iterator i = datas.iterator(); i.hasNext();)
      {
         DataType data = (DataType) i.next();
         if (data.getExternalReference() != null)
         {
            if (externalPackage.getId().equalsIgnoreCase(
                  data.getExternalReference().getLocation()))
            {
               elements.add(data);
            }
         }
      }
      if (!elements.isEmpty())
      {
         addChildren(model, elements);
      }
   }
   
   private void checkTypeDeclaration(TypeDeclarationType element)
   {
      List elements = new ArrayList();      
      
      List typeDeclarations = model.getTypeDeclarations().getTypeDeclaration();
      for (Iterator i = typeDeclarations.iterator(); i.hasNext();)
      {
         TypeDeclarationType typeDeclaration = (TypeDeclarationType) i.next();    
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
                                elements.add(typeDeclaration);
                             }
                         }           
                     }           
                 }
             }           
         }       
      }
            
      List datas = model.getData();
      for (Iterator i = datas.iterator(); i.hasNext();)
      {
         DataType data = (DataType) i.next();   
         DataTypeType type = data.getType();
         if(type != null && type.getId().equals(StructuredDataConstants.STRUCTURED_DATA))
         {
            String structuredDataId = AttributeUtil.getAttributeValue((IExtensibleElement) data, StructuredDataConstants.TYPE_DECLARATION_ATT);
            if(!StringUtils.isEmpty(structuredDataId) && structuredDataId.equals(element.getId()))
            {
               elements.add(data);
            }
         }
         else if(GenericUtils.isDMSDataType(data))
         {
            String structuredDataId = AttributeUtil.getAttributeValue((IExtensibleElement) data, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT);
            if(!StringUtils.isEmpty(structuredDataId) && structuredDataId.equals(element.getId()))
            {
               elements.add(data);
            }
         }               
      }      
      if(!elements.isEmpty())
      {
         addChildren(model, elements);
      }
   }      
   
   private void isElementUsedinApplications(EObject element)
   {
      List elements = new ArrayList();      
      List applications = model.getApplication();
      for (Iterator it = applications.iterator(); it.hasNext();)
      {
         boolean addApplication = false;
         ApplicationType application = (ApplicationType) it.next();   
         List accessPoints = application.getAccessPoint();
         for (Iterator i = accessPoints.iterator(); i.hasNext();)
         {
            AccessPointType accessPoint = (AccessPointType) i.next(); 
            TypeDeclarationType declaration = (TypeDeclarationType) AttributeUtil.getIdentifiable(
                  accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT);
            if(declaration != null && declaration.equals(element))
            {
               addApplication = true;
            }            
         }
         if(addApplication)
         {
            elements.add(application);
         }
      }
      if(!elements.isEmpty())
      {
         addChildren(model, elements);
      }
   }   

   private void isElementUsedinModelDiagrams(EObject element)
   {
      List elements = new ArrayList();      
      List diagrams = model.getDiagram();
      for (Iterator it = diagrams.iterator(); it.hasNext();)
      {
         DiagramType diagram = (DiagramType) it.next();   
         if(isElementUsedinDiagram(diagram, element))
         {
            elements.add(diagram);
         }
      }
      if(!elements.isEmpty())
      {
         addChildren(model, elements);
      }
   }   
   
   private boolean isElementUsedinDiagram(DiagramType diagram, EObject element)
   {      
      List pools = ((DiagramType) diagram).getPoolSymbols();
      for (Iterator p = pools.iterator(); p.hasNext();)
      {
         PoolSymbol pool = (PoolSymbol) p.next();   
         for(Iterator iter = pool.getLanes().iterator(); iter.hasNext();)
         {
            LaneSymbol lane = (LaneSymbol) iter.next();             
            IModelParticipant participant = lane.getParticipant();
            if(participant != null && participant.equals(element))
            {
               return true;
            }
         }
      }
      
      for(int p = 0; p < pools.size(); p++)
      {
         PoolSymbol pool = (PoolSymbol) pools.get(p); 
         EList genericLinks = ((PoolSymbol) pool).getGenericLinkConnection();
         for (int i = 0; i < genericLinks.size(); i++)
         {
            GenericLinkConnectionType connection = (GenericLinkConnectionType) genericLinks.get(i);
            EObject modelElement = connection.getLinkType();
            if(modelElement.equals(element))
            {
               return true;
            }
         }
         EList lanes = ((PoolSymbol) pool).getLanes();
         for(int l = 0; l < lanes.size(); l++)
         {
            LaneSymbol lane = (LaneSymbol) lanes.get(l);       
            genericLinks = ((LaneSymbol) lane).getGenericLinkConnection();
            for (int i = 0; i < genericLinks.size(); i++)
            {
               GenericLinkConnectionType connection = (GenericLinkConnectionType) genericLinks.get(i);
               EObject modelElement = connection.getLinkType();
               if(modelElement.equals(element))
               {
                  return true;
               }
            }                
         }
      }            
      EList genericLinks = diagram.getGenericLinkConnection();
      for (int i = 0; i < genericLinks.size(); i++)
      {
         GenericLinkConnectionType connection = (GenericLinkConnectionType) genericLinks.get(i);
         EObject modelElement = connection.getLinkType();
         if(modelElement.equals(element))
         {
            return true;
         }
      }            
      return false;
   }
      
   private void isParticipantUsedInOrganizations(EObject element)
   {
      List elements = new ArrayList();
      List organizations = model.getOrganization();
      for (Iterator i = organizations.iterator(); i.hasNext();)
      {
         boolean addOrganization = false;
         OrganizationType organization = (OrganizationType) i.next();   
         List participants = organization.getParticipant();
         for(int cnt = 0; cnt < participants.size(); cnt++)
         {
            IModelParticipant participant = ((ParticipantType) participants.get(cnt)).getParticipant();
            if(participant.equals(element))
            {
               addOrganization = true;
            }            
         }         
         RoleType role = organization.getTeamLead();
         if(role != null && role.equals(element))
         {
            addOrganization = true;
         }
         if(addOrganization)
         {
            elements.add(organization);               
         }         
      }
      if(!elements.isEmpty())
      {
         addChildren(model, elements);
      }
   }         

   private void isDataUsedInOrganization(EObject element)
   {
      List elements = new ArrayList();
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
                  elements.add(organization);                                 
               }
            }            
         }
      }
      if(!elements.isEmpty())
      {
         addChildren(model, elements);
      }
   }         
   
   private void isDataUsedInConditionalPerformer(EObject element)
   {
      List elements = new ArrayList();
      List conditionalPerformers = model.getConditionalPerformer();
      for (Iterator i = conditionalPerformers.iterator(); i.hasNext();)
      {
         boolean addConditionalPerformer = false;
         ConditionalPerformerType conditionalPerformer = (ConditionalPerformerType) i.next();   
         DataType dataType = conditionalPerformer.getData();
         if(dataType != null && dataType.equals(element))
         {                  
            addConditionalPerformer = true;
         }
         String dataId = AttributeUtil.getAttributeValue((IExtensibleElement) conditionalPerformer, PredefinedConstants.CONDITIONAL_PERFORMER_REALM_DATA);
         if(!StringUtils.isEmpty(dataId))
         {
            DataType realmData = (DataType) ModelUtils.findElementById(model.getData(), dataId);
            if(realmData != null && realmData.equals(element))
            {                  
               addConditionalPerformer = true;
            }
         }
         if(addConditionalPerformer)
         {
            elements.add(conditionalPerformer);               
         }         
      }
      if(!elements.isEmpty())
      {
         addChildren(model, elements);
      }
   }         
   
   private void isElementUsedInProcesses(ExternalPackage externalPackage)
   {
      List modelElements = new ArrayList();
      for (Iterator i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) i.next();

         List elements = new ArrayList();
         List activities = process.getActivity();

         for (Iterator it = activities.iterator(); it.hasNext();)
         {
            boolean addActivity = false;
            ActivityType activity = (ActivityType) it.next();
            if (activity.getExternalRef() != null
                  && activity.getExternalRef().getPackageRef().equals(externalPackage))
            {
               addActivity = true;
            }
            if (addActivity)
            {
               elements.add(activity);
            }
            if (!elements.isEmpty())
            {
               addChildren(process, elements);
               modelElements.remove(process);
               addActivity = false;
               elements.clear();
            }
         }
      }
      if (!modelElements.isEmpty())
      {
         addChildren(model, modelElements);
      }
   }
   
   private void isElementUsedInProcesses(EObject element)
   {
      List modelElements = new ArrayList();
      for (Iterator i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) i.next();  
         
         if(isElementUsedInEventHandlers(process.getEventHandler(), element))
         {
            modelElements.add(process);
         }         
         
         List elements = new ArrayList();
         List activities = process.getActivity();
         if(element instanceof ActivityType)
         {
            ProcessDefinitionType activityProcess = ((ActivityType) element).getImplementationProcess();
            if(activityProcess != null)
            {
               if(activityProcess.equals(process))
               {
                  modelElements.add(process);
               }            
            }
            activities = new ArrayList();
            if(ModelUtils.findContainingProcess(element).equals(process))
            {
               activities.add(element);
            }
         }
         
         for (Iterator it = activities.iterator(); it.hasNext();)
         {
            boolean addActivity = false;
            ActivityType activity = (ActivityType) it.next();   
            if(isElementUsedInDataMappings(activity, element))
            {
               addActivity = true;
            }
            if(isElementUsedInEventHandlers(activity.getEventHandler(), element))
            {
               addActivity = true;
            }
            Set transitions = new HashSet();
            List inTransitions = activity.getInTransitions();
            List outTransitions = activity.getOutTransitions();
            for (Iterator t = inTransitions.iterator(); t.hasNext();)
            {
               TransitionType transition = (TransitionType) t.next();
               if(transition.equals(element))
               {
                  addActivity = true;                  
               }               
               if(transition.getFrom().equals(element))
               {
                  transitions.add(transition);
               }
               if(transition.getTo().equals(element))
               {
                  transitions.add(transition);
               }
            }
            for (Iterator t = outTransitions.iterator(); t.hasNext();)
            {
               TransitionType transition = (TransitionType) t.next();   
               if(transition.equals(element))
               {
                  addActivity = true;                  
               }               
               if(transition.getFrom().equals(element))
               {
                  transitions.add(transition);
               }
               if(transition.getTo().equals(element))
               {
                  transitions.add(transition);
               }
            }            
            if(!transitions.isEmpty())
            {
               elements.addAll(transitions);
            }
            
            ApplicationType activityApplication = activity.getApplication();
            if(activityApplication != null)
            {
               if(activityApplication.equals(element))
               {
                  addActivity = true;
               }
            }
            ProcessDefinitionType activityProcess = activity.getImplementationProcess();
            if(activityProcess != null)
            {
               if(activityProcess.equals(element))
               {
                  addActivity = true;
               }            
            }
            IModelParticipant activityParticipant = activity.getPerformer();
            if(activityParticipant != null)
            {
               if(activityParticipant.equals(element))
               {
                  addActivity = true;
               }            
            }
            if(addActivity)
            {
               elements.add(activity);               
            }
         }   
         
         List triggers = process.getTrigger();
         for (Iterator it = triggers.iterator(); it.hasNext();)
         {
            boolean addTrigger = false;
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
                     addTrigger = true;
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
                  addTrigger = true;
               }                     
            }   
            if(addTrigger)
            {
               elements.add(trigger);
            }            
         }
         
         if(element instanceof DataType)
         {
            List transitions = process.getTransition();
            for (Iterator it = transitions.iterator(); it.hasNext();)
            {
               TransitionType transition = (TransitionType) it.next();   
               String condition = transition.getCondition();
               if(condition.equals("CONDITION")) //$NON-NLS-1$
               {
                  XmlTextNode type = transition.getExpression();
                  String expression = type == null ? null : ModelUtils.getCDataString(transition.getExpression().getMixed());
                  if(!StringUtils.isEmpty(expression)
                        && !expression.equals("true") //$NON-NLS-1$
                        && !expression.equals("false")) //$NON-NLS-1$
                  {
                     String dataId = ((DataType) element).getId();
                     if(expression.indexOf(dataId) != -1)
                     {
                        elements.add(transition);
                     }
                  }
               }         
            }
         }
         
         List diagrams = process.getDiagram();
         for (Iterator it = diagrams.iterator(); it.hasNext();)
         {
            DiagramType diagram = (DiagramType) it.next();   
            if(isElementUsedinDiagram(diagram, element))
            {
               elements.add(diagram);
            }
         }
         
         List dataPathList = process.getDataPath();
         for (Iterator it = dataPathList.iterator(); it.hasNext();)
         {               
            DataPathType dataPath = (DataPathType) it.next();
            DataType data = dataPath.getData();
            if(data != null && data.equals(element))
            {
               elements.add(dataPath);
            }
         }
         if(!elements.isEmpty())
         {
            addChildren(process, elements);
            modelElements.remove(process);
         }
      }
      if(!modelElements.isEmpty())
      {
         addChildren(model, modelElements);
      }
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
}