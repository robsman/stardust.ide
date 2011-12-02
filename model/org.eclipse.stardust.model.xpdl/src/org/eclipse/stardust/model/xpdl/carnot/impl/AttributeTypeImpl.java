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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;



/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Attribute Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.AttributeTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.AttributeTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.AttributeTypeImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.AttributeTypeImpl#getValueNode <em>Value Node</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.AttributeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.AttributeTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.AttributeTypeImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.AttributeTypeImpl#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeTypeImpl extends EObjectImpl implements AttributeType
{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getMixed()
    * @generated
    * @ordered
    */
   protected FeatureMap mixed;

   /**
    * The cached value of the '{@link #getValueNode() <em>Value Node</em>}' containment reference.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getValueNode()
    * @generated
    * @ordered
    */
   protected XmlTextNode valueNode;

   /**
    * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getName()
    * @generated
    * @ordered
    */
   protected static final String NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getName()
    * @generated
    * @ordered
    */
   protected String name = NAME_EDEFAULT;

   /**
    * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getType()
    * @generated
    * @ordered
    */
   protected static final String TYPE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getType()
    * @generated
    * @ordered
    */
   protected String type = TYPE_EDEFAULT;

   /**
    * The default value of the '{@link #getValue() <em>Value</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getValue()
    * @generated
    * @ordered
    */
   protected static final String VALUE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getValue() <em>Value</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getValue()
    * @generated
    * @ordered
    */
   protected String value = VALUE_EDEFAULT;

   /**
    * @generated
    */
   protected IdentifiableReference reference;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   protected AttributeTypeImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return CarnotWorkflowModelPackage.Literals.ATTRIBUTE_TYPE;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public FeatureMap getMixed()
   {
      if (mixed == null)
      {
         mixed = new BasicFeatureMap(this, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__MIXED);
      }
      return mixed;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public FeatureMap getGroup()
   {
      return (FeatureMap)getMixed().<FeatureMap.Entry>list(CarnotWorkflowModelPackage.Literals.ATTRIBUTE_TYPE__GROUP);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public FeatureMap getAny()
   {
      return (FeatureMap)getGroup().<FeatureMap.Entry>list(CarnotWorkflowModelPackage.Literals.ATTRIBUTE_TYPE__ANY);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public XmlTextNode getValueNode()
   {
      return valueNode;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetValueNode(XmlTextNode newValueNode,
         NotificationChain msgs)
   {
      XmlTextNode oldValueNode = valueNode;
      valueNode = newValueNode;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE_NODE, oldValueNode, newValueNode);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setValueNode(XmlTextNode newValueNode)
   {
      if (newValueNode != valueNode)
      {
         NotificationChain msgs = null;
         if (valueNode != null)
            msgs = ((InternalEObject)valueNode).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE_NODE, null, msgs);
         if (newValueNode != null)
            msgs = ((InternalEObject)newValueNode).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE_NODE, null, msgs);
         msgs = basicSetValueNode(newValueNode, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE_NODE, newValueNode, newValueNode));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String getName()
   {
      return name;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setName(String newName)
   {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String getType()
   {
      return type;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setType(String newType)
   {
      String oldType = type;
      type = newType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__TYPE, oldType, type));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String getValue()
   {
      return value;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setValue(String newValue)
   {
      String oldValue = value;
      value = newValue;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE, oldValue, value));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public IdentifiableReference getReference()
   {
      return reference;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetReference(IdentifiableReference newReference,
         NotificationChain msgs)
   {
      IdentifiableReference oldReference = reference;
      reference = newReference;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE, oldReference, newReference);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setReference(IdentifiableReference newReference)
   {
      if (newReference != reference)
      {
         NotificationChain msgs = null;
         if (reference != null)
            msgs = ((InternalEObject)reference).eInverseRemove(this, CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE, IdentifiableReference.class, msgs);
         if (newReference != null)
            msgs = ((InternalEObject)newReference).eInverseAdd(this, CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE, IdentifiableReference.class, msgs);
         msgs = basicSetReference(newReference, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE, newReference, newReference));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated NOT
    */
   public String getAttributeValue()
   {
      if (null != getValueNode())
      {
         return ModelUtils.getCDataString(getValueNode().getMixed());
      }
      else
      {
         return getValue();
      }
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated NOT
    */
   public void setAttributeValue(String type, String value)
   {
      setType(type);

      if ((-1 != value.indexOf("\n")) || (-1 != value.indexOf("\r"))) //$NON-NLS-1$ //$NON-NLS-2$
      {
         setValue(null);

         XmlTextNode valueNode = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
         ModelUtils.setCDataString(valueNode.getMixed(), value);
         setValueNode(valueNode);

         getMixed().clear();
      }
      else
      {
         setValue(value);
         setValueNode(null);
         getMixed().clear();
      }
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
         NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE:
            if (reference != null)
               msgs = ((InternalEObject)reference).eInverseRemove(this, CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE, IdentifiableReference.class, msgs);
            return basicSetReference((IdentifiableReference)otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
         NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__MIXED:
            return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__GROUP:
            return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__ANY:
            return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE_NODE:
            return basicSetValueNode(null, msgs);
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE:
            return basicSetReference(null, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__MIXED:
            if (coreType) return getMixed();
            return ((FeatureMap.Internal)getMixed()).getWrapper();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__GROUP:
            if (coreType) return getGroup();
            return ((FeatureMap.Internal)getGroup()).getWrapper();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__ANY:
            if (coreType) return getAny();
            return ((FeatureMap.Internal)getAny()).getWrapper();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE_NODE:
            return getValueNode();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__TYPE:
            return getType();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE:
            return getValue();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE:
            return getReference();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__MIXED:
            ((FeatureMap.Internal)getMixed()).set(newValue);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__GROUP:
            ((FeatureMap.Internal)getGroup()).set(newValue);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__ANY:
            ((FeatureMap.Internal)getAny()).set(newValue);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE_NODE:
            setValueNode((XmlTextNode)newValue);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__TYPE:
            setType((String)newValue);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE:
            setValue((String)newValue);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE:
            setReference((IdentifiableReference)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__MIXED:
            getMixed().clear();
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__GROUP:
            getGroup().clear();
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__ANY:
            getAny().clear();
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE_NODE:
            setValueNode((XmlTextNode)null);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__NAME:
            setName(NAME_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__TYPE:
            setType(TYPE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE:
            setValue(VALUE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE:
            setReference((IdentifiableReference)null);
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__MIXED:
            return mixed != null && !mixed.isEmpty();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__GROUP:
            return !getGroup().isEmpty();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__ANY:
            return !getAny().isEmpty();
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE_NODE:
            return valueNode != null;
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__TYPE:
            return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE:
            return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
         case CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE:
            return reference != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (mixed: ");
      result.append(mixed);
      result.append(", name: ");
      result.append(name);
      result.append(", type: ");
      result.append(type);
      result.append(", value: ");
      result.append(value);
      result.append(')');
      return result.toString();
   }

} // AttributeTypeImpl
