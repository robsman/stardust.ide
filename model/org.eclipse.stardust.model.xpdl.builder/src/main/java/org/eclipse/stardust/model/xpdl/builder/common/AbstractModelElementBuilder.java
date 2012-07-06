package org.eclipse.stardust.model.xpdl.builder.common;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.xpdl.builder.utils.ElementBuilderUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;



public abstract class AbstractModelElementBuilder<T extends IIdentifiableElement & IModelElement, B extends AbstractModelElementBuilder<T, B>>
      extends AbstractIdentifiableElementBuilder<T, B>
{
   protected ModelType model;

   public AbstractModelElementBuilder(T element)
   {
      super(element);
   }

   @Override
   public T build()
   {
      T element = super.build();

      // attaching element to model
      EList<? super T> elementContainer = getElementContainer();
      if ((null != elementContainer) && !elementContainer.contains(element))
      {
         elementContainer.add(element);
      }

      return element;
   }

   @Override
   protected T finalizeElement()
   {
      T element = super.finalizeElement();

      if (null == model)
      {
         throw new NullPointerException("Model must be set.");
      }

      return element;
   }

   public B inModel(ModelType model)
   {
      setModel(model);

      return self();
   }

   public B forModel(ModelType model)
   {
      return inModel(model);
   }

   public ModelType model()
   {
      return model;
   }

   public B withDescription(String description)
   {
      if ( !isEmpty(description))
      {
         DescriptionType descriptor = F_CWM.createDescriptionType();
         XpdlModelUtils.setCDataString(descriptor.getMixed(), description, true);

         if (element instanceof IIdentifiableModelElement)
         {
            ((IIdentifiableModelElement) element).setDescription(descriptor);
         }
         else
         {
            throw new IllegalArgumentException("Unsupported proeprty: description");
         }
      }

      return self();
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

   protected String deriveDefaultElementId()
   {
      if (null != getElementContainer())
      {
         return ElementBuilderUtils.deriveDefaultId(element, getElementContainer(),
               getDefaultElementIdPrefix());
      }
      else
      {
         return null;
      }
   }

   protected EList<? super T> getElementContainer()
   {
      return null;
   }

   protected abstract String getDefaultElementIdPrefix();

}
