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
package org.eclipse.stardust.modeling.templates.emf.template;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Roots Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.RootsType#getRoot <em>Root</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getRootsType()
 * @model extendedMetaData="name='roots' kind='elementOnly'"
 * @generated
 */
public interface RootsType extends EObject {
	/**
    * Returns the value of the '<em><b>Root</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Root</em>' containment reference list.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getRootsType_Root()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='root' namespace='##targetNamespace'"
    * @generated
    */
	EList<ReferenceType> getRoot();

} // RootsType
