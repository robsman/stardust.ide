package org.eclipse.stardust.model.xpdl.builder.diagram;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.builder.common.PropertySetter;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;


public abstract class AbstractConnectionSymbolBuilder<T extends IConnectionSymbol, C extends ISymbolContainer, B extends AbstractConnectionSymbolBuilder<T, C, B>>
      extends AbstractGraphicalObjectBuilder<T, C, B>
{
   private final EStructuralFeature containingFeature;

   public AbstractConnectionSymbolBuilder(T element, EStructuralFeature containingFeature)
   {
      super(element);

      this.containingFeature = containingFeature;
   }

   public AbstractConnectionSymbolBuilder(C container, T element, EStructuralFeature containingFeature)
   {
      super(container, element);

      this.containingFeature = containingFeature;
   }

   @Override
   protected T finalizeElement()
   {
      T element = super.finalizeElement();

      container.getConnections().add(containingFeature, element);

      return element;
   }

   protected B between(INodeSymbol start, INodeSymbol end, EStructuralFeature startFeature,
         EStructuralFeature endFeature)
   {
      setters.add(PropertySetter.directValue(startFeature, start));
      setters.add(PropertySetter.directValue(endFeature, end));

      return self();
   }

   protected B from(INodeSymbol start, EStructuralFeature startFeature)
   {
      setters.add(PropertySetter.directValue(startFeature, start));

      return self();
   }

   protected B to(INodeSymbol end, EStructuralFeature endFeature)
   {
      setters.add(PropertySetter.directValue(endFeature, end));

      return self();
   }

}
