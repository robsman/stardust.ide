/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.stardust.model.bpmn2.sdbpmn.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SdbpmnFactoryImpl extends EFactoryImpl implements SdbpmnFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SdbpmnFactory init() {
		try {
			SdbpmnFactory theSdbpmnFactory = (SdbpmnFactory)EPackage.Registry.INSTANCE.getEFactory(SdbpmnPackage.eNS_URI);
			if (theSdbpmnFactory != null) {
				return theSdbpmnFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SdbpmnFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SdbpmnFactoryImpl() {
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
			case SdbpmnPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case SdbpmnPackage.STARDUST_ACCESS_POINT_TYPE: return createStardustAccessPointType();
			case SdbpmnPackage.STARDUST_APPLICATION_TYPE: return createStardustApplicationType();
			case SdbpmnPackage.STARDUST_ATTRIBUTES_TYPE: return createStardustAttributesType();
			case SdbpmnPackage.STARDUST_CONTEXT_TYPE: return createStardustContextType();
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE: return createStardustDataObjectType();
			case SdbpmnPackage.STARDUST_DATA_STORE_TYPE: return createStardustDataStoreType();
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE: return createStardustInterfaceType();
			case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE: return createStardustMessageStartEventType();
			case SdbpmnPackage.STARDUST_MODEL_TYPE: return createStardustModelType();
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE: return createStardustResourceType();
			case SdbpmnPackage.STARDUST_SEQENCE_FLOW_TYPE: return createStardustSeqenceFlowType();
			case SdbpmnPackage.STARDUST_SERVICE_TASK_TYPE: return createStardustServiceTaskType();
			case SdbpmnPackage.STARDUST_START_EVENT_TYPE: return createStardustStartEventType();
			case SdbpmnPackage.STARDUST_SUBPROCESS_TYPE: return createStardustSubprocessType();
			case SdbpmnPackage.STARDUST_TIMER_START_EVENT_TYPE: return createStardustTimerStartEventType();
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE: return createStardustTriggerType();
			case SdbpmnPackage.STARDUST_USER_TASK_TYPE: return createStardustUserTaskType();
			case SdbpmnPackage.TSTARDUST_ACTIVITY: return createTStardustActivity();
			case SdbpmnPackage.TSTARDUST_COMMON: return createTStardustCommon();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
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
	public StardustAccessPointType createStardustAccessPointType() {
		StardustAccessPointTypeImpl stardustAccessPointType = new StardustAccessPointTypeImpl();
		return stardustAccessPointType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustApplicationType createStardustApplicationType() {
		StardustApplicationTypeImpl stardustApplicationType = new StardustApplicationTypeImpl();
		return stardustApplicationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustAttributesType createStardustAttributesType() {
		StardustAttributesTypeImpl stardustAttributesType = new StardustAttributesTypeImpl();
		return stardustAttributesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustContextType createStardustContextType() {
		StardustContextTypeImpl stardustContextType = new StardustContextTypeImpl();
		return stardustContextType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustDataObjectType createStardustDataObjectType() {
		StardustDataObjectTypeImpl stardustDataObjectType = new StardustDataObjectTypeImpl();
		return stardustDataObjectType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustDataStoreType createStardustDataStoreType() {
		StardustDataStoreTypeImpl stardustDataStoreType = new StardustDataStoreTypeImpl();
		return stardustDataStoreType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustInterfaceType createStardustInterfaceType() {
		StardustInterfaceTypeImpl stardustInterfaceType = new StardustInterfaceTypeImpl();
		return stardustInterfaceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustMessageStartEventType createStardustMessageStartEventType() {
		StardustMessageStartEventTypeImpl stardustMessageStartEventType = new StardustMessageStartEventTypeImpl();
		return stardustMessageStartEventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustModelType createStardustModelType() {
		StardustModelTypeImpl stardustModelType = new StardustModelTypeImpl();
		return stardustModelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustResourceType createStardustResourceType() {
		StardustResourceTypeImpl stardustResourceType = new StardustResourceTypeImpl();
		return stardustResourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustSeqenceFlowType createStardustSeqenceFlowType() {
		StardustSeqenceFlowTypeImpl stardustSeqenceFlowType = new StardustSeqenceFlowTypeImpl();
		return stardustSeqenceFlowType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustServiceTaskType createStardustServiceTaskType() {
		StardustServiceTaskTypeImpl stardustServiceTaskType = new StardustServiceTaskTypeImpl();
		return stardustServiceTaskType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustStartEventType createStardustStartEventType() {
		StardustStartEventTypeImpl stardustStartEventType = new StardustStartEventTypeImpl();
		return stardustStartEventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustSubprocessType createStardustSubprocessType() {
		StardustSubprocessTypeImpl stardustSubprocessType = new StardustSubprocessTypeImpl();
		return stardustSubprocessType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustTimerStartEventType createStardustTimerStartEventType() {
		StardustTimerStartEventTypeImpl stardustTimerStartEventType = new StardustTimerStartEventTypeImpl();
		return stardustTimerStartEventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustTriggerType createStardustTriggerType() {
		StardustTriggerTypeImpl stardustTriggerType = new StardustTriggerTypeImpl();
		return stardustTriggerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustUserTaskType createStardustUserTaskType() {
		StardustUserTaskTypeImpl stardustUserTaskType = new StardustUserTaskTypeImpl();
		return stardustUserTaskType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStardustActivity createTStardustActivity() {
		TStardustActivityImpl tStardustActivity = new TStardustActivityImpl();
		return tStardustActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStardustCommon createTStardustCommon() {
		TStardustCommonImpl tStardustCommon = new TStardustCommonImpl();
		return tStardustCommon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SdbpmnPackage getSdbpmnPackage() {
		return (SdbpmnPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SdbpmnPackage getPackage() {
		return SdbpmnPackage.eINSTANCE;
	}

} //SdbpmnFactoryImpl
