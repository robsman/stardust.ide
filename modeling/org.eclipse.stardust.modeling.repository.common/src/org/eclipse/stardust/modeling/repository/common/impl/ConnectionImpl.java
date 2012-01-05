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
package org.eclipse.stardust.modeling.repository.common.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.modeling.repository.common.Attribute;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.RepositoryFactory;
import org.eclipse.stardust.modeling.repository.common.RepositoryPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.repository.common.impl.ConnectionImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.repository.common.impl.ConnectionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.repository.common.impl.ConnectionImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.repository.common.impl.ConnectionImpl#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionImpl extends EObjectImpl implements Connection
{
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
    * The default value of the '{@link #getType() <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
   protected static final String TYPE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
   protected String type = TYPE_EDEFAULT;

   /**
    * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAttributes()
    * @generated
    * @ordered
    */
   protected EList<Attribute> attributes;

   /**
    * @generated NOT
    */
   protected Map<String, Object> properties = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ConnectionImpl()
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
      return RepositoryPackage.Literals.CONNECTION;
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
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET,
               RepositoryPackage.CONNECTION__ID, oldId, id));
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
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET,
               RepositoryPackage.CONNECTION__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getType()
   {
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setType(String newType)
   {
      String oldType = type;
      type = newType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET,
               RepositoryPackage.CONNECTION__TYPE, oldType, type));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public List<Attribute> getAttributes()
   {
      if (attributes == null)
      {
         attributes = new EObjectContainmentEList<Attribute>(Attribute.class, this,
               RepositoryPackage.CONNECTION__ATTRIBUTES);
      }
      return attributes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public String getAttribute(String name)
   {
      for (Attribute attribute : getAttributes())
      {
         if (name.equals(attribute.getName()))
         {
            return attribute.getValue();
         }
      }
      return null;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public void removeAttribute(String name)
   {
      for (Attribute attribute : getAttributes())
      {
         if (name.equals(attribute.getName()))
         {
            getAttributes().remove(attribute);
            return;
         }
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public void setAttribute(String name, String value)
   {
      for (Attribute attribute : getAttributes())
      {
         if (name.equals(attribute.getName()))
         {
            attribute.setValue(value);
            return;
         }
      }
      RepositoryFactory factory = RepositoryFactory.eINSTANCE;
      Attribute attribute = factory.createAttribute();
      attribute.setName(name);
      attribute.setValue(value);
      getAttributes().add(attribute);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public Object getProperty(String name)
   {
      return getProperties().get(name);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public void removeProperty(String name)
   {
      getProperties().remove(name);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public void setProperty(String name, Object value)
   {
      getProperties().put(name, value);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public Map<String, Object> getProperties()
   {
      if (properties == null)
      {
         properties = CollectionUtils.newMap();
      }
      return properties;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
         NotificationChain msgs)
   {
      switch (featureID)
      {
      case RepositoryPackage.CONNECTION__ATTRIBUTES:
         return ((InternalEList< ? >) getAttributes()).basicRemove(otherEnd, msgs);
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
      case RepositoryPackage.CONNECTION__ID:
         return getId();
      case RepositoryPackage.CONNECTION__NAME:
         return getName();
      case RepositoryPackage.CONNECTION__TYPE:
         return getType();
      case RepositoryPackage.CONNECTION__ATTRIBUTES:
         return getAttributes();
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
      case RepositoryPackage.CONNECTION__ID:
         setId((String) newValue);
         return;
      case RepositoryPackage.CONNECTION__NAME:
         setName((String) newValue);
         return;
      case RepositoryPackage.CONNECTION__TYPE:
         setType((String) newValue);
         return;
      case RepositoryPackage.CONNECTION__ATTRIBUTES:
         getAttributes().clear();
         getAttributes().addAll((Collection< ? extends Attribute>) newValue);
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
      case RepositoryPackage.CONNECTION__ID:
         setId(ID_EDEFAULT);
         return;
      case RepositoryPackage.CONNECTION__NAME:
         setName(NAME_EDEFAULT);
         return;
      case RepositoryPackage.CONNECTION__TYPE:
         setType(TYPE_EDEFAULT);
         return;
      case RepositoryPackage.CONNECTION__ATTRIBUTES:
         getAttributes().clear();
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
      case RepositoryPackage.CONNECTION__ID:
         return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case RepositoryPackage.CONNECTION__NAME:
         return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case RepositoryPackage.CONNECTION__TYPE:
         return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
      case RepositoryPackage.CONNECTION__ATTRIBUTES:
         return attributes != null && !attributes.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy())
         return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" ("+ "id" + ": "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      result.append(id);
      result.append(", "+ "name"+ ": "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      result.append(name);
      result.append("," + "type"+ ": "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      result.append(type);
      result.append(')');
      return result.toString();
   }
} //ConnectionImpl