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
package org.eclipse.stardust.modeling.repository.common.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class ObjectRepositoryView extends ViewPart
{
   private TreeViewer viewer;

   public ObjectRepositoryView()
   {
      viewer = new TreeViewer();
   }

   public void createPartControl(Composite parent)
   {
      viewer.createControl(parent);
      viewer.setEditPartFactory(new EditPartFactory()
      {
         public EditPart createEditPart(EditPart context, Object model)
         {
            return (EditPart) Platform.getAdapterManager().getAdapter(model, TreeEditPart.class);
         }
      });
//      viewer.addDragSourceListener(new ModelElementTransferDragSourceListener(viewer));
   }

   public void setFocus()
   {
   }
   
   public void saveState(IMemento memento)
   {
//      contentProvider.saveState(memento);
   }

   public void init(IViewSite site, IMemento memento) throws PartInitException
   {
      super.init(site, memento);
//      contentProvider.loadState(memento);
   }
}
