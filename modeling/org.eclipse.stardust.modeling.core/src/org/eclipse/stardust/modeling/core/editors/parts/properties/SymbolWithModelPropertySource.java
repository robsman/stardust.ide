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
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;


public class SymbolWithModelPropertySource implements IPropertySource
{
   private final IPropertySource symbolPropertySource;
   private final IPropertySource modelPropertySource;

   public SymbolWithModelPropertySource(IPropertySource modelPropertySource,
         IPropertySource symbolPropertySource)
   {
      this.modelPropertySource = modelPropertySource;
      this.symbolPropertySource = symbolPropertySource;
   }

   public Object getEditableValue()
   {
      return symbolPropertySource.getEditableValue();
   }

   public IPropertyDescriptor[] getPropertyDescriptors()
   {
      IPropertyDescriptor[] symbolProperties = symbolPropertySource.getPropertyDescriptors();
      IPropertyDescriptor[] modelProperties = modelPropertySource.getPropertyDescriptors();

      IPropertyDescriptor[] result = new IPropertyDescriptor[symbolProperties.length
            + modelProperties.length];
      for (int i = 0; i < symbolProperties.length; i++ )
      {
         result[i] = new AnnotatedPropertyDescriptor(symbolPropertySource,
               symbolProperties[i]);
      }
      for (int i = 0; i < modelProperties.length; i++ )
      {
         result[symbolProperties.length + i] = new AnnotatedPropertyDescriptor(
               modelPropertySource, modelProperties[i]);
      }
      return result;
   }

   public Object getPropertyValue(Object id)
   {
      Object value = null;
      if (id instanceof PropertyIdWithSource)
      {
         PropertyIdWithSource idWithSource = (PropertyIdWithSource) id;
         value = idWithSource.source.getPropertyValue(idWithSource.id);
      }
      else
      {
         System.err.println(Diagram_Messages.ERR_UnsupportedPropertyId + id);
      }
      return value;
   }

   public boolean isPropertySet(Object id)
   {
      boolean isSet = false;
      if (id instanceof PropertyIdWithSource)
      {
         PropertyIdWithSource idWithSource = (PropertyIdWithSource) id;
         isSet = idWithSource.source.isPropertySet(idWithSource.id);
      }
      else
      {
         System.err.println(Diagram_Messages.ERR_UnsupportedPropertyId + id);
      }
      return isSet;
   }

   public void resetPropertyValue(Object id)
   {
      if (id instanceof PropertyIdWithSource)
      {
         PropertyIdWithSource idWithSource = (PropertyIdWithSource) id;
         idWithSource.source.resetPropertyValue(idWithSource.id);
      }
      else
      {
         System.err.println(Diagram_Messages.ERR_UnsupportedPropertyId + id);
      }
   }

   public void setPropertyValue(Object id, Object value)
   {
      if (id instanceof PropertyIdWithSource)
      {
         PropertyIdWithSource idWithSource = (PropertyIdWithSource) id;
         idWithSource.source.setPropertyValue(idWithSource.id, value);
      }
      else
      {
         System.err.println(Diagram_Messages.ERR_UnsupportedPropertyId + id);
      }
   }

   private static class AnnotatedPropertyDescriptor implements IPropertyDescriptor
   {
      private final IPropertySource propSource;

      private final IPropertyDescriptor propDescriptor;

      AnnotatedPropertyDescriptor(IPropertySource source, IPropertyDescriptor descriptor)
      {
         this.propSource = source;
         this.propDescriptor = descriptor;
      }

      public Object getId()
      {
         return new PropertyIdWithSource(propSource, propDescriptor.getId());
      }

      public CellEditor createPropertyEditor(Composite parent)
      {
         return propDescriptor.createPropertyEditor(parent);
      }

      public String getCategory()
      {
         return propDescriptor.getCategory();
      }

      public String getDescription()
      {
         return propDescriptor.getDescription();
      }

      public String getDisplayName()
      {
         return propDescriptor.getDisplayName();
      }

      public String[] getFilterFlags()
      {
         return propDescriptor.getFilterFlags();
      }

      public Object getHelpContextIds()
      {
         return propDescriptor.getHelpContextIds();
      }

      public ILabelProvider getLabelProvider()
      {
         return propDescriptor.getLabelProvider();
      }

      public boolean isCompatibleWith(IPropertyDescriptor anotherProperty)
      {
         return propDescriptor.isCompatibleWith(anotherProperty);
      }
   }

   private static class PropertyIdWithSource
   {
      final IPropertySource source;
      final Object id;

      PropertyIdWithSource(IPropertySource source, Object id)
      {
         this.source = source;
         this.id = id;
      }
   }
}
