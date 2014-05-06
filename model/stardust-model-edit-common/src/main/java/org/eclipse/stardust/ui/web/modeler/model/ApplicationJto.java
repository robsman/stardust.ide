package org.eclipse.stardust.ui.web.modeler.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;

import com.google.gson.JsonObject;

public class ApplicationJto extends ModelElementJto
{
   public ApplicationJto()
   {
      this.type = ModelerConstants.APPLICATION_KEY;
   }

   public String applicationType;

   public boolean interactive;
   public Map<String, JsonObject> contexts = new LinkedHashMap<String, JsonObject>();
   public Map<String, JsonObject> accessPoints = new LinkedHashMap<String, JsonObject>();

   // TODO more details
}
