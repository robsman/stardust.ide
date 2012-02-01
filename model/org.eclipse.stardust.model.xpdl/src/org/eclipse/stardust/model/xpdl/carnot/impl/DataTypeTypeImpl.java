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
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#isIsPredefined <em>Is Predefined</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getAccessPathEditor <em>Access Path Editor</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getEvaluator <em>Evaluator</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getInstanceClass <em>Instance Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#isReadable <em>Readable</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getStorageStrategy <em>Storage Strategy</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getValueCreator <em>Value Creator</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#isWritable <em>Writable</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeTypeImpl#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataTypeTypeImpl extends EObjectImpl implements DataTypeType
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
    * The default value of the '{@link #getAccessPathEditor() <em>Access Path Editor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAccessPathEditor()
    * @generated
    * @ordered
    */
   protected static final String ACCESS_PATH_EDITOR_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getAccessPathEditor() <em>Access Path Editor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAccessPathEditor()
    * @generated
    * @ordered
    */
   protected String accessPathEditor = ACCESS_PATH_EDITOR_EDEFAULT;

   /**
    * The default value of the '{@link #getEvaluator() <em>Evaluator</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getEvaluator()
    * @generated
    * @ordered
    */
   protected static final String EVALUATOR_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getEvaluator() <em>Evaluator</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getEvaluator()
    * @generated
    * @ordered
    */
   protected String evaluator = EVALUATOR_EDEFAULT;

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
    * The default value of the '{@link #isReadable() <em>Readable</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isReadable()
    * @generated
    * @ordered
    */
   protected static final boolean READABLE_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isReadable() <em>Readable</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isReadable()
    * @generated
    * @ordered
    */
   protected boolean readable = READABLE_EDEFAULT;

   /**
    * This is true if the Readable attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean readableESet;

   /**
    * The default value of the '{@link #getStorageStrategy() <em>Storage Strategy</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStorageStrategy()
    * @generated
    * @ordered
    */
   protected static final String STORAGE_STRATEGY_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getStorageStrategy() <em>Storage Strategy</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStorageStrategy()
    * @generated
    * @ordered
    */
   protected String storageStrategy = STORAGE_STRATEGY_EDEFAULT;

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
    * The default value of the '{@link #getValueCreator() <em>Value Creator</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValueCreator()
    * @generated
    * @ordered
    */
   protected static final String VALUE_CREATOR_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getValueCreator() <em>Value Creator</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValueCreator()
    * @generated
    * @ordered
    */
   protected String valueCreator = VALUE_CREATOR_EDEFAULT;

   /**
    * The default value of the '{@link #isWritable() <em>Writable</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isWritable()
    * @generated
    * @ordered
    */
   protected static final boolean WRITABLE_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isWritable() <em>Writable</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isWritable()
    * @generated
    * @ordered
    */
   protected boolean writable = WRITABLE_EDEFAULT;

   /**
    * This is true if the Writable attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean writableESet;

   /**
    * The cached value of the '{@link #getData() <em>Data</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getData()
    * @generated
    * @ordered
    */
   protected EList<DataType> data;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected DataTypeTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION, newDescription, newDescription));
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getAccessPathEditor()
   {
      return accessPathEditor;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAccessPathEditor(String newAccessPathEditor)
   {
      String oldAccessPathEditor = accessPathEditor;
      accessPathEditor = newAccessPathEditor;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ACCESS_PATH_EDITOR, oldAccessPathEditor, accessPathEditor));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getEvaluator()
   {
      return evaluator;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setEvaluator(String newEvaluator)
   {
      String oldEvaluator = evaluator;
      evaluator = newEvaluator;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__EVALUATOR, oldEvaluator, evaluator));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__INSTANCE_CLASS, oldInstanceClass, instanceClass));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, isPredefined, !oldIsPredefinedESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, IS_PREDEFINED_EDEFAULT, oldIsPredefinedESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__PANEL_CLASS, oldPanelClass, panelClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isReadable()
   {
      return readable;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setReadable(boolean newReadable)
   {
      boolean oldReadable = readable;
      readable = newReadable;
      boolean oldReadableESet = readableESet;
      readableESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__READABLE, oldReadable, readable, !oldReadableESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetReadable()
   {
      boolean oldReadable = readable;
      boolean oldReadableESet = readableESet;
      readable = READABLE_EDEFAULT;
      readableESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__READABLE, oldReadable, READABLE_EDEFAULT, oldReadableESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetReadable()
   {
      return readableESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getStorageStrategy()
   {
      return storageStrategy;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setStorageStrategy(String newStorageStrategy)
   {
      String oldStorageStrategy = storageStrategy;
      storageStrategy = newStorageStrategy;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__STORAGE_STRATEGY, oldStorageStrategy, storageStrategy));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALIDATOR_CLASS, oldValidatorClass, validatorClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getValueCreator()
   {
      return valueCreator;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setValueCreator(String newValueCreator)
   {
      String oldValueCreator = valueCreator;
      valueCreator = newValueCreator;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALUE_CREATOR, oldValueCreator, valueCreator));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isWritable()
   {
      return writable;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setWritable(boolean newWritable)
   {
      boolean oldWritable = writable;
      writable = newWritable;
      boolean oldWritableESet = writableESet;
      writableESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__WRITABLE, oldWritable, writable, !oldWritableESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetWritable()
   {
      boolean oldWritable = writable;
      boolean oldWritableESet = writableESet;
      writable = WRITABLE_EDEFAULT;
      writableESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__WRITABLE, oldWritable, WRITABLE_EDEFAULT, oldWritableESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetWritable()
   {
      return writableESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DataType> getData()
   {
      if (data == null)
      {
         data = new EObjectWithInverseResolvingEList<DataType>(DataType.class, this, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA, CarnotWorkflowModelPackage.DATA_TYPE__TYPE);
      }
      return data;
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
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getData()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA:
            return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__IS_PREDEFINED:
            return isIsPredefined();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ACCESS_PATH_EDITOR:
            return getAccessPathEditor();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__EVALUATOR:
            return getEvaluator();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__INSTANCE_CLASS:
            return getInstanceClass();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__PANEL_CLASS:
            return getPanelClass();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__READABLE:
            return isReadable();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__STORAGE_STRATEGY:
            return getStorageStrategy();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALIDATOR_CLASS:
            return getValidatorClass();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALUE_CREATOR:
            return getValueCreator();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__WRITABLE:
            return isWritable();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA:
            return getData();
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
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__IS_PREDEFINED:
            setIsPredefined((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ACCESS_PATH_EDITOR:
            setAccessPathEditor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__EVALUATOR:
            setEvaluator((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__INSTANCE_CLASS:
            setInstanceClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__PANEL_CLASS:
            setPanelClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__READABLE:
            setReadable((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__STORAGE_STRATEGY:
            setStorageStrategy((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALIDATOR_CLASS:
            setValidatorClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALUE_CREATOR:
            setValueCreator((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__WRITABLE:
            setWritable((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA:
            getData().clear();
            getData().addAll((Collection<? extends DataType>)newValue);
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
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__IS_PREDEFINED:
            unsetIsPredefined();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ACCESS_PATH_EDITOR:
            setAccessPathEditor(ACCESS_PATH_EDITOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__EVALUATOR:
            setEvaluator(EVALUATOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__INSTANCE_CLASS:
            setInstanceClass(INSTANCE_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__PANEL_CLASS:
            setPanelClass(PANEL_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__READABLE:
            unsetReadable();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__STORAGE_STRATEGY:
            setStorageStrategy(STORAGE_STRATEGY_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALIDATOR_CLASS:
            setValidatorClass(VALIDATOR_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALUE_CREATOR:
            setValueCreator(VALUE_CREATOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__WRITABLE:
            unsetWritable();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA:
            getData().clear();
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
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__IS_PREDEFINED:
            return isSetIsPredefined();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ACCESS_PATH_EDITOR:
            return ACCESS_PATH_EDITOR_EDEFAULT == null ? accessPathEditor != null : !ACCESS_PATH_EDITOR_EDEFAULT.equals(accessPathEditor);
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__EVALUATOR:
            return EVALUATOR_EDEFAULT == null ? evaluator != null : !EVALUATOR_EDEFAULT.equals(evaluator);
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__INSTANCE_CLASS:
            return INSTANCE_CLASS_EDEFAULT == null ? instanceClass != null : !INSTANCE_CLASS_EDEFAULT.equals(instanceClass);
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__PANEL_CLASS:
            return PANEL_CLASS_EDEFAULT == null ? panelClass != null : !PANEL_CLASS_EDEFAULT.equals(panelClass);
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__READABLE:
            return isSetReadable();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__STORAGE_STRATEGY:
            return STORAGE_STRATEGY_EDEFAULT == null ? storageStrategy != null : !STORAGE_STRATEGY_EDEFAULT.equals(storageStrategy);
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALIDATOR_CLASS:
            return VALIDATOR_CLASS_EDEFAULT == null ? validatorClass != null : !VALIDATOR_CLASS_EDEFAULT.equals(validatorClass);
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALUE_CREATOR:
            return VALUE_CREATOR_EDEFAULT == null ? valueCreator != null : !VALUE_CREATOR_EDEFAULT.equals(valueCreator);
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__WRITABLE:
            return isSetWritable();
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA:
            return data != null && !data.isEmpty();
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
      return CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getTypedElements()
   {
      return getData();
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
            case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.DATA_TYPE_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ATTRIBUTE;
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
      result.append(", accessPathEditor: "); //$NON-NLS-1$
      result.append(accessPathEditor);
      result.append(", evaluator: "); //$NON-NLS-1$
      result.append(evaluator);
      result.append(", instanceClass: "); //$NON-NLS-1$
      result.append(instanceClass);
      result.append(", panelClass: "); //$NON-NLS-1$
      result.append(panelClass);
      result.append(", readable: "); //$NON-NLS-1$
      if (readableESet) result.append(readable); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", storageStrategy: "); //$NON-NLS-1$
      result.append(storageStrategy);
      result.append(", validatorClass: "); //$NON-NLS-1$
      result.append(validatorClass);
      result.append(", valueCreator: "); //$NON-NLS-1$
      result.append(valueCreator);
      result.append(", writable: "); //$NON-NLS-1$
      if (writableESet) result.append(writable); else result.append("<unset>"); //$NON-NLS-1$
      result.append(')');
      return result.toString();
   }

} //DataTypeTypeImpl
