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
 * A representation of the model object '<em><b>Stardust Service Task Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType#getApplication <em>Application</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustServiceTaskType()
 * @model extendedMetaData="name='StardustServiceTask_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustServiceTaskType extends TStardustActivity {
    /**
     * Returns the value of the '<em><b>Application</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     *
     * 								The model id of the application to execute when the attribute
     * 								"implementation" is set to "Application".
     *
     * <!-- end-model-doc -->
     * @return the value of the '<em>Application</em>' attribute.
     * @see #setApplication(String)
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustServiceTaskType_Application()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='application'"
     * @generated
     */
    String getApplication();

    /**
     * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType#getApplication <em>Application</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Application</em>' attribute.
     * @see #getApplication()
     * @generated
     */
    void setApplication(String value);

} // StardustServiceTaskType
