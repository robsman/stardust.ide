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
 * A representation of the model object '<em><b>Team Lead Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType#getTeamSymbol <em>Team Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType#getTeamLeadSymbol <em>Team Lead Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTeamLeadConnectionType()
 * @model extendedMetaData="name='teamLeadConnection_._type' kind='empty'"
 * @generated
 */
public interface TeamLeadConnectionType extends IConnectionSymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Team Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getTeamLead <em>Team Lead</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the organization symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Team Symbol</em>' reference.
    * @see #setTeamSymbol(OrganizationSymbolType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTeamLeadConnectionType_TeamSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType#getTeamLead
    * @model opposite="teamLead" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='teamSymbol'"
    * @generated
    */
   OrganizationSymbolType getTeamSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType#getTeamSymbol <em>Team Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Team Symbol</em>' reference.
    * @see #getTeamSymbol()
    * @generated
    */
   void setTeamSymbol(OrganizationSymbolType value);

   /**
    * Returns the value of the '<em><b>Team Lead Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getTeams <em>Teams</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the participant symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Team Lead Symbol</em>' reference.
    * @see #setTeamLeadSymbol(RoleSymbolType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTeamLeadConnectionType_TeamLeadSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getTeams
    * @model opposite="teams" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='teamLeadSymbol'"
    * @generated
    */
   RoleSymbolType getTeamLeadSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType#getTeamLeadSymbol <em>Team Lead Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Team Lead Symbol</em>' reference.
    * @see #getTeamLeadSymbol()
    * @generated
    */
   void setTeamLeadSymbol(RoleSymbolType value);

} // TeamLeadConnectionType