/*******************************************************************************
 * Copyright (c) 2012, 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
/**
 *
 */
package org.eclipse.stardust.model.xpdl.builder.compatibility.iam;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.*;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

/**
 * The class provides building block functionality for creating IPP model artifacts from
 * EMF (Eclipse modeling framework) classes. The methods create and return artifacts
 * without dependency on any other artifacts. While not every artifact is covered, the
 * class provides way to create most commonly used elements.
 *
 * NOTE: Each artifact in IPP requires a unique oid regardless of its context. The
 * responsibility of ensuring
 *
 * @author Ammar.Hassan
 * @version 1.0
 *
 */
public abstract class ModelFactory
{
   public static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   static CarnotWorkflowModelFactory elementFactory = CarnotWorkflowModelFactory.eINSTANCE;

   protected static <T extends IIdentifiableElement> T createIdentifiableElement(
         EClass eType, String id, String name)
   {
      @SuppressWarnings("unchecked")
      T element = (T) elementFactory.create(eType);

      element.setId(id);
      element.setName(name);

      return element;
   }

   // ----------------------------------------------------------
   // Create Artifacts
   // ----------------------------------------------------------

   /**
    * Creates and returns an IPP model. The models created has a default ModelOid of 1 and
    * the oid of 2.
    * <p>
    *
    * @param id
    *           the id of the model
    * @param name
    *           the name of the model to be displayed
    * @return ModelType object representing the EMF equivalent of the model
    */
   public static ModelType createModel(String id, String name)
   {
      ModelType model = createIdentifiableElement(PKG_CWM.getModelType(), id, name);

      model.setModelOID(1);
      model.setOid(2);

      return model;
   }

   /**
    * Creates and returns an IPP process definition. The process definition is however,
    * unbounded to the model. Thus, there is a need to explicitly add the data to the
    * model
    * <p>
    *
    * @param oid
    *           the unique oid of the process definition in the model
    * @param model
    *           the model to attach the data to
    * @param id
    *           the id of the process definition
    * @param name
    *           the name of the process definition
    * @return ProcessDefinitionType object representing the EMF equivalent of the process
    */
   public static ProcessDefinitionType createProcessDefinition(long oid, ModelType model, String id,
         String name)
   {
      return newProcessDefinition(model).withIdAndName(id, name).build();
   }

   public static RoleType createRole(long oid, ModelType model, String id, String name)
         throws DuplicateIdException
   {
      if (isRolePresent(model, id))
      {
         throw new DuplicateIdException("The Role seems to be already present");
      }

      RoleType newRole = newRole(model).withIdAndName(id, name).build();

      System.out.println("The Created Role ID is:" + newRole.getId());

      return newRole;
   }

   public static OrganizationType createOrganization(long oid, ModelType model, String id, String name)
         throws DuplicateIdException
   {
      if (isRolePresent(model, id))
      {
         throw new DuplicateIdException("The Organization seems to be already present");
      }

      OrganizationType newOrg = newOrganization(model).withIdAndName(id, name).build();

      System.out.println("The Created Role ID is:" + newOrg.getId());

      return newOrg;
   }

   public static ConditionalPerformerType createConditionalPerformer(long oid,
         ModelType model, String id, String name, DataType data, String dataPath,
         String perfKind, boolean isUser)
   {
      ConditionalPerformerType conPerfomer = null;

      if (isUser || PredefinedConstants.CONDITIONAL_PERFORMER_KIND_USER.equals(perfKind))
      {
         conPerfomer = newConditionalPerformer(model).withIdAndName(id, name)
               .resolvingToUser()
               .basedOnVariable(data, dataPath)
               .build();
      }
      else if (PredefinedConstants.CONDITIONAL_PERFORMER_KIND_MODEL_PARTICIPANT.equals(perfKind))
      {
         conPerfomer = newConditionalPerformer(model).withIdAndName(id, name)
               .resolvingToModelParticipant()
               .basedOnVariable(data)
               .build();
      }
      else if (PredefinedConstants.CONDITIONAL_PERFORMER_KIND_MODEL_PARTICIPANT_OR_USER_GROUP.equals(perfKind))
      {
         conPerfomer = newConditionalPerformer(model).withIdAndName(id, name)
               .resolvingToModelParticipantOrUserGroup()
               .basedOnVariable(data)
               .build();
      }
      else if (PredefinedConstants.CONDITIONAL_PERFORMER_KIND_USER_GROUP.equals(perfKind))
      {
         conPerfomer = newConditionalPerformer(model).withIdAndName(id, name)
               .resolvingToUserGroup()
               .basedOnVariable(data)
               .build();
      }

      return conPerfomer;
   }

