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
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType;

import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Resource Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustResourceTypeImpl#getStardustConditionalPerformer <em>Stardust Conditional Performer</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustResourceTypeImpl#getStardustRole <em>Stardust Role</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustResourceTypeImpl#getStardustOrganization <em>Stardust Organization</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustResourceTypeImpl#getDataId <em>Data Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustResourceTypeImpl extends EObjectImpl implements StardustResourceType {
	/**
	 * The cached value of the '{@link #getStardustConditionalPerformer() <em>Stardust Conditional Performer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStardustConditionalPerformer()
	 * @generated
	 * @ordered
	 */
	protected ConditionalPerformerType stardustConditionalPerformer;

	/**
	 * The cached value of the '{@link #getStardustRole() <em>Stardust Role</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStardustRole()
	 * @generated
	 * @ordered
	 */
	protected RoleType stardustRole;

	/**
	 * The cached value of the '{@link #getStardustOrganization() <em>Stardust Organization</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStardustOrganization()
	 * @generated
	 * @ordered
	 */
	protected OrganizationType stardustOrganization;

	/**
	 * The default value of the '{@link #getDataId() <em>Data Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataId()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataId() <em>Data Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataId()
	 * @generated
	 * @ordered
	 */
	protected String dataId = DATA_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StardustResourceTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.STARDUST_RESOURCE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalPerformerType getStardustConditionalPerformer() {
		return stardustConditionalPerformer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustConditionalPerformer(ConditionalPerformerType newStardustConditionalPerformer, NotificationChain msgs) {
		ConditionalPerformerType oldStardustConditionalPerformer = stardustConditionalPerformer;
		stardustConditionalPerformer = newStardustConditionalPerformer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER, oldStardustConditionalPerformer, newStardustConditionalPerformer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustConditionalPerformer(ConditionalPerformerType newStardustConditionalPerformer) {
		if (newStardustConditionalPerformer != stardustConditionalPerformer) {
			NotificationChain msgs = null;
			if (stardustConditionalPerformer != null)
				msgs = ((InternalEObject)stardustConditionalPerformer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER, null, msgs);
			if (newStardustConditionalPerformer != null)
				msgs = ((InternalEObject)newStardustConditionalPerformer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER, null, msgs);
			msgs = basicSetStardustConditionalPerformer(newStardustConditionalPerformer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER, newStardustConditionalPerformer, newStardustConditionalPerformer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleType getStardustRole() {
		return stardustRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustRole(RoleType newStardustRole, NotificationChain msgs) {
		RoleType oldStardustRole = stardustRole;
		stardustRole = newStardustRole;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ROLE, oldStardustRole, newStardustRole);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustRole(RoleType newStardustRole) {
		if (newStardustRole != stardustRole) {
			NotificationChain msgs = null;
			if (stardustRole != null)
				msgs = ((InternalEObject)stardustRole).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ROLE, null, msgs);
			if (newStardustRole != null)
				msgs = ((InternalEObject)newStardustRole).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ROLE, null, msgs);
			msgs = basicSetStardustRole(newStardustRole, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ROLE, newStardustRole, newStardustRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrganizationType getStardustOrganization() {
		return stardustOrganization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustOrganization(OrganizationType newStardustOrganization, NotificationChain msgs) {
		OrganizationType oldStardustOrganization = stardustOrganization;
		stardustOrganization = newStardustOrganization;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION, oldStardustOrganization, newStardustOrganization);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustOrganization(OrganizationType newStardustOrganization) {
		if (newStardustOrganization != stardustOrganization) {
			NotificationChain msgs = null;
			if (stardustOrganization != null)
				msgs = ((InternalEObject)stardustOrganization).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION, null, msgs);
			if (newStardustOrganization != null)
				msgs = ((InternalEObject)newStardustOrganization).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION, null, msgs);
			msgs = basicSetStardustOrganization(newStardustOrganization, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION, newStardustOrganization, newStardustOrganization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataId() {
		return dataId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataId(String newDataId) {
		String oldDataId = dataId;
		dataId = newDataId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_RESOURCE_TYPE__DATA_ID, oldDataId, dataId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER:
				return basicSetStardustConditionalPerformer(null, msgs);
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ROLE:
				return basicSetStardustRole(null, msgs);
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION:
				return basicSetStardustOrganization(null, msgs);
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
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER:
				return getStardustConditionalPerformer();
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ROLE:
				return getStardustRole();
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION:
				return getStardustOrganization();
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__DATA_ID:
				return getDataId();
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
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER:
				setStardustConditionalPerformer((ConditionalPerformerType)newValue);
				return;
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ROLE:
				setStardustRole((RoleType)newValue);
				return;
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION:
				setStardustOrganization((OrganizationType)newValue);
				return;
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__DATA_ID:
				setDataId((String)newValue);
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
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER:
				setStardustConditionalPerformer((ConditionalPerformerType)null);
				return;
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ROLE:
				setStardustRole((RoleType)null);
				return;
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION:
				setStardustOrganization((OrganizationType)null);
				return;
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__DATA_ID:
				setDataId(DATA_ID_EDEFAULT);
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
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER:
				return stardustConditionalPerformer != null;
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ROLE:
				return stardustRole != null;
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION:
				return stardustOrganization != null;
			case SdbpmnPackage.STARDUST_RESOURCE_TYPE__DATA_ID:
				return DATA_ID_EDEFAULT == null ? dataId != null : !DATA_ID_EDEFAULT.equals(dataId);
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
		result.append(" (dataId: ");
		result.append(dataId);
		result.append(')');
		return result.toString();
	}

} //StardustResourceTypeImpl
