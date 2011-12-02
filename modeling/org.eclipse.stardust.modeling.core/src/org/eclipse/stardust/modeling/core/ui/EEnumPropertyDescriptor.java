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

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * @author rsauer TODO To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Style - Code Templates
 */
public class EEnumPropertyDescriptor extends PropertyDescriptor
{
   private final EEnum type;

   public EEnumPropertyDescriptor(Object id, String displayName, EEnum type)
   {
      super(id, displayName);

      this.type = type;
   }

   public CellEditor createPropertyEditor(Composite parent)
   {
      CellEditor editor = EEnumCellEditor.createInstance(parent, type);
      if (getValidator() != null)
      {
         editor.setValidator(getValidator());
      }

      return editor;
   }
}