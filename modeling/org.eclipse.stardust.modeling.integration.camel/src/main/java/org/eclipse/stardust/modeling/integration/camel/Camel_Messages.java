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

   public static String label_Transacted_Route;
   
   public static String label_AutoStartup_Route;

   public static String label_Body_Input_Access_Point;

   public static String label_Body_Output_Access_Point;

   public static String issue_No_Producer_Route_Definition_Specified_For_Application;

   public static String issue_No_Consumer_Route_Definition_Specified_For_Application;

   public static String issue_Application_Contains_Out_AccessPoint_While_Endpoint_Pattern_Is_Set_To;

   public static String issue_CamelContextID_is_Empty;

   public static String issue_No_Issues_Found;

   public static String label_None;

   public static String label_Data_Strucutre;

   public static String label_Data_Type;

   public static String label_Class_Name;

   public static String label_Data;

   public static String label_Document_Type;

   public static String label_Data_Structure;

   public static String label_Primittive_Type;

   public static String label_Parameters_Definition;
   
   public static String label_Camel_Consumer_Route;
   
   public static String label_Camel_Producer_Route;

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Camel_Messages.class);
   }

   private Camel_Messages()
   {}
}
