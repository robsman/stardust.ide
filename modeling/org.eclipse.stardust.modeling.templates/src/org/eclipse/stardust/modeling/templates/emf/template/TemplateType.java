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
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getRoots <em>Roots</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getCategory <em>Category</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateType()
 * @model extendedMetaData="name='template' kind='elementOnly'"
 * @generated
 */
public interface TemplateType extends EObject {
	/**
    * Returns the value of the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Id</em>' attribute.
    * @see #setId(String)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateType_Id()
    * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
    *        extendedMetaData="kind='attribute' name='id'"
    * @generated
    */
	String getId();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getId <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Id</em>' attribute.
    * @see #getId()
    * @generated
    */
	void setId(String value);

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
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateType_Name()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='name'"
    * @generated
    */
	String getName();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
	void setName(String value);

	/**
    * Returns the value of the '<em><b>Documentation</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Documentation</em>' containment reference.
    * @see #setDocumentation(DocumentationType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateType_Documentation()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='documentation' namespace='##targetNamespace'"
    * @generated
    */
	DocumentationType getDocumentation();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getDocumentation <em>Documentation</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Documentation</em>' containment reference.
    * @see #getDocumentation()
    * @generated
    */
	void setDocumentation(DocumentationType value);

	/**
    * Returns the value of the '<em><b>Roots</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roots</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Roots</em>' containment reference.
    * @see #setRoots(RootsType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateType_Roots()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='roots' namespace='##targetNamespace'"
    * @generated
    */
	RootsType getRoots();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getRoots <em>Roots</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Roots</em>' containment reference.
    * @see #getRoots()
    * @generated
    */
	void setRoots(RootsType value);

	/**
    * Returns the value of the '<em><b>Style</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.modeling.templates.emf.template.StyleType}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Style</em>' attribute.
    * @see org.eclipse.stardust.modeling.templates.emf.template.StyleType
    * @see #setStyle(StyleType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateType_Style()
    * @model extendedMetaData="kind='attribute' name='style'"
    * @generated
    */
	StyleType getStyle();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getStyle <em>Style</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Style</em>' attribute.
    * @see org.eclipse.stardust.modeling.templates.emf.template.StyleType
    * @see #getStyle()
    * @generated
    */
	void setStyle(StyleType value);

	/**
    * Returns the value of the '<em><b>Category</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Category</em>' attribute.
    * @see #setCategory(String)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateType_Category()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='category'"
    * @generated
    */
	String getCategory();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getCategory <em>Category</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Category</em>' attribute.
    * @see #getCategory()
    * @generated
    */
	void setCategory(String value);

} // TemplateType
