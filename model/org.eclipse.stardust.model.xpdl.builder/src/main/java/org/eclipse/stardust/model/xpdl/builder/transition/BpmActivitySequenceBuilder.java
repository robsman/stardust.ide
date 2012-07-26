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

import org.eclipse.stardust.model.xpdl.builder.BpmActivitySequenceDef;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractProcessElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;


public class BpmActivitySequenceBuilder
      extends AbstractProcessElementBuilder<ActivityType, BpmActivitySequenceBuilder>
{
   private BpmActivitySequenceDef definition;

   public BpmActivitySequenceBuilder()
   {
      super(F_CWM.createActivityType());
   }

   @Override
   protected ActivityType finalizeElement()
   {
      super.finalizeElement();
      
      if (null != definition)
      {
         definition.build(this);
      }

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return null;
   }

   public BpmActivitySequenceBuilder definedAs(BpmActivitySequenceDef definition)
   {
      this.definition = definition;
      
      return self();
   }
}
