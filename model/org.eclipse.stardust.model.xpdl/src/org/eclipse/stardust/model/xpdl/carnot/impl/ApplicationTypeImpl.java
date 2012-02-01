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
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getAccessPoint <em>Access Point</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#isInteractive <em>Interactive</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getExecutedActivities <em>Executed Activities</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl#getApplicationSymbols <em>Application Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationTypeImpl extends EObjectImpl implements ApplicationType
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
    * The cached value of the '{@link #getAccessPoint() <em>Access Point</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAccessPoint()
    * @generated
    * @ordered
    */
   protected EList<AccessPointType> accessPoint;

   /**
    * The cached value of the '{@link #getContext() <em>Context</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getContext()
    * @generated
    * @ordered
    */
   protected EList<ContextType> context;

   /**
    * The default value of the '{@link #isInteractive() <em>Interactive</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isInteractive()
    * @generated
    * @ordered
    */
   protected static final boolean INTERACTIVE_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isInteractive() <em>Interactive</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isInteractive()
    * @generated
    * @ordered
    */
   protected boolean interactive = INTERACTIVE_EDEFAULT;

   /**
    * The cached value of the '{@link #getType() <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
   protected ApplicationTypeType type;

   /**
    * The cached value of the '{@link #getExecutedActivities() <em>Executed Activities</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExecutedActivities()
    * @generated
    * @ordered
    */
   protected EList<ActivityType> executedActivities;

   /**
    * The cached value of the '{@link #getApplicationSymbols() <em>Application Symbols</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getApplicationSymbols()
    * @generated
    * @ordered
    */
   protected EList<ApplicationSymbolType> applicationSymbols;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ApplicationTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.APPLICATION_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.APPLICATION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION, newDescription, newDescription));
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.APPLICATION_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ContextType> getContext()
   {
      if (context == null)
      {
         context = new EObjectContainmentEList<ContextType>(ContextType.class, this, CarnotWorkflowModelPackage.APPLICATION_TYPE__CONTEXT);
      }
      return context;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<AccessPointType> getAccessPoint()
   {
      if (accessPoint == null)
      {
         accessPoint = new EObjectContainmentEList<AccessPointType>(AccessPointType.class, this, CarnotWorkflowModelPackage.APPLICATION_TYPE__ACCESS_POINT);
      }
      return accessPoint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isInteractive()
   {
      return interactive;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setInteractive(boolean newInteractive)
   {
      boolean oldInteractive = interactive;
      interactive = newInteractive;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE__INTERACTIVE, oldInteractive, interactive));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.APPLICATION_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.APPLICATION_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
   public ApplicationTypeType getType()
   {
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetType(ApplicationTypeType newType, NotificationChain msgs)
   {
      ApplicationTypeType oldType = type;
      type = newType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE, oldType, newType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setType(ApplicationTypeType newType)
   {
      if (newType != type)
      {
         NotificationChain msgs = null;
         if (type != null)
            msgs = ((InternalEObject)type).eInverseRemove(this, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS, ApplicationTypeType.class, msgs);
         if (newType != null)
            msgs = ((InternalEObject)newType).eInverseAdd(this, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS, ApplicationTypeType.class, msgs);
         msgs = basicSetType(newType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE, newType, newType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ActivityType> getExecutedActivities()
   {
      if (executedActivities == null)
      {
         executedActivities = new EObjectWithInverseResolvingEList<ActivityType>(ActivityType.class, this, CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES, CarnotWorkflowModelPackage.ACTIVITY_TYPE__APPLICATION);
      }
      return executedActivities;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ApplicationSymbolType> getApplicationSymbols()
   {
      if (applicationSymbols == null)
      {
         applicationSymbols = new EObjectWithInverseResolvingEList<ApplicationSymbolType>(ApplicationSymbolType.class, this, CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS, CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__APPLICATION);
      }
      return applicationSymbols;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getSymbols()
   {
      return getApplicationSymbols();
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE:
            if (type != null)
               msgs = ((InternalEObject)type).eInverseRemove(this, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS, ApplicationTypeType.class, msgs);
            return basicSetType((ApplicationTypeType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecutedActivities()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getApplicationSymbols()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ACCESS_POINT:
            return ((InternalEList<?>)getAccessPoint()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__CONTEXT:
            return ((InternalEList<?>)getContext()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE:
            return basicSetType(null, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES:
            return ((InternalEList<?>)getExecutedActivities()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS:
            return ((InternalEList<?>)getApplicationSymbols()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ACCESS_POINT:
            return getAccessPoint();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__CONTEXT:
            return getContext();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__INTERACTIVE:
            return isInteractive();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE:
            return getType();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES:
            return getExecutedActivities();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS:
            return getApplicationSymbols();
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ACCESS_POINT:
            getAccessPoint().clear();
            getAccessPoint().addAll((Collection<? extends AccessPointType>)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__CONTEXT:
            getContext().clear();
            getContext().addAll((Collection<? extends ContextType>)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__INTERACTIVE:
            setInteractive((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE:
            setType((ApplicationTypeType)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES:
            getExecutedActivities().clear();
            getExecutedActivities().addAll((Collection<? extends ActivityType>)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS:
            getApplicationSymbols().clear();
            getApplicationSymbols().addAll((Collection<? extends ApplicationSymbolType>)newValue);
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ACCESS_POINT:
            getAccessPoint().clear();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__CONTEXT:
            getContext().clear();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__INTERACTIVE:
            setInteractive(INTERACTIVE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE:
            setType((ApplicationTypeType)null);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES:
            getExecutedActivities().clear();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS:
            getApplicationSymbols().clear();
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__ACCESS_POINT:
            return accessPoint != null && !accessPoint.isEmpty();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__CONTEXT:
            return context != null && !context.isEmpty();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__INTERACTIVE:
            return interactive != INTERACTIVE_EDEFAULT;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE:
            return type != null;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES:
            return executedActivities != null && !executedActivities.isEmpty();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS:
            return applicationSymbols != null && !applicationSymbols.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public IMetaType getMetaType()
   {
      return getType();
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
            case CarnotWorkflowModelPackage.APPLICATION_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.APPLICATION_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.APPLICATION_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
            default: return -1;
         }
      }
      if (baseClass == ITypedElement.class)
      {
         switch (derivedFeatureID)
         {
            default: return -1;
         }
      }
      if (baseClass == IAccessPointOwner.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.APPLICATION_TYPE__ACCESS_POINT: return CarnotWorkflowModelPackage.IACCESS_POINT_OWNER__ACCESS_POINT;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.APPLICATION_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.APPLICATION_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.APPLICATION_TYPE__ATTRIBUTE;
            default: return -1;
         }
      }
      if (baseClass == ITypedElement.class)
      {
         switch (baseFeatureID)
         {
            default: return -1;
         }
      }
      if (baseClass == IAccessPointOwner.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IACCESS_POINT_OWNER__ACCESS_POINT: return CarnotWorkflowModelPackage.APPLICATION_TYPE__ACCESS_POINT;
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
      result.append(", interactive: "); //$NON-NLS-1$
      result.append(interactive);
      result.append(')');
      return result.toString();
   }

} //ApplicationTypeImpl
