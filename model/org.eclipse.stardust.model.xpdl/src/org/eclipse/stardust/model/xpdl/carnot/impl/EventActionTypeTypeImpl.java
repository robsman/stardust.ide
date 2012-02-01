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
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Action Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#isIsPredefined <em>Is Predefined</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getActionClass <em>Action Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#isActivityAction <em>Activity Action</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#isProcessAction <em>Process Action</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getSupportedConditionTypes <em>Supported Condition Types</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getUnsupportedContexts <em>Unsupported Contexts</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventActionTypeTypeImpl#getActionInstances <em>Action Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventActionTypeTypeImpl extends EObjectImpl implements EventActionTypeType
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
    * The default value of the '{@link #getActionClass() <em>Action Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getActionClass()
    * @generated
    * @ordered
    */
   protected static final String ACTION_CLASS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getActionClass() <em>Action Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getActionClass()
    * @generated
    * @ordered
    */
   protected String actionClass = ACTION_CLASS_EDEFAULT;

   /**
    * The default value of the '{@link #isActivityAction() <em>Activity Action</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isActivityAction()
    * @generated
    * @ordered
    */
   protected static final boolean ACTIVITY_ACTION_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isActivityAction() <em>Activity Action</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isActivityAction()
    * @generated
    * @ordered
    */
   protected boolean activityAction = ACTIVITY_ACTION_EDEFAULT;

   /**
    * This is true if the Activity Action attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean activityActionESet;

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
    * The default value of the '{@link #isProcessAction() <em>Process Action</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isProcessAction()
    * @generated
    * @ordered
    */
   protected static final boolean PROCESS_ACTION_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isProcessAction() <em>Process Action</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isProcessAction()
    * @generated
    * @ordered
    */
   protected boolean processAction = PROCESS_ACTION_EDEFAULT;

   /**
    * This is true if the Process Action attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean processActionESet;

   /**
    * The default value of the '{@link #getSupportedConditionTypes() <em>Supported Condition Types</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSupportedConditionTypes()
    * @generated
    * @ordered
    */
   protected static final String SUPPORTED_CONDITION_TYPES_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getSupportedConditionTypes() <em>Supported Condition Types</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSupportedConditionTypes()
    * @generated
    * @ordered
    */
   protected String supportedConditionTypes = SUPPORTED_CONDITION_TYPES_EDEFAULT;

   /**
    * The default value of the '{@link #getUnsupportedContexts() <em>Unsupported Contexts</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getUnsupportedContexts()
    * @generated
    * @ordered
    */
   protected static final String UNSUPPORTED_CONTEXTS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getUnsupportedContexts() <em>Unsupported Contexts</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getUnsupportedContexts()
    * @generated
    * @ordered
    */
   protected String unsupportedContexts = UNSUPPORTED_CONTEXTS_EDEFAULT;

   /**
    * The cached value of the '{@link #getActionInstances() <em>Action Instances</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getActionInstances()
    * @generated
    * @ordered
    */
   protected EList<AbstractEventAction> actionInstances;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected EventActionTypeTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.EVENT_ACTION_TYPE_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION, newDescription, newDescription));
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getActionClass()
   {
      return actionClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setActionClass(String newActionClass)
   {
      String oldActionClass = actionClass;
      actionClass = newActionClass;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_CLASS, oldActionClass, actionClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isActivityAction()
   {
      return activityAction;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setActivityAction(boolean newActivityAction)
   {
      boolean oldActivityAction = activityAction;
      activityAction = newActivityAction;
      boolean oldActivityActionESet = activityActionESet;
      activityActionESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTIVITY_ACTION, oldActivityAction, activityAction, !oldActivityActionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetActivityAction()
   {
      boolean oldActivityAction = activityAction;
      boolean oldActivityActionESet = activityActionESet;
      activityAction = ACTIVITY_ACTION_EDEFAULT;
      activityActionESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTIVITY_ACTION, oldActivityAction, ACTIVITY_ACTION_EDEFAULT, oldActivityActionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetActivityAction()
   {
      return activityActionESet;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, isPredefined, !oldIsPredefinedESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, IS_PREDEFINED_EDEFAULT, oldIsPredefinedESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PANEL_CLASS, oldPanelClass, panelClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isProcessAction()
   {
      return processAction;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setProcessAction(boolean newProcessAction)
   {
      boolean oldProcessAction = processAction;
      processAction = newProcessAction;
      boolean oldProcessActionESet = processActionESet;
      processActionESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PROCESS_ACTION, oldProcessAction, processAction, !oldProcessActionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetProcessAction()
   {
      boolean oldProcessAction = processAction;
      boolean oldProcessActionESet = processActionESet;
      processAction = PROCESS_ACTION_EDEFAULT;
      processActionESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PROCESS_ACTION, oldProcessAction, PROCESS_ACTION_EDEFAULT, oldProcessActionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetProcessAction()
   {
      return processActionESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getSupportedConditionTypes()
   {
      return supportedConditionTypes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSupportedConditionTypes(String newSupportedConditionTypes)
   {
      String oldSupportedConditionTypes = supportedConditionTypes;
      supportedConditionTypes = newSupportedConditionTypes;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__SUPPORTED_CONDITION_TYPES, oldSupportedConditionTypes, supportedConditionTypes));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getUnsupportedContexts()
   {
      return unsupportedContexts;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setUnsupportedContexts(String newUnsupportedContexts)
   {
      String oldUnsupportedContexts = unsupportedContexts;
      unsupportedContexts = newUnsupportedContexts;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__UNSUPPORTED_CONTEXTS, oldUnsupportedContexts, unsupportedContexts));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<AbstractEventAction> getActionInstances()
   {
      if (actionInstances == null)
      {
         actionInstances = new EObjectWithInverseResolvingEList<AbstractEventAction>(AbstractEventAction.class, this, CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_INSTANCES, CarnotWorkflowModelPackage.ABSTRACT_EVENT_ACTION__TYPE);
      }
      return actionInstances;
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
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_INSTANCES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getActionInstances()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_INSTANCES:
            return ((InternalEList<?>)getActionInstances()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__IS_PREDEFINED:
            return isIsPredefined();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_CLASS:
            return getActionClass();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTIVITY_ACTION:
            return isActivityAction();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PANEL_CLASS:
            return getPanelClass();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PROCESS_ACTION:
            return isProcessAction();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__SUPPORTED_CONDITION_TYPES:
            return getSupportedConditionTypes();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__UNSUPPORTED_CONTEXTS:
            return getUnsupportedContexts();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_INSTANCES:
            return getActionInstances();
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
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__IS_PREDEFINED:
            setIsPredefined((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_CLASS:
            setActionClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTIVITY_ACTION:
            setActivityAction((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PANEL_CLASS:
            setPanelClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PROCESS_ACTION:
            setProcessAction((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__SUPPORTED_CONDITION_TYPES:
            setSupportedConditionTypes((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__UNSUPPORTED_CONTEXTS:
            setUnsupportedContexts((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_INSTANCES:
            getActionInstances().clear();
            getActionInstances().addAll((Collection<? extends AbstractEventAction>)newValue);
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
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__IS_PREDEFINED:
            unsetIsPredefined();
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_CLASS:
            setActionClass(ACTION_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTIVITY_ACTION:
            unsetActivityAction();
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PANEL_CLASS:
            setPanelClass(PANEL_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PROCESS_ACTION:
            unsetProcessAction();
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__SUPPORTED_CONDITION_TYPES:
            setSupportedConditionTypes(SUPPORTED_CONDITION_TYPES_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__UNSUPPORTED_CONTEXTS:
            setUnsupportedContexts(UNSUPPORTED_CONTEXTS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_INSTANCES:
            getActionInstances().clear();
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
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__IS_PREDEFINED:
            return isSetIsPredefined();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_CLASS:
            return ACTION_CLASS_EDEFAULT == null ? actionClass != null : !ACTION_CLASS_EDEFAULT.equals(actionClass);
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTIVITY_ACTION:
            return isSetActivityAction();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PANEL_CLASS:
            return PANEL_CLASS_EDEFAULT == null ? panelClass != null : !PANEL_CLASS_EDEFAULT.equals(panelClass);
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PROCESS_ACTION:
            return isSetProcessAction();
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__SUPPORTED_CONDITION_TYPES:
            return SUPPORTED_CONDITION_TYPES_EDEFAULT == null ? supportedConditionTypes != null : !SUPPORTED_CONDITION_TYPES_EDEFAULT.equals(supportedConditionTypes);
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__UNSUPPORTED_CONTEXTS:
            return UNSUPPORTED_CONTEXTS_EDEFAULT == null ? unsupportedContexts != null : !UNSUPPORTED_CONTEXTS_EDEFAULT.equals(unsupportedContexts);
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_INSTANCES:
            return actionInstances != null && !actionInstances.isEmpty();
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
      return CarnotConstants.ACTION_TYPES_EXTENSION_POINT_ID;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getTypedElements()
   {
      return getActionInstances();
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
            case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ATTRIBUTE;
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
      result.append(", actionClass: "); //$NON-NLS-1$
      result.append(actionClass);
      result.append(", activityAction: "); //$NON-NLS-1$
      if (activityActionESet) result.append(activityAction); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", panelClass: "); //$NON-NLS-1$
      result.append(panelClass);
      result.append(", processAction: "); //$NON-NLS-1$
      if (processActionESet) result.append(processAction); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", supportedConditionTypes: "); //$NON-NLS-1$
      result.append(supportedConditionTypes);
      result.append(", unsupportedContexts: "); //$NON-NLS-1$
      result.append(unsupportedContexts);
      result.append(')');
      return result.toString();
   }

} //EventActionTypeTypeImpl
