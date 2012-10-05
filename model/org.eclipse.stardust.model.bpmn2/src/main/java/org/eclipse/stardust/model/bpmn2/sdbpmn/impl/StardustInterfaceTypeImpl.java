/**
 * ****************************************************************************
 *  Copyright (c) 2012 ITpearls AG and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     ITpearls - initial API and implementation and/or initial documentation
 * *****************************************************************************
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Interface Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl#getStardustApplication <em>Stardust Application</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl#getApplicationType <em>Application Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustInterfaceTypeImpl extends EObjectImpl implements StardustInterfaceType {
	/**
	 * The cached value of the '{@link #getStardustApplication() <em>Stardust Application</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStardustApplication()
	 * @generated
	 * @ordered
	 */
	protected StardustApplicationType stardustApplication;

	/**
	 * The default value of the '{@link #getApplicationType() <em>Application Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationType()
	 * @generated
	 * @ordered
	 */
	protected static final String APPLICATION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getApplicationType() <em>Application Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationType()
	 * @generated
	 * @ordered
	 */
	protected String applicationType = APPLICATION_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StardustInterfaceTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.STARDUST_INTERFACE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustApplicationType getStardustApplication() {
		return stardustApplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustApplication(StardustApplicationType newStardustApplication, NotificationChain msgs) {
		StardustApplicationType oldStardustApplication = stardustApplication;
		stardustApplication = newStardustApplication;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION, oldStardustApplication, newStardustApplication);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustApplication(StardustApplicationType newStardustApplication) {
		if (newStardustApplication != stardustApplication) {
			NotificationChain msgs = null;
			if (stardustApplication != null)
				msgs = ((InternalEObject)stardustApplication).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION, null, msgs);
			if (newStardustApplication != null)
				msgs = ((InternalEObject)newStardustApplication).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION, null, msgs);
			msgs = basicSetStardustApplication(newStardustApplication, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION, newStardustApplication, newStardustApplication));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getApplicationType() {
		return applicationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setApplicationType(String newApplicationType) {
		String oldApplicationType = applicationType;
		applicationType = newApplicationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_INTERFACE_TYPE__APPLICATION_TYPE, oldApplicationType, applicationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION:
				return basicSetStardustApplication(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION:
				return getStardustApplication();
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__APPLICATION_TYPE:
				return getApplicationType();
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
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION:
				setStardustApplication((StardustApplicationType)newValue);
				return;
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__APPLICATION_TYPE:
				setApplicationType((String)newValue);
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
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION:
				setStardustApplication((StardustApplicationType)null);
				return;
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__APPLICATION_TYPE:
				setApplicationType(APPLICATION_TYPE_EDEFAULT);
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
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION:
				return stardustApplication != null;
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__APPLICATION_TYPE:
				return APPLICATION_TYPE_EDEFAULT == null ? applicationType != null : !APPLICATION_TYPE_EDEFAULT.equals(applicationType);
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
		result.append(" (applicationType: ");
		result.append(applicationType);
		result.append(')');
		return result.toString();
	}

} //StardustInterfaceTypeImpl
