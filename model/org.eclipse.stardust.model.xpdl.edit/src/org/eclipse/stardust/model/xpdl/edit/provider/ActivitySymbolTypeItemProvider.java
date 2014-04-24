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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.emf.edit.provider.*;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.ActivitySymbolType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ActivitySymbolTypeItemProvider
   extends IFlowObjectSymbolItemProvider
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
   public ActivitySymbolTypeItemProvider(AdapterFactory adapterFactory)
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

         addActivityPropertyDescriptor(object);
         addPerformsConnectionsPropertyDescriptor(object);
         addExecutedByConnectionsPropertyDescriptor(object);
         addDataMappingsPropertyDescriptor(object);
         addGatewaySymbolsPropertyDescriptor(object);
      }
      return itemPropertyDescriptors;
   }

   /**
    * This adds a property descriptor for the Activity feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addActivityPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ActivitySymbolType_activity_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_ActivitySymbolType_activity_feature", "_UI_ActivitySymbolType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.ACTIVITY_SYMBOL_TYPE__ACTIVITY,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Performs Connections feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addPerformsConnectionsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ActivitySymbolType_performsConnections_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_ActivitySymbolType_performsConnections_feature", "_UI_ActivitySymbolType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.ACTIVITY_SYMBOL_TYPE__PERFORMS_CONNECTIONS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Executed By Connections feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addExecutedByConnectionsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ActivitySymbolType_executedByConnections_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_ActivitySymbolType_executedByConnections_feature", "_UI_ActivitySymbolType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.ACTIVITY_SYMBOL_TYPE__EXECUTED_BY_CONNECTIONS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Data Mappings feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addDataMappingsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ActivitySymbolType_dataMappings_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_ActivitySymbolType_dataMappings_feature", "_UI_ActivitySymbolType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.ACTIVITY_SYMBOL_TYPE__DATA_MAPPINGS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Gateway Symbols feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addGatewaySymbolsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ActivitySymbolType_gatewaySymbols_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_ActivitySymbolType_gatewaySymbols_feature", "_UI_ActivitySymbolType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This returns ActivitySymbolType.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/ActivitySymbolType")); //$NON-NLS-1$
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
      ActivitySymbolType activitySymbolType = (ActivitySymbolType)object;
      return getString("_UI_ActivitySymbolType_type") + " " + activitySymbolType.getElementOid(); //$NON-NLS-1$ //$NON-NLS-2$
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
   }

}
