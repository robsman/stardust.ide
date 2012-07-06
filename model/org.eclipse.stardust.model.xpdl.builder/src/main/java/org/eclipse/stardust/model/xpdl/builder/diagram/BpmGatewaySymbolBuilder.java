package org.eclipse.stardust.model.xpdl.builder.diagram;

import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;

public class BpmGatewaySymbolBuilder
      extends AbstractNodeSymbolBuilder<GatewaySymbol, ISymbolContainer, BpmGatewaySymbolBuilder>
{

   public static BpmGatewaySymbolBuilder newGatewaySymbol(ISymbolContainer container)
   {
      return new BpmGatewaySymbolBuilder()
            .inContainer(container);
   }

   public static BpmGatewaySymbolBuilder newJoinGatewaySymbol(ISymbolContainer container)
   {
      return newGatewaySymbol(container).ofFlowKind(FlowControlType.JOIN_LITERAL);
   }

   public static BpmGatewaySymbolBuilder newSplitGatewaySymbol(ISymbolContainer container)
   {
      return newGatewaySymbol(container).ofFlowKind(FlowControlType.SPLIT_LITERAL);
   }

   public BpmGatewaySymbolBuilder()
   {
      super(F_CWM.createGatewaySymbol(), PKG_CWM.getISymbolContainer_GatewaySymbol());
   }

   public BpmGatewaySymbolBuilder forActivity(ActivityType activity)
   {
      return forElement(activity);
   }

   public BpmGatewaySymbolBuilder forActivitySymbol(ActivitySymbolType activitySymbol)
   {
      element.setActivitySymbol(activitySymbol);

      return forElement(activitySymbol.getActivity());
   }

   public BpmGatewaySymbolBuilder ofFlowKind(FlowControlType flowKind)
   {
      element.setFlowKind(flowKind);

      return self();
   }

   @Override
   protected GatewaySymbol finalizeElement()
   {
      GatewaySymbol element = super.finalizeElement();

      return element;
   }

}
