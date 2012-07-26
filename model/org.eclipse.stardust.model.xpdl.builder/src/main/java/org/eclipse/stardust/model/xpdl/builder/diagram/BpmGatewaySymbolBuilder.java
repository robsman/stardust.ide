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
