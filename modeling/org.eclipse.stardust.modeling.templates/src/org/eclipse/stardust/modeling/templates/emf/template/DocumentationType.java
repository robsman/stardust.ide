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
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documentation Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.DocumentationType#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.DocumentationType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.DocumentationType#getAny <em>Any</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getDocumentationType()
 * @model extendedMetaData="name='documentation' kind='mixed'"
 * @generated
 */
public interface DocumentationType extends EObject {
	/**
    * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
    * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Mixed</em>' attribute list.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getDocumentationType_Mixed()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
    *        extendedMetaData="kind='elementWildcard' name=':mixed'"
    * @generated
    */
	FeatureMap getMixed();

	/**
    * Returns the value of the '<em><b>Group</b></em>' attribute list.
    * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Group</em>' attribute list.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getDocumentationType_Group()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
    *        extendedMetaData="kind='group' name='group:1'"
    * @generated
    */
	FeatureMap getGroup();

	/**
    * Returns the value of the '<em><b>Any</b></em>' attribute list.
    * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Any</em>' attribute list.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getDocumentationType_Any()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
    *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':2' processing='lax' group='#group:1'"
    * @generated
    */
	FeatureMap getAny();
	
	/**
	 * @generated NOT
	 */
	String getAsText();

} // DocumentationType
