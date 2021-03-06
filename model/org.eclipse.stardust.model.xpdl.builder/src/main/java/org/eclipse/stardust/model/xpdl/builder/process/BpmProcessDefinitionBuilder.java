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
import org.eclipse.stardust.model.xpdl.builder.BpmProcessDef;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;


public class BpmProcessDefinitionBuilder
      extends
      AbstractModelElementBuilder<ProcessDefinitionType, BpmProcessDefinitionBuilder>
{
   private BpmProcessDef definition;

   public BpmProcessDefinitionBuilder()
   {
      super(F_CWM.createProcessDefinitionType());
   }

   public BpmProcessDefinitionBuilder(ModelType model)
   {
      super(F_CWM.createProcessDefinitionType());
      
      forModel(model);
   }

   @Override
   protected ProcessDefinitionType finalizeElement()
   {
      super.finalizeElement();
      
      if (null != definition)
      {
         definition.build(self(), element);
      }

      return element;
   }

   @Override
   protected EList<? super ProcessDefinitionType> getElementContainer()
   {
      return model.getProcessDefinition();
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "ProcessDefinition";
   }

   public static BpmProcessDefinitionBuilder newProcessDefinition()
   {
      return new BpmProcessDefinitionBuilder();
   }

   public static BpmProcessDefinitionBuilder newProcessDefinition(ModelType model)
   {
      return newProcessDefinition().inModel(model);
   }

   public BpmProcessDefinitionBuilder triggeredManuallyBy(String participantId)
   {
      // TODO manual trigger

      return this;
   }
   
   public BpmProcessDefinitionBuilder definedAs(BpmProcessDef definition)
   {
      // TODO finalize and add elements
      this.definition = definition;

      return this;
   }
}
