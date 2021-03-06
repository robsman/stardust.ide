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
 * A representation of the model object '<em><b>Organization Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getOrganization <em>Organization</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getSuperOrganizations <em>Super Organizations</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getSubOrganizations <em>Sub Organizations</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getMemberRoles <em>Member Roles</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getTeamLead <em>Team Lead</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getOrganizationSymbolType()
 * @model extendedMetaData="name='organizationSymbol_._type' kind='empty'"
 * @generated
 */
public interface OrganizationSymbolType extends IModelParticipantSymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Organization</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationType#getOrganizationSymbols <em>Organization Symbols</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The id of the corresponding activity.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Organization</em>' reference.
    * @see #setOrganization(OrganizationType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getOrganizationSymbolType_Organization()
    * @see org.eclipse.stardust.model.xpdl.carnot.OrganizationType#getOrganizationSymbols
    * @model opposite="organizationSymbols" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='refer'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   OrganizationType getOrganization();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getOrganization <em>Organization</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Organization</em>' reference.
    * @see #getOrganization()
    * @generated
    */
   void setOrganization(OrganizationType value);

   /**
    * Returns the value of the '<em><b>Super Organizations</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType#getSuborganizationSymbol <em>Suborganization Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Super Organizations</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Super Organizations</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getOrganizationSymbolType_SuperOrganizations()
    * @see org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType#getSuborganizationSymbol
    * @model opposite="suborganizationSymbol" transient="true"
    * @generated
    */
   EList<PartOfConnectionType> getSuperOrganizations();

   /**
    * Returns the value of the '<em><b>Sub Organizations</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType#getOrganizationSymbol <em>Organization Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Sub Organizations</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Sub Organizations</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getOrganizationSymbolType_SubOrganizations()
    * @see org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType#getOrganizationSymbol
    * @model opposite="organizationSymbol" transient="true"
    * @generated
    */
   EList<PartOfConnectionType> getSubOrganizations();

   /**
    * Returns the value of the '<em><b>Member Roles</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType#getOrganizationSymbol <em>Organization Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Member Roles</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Member Roles</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getOrganizationSymbolType_MemberRoles()
    * @see org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType#getOrganizationSymbol
    * @model opposite="organizationSymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<WorksForConnectionType> getMemberRoles();

   /**
    * Returns the value of the '<em><b>Team Lead</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType#getTeamSymbol <em>Team Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Team Lead</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Team Lead</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getOrganizationSymbolType_TeamLead()
    * @see org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType#getTeamSymbol
    * @model opposite="teamSymbol" transient="true"
    * @generated
    */
   EList<TeamLeadConnectionType> getTeamLead();

} // OrganizationSymbolType
