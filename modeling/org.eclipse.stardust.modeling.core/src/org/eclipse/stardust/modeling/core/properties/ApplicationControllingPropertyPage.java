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

public class ApplicationControllingPropertyPage extends AbstractControllingPropertyPage
{
   ControllingAttribute attributes[] =
   {
      new ControllingAttribute(Diagram_Messages.ControllingAttribute_LB_CostCenter, "costCenter", //$NON-NLS-1$
              null, ""), //$NON-NLS-1$
      new ControllingAttribute(Diagram_Messages.ControllingAttribute_LB_ActualCostPerSecond, "actualCostPerSecond", //$NON-NLS-1$
            Type.Money.getId(), Diagram_Messages.MEASURE_UNIT_EUR)
   };

   public ControllingAttribute[] getControllingAttributes()
   {
      return attributes;
   }
}
