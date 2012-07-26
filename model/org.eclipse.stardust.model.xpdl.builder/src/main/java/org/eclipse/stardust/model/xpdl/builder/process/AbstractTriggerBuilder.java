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
package org.eclipse.stardust.model.xpdl.builder.process;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractProcessElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;


public abstract class AbstractTriggerBuilder<B extends AbstractTriggerBuilder<B>>
      extends AbstractProcessElementBuilder<TriggerType, B>
{
   public AbstractTriggerBuilder()
   {
      super(null, F_CWM.createTriggerType());
   }

   public AbstractTriggerBuilder(ProcessDefinitionType process)
   {
      super(process, F_CWM.createTriggerType());
   }

   @Override
   protected EList<? super TriggerType> getElementContainer()
   {
      return process.getTrigger();
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public static BpmManualTriggerBuilder newManualTrigger()
   {
      return BpmManualTriggerBuilder.newManualTrigger();
   }

   public static BpmManualTriggerBuilder newManualTrigger(ProcessDefinitionType process)
   {
      return BpmManualTriggerBuilder.newManualTrigger(process);
   }

}
