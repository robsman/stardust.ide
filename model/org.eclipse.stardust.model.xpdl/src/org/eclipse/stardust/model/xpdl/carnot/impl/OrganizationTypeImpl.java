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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Organization Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getPerformedSwimlanes <em>Performed Swimlanes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getParticipantAssociations <em>Participant Associations</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getOrganizationSymbols <em>Organization Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.OrganizationTypeImpl#getTeamLead <em>Team Lead</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrganizationTypeImpl extends EObjectImpl implements OrganizationType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The default value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getElementOid()
    * @generated
    * @ordered
    */
   protected static final long ELEMENT_OID_EDEFAULT = 0L;

   /**
    * The cached value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getElementOid()
    * @generated
    * @ordered
    */
   protected long elementOid = ELEMENT_OID_EDEFAULT;

   /**
    * This is true if the Element Oid attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean elementOidESet;

   /**
    * The default value of the '{@link #getId() <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getId()
    * @generated
    * @ordered
    */
   protected static final String ID_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getId()
    * @generated
    * @ordered
    */
   protected String id = ID_EDEFAULT;

   /**
    * This is true if the Id attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean idESet;

   /**
    * The default value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected static final String NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected String name = NAME_EDEFAULT;

   /**
    * This is true if the Name attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean nameESet;

   /**
    * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAttribute()
    * @generated
    * @ordered
    */
   protected EList<AttributeType> attribute;

   /**
    * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDescription()
    * @generated
    * @ordered
    */
   protected DescriptionType description;

   /**
    * The cached value of the '{@link #getPerformedActivities() <em>Performed Activities</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPerformedActivities()
    * @generated
    * @ordered
    */
   protected EList<ActivityType> performedActivities;

   /**
    * The cached value of the '{@link #getPerformedSwimlanes() <em>Performed Swimlanes</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPerformedSwimlanes()
    * @generated
    * @ordered
    */
   protected EList<ISwimlaneSymbol> performedSwimlanes;

   /**
    * The cached value of the '{@link #getParticipantAssociations() <em>Participant Associations</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParticipantAssociations()
    * @generated
    * @ordered
    */
   protected EList<ParticipantType> participantAssociations;

   /**
    * The cached value of the '{@link #getParticipant() <em>Participant</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParticipant()
    * @generated
    * @ordered
    */
   protected EList<ParticipantType> participant;

   /**
    * The cached value of the '{@link #getOrganizationSymbols() <em>Organization Symbols</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOrganizationSymbols()
    * @generated
    * @ordered
    */
   protected EList<OrganizationSymbolType> organizationSymbols;

   /**
    * The cached value of the '{@link #getTeamLead() <em>Team Lead</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTeamLead()
    * @generated
    * @ordered
    */
   protected RoleType teamLead;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected OrganizationTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.ORGANIZATION_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public long getElementOid()
   {
      return elementOid;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setElementOid(long newElementOid)
   {
      long oldElementOid = elementOid;
      elementOid = newElementOid;
      boolean oldElementOidESet = elementOidESet;
      elementOidESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetElementOid()
   {
      long oldElementOid = elementOid;
      boolean oldElementOidESet = elementOidESet;
      elementOid = ELEMENT_OID_EDEFAULT;
      elementOidESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetElementOid()
   {
      return elementOidESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DescriptionType getDescription()
   {
      return description;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetDescription(DescriptionType newDescription, NotificationChain msgs)
   {
      DescriptionType oldDescription = description;
      description = newDescription;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__DESCRIPTION, oldDescription, newDescription);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setDescription(DescriptionType newDescription)
   {
      if (newDescription != description)
      {
         NotificationChain msgs = null;
         if (description != null)
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ORGANIZATION_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ORGANIZATION_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__DESCRIPTION, newDescription, newDescription));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ActivityType> getPerformedActivities()
   {
      if (performedActivities == null)
      {
         performedActivities = new EObjectWithInverseResolvingEList<ActivityType>(ActivityType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES, CarnotWorkflowModelPackage.ACTIVITY_TYPE__PERFORMER);
      }
      return performedActivities;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ISwimlaneSymbol> getPerformedSwimlanes()
   {
      if (performedSwimlanes == null)
      {
         performedSwimlanes = new EObjectWithInverseResolvingEList<ISwimlaneSymbol>(ISwimlaneSymbol.class, this, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES, CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__PARTICIPANT);
      }
      return performedSwimlanes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ParticipantType> getParticipantAssociations()
   {
      if (participantAssociations == null)
      {
         participantAssociations = new EObjectWithInverseResolvingEList<ParticipantType>(ParticipantType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS, CarnotWorkflowModelPackage.PARTICIPANT_TYPE__PARTICIPANT);
      }
      return participantAssociations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<AttributeType> getAttribute()
   {
      if (attribute == null)
      {
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ParticipantType> getParticipant()
   {
      if (participant == null)
      {
         participant = new EObjectContainmentEList<ParticipantType>(ParticipantType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT);
      }
      return participant;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<OrganizationSymbolType> getOrganizationSymbols()
   {
      if (organizationSymbols == null)
      {
         organizationSymbols = new EObjectWithInverseResolvingEList<OrganizationSymbolType>(OrganizationSymbolType.class, this, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS, CarnotWorkflowModelPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION);
      }
      return organizationSymbols;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public RoleType getTeamLead()
   {
      return teamLead;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetTeamLead(RoleType newTeamLead, NotificationChain msgs)
   {
      RoleType oldTeamLead = teamLead;
      teamLead = newTeamLead;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__TEAM_LEAD, oldTeamLead, newTeamLead);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTeamLead(RoleType newTeamLead)
   {
      if (newTeamLead != teamLead)
      {
         NotificationChain msgs = null;
         if (teamLead != null)
            msgs = ((InternalEObject)teamLead).eInverseRemove(this, CarnotWorkflowModelPackage.ROLE_TYPE__TEAMS, RoleType.class, msgs);
         if (newTeamLead != null)
            msgs = ((InternalEObject)newTeamLead).eInverseAdd(this, CarnotWorkflowModelPackage.ROLE_TYPE__TEAMS, RoleType.class, msgs);
         msgs = basicSetTeamLead(newTeamLead, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__TEAM_LEAD, newTeamLead, newTeamLead));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getSymbols()
   {
      return getOrganizationSymbols();
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
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedActivities()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedSwimlanes()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getParticipantAssociations()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getOrganizationSymbols()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__TEAM_LEAD:
            if (teamLead != null)
               msgs = ((InternalEObject)teamLead).eInverseRemove(this, CarnotWorkflowModelPackage.ROLE_TYPE__TEAMS, RoleType.class, msgs);
            return basicSetTeamLead((RoleType)otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
            return ((InternalEList<?>)getPerformedActivities()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
            return ((InternalEList<?>)getPerformedSwimlanes()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS:
            return ((InternalEList<?>)getParticipantAssociations()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT:
            return ((InternalEList<?>)getParticipant()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
            return ((InternalEList<?>)getOrganizationSymbols()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__TEAM_LEAD:
            return basicSetTeamLead(null, msgs);
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
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
            return getPerformedActivities();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
            return getPerformedSwimlanes();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS:
            return getParticipantAssociations();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT:
            return getParticipant();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
            return getOrganizationSymbols();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__TEAM_LEAD:
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
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
            getPerformedActivities().clear();
            getPerformedActivities().addAll((Collection<? extends ActivityType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
            getPerformedSwimlanes().clear();
            getPerformedSwimlanes().addAll((Collection<? extends ISwimlaneSymbol>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT:
            getParticipant().clear();
            getParticipant().addAll((Collection<? extends ParticipantType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
            getOrganizationSymbols().clear();
            getOrganizationSymbols().addAll((Collection<? extends OrganizationSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__TEAM_LEAD:
            setTeamLead((RoleType)newValue);
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
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
            getPerformedActivities().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
            getPerformedSwimlanes().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT:
            getParticipant().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
            getOrganizationSymbols().clear();
            return;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__TEAM_LEAD:
            setTeamLead((RoleType)null);
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
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
            return performedActivities != null && !performedActivities.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
            return performedSwimlanes != null && !performedSwimlanes.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS:
            return participantAssociations != null && !participantAssociations.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__PARTICIPANT:
            return participant != null && !participant.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
            return organizationSymbols != null && !organizationSymbols.isEmpty();
         case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__TEAM_LEAD:
            return teamLead != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getId()
   {
      return id;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setId(String newId)
   {
      String oldId = id;
      id = newId;
      boolean oldIdESet = idESet;
      idESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ID, oldId, id, !oldIdESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetId()
   {
      String oldId = id;
      boolean oldIdESet = idESet;
      id = ID_EDEFAULT;
      idESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetId()
   {
      return idESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getName()
   {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setName(String newName)
   {
      String oldName = name;
      name = newName;
      boolean oldNameESet = nameESet;
      nameESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__NAME, oldName, name, !oldNameESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetName()
   {
      String oldName = name;
      boolean oldNameESet = nameESet;
      name = NAME_EDEFAULT;
      nameESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ORGANIZATION_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetName()
   {
      return nameESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
   {
      if (baseClass == IIdentifiableElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
            default: return -1;
         }
      }
      return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
   {
      if (baseClass == IIdentifiableElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.ORGANIZATION_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.ORGANIZATION_TYPE__ATTRIBUTE;
            default: return -1;
         }
      }
      return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (elementOid: "); //$NON-NLS-1$
      if (elementOidESet) result.append(elementOid); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", id: "); //$NON-NLS-1$
      if (idESet) result.append(id); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", name: "); //$NON-NLS-1$
      if (nameESet) result.append(name); else result.append("<unset>"); //$NON-NLS-1$
      result.append(')');
      return result.toString();
   }

} //OrganizationTypeImpl
