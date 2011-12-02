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
package org.eclipse.stardust.modeling.core.views.bookmark;

import org.eclipse.gef.EditDomain;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.RenameViewCommand;
import org.eclipse.swt.widgets.TreeItem;


public abstract class AbstractCellModifier implements ICellModifier
{
   private EditDomain editDomain;

   private TreeViewer viewer;

   public AbstractCellModifier(EditDomain editDomain, TreeViewer viewer)
   {
      this.editDomain = editDomain;
      this.viewer = viewer;
   }

   public Object getValue(Object element, String property)
   {
      String value = null;
      if (element instanceof ViewType)
      {
         value = ((ViewType) element).getName();
      }
      return value;
   }

   public void modify(Object element, String property, Object value)
   {
      Object o = ((TreeItem) element).getData();
      if (o instanceof ViewType)
      {
         editDomain.getCommandStack().execute(
               new RenameViewCommand((ViewType) o, (String) value, viewer));
      }
   }

}
