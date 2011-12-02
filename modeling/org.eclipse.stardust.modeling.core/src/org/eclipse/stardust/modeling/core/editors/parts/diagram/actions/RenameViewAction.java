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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import org.eclipse.gef.EditDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.views.bookmark.AbstractCellModifier;


public class RenameViewAction extends Action
{
   private static final String ACTION_NAME = Diagram_Messages.NAME_RenameViewAction;

   private TreeViewer viewer;

   private ViewType view;

   private EditDomain editDomain;

   public RenameViewAction(EditDomain editDomain, TreeViewer viewer)
   {
      super(ACTION_NAME);
      this.viewer = viewer;
      this.editDomain = editDomain;
   }

   public void run()
   {
      view = (ViewType) ((IStructuredSelection) viewer.getSelection()).getFirstElement();
      addCellModifierToView(true);
      viewer.editElement(view, 0);
      addCellModifierToView(false);
   }

   private void addCellModifierToView(final boolean canModify)
   {
      viewer.setCellModifier(new AbstractCellModifier(editDomain, viewer)
      {
         public boolean canModify(Object element, String property)
         {
            return canModify ? element instanceof ViewType : canModify;
         }
      });
   }
}
