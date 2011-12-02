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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.ViewableType;
import org.eclipse.stardust.modeling.core.editors.dnd.ModelElementTransfer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;


public class BookmarkDragSourceListener implements DragSourceListener
{
   private TreeViewer viewer;

   public BookmarkDragSourceListener(TreeViewer viewer)
   {
      this.viewer = viewer;
   }

   public void dragStart(DragSourceEvent event)
   {
      event.doit = getElement() != null;
      ModelElementTransfer.getInstance().setObject(getElement());
   }

   public void dragSetData(DragSourceEvent event)
   {
      event.data = getElement();
   }

   private Object getElement()
   {
      Object object = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
      if (object instanceof ViewableType)
      {
         return ((ViewableType) object).getViewable();
      }
      return null;
   }

   public void dragFinished(DragSourceEvent event)
   {
      ModelElementTransfer.getInstance().setObject(null);
   }

}
