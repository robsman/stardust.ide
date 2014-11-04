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
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Data Object Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataObjectTypeImpl#getStardustAttributes <em>Stardust Attributes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataObjectTypeImpl#getPredefined <em>Predefined</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataObjectTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustDataObjectTypeImpl extends MinimalEObjectImpl.Container implements StardustDataObjectType {
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
	 * The default value of the '{@link #getPredefined() <em>Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredefined()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDEFINED_EDEFAULT = "false";

	/**
	 * The cached value of the '{@link #getPredefined() <em>Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredefined()
	 * @generated
	 * @ordered
	 */
	protected String predefined = PREDEFINED_EDEFAULT;

	/**
	 * This is true if the Predefined attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean predefinedESet;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StardustDataObjectTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.STARDUST_DATA_OBJECT_TYPE;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES, oldStardustAttributes, newStardustAttributes);
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
				msgs = ((InternalEObject)stardustAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES, null, msgs);
			if (newStardustAttributes != null)
				msgs = ((InternalEObject)newStardustAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES, null, msgs);
			msgs = basicSetStardustAttributes(newStardustAttributes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES, newStardustAttributes, newStardustAttributes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredefined() {
		return predefined;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredefined(String newPredefined) {
		String oldPredefined = predefined;
		predefined = newPredefined;
		boolean oldPredefinedESet = predefinedESet;
		predefinedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__PREDEFINED, oldPredefined, predefined, !oldPredefinedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPredefined() {
		String oldPredefined = predefined;
		boolean oldPredefinedESet = predefinedESet;
		predefined = PREDEFINED_EDEFAULT;
		predefinedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__PREDEFINED, oldPredefined, PREDEFINED_EDEFAULT, oldPredefinedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPredefined() {
		return predefinedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES:
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
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES:
				return getStardustAttributes();
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__PREDEFINED:
				return getPredefined();
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__TYPE:
				return getType();
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
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES:
				setStardustAttributes((StardustAttributesType)newValue);
				return;
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__PREDEFINED:
				setPredefined((String)newValue);
				return;
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__TYPE:
				setType((String)newValue);
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
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES:
				setStardustAttributes((StardustAttributesType)null);
				return;
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__PREDEFINED:
				unsetPredefined();
				return;
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__TYPE:
				setType(TYPE_EDEFAULT);
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
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES:
				return stardustAttributes != null;
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__PREDEFINED:
				return isSetPredefined();
			case SdbpmnPackage.STARDUST_DATA_OBJECT_TYPE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
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
		result.append(" (predefined: ");
		if (predefinedESet) result.append(predefined); else result.append("<unset>");
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //StardustDataObjectTypeImpl
