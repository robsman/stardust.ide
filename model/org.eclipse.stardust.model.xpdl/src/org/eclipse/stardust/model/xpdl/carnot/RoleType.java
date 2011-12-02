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
 * A representation of the model object '<em><b>Role Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.RoleType#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.RoleType#getTeams <em>Teams</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.RoleType#getRoleSymbols <em>Role Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRoleType()
 * @model extendedMetaData="name='role_._type' kind='elementOnly'"
 * @generated
 */
public interface RoleType extends IModelParticipant
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Cardinality</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The cardinality of the role. Valid values are "*:1" (to one) |
    *                   "*:N" (to many)
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Cardinality</em>' attribute.
    * @see #setCardinality(int)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRoleType_Cardinality()
    * @model unique="false"
    *        extendedMetaData="kind='attribute' name='cardinality'"
    * @generated
    */
   int getCardinality();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.RoleType#getCardinality <em>Cardinality</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Cardinality</em>' attribute.
    * @see #getCardinality()
    * @generated
    */
   void setCardinality(int value);

   /**
    * Returns the value of the '<em><b>Teams</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.OrganizationType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationType#getTeamLead <em>Team Lead</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Teams</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Teams</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRoleType_Teams()
    * @see org.eclipse.stardust.model.xpdl.carnot.OrganizationType#getTeamLead
    * @model opposite="teamLead" transient="true"
    * @generated
    */
   EList<OrganizationType> getTeams();

   /**
    * Returns the value of the '<em><b>Role Symbols</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getRole <em>Role</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Role Symbols</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Role Symbols</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRoleType_RoleSymbols()
    * @see org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType#getRole
    * @model opposite="role" transient="true"
    * @generated
    */
   EList<RoleSymbolType> getRoleSymbols();

} // RoleType
