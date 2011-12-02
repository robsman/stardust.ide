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
import org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.WorksForConnectionType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class WorksForConnectionTypeItemProvider
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
   public WorksForConnectionTypeItemProvider(AdapterFactory adapterFactory)
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
         addBorderColorPropertyDescriptor(object);
         addFillColorPropertyDescriptor(object);
         addStylePropertyDescriptor(object);
         addReferingToConnectionsPropertyDescriptor(object);
         addReferingFromConnectionsPropertyDescriptor(object);
         addSourceAnchorPropertyDescriptor(object);
         addTargetAnchorPropertyDescriptor(object);
         addRoutingPropertyDescriptor(object);
         addOrganizationSymbolPropertyDescriptor(object);
         addParticipantSymbolPropertyDescriptor(object);
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
    * This adds a property descriptor for the Border Color feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addBorderColorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IGraphicalObject_borderColor_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IGraphicalObject_borderColor_feature", "_UI_IGraphicalObject_type"),
             CarnotWorkflowModelPackage.Literals.IGRAPHICAL_OBJECT__BORDER_COLOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Fill Color feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addFillColorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IGraphicalObject_fillColor_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IGraphicalObject_fillColor_feature", "_UI_IGraphicalObject_type"),
             CarnotWorkflowModelPackage.Literals.IGRAPHICAL_OBJECT__FILL_COLOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Style feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addStylePropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IGraphicalObject_style_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IGraphicalObject_style_feature", "_UI_IGraphicalObject_type"),
             CarnotWorkflowModelPackage.Literals.IGRAPHICAL_OBJECT__STYLE,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Refering To Connections feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addReferingToConnectionsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IGraphicalObject_referingToConnections_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IGraphicalObject_referingToConnections_feature", "_UI_IGraphicalObject_type"),
             CarnotWorkflowModelPackage.Literals.IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Refering From Connections feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addReferingFromConnectionsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IGraphicalObject_referingFromConnections_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IGraphicalObject_referingFromConnections_feature", "_UI_IGraphicalObject_type"),
             CarnotWorkflowModelPackage.Literals.IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Source Anchor feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addSourceAnchorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IConnectionSymbol_sourceAnchor_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IConnectionSymbol_sourceAnchor_feature", "_UI_IConnectionSymbol_type"),
             CarnotWorkflowModelPackage.Literals.ICONNECTION_SYMBOL__SOURCE_ANCHOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Target Anchor feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addTargetAnchorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IConnectionSymbol_targetAnchor_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IConnectionSymbol_targetAnchor_feature", "_UI_IConnectionSymbol_type"),
             CarnotWorkflowModelPackage.Literals.ICONNECTION_SYMBOL__TARGET_ANCHOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Routing feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addRoutingPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IConnectionSymbol_routing_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IConnectionSymbol_routing_feature", "_UI_IConnectionSymbol_type"),
             CarnotWorkflowModelPackage.Literals.ICONNECTION_SYMBOL__ROUTING,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Organization Symbol feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addOrganizationSymbolPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_WorksForConnectionType_organizationSymbol_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_WorksForConnectionType_organizationSymbol_feature", "_UI_WorksForConnectionType_type"),
             CarnotWorkflowModelPackage.Literals.WORKS_FOR_CONNECTION_TYPE__ORGANIZATION_SYMBOL,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Participant Symbol feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addParticipantSymbolPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_WorksForConnectionType_participantSymbol_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_WorksForConnectionType_participantSymbol_feature", "_UI_WorksForConnectionType_type"),
             CarnotWorkflowModelPackage.Literals.WORKS_FOR_CONNECTION_TYPE__PARTICIPANT_SYMBOL,
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
         childrenFeatures.add(CarnotWorkflowModelPackage.Literals.ICONNECTION_SYMBOL__COORDINATES);
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
    * This returns WorksForConnectionType.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/WorksForConnectionType"));
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
      WorksForConnectionType worksForConnectionType = (WorksForConnectionType)object;
      return getString("_UI_WorksForConnectionType_type") + " " + worksForConnectionType.getElementOid();
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

      switch (notification.getFeatureID(WorksForConnectionType.class))
      {
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__ELEMENT_OID:
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__BORDER_COLOR:
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__FILL_COLOR:
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__STYLE:
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__SOURCE_ANCHOR:
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__TARGET_ANCHOR:
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__ROUTING:
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__ORGANIZATION_SYMBOL:
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__PARTICIPANT_SYMBOL:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
         case CarnotWorkflowModelPackage.WORKS_FOR_CONNECTION_TYPE__COORDINATES:
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
            (CarnotWorkflowModelPackage.Literals.ICONNECTION_SYMBOL__COORDINATES,
             CarnotWorkflowModelFactory.eINSTANCE.createCoordinates()));
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
