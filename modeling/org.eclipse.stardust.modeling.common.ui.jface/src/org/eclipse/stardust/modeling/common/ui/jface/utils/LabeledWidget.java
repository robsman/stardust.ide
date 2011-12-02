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
package org.eclipse.stardust.modeling.common.ui.jface.utils;

import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.swt.widgets.Widget;


public class LabeledWidget
{
   private final LabelWithStatus label;
   private final Widget widget;
   
   public LabeledWidget(Widget widget, LabelWithStatus label)
   {
      this.widget = widget;
      this.label = label;
   }
   
   public Widget getWidget()
   {
      return widget;
   }

   public LabelWithStatus getLabel()
   {
      return label;
   }
}
