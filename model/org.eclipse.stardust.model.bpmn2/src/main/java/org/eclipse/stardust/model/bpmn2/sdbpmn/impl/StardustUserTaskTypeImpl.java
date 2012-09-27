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
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust User Task Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustUserTaskTypeImpl#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustUserTaskTypeImpl#getInteractiveApplicationRef <em>Interactive Application Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustUserTaskTypeImpl extends TStardustActivityImpl implements StardustUserTaskType {
    /**
     * The default value of the '{@link #isAllowsAbortByPerformer() <em>Allows Abort By Performer</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAllowsAbortByPerformer()
     * @generated
     * @ordered
     */
    protected static final boolean ALLOWS_ABORT_BY_PERFORMER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isAllowsAbortByPerformer() <em>Allows Abort By Performer</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAllowsAbortByPerformer()
     * @generated
     * @ordered
     */
    protected boolean allowsAbortByPerformer = ALLOWS_ABORT_BY_PERFORMER_EDEFAULT;

    /**
     * This is true if the Allows Abort By Performer attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean allowsAbortByPerformerESet;

    /**
     * The default value of the '{@link #getInteractiveApplicationRef() <em>Interactive Application Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInteractiveApplicationRef()
     * @generated
     * @ordered
     */
    protected static final String INTERACTIVE_APPLICATION_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInteractiveApplicationRef() <em>Interactive Application Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInteractiveApplicationRef()
     * @generated
     * @ordered
     */
    protected String interactiveApplicationRef = INTERACTIVE_APPLICATION_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StardustUserTaskTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SdbpmnPackage.Literals.STARDUST_USER_TASK_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isAllowsAbortByPerformer() {
        return allowsAbortByPerformer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAllowsAbortByPerformer(boolean newAllowsAbortByPerformer) {
        boolean oldAllowsAbortByPerformer = allowsAbortByPerformer;
        allowsAbortByPerformer = newAllowsAbortByPerformer;
        boolean oldAllowsAbortByPerformerESet = allowsAbortByPerformerESet;
        allowsAbortByPerformerESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_USER_TASK_TYPE__ALLOWS_ABORT_BY_PERFORMER, oldAllowsAbortByPerformer, allowsAbortByPerformer, !oldAllowsAbortByPerformerESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetAllowsAbortByPerformer() {
        boolean oldAllowsAbortByPerformer = allowsAbortByPerformer;
        boolean oldAllowsAbortByPerformerESet = allowsAbortByPerformerESet;
        allowsAbortByPerformer = ALLOWS_ABORT_BY_PERFORMER_EDEFAULT;
        allowsAbortByPerformerESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, SdbpmnPackage.STARDUST_USER_TASK_TYPE__ALLOWS_ABORT_BY_PERFORMER, oldAllowsAbortByPerformer, ALLOWS_ABORT_BY_PERFORMER_EDEFAULT, oldAllowsAbortByPerformerESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetAllowsAbortByPerformer() {
        return allowsAbortByPerformerESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getInteractiveApplicationRef() {
        return interactiveApplicationRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInteractiveApplicationRef(String newInteractiveApplicationRef) {
        String oldInteractiveApplicationRef = interactiveApplicationRef;
        interactiveApplicationRef = newInteractiveApplicationRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_USER_TASK_TYPE__INTERACTIVE_APPLICATION_REF, oldInteractiveApplicationRef, interactiveApplicationRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SdbpmnPackage.STARDUST_USER_TASK_TYPE__ALLOWS_ABORT_BY_PERFORMER:
                return isAllowsAbortByPerformer();
            case SdbpmnPackage.STARDUST_USER_TASK_TYPE__INTERACTIVE_APPLICATION_REF:
                return getInteractiveApplicationRef();
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
            case SdbpmnPackage.STARDUST_USER_TASK_TYPE__ALLOWS_ABORT_BY_PERFORMER:
                setAllowsAbortByPerformer((Boolean)newValue);
                return;
            case SdbpmnPackage.STARDUST_USER_TASK_TYPE__INTERACTIVE_APPLICATION_REF:
                setInteractiveApplicationRef((String)newValue);
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
            case SdbpmnPackage.STARDUST_USER_TASK_TYPE__ALLOWS_ABORT_BY_PERFORMER:
                unsetAllowsAbortByPerformer();
                return;
            case SdbpmnPackage.STARDUST_USER_TASK_TYPE__INTERACTIVE_APPLICATION_REF:
                setInteractiveApplicationRef(INTERACTIVE_APPLICATION_REF_EDEFAULT);
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
            case SdbpmnPackage.STARDUST_USER_TASK_TYPE__ALLOWS_ABORT_BY_PERFORMER:
                return isSetAllowsAbortByPerformer();
            case SdbpmnPackage.STARDUST_USER_TASK_TYPE__INTERACTIVE_APPLICATION_REF:
                return INTERACTIVE_APPLICATION_REF_EDEFAULT == null ? interactiveApplicationRef != null : !INTERACTIVE_APPLICATION_REF_EDEFAULT.equals(interactiveApplicationRef);
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
        result.append(" (allowsAbortByPerformer: ");
        if (allowsAbortByPerformerESet) result.append(allowsAbortByPerformer); else result.append("<unset>");
        result.append(", interactiveApplicationRef: ");
        result.append(interactiveApplicationRef);
        result.append(')');
        return result.toString();
    }

} //StardustUserTaskTypeImpl
