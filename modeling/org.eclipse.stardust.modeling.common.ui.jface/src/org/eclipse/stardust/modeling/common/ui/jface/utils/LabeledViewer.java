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

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;


public class LabeledViewer
{
   private final LabelWithStatus label;
   private final StructuredViewer viewer;
   
   public LabeledViewer(StructuredViewer viewer, LabelWithStatus label)
   {
      this.viewer = viewer;
      this.label = label;
   }
   
   public StructuredViewer getViewer()
   {
      return viewer;
   }

   public LabelWithStatus getLabel()
   {
      return label;
   }
}
