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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.stardust.modeling.core.Diagram_Messages;

import ag.carnot.workflow.spi.providers.data.java.Type;

public class RoleControllingPropertyPage extends AbstractControllingPropertyPage
{
   private ControllingAttribute[] attributes =
   {
      new ControllingAttribute(Diagram_Messages.LB_WorkingWeeksPerYear, "workingWeeksPerYear", //$NON-NLS-1$
            "int", ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_ActualCostPerMinute, "actualCostPerMinute", //$NON-NLS-1$
            Type.Money.getId(), Diagram_Messages.MEASURE_UNIT_EUR),
      new ControllingAttribute(Diagram_Messages.LB_TargetWorktimePerDay, "targetWorkTimePerDay", //$NON-NLS-1$
            "float", ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetWorktimePerWeek, "targetWorkTimePerWeek", //$NON-NLS-1$
            "float", ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetQueueDepth, "targetQueueDepth", //$NON-NLS-1$
            "int", "") //$NON-NLS-1$ //$NON-NLS-2$
   };

   public ControllingAttribute[] getControllingAttributes()
   {
      return attributes;
   }
}
