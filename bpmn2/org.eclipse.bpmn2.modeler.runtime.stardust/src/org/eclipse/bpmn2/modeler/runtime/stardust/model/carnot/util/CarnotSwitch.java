/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.util;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage
 * @generated
 */
public class CarnotSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CarnotPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CarnotSwitch() {
		if (modelPackage == null) {
			modelPackage = CarnotPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CarnotPackage.COORDINATES: {
				Coordinates coordinates = (Coordinates)theEObject;
				T result = caseCoordinates(coordinates);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IIDENTIFIABLE_ELEMENT: {
				IIdentifiableElement iIdentifiableElement = (IIdentifiableElement)theEObject;
				T result = caseIIdentifiableElement(iIdentifiableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IEXTENSIBLE_ELEMENT: {
				IExtensibleElement iExtensibleElement = (IExtensibleElement)theEObject;
				T result = caseIExtensibleElement(iExtensibleElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IDENTIFIABLE_REFERENCE: {
				IdentifiableReference identifiableReference = (IdentifiableReference)theEObject;
				T result = caseIdentifiableReference(identifiableReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IMODEL_ELEMENT: {
				IModelElement iModelElement = (IModelElement)theEObject;
				T result = caseIModelElement(iModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IIDENTIFIABLE_MODEL_ELEMENT: {
				IIdentifiableModelElement iIdentifiableModelElement = (IIdentifiableModelElement)theEObject;
				T result = caseIIdentifiableModelElement(iIdentifiableModelElement);
				if (result == null) result = caseIModelElement(iIdentifiableModelElement);
				if (result == null) result = caseIIdentifiableElement(iIdentifiableModelElement);
				if (result == null) result = caseIExtensibleElement(iIdentifiableModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IEVENT_HANDLER_OWNER: {
				IEventHandlerOwner iEventHandlerOwner = (IEventHandlerOwner)theEObject;
				T result = caseIEventHandlerOwner(iEventHandlerOwner);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IACCESS_POINT_OWNER: {
				IAccessPointOwner iAccessPointOwner = (IAccessPointOwner)theEObject;
				T result = caseIAccessPointOwner(iAccessPointOwner);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IMETA_TYPE: {
				IMetaType iMetaType = (IMetaType)theEObject;
				T result = caseIMetaType(iMetaType);
				if (result == null) result = caseIIdentifiableModelElement(iMetaType);
				if (result == null) result = caseIModelElement(iMetaType);
				if (result == null) result = caseIIdentifiableElement(iMetaType);
				if (result == null) result = caseIExtensibleElement(iMetaType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ITYPED_ELEMENT: {
				ITypedElement iTypedElement = (ITypedElement)theEObject;
				T result = caseITypedElement(iTypedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ISYMBOL_CONTAINER: {
				ISymbolContainer iSymbolContainer = (ISymbolContainer)theEObject;
				T result = caseISymbolContainer(iSymbolContainer);
				if (result == null) result = caseIExtensibleElement(iSymbolContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IGRAPHICAL_OBJECT: {
				IGraphicalObject iGraphicalObject = (IGraphicalObject)theEObject;
				T result = caseIGraphicalObject(iGraphicalObject);
				if (result == null) result = caseIModelElement(iGraphicalObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.INODE_SYMBOL: {
				INodeSymbol iNodeSymbol = (INodeSymbol)theEObject;
				T result = caseINodeSymbol(iNodeSymbol);
				if (result == null) result = caseIGraphicalObject(iNodeSymbol);
				if (result == null) result = caseIModelElement(iNodeSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ISWIMLANE_SYMBOL: {
				ISwimlaneSymbol iSwimlaneSymbol = (ISwimlaneSymbol)theEObject;
				T result = caseISwimlaneSymbol(iSwimlaneSymbol);
				if (result == null) result = caseINodeSymbol(iSwimlaneSymbol);
				if (result == null) result = caseIIdentifiableElement(iSwimlaneSymbol);
				if (result == null) result = caseIGraphicalObject(iSwimlaneSymbol);
				if (result == null) result = caseIModelElement(iSwimlaneSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL: {
				IModelElementNodeSymbol iModelElementNodeSymbol = (IModelElementNodeSymbol)theEObject;
				T result = caseIModelElementNodeSymbol(iModelElementNodeSymbol);
				if (result == null) result = caseINodeSymbol(iModelElementNodeSymbol);
				if (result == null) result = caseIGraphicalObject(iModelElementNodeSymbol);
				if (result == null) result = caseIModelElement(iModelElementNodeSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IFLOW_OBJECT_SYMBOL: {
				IFlowObjectSymbol iFlowObjectSymbol = (IFlowObjectSymbol)theEObject;
				T result = caseIFlowObjectSymbol(iFlowObjectSymbol);
				if (result == null) result = caseINodeSymbol(iFlowObjectSymbol);
				if (result == null) result = caseIGraphicalObject(iFlowObjectSymbol);
				if (result == null) result = caseIModelElement(iFlowObjectSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ICONNECTION_SYMBOL: {
				IConnectionSymbol iConnectionSymbol = (IConnectionSymbol)theEObject;
				T result = caseIConnectionSymbol(iConnectionSymbol);
				if (result == null) result = caseIGraphicalObject(iConnectionSymbol);
				if (result == null) result = caseIModelElement(iConnectionSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IMODEL_PARTICIPANT: {
				IModelParticipant iModelParticipant = (IModelParticipant)theEObject;
				T result = caseIModelParticipant(iModelParticipant);
				if (result == null) result = caseIIdentifiableModelElement(iModelParticipant);
				if (result == null) result = caseIModelElement(iModelParticipant);
				if (result == null) result = caseIIdentifiableElement(iModelParticipant);
				if (result == null) result = caseIExtensibleElement(iModelParticipant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.IMODEL_PARTICIPANT_SYMBOL: {
				IModelParticipantSymbol iModelParticipantSymbol = (IModelParticipantSymbol)theEObject;
				T result = caseIModelParticipantSymbol(iModelParticipantSymbol);
				if (result == null) result = caseIModelElementNodeSymbol(iModelParticipantSymbol);
				if (result == null) result = caseINodeSymbol(iModelParticipantSymbol);
				if (result == null) result = caseIGraphicalObject(iModelParticipantSymbol);
				if (result == null) result = caseIModelElement(iModelParticipantSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ABSTRACT_EVENT_ACTION: {
				AbstractEventAction abstractEventAction = (AbstractEventAction)theEObject;
				T result = caseAbstractEventAction(abstractEventAction);
				if (result == null) result = caseIIdentifiableModelElement(abstractEventAction);
				if (result == null) result = caseITypedElement(abstractEventAction);
				if (result == null) result = caseIModelElement(abstractEventAction);
				if (result == null) result = caseIIdentifiableElement(abstractEventAction);
				if (result == null) result = caseIExtensibleElement(abstractEventAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ABSTRACT_EVENT_SYMBOL: {
				AbstractEventSymbol abstractEventSymbol = (AbstractEventSymbol)theEObject;
				T result = caseAbstractEventSymbol(abstractEventSymbol);
				if (result == null) result = caseIFlowObjectSymbol(abstractEventSymbol);
				if (result == null) result = caseIModelElementNodeSymbol(abstractEventSymbol);
				if (result == null) result = caseINodeSymbol(abstractEventSymbol);
				if (result == null) result = caseIGraphicalObject(abstractEventSymbol);
				if (result == null) result = caseIModelElement(abstractEventSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ACCESS_POINT_TYPE: {
				AccessPointType accessPointType = (AccessPointType)theEObject;
				T result = caseAccessPointType(accessPointType);
				if (result == null) result = caseIIdentifiableModelElement(accessPointType);
				if (result == null) result = caseITypedElement(accessPointType);
				if (result == null) result = caseIModelElement(accessPointType);
				if (result == null) result = caseIIdentifiableElement(accessPointType);
				if (result == null) result = caseIExtensibleElement(accessPointType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ACTIVITY_SYMBOL_TYPE: {
				ActivitySymbolType activitySymbolType = (ActivitySymbolType)theEObject;
				T result = caseActivitySymbolType(activitySymbolType);
				if (result == null) result = caseIFlowObjectSymbol(activitySymbolType);
				if (result == null) result = caseIModelElementNodeSymbol(activitySymbolType);
				if (result == null) result = caseINodeSymbol(activitySymbolType);
				if (result == null) result = caseIGraphicalObject(activitySymbolType);
				if (result == null) result = caseIModelElement(activitySymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ACTIVITY_TYPE: {
				ActivityType activityType = (ActivityType)theEObject;
				T result = caseActivityType(activityType);
				if (result == null) result = caseIIdentifiableModelElement(activityType);
				if (result == null) result = caseIEventHandlerOwner(activityType);
				if (result == null) result = caseIModelElement(activityType);
				if (result == null) result = caseIIdentifiableElement(activityType);
				if (result == null) result = caseIExtensibleElement(activityType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ANNOTATION_SYMBOL_TYPE: {
				AnnotationSymbolType annotationSymbolType = (AnnotationSymbolType)theEObject;
				T result = caseAnnotationSymbolType(annotationSymbolType);
				if (result == null) result = caseINodeSymbol(annotationSymbolType);
				if (result == null) result = caseIGraphicalObject(annotationSymbolType);
				if (result == null) result = caseIModelElement(annotationSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE: {
				ApplicationContextTypeType applicationContextTypeType = (ApplicationContextTypeType)theEObject;
				T result = caseApplicationContextTypeType(applicationContextTypeType);
				if (result == null) result = caseIMetaType(applicationContextTypeType);
				if (result == null) result = caseIIdentifiableModelElement(applicationContextTypeType);
				if (result == null) result = caseIModelElement(applicationContextTypeType);
				if (result == null) result = caseIIdentifiableElement(applicationContextTypeType);
				if (result == null) result = caseIExtensibleElement(applicationContextTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.APPLICATION_SYMBOL_TYPE: {
				ApplicationSymbolType applicationSymbolType = (ApplicationSymbolType)theEObject;
				T result = caseApplicationSymbolType(applicationSymbolType);
				if (result == null) result = caseIModelElementNodeSymbol(applicationSymbolType);
				if (result == null) result = caseINodeSymbol(applicationSymbolType);
				if (result == null) result = caseIGraphicalObject(applicationSymbolType);
				if (result == null) result = caseIModelElement(applicationSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.APPLICATION_TYPE: {
				ApplicationType applicationType = (ApplicationType)theEObject;
				T result = caseApplicationType(applicationType);
				if (result == null) result = caseIIdentifiableModelElement(applicationType);
				if (result == null) result = caseITypedElement(applicationType);
				if (result == null) result = caseIAccessPointOwner(applicationType);
				if (result == null) result = caseIModelElement(applicationType);
				if (result == null) result = caseIIdentifiableElement(applicationType);
				if (result == null) result = caseIExtensibleElement(applicationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.APPLICATION_TYPE_TYPE: {
				ApplicationTypeType applicationTypeType = (ApplicationTypeType)theEObject;
				T result = caseApplicationTypeType(applicationTypeType);
				if (result == null) result = caseIMetaType(applicationTypeType);
				if (result == null) result = caseIIdentifiableModelElement(applicationTypeType);
				if (result == null) result = caseIModelElement(applicationTypeType);
				if (result == null) result = caseIIdentifiableElement(applicationTypeType);
				if (result == null) result = caseIExtensibleElement(applicationTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ATTRIBUTE_TYPE: {
				AttributeType attributeType = (AttributeType)theEObject;
				T result = caseAttributeType(attributeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.BIND_ACTION_TYPE: {
				BindActionType bindActionType = (BindActionType)theEObject;
				T result = caseBindActionType(bindActionType);
				if (result == null) result = caseAbstractEventAction(bindActionType);
				if (result == null) result = caseIIdentifiableModelElement(bindActionType);
				if (result == null) result = caseITypedElement(bindActionType);
				if (result == null) result = caseIModelElement(bindActionType);
				if (result == null) result = caseIIdentifiableElement(bindActionType);
				if (result == null) result = caseIExtensibleElement(bindActionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.CODE: {
				Code code = (Code)theEObject;
				T result = caseCode(code);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE: {
				ConditionalPerformerSymbolType conditionalPerformerSymbolType = (ConditionalPerformerSymbolType)theEObject;
				T result = caseConditionalPerformerSymbolType(conditionalPerformerSymbolType);
				if (result == null) result = caseIModelParticipantSymbol(conditionalPerformerSymbolType);
				if (result == null) result = caseIModelElementNodeSymbol(conditionalPerformerSymbolType);
				if (result == null) result = caseINodeSymbol(conditionalPerformerSymbolType);
				if (result == null) result = caseIGraphicalObject(conditionalPerformerSymbolType);
				if (result == null) result = caseIModelElement(conditionalPerformerSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE: {
				ConditionalPerformerType conditionalPerformerType = (ConditionalPerformerType)theEObject;
				T result = caseConditionalPerformerType(conditionalPerformerType);
				if (result == null) result = caseIModelParticipant(conditionalPerformerType);
				if (result == null) result = caseIIdentifiableModelElement(conditionalPerformerType);
				if (result == null) result = caseIModelElement(conditionalPerformerType);
				if (result == null) result = caseIIdentifiableElement(conditionalPerformerType);
				if (result == null) result = caseIExtensibleElement(conditionalPerformerType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.CONTEXT_TYPE: {
				ContextType contextType = (ContextType)theEObject;
				T result = caseContextType(contextType);
				if (result == null) result = caseIModelElement(contextType);
				if (result == null) result = caseIExtensibleElement(contextType);
				if (result == null) result = caseITypedElement(contextType);
				if (result == null) result = caseIAccessPointOwner(contextType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.DATA_MAPPING_CONNECTION_TYPE: {
				DataMappingConnectionType dataMappingConnectionType = (DataMappingConnectionType)theEObject;
				T result = caseDataMappingConnectionType(dataMappingConnectionType);
				if (result == null) result = caseIConnectionSymbol(dataMappingConnectionType);
				if (result == null) result = caseIGraphicalObject(dataMappingConnectionType);
				if (result == null) result = caseIModelElement(dataMappingConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.DATA_MAPPING_TYPE: {
				DataMappingType dataMappingType = (DataMappingType)theEObject;
				T result = caseDataMappingType(dataMappingType);
				if (result == null) result = caseIModelElement(dataMappingType);
				if (result == null) result = caseIIdentifiableElement(dataMappingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.DATA_PATH_TYPE: {
				DataPathType dataPathType = (DataPathType)theEObject;
				T result = caseDataPathType(dataPathType);
				if (result == null) result = caseIIdentifiableModelElement(dataPathType);
				if (result == null) result = caseIModelElement(dataPathType);
				if (result == null) result = caseIIdentifiableElement(dataPathType);
				if (result == null) result = caseIExtensibleElement(dataPathType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.DATA_SYMBOL_TYPE: {
				DataSymbolType dataSymbolType = (DataSymbolType)theEObject;
				T result = caseDataSymbolType(dataSymbolType);
				if (result == null) result = caseIModelElementNodeSymbol(dataSymbolType);
				if (result == null) result = caseINodeSymbol(dataSymbolType);
				if (result == null) result = caseIGraphicalObject(dataSymbolType);
				if (result == null) result = caseIModelElement(dataSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.DATA_TYPE: {
				DataType dataType = (DataType)theEObject;
				T result = caseDataType(dataType);
				if (result == null) result = caseIIdentifiableModelElement(dataType);
				if (result == null) result = caseITypedElement(dataType);
				if (result == null) result = caseIModelElement(dataType);
				if (result == null) result = caseIIdentifiableElement(dataType);
				if (result == null) result = caseIExtensibleElement(dataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.DATA_TYPE_TYPE: {
				DataTypeType dataTypeType = (DataTypeType)theEObject;
				T result = caseDataTypeType(dataTypeType);
				if (result == null) result = caseIMetaType(dataTypeType);
				if (result == null) result = caseIIdentifiableModelElement(dataTypeType);
				if (result == null) result = caseIModelElement(dataTypeType);
				if (result == null) result = caseIIdentifiableElement(dataTypeType);
				if (result == null) result = caseIExtensibleElement(dataTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.DESCRIPTION_TYPE: {
				DescriptionType descriptionType = (DescriptionType)theEObject;
				T result = caseDescriptionType(descriptionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.DIAGRAM_TYPE: {
				DiagramType diagramType = (DiagramType)theEObject;
				T result = caseDiagramType(diagramType);
				if (result == null) result = caseISymbolContainer(diagramType);
				if (result == null) result = caseIModelElement(diagramType);
				if (result == null) result = caseIExtensibleElement(diagramType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.END_EVENT_SYMBOL: {
				EndEventSymbol endEventSymbol = (EndEventSymbol)theEObject;
				T result = caseEndEventSymbol(endEventSymbol);
				if (result == null) result = caseAbstractEventSymbol(endEventSymbol);
				if (result == null) result = caseIFlowObjectSymbol(endEventSymbol);
				if (result == null) result = caseIModelElementNodeSymbol(endEventSymbol);
				if (result == null) result = caseINodeSymbol(endEventSymbol);
				if (result == null) result = caseIGraphicalObject(endEventSymbol);
				if (result == null) result = caseIModelElement(endEventSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.EVENT_ACTION_TYPE: {
				EventActionType eventActionType = (EventActionType)theEObject;
				T result = caseEventActionType(eventActionType);
				if (result == null) result = caseAbstractEventAction(eventActionType);
				if (result == null) result = caseIIdentifiableModelElement(eventActionType);
				if (result == null) result = caseITypedElement(eventActionType);
				if (result == null) result = caseIModelElement(eventActionType);
				if (result == null) result = caseIIdentifiableElement(eventActionType);
				if (result == null) result = caseIExtensibleElement(eventActionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.EVENT_ACTION_TYPE_TYPE: {
				EventActionTypeType eventActionTypeType = (EventActionTypeType)theEObject;
				T result = caseEventActionTypeType(eventActionTypeType);
				if (result == null) result = caseIMetaType(eventActionTypeType);
				if (result == null) result = caseIIdentifiableModelElement(eventActionTypeType);
				if (result == null) result = caseIModelElement(eventActionTypeType);
				if (result == null) result = caseIIdentifiableElement(eventActionTypeType);
				if (result == null) result = caseIExtensibleElement(eventActionTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.EVENT_CONDITION_TYPE_TYPE: {
				EventConditionTypeType eventConditionTypeType = (EventConditionTypeType)theEObject;
				T result = caseEventConditionTypeType(eventConditionTypeType);
				if (result == null) result = caseIMetaType(eventConditionTypeType);
				if (result == null) result = caseIIdentifiableModelElement(eventConditionTypeType);
				if (result == null) result = caseIModelElement(eventConditionTypeType);
				if (result == null) result = caseIIdentifiableElement(eventConditionTypeType);
				if (result == null) result = caseIExtensibleElement(eventConditionTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.EVENT_HANDLER_TYPE: {
				EventHandlerType eventHandlerType = (EventHandlerType)theEObject;
				T result = caseEventHandlerType(eventHandlerType);
				if (result == null) result = caseIIdentifiableModelElement(eventHandlerType);
				if (result == null) result = caseITypedElement(eventHandlerType);
				if (result == null) result = caseIAccessPointOwner(eventHandlerType);
				if (result == null) result = caseIModelElement(eventHandlerType);
				if (result == null) result = caseIIdentifiableElement(eventHandlerType);
				if (result == null) result = caseIExtensibleElement(eventHandlerType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.EXECUTED_BY_CONNECTION_TYPE: {
				ExecutedByConnectionType executedByConnectionType = (ExecutedByConnectionType)theEObject;
				T result = caseExecutedByConnectionType(executedByConnectionType);
				if (result == null) result = caseIConnectionSymbol(executedByConnectionType);
				if (result == null) result = caseIGraphicalObject(executedByConnectionType);
				if (result == null) result = caseIModelElement(executedByConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ID_REF: {
				IdRef idRef = (IdRef)theEObject;
				T result = caseIdRef(idRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.GATEWAY_SYMBOL: {
				GatewaySymbol gatewaySymbol = (GatewaySymbol)theEObject;
				T result = caseGatewaySymbol(gatewaySymbol);
				if (result == null) result = caseIFlowObjectSymbol(gatewaySymbol);
				if (result == null) result = caseINodeSymbol(gatewaySymbol);
				if (result == null) result = caseIGraphicalObject(gatewaySymbol);
				if (result == null) result = caseIModelElement(gatewaySymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE: {
				GenericLinkConnectionType genericLinkConnectionType = (GenericLinkConnectionType)theEObject;
				T result = caseGenericLinkConnectionType(genericLinkConnectionType);
				if (result == null) result = caseIConnectionSymbol(genericLinkConnectionType);
				if (result == null) result = caseITypedElement(genericLinkConnectionType);
				if (result == null) result = caseIGraphicalObject(genericLinkConnectionType);
				if (result == null) result = caseIModelElement(genericLinkConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.GROUP_SYMBOL_TYPE: {
				GroupSymbolType groupSymbolType = (GroupSymbolType)theEObject;
				T result = caseGroupSymbolType(groupSymbolType);
				if (result == null) result = caseISymbolContainer(groupSymbolType);
				if (result == null) result = caseINodeSymbol(groupSymbolType);
				if (result == null) result = caseIExtensibleElement(groupSymbolType);
				if (result == null) result = caseIGraphicalObject(groupSymbolType);
				if (result == null) result = caseIModelElement(groupSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.INTERMEDIATE_EVENT_SYMBOL: {
				IntermediateEventSymbol intermediateEventSymbol = (IntermediateEventSymbol)theEObject;
				T result = caseIntermediateEventSymbol(intermediateEventSymbol);
				if (result == null) result = caseAbstractEventSymbol(intermediateEventSymbol);
				if (result == null) result = caseIFlowObjectSymbol(intermediateEventSymbol);
				if (result == null) result = caseIModelElementNodeSymbol(intermediateEventSymbol);
				if (result == null) result = caseINodeSymbol(intermediateEventSymbol);
				if (result == null) result = caseIGraphicalObject(intermediateEventSymbol);
				if (result == null) result = caseIModelElement(intermediateEventSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.LANE_SYMBOL: {
				LaneSymbol laneSymbol = (LaneSymbol)theEObject;
				T result = caseLaneSymbol(laneSymbol);
				if (result == null) result = caseISymbolContainer(laneSymbol);
				if (result == null) result = caseISwimlaneSymbol(laneSymbol);
				if (result == null) result = caseIExtensibleElement(laneSymbol);
				if (result == null) result = caseINodeSymbol(laneSymbol);
				if (result == null) result = caseIIdentifiableElement(laneSymbol);
				if (result == null) result = caseIGraphicalObject(laneSymbol);
				if (result == null) result = caseIModelElement(laneSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.LINK_TYPE_TYPE: {
				LinkTypeType linkTypeType = (LinkTypeType)theEObject;
				T result = caseLinkTypeType(linkTypeType);
				if (result == null) result = caseIMetaType(linkTypeType);
				if (result == null) result = caseIIdentifiableModelElement(linkTypeType);
				if (result == null) result = caseIModelElement(linkTypeType);
				if (result == null) result = caseIIdentifiableElement(linkTypeType);
				if (result == null) result = caseIExtensibleElement(linkTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.MODELER_SYMBOL_TYPE: {
				ModelerSymbolType modelerSymbolType = (ModelerSymbolType)theEObject;
				T result = caseModelerSymbolType(modelerSymbolType);
				if (result == null) result = caseIModelElementNodeSymbol(modelerSymbolType);
				if (result == null) result = caseINodeSymbol(modelerSymbolType);
				if (result == null) result = caseIGraphicalObject(modelerSymbolType);
				if (result == null) result = caseIModelElement(modelerSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.MODELER_TYPE: {
				ModelerType modelerType = (ModelerType)theEObject;
				T result = caseModelerType(modelerType);
				if (result == null) result = caseIIdentifiableModelElement(modelerType);
				if (result == null) result = caseIModelElement(modelerType);
				if (result == null) result = caseIIdentifiableElement(modelerType);
				if (result == null) result = caseIExtensibleElement(modelerType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.MODEL_TYPE: {
				ModelType modelType = (ModelType)theEObject;
				T result = caseModelType(modelType);
				if (result == null) result = caseIIdentifiableElement(modelType);
				if (result == null) result = caseIExtensibleElement(modelType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ORGANIZATION_SYMBOL_TYPE: {
				OrganizationSymbolType organizationSymbolType = (OrganizationSymbolType)theEObject;
				T result = caseOrganizationSymbolType(organizationSymbolType);
				if (result == null) result = caseIModelParticipantSymbol(organizationSymbolType);
				if (result == null) result = caseIModelElementNodeSymbol(organizationSymbolType);
				if (result == null) result = caseINodeSymbol(organizationSymbolType);
				if (result == null) result = caseIGraphicalObject(organizationSymbolType);
				if (result == null) result = caseIModelElement(organizationSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ORGANIZATION_TYPE: {
				OrganizationType organizationType = (OrganizationType)theEObject;
				T result = caseOrganizationType(organizationType);
				if (result == null) result = caseIModelParticipant(organizationType);
				if (result == null) result = caseIIdentifiableModelElement(organizationType);
				if (result == null) result = caseIModelElement(organizationType);
				if (result == null) result = caseIIdentifiableElement(organizationType);
				if (result == null) result = caseIExtensibleElement(organizationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.PARAMETER_MAPPING_TYPE: {
				ParameterMappingType parameterMappingType = (ParameterMappingType)theEObject;
				T result = caseParameterMappingType(parameterMappingType);
				if (result == null) result = caseIModelElement(parameterMappingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.PARTICIPANT_TYPE: {
				ParticipantType participantType = (ParticipantType)theEObject;
				T result = caseParticipantType(participantType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.PART_OF_CONNECTION_TYPE: {
				PartOfConnectionType partOfConnectionType = (PartOfConnectionType)theEObject;
				T result = casePartOfConnectionType(partOfConnectionType);
				if (result == null) result = caseIConnectionSymbol(partOfConnectionType);
				if (result == null) result = caseIGraphicalObject(partOfConnectionType);
				if (result == null) result = caseIModelElement(partOfConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.PERFORMS_CONNECTION_TYPE: {
				PerformsConnectionType performsConnectionType = (PerformsConnectionType)theEObject;
				T result = casePerformsConnectionType(performsConnectionType);
				if (result == null) result = caseIConnectionSymbol(performsConnectionType);
				if (result == null) result = caseIGraphicalObject(performsConnectionType);
				if (result == null) result = caseIModelElement(performsConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.POOL_SYMBOL: {
				PoolSymbol poolSymbol = (PoolSymbol)theEObject;
				T result = casePoolSymbol(poolSymbol);
				if (result == null) result = caseISymbolContainer(poolSymbol);
				if (result == null) result = caseISwimlaneSymbol(poolSymbol);
				if (result == null) result = caseIExtensibleElement(poolSymbol);
				if (result == null) result = caseINodeSymbol(poolSymbol);
				if (result == null) result = caseIIdentifiableElement(poolSymbol);
				if (result == null) result = caseIGraphicalObject(poolSymbol);
				if (result == null) result = caseIModelElement(poolSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.PROCESS_DEFINITION_TYPE: {
				ProcessDefinitionType processDefinitionType = (ProcessDefinitionType)theEObject;
				T result = caseProcessDefinitionType(processDefinitionType);
				if (result == null) result = caseIIdentifiableModelElement(processDefinitionType);
				if (result == null) result = caseIEventHandlerOwner(processDefinitionType);
				if (result == null) result = caseIModelElement(processDefinitionType);
				if (result == null) result = caseIIdentifiableElement(processDefinitionType);
				if (result == null) result = caseIExtensibleElement(processDefinitionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.PROCESS_SYMBOL_TYPE: {
				ProcessSymbolType processSymbolType = (ProcessSymbolType)theEObject;
				T result = caseProcessSymbolType(processSymbolType);
				if (result == null) result = caseIModelElementNodeSymbol(processSymbolType);
				if (result == null) result = caseINodeSymbol(processSymbolType);
				if (result == null) result = caseIGraphicalObject(processSymbolType);
				if (result == null) result = caseIModelElement(processSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.PUBLIC_INTERFACE_SYMBOL: {
				PublicInterfaceSymbol publicInterfaceSymbol = (PublicInterfaceSymbol)theEObject;
				T result = casePublicInterfaceSymbol(publicInterfaceSymbol);
				if (result == null) result = caseAbstractEventSymbol(publicInterfaceSymbol);
				if (result == null) result = caseIFlowObjectSymbol(publicInterfaceSymbol);
				if (result == null) result = caseIModelElementNodeSymbol(publicInterfaceSymbol);
				if (result == null) result = caseINodeSymbol(publicInterfaceSymbol);
				if (result == null) result = caseIGraphicalObject(publicInterfaceSymbol);
				if (result == null) result = caseIModelElement(publicInterfaceSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.QUALITY_CONTROL_TYPE: {
				QualityControlType qualityControlType = (QualityControlType)theEObject;
				T result = caseQualityControlType(qualityControlType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.REFERS_TO_CONNECTION_TYPE: {
				RefersToConnectionType refersToConnectionType = (RefersToConnectionType)theEObject;
				T result = caseRefersToConnectionType(refersToConnectionType);
				if (result == null) result = caseIConnectionSymbol(refersToConnectionType);
				if (result == null) result = caseIGraphicalObject(refersToConnectionType);
				if (result == null) result = caseIModelElement(refersToConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ROLE_SYMBOL_TYPE: {
				RoleSymbolType roleSymbolType = (RoleSymbolType)theEObject;
				T result = caseRoleSymbolType(roleSymbolType);
				if (result == null) result = caseIModelParticipantSymbol(roleSymbolType);
				if (result == null) result = caseIModelElementNodeSymbol(roleSymbolType);
				if (result == null) result = caseINodeSymbol(roleSymbolType);
				if (result == null) result = caseIGraphicalObject(roleSymbolType);
				if (result == null) result = caseIModelElement(roleSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.ROLE_TYPE: {
				RoleType roleType = (RoleType)theEObject;
				T result = caseRoleType(roleType);
				if (result == null) result = caseIModelParticipant(roleType);
				if (result == null) result = caseIIdentifiableModelElement(roleType);
				if (result == null) result = caseIModelElement(roleType);
				if (result == null) result = caseIIdentifiableElement(roleType);
				if (result == null) result = caseIExtensibleElement(roleType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.START_EVENT_SYMBOL: {
				StartEventSymbol startEventSymbol = (StartEventSymbol)theEObject;
				T result = caseStartEventSymbol(startEventSymbol);
				if (result == null) result = caseAbstractEventSymbol(startEventSymbol);
				if (result == null) result = caseIFlowObjectSymbol(startEventSymbol);
				if (result == null) result = caseIModelElementNodeSymbol(startEventSymbol);
				if (result == null) result = caseINodeSymbol(startEventSymbol);
				if (result == null) result = caseIGraphicalObject(startEventSymbol);
				if (result == null) result = caseIModelElement(startEventSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.SUB_PROCESS_OF_CONNECTION_TYPE: {
				SubProcessOfConnectionType subProcessOfConnectionType = (SubProcessOfConnectionType)theEObject;
				T result = caseSubProcessOfConnectionType(subProcessOfConnectionType);
				if (result == null) result = caseIConnectionSymbol(subProcessOfConnectionType);
				if (result == null) result = caseIGraphicalObject(subProcessOfConnectionType);
				if (result == null) result = caseIModelElement(subProcessOfConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE: {
				TeamLeadConnectionType teamLeadConnectionType = (TeamLeadConnectionType)theEObject;
				T result = caseTeamLeadConnectionType(teamLeadConnectionType);
				if (result == null) result = caseIConnectionSymbol(teamLeadConnectionType);
				if (result == null) result = caseIGraphicalObject(teamLeadConnectionType);
				if (result == null) result = caseIModelElement(teamLeadConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.TEXT_SYMBOL_TYPE: {
				TextSymbolType textSymbolType = (TextSymbolType)theEObject;
				T result = caseTextSymbolType(textSymbolType);
				if (result == null) result = caseINodeSymbol(textSymbolType);
				if (result == null) result = caseIGraphicalObject(textSymbolType);
				if (result == null) result = caseIModelElement(textSymbolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.TEXT_TYPE: {
				TextType textType = (TextType)theEObject;
				T result = caseTextType(textType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.TRANSITION_CONNECTION_TYPE: {
				TransitionConnectionType transitionConnectionType = (TransitionConnectionType)theEObject;
				T result = caseTransitionConnectionType(transitionConnectionType);
				if (result == null) result = caseIConnectionSymbol(transitionConnectionType);
				if (result == null) result = caseIGraphicalObject(transitionConnectionType);
				if (result == null) result = caseIModelElement(transitionConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.TRANSITION_TYPE: {
				TransitionType transitionType = (TransitionType)theEObject;
				T result = caseTransitionType(transitionType);
				if (result == null) result = caseIIdentifiableModelElement(transitionType);
				if (result == null) result = caseIModelElement(transitionType);
				if (result == null) result = caseIIdentifiableElement(transitionType);
				if (result == null) result = caseIExtensibleElement(transitionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.TRIGGERS_CONNECTION_TYPE: {
				TriggersConnectionType triggersConnectionType = (TriggersConnectionType)theEObject;
				T result = caseTriggersConnectionType(triggersConnectionType);
				if (result == null) result = caseIConnectionSymbol(triggersConnectionType);
				if (result == null) result = caseIGraphicalObject(triggersConnectionType);
				if (result == null) result = caseIModelElement(triggersConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.TRIGGER_TYPE: {
				TriggerType triggerType = (TriggerType)theEObject;
				T result = caseTriggerType(triggerType);
				if (result == null) result = caseIIdentifiableModelElement(triggerType);
				if (result == null) result = caseITypedElement(triggerType);
				if (result == null) result = caseIAccessPointOwner(triggerType);
				if (result == null) result = caseIModelElement(triggerType);
				if (result == null) result = caseIIdentifiableElement(triggerType);
				if (result == null) result = caseIExtensibleElement(triggerType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.TRIGGER_TYPE_TYPE: {
				TriggerTypeType triggerTypeType = (TriggerTypeType)theEObject;
				T result = caseTriggerTypeType(triggerTypeType);
				if (result == null) result = caseIMetaType(triggerTypeType);
				if (result == null) result = caseIIdentifiableModelElement(triggerTypeType);
				if (result == null) result = caseIModelElement(triggerTypeType);
				if (result == null) result = caseIIdentifiableElement(triggerTypeType);
				if (result == null) result = caseIExtensibleElement(triggerTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.UNBIND_ACTION_TYPE: {
				UnbindActionType unbindActionType = (UnbindActionType)theEObject;
				T result = caseUnbindActionType(unbindActionType);
				if (result == null) result = caseAbstractEventAction(unbindActionType);
				if (result == null) result = caseIIdentifiableModelElement(unbindActionType);
				if (result == null) result = caseITypedElement(unbindActionType);
				if (result == null) result = caseIModelElement(unbindActionType);
				if (result == null) result = caseIIdentifiableElement(unbindActionType);
				if (result == null) result = caseIExtensibleElement(unbindActionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.VIEWABLE_TYPE: {
				ViewableType viewableType = (ViewableType)theEObject;
				T result = caseViewableType(viewableType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.VIEW_TYPE: {
				ViewType viewType = (ViewType)theEObject;
				T result = caseViewType(viewType);
				if (result == null) result = caseIModelElement(viewType);
				if (result == null) result = caseIExtensibleElement(viewType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.WORKS_FOR_CONNECTION_TYPE: {
				WorksForConnectionType worksForConnectionType = (WorksForConnectionType)theEObject;
				T result = caseWorksForConnectionType(worksForConnectionType);
				if (result == null) result = caseIConnectionSymbol(worksForConnectionType);
				if (result == null) result = caseIGraphicalObject(worksForConnectionType);
				if (result == null) result = caseIModelElement(worksForConnectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CarnotPackage.XML_TEXT_NODE: {
				XmlTextNode xmlTextNode = (XmlTextNode)theEObject;
				T result = caseXmlTextNode(xmlTextNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Coordinates</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Coordinates</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCoordinates(Coordinates object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IIdentifiable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IIdentifiable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIIdentifiableElement(IIdentifiableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IExtensible Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IExtensible Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIExtensibleElement(IExtensibleElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Identifiable Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identifiable Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdentifiableReference(IdentifiableReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIModelElement(IModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IIdentifiable Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IIdentifiable Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIIdentifiableModelElement(IIdentifiableModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEvent Handler Owner</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEvent Handler Owner</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEventHandlerOwner(IEventHandlerOwner object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IAccess Point Owner</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IAccess Point Owner</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIAccessPointOwner(IAccessPointOwner object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IMeta Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IMeta Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIMetaType(IMetaType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ITyped Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ITyped Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseITypedElement(ITypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ISymbol Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ISymbol Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseISymbolContainer(ISymbolContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IGraphical Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IGraphical Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIGraphicalObject(IGraphicalObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>INode Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>INode Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseINodeSymbol(INodeSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ISwimlane Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ISwimlane Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseISwimlaneSymbol(ISwimlaneSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IModel Element Node Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IModel Element Node Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIModelElementNodeSymbol(IModelElementNodeSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IFlow Object Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IFlow Object Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIFlowObjectSymbol(IFlowObjectSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConnection Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConnection Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIConnectionSymbol(IConnectionSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IModel Participant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IModel Participant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIModelParticipant(IModelParticipant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IModel Participant Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IModel Participant Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIModelParticipantSymbol(IModelParticipantSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Event Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Event Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractEventAction(AbstractEventAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Event Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Event Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractEventSymbol(AbstractEventSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Access Point Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Access Point Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccessPointType(AccessPointType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivitySymbolType(ActivitySymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityType(ActivityType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotationSymbolType(AnnotationSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Context Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Context Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationContextTypeType(ApplicationContextTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationSymbolType(ApplicationSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationType(ApplicationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationTypeType(ApplicationTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeType(AttributeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bind Action Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bind Action Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindActionType(BindActionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Code</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Code</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCode(Code object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conditional Performer Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conditional Performer Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionalPerformerSymbolType(ConditionalPerformerSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conditional Performer Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conditional Performer Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionalPerformerType(ConditionalPerformerType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Context Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Context Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContextType(ContextType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Mapping Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Mapping Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataMappingConnectionType(DataMappingConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Mapping Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Mapping Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataMappingType(DataMappingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Path Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Path Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataPathType(DataPathType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataSymbolType(DataSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataType(DataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataTypeType(DataTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Description Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Description Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDescriptionType(DescriptionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Diagram Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Diagram Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiagramType(DiagramType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>End Event Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>End Event Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEndEventSymbol(EndEventSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Action Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Action Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventActionType(EventActionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Action Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Action Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventActionTypeType(EventActionTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Condition Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Condition Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventConditionTypeType(EventConditionTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Handler Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Handler Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventHandlerType(EventHandlerType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Executed By Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Executed By Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutedByConnectionType(ExecutedByConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Id Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Id Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdRef(IdRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gateway Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gateway Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGatewaySymbol(GatewaySymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Link Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Link Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericLinkConnectionType(GenericLinkConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Group Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroupSymbolType(GroupSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intermediate Event Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intermediate Event Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntermediateEventSymbol(IntermediateEventSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lane Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lane Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLaneSymbol(LaneSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkTypeType(LinkTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modeler Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modeler Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelerSymbolType(ModelerSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modeler Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modeler Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelerType(ModelerType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelType(ModelType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organization Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organization Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrganizationSymbolType(OrganizationSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organization Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organization Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrganizationType(OrganizationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Mapping Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Mapping Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterMappingType(ParameterMappingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Participant Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Participant Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParticipantType(ParticipantType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Part Of Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Part Of Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePartOfConnectionType(PartOfConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Performs Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Performs Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePerformsConnectionType(PerformsConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pool Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pool Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePoolSymbol(PoolSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Definition Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Definition Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessDefinitionType(ProcessDefinitionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessSymbolType(ProcessSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Public Interface Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Public Interface Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePublicInterfaceSymbol(PublicInterfaceSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quality Control Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quality Control Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQualityControlType(QualityControlType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Refers To Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Refers To Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRefersToConnectionType(RefersToConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleSymbolType(RoleSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleType(RoleType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Start Event Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Start Event Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStartEventSymbol(StartEventSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sub Process Of Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sub Process Of Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubProcessOfConnectionType(SubProcessOfConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Team Lead Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Team Lead Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTeamLeadConnectionType(TeamLeadConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text Symbol Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTextSymbolType(TextSymbolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTextType(TextType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionConnectionType(TransitionConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionType(TransitionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Triggers Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Triggers Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTriggersConnectionType(TriggersConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trigger Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTriggerType(TriggerType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trigger Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTriggerTypeType(TriggerTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unbind Action Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unbind Action Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnbindActionType(UnbindActionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Viewable Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Viewable Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseViewableType(ViewableType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>View Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>View Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseViewType(ViewType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Works For Connection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Works For Connection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorksForConnectionType(WorksForConnectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Xml Text Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Xml Text Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXmlTextNode(XmlTextNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //CarnotSwitch
