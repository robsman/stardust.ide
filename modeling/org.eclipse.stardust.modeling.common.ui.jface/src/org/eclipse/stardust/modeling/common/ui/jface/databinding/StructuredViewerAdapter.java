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
package org.eclipse.stardust.modeling.common.ui.jface.databinding;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

/**
 * @author fherinean
 * @version $Revision$
 */
public class StructuredViewerAdapter extends SwtWidgetAdapter
{
   private ISelectionChangedListener listener;
   private StructuredViewer viewer;

   public StructuredViewerAdapter(StructuredViewer viewer)
   {
      super(viewer.getControl());
      this.viewer = viewer;
   }

   public void bind(IBindingMediator manager)
   {
      super.bind(manager);

      // todo: (fh) investigate viewer.addPostSelectionChangedListener();
      viewer.addSelectionChangedListener(listener = new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
            updateModel(selection == null || selection.isEmpty() ? null
               : selection.getFirstElement());
         }
      });
   }

   public void unbind()
   {
      if (null != listener)
      {
         if (!getWidget().isDisposed())
         {
            viewer.removeSelectionChangedListener(listener);
         }
         listener = null;
      }

      super.unbind();
   }

   public void updateControl(Object value)
   {
      viewer.setSelection(value == null ? null : new StructuredSelection(value));
   }
}
