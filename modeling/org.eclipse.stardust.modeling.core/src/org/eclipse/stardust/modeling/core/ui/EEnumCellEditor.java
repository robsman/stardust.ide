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
package org.eclipse.stardust.modeling.core.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * EEnum cell editor, heavily inspired by
 * {@link org.eclipse.emf.edit.ui.provider.ExtendedComboBoxCellEditor}.
 * 
 * @author rsauer
 * @version $Revision$
 */
public class EEnumCellEditor extends ComboBoxCellEditor
{
   /**
    * This keeps track of the list of model objects.
    */
   private List list;

   public static EEnumCellEditor createInstance(Composite parent, EEnum type)
   {
      return new EEnumCellEditor(parent, getEnumItems(type));
   }

   protected EEnumCellEditor(Composite parent, List values)
   {
      super(parent, getItemLabels(values, true));

      this.list = values;
   }

   public Object doGetValue()
   {
      // Get the index into the list via this call to super.
      //
      int index = ((Integer) super.doGetValue()).intValue();
      return (index < list.size() && index >= 0)
            ? ((EEnumLiteral) list.get(((Integer) super.doGetValue()).intValue())).getInstance()
            : null;
   }

   public void doSetValue(Object value)
   {
      // Set the index of the object value in the list via this call to super.
      //

      for (Iterator i = list.iterator(); i.hasNext();)
      {
         EEnumLiteral literal = (EEnumLiteral) i.next();
         if (literal.getValue() == ((Enumerator) value).getValue())
         {
            super.doSetValue(new Integer(list.indexOf(literal)));
         }
      }
   }

   private static List getEnumItems(EEnum enumType)
   {
      // if (feature.getEType() instanceof EEnum)
      // EEnum enum = (EEnum)feature.getEType();
      List enumerators = new ArrayList();
      for (Iterator iter = enumType.getELiterals().iterator(); iter.hasNext();)
      {
         enumerators.add(iter.next());
      }
      return enumerators;
   }

   private static String[] getItemLabels(List list, boolean sorted)
   {
      String[] result;

      // If there are objects to populate...
      //
      if (list != null && list.size() > 0)
      {
         // Create an new array..
         //
         result = new String[list.size()];

         // Fill in the array with label/value pair items.
         //
         int i = 0;
         for (Iterator objects = list.iterator(); objects.hasNext(); ++i)
         {
            EEnumLiteral literal = (EEnumLiteral) objects.next();
            result[i] = literal.getName();
         }

         // We could collate the array, but we'd have to keep the list in synch.
         //
         // Arrays.sort(result, java.text.Collator.getInstance());
      }
      else
      {
         result = new String[] {""}; //$NON-NLS-1$
      }

      return result;
   }

}