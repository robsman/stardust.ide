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
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getTemplates <em>Templates</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateLibraryType()
 * @model extendedMetaData="kind='elementOnly' name='TemplateLibrary'"
 * @generated NOT
 */
public interface TemplateLibraryType extends EObject {
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
	 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateLibraryType_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getId <em>Id</em>}' attribute.
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
	 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateLibraryType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getName <em>Name</em>}' attribute.
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
	 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateLibraryType_Documentation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	DocumentationType getDocumentation();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(DocumentationType value);

	/**
	 * Returns the value of the '<em><b>Templates</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Templates</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Templates</em>' containment reference.
	 * @see #setTemplates(TemplatesType)
	 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateLibraryType_Templates()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='templates' namespace='##targetNamespace'"
	 * @generated
	 */
	TemplatesType getTemplates();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getTemplates <em>Templates</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Templates</em>' containment reference.
	 * @see #getTemplates()
	 * @generated
	 */
	void setTemplates(TemplatesType value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' containment reference.
	 * @see #setModel(ModelType)
	 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getTemplateLibraryType_Model()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='model' namespace='http://www.carnot.ag/workflowmodel/3.1'"
	 * @generated
	 */
	ModelType getModel();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getModel <em>Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' containment reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(ModelType value);

} // TemplateLibraryType
