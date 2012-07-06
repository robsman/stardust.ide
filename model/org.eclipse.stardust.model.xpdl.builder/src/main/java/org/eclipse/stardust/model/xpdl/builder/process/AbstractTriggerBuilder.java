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
