/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustSubprocessType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Subprocess Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.StardustSubprocessTypeImpl#getImplementationProcess <em>Implementation Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustSubprocessTypeImpl extends TStardustActivityImpl implements StardustSubprocessType {
	/**
	 * The default value of the '{@link #getImplementationProcess() <em>Implementation Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationProcess()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPLEMENTATION_PROCESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementationProcess() <em>Implementation Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationProcess()
	 * @generated
	 * @ordered
	 */
	protected String implementationProcess = IMPLEMENTATION_PROCESS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StardustSubprocessTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.eINSTANCE.getStardustSubprocessType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImplementationProcess() {
		return implementationProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementationProcess(String newImplementationProcess) {
		String oldImplementationProcess = implementationProcess;
		implementationProcess = newImplementationProcess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_SUBPROCESS_TYPE__IMPLEMENTATION_PROCESS, oldImplementationProcess, implementationProcess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_SUBPROCESS_TYPE__IMPLEMENTATION_PROCESS:
				return getImplementationProcess();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_SUBPROCESS_TYPE__IMPLEMENTATION_PROCESS:
				setImplementationProcess((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_SUBPROCESS_TYPE__IMPLEMENTATION_PROCESS:
				setImplementationProcess(IMPLEMENTATION_PROCESS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_SUBPROCESS_TYPE__IMPLEMENTATION_PROCESS:
				return IMPLEMENTATION_PROCESS_EDEFAULT == null ? implementationProcess != null : !IMPLEMENTATION_PROCESS_EDEFAULT.equals(implementationProcess);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (implementationProcess: ");
		result.append(implementationProcess);
		result.append(')');
		return result.toString();
	}

} //StardustSubprocessTypeImpl
