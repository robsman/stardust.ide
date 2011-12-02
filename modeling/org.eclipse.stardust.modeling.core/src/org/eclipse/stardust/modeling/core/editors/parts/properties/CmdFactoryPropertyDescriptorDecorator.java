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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class CmdFactoryPropertyDescriptorDecorator extends PropertyDescriptor
      implements IAdaptable
{
   private final PropertyDescriptor propDescr;
   private final IPropSheetCmdFactory cmdFactory;

   public static PropertyDescriptor create(PropertyDescriptor propDescr,
         IPropSheetCmdFactory cmdFactory)
   {
      return new CmdFactoryPropertyDescriptorDecorator(propDescr, cmdFactory);
   }

   private CmdFactoryPropertyDescriptorDecorator(PropertyDescriptor propDescr,
         IPropSheetCmdFactory cmdFactory)
   {
      super(propDescr.getId(), propDescr.getDisplayName());

      this.propDescr = propDescr;
      this.cmdFactory = cmdFactory;
   }

   public Object getAdapter(Class adapter)
   {
      Object result = null;
      if (IPropSheetCmdFactory.class.isAssignableFrom(adapter))
      {
         result = cmdFactory;
      }
      return result;
   }

   public CellEditor createPropertyEditor(Composite parent)
   {
      return propDescr.createPropertyEditor(parent);
   }

   public String getCategory()
   {
      return propDescr.getCategory();
   }

   public String getDescription()
   {
      return propDescr.getDescription();
   }

   public String getDisplayName()
   {
      return propDescr.getDisplayName();
   }

   public String[] getFilterFlags()
   {
      return propDescr.getFilterFlags();
   }

   public Object getHelpContextIds()
   {
      return propDescr.getHelpContextIds();
   }

   public Object getId()
   {
      return propDescr.getId();
   }

   public ILabelProvider getLabelProvider()
   {
      return propDescr.getLabelProvider();
   }

   public boolean isCompatibleWith(IPropertyDescriptor anotherProperty)
   {
      return propDescr.isCompatibleWith(anotherProperty);
   }

   public boolean isLabelProviderSet()
   {
      return propDescr.isLabelProviderSet();
   }

   public void setAlwaysIncompatible(boolean flag)
   {
      propDescr.setAlwaysIncompatible(flag);
   }

   public void setCategory(String category)
   {
      propDescr.setCategory(category);
   }

   public void setDescription(String description)
   {
      propDescr.setDescription(description);
   }

   public void setFilterFlags(String[] value)
   {
      propDescr.setFilterFlags(value);
   }

   public void setHelpContextIds(Object contextIds)
   {
      propDescr.setHelpContextIds(contextIds);
   }

   public void setLabelProvider(ILabelProvider provider)
   {
      propDescr.setLabelProvider(provider);
   }

   public void setValidator(ICellEditorValidator validator)
   {
      propDescr.setValidator(validator);
   }
}
