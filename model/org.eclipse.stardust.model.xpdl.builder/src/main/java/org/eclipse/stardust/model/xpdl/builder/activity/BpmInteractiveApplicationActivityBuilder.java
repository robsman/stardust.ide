/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.activity;

import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;

public class BpmInteractiveApplicationActivityBuilder
      extends
      AbstractInteractiveActivityBuilder<BpmInteractiveApplicationActivityBuilder>
{

   public BpmInteractiveApplicationActivityBuilder()
   {
      element.setImplementation(ActivityImplementationType.ROUTE_LITERAL);
   }

   @Override
   protected ActivityType finalizeElement()
   {
      // TODO more specific handling?

      return super.finalizeElement();
   }

   public BpmInteractiveApplicationActivityBuilder usingApplication(
         ApplicationType application)
   {
      element.setApplication(application);

      return this;
   }

}
