/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleSymbolTypeImpl#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleSymbolTypeImpl#getTriggeredEvents <em>Triggered Events</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleSymbolTypeImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleSymbolTypeImpl#getOrganizationMemberships <em>Organization Memberships</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleSymbolTypeImpl#getTeams <em>Teams</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleSymbolTypeImpl extends IModelElementNodeSymbolImpl implements RoleSymbolType {
	/**
	 * The cached value of the '{@link #getPerformedActivities() <em>Performed Activities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<PerformsConnectionType> performedActivities;

	/**
	 * The cached value of the '{@link #getTriggeredEvents() <em>Triggered Events</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggeredEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<TriggersConnectionType> triggeredEvents;

	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected RoleType role;

	/**
	 * The cached value of the '{@link #getOrganizationMemberships() <em>Organization Memberships</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganizationMemberships()
	 * @generated
	 * @ordered
	 */
	protected EList<WorksForConnectionType> organizationMemberships;

	/**
	 * The cached value of the '{@link #getTeams() <em>Teams</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeams()
	 * @generated
	 * @ordered
	 */
	protected EList<TeamLeadConnectionType> teams;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleSymbolTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getRoleSymbolType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PerformsConnectionType> getPerformedActivities() {
		if (performedActivities == null) {
			performedActivities = new EObjectWithInverseEList<PerformsConnectionType>(PerformsConnectionType.class, this, CarnotPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES, CarnotPackage.PERFORMS_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
		}
		return performedActivities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TriggersConnectionType> getTriggeredEvents() {
		if (triggeredEvents == null) {
			triggeredEvents = new EObjectWithInverseEList<TriggersConnectionType>(TriggersConnectionType.class, this, CarnotPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS, CarnotPackage.TRIGGERS_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
		}
		return triggeredEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleType getRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRole(RoleType newRole, NotificationChain msgs) {
		RoleType oldRole = role;
		role = newRole;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.ROLE_SYMBOL_TYPE__ROLE, oldRole, newRole);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRole(RoleType newRole) {
		if (newRole != role) {
			NotificationChain msgs = null;
			if (role != null)
				msgs = ((InternalEObject)role).eInverseRemove(this, CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS, RoleType.class, msgs);
			if (newRole != null)
				msgs = ((InternalEObject)newRole).eInverseAdd(this, CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS, RoleType.class, msgs);
			msgs = basicSetRole(newRole, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ROLE_SYMBOL_TYPE__ROLE, newRole, newRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WorksForConnectionType> getOrganizationMemberships() {
		if (organizationMemberships == null) {
			organizationMemberships = new EObjectWithInverseEList<WorksForConnectionType>(WorksForConnectionType.class, this, CarnotPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS, CarnotPackage.WORKS_FOR_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
		}
		return organizationMemberships;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TeamLeadConnectionType> getTeams() {
		if (teams == null) {
			teams = new EObjectWithInverseResolvingEList<TeamLeadConnectionType>(TeamLeadConnectionType.class, this, CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL);
		}
		return teams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedActivities()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTriggeredEvents()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ROLE_SYMBOL_TYPE__ROLE:
				if (role != null)
					msgs = ((InternalEObject)role).eInverseRemove(this, CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS, RoleType.class, msgs);
				return basicSetRole((RoleType)otherEnd, msgs);
			case CarnotPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOrganizationMemberships()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTeams()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<?>)getPerformedActivities()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
				return ((InternalEList<?>)getTriggeredEvents()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ROLE_SYMBOL_TYPE__ROLE:
				return basicSetRole(null, msgs);
			case CarnotPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
				return ((InternalEList<?>)getOrganizationMemberships()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS:
				return ((InternalEList<?>)getTeams()).basicRemove(otherEnd, msgs);
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
			case CarnotPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				return getPerformedActivities();
			case CarnotPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
				return getTriggeredEvents();
			case CarnotPackage.ROLE_SYMBOL_TYPE__ROLE:
				return getRole();
			case CarnotPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
				return getOrganizationMemberships();
			case CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS:
				return getTeams();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CarnotPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				getPerformedActivities().addAll((Collection<? extends PerformsConnectionType>)newValue);
				return;
			case CarnotPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
				getTriggeredEvents().clear();
				getTriggeredEvents().addAll((Collection<? extends TriggersConnectionType>)newValue);
				return;
			case CarnotPackage.ROLE_SYMBOL_TYPE__ROLE:
				setRole((RoleType)newValue);
				return;
			case CarnotPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
				getOrganizationMemberships().clear();
				getOrganizationMemberships().addAll((Collection<? extends WorksForConnectionType>)newValue);
				return;
			case CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS:
				getTeams().clear();
				getTeams().addAll((Collection<? extends TeamLeadConnectionType>)newValue);
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
			case CarnotPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				return;
			case CarnotPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
				getTriggeredEvents().clear();
				return;
			case CarnotPackage.ROLE_SYMBOL_TYPE__ROLE:
				setRole((RoleType)null);
				return;
			case CarnotPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
				getOrganizationMemberships().clear();
				return;
			case CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS:
				getTeams().clear();
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
			case CarnotPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				return performedActivities != null && !performedActivities.isEmpty();
			case CarnotPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
				return triggeredEvents != null && !triggeredEvents.isEmpty();
			case CarnotPackage.ROLE_SYMBOL_TYPE__ROLE:
				return role != null;
			case CarnotPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
				return organizationMemberships != null && !organizationMemberships.isEmpty();
			case CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS:
				return teams != null && !teams.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RoleSymbolTypeImpl
