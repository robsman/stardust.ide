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

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.swt.widgets.Composite;


/**
 * ModelElementList cell editor, heavily inspired by
 * {@link org.eclipse.emf.edit.ui.provider.ExtendedComboBoxCellEditor}.
 * 
 * @author rsauer
 * @version $Revision$
 */
public class ModelElementListCellEditor extends ComboBoxCellEditor
{
   /**
    * This keeps track of the list of model objects.
    */
   private List list;

   public static ModelElementListCellEditor createInstance(Composite parent, List elements)
   {
      return new ModelElementListCellEditor(parent, elements);
   }

   protected ModelElementListCellEditor(Composite parent, List values)
   {
      super(parent, getItemLabels(values));

      this.list = values;
   }

   public Object doGetValue()
   {
      // Get the index into the list via this call to super.
      //
      int index = ((Integer) super.doGetValue()).intValue();
      return (index < list.size() && index >= 0)
            ? list.get(((Integer) super.doGetValue()).intValue()) : null;
   }

   public void doSetValue(Object value)
   {
      // Set the index of the object value in the list via this call to super.
      //

      super.doSetValue(new Integer(list.indexOf(value)));
   }

   private static String[] getItemLabels(List list)
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
            Object element = objects.next();
            if (element instanceof IIdentifiableElement)
            {
               IIdentifiableElement identifiable = (IIdentifiableElement) element;
               result[i] = identifiable.getName() == null || identifiable.getName().length() == 0 ?
                  identifiable.getId() : identifiable.getName();
            }
            else if (element instanceof IModelElement)
            {
               IModelElement modelElement = (IModelElement) element;
               result[i] = Long.toString(modelElement.getElementOid());
            }
            else
            {
               result[i] = element.toString();
            }
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