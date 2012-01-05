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

import java.util.Collection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.AbstractWidgetAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IBindingMediator;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;


/**
 * @author fherinean
 * @version $Revision$
 */
public class Data2DataPathWidgetAdapter2 extends AbstractWidgetAdapter
{
   private StructuredViewer dataText;
   private DirectionType direction;
   private ISelectionChangedListener dataListener;
   private AccessPathBrowserComposite dataPathBrowser;

   public Data2DataPathWidgetAdapter2(StructuredViewer dataText,
         AccessPathBrowserComposite dataPathBrowser, DirectionType direction)
   {
      this.dataText = dataText;
      this.dataPathBrowser = dataPathBrowser;
      this.direction = direction;
   }

   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof Data2DataPathWidgetAdapter2)) return false;

      final Data2DataPathWidgetAdapter2 data2DataPathWidgetAdapter2 = (Data2DataPathWidgetAdapter2) o;

      if (!dataPathBrowser.equals(data2DataPathWidgetAdapter2.dataPathBrowser)) return false;
      if (!dataText.equals(data2DataPathWidgetAdapter2.dataText)) return false;
      if (!direction.equals(data2DataPathWidgetAdapter2.direction)) return false;

      return true;
   }

   public int hashCode()
   {
      int result;
      result = dataText.hashCode();
      result = 29 * result + direction.hashCode();
      result = 29 * result + dataPathBrowser.hashCode();
      return result;
   }

   public void bind(IBindingMediator manager)
   {
      super.bind(manager);

      this.dataListener = createDataListener(ModelUtils.getDualDirection(direction));

      dataText.addSelectionChangedListener(dataListener);
      dataText.getControl().addDisposeListener(new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            unbind();
         }
      });
   }

   public void updateVisuals(Object value)
   {
      if (value instanceof Collection)
      {
         ISelection selection = dataText.getSelection();
         dataText.setInput(value);
         dataText.setSelection(selection);
         updateDataPathData(dataText.getSelection(), ModelUtils.getDualDirection(direction));
      }
   }

   public void unbind()
   {
      if (null != dataListener)
      {
         if (!dataText.getControl().isDisposed())
         {
            dataText.removeSelectionChangedListener(dataListener);
         }
         dataListener = null;
      }

      super.unbind();
   }

   private ISelectionChangedListener createDataListener(final DirectionType direction)
   {
      return new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            updateDataPathData(event.getSelection(), direction);
         }
      };
   }

   private void updateDataPathData(ISelection selection, DirectionType direction)
   {
      ITypedElement data = null;
      if (selection instanceof IStructuredSelection)
      {
         IStructuredSelection structuredSelection = (IStructuredSelection) selection;
         if (!structuredSelection.isEmpty())
         {
            data = (ITypedElement) structuredSelection.getFirstElement();
         }
      }
      if (null != dataPathBrowser)
      {
         dataPathBrowser.setAccessPoint(data, direction);
      }
   }
}
