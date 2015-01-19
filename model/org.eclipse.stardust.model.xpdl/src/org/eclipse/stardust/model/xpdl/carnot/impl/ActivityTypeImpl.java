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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getEventHandler <em>Event Handler</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getExternalRef <em>External Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getDataMapping <em>Data Mapping</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#isHibernateOnCreation <em>Hibernate On Creation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getImplementationProcess <em>Implementation Process</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getLoopCondition <em>Loop Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getLoopType <em>Loop Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getQualityControlPerformer <em>Quality Control Performer</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getSplit <em>Split</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getSubProcessMode <em>Sub Process Mode</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getActivitySymbols <em>Activity Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getStartingEventSymbols <em>Starting Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getInTransitions <em>In Transitions</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getOutTransitions <em>Out Transitions</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getValidQualityCodes <em>Valid Quality Codes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivityTypeImpl#getLoop <em>Loop</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityTypeImpl extends EObjectImpl implements ActivityType
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
    * The cached value of the '{@link #getEventHandler() <em>Event Handler</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getEventHandler()
    * @generated
    * @ordered
    */
   protected EList<EventHandlerType> eventHandler;

   /**
    * The cached value of the '{@link #getExternalRef() <em>External Ref</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExternalRef()
    * @generated
    * @ordered
    */
   protected IdRef externalRef;

   /**
    * The cached value of the '{@link #getDataMapping() <em>Data Mapping</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDataMapping()
    * @generated
    * @ordered
    */
   protected EList<DataMappingType> dataMapping;

   /**
    * The default value of the '{@link #isAllowsAbortByPerformer() <em>Allows Abort By Performer</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isAllowsAbortByPerformer()
    * @generated
    * @ordered
    */
   protected static final boolean ALLOWS_ABORT_BY_PERFORMER_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isAllowsAbortByPerformer() <em>Allows Abort By Performer</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isAllowsAbortByPerformer()
    * @generated
    * @ordered
    */
   protected boolean allowsAbortByPerformer = ALLOWS_ABORT_BY_PERFORMER_EDEFAULT;

   /**
    * This is true if the Allows Abort By Performer attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean allowsAbortByPerformerESet;

   /**
    * The cached value of the '{@link #getApplication() <em>Application</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getApplication()
    * @generated
    * @ordered
    */
   protected ApplicationType application;

   /**
    * The default value of the '{@link #isHibernateOnCreation() <em>Hibernate On Creation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isHibernateOnCreation()
    * @generated
    * @ordered
    */
   protected static final boolean HIBERNATE_ON_CREATION_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isHibernateOnCreation() <em>Hibernate On Creation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isHibernateOnCreation()
    * @generated
    * @ordered
    */
   protected boolean hibernateOnCreation = HIBERNATE_ON_CREATION_EDEFAULT;

   /**
    * This is true if the Hibernate On Creation attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean hibernateOnCreationESet;

   /**
    * The default value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getImplementation()
    * @generated
    * @ordered
    */
   protected static final ActivityImplementationType IMPLEMENTATION_EDEFAULT = ActivityImplementationType.ROUTE_LITERAL;

   /**
    * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getImplementation()
    * @generated
    * @ordered
    */
   protected ActivityImplementationType implementation = IMPLEMENTATION_EDEFAULT;

   /**
    * The cached value of the '{@link #getImplementationProcess() <em>Implementation Process</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getImplementationProcess()
    * @generated
    * @ordered
    */
   protected ProcessDefinitionType implementationProcess;

   /**
    * The default value of the '{@link #getJoin() <em>Join</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getJoin()
    * @generated
    * @ordered
    */
   protected static final JoinSplitType JOIN_EDEFAULT = JoinSplitType.NONE_LITERAL;

   /**
    * The cached value of the '{@link #getJoin() <em>Join</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getJoin()
    * @generated
    * @ordered
    */
   protected JoinSplitType join = JOIN_EDEFAULT;

   /**
    * This is true if the Join attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean joinESet;

   /**
    * The default value of the '{@link #getLoopCondition() <em>Loop Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopCondition()
    * @generated
    * @ordered
    */
   protected static final String LOOP_CONDITION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getLoopCondition() <em>Loop Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopCondition()
    * @generated
    * @ordered
    */
   protected String loopCondition = LOOP_CONDITION_EDEFAULT;

   /**
    * The default value of the '{@link #getLoopType() <em>Loop Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopType()
    * @generated
    * @ordered
    */
   protected static final LoopType LOOP_TYPE_EDEFAULT = LoopType.NONE_LITERAL;

   /**
    * The cached value of the '{@link #getLoopType() <em>Loop Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopType()
    * @generated
    * @ordered
    */
   protected LoopType loopType = LOOP_TYPE_EDEFAULT;

   /**
    * This is true if the Loop Type attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean loopTypeESet;

   /**
    * The cached value of the '{@link #getPerformer() <em>Performer</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPerformer()
    * @generated
    * @ordered
    */
   protected IModelParticipant performer;

   /**
    * The cached value of the '{@link #getQualityControlPerformer() <em>Quality Control Performer</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getQualityControlPerformer()
    * @generated
    * @ordered
    */
   protected IModelParticipant qualityControlPerformer;

   /**
    * The default value of the '{@link #getSplit() <em>Split</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSplit()
    * @generated
    * @ordered
    */
   protected static final JoinSplitType SPLIT_EDEFAULT = JoinSplitType.NONE_LITERAL;

   /**
    * The cached value of the '{@link #getSplit() <em>Split</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSplit()
    * @generated
    * @ordered
    */
   protected JoinSplitType split = SPLIT_EDEFAULT;

   /**
    * This is true if the Split attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean splitESet;

   /**
    * The default value of the '{@link #getSubProcessMode() <em>Sub Process Mode</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSubProcessMode()
    * @generated
    * @ordered
    */
   protected static final SubProcessModeType SUB_PROCESS_MODE_EDEFAULT = SubProcessModeType.SYNC_SHARED_LITERAL;

   /**
    * The cached value of the '{@link #getSubProcessMode() <em>Sub Process Mode</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSubProcessMode()
    * @generated
    * @ordered
    */
   protected SubProcessModeType subProcessMode = SUB_PROCESS_MODE_EDEFAULT;

   /**
    * This is true if the Sub Process Mode attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean subProcessModeESet;

   /**
    * The cached value of the '{@link #getActivitySymbols() <em>Activity Symbols</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getActivitySymbols()
    * @generated
    * @ordered
    */
   protected EList<ActivitySymbolType> activitySymbols;

   /**
    * The cached value of the '{@link #getStartingEventSymbols() <em>Starting Event Symbols</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStartingEventSymbols()
    * @generated
    * @ordered
    */
   protected EList<StartEventSymbol> startingEventSymbols;

   /**
    * The cached value of the '{@link #getInTransitions() <em>In Transitions</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInTransitions()
    * @generated
    * @ordered
    */
   protected EList<TransitionType> inTransitions;

   /**
    * The cached value of the '{@link #getOutTransitions() <em>Out Transitions</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOutTransitions()
    * @generated
    * @ordered
    */
   protected EList<TransitionType> outTransitions;

   /**
    * The cached value of the '{@link #getValidQualityCodes() <em>Valid Quality Codes</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValidQualityCodes()
    * @generated
    * @ordered
    */
   protected EList<Code> validQualityCodes;

   /**
    * The cached value of the '{@link #getLoop() <em>Loop</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoop()
    * @generated
    * @ordered
    */
   protected org.eclipse.stardust.model.xpdl.xpdl2.LoopType loop;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ActivityTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.ACTIVITY_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ACTIVITY_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ACTIVITY_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__DESCRIPTION, newDescription, newDescription));
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DataMappingType> getDataMapping()
   {
      if (dataMapping == null)
      {
         dataMapping = new EObjectContainmentEList<DataMappingType>(DataMappingType.class, this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__DATA_MAPPING);
      }
      return dataMapping;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<EventHandlerType> getEventHandler()
   {
      if (eventHandler == null)
      {
         eventHandler = new EObjectContainmentEList<EventHandlerType>(EventHandlerType.class, this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__EVENT_HANDLER);
      }
      return eventHandler;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isAllowsAbortByPerformer()
   {
      return allowsAbortByPerformer;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAllowsAbortByPerformer(boolean newAllowsAbortByPerformer)
   {
      boolean oldAllowsAbortByPerformer = allowsAbortByPerformer;
      allowsAbortByPerformer = newAllowsAbortByPerformer;
      boolean oldAllowsAbortByPerformerESet = allowsAbortByPerformerESet;
      allowsAbortByPerformerESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ALLOWS_ABORT_BY_PERFORMER, oldAllowsAbortByPerformer, allowsAbortByPerformer, !oldAllowsAbortByPerformerESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetAllowsAbortByPerformer()
   {
      boolean oldAllowsAbortByPerformer = allowsAbortByPerformer;
      boolean oldAllowsAbortByPerformerESet = allowsAbortByPerformerESet;
      allowsAbortByPerformer = ALLOWS_ABORT_BY_PERFORMER_EDEFAULT;
      allowsAbortByPerformerESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ALLOWS_ABORT_BY_PERFORMER, oldAllowsAbortByPerformer, ALLOWS_ABORT_BY_PERFORMER_EDEFAULT, oldAllowsAbortByPerformerESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetAllowsAbortByPerformer()
   {
      return allowsAbortByPerformerESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public ApplicationType getApplication()
   {
      if (externalRef != null && ActivityImplementationType.APPLICATION_LITERAL == getImplementation())
      {
         return externalRef.get(ApplicationType.class);
      }
      return application;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public NotificationChain basicSetApplication(ApplicationType newApplication, NotificationChain msgs)
   {
      ApplicationType oldApplication = application;

      ModelType thisModel = ModelUtils.findContainingModel(this);
      ModelType otherModel = ModelUtils.findContainingModel(newApplication);
      if (thisModel == otherModel || otherModel == null ||
          thisModel == null && AttributeUtil.getAttribute(this, IConnectionManager.URI_ATTRIBUTE_NAME) == null)
      {
         application = newApplication;
         setExternalRef(null);
         AttributeUtil.setAttribute(this, IConnectionManager.URI_ATTRIBUTE_NAME, null);
      }
      else
      {
         application = null;
         IdRef ref = getExternalRef();
         if (ref == null)
         {
            ref = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
            setExternalRef(ref);
         }
         ref.set(newApplication);
         String uri = AttributeUtil.getAttributeValue(newApplication, IConnectionManager.URI_ATTRIBUTE_NAME);
         AttributeUtil.setAttribute(this, IConnectionManager.URI_ATTRIBUTE_NAME, uri);
      }

      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__APPLICATION, oldApplication, newApplication);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setApplication(ApplicationType newApplication)
   {
      if (newApplication != application)
      {
         NotificationChain msgs = null;
         if (application != null)
            msgs = ((InternalEObject)application).eInverseRemove(this, CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES, ApplicationType.class, msgs);
         if (newApplication != null)
            msgs = ((InternalEObject)newApplication).eInverseAdd(this, CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES, ApplicationType.class, msgs);
         msgs = basicSetApplication(newApplication, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__APPLICATION, newApplication, newApplication));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isHibernateOnCreation()
   {
      return hibernateOnCreation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setHibernateOnCreation(boolean newHibernateOnCreation)
   {
      boolean oldHibernateOnCreation = hibernateOnCreation;
      hibernateOnCreation = newHibernateOnCreation;
      boolean oldHibernateOnCreationESet = hibernateOnCreationESet;
      hibernateOnCreationESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__HIBERNATE_ON_CREATION, oldHibernateOnCreation, hibernateOnCreation, !oldHibernateOnCreationESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetHibernateOnCreation()
   {
      boolean oldHibernateOnCreation = hibernateOnCreation;
      boolean oldHibernateOnCreationESet = hibernateOnCreationESet;
      hibernateOnCreation = HIBERNATE_ON_CREATION_EDEFAULT;
      hibernateOnCreationESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__HIBERNATE_ON_CREATION, oldHibernateOnCreation, HIBERNATE_ON_CREATION_EDEFAULT, oldHibernateOnCreationESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetHibernateOnCreation()
   {
      return hibernateOnCreationESet;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
   public ActivityImplementationType getImplementation()
   {
      return implementation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setImplementation(ActivityImplementationType newImplementation)
   {
      ActivityImplementationType oldImplementation = implementation;
      implementation = newImplementation == null ? IMPLEMENTATION_EDEFAULT : newImplementation;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION, oldImplementation, implementation));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public ProcessDefinitionType getImplementationProcess()
   {
      if (externalRef != null && ActivityImplementationType.SUBPROCESS_LITERAL == getImplementation())
      {
         return externalRef.get(ProcessDefinitionType.class);
      }
      return implementationProcess;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public NotificationChain basicSetImplementationProcess(ProcessDefinitionType newImplementationProcess, NotificationChain msgs)
   {
      ProcessDefinitionType oldImplementationProcess = implementationProcess;

      ModelType thisModel = ModelUtils.findContainingModel(this);
      ModelType otherModel = ModelUtils.findContainingModel(newImplementationProcess);
      if (thisModel == otherModel || otherModel == null ||
          thisModel == null && AttributeUtil.getAttribute(this, IConnectionManager.URI_ATTRIBUTE_NAME) == null)
      {
         implementationProcess = newImplementationProcess;
         setExternalRef(null);
         AttributeUtil.setAttribute(this, IConnectionManager.URI_ATTRIBUTE_NAME, null);
      }
      else
      {
         implementationProcess = null;
         IdRef ref = getExternalRef();
         if (ref == null)
         {
            ref = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
            setExternalRef(ref);
         }
         ref.set(newImplementationProcess);
         String uri = AttributeUtil.getAttributeValue(newImplementationProcess, IConnectionManager.URI_ATTRIBUTE_NAME);
         AttributeUtil.setAttribute(this, IConnectionManager.URI_ATTRIBUTE_NAME, uri);
      }

      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION_PROCESS, oldImplementationProcess, newImplementationProcess);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public void setImplementationProcess(ProcessDefinitionType newImplementationProcess)
   {
      if (newImplementationProcess != getImplementationProcess())
      {
         NotificationChain msgs = null;
         if (implementationProcess != null)
            msgs = ((InternalEObject)implementationProcess).eInverseRemove(this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES, ProcessDefinitionType.class, msgs);
         if (newImplementationProcess != null)
            msgs = ((InternalEObject)newImplementationProcess).eInverseAdd(this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES, ProcessDefinitionType.class, msgs);
         msgs = basicSetImplementationProcess(newImplementationProcess, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION_PROCESS, newImplementationProcess, newImplementationProcess));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public JoinSplitType getJoin()
   {
      return join;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setJoin(JoinSplitType newJoin)
   {
      JoinSplitType oldJoin = join;
      join = newJoin == null ? JOIN_EDEFAULT : newJoin;
      boolean oldJoinESet = joinESet;
      joinESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__JOIN, oldJoin, join, !oldJoinESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetJoin()
   {
      JoinSplitType oldJoin = join;
      boolean oldJoinESet = joinESet;
      join = JOIN_EDEFAULT;
      joinESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__JOIN, oldJoin, JOIN_EDEFAULT, oldJoinESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetJoin()
   {
      return joinESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getLoopCondition()
   {
      return loopCondition;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoopCondition(String newLoopCondition)
   {
      String oldLoopCondition = loopCondition;
      loopCondition = newLoopCondition;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_CONDITION, oldLoopCondition, loopCondition));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopType getLoopType()
   {
      return loopType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoopType(LoopType newLoopType)
   {
      LoopType oldLoopType = loopType;
      loopType = newLoopType == null ? LOOP_TYPE_EDEFAULT : newLoopType;
      boolean oldLoopTypeESet = loopTypeESet;
      loopTypeESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_TYPE, oldLoopType, loopType, !oldLoopTypeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetLoopType()
   {
      LoopType oldLoopType = loopType;
      boolean oldLoopTypeESet = loopTypeESet;
      loopType = LOOP_TYPE_EDEFAULT;
      loopTypeESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_TYPE, oldLoopType, LOOP_TYPE_EDEFAULT, oldLoopTypeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetLoopType()
   {
      return loopTypeESet;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
   public IModelParticipant getPerformer()
   {
      return performer;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetPerformer(IModelParticipant newPerformer, NotificationChain msgs)
   {
      IModelParticipant oldPerformer = performer;
      performer = newPerformer;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__PERFORMER, oldPerformer, newPerformer);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPerformer(IModelParticipant newPerformer)
   {
      if (newPerformer != performer)
      {
         NotificationChain msgs = null;
         if (performer != null)
            msgs = ((InternalEObject)performer).eInverseRemove(this, CarnotWorkflowModelPackage.IMODEL_PARTICIPANT__PERFORMED_ACTIVITIES, IModelParticipant.class, msgs);
         if (newPerformer != null)
            msgs = ((InternalEObject)newPerformer).eInverseAdd(this, CarnotWorkflowModelPackage.IMODEL_PARTICIPANT__PERFORMED_ACTIVITIES, IModelParticipant.class, msgs);
         msgs = basicSetPerformer(newPerformer, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__PERFORMER, newPerformer, newPerformer));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public IModelParticipant getQualityControlPerformer()
   {
      return qualityControlPerformer;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setQualityControlPerformer(IModelParticipant newQualityControlPerformer)
   {
      IModelParticipant oldQualityControlPerformer = qualityControlPerformer;
      qualityControlPerformer = newQualityControlPerformer;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__QUALITY_CONTROL_PERFORMER, oldQualityControlPerformer, qualityControlPerformer));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public JoinSplitType getSplit()
   {
      return split;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSplit(JoinSplitType newSplit)
   {
      JoinSplitType oldSplit = split;
      split = newSplit == null ? SPLIT_EDEFAULT : newSplit;
      boolean oldSplitESet = splitESet;
      splitESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__SPLIT, oldSplit, split, !oldSplitESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetSplit()
   {
      JoinSplitType oldSplit = split;
      boolean oldSplitESet = splitESet;
      split = SPLIT_EDEFAULT;
      splitESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__SPLIT, oldSplit, SPLIT_EDEFAULT, oldSplitESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetSplit()
   {
      return splitESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SubProcessModeType getSubProcessMode()
   {
      return subProcessMode;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSubProcessMode(SubProcessModeType newSubProcessMode)
   {
      SubProcessModeType oldSubProcessMode = subProcessMode;
      subProcessMode = newSubProcessMode == null ? SUB_PROCESS_MODE_EDEFAULT : newSubProcessMode;
      boolean oldSubProcessModeESet = subProcessModeESet;
      subProcessModeESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__SUB_PROCESS_MODE, oldSubProcessMode, subProcessMode, !oldSubProcessModeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetSubProcessMode()
   {
      SubProcessModeType oldSubProcessMode = subProcessMode;
      boolean oldSubProcessModeESet = subProcessModeESet;
      subProcessMode = SUB_PROCESS_MODE_EDEFAULT;
      subProcessModeESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__SUB_PROCESS_MODE, oldSubProcessMode, SUB_PROCESS_MODE_EDEFAULT, oldSubProcessModeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetSubProcessMode()
   {
      return subProcessModeESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ActivitySymbolType> getActivitySymbols()
   {
      if (activitySymbols == null)
      {
         activitySymbols = new EObjectWithInverseResolvingEList<ActivitySymbolType>(ActivitySymbolType.class, this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__ACTIVITY);
      }
      return activitySymbols;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<StartEventSymbol> getStartingEventSymbols()
   {
      if (startingEventSymbols == null)
      {
         startingEventSymbols = new EObjectWithInverseResolvingEList<StartEventSymbol>(StartEventSymbol.class, this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS, CarnotWorkflowModelPackage.START_EVENT_SYMBOL__START_ACTIVITY);
      }
      return startingEventSymbols;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TransitionType> getInTransitions()
   {
      if (inTransitions == null)
      {
         inTransitions = new EObjectWithInverseResolvingEList<TransitionType>(TransitionType.class, this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS, CarnotWorkflowModelPackage.TRANSITION_TYPE__TO);
      }
      return inTransitions;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TransitionType> getOutTransitions()
   {
      if (outTransitions == null)
      {
         outTransitions = new EObjectWithInverseResolvingEList<TransitionType>(TransitionType.class, this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS, CarnotWorkflowModelPackage.TRANSITION_TYPE__FROM);
      }
      return outTransitions;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public IdRef getExternalRef()
   {
      return externalRef;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetExternalRef(IdRef newExternalRef, NotificationChain msgs)
   {
      IdRef oldExternalRef = externalRef;
      externalRef = newExternalRef;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF, oldExternalRef, newExternalRef);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setExternalRef(IdRef newExternalRef)
   {
      if (newExternalRef != externalRef)
      {
         NotificationChain msgs = null;
         if (externalRef != null)
            msgs = ((InternalEObject)externalRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF, null, msgs);
         if (newExternalRef != null)
            msgs = ((InternalEObject)newExternalRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF, null, msgs);
         msgs = basicSetExternalRef(newExternalRef, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF, newExternalRef, newExternalRef));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<Code> getValidQualityCodes()
   {
      if (validQualityCodes == null)
      {
         validQualityCodes = new EObjectResolvingEList<Code>(Code.class, this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__VALID_QUALITY_CODES);
      }
      return validQualityCodes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public org.eclipse.stardust.model.xpdl.xpdl2.LoopType getLoop()
   {
      return loop;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetLoop(org.eclipse.stardust.model.xpdl.xpdl2.LoopType newLoop, NotificationChain msgs)
   {
      org.eclipse.stardust.model.xpdl.xpdl2.LoopType oldLoop = loop;
      loop = newLoop;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP, oldLoop, newLoop);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoop(org.eclipse.stardust.model.xpdl.xpdl2.LoopType newLoop)
   {
      if (newLoop != loop)
      {
         NotificationChain msgs = null;
         if (loop != null)
            msgs = ((InternalEObject)loop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP, null, msgs);
         if (newLoop != null)
            msgs = ((InternalEObject)newLoop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP, null, msgs);
         msgs = basicSetLoop(newLoop, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP, newLoop, newLoop));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public EList getSymbols()
   {
      return getActivitySymbols();
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
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__APPLICATION:
            if (application != null)
               msgs = ((InternalEObject)application).eInverseRemove(this, CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES, ApplicationType.class, msgs);
            return basicSetApplication((ApplicationType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION_PROCESS:
            if (implementationProcess != null)
               msgs = ((InternalEObject)implementationProcess).eInverseRemove(this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES, ProcessDefinitionType.class, msgs);
            return basicSetImplementationProcess((ProcessDefinitionType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__PERFORMER:
            if (performer != null)
               msgs = ((InternalEObject)performer).eInverseRemove(this, CarnotWorkflowModelPackage.IMODEL_PARTICIPANT__PERFORMED_ACTIVITIES, IModelParticipant.class, msgs);
            return basicSetPerformer((IModelParticipant)otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getActivitySymbols()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getStartingEventSymbols()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getInTransitions()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutTransitions()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EVENT_HANDLER:
            return ((InternalEList<?>)getEventHandler()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF:
            return basicSetExternalRef(null, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DATA_MAPPING:
            return ((InternalEList<?>)getDataMapping()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__APPLICATION:
            return basicSetApplication(null, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION_PROCESS:
            return basicSetImplementationProcess(null, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__PERFORMER:
            return basicSetPerformer(null, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS:
            return ((InternalEList<?>)getActivitySymbols()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS:
            return ((InternalEList<?>)getStartingEventSymbols()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS:
            return ((InternalEList<?>)getInTransitions()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS:
            return ((InternalEList<?>)getOutTransitions()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP:
            return basicSetLoop(null, msgs);
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
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EVENT_HANDLER:
            return getEventHandler();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF:
            return getExternalRef();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DATA_MAPPING:
            return getDataMapping();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ALLOWS_ABORT_BY_PERFORMER:
            return isAllowsAbortByPerformer();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__APPLICATION:
            return getApplication();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__HIBERNATE_ON_CREATION:
            return isHibernateOnCreation();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION:
            return getImplementation();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION_PROCESS:
            return getImplementationProcess();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__JOIN:
            return getJoin();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_CONDITION:
            return getLoopCondition();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_TYPE:
            return getLoopType();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__PERFORMER:
            return getPerformer();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__QUALITY_CONTROL_PERFORMER:
            return getQualityControlPerformer();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__SPLIT:
            return getSplit();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__SUB_PROCESS_MODE:
            return getSubProcessMode();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS:
            return getActivitySymbols();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS:
            return getStartingEventSymbols();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS:
            return getInTransitions();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS:
            return getOutTransitions();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__VALID_QUALITY_CODES:
            return getValidQualityCodes();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP:
            return getLoop();
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
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EVENT_HANDLER:
            getEventHandler().clear();
            getEventHandler().addAll((Collection<? extends EventHandlerType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF:
            setExternalRef((IdRef)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DATA_MAPPING:
            getDataMapping().clear();
            getDataMapping().addAll((Collection<? extends DataMappingType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ALLOWS_ABORT_BY_PERFORMER:
            setAllowsAbortByPerformer((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__APPLICATION:
            setApplication((ApplicationType)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__HIBERNATE_ON_CREATION:
            setHibernateOnCreation((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION:
            setImplementation((ActivityImplementationType)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION_PROCESS:
            setImplementationProcess((ProcessDefinitionType)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__JOIN:
            setJoin((JoinSplitType)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_CONDITION:
            setLoopCondition((String)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_TYPE:
            setLoopType((LoopType)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__PERFORMER:
            setPerformer((IModelParticipant)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__QUALITY_CONTROL_PERFORMER:
            setQualityControlPerformer((IModelParticipant)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__SPLIT:
            setSplit((JoinSplitType)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__SUB_PROCESS_MODE:
            setSubProcessMode((SubProcessModeType)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS:
            getActivitySymbols().clear();
            getActivitySymbols().addAll((Collection<? extends ActivitySymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS:
            getStartingEventSymbols().clear();
            getStartingEventSymbols().addAll((Collection<? extends StartEventSymbol>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS:
            getInTransitions().clear();
            getInTransitions().addAll((Collection<? extends TransitionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS:
            getOutTransitions().clear();
            getOutTransitions().addAll((Collection<? extends TransitionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__VALID_QUALITY_CODES:
            getValidQualityCodes().clear();
            getValidQualityCodes().addAll((Collection<? extends Code>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP:
            setLoop((org.eclipse.stardust.model.xpdl.xpdl2.LoopType)newValue);
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
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EVENT_HANDLER:
            getEventHandler().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF:
            setExternalRef((IdRef)null);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DATA_MAPPING:
            getDataMapping().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ALLOWS_ABORT_BY_PERFORMER:
            unsetAllowsAbortByPerformer();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__APPLICATION:
            setApplication((ApplicationType)null);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__HIBERNATE_ON_CREATION:
            unsetHibernateOnCreation();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION:
            setImplementation(IMPLEMENTATION_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION_PROCESS:
            setImplementationProcess((ProcessDefinitionType)null);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__JOIN:
            unsetJoin();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_CONDITION:
            setLoopCondition(LOOP_CONDITION_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_TYPE:
            unsetLoopType();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__PERFORMER:
            setPerformer((IModelParticipant)null);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__QUALITY_CONTROL_PERFORMER:
            setQualityControlPerformer((IModelParticipant)null);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__SPLIT:
            unsetSplit();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__SUB_PROCESS_MODE:
            unsetSubProcessMode();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS:
            getActivitySymbols().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS:
            getStartingEventSymbols().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS:
            getInTransitions().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS:
            getOutTransitions().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__VALID_QUALITY_CODES:
            getValidQualityCodes().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP:
            setLoop((org.eclipse.stardust.model.xpdl.xpdl2.LoopType)null);
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
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EVENT_HANDLER:
            return eventHandler != null && !eventHandler.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF:
            return externalRef != null;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__DATA_MAPPING:
            return dataMapping != null && !dataMapping.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ALLOWS_ABORT_BY_PERFORMER:
            return isSetAllowsAbortByPerformer();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__APPLICATION:
            return application != null;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__HIBERNATE_ON_CREATION:
            return isSetHibernateOnCreation();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION:
            return implementation != IMPLEMENTATION_EDEFAULT;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION_PROCESS:
            return implementationProcess != null;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__JOIN:
            return isSetJoin();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_CONDITION:
            return LOOP_CONDITION_EDEFAULT == null ? loopCondition != null : !LOOP_CONDITION_EDEFAULT.equals(loopCondition);
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP_TYPE:
            return isSetLoopType();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__PERFORMER:
            return performer != null;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__QUALITY_CONTROL_PERFORMER:
            return qualityControlPerformer != null;
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__SPLIT:
            return isSetSplit();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__SUB_PROCESS_MODE:
            return isSetSubProcessMode();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS:
            return activitySymbols != null && !activitySymbols.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS:
            return startingEventSymbols != null && !startingEventSymbols.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__IN_TRANSITIONS:
            return inTransitions != null && !inTransitions.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__OUT_TRANSITIONS:
            return outTransitions != null && !outTransitions.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__VALID_QUALITY_CODES:
            return validQualityCodes != null && !validQualityCodes.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_TYPE__LOOP:
            return loop != null;
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
            case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.ACTIVITY_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.ACTIVITY_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
            default: return -1;
         }
      }
      if (baseClass == IEventHandlerOwner.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EVENT_HANDLER: return CarnotWorkflowModelPackage.IEVENT_HANDLER_OWNER__EVENT_HANDLER;
            default: return -1;
         }
      }
      if (baseClass == IdRefOwner.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF: return CarnotWorkflowModelPackage.ID_REF_OWNER__EXTERNAL_REF;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.ACTIVITY_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.ACTIVITY_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.ACTIVITY_TYPE__ATTRIBUTE;
            default: return -1;
         }
      }
      if (baseClass == IEventHandlerOwner.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEVENT_HANDLER_OWNER__EVENT_HANDLER: return CarnotWorkflowModelPackage.ACTIVITY_TYPE__EVENT_HANDLER;
            default: return -1;
         }
      }
      if (baseClass == IdRefOwner.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.ID_REF_OWNER__EXTERNAL_REF: return CarnotWorkflowModelPackage.ACTIVITY_TYPE__EXTERNAL_REF;
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
      result.append(", allowsAbortByPerformer: "); //$NON-NLS-1$
      if (allowsAbortByPerformerESet) result.append(allowsAbortByPerformer); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", hibernateOnCreation: "); //$NON-NLS-1$
      if (hibernateOnCreationESet) result.append(hibernateOnCreation); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", implementation: "); //$NON-NLS-1$
      result.append(implementation);
      result.append(", join: "); //$NON-NLS-1$
      if (joinESet) result.append(join); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", loopCondition: "); //$NON-NLS-1$
      result.append(loopCondition);
      result.append(", loopType: "); //$NON-NLS-1$
      if (loopTypeESet) result.append(loopType); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", split: "); //$NON-NLS-1$
      if (splitESet) result.append(split); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", subProcessMode: "); //$NON-NLS-1$
      if (subProcessModeESet) result.append(subProcessMode); else result.append("<unset>"); //$NON-NLS-1$
      result.append(')');
      return result.toString();
   }

} //ActivityTypeImpl
