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
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.ProcessDefinitionType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessDefinitionTypeItemProvider
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
   public ProcessDefinitionTypeItemProvider(AdapterFactory adapterFactory)
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
         addExecutingActivitiesPropertyDescriptor(object);
         addProcessSymbolsPropertyDescriptor(object);
         addDefaultPriorityPropertyDescriptor(object);
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
             getString("_UI_IModelElement_elementOid_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IModelElement_elementOid_feature", "_UI_IModelElement_type"),
             CarnotWorkflowModelPackage.Literals.IMODEL_ELEMENT__ELEMENT_OID,
             true,
             false,
             false,
             ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
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
    * This adds a property descriptor for the Executing Activities feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addExecutingActivitiesPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ProcessDefinitionType_executingActivities_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ProcessDefinitionType_executingActivities_feature", "_UI_ProcessDefinitionType_type"),
             CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Process Symbols feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addProcessSymbolsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ProcessDefinitionType_processSymbols_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ProcessDefinitionType_processSymbols_feature", "_UI_ProcessDefinitionType_type"),
             CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Default Priority feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addDefaultPriorityPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ProcessDefinitionType_defaultPriority_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_ProcessDefinitionType_defaultPriority_feature", "_UI_ProcessDefinitionType_type"),
             CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__DEFAULT_PRIORITY,
             true,
             false,
             false,
             ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
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
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.IEVENT_HANDLER_OWNER__EVENT_HANDLER);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__ACTIVITY);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__TRANSITION);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__TRIGGER);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__DATA_PATH);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__DIAGRAM);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__EXTERNAL_REF);
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
    * This returns ProcessDefinitionType.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/ProcessDefinitionType"));
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
      String label = ((ProcessDefinitionType)object).getName();
      return label == null || label.length() == 0 ?
         getString("_UI_ProcessDefinitionType_type") :
         getString("_UI_ProcessDefinitionType_type") + " " + label;
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

      switch (notification.getFeatureID(ProcessDefinitionType.class))
      {
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ELEMENT_OID:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ID:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__NAME:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DEFAULT_PRIORITY:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ATTRIBUTE:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DESCRIPTION:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EVENT_HANDLER:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__ACTIVITY:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRANSITION:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__TRIGGER:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DATA_PATH:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__DIAGRAM:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS:
         case CarnotWorkflowModelPackage.PROCESS_DEFINITION_TYPE__EXTERNAL_REF:
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

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.IEVENT_HANDLER_OWNER__EVENT_HANDLER,
             CarnotWorkflowModelFactory.eINSTANCE.createEventHandlerType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__ACTIVITY,
             CarnotWorkflowModelFactory.eINSTANCE.createActivityType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__TRANSITION,
             CarnotWorkflowModelFactory.eINSTANCE.createTransitionType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__TRIGGER,
             CarnotWorkflowModelFactory.eINSTANCE.createTriggerType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__DATA_PATH,
             CarnotWorkflowModelFactory.eINSTANCE.createDataPathType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__DIAGRAM,
             CarnotWorkflowModelFactory.eINSTANCE.createDiagramType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS,
             XpdlFactory.eINSTANCE.createFormalParametersType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS,
             ExtensionsFactory.eINSTANCE.createFormalParameterMappingsType()));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.PROCESS_DEFINITION_TYPE__EXTERNAL_REF,
             CarnotWorkflowModelFactory.eINSTANCE.createIdRef()));
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
