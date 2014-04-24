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
package org.eclipse.stardust.model.xpdl.edit.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.emf.edit.provider.*;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.DataTypeType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DataTypeTypeItemProvider
   extends ItemProviderAdapter
   implements	
      IEditingDomainItemProvider,	
      IStructuredItemContentProvider,	
      ITreeItemContentProvider,	
      IItemLabelProvider,	
      IItemPropertySource		
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * This constructs an instance from a factory and a notifier.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DataTypeTypeItemProvider(AdapterFactory adapterFactory)
   {
      super(adapterFactory);
   }

   /**
    * This returns the property descriptors for the adapted class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
   {
      if (itemPropertyDescriptors == null)
      {
         super.getPropertyDescriptors(object);

         addElementOidPropertyDescriptor(object);
         addIdPropertyDescriptor(object);
         addNamePropertyDescriptor(object);
         addIsPredefinedPropertyDescriptor(object);
         addAccessPathEditorPropertyDescriptor(object);
         addEvaluatorPropertyDescriptor(object);
         addInstanceClassPropertyDescriptor(object);
         addPanelClassPropertyDescriptor(object);
         addReadablePropertyDescriptor(object);
         addStorageStrategyPropertyDescriptor(object);
         addValidatorClassPropertyDescriptor(object);
         addValueCreatorPropertyDescriptor(object);
         addWritablePropertyDescriptor(object);
         addDataPropertyDescriptor(object);
      }
      return itemPropertyDescriptors;
   }

   /**
    * This adds a property descriptor for the Element Oid feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addElementOidPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IModelElement_elementOid_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_IModelElement_elementOid_feature", "_UI_IModelElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.IMODEL_ELEMENT__ELEMENT_OID,
             true,
             false,
             false,
             ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Access Path Editor feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addAccessPathEditorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_accessPathEditor_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_accessPathEditor_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__ACCESS_PATH_EDITOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Evaluator feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addEvaluatorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_evaluator_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_evaluator_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__EVALUATOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Id feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addIdPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IIdentifiableElement_id_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_IIdentifiableElement_id_feature", "_UI_IIdentifiableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.IIDENTIFIABLE_ELEMENT__ID,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Instance Class feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addInstanceClassPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_instanceClass_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_instanceClass_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__INSTANCE_CLASS,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Name feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addNamePropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IIdentifiableElement_name_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_IIdentifiableElement_name_feature", "_UI_IIdentifiableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.IIDENTIFIABLE_ELEMENT__NAME,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Is Predefined feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addIsPredefinedPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IMetaType_isPredefined_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_IMetaType_isPredefined_feature", "_UI_IMetaType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.IMETA_TYPE__IS_PREDEFINED,
             true,
             false,
             false,
             ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Panel Class feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addPanelClassPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_panelClass_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_panelClass_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__PANEL_CLASS,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Readable feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addReadablePropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_readable_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_readable_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__READABLE,
             true,
             false,
             false,
             ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Storage Strategy feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addStorageStrategyPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_storageStrategy_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_storageStrategy_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__STORAGE_STRATEGY,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Validator Class feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addValidatorClassPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_validatorClass_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_validatorClass_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__VALIDATOR_CLASS,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Value Creator feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addValueCreatorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_valueCreator_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_valueCreator_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__VALUE_CREATOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Writable feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addWritablePropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_writable_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_writable_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__WRITABLE,
             true,
             false,
             false,
             ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Data feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addDataPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_DataTypeType_data_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_DataTypeType_data_feature", "_UI_DataTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.DATA_TYPE_TYPE__DATA,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
    * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
    * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
   {
      if (childrenFeatures == null)
      {
         super.getChildrenFeatures(object);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.IEXTENSIBLE_ELEMENT__ATTRIBUTE);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION);
      }
      return childrenFeatures;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EStructuralFeature getChildFeature(Object object, Object child)
   {
      // Check the type of the specified child object and return the proper feature to use for
      // adding (see {@link AddCommand}) it as a child.

      return super.getChildFeature(object, child);
   }

   /**
    * This returns DataTypeType.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/DataTypeType")); //$NON-NLS-1$
   }

   /**
    * This returns the label text for the adapted class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String getText(Object object)
   {
      String label = ((DataTypeType)object).getName();
      return label == null || label.length() == 0 ?
         getString("_UI_DataTypeType_type") : //$NON-NLS-1$
         getString("_UI_DataTypeType_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
   }

   /**
    * This handles model notifications by calling {@link #updateChildren} to update any cached
    * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void notifyChanged(Notification notification)
   {
      updateChildren(notification);

      switch (notification.getFeatureID(DataTypeType.class))
      {
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ELEMENT_OID:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ID:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__NAME:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__IS_PREDEFINED:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ACCESS_PATH_EDITOR:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__EVALUATOR:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__INSTANCE_CLASS:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__PANEL_CLASS:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__READABLE:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__STORAGE_STRATEGY:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALIDATOR_CLASS:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__VALUE_CREATOR:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__WRITABLE:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__ATTRIBUTE:
         case CarnotWorkflowModelPackage.DATA_TYPE_TYPE__DESCRIPTION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
      }
      super.notifyChanged(notification);
   }

   /**
    * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
    * that can be created under this object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
   {
      super.collectNewChildDescriptors(newChildDescriptors, object);

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.IEXTENSIBLE_ELEMENT__ATTRIBUTE,
             CarnotWorkflowModelFactory.eINSTANCE.createAttributeType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION,
             CarnotWorkflowModelFactory.eINSTANCE.createDescriptionType()));
   }

   /**
    * Return the resource locator for this item provider's resources.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public ResourceLocator getResourceLocator()
   {
      return CarnotWorkflowModelEditPlugin.INSTANCE;
   }

}
