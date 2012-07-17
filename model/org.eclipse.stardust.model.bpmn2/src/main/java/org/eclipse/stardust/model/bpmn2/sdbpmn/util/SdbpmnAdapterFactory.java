/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.model.bpmn2.sdbpmn.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage
 * @generated
 */
public class SdbpmnAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SdbpmnPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SdbpmnAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SdbpmnPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SdbpmnSwitch<Adapter> modelSwitch =
		new SdbpmnSwitch<Adapter>() {
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseStardustAttributesType(StardustAttributesType object) {
				return createStardustAttributesTypeAdapter();
			}
			@Override
			public Adapter caseStardustMessageStartEventType(StardustMessageStartEventType object) {
				return createStardustMessageStartEventTypeAdapter();
			}
			@Override
			public Adapter caseStardustModelType(StardustModelType object) {
				return createStardustModelTypeAdapter();
			}
			@Override
			public Adapter caseStardustSeqenceFlowType(StardustSeqenceFlowType object) {
				return createStardustSeqenceFlowTypeAdapter();
			}
			@Override
			public Adapter caseStardustServiceTaskType(StardustServiceTaskType object) {
				return createStardustServiceTaskTypeAdapter();
			}
			@Override
			public Adapter caseStardustStartEventType(StardustStartEventType object) {
				return createStardustStartEventTypeAdapter();
			}
			@Override
			public Adapter caseStardustSubprocessType(StardustSubprocessType object) {
				return createStardustSubprocessTypeAdapter();
			}
			@Override
			public Adapter caseStardustTimerStartEventType(StardustTimerStartEventType object) {
				return createStardustTimerStartEventTypeAdapter();
			}
			@Override
			public Adapter caseStardustUserTaskType(StardustUserTaskType object) {
				return createStardustUserTaskTypeAdapter();
			}
			@Override
			public Adapter caseTStardustActivity(TStardustActivity object) {
				return createTStardustActivityAdapter();
			}
			@Override
			public Adapter caseTStardustCommon(TStardustCommon object) {
				return createTStardustCommonAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType <em>Stardust Attributes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType
	 * @generated
	 */
	public Adapter createStardustAttributesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType <em>Stardust Message Start Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType
	 * @generated
	 */
	public Adapter createStardustMessageStartEventTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType <em>Stardust Model Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType
	 * @generated
	 */
	public Adapter createStardustModelTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType <em>Stardust Seqence Flow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType
	 * @generated
	 */
	public Adapter createStardustSeqenceFlowTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType <em>Stardust Service Task Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType
	 * @generated
	 */
	public Adapter createStardustServiceTaskTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType <em>Stardust Start Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType
	 * @generated
	 */
	public Adapter createStardustStartEventTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType <em>Stardust Subprocess Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType
	 * @generated
	 */
	public Adapter createStardustSubprocessTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType <em>Stardust Timer Start Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType
	 * @generated
	 */
	public Adapter createStardustTimerStartEventTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType <em>Stardust User Task Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType
	 * @generated
	 */
	public Adapter createStardustUserTaskTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity <em>TStardust Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity
	 * @generated
	 */
	public Adapter createTStardustActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon <em>TStardust Common</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon
	 * @generated
	 */
	public Adapter createTStardustCommonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SdbpmnAdapterFactory
