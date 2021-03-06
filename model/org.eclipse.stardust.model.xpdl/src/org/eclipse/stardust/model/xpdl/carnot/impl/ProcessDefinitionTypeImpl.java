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
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.IdRefOwner;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Definition Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getEventHandler <em>Event Handler</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getExternalRef <em>External Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getExecutingActivities <em>Executing Activities</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getProcessSymbols <em>Process Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getDefaultPriority <em>Default Priority</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ProcessDefinitionTypeImpl#getFormalParameterMappings <em>Formal Parameter Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessDefinitionTypeImpl extends EObjectImpl implements ProcessDefinitionType
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
    * The cached value of the '{@link #getActivity() <em>Activity</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getActivity()
    * @generated
    * @ordered
    */
   protected EList<ActivityType> activity;

   /**
    * The cached value of the '{@link #getTransition() <em>Transition</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTransition()
    * @generated
    * @ordered
    */
   protected EList<TransitionType> transition;

   /**
    * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTrigger()
    * @generated
    * @ordered
    */
   protected EList<TriggerType> trigger;

   /**
    * The cached value of the '{@link #getDataPath() <em>Data Path</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDataPath()
    * @generated
    * @ordered
    */
   protected EList<DataPathType> dataPath;

   /**
    * The cached value of the '{@link #getDiagram() <em>Diagram</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDiagram()
    * @generated
    * @ordered
    */
   protected EList<DiagramType> diagram;

   /**
    * The cached value of the '{@link #getExecutingActivities() <em>Executing Activities</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExecutingActivities()
    * @generated
    * @ordered
    */
   protected EList<ActivityType> executingActivities;

   /**
    * The cached value of the '{@link #getProcessSymbols() <em>Process Symbols</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getProcessSymbols()
    * @generated
    * @ordered
    */
   protected EList<ProcessSymbolType> processSymbols;

   /**
    * The default value of the '{@link #getDefaultPriority() <em>Default Priority</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDefaultPriority()
    * @generated
    * @ordered
    */
   protected static final int DEFAULT_PRIORITY_EDEFAULT = 0;

   /**
    * The cached value of the '{@link #getDefaultPriority() <em>Default Priority</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDefaultPriority()
    * @generated
    * @ordered
    */
   protected int defaultPriority = DEFAULT_PRIORITY_EDEFAULT;

   /**
    * The cached value of the '{@link #getFormalParameters() <em>Formal Parameters</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getFormalParameters()
    * @generated
    * @ordered
    */
   protected FormalParametersType formalParameters;

   /**
    * The cached value of the '{@link #getFormalParameterMappings() <em>Formal Parameter Mappings</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getFormalParameterMappings()
    * @generated
    * @ordered
    */
   protected FormalParameterMappingsType formalParameterMappings;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ProcessDefinitionTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION, newDescription, newDescription));
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ActivityType> getActivity()
   {
      if (activity == null)
      {
         activity = new EObjectContainmentEList<ActivityType>(ActivityType.class, this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ACTIVITY);
      }
      return activity;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TransitionType> getTransition()
   {
      if (transition == null)
      {
         transition = new EObjectContainmentEList<TransitionType>(TransitionType.class, this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRANSITION);
      }
      return transition;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TriggerType> getTrigger()
   {
      if (trigger == null)
      {
         trigger = new EObjectContainmentEList<TriggerType>(TriggerType.class, this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRIGGER);
      }
      return trigger;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DataPathType> getDataPath()
   {
      if (dataPath == null)
      {
         dataPath = new EObjectContainmentEList<DataPathType>(DataPathType.class, this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DATA_PATH);
      }
      return dataPath;
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
         eventHandler = new EObjectContainmentEList<EventHandlerType>(EventHandlerType.class, this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EVENT_HANDLER);
      }
      return eventHandler;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DiagramType> getDiagram()
   {
      if (diagram == null)
      {
         diagram = new EObjectContainmentEList<DiagramType>(DiagramType.class, this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DIAGRAM);
      }
      return diagram;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ActivityType> getExecutingActivities()
   {
      if (executingActivities == null)
      {
         executingActivities = new EObjectWithInverseResolvingEList<ActivityType>(ActivityType.class, this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES, CarnotWorkflowModelPackage.ACTIVITY_TYPE__IMPLEMENTATION_PROCESS);
      }
      return executingActivities;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ProcessSymbolType> getProcessSymbols()
   {
      if (processSymbols == null)
      {
         processSymbols = new EObjectWithInverseResolvingEList<ProcessSymbolType>(ProcessSymbolType.class, this, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS, CarnotWorkflowModelPackage.PROCESS_SYMBOL_TYPE__PROCESS);
      }
      return processSymbols;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public int getDefaultPriority()
   {
      return defaultPriority;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setDefaultPriority(int newDefaultPriority)
   {
      int oldDefaultPriority = defaultPriority;
      defaultPriority = newDefaultPriority;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DEFAULT_PRIORITY, oldDefaultPriority, defaultPriority));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FormalParametersType getFormalParameters()
   {
      return formalParameters;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetFormalParameters(FormalParametersType newFormalParameters, NotificationChain msgs)
   {
      FormalParametersType oldFormalParameters = formalParameters;
      formalParameters = newFormalParameters;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS, oldFormalParameters, newFormalParameters);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setFormalParameters(FormalParametersType newFormalParameters)
   {
      if (newFormalParameters != formalParameters)
      {
         NotificationChain msgs = null;
         if (formalParameters != null)
            msgs = ((InternalEObject)formalParameters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS, null, msgs);
         if (newFormalParameters != null)
            msgs = ((InternalEObject)newFormalParameters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS, null, msgs);
         msgs = basicSetFormalParameters(newFormalParameters, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS, newFormalParameters, newFormalParameters));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FormalParameterMappingsType getFormalParameterMappings()
   {
      return formalParameterMappings;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetFormalParameterMappings(FormalParameterMappingsType newFormalParameterMappings, NotificationChain msgs)
   {
      FormalParameterMappingsType oldFormalParameterMappings = formalParameterMappings;
      formalParameterMappings = newFormalParameterMappings;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS, oldFormalParameterMappings, newFormalParameterMappings);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setFormalParameterMappings(FormalParameterMappingsType newFormalParameterMappings)
   {
      if (newFormalParameterMappings != formalParameterMappings)
      {
         NotificationChain msgs = null;
         if (formalParameterMappings != null)
            msgs = ((InternalEObject)formalParameterMappings).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS, null, msgs);
         if (newFormalParameterMappings != null)
            msgs = ((InternalEObject)newFormalParameterMappings).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS, null, msgs);
         msgs = basicSetFormalParameterMappings(newFormalParameterMappings, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS, newFormalParameterMappings, newFormalParameterMappings));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF, oldExternalRef, newExternalRef);
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
            msgs = ((InternalEObject)externalRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF, null, msgs);
         if (newExternalRef != null)
            msgs = ((InternalEObject)newExternalRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF, null, msgs);
         msgs = basicSetExternalRef(newExternalRef, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF, newExternalRef, newExternalRef));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public EList getSymbols()
   {
      return getProcessSymbols();
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
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecutingActivities()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getProcessSymbols()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EVENT_HANDLER:
            return ((InternalEList<?>)getEventHandler()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF:
            return basicSetExternalRef(null, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ACTIVITY:
            return ((InternalEList<?>)getActivity()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRANSITION:
            return ((InternalEList<?>)getTransition()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRIGGER:
            return ((InternalEList<?>)getTrigger()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DATA_PATH:
            return ((InternalEList<?>)getDataPath()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DIAGRAM:
            return ((InternalEList<?>)getDiagram()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES:
            return ((InternalEList<?>)getExecutingActivities()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS:
            return ((InternalEList<?>)getProcessSymbols()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS:
            return basicSetFormalParameters(null, msgs);
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS:
            return basicSetFormalParameterMappings(null, msgs);
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
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EVENT_HANDLER:
            return getEventHandler();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF:
            return getExternalRef();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ACTIVITY:
            return getActivity();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRANSITION:
            return getTransition();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRIGGER:
            return getTrigger();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DATA_PATH:
            return getDataPath();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DIAGRAM:
            return getDiagram();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES:
            return getExecutingActivities();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS:
            return getProcessSymbols();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DEFAULT_PRIORITY:
            return getDefaultPriority();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS:
            return getFormalParameters();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS:
            return getFormalParameterMappings();
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
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EVENT_HANDLER:
            getEventHandler().clear();
            getEventHandler().addAll((Collection<? extends EventHandlerType>)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF:
            setExternalRef((IdRef)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ACTIVITY:
            getActivity().clear();
            getActivity().addAll((Collection<? extends ActivityType>)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRANSITION:
            getTransition().clear();
            getTransition().addAll((Collection<? extends TransitionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRIGGER:
            getTrigger().clear();
            getTrigger().addAll((Collection<? extends TriggerType>)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DATA_PATH:
            getDataPath().clear();
            getDataPath().addAll((Collection<? extends DataPathType>)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DIAGRAM:
            getDiagram().clear();
            getDiagram().addAll((Collection<? extends DiagramType>)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES:
            getExecutingActivities().clear();
            getExecutingActivities().addAll((Collection<? extends ActivityType>)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS:
            getProcessSymbols().clear();
            getProcessSymbols().addAll((Collection<? extends ProcessSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DEFAULT_PRIORITY:
            setDefaultPriority((Integer)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS:
            setFormalParameters((FormalParametersType)newValue);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS:
            setFormalParameterMappings((FormalParameterMappingsType)newValue);
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
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EVENT_HANDLER:
            getEventHandler().clear();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF:
            setExternalRef((IdRef)null);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ACTIVITY:
            getActivity().clear();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRANSITION:
            getTransition().clear();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRIGGER:
            getTrigger().clear();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DATA_PATH:
            getDataPath().clear();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DIAGRAM:
            getDiagram().clear();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES:
            getExecutingActivities().clear();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS:
            getProcessSymbols().clear();
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DEFAULT_PRIORITY:
            setDefaultPriority(DEFAULT_PRIORITY_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS:
            setFormalParameters((FormalParametersType)null);
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS:
            setFormalParameterMappings((FormalParameterMappingsType)null);
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
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EVENT_HANDLER:
            return eventHandler != null && !eventHandler.isEmpty();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF:
            return externalRef != null;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ACTIVITY:
            return activity != null && !activity.isEmpty();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRANSITION:
            return transition != null && !transition.isEmpty();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRIGGER:
            return trigger != null && !trigger.isEmpty();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DATA_PATH:
            return dataPath != null && !dataPath.isEmpty();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DIAGRAM:
            return diagram != null && !diagram.isEmpty();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES:
            return executingActivities != null && !executingActivities.isEmpty();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS:
            return processSymbols != null && !processSymbols.isEmpty();
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DEFAULT_PRIORITY:
            return defaultPriority != DEFAULT_PRIORITY_EDEFAULT;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS:
            return formalParameters != null;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS:
            return formalParameterMappings != null;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
            case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
            default: return -1;
         }
      }
      if (baseClass == IEventHandlerOwner.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EVENT_HANDLER: return CarnotWorkflowModelPackage.IEVENT_HANDLER_OWNER__EVENT_HANDLER;
            default: return -1;
         }
      }
      if (baseClass == IdRefOwner.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF: return CarnotWorkflowModelPackage.ID_REF_OWNER__EXTERNAL_REF;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ATTRIBUTE;
            default: return -1;
         }
      }
      if (baseClass == IEventHandlerOwner.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEVENT_HANDLER_OWNER__EVENT_HANDLER: return CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EVENT_HANDLER;
            default: return -1;
         }
      }
      if (baseClass == IdRefOwner.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.ID_REF_OWNER__EXTERNAL_REF: return CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF;
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
      result.append(", defaultPriority: "); //$NON-NLS-1$
      result.append(defaultPriority);
      result.append(')');
      return result.toString();
   }

} //ProcessDefinitionTypeImpl
