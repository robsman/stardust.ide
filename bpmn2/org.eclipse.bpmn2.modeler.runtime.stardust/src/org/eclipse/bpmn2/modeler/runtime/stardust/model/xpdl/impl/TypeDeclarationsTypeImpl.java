/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.TypeDeclarationType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.TypeDeclarationsType;
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
 * An implementation of the model object '<em><b>Type Declarations Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.impl.TypeDeclarationsTypeImpl#getTypeDeclaration <em>Type Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeDeclarationsTypeImpl extends MinimalEObjectImpl.Container implements TypeDeclarationsType {
	/**
	 * The cached value of the '{@link #getTypeDeclaration() <em>Type Declaration</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeDeclarationType> typeDeclaration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeDeclarationsTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XpdlPackage.Literals.TYPE_DECLARATIONS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeDeclarationType> getTypeDeclaration() {
		if (typeDeclaration == null) {
			typeDeclaration = new EObjectContainmentEList<TypeDeclarationType>(TypeDeclarationType.class, this, XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION);
		}
		return typeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationType getTypeDeclaration(String typeId) {
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
			case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
				return ((InternalEList<?>)getTypeDeclaration()).basicRemove(otherEnd, msgs);
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
			case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
				return getTypeDeclaration();
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
			case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
				getTypeDeclaration().clear();
				getTypeDeclaration().addAll((Collection<? extends TypeDeclarationType>)newValue);
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
			case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
				getTypeDeclaration().clear();
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
			case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
				return typeDeclaration != null && !typeDeclaration.isEmpty();
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
			case XpdlPackage.TYPE_DECLARATIONS_TYPE___GET_TYPE_DECLARATION__STRING:
				return getTypeDeclaration((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //TypeDeclarationsTypeImpl
