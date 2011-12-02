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
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getDataMappings <em>Data Mappings</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataType#isPredefined <em>Predefined</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getDataSymbols <em>Data Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getConditionalPerformers <em>Conditional Performers</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getDataPaths <em>Data Paths</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getParameterMappings <em>Parameter Mappings</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getExternalReference <em>External Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataType()
 * @model extendedMetaData="name='data_._type' kind='elementOnly'"
 * @generated
 */
public interface DataType extends IIdentifiableModelElement, ITypedElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Data Mappings</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getData <em>Data</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Data Mappings</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Data Mappings</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataType_DataMappings()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataMappingType#getData
    * @model opposite="data" transient="true"
    * @generated
    */
   EList<DataMappingType> getDataMappings();

   /**
    * Returns the value of the '<em><b>Predefined</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A boolean that indicates whether the data element is predefined or
    *                   created by modeling.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Predefined</em>' attribute.
    * @see #isSetPredefined()
    * @see #unsetPredefined()
    * @see #setPredefined(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataType_Predefined()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='predefined'"
    * @generated
    */
   boolean isPredefined();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#isPredefined <em>Predefined</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Predefined</em>' attribute.
    * @see #isSetPredefined()
    * @see #unsetPredefined()
    * @see #isPredefined()
    * @generated
    */
   void setPredefined(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#isPredefined <em>Predefined</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetPredefined()
    * @see #isPredefined()
    * @see #setPredefined(boolean)
    * @generated
    */
   void unsetPredefined();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#isPredefined <em>Predefined</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Predefined</em>' attribute is set.
    * @see #unsetPredefined()
    * @see #isPredefined()
    * @see #setPredefined(boolean)
    * @generated
    */
   boolean isSetPredefined();

   /**
    * Returns the value of the '<em><b>Type</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getData <em>Data</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   Abstract description of the data type of the data element (e.g.
    *                   primitive, entity). Depending on that type class names are given as
    *                   attribute elements.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Type</em>' reference.
    * @see #setType(DataTypeType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataType_Type()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataTypeType#getData
    * @model opposite="data" resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='type'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   DataTypeType getType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getType <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' reference.
    * @see #getType()
    * @generated
    */
   void setType(DataTypeType value);

   /**
    * Returns the value of the '<em><b>Data Symbols</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.DataSymbolType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataSymbolType#getData <em>Data</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Data Symbols</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Data Symbols</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataType_DataSymbols()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataSymbolType#getData
    * @model opposite="data" transient="true"
    * @generated
    */
   EList<DataSymbolType> getDataSymbols();

   /**
    * Returns the value of the '<em><b>Conditional Performers</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#getData <em>Data</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Conditional Performers</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Conditional Performers</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataType_ConditionalPerformers()
    * @see org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#getData
    * @model opposite="data" transient="true"
    * @generated
    */
   EList<ConditionalPerformerType> getConditionalPerformers();

   /**
    * Returns the value of the '<em><b>Data Paths</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.DataPathType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType#getData <em>Data</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Data Paths</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Data Paths</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataType_DataPaths()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataPathType#getData
    * @model opposite="data" transient="true"
    * @generated
    */
   EList<DataPathType> getDataPaths();

   /**
    * Returns the value of the '<em><b>Parameter Mappings</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getData <em>Data</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parameter Mappings</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Parameter Mappings</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataType_ParameterMappings()
    * @see org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType#getData
    * @model opposite="data" transient="true"
    * @generated
    */
   EList<ParameterMappingType> getParameterMappings();

   /**
    * Returns the value of the '<em><b>External Reference</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>External Reference</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>External Reference</em>' containment reference.
    * @see #setExternalReference(ExternalReferenceType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDataType_ExternalReference()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='ExternalReference' namespace='http://www.wfmc.org/2008/XPDL2.1'"
    * @generated
    */
   ExternalReferenceType getExternalReference();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getExternalReference <em>External Reference</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>External Reference</em>' containment reference.
    * @see #getExternalReference()
    * @generated
    */
   void setExternalReference(ExternalReferenceType value);

} // DataType
