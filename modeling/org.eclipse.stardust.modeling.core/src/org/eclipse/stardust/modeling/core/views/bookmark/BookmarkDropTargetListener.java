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
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.ViewableType;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateViewableTypeCommand;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;


public class BookmarkDropTargetListener implements DropTargetListener
{
   private TreeViewer viewer;

   private EditDomain domain;

   private ViewType view;

   public BookmarkDropTargetListener(TreeViewer viewer, EditDomain domain)
   {
      this.viewer = viewer;
      this.domain = domain;
   }

   public void dragEnter(DropTargetEvent event)
   {
      event.detail = DND.DROP_COPY;
   }

   public void dragLeave(DropTargetEvent event)
   {}

   public void dragOperationChanged(DropTargetEvent event)
   {}

   public void dragOver(DropTargetEvent event)
   {}

   public void dropAccept(DropTargetEvent event)
   {}

   public void drop(DropTargetEvent event)
   {
      IModelElement element = ((IModelElement) event.data);
      if ((isViewableElement(element)) && event.item != null)
      {
         Object treeElement = event.item.getData();
         if (treeElement instanceof ViewType)
         {
            view = (ViewType) treeElement;
         }
         else if (treeElement instanceof ViewableType)
         {
            view = (ViewType) ((ViewableType) treeElement).eContainer();
         }
         if (view != null)
         {
            domain.getCommandStack().execute(
                  new CreateViewableTypeCommand(view, element, viewer));
            viewer.refresh();
            viewer.expandToLevel(view, AbstractTreeViewer.ALL_LEVELS);
            view = null;
         }
      }
   }

   private boolean isViewableElement(IModelElement element)
   {
      return element instanceof ActivityType || element instanceof ApplicationType
            || element instanceof DataType || element instanceof ParticipantType
            || element instanceof IModelParticipant
            || element instanceof ProcessDefinitionType || element instanceof TriggerType;
   }

}
