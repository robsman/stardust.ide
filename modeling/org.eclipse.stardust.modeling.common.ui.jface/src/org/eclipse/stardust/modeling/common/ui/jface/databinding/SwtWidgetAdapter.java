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

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Widget;

/**
 * @author fherinean
 * @version $Revision$
 */
public abstract class SwtWidgetAdapter extends AbstractWidgetAdapter
{
   private final Widget widget;

   private DisposeListener disposer;

   public SwtWidgetAdapter(Widget widget)
   {
      this.widget = widget;
   }

   public abstract void updateControl(Object value);

   public Widget getWidget()
   {
      return widget;
   }

   public void bind(IBindingMediator manager)
   {
      super.bind(manager);

      this.disposer = new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            unbind();
         }
      };
      widget.addDisposeListener(disposer);
   }

   public void unbind()
   {
      if (null != disposer)
      {
         if ( !widget.isDisposed())
         {
            widget.removeDisposeListener(disposer);
         }
         this.disposer = null;
      }

      super.unbind();
   }

   public void updateVisuals(Object value)
   {
      if ( !widget.isDisposed())
      {
         updateControl(value);
      }
   }
}
