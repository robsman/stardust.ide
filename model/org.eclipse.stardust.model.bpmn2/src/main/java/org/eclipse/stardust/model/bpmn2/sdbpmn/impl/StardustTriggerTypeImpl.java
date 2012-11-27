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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType;

import org.eclipse.stardust.model.xpdl.carnot.impl.TriggerTypeImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Trigger Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTriggerTypeImpl#getAccessPoint1 <em>Access Point1</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTriggerTypeImpl#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustTriggerTypeImpl extends TriggerTypeImpl implements StardustTriggerType {
	/**
	 * The cached value of the '{@link #getAccessPoint1() <em>Access Point1</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessPoint1()
	 * @generated
	 * @ordered
	 */
	protected EList<StardustAccessPointType> accessPoint1;

	/**
	 * The cached value of the '{@link #getContext() <em>Context</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContext()
	 * @generated
	 * @ordered
	 */
	protected EList<StardustContextType> context;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StardustTriggerTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.STARDUST_TRIGGER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StardustAccessPointType> getAccessPoint1() {
		if (accessPoint1 == null) {
			accessPoint1 = new EObjectContainmentEList<StardustAccessPointType>(StardustAccessPointType.class, this, SdbpmnPackage.STARDUST_TRIGGER_TYPE__ACCESS_POINT1);
		}
		return accessPoint1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StardustContextType> getContext() {
		if (context == null) {
			context = new EObjectContainmentEList<StardustContextType>(StardustContextType.class, this, SdbpmnPackage.STARDUST_TRIGGER_TYPE__CONTEXT);
		}
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__ACCESS_POINT1:
				return ((InternalEList<?>)getAccessPoint1()).basicRemove(otherEnd, msgs);
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__CONTEXT:
				return ((InternalEList<?>)getContext()).basicRemove(otherEnd, msgs);
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
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__ACCESS_POINT1:
				return getAccessPoint1();
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__CONTEXT:
				return getContext();
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
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__ACCESS_POINT1:
				getAccessPoint1().clear();
				getAccessPoint1().addAll((Collection<? extends StardustAccessPointType>)newValue);
				return;
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__CONTEXT:
				getContext().clear();
				getContext().addAll((Collection<? extends StardustContextType>)newValue);
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
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__ACCESS_POINT1:
				getAccessPoint1().clear();
				return;
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__CONTEXT:
				getContext().clear();
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
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__ACCESS_POINT1:
				return accessPoint1 != null && !accessPoint1.isEmpty();
			case SdbpmnPackage.STARDUST_TRIGGER_TYPE__CONTEXT:
				return context != null && !context.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //StardustTriggerTypeImpl