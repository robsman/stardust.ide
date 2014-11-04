/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Interface Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl#getStardustApplication <em>Stardust Application</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl#getStardustTrigger <em>Stardust Trigger</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl#getApplicationType <em>Application Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustInterfaceTypeImpl extends MinimalEObjectImpl.Container implements StardustInterfaceType {
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
	 * The cached value of the '{@link #getStardustTrigger() <em>Stardust Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStardustTrigger()
	 * @generated
	 * @ordered
	 */
	protected StardustTriggerType stardustTrigger;

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
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	public StardustTriggerType getStardustTrigger() {
		return stardustTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustTrigger(StardustTriggerType newStardustTrigger, NotificationChain msgs) {
		StardustTriggerType oldStardustTrigger = stardustTrigger;
		stardustTrigger = newStardustTrigger;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER, oldStardustTrigger, newStardustTrigger);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustTrigger(StardustTriggerType newStardustTrigger) {
		if (newStardustTrigger != stardustTrigger) {
			NotificationChain msgs = null;
			if (stardustTrigger != null)
				msgs = ((InternalEObject)stardustTrigger).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER, null, msgs);
			if (newStardustTrigger != null)
				msgs = ((InternalEObject)newStardustTrigger).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER, null, msgs);
			msgs = basicSetStardustTrigger(newStardustTrigger, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER, newStardustTrigger, newStardustTrigger));
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
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_INTERFACE_TYPE__ID, oldId, id));
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
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER:
				return basicSetStardustTrigger(null, msgs);
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
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER:
				return getStardustTrigger();
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__APPLICATION_TYPE:
				return getApplicationType();
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__ID:
				return getId();
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
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER:
				setStardustTrigger((StardustTriggerType)newValue);
				return;
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__APPLICATION_TYPE:
				setApplicationType((String)newValue);
				return;
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__ID:
				setId((String)newValue);
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
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER:
				setStardustTrigger((StardustTriggerType)null);
				return;
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__APPLICATION_TYPE:
				setApplicationType(APPLICATION_TYPE_EDEFAULT);
				return;
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__ID:
				setId(ID_EDEFAULT);
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
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER:
				return stardustTrigger != null;
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__APPLICATION_TYPE:
				return APPLICATION_TYPE_EDEFAULT == null ? applicationType != null : !APPLICATION_TYPE_EDEFAULT.equals(applicationType);
			case SdbpmnPackage.STARDUST_INTERFACE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //StardustInterfaceTypeImpl
