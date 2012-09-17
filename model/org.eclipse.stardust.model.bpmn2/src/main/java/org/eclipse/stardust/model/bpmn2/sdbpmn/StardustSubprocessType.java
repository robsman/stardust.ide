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
package org.eclipse.stardust.model.bpmn2.sdbpmn;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Subprocess Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType#getImplementationProcess <em>Implementation Process</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustSubprocessType()
 * @model extendedMetaData="name='StardustSubprocess_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustSubprocessType extends TStardustActivity {
    /**
     * Returns the value of the '<em><b>Implementation Process</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     *
     * 								The model id of the subprocess implementing the activity when the
     * 								attribute "implementation" is set to "Subprocess".
     *
     * <!-- end-model-doc -->
     * @return the value of the '<em>Implementation Process</em>' attribute.
     * @see #setImplementationProcess(String)
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustSubprocessType_ImplementationProcess()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='implementationProcess'"
     * @generated
     */
    String getImplementationProcess();

    /**
     * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType#getImplementationProcess <em>Implementation Process</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation Process</em>' attribute.
     * @see #getImplementationProcess()
     * @generated
     */
    void setImplementationProcess(String value);

} // StardustSubprocessType
