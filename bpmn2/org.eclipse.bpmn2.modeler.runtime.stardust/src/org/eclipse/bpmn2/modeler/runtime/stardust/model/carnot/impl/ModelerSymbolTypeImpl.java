/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modeler Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelerSymbolTypeImpl#getModeler <em>Modeler</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelerSymbolTypeImpl extends IModelElementNodeSymbolImpl implements ModelerSymbolType {
	/**
	 * The cached value of the '{@link #getModeler() <em>Modeler</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModeler()
	 * @generated
	 * @ordered
	 */
	protected ModelerType modeler;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelerSymbolTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getModelerSymbolType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelerType getModeler() {
		return modeler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModeler(ModelerType newModeler, NotificationChain msgs) {
		ModelerType oldModeler = modeler;
		modeler = newModeler;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.MODELER_SYMBOL_TYPE__MODELER, oldModeler, newModeler);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModeler(ModelerType newModeler) {
		if (newModeler != modeler) {
			NotificationChain msgs = null;
			if (modeler != null)
				msgs = ((InternalEObject)modeler).eInverseRemove(this, CarnotPackage.MODELER_TYPE__MODELER_SYMBOLS, ModelerType.class, msgs);
			if (newModeler != null)
				msgs = ((InternalEObject)newModeler).eInverseAdd(this, CarnotPackage.MODELER_TYPE__MODELER_SYMBOLS, ModelerType.class, msgs);
			msgs = basicSetModeler(newModeler, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODELER_SYMBOL_TYPE__MODELER, newModeler, newModeler));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.MODELER_SYMBOL_TYPE__MODELER:
				if (modeler != null)
					msgs = ((InternalEObject)modeler).eInverseRemove(this, CarnotPackage.MODELER_TYPE__MODELER_SYMBOLS, ModelerType.class, msgs);
				return basicSetModeler((ModelerType)otherEnd, msgs);
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
			case CarnotPackage.MODELER_SYMBOL_TYPE__MODELER:
				return basicSetModeler(null, msgs);
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
			case CarnotPackage.MODELER_SYMBOL_TYPE__MODELER:
				return getModeler();
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
			case CarnotPackage.MODELER_SYMBOL_TYPE__MODELER:
				setModeler((ModelerType)newValue);
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
			case CarnotPackage.MODELER_SYMBOL_TYPE__MODELER:
				setModeler((ModelerType)null);
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
			case CarnotPackage.MODELER_SYMBOL_TYPE__MODELER:
				return modeler != null;
		}
		return super.eIsSet(featureID);
	}

} //ModelerSymbolTypeImpl
