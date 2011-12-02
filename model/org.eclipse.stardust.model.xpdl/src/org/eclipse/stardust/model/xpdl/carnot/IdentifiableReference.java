/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.carnot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifiable Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference#getIdentifiable <em>Identifiable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIdentifiableReference()
 * @model
 * @generated
 */
public interface IdentifiableReference extends EObject {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH";

	/**
    * Returns the value of the '<em><b>Attribute</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getReference <em>Reference</em>}'.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Attribute</em>' reference.
    * @see #setAttribute(AttributeType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIdentifiableReference_Attribute()
    * @see org.eclipse.stardust.model.xpdl.carnot.AttributeType#getReference
    * @model opposite="reference" resolveProxies="false" transient="true" derived="true"
    * @generated
    */
	AttributeType getAttribute();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference#getAttribute <em>Attribute</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Attribute</em>' reference.
    * @see #getAttribute()
    * @generated
    */
	void setAttribute(AttributeType value);

	/**
    * Returns the value of the '<em><b>Identifiable</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifiable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Identifiable</em>' reference.
    * @see #setIdentifiable(EObject)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIdentifiableReference_Identifiable()
    * @model resolveProxies="false" transient="true" derived="true"
    * @generated
    */
	EObject getIdentifiable();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference#getIdentifiable <em>Identifiable</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Identifiable</em>' reference.
    * @see #getIdentifiable()
    * @generated
    */
	void setIdentifiable(EObject value);

} // IdentifiableReference
