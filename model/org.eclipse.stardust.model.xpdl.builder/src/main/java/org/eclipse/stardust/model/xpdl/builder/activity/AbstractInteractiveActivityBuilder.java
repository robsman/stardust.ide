package org.eclipse.stardust.model.xpdl.builder.activity;

import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.directValue;
import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.participantById;

import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;

public abstract class AbstractInteractiveActivityBuilder<B extends AbstractInteractiveActivityBuilder<B>>
      extends AbstractActivityBuilder<B>
{
   public B havingDefaultPerformer(String participantId)
   {
      setters.add(participantById(PKG_CWM.getActivityType_Performer(), participantId));

      return self();
   }

   public B havingDefaultPerformer(IModelParticipant participant)
   {
      setters.add(directValue(PKG_CWM.getActivityType_Performer(), participant));

      return self();
   }

}
