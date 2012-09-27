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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Message Start Event Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl#getStardustAttributes <em>Stardust Attributes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl#getAccessPoint <em>Access Point</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl#getParameterMapping <em>Parameter Mapping</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustMessageStartEventTypeImpl extends EObjectImpl implements StardustMessageStartEventType {
    /**
     * The cached value of the '{@link #getStardustAttributes() <em>Stardust Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStardustAttributes()
     * @generated
     * @ordered
     */
    protected StardustAttributesType stardustAttributes;

    /**
     * The cached value of the '{@link #getAccessPoint() <em>Access Point</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAccessPoint()
     * @generated
     * @ordered
     */
    protected EList<AccessPointType> accessPoint;

    /**
     * The cached value of the '{@link #getParameterMapping() <em>Parameter Mapping</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterMapping()
     * @generated
     * @ordered
     */
    protected EList<ParameterMappingType> parameterMapping;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StardustMessageStartEventTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SdbpmnPackage.Literals.STARDUST_MESSAGE_START_EVENT_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StardustAttributesType getStardustAttributes() {
        return stardustAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStardustAttributes(StardustAttributesType newStardustAttributes, NotificationChain msgs) {
        StardustAttributesType oldStardustAttributes = stardustAttributes;
        stardustAttributes = newStardustAttributes;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES, oldStardustAttributes, newStardustAttributes);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStardustAttributes(StardustAttributesType newStardustAttributes) {
        if (newStardustAttributes != stardustAttributes) {
            NotificationChain msgs = null;
            if (stardustAttributes != null)
                msgs = ((InternalEObject)stardustAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES, null, msgs);
            if (newStardustAttributes != null)
                msgs = ((InternalEObject)newStardustAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES, null, msgs);
            msgs = basicSetStardustAttributes(newStardustAttributes, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES, newStardustAttributes, newStardustAttributes));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AccessPointType> getAccessPoint() {
        if (accessPoint == null) {
            accessPoint = new EObjectContainmentEList<AccessPointType>(AccessPointType.class, this, SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__ACCESS_POINT);
        }
        return accessPoint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ParameterMappingType> getParameterMapping() {
        if (parameterMapping == null) {
            parameterMapping = new EObjectContainmentEList<ParameterMappingType>(ParameterMappingType.class, this, SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__PARAMETER_MAPPING);
        }
        return parameterMapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
                return basicSetStardustAttributes(null, msgs);
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__ACCESS_POINT:
                return ((InternalEList<?>)getAccessPoint()).basicRemove(otherEnd, msgs);
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__PARAMETER_MAPPING:
                return ((InternalEList<?>)getParameterMapping()).basicRemove(otherEnd, msgs);
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
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
                return getStardustAttributes();
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__ACCESS_POINT:
                return getAccessPoint();
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__PARAMETER_MAPPING:
                return getParameterMapping();
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__TYPE:
                return getType();
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
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
                setStardustAttributes((StardustAttributesType)newValue);
                return;
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__ACCESS_POINT:
                getAccessPoint().clear();
                getAccessPoint().addAll((Collection<? extends AccessPointType>)newValue);
                return;
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__PARAMETER_MAPPING:
                getParameterMapping().clear();
                getParameterMapping().addAll((Collection<? extends ParameterMappingType>)newValue);
                return;
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__TYPE:
                setType((String)newValue);
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
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
                setStardustAttributes((StardustAttributesType)null);
                return;
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__ACCESS_POINT:
                getAccessPoint().clear();
                return;
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__PARAMETER_MAPPING:
                getParameterMapping().clear();
                return;
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__TYPE:
                setType(TYPE_EDEFAULT);
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
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES:
                return stardustAttributes != null;
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__ACCESS_POINT:
                return accessPoint != null && !accessPoint.isEmpty();
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__PARAMETER_MAPPING:
                return parameterMapping != null && !parameterMapping.isEmpty();
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
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
        result.append(" (type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //StardustMessageStartEventTypeImpl
