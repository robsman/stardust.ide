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
import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.directValue;
import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.elementById;

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;

public class BpmInDataMappingBuilder
      extends AbstractDataMappingBuilder<BpmInDataMappingBuilder>
{
   public BpmInDataMappingBuilder()
   {
      super(DirectionType.IN_LITERAL);
   }

   @Override
   protected DataMappingType finalizeElement()
   {
      return super.finalizeElement();
   }

   public static BpmInDataMappingBuilder newInDataMapping()
   {
      return new BpmInDataMappingBuilder();
   }

   public static BpmInDataMappingBuilder newInDataMapping(ActivityType activity)
   {
      return newInDataMapping().forActivity(activity);
   }

   public BpmInDataMappingBuilder fromVariable(String variableId)
   {
      setters.add(elementById(PKG_CWM.getDataMappingType_Data(),
            PKG_CWM.getModelType_Data(), variableId));

      return self();
   }

   public BpmInDataMappingBuilder fromVariable(DataType variable)
   {
      setters.add(directValue(PKG_CWM.getDataMappingType_Data(), variable));

      return self();
   }

   public BpmInDataMappingBuilder toApplicationAccessPoint(String accessPointId)
   {
      return toApplicationAccessPoint(accessPointId, null);
   }

   public BpmInDataMappingBuilder toApplicationAccessPoint(String accessPointId,
         String accessPath)
   {
      if ( !isEmpty(accessPointId))
      {
         element.setApplicationAccessPoint(accessPointId);
      }
      else
      {
         element.eUnset(PKG_CWM.getDataMappingType_ApplicationAccessPoint());
      }

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

}
