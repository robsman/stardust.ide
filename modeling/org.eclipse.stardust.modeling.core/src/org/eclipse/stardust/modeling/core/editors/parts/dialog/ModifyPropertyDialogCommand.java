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
package org.eclipse.stardust.modeling.core.editors.parts.dialog;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdapter;


public class ModifyPropertyDialogCommand extends Command
{
   private EObject currentValueObject;

   private EObject oldPropertyValueObject;

   private EObject newPropertyValueObject;

   private NotificationAdapter adapter;

   public ModifyPropertyDialogCommand(NotificationAdapter adapter, EObject originalValue,
         EObject newValue)
   {
      super();
      this.currentValueObject = originalValue;
      this.newPropertyValueObject = newValue;
      this.oldPropertyValueObject = EcoreUtil.copy(originalValue);
      this.adapter = adapter;
   }

   public EObject getNewPropertyValueObject()
   {
      return newPropertyValueObject;
   }

   public void setNewPropertyDialogValueObject(EObject newPropertyDialogValueObject)
   {
      this.newPropertyValueObject = newPropertyDialogValueObject;
   }

   public void execute()
   {
      currentValueObject.eAdapters().add(this.adapter);

      for (Iterator _iterator = newPropertyValueObject.eClass().getEAllAttributes()
            .iterator(); _iterator.hasNext();)
      {
         EAttribute attribute = (EAttribute) _iterator.next();
         Object newAttributeValue = newPropertyValueObject.eGet(attribute);

         if (currentValueObject != null && newAttributeValue != null)
         {
            Object currentAttributeValue = currentValueObject.eGet(attribute);

            if (!newAttributeValue.equals(currentAttributeValue))
            {
               currentValueObject.eSet(attribute, newAttributeValue);
            }
         }
      }

      currentValueObject.eAdapters().remove(this.adapter);
   }

   public void undo()
   {
      currentValueObject.eAdapters().add(this.adapter);

      for (Iterator _iterator = oldPropertyValueObject.eClass().getEAllAttributes()
            .iterator(); _iterator.hasNext();)
      {
         EAttribute attribute = (EAttribute) _iterator.next();
         Object oldAttributeValue = oldPropertyValueObject.eGet(attribute);

         if (currentValueObject != null && oldAttributeValue != null)
         {
            Object currentAttributeValue = currentValueObject.eGet(attribute);

            if (!oldAttributeValue.equals(currentAttributeValue))
            {
               currentValueObject.eSet(attribute, oldAttributeValue);
            }
         }
      }

      currentValueObject.eAdapters().remove(this.adapter);
   }

   public void redo()
   {
      currentValueObject.eAdapters().add(this.adapter);

      for (Iterator _iterator = newPropertyValueObject.eClass().getEAllAttributes()
            .iterator(); _iterator.hasNext();)
      {
         EAttribute attribute = (EAttribute) _iterator.next();
         Object newAttributeValue = newPropertyValueObject.eGet(attribute);

         if (currentValueObject != null && newAttributeValue != null)
         {
            Object currentAttributeValue = currentValueObject.eGet(attribute);

            if (!newAttributeValue.equals(currentAttributeValue))
            {
               currentValueObject.eSet(attribute, newAttributeValue);
            }
         }
      }

      currentValueObject.eAdapters().remove(this.adapter);
   }

   public EObject getCurrentValueObject()
   {
      return currentValueObject;
   }

}
