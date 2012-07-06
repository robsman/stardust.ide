package org.eclipse.stardust.model.xpdl.builder.participant;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;



public class BpmConditionalPerformerBuilder
      extends
      AbstractModelElementBuilder<ConditionalPerformerType, BpmConditionalPerformerBuilder>
{
   public BpmConditionalPerformerBuilder(ModelType model)
   {
      super(F_CWM.createConditionalPerformerType());

      forModel(model);
   }

   @Override
   protected ConditionalPerformerType finalizeElement()
   {
      model.getConditionalPerformer().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "ConditionalPerformer";
   }

   public static BpmConditionalPerformerBuilder newConditionalPerformer(ModelType model)
   {
      return new BpmConditionalPerformerBuilder(model);
   }

   public BpmConditionalPerformerBuilder basedOnVariable(String dataId)
   {
      return basedOnVariable(XpdlModelUtils.findElementById(model.getData(), dataId));
   }

   public BpmConditionalPerformerBuilder basedOnVariable(String dataId, String derefExpression)
   {
      return basedOnVariable(XpdlModelUtils.findElementById(model.getData(), dataId), derefExpression);
   }

   public BpmConditionalPerformerBuilder basedOnVariable(DataType data)
   {
      return basedOnVariable(data, null);
   }

   public BpmConditionalPerformerBuilder basedOnVariable(DataType data, String derefExpression)
   {
      element.setData(data);

      if ( !isEmpty(derefExpression))
      {
         element.setDataPath(derefExpression);
      }
      else
      {
         element.eUnset(PKG_CWM.getConditionalPerformerType_DataPath());
      }

      return this;
   }

   public BpmConditionalPerformerBuilder resolvingToUser()
   {
      element.setIsUser(true);
      AttributeUtil.setAttribute(element, PredefinedConstants.CONDITIONAL_PERFORMER_KIND,
            PredefinedConstants.CONDITIONAL_PERFORMER_KIND_USER);

      return this;
   }

   public BpmConditionalPerformerBuilder resolvingToModelParticipant()
   {
      element.setIsUser(false);
      AttributeUtil.setAttribute(element, PredefinedConstants.CONDITIONAL_PERFORMER_KIND,
            PredefinedConstants.CONDITIONAL_PERFORMER_KIND_MODEL_PARTICIPANT);

      return this;
   }

   public BpmConditionalPerformerBuilder resolvingToModelParticipantOrUserGroup()
   {
      element.setIsUser(false);
      AttributeUtil.setAttribute(
            element,
            PredefinedConstants.CONDITIONAL_PERFORMER_KIND,
            PredefinedConstants.CONDITIONAL_PERFORMER_KIND_MODEL_PARTICIPANT_OR_USER_GROUP);

      return this;
   }

   public BpmConditionalPerformerBuilder resolvingToUserGroup()
   {
      element.setIsUser(false);
      AttributeUtil.setAttribute(element, PredefinedConstants.CONDITIONAL_PERFORMER_KIND,
            PredefinedConstants.CONDITIONAL_PERFORMER_KIND_USER_GROUP);

      return this;
   }

}
