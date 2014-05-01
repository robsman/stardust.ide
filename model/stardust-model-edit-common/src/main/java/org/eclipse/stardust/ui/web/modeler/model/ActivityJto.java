package org.eclipse.stardust.ui.web.modeler.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;

public class ActivityJto extends ModelElementJto
{
   public ActivityJto()
   {
      this.type = ModelerConstants.ACTIVITY_KEY;
   }

   public String activityType;
   public String taskType;
   public String participantFullId;
   public String applicationFullId;
   public String subprocessFullId;

//   public Map<String, DataMappingJto> inDataFlows = new LinkedHashMap<String, DataMappingJto>();
//   public Map<String, DataMappingJto> outDataFlows = new LinkedHashMap<String, DataMappingJto>();

}