package org.eclipse.stardust.ui.web.modeler.model;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;

public class DataMappingJto extends ModelElementJto
{
   public DataMappingJto()
   {
      this.type = ModelerConstants.DATA_FLOW_LITERAL;
   }

   public String dataFullId;
   public String dataPath;
   public String accessPointId;
   public String accessPointContext;
   public String accessPointPath;

}
