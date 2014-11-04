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
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Start Event Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustStartEventTypeImpl#getStardustAttributes <em>Stardust Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustStartEventTypeImpl extends MinimalEObjectImpl.Container implements StardustStartEventType {
	/**
	 * The cached value of the '{@link #getStardustAttributes() <em>Stardust Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStardustAttributes()
	 * @generated
	 * @ordered
	 */
	protected StardustAttributesType stardustAttributes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StardustStartEventTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.STARDUST_START_EVENT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustAttributesType getStardustAttributes() {
		return stardustAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustAttributes(StardustAttributesType newStardustAttributes, NotificationChain msgs) {
		StardustAttributesType oldStardustAttributes = stardustAttributes;
		stardustAttributes = newStardustAttributes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES, oldStardustAttributes, newStardustAttributes);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustAttributes(StardustAttributesType newStardustAttributes) {
		if (newStardustAttributes != stardustAttributes) {
			NotificationChain msgs = null;
			if (stardustAttributes != null)
				msgs = ((InternalEObject)stardustAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES, null, msgs);
			if (newStardustAttributes != null)
				msgs = ((InternalEObject)newStardustAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES, null, msgs);
			msgs = basicSetStardustAttributes(newStardustAttributes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES, newStardustAttributes, newStardustAttributes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
				return basicSetStardustAttributes(null, msgs);
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
			case SdbpmnPackage.STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
				return getStardustAttributes();
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
			case SdbpmnPackage.STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
				setStardustAttributes((StardustAttributesType)newValue);
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
			case SdbpmnPackage.STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
				setStardustAttributes((StardustAttributesType)null);
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
			case SdbpmnPackage.STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
				return stardustAttributes != null;
		}
		return super.eIsSet(featureID);
	}

} //StardustStartEventTypeImpl
