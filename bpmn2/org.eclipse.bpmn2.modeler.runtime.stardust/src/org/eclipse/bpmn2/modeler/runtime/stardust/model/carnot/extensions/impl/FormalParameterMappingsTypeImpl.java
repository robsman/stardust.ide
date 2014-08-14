/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingsType;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.FormalParameterType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Formal Parameter Mappings Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl.FormalParameterMappingsTypeImpl#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormalParameterMappingsTypeImpl extends MinimalEObjectImpl.Container implements FormalParameterMappingsType {
	/**
	 * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapping()
	 * @generated
	 * @ordered
	 */
	protected EList<FormalParameterMappingType> mapping;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormalParameterMappingsTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtensionsPackage.Literals.FORMAL_PARAMETER_MAPPINGS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FormalParameterMappingType> getMapping() {
		if (mapping == null) {
			mapping = new EObjectContainmentEList<FormalParameterMappingType>(FormalParameterMappingType.class, this, ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING);
		}
		return mapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getMappedData(FormalParameterType formalParameter) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedData(FormalParameterType formalParameter, DataType data) {
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
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
				return ((InternalEList<?>)getMapping()).basicRemove(otherEnd, msgs);
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
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
				return getMapping();
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
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
				getMapping().clear();
				getMapping().addAll((Collection<? extends FormalParameterMappingType>)newValue);
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
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
				getMapping().clear();
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
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
				return mapping != null && !mapping.isEmpty();
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
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE___GET_MAPPED_DATA__FORMALPARAMETERTYPE:
				return getMappedData((FormalParameterType)arguments.get(0));
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE___SET_MAPPED_DATA__FORMALPARAMETERTYPE_DATATYPE:
				setMappedData((FormalParameterType)arguments.get(0), (DataType)arguments.get(1));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

} //FormalParameterMappingsTypeImpl
