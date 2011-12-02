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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getFeatureType()
 * @model extendedMetaData="name='feature' kind='elementOnly'"
 * @generated
 */
public interface FeatureType extends EObject {
	/**
    * Returns the value of the '<em><b>Label</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Label</em>' attribute.
    * @see #setLabel(String)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getFeatureType_Label()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='label'"
    * @generated
    */
	String getLabel();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getLabel <em>Label</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Label</em>' attribute.
    * @see #getLabel()
    * @generated
    */
	void setLabel(String value);

	/**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getFeatureType_Name()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
    *        extendedMetaData="kind='attribute' name='name'"
    * @generated
    */
	String getName();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
	void setName(String value);

	/**
    * Returns the value of the '<em><b>Type</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Type</em>' attribute.
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType
    * @see #setType(FeatureStyleType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getFeatureType_Type()
    * @model extendedMetaData="kind='attribute' name='type'"
    * @generated
    */
	FeatureStyleType getType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getType <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' attribute.
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType
    * @see #getType()
    * @generated
    */
	void setType(FeatureStyleType value);

	/**
    * Returns the value of the '<em><b>Scope</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.modeling.templates.emf.template.ScopeType}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Scope</em>' attribute.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ScopeType
    * @see #setScope(ScopeType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getFeatureType_Scope()
    * @model extendedMetaData="kind='attribute' name='scope'"
    * @generated
    */
	ScopeType getScope();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getScope <em>Scope</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Scope</em>' attribute.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ScopeType
    * @see #getScope()
    * @generated
    */
	void setScope(ScopeType value);

} // FeatureType
