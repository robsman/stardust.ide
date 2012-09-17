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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Attributes Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType#getAttributeType <em>Attribute Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustAttributesType()
 * @model extendedMetaData="name='StardustAttributes_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustAttributesType extends EObject {
    /**
     * Returns the value of the '<em><b>Attribute Type</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.AttributeType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attribute Type</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attribute Type</em>' containment reference list.
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustAttributesType_AttributeType()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='AttributeType' namespace='http://www.carnot.ag/workflowmodel/3.1'"
     * @generated
     */
    EList<AttributeType> getAttributeType();

} // StardustAttributesType
