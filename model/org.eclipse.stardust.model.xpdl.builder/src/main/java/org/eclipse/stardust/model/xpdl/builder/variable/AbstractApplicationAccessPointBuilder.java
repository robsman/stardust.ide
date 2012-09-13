/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.model.xpdl.builder.variable;

import org.eclipse.stardust.model.xpdl.builder.common.AbstractIdentifiableElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;

public class AbstractApplicationAccessPointBuilder <T extends AccessPointType, B extends AbstractApplicationAccessPointBuilder<T, B>>
extends AbstractIdentifiableElementBuilder<T, B>
{
   private ApplicationType application;

   public AbstractApplicationAccessPointBuilder(T element)
   {
      super(element);
   }

   @Override
   protected String deriveDefaultElementId()
   {
      // TODO Auto-generated method stub
      return null;
   }

   protected B inApplication (ApplicationType application) {
      setApplication(application);
      return self();
   }

   protected void setApplication(ApplicationType application)
   {
      if (null == this.application)
      {
         if (null != application)
         {
            this.application = application;
         }
      }
      else
      {
         if (this.application != application)
         {
            throw new IllegalArgumentException("Application must only be set once.");
         }
      }
   }

   public B withDirection(DirectionType direction)
   {
      element.setDirection(direction);
      return self();
   }

   public B withTypeType(DataTypeType dataTypeType)
   {
      element.setType(dataTypeType);
      return self();
   }


}
