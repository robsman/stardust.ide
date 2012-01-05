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
package org.eclipse.stardust.model.xpdl.xpdl2;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extended Attribute Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getExtendedAnnotation <em>Extended Annotation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExtendedAttributeType()
 * @model extendedMetaData="name='ExtendedAttribute_._type' kind='mixed'"
 * @generated
 */
public interface ExtendedAttributeType extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public static final String EXTERNAL_ANNOTATIONS_NAME = "carnot:engine:resource:mapping:annotations";

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2008 by SunGard";

	/**
    * Returns the value of the '<em><b>Extended Annotation</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Annotation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Extended Annotation</em>' containment reference.
    * @see #setExtendedAnnotation(ExtendedAnnotationType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExtendedAttributeType_ExtendedAnnotation()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='ExtendedAnnotation' namespace='http://www.carnot.ag/workflowmodel/3.1/xpdl/extensions'"
    * @generated
    */
	ExtendedAnnotationType getExtendedAnnotation();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getExtendedAnnotation <em>Extended Annotation</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Extended Annotation</em>' containment reference.
    * @see #getExtendedAnnotation()
    * @generated
    */
	void setExtendedAnnotation(ExtendedAnnotationType value);

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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExtendedAttributeType_Mixed()
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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExtendedAttributeType_Group()
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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExtendedAttributeType_Any()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
    *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':2' processing='lax' group='#group:1'"
    * @generated
    */
	FeatureMap getAny();

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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExtendedAttributeType_Name()
    * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
    *        extendedMetaData="kind='attribute' name='Name'"
    * @generated
    */
	String getName();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
	void setName(String value);

	/**
    * Returns the value of the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Value</em>' attribute.
    * @see #setValue(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExtendedAttributeType_Value()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='Value'"
    * @generated
    */
	String getValue();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getValue <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Value</em>' attribute.
    * @see #getValue()
    * @generated
    */
	void setValue(String value);

} // ExtendedAttributeType