   /**
    * Creates and returns a data object for the model. The data object is added to the
    * model as well
    * <p>
    *
    * @param oid
    *           the unique oid of the data in the model
    * @param model
    *           the model to attach the data to
    * @param id
    *           the id of the data
    * @param name
    *           the name of the data
    * @param primitiveType
    *           the primitive's type (e.g. String, long etc for primitive data)
    * @param defaultValue
    *           the default value of the data object
    * @return DataType object representing the EMF equivalent of the data
    */
   public static DataType createPrimitiveData(long oid, ModelType model, String id,
         String name, Type primitiveType, Object defaultValue)
   {
      return newPrimitiveVariable(model).withIdAndName(id, name)
            .ofType(primitiveType)
            .havingDefaultValue(defaultValue)
            .build();
   }

   /**
    * Creates and returns a structured data. (NOTE: The functionality is not complete yet
    * as a couple of attribute types are missing. The method is not in at the moment for
    * version 1 of Project Rooftop.
    * <p>
    *
    * @param oid
    *           the unique oid of the structured data element in the model
    * @param model
    *           the model to attach the data to
    * @param id
    *           the id of the data
    * @param name
    *           the name of the data
    * @param subType
    *           the type of the structured data
    * @return ProcessDefinitionType object representing the EMF equivalent of the process
    */
   public static DataType createStructuredData(long oid, ModelType model, String id,
         String name, String subType)
   {
      return newStructVariable(model).withIdAndName(id, name).ofType(subType).build();
   }

   /**
    * Creates and returns a data object for the model. The data object is added to the
    * model as well
    * <p>
    *
    * @param oid
    *           the unique oid of the data in the model
    * @param model
    *           the model to attach the data to
    * @param id
    *           the id of the data
    * @param name
    *           the name of the data
    * @param type
    *           the type of data (e.g. Primitive, Serializable etc). (Value:
    *           PredefinedConstants.PRIMITIVE_DATA)
    * @param subType
    *           the subType (e.g. String, long etc for primitive data) (Value: "String",
    *           "long", "boolean".. for Primitive)
    * @param defaultValue
    *           the default value of the data object
    * @return DataType object representing the EMF equivalent of the data
    */
   public static DataType createData(long oid, ModelType model, String id, String name,
         String type, String subType, Object defaultValue)
   {
      DataType data = createIdentifiableElement(PKG_CWM.getDataType(), id, name);

      data.setElementOid(oid);

      data.setType((DataTypeType) ModelUtils.findIdentifiableElement(model.getDataType(),
            type));

      // Sets the type attribute (compulsory)
      // TODO attribute type
      AttributeUtil.setAttribute(data, PredefinedConstants.TYPE_ATT,
            "ag.carnot.workflow.spi.providers.data.java.Type", subType);

      if (null != defaultValue)
      {
         AttributeUtil.setAttribute(data, PredefinedConstants.DEFAULT_VALUE_ATT,
               defaultValue.toString());
      }

      if ( !isDataPresent(model, data.getId()))
      {
         model.getData().add(data);
      }

      return data;
   }

