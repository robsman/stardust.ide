package org.eclipse.stardust.model.xpdl.builder.datamapping;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractActivityElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;


public abstract class AbstractDataMappingBuilder<B extends AbstractDataMappingBuilder<B>>
      extends AbstractActivityElementBuilder<DataMappingType, B>
{
   public AbstractDataMappingBuilder(DirectionType direction)
   {
      super(F_CWM.createDataMappingType());
      
      element.setDirection(direction);
   }

   @Override
   protected DataMappingType finalizeElement()
   {
      super.finalizeElement();
      
      activity.getDataMapping().add(element);

      return element;
   }

   @Override
   protected EList<? super DataMappingType> getElementContainer()
   {
      return activity.getDataMapping();
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "DataMapping";
   }

   @SuppressWarnings("unchecked")
   public B inContext(String contextId)
   {
      element.setContext(contextId);

      return (B) this;
   }

   @SuppressWarnings("unchecked")
   protected B forAccess(DirectionType direction)
   {
      if (null != direction)
      {
         element.setDirection(direction);
      }
      else
      {
         element.eUnset(PKG_CWM.getDataPathType_Direction());
      }

      return (B) this;
   }

}
