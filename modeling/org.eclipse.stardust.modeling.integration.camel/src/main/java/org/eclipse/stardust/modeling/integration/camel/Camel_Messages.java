package org.eclipse.stardust.modeling.integration.camel;

import org.eclipse.osgi.util.NLS;

public class Camel_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.integration.camel.camel-messages"; //$NON-NLS-1$

   public static String label_additionalSpringBeanDef;

   public static String label_CamelContextId;

   public static String label_Route;

   public static String label_Invocation_Pattern;

   public static String label_Invocation_Pattern_Send;

   public static String label_Invocation_Pattern_SendReceive;

   public static String label_Invocation_Pattern_Receive;

   public static String label_Invocation_Type;

   public static String label_Invocation_Type_Sync;

   public static String label_Invocation_Type_Async;

   public static String label_Endpoint_Type;

   public static String label_EndpointSettings;

   public static String label_AdditionalBean;

   public static String label_Include_Process_Context_Headers;

   public static String label_Body_Input_Access_Point;

   public static String label_Body_Output_Access_Point;

   public static String issue_No_Producer_Route_Definition_Specified_For_Application;

   public static String issue_No_Consumer_Route_Definition_Specified_For_Application;


   // public static String label_
   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Camel_Messages.class);
   }

   private Camel_Messages()
   {}
}
