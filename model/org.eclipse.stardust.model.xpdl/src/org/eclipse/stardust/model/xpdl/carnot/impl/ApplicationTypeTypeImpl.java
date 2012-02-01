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
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#isIsPredefined <em>Is Predefined</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getAccessPointProviderClass <em>Access Point Provider Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getInstanceClass <em>Instance Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#isSynchronous <em>Synchronous</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeTypeImpl#getApplications <em>Applications</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationTypeTypeImpl extends EObjectImpl implements ApplicationTypeType
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
    * The default value of the '{@link #isIsPredefined() <em>Is Predefined</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isIsPredefined()
    * @generated
    * @ordered
    */
   protected static final boolean IS_PREDEFINED_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isIsPredefined() <em>Is Predefined</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isIsPredefined()
    * @generated
    * @ordered
    */
   protected boolean isPredefined = IS_PREDEFINED_EDEFAULT;

   /**
    * This is true if the Is Predefined attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean isPredefinedESet;

   /**
    * The default value of the '{@link #getAccessPointProviderClass() <em>Access Point Provider Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAccessPointProviderClass()
    * @generated
    * @ordered
    */
   protected static final String ACCESS_POINT_PROVIDER_CLASS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getAccessPointProviderClass() <em>Access Point Provider Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAccessPointProviderClass()
    * @generated
    * @ordered
    */
   protected String accessPointProviderClass = ACCESS_POINT_PROVIDER_CLASS_EDEFAULT;

   /**
    * The default value of the '{@link #getInstanceClass() <em>Instance Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInstanceClass()
    * @generated
    * @ordered
    */
   protected static final String INSTANCE_CLASS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getInstanceClass() <em>Instance Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInstanceClass()
    * @generated
    * @ordered
    */
   protected String instanceClass = INSTANCE_CLASS_EDEFAULT;

   /**
    * The default value of the '{@link #getPanelClass() <em>Panel Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPanelClass()
    * @generated
    * @ordered
    */
   protected static final String PANEL_CLASS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getPanelClass() <em>Panel Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPanelClass()
    * @generated
    * @ordered
    */
   protected String panelClass = PANEL_CLASS_EDEFAULT;

   /**
    * The default value of the '{@link #isSynchronous() <em>Synchronous</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSynchronous()
    * @generated
    * @ordered
    */
   protected static final boolean SYNCHRONOUS_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isSynchronous() <em>Synchronous</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSynchronous()
    * @generated
    * @ordered
    */
   protected boolean synchronous = SYNCHRONOUS_EDEFAULT;

   /**
    * This is true if the Synchronous attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean synchronousESet;

   /**
    * The default value of the '{@link #getValidatorClass() <em>Validator Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValidatorClass()
    * @generated
    * @ordered
    */
   protected static final String VALIDATOR_CLASS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getValidatorClass() <em>Validator Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValidatorClass()
    * @generated
    * @ordered
    */
   protected String validatorClass = VALIDATOR_CLASS_EDEFAULT;

   /**
    * The cached value of the '{@link #getApplications() <em>Applications</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getApplications()
    * @generated
    * @ordered
    */
   protected EList<ApplicationType> applications;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ApplicationTypeTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.APPLICATION_TYPE_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__DESCRIPTION, newDescription, newDescription));
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getAccessPointProviderClass()
   {
      return accessPointProviderClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAccessPointProviderClass(String newAccessPointProviderClass)
   {
      String oldAccessPointProviderClass = accessPointProviderClass;
      accessPointProviderClass = newAccessPointProviderClass;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS, oldAccessPointProviderClass, accessPointProviderClass));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
   public String getInstanceClass()
   {
      return instanceClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setInstanceClass(String newInstanceClass)
   {
      String oldInstanceClass = instanceClass;
      instanceClass = newInstanceClass;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__INSTANCE_CLASS, oldInstanceClass, instanceClass));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
   public boolean isIsPredefined()
   {
      return isPredefined;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setIsPredefined(boolean newIsPredefined)
   {
      boolean oldIsPredefined = isPredefined;
      isPredefined = newIsPredefined;
      boolean oldIsPredefinedESet = isPredefinedESet;
      isPredefinedESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, isPredefined, !oldIsPredefinedESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetIsPredefined()
   {
      boolean oldIsPredefined = isPredefined;
      boolean oldIsPredefinedESet = isPredefinedESet;
      isPredefined = IS_PREDEFINED_EDEFAULT;
      isPredefinedESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, IS_PREDEFINED_EDEFAULT, oldIsPredefinedESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetIsPredefined()
   {
      return isPredefinedESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getPanelClass()
   {
      return panelClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPanelClass(String newPanelClass)
   {
      String oldPanelClass = panelClass;
      panelClass = newPanelClass;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__PANEL_CLASS, oldPanelClass, panelClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSynchronous()
   {
      return synchronous;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSynchronous(boolean newSynchronous)
   {
      boolean oldSynchronous = synchronous;
      synchronous = newSynchronous;
      boolean oldSynchronousESet = synchronousESet;
      synchronousESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__SYNCHRONOUS, oldSynchronous, synchronous, !oldSynchronousESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetSynchronous()
   {
      boolean oldSynchronous = synchronous;
      boolean oldSynchronousESet = synchronousESet;
      synchronous = SYNCHRONOUS_EDEFAULT;
      synchronousESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__SYNCHRONOUS, oldSynchronous, SYNCHRONOUS_EDEFAULT, oldSynchronousESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetSynchronous()
   {
      return synchronousESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getValidatorClass()
   {
      return validatorClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setValidatorClass(String newValidatorClass)
   {
      String oldValidatorClass = validatorClass;
      validatorClass = newValidatorClass;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__VALIDATOR_CLASS, oldValidatorClass, validatorClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ApplicationType> getApplications()
   {
      if (applications == null)
      {
         applications = new EObjectWithInverseResolvingEList<ApplicationType>(ApplicationType.class, this, CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS, CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE);
      }
      return applications;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<INodeSymbol> getSymbols()
   {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getApplications()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS:
            return ((InternalEList<?>)getApplications()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__IS_PREDEFINED:
            return isIsPredefined();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS:
            return getAccessPointProviderClass();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__INSTANCE_CLASS:
            return getInstanceClass();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__PANEL_CLASS:
            return getPanelClass();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__SYNCHRONOUS:
            return isSynchronous();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__VALIDATOR_CLASS:
            return getValidatorClass();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS:
            return getApplications();
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__IS_PREDEFINED:
            setIsPredefined((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS:
            setAccessPointProviderClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__INSTANCE_CLASS:
            setInstanceClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__PANEL_CLASS:
            setPanelClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__SYNCHRONOUS:
            setSynchronous((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__VALIDATOR_CLASS:
            setValidatorClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS:
            getApplications().clear();
            getApplications().addAll((Collection<? extends ApplicationType>)newValue);
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__IS_PREDEFINED:
            unsetIsPredefined();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS:
            setAccessPointProviderClass(ACCESS_POINT_PROVIDER_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__INSTANCE_CLASS:
            setInstanceClass(INSTANCE_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__PANEL_CLASS:
            setPanelClass(PANEL_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__SYNCHRONOUS:
            unsetSynchronous();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__VALIDATOR_CLASS:
            setValidatorClass(VALIDATOR_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS:
            getApplications().clear();
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
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__IS_PREDEFINED:
            return isSetIsPredefined();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS:
            return ACCESS_POINT_PROVIDER_CLASS_EDEFAULT == null ? accessPointProviderClass != null : !ACCESS_POINT_PROVIDER_CLASS_EDEFAULT.equals(accessPointProviderClass);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__INSTANCE_CLASS:
            return INSTANCE_CLASS_EDEFAULT == null ? instanceClass != null : !INSTANCE_CLASS_EDEFAULT.equals(instanceClass);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__PANEL_CLASS:
            return PANEL_CLASS_EDEFAULT == null ? panelClass != null : !PANEL_CLASS_EDEFAULT.equals(panelClass);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__SYNCHRONOUS:
            return isSetSynchronous();
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__VALIDATOR_CLASS:
            return VALIDATOR_CLASS_EDEFAULT == null ? validatorClass != null : !VALIDATOR_CLASS_EDEFAULT.equals(validatorClass);
         case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__APPLICATIONS:
            return applications != null && !applications.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public String getExtensionPointId()
   {
      return CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getTypedElements()
   {
      return getApplications();
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
            case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.APPLICATION_TYPE_TYPE__ATTRIBUTE;
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
      result.append(", isPredefined: "); //$NON-NLS-1$
      if (isPredefinedESet) result.append(isPredefined); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", accessPointProviderClass: "); //$NON-NLS-1$
      result.append(accessPointProviderClass);
      result.append(", instanceClass: "); //$NON-NLS-1$
      result.append(instanceClass);
      result.append(", panelClass: "); //$NON-NLS-1$
      result.append(panelClass);
      result.append(", synchronous: "); //$NON-NLS-1$
      if (synchronousESet) result.append(synchronous); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", validatorClass: "); //$NON-NLS-1$
      result.append(validatorClass);
      result.append(')');
      return result.toString();
   }

} //ApplicationTypeTypeImpl
