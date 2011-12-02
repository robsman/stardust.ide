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

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DocumentRoot#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

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
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDocumentRoot_Mixed()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
    *        extendedMetaData="kind='elementWildcard' name=':mixed'"
    * @generated
    */
   FeatureMap getMixed();

   /**
    * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
    * The key is of type {@link java.lang.String},
    * and the value is of type {@link java.lang.String},
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>XMLNS Prefix Map</em>' map.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDocumentRoot_XMLNSPrefixMap()
    * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
    *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
    * @generated
    */
   EMap<String, String> getXMLNSPrefixMap();

   /**
    * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
    * The key is of type {@link java.lang.String},
    * and the value is of type {@link java.lang.String},
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>XSI Schema Location</em>' map.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDocumentRoot_XSISchemaLocation()
    * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
    *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
    * @generated
    */
   EMap<String, String> getXSISchemaLocation();

   /**
    * Returns the value of the '<em><b>Model</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *             The model declaration defines the root element of a model file.
    *          
    * <!-- end-model-doc -->
    * @return the value of the '<em>Model</em>' containment reference.
    * @see #setModel(ModelType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDocumentRoot_Model()
    * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
    *        extendedMetaData="kind='element' name='model' namespace='##targetNamespace'"
    * @generated
    */
   ModelType getModel();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DocumentRoot#getModel <em>Model</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Model</em>' containment reference.
    * @see #getModel()
    * @generated
    */
   void setModel(ModelType value);

} // DocumentRoot