   /**
    * Creates and returns an event handler (to exclude a user from performing an
    * activity). The event handler created is not binded to an activity hence can be used
    * freely for desired activity object.
    * <p>
    *
    * @param model
    *           the model to which the event handler should belong
    * @param handlerOid
    *           the handlerOid the unique oid of the handler for the model
    * @param handlerId
    *           the id for the handler object
    * @param handlerType
    *           the type of handler. (Value: is
    *           PredefinedConstants.ACTIVITY_ON_ASSIGNMENT_CONDITION)
    * @param eventOid
    *           the Oid for the event object to bind to the handle
    * @param eventId
    *           the id for the event object
    * @param eventName
    *           the name of the event Object
    * @param eventType
    *           the type of event (Value: is to exclude a user from performing the
    *           activity: PredefinedConstants.EXCLUDE_USER_ACTION
    * @param atributeType
    *           the type of attribute for the event action
    *           (Value:PredefinedConstants.EXCLUDED_PERFORMER_DATA)
    * @param attributeValue
    *           the id of the data for the userGroup
    * @return EventHandlerType object representing the EMF equivalents of event handler
    */
   public static EventHandlerType createEventHandlerToExcludeUser(ModelType model, ActivityType activity,
         long handlerOid, String handlerId, String handlerName, String handlerType,
         long eventOid, String eventId, String eventName, String eventType,
         String attributeType, String attributeValue)
   {
      EventHandlerType handler = null;
      if (PredefinedConstants.ACTIVITY_ON_ASSIGNMENT_CONDITION.equals(handlerType))
      {
         handler = newActivityAssignmentHandler(activity).withIdAndName(handlerId,
               handlerName).build();
      }
      else
      {
         handler = elementFactory.createEventHandlerType();
         handler.setElementOid(handlerOid);
         handler.setId(handlerId);
         handler.setName(handlerName);
         handler.setType((EventConditionTypeType) ModelUtils.findIdentifiableElement(
               model.getEventConditionType(), handlerType));
      }

      EventActionType eventAction = null;
      if (PredefinedConstants.EXCLUDE_USER_ACTION.equals(eventType)
            && PredefinedConstants.EXCLUDED_PERFORMER_DATA.equals(attributeType))
      {
         eventAction = newExcludeUserAction(handler).withIdAndName(eventId, eventName)
               .basedOnVariable(attributeValue)
               .build();
      }
      else
      {
         eventAction = elementFactory.createEventActionType();

         eventAction.setElementOid(eventOid);
         eventAction.setId(eventId);
         eventAction.setName(eventName);
         eventAction.setType((EventActionTypeType) ModelUtils.findIdentifiableElement(
               model.getEventActionType(), eventType));

         AttributeUtil.setAttribute(eventAction, attributeType, attributeValue);

         handler.getEventAction().add(eventAction);
      }

      return handler;
   }

   /**
    * Creates and returns a data object for the model. The data object is added to the
    * model as well
    * <p>
    *
    * @param oid
    *           the unique oid for the data mapping in the model
    * @param id
    *           the id of the data mapping
    * @param name
    *           the name of the data
    * @param type
    *           the type of data (e.g. Primitive, Serializable etc). (Value:
    *           PredefinedConstants.PRIMITIVE_DATA)
    * @param subType
    *           the subType (e.g. String, long etc for primitive data) (Value: "String",
    *           "long", "boolean".. for Primitive)
    * @param defaultValue
    *           the default value of the data object
    * @return DataType object representing the EMF equivalent of the data
    */
   public static DataMappingType createDataMapping(long oid, ActivityType activity,
         String id, DirectionType direction, String context, String accessPoint,
         String accessPointPath, DataType data)
   {
      DataMappingType dataMapping = null;

      if (DirectionType.IN_LITERAL == direction)
      {
         newInDataMapping(activity).withId(id)
               .inContext(context)
               .fromVariable(data)
               .toApplicationAccessPoint(accessPoint, accessPointPath)
               .build();
      }
      else if (DirectionType.OUT_LITERAL == direction)
      {
         newOutDataMapping(activity).withId(id)
               .inContext(context)
               .fromApplicationAccessPoint(accessPoint, accessPointPath)
               .toVariable(data)
               .build();
      }

      return dataMapping;
   }

