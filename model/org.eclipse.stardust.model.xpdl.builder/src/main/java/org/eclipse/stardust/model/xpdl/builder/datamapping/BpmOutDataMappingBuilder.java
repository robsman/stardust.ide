package org.eclipse.stardust.model.xpdl.builder.datamapping;

import static org.eclipse.stardust.common.StringUtils.isEmpty;
import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.directValue;
import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.elementById;

import org.eclipse.stardust.model.xpdl.builder.common.Var;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;


public class BpmOutDataMappingBuilder
      extends AbstractDataMappingBuilder<BpmOutDataMappingBuilder>
{
   public BpmOutDataMappingBuilder()
   {
      super(DirectionType.OUT_LITERAL);
   }

   @Override
   protected DataMappingType finalizeElement()
   {
      return super.finalizeElement();
   }

   public static BpmOutDataMappingBuilder newOutDataMapping()
   {
      return new BpmOutDataMappingBuilder();
   }

   public static BpmOutDataMappingBuilder newOutDataMapping(ActivityType activity)
   {
      return newOutDataMapping().forActivity(activity);
   }

   public BpmOutDataMappingBuilder toVariable(String variableId)
   {
      setters.add(elementById(PKG_CWM.getDataMappingType_Data(), PKG_CWM.getModelType_Data(), variableId));

      return self();
   }

   public BpmOutDataMappingBuilder toVariable(DataType variable)
   {
      setters.add(directValue(PKG_CWM.getDataMappingType_Data(), variable));

      return self();
   }

   public <T> BpmOutDataMappingBuilder toVariable(Var<T> variable)
   {
      setters.add(elementById(PKG_CWM.getDataMappingType_Data(), PKG_CWM.getModelType_Data(), variable.variableId()));

      return self();
   }

   public BpmOutDataMappingBuilder fromApplicationAccessPoint(String accessPointId)
   {
      return fromApplicationAccessPoint(accessPointId, null);
   }

   public BpmOutDataMappingBuilder fromApplicationAccessPoint(String accessPointId,
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
