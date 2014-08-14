/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.ExternalPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.ExternalPackages;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Packages</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.impl.ExternalPackagesImpl#getExternalPackage <em>External Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalPackagesImpl extends MinimalEObjectImpl.Container implements ExternalPackages {
	/**
	 * The cached value of the '{@link #getExternalPackage() <em>External Package</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPackage()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalPackage> externalPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalPackagesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XpdlPackage.Literals.EXTERNAL_PACKAGES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExternalPackage> getExternalPackage() {
		if (externalPackage == null) {
			externalPackage = new EObjectContainmentEList<ExternalPackage>(ExternalPackage.class, this, XpdlPackage.EXTERNAL_PACKAGES__EXTERNAL_PACKAGE);
		}
		return externalPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPackage getExternalPackage(String packageId) {
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XpdlPackage.EXTERNAL_PACKAGES__EXTERNAL_PACKAGE:
				return ((InternalEList<?>)getExternalPackage()).basicRemove(otherEnd, msgs);
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
			case XpdlPackage.EXTERNAL_PACKAGES__EXTERNAL_PACKAGE:
				return getExternalPackage();
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
			case XpdlPackage.EXTERNAL_PACKAGES__EXTERNAL_PACKAGE:
				getExternalPackage().clear();
				getExternalPackage().addAll((Collection<? extends ExternalPackage>)newValue);
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
			case XpdlPackage.EXTERNAL_PACKAGES__EXTERNAL_PACKAGE:
				getExternalPackage().clear();
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
			case XpdlPackage.EXTERNAL_PACKAGES__EXTERNAL_PACKAGE:
				return externalPackage != null && !externalPackage.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case XpdlPackage.EXTERNAL_PACKAGES___GET_EXTERNAL_PACKAGE__STRING:
				return getExternalPackage((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //ExternalPackagesImpl