   public static ActivityType createActivity(long oid, ProcessDefinitionType process,
         String id, String name, ActivityImplementationType implementation,
         IModelParticipant performer, ApplicationType application, JoinSplitType split,
         JoinSplitType join, ProcessDefinitionType subProcess,
         SubProcessModeType subProcessMode, boolean hibernateOnCreation)
   {
      ActivityType activity = null;

      if (implementation == ActivityImplementationType.ROUTE_LITERAL)
      {
         // If the implementation is route
         activity = newRouteActivity(process).withIdAndName(id, name)
               .usingControlFlow(join, split)
               .build();
      }
      if (implementation == ActivityImplementationType.APPLICATION_LITERAL)
      {
         // If the implementation is application
         if (application.isInteractive())
         {
            activity = newInteractiveApplicationActivity(process).withIdAndName(id, name)
                  .usingControlFlow(join, split)
                  .havingDefaultPerformer(performer)
                  .usingApplication(application)
                  .build();
         }
         else
         {
            activity = newApplicationActivity(process).withIdAndName(id, name)
                  .usingControlFlow(join, split)
                  .invokingApplication(application)
                  .build();
         }
      }
      if (implementation == ActivityImplementationType.MANUAL_LITERAL)
      {
         // If implementation type is manual
         activity = newManualActivity(process).withIdAndName(id, name)
               .usingControlFlow(join, split)
               .havingDefaultPerformer(performer)
               .build();

      }
      else if (implementation == ActivityImplementationType.SUBPROCESS_LITERAL)
      {
         // If implementation type is Subprocess
         activity = newSubProcessActivity(process).withIdAndName(id, name)
               .usingControlFlow(join, split)
               .invokingProcess(subProcess)
               .usingMode(subProcessMode)
               .build();
      }

      if (hibernateOnCreation)
      {
         activity.setHibernateOnCreation(true);
      }

      return activity;
   }

   public static void setActivityMetaData(ActivityType activity, String attribute,
         String value)
   {
      AttributeUtil.createAttribute(attribute);
      AttributeUtil.setAttribute(activity, attribute, value);
   }

   public static DataType setData(ModelType model, String id, Object value)
   {
      DataType data = getData(model, id);
      AttributeUtil.setAttribute(data, PredefinedConstants.DEFAULT_VALUE_ATT,
            value.toString());
      return data;
   }

   /*
    * @SuppressWarnings("unchecked") public static ApplicationType
    * createJSFApplication(long oid, String id, String name, String type, String
    * className, String completionMethod, String managedBean, String url) throws
    * IdAlreadyPresentException {
    *
    * ApplicationType application = elementFactory.createApplicationType();
    * application.setId(id); application.setName(name); application.setElementOid(oid);
    * application.setInteractive(true);
    *
    * if (isApplicationPresent(model, id)) throw new IdAlreadyPresentException(
    * "The Application seems to be already present");
    *
    * ContextType jsfContext = elementFactory.createContextType();
    * application.getContext().add(jsfContext);
    *
    * jsfContext.setElementOid(oid+1); // No Id or Name
    * jsfContext.setType((ApplicationContextTypeType) ModelUtils
    * .findIdentifiableElement(model.getApplicationContextType(), type));
    *
    * AttributeUtil.setAttribute(jsfContext, PredefinedConstants.CLASS_NAME_ATT,
    * className); AttributeUtil.setAttribute(jsfContext,
    * PredefinedConstants.METHOD_NAME_ATT, completionMethod);
    * AttributeUtil.setAttribute(jsfContext, JSFContextPropertyPage.COMPONENT_KIND_ATT,
    * "facelets"); AttributeUtil.setAttribute(jsfContext,
    * JSFContextPropertyPage.COMPONENT_URL_ATT, url);
    * AttributeUtil.setAttribute(jsfContext, JSFContextPropertyPage.MANAGED_BEAN_NAME_ATT,
    * managedBean);
    *
    * model.getApplication().add(application);
    *
    * return application; }
    */
   public static DataPathType createDataPath(long oid, ProcessDefinitionType process, String id, String name,
         DataType data, DirectionType direction, boolean hasDescriptor)
   {
      DataPathType dataPath = null;
      if (hasDescriptor)
      {
         dataPath = newDescriptor(process).withIdAndName(id, name)
               .onVariable(data)
               .build();
      }
      else if (DirectionType.IN_LITERAL == direction)
      {
         dataPath = newDataPath(process).withIdAndName(id, name)
               .onVariable(data)
               .forReading()
               .build();
      }
      else if (DirectionType.OUT_LITERAL == direction)
      {
         dataPath = newDataPath(process).withIdAndName(id, name)
               .onVariable(data)
               .forWriting()
               .build();
      }
      else if (DirectionType.INOUT_LITERAL == direction)
      {
         dataPath = newDataPath(process).withIdAndName(id, name)
               .onVariable(data)
               .forReadWrite()
               .build();
      }

      return dataPath;
   }

