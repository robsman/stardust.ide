package org.eclipse.stardust.model.xpdl.builder.process;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;


public class BpmManualTriggerBuilder
      extends AbstractTriggerBuilder<BpmManualTriggerBuilder>
{
   @Override
   protected void setProcess(ProcessDefinitionType process)
   {
      super.setProcess(process);

      if ((null == element.getType()) && (null != model))
      {
         element.setType(XpdlModelUtils.findElementById(model.getTriggerType(),
               PredefinedConstants.MANUAL_TRIGGER));
      }
   }

   @Override
   protected TriggerType finalizeElement()
   {
      super.finalizeElement();

      // TODO

      return super.finalizeElement();
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "ManualTrigger";
   }

   public static BpmManualTriggerBuilder newManualTrigger()
   {
      return new BpmManualTriggerBuilder();
   }

   public static BpmManualTriggerBuilder newManualTrigger(ProcessDefinitionType process)
   {
      return newManualTrigger().forProcess(process);
   }

   public BpmManualTriggerBuilder accessibleTo(String participantId)
   {
      if ( !isEmpty(participantId))
      {
         AttributeUtil.setAttribute(element,
               PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT, participantId);
      }
      else
      {
         AttributeType participantAttr = AttributeUtil.getAttribute(element,
               PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT);
         if (null != participantAttr)
         {
            element.getAttribute().remove(participantAttr);
         }
      }

      return this;
   }

   public BpmManualTriggerBuilder accessibleTo(IModelParticipant participant)
   {
      accessibleTo((null != participant) ? participant.getId() : null);

      return this;
   }

}
