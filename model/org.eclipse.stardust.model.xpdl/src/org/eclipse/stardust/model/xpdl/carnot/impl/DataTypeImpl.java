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
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getDataMappings <em>Data Mappings</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#isPredefined <em>Predefined</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getDataSymbols <em>Data Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getConditionalPerformers <em>Conditional Performers</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getDataPaths <em>Data Paths</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getParameterMappings <em>Parameter Mappings</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataTypeImpl#getExternalReference <em>External Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataTypeImpl extends EObjectImpl implements DataType
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
    * The cached value of the '{@link #getDataMappings() <em>Data Mappings</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDataMappings()
    * @generated
    * @ordered
    */
   protected EList<DataMappingType> dataMappings;

   /**
    * The default value of the '{@link #isPredefined() <em>Predefined</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isPredefined()
    * @generated
    * @ordered
    */
   protected static final boolean PREDEFINED_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isPredefined() <em>Predefined</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isPredefined()
    * @generated
    * @ordered
    */
   protected boolean predefined = PREDEFINED_EDEFAULT;

   /**
    * This is true if the Predefined attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean predefinedESet;

   /**
    * The cached value of the '{@link #getType() <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
   protected DataTypeType type;

   /**
    * The cached value of the '{@link #getDataSymbols() <em>Data Symbols</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDataSymbols()
    * @generated
    * @ordered
    */
   protected EList<DataSymbolType> dataSymbols;

   /**
    * The cached value of the '{@link #getConditionalPerformers() <em>Conditional Performers</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getConditionalPerformers()
    * @generated
    * @ordered
    */
   protected EList<ConditionalPerformerType> conditionalPerformers;

   /**
    * The cached value of the '{@link #getDataPaths() <em>Data Paths</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDataPaths()
    * @generated
    * @ordered
    */
   protected EList<DataPathType> dataPaths;

   /**
    * The cached value of the '{@link #getParameterMappings() <em>Parameter Mappings</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParameterMappings()
    * @generated
    * @ordered
    */
   protected EList<ParameterMappingType> parameterMappings;

   /**
    * The cached value of the '{@link #getExternalReference() <em>External Reference</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExternalReference()
    * @generated
    * @ordered
    */
   protected ExternalReferenceType externalReference;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected DataTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.DATA_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.DATA_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.DATA_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__DESCRIPTION, newDescription, newDescription));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DataMappingType> getDataMappings()
   {
      if (dataMappings == null)
      {
         dataMappings = new EObjectWithInverseResolvingEList<DataMappingType>(DataMappingType.class, this, CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA);
      }
      return dataMappings;
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
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.DATA_TYPE__ATTRIBUTE);
      }
      return attribute;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
   public boolean isPredefined()
   {
      return predefined;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPredefined(boolean newPredefined)
   {
      boolean oldPredefined = predefined;
      predefined = newPredefined;
      boolean oldPredefinedESet = predefinedESet;
      predefinedESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__PREDEFINED, oldPredefined, predefined, !oldPredefinedESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetPredefined()
   {
      boolean oldPredefined = predefined;
      boolean oldPredefinedESet = predefinedESet;
      predefined = PREDEFINED_EDEFAULT;
      predefinedESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_TYPE__PREDEFINED, oldPredefined, PREDEFINED_EDEFAULT, oldPredefinedESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetPredefined()
   {
      return predefinedESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DataTypeType getType()
   {
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetType(DataTypeType newType, NotificationChain msgs)
   {
      DataTypeType oldType = type;
      type = newType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__TYPE, oldType, newType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setType(DataTypeType newType)
   {
      if (newType != type)
      {
         NotificationChain msgs = null;
         if (type != null)
            msgs = ((InternalEObject)type).eInverseRemove(this, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA, DataTypeType.class, msgs);
         if (newType != null)
            msgs = ((InternalEObject)newType).eInverseAdd(this, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA, DataTypeType.class, msgs);
         msgs = basicSetType(newType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__TYPE, newType, newType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DataSymbolType> getDataSymbols()
   {
      if (dataSymbols == null)
      {
         dataSymbols = new EObjectWithInverseResolvingEList<DataSymbolType>(DataSymbolType.class, this, CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS, CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA);
      }
      return dataSymbols;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ConditionalPerformerType> getConditionalPerformers()
   {
      if (conditionalPerformers == null)
      {
         conditionalPerformers = new EObjectWithInverseResolvingEList<ConditionalPerformerType>(ConditionalPerformerType.class, this, CarnotWorkflowModelPackage.DATA_TYPE__CONDITIONAL_PERFORMERS, CarnotWorkflowModelPackage.CONDITIONAL_PERFORMER_TYPE__DATA);
      }
      return conditionalPerformers;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DataPathType> getDataPaths()
   {
      if (dataPaths == null)
      {
         dataPaths = new EObjectWithInverseResolvingEList<DataPathType>(DataPathType.class, this, CarnotWorkflowModelPackage.DATA_TYPE__DATA_PATHS, CarnotWorkflowModelPackage.DATA_PATH_TYPE__DATA);
      }
      return dataPaths;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ParameterMappingType> getParameterMappings()
   {
      if (parameterMappings == null)
      {
         parameterMappings = new EObjectWithInverseResolvingEList<ParameterMappingType>(ParameterMappingType.class, this, CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS, CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA);
      }
      return parameterMappings;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExternalReferenceType getExternalReference()
   {
      return externalReference;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetExternalReference(ExternalReferenceType newExternalReference, NotificationChain msgs)
   {
      ExternalReferenceType oldExternalReference = externalReference;
      externalReference = newExternalReference;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__EXTERNAL_REFERENCE, oldExternalReference, newExternalReference);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setExternalReference(ExternalReferenceType newExternalReference)
   {
      if (newExternalReference != externalReference)
      {
         NotificationChain msgs = null;
         if (externalReference != null)
            msgs = ((InternalEObject)externalReference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.DATA_TYPE__EXTERNAL_REFERENCE, null, msgs);
         if (newExternalReference != null)
            msgs = ((InternalEObject)newExternalReference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.DATA_TYPE__EXTERNAL_REFERENCE, null, msgs);
         msgs = basicSetExternalReference(newExternalReference, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_TYPE__EXTERNAL_REFERENCE, newExternalReference, newExternalReference));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getSymbols()
   {
      return getDataSymbols();
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
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getDataMappings()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__TYPE:
            if (type != null)
               msgs = ((InternalEObject)type).eInverseRemove(this, CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DATA, DataTypeType.class, msgs);
            return basicSetType((DataTypeType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getDataSymbols()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__CONDITIONAL_PERFORMERS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getConditionalPerformers()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_PATHS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getDataPaths()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameterMappings()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.DATA_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS:
            return ((InternalEList<?>)getDataMappings()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__TYPE:
            return basicSetType(null, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS:
            return ((InternalEList<?>)getDataSymbols()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__CONDITIONAL_PERFORMERS:
            return ((InternalEList<?>)getConditionalPerformers()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_PATHS:
            return ((InternalEList<?>)getDataPaths()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS:
            return ((InternalEList<?>)getParameterMappings()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_TYPE__EXTERNAL_REFERENCE:
            return basicSetExternalReference(null, msgs);
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
         case CarnotWorkflowModelPackage.DATA_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.DATA_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.DATA_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.DATA_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.DATA_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS:
            return getDataMappings();
         case CarnotWorkflowModelPackage.DATA_TYPE__PREDEFINED:
            return isPredefined();
         case CarnotWorkflowModelPackage.DATA_TYPE__TYPE:
            return getType();
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS:
            return getDataSymbols();
         case CarnotWorkflowModelPackage.DATA_TYPE__CONDITIONAL_PERFORMERS:
            return getConditionalPerformers();
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_PATHS:
            return getDataPaths();
         case CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS:
            return getParameterMappings();
         case CarnotWorkflowModelPackage.DATA_TYPE__EXTERNAL_REFERENCE:
            return getExternalReference();
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
         case CarnotWorkflowModelPackage.DATA_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS:
            getDataMappings().clear();
            getDataMappings().addAll((Collection<? extends DataMappingType>)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__PREDEFINED:
            setPredefined((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__TYPE:
            setType((DataTypeType)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS:
            getDataSymbols().clear();
            getDataSymbols().addAll((Collection<? extends DataSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__CONDITIONAL_PERFORMERS:
            getConditionalPerformers().clear();
            getConditionalPerformers().addAll((Collection<? extends ConditionalPerformerType>)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_PATHS:
            getDataPaths().clear();
            getDataPaths().addAll((Collection<? extends DataPathType>)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS:
            getParameterMappings().clear();
            getParameterMappings().addAll((Collection<? extends ParameterMappingType>)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__EXTERNAL_REFERENCE:
            setExternalReference((ExternalReferenceType)newValue);
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
         case CarnotWorkflowModelPackage.DATA_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS:
            getDataMappings().clear();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__PREDEFINED:
            unsetPredefined();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__TYPE:
            setType((DataTypeType)null);
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS:
            getDataSymbols().clear();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__CONDITIONAL_PERFORMERS:
            getConditionalPerformers().clear();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_PATHS:
            getDataPaths().clear();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS:
            getParameterMappings().clear();
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE__EXTERNAL_REFERENCE:
            setExternalReference((ExternalReferenceType)null);
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
         case CarnotWorkflowModelPackage.DATA_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.DATA_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.DATA_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.DATA_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.DATA_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS:
            return dataMappings != null && !dataMappings.isEmpty();
         case CarnotWorkflowModelPackage.DATA_TYPE__PREDEFINED:
            return isSetPredefined();
         case CarnotWorkflowModelPackage.DATA_TYPE__TYPE:
            return type != null;
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS:
            return dataSymbols != null && !dataSymbols.isEmpty();
         case CarnotWorkflowModelPackage.DATA_TYPE__CONDITIONAL_PERFORMERS:
            return conditionalPerformers != null && !conditionalPerformers.isEmpty();
         case CarnotWorkflowModelPackage.DATA_TYPE__DATA_PATHS:
            return dataPaths != null && !dataPaths.isEmpty();
         case CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS:
            return parameterMappings != null && !parameterMappings.isEmpty();
         case CarnotWorkflowModelPackage.DATA_TYPE__EXTERNAL_REFERENCE:
            return externalReference != null;
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
            case CarnotWorkflowModelPackage.DATA_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.DATA_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.DATA_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.DATA_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.DATA_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.DATA_TYPE__ATTRIBUTE;
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
      result.append(", predefined: ");
      if (predefinedESet) result.append(predefined); else result.append("<unset>");
      result.append(')');
      return result.toString();
   }

} //DataTypeImpl