   public static TransitionType createTransition(long oid, ProcessDefinitionType process, String id, String name,
         ActivityType from, ActivityType to, String conditionType, String condition)
   {
      TransitionType transition = null;

      if (conditionType.equalsIgnoreCase("CONDITION"))
      {
         transition = newTransition(process).withIdAndName(id, name)
               .betweenActivities(from, to)
               .onCondition(condition)
               .build();
      }
      else if (conditionType.equalsIgnoreCase("OTHERWISE"))
      {
         transition = newOtherwiseTransition(process).withIdAndName(id, name)
               .betweenActivities(from, to)
               .build();
      }

      return transition;
   }

   public static TriggerType createManualTrigger(long oid, ProcessDefinitionType process,
         String id, String name, String triggerTypeValue, String roleName)
   {
      return newManualTrigger(process).withIdAndName(id, name)
            .accessibleTo(roleName)
            .build();
   }

   public static DiagramType createDiagram(long oid, String name,
         OrientationType orientation)
   {
      DiagramType diagram = elementFactory.createDiagramType();
      diagram.setElementOid(oid);
      diagram.setName(name);
      diagram.setOrientation(orientation);

      return diagram;
   }

   // ---------------------------------------------------------------
   // Remove Functions
   // ---------------------------------------------------------------

   public static void removeProcessDefinitionById(ModelType model, String id)
   {
      for (Iterator<ProcessDefinitionType> iterator = getProcessDefinitions(model); iterator.hasNext();)
      {
         ProcessDefinitionType processDefinitionType = iterator.next();
         if (processDefinitionType.getId().equals(id))
         {
            iterator.remove();
         }
      }
   }

   public static void removeProcessDefinitionByName(ModelType model, String name)
   {
      for (Iterator<ProcessDefinitionType> iterator = getProcessDefinitions(model); iterator.hasNext();)
      {
         ProcessDefinitionType processDefinitionType = iterator.next();
         if (processDefinitionType.getId().equals(name))
         {
            iterator.remove();
         }
      }
   }

   // ---------------------------------------------------------------
   // Utility Get Functions
   // ---------------------------------------------------------------

   public static ApplicationType getApplication(ModelType model, String applicationId)
   {
      Iterator<ApplicationType> application = model.getApplication().iterator();
      for (; application.hasNext();)
      {
         ApplicationType applicationType = application.next();
         if (applicationType.getId().equals(applicationId))
            return applicationType;
      }
      return null;
   }

   public static DataType getData(ModelType model, String id)
   {
      DataType data = null;
      if (isDataPresent(model, id))
      {
         for (Iterator<DataType> iterator = ((List<DataType>) model.getData()).iterator(); iterator.hasNext();)
         {
            DataType dataType = iterator.next();
            if (dataType.getId().equals(id))
            {
               data = dataType;
            }
         }
      }
      return data;
   }

