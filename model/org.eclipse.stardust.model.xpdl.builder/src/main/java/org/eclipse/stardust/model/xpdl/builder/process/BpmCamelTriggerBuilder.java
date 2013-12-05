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

import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class BpmCamelTriggerBuilder
      extends AbstractTriggerBuilder<BpmCamelTriggerBuilder>
{
   private BpmCamelTriggerBuilder(ProcessDefinitionType process)
   {
      super(process);
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "CamelTrigger";
   }

   public static BpmCamelTriggerBuilder newCamelTrigger(ProcessDefinitionType process)
   {
      return new BpmCamelTriggerBuilder(process);
   }

   protected TriggerType finalizeElement()
   {
      TriggerTypeType triggerMetaType = ModelUtils.findIdentifiableElement(
            model.getTriggerType(), ModelerConstants.CAMEL_TRIGGER_TYPE_ID);
      if (null == triggerMetaType)
      {
         triggerMetaType = F_CWM.createTriggerTypeType();
         
         triggerMetaType.setId(ModelerConstants.CAMEL_TRIGGER_TYPE_ID);
         triggerMetaType.setName("Camel Trigger");
         triggerMetaType.setIsPredefined(true);
         triggerMetaType.setPullTrigger(false);
         
         AttributeUtil.setAttribute(triggerMetaType, "carnot:engine:validator", "org.eclipse.stardust.engine.extensions.camel.trigger.validation.CamelTriggerValidator");
         AttributeUtil.setAttribute(triggerMetaType, "carnot:engine:runtimeValidator", "org.eclipse.stardust.engine.extensions.camel.trigger.validation.CamelTriggerValidator");

         model.getTriggerType().add(triggerMetaType);
      }
      
      super.finalizeElement();
      element.setType(triggerMetaType);
      return element;
   }
}