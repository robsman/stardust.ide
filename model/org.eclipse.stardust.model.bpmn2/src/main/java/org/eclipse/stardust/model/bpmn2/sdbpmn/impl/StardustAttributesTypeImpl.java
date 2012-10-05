/**
 * ****************************************************************************
 *  Copyright (c) 2012 ITpearls AG and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     ITpearls - initial API and implementation and/or initial documentation
 * *****************************************************************************
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Attributes Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAttributesTypeImpl#getAttributeType <em>Attribute Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustAttributesTypeImpl extends EObjectImpl implements StardustAttributesType {
	/**
	 * The cached value of the '{@link #getAttributeType() <em>Attribute Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeType()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeType> attributeType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StardustAttributesTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.STARDUST_ATTRIBUTES_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeType> getAttributeType() {
		if (attributeType == null) {
			attributeType = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, SdbpmnPackage.STARDUST_ATTRIBUTES_TYPE__ATTRIBUTE_TYPE);
		}
		return attributeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_ATTRIBUTES_TYPE__ATTRIBUTE_TYPE:
				return ((InternalEList<?>)getAttributeType()).basicRemove(otherEnd, msgs);
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
			case SdbpmnPackage.STARDUST_ATTRIBUTES_TYPE__ATTRIBUTE_TYPE:
				return getAttributeType();
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
			case SdbpmnPackage.STARDUST_ATTRIBUTES_TYPE__ATTRIBUTE_TYPE:
				getAttributeType().clear();
				getAttributeType().addAll((Collection<? extends AttributeType>)newValue);
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
			case SdbpmnPackage.STARDUST_ATTRIBUTES_TYPE__ATTRIBUTE_TYPE:
				getAttributeType().clear();
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
			case SdbpmnPackage.STARDUST_ATTRIBUTES_TYPE__ATTRIBUTE_TYPE:
				return attributeType != null && !attributeType.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //StardustAttributesTypeImpl
