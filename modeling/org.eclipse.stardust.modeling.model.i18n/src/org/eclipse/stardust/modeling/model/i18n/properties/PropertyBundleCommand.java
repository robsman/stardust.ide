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
package org.eclipse.stardust.modeling.model.i18n.properties;

import java.util.Properties;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;


public class PropertyBundleCommand extends CompoundCommand
{
   private ModelType model;
   private PropertyModel propertyModel;

   public PropertyBundleCommand(ModelType model)
   {
      this.model = model;
   }

   public void redo()
   {
      propertyModel = PropertyModel.get(model);
      super.redo();
      propertyModel.save();
      propertyModel.dispose();
   }

   public void undo()
   {
      propertyModel = PropertyModel.get(model);
      super.undo();
      propertyModel.save();
      propertyModel.dispose();
   }

   public void addUpdatePropertiesFor(IIdentifiableElement changedElement, String oldId, String newId)
   {
      add(new UpdatePropertiesFor(changedElement, oldId, newId));
   }
   
   public void addLocale(Object locale)
   {
      add(new AddLocale(locale));
   }

   public void setProperty(Object locale, String propertyName, String newValue, String oldValue)
   {
      add(new SetProperty(locale, propertyName, newValue, oldValue));
   }

   private class UpdatePropertiesFor extends Command
   {
      private IIdentifiableElement changedElement;
      private String oldId;
      private String newId;

      public UpdatePropertiesFor(IIdentifiableElement changedElement, String oldId, String newId)
      {
         this.changedElement = changedElement;
         this.oldId = oldId;
         this.newId = newId;
      }

      public void redo()
      {
         propertyModel.updatePropertiesFor(changedElement, oldId, newId);
      }

      public void undo()
      {
         propertyModel.updatePropertiesFor(changedElement, newId, oldId);
      }
   }
   
   private class AddLocale extends Command
   {
      private Object locale;
      private Properties properties;

      public AddLocale(Object locale)
      {
         this.locale = locale;
      }

      public void redo()
      {
         propertyModel.addLocale(locale, properties);
         properties = null;
      }

      public void undo()
      {
         properties = propertyModel.getPropertiesFor(locale);
         propertyModel.deleteNls(locale);
      }
   }
   
   private class SetProperty extends Command
   {
      private Object locale;
      private String propertyName;
      private String newValue;
      private String oldValue;

      public SetProperty(Object locale, String propertyName, String newValue, String oldValue)
      {
         this.locale = locale;
         this.propertyName = propertyName;
         this.newValue = newValue;
         this.oldValue = oldValue;
      }

      public void redo()
      {
         set(newValue);
      }

      public void undo()
      {
         set(oldValue);
      }
      
      public void set(String value)
      {
         if (value == null)
         {
            propertyModel.deleteProperty(locale, propertyName);
         }
         else
         {
            propertyModel.setProperty(locale, propertyName, value);
         }
      }
   }
}
