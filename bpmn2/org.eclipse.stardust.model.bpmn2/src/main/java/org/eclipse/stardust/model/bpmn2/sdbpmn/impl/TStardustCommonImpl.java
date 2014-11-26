/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TStardust Common</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustCommonImpl#getElementOid <em>Element Oid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TStardustCommonImpl extends MinimalEObjectImpl.Container implements TStardustCommon {
	/**
	 * The default value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementOid()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_OID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementOid()
	 * @generated
	 * @ordered
	 */
	protected String elementOid = ELEMENT_OID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TStardustCommonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.TSTARDUST_COMMON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElementOid() {
		return elementOid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementOid(String newElementOid) {
		String oldElementOid = elementOid;
		elementOid = newElementOid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.TSTARDUST_COMMON__ELEMENT_OID, oldElementOid, elementOid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SdbpmnPackage.TSTARDUST_COMMON__ELEMENT_OID:
				return getElementOid();
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
			case SdbpmnPackage.TSTARDUST_COMMON__ELEMENT_OID:
				setElementOid((String)newValue);
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
			case SdbpmnPackage.TSTARDUST_COMMON__ELEMENT_OID:
				setElementOid(ELEMENT_OID_EDEFAULT);
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
			case SdbpmnPackage.TSTARDUST_COMMON__ELEMENT_OID:
				return ELEMENT_OID_EDEFAULT == null ? elementOid != null : !ELEMENT_OID_EDEFAULT.equals(elementOid);
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
		result.append(" (elementOid: ");
		result.append(elementOid);
		result.append(')');
		return result.toString();
	}

} //TStardustCommonImpl
