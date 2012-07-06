package org.eclipse.stardust.model.xpdl.builder.diagram;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;

public abstract class AbstractNodeSymbolBuilder<T extends INodeSymbol, C extends ISymbolContainer, B extends AbstractNodeSymbolBuilder<T, C, B>>
      extends AbstractGraphicalObjectBuilder<T, C, B>
{
   private final EStructuralFeature containingFeature;

   public AbstractNodeSymbolBuilder(T element, EStructuralFeature containingFeature)
   {
      super(element);

      this.containingFeature = containingFeature;
   }

   public AbstractNodeSymbolBuilder(C container, T element, EStructuralFeature containingFeature)
   {
      super(container, element);

      this.containingFeature = containingFeature;
   }

   @Override
   protected T finalizeElement()
   {
      T element = super.finalizeElement();

      if (null != containingFeature)
      {
         container.getNodes().add(containingFeature, element);
      }
      return element;
   }

   public B atPosition(int x, int y)
   {
      element.setXPos(x);
      element.setYPos(y);

      return self();
   }

   public B withSize(int width, int height)
   {
      element.setWidth(width);
      element.setHeight(height);

      return self();
   }

}
