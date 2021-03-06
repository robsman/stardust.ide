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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Parameter Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getMode <em>Mode</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getFormalParameterType()
 * @model extendedMetaData="name='FormalParameter_._type' kind='elementOnly'"
 * @generated
 */
public interface FormalParameterType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Data Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Data Type</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Data Type</em>' containment reference.
    * @see #setDataType(DataTypeType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getFormalParameterType_DataType()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='DataType' namespace='##targetNamespace'"
    * @generated
    */
   DataTypeType getDataType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getDataType <em>Data Type</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Data Type</em>' containment reference.
    * @see #getDataType()
    * @generated
    */
   void setDataType(DataTypeType value);

   /**
    * Returns the value of the '<em><b>Description</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Description</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Description</em>' attribute.
    * @see #setDescription(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getFormalParameterType_Description()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
    * @generated
    */
   String getDescription();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getDescription <em>Description</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Description</em>' attribute.
    * @see #getDescription()
    * @generated
    */
   void setDescription(String value);

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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getFormalParameterType_Id()
    * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
    *        extendedMetaData="kind='attribute' name='Id'"
    * @generated
    */
   String getId();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getId <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Id</em>' attribute.
    * @see #getId()
    * @generated
    */
   void setId(String value);

   /**
    * Returns the value of the '<em><b>Mode</b></em>' attribute.
    * The default value is <code>"IN"</code>.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.xpdl2.ModeType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Mode</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Mode</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
    * @see #isSetMode()
    * @see #unsetMode()
    * @see #setMode(ModeType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getFormalParameterType_Mode()
    * @model default="IN" unsettable="true"
    *        extendedMetaData="kind='attribute' name='Mode'"
    * @generated
    */
   ModeType getMode();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getMode <em>Mode</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Mode</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
    * @see #isSetMode()
    * @see #unsetMode()
    * @see #getMode()
    * @generated
    */
   void setMode(ModeType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getMode <em>Mode</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetMode()
    * @see #getMode()
    * @see #setMode(ModeType)
    * @generated
    */
   void unsetMode();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getMode <em>Mode</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Mode</em>' attribute is set.
    * @see #unsetMode()
    * @see #getMode()
    * @see #setMode(ModeType)
    * @generated
    */
   boolean isSetMode();

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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getFormalParameterType_Name()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='Name'"
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

} // FormalParameterType
