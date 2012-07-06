package org.eclipse.stardust.model.xpdl.builder.diagram;

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;

public class BpmPoolBuilder
      extends AbstractNodeSymbolBuilder<PoolSymbol, DiagramType, BpmPoolBuilder>
{

   public BpmPoolBuilder()
   {
      super(F_CWM.createPoolSymbol(), null);
   }

   public BpmPoolBuilder forActivity(ActivityType activity)
   {
      return forElement(activity);
   }

   public BpmPoolBuilder coveringTheWholeDiagram()
   {
      return atPosition(0, 0).withSize(-1, -1);
   }

   @Override
   protected PoolSymbol finalizeElement()
   {
      PoolSymbol element = super.finalizeElement();

      container.getPoolSymbols().add(element);

      return element;
   }

}
