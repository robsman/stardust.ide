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
 * A representation of the model object '<em><b>Conditional Performer Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#isIsUser <em>Is User</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#getConditionalPerformerSymbols <em>Conditional Performer Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getConditionalPerformerType()
 * @model extendedMetaData="name='conditionalPerformer_._type' kind='elementOnly'"
 * @generated
 */
public interface ConditionalPerformerType extends IModelParticipant{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Data</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataType#getConditionalPerformers <em>Conditional Performers</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The unique model id of the corresponding data element.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Data</em>' reference.
    * @see #setData(DataType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getConditionalPerformerType_Data()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataType#getConditionalPerformers
    * @model opposite="conditionalPerformers" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='data'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   DataType getData();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#getData <em>Data</em>}' reference.
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
    *                   The data path the data which determines the runtime identity of the
    *                   performer. This must evaluate to String (id) or long (oid).
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Data Path</em>' attribute.
    * @see #setDataPath(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getConditionalPerformerType_DataPath()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='dataPath'"
    * @generated
    */
   String getDataPath();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#getDataPath <em>Data Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Data Path</em>' attribute.
    * @see #getDataPath()
    * @generated
    */
   void setDataPath(String value);

   /**
    * Returns the value of the '<em><b>Is User</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A boolean that indicates whether this conditional performer is a user or
    *                   not.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Is User</em>' attribute.
    * @see #isSetIsUser()
    * @see #unsetIsUser()
    * @see #setIsUser(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getConditionalPerformerType_IsUser()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='is_user'"
    * @generated
    */
   boolean isIsUser();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#isIsUser <em>Is User</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Is User</em>' attribute.
    * @see #isSetIsUser()
    * @see #unsetIsUser()
    * @see #isIsUser()
    * @generated
    */
   void setIsUser(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#isIsUser <em>Is User</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetIsUser()
    * @see #isIsUser()
    * @see #setIsUser(boolean)
    * @generated
    */
   void unsetIsUser();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#isIsUser <em>Is User</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Is User</em>' attribute is set.
    * @see #unsetIsUser()
    * @see #isIsUser()
    * @see #setIsUser(boolean)
    * @generated
    */
   boolean isSetIsUser();

   /**
    * Returns the value of the '<em><b>Conditional Performer Symbols</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType#getParticipant <em>Participant</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Conditional Performer Symbols</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Conditional Performer Symbols</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getConditionalPerformerType_ConditionalPerformerSymbols()
    * @see org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType#getParticipant
    * @model opposite="participant" transient="true"
    * @generated
    */
   EList<ConditionalPerformerSymbolType> getConditionalPerformerSymbols();

} // ConditionalPerformerType
