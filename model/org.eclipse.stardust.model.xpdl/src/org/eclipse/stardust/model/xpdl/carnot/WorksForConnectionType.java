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
 * A representation of the model object '<em><b>Works For Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType#getOrganizationSymbol <em>Organization Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType#getParticipantSymbol <em>Participant Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getWorksForConnectionType()
 * @model extendedMetaData="name='worksForConnection_._type' kind='empty'"
 * @generated
 */
public interface WorksForConnectionType extends IConnectionSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Organization Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getMemberRoles <em>Member Roles</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the organization symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Organization Symbol</em>' reference.
    * @see #setOrganizationSymbol(OrganizationSymbolType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getWorksForConnectionType_OrganizationSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getMemberRoles
    * @model opposite="memberRoles" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='organizationSymbol'"
    * @generated
    */
   OrganizationSymbolType getOrganizationSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType#getOrganizationSymbol <em>Organization Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Organization Symbol</em>' reference.
    * @see #getOrganizationSymbol()
    * @generated
    */
   void setOrganizationSymbol(OrganizationSymbolType value);

   /**
    * Returns the value of the '<em><b>Participant Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getOrganizationMemberships <em>Organization Memberships</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the participant symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Participant Symbol</em>' reference.
    * @see #setParticipantSymbol(RoleSymbolType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getWorksForConnectionType_ParticipantSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getOrganizationMemberships
    * @model opposite="organizationMemberships" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='participantSymbol'"
    * @generated
    */
   RoleSymbolType getParticipantSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType#getParticipantSymbol <em>Participant Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Participant Symbol</em>' reference.
    * @see #getParticipantSymbol()
    * @generated
    */
   void setParticipantSymbol(RoleSymbolType value);

} // WorksForConnectionType
