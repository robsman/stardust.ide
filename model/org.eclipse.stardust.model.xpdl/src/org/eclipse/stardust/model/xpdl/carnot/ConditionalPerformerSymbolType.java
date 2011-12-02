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
 * A representation of the model object '<em><b>Conditional Performer Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType#getParticipant <em>Participant</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getConditionalPerformerSymbolType()
 * @model extendedMetaData="name='conditionalPerformerSymbol_._type' kind='empty'"
 * @generated
 */
public interface ConditionalPerformerSymbolType extends IModelParticipantSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Participant</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#getConditionalPerformerSymbols <em>Conditional Performer Symbols</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The id of the corresponding activity.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Participant</em>' reference.
    * @see #setParticipant(ConditionalPerformerType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getConditionalPerformerSymbolType_Participant()
    * @see org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType#getConditionalPerformerSymbols
    * @model opposite="conditionalPerformerSymbols" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='refer'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   ConditionalPerformerType getParticipant();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType#getParticipant <em>Participant</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Participant</em>' reference.
    * @see #getParticipant()
    * @generated
    */
   void setParticipant(ConditionalPerformerType value);

} // ConditionalPerformerSymbolType
