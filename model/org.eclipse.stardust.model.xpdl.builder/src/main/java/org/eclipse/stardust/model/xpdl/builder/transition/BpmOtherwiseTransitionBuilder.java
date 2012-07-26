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
package org.eclipse.stardust.model.xpdl.builder.transition;

import org.eclipse.stardust.model.xpdl.carnot.TransitionType;


public class BpmOtherwiseTransitionBuilder
      extends AbstractTransitionBuilder<BpmOtherwiseTransitionBuilder>
{
   public BpmOtherwiseTransitionBuilder()
   {
      element.setCondition("OTHERWISE");
      element.eUnset(PKG_CWM.getTransitionType_Expression());
   }

   @Override
   protected TransitionType finalizeElement()
   {
      return super.finalizeElement();
   }

}
