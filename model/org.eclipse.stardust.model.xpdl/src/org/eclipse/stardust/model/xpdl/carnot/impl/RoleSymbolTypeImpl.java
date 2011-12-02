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
package org.eclipse.stardust.model.xpdl.carnot.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.RoleSymbolTypeImpl#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.RoleSymbolTypeImpl#getTriggeredEvents <em>Triggered Events</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.RoleSymbolTypeImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.RoleSymbolTypeImpl#getOrganizationMemberships <em>Organization Memberships</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.RoleSymbolTypeImpl#getTeams <em>Teams</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleSymbolTypeImpl extends IModelElementNodeSymbolImpl implements RoleSymbolType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The cached value of the '{@link #getPerformedActivities() <em>Performed Activities</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPerformedActivities()
    * @generated
    * @ordered
    */
   protected EList<PerformsConnectionType> performedActivities;

   /**
    * The cached value of the '{@link #getTriggeredEvents() <em>Triggered Events</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTriggeredEvents()
    * @generated
    * @ordered
    */
   protected EList<TriggersConnectionType> triggeredEvents;

   /**
    * The cached value of the '{@link #getRole() <em>Role</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getRole()
    * @generated
    * @ordered
    */
   protected RoleType role;

   /**
    * The cached value of the '{@link #getOrganizationMemberships() <em>Organization Memberships</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOrganizationMemberships()
    * @generated
    * @ordered
    */
   protected EList<WorksForConnectionType> organizationMemberships;

   /**
    * The cached value of the '{@link #getTeams() <em>Teams</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTeams()
    * @generated
    * @ordered
    */
   protected EList<TeamLeadConnectionType> teams;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected RoleSymbolTypeImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return CarnotWorkflowModelPackage.Literals.ROLE_SYMBOL_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PerformsConnectionType> getPerformedActivities()
   {
      if (performedActivities == null)
      {
         performedActivities = new EObjectWithInverseEList<PerformsConnectionType>(PerformsConnectionType.class, this, CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES, CarnotWorkflowModelPackage.PERFORMS_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
      }
      return performedActivities;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TriggersConnectionType> getTriggeredEvents()
   {
      if (triggeredEvents == null)
      {
         triggeredEvents = new EObjectWithInverseEList<TriggersConnectionType>(TriggersConnectionType.class, this, CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS, CarnotWorkflowModelPackage.TRIGGERS_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
      }
      return triggeredEvents;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public RoleType getRole()
   {
      return role;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetRole(RoleType newRole, NotificationChain msgs)
   {
      RoleType oldRole = role;
      role = newRole;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ROLE, oldRole, newRole);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setRole(RoleType newRole)
   {
      if (newRole != role)
      {
         NotificationChain msgs = null;
         if (role != null)
            msgs = ((InternalEObject)role).eInverseRemove(this, CarnotWorkflowModelPackage.ROLE_TYPE__ROLE_SYMBOLS, RoleType.class, msgs);
         if (newRole != null)
            msgs = ((InternalEObject)newRole).eInverseAdd(this, CarnotWorkflowModelPackage.ROLE_TYPE__ROLE_SYMBOLS, RoleType.class, msgs);
         msgs = basicSetRole(newRole, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ROLE, newRole, newRole));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<WorksForConnectionType> getOrganizationMemberships()
   {
      if (organizationMemberships == null)
      {
         organizationMemberships = new EObjectWithInverseEList<WorksForConnectionType>(WorksForConnectionType.class, this, CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS, CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
      }
      return organizationMemberships;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TeamLeadConnectionType> getTeams()
   {
      if (teams == null)
      {
         teams = new EObjectWithInverseResolvingEList<TeamLeadConnectionType>(TeamLeadConnectionType.class, this, CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TEAMS, CarnotWorkflowModelPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL);
      }
      return teams;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedActivities()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getTriggeredEvents()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ROLE:
            if (role != null)
               msgs = ((InternalEObject)role).eInverseRemove(this, CarnotWorkflowModelPackage.ROLE_TYPE__ROLE_SYMBOLS, RoleType.class, msgs);
            return basicSetRole((RoleType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getOrganizationMemberships()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TEAMS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getTeams()).basicAdd(otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            return ((InternalEList<?>)getPerformedActivities()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
            return ((InternalEList<?>)getTriggeredEvents()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ROLE:
            return basicSetRole(null, msgs);
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
            return ((InternalEList<?>)getOrganizationMemberships()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TEAMS:
            return ((InternalEList<?>)getTeams()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            return getPerformedActivities();
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
            return getTriggeredEvents();
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ROLE:
            return getRole();
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
            return getOrganizationMemberships();
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TEAMS:
            return getTeams();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            getPerformedActivities().clear();
            getPerformedActivities().addAll((Collection<? extends PerformsConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
            getTriggeredEvents().clear();
            getTriggeredEvents().addAll((Collection<? extends TriggersConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ROLE:
            setRole((RoleType)newValue);
            return;
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
            getOrganizationMemberships().clear();
            getOrganizationMemberships().addAll((Collection<? extends WorksForConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TEAMS:
            getTeams().clear();
            getTeams().addAll((Collection<? extends TeamLeadConnectionType>)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            getPerformedActivities().clear();
            return;
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
            getTriggeredEvents().clear();
            return;
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ROLE:
            setRole((RoleType)null);
            return;
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
            getOrganizationMemberships().clear();
            return;
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TEAMS:
            getTeams().clear();
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            return performedActivities != null && !performedActivities.isEmpty();
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS:
            return triggeredEvents != null && !triggeredEvents.isEmpty();
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ROLE:
            return role != null;
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS:
            return organizationMemberships != null && !organizationMemberships.isEmpty();
         case CarnotWorkflowModelPackage.ROLE_SYMBOL_TYPE__TEAMS:
            return teams != null && !teams.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * @generated NOT
    */
   public IIdentifiableModelElement getModelElement()
   {
      return getRole();
   }

   /**
    * @generated NOT
    */
   public void setModelElement(IIdentifiableModelElement element)
   {
      setRole((RoleType) element);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getInConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIGraphicalObject_ReferingToConnections(),
      });
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getOutConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIModelParticipantSymbol_PerformedActivities(),
            CarnotWorkflowModelPackage.eINSTANCE.getRoleSymbolType_OrganizationMemberships(),
            CarnotWorkflowModelPackage.eINSTANCE.getRoleSymbolType_Teams(),
            CarnotWorkflowModelPackage.eINSTANCE.getIModelParticipantSymbol_TriggeredEvents()
      });
   }

} //RoleSymbolTypeImpl
