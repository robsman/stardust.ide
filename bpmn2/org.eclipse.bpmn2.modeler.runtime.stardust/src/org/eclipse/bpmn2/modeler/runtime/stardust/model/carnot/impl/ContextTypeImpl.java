/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AccessPointType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IAccessPointOwner;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ContextTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ContextTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ContextTypeImpl#getAccessPoint <em>Access Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ContextTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ContextTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContextTypeImpl extends MinimalEObjectImpl.Container implements ContextType {
	/**
	 * The default value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementOid()
	 * @generated
	 * @ordered
	 */
	protected static final long ELEMENT_OID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementOid()
	 * @generated
	 * @ordered
	 */
	protected long elementOid = ELEMENT_OID_EDEFAULT;

	/**
	 * This is true if the Element Oid attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean elementOidESet;

	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeType> attribute;

	/**
	 * The cached value of the '{@link #getAccessPoint() <em>Access Point</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessPoint()
	 * @generated
	 * @ordered
	 */
	protected EList<AccessPointType> accessPoint;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected DescriptionType description;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ApplicationContextTypeType type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContextTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getContextType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getElementOid() {
		return elementOid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementOid(long newElementOid) {
		long oldElementOid = elementOid;
		elementOid = newElementOid;
		boolean oldElementOidESet = elementOidESet;
		elementOidESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONTEXT_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetElementOid() {
		long oldElementOid = elementOid;
		boolean oldElementOidESet = elementOidESet;
		elementOid = ELEMENT_OID_EDEFAULT;
		elementOidESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.CONTEXT_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetElementOid() {
		return elementOidESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeType> getAttribute() {
		if (attribute == null) {
			attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotPackage.CONTEXT_TYPE__ATTRIBUTE);
		}
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AccessPointType> getAccessPoint() {
		if (accessPoint == null) {
			accessPoint = new EObjectContainmentEList<AccessPointType>(AccessPointType.class, this, CarnotPackage.CONTEXT_TYPE__ACCESS_POINT);
		}
		return accessPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DescriptionType getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDescription(DescriptionType newDescription, NotificationChain msgs) {
		DescriptionType oldDescription = description;
		description = newDescription;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.CONTEXT_TYPE__DESCRIPTION, oldDescription, newDescription);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(DescriptionType newDescription) {
		if (newDescription != description) {
			NotificationChain msgs = null;
			if (description != null)
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.CONTEXT_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.CONTEXT_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONTEXT_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationContextTypeType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(ApplicationContextTypeType newType, NotificationChain msgs) {
		ApplicationContextTypeType oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.CONTEXT_TYPE__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ApplicationContextTypeType newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS, ApplicationContextTypeType.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS, ApplicationContextTypeType.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONTEXT_TYPE__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMetaType getMetaType() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.CONTEXT_TYPE__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS, ApplicationContextTypeType.class, msgs);
				return basicSetType((ApplicationContextTypeType)otherEnd, msgs);
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
			case CarnotPackage.CONTEXT_TYPE__ATTRIBUTE:
				return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
			case CarnotPackage.CONTEXT_TYPE__ACCESS_POINT:
				return ((InternalEList<?>)getAccessPoint()).basicRemove(otherEnd, msgs);
			case CarnotPackage.CONTEXT_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case CarnotPackage.CONTEXT_TYPE__TYPE:
				return basicSetType(null, msgs);
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
			case CarnotPackage.CONTEXT_TYPE__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.CONTEXT_TYPE__ATTRIBUTE:
				return getAttribute();
			case CarnotPackage.CONTEXT_TYPE__ACCESS_POINT:
				return getAccessPoint();
			case CarnotPackage.CONTEXT_TYPE__DESCRIPTION:
				return getDescription();
			case CarnotPackage.CONTEXT_TYPE__TYPE:
				return getType();
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
			case CarnotPackage.CONTEXT_TYPE__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.CONTEXT_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection<? extends AttributeType>)newValue);
				return;
			case CarnotPackage.CONTEXT_TYPE__ACCESS_POINT:
				getAccessPoint().clear();
				getAccessPoint().addAll((Collection<? extends AccessPointType>)newValue);
				return;
			case CarnotPackage.CONTEXT_TYPE__DESCRIPTION:
				setDescription((DescriptionType)newValue);
				return;
			case CarnotPackage.CONTEXT_TYPE__TYPE:
				setType((ApplicationContextTypeType)newValue);
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
			case CarnotPackage.CONTEXT_TYPE__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.CONTEXT_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case CarnotPackage.CONTEXT_TYPE__ACCESS_POINT:
				getAccessPoint().clear();
				return;
			case CarnotPackage.CONTEXT_TYPE__DESCRIPTION:
				setDescription((DescriptionType)null);
				return;
			case CarnotPackage.CONTEXT_TYPE__TYPE:
				setType((ApplicationContextTypeType)null);
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
			case CarnotPackage.CONTEXT_TYPE__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.CONTEXT_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case CarnotPackage.CONTEXT_TYPE__ACCESS_POINT:
				return accessPoint != null && !accessPoint.isEmpty();
			case CarnotPackage.CONTEXT_TYPE__DESCRIPTION:
				return description != null;
			case CarnotPackage.CONTEXT_TYPE__TYPE:
				return type != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IExtensibleElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.CONTEXT_TYPE__ATTRIBUTE: return CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
				default: return -1;
			}
		}
		if (baseClass == ITypedElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IAccessPointOwner.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.CONTEXT_TYPE__ACCESS_POINT: return CarnotPackage.IACCESS_POINT_OWNER__ACCESS_POINT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IExtensibleElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotPackage.CONTEXT_TYPE__ATTRIBUTE;
				default: return -1;
			}
		}
		if (baseClass == ITypedElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IAccessPointOwner.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IACCESS_POINT_OWNER__ACCESS_POINT: return CarnotPackage.CONTEXT_TYPE__ACCESS_POINT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == IExtensibleElement.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == ITypedElement.class) {
			switch (baseOperationID) {
				case CarnotPackage.ITYPED_ELEMENT___GET_META_TYPE: return CarnotPackage.CONTEXT_TYPE___GET_META_TYPE;
				default: return -1;
			}
		}
		if (baseClass == IAccessPointOwner.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case CarnotPackage.CONTEXT_TYPE___GET_META_TYPE:
				return getMetaType();
		}
		return super.eInvoke(operationID, arguments);
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
		if (elementOidESet) result.append(elementOid); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ContextTypeImpl
