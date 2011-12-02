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
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.LinkCardinality;
import org.eclipse.stardust.model.xpdl.carnot.LinkColor;
import org.eclipse.stardust.model.xpdl.carnot.LinkEndStyle;
import org.eclipse.stardust.model.xpdl.carnot.LinkLineStyle;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#isIsPredefined <em>Is Predefined</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getSourceRole <em>Source Role</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getSourceClass <em>Source Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getSourceCardinality <em>Source Cardinality</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getTargetRole <em>Target Role</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getTargetClass <em>Target Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getTargetCardinality <em>Target Cardinality</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getSourceSymbol <em>Source Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getTargetSymbol <em>Target Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#isShowRoleNames <em>Show Role Names</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#isShowLinkTypeName <em>Show Link Type Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LinkTypeTypeImpl#getLinkInstances <em>Link Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkTypeTypeImpl extends EObjectImpl implements LinkTypeType
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
    * The default value of the '{@link #getSourceRole() <em>Source Role</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSourceRole()
    * @generated
    * @ordered
    */
   protected static final String SOURCE_ROLE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getSourceRole() <em>Source Role</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSourceRole()
    * @generated
    * @ordered
    */
   protected String sourceRole = SOURCE_ROLE_EDEFAULT;

   /**
    * The default value of the '{@link #getSourceClass() <em>Source Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSourceClass()
    * @generated
    * @ordered
    */
   protected static final String SOURCE_CLASS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getSourceClass() <em>Source Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSourceClass()
    * @generated
    * @ordered
    */
   protected String sourceClass = SOURCE_CLASS_EDEFAULT;

   /**
    * The default value of the '{@link #getSourceCardinality() <em>Source Cardinality</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSourceCardinality()
    * @generated
    * @ordered
    */
   protected static final LinkCardinality SOURCE_CARDINALITY_EDEFAULT = LinkCardinality.UNKNOWN_LITERAL;

   /**
    * The cached value of the '{@link #getSourceCardinality() <em>Source Cardinality</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSourceCardinality()
    * @generated
    * @ordered
    */
   protected LinkCardinality sourceCardinality = SOURCE_CARDINALITY_EDEFAULT;

   /**
    * The default value of the '{@link #getTargetRole() <em>Target Role</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTargetRole()
    * @generated
    * @ordered
    */
   protected static final String TARGET_ROLE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getTargetRole() <em>Target Role</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTargetRole()
    * @generated
    * @ordered
    */
   protected String targetRole = TARGET_ROLE_EDEFAULT;

   /**
    * The default value of the '{@link #getTargetClass() <em>Target Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTargetClass()
    * @generated
    * @ordered
    */
   protected static final String TARGET_CLASS_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getTargetClass() <em>Target Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTargetClass()
    * @generated
    * @ordered
    */
   protected String targetClass = TARGET_CLASS_EDEFAULT;

   /**
    * The default value of the '{@link #getTargetCardinality() <em>Target Cardinality</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTargetCardinality()
    * @generated
    * @ordered
    */
   protected static final LinkCardinality TARGET_CARDINALITY_EDEFAULT = LinkCardinality.UNKNOWN_LITERAL;

   /**
    * The cached value of the '{@link #getTargetCardinality() <em>Target Cardinality</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTargetCardinality()
    * @generated
    * @ordered
    */
   protected LinkCardinality targetCardinality = TARGET_CARDINALITY_EDEFAULT;

   /**
    * The default value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLineStyle()
    * @generated
    * @ordered
    */
   protected static final LinkLineStyle LINE_STYLE_EDEFAULT = LinkLineStyle.UNKNOWN_LITERAL;

   /**
    * The cached value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLineStyle()
    * @generated
    * @ordered
    */
   protected LinkLineStyle lineStyle = LINE_STYLE_EDEFAULT;

   /**
    * The default value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLineColor()
    * @generated
    * @ordered
    */
   protected static final LinkColor LINE_COLOR_EDEFAULT = LinkColor.UNKNOWN_LITERAL;

   /**
    * The cached value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLineColor()
    * @generated
    * @ordered
    */
   protected LinkColor lineColor = LINE_COLOR_EDEFAULT;

   /**
    * The default value of the '{@link #getSourceSymbol() <em>Source Symbol</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSourceSymbol()
    * @generated
    * @ordered
    */
   protected static final LinkEndStyle SOURCE_SYMBOL_EDEFAULT = LinkEndStyle.UNKNOWN_LITERAL;

   /**
    * The cached value of the '{@link #getSourceSymbol() <em>Source Symbol</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSourceSymbol()
    * @generated
    * @ordered
    */
   protected LinkEndStyle sourceSymbol = SOURCE_SYMBOL_EDEFAULT;

   /**
    * The default value of the '{@link #getTargetSymbol() <em>Target Symbol</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTargetSymbol()
    * @generated
    * @ordered
    */
   protected static final LinkEndStyle TARGET_SYMBOL_EDEFAULT = LinkEndStyle.UNKNOWN_LITERAL;

   /**
    * The cached value of the '{@link #getTargetSymbol() <em>Target Symbol</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTargetSymbol()
    * @generated
    * @ordered
    */
   protected LinkEndStyle targetSymbol = TARGET_SYMBOL_EDEFAULT;

   /**
    * The default value of the '{@link #isShowRoleNames() <em>Show Role Names</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isShowRoleNames()
    * @generated
    * @ordered
    */
   protected static final boolean SHOW_ROLE_NAMES_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isShowRoleNames() <em>Show Role Names</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isShowRoleNames()
    * @generated
    * @ordered
    */
   protected boolean showRoleNames = SHOW_ROLE_NAMES_EDEFAULT;

   /**
    * This is true if the Show Role Names attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean showRoleNamesESet;

   /**
    * The default value of the '{@link #isShowLinkTypeName() <em>Show Link Type Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isShowLinkTypeName()
    * @generated
    * @ordered
    */
   protected static final boolean SHOW_LINK_TYPE_NAME_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isShowLinkTypeName() <em>Show Link Type Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isShowLinkTypeName()
    * @generated
    * @ordered
    */
   protected boolean showLinkTypeName = SHOW_LINK_TYPE_NAME_EDEFAULT;

   /**
    * This is true if the Show Link Type Name attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean showLinkTypeNameESet;

   /**
    * The cached value of the '{@link #getLinkInstances() <em>Link Instances</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLinkInstances()
    * @generated
    * @ordered
    */
   protected EList<GenericLinkConnectionType> linkInstances;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected LinkTypeTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.LINK_TYPE_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ID, oldId, id, !oldIdESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
   public LinkColor getLineColor()
   {
      return lineColor;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLineColor(LinkColor newLineColor)
   {
      LinkColor oldLineColor = lineColor;
      lineColor = newLineColor == null ? LINE_COLOR_EDEFAULT : newLineColor;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_COLOR, oldLineColor, lineColor));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<GenericLinkConnectionType> getLinkInstances()
   {
      if (linkInstances == null)
      {
         linkInstances = new EObjectWithInverseResolvingEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE);
      }
      return linkInstances;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__NAME, oldName, name, !oldNameESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
   public EList<AttributeType> getAttribute()
   {
      if (attribute == null)
      {
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ATTRIBUTE);
      }
      return attribute;
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__DESCRIPTION, oldDescription, newDescription);
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
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.LINK_TYPE_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.LINK_TYPE_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__DESCRIPTION, newDescription, newDescription));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, isPredefined, !oldIsPredefinedESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, IS_PREDEFINED_EDEFAULT, oldIsPredefinedESet));
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
   public String getSourceRole()
   {
      return sourceRole;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSourceRole(String newSourceRole)
   {
      String oldSourceRole = sourceRole;
      sourceRole = newSourceRole;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_ROLE, oldSourceRole, sourceRole));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getSourceClass()
   {
      return sourceClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSourceClass(String newSourceClass)
   {
      String oldSourceClass = sourceClass;
      sourceClass = newSourceClass;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CLASS, oldSourceClass, sourceClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isShowRoleNames()
   {
      return showRoleNames;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setShowRoleNames(boolean newShowRoleNames)
   {
      boolean oldShowRoleNames = showRoleNames;
      showRoleNames = newShowRoleNames;
      boolean oldShowRoleNamesESet = showRoleNamesESet;
      showRoleNamesESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_ROLE_NAMES, oldShowRoleNames, showRoleNames, !oldShowRoleNamesESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetShowRoleNames()
   {
      boolean oldShowRoleNames = showRoleNames;
      boolean oldShowRoleNamesESet = showRoleNamesESet;
      showRoleNames = SHOW_ROLE_NAMES_EDEFAULT;
      showRoleNamesESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_ROLE_NAMES, oldShowRoleNames, SHOW_ROLE_NAMES_EDEFAULT, oldShowRoleNamesESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetShowRoleNames()
   {
      return showRoleNamesESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isShowLinkTypeName()
   {
      return showLinkTypeName;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setShowLinkTypeName(boolean newShowLinkTypeName)
   {
      boolean oldShowLinkTypeName = showLinkTypeName;
      showLinkTypeName = newShowLinkTypeName;
      boolean oldShowLinkTypeNameESet = showLinkTypeNameESet;
      showLinkTypeNameESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_LINK_TYPE_NAME, oldShowLinkTypeName, showLinkTypeName, !oldShowLinkTypeNameESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetShowLinkTypeName()
   {
      boolean oldShowLinkTypeName = showLinkTypeName;
      boolean oldShowLinkTypeNameESet = showLinkTypeNameESet;
      showLinkTypeName = SHOW_LINK_TYPE_NAME_EDEFAULT;
      showLinkTypeNameESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_LINK_TYPE_NAME, oldShowLinkTypeName, SHOW_LINK_TYPE_NAME_EDEFAULT, oldShowLinkTypeNameESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetShowLinkTypeName()
   {
      return showLinkTypeNameESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkCardinality getSourceCardinality()
   {
      return sourceCardinality;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSourceCardinality(LinkCardinality newSourceCardinality)
   {
      LinkCardinality oldSourceCardinality = sourceCardinality;
      sourceCardinality = newSourceCardinality == null ? SOURCE_CARDINALITY_EDEFAULT : newSourceCardinality;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CARDINALITY, oldSourceCardinality, sourceCardinality));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getTargetRole()
   {
      return targetRole;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTargetRole(String newTargetRole)
   {
      String oldTargetRole = targetRole;
      targetRole = newTargetRole;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_ROLE, oldTargetRole, targetRole));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getTargetClass()
   {
      return targetClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTargetClass(String newTargetClass)
   {
      String oldTargetClass = targetClass;
      targetClass = newTargetClass;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CLASS, oldTargetClass, targetClass));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkEndStyle getSourceSymbol()
   {
      return sourceSymbol;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSourceSymbol(LinkEndStyle newSourceSymbol)
   {
      LinkEndStyle oldSourceSymbol = sourceSymbol;
      sourceSymbol = newSourceSymbol == null ? SOURCE_SYMBOL_EDEFAULT : newSourceSymbol;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_SYMBOL, oldSourceSymbol, sourceSymbol));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkCardinality getTargetCardinality()
   {
      return targetCardinality;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTargetCardinality(LinkCardinality newTargetCardinality)
   {
      LinkCardinality oldTargetCardinality = targetCardinality;
      targetCardinality = newTargetCardinality == null ? TARGET_CARDINALITY_EDEFAULT : newTargetCardinality;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CARDINALITY, oldTargetCardinality, targetCardinality));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkLineStyle getLineStyle()
   {
      return lineStyle;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLineStyle(LinkLineStyle newLineStyle)
   {
      LinkLineStyle oldLineStyle = lineStyle;
      lineStyle = newLineStyle == null ? LINE_STYLE_EDEFAULT : newLineStyle;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_STYLE, oldLineStyle, lineStyle));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkEndStyle getTargetSymbol()
   {
      return targetSymbol;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTargetSymbol(LinkEndStyle newTargetSymbol)
   {
      LinkEndStyle oldTargetSymbol = targetSymbol;
      targetSymbol = newTargetSymbol == null ? TARGET_SYMBOL_EDEFAULT : newTargetSymbol;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_SYMBOL, oldTargetSymbol, targetSymbol));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getSymbols()
   {
      return getLinkInstances();
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
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getLinkInstances()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES:
            return ((InternalEList<?>)getLinkInstances()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__IS_PREDEFINED:
            return isIsPredefined();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_ROLE:
            return getSourceRole();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CLASS:
            return getSourceClass();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CARDINALITY:
            return getSourceCardinality();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_ROLE:
            return getTargetRole();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CLASS:
            return getTargetClass();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CARDINALITY:
            return getTargetCardinality();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_STYLE:
            return getLineStyle();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_COLOR:
            return getLineColor();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_SYMBOL:
            return getSourceSymbol();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_SYMBOL:
            return getTargetSymbol();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_ROLE_NAMES:
            return isShowRoleNames();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_LINK_TYPE_NAME:
            return isShowLinkTypeName();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES:
            return getLinkInstances();
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
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__IS_PREDEFINED:
            setIsPredefined((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_ROLE:
            setSourceRole((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CLASS:
            setSourceClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CARDINALITY:
            setSourceCardinality((LinkCardinality)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_ROLE:
            setTargetRole((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CLASS:
            setTargetClass((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CARDINALITY:
            setTargetCardinality((LinkCardinality)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_STYLE:
            setLineStyle((LinkLineStyle)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_COLOR:
            setLineColor((LinkColor)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_SYMBOL:
            setSourceSymbol((LinkEndStyle)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_SYMBOL:
            setTargetSymbol((LinkEndStyle)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_ROLE_NAMES:
            setShowRoleNames((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_LINK_TYPE_NAME:
            setShowLinkTypeName((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES:
            getLinkInstances().clear();
            getLinkInstances().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
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
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__IS_PREDEFINED:
            unsetIsPredefined();
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_ROLE:
            setSourceRole(SOURCE_ROLE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CLASS:
            setSourceClass(SOURCE_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CARDINALITY:
            setSourceCardinality(SOURCE_CARDINALITY_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_ROLE:
            setTargetRole(TARGET_ROLE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CLASS:
            setTargetClass(TARGET_CLASS_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CARDINALITY:
            setTargetCardinality(TARGET_CARDINALITY_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_STYLE:
            setLineStyle(LINE_STYLE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_COLOR:
            setLineColor(LINE_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_SYMBOL:
            setSourceSymbol(SOURCE_SYMBOL_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_SYMBOL:
            setTargetSymbol(TARGET_SYMBOL_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_ROLE_NAMES:
            unsetShowRoleNames();
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_LINK_TYPE_NAME:
            unsetShowLinkTypeName();
            return;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES:
            getLinkInstances().clear();
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
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__IS_PREDEFINED:
            return isSetIsPredefined();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_ROLE:
            return SOURCE_ROLE_EDEFAULT == null ? sourceRole != null : !SOURCE_ROLE_EDEFAULT.equals(sourceRole);
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CLASS:
            return SOURCE_CLASS_EDEFAULT == null ? sourceClass != null : !SOURCE_CLASS_EDEFAULT.equals(sourceClass);
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_CARDINALITY:
            return sourceCardinality != SOURCE_CARDINALITY_EDEFAULT;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_ROLE:
            return TARGET_ROLE_EDEFAULT == null ? targetRole != null : !TARGET_ROLE_EDEFAULT.equals(targetRole);
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CLASS:
            return TARGET_CLASS_EDEFAULT == null ? targetClass != null : !TARGET_CLASS_EDEFAULT.equals(targetClass);
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_CARDINALITY:
            return targetCardinality != TARGET_CARDINALITY_EDEFAULT;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_STYLE:
            return lineStyle != LINE_STYLE_EDEFAULT;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINE_COLOR:
            return lineColor != LINE_COLOR_EDEFAULT;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SOURCE_SYMBOL:
            return sourceSymbol != SOURCE_SYMBOL_EDEFAULT;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__TARGET_SYMBOL:
            return targetSymbol != TARGET_SYMBOL_EDEFAULT;
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_ROLE_NAMES:
            return isSetShowRoleNames();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__SHOW_LINK_TYPE_NAME:
            return isSetShowLinkTypeName();
         case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES:
            return linkInstances != null && !linkInstances.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getExtensionPointId()
   {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EList getTypedElements()
   {
      return getLinkInstances();
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
            case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.LINK_TYPE_TYPE__NAME;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.LINK_TYPE_TYPE__ATTRIBUTE;
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
      result.append(", isPredefined: ");
      if (isPredefinedESet) result.append(isPredefined); else result.append("<unset>");
      result.append(", sourceRole: ");
      result.append(sourceRole);
      result.append(", sourceClass: ");
      result.append(sourceClass);
      result.append(", sourceCardinality: ");
      result.append(sourceCardinality);
      result.append(", targetRole: ");
      result.append(targetRole);
      result.append(", targetClass: ");
      result.append(targetClass);
      result.append(", targetCardinality: ");
      result.append(targetCardinality);
      result.append(", lineStyle: ");
      result.append(lineStyle);
      result.append(", lineColor: ");
      result.append(lineColor);
      result.append(", sourceSymbol: ");
      result.append(sourceSymbol);
      result.append(", targetSymbol: ");
      result.append(targetSymbol);
      result.append(", showRoleNames: ");
      if (showRoleNamesESet) result.append(showRoleNames); else result.append("<unset>");
      result.append(", showLinkTypeName: ");
      if (showLinkTypeNameESet) result.append(showLinkTypeName); else result.append("<unset>");
      result.append(')');
      return result.toString();
   }

} //LinkTypeTypeImpl
