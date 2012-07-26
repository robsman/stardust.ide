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
package org.eclipse.stardust.model.xpdl.builder.datamapping;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import org.eclipse.stardust.model.xpdl.builder.common.AbstractActivityElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;


public class BpmDataMappingBuilder
      extends AbstractActivityElementBuilder<DataMappingType, BpmDataMappingBuilder>
{
   public BpmDataMappingBuilder()
   {
      super(F_CWM.createDataMappingType());
   }

   @Override
   protected DataMappingType finalizeElement()
   {
      super.finalizeElement();

      activity.getDataMapping().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "DataMapping";
   }

   public static BpmDataMappingBuilder newDataMapping()
   {
      return new BpmDataMappingBuilder();
   }

   public static BpmDataMappingBuilder newDataMapping(ActivityType activity)
   {
      return newDataMapping().forActivity(activity);
   }

   public static BpmDataMappingBuilder newInDataMapping(ActivityType activity)
   {
      return newDataMapping(activity).forAccess(DirectionType.IN_LITERAL);
   }

   public static BpmDataMappingBuilder newOutDataMapping(ActivityType activity)
   {
      return newDataMapping(activity).forAccess(DirectionType.OUT_LITERAL);
   }

   public BpmDataMappingBuilder inContext(String contextId)
   {
      element.setContext(contextId);

      return this;
   }

   public BpmDataMappingBuilder onVariable(DataType variable)
   {
      element.setData(variable);

      return this;
   }

   public BpmDataMappingBuilder usingApplicationAccessPoint(String accessPointId)
   {
      return usingApplicationAccessPoint(accessPointId, null);
   }

   public BpmDataMappingBuilder usingApplicationAccessPoint(String accessPointId,
         String accessPath)
   {
      element.setApplicationAccessPoint(accessPointId);

      if ( !isEmpty(accessPath))
      {
         element.setApplicationPath(accessPath);
      }
      else
      {
         element.eUnset(PKG_CWM.getDataMappingType_ApplicationPath());
      }

      return this;
   }

   public BpmDataMappingBuilder forAccess(DirectionType direction)
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

}
