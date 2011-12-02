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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.emf.edit.provider.*;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.TextSymbolType;


/**
 * This is the item provider adapter for a {@link org.eclipse.stardust.model.xpdl.edit.TextSymbolType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TextSymbolTypeItemProvider
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
   public TextSymbolTypeItemProvider(AdapterFactory adapterFactory)
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
         addXPosPropertyDescriptor(object);
         addYPosPropertyDescriptor(object);
         addWidthPropertyDescriptor(object);
         addHeightPropertyDescriptor(object);
         addShapePropertyDescriptor(object);
         addInLinksPropertyDescriptor(object);
         addOutLinksPropertyDescriptor(object);
         addTextPropertyDescriptor(object);
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
    * This adds a property descriptor for the XPos feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addXPosPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_INodeSymbol_xPos_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_INodeSymbol_xPos_feature", "_UI_INodeSymbol_type"),
             CarnotWorkflowModelPackage.Literals.INODE_SYMBOL__XPOS,
             true,
             false,
             false,
             ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the YPos feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addYPosPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_INodeSymbol_yPos_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_INodeSymbol_yPos_feature", "_UI_INodeSymbol_type"),
             CarnotWorkflowModelPackage.Literals.INODE_SYMBOL__YPOS,
             true,
             false,
             false,
             ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Width feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addWidthPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_INodeSymbol_width_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_INodeSymbol_width_feature", "_UI_INodeSymbol_type"),
             CarnotWorkflowModelPackage.Literals.INODE_SYMBOL__WIDTH,
             true,
             false,
             false,
             ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Height feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addHeightPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_INodeSymbol_height_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_INodeSymbol_height_feature", "_UI_INodeSymbol_type"),
             CarnotWorkflowModelPackage.Literals.INODE_SYMBOL__HEIGHT,
             true,
             false,
             false,
             ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Shape feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addShapePropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_INodeSymbol_shape_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_INodeSymbol_shape_feature", "_UI_INodeSymbol_type"),
             CarnotWorkflowModelPackage.Literals.INODE_SYMBOL__SHAPE,
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
    * This adds a property descriptor for the In Links feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addInLinksPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_INodeSymbol_inLinks_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_INodeSymbol_inLinks_feature", "_UI_INodeSymbol_type"),
             CarnotWorkflowModelPackage.Literals.INODE_SYMBOL__IN_LINKS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Out Links feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addOutLinksPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_INodeSymbol_outLinks_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_INodeSymbol_outLinks_feature", "_UI_INodeSymbol_type"),
             CarnotWorkflowModelPackage.Literals.INODE_SYMBOL__OUT_LINKS,
             true,
             false,
             false,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Text feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addTextPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_TextSymbolType_text_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_TextSymbolType_text_feature", "_UI_TextSymbolType_type"),
             CarnotWorkflowModelPackage.Literals.TEXT_SYMBOL_TYPE__TEXT,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This returns TextSymbolType.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/TextSymbolType"));
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
      TextSymbolType textSymbolType = (TextSymbolType)object;
      return getString("_UI_TextSymbolType_type") + " " + textSymbolType.getElementOid();
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

      switch (notification.getFeatureID(TextSymbolType.class))
      {
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__ELEMENT_OID:
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__BORDER_COLOR:
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__FILL_COLOR:
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__STYLE:
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__XPOS:
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__YPOS:
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__WIDTH:
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__HEIGHT:
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__SHAPE:
         case CarnotWorkflowModelPackage.TEXT_SYMBOL_TYPE__TEXT:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
