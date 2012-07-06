package org.eclipse.stardust.model.xpdl.builder.common;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;


public abstract class AbstractIdentifiableElementBuilder<T extends IIdentifiableElement, B extends AbstractIdentifiableElementBuilder<T, B>> extends AbstractElementBuilder<T, B>
{
   public AbstractIdentifiableElementBuilder(T element)
   {
      super(element);
   }

   public T build()
   {
      // TODO final verifications
      if (isEmpty(element.getId()))
      {

      }

      return finalizeElement();
   }

   protected abstract String deriveDefaultElementId();

   @Override
   protected T finalizeElement()
   {
      T element = super.finalizeElement();

      if ( !element.isSetId())
      {
         if ( !isEmpty(element.getName()))
         {
            element.setId(XpdlModelUtils.computeId(element.getName()));
         }
         else
         {
            String defaultId = deriveDefaultElementId();
            if (null != defaultId)
            {
               element.setId(defaultId);
            }
         }
      }

      if ( !element.isSetName())
      {
         element.setName(element.getId());
      }

      return element;
   }

   public B withId(String id)
   {
      element.setId(id);

      return self();
   }

   public B withName(String name)
   {
      element.setName(name);

      return self();
   }

   public B withIdAndName(String id, String name)
   {
      withId(id);
      withName(name);

      return self();
   }

}
