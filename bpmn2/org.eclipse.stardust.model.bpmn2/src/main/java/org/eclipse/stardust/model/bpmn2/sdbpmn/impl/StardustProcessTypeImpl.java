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
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Process Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustProcessTypeImpl#getStardustAttributes <em>Stardust Attributes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustProcessTypeImpl#isSupportsProcessAttachments <em>Supports Process Attachments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustProcessTypeImpl extends MinimalEObjectImpl.Container implements StardustProcessType {
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
	 * The default value of the '{@link #isSupportsProcessAttachments() <em>Supports Process Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsProcessAttachments()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPORTS_PROCESS_ATTACHMENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSupportsProcessAttachments() <em>Supports Process Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsProcessAttachments()
	 * @generated
	 * @ordered
	 */
	protected boolean supportsProcessAttachments = SUPPORTS_PROCESS_ATTACHMENTS_EDEFAULT;

	/**
	 * This is true if the Supports Process Attachments attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean supportsProcessAttachmentsESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StardustProcessTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.STARDUST_PROCESS_TYPE;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_PROCESS_TYPE__STARDUST_ATTRIBUTES, oldStardustAttributes, newStardustAttributes);
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
				msgs = ((InternalEObject)stardustAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_PROCESS_TYPE__STARDUST_ATTRIBUTES, null, msgs);
			if (newStardustAttributes != null)
				msgs = ((InternalEObject)newStardustAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_PROCESS_TYPE__STARDUST_ATTRIBUTES, null, msgs);
			msgs = basicSetStardustAttributes(newStardustAttributes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_PROCESS_TYPE__STARDUST_ATTRIBUTES, newStardustAttributes, newStardustAttributes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSupportsProcessAttachments() {
		return supportsProcessAttachments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupportsProcessAttachments(boolean newSupportsProcessAttachments) {
		boolean oldSupportsProcessAttachments = supportsProcessAttachments;
		supportsProcessAttachments = newSupportsProcessAttachments;
		boolean oldSupportsProcessAttachmentsESet = supportsProcessAttachmentsESet;
		supportsProcessAttachmentsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_PROCESS_TYPE__SUPPORTS_PROCESS_ATTACHMENTS, oldSupportsProcessAttachments, supportsProcessAttachments, !oldSupportsProcessAttachmentsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSupportsProcessAttachments() {
		boolean oldSupportsProcessAttachments = supportsProcessAttachments;
		boolean oldSupportsProcessAttachmentsESet = supportsProcessAttachmentsESet;
		supportsProcessAttachments = SUPPORTS_PROCESS_ATTACHMENTS_EDEFAULT;
		supportsProcessAttachmentsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SdbpmnPackage.STARDUST_PROCESS_TYPE__SUPPORTS_PROCESS_ATTACHMENTS, oldSupportsProcessAttachments, SUPPORTS_PROCESS_ATTACHMENTS_EDEFAULT, oldSupportsProcessAttachmentsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSupportsProcessAttachments() {
		return supportsProcessAttachmentsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_PROCESS_TYPE__STARDUST_ATTRIBUTES:
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
			case SdbpmnPackage.STARDUST_PROCESS_TYPE__STARDUST_ATTRIBUTES:
				return getStardustAttributes();
			case SdbpmnPackage.STARDUST_PROCESS_TYPE__SUPPORTS_PROCESS_ATTACHMENTS:
				return isSupportsProcessAttachments();
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
			case SdbpmnPackage.STARDUST_PROCESS_TYPE__STARDUST_ATTRIBUTES:
				setStardustAttributes((StardustAttributesType)newValue);
				return;
			case SdbpmnPackage.STARDUST_PROCESS_TYPE__SUPPORTS_PROCESS_ATTACHMENTS:
				setSupportsProcessAttachments((Boolean)newValue);
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
			case SdbpmnPackage.STARDUST_PROCESS_TYPE__STARDUST_ATTRIBUTES:
				setStardustAttributes((StardustAttributesType)null);
				return;
			case SdbpmnPackage.STARDUST_PROCESS_TYPE__SUPPORTS_PROCESS_ATTACHMENTS:
				unsetSupportsProcessAttachments();
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
			case SdbpmnPackage.STARDUST_PROCESS_TYPE__STARDUST_ATTRIBUTES:
				return stardustAttributes != null;
			case SdbpmnPackage.STARDUST_PROCESS_TYPE__SUPPORTS_PROCESS_ATTACHMENTS:
				return isSetSupportsProcessAttachments();
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
		result.append(" (supportsProcessAttachments: ");
		if (supportsProcessAttachmentsESet) result.append(supportsProcessAttachments); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //StardustProcessTypeImpl
