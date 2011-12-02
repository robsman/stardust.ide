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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * @author rsauer TODO To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Style - Code Templates
 */
public class BooleanPropertyDescriptor extends PropertyDescriptor
{
   public BooleanPropertyDescriptor(Object id, String displayName)
   {
      super(id, displayName);
   }

   public CellEditor createPropertyEditor(Composite parent)
   {
      CellEditor editor = new BooleanCellEditor(parent);
      if (getValidator() != null)
      {
         editor.setValidator(getValidator());
      }

      return editor;
   }
}