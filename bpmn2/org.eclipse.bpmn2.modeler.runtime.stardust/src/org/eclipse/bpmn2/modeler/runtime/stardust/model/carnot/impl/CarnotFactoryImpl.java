/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.util.List;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CarnotFactoryImpl extends EFactoryImpl implements CarnotFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CarnotFactory init() {
		try {
			CarnotFactory theCarnotFactory = (CarnotFactory)EPackage.Registry.INSTANCE.getEFactory(CarnotPackage.eNS_URI);
			if (theCarnotFactory != null) {
				return theCarnotFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CarnotFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CarnotFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CarnotPackage.COORDINATES: return createCoordinates();
			case CarnotPackage.IEXTENSIBLE_ELEMENT: return createIExtensibleElement();
			case CarnotPackage.IDENTIFIABLE_REFERENCE: return createIdentifiableReference();
			case CarnotPackage.ACCESS_POINT_TYPE: return createAccessPointType();
			case CarnotPackage.ACTIVITY_SYMBOL_TYPE: return createActivitySymbolType();
			case CarnotPackage.ACTIVITY_TYPE: return createActivityType();
			case CarnotPackage.ANNOTATION_SYMBOL_TYPE: return createAnnotationSymbolType();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE: return createApplicationContextTypeType();
			case CarnotPackage.APPLICATION_SYMBOL_TYPE: return createApplicationSymbolType();
			case CarnotPackage.APPLICATION_TYPE: return createApplicationType();
			case CarnotPackage.APPLICATION_TYPE_TYPE: return createApplicationTypeType();
			case CarnotPackage.ATTRIBUTE_TYPE: return createAttributeType();
			case CarnotPackage.BIND_ACTION_TYPE: return createBindActionType();
			case CarnotPackage.CODE: return createCode();
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE: return createConditionalPerformerSymbolType();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE: return createConditionalPerformerType();
			case CarnotPackage.CONTEXT_TYPE: return createContextType();
			case CarnotPackage.DATA_MAPPING_CONNECTION_TYPE: return createDataMappingConnectionType();
			case CarnotPackage.DATA_MAPPING_TYPE: return createDataMappingType();
			case CarnotPackage.DATA_PATH_TYPE: return createDataPathType();
			case CarnotPackage.DATA_SYMBOL_TYPE: return createDataSymbolType();
			case CarnotPackage.DATA_TYPE: return createDataType();
			case CarnotPackage.DATA_TYPE_TYPE: return createDataTypeType();
			case CarnotPackage.DESCRIPTION_TYPE: return createDescriptionType();
			case CarnotPackage.DIAGRAM_TYPE: return createDiagramType();
			case CarnotPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case CarnotPackage.END_EVENT_SYMBOL: return createEndEventSymbol();
			case CarnotPackage.EVENT_ACTION_TYPE: return createEventActionType();
			case CarnotPackage.EVENT_ACTION_TYPE_TYPE: return createEventActionTypeType();
			case CarnotPackage.EVENT_CONDITION_TYPE_TYPE: return createEventConditionTypeType();
			case CarnotPackage.EVENT_HANDLER_TYPE: return createEventHandlerType();
			case CarnotPackage.EXECUTED_BY_CONNECTION_TYPE: return createExecutedByConnectionType();
			case CarnotPackage.ID_REF: return createIdRef();
			case CarnotPackage.GATEWAY_SYMBOL: return createGatewaySymbol();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE: return createGenericLinkConnectionType();
			case CarnotPackage.GROUP_SYMBOL_TYPE: return createGroupSymbolType();
			case CarnotPackage.INTERMEDIATE_EVENT_SYMBOL: return createIntermediateEventSymbol();
			case CarnotPackage.LANE_SYMBOL: return createLaneSymbol();
			case CarnotPackage.LINK_TYPE_TYPE: return createLinkTypeType();
			case CarnotPackage.MODELER_SYMBOL_TYPE: return createModelerSymbolType();
			case CarnotPackage.MODELER_TYPE: return createModelerType();
			case CarnotPackage.MODEL_TYPE: return createModelType();
			case CarnotPackage.ORGANIZATION_SYMBOL_TYPE: return createOrganizationSymbolType();
			case CarnotPackage.ORGANIZATION_TYPE: return createOrganizationType();
			case CarnotPackage.PARAMETER_MAPPING_TYPE: return createParameterMappingType();
			case CarnotPackage.PARTICIPANT_TYPE: return createParticipantType();
			case CarnotPackage.PART_OF_CONNECTION_TYPE: return createPartOfConnectionType();
			case CarnotPackage.PERFORMS_CONNECTION_TYPE: return createPerformsConnectionType();
			case CarnotPackage.POOL_SYMBOL: return createPoolSymbol();
			case CarnotPackage.PROCESS_DEFINITION_TYPE: return createProcessDefinitionType();
			case CarnotPackage.PROCESS_SYMBOL_TYPE: return createProcessSymbolType();
			case CarnotPackage.PUBLIC_INTERFACE_SYMBOL: return createPublicInterfaceSymbol();
			case CarnotPackage.QUALITY_CONTROL_TYPE: return createQualityControlType();
			case CarnotPackage.REFERS_TO_CONNECTION_TYPE: return createRefersToConnectionType();
			case CarnotPackage.ROLE_SYMBOL_TYPE: return createRoleSymbolType();
			case CarnotPackage.ROLE_TYPE: return createRoleType();
			case CarnotPackage.START_EVENT_SYMBOL: return createStartEventSymbol();
			case CarnotPackage.SUB_PROCESS_OF_CONNECTION_TYPE: return createSubProcessOfConnectionType();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE: return createTeamLeadConnectionType();
			case CarnotPackage.TEXT_SYMBOL_TYPE: return createTextSymbolType();
			case CarnotPackage.TEXT_TYPE: return createTextType();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE: return createTransitionConnectionType();
			case CarnotPackage.TRANSITION_TYPE: return createTransitionType();
			case CarnotPackage.TRIGGERS_CONNECTION_TYPE: return createTriggersConnectionType();
			case CarnotPackage.TRIGGER_TYPE: return createTriggerType();
			case CarnotPackage.TRIGGER_TYPE_TYPE: return createTriggerTypeType();
			case CarnotPackage.UNBIND_ACTION_TYPE: return createUnbindActionType();
			case CarnotPackage.VIEWABLE_TYPE: return createViewableType();
			case CarnotPackage.VIEW_TYPE: return createViewType();
			case CarnotPackage.WORKS_FOR_CONNECTION_TYPE: return createWorksForConnectionType();
			case CarnotPackage.XML_TEXT_NODE: return createXmlTextNode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CarnotPackage.ACTIVITY_IMPLEMENTATION_TYPE:
				return createActivityImplementationTypeFromString(eDataType, initialValue);
			case CarnotPackage.DIRECTION_TYPE:
				return createDirectionTypeFromString(eDataType, initialValue);
			case CarnotPackage.FLOW_CONTROL_TYPE:
				return createFlowControlTypeFromString(eDataType, initialValue);
			case CarnotPackage.IMPLEMENTATION_TYPE:
				return createImplementationTypeFromString(eDataType, initialValue);
			case CarnotPackage.JOIN_SPLIT_TYPE:
				return createJoinSplitTypeFromString(eDataType, initialValue);
			case CarnotPackage.LINK_CARDINALITY:
				return createLinkCardinalityFromString(eDataType, initialValue);
			case CarnotPackage.LINK_COLOR:
				return createLinkColorFromString(eDataType, initialValue);
			case CarnotPackage.LINK_LINE_STYLE:
				return createLinkLineStyleFromString(eDataType, initialValue);
			case CarnotPackage.LINK_END_STYLE:
				return createLinkEndStyleFromString(eDataType, initialValue);
			case CarnotPackage.LOOP_TYPE:
				return createLoopTypeFromString(eDataType, initialValue);
			case CarnotPackage.ORIENTATION_TYPE:
				return createOrientationTypeFromString(eDataType, initialValue);
			case CarnotPackage.ROUTING_TYPE:
				return createRoutingTypeFromString(eDataType, initialValue);
			case CarnotPackage.SUB_PROCESS_MODE_TYPE:
				return createSubProcessModeTypeFromString(eDataType, initialValue);
			case CarnotPackage.DIAGRAM_MODE_TYPE:
				return createDiagramModeTypeFromString(eDataType, initialValue);
			case CarnotPackage.ELEMENT_ID:
				return createElementIdFromString(eDataType, initialValue);
			case CarnotPackage.FEATURE_LIST:
				return createFeatureListFromString(eDataType, initialValue);
			case CarnotPackage.ACTIVITY_IMPLEMENTATION_TYPE_OBJECT:
				return createActivityImplementationTypeObjectFromString(eDataType, initialValue);
			case CarnotPackage.DIRECTION_TYPE_OBJECT:
				return createDirectionTypeObjectFromString(eDataType, initialValue);
			case CarnotPackage.FLOW_CONTROL_TYPE_OBJECT:
				return createFlowControlTypeObjectFromString(eDataType, initialValue);
			case CarnotPackage.IMPLEMENTATION_TYPE_OBJECT:
				return createImplementationTypeObjectFromString(eDataType, initialValue);
			case CarnotPackage.LINK_CARDINALITY_OBJECT:
				return createLinkCardinalityObjectFromString(eDataType, initialValue);
			case CarnotPackage.LINK_COLOR_OBJECT:
				return createLinkColorObjectFromString(eDataType, initialValue);
			case CarnotPackage.LINK_LINE_STYLE_OBJECT:
				return createLinkLineStyleObjectFromString(eDataType, initialValue);
			case CarnotPackage.LINK_END_STYLE_OBJECT:
				return createLinkEndStyleObjectFromString(eDataType, initialValue);
			case CarnotPackage.JOIN_SPLIT_TYPE_OBJECT:
				return createJoinSplitTypeObjectFromString(eDataType, initialValue);
			case CarnotPackage.LOOP_TYPE_OBJECT:
				return createLoopTypeObjectFromString(eDataType, initialValue);
			case CarnotPackage.ORIENTATION_TYPE_OBJECT:
				return createOrientationTypeObjectFromString(eDataType, initialValue);
			case CarnotPackage.ROUTING_TYPE_OBJECT:
				return createRoutingTypeObjectFromString(eDataType, initialValue);
			case CarnotPackage.SUB_PROCESS_MODE_TYPE_OBJECT:
				return createSubProcessModeTypeObjectFromString(eDataType, initialValue);
			case CarnotPackage.DIAGRAM_MODE_TYPE_OBJECT:
				return createDiagramModeTypeObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CarnotPackage.ACTIVITY_IMPLEMENTATION_TYPE:
				return convertActivityImplementationTypeToString(eDataType, instanceValue);
			case CarnotPackage.DIRECTION_TYPE:
				return convertDirectionTypeToString(eDataType, instanceValue);
			case CarnotPackage.FLOW_CONTROL_TYPE:
				return convertFlowControlTypeToString(eDataType, instanceValue);
			case CarnotPackage.IMPLEMENTATION_TYPE:
				return convertImplementationTypeToString(eDataType, instanceValue);
			case CarnotPackage.JOIN_SPLIT_TYPE:
				return convertJoinSplitTypeToString(eDataType, instanceValue);
			case CarnotPackage.LINK_CARDINALITY:
				return convertLinkCardinalityToString(eDataType, instanceValue);
			case CarnotPackage.LINK_COLOR:
				return convertLinkColorToString(eDataType, instanceValue);
			case CarnotPackage.LINK_LINE_STYLE:
				return convertLinkLineStyleToString(eDataType, instanceValue);
			case CarnotPackage.LINK_END_STYLE:
				return convertLinkEndStyleToString(eDataType, instanceValue);
			case CarnotPackage.LOOP_TYPE:
				return convertLoopTypeToString(eDataType, instanceValue);
			case CarnotPackage.ORIENTATION_TYPE:
				return convertOrientationTypeToString(eDataType, instanceValue);
			case CarnotPackage.ROUTING_TYPE:
				return convertRoutingTypeToString(eDataType, instanceValue);
			case CarnotPackage.SUB_PROCESS_MODE_TYPE:
				return convertSubProcessModeTypeToString(eDataType, instanceValue);
			case CarnotPackage.DIAGRAM_MODE_TYPE:
				return convertDiagramModeTypeToString(eDataType, instanceValue);
			case CarnotPackage.ELEMENT_ID:
				return convertElementIdToString(eDataType, instanceValue);
			case CarnotPackage.FEATURE_LIST:
				return convertFeatureListToString(eDataType, instanceValue);
			case CarnotPackage.ACTIVITY_IMPLEMENTATION_TYPE_OBJECT:
				return convertActivityImplementationTypeObjectToString(eDataType, instanceValue);
			case CarnotPackage.DIRECTION_TYPE_OBJECT:
				return convertDirectionTypeObjectToString(eDataType, instanceValue);
			case CarnotPackage.FLOW_CONTROL_TYPE_OBJECT:
				return convertFlowControlTypeObjectToString(eDataType, instanceValue);
			case CarnotPackage.IMPLEMENTATION_TYPE_OBJECT:
				return convertImplementationTypeObjectToString(eDataType, instanceValue);
			case CarnotPackage.LINK_CARDINALITY_OBJECT:
				return convertLinkCardinalityObjectToString(eDataType, instanceValue);
			case CarnotPackage.LINK_COLOR_OBJECT:
				return convertLinkColorObjectToString(eDataType, instanceValue);
			case CarnotPackage.LINK_LINE_STYLE_OBJECT:
				return convertLinkLineStyleObjectToString(eDataType, instanceValue);
			case CarnotPackage.LINK_END_STYLE_OBJECT:
				return convertLinkEndStyleObjectToString(eDataType, instanceValue);
			case CarnotPackage.JOIN_SPLIT_TYPE_OBJECT:
				return convertJoinSplitTypeObjectToString(eDataType, instanceValue);
			case CarnotPackage.LOOP_TYPE_OBJECT:
				return convertLoopTypeObjectToString(eDataType, instanceValue);
			case CarnotPackage.ORIENTATION_TYPE_OBJECT:
				return convertOrientationTypeObjectToString(eDataType, instanceValue);
			case CarnotPackage.ROUTING_TYPE_OBJECT:
				return convertRoutingTypeObjectToString(eDataType, instanceValue);
			case CarnotPackage.SUB_PROCESS_MODE_TYPE_OBJECT:
				return convertSubProcessModeTypeObjectToString(eDataType, instanceValue);
			case CarnotPackage.DIAGRAM_MODE_TYPE_OBJECT:
				return convertDiagramModeTypeObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Coordinates createCoordinates() {
		CoordinatesImpl coordinates = new CoordinatesImpl();
		return coordinates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IExtensibleElement createIExtensibleElement() {
		IExtensibleElementImpl iExtensibleElement = new IExtensibleElementImpl();
		return iExtensibleElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentifiableReference createIdentifiableReference() {
		IdentifiableReferenceImpl identifiableReference = new IdentifiableReferenceImpl();
		return identifiableReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccessPointType createAccessPointType() {
		AccessPointTypeImpl accessPointType = new AccessPointTypeImpl();
		return accessPointType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitySymbolType createActivitySymbolType() {
		ActivitySymbolTypeImpl activitySymbolType = new ActivitySymbolTypeImpl();
		return activitySymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityType createActivityType() {
		ActivityTypeImpl activityType = new ActivityTypeImpl();
		return activityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationSymbolType createAnnotationSymbolType() {
		AnnotationSymbolTypeImpl annotationSymbolType = new AnnotationSymbolTypeImpl();
		return annotationSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationContextTypeType createApplicationContextTypeType() {
		ApplicationContextTypeTypeImpl applicationContextTypeType = new ApplicationContextTypeTypeImpl();
		return applicationContextTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationSymbolType createApplicationSymbolType() {
		ApplicationSymbolTypeImpl applicationSymbolType = new ApplicationSymbolTypeImpl();
		return applicationSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationType createApplicationType() {
		ApplicationTypeImpl applicationType = new ApplicationTypeImpl();
		return applicationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationTypeType createApplicationTypeType() {
		ApplicationTypeTypeImpl applicationTypeType = new ApplicationTypeTypeImpl();
		return applicationTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeType createAttributeType() {
		AttributeTypeImpl attributeType = new AttributeTypeImpl();
		return attributeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindActionType createBindActionType() {
		BindActionTypeImpl bindActionType = new BindActionTypeImpl();
		return bindActionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Code createCode() {
		CodeImpl code = new CodeImpl();
		return code;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalPerformerSymbolType createConditionalPerformerSymbolType() {
		ConditionalPerformerSymbolTypeImpl conditionalPerformerSymbolType = new ConditionalPerformerSymbolTypeImpl();
		return conditionalPerformerSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalPerformerType createConditionalPerformerType() {
		ConditionalPerformerTypeImpl conditionalPerformerType = new ConditionalPerformerTypeImpl();
		return conditionalPerformerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContextType createContextType() {
		ContextTypeImpl contextType = new ContextTypeImpl();
		return contextType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMappingConnectionType createDataMappingConnectionType() {
		DataMappingConnectionTypeImpl dataMappingConnectionType = new DataMappingConnectionTypeImpl();
		return dataMappingConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMappingType createDataMappingType() {
		DataMappingTypeImpl dataMappingType = new DataMappingTypeImpl();
		return dataMappingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataPathType createDataPathType() {
		DataPathTypeImpl dataPathType = new DataPathTypeImpl();
		return dataPathType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSymbolType createDataSymbolType() {
		DataSymbolTypeImpl dataSymbolType = new DataSymbolTypeImpl();
		return dataSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType createDataType() {
		DataTypeImpl dataType = new DataTypeImpl();
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeType createDataTypeType() {
		DataTypeTypeImpl dataTypeType = new DataTypeTypeImpl();
		return dataTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DescriptionType createDescriptionType() {
		DescriptionTypeImpl descriptionType = new DescriptionTypeImpl();
		return descriptionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramType createDiagramType() {
		DiagramTypeImpl diagramType = new DiagramTypeImpl();
		return diagramType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndEventSymbol createEndEventSymbol() {
		EndEventSymbolImpl endEventSymbol = new EndEventSymbolImpl();
		return endEventSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventActionType createEventActionType() {
		EventActionTypeImpl eventActionType = new EventActionTypeImpl();
		return eventActionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventActionTypeType createEventActionTypeType() {
		EventActionTypeTypeImpl eventActionTypeType = new EventActionTypeTypeImpl();
		return eventActionTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventConditionTypeType createEventConditionTypeType() {
		EventConditionTypeTypeImpl eventConditionTypeType = new EventConditionTypeTypeImpl();
		return eventConditionTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventHandlerType createEventHandlerType() {
		EventHandlerTypeImpl eventHandlerType = new EventHandlerTypeImpl();
		return eventHandlerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutedByConnectionType createExecutedByConnectionType() {
		ExecutedByConnectionTypeImpl executedByConnectionType = new ExecutedByConnectionTypeImpl();
		return executedByConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdRef createIdRef() {
		IdRefImpl idRef = new IdRefImpl();
		return idRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GatewaySymbol createGatewaySymbol() {
		GatewaySymbolImpl gatewaySymbol = new GatewaySymbolImpl();
		return gatewaySymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericLinkConnectionType createGenericLinkConnectionType() {
		GenericLinkConnectionTypeImpl genericLinkConnectionType = new GenericLinkConnectionTypeImpl();
		return genericLinkConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupSymbolType createGroupSymbolType() {
		GroupSymbolTypeImpl groupSymbolType = new GroupSymbolTypeImpl();
		return groupSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntermediateEventSymbol createIntermediateEventSymbol() {
		IntermediateEventSymbolImpl intermediateEventSymbol = new IntermediateEventSymbolImpl();
		return intermediateEventSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LaneSymbol createLaneSymbol() {
		LaneSymbolImpl laneSymbol = new LaneSymbolImpl();
		return laneSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkTypeType createLinkTypeType() {
		LinkTypeTypeImpl linkTypeType = new LinkTypeTypeImpl();
		return linkTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelerSymbolType createModelerSymbolType() {
		ModelerSymbolTypeImpl modelerSymbolType = new ModelerSymbolTypeImpl();
		return modelerSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelerType createModelerType() {
		ModelerTypeImpl modelerType = new ModelerTypeImpl();
		return modelerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelType createModelType() {
		ModelTypeImpl modelType = new ModelTypeImpl();
		return modelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrganizationSymbolType createOrganizationSymbolType() {
		OrganizationSymbolTypeImpl organizationSymbolType = new OrganizationSymbolTypeImpl();
		return organizationSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrganizationType createOrganizationType() {
		OrganizationTypeImpl organizationType = new OrganizationTypeImpl();
		return organizationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterMappingType createParameterMappingType() {
		ParameterMappingTypeImpl parameterMappingType = new ParameterMappingTypeImpl();
		return parameterMappingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParticipantType createParticipantType() {
		ParticipantTypeImpl participantType = new ParticipantTypeImpl();
		return participantType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartOfConnectionType createPartOfConnectionType() {
		PartOfConnectionTypeImpl partOfConnectionType = new PartOfConnectionTypeImpl();
		return partOfConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerformsConnectionType createPerformsConnectionType() {
		PerformsConnectionTypeImpl performsConnectionType = new PerformsConnectionTypeImpl();
		return performsConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PoolSymbol createPoolSymbol() {
		PoolSymbolImpl poolSymbol = new PoolSymbolImpl();
		return poolSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessDefinitionType createProcessDefinitionType() {
		ProcessDefinitionTypeImpl processDefinitionType = new ProcessDefinitionTypeImpl();
		return processDefinitionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessSymbolType createProcessSymbolType() {
		ProcessSymbolTypeImpl processSymbolType = new ProcessSymbolTypeImpl();
		return processSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PublicInterfaceSymbol createPublicInterfaceSymbol() {
		PublicInterfaceSymbolImpl publicInterfaceSymbol = new PublicInterfaceSymbolImpl();
		return publicInterfaceSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualityControlType createQualityControlType() {
		QualityControlTypeImpl qualityControlType = new QualityControlTypeImpl();
		return qualityControlType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefersToConnectionType createRefersToConnectionType() {
		RefersToConnectionTypeImpl refersToConnectionType = new RefersToConnectionTypeImpl();
		return refersToConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleSymbolType createRoleSymbolType() {
		RoleSymbolTypeImpl roleSymbolType = new RoleSymbolTypeImpl();
		return roleSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleType createRoleType() {
		RoleTypeImpl roleType = new RoleTypeImpl();
		return roleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartEventSymbol createStartEventSymbol() {
		StartEventSymbolImpl startEventSymbol = new StartEventSymbolImpl();
		return startEventSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubProcessOfConnectionType createSubProcessOfConnectionType() {
		SubProcessOfConnectionTypeImpl subProcessOfConnectionType = new SubProcessOfConnectionTypeImpl();
		return subProcessOfConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TeamLeadConnectionType createTeamLeadConnectionType() {
		TeamLeadConnectionTypeImpl teamLeadConnectionType = new TeamLeadConnectionTypeImpl();
		return teamLeadConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextSymbolType createTextSymbolType() {
		TextSymbolTypeImpl textSymbolType = new TextSymbolTypeImpl();
		return textSymbolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextType createTextType() {
		TextTypeImpl textType = new TextTypeImpl();
		return textType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionConnectionType createTransitionConnectionType() {
		TransitionConnectionTypeImpl transitionConnectionType = new TransitionConnectionTypeImpl();
		return transitionConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionType createTransitionType() {
		TransitionTypeImpl transitionType = new TransitionTypeImpl();
		return transitionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggersConnectionType createTriggersConnectionType() {
		TriggersConnectionTypeImpl triggersConnectionType = new TriggersConnectionTypeImpl();
		return triggersConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggerType createTriggerType() {
		TriggerTypeImpl triggerType = new TriggerTypeImpl();
		return triggerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggerTypeType createTriggerTypeType() {
		TriggerTypeTypeImpl triggerTypeType = new TriggerTypeTypeImpl();
		return triggerTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnbindActionType createUnbindActionType() {
		UnbindActionTypeImpl unbindActionType = new UnbindActionTypeImpl();
		return unbindActionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewableType createViewableType() {
		ViewableTypeImpl viewableType = new ViewableTypeImpl();
		return viewableType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewType createViewType() {
		ViewTypeImpl viewType = new ViewTypeImpl();
		return viewType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorksForConnectionType createWorksForConnectionType() {
		WorksForConnectionTypeImpl worksForConnectionType = new WorksForConnectionTypeImpl();
		return worksForConnectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XmlTextNode createXmlTextNode() {
		XmlTextNodeImpl xmlTextNode = new XmlTextNodeImpl();
		return xmlTextNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityImplementationType createActivityImplementationTypeFromString(EDataType eDataType, String initialValue) {
		ActivityImplementationType result = ActivityImplementationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertActivityImplementationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirectionType createDirectionTypeFromString(EDataType eDataType, String initialValue) {
		DirectionType result = DirectionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirectionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowControlType createFlowControlTypeFromString(EDataType eDataType, String initialValue) {
		FlowControlType result = FlowControlType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFlowControlTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplementationType createImplementationTypeFromString(EDataType eDataType, String initialValue) {
		ImplementationType result = ImplementationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertImplementationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinSplitType createJoinSplitTypeFromString(EDataType eDataType, String initialValue) {
		JoinSplitType result = JoinSplitType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJoinSplitTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkCardinality createLinkCardinalityFromString(EDataType eDataType, String initialValue) {
		LinkCardinality result = LinkCardinality.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkCardinalityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkColor createLinkColorFromString(EDataType eDataType, String initialValue) {
		LinkColor result = LinkColor.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkColorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkLineStyle createLinkLineStyleFromString(EDataType eDataType, String initialValue) {
		LinkLineStyle result = LinkLineStyle.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkLineStyleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkEndStyle createLinkEndStyleFromString(EDataType eDataType, String initialValue) {
		LinkEndStyle result = LinkEndStyle.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkEndStyleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoopType createLoopTypeFromString(EDataType eDataType, String initialValue) {
		LoopType result = LoopType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLoopTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationType createOrientationTypeFromString(EDataType eDataType, String initialValue) {
		OrientationType result = OrientationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoutingType createRoutingTypeFromString(EDataType eDataType, String initialValue) {
		RoutingType result = RoutingType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRoutingTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubProcessModeType createSubProcessModeTypeFromString(EDataType eDataType, String initialValue) {
		SubProcessModeType result = SubProcessModeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSubProcessModeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramModeType createDiagramModeTypeFromString(EDataType eDataType, String initialValue) {
		DiagramModeType result = DiagramModeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDiagramModeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createElementIdFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertElementIdToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List createFeatureListFromString(EDataType eDataType, String initialValue) {
		return (List)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFeatureListToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityImplementationType createActivityImplementationTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createActivityImplementationTypeFromString(CarnotPackage.eINSTANCE.getActivityImplementationType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertActivityImplementationTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertActivityImplementationTypeToString(CarnotPackage.eINSTANCE.getActivityImplementationType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirectionType createDirectionTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDirectionTypeFromString(CarnotPackage.eINSTANCE.getDirectionType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirectionTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDirectionTypeToString(CarnotPackage.eINSTANCE.getDirectionType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowControlType createFlowControlTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createFlowControlTypeFromString(CarnotPackage.eINSTANCE.getFlowControlType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFlowControlTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertFlowControlTypeToString(CarnotPackage.eINSTANCE.getFlowControlType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplementationType createImplementationTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createImplementationTypeFromString(CarnotPackage.eINSTANCE.getImplementationType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertImplementationTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertImplementationTypeToString(CarnotPackage.eINSTANCE.getImplementationType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkCardinality createLinkCardinalityObjectFromString(EDataType eDataType, String initialValue) {
		return createLinkCardinalityFromString(CarnotPackage.eINSTANCE.getLinkCardinality(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkCardinalityObjectToString(EDataType eDataType, Object instanceValue) {
		return convertLinkCardinalityToString(CarnotPackage.eINSTANCE.getLinkCardinality(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkColor createLinkColorObjectFromString(EDataType eDataType, String initialValue) {
		return createLinkColorFromString(CarnotPackage.eINSTANCE.getLinkColor(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkColorObjectToString(EDataType eDataType, Object instanceValue) {
		return convertLinkColorToString(CarnotPackage.eINSTANCE.getLinkColor(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkLineStyle createLinkLineStyleObjectFromString(EDataType eDataType, String initialValue) {
		return createLinkLineStyleFromString(CarnotPackage.eINSTANCE.getLinkLineStyle(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkLineStyleObjectToString(EDataType eDataType, Object instanceValue) {
		return convertLinkLineStyleToString(CarnotPackage.eINSTANCE.getLinkLineStyle(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkEndStyle createLinkEndStyleObjectFromString(EDataType eDataType, String initialValue) {
		return createLinkEndStyleFromString(CarnotPackage.eINSTANCE.getLinkEndStyle(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkEndStyleObjectToString(EDataType eDataType, Object instanceValue) {
		return convertLinkEndStyleToString(CarnotPackage.eINSTANCE.getLinkEndStyle(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinSplitType createJoinSplitTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createJoinSplitTypeFromString(CarnotPackage.eINSTANCE.getJoinSplitType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJoinSplitTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertJoinSplitTypeToString(CarnotPackage.eINSTANCE.getJoinSplitType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoopType createLoopTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createLoopTypeFromString(CarnotPackage.eINSTANCE.getLoopType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLoopTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertLoopTypeToString(CarnotPackage.eINSTANCE.getLoopType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationType createOrientationTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createOrientationTypeFromString(CarnotPackage.eINSTANCE.getOrientationType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertOrientationTypeToString(CarnotPackage.eINSTANCE.getOrientationType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoutingType createRoutingTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createRoutingTypeFromString(CarnotPackage.eINSTANCE.getRoutingType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRoutingTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertRoutingTypeToString(CarnotPackage.eINSTANCE.getRoutingType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubProcessModeType createSubProcessModeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createSubProcessModeTypeFromString(CarnotPackage.eINSTANCE.getSubProcessModeType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSubProcessModeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertSubProcessModeTypeToString(CarnotPackage.eINSTANCE.getSubProcessModeType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramModeType createDiagramModeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDiagramModeTypeFromString(CarnotPackage.eINSTANCE.getDiagramModeType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDiagramModeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDiagramModeTypeToString(CarnotPackage.eINSTANCE.getDiagramModeType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CarnotPackage getCarnotPackage() {
		return (CarnotPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CarnotPackage getPackage() {
		return CarnotPackage.eINSTANCE;
	}

} //CarnotFactoryImpl
