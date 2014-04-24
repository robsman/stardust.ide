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
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.emf.edit.provider.*;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.ISymbolContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ISymbolContainerItemProvider
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
   public ISymbolContainerItemProvider(AdapterFactory adapterFactory)
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

      }
      return itemPropertyDescriptors;
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
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES);
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS);
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
    * This returns the label text for the adapted class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String getText(Object object)
   {
      return getString("_UI_ISymbolContainer_type"); //$NON-NLS-1$
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

      switch (notification.getFeatureID(ISymbolContainer.class))
      {
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__NODES:
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONNECTIONS:
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
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createActivitySymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createAnnotationSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__APPLICATION_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createApplicationSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createConditionalPerformerSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__DATA_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createDataSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS,
                CarnotWorkflowModelFactory.eINSTANCE.createEndEventSymbol())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__GATEWAY_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createGatewaySymbol())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__GROUP_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createGroupSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS,
                CarnotWorkflowModelFactory.eINSTANCE.createIntermediateEventSymbol())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__MODELER_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createModelerSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createOrganizationSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__PROCESS_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createProcessSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS,
                CarnotWorkflowModelFactory.eINSTANCE.createPublicInterfaceSymbol())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__ROLE_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createRoleSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS,
                CarnotWorkflowModelFactory.eINSTANCE.createStartEventSymbol())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__NODES,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__TEXT_SYMBOL,
                CarnotWorkflowModelFactory.eINSTANCE.createTextSymbolType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createDataMappingConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createExecutedByConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createGenericLinkConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__PART_OF_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createPartOfConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__PERFORMS_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createPerformsConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createTriggersConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createRefersToConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createSubProcessOfConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__TRANSITION_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createTransitionConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createWorksForConnectionType())));

      newChildDescriptors.add
         (createChildParameter
            (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONNECTIONS,
             FeatureMapUtil.createEntry
               (CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION,
                CarnotWorkflowModelFactory.eINSTANCE.createTeamLeadConnectionType())));
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
