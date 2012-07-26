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
package org.eclipse.stardust.model.xpdl.builder.process;

import org.eclipse.stardust.model.xpdl.builder.common.AbstractProcessElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;


public class BpmDataPathBuilder
      extends AbstractProcessElementBuilder<DataPathType, BpmDataPathBuilder>
{
   public BpmDataPathBuilder(ProcessDefinitionType process)
   {
      super(process, F_CWM.createDataPathType());
   }

   @Override
   protected DataPathType finalizeElement()
   {
      if (element.isDescriptor())
      {
         forReading();
      }

      process.getDataPath().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "DataPath";
   }

   public static BpmDataPathBuilder newDataPath(ProcessDefinitionType process)
   {
      return new BpmDataPathBuilder(process);
   }

   public static BpmDataPathBuilder newInDataPath(ProcessDefinitionType process)
   {
      return newDataPath(process).forReading();
   }

   public static BpmDataPathBuilder newOutDataPath(ProcessDefinitionType process)
   {
      return newDataPath(process).forWriting();
   }

   public static BpmDataPathBuilder newDescriptor(ProcessDefinitionType process)
   {
      return newDataPath(process).asDescriptor();
   }

   public BpmDataPathBuilder onVariable(DataType variable)
   {
      element.setData(variable);

      return this;
   }

   public BpmDataPathBuilder forAccess(DirectionType direction)
   {
      if (null != direction)
      {
         element.setDirection(direction);
      }
      else
      {
         element.eUnset(PKG_CWM.getDataPathType_Direction());
      }

      return this;
   }

   public BpmDataPathBuilder forReading()
   {
      forAccess(DirectionType.IN_LITERAL);

      return this;
   }

   public BpmDataPathBuilder forWriting()
   {
      forAccess(DirectionType.OUT_LITERAL);

      return this;
   }

   public BpmDataPathBuilder forReadWrite()
   {
      forAccess(DirectionType.INOUT_LITERAL);

      return this;
   }

   public BpmDataPathBuilder asDescriptor()
   {
      return asDescriptor(true);
   }

   public BpmDataPathBuilder asDescriptor(boolean descriptor)
   {
      element.setDescriptor(descriptor);

      return this;
   }

}
