/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************
 * $Id$
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.model.bpmn2.sdbpmn.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage
 * @generated
 */
public class SdbpmnSwitch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static SdbpmnPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SdbpmnSwitch() {
        if (modelPackage == null) {
            modelPackage = SdbpmnPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case SdbpmnPackage.DOCUMENT_ROOT: {
                DocumentRoot documentRoot = (DocumentRoot)theEObject;
                T result = caseDocumentRoot(documentRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.STARDUST_ATTRIBUTES_TYPE: {
                StardustAttributesType stardustAttributesType = (StardustAttributesType)theEObject;
                T result = caseStardustAttributesType(stardustAttributesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.STARDUST_MESSAGE_START_EVENT_TYPE: {
                StardustMessageStartEventType stardustMessageStartEventType = (StardustMessageStartEventType)theEObject;
                T result = caseStardustMessageStartEventType(stardustMessageStartEventType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.STARDUST_MODEL_TYPE: {
                StardustModelType stardustModelType = (StardustModelType)theEObject;
                T result = caseStardustModelType(stardustModelType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.STARDUST_SEQENCE_FLOW_TYPE: {
                StardustSeqenceFlowType stardustSeqenceFlowType = (StardustSeqenceFlowType)theEObject;
                T result = caseStardustSeqenceFlowType(stardustSeqenceFlowType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.STARDUST_SERVICE_TASK_TYPE: {
                StardustServiceTaskType stardustServiceTaskType = (StardustServiceTaskType)theEObject;
                T result = caseStardustServiceTaskType(stardustServiceTaskType);
                if (result == null) result = caseTStardustActivity(stardustServiceTaskType);
                if (result == null) result = caseTStardustCommon(stardustServiceTaskType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.STARDUST_START_EVENT_TYPE: {
                StardustStartEventType stardustStartEventType = (StardustStartEventType)theEObject;
                T result = caseStardustStartEventType(stardustStartEventType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.STARDUST_SUBPROCESS_TYPE: {
                StardustSubprocessType stardustSubprocessType = (StardustSubprocessType)theEObject;
                T result = caseStardustSubprocessType(stardustSubprocessType);
                if (result == null) result = caseTStardustActivity(stardustSubprocessType);
                if (result == null) result = caseTStardustCommon(stardustSubprocessType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.STARDUST_TIMER_START_EVENT_TYPE: {
                StardustTimerStartEventType stardustTimerStartEventType = (StardustTimerStartEventType)theEObject;
                T result = caseStardustTimerStartEventType(stardustTimerStartEventType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.STARDUST_USER_TASK_TYPE: {
                StardustUserTaskType stardustUserTaskType = (StardustUserTaskType)theEObject;
                T result = caseStardustUserTaskType(stardustUserTaskType);
                if (result == null) result = caseTStardustActivity(stardustUserTaskType);
                if (result == null) result = caseTStardustCommon(stardustUserTaskType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.TSTARDUST_ACTIVITY: {
                TStardustActivity tStardustActivity = (TStardustActivity)theEObject;
                T result = caseTStardustActivity(tStardustActivity);
                if (result == null) result = caseTStardustCommon(tStardustActivity);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SdbpmnPackage.TSTARDUST_COMMON: {
                TStardustCommon tStardustCommon = (TStardustCommon)theEObject;
                T result = caseTStardustCommon(tStardustCommon);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentRoot(DocumentRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stardust Attributes Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stardust Attributes Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStardustAttributesType(StardustAttributesType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stardust Message Start Event Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stardust Message Start Event Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStardustMessageStartEventType(StardustMessageStartEventType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stardust Model Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stardust Model Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStardustModelType(StardustModelType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stardust Seqence Flow Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stardust Seqence Flow Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStardustSeqenceFlowType(StardustSeqenceFlowType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stardust Service Task Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stardust Service Task Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStardustServiceTaskType(StardustServiceTaskType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stardust Start Event Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stardust Start Event Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStardustStartEventType(StardustStartEventType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stardust Subprocess Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stardust Subprocess Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStardustSubprocessType(StardustSubprocessType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stardust Timer Start Event Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stardust Timer Start Event Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStardustTimerStartEventType(StardustTimerStartEventType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stardust User Task Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stardust User Task Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStardustUserTaskType(StardustUserTaskType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TStardust Activity</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TStardust Activity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTStardustActivity(TStardustActivity object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TStardust Common</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TStardust Common</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTStardustCommon(TStardustCommon object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} //SdbpmnSwitch
