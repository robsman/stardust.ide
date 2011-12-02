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
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#isForkOnTraversal <em>Fork On Traversal</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getTo <em>To</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.TransitionTypeImpl#getTransitionConnections <em>Transition Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransitionTypeImpl extends EObjectImpl implements TransitionType
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
    * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExpression()
    * @generated
    * @ordered
    */
   protected XmlTextNode expression;

   /**
    * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getCondition()
    * @generated
    * @ordered
    */
   protected static final String CONDITION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getCondition()
    * @generated
    * @ordered
    */
   protected String condition = CONDITION_EDEFAULT;

   /**
    * The default value of the '{@link #isForkOnTraversal() <em>Fork On Traversal</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isForkOnTraversal()
    * @generated
    * @ordered
    */
   protected static final boolean FORK_ON_TRAVERSAL_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isForkOnTraversal() <em>Fork On Traversal</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isForkOnTraversal()
    * @generated
    * @ordered
    */
   protected boolean forkOnTraversal = FORK_ON_TRAVERSAL_EDEFAULT;

   /**
    * This is true if the Fork On Traversal attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean forkOnTraversalESet;

   /**
    * The cached value of the '{@link #getFrom() <em>From</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getFrom()
    * @generated
    * @ordered
    */
   protected ActivityType from;

   /**
    * The cached value of the '{@link #getTo() <em>To</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTo()
    * @generated
    * @ordered
    */
   protected ActivityType to;

   /**
    * The cached value of the '{@link #getTransitionConnections() <em>Transition Connections</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTransitionConnections()
    * @generated
    * @ordered
    */
   protected EList<TransitionConnectionType> transitionConnections;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected TransitionTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.TRANSITION_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.TRANSITION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.TRANSITION_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.TRANSITION_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__DESCRIPTION, newDescription, newDescription));
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.TRANSITION_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public XmlTextNode getExpression()
   {
      return expression;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetExpression(XmlTextNode newExpression, NotificationChain msgs)
   {
      XmlTextNode oldExpression = expression;
      expression = newExpression;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__EXPRESSION, oldExpression, newExpression);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setExpression(XmlTextNode newExpression)
   {
      if (newExpression != expression)
      {
         NotificationChain msgs = null;
         if (expression != null)
            msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.TRANSITION_TYPE__EXPRESSION, null, msgs);
         if (newExpression != null)
            msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.TRANSITION_TYPE__EXPRESSION, null, msgs);
         msgs = basicSetExpression(newExpression, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__EXPRESSION, newExpression, newExpression));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getCondition()
   {
      return condition;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setCondition(String newCondition)
   {
      String oldCondition = condition;
      condition = newCondition;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__CONDITION, oldCondition, condition));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isForkOnTraversal()
   {
      return forkOnTraversal;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setForkOnTraversal(boolean newForkOnTraversal)
   {
      boolean oldForkOnTraversal = forkOnTraversal;
      forkOnTraversal = newForkOnTraversal;
      boolean oldForkOnTraversalESet = forkOnTraversalESet;
      forkOnTraversalESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__FORK_ON_TRAVERSAL, oldForkOnTraversal, forkOnTraversal, !oldForkOnTraversalESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetForkOnTraversal()
   {
      boolean oldForkOnTraversal = forkOnTraversal;
      boolean oldForkOnTraversalESet = forkOnTraversalESet;
      forkOnTraversal = FORK_ON_TRAVERSAL_EDEFAULT;
      forkOnTraversalESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.TRANSITION_TYPE__FORK_ON_TRAVERSAL, oldForkOnTraversal, FORK_ON_TRAVERSAL_EDEFAULT, oldForkOnTraversalESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetForkOnTraversal()
   {
      return forkOnTraversalESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ActivityType getFrom()
   {
      return from;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetFrom(ActivityType newFrom, NotificationChain msgs)
   {
      ActivityType oldFrom = from;
      from = newFrom;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__FROM, oldFrom, newFrom);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setFrom(ActivityType newFrom)
   {
      if (newFrom != from)
      {
         NotificationChain msgs = null;
         if (from != null)
            msgs = ((InternalEObject)from).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS, ActivityType.class, msgs);
         if (newFrom != null)
            msgs = ((InternalEObject)newFrom).eInverseAdd(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS, ActivityType.class, msgs);
         msgs = basicSetFrom(newFrom, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__FROM, newFrom, newFrom));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.TRANSITION_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.TRANSITION_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
   public ActivityType getTo()
   {
      return to;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetTo(ActivityType newTo, NotificationChain msgs)
   {
      ActivityType oldTo = to;
      to = newTo;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__TO, oldTo, newTo);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTo(ActivityType newTo)
   {
      if (newTo != to)
      {
         NotificationChain msgs = null;
         if (to != null)
            msgs = ((InternalEObject)to).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS, ActivityType.class, msgs);
         if (newTo != null)
            msgs = ((InternalEObject)newTo).eInverseAdd(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS, ActivityType.class, msgs);
         msgs = basicSetTo(newTo, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.TRANSITION_TYPE__TO, newTo, newTo));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TransitionConnectionType> getTransitionConnections()
   {
      if (transitionConnections == null)
      {
         transitionConnections = new EObjectWithInverseResolvingEList<TransitionConnectionType>(TransitionConnectionType.class, this, CarnotWorkflowModelPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS, CarnotWorkflowModelPackage.TRANSITION_CONNECTION_TYPE__TRANSITION);
      }
      return transitionConnections;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getSymbols()
   {
      return getTransitionConnections();
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
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FROM:
            if (from != null)
               msgs = ((InternalEObject)from).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS, ActivityType.class, msgs);
            return basicSetFrom((ActivityType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TO:
            if (to != null)
               msgs = ((InternalEObject)to).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS, ActivityType.class, msgs);
            return basicSetTo((ActivityType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getTransitionConnections()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__EXPRESSION:
            return basicSetExpression(null, msgs);
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FROM:
            return basicSetFrom(null, msgs);
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TO:
            return basicSetTo(null, msgs);
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS:
            return ((InternalEList<?>)getTransitionConnections()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__EXPRESSION:
            return getExpression();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__CONDITION:
            return getCondition();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FORK_ON_TRAVERSAL:
            return isForkOnTraversal();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FROM:
            return getFrom();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TO:
            return getTo();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS:
            return getTransitionConnections();
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
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__EXPRESSION:
            setExpression((XmlTextNode)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__CONDITION:
            setCondition((String)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FORK_ON_TRAVERSAL:
            setForkOnTraversal((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FROM:
            setFrom((ActivityType)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TO:
            setTo((ActivityType)newValue);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS:
            getTransitionConnections().clear();
            getTransitionConnections().addAll((Collection<? extends TransitionConnectionType>)newValue);
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
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__EXPRESSION:
            setExpression((XmlTextNode)null);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__CONDITION:
            setCondition(CONDITION_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FORK_ON_TRAVERSAL:
            unsetForkOnTraversal();
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FROM:
            setFrom((ActivityType)null);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TO:
            setTo((ActivityType)null);
            return;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS:
            getTransitionConnections().clear();
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
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__EXPRESSION:
            return expression != null;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__CONDITION:
            return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FORK_ON_TRAVERSAL:
            return isSetForkOnTraversal();
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__FROM:
            return from != null;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TO:
            return to != null;
         case CarnotWorkflowModelPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS:
            return transitionConnections != null && !transitionConnections.isEmpty();
      }
      return super.eIsSet(featureID);
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
            case CarnotWorkflowModelPackage.TRANSITION_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.TRANSITION_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.TRANSITION_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.TRANSITION_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.TRANSITION_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.TRANSITION_TYPE__ATTRIBUTE;
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
      result.append(", condition: ");
      result.append(condition);
      result.append(", forkOnTraversal: ");
      if (forkOnTraversalESet) result.append(forkOnTraversal); else result.append("<unset>");
      result.append(')');
      return result.toString();
   }

} //TransitionTypeImpl
