package org.eclipse.stardust.model.xpdl.builder;

import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.builder.activity.AbstractActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmManualActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.transition.BpmActivitySequenceBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;


public abstract class BpmActivitySequenceDef
{
   private List<AbstractActivityBuilder<?>> elementBuilders = CollectionUtils.newArrayList();

   public void build(BpmActivitySequenceBuilder sequenceBuilder)
   {
      // finalize builders

      ActivityType predecessorActivity = null;
      for (AbstractActivityBuilder<?> builder : elementBuilders)
      {
         ActivityType activity = builder.forModel(sequenceBuilder.model())
               .forProcess(sequenceBuilder.process())
               .build();

         if (null != predecessorActivity)
         {
            BpmModelBuilder.newTransition()
                  .forModel(sequenceBuilder.model())
                  .inProcess(sequenceBuilder.process())
                  .from(predecessorActivity)
                  .to(activity)
                  .build();
         }

         predecessorActivity = activity;
      }
   }

   protected BpmManualActivityBuilder manualActivity()
   {
      return wrap(BpmModelBuilder.newManualActivity());
   }

   private <B extends AbstractActivityBuilder<B>> B wrap(B builder)
   {
      elementBuilders.add(builder);

      return builder;
   }
}
