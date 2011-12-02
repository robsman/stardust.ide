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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Path Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#isDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#isKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#getDirection <em>Direction</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataPathType()
 * @model extendedMetaData="name='dataPath_._type' kind='empty'"
 * @generated
 */
public interface DataPathType extends IIdentifiableModelElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Data</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getDataPaths <em>Data Paths</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The unique model id of the corresponding data element.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Data</em>' reference.
    * @see #setData(DataType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataPathType_Data()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataType#getDataPaths
    * @model opposite="dataPaths" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='data'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   DataType getData();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#getData <em>Data</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Data</em>' reference.
    * @see #getData()
    * @generated
    */
   void setData(DataType value);

   /**
    * Returns the value of the '<em><b>Data Path</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The method of the data object to retrieve or put the data. Can be left
    *                   empty, if the whole object is used.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Data Path</em>' attribute.
    * @see #setDataPath(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataPathType_DataPath()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='dataPath'"
    * @generated
    */
   String getDataPath();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#getDataPath <em>Data Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Data Path</em>' attribute.
    * @see #getDataPath()
    * @generated
    */
   void setDataPath(String value);

   /**
    * Returns the value of the '<em><b>Descriptor</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Descriptor</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Descriptor</em>' attribute.
    * @see #isSetDescriptor()
    * @see #unsetDescriptor()
    * @see #setDescriptor(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataPathType_Descriptor()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='descriptor'"
    * @generated
    */
   boolean isDescriptor();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#isDescriptor <em>Descriptor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Descriptor</em>' attribute.
    * @see #isSetDescriptor()
    * @see #unsetDescriptor()
    * @see #isDescriptor()
    * @generated
    */
   void setDescriptor(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#isDescriptor <em>Descriptor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetDescriptor()
    * @see #isDescriptor()
    * @see #setDescriptor(boolean)
    * @generated
    */
   void unsetDescriptor();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#isDescriptor <em>Descriptor</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Descriptor</em>' attribute is set.
    * @see #unsetDescriptor()
    * @see #isDescriptor()
    * @see #setDescriptor(boolean)
    * @generated
    */
   boolean isSetDescriptor();

   /**
    * Returns the value of the '<em><b>Key</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Key</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Key</em>' attribute.
    * @see #isSetKey()
    * @see #unsetKey()
    * @see #setKey(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataPathType_Key()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='key'"
    * @generated
    */
   boolean isKey();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#isKey <em>Key</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Key</em>' attribute.
    * @see #isSetKey()
    * @see #unsetKey()
    * @see #isKey()
    * @generated
    */
   void setKey(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#isKey <em>Key</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetKey()
    * @see #isKey()
    * @see #setKey(boolean)
    * @generated
    */
   void unsetKey();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#isKey <em>Key</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Key</em>' attribute is set.
    * @see #unsetKey()
    * @see #isKey()
    * @see #setKey(boolean)
    * @generated
    */
   boolean isSetKey();

   /**
    * Returns the value of the '<em><b>Direction</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.carnot.DirectionType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The direction of the data path. Valid values are "IN" |
    *                   "OUT".
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Direction</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.DirectionType
    * @see #setDirection(DirectionType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataPathType_Direction()
    * @model unique="false" required="true"
    *        extendedMetaData="kind='attribute' name='direction'"
    * @generated
    */
   DirectionType getDirection();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#getDirection <em>Direction</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Direction</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.DirectionType
    * @see #getDirection()
    * @generated
    */
   void setDirection(DirectionType value);

} // DataPathType
