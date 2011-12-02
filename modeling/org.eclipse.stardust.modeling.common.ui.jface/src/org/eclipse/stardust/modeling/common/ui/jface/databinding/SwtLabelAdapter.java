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

import org.eclipse.swt.widgets.Label;

/**
 * @author fherinean
 * @version $Revision$
 */
public class SwtLabelAdapter extends SwtWidgetAdapter
{
   public SwtLabelAdapter(Label control)
   {
      super(control);
   }

   public void updateControl(Object value)
   {
      Label label = getBoundLabel();
      if ( !label.getText().equals(value))
      {
         label.setText(value == null ? "" : value.toString()); //$NON-NLS-1$
      }
   }

   protected Label getBoundLabel()
   {
      return (Label) getWidget();
   }
}
