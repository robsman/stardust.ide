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
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Condition Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#isIsPredefined <em>Is Predefined</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#isActivityCondition <em>Activity Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getBinderClass <em>Binder Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#isProcessCondition <em>Process Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getPullEventEmitterClass <em>Pull Event Emitter Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getRule <em>Rule</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventConditionTypeTypeImpl#getEventHandlers <em>Event Handlers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventConditionTypeTypeImpl extends EObjectImpl implements EventConditionTypeType
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
    * The default value of the '{@link #isActivityCondition() <em>Activity Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isActivityCondition()
    * @generated
    * @ordered
    */
   protected static final boolean ACTIVITY_CONDITION_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isActivityCondition() <em>Activity Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isActivityCondition()
    * @generated
    * @ordered
    */
   protected boolean activityCondition = ACTIVITY_CONDITION_EDEFAULT;

   /**
    * This is true if the Activity Condition attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean activityConditionESet;

   /**
    * The default value of the '{@link #getBinderClass() <em>Binder Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getBinderClass()
    * @generated
    * @ordered
    */
   protected static final String BINDER_CLASS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getBinderClass() <em>Binder Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getBinderClass()
    * @generated
    * @ordered
    */
   protected String binderClass = BINDER_CLASS_EDEFAULT;

   /**
    * The default value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getImplementation()
    * @generated
    * @ordered
    */
   protected static final ImplementationType IMPLEMENTATION_EDEFAULT = ImplementationType.ENGINE_LITERAL;

   /**
    * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getImplementation()
    * @generated
    * @ordered
    */
   protected ImplementationType implementation = IMPLEMENTATION_EDEFAULT;

   /**
    * This is true if the Implementation attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean implementationESet;

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
    * The default value of the '{@link #isProcessCondition() <em>Process Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isProcessCondition()
    * @generated
    * @ordered
    */
   protected static final boolean PROCESS_CONDITION_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isProcessCondition() <em>Process Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isProcessCondition()
    * @generated
    * @ordered
    */
   protected boolean processCondition = PROCESS_CONDITION_EDEFAULT;

   /**
    * This is true if the Process Condition attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean processConditionESet;

   /**
    * The default value of the '{@link #getPullEventEmitterClass() <em>Pull Event Emitter Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPullEventEmitterClass()
    * @generated
    * @ordered
    */
   protected static final String PULL_EVENT_EMITTER_CLASS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getPullEventEmitterClass() <em>Pull Event Emitter Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPullEventEmitterClass()
    * @generated
    * @ordered
    */
   protected String pullEventEmitterClass = PULL_EVENT_EMITTER_CLASS_EDEFAULT;

   /**
    * The default value of the '{@link #getRule() <em>Rule</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getRule()
    * @generated
    * @ordered
    */
   protected static final String RULE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getRule() <em>Rule</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getRule()
    * @generated
    * @ordered
    */
   protected String rule = RULE_EDEFAULT;

   /**
    * The cached value of the '{@link #getEventHandlers() <em>Event Handlers</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getEventHandlers()
    * @generated
    * @ordered
    */
   protected EList<EventHandlerType> eventHandlers;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected EventConditionTypeTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.EVENT_CONDITION_TYPE_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__DESCRIPTION, newDescription, newDescription));
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isActivityCondition()
   {
      return activityCondition;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setActivityCondition(boolean newActivityCondition)
   {
      boolean oldActivityCondition = activityCondition;
      activityCondition = newActivityCondition;
      boolean oldActivityConditionESet = activityConditionESet;
      activityConditionESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ACTIVITY_CONDITION, oldActivityCondition, activityCondition, !oldActivityConditionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetActivityCondition()
   {
      boolean oldActivityCondition = activityCondition;
      boolean oldActivityConditionESet = activityConditionESet;
      activityCondition = ACTIVITY_CONDITION_EDEFAULT;
      activityConditionESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ACTIVITY_CONDITION, oldActivityCondition, ACTIVITY_CONDITION_EDEFAULT, oldActivityConditionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetActivityCondition()
   {
      return activityConditionESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getBinderClass()
   {
      return binderClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setBinderClass(String newBinderClass)
   {
      String oldBinderClass = binderClass;
      binderClass = newBinderClass;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__BINDER_CLASS, oldBinderClass, binderClass));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
   public ImplementationType getImplementation()
   {
      return implementation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setImplementation(ImplementationType newImplementation)
   {
      ImplementationType oldImplementation = implementation;
      implementation = newImplementation == null ? IMPLEMENTATION_EDEFAULT : newImplementation;
      boolean oldImplementationESet = implementationESet;
      implementationESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IMPLEMENTATION, oldImplementation, implementation, !oldImplementationESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetImplementation()
   {
      ImplementationType oldImplementation = implementation;
      boolean oldImplementationESet = implementationESet;
      implementation = IMPLEMENTATION_EDEFAULT;
      implementationESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IMPLEMENTATION, oldImplementation, IMPLEMENTATION_EDEFAULT, oldImplementationESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetImplementation()
   {
      return implementationESet;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, isPredefined, !oldIsPredefinedESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, IS_PREDEFINED_EDEFAULT, oldIsPredefinedESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PANEL_CLASS, oldPanelClass, panelClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isProcessCondition()
   {
      return processCondition;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setProcessCondition(boolean newProcessCondition)
   {
      boolean oldProcessCondition = processCondition;
      processCondition = newProcessCondition;
      boolean oldProcessConditionESet = processConditionESet;
      processConditionESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PROCESS_CONDITION, oldProcessCondition, processCondition, !oldProcessConditionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetProcessCondition()
   {
      boolean oldProcessCondition = processCondition;
      boolean oldProcessConditionESet = processConditionESet;
      processCondition = PROCESS_CONDITION_EDEFAULT;
      processConditionESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PROCESS_CONDITION, oldProcessCondition, PROCESS_CONDITION_EDEFAULT, oldProcessConditionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetProcessCondition()
   {
      return processConditionESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getPullEventEmitterClass()
   {
      return pullEventEmitterClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPullEventEmitterClass(String newPullEventEmitterClass)
   {
      String oldPullEventEmitterClass = pullEventEmitterClass;
      pullEventEmitterClass = newPullEventEmitterClass;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PULL_EVENT_EMITTER_CLASS, oldPullEventEmitterClass, pullEventEmitterClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getRule()
   {
      return rule;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setRule(String newRule)
   {
      String oldRule = rule;
      rule = newRule;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__RULE, oldRule, rule));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<EventHandlerType> getEventHandlers()
   {
      if (eventHandlers == null)
      {
         eventHandlers = new EObjectWithInverseResolvingEList<EventHandlerType>(EventHandlerType.class, this, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__TYPE);
      }
      return eventHandlers;
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
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getEventHandlers()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS:
            return ((InternalEList<?>)getEventHandlers()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IS_PREDEFINED:
            return isIsPredefined();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ACTIVITY_CONDITION:
            return isActivityCondition();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__BINDER_CLASS:
            return getBinderClass();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IMPLEMENTATION:
            return getImplementation();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PANEL_CLASS:
            return getPanelClass();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PROCESS_CONDITION:
            return isProcessCondition();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PULL_EVENT_EMITTER_CLASS:
            return getPullEventEmitterClass();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__RULE:
            return getRule();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS:
            return getEventHandlers();
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
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IS_PREDEFINED:
            setIsPredefined((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ACTIVITY_CONDITION:
            setActivityCondition((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__BINDER_CLASS:
            setBinderClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IMPLEMENTATION:
            setImplementation((ImplementationType)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PANEL_CLASS:
            setPanelClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PROCESS_CONDITION:
            setProcessCondition((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PULL_EVENT_EMITTER_CLASS:
            setPullEventEmitterClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__RULE:
            setRule((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS:
            getEventHandlers().clear();
            getEventHandlers().addAll((Collection<? extends EventHandlerType>)newValue);
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
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IS_PREDEFINED:
            unsetIsPredefined();
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ACTIVITY_CONDITION:
            unsetActivityCondition();
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__BINDER_CLASS:
            setBinderClass(BINDER_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IMPLEMENTATION:
            unsetImplementation();
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PANEL_CLASS:
            setPanelClass(PANEL_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PROCESS_CONDITION:
            unsetProcessCondition();
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PULL_EVENT_EMITTER_CLASS:
            setPullEventEmitterClass(PULL_EVENT_EMITTER_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__RULE:
            setRule(RULE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS:
            getEventHandlers().clear();
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
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IS_PREDEFINED:
            return isSetIsPredefined();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ACTIVITY_CONDITION:
            return isSetActivityCondition();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__BINDER_CLASS:
            return BINDER_CLASS_EDEFAULT == null ? binderClass != null : !BINDER_CLASS_EDEFAULT.equals(binderClass);
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__IMPLEMENTATION:
            return isSetImplementation();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PANEL_CLASS:
            return PANEL_CLASS_EDEFAULT == null ? panelClass != null : !PANEL_CLASS_EDEFAULT.equals(panelClass);
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PROCESS_CONDITION:
            return isSetProcessCondition();
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__PULL_EVENT_EMITTER_CLASS:
            return PULL_EVENT_EMITTER_CLASS_EDEFAULT == null ? pullEventEmitterClass != null : !PULL_EVENT_EMITTER_CLASS_EDEFAULT.equals(pullEventEmitterClass);
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__RULE:
            return RULE_EDEFAULT == null ? rule != null : !RULE_EDEFAULT.equals(rule);
         case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS:
            return eventHandlers != null && !eventHandlers.isEmpty();
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
      return CarnotConstants.CONDITION_TYPES_EXTENSION_POINT_ID;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getTypedElements()
   {
      return getEventHandlers();
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
            case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__ATTRIBUTE;
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
      result.append(", activityCondition: "); //$NON-NLS-1$
      if (activityConditionESet) result.append(activityCondition); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", binderClass: "); //$NON-NLS-1$
      result.append(binderClass);
      result.append(", implementation: "); //$NON-NLS-1$
      if (implementationESet) result.append(implementation); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", panelClass: "); //$NON-NLS-1$
      result.append(panelClass);
      result.append(", processCondition: "); //$NON-NLS-1$
      if (processConditionESet) result.append(processCondition); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", pullEventEmitterClass: "); //$NON-NLS-1$
      result.append(pullEventEmitterClass);
      result.append(", rule: "); //$NON-NLS-1$
      result.append(rule);
      result.append(')');
      return result.toString();
   }

} //EventConditionTypeTypeImpl
