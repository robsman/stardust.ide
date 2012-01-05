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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Id Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IdRefImpl#getPackageRef <em>Package Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IdRefImpl#getRef <em>Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdRefImpl extends EObjectImpl implements IdRef
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH";

   /**
    * The cached value of the '{@link #getPackageRef() <em>Package Ref</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPackageRef()
    * @generated
    * @ordered
    */
   protected ExternalPackage packageRef;

   /**
    * The default value of the '{@link #getRef() <em>Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getRef()
    * @generated
    * @ordered
    */
   protected static final String REF_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getRef() <em>Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getRef()
    * @generated
    * @ordered
    */
   protected String ref = REF_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected IdRefImpl()
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
      return CarnotWorkflowModelPackage.Literals.ID_REF;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExternalPackage getPackageRef()
   {
      return packageRef;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPackageRef(ExternalPackage newPackageRef)
   {
      ExternalPackage oldPackageRef = packageRef;
      packageRef = newPackageRef;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ID_REF__PACKAGE_REF, oldPackageRef, packageRef));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getRef()
   {
      return ref;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setRef(String newRef)
   {
      String oldRef = ref;
      ref = newRef;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ID_REF__REF, oldRef, ref));
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
         case CarnotWorkflowModelPackage.ID_REF__PACKAGE_REF:
            return getPackageRef();
         case CarnotWorkflowModelPackage.ID_REF__REF:
            return getRef();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ID_REF__PACKAGE_REF:
            setPackageRef((ExternalPackage)newValue);
            return;
         case CarnotWorkflowModelPackage.ID_REF__REF:
            setRef((String)newValue);
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
         case CarnotWorkflowModelPackage.ID_REF__PACKAGE_REF:
            setPackageRef((ExternalPackage)null);
            return;
         case CarnotWorkflowModelPackage.ID_REF__REF:
            setRef(REF_EDEFAULT);
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
         case CarnotWorkflowModelPackage.ID_REF__PACKAGE_REF:
            return packageRef != null;
         case CarnotWorkflowModelPackage.ID_REF__REF:
            return REF_EDEFAULT == null ? ref != null : !REF_EDEFAULT.equals(ref);
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
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (ref: ");
      result.append(ref);
      result.append(')');
      return result.toString();
   }

   @SuppressWarnings("unchecked")
   public <T extends IIdentifiableModelElement> T get(Class<T> clz)
   {
      // TODO: consider caching
      T result = null;
      ModelType model = ModelUtils.findContainingModel(this);
      if (getPackageRef() != null)
      {
         IConnectionManager manager = model.getConnectionManager();
         model = manager == null ? null : manager.find(getPackageRef());
      }
      if (model != null)
      {
         if (ProcessDefinitionType.class == clz)
         {
            result = (T) ModelUtils.findIdentifiableElement(model.getProcessDefinition(), getRef());
         }
         if (ApplicationType.class == clz)
         {
            result = (T) ModelUtils.findIdentifiableElement(model.getApplication(), getRef());
         }
      }
      return result;
   }

   public <T extends IIdentifiableModelElement> void set(T identifiable)
   {
      setRef(identifiable.getId());
      ExternalPackages packages = ModelUtils.findContainingModel(this).getExternalPackages();
      if (packages != null)
      {
         setPackageRef(packages.getExternalPackage(ModelUtils.findContainingModel(identifiable).getId()));
      }
   }

} //IdRefImpl
