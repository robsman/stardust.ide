/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.integration.camel.triggerTypes;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.modeling.core.spi.triggerTypes.ParameterMappingTablePage;
import org.eclipse.swt.widgets.Button;

public class CamelParameterMappingTablePage extends ParameterMappingTablePage
{
   private static final String BODY_ACCESS_POINT_ID = "message";

   @Override
   protected void performAdd(Button[] buttons)
   {

      for (AccessPointType accessPt : ((TriggerType) getModelElement()).getAccessPoint())
      {
         if (accessPt.getId().equals(BODY_ACCESS_POINT_ID))
         {
            super.performAdd(buttons);
            return;
         }
      }
      CamelParameterMappingPage.createBodyAccessPoint((TriggerType) getModelElement(), null);
      super.performAdd(buttons);
   }
}
