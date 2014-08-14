/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingType;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.FormalParameterType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Formal Parameter Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl.FormalParameterMappingTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl.FormalParameterMappingTypeImpl#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormalParameterMappingTypeImpl extends MinimalEObjectImpl.Container implements FormalParameterMappingType {
	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected DataType data;

	/**
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected FormalParameterType parameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormalParameterMappingTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtensionsPackage.Literals.FORMAL_PARAMETER_MAPPING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getData() {
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setData(DataType newData) {
		DataType oldData = data;
		data = newData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__DATA, oldData, data));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParameterType getParameter() {
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameter(FormalParameterType newParameter) {
		FormalParameterType oldParameter = parameter;
		parameter = newParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__PARAMETER, oldParameter, parameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__DATA:
				return getData();
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__PARAMETER:
				return getParameter();
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
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__DATA:
				setData((DataType)newValue);
				return;
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__PARAMETER:
				setParameter((FormalParameterType)newValue);
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
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__DATA:
				setData((DataType)null);
				return;
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__PARAMETER:
				setParameter((FormalParameterType)null);
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
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__DATA:
				return data != null;
			case ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE__PARAMETER:
				return parameter != null;
		}
		return super.eIsSet(featureID);
	}

} //FormalParameterMappingTypeImpl
