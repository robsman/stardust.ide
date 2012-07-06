package org.eclipse.stardust.model.xpdl.builder.common;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;

public abstract class AbstractElementBuilder<T extends EObject, B extends AbstractElementBuilder<T, B>>
{
   public static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   public static final CarnotWorkflowModelFactory F_CWM = CarnotWorkflowModelFactory.eINSTANCE;

   protected T element;

   protected List<PropertySetter> setters = CollectionUtils.newArrayList();

   public AbstractElementBuilder(T element)
   {
      this.element = element;
   }

   protected T finalizeElement()
   {
      for (PropertySetter setter : setters)
      {
         setter.apply(self());
      }

      return element;
   }

   @SuppressWarnings("unchecked")
   protected B self()
   {
      return (B) this;
   }

   public T build()
   {
      // TODO final verifications

      return finalizeElement();
   }
}
