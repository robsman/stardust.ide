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

public class IdRefHandler implements EObjectReference, Adapter
{
   private IdRefOwner owner;
   private IIdentifiableModelElement target;

   private IdRefHandler(IdRefOwner owner)
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
      owner.getExternalRef().setRef(newId);
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

   public static void adapt(IdRefOwner owner)
   {
      IdRef idRef = owner.getExternalRef();
      if (idRef != null)
      {
         IIdentifiableModelElement other = findReferencedObject(owner, idRef);
         if (other != null)
         {
            other.eAdapters().add(new IdRefHandler(owner));
         }
      }
   }

   private static IIdentifiableModelElement findReferencedObject(IdRefOwner owner,
         IdRef idRef)
   {
      IIdentifiableModelElement other = idRef.get(getReferencedClass(owner));
      if (owner instanceof IExtensibleElement)
      {
         String referencedUuid = AttributeUtil.getAttributeValue((IExtensibleElement) owner, "carnot:connection:uuid");
         if (!StringUtils.isEmpty(referencedUuid))
         {
            String uuid = other == null ? null : AttributeUtil.getAttributeValue((IExtensibleElement) other, "carnot:model:uuid");
            if (!referencedUuid.equals(uuid))
            {
               ModelType model = ModelUtils.findContainingModel(owner);
               if (idRef.getPackageRef() != null && model != null)
               {
                  IConnectionManager manager = model.getConnectionManager();
                  model = manager == null ? null : manager.find(idRef.getPackageRef());
               }
               if (model != null)
               {
                  List<? extends IIdentifiableModelElement> domain = getReferencedClass(owner) == ApplicationType.class
                        ? model.getApplication() : model.getProcessDefinition();
                  for (IIdentifiableModelElement element : domain)
                  {
                     if (element != other)
                     {
                        uuid = AttributeUtil.getAttributeValue((IExtensibleElement) element, "carnot:model:uuid");
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

   private static Class<? extends IIdentifiableModelElement> getReferencedClass(IdRefOwner owner)
   {
      if (owner instanceof ActivityType
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

   public static void cleanup(IdRefOwner owner)
   {
      IdRef idRef = owner.getExternalRef();
      if (idRef != null)
      {
         IIdentifiableModelElement other = findReferencedObject(owner, idRef);
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
