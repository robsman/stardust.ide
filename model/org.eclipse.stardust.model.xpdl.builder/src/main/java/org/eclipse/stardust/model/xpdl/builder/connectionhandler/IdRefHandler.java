/*******************************************************************************
 * Copyright (c) 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.connectionhandler;

import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;

public class IdRefHandler implements EObjectReference, Adapter
{
   private static final String CARNOT_CONNECTION_UUID = "carnot:connection:uuid";
   private static final String CARNOT_MODEL_UUID = "carnot:model:uuid";
   private IIdentifiableModelElement owner;
   private IIdentifiableModelElement target;

   private IdRefHandler(IIdentifiableModelElement owner)
   {
      this.owner = owner;
   }

   @Override
   public void notifyChanged(Notification event)
   {
      Object newValue = event.getNewValue();
      if (newValue != null)
      {
         if (CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id().equals(event.getFeature()))
         {
            updateIdRef(event.getNewValue().toString());
         }
      }
   }

   private void updateIdRef(String newId)
   {
      if (owner instanceof IdRefOwner)
      {
         ((IdRefOwner) owner).getExternalRef().setRef(newId);
      }
      else if (owner instanceof DataType)
      {
         ((DataType) owner).getExternalReference().setXref(newId);
      }
   }

   @Override
   public Notifier getTarget()
   {
      return target;
   }

   @Override
   public void setTarget(Notifier target)
   {
      this.target = (IIdentifiableModelElement) target;
   }

   @Override
   public boolean isAdapterForType(Object type)
   {
      return false;
   }

   public static void adapt(IIdentifiableModelElement owner)
   {
      EObject idRef = getIdRef(owner);
      if (idRef != null)
      {
         EObject other = findReferencedObject(owner, idRef);
         if (other != null)
         {
            other.eAdapters().add(new IdRefHandler(owner));
         }
      }
   }

   private static EObject getIdRef(IIdentifiableModelElement owner)
   {
      return owner instanceof IdRefOwner ? ((IdRefOwner) owner).getExternalRef()
            : owner instanceof DataType ? ((DataType) owner).getExternalReference() : null;
   }

   private static EObject findReferencedObject(IIdentifiableModelElement owner, EObject idRef)
   {
      EObject other = getReferencedObject(owner, idRef);
      if (owner instanceof IExtensibleElement)
      {
         String referencedUuid = getConnectionUUID(owner);
         if (!StringUtils.isEmpty(referencedUuid))
         {
            String uuid = other == null ? null : getModelElementUUID(other);
            if (!referencedUuid.equals(uuid))
            {
               ModelType model = getReferencedModel(owner, idRef);
               if (model != null)
               {
                  List<? extends IIdentifiableModelElement> domain = getReferencedClass(owner) == ApplicationType.class
                        ? model.getApplication() : model.getProcessDefinition();
                  for (IIdentifiableModelElement element : domain)
                  {
                     if (element != other)
                     {
                        uuid = AttributeUtil.getAttributeValue((IExtensibleElement) element, CARNOT_MODEL_UUID);
                        if (referencedUuid.equals(uuid))
                        {
                           return element;
                        }
                     }
                  }
               }
            }
         }
      }
      return other;
   }

   private static ModelType getReferencedModel(IIdentifiableModelElement owner, EObject idRef)
   {
      ModelType model = ModelUtils.findContainingModel(owner);
      if (model != null)
      {
         if (idRef instanceof IdRef)
         {
            if (((IdRef) idRef).getPackageRef() != null)
            {
               IConnectionManager manager = model.getConnectionManager();
               model = manager == null ? null : manager.find(((IdRef) idRef).getPackageRef());
            }
         }
         else if (idRef instanceof ExternalReferenceType)
         {
            // TODO
         }
      }
      return model;
   }

   private static String getModelElementUUID(EObject other)
   {
      return other instanceof IExtensibleElement
            ? AttributeUtil.getAttributeValue((IExtensibleElement) other, CARNOT_MODEL_UUID)
            : ExtendedAttributeUtil.getAttributeValue((Extensible) other, CARNOT_MODEL_UUID);
   }

   private static String getConnectionUUID(IIdentifiableModelElement owner)
   {
      return owner instanceof DataType
            ? ((DataType) owner).getExternalReference().getUuid()
            : AttributeUtil.getAttributeValue((IExtensibleElement) owner, CARNOT_CONNECTION_UUID);
   }

   private static EObject getReferencedObject(IIdentifiableModelElement owner, EObject idRef)
   {
      if (idRef instanceof IdRef)
      {
         @SuppressWarnings("unchecked")
         Class<? extends IIdentifiableModelElement> referencedClass =

         (Class< ? extends IIdentifiableModelElement>) getReferencedClass((IdRefOwner) owner);
         return ((IdRef) idRef).get(referencedClass);
      }
      else if (idRef instanceof ExternalReferenceType)
      {
         // TODO: find and return the type declaration
      }
      return null;
   }

   private static Class<? extends EObject> getReferencedClass(EObject owner)
   {
      if (owner instanceof DataType)
      {
         return TypeDeclarationType.class;
      }
      else if (owner instanceof ActivityType
            && ActivityImplementationType.APPLICATION_LITERAL == ((ActivityType) owner).getImplementation())
      {
         return ApplicationType.class;
      }
      return ProcessDefinitionType.class;
   }

   @Override
   public EObject getSelf()
   {
      return owner;
   }

   public static void cleanup(IIdentifiableModelElement owner)
   {
      EObject idRef = getIdRef(owner);
      if (idRef != null)
      {
         EObject other = findReferencedObject(owner, idRef);
         if (other != null)
         {
            for (Adapter adapter : other.eAdapters())
            {
               if ((adapter instanceof IdRefHandler) && ((IdRefHandler) adapter).owner == owner)
               {
                  other.eAdapters().remove(adapter);
                  break;
               }
            }
         }
      }
   }
}
