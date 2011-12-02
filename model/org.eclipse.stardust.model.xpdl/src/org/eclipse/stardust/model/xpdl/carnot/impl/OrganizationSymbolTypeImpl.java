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
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Organization Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationSymbolTypeImpl#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationSymbolTypeImpl#getTriggeredEvents <em>Triggered Events</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationSymbolTypeImpl#getOrganization <em>Organization</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationSymbolTypeImpl#getSuperOrganizations <em>Super Organizations</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationSymbolTypeImpl#getSubOrganizations <em>Sub Organizations</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationSymbolTypeImpl#getMemberRoles <em>Member Roles</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationSymbolTypeImpl#getTeamLead <em>Team Lead</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrganizationSymbolTypeImpl extends IModelElementNodeSymbolImpl implements OrganizationSymbolType
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
    * The cached value of the '{@link #getOrganization() <em>Organization</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOrganization()
    * @generated
    * @ordered
    */
   protected OrganizationType organization;

   /**
    * The cached value of the '{@link #getSuperOrganizations() <em>Super Organizations</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSuperOrganizations()
    * @generated
    * @ordered
    */
   protected EList<PartOfConnectionType> superOrganizations;

   /**
    * The cached value of the '{@link #getSubOrganizations() <em>Sub Organizations</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSubOrganizations()
    * @generated
    * @ordered
    */
   protected EList<PartOfConnectionType> subOrganizations;

   /**
    * The cached value of the '{@link #getMemberRoles() <em>Member Roles</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMemberRoles()
    * @generated
    * @ordered
    */
   protected EList<WorksForConnectionType> memberRoles;

   /**
    * The cached value of the '{@link #getTeamLead() <em>Team Lead</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTeamLead()
    * @generated
    * @ordered
    */
   protected EList<TeamLeadConnectionType> teamLead;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected OrganizationSymbolTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.ORGANIZATION_SYMBOL_TYPE;
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
         performedActivities = new EObjectWithInverseEList<PerformsConnectionType>(PerformsConnectionType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__PERFORMED_ACTIVITIES, CarnotWorkflowModelPackage.PERFORMS_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
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
         triggeredEvents = new EObjectWithInverseEList<TriggersConnectionType>(TriggersConnectionType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TRIGGERED_EVENTS, CarnotWorkflowModelPackage.TRIGGERS_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
      }
      return triggeredEvents;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public OrganizationType getOrganization()
   {
      return organization;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetOrganization(OrganizationType newOrganization, NotificationChain msgs)
   {
      OrganizationType oldOrganization = organization;
      organization = newOrganization;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION, oldOrganization, newOrganization);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setOrganization(OrganizationType newOrganization)
   {
      if (newOrganization != organization)
      {
         NotificationChain msgs = null;
         if (organization != null)
            msgs = ((InternalEObject)organization).eInverseRemove(this, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS, OrganizationType.class, msgs);
         if (newOrganization != null)
            msgs = ((InternalEObject)newOrganization).eInverseAdd(this, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS, OrganizationType.class, msgs);
         msgs = basicSetOrganization(newOrganization, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION, newOrganization, newOrganization));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PartOfConnectionType> getSuperOrganizations()
   {
      if (superOrganizations == null)
      {
         superOrganizations = new EObjectWithInverseResolvingEList<PartOfConnectionType>(PartOfConnectionType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUPER_ORGANIZATIONS, CarnotWorkflowModelPackage.PART_OF_CONNECTION_TYPE__SUBORGANIZATION_SYMBOL);
      }
      return superOrganizations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PartOfConnectionType> getSubOrganizations()
   {
      if (subOrganizations == null)
      {
         subOrganizations = new EObjectWithInverseResolvingEList<PartOfConnectionType>(PartOfConnectionType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUB_ORGANIZATIONS, CarnotWorkflowModelPackage.PART_OF_CONNECTION_TYPE__ORGANIZATION_SYMBOL);
      }
      return subOrganizations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<WorksForConnectionType> getMemberRoles()
   {
      if (memberRoles == null)
      {
         memberRoles = new EObjectWithInverseEList<WorksForConnectionType>(WorksForConnectionType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__MEMBER_ROLES, CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__ORGANIZATION_SYMBOL);
      }
      return memberRoles;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TeamLeadConnectionType> getTeamLead()
   {
      if (teamLead == null)
      {
         teamLead = new EObjectWithInverseResolvingEList<TeamLeadConnectionType>(TeamLeadConnectionType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD, CarnotWorkflowModelPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL);
      }
      return teamLead;
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
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedActivities()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TRIGGERED_EVENTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getTriggeredEvents()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION:
            if (organization != null)
               msgs = ((InternalEObject)organization).eInverseRemove(this, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS, OrganizationType.class, msgs);
            return basicSetOrganization((OrganizationType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUPER_ORGANIZATIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getSuperOrganizations()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUB_ORGANIZATIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubOrganizations()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__MEMBER_ROLES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getMemberRoles()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getTeamLead()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            return ((InternalEList<?>)getPerformedActivities()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TRIGGERED_EVENTS:
            return ((InternalEList<?>)getTriggeredEvents()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION:
            return basicSetOrganization(null, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUPER_ORGANIZATIONS:
            return ((InternalEList<?>)getSuperOrganizations()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUB_ORGANIZATIONS:
            return ((InternalEList<?>)getSubOrganizations()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__MEMBER_ROLES:
            return ((InternalEList<?>)getMemberRoles()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD:
            return ((InternalEList<?>)getTeamLead()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            return getPerformedActivities();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TRIGGERED_EVENTS:
            return getTriggeredEvents();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION:
            return getOrganization();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUPER_ORGANIZATIONS:
            return getSuperOrganizations();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUB_ORGANIZATIONS:
            return getSubOrganizations();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__MEMBER_ROLES:
            return getMemberRoles();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD:
            return getTeamLead();
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
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            getPerformedActivities().clear();
            getPerformedActivities().addAll((Collection<? extends PerformsConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TRIGGERED_EVENTS:
            getTriggeredEvents().clear();
            getTriggeredEvents().addAll((Collection<? extends TriggersConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION:
            setOrganization((OrganizationType)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUPER_ORGANIZATIONS:
            getSuperOrganizations().clear();
            getSuperOrganizations().addAll((Collection<? extends PartOfConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUB_ORGANIZATIONS:
            getSubOrganizations().clear();
            getSubOrganizations().addAll((Collection<? extends PartOfConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__MEMBER_ROLES:
            getMemberRoles().clear();
            getMemberRoles().addAll((Collection<? extends WorksForConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD:
            getTeamLead().clear();
            getTeamLead().addAll((Collection<? extends TeamLeadConnectionType>)newValue);
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
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            getPerformedActivities().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TRIGGERED_EVENTS:
            getTriggeredEvents().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION:
            setOrganization((OrganizationType)null);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUPER_ORGANIZATIONS:
            getSuperOrganizations().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUB_ORGANIZATIONS:
            getSubOrganizations().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__MEMBER_ROLES:
            getMemberRoles().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD:
            getTeamLead().clear();
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
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
            return performedActivities != null && !performedActivities.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TRIGGERED_EVENTS:
            return triggeredEvents != null && !triggeredEvents.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION:
            return organization != null;
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUPER_ORGANIZATIONS:
            return superOrganizations != null && !superOrganizations.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__SUB_ORGANIZATIONS:
            return subOrganizations != null && !subOrganizations.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__MEMBER_ROLES:
            return memberRoles != null && !memberRoles.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD:
            return teamLead != null && !teamLead.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * @generated NOT
    */
   public IIdentifiableModelElement getModelElement()
   {
      return getOrganization();
   }

   /**
    * @generated NOT
    */
   public void setModelElement(IIdentifiableModelElement element)
   {
      setOrganization((OrganizationType) element);
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
            CarnotWorkflowModelPackage.eINSTANCE.getOrganizationSymbolType_SubOrganizations(),
            CarnotWorkflowModelPackage.eINSTANCE.getOrganizationSymbolType_MemberRoles(),
            CarnotWorkflowModelPackage.eINSTANCE.getOrganizationSymbolType_TeamLead()});
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getOutConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            // refering from is listed due to backward compatibility issues with old DefDesk
            CarnotWorkflowModelPackage.eINSTANCE.getIModelParticipantSymbol_PerformedActivities(),
            CarnotWorkflowModelPackage.eINSTANCE.getOrganizationSymbolType_SuperOrganizations(),
            CarnotWorkflowModelPackage.eINSTANCE.getIModelParticipantSymbol_TriggeredEvents()});
   }

} //OrganizationSymbolTypeImpl
