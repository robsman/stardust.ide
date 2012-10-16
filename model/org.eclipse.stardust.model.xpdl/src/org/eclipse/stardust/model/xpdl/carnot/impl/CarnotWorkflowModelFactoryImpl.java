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
package org.eclipse.stardust.model.xpdl.carnot.impl;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.Code;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.Coordinates;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.DocumentRoot;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference;
import org.eclipse.stardust.model.xpdl.carnot.ImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.IntermediateEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LinkCardinality;
import org.eclipse.stardust.model.xpdl.carnot.LinkColor;
import org.eclipse.stardust.model.xpdl.carnot.LinkEndStyle;
import org.eclipse.stardust.model.xpdl.carnot.LinkLineStyle;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.stardust.model.xpdl.carnot.LoopType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.Model_Messages;
import org.eclipse.stardust.model.xpdl.carnot.ModelerSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ModelerType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.PublicInterfaceSymbol;
import org.eclipse.stardust.model.xpdl.carnot.QualityControlType;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.RoutingType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TextSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.TextType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.UnbindActionType;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.ViewableType;
import org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;


/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class CarnotWorkflowModelFactoryImpl extends EFactoryImpl
      implements CarnotWorkflowModelFactory
{
   public static final String MSG_DATATYPE_NO_VALID_CLASSIFIER = Model_Messages.MSG_DATATYPE_NOT_VALID; //$NON-NLS-1$

   private static final String MSG_CLASS_NO_VALID_CLASSIFIER = Model_Messages.MSG_CLASS_NOT_VALID; //$NON-NLS-1$

   private static final String MSG_NO_VALID_ENUM = Model_Messages.MSG_NO_VALID_ENUMERATION; //$NON-NLS-1$

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static CarnotWorkflowModelFactory init()
   {
      try
      {
         CarnotWorkflowModelFactory theCarnotWorkflowModelFactory = (CarnotWorkflowModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.carnot.ag/workflowmodel/3.1"); 
         if (theCarnotWorkflowModelFactory != null)
         {
            return theCarnotWorkflowModelFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new CarnotWorkflowModelFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public CarnotWorkflowModelFactoryImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EObject create(EClass eClass)
   {
      switch (eClass.getClassifierID())
      {
         case CarnotWorkflowModelPackage.COORDINATES: return createCoordinates();
         case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT: return createIExtensibleElement();
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE: return createIdentifiableReference();
         case CarnotWorkflowModelPackage.ACCESS_POINT_TYPE: return createAccessPointType();
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE: return createActivitySymbolType();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE: return createActivityType();
         case CarnotWorkflowModelPackage.ANNOTATION_SYMBOL_TYPE: return createAnnotationSymbolType();
         case CarnotWorkflowModelPackage.APPLICATION_CONTEXT_TYPE_TYPE: return createApplicationContextTypeType();
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE: return createApplicationSymbolType();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE: return createApplicationType();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE: return createApplicationTypeType();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE: return createAttributeType();
         case CarnotWorkflowModelPackage.BIND_ACTION_TYPE: return createBindActionType();
         case CarnotWorkflowModelPackage.CODE: return createCode();
         case CarnotWorkflowModelPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE: return createConditionalPerformerSymbolType();
         case CarnotWorkflowModelPackage.CONDITIONAL_PERFORMER_TYPE: return createConditionalPerformerType();
         case CarnotWorkflowModelPackage.CONTEXT_TYPE: return createContextType();
         case CarnotWorkflowModelPackage.DATA_MAPPING_CONNECTION_TYPE: return createDataMappingConnectionType();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE: return createDataMappingType();
         case CarnotWorkflowModelPackage.DATA_PATH_TYPE: return createDataPathType();
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE: return createDataSymbolType();
         case CarnotWorkflowModelPackage.DATA_TYPE: return createDataType();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE: return createDataTypeType();
         case CarnotWorkflowModelPackage.DESCRIPTION_TYPE: return createDescriptionType();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE: return createDiagramType();
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT: return createDocumentRoot();
         case CarnotWorkflowModelPackage.END_EVENT_SYMBOL: return createEndEventSymbol();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE: return createEventActionType();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE: return createEventActionTypeType();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE: return createEventConditionTypeType();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE: return createEventHandlerType();
         case CarnotWorkflowModelPackage.EXECUTED_BY_CONNECTION_TYPE: return createExecutedByConnectionType();
         case CarnotWorkflowModelPackage.ID_REF: return createIdRef();
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL: return createGatewaySymbol();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE: return createGenericLinkConnectionType();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE: return createGroupSymbolType();
         case CarnotWorkflowModelPackage.INTERMEDIATE_EVENT_SYMBOL: return createIntermediateEventSymbol();
         case CarnotWorkflowModelPackage.LANE_SYMBOL: return createLaneSymbol();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE: return createLinkTypeType();
         case CarnotWorkflowModelPackage.MODELER_SYMBOL_TYPE: return createModelerSymbolType();
         case CarnotWorkflowModelPackage.MODELER_TYPE: return createModelerType();
         case CarnotWorkflowModelPackage.MODEL_TYPE: return createModelType();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE: return createOrganizationSymbolType();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE: return createOrganizationType();
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE: return createParameterMappingType();
         case CarnotWorkflowModelPackage.PARTICIPANT_TYPE: return createParticipantType();
         case CarnotWorkflowModelPackage.PART_OF_CONNECTION_TYPE: return createPartOfConnectionType();
         case CarnotWorkflowModelPackage.PERFORMS_CONNECTION_TYPE: return createPerformsConnectionType();
         case CarnotWorkflowModelPackage.POOL_SYMBOL: return createPoolSymbol();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE: return createProcessDefinitionType();
         case CarnotWorkflowModelPackage.PROCESS_SYMBOL_TYPE: return createProcessSymbolType();
         case CarnotWorkflowModelPackage.PUBLIC_INTERFACE_SYMBOL: return createPublicInterfaceSymbol();
         case CarnotWorkflowModelPackage.QUALITY_CONTROL_TYPE: return createQualityControlType();
         case CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE: return createRefersToConnectionType();
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE: return createRoleSymbolType();
         case CarnotWorkflowModelPackage.ROLE_TYPE: return createRoleType();
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL: return createStartEventSymbol();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE: return createSubProcessOfConnectionType();
         case CarnotWorkflowModelPackage.TEAM_LEAD_CONNECTION_TYPE: return createTeamLeadConnectionType();
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE: return createTextSymbolType();
         case CarnotWorkflowModelPackage.TEXT_TYPE: return createTextType();
         case CarnotWorkflowModelPackage.TRANSITION_CONNECTION_TYPE: return createTransitionConnectionType();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE: return createTransitionType();
         case CarnotWorkflowModelPackage.TRIGGERS_CONNECTION_TYPE: return createTriggersConnectionType();
         case CarnotWorkflowModelPackage.TRIGGER_TYPE: return createTriggerType();
         case CarnotWorkflowModelPackage.TRIGGER_TYPE_TYPE: return createTriggerTypeType();
         case CarnotWorkflowModelPackage.UNBIND_ACTION_TYPE: return createUnbindActionType();
         case CarnotWorkflowModelPackage.VIEWABLE_TYPE: return createViewableType();
         case CarnotWorkflowModelPackage.VIEW_TYPE: return createViewType();
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE: return createWorksForConnectionType();
         case CarnotWorkflowModelPackage.XML_TEXT_NODE: return createXmlTextNode();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object createFromString(EDataType eDataType, String initialValue)
   {
      switch (eDataType.getClassifierID())
      {
         case CarnotWorkflowModelPackage.ACTIVITY_IMPLEMENTATION_TYPE:
            return createActivityImplementationTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.DIRECTION_TYPE:
            return createDirectionTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.FLOW_CONTROL_TYPE:
            return createFlowControlTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.IMPLEMENTATION_TYPE:
            return createImplementationTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.JOIN_SPLIT_TYPE:
            return createJoinSplitTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LINK_CARDINALITY:
            return createLinkCardinalityFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LINK_COLOR:
            return createLinkColorFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LINK_LINE_STYLE:
            return createLinkLineStyleFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LINK_END_STYLE:
            return createLinkEndStyleFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LOOP_TYPE:
            return createLoopTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.ORIENTATION_TYPE:
            return createOrientationTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.ROUTING_TYPE:
            return createRoutingTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.SUB_PROCESS_MODE_TYPE:
            return createSubProcessModeTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.DIAGRAM_MODE_TYPE:
            return createDiagramModeTypeFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.ELEMENT_ID:
            return createElementIdFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.FEATURE_LIST:
            return createFeatureListFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.ACTIVITY_IMPLEMENTATION_TYPE_OBJECT:
            return createActivityImplementationTypeObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.DIRECTION_TYPE_OBJECT:
            return createDirectionTypeObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.FLOW_CONTROL_TYPE_OBJECT:
            return createFlowControlTypeObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.IMPLEMENTATION_TYPE_OBJECT:
            return createImplementationTypeObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LINK_CARDINALITY_OBJECT:
            return createLinkCardinalityObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LINK_COLOR_OBJECT:
            return createLinkColorObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LINK_LINE_STYLE_OBJECT:
            return createLinkLineStyleObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LINK_END_STYLE_OBJECT:
            return createLinkEndStyleObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.JOIN_SPLIT_TYPE_OBJECT:
            return createJoinSplitTypeObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.LOOP_TYPE_OBJECT:
            return createLoopTypeObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.ORIENTATION_TYPE_OBJECT:
            return createOrientationTypeObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.ROUTING_TYPE_OBJECT:
            return createRoutingTypeObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.SUB_PROCESS_MODE_TYPE_OBJECT:
            return createSubProcessModeTypeObjectFromString(eDataType, initialValue);
         case CarnotWorkflowModelPackage.DIAGRAM_MODE_TYPE_OBJECT:
            return createDiagramModeTypeObjectFromString(eDataType, initialValue);
         default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String convertToString(EDataType eDataType, Object instanceValue)
   {
      switch (eDataType.getClassifierID())
      {
         case CarnotWorkflowModelPackage.ACTIVITY_IMPLEMENTATION_TYPE:
            return convertActivityImplementationTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.DIRECTION_TYPE:
            return convertDirectionTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.FLOW_CONTROL_TYPE:
            return convertFlowControlTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.IMPLEMENTATION_TYPE:
            return convertImplementationTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.JOIN_SPLIT_TYPE:
            return convertJoinSplitTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LINK_CARDINALITY:
            return convertLinkCardinalityToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LINK_COLOR:
            return convertLinkColorToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LINK_LINE_STYLE:
            return convertLinkLineStyleToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LINK_END_STYLE:
            return convertLinkEndStyleToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LOOP_TYPE:
            return convertLoopTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.ORIENTATION_TYPE:
            return convertOrientationTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.ROUTING_TYPE:
            return convertRoutingTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.SUB_PROCESS_MODE_TYPE:
            return convertSubProcessModeTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.DIAGRAM_MODE_TYPE:
            return convertDiagramModeTypeToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.ELEMENT_ID:
            return convertElementIdToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.FEATURE_LIST:
            return convertFeatureListToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.ACTIVITY_IMPLEMENTATION_TYPE_OBJECT:
            return convertActivityImplementationTypeObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.DIRECTION_TYPE_OBJECT:
            return convertDirectionTypeObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.FLOW_CONTROL_TYPE_OBJECT:
            return convertFlowControlTypeObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.IMPLEMENTATION_TYPE_OBJECT:
            return convertImplementationTypeObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LINK_CARDINALITY_OBJECT:
            return convertLinkCardinalityObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LINK_COLOR_OBJECT:
            return convertLinkColorObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LINK_LINE_STYLE_OBJECT:
            return convertLinkLineStyleObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LINK_END_STYLE_OBJECT:
            return convertLinkEndStyleObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.JOIN_SPLIT_TYPE_OBJECT:
            return convertJoinSplitTypeObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.LOOP_TYPE_OBJECT:
            return convertLoopTypeObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.ORIENTATION_TYPE_OBJECT:
            return convertOrientationTypeObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.ROUTING_TYPE_OBJECT:
            return convertRoutingTypeObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.SUB_PROCESS_MODE_TYPE_OBJECT:
            return convertSubProcessModeTypeObjectToString(eDataType, instanceValue);
         case CarnotWorkflowModelPackage.DIAGRAM_MODE_TYPE_OBJECT:
            return convertDiagramModeTypeObjectToString(eDataType, instanceValue);
         default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public Coordinates createCoordinates()
   {
      CoordinatesImpl coordinates = new CoordinatesImpl();
      return coordinates;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public IExtensibleElement createIExtensibleElement()
   {
      IExtensibleElementImpl iExtensibleElement = new IExtensibleElementImpl();
      return iExtensibleElement;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public IdentifiableReference createIdentifiableReference()
   {
      IdentifiableReferenceImpl identifiableReference = new IdentifiableReferenceImpl();
      return identifiableReference;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public AccessPointType createAccessPointType()
   {
      AccessPointTypeImpl accessPointType = new AccessPointTypeImpl();
      return accessPointType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ActivitySymbolType createActivitySymbolType()
   {
      ActivitySymbolTypeImpl activitySymbolType = new ActivitySymbolTypeImpl();
      return activitySymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ActivityType createActivityType()
   {
      ActivityTypeImpl activityType = new ActivityTypeImpl();
      return activityType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public AnnotationSymbolType createAnnotationSymbolType()
   {
      AnnotationSymbolTypeImpl annotationSymbolType = new AnnotationSymbolTypeImpl();
      return annotationSymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ApplicationContextTypeType createApplicationContextTypeType()
   {
      ApplicationContextTypeTypeImpl applicationContextTypeType = new ApplicationContextTypeTypeImpl();
      return applicationContextTypeType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ApplicationSymbolType createApplicationSymbolType()
   {
      ApplicationSymbolTypeImpl applicationSymbolType = new ApplicationSymbolTypeImpl();
      return applicationSymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ApplicationType createApplicationType()
   {
      ApplicationTypeImpl applicationType = new ApplicationTypeImpl();
      return applicationType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ApplicationTypeType createApplicationTypeType()
   {
      ApplicationTypeTypeImpl applicationTypeType = new ApplicationTypeTypeImpl();
      return applicationTypeType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public AttributeType createAttributeType()
   {
      AttributeTypeImpl attributeType = new AttributeTypeImpl();
      return attributeType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public BindActionType createBindActionType()
   {
      BindActionTypeImpl bindActionType = new BindActionTypeImpl();
      return bindActionType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Code createCode()
   {
      CodeImpl code = new CodeImpl();
      return code;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ConditionalPerformerSymbolType createConditionalPerformerSymbolType()
   {
      ConditionalPerformerSymbolTypeImpl conditionalPerformerSymbolType = new ConditionalPerformerSymbolTypeImpl();
      return conditionalPerformerSymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ConditionalPerformerType createConditionalPerformerType()
   {
      ConditionalPerformerTypeImpl conditionalPerformerType = new ConditionalPerformerTypeImpl();
      return conditionalPerformerType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ContextType createContextType()
   {
      ContextTypeImpl contextType = new ContextTypeImpl();
      return contextType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DataMappingConnectionType createDataMappingConnectionType()
   {
      DataMappingConnectionTypeImpl dataMappingConnectionType = new DataMappingConnectionTypeImpl();
      return dataMappingConnectionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DataMappingType createDataMappingType()
   {
      DataMappingTypeImpl dataMappingType = new DataMappingTypeImpl();
      return dataMappingType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DataPathType createDataPathType()
   {
      DataPathTypeImpl dataPathType = new DataPathTypeImpl();
      return dataPathType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DataSymbolType createDataSymbolType()
   {
      DataSymbolTypeImpl dataSymbolType = new DataSymbolTypeImpl();
      return dataSymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DataType createDataType()
   {
      DataTypeImpl dataType = new DataTypeImpl();
      return dataType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DataTypeType createDataTypeType()
   {
      DataTypeTypeImpl dataTypeType = new DataTypeTypeImpl();
      return dataTypeType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DescriptionType createDescriptionType()
   {
      DescriptionTypeImpl descriptionType = new DescriptionTypeImpl();
      return descriptionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DiagramType createDiagramType()
   {
      DiagramTypeImpl diagramType = new DiagramTypeImpl();
      return diagramType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DocumentRoot createDocumentRoot()
   {
      DocumentRootImpl documentRoot = new DocumentRootImpl();
      return documentRoot;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EndEventSymbol createEndEventSymbol()
   {
      EndEventSymbolImpl endEventSymbol = new EndEventSymbolImpl();
      return endEventSymbol;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EventActionType createEventActionType()
   {
      EventActionTypeImpl eventActionType = new EventActionTypeImpl();
      return eventActionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EventActionTypeType createEventActionTypeType()
   {
      EventActionTypeTypeImpl eventActionTypeType = new EventActionTypeTypeImpl();
      return eventActionTypeType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EventConditionTypeType createEventConditionTypeType()
   {
      EventConditionTypeTypeImpl eventConditionTypeType = new EventConditionTypeTypeImpl();
      return eventConditionTypeType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EventHandlerType createEventHandlerType()
   {
      EventHandlerTypeImpl eventHandlerType = new EventHandlerTypeImpl();
      return eventHandlerType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ExecutedByConnectionType createExecutedByConnectionType()
   {
      ExecutedByConnectionTypeImpl executedByConnectionType = new ExecutedByConnectionTypeImpl();
      return executedByConnectionType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public IdRef createIdRef()
   {
      IdRefImpl idRef = new IdRefImpl();
      return idRef;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public GatewaySymbol createGatewaySymbol()
   {
      GatewaySymbolImpl gatewaySymbol = new GatewaySymbolImpl();
      return gatewaySymbol;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public GenericLinkConnectionType createGenericLinkConnectionType()
   {
      GenericLinkConnectionTypeImpl genericLinkConnectionType = new GenericLinkConnectionTypeImpl();
      return genericLinkConnectionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public GroupSymbolType createGroupSymbolType()
   {
      GroupSymbolTypeImpl groupSymbolType = new GroupSymbolTypeImpl();
      return groupSymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public IntermediateEventSymbol createIntermediateEventSymbol()
   {
      IntermediateEventSymbolImpl intermediateEventSymbol = new IntermediateEventSymbolImpl();
      return intermediateEventSymbol;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public LaneSymbol createLaneSymbol()
   {
      LaneSymbolImpl laneSymbol = new LaneSymbolImpl();
      return laneSymbol;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public LinkTypeType createLinkTypeType()
   {
      LinkTypeTypeImpl linkTypeType = new LinkTypeTypeImpl();
      return linkTypeType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ModelerSymbolType createModelerSymbolType()
   {
      ModelerSymbolTypeImpl modelerSymbolType = new ModelerSymbolTypeImpl();
      return modelerSymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ModelerType createModelerType()
   {
      ModelerTypeImpl modelerType = new ModelerTypeImpl();
      return modelerType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ModelType createModelType()
   {
      ModelTypeImpl modelType = new ModelTypeImpl();
      return modelType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public OrganizationSymbolType createOrganizationSymbolType()
   {
      OrganizationSymbolTypeImpl organizationSymbolType = new OrganizationSymbolTypeImpl();
      return organizationSymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public OrganizationType createOrganizationType()
   {
      OrganizationTypeImpl organizationType = new OrganizationTypeImpl();
      return organizationType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ParameterMappingType createParameterMappingType()
   {
      ParameterMappingTypeImpl parameterMappingType = new ParameterMappingTypeImpl();
      return parameterMappingType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ParticipantType createParticipantType()
   {
      ParticipantTypeImpl participantType = new ParticipantTypeImpl();
      return participantType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public PartOfConnectionType createPartOfConnectionType()
   {
      PartOfConnectionTypeImpl partOfConnectionType = new PartOfConnectionTypeImpl();
      return partOfConnectionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public PerformsConnectionType createPerformsConnectionType()
   {
      PerformsConnectionTypeImpl performsConnectionType = new PerformsConnectionTypeImpl();
      return performsConnectionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public PoolSymbol createPoolSymbol()
   {
      PoolSymbolImpl poolSymbol = new PoolSymbolImpl();
      return poolSymbol;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ProcessDefinitionType createProcessDefinitionType()
   {
      ProcessDefinitionTypeImpl processDefinitionType = new ProcessDefinitionTypeImpl();
      return processDefinitionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ProcessSymbolType createProcessSymbolType()
   {
      ProcessSymbolTypeImpl processSymbolType = new ProcessSymbolTypeImpl();
      return processSymbolType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PublicInterfaceSymbol createPublicInterfaceSymbol()
   {
      PublicInterfaceSymbolImpl publicInterfaceSymbol = new PublicInterfaceSymbolImpl();
      return publicInterfaceSymbol;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public QualityControlType createQualityControlType()
   {
      QualityControlTypeImpl qualityControlType = new QualityControlTypeImpl();
      return qualityControlType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public RefersToConnectionType createRefersToConnectionType()
   {
      RefersToConnectionTypeImpl refersToConnectionType = new RefersToConnectionTypeImpl();
      return refersToConnectionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public RoleSymbolType createRoleSymbolType()
   {
      RoleSymbolTypeImpl roleSymbolType = new RoleSymbolTypeImpl();
      return roleSymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public RoleType createRoleType()
   {
      RoleTypeImpl roleType = new RoleTypeImpl();
      return roleType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public StartEventSymbol createStartEventSymbol()
   {
      StartEventSymbolImpl startEventSymbol = new StartEventSymbolImpl();
      return startEventSymbol;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public SubProcessOfConnectionType createSubProcessOfConnectionType()
   {
      SubProcessOfConnectionTypeImpl subProcessOfConnectionType = new SubProcessOfConnectionTypeImpl();
      return subProcessOfConnectionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public TextSymbolType createTextSymbolType()
   {
      TextSymbolTypeImpl textSymbolType = new TextSymbolTypeImpl();
      return textSymbolType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public TextType createTextType()
   {
      TextTypeImpl textType = new TextTypeImpl();
      return textType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public TransitionConnectionType createTransitionConnectionType()
   {
      TransitionConnectionTypeImpl transitionConnectionType = new TransitionConnectionTypeImpl();
      return transitionConnectionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public TransitionType createTransitionType()
   {
      TransitionTypeImpl transitionType = new TransitionTypeImpl();
      return transitionType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public TriggersConnectionType createTriggersConnectionType()
   {
      TriggersConnectionTypeImpl triggersConnectionType = new TriggersConnectionTypeImpl();
      return triggersConnectionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public TriggerType createTriggerType()
   {
      TriggerTypeImpl triggerType = new TriggerTypeImpl();
      return triggerType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public TriggerTypeType createTriggerTypeType()
   {
      TriggerTypeTypeImpl triggerTypeType = new TriggerTypeTypeImpl();
      return triggerTypeType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public UnbindActionType createUnbindActionType()
   {
      UnbindActionTypeImpl unbindActionType = new UnbindActionTypeImpl();
      return unbindActionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ViewableType createViewableType()
   {
      ViewableTypeImpl viewableType = new ViewableTypeImpl();
      return viewableType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ViewType createViewType()
   {
      ViewTypeImpl viewType = new ViewTypeImpl();
      return viewType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public WorksForConnectionType createWorksForConnectionType()
   {
      WorksForConnectionTypeImpl worksForConnectionType = new WorksForConnectionTypeImpl();
      return worksForConnectionType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public XmlTextNode createXmlTextNode()
   {
      XmlTextNodeImpl xmlTextNode = new XmlTextNodeImpl();
      return xmlTextNode;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public TeamLeadConnectionType createTeamLeadConnectionType()
   {
      TeamLeadConnectionTypeImpl teamLeadConnectionType = new TeamLeadConnectionTypeImpl();
      return teamLeadConnectionType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ActivityImplementationType createActivityImplementationTypeFromString(EDataType eDataType, String initialValue)
   {
      ActivityImplementationType result = ActivityImplementationType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertActivityImplementationTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DirectionType createDirectionTypeFromString(EDataType eDataType, String initialValue)
   {
      DirectionType result = DirectionType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertDirectionTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FlowControlType createFlowControlTypeFromString(EDataType eDataType, String initialValue)
   {
      FlowControlType result = FlowControlType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertFlowControlTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ImplementationType createImplementationTypeFromString(EDataType eDataType, String initialValue)
   {
      ImplementationType result = ImplementationType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertImplementationTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public JoinSplitType createJoinSplitTypeFromString(EDataType eDataType, String initialValue)
   {
      JoinSplitType result = JoinSplitType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertJoinSplitTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkCardinality createLinkCardinalityFromString(EDataType eDataType, String initialValue)
   {
      LinkCardinality result = LinkCardinality.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertLinkCardinalityToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkColor createLinkColorFromString(EDataType eDataType, String initialValue)
   {
      LinkColor result = LinkColor.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertLinkColorToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkLineStyle createLinkLineStyleFromString(EDataType eDataType, String initialValue)
   {
      LinkLineStyle result = LinkLineStyle.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertLinkLineStyleToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkEndStyle createLinkEndStyleFromString(EDataType eDataType, String initialValue)
   {
      LinkEndStyle result = LinkEndStyle.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertLinkEndStyleToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopType createLoopTypeFromString(EDataType eDataType, String initialValue)
   {
      LoopType result = LoopType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertLoopTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public OrientationType createOrientationTypeFromString(EDataType eDataType, String initialValue)
   {
      OrientationType result = OrientationType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertOrientationTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public RoutingType createRoutingTypeFromString(EDataType eDataType, String initialValue)
   {
      RoutingType result = RoutingType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertRoutingTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public SubProcessModeType createSubProcessModeTypeFromString(EDataType eDataType, String initialValue)
   {
      SubProcessModeType result = SubProcessModeType.getByName(initialValue);
      if (result == null) throw new IllegalArgumentException(MessageFormat.format(Model_Messages.EXC_THE_VALUE_NULL_IS_NOT_VALID_ENUMERATION_OF_ONE , new Object[]{initialValue ,eDataType.getName()}));
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public String convertSubProcessModeTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : ((SubProcessModeType) instanceValue).getName();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DiagramModeType createDiagramModeTypeFromString(EDataType eDataType, String initialValue)
   {
      DiagramModeType result = DiagramModeType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertDiagramModeTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String createElementIdFromString(EDataType eDataType, String initialValue)
   {
      return (String)super.createFromString(eDataType, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertElementIdToString(EDataType eDataType, Object instanceValue)
   {
      return super.convertToString(eDataType, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public List createFeatureListFromString(EDataType eDataType, String initialValue)
   {
      return (List)super.createFromString(eDataType, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertFeatureListToString(EDataType eDataType, Object instanceValue)
   {
      return super.convertToString(eDataType, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ActivityImplementationType createActivityImplementationTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createActivityImplementationTypeFromString(CarnotWorkflowModelPackage.Literals.ACTIVITY_IMPLEMENTATION_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertActivityImplementationTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertActivityImplementationTypeToString(CarnotWorkflowModelPackage.Literals.ACTIVITY_IMPLEMENTATION_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public DirectionType createDirectionTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createDirectionTypeFromString(CarnotWorkflowModelPackage.Literals.DIRECTION_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertDirectionTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertDirectionTypeToString(CarnotWorkflowModelPackage.Literals.DIRECTION_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public FlowControlType createFlowControlTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createFlowControlTypeFromString(CarnotWorkflowModelPackage.Literals.FLOW_CONTROL_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertFlowControlTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertFlowControlTypeToString(CarnotWorkflowModelPackage.Literals.FLOW_CONTROL_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ImplementationType createImplementationTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createImplementationTypeFromString(CarnotWorkflowModelPackage.Literals.IMPLEMENTATION_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertImplementationTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertImplementationTypeToString(CarnotWorkflowModelPackage.Literals.IMPLEMENTATION_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public JoinSplitType createJoinSplitTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createJoinSplitTypeFromString(CarnotWorkflowModelPackage.Literals.JOIN_SPLIT_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertJoinSplitTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertJoinSplitTypeToString(CarnotWorkflowModelPackage.Literals.JOIN_SPLIT_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public LinkCardinality createLinkCardinalityObjectFromString(EDataType eDataType, String initialValue)
   {
      return createLinkCardinalityFromString(CarnotWorkflowModelPackage.Literals.LINK_CARDINALITY, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertLinkCardinalityObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertLinkCardinalityToString(CarnotWorkflowModelPackage.Literals.LINK_CARDINALITY, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public LinkColor createLinkColorObjectFromString(EDataType eDataType, String initialValue)
   {
      return createLinkColorFromString(CarnotWorkflowModelPackage.Literals.LINK_COLOR, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertLinkColorObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertLinkColorToString(CarnotWorkflowModelPackage.Literals.LINK_COLOR, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public LinkLineStyle createLinkLineStyleObjectFromString(EDataType eDataType, String initialValue)
   {
      return createLinkLineStyleFromString(CarnotWorkflowModelPackage.Literals.LINK_LINE_STYLE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertLinkLineStyleObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertLinkLineStyleToString(CarnotWorkflowModelPackage.Literals.LINK_LINE_STYLE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public LinkEndStyle createLinkEndStyleObjectFromString(EDataType eDataType, String initialValue)
   {
      return createLinkEndStyleFromString(CarnotWorkflowModelPackage.Literals.LINK_END_STYLE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertLinkEndStyleObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertLinkEndStyleToString(CarnotWorkflowModelPackage.Literals.LINK_END_STYLE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public LoopType createLoopTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createLoopTypeFromString(CarnotWorkflowModelPackage.Literals.LOOP_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertLoopTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertLoopTypeToString(CarnotWorkflowModelPackage.Literals.LOOP_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public OrientationType createOrientationTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createOrientationTypeFromString(CarnotWorkflowModelPackage.Literals.ORIENTATION_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertOrientationTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertOrientationTypeToString(CarnotWorkflowModelPackage.Literals.ORIENTATION_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public RoutingType createRoutingTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createRoutingTypeFromString(CarnotWorkflowModelPackage.Literals.ROUTING_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertRoutingTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertRoutingTypeToString(CarnotWorkflowModelPackage.Literals.ROUTING_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public SubProcessModeType createSubProcessModeTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createSubProcessModeTypeFromString(CarnotWorkflowModelPackage.Literals.SUB_PROCESS_MODE_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String convertSubProcessModeTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertSubProcessModeTypeToString(CarnotWorkflowModelPackage.Literals.SUB_PROCESS_MODE_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DiagramModeType createDiagramModeTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createDiagramModeTypeFromString(CarnotWorkflowModelPackage.Literals.DIAGRAM_MODE_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertDiagramModeTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertDiagramModeTypeToString(CarnotWorkflowModelPackage.Literals.DIAGRAM_MODE_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public CarnotWorkflowModelPackage getCarnotWorkflowModelPackage()
   {
      return (CarnotWorkflowModelPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static CarnotWorkflowModelPackage getPackage()
   {
      return CarnotWorkflowModelPackage.eINSTANCE;
   }

} // CarnotWorkflowModelFactoryImpl
