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
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.EventActionTypeType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EventActionTypeTypeItemProvider
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
   public EventActionTypeTypeItemProvider(AdapterFactory adapterFactory)
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
         addActionClassPropertyDescriptor(object);
         addActivityActionPropertyDescriptor(object);
         addPanelClassPropertyDescriptor(object);
         addProcessActionPropertyDescriptor(object);
         addSupportedConditionTypesPropertyDescriptor(object);
         addUnsupportedContextsPropertyDescriptor(object);
         addActionInstancesPropertyDescriptor(object);
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
    * This adds a property descriptor for the Action Class feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addActionClassPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_EventActionTypeType_actionClass_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_EventActionTypeType_actionClass_feature", "_UI_EventActionTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.EVENT_ACTION_TYPE_TYPE__ACTION_CLASS,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Activity Action feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addActivityActionPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_EventActionTypeType_activityAction_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_EventActionTypeType_activityAction_feature", "_UI_EventActionTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.EVENT_ACTION_TYPE_TYPE__ACTIVITY_ACTION,
             true,
             false,
             false,
             ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
             getString("_UI_EventActionTypeType_panelClass_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_EventActionTypeType_panelClass_feature", "_UI_EventActionTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.EVENT_ACTION_TYPE_TYPE__PANEL_CLASS,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Process Action feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addProcessActionPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_EventActionTypeType_processAction_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_EventActionTypeType_processAction_feature", "_UI_EventActionTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.EVENT_ACTION_TYPE_TYPE__PROCESS_ACTION,
             true,
             false,
             false,
             ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Supported Condition Types feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addSupportedConditionTypesPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_EventActionTypeType_supportedConditionTypes_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_EventActionTypeType_supportedConditionTypes_feature", "_UI_EventActionTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.EVENT_ACTION_TYPE_TYPE__SUPPORTED_CONDITION_TYPES,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Unsupported Contexts feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addUnsupportedContextsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_EventActionTypeType_unsupportedContexts_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_EventActionTypeType_unsupportedContexts_feature", "_UI_EventActionTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.EVENT_ACTION_TYPE_TYPE__UNSUPPORTED_CONTEXTS,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Action Instances feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addActionInstancesPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_EventActionTypeType_actionInstances_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_EventActionTypeType_actionInstances_feature", "_UI_EventActionTypeType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.EVENT_ACTION_TYPE_TYPE__ACTION_INSTANCES,
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
    * This returns EventActionTypeType.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/EventActionTypeType")); //$NON-NLS-1$
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
      String label = ((EventActionTypeType)object).getName();
      return label == null || label.length() == 0 ?
         getString("_UI_EventActionTypeType_type") : //$NON-NLS-1$
         getString("_UI_EventActionTypeType_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

      switch (notification.getFeatureID(EventActionTypeType.class))
      {
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ELEMENT_OID:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ID:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__NAME:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__IS_PREDEFINED:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTION_CLASS:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ACTIVITY_ACTION:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PANEL_CLASS:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__PROCESS_ACTION:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__SUPPORTED_CONDITION_TYPES:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__UNSUPPORTED_CONTEXTS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__ATTRIBUTE:
         case CarnotWorkflowModelPackage.EVENT_ACTION_TYPE_TYPE__DESCRIPTION:
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
