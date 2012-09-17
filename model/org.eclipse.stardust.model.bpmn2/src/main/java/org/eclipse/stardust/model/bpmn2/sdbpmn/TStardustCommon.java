/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TStardust Common</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon#getElementOid <em>Element Oid</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getTStardustCommon()
 * @model extendedMetaData="name='tStardustCommon' kind='empty'"
 * @generated
 */
public interface TStardustCommon extends EObject {
    /**
     * Returns the value of the '<em><b>Element Oid</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element Oid</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Element Oid</em>' attribute.
     * @see #setElementOid(String)
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getTStardustCommon_ElementOid()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='elementOid'"
     * @generated
     */
    String getElementOid();

    /**
     * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon#getElementOid <em>Element Oid</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Element Oid</em>' attribute.
     * @see #getElementOid()
     * @generated
     */
    void setElementOid(String value);

} // TStardustCommon
