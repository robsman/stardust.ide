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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getAccessPathEditor <em>Access Path Editor</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getEvaluator <em>Evaluator</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getInstanceClass <em>Instance Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#isReadable <em>Readable</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getStorageStrategy <em>Storage Strategy</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getValueCreator <em>Value Creator</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#isWritable <em>Writable</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType()
 * @model extendedMetaData="name='dataType_._type' kind='elementOnly'"
 * @generated
 */
public interface DataTypeType extends IMetaType{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Access Path Editor</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Access Path Editor</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Access Path Editor</em>' attribute.
    * @see #setAccessPathEditor(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_AccessPathEditor()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='accessPathEditor'"
    * @generated
    */
   String getAccessPathEditor();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getAccessPathEditor <em>Access Path Editor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Access Path Editor</em>' attribute.
    * @see #getAccessPathEditor()
    * @generated
    */
   void setAccessPathEditor(String value);

   /**
    * Returns the value of the '<em><b>Evaluator</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Evaluator</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Evaluator</em>' attribute.
    * @see #setEvaluator(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_Evaluator()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='evaluator'"
    * @generated
    */
   String getEvaluator();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getEvaluator <em>Evaluator</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Evaluator</em>' attribute.
    * @see #getEvaluator()
    * @generated
    */
   void setEvaluator(String value);

   /**
    * Returns the value of the '<em><b>Instance Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Instance Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Instance Class</em>' attribute.
    * @see #setInstanceClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_InstanceClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='instanceClass'"
    * @generated
    */
   String getInstanceClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getInstanceClass <em>Instance Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Instance Class</em>' attribute.
    * @see #getInstanceClass()
    * @generated
    */
   void setInstanceClass(String value);

   /**
    * Returns the value of the '<em><b>Panel Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Panel Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Panel Class</em>' attribute.
    * @see #setPanelClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_PanelClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='panelClass'"
    * @generated
    */
   String getPanelClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getPanelClass <em>Panel Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Panel Class</em>' attribute.
    * @see #getPanelClass()
    * @generated
    */
   void setPanelClass(String value);

   /**
    * Returns the value of the '<em><b>Readable</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Readable</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Readable</em>' attribute.
    * @see #isSetReadable()
    * @see #unsetReadable()
    * @see #setReadable(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_Readable()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='readable'"
    * @generated
    */
   boolean isReadable();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#isReadable <em>Readable</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Readable</em>' attribute.
    * @see #isSetReadable()
    * @see #unsetReadable()
    * @see #isReadable()
    * @generated
    */
   void setReadable(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#isReadable <em>Readable</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetReadable()
    * @see #isReadable()
    * @see #setReadable(boolean)
    * @generated
    */
   void unsetReadable();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#isReadable <em>Readable</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Readable</em>' attribute is set.
    * @see #unsetReadable()
    * @see #isReadable()
    * @see #setReadable(boolean)
    * @generated
    */
   boolean isSetReadable();

   /**
    * Returns the value of the '<em><b>Storage Strategy</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Storage Strategy</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Storage Strategy</em>' attribute.
    * @see #setStorageStrategy(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_StorageStrategy()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='storageStrategy'"
    * @generated
    */
   String getStorageStrategy();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getStorageStrategy <em>Storage Strategy</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Storage Strategy</em>' attribute.
    * @see #getStorageStrategy()
    * @generated
    */
   void setStorageStrategy(String value);

   /**
    * Returns the value of the '<em><b>Validator Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Validator Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Validator Class</em>' attribute.
    * @see #setValidatorClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_ValidatorClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='validatorClass'"
    * @generated
    */
   String getValidatorClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getValidatorClass <em>Validator Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Validator Class</em>' attribute.
    * @see #getValidatorClass()
    * @generated
    */
   void setValidatorClass(String value);

   /**
    * Returns the value of the '<em><b>Value Creator</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Value Creator</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Value Creator</em>' attribute.
    * @see #setValueCreator(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_ValueCreator()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='valueCreator'"
    * @generated
    */
   String getValueCreator();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getValueCreator <em>Value Creator</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Value Creator</em>' attribute.
    * @see #getValueCreator()
    * @generated
    */
   void setValueCreator(String value);

   /**
    * Returns the value of the '<em><b>Writable</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Writable</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Writable</em>' attribute.
    * @see #isSetWritable()
    * @see #unsetWritable()
    * @see #setWritable(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_Writable()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='writable'"
    * @generated
    */
   boolean isWritable();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#isWritable <em>Writable</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Writable</em>' attribute.
    * @see #isSetWritable()
    * @see #unsetWritable()
    * @see #isWritable()
    * @generated
    */
   void setWritable(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#isWritable <em>Writable</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetWritable()
    * @see #isWritable()
    * @see #setWritable(boolean)
    * @generated
    */
   void unsetWritable();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#isWritable <em>Writable</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Writable</em>' attribute is set.
    * @see #unsetWritable()
    * @see #isWritable()
    * @see #setWritable(boolean)
    * @generated
    */
   boolean isSetWritable();

   /**
    * Returns the value of the '<em><b>Data</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.DataType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Data</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Data</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataTypeType_Data()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataType#getType
    * @model opposite="type" transient="true"
    * @generated
    */
   EList<DataType> getData();

} // DataTypeType
