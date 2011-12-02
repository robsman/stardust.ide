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
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ParameterType#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ParameterType#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getParameterType()
 * @model extendedMetaData="name='parameter' kind='elementOnly'"
 * @generated
 */
public interface ParameterType extends ReferenceType {
	/**
    * Returns the value of the '<em><b>Activity</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Activity</em>' reference.
    * @see #setActivity(ActivityType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getParameterType_Activity()
    * @model extendedMetaData="kind='attribute' name='activity'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
    * @generated
    */
	ActivityType getActivity();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ParameterType#getActivity <em>Activity</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Activity</em>' reference.
    * @see #getActivity()
    * @generated
    */
	void setActivity(ActivityType value);

	/**
    * Returns the value of the '<em><b>Features</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Features</em>' containment reference list.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getParameterType_Features()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='feature' namespace='##targetNamespace'"
    * @generated
    */
	EList<FeatureType> getFeatures();

} // ParameterType
