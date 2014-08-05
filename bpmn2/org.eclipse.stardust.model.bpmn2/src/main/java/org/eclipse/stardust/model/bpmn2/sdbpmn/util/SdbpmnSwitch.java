/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.stardust.model.bpmn2.sdbpmn.*;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;

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
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage
 * @generated
 */
public class SdbpmnSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SdbpmnPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SdbpmnSwitch() {
		if (modelPackage == null) {
			modelPackage = SdbpmnPackage.eINSTANCE;
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
			case SdbpmnPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_ACCESS_POINT_TYPE: {
				StardustAccessPointType stardustAccessPointType = (StardustAccessPointType)theEObject;
				T result = caseStardustAccessPointType(stardustAccessPointType);
				if (result == null) result = caseAccessPointType(stardustAccessPointType);
				if (result == null) result = caseIIdentifiableModelElement(stardustAccessPointType);
				if (result == null) result = caseIModelElement(stardustAccessPointType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_APPLICATION_TYPE: {
				StardustApplicationType stardustApplicationType = (StardustApplicationType)theEObject;
				T result = caseStardustApplicationType(stardustApplicationType);
				if (result == null) result = caseApplicationType(stardustApplicationType);
				if (result == null) result = caseIIdentifiableModelElement(stardustApplicationType);
				if (result == null) result = caseIModelElement(stardustApplicationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_ATTRIBUTES_TYPE: {
				StardustAttributesType stardustAttributesType = (StardustAttributesType)theEObject;
				T result = caseStardustAttributesType(stardustAttributesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_CONTEXT_TYPE: {
				StardustContextType stardustContextType = (StardustContextType)theEObject;
				T result = caseStardustContextType(stardustContextType);
				if (result == null) result = caseContextType(stardustContextType);
				if (result == null) result = caseIModelElement(stardustContextType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE: {
				StardustInterfaceType stardustInterfaceType = (StardustInterfaceType)theEObject;
				T result = caseStardustInterfaceType(stardustInterfaceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE: {
				StardustMessageStartEventType stardustMessageStartEventType = (StardustMessageStartEventType)theEObject;
				T result = caseStardustMessageStartEventType(stardustMessageStartEventType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_MODEL_TYPE: {
				StardustModelType stardustModelType = (StardustModelType)theEObject;
				T result = caseStardustModelType(stardustModelType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE: {
				StardustResourceType stardustResourceType = (StardustResourceType)theEObject;
				T result = caseStardustResourceType(stardustResourceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_SEQENCE_FLOW_TYPE: {
				StardustSeqenceFlowType stardustSeqenceFlowType = (StardustSeqenceFlowType)theEObject;
				T result = caseStardustSeqenceFlowType(stardustSeqenceFlowType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_SERVICE_TASK_TYPE: {
				StardustServiceTaskType stardustServiceTaskType = (StardustServiceTaskType)theEObject;
				T result = caseStardustServiceTaskType(stardustServiceTaskType);
				if (result == null) result = caseTStardustActivity(stardustServiceTaskType);
				if (result == null) result = caseTStardustCommon(stardustServiceTaskType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_START_EVENT_TYPE: {
				StardustStartEventType stardustStartEventType = (StardustStartEventType)theEObject;
				T result = caseStardustStartEventType(stardustStartEventType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_SUBPROCESS_TYPE: {
				StardustSubprocessType stardustSubprocessType = (StardustSubprocessType)theEObject;
				T result = caseStardustSubprocessType(stardustSubprocessType);
				if (result == null) result = caseTStardustActivity(stardustSubprocessType);
				if (result == null) result = caseTStardustCommon(stardustSubprocessType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_TIMER_START_EVENT_TYPE: {
				StardustTimerStartEventType stardustTimerStartEventType = (StardustTimerStartEventType)theEObject;
				T result = caseStardustTimerStartEventType(stardustTimerStartEventType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE: {
				StardustTriggerType stardustTriggerType = (StardustTriggerType)theEObject;
				T result = caseStardustTriggerType(stardustTriggerType);
				if (result == null) result = caseTriggerType(stardustTriggerType);
				if (result == null) result = caseIIdentifiableModelElement(stardustTriggerType);
				if (result == null) result = caseIModelElement(stardustTriggerType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.STARDUST_USER_TASK_TYPE: {
				StardustUserTaskType stardustUserTaskType = (StardustUserTaskType)theEObject;
				T result = caseStardustUserTaskType(stardustUserTaskType);
				if (result == null) result = caseTStardustActivity(stardustUserTaskType);
				if (result == null) result = caseTStardustCommon(stardustUserTaskType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.TSTARDUST_ACTIVITY: {
				TStardustActivity tStardustActivity = (TStardustActivity)theEObject;
				T result = caseTStardustActivity(tStardustActivity);
				if (result == null) result = caseTStardustCommon(tStardustActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SdbpmnPackage.TSTARDUST_COMMON: {
				TStardustCommon tStardustCommon = (TStardustCommon)theEObject;
				T result = caseTStardustCommon(tStardustCommon);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Access Point Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Access Point Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustAccessPointType(StardustAccessPointType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Application Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Application Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustApplicationType(StardustApplicationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Attributes Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Attributes Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustAttributesType(StardustAttributesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Context Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Context Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustContextType(StardustContextType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Interface Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Interface Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustInterfaceType(StardustInterfaceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Message Start Event Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Message Start Event Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustMessageStartEventType(StardustMessageStartEventType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Model Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Model Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustModelType(StardustModelType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Resource Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Resource Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustResourceType(StardustResourceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Seqence Flow Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Seqence Flow Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustSeqenceFlowType(StardustSeqenceFlowType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Service Task Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Service Task Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustServiceTaskType(StardustServiceTaskType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Start Event Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Start Event Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustStartEventType(StardustStartEventType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Subprocess Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Subprocess Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustSubprocessType(StardustSubprocessType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Timer Start Event Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Timer Start Event Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustTimerStartEventType(StardustTimerStartEventType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust Trigger Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust Trigger Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustTriggerType(StardustTriggerType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stardust User Task Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stardust User Task Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStardustUserTaskType(StardustUserTaskType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TStardust Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TStardust Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTStardustActivity(TStardustActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TStardust Common</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TStardust Common</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTStardustCommon(TStardustCommon object) {
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

} //SdbpmnSwitch
