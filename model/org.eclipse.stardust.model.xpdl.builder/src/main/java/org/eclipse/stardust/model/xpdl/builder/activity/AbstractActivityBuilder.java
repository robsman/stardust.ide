package org.eclipse.stardust.model.xpdl.builder.activity;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.xpdl.builder.BpmActivityDef;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractProcessElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;


public abstract class AbstractActivityBuilder<B extends AbstractActivityBuilder<B>>
      extends AbstractProcessElementBuilder<ActivityType, B>
{
   private BpmActivityDef definition;

   public AbstractActivityBuilder()
   {
      super(F_CWM.createActivityType());
   }

   public void setActivity(ActivityType activity)
   {
      this.element = activity;
   }
   
   @Override
   protected ActivityType finalizeElement()
   {
      super.finalizeElement();

      if (null == element.getJoin())
      {
         usingJoinControlFlow(JoinSplitType.NONE_LITERAL);
      }
      if (null == element.getSplit())
      {
         usingSplitControlFlow(JoinSplitType.NONE_LITERAL);
      }

      if (null != definition)
      {
         definition.build(this, element);
      }

      return element;
   }

   @Override
   protected EList<? super ActivityType> getElementContainer()
   {
      return process.getActivity();
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Activity";
   }

   public static BpmRouteActivityBuilder newRouteActivity()
   {
      return new BpmRouteActivityBuilder();
   }

   public static BpmRouteActivityBuilder newRouteActivity(ProcessDefinitionType process)
   {
      return newRouteActivity().inProcess(process);
   }

   public static BpmManualActivityBuilder newManualActivity()
   {
      return new BpmManualActivityBuilder();
   }

   public static BpmManualActivityBuilder newManualActivity(ProcessDefinitionType process)
   {
      return newManualActivity().inProcess(process);
   }

   public static BpmApplicationActivityBuilder newApplicationActivity()
   {
      return new BpmApplicationActivityBuilder();
   }

   public static BpmApplicationActivityBuilder newApplicationActivity(
         ProcessDefinitionType process)
   {
      return newApplicationActivity().inProcess(process);
   }

   public static BpmInteractiveApplicationActivityBuilder newInteractiveApplicationActivity()
   {
      return new BpmInteractiveApplicationActivityBuilder();
   }

   public static BpmInteractiveApplicationActivityBuilder newInteractiveApplicationActivity(
         ProcessDefinitionType process)
   {
      return newInteractiveApplicationActivity().inProcess(process);
   }

   public static BpmSubProcessActivityBuilder newSubProcessActivity()
   {
      return new BpmSubProcessActivityBuilder();
   }

   public static BpmSubProcessActivityBuilder newSubProcessActivity(
         ProcessDefinitionType process)
   {
      return newSubProcessActivity().inProcess(process);
   }

   @SuppressWarnings("unchecked")
   public B usingControlFlow(JoinSplitType joinMode, JoinSplitType splitMode)
   {
      usingJoinControlFlow(joinMode);
      usingSplitControlFlow(splitMode);

      return (B) this;
   }

   @SuppressWarnings("unchecked")
   public B usingJoinControlFlow(JoinSplitType joinMode)
   {
      element.setJoin(joinMode);

      return (B) this;
   }

   @SuppressWarnings("unchecked")
   public B usingSplitControlFlow(JoinSplitType splitMode)
   {
      element.setSplit(splitMode);

      return (B) this;
   }

   @SuppressWarnings("unchecked")
   public B definedAs(BpmActivityDef definition)
   {
      // TODO finalize and add elements
      this.definition = definition;

      return (B) this;
   }
}
