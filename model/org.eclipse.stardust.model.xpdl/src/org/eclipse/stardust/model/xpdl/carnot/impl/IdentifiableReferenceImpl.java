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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Identifiable Reference</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IdentifiableReferenceImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IdentifiableReferenceImpl#getIdentifiable <em>Identifiable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdentifiableReferenceImpl extends EObjectImpl
      implements IdentifiableReference
{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * generated
    */
   protected AttributeType attribute;

   /**
    * generated
    */
   protected EObject identifiable;

   /**
    * generated NOT
    */
   private boolean changing;

   /**
    * generated NOT
    */
   private Adapter adapter = new Adapter()
   {
      public void notifyChanged(Notification notification)
      {
         if (notification.getNotifier().equals(attribute))
         {
            if (CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value().equals(
                  notification.getFeature()))
            {
               if (!changing)
               {
                  // update reference because it was changed externally
               }
            }
         }
         else if (notification.getNotifier().equals(identifiable))
         {
            if (CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id().equals(
                  notification.getFeature())
                  || XpdlPackage.eINSTANCE.getTypeDeclarationType_Id().equals(
                        notification.getFeature()))
            {
               setValue(getId(identifiable));
            }
         }
         else
         {
            switch (notification.getEventType())
            {
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
               if (identifiable == notification.getOldValue() && identifiable.eContainer() == null)
               {
                  ((EObject) notification.getNotifier()).eAdapters().remove(this);
                  if (attribute != null)
                  {
                     IExtensibleElement extensible = (IExtensibleElement) attribute.eContainer();
                     if (extensible != null)
                     {
                        extensible.getAttribute().remove(attribute);
                     }
                  }
               }
            }
         }
      }

      public Notifier getTarget()
      {
         // shared
         return null;
      }

      public void setTarget(Notifier newTarget)
      {
      // shared
      }

      public boolean isAdapterForType(Object type)
      {
         return false;
      }
   };

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   protected IdentifiableReferenceImpl()
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
      return CarnotWorkflowModelPackage.Literals.IDENTIFIABLE_REFERENCE;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public AttributeType getAttribute()
   {
      return attribute;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated NOT
    */
   public NotificationChain basicSetAttribute(AttributeType newAttribute,
         NotificationChain msgs)
   {
      if (attribute != null)
      {
         attribute.eAdapters().remove(adapter);
      }
      AttributeType oldAttribute = attribute;
      attribute = newAttribute;
      if (identifiable != null && attribute != null)
      {
         attribute.setValue(getId(identifiable));
         attribute.eAdapters().add(adapter);
      }
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
               CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE,
               oldAttribute, newAttribute);
         if (msgs == null)
            msgs = notification;
         else
            msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setAttribute(AttributeType newAttribute)
   {
      if (newAttribute != attribute)
      {
         NotificationChain msgs = null;
         if (attribute != null)
            msgs = ((InternalEObject)attribute).eInverseRemove(this, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE, AttributeType.class, msgs);
         if (newAttribute != null)
            msgs = ((InternalEObject)newAttribute).eInverseAdd(this, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE, AttributeType.class, msgs);
         msgs = basicSetAttribute(newAttribute, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE, newAttribute, newAttribute));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EObject getIdentifiable()
   {
      return identifiable;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated NOT
    */
   public void setIdentifiable(EObject newIdentifiable)
   {
      if (identifiable != null)
      {
         identifiable.eAdapters().remove(adapter);
      }
      EObject oldIdentifiable = identifiable;
      identifiable = newIdentifiable;
      if (attribute != null)
      {
         // disable notification but only for the reference.
         // investigate optimization using a boolean field.
         String value = getId(identifiable);
         setValue(value);
      }
      if (newIdentifiable != null)
      {
         identifiable.eAdapters().add(adapter);
         identifiable.eContainer().eAdapters().add(adapter);
      }
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET,
               CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE,
               oldIdentifiable, identifiable));
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
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
            if (attribute != null)
               msgs = ((InternalEObject)attribute).eInverseRemove(this, CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__REFERENCE, AttributeType.class, msgs);
            return basicSetAttribute((AttributeType)otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
            return basicSetAttribute(null, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   private String getId(EObject identifiable)
   {
      String id = null;
      if (identifiable instanceof IIdentifiableElement)
      {
         id = ((IIdentifiableElement) identifiable).getId();
      }
      else if (identifiable instanceof TypeDeclarationType)
      {
         id = ((TypeDeclarationType) identifiable).getId();
      }
      ModelType thisModel = ModelUtils.findContainingModel(attribute);
      ModelType otherModel = ModelUtils.findContainingModel(identifiable);
      if (thisModel != otherModel)
      {
         String feature = identifiable.eContainingFeature().getName();
         id = feature + ":{" + otherModel.getId() + "}" + id;
      }
      return id;
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
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE:
            return getIdentifiable();
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
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
            setAttribute((AttributeType)newValue);
            return;
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE:
            setIdentifiable((EObject)newValue);
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
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
            setAttribute((AttributeType)null);
            return;
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE:
            setIdentifiable((EObject)null);
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
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__ATTRIBUTE:
            return attribute != null;
         case CarnotWorkflowModelPackage.IDENTIFIABLE_REFERENCE__IDENTIFIABLE:
            return identifiable != null;
      }
      return super.eIsSet(featureID);
   }

   private void setValue(String value)
   {
      changing = true;
      try
      {
         attribute.setValue(value);
      }
      finally
      {
         changing = false;
      }
   }

} // IdentifiableReferenceImpl
