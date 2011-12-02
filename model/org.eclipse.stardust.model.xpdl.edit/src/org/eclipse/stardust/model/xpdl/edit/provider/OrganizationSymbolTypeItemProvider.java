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
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.OrganizationSymbolType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OrganizationSymbolTypeItemProvider
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
   public OrganizationSymbolTypeItemProvider(AdapterFactory adapterFactory)
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

         addPerformedActivitiesPropertyDescriptor(object);
         addTriggeredEventsPropertyDescriptor(object);
         addOrganizationPropertyDescriptor(object);
         addSuperOrganizationsPropertyDescriptor(object);
         addSubOrganizationsPropertyDescriptor(object);
         addMemberRolesPropertyDescriptor(object);
         addTeamLeadPropertyDescriptor(object);
      }
      return itemPropertyDescriptors;
   }

   /**
    * This adds a property descriptor for the Performed Activities feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addPerformedActivitiesPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IModelParticipantSymbol_performedActivities_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IModelParticipantSymbol_performedActivities_feature", "_UI_IModelParticipantSymbol_type"),
             CarnotWorkflowModelPackage.Literals.IMODEL_PARTICIPANT_SYMBOL__PERFORMED_ACTIVITIES,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Triggered Events feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addTriggeredEventsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_IModelParticipantSymbol_triggeredEvents_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_IModelParticipantSymbol_triggeredEvents_feature", "_UI_IModelParticipantSymbol_type"),
             CarnotWorkflowModelPackage.Literals.IMODEL_PARTICIPANT_SYMBOL__TRIGGERED_EVENTS,
             true,
             false,
             true,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Organization feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addOrganizationPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_OrganizationSymbolType_organization_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_OrganizationSymbolType_organization_feature", "_UI_OrganizationSymbolType_type"),
             CarnotWorkflowModelPackage.Literals.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Super Organizations feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addSuperOrganizationsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_OrganizationSymbolType_superOrganizations_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_OrganizationSymbolType_superOrganizations_feature", "_UI_OrganizationSymbolType_type"),
             CarnotWorkflowModelPackage.Literals.ORGANIZATION_SYMBOL_TYPE__SUPER_ORGANIZATIONS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Sub Organizations feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addSubOrganizationsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_OrganizationSymbolType_subOrganizations_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_OrganizationSymbolType_subOrganizations_feature", "_UI_OrganizationSymbolType_type"),
             CarnotWorkflowModelPackage.Literals.ORGANIZATION_SYMBOL_TYPE__SUB_ORGANIZATIONS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Member Roles feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addMemberRolesPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_OrganizationSymbolType_memberRoles_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_OrganizationSymbolType_memberRoles_feature", "_UI_OrganizationSymbolType_type"),
             CarnotWorkflowModelPackage.Literals.ORGANIZATION_SYMBOL_TYPE__MEMBER_ROLES,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Team Lead feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addTeamLeadPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_OrganizationSymbolType_teamLead_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_OrganizationSymbolType_teamLead_feature", "_UI_OrganizationSymbolType_type"),
             CarnotWorkflowModelPackage.Literals.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD,
             true,
             false,
             true,
             null,
             null,
             null));
   }

   /**
    * This returns OrganizationSymbolType.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/OrganizationSymbolType"));
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
      OrganizationSymbolType organizationSymbolType = (OrganizationSymbolType)object;
      return getString("_UI_OrganizationSymbolType_type") + " " + organizationSymbolType.getElementOid();
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
