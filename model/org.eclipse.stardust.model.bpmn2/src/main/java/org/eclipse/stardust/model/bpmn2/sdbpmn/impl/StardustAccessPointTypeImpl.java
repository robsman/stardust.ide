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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;

import org.eclipse.stardust.model.xpdl.carnot.impl.AccessPointTypeImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Access Point Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAccessPointTypeImpl#getTypeRef <em>Type Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustAccessPointTypeImpl extends AccessPointTypeImpl implements StardustAccessPointType {
    /**
     * The default value of the '{@link #getTypeRef() <em>Type Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeRef()
     * @generated
     * @ordered
     */
    protected static final String TYPE_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTypeRef() <em>Type Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeRef()
     * @generated
     * @ordered
     */
    protected String typeRef = TYPE_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StardustAccessPointTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SdbpmnPackage.Literals.STARDUST_ACCESS_POINT_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTypeRef() {
        return typeRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypeRef(String newTypeRef) {
        String oldTypeRef = typeRef;
        typeRef = newTypeRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_ACCESS_POINT_TYPE__TYPE_REF, oldTypeRef, typeRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SdbpmnPackage.STARDUST_ACCESS_POINT_TYPE__TYPE_REF:
                return getTypeRef();
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
            case SdbpmnPackage.STARDUST_ACCESS_POINT_TYPE__TYPE_REF:
                setTypeRef((String)newValue);
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
            case SdbpmnPackage.STARDUST_ACCESS_POINT_TYPE__TYPE_REF:
                setTypeRef(TYPE_REF_EDEFAULT);
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
            case SdbpmnPackage.STARDUST_ACCESS_POINT_TYPE__TYPE_REF:
                return TYPE_REF_EDEFAULT == null ? typeRef != null : !TYPE_REF_EDEFAULT.equals(typeRef);
        }
        return super.eIsSet(featureID);
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
        result.append(" (typeRef: ");
        result.append(typeRef);
        result.append(')');
        return result.toString();
    }

} //StardustAccessPointTypeImpl