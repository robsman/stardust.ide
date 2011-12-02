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
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.ModelType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelTypeItemProvider
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
   public ModelTypeItemProvider(AdapterFactory adapterFactory)
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

         addIdPropertyDescriptor(object);
         addNamePropertyDescriptor(object);
         addTypeDeclarationsPropertyDescriptor(object);
         addAuthorPropertyDescriptor(object);
         addCarnotVersionPropertyDescriptor(object);
         addCreatedPropertyDescriptor(object);
         addModelOIDPropertyDescriptor(object);
         addOidPropertyDescriptor(object);
         addVendorPropertyDescriptor(object);
      }
      return itemPropertyDescriptors;
   }

   /**
    * This adds a property descriptor for the Author feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addAuthorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ModelType_author_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ModelType_author_feature", "_UI_ModelType_type"),
             CarnotWorkflowModelPackage.Literals.MODEL_TYPE__AUTHOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Carnot Version feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addCarnotVersionPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ModelType_carnotVersion_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ModelType_carnotVersion_feature", "_UI_ModelType_type"),
             CarnotWorkflowModelPackage.Literals.MODEL_TYPE__CARNOT_VERSION,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Created feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addCreatedPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ModelType_created_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ModelType_created_feature", "_UI_ModelType_type"),
             CarnotWorkflowModelPackage.Literals.MODEL_TYPE__CREATED,
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
             getString("_UI_IIdentifiableElement_id_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IIdentifiableElement_id_feature", "_UI_IIdentifiableElement_type"),
             CarnotWorkflowModelPackage.Literals.IIDENTIFIABLE_ELEMENT__ID,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Model OID feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addModelOIDPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ModelType_modelOID_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ModelType_modelOID_feature", "_UI_ModelType_type"),
             CarnotWorkflowModelPackage.Literals.MODEL_TYPE__MODEL_OID,
             true,
             false,
             false,
             ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
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
             getString("_UI_IIdentifiableElement_name_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IIdentifiableElement_name_feature", "_UI_IIdentifiableElement_type"),
             CarnotWorkflowModelPackage.Literals.IIDENTIFIABLE_ELEMENT__NAME,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Type Declarations feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addTypeDeclarationsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ModelType_typeDeclarations_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ModelType_typeDeclarations_feature", "_UI_ModelType_type"),
             CarnotWorkflowModelPackage.Literals.MODEL_TYPE__TYPE_DECLARATIONS,
             true,
             false,
             true,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Oid feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addOidPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ModelType_oid_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ModelType_oid_feature", "_UI_ModelType_type"),
             CarnotWorkflowModelPackage.Literals.MODEL_TYPE__OID,
             true,
             false,
             false,
             ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Vendor feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addVendorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ModelType_vendor_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ModelType_vendor_feature", "_UI_ModelType_type"),
             CarnotWorkflowModelPackage.Literals.MODEL_TYPE__VENDOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__DESCRIPTION);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__DATA_TYPE);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__APPLICATION_TYPE);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__APPLICATION_CONTEXT_TYPE);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__TRIGGER_TYPE);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__EVENT_CONDITION_TYPE);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__EVENT_ACTION_TYPE);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__DATA);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__APPLICATION);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__MODELER);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__ROLE);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__ORGANIZATION);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__CONDITIONAL_PERFORMER);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__PROCESS_DEFINITION);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__EXTERNAL_PACKAGES);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__SCRIPT);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__DIAGRAM);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__LINK_TYPE);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.MODEL_TYPE__VIEW);
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
    * This returns ModelType.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/ModelType"));
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
      String label = ((ModelType)object).getName();
      return label == null || label.length() == 0 ?
         getString("_UI_ModelType_type") :
         getString("_UI_ModelType_type") + " " + label;
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

      switch (notification.getFeatureID(ModelType.class))
      {
         case CarnotWorkflowModelPackage.MODEL_TYPE__ID:
         case CarnotWorkflowModelPackage.MODEL_TYPE__NAME:
         case CarnotWorkflowModelPackage.MODEL_TYPE__AUTHOR:
         case CarnotWorkflowModelPackage.MODEL_TYPE__CARNOT_VERSION:
         case CarnotWorkflowModelPackage.MODEL_TYPE__CREATED:
         case CarnotWorkflowModelPackage.MODEL_TYPE__MODEL_OID:
         case CarnotWorkflowModelPackage.MODEL_TYPE__OID:
         case CarnotWorkflowModelPackage.MODEL_TYPE__VENDOR:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
         case CarnotWorkflowModelPackage.MODEL_TYPE__ATTRIBUTE:
         case CarnotWorkflowModelPackage.MODEL_TYPE__DESCRIPTION:
         case CarnotWorkflowModelPackage.MODEL_TYPE__DATA_TYPE:
         case CarnotWorkflowModelPackage.MODEL_TYPE__APPLICATION_TYPE:
         case CarnotWorkflowModelPackage.MODEL_TYPE__APPLICATION_CONTEXT_TYPE:
         case CarnotWorkflowModelPackage.MODEL_TYPE__TRIGGER_TYPE:
         case CarnotWorkflowModelPackage.MODEL_TYPE__EVENT_CONDITION_TYPE:
         case CarnotWorkflowModelPackage.MODEL_TYPE__EVENT_ACTION_TYPE:
         case CarnotWorkflowModelPackage.MODEL_TYPE__DATA:
         case CarnotWorkflowModelPackage.MODEL_TYPE__APPLICATION:
         case CarnotWorkflowModelPackage.MODEL_TYPE__MODELER:
         case CarnotWorkflowModelPackage.MODEL_TYPE__ROLE:
         case CarnotWorkflowModelPackage.MODEL_TYPE__ORGANIZATION:
         case CarnotWorkflowModelPackage.MODEL_TYPE__CONDITIONAL_PERFORMER:
         case CarnotWorkflowModelPackage.MODEL_TYPE__PROCESS_DEFINITION:
         case CarnotWorkflowModelPackage.MODEL_TYPE__EXTERNAL_PACKAGES:
         case CarnotWorkflowModelPackage.MODEL_TYPE__SCRIPT:
         case CarnotWorkflowModelPackage.MODEL_TYPE__DIAGRAM:
         case CarnotWorkflowModelPackage.MODEL_TYPE__LINK_TYPE:
         case CarnotWorkflowModelPackage.MODEL_TYPE__VIEW:
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
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__DESCRIPTION,
             CarnotWorkflowModelFactory.eINSTANCE.createDescriptionType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__DATA_TYPE,
             CarnotWorkflowModelFactory.eINSTANCE.createDataTypeType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__APPLICATION_TYPE,
             CarnotWorkflowModelFactory.eINSTANCE.createApplicationTypeType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__APPLICATION_CONTEXT_TYPE,
             CarnotWorkflowModelFactory.eINSTANCE.createApplicationContextTypeType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__TRIGGER_TYPE,
             CarnotWorkflowModelFactory.eINSTANCE.createTriggerTypeType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__EVENT_CONDITION_TYPE,
             CarnotWorkflowModelFactory.eINSTANCE.createEventConditionTypeType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__EVENT_ACTION_TYPE,
             CarnotWorkflowModelFactory.eINSTANCE.createEventActionTypeType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__DATA,
             CarnotWorkflowModelFactory.eINSTANCE.createDataType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__APPLICATION,
             CarnotWorkflowModelFactory.eINSTANCE.createApplicationType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__MODELER,
             CarnotWorkflowModelFactory.eINSTANCE.createModelerType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__ROLE,
             CarnotWorkflowModelFactory.eINSTANCE.createRoleType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__ORGANIZATION,
             CarnotWorkflowModelFactory.eINSTANCE.createOrganizationType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__CONDITIONAL_PERFORMER,
             CarnotWorkflowModelFactory.eINSTANCE.createConditionalPerformerType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__PROCESS_DEFINITION,
             CarnotWorkflowModelFactory.eINSTANCE.createProcessDefinitionType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__EXTERNAL_PACKAGES,
             XpdlFactory.eINSTANCE.createExternalPackages()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__SCRIPT,
             XpdlFactory.eINSTANCE.createScriptType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__DIAGRAM,
             CarnotWorkflowModelFactory.eINSTANCE.createDiagramType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__LINK_TYPE,
             CarnotWorkflowModelFactory.eINSTANCE.createLinkTypeType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.MODEL_TYPE__VIEW,
             CarnotWorkflowModelFactory.eINSTANCE.createViewType()));
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