   public static Iterator<ProcessDefinitionType> getProcessDefinitions(ModelType model)
   {
      return model.getProcessDefinition().iterator();
   }

   protected static boolean isRolePresent(ModelType model, String roleID)
   {
      Iterator<RoleType> iterator = model.getRole().iterator();
      while (iterator.hasNext())
      {
         if (iterator.next().getId().equals(roleID))
            return true;
      }
      return false;
   }

   protected static boolean isActivityPresent(ProcessDefinitionType process,
         String activityID)
   {
      Iterator<ActivityType> iterator = process.getActivity().iterator();
      while (iterator.hasNext())
      {
         if (iterator.next().getId().equals(activityID))
            return true;
      }
      return false;
   }

   protected static boolean isDataPresent(ModelType model, String DataID)
   {
      Iterator<DataType> iterator = model.getData().iterator();
      while (iterator.hasNext())
      {
         if (iterator.next().getId().equals(DataID))
            return true;
      }
      return false;
   }

   protected static boolean isApplicationPresent(ModelType model, String appId)
   {
      Iterator<ApplicationType> appObjects = model.getApplication().iterator();

      while (appObjects.hasNext())
      {
         ApplicationType applicationType = appObjects.next();
         if (applicationType.getId().equals(appId))
            return true;
      }
      return false;
   }

   protected static boolean isIdPresent(String DataID)
   {
      // This function will check to see if an ID already exists in similar
      // context

      return false;
   }

   public static RoleType getARole(ModelType model, String roleId)
   {
      Iterator<RoleType> allRoles = model.getRole().iterator();
      for (; allRoles.hasNext();)
      {
         RoleType roleType = allRoles.next();
         if (roleType.getId().equals(roleId))
            return roleType;
      }
      return null;
   }

   public static ConditionalPerformerType getAConditionalPerformer(ModelType model,
         String id)
   {
      Iterator<ConditionalPerformerType> allRoles = model.getConditionalPerformer()
            .iterator();
      for (; allRoles.hasNext();)
      {
         ConditionalPerformerType role = allRoles.next();
         if (role.getId().equals(id))
            return role;
      }
      return null;
   }

   public static ProcessDefinitionType getProcessDefinition(ModelType model,
         String processId)
   {
      Iterator<ProcessDefinitionType> allProcesses = model.getProcessDefinition()
            .iterator();
      for (; allProcesses.hasNext();)
      {
         ProcessDefinitionType processType = allProcesses.next();

         if (processType.getId().equals(processId))
            return processType;
      }
      return null;
   }

   public static ActivitySymbolType createActivitySymbol(long oid, ActivityType activity,
         int xPos, int yPos)
   {

      ActivitySymbolType actSymbol = elementFactory.createActivitySymbolType();
      actSymbol.setElementOid(oid);
      actSymbol.setActivity(activity);
      actSymbol.setXPos(xPos);
      actSymbol.setYPos(yPos);

      String joinKind = activity.getJoin().getLiteral();
      if (joinKind.equals(JoinSplitType.NONE_LITERAL))
      {
         GatewaySymbol gateway = elementFactory.createGatewaySymbol();
         gateway.setFlowKind(FlowControlType.JOIN_LITERAL);
         gateway.setElementOid(oid + 1);
         actSymbol.getGatewaySymbols().add(gateway);
      }

      // System.out.println(activity.getJoin().getLiteral() == );
      System.out.println(activity.getSplit().getLiteral());

      return actSymbol;
   }

   public static TransitionConnectionType createTransitionConnection(long oid,
         TransitionType transition)
   {
      TransitionConnectionType traConn = elementFactory.createTransitionConnectionType();
      traConn.setElementOid(oid);
      traConn.setTransition(transition);
      traConn.setRouting(RoutingType.MANHATTAN_LITERAL);
      traConn.setSourceActivitySymbol(transition.getTo().getActivitySymbols().get(0));
      traConn.setSourceActivitySymbol(transition.getFrom().getActivitySymbols().get(0));
      return traConn;
   }

}
