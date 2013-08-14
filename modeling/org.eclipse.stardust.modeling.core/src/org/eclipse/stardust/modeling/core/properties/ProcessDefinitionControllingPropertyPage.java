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

import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.modeling.core.Diagram_Messages;

public class ProcessDefinitionControllingPropertyPage extends AbstractControllingPropertyPage
{
   private ControllingAttribute[] controllingAttributes =
   {
      new ControllingAttribute(Diagram_Messages.LB_CostDriver, "costDriver", //$NON-NLS-1$
            null, ""), //$NON-NLS-1$
      new ControllingAttribute(Diagram_Messages.LB_TargetCostDriverQuantity, "costDriverQuantity", //$NON-NLS-1$
            "long", ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetProcessingTime, "targetProcessingTime", //$NON-NLS-1$
            PERIOD, ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetExecutionTime, "targetExecutionTime", //$NON-NLS-1$
            PERIOD, ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetIdleTime, "targetIdleTime", //$NON-NLS-1$
            PERIOD, ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetCostPerExecution, "targetCostPerExecution", //$NON-NLS-1$
            Type.Money.getId(), Diagram_Messages.MEASURE_UNIT_EUR),
      new ControllingAttribute(Diagram_Messages.LB_TargetCostPerSecond, "targetCostPerSecond", //$NON-NLS-1$
            Type.Money.getId(), Diagram_Messages.MEASURE_UNIT_EUR),
      new ControllingAttribute(Diagram_Messages.LB_OverdueThreshold, OVERDUE_THRESHOLD, 
            OVERDUE_THRESHOLD,"")//$NON-NLS-1$
   };

   public ControllingAttribute[] getControllingAttributes()
   {
      return controllingAttributes;
   }
}
