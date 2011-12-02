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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.UnbindActionType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Handler Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getAccessPoint <em>Access Point</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getBindAction <em>Bind Action</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getEventAction <em>Event Action</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getUnbindAction <em>Unbind Action</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#isAutoBind <em>Auto Bind</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#isConsumeOnMatch <em>Consume On Match</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#isLogHandler <em>Log Handler</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.EventHandlerTypeImpl#isUnbindOnMatch <em>Unbind On Match</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventHandlerTypeImpl extends EObjectImpl implements EventHandlerType
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
    * The cached value of the '{@link #getBindAction() <em>Bind Action</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getBindAction()
    * @generated
    * @ordered
    */
   protected EList<BindActionType> bindAction;

   /**
    * The cached value of the '{@link #getEventAction() <em>Event Action</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getEventAction()
    * @generated
    * @ordered
    */
   protected EList<EventActionType> eventAction;

   /**
    * The cached value of the '{@link #getUnbindAction() <em>Unbind Action</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getUnbindAction()
    * @generated
    * @ordered
    */
   protected EList<UnbindActionType> unbindAction;

   /**
    * The default value of the '{@link #isAutoBind() <em>Auto Bind</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isAutoBind()
    * @generated
    * @ordered
    */
   protected static final boolean AUTO_BIND_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isAutoBind() <em>Auto Bind</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isAutoBind()
    * @generated
    * @ordered
    */
   protected boolean autoBind = AUTO_BIND_EDEFAULT;

   /**
    * This is true if the Auto Bind attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean autoBindESet;

   /**
    * The default value of the '{@link #isConsumeOnMatch() <em>Consume On Match</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isConsumeOnMatch()
    * @generated
    * @ordered
    */
   protected static final boolean CONSUME_ON_MATCH_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isConsumeOnMatch() <em>Consume On Match</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isConsumeOnMatch()
    * @generated
    * @ordered
    */
   protected boolean consumeOnMatch = CONSUME_ON_MATCH_EDEFAULT;

   /**
    * This is true if the Consume On Match attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean consumeOnMatchESet;

   /**
    * The default value of the '{@link #isLogHandler() <em>Log Handler</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isLogHandler()
    * @generated
    * @ordered
    */
   protected static final boolean LOG_HANDLER_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isLogHandler() <em>Log Handler</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isLogHandler()
    * @generated
    * @ordered
    */
   protected boolean logHandler = LOG_HANDLER_EDEFAULT;

   /**
    * This is true if the Log Handler attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean logHandlerESet;

   /**
    * The cached value of the '{@link #getType() <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
   protected EventConditionTypeType type;

   /**
    * The default value of the '{@link #isUnbindOnMatch() <em>Unbind On Match</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isUnbindOnMatch()
    * @generated
    * @ordered
    */
   protected static final boolean UNBIND_ON_MATCH_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isUnbindOnMatch() <em>Unbind On Match</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isUnbindOnMatch()
    * @generated
    * @ordered
    */
   protected boolean unbindOnMatch = UNBIND_ON_MATCH_EDEFAULT;

   /**
    * This is true if the Unbind On Match attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean unbindOnMatchESet;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected EventHandlerTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.EVENT_HANDLER_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__DESCRIPTION, newDescription, newDescription));
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
         accessPoint = new EObjectContainmentEList<AccessPointType>(AccessPointType.class, this, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ACCESS_POINT);
      }
      return accessPoint;
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<BindActionType> getBindAction()
   {
      if (bindAction == null)
      {
         bindAction = new EObjectContainmentEList<BindActionType>(BindActionType.class, this, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__BIND_ACTION);
      }
      return bindAction;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<EventActionType> getEventAction()
   {
      if (eventAction == null)
      {
         eventAction = new EObjectContainmentEList<EventActionType>(EventActionType.class, this, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__EVENT_ACTION);
      }
      return eventAction;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<UnbindActionType> getUnbindAction()
   {
      if (unbindAction == null)
      {
         unbindAction = new EObjectContainmentEList<UnbindActionType>(UnbindActionType.class, this, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION);
      }
      return unbindAction;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isAutoBind()
   {
      return autoBind;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAutoBind(boolean newAutoBind)
   {
      boolean oldAutoBind = autoBind;
      autoBind = newAutoBind;
      boolean oldAutoBindESet = autoBindESet;
      autoBindESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__AUTO_BIND, oldAutoBind, autoBind, !oldAutoBindESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetAutoBind()
   {
      boolean oldAutoBind = autoBind;
      boolean oldAutoBindESet = autoBindESet;
      autoBind = AUTO_BIND_EDEFAULT;
      autoBindESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__AUTO_BIND, oldAutoBind, AUTO_BIND_EDEFAULT, oldAutoBindESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetAutoBind()
   {
      return autoBindESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isConsumeOnMatch()
   {
      return consumeOnMatch;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setConsumeOnMatch(boolean newConsumeOnMatch)
   {
      boolean oldConsumeOnMatch = consumeOnMatch;
      consumeOnMatch = newConsumeOnMatch;
      boolean oldConsumeOnMatchESet = consumeOnMatchESet;
      consumeOnMatchESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH, oldConsumeOnMatch, consumeOnMatch, !oldConsumeOnMatchESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetConsumeOnMatch()
   {
      boolean oldConsumeOnMatch = consumeOnMatch;
      boolean oldConsumeOnMatchESet = consumeOnMatchESet;
      consumeOnMatch = CONSUME_ON_MATCH_EDEFAULT;
      consumeOnMatchESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH, oldConsumeOnMatch, CONSUME_ON_MATCH_EDEFAULT, oldConsumeOnMatchESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetConsumeOnMatch()
   {
      return consumeOnMatchESet;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
   public boolean isLogHandler()
   {
      return logHandler;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLogHandler(boolean newLogHandler)
   {
      boolean oldLogHandler = logHandler;
      logHandler = newLogHandler;
      boolean oldLogHandlerESet = logHandlerESet;
      logHandlerESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__LOG_HANDLER, oldLogHandler, logHandler, !oldLogHandlerESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetLogHandler()
   {
      boolean oldLogHandler = logHandler;
      boolean oldLogHandlerESet = logHandlerESet;
      logHandler = LOG_HANDLER_EDEFAULT;
      logHandlerESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__LOG_HANDLER, oldLogHandler, LOG_HANDLER_EDEFAULT, oldLogHandlerESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetLogHandler()
   {
      return logHandlerESet;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
   public EventConditionTypeType getType()
   {
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetType(EventConditionTypeType newType, NotificationChain msgs)
   {
      EventConditionTypeType oldType = type;
      type = newType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__TYPE, oldType, newType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setType(EventConditionTypeType newType)
   {
      if (newType != type)
      {
         NotificationChain msgs = null;
         if (type != null)
            msgs = ((InternalEObject)type).eInverseRemove(this, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS, EventConditionTypeType.class, msgs);
         if (newType != null)
            msgs = ((InternalEObject)newType).eInverseAdd(this, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS, EventConditionTypeType.class, msgs);
         msgs = basicSetType(newType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__TYPE, newType, newType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isUnbindOnMatch()
   {
      return unbindOnMatch;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setUnbindOnMatch(boolean newUnbindOnMatch)
   {
      boolean oldUnbindOnMatch = unbindOnMatch;
      unbindOnMatch = newUnbindOnMatch;
      boolean oldUnbindOnMatchESet = unbindOnMatchESet;
      unbindOnMatchESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH, oldUnbindOnMatch, unbindOnMatch, !oldUnbindOnMatchESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetUnbindOnMatch()
   {
      boolean oldUnbindOnMatch = unbindOnMatch;
      boolean oldUnbindOnMatchESet = unbindOnMatchESet;
      unbindOnMatch = UNBIND_ON_MATCH_EDEFAULT;
      unbindOnMatchESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH, oldUnbindOnMatch, UNBIND_ON_MATCH_EDEFAULT, oldUnbindOnMatchESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetUnbindOnMatch()
   {
      return unbindOnMatchESet;
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
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__TYPE:
            if (type != null)
               msgs = ((InternalEObject)type).eInverseRemove(this, CarnotWorkflowModelPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS, EventConditionTypeType.class, msgs);
            return basicSetType((EventConditionTypeType)otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
            return ((InternalEList<?>)getAccessPoint()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
            return ((InternalEList<?>)getBindAction()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
            return ((InternalEList<?>)getEventAction()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
            return ((InternalEList<?>)getUnbindAction()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__TYPE:
            return basicSetType(null, msgs);
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
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
            return getAccessPoint();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
            return getBindAction();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
            return getEventAction();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
            return getUnbindAction();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__AUTO_BIND:
            return isAutoBind();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH:
            return isConsumeOnMatch();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__LOG_HANDLER:
            return isLogHandler();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__TYPE:
            return getType();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH:
            return isUnbindOnMatch();
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
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
            getAccessPoint().clear();
            getAccessPoint().addAll((Collection<? extends AccessPointType>)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
            getBindAction().clear();
            getBindAction().addAll((Collection<? extends BindActionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
            getEventAction().clear();
            getEventAction().addAll((Collection<? extends EventActionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
            getUnbindAction().clear();
            getUnbindAction().addAll((Collection<? extends UnbindActionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__AUTO_BIND:
            setAutoBind((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH:
            setConsumeOnMatch((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__LOG_HANDLER:
            setLogHandler((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__TYPE:
            setType((EventConditionTypeType)newValue);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH:
            setUnbindOnMatch((Boolean)newValue);
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
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
            getAccessPoint().clear();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
            getBindAction().clear();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
            getEventAction().clear();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
            getUnbindAction().clear();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__AUTO_BIND:
            unsetAutoBind();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH:
            unsetConsumeOnMatch();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__LOG_HANDLER:
            unsetLogHandler();
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__TYPE:
            setType((EventConditionTypeType)null);
            return;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH:
            unsetUnbindOnMatch();
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
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
            return accessPoint != null && !accessPoint.isEmpty();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
            return bindAction != null && !bindAction.isEmpty();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
            return eventAction != null && !eventAction.isEmpty();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
            return unbindAction != null && !unbindAction.isEmpty();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__AUTO_BIND:
            return isSetAutoBind();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH:
            return isSetConsumeOnMatch();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__LOG_HANDLER:
            return isSetLogHandler();
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__TYPE:
            return type != null;
         case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH:
            return isSetUnbindOnMatch();
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
            case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ACCESS_POINT: return CarnotWorkflowModelPackage.IACCESS_POINT_OWNER__ACCESS_POINT;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.IACCESS_POINT_OWNER__ACCESS_POINT: return CarnotWorkflowModelPackage.EVENT_HANDLER_TYPE__ACCESS_POINT;
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
      result.append(" (elementOid: ");
      if (elementOidESet) result.append(elementOid); else result.append("<unset>");
      result.append(", id: ");
      if (idESet) result.append(id); else result.append("<unset>");
      result.append(", name: ");
      if (nameESet) result.append(name); else result.append("<unset>");
      result.append(", autoBind: ");
      if (autoBindESet) result.append(autoBind); else result.append("<unset>");
      result.append(", consumeOnMatch: ");
      if (consumeOnMatchESet) result.append(consumeOnMatch); else result.append("<unset>");
      result.append(", logHandler: ");
      if (logHandlerESet) result.append(logHandler); else result.append("<unset>");
      result.append(", unbindOnMatch: ");
      if (unbindOnMatchESet) result.append(unbindOnMatch); else result.append("<unset>");
      result.append(')');
      return result.toString();
   }

} //EventHandlerTypeImpl
