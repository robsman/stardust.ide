package org.eclipse.stardust.model.xpdl.builder.diagram;

import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.directValue;

import org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;

public class BpmTransitionConnectionBuilder
      extends AbstractConnectionSymbolBuilder<TransitionConnectionType, ISymbolContainer, BpmTransitionConnectionBuilder>
{
   private TransitionType modelElement;

   protected BpmTransitionConnectionBuilder()
   {
      super(F_CWM.createTransitionConnectionType(),
            PKG_CWM.getISymbolContainer_TransitionConnection());
   }

   public BpmTransitionConnectionBuilder forTransition(TransitionType transition)
   {
      this.modelElement = transition;

      return forElement(transition);
   }

   public BpmTransitionConnectionBuilder between(IFlowObjectSymbol start, IFlowObjectSymbol end)
   {
      return between(start, end,
            PKG_CWM.getTransitionConnectionType_SourceActivitySymbol(),
            PKG_CWM.getTransitionConnectionType_TargetActivitySymbol());
   }

   public BpmTransitionConnectionBuilder from(IFlowObjectSymbol start)
   {
      return from(start, PKG_CWM.getTransitionConnectionType_SourceActivitySymbol());
   }

   public BpmTransitionConnectionBuilder fromTopOf(IFlowObjectSymbol start)
   {
      setters.add(directValue(PKG_CWM.getIConnectionSymbol_SourceAnchor(),
            "top"));

      return from(start);
   }

   public BpmTransitionConnectionBuilder fromLeftOf(IFlowObjectSymbol start)
   {
      setters.add(directValue(PKG_CWM.getIConnectionSymbol_SourceAnchor(),
            "left"));

      return from(start);
   }

   public BpmTransitionConnectionBuilder fromRightOf(IFlowObjectSymbol start)
   {
      setters.add(directValue(PKG_CWM.getIConnectionSymbol_SourceAnchor(),
            "right"));

      return from(start);
   }

   public BpmTransitionConnectionBuilder fromBottomOf(IFlowObjectSymbol start)
   {
      setters.add(directValue(PKG_CWM.getIConnectionSymbol_SourceAnchor(),
            "bottom"));

      return from(start);
   }

   public BpmTransitionConnectionBuilder to(IFlowObjectSymbol end)
   {
      return from(end, PKG_CWM.getTransitionConnectionType_TargetActivitySymbol());
   }

   public BpmTransitionConnectionBuilder toTopOf(IFlowObjectSymbol end)
   {
      setters.add(directValue(PKG_CWM.getIConnectionSymbol_TargetAnchor(),
            "top"));

      return to(end);
   }

   public BpmTransitionConnectionBuilder toLeftOf(IFlowObjectSymbol end)
   {
      setters.add(directValue(PKG_CWM.getIConnectionSymbol_TargetAnchor(),
            "left"));

      return to(end);
   }

   public BpmTransitionConnectionBuilder toRightOf(IFlowObjectSymbol end)
   {
      setters.add(directValue(PKG_CWM.getIConnectionSymbol_TargetAnchor(),
            "right"));

      return to(end);
   }

   public BpmTransitionConnectionBuilder toBottomOf(IFlowObjectSymbol end)
   {
      setters.add(directValue(PKG_CWM.getIConnectionSymbol_TargetAnchor(),
            "bottom"));

      return to(end);
   }

   @Override
   protected TransitionConnectionType finalizeElement()
   {
      TransitionConnectionType element = super.finalizeElement();

      if (null == modelElement)
      {
         throw new NullPointerException("Model element must be set.");
      }

      element.setTransition(modelElement);

      return element;
   }

}
