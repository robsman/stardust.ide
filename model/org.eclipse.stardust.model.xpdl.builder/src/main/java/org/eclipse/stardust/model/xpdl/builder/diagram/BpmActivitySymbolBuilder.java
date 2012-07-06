package org.eclipse.stardust.model.xpdl.builder.diagram;

import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;

public class BpmActivitySymbolBuilder
      extends AbstractNodeSymbolBuilder<ActivitySymbolType, ISymbolContainer, BpmActivitySymbolBuilder>
{
   private ActivityType modelElement;

   public BpmActivitySymbolBuilder()
   {
      super(F_CWM.createActivitySymbolType(),
            PKG_CWM.getISymbolContainer_ActivitySymbol());
   }

   public BpmActivitySymbolBuilder forActivity(ActivityType activity)
   {
      this.modelElement = activity;

      return forElement(activity);
   }

   @Override
   protected ActivitySymbolType finalizeElement()
   {
      ActivitySymbolType element = super.finalizeElement();

      if (null == modelElement)
      {
         throw new NullPointerException("Model element must be set.");
      }

      element.setActivity(modelElement);

      return element;
   }

}
