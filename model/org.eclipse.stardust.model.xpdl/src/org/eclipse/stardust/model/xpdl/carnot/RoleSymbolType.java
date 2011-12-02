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
 * A representation of the model object '<em><b>Role Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getOrganizationMemberships <em>Organization Memberships</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getTeams <em>Teams</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRoleSymbolType()
 * @model extendedMetaData="name='roleSymbol_._type' kind='empty'"
 * @generated
 */
public interface RoleSymbolType extends IModelParticipantSymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Role</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.RoleType#getRoleSymbols <em>Role Symbols</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The id of the corresponding activity.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Role</em>' reference.
    * @see #setRole(RoleType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRoleSymbolType_Role()
    * @see org.eclipse.stardust.model.xpdl.carnot.RoleType#getRoleSymbols
    * @model opposite="roleSymbols" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='refer'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   RoleType getRole();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getRole <em>Role</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Role</em>' reference.
    * @see #getRole()
    * @generated
    */
   void setRole(RoleType value);

   /**
    * Returns the value of the '<em><b>Organization Memberships</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType#getParticipantSymbol <em>Participant Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Organization Memberships</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Organization Memberships</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRoleSymbolType_OrganizationMemberships()
    * @see org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType#getParticipantSymbol
    * @model opposite="participantSymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<WorksForConnectionType> getOrganizationMemberships();

   /**
    * Returns the value of the '<em><b>Teams</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType#getTeamLeadSymbol <em>Team Lead Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Teams</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Teams</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRoleSymbolType_Teams()
    * @see org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType#getTeamLeadSymbol
    * @model opposite="teamLeadSymbol" transient="true"
    * @generated
    */
   EList<TeamLeadConnectionType> getTeams();

} // RoleSymbolType
