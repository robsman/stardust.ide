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
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Interface Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getStardustApplication <em>Stardust Application</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getApplicationType <em>Application Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustInterfaceType()
 * @model extendedMetaData="name='StardustInterface_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustInterfaceType extends EObject {
    /**
     * Returns the value of the '<em><b>Stardust Application</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stardust Application</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Stardust Application</em>' containment reference.
     * @see #setStardustApplication(StardustApplicationType)
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustInterfaceType_StardustApplication()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='StardustApplication' namespace='##targetNamespace'"
     * @generated
     */
    StardustApplicationType getStardustApplication();

    /**
     * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getStardustApplication <em>Stardust Application</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Stardust Application</em>' containment reference.
     * @see #getStardustApplication()
     * @generated
     */
    void setStardustApplication(StardustApplicationType value);

    /**
     * Returns the value of the '<em><b>Application Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Application Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Application Type</em>' attribute.
     * @see #setApplicationType(String)
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustInterfaceType_ApplicationType()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='applicationType'"
     * @generated
     */
    String getApplicationType();

    /**
     * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getApplicationType <em>Application Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Application Type</em>' attribute.
     * @see #getApplicationType()
     * @generated
     */
    void setApplicationType(String value);

} // StardustInterfaceType
