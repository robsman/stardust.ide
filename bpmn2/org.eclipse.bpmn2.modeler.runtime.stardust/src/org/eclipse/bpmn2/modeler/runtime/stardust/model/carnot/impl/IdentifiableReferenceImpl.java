/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdentifiableReference;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Identifiable Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IdentifiableReferenceImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IdentifiableReferenceImpl#getIdentifiable <em>Identifiable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdentifiableReferenceImpl extends MinimalEObjectImpl.Container implements IdentifiableReference {
	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected AttributeType attribute;

	/**
	 * The cached value of the '{@link #getIdentifiable() <em>Identifiable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifiable()
	 * @generated
	 * @ordered
	 */
	protected EObject identifiable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdentifiableReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getIdentifiableReference();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeType getAttribute() {
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAttribute(AttributeType newAttribute, NotificationChain msgs) {
		AttributeType oldAttribute = attribute;
		attribute = newAttribute;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE, oldAttribute, newAttribute);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttribute(AttributeType newAttribute) {
		if (newAttribute != attribute) {
			NotificationChain msgs = null;
			if (attribute != null)
				msgs = ((InternalEObject)attribute).eInverseRemove(this, CarnotPackage.ATTRIBUTE_TYPE__REFERENCE, AttributeType.class, msgs);
			if (newAttribute != null)
				msgs = ((InternalEObject)newAttribute).eInverseAdd(this, CarnotPackage.ATTRIBUTE_TYPE__REFERENCE, AttributeType.class, msgs);
			msgs = basicSetAttribute(newAttribute, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE, newAttribute, newAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getIdentifiable() {
		return identifiable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentifiable(EObject newIdentifiable) {
		EObject oldIdentifiable = identifiable;
		identifiable = newIdentifiable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE, oldIdentifiable, identifiable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
				if (attribute != null)
					msgs = ((InternalEObject)attribute).eInverseRemove(this, CarnotPackage.ATTRIBUTE_TYPE__REFERENCE, AttributeType.class, msgs);
				return basicSetAttribute((AttributeType)otherEnd, msgs);
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
			case CarnotPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
				return basicSetAttribute(null, msgs);
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
			case CarnotPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
				return getAttribute();
			case CarnotPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE:
				return getIdentifiable();
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
			case CarnotPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
				setAttribute((AttributeType)newValue);
				return;
			case CarnotPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE:
				setIdentifiable((EObject)newValue);
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
			case CarnotPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
				setAttribute((AttributeType)null);
				return;
			case CarnotPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE:
				setIdentifiable((EObject)null);
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
			case CarnotPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
				return attribute != null;
			case CarnotPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE:
				return identifiable != null;
		}
		return super.eIsSet(featureID);
	}

} //IdentifiableReferenceImpl
