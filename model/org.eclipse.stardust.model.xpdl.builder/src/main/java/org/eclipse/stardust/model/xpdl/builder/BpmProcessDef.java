package org.eclipse.stardust.model.xpdl.builder;

import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmManualActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractProcessElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.process.BpmManualTriggerBuilder;
import org.eclipse.stardust.model.xpdl.builder.process.BpmProcessDefinitionBuilder;
import org.eclipse.stardust.model.xpdl.builder.transition.BpmActivitySequenceBuilder;
import org.eclipse.stardust.model.xpdl.builder.transition.BpmConditionalTransitionBuilder;
import org.eclipse.stardust.model.xpdl.builder.transition.BpmOtherwiseTransitionBuilder;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;


public abstract class BpmProcessDef
{
   private List<AbstractProcessElementBuilder<? , ? >> elementBuilders = CollectionUtils.newArrayList();

   public void build(BpmProcessDefinitionBuilder builder, ProcessDefinitionType process)
   {
      // finalize builders
      for (AbstractProcessElementBuilder<? , ? > elementBuilder : elementBuilders)
      {
         elementBuilder.forModel(builder.model()) //
               .forProcess(process)
               .build();
      }
   }

   protected BpmManualTriggerBuilder manualTrigger()
   {
      return wrap(BpmModelBuilder.newManualTrigger());
   }

   protected BpmManualActivityBuilder manualActivity()
   {
      return wrap(BpmModelBuilder.newManualActivity());
   }

   protected BpmConditionalTransitionBuilder transition()
   {
      return wrap(BpmModelBuilder.newTransition());
   }

   protected BpmOtherwiseTransitionBuilder transitionOtherwise()
   {
      return wrap(BpmModelBuilder.newOtherwiseTransition());
   }

   protected BpmActivitySequenceBuilder activitySequence()
   {
      return wrap(BpmModelBuilder.newActivitySequence());
   }

   private <T extends IIdentifiableModelElement, B extends AbstractProcessElementBuilder<T, B>> B wrap(
         B builder)
   {
      elementBuilders.add(builder);

      return builder;
   }

}
