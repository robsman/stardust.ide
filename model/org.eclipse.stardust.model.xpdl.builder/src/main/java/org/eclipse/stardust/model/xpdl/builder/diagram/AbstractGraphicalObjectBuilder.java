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

import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;



public abstract class AbstractGraphicalObjectBuilder<T extends IGraphicalObject, C extends ISymbolContainer, B extends AbstractGraphicalObjectBuilder<T, C, B>>
      extends AbstractElementBuilder<T, B>
{
   protected ModelType model;

   protected C container;

   public AbstractGraphicalObjectBuilder(T element)
   {
      super(element);
   }

   public AbstractGraphicalObjectBuilder(C container, T element)
   {
      this(element);

      inContainer(container);
   }

   @Override
   protected T finalizeElement()
   {
      T element = super.finalizeElement();

      if (null == container)
      {
         throw new NullPointerException("Container must be set.");
      }

      if (null == model)
      {
         throw new NullPointerException("Model must be set.");
      }

      return element;
   }
/*
   public B inDiagram(DiagramType diagram)
   {
      setContainer(diagram);

      return self();
   }

   public B inPool(PoolSymbol pool)
   {
      setContainer(pool);

      return self();
   }

   public B inLane(LaneSymbol lane)
   {
      setContainer(lane);

      return self();
   }
*/
   public B inContainer(C container)
   {
      setContainer(container);

      return self();
   }

   public C container()
   {
      return container;
   }

   protected void setContainer(C container)
   {
      if (null == this.container)
      {
         if (null != container)
         {
            this.container = container;

            ModelType containingModel = XpdlModelUtils.findContainingModel(container);
            if (null != containingModel)
            {
               setModel(containingModel);
            }
         }
      }
      else
      {
         if (this.container != container)
         {
            throw new IllegalArgumentException("Container must only be set once.");
         }
      }
   }

   public B inModel(ModelType model)
   {
      setModel(model);

      return self();
   }

   protected B forElement(IModelElement modelElement)
   {
      setModelElement(modelElement);

      return self();
   }

   protected void setModelElement(IModelElement modelElement)
   {
      ModelType containingModel = XpdlModelUtils.findContainingModel(modelElement);
      if (null != containingModel)
      {
         setModel(containingModel);
      }
   }

   public B forModel(ModelType model)
   {
      return inModel(model);
   }

   public ModelType model()
   {
      return model;
   }

   protected void setModel(ModelType model)
   {
      if (null == this.model)
      {
         if (null != model)
         {
            this.model = model;
         }
      }
      else
      {
         if (this.model != model)
         {
            throw new IllegalArgumentException("Model must only be set once.");
         }
      }
   }
}
