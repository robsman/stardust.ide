/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.FormalParameterType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.FormalParametersType;
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
 * An implementation of the model object '<em><b>Formal Parameters Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.impl.FormalParametersTypeImpl#getFormalParameter <em>Formal Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormalParametersTypeImpl extends MinimalEObjectImpl.Container implements FormalParametersType {
	/**
	 * The cached value of the '{@link #getFormalParameter() <em>Formal Parameter</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormalParameter()
	 * @generated
	 * @ordered
	 */
	protected EList<FormalParameterType> formalParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormalParametersTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XpdlPackage.Literals.FORMAL_PARAMETERS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FormalParameterType> getFormalParameter() {
		if (formalParameter == null) {
			formalParameter = new EObjectContainmentEList<FormalParameterType>(FormalParameterType.class, this, XpdlPackage.FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER);
		}
		return formalParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addFormalParameter(FormalParameterType parameter) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParameterType getFormalParameter(String parameterId) {
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
			case XpdlPackage.FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER:
				return ((InternalEList<?>)getFormalParameter()).basicRemove(otherEnd, msgs);
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
			case XpdlPackage.FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER:
				return getFormalParameter();
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
			case XpdlPackage.FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER:
				getFormalParameter().clear();
				getFormalParameter().addAll((Collection<? extends FormalParameterType>)newValue);
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
			case XpdlPackage.FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER:
				getFormalParameter().clear();
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
			case XpdlPackage.FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER:
				return formalParameter != null && !formalParameter.isEmpty();
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
			case XpdlPackage.FORMAL_PARAMETERS_TYPE___ADD_FORMAL_PARAMETER__FORMALPARAMETERTYPE:
				addFormalParameter((FormalParameterType)arguments.get(0));
				return null;
			case XpdlPackage.FORMAL_PARAMETERS_TYPE___GET_FORMAL_PARAMETER__STRING:
				return getFormalParameter((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //FormalParametersTypeImpl
