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
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getValueNode <em>Value Node</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAttributeType()
 * @model extendedMetaData="name='attribute_._type' kind='mixed'"
 * @generated
 */
public interface AttributeType extends EObject
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
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAttributeType_Mixed()
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
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAttributeType_Group()
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
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAttributeType_Any()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
    *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':2' processing='lax' group='#group:1'"
    * @generated
    */
   FeatureMap getAny();

   /**
    * Returns the value of the '<em><b>Value Node</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * Stores multi line attribute values.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Value Node</em>' containment reference.
    * @see #setValueNode(XmlTextNode)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAttributeType_ValueNode()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='value' namespace='##targetNamespace' group='#group:1'"
    * @generated
    */
   XmlTextNode getValueNode();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getValueNode <em>Value Node</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Value Node</em>' containment reference.
    * @see #getValueNode()
    * @generated
    */
   void setValueNode(XmlTextNode value);

   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The name of this attributes. It is recommended to use names with a
    *                   structure like
    *                   <code xmlns="http://www.carnot.ag/workflowmodel/3.1">vendor:component:sub-component:..:property</code>
    *                   , i.e.
    *                   <code xmlns="http://www.carnot.ag/workflowmodel/3.1">carnot:defdesk:icon</code>
    *                   vs.
    *                   <code xmlns="http://www.carnot.ag/workflowmodel/3.1">carnot:engine:defaultValue</code>
    *                   .
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAttributeType_Name()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
    *        extendedMetaData="kind='attribute' name='name'"
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

   /**
    * Returns the value of the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The optional type of this attribute. Will be used as hint while
    *                   attribute value evaluation, if existent.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Type</em>' attribute.
    * @see #setType(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAttributeType_Type()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='type'"
    * @generated
    */
   String getType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getType <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' attribute.
    * @see #getType()
    * @generated
    */
   void setType(String value);

   /**
    * Returns the value of the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The value of this attribute, if it can be represented as a single line
    *                   string. Alterntively arbitrary complex child element nodes may be used
    *                   to represent more complex values.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Value</em>' attribute.
    * @see #setValue(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAttributeType_Value()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='value'"
    * @generated
    */
   String getValue();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getValue <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Value</em>' attribute.
    * @see #getValue()
    * @generated
    */
   void setValue(String value);

   /**
    * Returns the value of the '<em><b>Reference</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference#getAttribute <em>Attribute</em>}'.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Reference</em>' reference.
    * @see #setReference(IdentifiableReference)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAttributeType_Reference()
    * @see org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference#getAttribute
    * @model opposite="attribute" resolveProxies="false" transient="true" derived="true"
    * @generated
    */
	IdentifiableReference getReference();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType#getReference <em>Reference</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Reference</em>' reference.
    * @see #getReference()
    * @generated
    */
	void setReference(IdentifiableReference value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation" dataType="org.eclipse.emf.ecore.xml.type.String"
    * @generated
    */
   String getAttributeValue();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model typeDataType="org.eclipse.emf.ecore.xml.type.String" valueDataType="org.eclipse.emf.ecore.xml.type.String"
    * @generated
    */
   void setAttributeValue(String type, String value);

} // AttributeType
