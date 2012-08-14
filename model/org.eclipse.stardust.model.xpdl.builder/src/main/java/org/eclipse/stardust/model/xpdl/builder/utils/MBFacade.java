/*******************************************************************************
 * Copyright (c) 2011, 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.utils;

import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ADMINISTRATOR_ROLE;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newApplicationActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newDocumentVariable;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualTrigger;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newOrganization;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newPrimitiveVariable;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRole;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newStructVariable;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newSubProcessActivity;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.ObjectNotFoundException;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmSubProcessActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.strategy.ModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.builder.variable.BpmDocumentVariableBuilder;
import org.eclipse.stardust.model.xpdl.builder.variable.BpmStructVariableBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceModelElementDescriptor;

public class MBFacade
{
   private final static MBFacade mbFacade;

   private ModelManagementStrategy modelManagementStrategy;

   static
   {
      mbFacade = new MBFacade();
   }

   private MBFacade()
   {

   }

   public static MBFacade getInstance()
   {
      return mbFacade;
   }

   public static MBFacade getInstance(ModelManagementStrategy modelManagementStrategy)
   {
      mbFacade.modelManagementStrategy = modelManagementStrategy;
      return mbFacade;
   }

   public ModelManagementStrategy getModelManagementStrategy()
   {
      return modelManagementStrategy;
   }

   /**
    * 
    * @param organization
    * @param role
    * @return
    */
   public void setTeamLeader(OrganizationType organization, RoleType role)
   {
      organization.setTeamLead(role);
   }

   /**
    * 
    * @param organization
    * @param participant
    * @return
    */
   public void addOrganizationParticipant(OrganizationType organization,
         IModelParticipant participant)
   {
      ParticipantType participantType = AbstractElementBuilder.F_CWM
            .createParticipantType();
      participantType.setParticipant(participant);
      organization.getParticipant().add(participantType);
   }

   /**
    * @param model
    * @param participant
    * @return
    */
   public List<OrganizationType> getParentOrganizations(ModelType model,
         IModelParticipant participant)
   {
      List<OrganizationType> belongsTo = new ArrayList<OrganizationType>();
      EList<OrganizationType> orgs = model.getOrganization();
      if (null != orgs)
      {
         for (OrganizationType org : orgs)
         {
            for (ParticipantType pt : org.getParticipant())
            {
               if (participant.equals(pt.getParticipant()))
               {
                  belongsTo.add(org);
               }
            }
         }
      }

      return belongsTo;
   }

   public TypeDeclarationType createTypeDeclaration(ModelType model, String typeId,
         String typeName)
   {
      TypeDeclarationType structuredDataType = XpdlFactory.eINSTANCE
            .createTypeDeclarationType();

      structuredDataType.setId(typeId);
      structuredDataType.setName(typeName);

      model.getTypeDeclarations().getTypeDeclaration().add(structuredDataType);

      return structuredDataType;
   }

   public DataType createDocumentData(ModelType model, String id, String name,
         String structuredDataFullId)
   {
      DataType data;
      BpmDocumentVariableBuilder documentVariable = newDocumentVariable(model);
      if (!StringUtils.isEmpty(structuredDataFullId))
      {
         documentVariable.setTypeDeclaration(structuredDataFullId);
      }

      data = documentVariable.withIdAndName(id, name).build();

      return data;
   }

   public DataType createStructuredData(ModelType model, String stripFullId_, String id,
         String name, String structuredDataFullId)
   {
      DataType data;
      ModelType typeDeclarationModel = getModelManagementStrategy().getModels().get(
            stripFullId_);

      BpmStructVariableBuilder structVariable = newStructVariable(model);
      structVariable.setTypeDeclarationModel(typeDeclarationModel);

      data = structVariable.withIdAndName(id, name).ofType(structuredDataFullId).build();

      return data;
   }

   public DataType createPrimitiveData(ModelType model, String id, String name,
         String primitiveType)
   {
      DataType data;
      Type type = null;

      if (primitiveType.equals(ModelerConstants.STRING_PRIMITIVE_DATA_TYPE))
      {
         type = Type.String;
      }
      else if (primitiveType.equals(ModelerConstants.DATE_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Calendar;
      }
      else if (primitiveType.equals(ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Integer;
      }
      else if (primitiveType.equals(ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Double;
      }
      else if (primitiveType.equals(ModelerConstants.DECIMAL_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Money;
      }

      data = newPrimitiveVariable(model).withIdAndName(id, name).ofType(type).build();

      return data;
   }

   public DataSymbolType createDataSymbol(ProcessDefinitionType processDefinition,
         int xProperty, int yProperty, int widthProperty, int heightProperty,
         String parentSymbolID, long maxOid, DataType data)
   {
      DataSymbolType dataSymbol = AbstractElementBuilder.F_CWM.createDataSymbolType();
      LaneSymbol parentLaneSymbol = findLaneInProcess(processDefinition, parentSymbolID);

      dataSymbol.setElementOid(++maxOid);

      dataSymbol.setData(data);

      processDefinition.getDiagram().get(0).getDataSymbol().add(dataSymbol);
      data.getDataSymbols().add(dataSymbol);
      dataSymbol.setXPos(xProperty - parentLaneSymbol.getXPos());
      dataSymbol.setYPos(yProperty - parentLaneSymbol.getYPos());
      dataSymbol.setWidth(widthProperty);
      dataSymbol.setHeight(heightProperty);

      parentLaneSymbol.getDataSymbol().add(dataSymbol);
      return dataSymbol;
   }

   public DataType createNewPrimitive(ModelType model, String dataID, String dataName)
   {
      DataType data;
      Type type = null;

      if (true)
      {
         type = Type.String;
      }

      data = newPrimitiveVariable(model)
            .withIdAndName(stripFullId(dataID), stripFullId(dataName)).ofType(type)
            .build();
      return data;
   }

   public DataType importData(ModelType targetModel, String dataFullID)
   {
      DataType data;
      // TODO Cross-model references

      String dataModelId = getModelId(dataFullID);

      ModelType dataModel = getModelManagementStrategy().getModels().get(dataModelId);

      data = findData(dataModel, stripFullId(dataFullID));

      if (!dataModelId.equals(targetModel.getId()))
      {
         String fileConnectionId = JcrConnectionManager.createFileConnection(targetModel,
               dataModel);

         String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
         URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

         ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(
               uri, data, bundleId, null, true);

         PepperIconFactory iconFactory = new PepperIconFactory();

         descriptor.importElements(iconFactory, targetModel, true);

      }
      return data;
   }

   public ActivitySymbolType createActivitySymbol(
         ProcessDefinitionType processDefinition, String parentSymbolID, int xProperty,
         int yProperty, int widthProperty, int heightProperty, long maxOid,
         ActivityType activity)
   {
      ActivitySymbolType activitySymbol = AbstractElementBuilder.F_CWM
            .createActivitySymbolType();
      LaneSymbol parentLaneSymbol = findLaneInProcess(processDefinition, parentSymbolID);

      activitySymbol.setElementOid(++maxOid);
      activitySymbol.setXPos(xProperty - parentLaneSymbol.getXPos());
      activitySymbol.setYPos(yProperty - parentLaneSymbol.getYPos());
      activitySymbol.setWidth(widthProperty);
      activitySymbol.setHeight(heightProperty);
      activitySymbol.setActivity(activity);
      activity.getActivitySymbols().add(activitySymbol);

      processDefinition.getDiagram().get(0).getActivitySymbol().add(activitySymbol);
      parentLaneSymbol.getActivitySymbol().add(activitySymbol);
      return activitySymbol;
   }

   public RoleType createRole(ModelType model, String roleID, String roleName)
   {
      RoleType role = newRole(model).withIdAndName(roleID, roleName).build();
      return role;
   }

   public OrganizationType createOrganization(ModelType model, String orgID,
         String orgName)
   {
      OrganizationType org = newOrganization(model).withIdAndName(orgID, orgName).build();
      return org;
   }

   public ActivityType createActivity(String modelId,
         ProcessDefinitionType processDefinition, String activityType,
         String participantFullID, String activityID, String activityName,
         String applicationFullID, String subProcessFullID, long maxOid)
   {
      ActivityType activity = null;

      if (ModelerConstants.MANUAL_ACTIVITY.equals(activityType))
      {
         if (participantFullID != null)
         {
            activity = newManualActivity(processDefinition)
                  .withIdAndName(activityID, activityName)
                  .havingDefaultPerformer(stripFullId(participantFullID)).build();
         }
         else
         {
            activity = newManualActivity(processDefinition).withIdAndName(activityID,
                  activityName).build();
         }
      }
      else if (ModelerConstants.APPLICATION_ACTIVITY.equals(activityType))

      {

         String stripFullId_ = getModelId(applicationFullID);
         if (StringUtils.isEmpty(stripFullId_))
         {
            stripFullId_ = modelId;
         }

         ApplicationType application = getApplication(stripFullId_,
               stripFullId(applicationFullID));
         ModelType applicationModel = ModelUtils.findContainingModel(application);
         BpmApplicationActivityBuilder applicationActivity = newApplicationActivity(processDefinition);
         applicationActivity.setApplicationModel(applicationModel);

         activity = applicationActivity
               .withIdAndName(activityID, activityName)
               .invokingApplication(
                     getApplication(applicationModel.getId(),
                           stripFullId(applicationFullID))).build();
         // }
      }
      else if (ModelerConstants.SUBPROCESS_ACTIVITY.equals(activityType))
      {

         String stripFullId = getModelId(subProcessFullID);
         if (StringUtils.isEmpty(stripFullId))
         {
            stripFullId = modelId;
         }

         ProcessDefinitionType subProcessDefinition = findProcessDefinition(
               getModelManagementStrategy().getModels().get(stripFullId),
               stripFullId(subProcessFullID));
         ModelType subProcessModel = ModelUtils.findContainingModel(subProcessDefinition);

         BpmSubProcessActivityBuilder subProcessActivity = newSubProcessActivity(processDefinition);
         subProcessActivity.setSubProcessModel(subProcessModel);

         activity = subProcessActivity
               .withIdAndName(modelId, activityName)
               .invokingProcess(
                     findProcessDefinition(
                           getModelManagementStrategy().getModels().get(
                                 subProcessModel.getId()), stripFullId(subProcessFullID)))
               .build();
      }
      else
      {
         activity = newRouteActivity(processDefinition).withIdAndName(activityID,
               activityName).build();
      }
      activity.setElementOid(maxOid + 1);
      return activity;
   }

   public ProcessDefinitionType createProcess(ModelType model, String name, String id)
   {
      ProcessDefinitionType processDefinition = newProcessDefinition(model)
            .withIdAndName(id, name).build();

      // Create diagram bits too

      DiagramType diagram = AbstractElementBuilder.F_CWM.createDiagramType();
      diagram.setMode(DiagramModeType.MODE_450_LITERAL);
      diagram.setOrientation(OrientationType.VERTICAL_LITERAL);
      long maxOid = XpdlModelUtils.getMaxUsedOid(model);
      diagram.setElementOid(++maxOid);
      diagram.setName("Diagram 1");

      PoolSymbol poolSymbol = AbstractElementBuilder.F_CWM.createPoolSymbol();

      diagram.getPoolSymbols().add(poolSymbol);

      poolSymbol.setElementOid(++maxOid);
      poolSymbol.setXPos(0);
      poolSymbol.setYPos(0);
      poolSymbol.setWidth(500);
      poolSymbol.setHeight(600);
      poolSymbol.setName("DEFAULT_POOL");
      poolSymbol.setId("DEFAULT_POOL");
      poolSymbol.setOrientation(OrientationType.VERTICAL_LITERAL);

      LaneSymbol laneSymbol = AbstractElementBuilder.F_CWM.createLaneSymbol();

      poolSymbol.getChildLanes().add(laneSymbol);
      laneSymbol.setParentPool(poolSymbol);

      laneSymbol.setElementOid(++maxOid);
      laneSymbol.setId(ModelerConstants.DEF_LANE_ID);
      laneSymbol.setName(ModelerConstants.DEF_LANE_NAME);
      laneSymbol.setXPos(10);
      laneSymbol.setYPos(10);
      laneSymbol.setWidth(480);
      laneSymbol.setHeight(580);
      laneSymbol.setOrientation(OrientationType.VERTICAL_LITERAL);

      processDefinition.getDiagram().add(diagram);

      newManualTrigger(processDefinition).accessibleTo(ADMINISTRATOR_ROLE).build();
      return processDefinition;
   }

   public LaneSymbol createLane(String modelId, ModelType model,
         ProcessDefinitionType processDefinition, String laneId, String laneName,
         int xPos, int yPos, int width, int height, String orientation,
         String participantFullID)
   {
      long maxOid = XpdlModelUtils.getMaxUsedOid(model);

      LaneSymbol laneSymbol = AbstractElementBuilder.F_CWM.createLaneSymbol();

      processDefinition.getDiagram().get(0).getPoolSymbols().get(0).getChildLanes()
            .add(laneSymbol);

      laneSymbol.setElementOid(++maxOid);

      laneSymbol.setId(laneId);
      laneSymbol.setName(laneName);
      laneSymbol.setXPos(xPos);
      laneSymbol.setYPos(yPos);
      laneSymbol.setWidth(width);
      laneSymbol.setHeight(height);

      if (orientation.equals(ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL))
      {
         laneSymbol.setOrientation(OrientationType.HORIZONTAL_LITERAL);
      }
      else
      {
         laneSymbol.setOrientation(OrientationType.VERTICAL_LITERAL);
      }

      // TODO Deal with full Ids

      if (participantFullID != null)
      {

         String participantModelID = getModelId(participantFullID);
         if (StringUtils.isEmpty(participantModelID))
         {
            participantModelID = modelId;
         }
         ModelType participantModel = model;
         if (!participantModelID.equals(modelId))
         {
            participantModel = getModelManagementStrategy().getModels().get(
                  participantModelID);
         }

         IModelParticipant modelParticipant = findParticipant(
               getModelManagementStrategy().getModels().get(participantModelID),
               stripFullId(participantFullID));

         if (!participantModelID.equals(modelId))
         {
            String fileConnectionId = JcrConnectionManager.createFileConnection(model,
                  participantModel);

            String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
            URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

            ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(
                  uri, modelParticipant, bundleId, null, true);

            PepperIconFactory iconFactory = new PepperIconFactory();

            descriptor.importElements(iconFactory, model, true);
         }

         laneSymbol.setParticipant(modelParticipant);
      }
      return laneSymbol;
   }

   public ModelType findModel(String modelId)
   {
      return getModelManagementStrategy().getModels().get(modelId);
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public ProcessDefinitionType findProcessDefinition(ModelType model, String id)
   {
      for (ProcessDefinitionType processDefinition : model.getProcessDefinition())
      {
         if (processDefinition.getId().equals(id))
         {
            return processDefinition;
         }
      }

      throw new ObjectNotFoundException("Process Definition " + id + " does not exist.");
   }

   /**
    * 
    * @param modelId
    * @param id
    * @return
    */
   public ProcessDefinitionType getProcessDefinition(String modelId, String id)
   {
      return findProcessDefinition(findModel(modelId), id);
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public ApplicationType findApplication(ModelType model, String id)
   {
      for (ApplicationType application : model.getApplication())
      {
         if (application.getId().equals(id))
         {
            return application;
         }
      }

      throw new ObjectNotFoundException("Application " + id + " does not exist.");
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public ApplicationTypeType findApplicationTypeType(ModelType model, String id)
   {
      for (ApplicationTypeType applicationType : model.getApplicationType())
      {
         if (applicationType.getId().equals(id))
         {
            return applicationType;
         }
      }

      throw new ObjectNotFoundException("Application type " + id + " does not exist.");
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public ApplicationContextTypeType findApplicationContextTypeType(ModelType model,
         String id)
   {
      for (ApplicationContextTypeType applicationContextType : model
            .getApplicationContextType())
      {
         if (applicationContextType.getId().equals(id))
         {
            return applicationContextType;
         }
      }

      throw new ObjectNotFoundException("Application Context type " + id
            + " does not exist.");
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public TypeDeclarationType findTypeDeclaration(ModelType model, String id)
   {
      for (TypeDeclarationType typeDeclaration : model.getTypeDeclarations()
            .getTypeDeclaration())
      {
         if (typeDeclaration.getId().equals(id))
         {
            return typeDeclaration;
         }
      }

      throw new ObjectNotFoundException("Type declaration " + id + " does not exist.");
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public DataTypeType findDataType(ModelType model, String id)
   {
      for (DataTypeType dataType : model.getDataType())
      {
         if (dataType.getId().equals(id))
         {
            return dataType;
         }
      }

      throw new ObjectNotFoundException("Data type " + id + " does not exist.");
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public TypeDeclarationType findStructuredDataType(ModelType model, String id)
   {
      for (TypeDeclarationType structType : model.getTypeDeclarations()
            .getTypeDeclaration())
      {
         if (structType.getId().equals(id))
         {
            return structType;
         }
      }

      throw new ObjectNotFoundException("Data type " + id + " does not exist.");
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public DataType findData(ModelType model, String id)
   {
      for (DataType data : model.getData())
      {
         if (data.getId().equals(id))
         {
            return data;
         }
      }

      throw new ObjectNotFoundException("Data " + id + " does not exist.");
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public IModelParticipant findParticipant(ModelType model, String id)
   {
      for (RoleType role : model.getRole())
      {
         if (role.getId().equals(id))
         {
            return role;
         }
      }

      for (OrganizationType organization : model.getOrganization())
      {
         if (organization.getId().equals(id))
         {
            return organization;
         }
      }

      throw new ObjectNotFoundException("Participant " + id + " does not exist.");
   }

   /**
    * 
    * @param diagram
    * @param oid
    * @return
    */
   public DataSymbolType findDataSymbol(DiagramType diagram, long oid)
   {
      for (DataSymbolType dataSymbol : diagram.getDataSymbol())
      {
         if (dataSymbol.getElementOid() == oid)
         {
            return dataSymbol;
         }
      }

      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol childLaneSymbol : poolSymbol.getChildLanes())
         {
            DataSymbolType dataSymbol = findDataSymbolRecursively(childLaneSymbol, oid);

            if (dataSymbol != null)
            {
               return dataSymbol;
            }
         }
      }

      throw new ObjectNotFoundException("Data Symbol " + oid + " does not exist.");
   }

   /**
    * 
    * @param laneSymbol
    * @param oid
    * @return
    */
   public DataSymbolType findDataSymbolRecursively(LaneSymbol laneSymbol, long oid)
   {
      for (DataSymbolType dataSymbol : laneSymbol.getDataSymbol())
      {
         if (dataSymbol.getElementOid() == oid)
         {
            return dataSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         DataSymbolType dataSymbol = findDataSymbolRecursively(childLaneSymbol, oid);

         if (dataSymbol != null)
         {
            return dataSymbol;
         }
      }

      return null;
   }

   /**
    * 
    * @param processDefinition
    * @param id
    * @return
    */
   public ActivityType findActivity(ProcessDefinitionType processDefinition, String id)
   {
      for (ActivityType activity : processDefinition.getActivity())
      {
         if (activity.getId().equals(id))
         {
            return activity;
         }
      }

      throw new ObjectNotFoundException("Activity " + id + " does not exist.");
   }

   /**
    * 
    * @param diagram
    * @param oid
    * @return
    */
   public ActivitySymbolType findActivitySymbol(DiagramType diagram, long oid)
   {
      LaneSymbol laneSymbol = findLaneContainingActivitySymbol(diagram, oid);

      if (laneSymbol != null)
      {
         return findActivitySymbol(laneSymbol, oid);
      }

      return null;
   }

   /**
    * 
    * @param laneSymbol
    * @param oid
    * @return
    */
   public ActivitySymbolType findActivitySymbol(LaneSymbol laneSymbol, long oid)
   {
      for (ActivitySymbolType activitySymbol : laneSymbol.getActivitySymbol())
      {
         if (activitySymbol.getElementOid() == oid)
         {
            return activitySymbol;
         }
      }

      return null;
   }

   /**
    * 
    * @param diagram
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingActivitySymbol(DiagramType diagram, long oid)
   {
      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol containingLaneSymbol = findLaneContainingActivitySymbolRecursively(
                  laneSymbol, oid);

            if (containingLaneSymbol != null)
            {
               return containingLaneSymbol;
            }
         }
      }

      return null;
   }

   public LaneSymbol findLaneContainingActivitySymbolRecursively(LaneSymbol laneSymbol,
         long oid)
   {
      for (ActivitySymbolType activitySymbol : laneSymbol.getActivitySymbol())
      {
         if (activitySymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol containingLaneSymbol = findLaneContainingActivitySymbolRecursively(
               childLaneSymbol, oid);

         if (containingLaneSymbol != null)
         {
            return containingLaneSymbol;
         }
      }

      return null;
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public LaneSymbol findLaneInProcess(ProcessDefinitionType processDefinition, String id)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol foundLaneSymbol = findLaneRecursively(laneSymbol, id);

            if (foundLaneSymbol != null)
            {
               return foundLaneSymbol;
            }
         }
      }

      return null;
   }

   /**
    * 
    * @param diagram
    * @param oid
    * @return
    */
   public StartEventSymbol findStartEventSymbol(DiagramType diagram, long oid)
   {
      LaneSymbol laneSymbol = findLaneContainingStartEventSymbol(diagram, oid);

      if (laneSymbol != null)
      {
         return findStartEventSymbol(laneSymbol, oid);
      }

      return null;
   }

   /**
    * 
    * @param laneSymbol
    * @param oid
    * @return
    */
   public StartEventSymbol findStartEventSymbol(LaneSymbol laneSymbol, long oid)
   {
      for (StartEventSymbol startEventSymbol : laneSymbol.getStartEventSymbols())
      {
         if (startEventSymbol.getElementOid() == oid)
         {
            return startEventSymbol;
         }
      }

      return null;
   }

   /**
    * 
    * @param diagram
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingStartEventSymbol(DiagramType diagram, long oid)
   {
      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol childLaneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol containingLaneSymbol = findLaneContainingStartEventSymbolRecursively(
                  childLaneSymbol, oid);

            if (containingLaneSymbol != null)
            {
               return containingLaneSymbol;
            }
         }
      }

      return null;
   }

   /**
    * 
    * @param laneSymbol
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingStartEventSymbolRecursively(LaneSymbol laneSymbol,
         long oid)
   {
      for (StartEventSymbol startEventSymbol : laneSymbol.getStartEventSymbols())
      {
         if (startEventSymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol containingLaneSymbol = findLaneContainingStartEventSymbolRecursively(
               childLaneSymbol, oid);

         if (containingLaneSymbol != null)
         {
            return containingLaneSymbol;
         }
      }

      return null;
   }

   /**
    * 
    * @param diagram
    * @param oid
    * @return
    */
   public EndEventSymbol findEndEventSymbol(DiagramType diagram, long oid)
   {
      LaneSymbol laneSymbol = findLaneContainingEndEventSymbol(diagram, oid);

      if (laneSymbol != null)
      {
         return findEndEventSymbol(laneSymbol, oid);
      }

      return null;
   }

   /**
    * 
    * @param laneSymbol
    * @param oid
    * @return
    */
   public EndEventSymbol findEndEventSymbol(LaneSymbol laneSymbol, long oid)
   {
      for (EndEventSymbol endEventSymbol : laneSymbol.getEndEventSymbols())
      {
         if (endEventSymbol.getElementOid() == oid)
         {
            return endEventSymbol;
         }
      }

      return null;
   }

   /**
    * 
    * @param diagram
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingEndEventSymbol(DiagramType diagram, long oid)
   {
      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol childLaneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol containingLaneSymbol = findLaneContainingEndEventSymbolRecursively(
                  childLaneSymbol, oid);

            if (containingLaneSymbol != null)
            {
               return containingLaneSymbol;
            }
         }
      }

      return null;
   }

   /**
    * 
    * @param laneSymbol
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingEndEventSymbolRecursively(LaneSymbol laneSymbol,
         long oid)
   {
      for (EndEventSymbol endEventSymbol : laneSymbol.getEndEventSymbols())
      {
         if (endEventSymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol containingLaneSymbol = findLaneContainingEndEventSymbolRecursively(
               childLaneSymbol, oid);

         if (containingLaneSymbol != null)
         {
            return containingLaneSymbol;
         }
      }

      return null;
   }

   /**
    * 
    * @param laneSymbol
    * @param id
    * @return
    */
   public LaneSymbol findLaneRecursively(LaneSymbol laneSymbol, String id)
   {
      if (laneSymbol.getId().equals(id))
      {
         return laneSymbol;
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol foundLaneSymbol = findLaneRecursively(childLaneSymbol, id);

         if (foundLaneSymbol != null)
         {
            return foundLaneSymbol;
         }
      }

      return null;
   }

   /**
    * 
    * @param model
    * @param id
    * @return
    */
   public LaneSymbol findLaneContainingActivitySymbol(
         ProcessDefinitionType processDefinition, ActivitySymbolType activitySymbol)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol foundLaneSymbol = findLaneContainingActivitySymbolRecursively(
                  laneSymbol, activitySymbol);

            if (foundLaneSymbol != null)
            {
               return foundLaneSymbol;
            }
         }
      }

      throw new ObjectNotFoundException(
            "Lane symbol containing Activity Symbol for Activity "
                  + activitySymbol.getActivity().getId() + " cannot be found.");
   }

   /**
    * 
    * @param laneSymbol
    * @param id
    * @return
    */
   public LaneSymbol findLaneContainingActivitySymbolRecursively(LaneSymbol laneSymbol,
         ActivitySymbolType activitySymbol)
   {
      if (laneSymbol.getActivitySymbol().contains(activitySymbol))
      {
         return laneSymbol;
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol foundLaneSymbol = findLaneContainingActivitySymbolRecursively(
               childLaneSymbol, activitySymbol);

         if (foundLaneSymbol != null)
         {
            return foundLaneSymbol;
         }
      }

      return null;
   }

   /**
    * 
    * @param processDefinition
    * @param oid
    * @return
    */
   public TransitionConnectionType findTransitionConnectionByModelOid(
         ProcessDefinitionType processDefinition, long oid)
   {
      for (TransitionConnectionType transitionConnection : processDefinition.getDiagram()
            .get(0).getTransitionConnection())
      {
         if (transitionConnection.getElementOid() == oid)
         {
            return transitionConnection;
         }
      }

      // Lookup transition in first/default pool

      // TODO Support multiple pools

      for (TransitionConnectionType transitionConnection : processDefinition.getDiagram()
            .get(0).getPoolSymbols().get(0).getTransitionConnection())
      {
         if (transitionConnection.getElementOid() == oid)
         {
            return transitionConnection;
         }
      }

      throw new ObjectNotFoundException("Could not find Transition " + oid + ".");
   }

   /**
    * 
    * @param processDefinition
    * @param oid
    * @return
    */
   public DataMappingConnectionType findDataMappingConnectionByModelOid(
         ProcessDefinitionType processDefinition, long oid)
   {
      for (DataMappingConnectionType dataMappingConnectionType : processDefinition
            .getDiagram().get(0).getDataMappingConnection())
      {
         if (dataMappingConnectionType.getElementOid() == oid)
         {
            return dataMappingConnectionType;
         }
      }

      throw new ObjectNotFoundException("Could not find " + oid + ".");
   }

   /**
    * 
    * @param processDefinition
    * @param oid
    * @return
    */
   public PoolSymbol findPoolSymbolByElementOid(ProcessDefinitionType processDefinition,
         long oid)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         if (poolSymbol.getElementOid() == oid)
         {
            return poolSymbol;
         }
      }

      throw new ObjectNotFoundException("Could not find Pool Symbol with OID " + oid
            + ".");
   }

   /**
    * 
    * @param poolSymbol
    * @param oid
    * @return
    */
   public LaneSymbol findLaneSymbolByElementOid(PoolSymbol poolSymbol, long oid)
   {
      for (LaneSymbol laneSymbol : poolSymbol.getLanes())
      {
         if (laneSymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      throw new ObjectNotFoundException("Could not find Lane Symbol with OID " + oid
            + ".");
   }

   /**
    * 
    * @param poolSymbol
    * @param oid
    * @return
    */
   public LaneSymbol findLaneSymbolById(ProcessDefinitionType processDefinition, String id)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getLanes())
         {

            // TODO Recursion

            if (laneSymbol.getId().equals(id))
            {
               return laneSymbol;
            }
         }
      }

      throw new ObjectNotFoundException("Could not find Lane Symbol with ID " + id + ".");
   }

   /**
    * 
    * @param modelId
    * @param appId
    * @return
    */
   public ApplicationType getApplication(String modelId, String appId)
   {
      ModelType model = getModelManagementStrategy().getModels().get(modelId);
      List<ApplicationType> apps = model.getApplication();
      for (ApplicationType a : apps)
      {
         if (a.getId().equals(appId))
         {
            return a;
         }
      }

      return null;
   }

   /**
    * TODO Replace by Eclipse modeler logic
    * 
    * @param name
    * @return
    */
   public String createIdFromName(String name)
   {
      return name.replace(" ", "_");
   }

   /**
    * 
    * @param model
    * @param modelElement
    * @return
    */
   public String createFullId(ModelType model, IIdentifiableModelElement modelElement)
   {
      // TODO Change to {modelId}elementId

      return model.getId() + ":" + modelElement.getId();
   }

   /**
    * TODO Auxiliary method while cross-model references are not supported
    * 
    * @param fullId
    * @return
    */
   public String stripFullId(String fullId)
   {
      String[] ids = fullId.split(":");

      return ids[ids.length - 1];
   }

   /**
    * Retrieves the model ID of a full ID (e.g. ModelA for ModelA:CreateCustomer).
    * 
    * @param fullId
    * @return
    */
   public String getModelId(String fullId)
   {
      String[] ids = fullId.split(":");

      return ids[0];
   }
}
