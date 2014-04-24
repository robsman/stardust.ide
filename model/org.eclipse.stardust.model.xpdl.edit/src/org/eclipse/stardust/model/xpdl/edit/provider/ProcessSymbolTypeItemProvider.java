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
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.ProcessSymbolType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessSymbolTypeItemProvider
   extends IModelElementNodeSymbolItemProvider
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
   public ProcessSymbolTypeItemProvider(AdapterFactory adapterFactory)
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

         addProcessPropertyDescriptor(object);
         addSubProcessesPropertyDescriptor(object);
         addParentProcessesPropertyDescriptor(object);
      }
      return itemPropertyDescriptors;
   }

   /**
    * This adds a property descriptor for the Process feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addProcessPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ProcessSymbolType_process_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_ProcessSymbolType_process_feature", "_UI_ProcessSymbolType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.PROCESS_SYMBOL_TYPE__PROCESS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Sub Processes feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addSubProcessesPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ProcessSymbolType_subProcesses_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_ProcessSymbolType_subProcesses_feature", "_UI_ProcessSymbolType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.PROCESS_SYMBOL_TYPE__SUB_PROCESSES,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Parent Processes feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addParentProcessesPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_ProcessSymbolType_parentProcesses_feature"), //$NON-NLS-1$
             getString("_UI_PropertyDescriptor_description", "_UI_ProcessSymbolType_parentProcesses_feature", "_UI_ProcessSymbolType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             CarnotWorkflowModelPackage.Literals.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This returns ProcessSymbolType.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/ProcessSymbolType")); //$NON-NLS-1$
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
      ProcessSymbolType processSymbolType = (ProcessSymbolType)object;
      return getString("_UI_ProcessSymbolType_type") + " " + processSymbolType.getElementOid(); //$NON-NLS-1$ //$NON-NLS-2$
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
