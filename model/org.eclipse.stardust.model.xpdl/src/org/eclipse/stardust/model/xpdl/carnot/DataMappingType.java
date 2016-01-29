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
 * A representation of the model object '<em><b>Data Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getApplicationAccessPoint <em>Application Access Point</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getApplicationPath <em>Application Path</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getContext <em>Context</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getDirection <em>Direction</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataMappingType()
 * @model extendedMetaData="name='dataMapping_._type' kind='empty'"
 * @generated
 */
public interface DataMappingType extends IModelElement, IIdentifiableElement, IExtensibleElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Application Access Point</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The name of the access point for the application in scope of the
    *                   activity.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Application Access Point</em>' attribute.
    * @see #setApplicationAccessPoint(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataMappingType_ApplicationAccessPoint()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='applicationAccessPoint'"
    * @generated
    */
   String getApplicationAccessPoint();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getApplicationAccessPoint <em>Application Access Point</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Application Access Point</em>' attribute.
    * @see #getApplicationAccessPoint()
    * @generated
    */
   void setApplicationAccessPoint(String value);

   /**
    * Returns the value of the '<em><b>Application Path</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The method of the application to retrieve or put the data to/from the
    *                   application.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Application Path</em>' attribute.
    * @see #setApplicationPath(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataMappingType_ApplicationPath()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='applicationPath'"
    * @generated
    */
   String getApplicationPath();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getApplicationPath <em>Application Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Application Path</em>' attribute.
    * @see #getApplicationPath()
    * @generated
    */
   void setApplicationPath(String value);

   /**
    * Returns the value of the '<em><b>Context</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   If the activity is an interactive activity then the context specifies
    *                   for which type of interactive activity this data mapping is used. Valid
    *                   values are the following model id's of previously defined
    *                   applicationContextType elements: "jfc" | "jsp"
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Context</em>' attribute.
    * @see #setContext(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataMappingType_Context()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='context'"
    * @generated
    */
   String getContext();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getContext <em>Context</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Context</em>' attribute.
    * @see #getContext()
    * @generated
    */
   void setContext(String value);

   /**
    * Returns the value of the '<em><b>Data</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getDataMappings <em>Data Mappings</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The unique model id of the corresponding data element.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Data</em>' reference.
    * @see #setData(DataType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataMappingType_Data()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataType#getDataMappings
    * @model opposite="dataMappings" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='data'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   DataType getData();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getData <em>Data</em>}' reference.
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
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataMappingType_DataPath()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='dataPath'"
    * @generated
    */
   String getDataPath();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getDataPath <em>Data Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Data Path</em>' attribute.
    * @see #getDataPath()
    * @generated
    */
   void setDataPath(String value);

   /**
    * Returns the value of the '<em><b>Direction</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.carnot.DirectionType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The direction of the mapping. Valid values are "IN" |
    *                   "OUT".
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Direction</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.DirectionType
    * @see #setDirection(DirectionType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataMappingType_Direction()
    * @model unique="false" required="true"
    *        extendedMetaData="kind='attribute' name='direction'"
    * @generated
    */
   DirectionType getDirection();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getDirection <em>Direction</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Direction</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.DirectionType
    * @see #getDirection()
    * @generated
    */
   void setDirection(DirectionType value);

} // DataMappingType
