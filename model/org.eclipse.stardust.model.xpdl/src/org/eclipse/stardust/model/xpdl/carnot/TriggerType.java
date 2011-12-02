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
 * A representation of the model object '<em><b>Trigger Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TriggerType#getParameterMapping <em>Parameter Mapping</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TriggerType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TriggerType#getStartingEventSymbols <em>Starting Event Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTriggerType()
 * @model extendedMetaData="name='trigger_._type' kind='elementOnly'"
 * @generated
 */
public interface TriggerType extends IIdentifiableModelElement, ITypedElement, IAccessPointOwner
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Parameter Mapping</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The list of parameter mappings.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Parameter Mapping</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTriggerType_ParameterMapping()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='parameterMapping' namespace='##targetNamespace'"
    * @generated
    */
   EList<ParameterMappingType> getParameterMapping();

   /**
    * Returns the value of the '<em><b>Type</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType#getTriggers <em>Triggers</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model id of one of the previously defined triggerType elements.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Type</em>' reference.
    * @see #setType(TriggerTypeType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTriggerType_Type()
    * @see org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType#getTriggers
    * @model opposite="triggers" resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='type'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   TriggerTypeType getType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TriggerType#getType <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' reference.
    * @see #getType()
    * @generated
    */
   void setType(TriggerTypeType value);

   /**
    * Returns the value of the '<em><b>Starting Event Symbols</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol#getTrigger <em>Trigger</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Starting Event Symbols</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Starting Event Symbols</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTriggerType_StartingEventSymbols()
    * @see org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol#getTrigger
    * @model opposite="trigger" transient="true"
    * @generated
    */
   EList<StartEventSymbol> getStartingEventSymbols();

} // TriggerType
