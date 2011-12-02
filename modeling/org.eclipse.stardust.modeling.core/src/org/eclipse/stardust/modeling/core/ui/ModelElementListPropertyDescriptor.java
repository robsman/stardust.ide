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
/*
 * Created on 09.05.2004 TODO To change the template for this generated file go to Window - Preferences - Java - Code
 * Style - Code Templates
 */
package org.eclipse.stardust.modeling.core.ui;

import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;


/**
 * @author rsauer TODO To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Style - Code Templates
 */
public class ModelElementListPropertyDescriptor extends PropertyDescriptor
{
   private final List elements;

   public ModelElementListPropertyDescriptor(Object id, String displayName, List elements)
   {
      super(id, displayName);

      setLabelProvider(new LabelProvider()
      {
         public String getText(Object object)
         {
            if (object instanceof IIdentifiableElement)
            {
               IIdentifiableElement identifiable = (IIdentifiableElement) object;
               return identifiable.getName() == null || identifiable.getName().length() == 0
                  ? identifiable.getId() == null ? "?" : identifiable.getId() //$NON-NLS-1$
                  : identifiable.getName();
            }
            if (object instanceof IModelElement)
            {
               IModelElement element = (IModelElement) object;
               return Long.toString(element.getElementOid());
            }
            return super.getText(object);
         }
      });

      this.elements = elements;
   }

   public CellEditor createPropertyEditor(Composite parent)
   {
      CellEditor editor = ModelElementListCellEditor.createInstance(parent, elements);
      if (getValidator() != null)
      {
         editor.setValidator(getValidator());
      }

      return editor;
   }
}