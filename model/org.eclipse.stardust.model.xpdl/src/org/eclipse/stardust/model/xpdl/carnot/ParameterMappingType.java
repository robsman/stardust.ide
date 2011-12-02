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
 * A representation of the model object '<em><b>Parameter Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getParameterPath <em>Parameter Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getParameterMappingType()
 * @model extendedMetaData="name='parameterMapping_._type' kind='empty'"
 * @generated
 */
public interface ParameterMappingType extends IModelElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Data</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getParameterMappings <em>Parameter Mappings</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The model id of the data element.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Data</em>' reference.
    * @see #setData(DataType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getParameterMappingType_Data()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataType#getParameterMappings
    * @model opposite="parameterMappings" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='data'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   DataType getData();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getData <em>Data</em>}' reference.
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
    *                   The access path (data mapping) to the data element.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Data Path</em>' attribute.
    * @see #setDataPath(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getParameterMappingType_DataPath()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='dataPath'"
    * @generated
    */
   String getDataPath();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getDataPath <em>Data Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Data Path</em>' attribute.
    * @see #getDataPath()
    * @generated
    */
   void setDataPath(String value);

   /**
    * Returns the value of the '<em><b>Parameter</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The name of the parameter the data element is mapped to.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Parameter</em>' attribute.
    * @see #setParameter(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getParameterMappingType_Parameter()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='parameter'"
    * @generated
    */
   String getParameter();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getParameter <em>Parameter</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Parameter</em>' attribute.
    * @see #getParameter()
    * @generated
    */
   void setParameter(String value);

   /**
    * Returns the value of the '<em><b>Parameter Path</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The access path (data mapping) to the data element.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Parameter Path</em>' attribute.
    * @see #setParameterPath(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getParameterMappingType_ParameterPath()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='parameterPath'"
    * @generated
    */
   String getParameterPath();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getParameterPath <em>Parameter Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Parameter Path</em>' attribute.
    * @see #getParameterPath()
    * @generated
    */
   void setParameterPath(String value);

} // ParameterMappingType
