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
package org.eclipse.stardust.modeling.core.editors.ui.validation;

import java.util.Iterator;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtWidgetAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.IValidationStatus;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SwtValidationLabelAdapter extends SwtWidgetAdapter
{
   public SwtValidationLabelAdapter(Label label)
   {
      super(label);
   }

   public SwtValidationLabelAdapter(LabelWithStatus label)
   {
      super(label);
   }

   public void updateControl(Object value)
   {
      if ((value instanceof IValidationStatus) && (null != getWidget())
            && !getWidget().isDisposed())
      {
         IValidationStatus status = (IValidationStatus) value;

         if (getWidget() instanceof LabelWithStatus)
         {
            ((LabelWithStatus) getWidget()).setValidationStatus(status);
         }
         else if (getWidget() instanceof Label)
         {
            ((Label) getWidget()).setForeground( !status.getErrors().isEmpty()
                  ? ColorConstants.red
                  : !status.getWarnings().isEmpty()
                        ? ColorConstants.yellow
                        : ColorConstants.black);
         }

         if (getWidget() instanceof Control)
         {
            String nl = ""; //$NON-NLS-1$
            StringBuffer toolTip = new StringBuffer();
            for (Iterator i = status.getErrors().iterator(); i.hasNext();)
            {
               toolTip.append(nl).append(Diagram_Messages.LB_ToolTip_Error).append(
                     ((Issue) i.next()).getMessage());
               nl = "\n"; //$NON-NLS-1$
            }
            for (Iterator i = status.getWarnings().iterator(); i.hasNext();)
            {
               toolTip.append(nl).append(Diagram_Messages.LB_ToolTip_Warning).append(
                     ((Issue) i.next()).getMessage());
               nl = "\n"; //$NON-NLS-1$
            }
            ((Control) getWidget()).setToolTipText(toolTip.toString());
         }
      }
   }
}
